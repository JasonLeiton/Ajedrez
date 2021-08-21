package gastmappers.rpgmapper.mapperutilities.Statements.Types;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.LookUpArrayStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.LookUpTableStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class LookUpFactory extends CTypeStatement {

	private Statement lookUpStatement;

	public LookUpFactory(Opcode opcode) {
		super(opcode);

	}

	public Statement getLookUpStatement() {
		return lookUpStatement;
	}

	public void setLookUpStatement(Statement lookUpStatement) {
		this.lookUpStatement = lookUpStatement;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		Factor rightFactor = getFactor(parseTree, "rightFactor");
		if (rightFactor.getClass().getSimpleName().contentEquals("ArrayFactor")) {
			lookUpStatement = new LookUpArrayStatement(getOpcode());
		} else {
			lookUpStatement = new LookUpTableStatement(getOpcode());
		}
		lookUpStatement.constructStatement(parseTree);
	}

}
