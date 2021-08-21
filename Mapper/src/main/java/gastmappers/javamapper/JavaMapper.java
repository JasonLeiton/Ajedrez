package gastmappers.javamapper;

import gastmappers.Mapper;
import gastmappers.misc.Misc;
import gastvalidator.ASTMStructureValidator;
import gastvalidator.DifferenceLog;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static gastmappers.misc.Misc.writeInFile;

//import org.eclipse.core.runtime.CoreException;

/**
 * This class models the Java Mapper, it contains the methods to handle the map process.
 */
public class JavaMapper implements Mapper {

    @Override
    // JNS: unitName, sources, and classpath added
    public ArrayList<ASTMCore.ASTMSource.CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath)
            throws IOException {

        // Get the source code from the file.
        String sourceCode = Misc.readFileToString(sourceFilePath);

        // Get the AST of the source language.
        CompilationUnit javaCompilationUnit = parseSourceLanguage(sourceCode, unitName, sources, classpath);

        // JNS: Fix - collect JDT compilation problems for debugging purposes
        PROBLEMS.addAll(Arrays.asList(javaCompilationUnit.getProblems()));

        // Map the source AST to GAST.
        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = mapSourceAst(javaCompilationUnit, true);

        // Instace the ArrayList to be returned, to match the interface.
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits = new ArrayList<>();

        // Store the GAST compilation unit in the array.
        gastCompilationUnits.add(gastCompilationUnit);

        // Return the compilation unit array.
        return gastCompilationUnits;

    }
    @Override
    // JNS: unitName, sources, classpath, and solutionPath added
    public ArrayList<ASTMCore.ASTMSource.CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath, String solutionPath)
            throws IOException {

        // call the other method
        return getGastCompilationUnit( sourceFilePath, unitName, sources, classpath);
    }

    @Override
    public ArrayList<ASTMCore.ASTMSource.CompilationUnit> getGastCompilationUnit(String sourceFilePath)
            throws IOException {

        // Get the source code from the file.
        String sourceCode = Misc.readFileToString(sourceFilePath);

        // Get the AST of the source language.
        CompilationUnit javaCompilationUnit = parseSourceLanguage(sourceCode);

        // Map the source AST to GAST.
        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = mapSourceAst(javaCompilationUnit, false);

        // Instace the ArrayList to be returned, to match the interface.
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits = new ArrayList<>();

        // Store the GAST compilation unit in the array.
        gastCompilationUnits.add(gastCompilationUnit);

        // Return the compilation unit array.
        return gastCompilationUnits;

    }

    @Override
    public ArrayList<ASTMCore.ASTMSource.CompilationUnit> getGastCompilationUnitInMemory(String sourceCode) throws IOException {
        return null;
    }

    @Override
    // JNS: unitName, sources, and classpath added
    public void validateMap(String pathFile, String differencesFilePath, String unitName, String[] sources, String[] classpath) throws IOException {

        // Get the source code from the file.
        String sourceCode = Misc.readFileToString(pathFile);

        // Get the AST of the source language.
        CompilationUnit javaCompilationUnit = parseSourceLanguage(sourceCode, unitName, sources, classpath);

        // Map the source AST to GAST.
        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = mapSourceAst(javaCompilationUnit, true);

        // Validate the GAST.
        validateGast(javaCompilationUnit, gastCompilationUnit, pathFile, differencesFilePath);

    }

    /**
     * This method parses the Java source code, to create the AST representation.
     * USED BY SEMANTICS ANALYSIS.
     *
     * @param sourceCode The string with the source code, extracted from a file.
     * @return The AST representation of Java.
     */
    @SuppressWarnings("unchecked")
    // JNS: unitName, sources, and classpath added
    private CompilationUnit parseSourceLanguage(String sourceCode, String unitName, String[] sources, String[] classpath) {
        // Create the Java parser.
        Map options = JavaCore.getOptions();
        // JNS: Compiler and JLS upgrade
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_14);
        ASTParser parser = ASTParser.newParser(AST.JLS14);
        // JNS: Adding bindings resolutions for function calls
        parser.setBindingsRecovery(true);
        parser.setResolveBindings(true);
        parser.setUnitName(unitName);

        //parser.setEnvironment(classpath, sources, new String[] { "UTF-8" }, true);
        parser.setEnvironment(classpath, sources, null, true); //new String[] { "UTF-8" }, true);
        // JNS: ----------------------------------------------
        parser.setEnvironment(classpath, sources, null, true);
        parser.setCompilerOptions(options);
        parser.setSource(sourceCode.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        // Return the Java AST.
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);
        return cu;
    }
    /**
     * This method parses the Java source code, to create the AST representation.
     *
     * @param sourceCode The string with the source code, extracted from a file.
     * @return The AST representation of Java.
     */
    @SuppressWarnings("unchecked")
    private CompilationUnit parseSourceLanguage(String sourceCode) {
        // Create the Java parser.
        Map options = JavaCore.getOptions();
        // JNS: Compiler and JLS upgrade
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_14);
        ASTParser parser = ASTParser.newParser(AST.JLS14);
        parser.setCompilerOptions(options);
        parser.setSource(sourceCode.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);

        // Return the Java AST.
        return (CompilationUnit) parser.createAST(null);
    }

    /**
     * This method takes the AST representation of Java and map its to the GAST representation.
     *
     * @param javaCompilationUnit The AST of Java.
     * @return The result GAST of the map process.
     */
    @SuppressWarnings("unchecked")
    private ASTMCore.ASTMSource.CompilationUnit mapSourceAst(CompilationUnit javaCompilationUnit, boolean semantic) {
        // Map the AST of the source language to the GAST.
        JavaCompilationMapper.semantic.setSemantic(semantic);
        JavaCompilationMapper.compilation.setCu(javaCompilationUnit);
        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit =
                JavaCompilationMapper.INSTANCE.compilationtocompilation(
                        javaCompilationUnit, javaCompilationUnit.imports(), javaCompilationUnit.types());

        // Set the source code language on the GAST.
        gastCompilationUnit.setLanguage("Java");

        // Return the GASTs;
        return gastCompilationUnit;
    }

    private void validateGast(CompilationUnit javaCompilationUnit,
                              ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit, String pathFile,
                              String differencesFilePath) {
        // Validate the result obtained from the mapper.
        ASTMStructureValidator validator = new ASTMStructureValidator(javaCompilationUnit, gastCompilationUnit,
                pathFile);

        try {
            ArrayList<DifferenceLog> differencesList = validator.compareCompilationUnits();
            if (differencesList.size() > 0) {

                String differences = Misc.getDifferencesString(differencesList);
                writeInFile(differences, differencesFilePath, true);

            }
        } catch (Exception e) {
            System.out.println("Error en " + pathFile + " " + e);
        }

    }


}
