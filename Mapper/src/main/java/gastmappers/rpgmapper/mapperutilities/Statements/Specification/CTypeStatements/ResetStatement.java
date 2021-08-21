package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ResetStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private boolean noKey, all;
	private Factor variableReset;

	public ResetStatement(Opcode opcode) {
		super(opcode);
	}

	public boolean isNoKey() {
		return noKey;
	}

	public void setNoKey(boolean noKey) {
		this.noKey = noKey;
	}

	public boolean isAll() {
		return all;
	}

	public void setAll(boolean all) {
		this.all = all;
	}

	public Factor getVariableReset() {
		return variableReset;
	}

	public void setVariableReset(Factor variableReset) {
		this.variableReset = variableReset;
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
		int identifierIndex = getSpecificChildIndex(parseTree, "IdentifierContext");

		for (int i = identifierIndex; i < parseTree.getChildCount(); i++) {
			Factor factor = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(i));

			switch (factor.getValue().toUpperCase()) {
			case "*NOKEY":
				this.noKey = true;
				break;
			case "*ALL":
				this.all = true;
				break;
			default:
				this.variableReset = factor;
				break;
			}
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.noKey = getLeftFactor(parseTree) != null;
		this.all = getFactor(parseTree, "rightFactor") != null;
		this.variableReset = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}

}
