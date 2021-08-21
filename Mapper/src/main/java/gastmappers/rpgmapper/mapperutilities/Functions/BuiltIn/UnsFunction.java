package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class UnsFunction extends BuiltInFunction implements Factor {

	private Factor valueFactor;
	private boolean halfAdjust;

	public UnsFunction(String functionName) {
		super(functionName);
		this.halfAdjust = functionName.contentEquals("UNSH");
	}

	public Factor getValueFactor() {
		return valueFactor;
	}

	public void setValueFactor(Factor valueFactor) {
		this.valueFactor = valueFactor;
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
		this.valueFactor = this.getSingleExpression(parseTree);
	}
}
