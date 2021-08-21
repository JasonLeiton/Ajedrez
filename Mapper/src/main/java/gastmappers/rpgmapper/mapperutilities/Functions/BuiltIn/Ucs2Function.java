package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class Ucs2Function extends BuiltInFunction implements Factor {

	private Factor characterValue;

	public Ucs2Function(String functionName) {
		super(functionName);
	}

	public Factor getCharacterValue() {
		return characterValue;
	}

	public void setCharacterValue(Factor characterValue) {
		this.characterValue = characterValue;
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
		this.characterValue = this.getSingleExpression(parseTree);
	}

}
