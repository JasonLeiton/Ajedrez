package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.FactorContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.DurationCodes;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class SubDurStatement extends CTypeStatement {

	private Factor leftOperand, rightOperand, resultingDate;
	private Operand operand;
	private String duration, durationMember;

	public SubDurStatement(Opcode opcode) {
		super(opcode);

	}
	
	public Operand getOperand() {
		return operand;
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
	}


	public Factor getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(Factor leftOperand) {
		this.leftOperand = leftOperand;
	}

	public Factor getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(Factor rightOperand) {
		this.rightOperand = rightOperand;
	}

	public Factor getResultingDate() {
		return resultingDate;
	}

	public void setResultingDate(Factor resultingDate) {
		this.resultingDate = resultingDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDurationMember() {
		return durationMember;
	}

	public void setDurationMember(String durationMember) {
		this.durationMember = durationMember;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.leftOperand = getLeftFactor(parseTree);
		this.resultingDate = getFactor(parseTree, "resultFactor");
		this.rightOperand = getFactor(parseTree, "rightFactor");

		if (!resultHasExtender(resultingDate)) {
			this.duration = getDurationCode(parseTree);
			this.durationMember = "RightOperand";
		}

		if (leftOperand == null) {
			this.leftOperand = this.resultingDate;
		}

		this.operand = new Operand("-");
		this.setControlIndicators(parseTree);
		this.setType("Statement");

	}

	private boolean resultHasExtender(Factor factor) {
		if (factor.getValue().contains(":")) {
			String[] values = factor.getValue().split(":");
			this.duration = DurationCodes.getDurationCode(values[1]);
			this.resultingDate = new LiteralFactor(values[0]);
			this.durationMember = "Result";
			return true;
		}
		return false;
	}

	private String getDurationCode(ParseTree parseTree) {
		parseTree = parseTree.getChild(5).getChild(0).getChild(1);
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("FactorContext")) {
				String duration = ((FactorContext) child).constant2.getText();
				return DurationCodes.getDurationCode(duration);
			}
			i++;
		}
		return "";
	}

}
