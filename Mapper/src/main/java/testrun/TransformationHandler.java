package testrun;

import ASTMCore.ASTMSource.CompilationUnit;
import com.google.gson.Gson;
import gastmappers.Language;
import gastmappers.Mapper;
import gastmappers.exceptions.UnsupportedLanguageException;
import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static gastmappers.misc.Misc.writeInFile;

/**
 * This class stores the necessary data to make a transformation from a source code to a GAST representation.
 *
 * @author AVIB
 * @version 1.0
 * @see TransformationHandler
 * @since 1.0
 */

public class TransformationHandler {

    /**
     * Inputh directory to take the code files.
     */
    private final String inputPath;
    /**
     * The file path of the JSON with the GAST result.
     */
    private final String translationFilePath;
    /**
     * File path of the differences found in the validation process.
     */
    private final String differencesFilePath;
    /**
     * The list with the JSON representations of the obtained GASTs.
     */
    private final ArrayList<String> parsedFileList;
    /**
     * The language to be mapped.
     */
    private final Language language;
    /**
     * The corresponding mapper for the language.
     */
    private final Mapper mapper;
    /**
     * If is necessary to validate the map process.
     */
    private final boolean validate;
    /**
     * If is necessary to use the semantic part.
     */
    private final boolean semantic;


    /**
     * Builder method with all of the instance variables.
     *
     * @param inputPath  Input directory to take the files to transform.
     * @param outputPath Output directory to store the JSON representation of the GAST.
     * @param language   The language to be mapped.
     * @param mapper     The corresponding mapper for the language.
     * @param validate   True if is necessary to validate the map process (run the Validator).
     * @throws IOException Error manipulating a file.
     */
    public TransformationHandler(String inputPath, String outputPath, Language language, Mapper mapper, boolean validate, boolean semantic)
            throws IOException {
        this.inputPath = inputPath;
        this.translationFilePath = outputPath + "\\result.json";
        this.language = language;
        this.parsedFileList = new ArrayList<>();
        this.mapper = mapper;
        this.differencesFilePath = outputPath + "\\summaryDifferences.txt";
        this.validate = validate;
        this.semantic = semantic;

        if (validate) {
            writeInFile("", this.differencesFilePath, false);
        }

    }

    /**
     * @return Input directory variable of the class.
     */
    public String getPath() {
        return inputPath;
    }


    /**
     * This method calls the method processFilesInDir with the inputPath instance variable.
     *
     * @throws IOException              Error manipulating a file.
     * @throws IllegalArgumentException If a method is invoked with distinct arguments from the ones it receives.
     */
    public void processFilesInDir(String unitName, String[] sources, String[] classpath) throws IOException, UnsupportedLanguageException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        processFilesInDir(getPath(), unitName, sources, classpath);
    }

    /**
     * This method handles the transformation process, based on a inputPath, it obtains all the files to be mapped
     * (according to the Language), then, it reads the source code in every file and starts the map process using a
     * MapperFactory to obtain the corresponding Mapper, the Mapper return the GAST representation of the source code.
     * The GAST representation is turned into a JSON representation to be stored in a file.
     *
     * @param path The path of the directory of where to obtain the files.
     * @throws IOException              Error manipulating a file.
     * @throws IllegalArgumentException If a method is invoked with distinct arguments from the ones it receives.
     * @throws SecurityException        If there is an attempt to access a protected value.
     * @see Language
     * @see gastmappers.MapperFactory
     * @see gastmappers.Mapper
     */
    private void processFilesInDir(String path, String unitName, String[] sources, String[] classpath) throws IOException, IllegalArgumentException, SecurityException,
            UnsupportedLanguageException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {        // Get the input directory as a File.
        File dirs = new File(path);

        // Get the root directory of the input.
        String dirPath = dirs.getCanonicalPath() + File.separator;
        File root = new File(dirPath);

        // Get all files in the root directory.
        File[] files = root.listFiles();

        // Iterate all of the files, if there are.
        if (files != null) {
            for (File f : files) {

                // Get the file inputPath.
                String filePath = f.getAbsolutePath();

                // Check if it is a file or a directory.
                if (f.isFile()) {

                    if (FilenameUtils.getExtension(filePath).equals(Language.getFileExtension(this.language))) {

                        // List the file.
                        System.out.println((parsedFileList.size() + 1) + ") " + filePath);

                        // Parse the file and obtain the GAST.
                        ArrayList<CompilationUnit> compilationUnits = new ArrayList<>();
                        if (this.semantic){

                            compilationUnits =
                                    this.mapper.getGastCompilationUnit(filePath, unitName, sources, classpath);
                        }else{
                            compilationUnits =
                                    this.mapper.getGastCompilationUnit(filePath);
                        }


                        // Validate the result if necessary.
                        if (validate) {
                            this.mapper.validateMap(filePath, this.differencesFilePath, unitName, sources, classpath);
                        }

                        // Add the results in the parsed file list.
                        for (CompilationUnit compilationUnit : compilationUnits) {
                            // Transform the compilation unit into its JSON representation.
                            Gson gson = new Gson();
                            String jsonRepresentation = gson.toJson(compilationUnit);

                            // Remove the "null"'s values into a empty string.
                            jsonRepresentation = jsonRepresentation.replaceAll("null", "");

                            // Store the JSON representation in the parsed file list.
                            parsedFileList.add(jsonRepresentation);
                        }

                    }

                } else if (f.isDirectory()) {
                    // Parse all the files in the subdirectory.
                    processFilesInDir(filePath, unitName, sources, classpath);
                }
            }
        }
    }

    /**
     * This method takes the JSON representations of the obtained GAST during the process and converts these
     * representations into a String, to be written in a file.
     *
     * @throws IOException       Error manipulating a file.
     * @throws HeadlessException Errors on graphic ambient.
     */
    public void WriteTranslation() throws IOException, HeadlessException {
        // Get the processed GASTs in a String format.
        String translation = parsedFileList.toString();
        // Write the obtained string.
        writeInFile(translation, this.translationFilePath, false);
    }

}
