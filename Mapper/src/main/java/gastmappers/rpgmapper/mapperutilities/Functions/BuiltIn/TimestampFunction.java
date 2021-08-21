package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_timestampContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.DurationCodes;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class TimestampFunction extends BuiltInFunction implements Factor {

	private Factor expression;
	private String formatCode;

	public TimestampFunction(String functionName) {
		super(functionName);
	}

	public Factor getExpression() {
		return expression;
	}

	public void setExpression(Factor expression) {
		this.expression = expression;
	}

	public String getFormatCode() {
		return formatCode;
	}

	public void setFormatCode(String formatCode) {
		this.formatCode = formatCode;
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
		Bif_timestampContext timestampContext = (Bif_timestampContext) parseTree;
		this.formatCode = DurationCodes
				.getDurationCode(FreeContextAnalyzer.getFreeContextFactor(timestampContext.format).getValue());
		this.expression = this.getSingleExpression(parseTree);
	}

}
