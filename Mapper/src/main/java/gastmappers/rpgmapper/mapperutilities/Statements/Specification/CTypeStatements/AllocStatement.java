package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;


import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class AllocStatement extends CTypeStatement {

	private int lengthBytes;
	private Factor pointerVariable;

	public AllocStatement(Opcode opcode) {
		super(opcode);

	}

	public int getLengthBytes() {
		return lengthBytes;
	}

	public void setLengthBytes(int lengthBytes) {
		this.lengthBytes = lengthBytes;
	}

	public Factor getPointerVariable() {
		return pointerVariable;
	}

	public void setPointerVariable(Factor pointerVariable) {
		this.pointerVariable = pointerVariable;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.pointerVariable = this.getFactor(parseTree, "resultFactor");

		Factor lengthFactor = this.getFactor(parseTree, "rightFactor");
		this.lengthBytes = Integer.valueOf(lengthFactor.getValue());

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
