package gastmappers.rpgmapper.mapperutilities.Statements.Specification.ITypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.ITypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class ExternallyDescribedFiles extends ITypeStatement {

	private String recordFormat;
	private Factor recordIdentifyingIndicator;

	public ExternallyDescribedFiles(String inputType) {
		this.inputType = inputType;
	}

	public String getRecordFormat() {
		return recordFormat;
	}

	public void setRecordFormat(String recordFormat) {
		this.recordFormat = recordFormat;
	}

	public Factor getRecordIdentifyingIndicator() {
		return recordIdentifyingIndicator;
	}

	public void setRecordIdentifyingIndicator(Factor recordIdentifyingIndicator) {
		this.recordIdentifyingIndicator = recordIdentifyingIndicator;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.recordFormat = getStringColumn(parseTree, 1);
		this.recordIdentifyingIndicator = getFactorColumn(parseTree, 2);

		this.setType("IType Statement");
	}

}
