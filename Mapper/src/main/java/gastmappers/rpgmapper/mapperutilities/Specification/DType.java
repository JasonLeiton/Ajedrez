package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.DTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class DType extends Specification {

	public DType(SpecificationEnum specificationType) {
		super(specificationType);

	}

	@Override
	public Statement constructStatement(ParseTree parseTree) {
		DTypeStatement dtypeStatement = new DTypeStatement();

		if (childIsParm(parseTree)) {
			dtypeStatement.buildParms(parseTree);
		}

		if (!child0IsTerminal(parseTree)) {
			parseTree = parseTree.getChild(0);
		}

		dtypeStatement.constructStatement(parseTree);
		return dtypeStatement;
	}

	private boolean child0IsTerminal(ParseTree parseTree) {
		return parseTree.getChild(0).getClass().getSimpleName().contentEquals("TerminalNodeImpl");
	}

	private boolean childIsParm(ParseTree parseTree) {
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("Pi_parm_fixedContext")) {
				return true;
			}
			i++;
		}
		return false;
	}

	@Override
	public Statement constructSubroutine(ParseTree parseTree) {

		return null;
	}

}
