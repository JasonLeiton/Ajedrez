package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class InStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private boolean constantLock;
	private Factor dataArea;

	public InStatement(Opcode opcode) {
		super(opcode);

	}

	public boolean isConstantLock() {
		return constantLock;
	}

	public void setConstantLock(boolean constantLock) {
		this.constantLock = constantLock;
	}

	public Factor getDataArea() {
		return dataArea;
	}

	public void setDataArea(Factor dataArea) {
		this.dataArea = dataArea;
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
		int identifierContext = getSpecificChildIndex(parseTree, "IdentifierContext");

		Factor identifierFactor = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierContext));

		this.constantLock = identifierFactor.getValue().toUpperCase().contentEquals("*LOCK");

		if (!this.constantLock) {
			this.dataArea = identifierFactor;
		} else {
			identifierContext++;
			this.dataArea = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierContext));
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		try {
			this.constantLock = this.getLeftFactor(parseTree).getValue().toUpperCase().contentEquals("*LOCK");
		} catch (Exception e) {
			this.constantLock = false;
		}
		this.dataArea = this.getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
