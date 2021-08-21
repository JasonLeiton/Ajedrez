package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_editfltContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class EditFltFunction extends BuiltInFunction implements Factor {

	private Factor numericExpression;

	public EditFltFunction(String functionName) {
		super(functionName);
	}

	public Factor getNumericExpression() {
		return numericExpression;
	}

	public void setNumericExpression(Factor numericExpression) {
		this.numericExpression = numericExpression;
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
		Bif_editfltContext editFltContext = (Bif_editfltContext) parseTree;
		this.numericExpression = FreeContextAnalyzer.getFreeContextFactor(editFltContext.numericexpression);
	}

}
