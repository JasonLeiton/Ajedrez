package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class TrimFunction extends BuiltInFunction implements Factor {

	private Factor baseValue, charTrim;
	private String extender;

	public TrimFunction(String functionName) {
		super(functionName);
		this.extender = functionName.replace("TRIM", "");
	}

	public Factor getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(Factor baseValue) {
		this.baseValue = baseValue;
	}

	public Factor getCharTrim() {
		return charTrim;
	}

	public void setCharTrim(Factor charTrim) {
		this.charTrim = charTrim;
	}

	public String getExtender() {
		return extender;
	}

	public void setExtender(String extender) {
		this.extender = extender;
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
		this.baseValue = this.getReflectionFactor(parseTree, "string");
		this.charTrim = this.getReflectionFactor(parseTree, "trimcharacters");
	}

}
