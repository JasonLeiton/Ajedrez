package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationManager;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class PTypeStatement extends Statement {

	private String procedureName;
	private boolean exportedFlag;
	private GroupStatements listStatements;

	public PTypeStatement() {
		this.listStatements = new GroupStatements();
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public boolean isExportedFlag() {
		return exportedFlag;
	}

	public void setExportedFlag(boolean exportedFlag) {
		this.exportedFlag = exportedFlag;
	}

	public GroupStatements getListStatements() {
		return listStatements;
	}

	public void setListStatements(GroupStatements listStatements) {
		this.listStatements = listStatements;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.procedureName = getProcedureName(parseTree.getChild(0));
		this.exportedFlag = getExportedFlag(parseTree.getChild(0));

		for (int i = 1; i < parseTree.getChildCount() - 1; i++) {
			ParseTree child = parseTree.getChild(i);
			if (!isDirective(child)) {
				SpecificationManager statement = new SpecificationManager();
				this.listStatements.addStatement(statement.createStatement(child));
			}
		}

		this.setType("PType Statement");
	}

	private String getProcedureName(ParseTree parseTree) {
		ParseTree nodeName = getProcedureNode(parseTree, "procedureName");
		return FreeContextAnalyzer.getFreeContextFactor(nodeName).getValue();
	}

	private boolean getExportedFlag(ParseTree parseTree) {
		ParseTree exportedNode = getProcedureNode(parseTree, "exportedFlag");
		Factor valueNode = FreeContextAnalyzer.getFreeContextFactor(exportedNode);
		if (valueNode != null) {
			String value = valueNode.getValue().toUpperCase().replace(" ", "");
			return value.contentEquals("EXPORT");
		} else {
			return false;
		}

	}

	private ParseTree getProcedureNode(ParseTree parseTree, String function) {
		if (parseTree.getChildCount() > 1) {
			int i = 0;
			while (i < parseTree.getChildCount()) {
				ParseTree child = parseTree.getChild(i);
				String className = child.getClass().getSimpleName();
				if (!className.contentEquals("TerminalNodeImpl") && function.contentEquals("procedureName")) {
					return child;
				} else if (className.contentEquals("TerminalNodeImpl") && i + 1 == parseTree.getChildCount()
						&& function.contentEquals("exportedFlag")) {
					return child;
				}
				i++;
			}
			return null;
		} else {
			return getProcedureNode(parseTree.getChild(0), function);
		}
	}

	/**
	 * Método para determinar si la declarión corresponde a /FREE o /END-FREE para
	 * no tomarla en cuenta.
	 * 
	 * @param parseTree
	 * @return
	 */
	private boolean isDirective(ParseTree parseTree) {
		String className = parseTree.getChild(0).getChild(0).getClass().getSimpleName();
		return className.contentEquals("DirectiveContext");
	}

}
