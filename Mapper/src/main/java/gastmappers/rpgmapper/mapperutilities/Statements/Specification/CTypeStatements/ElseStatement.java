package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class ElseStatement extends CTypeStatement {

	public ElseStatement(Opcode opcode) {
		super(opcode);

	}

	@Override
	public void constructStatement(ParseTree parseTree) {

	}

}
