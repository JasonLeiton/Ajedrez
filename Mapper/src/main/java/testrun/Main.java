/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testrun;

import gastmappers.Language;
import gastmappers.Mapper;
import gastmappers.MapperFactory;
import gastmappers.exceptions.UnsupportedLanguageException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import testrun.config.ConfigurationTestRun;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.lang.Math;


/**
 * Main program, for test runs. It instance an TransformationHandler class for every ConfigurationTestRun, loaded from
 * configuration file (configurationPath class variable). The TransformationHandler handle the map process.
 *
 * @author AVIB
 * @version 1.0
 * @see ConfigurationTestRun
 * @see TransformationHandler
 * @since 1.0
 */
public class Main {

    /**
     * To store all of the test runs.
     */
    private static final ArrayList<ConfigurationTestRun> configurations = new ArrayList<>();
    /**
     * The file path of the test runs.
     */
    private static final String configurationPath = "./src/main/java/testrun/config/configurationTestRun.json";


    /**
     * Main method, it reads the configuration file with the test runs, then it runs the test.
     *
     * @param args Argument list from console.
     * @throws HeadlessException            Errors on graphic ambient.
     * @throws IllegalArgumentException     If a method is invoked with distinct arguments from the ones it receives.
     * @throws SecurityException            If there is an attempt to access a protected value.
     * @throws IOException                  Error manipulating a file.
     * @throws UnsupportedLanguageException If trying to map an unimplemented language.
     */
    public static void main(String[] args) throws HeadlessException, IllegalArgumentException, SecurityException,
            IOException, UnsupportedLanguageException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // Read the configuration file.
        readConfigurationFile();

        // Begin the transformation for each run.
        for (ConfigurationTestRun testRun : configurations) {
            beginTransformation(testRun.getInputDirectory(), testRun.getOutputDirectory(), testRun.getSourceLanguage(),
                    testRun.isValidateMap(), testRun.isSemantic());
        }

    }

    /**
     * Reads the configuration file and stores the test run cases.
     *
     * @throws UnsupportedLanguageException If trying to map an unimplemented language.
     */
    private static void readConfigurationFile() throws UnsupportedLanguageException {
        // Create the JSON parser.
        JSONParser jsonParser = new JSONParser();

        // Read the file.
        try (FileReader reader = new FileReader(configurationPath)) {

            // Parse the JSON file.
            // Read the first array with the configurations.
            JSONArray configurationsArray = (JSONArray) jsonParser.parse(reader);

            // For every object in the array,
            for (Object configurationRawObject : configurationsArray) {
                // Cast the object.
                JSONObject configurationObject = (JSONObject) configurationRawObject;

                // Get the values by the key.
                String inputDirectory = (String) configurationObject.get("inputDirectory");
                String outputDirectory = (String) configurationObject.get("outputDirectory");
                String sourceLanguageRaw = (String) configurationObject.get("sourceLanguage");
                Language sourceLanguage = Language.getLanguageFromString(sourceLanguageRaw);
                boolean validateMap = (boolean) configurationObject.get("validateMap");
                boolean semantic = (boolean) configurationObject.get("semantic");

                // Create a test run.
                ConfigurationTestRun configurationTestRun =
                        new ConfigurationTestRun(inputDirectory, outputDirectory, sourceLanguage, validateMap, semantic);

                // Store the test run config.
                configurations.add(configurationTestRun);
            }

        } catch (IOException | ParseException e) {
            System.err.println("Error reading the configuration file.");
            e.printStackTrace();
        }

    }

    /**
     * Init the transformation process, i.e. take a code file and map it to the GAST, then it transform the GAST in a
     * JSON representation, to be stored in a output file.
     *
     * @param inputDirectory  Input directory to take the files to transform.
     * @param outputDirectory Output directory to store the JSON representation of the GAST.
     * @param language        The language to be mapped.
     * @param validateMap     True if is necessary to validate the map process (run the Validator).
     * @throws HeadlessException            Errors on graphic ambient.
     * @throws IllegalArgumentException     If a method is invoked with distinct arguments from the ones it receives.
     * @throws SecurityException            If there is an attempt to access a protected value.
     * @throws IOException                  Error manipulating a file.
     * @throws UnsupportedLanguageException If trying to map an unimplemented language.
     */
    private static void beginTransformation(
            String inputDirectory, String outputDirectory, Language language, boolean validateMap, boolean semantic) throws IOException,
            IllegalArgumentException, SecurityException, HeadlessException, UnsupportedLanguageException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        // Instance the factory.
        MapperFactory factory = new MapperFactory();

        // Build the Java mapper.
        Mapper mapper = factory.createMapper(language);

        // Instance a transformation handler with the configuration provided by parameters.
        System.out.println(semantic);

        long start = System.nanoTime();

        TransformationHandler transformationHandler =
                new TransformationHandler(inputDirectory, outputDirectory, language, mapper, validateMap, semantic);

        // Init the map process.
        transformationHandler.processFilesInDir(null, null, null);

        // Store the results.
        transformationHandler.WriteTranslation();

        long elapsedTime = System.nanoTime() - start;
        System.out.println(elapsedTime/Math.pow(10, 9));

        // Show a pretty message.
        JOptionPane.showMessageDialog(null, "The process has finished correctly");

    }
}