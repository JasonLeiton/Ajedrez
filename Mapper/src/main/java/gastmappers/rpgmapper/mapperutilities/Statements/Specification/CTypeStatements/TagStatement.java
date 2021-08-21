package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class TagStatement extends CTypeStatement {

	private Factor label;

	public TagStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getLabel() {
		return label;
	}

	public void setLabel(Factor label) {
		this.label = label;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.label = this.getLeftFactor(parseTree);

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
