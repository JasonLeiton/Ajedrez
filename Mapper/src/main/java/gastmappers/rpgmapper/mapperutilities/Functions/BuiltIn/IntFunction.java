package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class IntFunction extends BuiltInFunction implements Factor {

	private Factor valueInt;
	private boolean halfAdjust;

	public IntFunction(String functionName) {
		super(functionName);
		this.halfAdjust = functionName.contentEquals("INTH");
	}

	public Factor getValueInt() {
		return valueInt;
	}

	public void setValueInt(Factor valueInt) {
		this.valueInt = valueInt;
	}

	public boolean isHalfAdjust() {
		return halfAdjust;
	}

	public void setHalfAdjust(boolean halfAdjust) {
		this.halfAdjust = halfAdjust;
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
		this.valueInt = this.getSingleExpression(parseTree);
	}

}
