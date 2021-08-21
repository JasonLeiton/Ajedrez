package gastvalidator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;

/**
 * Clase para revisar elementos exclusivos del metalenguaje.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class MetaLanguageValidator extends NodeComparator {

	static final String[] RESERVED_CLASS = { "FunctionCallExpression", "FunctionDefintion", "VariableDefinition",
			"FormalParameterDefinition" };
	static final String[] RESERVED_STATEMENTS = { "BreakStatement" };

	/**
	 * Constructor de la clase siguiendo los parámetros de <i>Validator</i>.
	 * 
	 * @param language          lenguaje de programación del código fuente.
	 * @param dictionaryMethods hashmap que tiene las equivalencias de ambas
	 *                          estructuras para los métodos.
	 * @param validatorFactory  fábrica que se ajusta según el lenguaje de
	 *                          programación.
	 * @param filePath          ruta absoluta del archivo con el código fuente.
	 */
	public MetaLanguageValidator(String language, Map<String, ArrayList<String>> dictionaryMethods,
			ValidatorFactory validatorFactory, String filePath) {
		super(language, dictionaryMethods, validatorFactory, filePath);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Obtiene nombre para elementos del metalenguaje donde el nombre esta dentro
	 * del nodo <i>nameString</i>.
	 * 
	 * @param elementNode nodo para extraer el nombre.
	 * @return nombre asociado al nodo.
	 */
	protected String getMetalanguageNodeName(Object elementNode) {
		try {
			return (String) (elementNode.getClass().getMethod("getNameString")).invoke(elementNode);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * Obtiene la lista para casos donde se debe bajar un nivel en los nodos.
	 * Ejemplo: declaraciones del metalenguaje.
	 * 
	 * @param elementNode nodo que será analizado.
	 * @param dictionary  hashmap asociado al lenguaje con todos los valores del
	 *                    diccionario.
	 * @return Lista obtenida a partir del nodo.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 */
	protected Object getList(Object elementNode, Map<String, ArrayList<String>> dictionary)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object listNode = null;
		Method[] methodsNode = elementNode.getClass().getMethods();
		for (Method method : methodsNode) {
			ArrayList<String> vectorValueDictionary = dictionary.get(method.getName());
			if (vectorValueDictionary != null) {
				for (String valueVector : vectorValueDictionary) {
					if (valueVector != null) {
						listNode = method.invoke(elementNode);
						break;
					}
				}
			}
		}
		return listNode;
	}

	/**
	 * Navegar dentro del nodo hasta llegar al nodo terminal del ASTM.
	 * 
	 * @param node       nodo que será analizado.
	 * @param dictionary hashmap asociado al lenguaje con todos los valores del
	 *                   diccionario.
	 * @return Nodo terminal dentro de la estructura de <i>node</i>.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 */
	protected Object getMetalanguageNodeName(Object node, Map<String, ArrayList<String>> dictionary)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object listNode = null;
		if (elementHasValue(node)) {
			return node;
		}
		Method[] methodsNode = node.getClass().getMethods();
		for (Method method : methodsNode) {
			ArrayList<String> vectorValueDictionary = dictionary.get(method.getName());
			if (vectorValueDictionary != null) {
				for (String valueVector : vectorValueDictionary) {
					if (valueVector != null) {
						listNode = method.invoke(node);
						break;
					}
				}
			}
			if (listNode != null) {
				break;
			}
		}
		return getMetalanguageNodeName(listNode, dictionary);
	}

	/**
	 * Obtener el tipo de objeto para nodos que no estan presentes en el AST de Java
	 * 
	 * @param node       nodo que será analizado.
	 * @param methods    lista de métodos para ser ejecutados en el nodo.
	 * @param dictionary hashmap asociado al lenguaje con todos los valores del
	 *                   diccionario.
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
	protected void getTypeNode(Object node, String[] methods, Map<String, ArrayList<String>> dictionary)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, IOException {
		// Caso para imprimir valores unicos del ASTM como Array, Method, Parameterized
		if (elementInList(node.getClass().getName(), RESERVED_CLASS)) {
			for (String m : methods) {
				Method method = getMethodByName(node, m);
				if (method != null) {
					Object subTree = method.invoke(node);
					if (subTree != null) {
						Object dataType = 0;
						// Por metodos similares, hay casos donde el nodo corresponde al nombre de una
						// variable
						if (node.getClass().getName().contains("VariableDefinition")
								|| node.getClass().getName().contains("FormalParameterDefinition")) {
							dataType = (getMethodByName(subTree, "getDataType")).invoke(subTree);
						}

						if (dataType != null) {
							Method typeNameMethod = getMethodByName(subTree, "getTypeName");
							Object nameTree = typeNameMethod.invoke(subTree);
							Object typeNode = getMetalanguageNodeName(nameTree, dictionary);
							DifferenceLog log = printNodeValues("", typeNode, 1, nameTree);
							super.addElement(log);
						}
					}
				}
			}
		} else if (elementInList(node.getClass().getName(), RESERVED_STATEMENTS)) {
			// Caso para break donde no aparece explícitamente en el AST
			for (String m : methods) {
				Method method = getMethodByName(node, m);
				if (method != null) {
					try {
						Object targetStatement = method.invoke(node);
						Object valueTarget = getMetalanguageNodeName(targetStatement, dictionary);
						DifferenceLog log = printNodeValues(valueTarget, valueTarget, 1, targetStatement);
						super.addElement(log);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			}
		}
	}
}
