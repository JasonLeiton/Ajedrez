package gastmappers.rpgmapper.mapperutilities.Statements.Specification.OTypeStatements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.OTypeStatement;

public class ProgramDescribedOutputFile extends OTypeStatement {

	private String name, exceptName, fileAddition;
	private char outputType;
	private List<String> controllingIndicators;

	public ProgramDescribedOutputFile(String outputDescriptionType) {
		this.outputDescriptionType = outputDescriptionType;
		this.controllingIndicators = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExceptName() {
		return exceptName;
	}

	public void setExceptName(String exceptName) {
		this.exceptName = exceptName;
	}

	public String getFileAddition() {
		return fileAddition;
	}

	public void setFileAddition(String fileAddition) {
		this.fileAddition = fileAddition;
	}

	public char getOutputType() {
		return outputType;
	}

	public void setOutputType(char outputType) {
		this.outputType = outputType;
	}

	public List<String> getControllingIndicators() {
		return controllingIndicators;
	}

	public void setControllingIndicators(List<String> controllingIndicators) {
		this.controllingIndicators = controllingIndicators;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		ParseTree context = parseTree.getChild(3);

		this.name = getStringColumn(parseTree, 1);
		this.outputType = getCharColumn(parseTree, 2);

		this.fileAddition = getStringColumn(context, 0);
		this.controllingIndicators = addControllingIndicators(context, 1, 3);
		this.exceptName = getStringColumn(context, 4);

		this.setType("OType Statement");
	}

}
