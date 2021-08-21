package gastmappers.rpgmapper.mapperutilities.freecontext;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.LiteralContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunctionFactory;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.NegateFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;

public class FreeContextAnalyzer {

	public static Factor getFreeContextFactor(ParseTree parseTree) {
		try {
			String className = parseTree.getClass().getSimpleName();
			if (className.contentEquals("TerminalNodeImpl") || className.contentEquals("LiteralContext")
					|| className.contentEquals("Cs_operationExtenderContext")) {
				return createFactor(parseTree.getText());
			} else if (parseTree.getChild(0).getText().toUpperCase().contentEquals("NOT")) {
				Factor factor = getFreeContextFactor(parseTree.getChild(1));
				return new NegateFactor(factor);
			} else if (className.contains("Bif_") && !className.contains("format")) {
				return buildFunctions(parseTree);
			} else if (className.contentEquals("FunctionContext")) {
				return buildFunctionContext(parseTree);
			} else if (className.contentEquals("UnaryExpressionContext") && parseTree.getChildCount() == 2) {
				return new LiteralFactor(parseTree.getText());
			} else if (parseTree.getChildCount() == 3) {
				if (allNodesAreTerminal(parseTree)) {
					return new LiteralFactor(parseTree.getText());
				} else if (middleNotTerminal(parseTree)) {
					return getFreeContextFactor(parseTree.getChild(1));
				} else {
					Operand operand = new Operand(parseTree.getChild(1).getText());
					Factor leftFactor = getFreeContextFactor(parseTree.getChild(0));
					Factor rightFactor = getFreeContextFactor(parseTree.getChild(2));
					ComparisonFactor twoFactors = new ComparisonFactor();
					twoFactors.setOperands(leftFactor, rightFactor, operand);
					return twoFactors;
				}

			} else {
				return getFreeContextFactor(parseTree.getChild(0));
			}
		} catch (Exception e) {
			return null;
		}

	}

	private static boolean middleNotTerminal(ParseTree parseTree) {
		if (compareClassName(parseTree, 0, "TerminalNodeImpl") && compareClassName(parseTree, 2, "TerminalNodeImpl")) {
			return true;
		}
		return false;
	}

	private static String nameOfSpecificNode(ParseTree parseTree, int numberChild) {
		return parseTree.getChild(numberChild).getClass().getSimpleName();
	}

	private static boolean compareClassName(ParseTree parseTree, int numberChild, String className) {
		return nameOfSpecificNode(parseTree, numberChild).contentEquals(className);
	}

	public static String getChild0String(ParseTree parseTree) {
		String className = parseTree.getClass().getSimpleName();
		if (className.contentEquals("TerminalNodeImpl")) {
			return parseTree.getText();
		} else {
			return getChild0String(parseTree.getChild(0));
		}
	}

	private static Factor buildFunctions(ParseTree parseTree) {
		String functionName = getFunctionName(parseTree);
		// Caso especial que es build-in Function, pero solo tiene un TerminalNode
		if (functionName.contentEquals("EDITCCURRENCY") || functionName.contentEquals("TIMESTAMPARGS")) {
			return FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(0));
		} else if (functionName.contains("TLOOKUP")) {
			return buildFunctionContext(parseTree);
		} else {
			BuiltInFunction function = BuiltInFunctionFactory.buildFunction(functionName);
			function.generateFactor(parseTree);
			return (Factor) function;
		}
	}

	private static Factor buildFunctionContext(ParseTree parseTree) {
		String functionName = getFunctionContextName(parseTree.getChild(0));
		BuiltInFunction function = BuiltInFunctionFactory.buildFunction(functionName);
		function.generateFactor(parseTree.getChild(1));
		return (Factor) function;
	}

	private static String getFunctionContextName(ParseTree parseTree) {
		return FreeContextAnalyzer.getFreeContextFactor(parseTree).getValue().replace("%", "").toUpperCase();
	}

	private static String getFunctionName(ParseTree parseTree) {
		String className = parseTree.getClass().getSimpleName();
		String[] parts = className.split("_");
		return parts[1].replace("Context", "").toUpperCase();
	}

	private static Factor createFactor(String value) {
		if (!value.replace(" ", "").contentEquals("")) {
			return new LiteralFactor(value);
		} else {
			return null;
		}

	}

	private static boolean allNodesAreTerminal(ParseTree parseTree) {
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			String className = parseTree.getChild(i).getClass().getSimpleName();
			if (!className.contentEquals("TerminalNodeImpl")) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Método para encontrar hijos con información ya que estaban en una cadena.
	 * Específicamente para CALLP.
	 * 
	 * @param parseTree
	 * @return
	 */
	public static ParseTree getRightFactorParseTree(ParseTree parseTree) {
		if (parseTree == null){
			return null;
		} else if (parseTree.getChildCount() > 1) {
			return parseTree;
		} else {
			return getRightFactorParseTree(parseTree.getChild(0));
		}
	}

	/**
	 * En instrucciones donde se tienen elementos separados por un símbolo (CALLP es
	 * ':'), se deben separar los elementos
	 * 
	 * @param parseTree
	 * @return
	 */
	public static List<Factor> getListElements(ParseTree parseTree, int beginIndex) {
		List<Factor> elements = new ArrayList<>();
		for (int i = beginIndex; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (!className.contentEquals("TerminalNodeImpl")) {
				elements.add(getContextValue(child.getChild(0)));
			}
		}
		return elements;
	}

	/**
	 * @param parseTree
	 * @return
	 */
	private static Factor getContextValue(ParseTree parseTree) {
		String className = parseTree.getClass().getSimpleName();
		switch (className) {
		case "LiteralContext":
			String value = ((LiteralContext) parseTree).getText();
			return new LiteralFactor(value);
		case "IdentifierContext":
			return getFreeContextFactor(parseTree);
		default:
			break;
		}
		return null;
	}

	// Métodos para opcodes que soportan free y columnar

	/**
	 * Método para bajar de nivel entre los hijos hasta encontrar más de uno
	 * (contiene los elementos que acompañan al opcode)
	 * 
	 * @param parseTree
	 * @return
	 */
	public static ParseTree getOpcodeElements(ParseTree parseTree) {
		if (parseTree.getClass().getSimpleName().contentEquals("TerminalNodeImpl")) {
			return parseTree;
		} else if (parseTree.getChildCount() > 1) {
			return parseTree;
		} else {
			return getOpcodeElements(parseTree.getChild(0));
		}
	}

	/**
	 * Método para verificar que el árbol corresponde a FreeContext. Sirve para
	 * verificar los opcodes.
	 * 
	 * @param parseTree
	 * @return
	 */
	public static boolean parseTreeIsFreeContext(ParseTree parseTree) {
		return parseTree.getClass().getSimpleName().contentEquals("FreeContext");
	}

}
