package gastmappers.sb2mapper;

import ASTMCore.ASTMSource.CompilationUnit;
import gastmappers.Mapper;
import gastmappers.misc.Misc;
import gastmappers.sb2mapper.grammar.Scratch2Lexer;
import gastmappers.sb2mapper.grammar.Scratch2Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SB2Mapper implements Mapper {

    public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath) throws IOException {
        // Get the source code from the file.
        String sourceCode = Misc.readFileToString(sourceFilePath);

        // Get the AST of the source language.
        Scratch2Parser.RootContext rootContext = parseSourceLanguage(sourceCode);
        // Map the source AST to GAST.
        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = mapSourceAst(rootContext);
        // Instace the ArrayList to be returned, to match the interface.
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits = new ArrayList<>();

        // Store the GAST compilation unit in the array.
        gastCompilationUnits.add(gastCompilationUnit);
        return gastCompilationUnits;
    }

    @Override
    public ArrayList<CompilationUnit> getGastCompilationUnitInMemory(String sourceCode) throws IOException {
        return null;
    }


    private Scratch2Parser.RootContext parseSourceLanguage(String sourceCode){
        // Create the CSharp parser.
        Scratch2Lexer lexer = new Scratch2Lexer(CharStreams.fromString(sourceCode));
        Scratch2Parser parser = new Scratch2Parser(new CommonTokenStream(lexer));
        // Parse the CSharp code.
        Scratch2Parser.RootContext compilationUnit = parser.root();

        // Debug: Tree Printer
        List<String> ruleNamesList = Arrays.asList(parser.getRuleNames());
        //String prettyTree = TreeUtils.toPrettyTree(compilationUnit, ruleNamesList);
        //System.out.println(prettyTree);
        return compilationUnit;
    }

    private ASTMCore.ASTMSource.CompilationUnit mapSourceAst(Scratch2Parser.RootContext rootContext) {
        // Map the AST of the source language to the GAST.
        Scratch2Parser.SpriteObjectContext spriteObjectContext = rootContext.spriteObject();
        spriteObjectContext.children();

        ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = SB2CompilationMapper.INSTANCE.compilationToCompilation(rootContext,
                spriteObjectContext,
                spriteObjectContext.children());
        //spriteObjectContext.children().get(0).children_blocks().sprite_object().get(1).scripts()

        // Set the source code language on the GAST.
        gastCompilationUnit.setLanguage("Sb2");

        // Return the GASTs;
        return gastCompilationUnit;
    }

    /* This changes was made for the semantics requirements into another
    * mappers like Java or C#. TODO: Extract the semantic AST. */
    @Override
    public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath) throws IOException {
        return getGastCompilationUnit(sourceFilePath);
    }
    /* This changes was made for the semantics requirements into another
     * mappers like Java or C#. TODO: Extract the semantic AST. */
    @Override
    public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath, String solutionPath) throws IOException {
        return getGastCompilationUnit(sourceFilePath);
    }

    @Override
    public void validateMap(String sourceFilePath, String differencesFilePath, String unitName, String[] sources, String[] classpath) throws IOException {

    }
}
