package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class HoursFunction extends BuiltInFunction implements Factor {

	private Factor numericValue;

	public HoursFunction(String functionName) {
		super(functionName);
	}

	public Factor getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(Factor numericValue) {
		this.numericValue = numericValue;
	}

	@Override
	public void generateFactor(ParseTree parseTree) {
		RpgParser.ExpressionContext expressionContext = ((RpgParser.Bif_hoursContext) parseTree).numberexpression;
		this.numericValue = FreeContextAnalyzer.getFreeContextFactor(expressionContext);
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
}
