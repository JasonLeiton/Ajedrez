package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class GotoStatement extends CTypeStatement {

	private String branchLabel;

	public GotoStatement(Opcode opcode) {
		super(opcode);

	}

	public String getBranchLabel() {
		return branchLabel;
	}

	public void setBranchLabel(String branchLabel) {
		this.branchLabel = branchLabel;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.branchLabel = this.getFactor(parseTree, "rightFactor").getValue();

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
