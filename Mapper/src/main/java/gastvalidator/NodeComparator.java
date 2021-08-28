package gastvalidator;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.AggregateTypeDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.FunctionDefintion;
import ASTMCore.ASTMSyntax.Expression.Literal;
import ASTMCore.ASTMSyntax.Statement.BlockStatement;
import ASTMCore.ASTMSyntax.Statement.ExpressionStatement;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionNode;
import gastvalidator.RPG2GASTMCertifier.Statements.AssigmentStatement;
import org.apache.commons.lang3.StringEscapeUtils;

/**
 * Clase para comparar dos nodos y decidir si bajar un nivel extra dependiendo
 * del caso. Imprimir los valores encontrados en los elementos terminales.
 * Escribir en el registro de diferencias.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
/**
 * @author laba1
 *
 */
public class NodeComparator {
	private Map<String, ArrayList<String>> dictionaryMethods;
	private Validator validatorGeneric;
	private ValidatorFactory validatorFactory;
	private String sourceCodeLanguage;
	private MetaLanguageValidator metaLanguageValidator;
	private String filePath;
	protected static ArrayList<DifferenceLog> differencesList = new ArrayList<DifferenceLog>();

	// Casos de Java para analizar el nombre sin tener que obtener un subarbol
	static final String[] LANGUAGE_RESERVED_CLASS = { "QualifiedName", "NameSpaceDefinition" };
	// Etiquetas o keywords con valores en hojas
	static final String[] RESERVED_VALUES = { "value", "keyword", "name", "token", "op", "identifier", "escapedValue",
			"tokenValue" };
	// Clase de nodos para con valor de baseType
	static final String[] METALANGUAGE_RESERVED_CLASS = { "NamedTypeReference", "ParameterizedType",
			"ImportDeclaration", "NameSpaceDefinition", "Name" };
	// Clase de Java para crear arreglos
	static final String[] ARRAY_TYPES_LANGUAGE = { "ArrayType" };
	// Valores en diccionario que contiene excepciones
	static final String[] SPECIAL_CASES_DICTIONARY = { "openScope_list", "member_body", "declaration",
			"definitionType" };
	static final String[] SPECIAL_CASES_DICTIONARY_VALUES = { "getAggregateType", "getMember", "getDeclOrDefn",
			"getDefinitionType" };
	// Valores de metodos para obtener el tipo en el metalenguaje
	static final String[] METALANGUAGE_METHODS_TYPES = { "getDefinitionType", "getExpressionType", "getTarget" };

	/**
	 * Constructor de la clase para asignar valores a los atributos.
	 * 
	 * @param language          lenguaje de programación del código fuente.
	 * @param dictionaryMethods hashmap con el diccionario de métodos de ambas
	 *                          estructuras.
	 * @param validatorFactory  fábrica para ejecutar casos especiales para cada
	 *                          lenguaje.
	 * @param filePath          dirección absoluta con el archivo con el código
	 *                          fuente.
	 */
	public NodeComparator(String language, Map<String, ArrayList<String>> dictionaryMethods,
			ValidatorFactory validatorFactory, String filePath) {

		this.sourceCodeLanguage = language;
		this.dictionaryMethods = dictionaryMethods;
		this.validatorFactory = validatorFactory;
		this.filePath = filePath;
	}

	/**
	 * Recibe dos nodos y verifica los metodos iguales para bajar de nivel u obtener
	 * los valores.
	 * 
	 * @param parentNodeLanguage     nodo padre del AST.
	 * @param parentNodeMetalanguage nodo padre del ASTM.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 * @throws NoSuchMethodException     si se invoca un método que no existe en el
	 *                                   objeto.
	 * @throws SecurityException         si se trata de acceder a un valor
	 *                                   protegido.
	 * @throws IOException               si ocurre un problema en la lectura o
	 *                                   escritura de un archivo.
	 */
	public void analyzeNodes(Object parentNodeLanguage, Object parentNodeMetalanguage) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

