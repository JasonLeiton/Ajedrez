package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_diffContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.DurationCodes;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class DiffFunction extends BuiltInFunction implements Factor {

	private Factor leftFactor, rightFactor;
	private String durationCode;

	public DiffFunction(String functionName) {
		super(functionName);
	}

	public Factor getLeftFactor() {
		return leftFactor;
	}

	public void setLeftFactor(Factor leftFactor) {
		this.leftFactor = leftFactor;
	}

	public Factor getRightFactor() {
		return rightFactor;
	}

	public void setRightFactor(Factor rightFactor) {
		this.rightFactor = rightFactor;
	}

	public String getDurationCode() {
		return durationCode;
	}

	public void setDurationCode(String durationCode) {
		this.durationCode = durationCode;
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
		Bif_diffContext diffContext = (Bif_diffContext) parseTree;
		this.leftFactor = FreeContextAnalyzer.getFreeContextFactor(diffContext.op1);
		this.rightFactor = FreeContextAnalyzer.getFreeContextFactor(diffContext.op2);
		this.durationCode = DurationCodes
				.getDurationCode(FreeContextAnalyzer.getFreeContextFactor(diffContext.format).getValue());
	}

}
