package gastmappers.rpgmapper.mapperutilities.Statements.Specification.ITypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.ITypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class ExternallyDescribedFieldDescriptions extends ITypeStatement {

	private String externalFieldName, fieldName;
	private Factor controlField, matchField, fieldRecordRelation, plusFieldIndicator, minusFieldIndicator,
			blankFieldIndicator;

	public ExternallyDescribedFieldDescriptions(String inputType) {
		this.inputType = inputType;
	}

	public String getExternalFieldName() {
		return externalFieldName;
	}

	public void setExternalFieldName(String externalFieldName) {
		this.externalFieldName = externalFieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Factor getControlField() {
		return controlField;
	}

	public void setControlField(Factor controlField) {
		this.controlField = controlField;
	}

	public Factor getMatchField() {
		return matchField;
	}

	public void setMatchField(Factor matchField) {
		this.matchField = matchField;
	}

	public Factor getFieldRecordRelation() {
		return fieldRecordRelation;
	}

	public void setFieldRecordRelation(Factor fieldRecordRelation) {
		this.fieldRecordRelation = fieldRecordRelation;
	}

	public Factor getPlusFieldIndicator() {
		return plusFieldIndicator;
	}

	public void setPlusFieldIndicator(Factor plusFieldIndicator) {
		this.plusFieldIndicator = plusFieldIndicator;
	}

	public Factor getMinusFieldIndicator() {
		return minusFieldIndicator;
	}

	public void setMinusFieldIndicator(Factor minusFieldIndicator) {
		this.minusFieldIndicator = minusFieldIndicator;
	}

	public Factor getBlankFieldIndicator() {
		return blankFieldIndicator;
	}

	public void setBlankFieldIndicator(Factor blankFieldIndicator) {
		this.blankFieldIndicator = blankFieldIndicator;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		ParseTree context = parseTree.getChild(1);
		this.externalFieldName = getStringColumn(context, 0);
		this.fieldName = getStringColumn(context, 1);
		this.controlField = getFactorColumn(context, 2);
		this.matchField = getFactorColumn(context, 3);
		this.plusFieldIndicator = getFactorColumn(context, 4);
		this.minusFieldIndicator = getFactorColumn(context, 5);
		this.blankFieldIndicator = getFactorColumn(context, 6);

		this.setType("IType Statement");
	}

}
