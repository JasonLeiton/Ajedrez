package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class SizeFunction extends BuiltInFunction implements Factor {

	private Factor variableName;
	private boolean allFlag;

	public SizeFunction(String functionName) {
		super(functionName);
	}

	public Factor getVariableName() {
		return variableName;
	}

	public void setVariableName(Factor variableName) {
		this.variableName = variableName;
	}

	public boolean isAllFlag() {
		return allFlag;
	}

	public void setAllFlag(boolean allFlag) {
		this.allFlag = allFlag;
	}

	@Override
	public String getValue() {

		return null;
	}

	@Override
	public String getType() {

		return null;
	}

	@Override
	public void setValue(String value) {

	}

	@Override
	public void setType(String type) {

	}

	@Override
	public void generateFactor(ParseTree parseTree) {
		this.variableName = this.getSingleExpression(parseTree);
		this.allFlag = this.searchTerminalValue(parseTree, "*ALL");
	}

}
