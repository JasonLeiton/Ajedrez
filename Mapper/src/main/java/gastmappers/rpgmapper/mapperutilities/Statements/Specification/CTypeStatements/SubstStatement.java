package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class SubstStatement extends CTypeStatement {

	private Factor lengthValue, source, startValue, target;
	private boolean padFlag;

	public Factor getLengthValue() {
		return lengthValue;
	}

	public void setLengthValue(Factor lengthValue) {
		this.lengthValue = lengthValue;
	}

	public Factor getSource() {
		return source;
	}

	public void setSource(Factor source) {
		this.source = source;
	}

	public Factor getStartValue() {
		return startValue;
	}

	public void setStartValue(Factor startValue) {
		this.startValue = startValue;
	}

	public Factor getTarget() {
		return target;
	}

	public void setTarget(Factor target) {
		this.target = target;
	}

	public boolean isPadFlag() {
		return padFlag;
	}

	public void setPadFlag(boolean padFlag) {
		this.padFlag = padFlag;
	}

	public SubstStatement(Opcode opcode) {
		super(opcode);

	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.lengthValue = getLeftFactor(parseTree);
		this.source = getFactor(parseTree, "rightFactor");
		this.startValue = getFactorExtender(parseTree);
		this.target = getFactor(parseTree, "resultFactor");

		this.padFlag = hasExtender(parseTree, "P");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
