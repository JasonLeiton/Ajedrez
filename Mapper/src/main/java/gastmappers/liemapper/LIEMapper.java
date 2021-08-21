package gastmappers.liemapper;

import ASTMCore.ASTMSource.CompilationUnit;
import gastmappers.Mapper;
import gastmappers.csharpmapper.TreeUtils;
import gastmappers.misc.Misc;
import gastmappers.liemapper.grammar.fodcatLexer;
import gastmappers.liemapper.grammar.fodcatParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LIEMapper implements Mapper {

    @Override
    public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath) throws IOException {
        return null;
    }

    @Override
    public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath, String solutionPath) throws IOException {
        return null;
    }

    /**
     * Overload method to recived the source path where are the LIE++
     * files, processed and return the corresponding CompilationUnits.
     * @param sourceFilePath: The path of the file with the source code.
     * @return
     * @throws IOException
     */
    @Override
    public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath) throws IOException {
        // Get the source code from the file.
        String sourceCode = Misc.readFileToString(sourceFilePath);

        // Get the AST of the source language.
        //fodcatParser.SuperProgramContext programContext = parseSourceLanguage(sourceCode);
        fodcatParser.ProgramContext programContext = parseSourceLanguage(sourceCode);

        // Map the source AST to GAST.
        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = mapSourceAst(programContext);

        // Instace the ArrayList to be returned, to match the interface.
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits = new ArrayList<>();

        // Store the GAST compilation unit in the array.
        gastCompilationUnits.add(gastCompilationUnit);
        return gastCompilationUnits;
    }

    @Override
    public ArrayList<CompilationUnit> getGastCompilationUnitInMemory(String sourceCode) throws IOException {
        // Get the AST of the source language.
        fodcatParser.ProgramContext programContext = parseSourceLanguage(sourceCode);

        // Map the source AST to GAST.
        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = mapSourceAst(programContext);

        // Instace the ArrayList to be returned, to match the interface.
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits = new ArrayList<>();

        // Store the GAST compilation unit in the array.
        gastCompilationUnits.add(gastCompilationUnit);
        return gastCompilationUnits;
    }

    @Override
    public void validateMap(String sourceFilePath, String differencesFilePath, String unitName, String[] sources, String[] classpath) throws IOException {

    }

    /**
     * Method to parse the language and transformed into an AST.
     * @param sourceCode the text of the code extracted form a file.
     * @return returns the main root of the language AST.
     */
    private fodcatParser.ProgramContext parseSourceLanguage(String sourceCode) {
        // Create the lie parser. 
        fodcatLexer lexer = new fodcatLexer(CharStreams.fromString(sourceCode));
        fodcatParser parser = new fodcatParser(new CommonTokenStream(lexer));

        // Parse the lie code.
        fodcatParser.ProgramContext compilationUnit = parser.program();

        // Debug: Tree Printer
        List<String> ruleNamesList = Arrays.asList(parser.getRuleNames());
        String prettyTree = TreeUtils.toPrettyTree(compilationUnit, ruleNamesList);
        System.out.println(prettyTree);
        return compilationUnit;
    }

    /**
     * Method to get the compilation unit wrap form the LIECompilationMapper.
     * @param pProgramContext is the main root of the AST from the language.
     * @return returns the compilation unit with the mapping of the language.
     */
    private ASTMCore.ASTMSource.CompilationUnit mapSourceAst(fodcatParser.ProgramContext pProgramContext) {

        // Map the AST of the source language to the GAST.
        //fodcatParser.ProgramContext programContext = superProgramContext.program();

        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = LIECompilationMapper.INSTANCE.
                compilationToCompilation(pProgramContext);

        // Set the source code language on the GAST.
        gastCompilationUnit.setLanguage("LIE");

        // Return the GASTs;
        return gastCompilationUnit;
    }
}
