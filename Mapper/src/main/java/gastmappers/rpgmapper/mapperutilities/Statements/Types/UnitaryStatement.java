package gastmappers.rpgmapper.mapperutilities.Statements.Types;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class UnitaryStatement extends Statement {

	private Factor factor;

	public UnitaryStatement() {
	}

	public Factor getFactor() {
		return factor;
	}

	public void setFactor(Factor factor) {
		this.factor = factor;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

	}

	public void setOperand(Factor factor) {
		this.factor = factor;
	}

}