		this.validatorGeneric = this.validatorFactory.getValidator(this.sourceCodeLanguage, this.dictionaryMethods,
				this.validatorFactory, this.filePath);
		this.metaLanguageValidator = new MetaLanguageValidator(this.sourceCodeLanguage, this.dictionaryMethods,
				this.validatorFactory, this.filePath);


		if (parentNodeMetalanguage instanceof CompilationUnit && this.sourceCodeLanguage == "RPG"){
			Object[] dod = (Object[]) ((CompilationUnit) parentNodeMetalanguage).getOpensScope().getDeclOrDefn().toArray();
			AggregateTypeDefinition atd = (AggregateTypeDefinition)dod[0];
			Object[] dod2 = atd.getAggregateType().getOpensScope().getDeclOrDefn().toArray();
			FunctionDefintion fd = (FunctionDefintion)dod2[0];
			parentNodeMetalanguage = fd.getBody();
		}
		boolean flagMethodsMatch = false;
		// Metodos del Metalenguaje
		Method[] methodsNodeMetalanguage = parentNodeMetalanguage.getClass().getMethods();
		for (Method methodMetalanguage : methodsNodeMetalanguage) {
			// Busca en el diccionario un método existente en META que coincida con el AST
			// del lenguaje original
			ArrayList<String> vectorValueDictionary = this.dictionaryMethods.get(methodMetalanguage.getName());
			if (vectorValueDictionary != null) {
				for (String valueDictionary : vectorValueDictionary) {
					if (valueDictionary != null && parentNodeLanguage != null) {
						// Metodos del AST lenguaje original
						Method methodsNodeLanguage[] = parentNodeLanguage.getClass().getMethods();
						for (Method methodLanguage : methodsNodeLanguage) {
							// Busca el metodo correspondiente al del Metalenguaje
							if (valueDictionary.contentEquals(methodLanguage.getName())
									&& (!conditionalImports(methodMetalanguage, valueDictionary,
											parentNodeMetalanguage))) {

								flagMethodsMatch = true;
								// El metodo encontrado sera ejecutado en ambos nodos para obtener una misma
								// estructura
								Object childrenNodeLanguage = null;
								Object childrenNodeMetalanguage = null;
								try {
									childrenNodeLanguage = methodLanguage.invoke(parentNodeLanguage);
									childrenNodeMetalanguage = methodMetalanguage.invoke(parentNodeMetalanguage);
								} catch (Exception e) {
									// TODO: handle exception
								}
								// Caso especial de elementos para los metodos y variables que conforman el
								// codigo no toma en cuenta imports o paquetes
								if (valueDictionary.contentEquals("bodyDeclarations")) {

									this.validatorGeneric.analyzeBody(childrenNodeLanguage, parentNodeMetalanguage);

								} else if (parentNodeMetalanguage.getClass().getName().contains("SwitchStatement")
										&& methodMetalanguage.getName().contains("getCases")) {

									this.validatorGeneric.analyzeCasesSwitch(childrenNodeLanguage,
											childrenNodeMetalanguage);

								} else if (childrenNodeLanguage == null || childrenNodeMetalanguage == null
										|| (parentNodeLanguage.getClass().getName().contains("ParenthesizedExpression"))
												&& (parentNodeMetalanguage.getClass().getName()
														.contains("FunctionCallExpression")
														|| parentNodeMetalanguage.getClass().getName()
																.contains("CastExpression"))
										|| parentNodeMetalanguage.getClass().getName()
												.contains("ActualParameterExpression")) {
									// Caso especial donde uno de los AST presenta el metodo, pero devuelve nulo
									// de manera que son nodos no importantes para el analisis porque contienen
									// valores no deseados
									// Tambien los parentesis contienen expresion y se extrae valor incorrecto
									flagMethodsMatch = false;
								} else if (parentNodeLanguage.getClass().getName()
										.contains("ParenthesizedExpression")) {
									break;
								} else if (childrenNodeLanguage.getClass().getName()
										.contains("ParenthesizedExpression")) {
									Method getExpressionMethod = getMethodByName(childrenNodeLanguage, "getExpression");
									try {
										childrenNodeLanguage = getExpressionMethod.invoke(childrenNodeLanguage);
										analyzeNodes(childrenNodeLanguage, childrenNodeMetalanguage);
										break;
									} catch (Exception e) {
										// TODO: handle exception
									}
								} else if (isList(childrenNodeLanguage.getClass())) {
									// Caso donde el nodo del lenguaje sea una lista

									// Hay listas que son HashSet, por lo que deben ser convertidos a una lista
									if (!isList(childrenNodeMetalanguage.getClass())) {
										if (childrenNodeMetalanguage.getClass().toString().contains("HashSet")) {
											childrenNodeMetalanguage = convertHashSetToList(childrenNodeMetalanguage);
										} else {
											// Conversion HashSet a Lista
											childrenNodeMetalanguage = convertHashSetToList(this.metaLanguageValidator
													.getList(childrenNodeMetalanguage, this.dictionaryMethods));
										}
									}
									Method methodGetSize = getMethodByName(childrenNodeMetalanguage, "size");
									// Calculo del largo de la lista del Metalenguaje
									int sizeNodeMetalanguage = (int) methodGetSize.invoke(childrenNodeMetalanguage);
									int sizeNodeMetalanguageCopy = (int) methodGetSize.invoke(childrenNodeMetalanguage);
									// Calculo del largo de la lista del lenguaje original
									int sizeNodeLanguage = (((List<?>) childrenNodeLanguage).size());

									if (sizeNodeLanguage == 0 || sizeNodeMetalanguage == 0) {
										break;
									} else if (sizeNodeMetalanguage > 0) {
										// Se debe ordenar la lista de manera que los elementos tengan el mismo orden
										// por id.
										// En el META el orden de aparicion varia con los del AST
										((List<?>) childrenNodeMetalanguage).removeAll(Collections.singletonList(null));
										Collections.sort(((List<?>) childrenNodeMetalanguage), new SortingAlgorithm());
									}

									sizeNodeMetalanguageCopy = (int) methodGetSize.invoke(childrenNodeMetalanguage);

									if (sizeNodeMetalanguage != sizeNodeMetalanguageCopy) {
										break;
									} else if (sizeNodeLanguage != sizeNodeMetalanguage) {
										//System.out.println("Listas de distinto tamaño en archivo " + this.filePath);
										break;
									}
									// Analisis de cada elemento de la lista que sera un nodo, por lo que se llama
									// de nuevo al metodo
									for (int i = 0; i < sizeNodeLanguage; i++) {
										Object elementListLanguage = (((List<?>) childrenNodeLanguage).get(i));
										Object elementListMetalanguage = (((List<?>) childrenNodeMetalanguage).get(i));
										if (elementListLanguage != null && elementListMetalanguage != null) {
											analyzeNodes(elementListLanguage, elementListMetalanguage);
										}
									}
								} else if (isList(childrenNodeMetalanguage.getClass())) {
									// Caso especial donde el Metalenguaje sea una lista, pero el lenguaje original
									// no
									// Ejemplos: extends clase
									// Obtiene el largo de la lista del metalenguaje
									Method methodGetSizeList = getMethodByName(childrenNodeMetalanguage, "size");
									int sizeNodeMetalanguage = (int) methodGetSizeList.invoke(childrenNodeMetalanguage);

									// Analisis de cada elemento de la lista que sera un nodo, por lo que se llama
									// de nuevo al metodo
									for (int i = 0; i < sizeNodeMetalanguage; i++) {
										Object element_Me = (((List<?>) childrenNodeMetalanguage).get(i));
										analyzeNodes(childrenNodeLanguage, element_Me);
									}
								} else if (elementInList(childrenNodeLanguage.getClass().getName(),
										ARRAY_TYPES_LANGUAGE)) {
									// caso especial donde hay arreglos y se debe analizar el nodo del lenguaje por
									// separado

									analyzeArrays(childrenNodeLanguage, childrenNodeMetalanguage,
											parentNodeMetalanguage);
									break;

								} else if ((elementInList(childrenNodeLanguage.getClass().getName(),
										LANGUAGE_RESERVED_CLASS)
										&& (elementInList(parentNodeMetalanguage.getClass().getName(),
												METALANGUAGE_RESERVED_CLASS)
												|| elementInList(childrenNodeMetalanguage.getClass().getName(),
														METALANGUAGE_RESERVED_CLASS)))
										|| nodesHaveValue(childrenNodeLanguage, childrenNodeMetalanguage)) {

									if (childrenNodeLanguage.getClass().getName().contains("ParenthesizedExpression")) {
										break;
									}
									// Caso especial donde se puede ya se puede detectar el nombre del nodo

									Method getTypeNameMethod = getMethodByName(childrenNodeMetalanguage, "getTypeName");
									if (getTypeNameMethod != null) {
										try {
											childrenNodeMetalanguage = getTypeNameMethod
													.invoke(childrenNodeMetalanguage);
										} catch (Exception e) {
											// TODO: handle exception
										}
									} else if (elementInList(childrenNodeLanguage.getClass().getName(),
											LANGUAGE_RESERVED_CLASS)) {
										// El metalenguaje no presenta 'value', pero el lenguaje si
										childrenNodeMetalanguage = this.metaLanguageValidator
												.getMetalanguageNodeName(childrenNodeMetalanguage);
									} else if (!elementHasValue(childrenNodeMetalanguage)) {
										childrenNodeMetalanguage = this.metaLanguageValidator.getMetalanguageNodeName(
												childrenNodeMetalanguage, this.dictionaryMethods);
									}

									Method getNameStringMethod = getMethodByName(childrenNodeMetalanguage,
											"getNameString");
									if (getNameStringMethod != null) {
										try {
											childrenNodeMetalanguage = getNameStringMethod
													.invoke(childrenNodeMetalanguage);
										} catch (Exception e) {
											// TODO: handle exception
										}
									}

									if ((elementHasValue(childrenNodeLanguage)
											&& elementHasValue(childrenNodeMetalanguage))
											|| parentNodeLanguage.getClass().getName().contains("PackageDeclaration")
											|| childrenNodeLanguage.getClass().getName().contains("ThisExpression")) {
										// Imprimir los valores obtenidos
										if(childrenNodeLanguage instanceof ExpressionNode){
											childrenNodeLanguage = ((ExpressionNode) childrenNodeLanguage).getValue();
											try {
												childrenNodeMetalanguage = ((Literal)childrenNodeMetalanguage).getValue();
											} catch (Exception e) {
												//e.printStackTrace();
											}
										}
										DifferenceLog log = printNodeValues(childrenNodeLanguage,
												childrenNodeMetalanguage, 0, parentNodeMetalanguage);
										addElement(log);
										break;
									}
								} else {
									// Seguir analizando los nodos
									analyzeNodes(childrenNodeLanguage, childrenNodeMetalanguage);
								}
							}
						}

						// Casos especiales con los valores del diccionario en que hay que bajar un nodo
						// mas porque no coinciden los nodos para el META
						if (elementInList(valueDictionary, SPECIAL_CASES_DICTIONARY)) {
							boolean classIsEnum = true;
							if (valueDictionary.contentEquals("definitionType")) {
								if (!parentNodeMetalanguage.getClass().getSimpleName()
										.contentEquals("EnumTypeDefinition")) {
									classIsEnum = false;
								}
							}
							if (classIsEnum) {
								Object childrenMetalanguage = getObjectByIndex(valueDictionary,
										SPECIAL_CASES_DICTIONARY, SPECIAL_CASES_DICTIONARY_VALUES,
										parentNodeMetalanguage);
								if (childrenMetalanguage != null) {
									analyzeNodes(parentNodeLanguage, childrenMetalanguage);
								}
							}
						}
					}
				}
			}

			// Caso especial para bajar un nivel mas para baseType
			if (elementInList(parentNodeMetalanguage.getClass().getName(), METALANGUAGE_RESERVED_CLASS)
					&& elementInList(parentNodeLanguage.getClass().getName(), METALANGUAGE_RESERVED_CLASS)) {
				if (methodMetalanguage.getName().toLowerCase().contains("type")
						&& methodMetalanguage.getName().toLowerCase().contains("get")) {
					Object typeMetalanguageNode = methodMetalanguage.invoke(parentNodeMetalanguage);
					if (typeMetalanguageNode != null) {
						analyzeNodes(parentNodeLanguage, typeMetalanguageNode);
					}
				}
			}

			if (elementInList(methodMetalanguage.getName(), METALANGUAGE_METHODS_TYPES)) {
				this.metaLanguageValidator.getTypeNode(parentNodeMetalanguage, METALANGUAGE_METHODS_TYPES,
						this.dictionaryMethods);
			}

		}

