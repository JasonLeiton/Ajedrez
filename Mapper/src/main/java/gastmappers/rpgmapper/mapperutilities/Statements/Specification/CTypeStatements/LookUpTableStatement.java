package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class LookUpTableStatement extends LookUpStatement {

	private Factor tableName, alternateTableName;

	public LookUpTableStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getTableName() {
		return tableName;
	}

	public void setTableName(Factor tableName) {
		this.tableName = tableName;
	}

	public Factor getAlternateTableName() {
		return alternateTableName;
	}

	public void setAlternateTableName(Factor alternateTableName) {
		this.alternateTableName = alternateTableName;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		setSearchArgument(getLeftFactor(parseTree));
		this.setIndicators(parseTree);

		this.tableName = getFactor(parseTree, "rightFactor");
		this.alternateTableName = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
