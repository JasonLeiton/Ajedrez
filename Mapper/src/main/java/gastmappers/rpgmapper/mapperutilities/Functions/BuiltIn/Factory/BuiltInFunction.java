package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BuiltInFunction {

	private String functionName;

	public BuiltInFunction(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public abstract void generateFactor(ParseTree parseTree);

	protected List<Factor> getListArguments(ParseTree parseTree) {
		List<Factor> listFactor = new ArrayList<Factor>();
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("ExpressionContext")) {
				listFactor.add(FreeContextAnalyzer.getFreeContextFactor(child));
			}
		}
		return listFactor;
	}

	protected Factor getSingleExpression(ParseTree parseTree) {
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("ExpressionContext") || className.contentEquals("IdentifierContext")) {
				return FreeContextAnalyzer.getFreeContextFactor(child);
			}
		}
		return null;
	}

	protected Factor getReflectionFactor(ParseTree parseTree, String fieldName) {
		try {
			Field field = parseTree.getClass().getDeclaredField(fieldName);
			ParseTree value = (ParseTree) field.get(parseTree);
			return FreeContextAnalyzer.getFreeContextFactor(value);
		} catch (Exception e) {
			return null;
		}
	}

	protected boolean searchTerminalValue(ParseTree parseTree, String value) {
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			if (child.getText().contentEquals(value)) {
				return true;
			}
		}
		return false;
	}

}
