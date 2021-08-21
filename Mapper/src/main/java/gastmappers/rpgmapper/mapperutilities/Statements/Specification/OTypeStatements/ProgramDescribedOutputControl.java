package gastmappers.rpgmapper.mapperutilities.Statements.Specification.OTypeStatements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.OTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class ProgramDescribedOutputControl extends OTypeStatement {

	private String name, exceptName;
	private char outputType, outputControl;
	private List<String> controllingIndicators;
	private Factor lineSpacingBeforePrinting, lineSpacingAfterPrinting, lineSkippingBefore, lineSkippingAfter;

	public ProgramDescribedOutputControl(String outputDescriptionType) {
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

	public char getOutputType() {
		return outputType;
	}

	public void setOutputType(char outputType) {
		this.outputType = outputType;
	}

	public char getOutputControl() {
		return outputControl;
	}

	public void setOutputControl(char outputControl) {
		this.outputControl = outputControl;
	}

	public List<String> getControllingIndicators() {
		return controllingIndicators;
	}

	public void setControllingIndicators(List<String> controllingIndicators) {
		this.controllingIndicators = controllingIndicators;
	}

	public Factor getLineSpacingBeforePrinting() {
		return lineSpacingBeforePrinting;
	}

	public void setLineSpacingBeforePrinting(Factor lineSpacingBeforePrinting) {
		this.lineSpacingBeforePrinting = lineSpacingBeforePrinting;
	}

	public Factor getLineSpacingAfterPrinting() {
		return lineSpacingAfterPrinting;
	}

	public void setLineSpacingAfterPrinting(Factor lineSpacingAfterPrinting) {
		this.lineSpacingAfterPrinting = lineSpacingAfterPrinting;
	}

	public Factor getLineSkippingBefore() {
		return lineSkippingBefore;
	}

	public void setLineSkippingBefore(Factor lineSkippingBefore) {
		this.lineSkippingBefore = lineSkippingBefore;
	}

	public Factor getLineSkippingAfter() {
		return lineSkippingAfter;
	}

	public void setLineSkippingAfter(Factor lineSkippingAfter) {
		this.lineSkippingAfter = lineSkippingAfter;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		ParseTree context = parseTree.getChild(3);
		if(context == null) context = parseTree.getChild(1);

		this.name = getStringColumn(parseTree, 1);
		this.outputType = getCharColumn(parseTree, 2);

		this.outputControl = getCharColumn(context, 0);
		this.controllingIndicators = addControllingIndicators(context, 1, 3);
		this.exceptName = getStringColumn(context, 4);
		this.lineSpacingBeforePrinting = getFactorColumn(context, 5);
		this.lineSpacingAfterPrinting = getFactorColumn(context, 6);
		this.lineSkippingBefore = getFactorColumn(context, 7);
		this.lineSkippingAfter = getFactorColumn(context, 8);

		this.setType("OType Statement");
	}

}
