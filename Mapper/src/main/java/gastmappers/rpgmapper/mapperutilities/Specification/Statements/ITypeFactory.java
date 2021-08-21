package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Specification.ITypeStatements.ExternallyDescribedFieldDescriptions;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.ITypeStatements.ExternallyDescribedFiles;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.ITypeStatements.ProgramDescribed;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class ITypeFactory {

	public static Statement buildStatement(ParseTree parseTree) {
		Statement statement = null;
		String childClassName = getChildClass(parseTree);
		switch (childClassName) {
		case "rec":
			statement = new ProgramDescribed("Program-Described Files");
			break;
		case "externalrec":
			statement = new ExternallyDescribedFiles("Externally Described Files");
			break;
		case "externalfield":
			statement = new ExternallyDescribedFieldDescriptions("Externally Described Field Descriptions");
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
				childClassName = className.replace("Is", "");
				childClassName = childClassName.replace("Context", "");
				childClassName = childClassName.replace("_", "");
				break;
			}
			i++;
		}
		return childClassName;
	}

}
