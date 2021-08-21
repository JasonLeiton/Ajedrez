package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_remContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class RemFunction extends BuiltInFunction implements Factor {

	private ComparisonFactor remFactor;

	public RemFunction(String functionName) {
		super(functionName);
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
		this.remFactor = new ComparisonFactor();
		Bif_remContext remainderContext = (Bif_remContext) parseTree;
		Operand operand = new Operand("%");
		Factor leftFactor = FreeContextAnalyzer.getFreeContextFactor(remainderContext.numerator);
		Factor rightFactor = FreeContextAnalyzer.getFreeContextFactor(remainderContext.denominator);
		this.remFactor.setOperands(leftFactor, rightFactor, operand);
	}

}
