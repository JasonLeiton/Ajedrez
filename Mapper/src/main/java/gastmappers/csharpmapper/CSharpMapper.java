package gastmappers.csharpmapper;

import ASTMCore.ASTMSource.CompilationUnit;
import VisitorValidator.CSharpValidator;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import gastmappers.Mapper;
import gastmappers.csharpmapper.ANTLR.CSharpLexer;
import gastmappers.csharpmapper.ANTLR.CSharpParser;
import gastmappers.csharpmapper.bindings.CallBinding;
import gastmappers.csharpmapper.bindings.ClassBinding;
import gastmappers.csharpmapper.bindings.MethodBinding;
import gastmappers.misc.Misc;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * This class models the CSharp Mapper, it contains the methods to handle the map process.
 */
public class CSharpMapper implements Mapper {

	public CSharpValidator validator;
    // JNS: unitName, sources, and classpath parameters added
    @Override
    public ArrayList<ASTMCore.ASTMSource.CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath)
            throws IOException {

        // Get the source code from the file.
        String sourceCode = Misc.readFileToString(sourceFilePath);

        // Get the AST of the source language.
        // JNS: unitName, sources, and classpath parameters added
        CSharpParser.Compilation_unitContext csharpCompilationUnit = parseSourceLanguage(sourceCode, unitName, sources, classpath);
        // Map the source AST to GAST.

        return  mapSourceAst(csharpCompilationUnit, null, sourceFilePath);

    }

    // JNS: unitName, sources, classpath, and solutionPath parameters added
    @Override
    public ArrayList<ASTMCore.ASTMSource.CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources, String[] classpath, String solutionPath)
            throws IOException {

        // Get the source code from the file.
        String sourceCode = Misc.readFileToString(sourceFilePath);

        // Get the AST of the source language.
        // JNS: unitName, sources, and classpath parameters added
        CSharpParser.Compilation_unitContext csharpCompilationUnit = parseSourceLanguage(sourceCode, unitName, sources, classpath);

        // Map the source AST to GAST.
        return mapSourceAst(csharpCompilationUnit, solutionPath, sourceFilePath);

    }

    @Override
    public ArrayList<ASTMCore.ASTMSource.CompilationUnit> getGastCompilationUnit(String sourceFilePath)
            throws IOException {

        // Get the source code from the file.
        String sourceCode = Misc.readFileToString(sourceFilePath);

        // Get the AST of the source language.
        CSharpParser.Compilation_unitContext csharpCompilationUnit = parseSourceLanguage(sourceCode, null, null, null);
        
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> list = mapSourceAst(csharpCompilationUnit);
        
        // Map the source AST to GAST.
        return list;

    }

    @Override
    public ArrayList<CompilationUnit> getGastCompilationUnitInMemory(String sourceCode) throws IOException {
        return null;
    }

    // JNS: unitName, sources, and classpath parameters added
    @Override
    public void validateMap(String sourceFilePath, String differencesFilePath, String unitName, String[] sources, String[] classpath) throws IOException {
    	
    	String sourceCode = Misc.readFileToString(sourceFilePath);
        // Get the AST of the source language.
        CSharpParser.Compilation_unitContext csharpCompilationUnit = parseSourceLanguage(sourceCode, null, null, null);

        ArrayList<ASTMCore.ASTMSource.CompilationUnit> lista = getGastCompilationUnit(sourceFilePath);
        if (lista.size() > 0){
            validator = new CSharpValidator(lista.get(0),csharpCompilationUnit);
            Misc.writeInFile(validator.getDiffOutput() + "\nIn file: " + sourceFilePath, differencesFilePath, true);
        }


    }

    /**
     * This method parses the CSharp source code, to create the AST representation.
     *
     * @param sourceCode The string with the source code, extracted from a file.
     * @return The AST representation of CSharp.
     */
    // JNS: unitName, sources, and classpath parameters added
    private CSharpParser.Compilation_unitContext parseSourceLanguage(String sourceCode, String unitName, String[] sources, String[] classpath) {

        // Create the CSharp parser.
        CSharpLexer lexer = new CSharpLexer(CharStreams.fromString(sourceCode));
        CSharpParser parser = new CSharpParser(new CommonTokenStream(lexer));

        // Parse the CSharp code.
        CSharpParser.Compilation_unitContext csharpCompilationUnit = parser.compilation_unit();

        // Debug: Tree Printer
        List<String> ruleNamesList = Arrays.asList(parser.getRuleNames());
        String prettyTree = TreeUtils.toPrettyTree(csharpCompilationUnit, ruleNamesList);
        //System.out.println(prettyTree);

        // Return the CSharp AST.
        return csharpCompilationUnit;
    }

    /**
     * This method takes the AST representation of CSharp and map its to the GAST representation.
     *
     * @param csharpCompilationUnit The AST of CSharp.
     * @return The result CSharp of the map process.
     */
    private ArrayList<ASTMCore.ASTMSource.CompilationUnit> mapSourceAst(CSharpParser.Compilation_unitContext csharpCompilationUnit) {

        // Instace the ArrayList to be returned.
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits = new ArrayList<>();

        try {
            // Map the AST of the source language to the GAST.
            List<CSharpParser.Namespace_member_declarationContext> namespaces =
                    csharpCompilationUnit.namespace_member_declarations().namespace_member_declaration();

            for (CSharpParser.Namespace_member_declarationContext namespace : namespaces) {
                ASTMCore.ASTMSource.CompilationUnit comp = CSharpCompilationMapper.INSTANCE.compilationtocompilation(
                        namespace.namespace_declaration() != null ? namespace.namespace_declaration().qualified_identifier().identifier(0) : null,
                        csharpCompilationUnit.using_directives() != null ? csharpCompilationUnit.using_directives().using_directive() : null,
                        namespace.namespace_declaration() != null ? namespace.namespace_declaration().namespace_body().namespace_member_declarations().namespace_member_declaration() : null);

                //System.out.println(Verifier.getInstance().getAnalysis(false));

                // Set the source code language on the GAST.
                comp.setLanguage("CSharp");

                // Store the GAST compilation unit in the array.
                gastCompilationUnits.add(comp);
            }
        } catch (Exception e) {
            //e.printStackTrace();
        }

        // Return the GASTs;
        return gastCompilationUnits;


    }

    /**
     * This method takes the AST representation of CSharp and map its to the GAST representation.
     *
     * @param csharpCompilationUnit The AST of CSharp.
     * @return The result CSharp of the map process.
     */
    private ArrayList<ASTMCore.ASTMSource.CompilationUnit> mapSourceAst(CSharpParser.Compilation_unitContext csharpCompilationUnit, String solutionPath, String sourceFilePath) {

        // Retrieve semantic bindings from the solution and pass it to the Mapper via CSharpCompilationMapper.BINDINGS
        CSharpCompilationMapper.BINDINGS.clear();
        if (solutionPath != null) {
            Hashtable<String, Object> bindings = new Hashtable<String, Object>();
            Runtime rt = Runtime.getRuntime();
            try {
                Process pr = rt.exec("dotnet ./GetBindings/GetBindings.dll" +
                                     " -g \"" + solutionPath + "\" -o ./GetBindings/GetBindings.json");
                int retValue = pr.waitFor();
                if (retValue == 0) {
                    JsonParser parser = new JsonParser();
                    JsonReader reader = new JsonReader(new FileReader("./GetBindings/GetBindings.json"));
                    JsonElement root = parser.parse(reader);
                    JsonObject bindingsJson = root.getAsJsonObject();

                    JsonArray classes = bindingsJson.getAsJsonArray("classes");
                    for (int i = 0; i < classes.size(); i++) {
                        JsonObject clazz = classes.get(i).getAsJsonObject();
                        ClassBinding classBinding = new ClassBinding();
                        classBinding.setPath(clazz.get("classPath").getAsString());
                        classBinding.setLine(clazz.get("classLine").getAsInt());
                        classBinding.setNamespace(clazz.get("classPackage").getAsString());
                        classBinding.setClassName(clazz.get("className").getAsString());
                        bindings.put(classBinding.getKey(), classBinding);
                    }

                    JsonArray methods = bindingsJson.getAsJsonArray("methods");
                    for (int i = 0; i < methods.size(); i++) {
                        JsonObject method = methods.get(i).getAsJsonObject();
                        MethodBinding methodBinding = new MethodBinding();
                        methodBinding.setPath(method.get("methodPath").getAsString());
                        methodBinding.setLine(method.get("methodLine").getAsInt());
                        methodBinding.setNamespace(method.get("methodPackage").getAsString());
                        methodBinding.setClassName(method.get("methodClassName").getAsString());
                        methodBinding.setMethodName(method.get("methodSignature").getAsString());
                        bindings.put(methodBinding.getKey(), methodBinding);
                    }

                    JsonArray calls = bindingsJson.getAsJsonArray("calls");
                    for (int i = 0; i < calls.size(); i++) {
                        JsonObject call = calls.get(i).getAsJsonObject();
                        CallBinding callBinding = new CallBinding();
                        callBinding.setPath(call.get("callPath").getAsString());
                        callBinding.setLine(call.get("callLine").getAsInt());
                        callBinding.setColumn(call.get("callColumn").getAsInt());
                        callBinding.setCallerNamespace(call.get("callerPackage").getAsString());
                        callBinding.setCallerClassName(call.get("callerName").getAsString());
                        callBinding.setCallerMethodName(call.get("callerSignature").getAsString());
                        callBinding.setCalleeNamespace(call.get("calleePackage").getAsString());
                        callBinding.setCalleeClassName(call.get("calleeName").getAsString());
                        callBinding.setCalleeMethodName(call.get("calleeSignature").getAsString());
                        bindings.put(callBinding.getKey(), callBinding);
                    }
                }
            }
            catch (IOException ex) { }
            catch (InterruptedException ex) { }
            CSharpCompilationMapper.BINDINGS.putAll(bindings);
        }

        CSharpCompilationMapper.SOURCEFILE.setPath(sourceFilePath);

        // Instace the ArrayList to be returned.
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits = new ArrayList<>();

        // Map the AST of the source language to the GAST.
        List<CSharpParser.Namespace_member_declarationContext> namespaces =
                csharpCompilationUnit.namespace_member_declarations().namespace_member_declaration();

        for (CSharpParser.Namespace_member_declarationContext namespace : namespaces) {
            ASTMCore.ASTMSource.CompilationUnit comp = CSharpCompilationMapper.INSTANCE.compilationtocompilation(
                    namespace.namespace_declaration() != null ? namespace.namespace_declaration().qualified_identifier().identifier(0) : null,
                    csharpCompilationUnit.using_directives() != null ? csharpCompilationUnit.using_directives().using_directive() : null,
                    namespace.namespace_declaration() != null ? namespace.namespace_declaration().namespace_body().namespace_member_declarations().namespace_member_declaration() : null);

            //System.out.println(Verifier.getInstance().getAnalysis(false));

            // Set the source code language on the GAST.
            comp.setLanguage("CSharp");

            // Store the GAST compilation unit in the array.
            gastCompilationUnits.add(comp);
        }

        // Return the GASTs;
        return gastCompilationUnits;
    }

}
