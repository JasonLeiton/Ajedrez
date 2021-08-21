package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ClearStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor variable;
	private boolean noKeyFlag, allFlag;

	public ClearStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getVariable() {
		return variable;
	}

	public void setVariable(Factor variable) {
		this.variable = variable;
	}

	public boolean isNoKeyFlag() {
		return noKeyFlag;
	}

	public void setNoKeyFlag(boolean noKeyFlag) {
		this.noKeyFlag = noKeyFlag;
	}

	public boolean isAllFlag() {
		return allFlag;
	}

	public void setAllFlag(boolean allFlag) {
		this.allFlag = allFlag;
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
		// Análisis para el caso en que sea FreeContext
		this.variable = getLastChildFactor(parseTree);

		for (int i = 1; i < parseTree.getChildCount() - 1; i++) {
			String childString = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(i)).getValue();

			if (!this.noKeyFlag) {
				this.noKeyFlag = childString.toUpperCase().contentEquals("*NOKEY");
			}

			if (!this.allFlag) {
				this.allFlag = childString.toUpperCase().contentEquals("*ALL");
			}
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.variable = this.getFactor(parseTree, "resultFactor");
		try {
			this.noKeyFlag = this.getLeftFactor(parseTree).getValue().toUpperCase().contentEquals("*NOKEY");
			this.allFlag = this.getFactor(parseTree, "rightFactor").getValue().toUpperCase().contentEquals("*ALL");
		} catch (Exception e) {
			// TODO: handle exception
		}

		this.setControlIndicators(parseTree);
	}

}
