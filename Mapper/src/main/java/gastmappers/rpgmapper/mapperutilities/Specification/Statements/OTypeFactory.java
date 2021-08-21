package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Specification.OTypeStatements.ProgramDescribedOutputControl;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.OTypeStatements.ProgramDescribedOutputFile;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class OTypeFactory {

	public static Statement buildStatement(ParseTree parseTree) {
		Statement statement = null;
		String childClassName = getChildClass(parseTree);
		switch (childClassName) {
		case "fixedpgmdesc1":
		case "fixedpgmfield":
			statement = new ProgramDescribedOutputControl("Program-Described Output File Control Entries");
			break;
		case "fixedpgmdesc2":
			statement = new ProgramDescribedOutputFile("Program-Described Output File Control Entries");
			break;
		default:
			break;
		}
		return statement;
	}

	private static String getChildClass(ParseTree parseTree) {
		String childClassName = "";
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (!className.contentEquals("TerminalNodeImpl")) {
				childClassName = className.replace("Os", "");
				childClassName = childClassName.replace("Context", "");
				childClassName = childClassName.replace("_", "");
				break;
			}
			i++;
		}
		return childClassName;
	}

}
