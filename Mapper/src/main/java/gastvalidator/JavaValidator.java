package gastvalidator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Clase dedicada para analizar los casos especiales del lenguaje Java.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class JavaValidator extends NodeComparator implements Validator {

	static final String[] CASES_LANGUAGE = { "ParenthesizedExpression", "ArrayType", "ClassInstanceCreation" };
	static final String[] CASES_LANGUAGE_VALUES = { "getExpression", "getComponentType", "getValue" };
	static final String[] CASES_METALANGUAGE = { "ActualParameterExpression", "VariableExpression" };
	static final String[] CASES_METALANGUAGE_VALUES = { "getValue", "getVariable" };

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
	public JavaValidator(String language, Map<String, ArrayList<String>> dictionaryMethods,
			ValidatorFactory validatorFactory, String filePath) {
		super(language, dictionaryMethods, validatorFactory, filePath);
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ASTMValidator.Validator#analyzeBody(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void analyzeBody(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		List<?> listNodeLanguage = (List<?>) nodeLanguage;
		// Invoca el nodo especifico de variables declaradas en el metalenguaje
		Object membersNodeMetalanguage = getMethodByName(nodeMetalanguage, "getMembers").invoke(nodeMetalanguage);
		// Invoca el nodo especifico de sentencias en el metalenguaje
		Object scopeNodeMetalanguage = getMethodByName(nodeMetalanguage, "getOpensScope").invoke(nodeMetalanguage);
		// Las sentencias son un HashSet, por lo que debe ser convertido alista
		List<?> bodyElementsNodeMetalanguage = convertHashSetToList(
				getMethodByName(scopeNodeMetalanguage, "getDeclOrDefn").invoke(scopeNodeMetalanguage));

		// Ordenar las listas por id
		Collections.sort(((List<?>) membersNodeMetalanguage), new SortingAlgorithm());
		Collections.sort(((List<?>) bodyElementsNodeMetalanguage), new SortingAlgorithm());

		int indexMembers = 0, indexMethods = 0;

		// Caso especial donde el metalenguage tiene un elemento nulo en los metodos
		if (bodyElementsNodeMetalanguage.size() > 0 && bodyElementsNodeMetalanguage.get(0) == null) {
			bodyElementsNodeMetalanguage.remove(0);
		}

		// Enviar cada metodo con el respectivo metodo del metalenguaje, al igual que
		// los campos de una clase
		for (int i = 0; i < listNodeLanguage.size(); i++) {
			Object definitionLanguage = listNodeLanguage.get(i); // Puede ser metodo o variable
			Object definitionMetalanguage = null;
			String classFieldLanguage = definitionLanguage.getClass().getSimpleName();
			switch (classFieldLanguage) {
			case "FieldDeclaration":
				definitionMetalanguage = ((List<?>) membersNodeMetalanguage).get(indexMembers);
				indexMembers++;
				break;

			case "TypeDeclaration":
			case "EnumDeclaration":
			case "MethodDeclaration":
				definitionMetalanguage = bodyElementsNodeMetalanguage.get(indexMethods);
				indexMethods++;
				break;

			default:
				break;
			}

			if (definitionMetalanguage != null && definitionLanguage != null) {
				analyzeNodes(definitionLanguage, definitionMetalanguage);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ASTMValidator.Validator#getArrayType(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object getArrayType(Object array, String className) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		if (array.getClass().getName().contains(className)) {
			// Obtiene el tipo de dato que presenta el array.
			Method getComponent = getMethodByName(array, "getComponentType");
			Object result = getComponent.invoke(array);
			return getArrayType(result, className);
		} else {
			return array;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ASTMValidator.Validator#getDimensionArray(java.lang.Object,
	 * java.lang.String, int)
	 */
	@Override
	public int getDimensionArray(Object array, String className, int numberDimensions) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Busca componentType de manera recursiva para sumar las dimensiones.
		if (array.getClass().getName().contains(className)) {
			Method getComponent = getMethodByName(array, "getComponentType");
			Object result = getComponent.invoke(array);
			return getDimensionArray(result, className, numberDimensions + 1);
		} else {
			return numberDimensions;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ASTMValidator.Validator#analyzeSpecialCases(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void analyzeSpecialCases(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

		String classNameNodeLanguage = nodeLanguage.getClass().getName();
		String classNameNodeMetaLanguage = nodeMetalanguage.getClass().getName();
		String classToAnalize = "";
		Object nodeToAnalize = null;
		Object childrenLanguage = null;
		Object childrenMetaLanguage = null;
		String[] specialCases = {};
		String[] specialCasesValues = {};
		boolean changeMetalanguageNode = true;

		// Asocia la clase del nodo a un método para obtener un nuevo nodo.
		if (elementInList(classNameNodeMetaLanguage, CASES_METALANGUAGE)
				|| classNameNodeLanguage.contains("ClassInstanceCreation")) {

			specialCases = CASES_METALANGUAGE;
			specialCasesValues = CASES_METALANGUAGE_VALUES;
			nodeToAnalize = nodeMetalanguage;
			classToAnalize = classNameNodeMetaLanguage;
		} else if (elementInList(classNameNodeLanguage, CASES_LANGUAGE)) {

			specialCases = CASES_LANGUAGE;
			specialCasesValues = CASES_LANGUAGE_VALUES;
			nodeToAnalize = nodeLanguage;
			classToAnalize = classNameNodeLanguage;
			changeMetalanguageNode = false;
		}

		// Asigna los nodos a una variable para enviar solo un análisis de nodos.
		if (specialCases.length > 0 && nodeToAnalize != null) {
			Object node = getObjectByIndex(classToAnalize, specialCases, specialCasesValues, nodeToAnalize);

			if (changeMetalanguageNode) {
				childrenLanguage = nodeLanguage;
				childrenMetaLanguage = node;
			} else {
				childrenLanguage = node;
				childrenMetaLanguage = nodeMetalanguage;
			}

			if (childrenLanguage != null && childrenMetaLanguage != null) {
				analyzeNodes(childrenLanguage, childrenMetaLanguage);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ASTMValidator.Validator#analyzeCasesSwitch(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public void analyzeCasesSwitch(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		List<?> listNodeLanguage = (List<?>) nodeLanguage;
		List<?> listMetalanguage = (List<?>) nodeMetalanguage;
		Collections.sort(listMetalanguage, new SortingAlgorithm());
		int metalanguageIndex = -1;
		int bodyCaseMetalanguageIndex = 0;
		Object elementMetalanguage = null;
		List<?> bodyCaseMetalanguage = null;
		Object evaluationMetalanguage = null;
		for (int i = 0; i < listNodeLanguage.size(); i++) {
			Object elementLanguage = listNodeLanguage.get(i);
			String className = elementLanguage.getClass().getSimpleName();

			switch (className) {
			// Obtiene la condición en el case.
			case "SwitchCase":
				metalanguageIndex++;
				elementMetalanguage = listMetalanguage.get(metalanguageIndex);
				bodyCaseMetalanguage = (List<?>) getElementCaseMetalanguage(elementMetalanguage, "getBody");
				bodyCaseMetalanguageIndex = 0;
				evaluationMetalanguage = getElementCaseMetalanguage(elementMetalanguage, "getEvaluation");
				try {
					elementLanguage = getMethodByName(elementLanguage, "getExpression").invoke(elementLanguage);
				} catch (Exception e) {
					// TODO: handle exception
				}
				if (elementLanguage != null && evaluationMetalanguage != null) {
					analyzeNodes(elementLanguage, evaluationMetalanguage);
				}
				break;

			// Obtiene el cuerpo que se ejecuta en el case.
			default:
				Object caseMetalanguage = bodyCaseMetalanguage.get(bodyCaseMetalanguageIndex);
				bodyCaseMetalanguageIndex++;
				if (elementLanguage != null && caseMetalanguage != null) {
					analyzeNodes(elementLanguage, caseMetalanguage);
				}
				break;
			}

		}
	}

	/**
	 * Obtiene un objeto que corresponde a una invocación de un método dado.
	 * 
	 * @param node   nodo al que se busca extraer un subnodo.
	 * @param method nombre del método para invocar en el nodo.
	 * @return subnodo obtenido de invocar el método.
	 */
	private Object getElementCaseMetalanguage(Object node, String method) {
		try {
			Method methodToInvoke = getMethodByName(node, method);
			return methodToInvoke.invoke(node);

		} catch (Exception e) {
			return null;
		}
	}

}