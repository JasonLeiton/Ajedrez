package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class FieldsFunction extends BuiltInFunction implements Factor {

	private List<Factor> listFields;

	public FieldsFunction(String functionName) {
		super(functionName);
	}

	public List<Factor> getListFields() {
		return listFields;
	}

	public void setListFields(List<Factor> listFields) {
		this.listFields = listFields;
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
		this.listFields = getListArguments(parseTree);
	}

}
