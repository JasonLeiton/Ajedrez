package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class KfldStatement extends CTypeStatement {

	private Factor keyField;

	public KfldStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getKeyField() {
		return keyField;
	}

	public void setKeyField(Factor keyField) {
		this.keyField = keyField;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		Cspec_fixed_standard_partsContext kfldContext = (Cspec_fixed_standard_partsContext) parseTree;
		String value = kfldContext.result.getText();
		this.keyField = new LiteralFactor(value);

		this.setType("Statement");
	}

}
