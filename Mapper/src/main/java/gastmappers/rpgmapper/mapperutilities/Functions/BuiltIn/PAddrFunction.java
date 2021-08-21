package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class PAddrFunction extends BuiltInFunction implements Factor {

	private Factor expression;

	public PAddrFunction(String functionName) {
		super(functionName);
	}

	public Factor getExpression() {
		return expression;
	}

	public void setExpression(Factor expression) {
		this.expression = expression;
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
		this.expression = getSingleExpression(parseTree);
	}
}
