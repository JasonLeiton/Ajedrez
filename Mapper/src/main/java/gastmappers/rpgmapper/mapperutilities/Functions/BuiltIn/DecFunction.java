package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class DecFunction extends BuiltInFunction implements Factor {

	private Factor expression, length, decimalPositions;
	private boolean halfAdjust;

	public DecFunction(String functionName) {
		super(functionName);
		this.halfAdjust = functionName.contentEquals("DECH");
	}

	public Factor getExpression() {
		return expression;
	}

	public void setExpression(Factor expression) {
		this.expression = expression;
	}

	public Factor getLength() {
		return length;
	}

	public void setLength(Factor length) {
		this.length = length;
	}

	public Factor getDecimalPositions() {
		return decimalPositions;
	}

	public void setDecimalPositions(Factor decimalPositions) {
		this.decimalPositions = decimalPositions;
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
		List<Factor> factorList = this.getListArguments(parseTree);

		Factor[] factorArray = { null, null, null };

		for (int i = 0; i < factorList.size(); i++) {
			factorArray[i] = factorList.get(i);
		}

		this.expression = factorArray[0];
		this.length = factorArray[1];
		this.decimalPositions = factorArray[2];
	}
}
