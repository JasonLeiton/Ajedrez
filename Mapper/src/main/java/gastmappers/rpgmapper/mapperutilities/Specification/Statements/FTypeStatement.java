package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class FTypeStatement extends Statement {

	private String fileName, deviceType;
	private char fileType, fileDesignation, sequence, format, recordAddressType, fileOrganization;
	private boolean endFile, fileAddition, modeProcessing;
	private int recordLength, lengthKeyFields;
	private List<Factor> listKeywords;

	public FTypeStatement() {
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public char getFileType() {
		return fileType;
	}

	public void setFileType(char fileType) {
		this.fileType = fileType;
	}

	public char getFileDesignation() {
		return fileDesignation;
	}

	public void setFileDesignation(char fileDesignation) {
		this.fileDesignation = fileDesignation;
	}

	public char getSequence() {
		return sequence;
	}

	public void setSequence(char sequence) {
		this.sequence = sequence;
	}

	public char getFormat() {
		return format;
	}

	public void setFormat(char format) {
		this.format = format;
	}

	public char getRecordAddressType() {
		return recordAddressType;
	}

	public void setRecordAddressType(char recordAddressType) {
		this.recordAddressType = recordAddressType;
	}

	public char getFileOrganization() {
		return fileOrganization;
	}

	public void setFileOrganization(char fileOrganization) {
		this.fileOrganization = fileOrganization;
	}

	public boolean isEndFile() {
		return endFile;
	}

	public void setEndFile(boolean endFile) {
		this.endFile = endFile;
	}

	public boolean isFileAddition() {
		return fileAddition;
	}

	public void setFileAddition(boolean fileAddition) {
		this.fileAddition = fileAddition;
	}

	public boolean isModeProcessing() {
		return modeProcessing;
	}

	public void setModeProcessing(boolean modeProcessing) {
		this.modeProcessing = modeProcessing;
	}

	public int getRecordLength() {
		return recordLength;
	}

	public void setRecordLength(int recordLength) {
		this.recordLength = recordLength;
	}

	public int getLengthKeyFields() {
		return lengthKeyFields;
	}

	public void setLengthKeyFields(int lengthKeyFields) {
		this.lengthKeyFields = lengthKeyFields;
	}

	public List<Factor> getListKeywords() {
		return listKeywords;
	}

	public void setListKeywords(List<Factor> listKeywords) {
		this.listKeywords = listKeywords;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.fileName = getStringColumn(parseTree, 1);
		this.fileType = getCharColumn(parseTree, 2);
		this.fileDesignation = getCharColumn(parseTree, 3);
		this.endFile = getBooleanColumn(parseTree, 4, "E");
		this.fileAddition = getBooleanColumn(parseTree, 5, "A");
		this.sequence = getCharColumn(parseTree, 6);
		this.format = getCharColumn(parseTree, 7);
		this.recordLength = getNumberColumn(parseTree, 8);
		this.modeProcessing = getBooleanColumn(parseTree, 9, "L");
		this.lengthKeyFields = getNumberColumn(parseTree, 10);
		this.recordAddressType = getCharColumn(parseTree, 11);
		this.fileOrganization = getCharColumn(parseTree, 12);
		this.deviceType = getStringColumn(parseTree, 13);
		this.listKeywords = this.getKeywordList(parseTree);
	}

}
