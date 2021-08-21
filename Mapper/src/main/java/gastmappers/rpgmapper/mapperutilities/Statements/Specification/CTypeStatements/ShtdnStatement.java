package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class ShtdnStatement extends CTypeStatement {

	private Factor label, flagValue;

	public ShtdnStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getLabel() {
		return label;
	}

	public void setLabel(Factor label) {
		this.label = label;
	}

	public Factor getFlagValue() {
		return flagValue;
	}

	public void setFlagValue(Factor flagValue) {
		this.flagValue = flagValue;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		Cspec_fixed_standard_partsContext context = (Cspec_fixed_standard_partsContext) parseTree.getChild(5)
				.getChild(0).getChild(1);
		this.flagValue = new LiteralFactor(context.hi.getText());
		this.label = getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
