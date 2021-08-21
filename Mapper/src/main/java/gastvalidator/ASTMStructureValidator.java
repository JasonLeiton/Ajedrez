package gastvalidator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Crea la clase para analizar los dos AST con su respectivo diccionario.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class ASTMStructureValidator {
	Object compilationUnitLanguage;
	ASTMCore.ASTMSource.CompilationUnit compilationUnitMetalanguage;
	String sourceCodeLanguage;
	String filePath;

	/**
	 * Constructor de la clase para inicializar las variables.
	 * 
	 * @param compilationUnitLanguage     AST del lenguaje que se va a analizar.
	 * @param compilationUnitMetalanguage AST del metalenguaje para comparar.
	 * @param filePath                    dirección absoluta del archivo con el
	 *                                    código fuente.
	 */
	public ASTMStructureValidator(Object compilationUnitLanguage,
			ASTMCore.ASTMSource.CompilationUnit compilationUnitMetalanguage, String filePath) {
		this.compilationUnitLanguage = compilationUnitLanguage;
		this.compilationUnitMetalanguage = compilationUnitMetalanguage;
		this.sourceCodeLanguage = compilationUnitMetalanguage.getLanguage();
		this.filePath = filePath;
	}

	/**
	 * Método para crear el diccionario del lenguaje correspondiente, crear la
	 * fábrica con el lenguaje de programación y comparar ambos AST.
	 * 
	 * @return Lista con errores para ser escritos en el documento.
	 * 
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
	public ArrayList<DifferenceLog> compareCompilationUnits() throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
		Map<String, ArrayList<String>> dictionaryMethods = createDictionaryMethods(
				this.compilationUnitMetalanguage.getLanguage());
		ValidatorFactory validatorFactory = new ValidatorFactory();
		NodeComparator nodeComparator = new NodeComparator(this.compilationUnitMetalanguage.getLanguage(),
				dictionaryMethods, validatorFactory, this.filePath);
		nodeComparator.analyzeNodes(this.compilationUnitLanguage, this.compilationUnitMetalanguage);
		return nodeComparator.getDifferences();
	}

	/**
	 * Crea un diccionario (utilizando un .json) con el lenguaje presente en el AST
	 * del metalenguaje.
	 * 
	 * @param languageAST lenguaje del AST en String.
	 * @return un diccionario para ingresar una palabra y devolver una palabra
	 *         asociada.
	 */
	public Map<String, ArrayList<String>> createDictionaryMethods(String languageAST) {
		HashMapDictionary hashMapDictionary = null;
		gastvalidator.LoadFile loadedFile = new gastvalidator.LoadFile();
		List<gastvalidator.Dictionary> dictionariesLanguages = loadedFile.readFile();
		for (gastvalidator.Dictionary dictionary : dictionariesLanguages) {
			// Compara el lenguaje del META con el lenguaje del diccionario
			if (dictionary.getLanguage().contentEquals(languageAST)) {
				hashMapDictionary = new HashMapDictionary(dictionary.getDictionary());
			}
		}
		return hashMapDictionary.getHashMapDictionary();
	}

}
