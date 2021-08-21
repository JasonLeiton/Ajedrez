package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class EndStatement extends CTypeStatement {

	private Factor factor;

	public EndStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getFactor() {
		return factor;
	}

	public void setFactor(Factor factor) {
		this.factor = factor;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.factor = this.getFactor(parseTree, "rightFactor");
	}

	public Factor getEndDoFactor() {
		if (this.factor == null) {
			return new LiteralFactor("1");
		}
		return this.factor;
	}
}
