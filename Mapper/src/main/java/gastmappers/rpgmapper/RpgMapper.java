package gastmappers.rpgmapper;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.AggregateTypeDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.FunctionDefintion;
import ASTMCore.ASTMSyntax.Statement.IfStatement;
import com.fasterxml.jackson.databind.ObjectMapper;
import gastmappers.misc.Misc;
import gastvalidator.ASTMStructureValidator;
import gastvalidator.DifferenceLog;
import gastvalidator.NodeComparator;
import gastvalidator.RPG2GASTMCertifier.IRpgSyntaxElement;
import gastvalidator.RPG2GASTMCertifier.Rpg2GastmCertifier;
import ASTMCore.ASTMSyntax.Statement.BlockStatement;
import ASTMCore.ASTMSyntax.Statement.Statement;
import gastvalidator.ValidatorFactory;
import org.antlr.v4.runtime.tree.ParseTree;

import ASTMCore.ASTMSource.CompilationUnit;
import gastmappers.Mapper;
import gastmappers.rpgmapper.mapperutilities.ParseTree.TreeParser;
import gastmappers.rpgmapper.mapperutilities.SourceCodeRetriever.RPGCodeRetriever;
import gastmappers.rpgmapper.mapperutilities.Structure.RPGSyntaxTree;

import static gastmappers.misc.Misc.writeInFile;

public class RpgMapper implements Mapper {

	@Override
	public void initProblemsBag() {

	}

	@Override
	public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources,
			String[] classpath) throws IOException {
		// TODO Auto-generated method stub
		RPGCodeRetriever retriever = new RPGCodeRetriever();
		// ParseTree from Antlr
		ParseTree parseTree = retriever.getParserFromFilePath(sourceFilePath);
		TreeParser treeParser = new TreeParser();
		
		// RPG mid structure
		RPGSyntaxTree rpgTree = treeParser.getSyntaxTree(parseTree);
		
		ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit = mapSourceAst(rpgTree);
		
		// Instace the ArrayList to be returned, to match the interface.
        ArrayList<ASTMCore.ASTMSource.CompilationUnit> gastCompilationUnits = new ArrayList<>();

        // Store the GAST compilation unit in the array.
        gastCompilationUnits.add(gastCompilationUnit);

        // Return the compilation unit array.
        return gastCompilationUnits;		
	}

	@Override
	public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath, String unitName, String[] sources,
			String[] classpath, String solutionPath) throws IOException {
		// TODO Auto-generated method stub
		// call the other method
        return getGastCompilationUnit( sourceFilePath, unitName, sources, classpath);
	}

	@Override
	public ArrayList<CompilationUnit> getGastCompilationUnit(String sourceFilePath) throws IOException {
		// TODO Auto-generated method stub
		return getGastCompilationUnit( sourceFilePath, "", new String[0], new String[0]);
	}

	@Override
	public ArrayList<CompilationUnit> getGastCompilationUnitInMemory(String sourceCode) throws IOException {
		return null;
	}

	/**
     * This method takes the AST representation of RPG and map its to the GAST representation.
     *
     * @param rpgTree The AST of RPG.
     * @return The result GAST of the map process.
     */
    private ASTMCore.ASTMSource.CompilationUnit mapSourceAst(RPGSyntaxTree rpgTree) {
        // Map the AST of the source language to the GAST.
    	ASTMCore.ASTMSource.CompilationUnit gastCompilationUnit =
    			RPGCompilationMapper.INSTANCE.Compilationtocompilation(rpgTree);
    	
        // Set the source code language on the GAST.
        gastCompilationUnit.setLanguage("RPG");

        // Return the GASTs;
        return gastCompilationUnit;
    }

	@Override
	public void validateMap(String sourceFilePath, String differencesFilePath, String unitName, String[] sources,
			String[] classpath) throws IOException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		RPGCodeRetriever retriever = new RPGCodeRetriever();
		// ParseTree de Antlr
		ParseTree parseTree = retriever.getParserFromFilePath(sourceFilePath);

		Rpg2GastmCertifier cert = new Rpg2GastmCertifier();
		IRpgSyntaxElement body = cert.visit(parseTree);

		TreeParser treeParser = new TreeParser();

		// Estructura intermedia RPG
		RPGSyntaxTree rpgTree = treeParser.getSyntaxTree(parseTree);

		// ASTM de RPG
		CompilationUnit comp = RPGCompilationMapper.INSTANCE.Compilationtocompilation(rpgTree);
		Object[] dod = (Object[])comp.getOpensScope().getDeclOrDefn().toArray();
		AggregateTypeDefinition atd = (AggregateTypeDefinition)dod[0];
		Object[] dod2 = atd.getAggregateType().getOpensScope().getDeclOrDefn().toArray();
		FunctionDefintion fd = (FunctionDefintion)dod2[0];
		BlockStatement bstmt = (BlockStatement) fd.getBody();
		gastvalidator.RPG2GASTMCertifier.Statements.BlockStatement toStatement = (gastvalidator.RPG2GASTMCertifier.Statements.BlockStatement)body;
		Iterator<gastvalidator.RPG2GASTMCertifier.Statements.Statement > IStatement = toStatement.getStatements().iterator();
		gastvalidator.RPG2GASTMCertifier.Statements.Statement currentStmt;
		ASTMStructureValidator validator = new ASTMStructureValidator(body, comp, sourceFilePath);

		try {
			ArrayList<DifferenceLog> differencesList = validator.compareCompilationUnits();
			if (differencesList.size() > 0) {

				String differences = Misc.getDifferencesString(differencesList);
				writeInFile(differences, differencesFilePath, true);

			}
		} catch (Exception e) {
			System.out.println("Error en " + unitName + " " + e);
		}
		//ArrayList<DifferenceLog> prueba = astm.compareCompilationUnits();

		/**Map<String, ArrayList<String>> dictionaryMethods = astm.createDictionaryMethods(comp.getLanguage());
		ValidatorFactory validatorFactory = new ValidatorFactory();
		NodeComparator nodeComparator = new NodeComparator("RPG", dictionaryMethods, validatorFactory, sourceFilePath);

		for (Statement stmt : bstmt.getSubStatements()) {
			currentStmt = IStatement.next();
			if(stmt instanceof IfStatement){
				nodeComparator.analyzeNodes(stmt, currentStmt);
				System.out.println(nodeComparator.getDifferences());
			}
		}
		*/
		/*ObjectMapper mapper = new ObjectMapper();
		//Converting the Object to JSONString
		String jsonString = mapper.writeValueAsString(comp);
		System.out.println(jsonString);*/
	}

}
