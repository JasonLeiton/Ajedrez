package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class OccurFunction extends BuiltInFunction implements Factor {

	private Factor dataStructure;

	public OccurFunction(String functionName) {
		super(functionName);
	}

	public Factor getDataStructure() {
		return dataStructure;
	}

	public void setDataStructure(Factor dataStructure) {
		this.dataStructure = dataStructure;
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
		this.dataStructure = this.getSingleExpression(parseTree);
	}

}
