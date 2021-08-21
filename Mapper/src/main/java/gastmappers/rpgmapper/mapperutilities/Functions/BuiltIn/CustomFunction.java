package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class CustomFunction extends BuiltInFunction implements Factor {

	private List<Factor> arguments;

	public CustomFunction(String functionName) {
		super(functionName);
	}

	public List<Factor> getArguments() {
		return arguments;
	}

	public void setArguments(List<Factor> arguments) {
		this.arguments = arguments;
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
		this.arguments = this.getListArguments(parseTree);
	}

}
