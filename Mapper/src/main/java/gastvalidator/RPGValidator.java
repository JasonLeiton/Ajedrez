package gastvalidator;

import ASTMCore.ASTMSyntax.Statement.ExpressionStatement;
import gastvalidator.NodeComparator;
import gastvalidator.RPG2GASTMCertifier.Statements.AssigmentStatement;
import gastvalidator.Validator;
import gastvalidator.ValidatorFactory;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Map;

public class RPGValidator extends NodeComparator implements Validator {
    /**
     * Constructor de la clase para asignar valores a los atributos.
     *
     * @param language          lenguaje de programación del código fuente.
     * @param dictionaryMethods hashmap con el diccionario de métodos de ambas
     *                          estructuras.
     * @param validatorFactory  fábrica para ejecutar casos especiales para cada
     *                          lenguaje.
     * @param filePath          dirección absoluta con el archivo con el código
     */
    public RPGValidator(String language, Map<String, ArrayList<String>> dictionaryMethods, ValidatorFactory validatorFactory, String filePath) {
        super(language, dictionaryMethods, validatorFactory, filePath);
    }

    @Override
    public void analyzeBody(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

    }

    @Override
    public Object getArrayType(Object array, String className) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        return null;
    }

    @Override
    public int getDimensionArray(Object array, String className, int numberDimensions) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return 0;
    }

    @Override
    public void analyzeSpecialCases(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {
        if(nodeLanguage instanceof AssigmentStatement && nodeMetalanguage instanceof ExpressionStatement){
            nodeMetalanguage = ((ExpressionStatement) nodeMetalanguage).getExpression();
            analyzeNodes(nodeLanguage, nodeMetalanguage);
        }
    }

    @Override
    public void analyzeCasesSwitch(Object nodeLanguage, Object nodeMetalanguage) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IOException {

    }
}
