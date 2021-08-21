package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class TestZStatement extends CTypeStatement {

	private Factor result;
	private boolean positive, negative, other;

	public TestZStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getResult() {
		return result;
	}

	public void setResult(Factor result) {
		this.result = result;
	}

	public boolean isPositive() {
		return positive;
	}

	public void setPositive(boolean positive) {
		this.positive = positive;
	}

	public boolean isNegative() {
		return negative;
	}

	public void setNegative(boolean negative) {
		this.negative = negative;
	}

	public boolean isOther() {
		return other;
	}

	public void setOther(boolean other) {
		this.other = other;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.result = getFactor(parseTree, "resultFactor");

		setIndicators(parseTree);

		this.setControlIndicators(parseTree);
		this.setType("Statement");

	}

	protected void setIndicators(ParseTree parseTree) {
		RpgParser.Cspec_fixed_standard_partsContext context = (RpgParser.Cspec_fixed_standard_partsContext) parseTree
				.getChild(5).getChild(0).getChild(1);

		this.positive = !context.hi.getChild(0).getText().contentEquals("  ");
		this.negative = !context.lo.getChild(0).getText().contentEquals("  ");
		this.other = !context.eq.getChild(0).getText().contentEquals("  ");
	}

}
