package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public abstract class ITypeStatement extends Statement {

	protected String inputType;

	protected int getContext(ParseTree parseTree) {
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (!className.contentEquals("TerminalNodeImpl")) {
				break;
			}
			i++;
		}
		return i;
	}

}
