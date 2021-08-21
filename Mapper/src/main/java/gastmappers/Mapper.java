package gastmappers;

import ASTMCore.ASTMSource.CompilationUnit;
import org.eclipse.jdt.core.compiler.IProblem;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * Mapper interface to specify the methods that a Mapper needs to have.
 */
public interface Mapper {

    // JNS: Problems collection for JDT
    static final ArrayList<IProblem> PROBLEMS = new ArrayList<IProblem>();
    default void initProblemsBag() { PROBLEMS.clear(); }

    /**
     * This method returns the list of GAST compilations units obtained from a file.
     *
     * @param sourceFilePath: The path of the file with the source code.
     */
    // JNS: unitName, sources, and classpath parameters added
    ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath) throws IOException;

    // JNS: unitName, sources, classpath, and solutionPath parameters added
    ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath, String solutionPath) throws IOException;

    // Only syntax analysis
    ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath) throws IOException;

    // receives the source code instead of the file path
    ArrayList<CompilationUnit> getGastCompilationUnitInMemory(String sourceCode) throws IOException;

    /**
     * This method validate the map process, it do the same as {@code Mapper#getGastCompilationUnit}, but it also runs
     * the validator for that map process.
     *
     * @param sourceFilePath      The path of the file with the source code.
     * @param differencesFilePath The path where the differences is going to be written.
     * @throws IOException Error manipulating a file.
     * @see gastvalidator.Validator
     */
    // JNS: unitName, sources, and classpath parameters added
    void validateMap(String sourceFilePath, String differencesFilePath, String unitName, String[] sources, String[] classpath) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;

}
