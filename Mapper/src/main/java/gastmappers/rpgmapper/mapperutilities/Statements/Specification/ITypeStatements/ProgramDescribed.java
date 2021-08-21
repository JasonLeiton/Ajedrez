package gastmappers.rpgmapper.mapperutilities.Statements.Specification.ITypeStatements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.ITypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.ITypeStatements.Codes.RecordTypeCodes;

public class ProgramDescribed extends ITypeStatement {

	private String formatEntryContinuation;
	private Factor sequencing, sequenceNumber, recordIdentifyingIndicator;
	private boolean option;
	private List<RecordTypeCodes> listCodes;

	public ProgramDescribed(String inputType) {
		this.inputType = inputType;
		this.listCodes = new ArrayList<RecordTypeCodes>();
	}

	public String getFormatEntryContinuation() {
		return formatEntryContinuation;
	}

	public void setFormatEntryContinuation(String formatEntryContinuation) {
		this.formatEntryContinuation = formatEntryContinuation;
	}

	public Factor getSequencing() {
		return sequencing;
	}

	public void setSequencing(Factor sequencing) {
		this.sequencing = sequencing;
	}

	public Factor getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(Factor sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public Factor getRecordIdentifyingIndicator() {
		return recordIdentifyingIndicator;
	}

	public void setRecordIdentifyingIndicator(Factor recordIdentifyingIndicator) {
		this.recordIdentifyingIndicator = recordIdentifyingIndicator;
	}

	public boolean isOption() {
		return option;
	}

	public void setOption(boolean option) {
		this.option = option;
	}

	public List<RecordTypeCodes> getListCodes() {
		return listCodes;
	}

	public void setListCodes(List<RecordTypeCodes> listCodes) {
		this.listCodes = listCodes;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		ParseTree context = parseTree.getChild(getContext(parseTree));

		this.sequencing = getFactorColumn(context, 0);
		this.sequenceNumber = getFactorColumn(context, 1);
		this.option = getBooleanColumn(context, 2, "O");
		this.recordIdentifyingIndicator = getFactorColumn(context, 3);
		addCodes(context.getChild(4).getText());

		char continuationChar = getCharColumn(parseTree, 1);
		if (continuationChar == 'A') {
			this.formatEntryContinuation = "AND";
		} else if (continuationChar == 'O') {
			this.formatEntryContinuation = "OR";
		}

		this.setType("IType Statement");
	}

	private void addCodes(String codes) {
		String[] singleCodes = codes.split("(?<=\\G........)");
		for (int i = 0; i < singleCodes.length; i++) {
			if (!codeIsEmpty(singleCodes[i])) {
				this.listCodes.add(new RecordTypeCodes(singleCodes[i]));
			}
		}
	}

	private boolean codeIsEmpty(String code) {
		return code.replace(" ", "").contentEquals("");
	}

}
