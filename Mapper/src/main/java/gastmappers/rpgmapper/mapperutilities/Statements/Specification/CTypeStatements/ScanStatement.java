package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class ScanStatement extends CTypeStatement {

	private Factor argumentValue, argumentLength, searchVarValue, startValue, positionValue;

	public ScanStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getArgumentValue() {
		return argumentValue;
	}

	public void setArgumentValue(Factor argumentValue) {
		this.argumentValue = argumentValue;
	}

	public Factor getArgumentLength() {
		return argumentLength;
	}

	public void setArgumentLength(Factor argumentLength) {
		this.argumentLength = argumentLength;
	}

	public Factor getSearchVarValue() {
		return searchVarValue;
	}

	public void setSearchVarValue(Factor searchVarValue) {
		this.searchVarValue = searchVarValue;
	}

	public Factor getStartValue() {
		return startValue;
	}

	public void setStartValue(Factor startValue) {
		this.startValue = startValue;
	}

	public Factor getPositionValue() {
		return positionValue;
	}

	public void setPositionValue(Factor positionValue) {
		this.positionValue = positionValue;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.argumentValue = getLeftFactorExtender(parseTree, "left");
		this.argumentLength = getLeftFactorExtender(parseTree, "right");

		this.searchVarValue = getFactor(parseTree, "rightFactor");
		this.startValue = getFactorExtender(parseTree);

		this.positionValue = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
