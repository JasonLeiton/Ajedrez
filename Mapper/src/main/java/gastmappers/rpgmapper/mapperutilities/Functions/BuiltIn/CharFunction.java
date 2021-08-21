package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_charContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class CharFunction extends BuiltInFunction implements Factor {

	private Factor variable, format;

	public CharFunction(String functionName) {
		super(functionName);
	}

	public Factor getVariable() {
		return variable;
	}

	public void setVariable(Factor variable) {
		this.variable = variable;
	}

	public Factor getFormat() {
		return format;
	}

	public void setFormat(Factor format) {
		this.format = format;
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
		Bif_charContext charContext = (Bif_charContext) parseTree;
		this.variable = this.getSingleExpression(charContext);
		this.format = FreeContextAnalyzer.getFreeContextFactor(charContext.format);
	}
}
