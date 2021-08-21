package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_divContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class DivFunction extends BuiltInFunction implements Factor {

	private ComparisonFactor divFactor;

	public DivFunction(String functionName) {
		super(functionName);
	}

	public ComparisonFactor getDivFactor() {
		return divFactor;
	}

	public void setDivFactor(ComparisonFactor divFactor) {
		this.divFactor = divFactor;
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
		Bif_divContext divContext = (Bif_divContext) parseTree;
		Factor leftOperand = FreeContextAnalyzer.getFreeContextFactor(divContext.numerator);
		Factor rightOperand = FreeContextAnalyzer.getFreeContextFactor(divContext.denominator);
		this.divFactor = new ComparisonFactor(leftOperand, rightOperand, new Operand("/"));
	}

}
