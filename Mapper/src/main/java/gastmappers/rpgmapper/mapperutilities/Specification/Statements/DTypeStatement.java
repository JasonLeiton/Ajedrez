package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class DTypeStatement extends Statement {

	private String fileName, dataSetType;
	private boolean externallyDescribed, dataArea;
	private int startLocation, endingLocation, decimalPositions;
	private char dataType;
	private List<Factor> listKeywords;

	private GroupStatements listParms;

	public DTypeStatement() {
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDataSetType() {
		return dataSetType;
	}

	public void setDataSetType(String dataSetType) {
		this.dataSetType = dataSetType;
	}

	public boolean isExternallyDescribed() {
		return externallyDescribed;
	}

	public void setExternallyDescribed(boolean externallyDescribed) {
		this.externallyDescribed = externallyDescribed;
	}

	public boolean isDataArea() {
		return dataArea;
	}

	public void setDataArea(boolean dataArea) {
		this.dataArea = dataArea;
	}

	public int getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(int startLocation) {
		this.startLocation = startLocation;
	}

	public int getEndingLocation() {
		return endingLocation;
	}

	public void setEndingLocation(int endingLocation) {
		this.endingLocation = endingLocation;
	}

	public int getDecimalPositions() {
		return decimalPositions;
	}

	public void setDecimalPositions(int decimalPositions) {
		this.decimalPositions = decimalPositions;
	}

	public char getDataType() {
		return dataType;
	}

	public void setDataType(char dataType) {
		this.dataType = dataType;
	}

	public List<Factor> getListKeywords() {
		return listKeywords;
	}

	public void setListKeywords(List<Factor> listKeywords) {
		this.listKeywords = listKeywords;
	}

	public GroupStatements getListParms() {
		return listParms;
	}

	public void setListParms(GroupStatements listParms) {
		this.listParms = listParms;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.fileName = getStringColumn(parseTree, 1);
		this.externallyDescribed = getBooleanColumn(parseTree, 2, "E");
		this.dataArea = getBooleanColumn(parseTree, 3, "U");
		this.dataSetType = getStringColumn(parseTree, 4);
		this.startLocation = getNumberColumn(parseTree, 5);
		this.endingLocation = getNumberColumn(parseTree, 6);
		this.dataType = getCharColumn(parseTree, 7);
		this.decimalPositions = getNumberColumn(parseTree, 8);
		this.listKeywords = this.getKeywordList(parseTree);

		this.setType("DType Statement");
	}

	public void buildParms(ParseTree parseTree) {
		this.listParms = new GroupStatements();
		for (int i = 1; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			DTypeStatement dtypeStatement = new DTypeStatement();
			dtypeStatement.constructStatement(child.getChild(0));
			this.listParms.addStatement(dtypeStatement);
		}
	}

}
