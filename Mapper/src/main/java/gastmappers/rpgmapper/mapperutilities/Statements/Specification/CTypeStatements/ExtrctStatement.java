package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;
import org.rpgleparser.RpgParser.FactorContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.DurationCodes;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class ExtrctStatement extends CTypeStatement {

	private Factor date, numericValue;
	private String duration;

	public ExtrctStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getDate() {
		return date;
	}

	public void setDate(Factor date) {
		this.date = date;
	}

	public Factor getNumericValue() {
		return numericValue;
	}

	public void setNumericValue(Factor numericValue) {
		this.numericValue = numericValue;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.numericValue = getFactor(parseTree, "resultFactor");
		this.date = getFactor(parseTree, "rightFactor");
		this.duration = getDurationCode(parseTree);

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

	private String getDurationCode(ParseTree parseTree) {
		FactorContext factorContext = ((Cspec_fixed_standard_partsContext) parseTree.getChild(5).getChild(0)
				.getChild(1)).factor2;
		try {
			return DurationCodes.getDurationCode(factorContext.content2.getText());
		} catch (Exception e) {
			return DurationCodes.getDurationCode(factorContext.constant2.getText());
		}
	}

}
