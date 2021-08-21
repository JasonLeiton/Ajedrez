package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class KdsFunction extends BuiltInFunction implements Factor {

	private Factor dataStructure, number;

	public KdsFunction(String functionName) {
		super(functionName);
		// TODO Auto-generated constructor stub
	}

	public Factor getDataStructure() {
		return dataStructure;
	}

	public void setDataStructure(Factor dataStructure) {
		this.dataStructure = dataStructure;
	}

	public Factor getNumber() {
		return number;
	}

	public void setNumber(Factor number) {
		this.number = number;
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setValue(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setType(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void generateFactor(ParseTree parseTree) {
		List<Factor> listFactors = getListArguments(parseTree);

		for (int i = 0; i < listFactors.size(); i++) {
			Factor factor = listFactors.get(i);
			switch (i) {
			case 0:
				this.dataStructure = factor;
				break;
			case 1:
				this.number = factor;
				break;
			default:
				break;
			}
		}
	}

}
