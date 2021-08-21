package gastmappers.rpgmapper.mapperutilities.Statements.Types;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.BegsrContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Specification;
import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationEnum;
import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationFactory;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.KeywordFactor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public abstract class Statement {

	String type;

	public Statement() {

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public abstract void constructStatement(ParseTree parseTree);

	public List<Factor> getKeywordList(ParseTree parseTree) {
		List<Factor> listKeywords = new ArrayList<Factor>();
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			String classNameChild = child.getClass().getSimpleName();
			if (classNameChild.contains("Keyword") || classNameChild.contains("Hs_expression")) {
				listKeywords.add(new KeywordFactor(child));
			}
		}
		return listKeywords;
	}

	protected Factor getFactorColumn(ParseTree parseTree, int numberChild) {
		return FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(numberChild));
	}

	/**
	 * Método para obtener casos de columnas que tienen valores únicos (X/blank)
	 * 
	 * @param parseTree   Árbol con la instrucción
	 * @param numberChild Número de hijo donde se va a buscar el valor.
	 * @param columnValue Valor con el que se va a comparar
	 * @return valor si coincide o no
	 */
	protected boolean getBooleanColumn(ParseTree parseTree, int numberChild, String columnValue) {
		return getParseTreeText(parseTree, numberChild).toUpperCase().contentEquals(columnValue);
	}

	/**
	 * Método para obtener el texto de una columna
	 * 
	 * @param parseTree   Árbol con la instrucción
	 * @param numberChild Número de hijo donde se va a buscar el valor.
	 * @return Texto de una columna según el número de columna
	 */
	protected String getStringColumn(ParseTree parseTree, int numberChild) {
		return getParseTreeText(parseTree, numberChild);
	}

	/**
	 * Método para obtener el caracter de una columna
	 * 
	 * @param parseTree   Árbol con la instrucción
	 * @param numberChild Número de hijo donde se va a buscar el valor.
	 * @return Caracter de una columna según el número de columna
	 */
	protected char getCharColumn(ParseTree parseTree, int numberChild) {
		try {
			return getParseTreeText(parseTree, numberChild).toUpperCase().charAt(0);
		} catch (Exception e) {
			return ' ';
		}
	}

	/**
	 * Método para obtener el número de una columna
	 * 
	 * @param parseTree   Árbol con la instrucción
	 * @param numberChild Número de hijo donde se va a buscar el valor.
	 * @return Número que se encuentra en una columna según el número de columna
	 */
	protected int getNumberColumn(ParseTree parseTree, int numberChild) {
		try {
			return Integer.parseInt(getParseTreeText(parseTree, numberChild));
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * Método para obtener el texto según un nodo dado
	 * 
	 * @param parseTree   Árbol con la instrucción
	 * @param numberChild Número de hijo donde se va a buscar
	 * @return Texto dentro de un nodo en el árbol
	 */
	private String getParseTreeText(ParseTree parseTree, int numberChild) {
		return parseTree.getChild(numberChild).getText().replace(" ", "");
	}

	public static Statement generateStatement(ParseTree parseTree, ParseTree constructParseTree) {
	
		if (constructParseTree.getClass().getSimpleName().contentEquals("TerminalNodeImpl")) {
			constructParseTree = parseTree;
		} else if (constructParseTree.getClass().getSimpleName().contentEquals("Op_on_errorContext")) {
			constructParseTree = parseTree;
		}
		try{
			String specificationChar = Specification.getSpecificationLetter(parseTree);
			SpecificationEnum specificationEnum = SpecificationEnum.valueOf(specificationChar);
			Specification specification = SpecificationFactory.buildSpecification(specificationEnum);

			return specification.constructStatement(constructParseTree);
		} catch (IllegalArgumentException e) {
			return null;
		}

	}

	public static Statement generateSubroutineStatement(ParseTree parseTree) {
		String specificationChar = (parseTree.getChild(0) instanceof BegsrContext)? "C" : Specification.getSpecificationLetter(parseTree);
		SpecificationEnum specificationEnum = SpecificationEnum.valueOf(specificationChar);
		Specification specification = SpecificationFactory.buildSpecification(specificationEnum);
		return specification.constructSubroutine(parseTree);
	}

}
