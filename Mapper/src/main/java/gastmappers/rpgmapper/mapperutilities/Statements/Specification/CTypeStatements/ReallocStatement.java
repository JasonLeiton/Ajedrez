package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class ReallocStatement extends CTypeStatement {

	private Factor lengthBytes, pointerVariable;

	public ReallocStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getLengthBytes() {
		return lengthBytes;
	}

	public void setLengthBytes(Factor lengthBytes) {
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
		this.lengthBytes = getFactor(parseTree, "rightFactor");
		this.pointerVariable = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
