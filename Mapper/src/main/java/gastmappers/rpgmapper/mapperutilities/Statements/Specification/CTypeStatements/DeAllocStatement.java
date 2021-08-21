package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;

public class DeAllocStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor pointerVariable;
	private boolean setNull;

	public DeAllocStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getPointerVariable() {
		return pointerVariable;
	}

	public void setPointerVariable(Factor pointerVariable) {
		this.pointerVariable = pointerVariable;
	}

	public boolean isSetNull() {
		return setNull;
	}

	public void setSetNull(boolean setNull) {
		this.setNull = setNull;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.setNull = hasExtender(parseTree, "N");

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		// Análisis para el caso en que sea FreeContext
		this.pointerVariable = getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.pointerVariable = this.getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}
}
