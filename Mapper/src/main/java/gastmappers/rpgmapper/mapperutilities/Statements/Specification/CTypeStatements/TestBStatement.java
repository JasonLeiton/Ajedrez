package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class TestBStatement extends CTypeStatement {

	private Factor result, testBits;
	private boolean xor, mix, equal;

	public TestBStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getResult() {
		return result;
	}

	public void setResult(Factor result) {
		this.result = result;
	}

	public Factor getTestBits() {
		return testBits;
	}

	public void setTestBits(Factor testBits) {
		this.testBits = testBits;
	}

	public boolean isXor() {
		return xor;
	}

	public void setXor(boolean xor) {
		this.xor = xor;
	}

	public boolean isMix() {
		return mix;
	}

	public void setMix(boolean mix) {
		this.mix = mix;
	}

	public boolean isEqual() {
		return equal;
	}

	public void setEqual(boolean equal) {
		this.equal = equal;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		result = getFactor(parseTree, "resultFactor");
		testBits = getFactor(parseTree, "rightFactor");
		setIndicators(parseTree);

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

	protected void setIndicators(ParseTree parseTree) {
		RpgParser.Cspec_fixed_standard_partsContext context = (RpgParser.Cspec_fixed_standard_partsContext) parseTree
				.getChild(5).getChild(0).getChild(1);

		this.xor = !context.hi.getChild(0).getText().contentEquals("  ");
		this.mix = !context.lo.getChild(0).getText().contentEquals("  ");
		this.equal = !context.eq.getChild(0).getText().contentEquals("  ");
	}

}
