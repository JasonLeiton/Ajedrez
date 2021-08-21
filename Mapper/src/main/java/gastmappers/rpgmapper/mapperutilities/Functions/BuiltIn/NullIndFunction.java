package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class NullIndFunction extends BuiltInFunction implements Factor {

	private Factor nullField;

	public NullIndFunction(String functionName) {
		super(functionName);
	}

	public Factor getNullField() {
		return nullField;
	}

	public void setNullField(Factor nullField) {
		this.nullField = nullField;
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
		this.nullField = this.getSingleExpression(parseTree);
	}

}
