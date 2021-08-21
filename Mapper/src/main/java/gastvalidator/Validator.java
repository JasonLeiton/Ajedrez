package gastvalidator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Interfaz de un validador para evaluar casos especiales de cada lenguaje de
 * programación.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public interface Validator {

	/**
	 * Analiza el cuerpo del programa (sentencias y declaraciones). En Metalenguaje
	 * se tienen dos nodos: Members y Scope donde se guardan los miembros y los
	 * metodos, mientras que en Java estos dos elementos son una sola lista.
	 * 
	 * @param nodeLanguage     nodo del AST.
	 * @param nodeMetalanguage nodo del ASTM.
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
	public void analyzeBody(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException;

	/**
	 * Función recursiva para obtener tipo de dato en los arreglos. Ejemplo: int[][]
	 * 
	 * @param array     elemento tipo arreglo.
	 * @param className nombre de clase para <i>array</i> en AST.
	 * @return Tipo de dato en el arreglo.
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
	 */
	public Object getArrayType(Object array, String className) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException;

	/**
	 * Función recursiva para contar las dimensiones en un arreglo.
	 * 
	 * @param array            elemento tipo arreglo.
	 * @param className        nombre de clase para <i>array</i> en AST.
	 * @param numberDimensions variable que lleva las dimensiones contadas de forma
	 *                         recursiva.
	 * @return número de dimensiones en el arreglo.
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
	public int getDimensionArray(Object array, String className, int numberDimensions) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

	/**
	 * Casos especiales para bajar un nivel en una de las dos estructuras. Por
	 * ejemplo con las expresiones entre paréntesis.
	 * 
	 * @param nodeLanguage     nodo del AST.
	 * @param nodeMetalanguage nodo del ASTM.
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
	public void analyzeSpecialCases(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException;

	/**
	 * Obtiene las condiciones de los <i>case</i> y el cuerpo asociado al
	 * <i>case</i>.
	 * 
	 * @param nodeLanguage     nodo del AST.
	 * @param nodeMetalanguage nodo del ASTM.
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
	public void analyzeCasesSwitch(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException;
}
