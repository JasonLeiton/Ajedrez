package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class BitAndFunction extends BuiltInFunction implements Factor {

	private List<Factor> listFactors;

	public BitAndFunction(String functionName) {
		super(functionName);
	}

	public List<Factor> getListFactors() {
		return listFactors;
	}

	public void setListFactors(List<Factor> listFactors) {
		this.listFactors = listFactors;
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
		this.listFactors = this.getListArguments(parseTree);
	}
}
