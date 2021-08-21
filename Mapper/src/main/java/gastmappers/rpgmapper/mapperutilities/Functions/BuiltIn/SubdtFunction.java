package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_subdtContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.DurationCodes;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class SubdtFunction extends BuiltInFunction implements Factor {

	private Factor dateValue;
	private String durationCode;

	public SubdtFunction(String functionName) {
		super(functionName);
	}

	public Factor getDateValue() {
		return dateValue;
	}

	public void setDateValue(Factor dateValue) {
		this.dateValue = dateValue;
	}

	public String getDurationCode() {
		return durationCode;
	}

	public void setDurationCode(String durationCode) {
		this.durationCode = durationCode;
	}

	@Override
	public String getValue() {

		return null;
	}

	@Override
	public String getType() {

		return null;
	}

	@Override
	public void setValue(String value) {

	}

	@Override
	public void setType(String type) {

	}

	@Override
	public void generateFactor(ParseTree parseTree) {
		Bif_subdtContext subdtContext = (Bif_subdtContext) parseTree;
		this.dateValue = FreeContextAnalyzer.getFreeContextFactor(subdtContext.value);
		this.durationCode = DurationCodes
				.getDurationCode(FreeContextAnalyzer.getFreeContextFactor(subdtContext.format).getValue());
	}

}
