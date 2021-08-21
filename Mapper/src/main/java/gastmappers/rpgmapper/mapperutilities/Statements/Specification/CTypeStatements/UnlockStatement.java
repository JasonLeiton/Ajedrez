package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;

public class UnlockStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor unlockArea;

	public UnlockStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getUnlockArea() {
		return unlockArea;
	}

	public void setUnlockArea(Factor unlockArea) {
		this.unlockArea = unlockArea;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		this.unlockArea = getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.unlockArea = getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);

	}
}