		// Verificar casos especiales de Java para bajar un nivel
		if (parentNodeLanguage != null && parentNodeMetalanguage != null && !flagMethodsMatch) {
			if (elementInList(parentNodeLanguage.getClass().getName(), ARRAY_TYPES_LANGUAGE)) {
				analyzeArrays(parentNodeLanguage, parentNodeMetalanguage, parentNodeMetalanguage);
			} else if (parentNodeMetalanguage.getClass().toString().contains("FunctionCallExpression")) {
				try {
					Object newChildrenMetaLanguage = getMethodByName(parentNodeMetalanguage, "getCalledFunction")
							.invoke(parentNodeMetalanguage);
					analyzeNodes(parentNodeLanguage, newChildrenMetaLanguage);
				} catch (Exception e) {
					// TODO: handle exception
				}
			} else {
				// Caso especial de bajar un nivel en el META para FunctionCallExpression
				validatorGeneric.analyzeSpecialCases(parentNodeLanguage, parentNodeMetalanguage);
			}
		}

	}

	/**
	 * Se obtiene el tipo y dimensiones de un arreglo
	 * 
	 * @param nodeLanguage           nodo con el arreglo en el AST del lenguaje.
	 * @param nodeMetalanguage       nodo con el arreglo en el ASTM.
	 * @param parentNodeMetalanguage nodo padre del ASTM para obtener el ID.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 * @throws NoSuchMethodException     si se invoca un método que no existe en el
	 *                                   objeto.
	 * @throws SecurityException         si se trata de acceder a un valor
	 *                                   protegido.
	 * @throws IOException               si ocurre un problema en la lectura o
	 *                                   escritura de un archivo.
	 */
	private void analyzeArrays(Object nodeLanguage, Object nodeMetalanguage, Object parentNodeMetalanguage)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, IOException {
		// Buscar el tipo de arreglo
		Object arrayTypeLanguage = this.validatorGeneric.getArrayType(nodeLanguage, "ArrayType");
		Object arrayTypeMetalanguage = null;

		Method methodDataTypeMetalanguage = getMethodByName(nodeMetalanguage, "getDataType");

		if (methodDataTypeMetalanguage != null) {
			arrayTypeMetalanguage = methodDataTypeMetalanguage.invoke(nodeMetalanguage);
			if (arrayTypeMetalanguage != null) {

				int dimensionArrayLanguage = this.validatorGeneric.getDimensionArray(nodeLanguage, "ArrayType", 0);

				Method methodAmountDimension = getMethodByName(arrayTypeMetalanguage, "getAmountDimension");
				int dimensionArrayMetalanguage = (int) (methodAmountDimension).invoke(arrayTypeMetalanguage);

				DifferenceLog log = printNodeValues(dimensionArrayLanguage, dimensionArrayMetalanguage, 0,
						parentNodeMetalanguage);
				addElement(log);
				analyzeNodes(arrayTypeLanguage, arrayTypeMetalanguage);
			}
		}
	}

	/**
	 * Verifica que el elemento es una lista.
	 * 
	 * @param objectClass Clase del nodo.
	 * @return verdadero o falso si el objeto es una lista.
	 */
	private boolean isList(Class<? extends Object> objectClass) {
		return objectClass.getSimpleName().contains("List");
	}

	/**
	 * Convierte un HashSet a lista.
	 * 
	 * @param object que corresponde a un hashset para ser transformado.
	 * @return una lista a partir de un hashset.
	 */
	protected List<?> convertHashSetToList(Object object) {
		List<?> list = new ArrayList<Object>((HashSet<?>) object);
		return list;

	}

	/**
	 * Devuelve un metodo de un objeto con un nombre especifico.
	 * 
	 * @param object nodo que será analizado.
	 * @param method método que será buscado en el objeto.
	 * @return Método asociado al nodo.
	 * @throws NoSuchMethodException si se invoca un método que no existe en el
	 *                               objeto.
	 * @throws SecurityException     si se trata de acceder a un valor protegido.
	 */
	protected Method getMethodByName(Object object, String method) throws NoSuchMethodException, SecurityException {
		try {
			return object.getClass().getMethod(method);
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Busca si un elemento se encuentra en la lista de tipos reservados.
	 * 
	 * @param value      elemento por buscar en la lista.
	 * @param listValues lista con posibles valores.
	 * @return si el elemento sí se encuentra en la lista.
	 */
	protected boolean elementInList(String value, String[] listValues) {
		for (int i = 0; i < listValues.length; i++) {
			if (value.contains(listValues[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Obtener indice de elemento de lista.
	 * 
	 * @param value      elemento por buscar en la lista.
	 * @param listValues lista con posibles valores.
	 * @return índice que presenta el elemento en la lista.
	 */
	private int getIndexElement(String value, String[] listValues) {
		for (int i = 0; i < listValues.length; i++) {
			if (value.contains(listValues[i])) {
				return i;
			}
		}
		return 0;
	}

	/**
	 * Obtener un objeto según el nombre del metodo asociado. La idea es recibir un
	 * método, buscar un homólogo e invocarlo en el nodo.
	 * 
	 * @param methodName  método que se invocará en el nodo.
	 * @param listMethods lista de posibles métodos.
	 * @param listValues  lista de nombres de métodos asociados a la lista que son
	 *                    correspondientes.
	 * @param node        nodo que será analizado.
	 * @return Subnodo que será invocado mediante un método correspondiente a un
	 *         valor.
	 */
	protected Object getObjectByIndex(String methodName, String[] listMethods, String[] listValues, Object node) {
		int indexValue = getIndexElement(methodName, listMethods);
		String stringMethodToInvoke = listValues[indexValue];
		try {
			Method method = getMethodByName(node, stringMethodToInvoke);
			return method.invoke(node);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * Revisa si los nodos son hoja al buscar el campo <i>value</i> o similares. En
	 * este caso el valor es <i>true</i> si alguno de los candidatos corresponde a
	 * un nodo terminal.
	 * 
	 * @param nodeLanguage     nodo del AST de Java
	 * @param nodeMetalanguage nodo del ASTM.
	 * @return Operación entre varios operandos para determinar si entre todos hay
	 *         uno que sea terminal.
	 */
	private boolean nodesHaveValue(Object nodeLanguage, Object nodeMetalanguage) {
		boolean valueLanguage = elementHasValue(nodeLanguage);
		boolean valueMetalanguage = elementHasValue(nodeMetalanguage);
		// Caso especial para la palabra clave 'this'.
		if (nodeLanguage.getClass().toString().contains("ThisExpression")) {
			boolean flag = nodeMetalanguage.toString().contains("Memberaccess");
			valueMetalanguage = flag ? valueMetalanguage : false;
		}
		// Hay nodos en el metalenguaje que presentan un campo value pero solo se puede
		// saber al obtener los datos
		boolean valueMethodMetalanguage = elementHasValueMethod(nodeMetalanguage);
		return valueLanguage || valueMetalanguage || valueMethodMetalanguage;
	}

	/**
	 * Revisa un nodo para ver si tiene el método value.
	 * 
	 * @param node nodo que será analizado.
	 * @return si el nodo analizado presenta el método <i>getValue</i>.
	 */
	private boolean elementHasValueMethod(Object node) {
		boolean value = false;
		Method[] methodsNode = node.getClass().getMethods();
		for (Method method : methodsNode) {
			String name = method.getName();
			if (name.contains("getValue")) {
				value = true;
				break;
			}
		}
		return value;
	}

	/**
	 * Revisa un nodo para ver si tiene un campo con valores de hoja.
	 * 
	 * @param node nodo que será analizado.
	 * @return el nodo corresponde a un nodo terminal.
	 */
	protected boolean elementHasValue(Object node) {
		boolean value = false;
		if (node != null) {
			Field[] fieldsNode = node.getClass().getDeclaredFields();
			for (Field field : fieldsNode) {
				String name = field.getName();
				// Revisa los posibles campos con una lista de opciones para determinar si es
				// nodo terminal.
				for (String reservedValue : RESERVED_VALUES) {
					// Caso especiales que las clases presentan estas palabras reservados pero no
					// son hojas.
					if (name.contentEquals(reservedValue) && !node.getClass().getName().contains("QualifiedName")
							&& !node.getClass().getName().contains("SuperMethodInvocation")) {
						value = true;
						break;
					}
				}
			}
		}
		return value;
	}

	/**
	 * Condición para los imports para no ser analizados internamente por cuestiones
	 * de cómo está estructurado el nombre.
	 * 
	 * @param methodLanguage     método actual del AST.
	 * @param methodMetalanguage método actual del ASTM.
	 * @param node               nodo del ASTM que será analizado.
	 * @return consiste en un import, por lo tanto no será analizado internamente.
	 */
	private boolean conditionalImports(Method methodLanguage, String methodMetalanguage, Object node) {
		boolean flagMethodLanguage = methodLanguage.getName().contentEquals("getIdentifierName") ? true : false;
		boolean flagMethodMetalanguage = methodMetalanguage.contentEquals("getName");
		boolean flagNode = node.getClass().getName().contains("QualifiedOverData") ? true : false;
		return flagMethodLanguage && flagNode && flagMethodMetalanguage;
	}

	/**
	 * Funcion para imprimir los valores de las hojas.
	 * 
	 * @param nodeLanguage           hoja del AST.
	 * @param nodeMetalanguage       hoja del ASTM.
	 * @param typeDifference         si presentan diferencias en los nombres (0) o
	 *                               si el nodo no se encuentra en el AST (1).
	 * @param parentNodeMetalanguage nodo padre del ASTM para obtener el id del
	 *                               mismo.
	 * @throws NoSuchMethodException     si se invoca un método que no existe en el
	 *                                   objeto.
	 * @throws SecurityException         si se trata de acceder a un valor
	 *                                   protegido.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 * @throws IOException               si ocurre un problema en la lectura o
	 *                                   escritura de un archivo.
	 */
	protected DifferenceLog printNodeValues(Object nodeLanguage, Object nodeMetalanguage, int typeDifference,
			Object parentNodeMetalanguage) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {

		// Los String en el AST presentan comillas dobles, por lo que se debe eliminar
		// un par.
		nodeLanguage = removeBlankSpaces(nodeLanguage.toString());
		nodeMetalanguage = removeBlankSpaces(nodeMetalanguage.toString());

		// Hay strings que contienen caracteres en unicode y deben ser transformados
		// para poder compararlos
		String nameNodeLanguage = "";
		try {
			nameNodeLanguage = StringEscapeUtils.unescapeJava(nodeLanguage.toString());
		} catch (Exception e) {
			nameNodeLanguage = nodeLanguage.toString();
		}

		String nameNodeMetaLanguage = nodeMetalanguage.toString();
		if (nameNodeLanguage.length() > 0 && nameNodeLanguage.charAt(0) == '"'
				&& nameNodeLanguage.charAt(nameNodeLanguage.length() - 1) == '"') {
			nameNodeLanguage = nameNodeLanguage.substring(1, nameNodeLanguage.length() - 1);
		}

		// El ASTM no muestra == sino 'equal', entonces debe ser cambiado.
		if (nodeMetalanguage.toString().contentEquals("equal") && nameNodeLanguage.contentEquals("==")) {
			nameNodeMetaLanguage = "==";
		}

		// Si es 'Class' o 'Enum' deben mostrarse como diferencias.
		if (nodeMetalanguage.toString().contentEquals("Class") || nodeMetalanguage.toString().contentEquals("Enum")
				|| nodeMetalanguage.toString().contentEquals("Array")) {
			typeDifference = 1;
			nameNodeLanguage = "";
		}

		// No tomar en cuenta la palabra 'this' en las diferencias ya que son muchas
		if (nodeLanguage.toString().contentEquals("this")) {
			typeDifference = 3;
		}

		DifferenceLog differenceLog = null;
		// Se crea un objeto para almacenar la información de la diferencia.
		if (!nameNodeMetaLanguage.contentEquals(nameNodeLanguage)) {
			long idNodeMetalanguage = getNodeID(parentNodeMetalanguage);
			if (!searchDuplicateURL(nodeMetalanguage.toString(), idNodeMetalanguage)) {
				differenceLog = new DifferenceLog(this.filePath, idNodeMetalanguage, typeDifference,
						nameNodeMetaLanguage, nameNodeLanguage);
			}
		}
		return differenceLog;
	}

	/**
	 * Dentro de la lista para escribir en el documento se buscan por si hay
	 * mensajes iguales, pero con nodos distintos. De esta manera se indica si
	 * existe una misma diferencia para el archivo en específico.
	 * 
	 * @param difference valor distinto encontrado en el ASTM.
	 * @param idNode     id del nodo del ASTM.
	 * @return falso si no hay otra diferencia, verdadero si hay otra diferencia.
	 */
	private boolean searchDuplicateURL(String difference, long idNode) {
		boolean returnFlag = false;
		for (int i = NodeComparator.differencesList.size() - 1; i >= 0; i--) {
			DifferenceLog differenceLog = NodeComparator.differencesList.get(i);
			if (differenceLog.getFilePath().contentEquals(this.filePath)
					&& differenceLog.getASTMValue().contentEquals(difference)) {
				// Se agrega el id del nodo a la lista
				NodeComparator.differencesList.get(i).addIdNode(idNode);
				returnFlag = true;
				break;
			}

			// Para evitar comparaciones innecesarias, una vez que los nombres de archivos
			// no coincidan, se cancela la iteración.
			if (!this.filePath.contentEquals(differenceLog.getFilePath())) {
				break;
			}
		}
		return returnFlag;
	}

	/**
	 * Elimina espacios generados por cambios de línea, tabs, entre otros.
	 * 
	 * @param text texto obtenido de un árbol.
	 * @return texto sin espacios generados por cambios de línea.
	 */
	private String removeBlankSpaces(String text) {
		return text.replaceAll("\\t|\\r|\\n|\\f|\\\\t|\\\\r|\\\\n|\\\\f", "");
	}

	/**
	 * Obtener el id del nodo en el ASTM.
	 * 
	 * @param node nodo del ASTM que será analizado.
	 * @return número de id del nodo.
	 * @throws NoSuchMethodException     si se invoca un método que no existe en el
	 *                                   objeto.
	 * @throws SecurityException         si se trata de acceder a un valor
	 *                                   protegido.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 */
	private long getNodeID(Object node) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		try {
			Method getIdMethod = getMethodByName(node, "getId");
			return (long) getIdMethod.invoke(node);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Agrega elementos a la lista de diferencias.
	 * 
	 * @param logDifference información detallada de la ubicación y tipo de
	 *                      diferencia encontrada.
	 */
	public void addElement(DifferenceLog logDifference) {
		if (logDifference != null) {
			NodeComparator.differencesList.add(logDifference);
		}
	}

	/**
	 * Getter de la lista de diferencias del archivo.
	 * 
	 * @return Lista de String con cada una de las diferencias.
	 */
	public ArrayList<DifferenceLog> getDifferences() {
		return NodeComparator.differencesList;
	}

}