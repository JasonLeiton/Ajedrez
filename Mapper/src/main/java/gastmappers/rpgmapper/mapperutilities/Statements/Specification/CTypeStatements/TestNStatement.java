package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class TestNStatement extends CTypeStatement {

	private Factor result;
	private boolean num, mix, blank;

	public TestNStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getResult() {
		return result;
	}

	public void setResult(Factor result) {
		this.result = result;
	}

	public boolean isNum() {
		return num;
	}

	public void setNum(boolean num) {
		this.num = num;
	}

	public boolean isMix() {
		return mix;
	}

	public void setMix(boolean mix) {
		this.mix = mix;
	}

	public boolean isBlank() {
		return blank;
	}

	public void setBlank(boolean blank) {
		this.blank = blank;
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

		this.num = !context.hi.getChild(0).getText().contentEquals("  ");
		this.mix = !context.lo.getChild(0).getText().contentEquals("  ");
		this.blank = !context.eq.getChild(0).getText().contentEquals("  ");
	}

}
