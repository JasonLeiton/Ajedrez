package gastmappers.rpgmapper.mapperutilities.Statements.Specification.ITypeStatements.Codes;

public class RecordTypeCodes {

	private int position;
	private boolean notFlag;
	private char comparisonType, recordIdentifyingValue;

	public RecordTypeCodes(String typeCode) {
		this.position = getPosition(typeCode.substring(0, 5));
		this.notFlag = typeCode.charAt(5) == 'N';
		this.comparisonType = typeCode.charAt(6);
		this.recordIdentifyingValue = typeCode.charAt(7);
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public boolean isNotFlag() {
		return notFlag;
	}

	public void setNotFlag(boolean notFlag) {
		this.notFlag = notFlag;
	}

	public char getComparisonType() {
		return comparisonType;
	}

	public void setComparisonType(char comparisonType) {
		this.comparisonType = comparisonType;
	}

	public char getRecordIdentifyingValue() {
		return recordIdentifyingValue;
	}

	public void setRecordIdentifyingValue(char recordIdentifyingValue) {
		this.recordIdentifyingValue = recordIdentifyingValue;
	}

	private int getPosition(String position) {
		try {
			return Integer.parseInt(position);
		} catch (Exception e) {
			return 0;
		}
	}
}
