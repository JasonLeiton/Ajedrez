package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public abstract class LookUpStatement extends CTypeStatement {

	private Factor searchArgument;
	private boolean greater, less, equal;

	public LookUpStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getSearchArgument() {
		return searchArgument;
	}

	public void setSearchArgument(Factor searchArgument) {
		this.searchArgument = searchArgument;
	}

	public boolean isGreater() {
		return greater;
	}

	public void setGreater(boolean greater) {
		this.greater = greater;
	}

	public boolean isLess() {
		return less;
	}

	public void setLess(boolean less) {
		this.less = less;
	}

	public boolean isEqual() {
		return equal;
	}

	public void setEqual(boolean equal) {
		this.equal = equal;
	}

	protected void setIndicators(ParseTree parseTree) {
		Cspec_fixed_standard_partsContext context = (Cspec_fixed_standard_partsContext) parseTree.getChild(5)
				.getChild(0).getChild(1);
		this.greater = !context.hi.getChild(0).getText().contentEquals("  ");
		this.less = !context.lo.getChild(0).getText().contentEquals("  ");
		this.equal = !context.eq.getChild(0).getText().contentEquals("  ");
	}
}
