package gastvalidator;

import java.util.ArrayList;
import java.util.Map;

/**
 * Fábrica de validador para cada uno de los casos de lenguajes de programación.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class ValidatorFactory {

	/**
	 * Crea un validador según el lenguaje de programación.
	 * 
	 * @param language          lenguaje de programación del código fuente.
	 * @param dictionaryMethods hashmap con los métodos del diccionario.
	 * @param validatorFactory  fábrica de cada validador.
	 * @param filePath          dirección absoluta del archivo del código fuente.
	 * @return un validador ajustado al lenguaje de programación.
	 */
	public Validator getValidator(String language, Map<String, ArrayList<String>> dictionaryMethods,
			ValidatorFactory validatorFactory, String filePath) {
		switch (language) {
		case "Java":
			return new JavaValidator(language, dictionaryMethods, validatorFactory, filePath);
		case "RPG":
			return new RPGValidator(language, dictionaryMethods, validatorFactory, filePath);
		default:
			return null;
		}
	}
}
