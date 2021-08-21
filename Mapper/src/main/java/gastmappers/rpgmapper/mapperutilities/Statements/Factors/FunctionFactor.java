package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

import java.util.List;

public class FunctionFactor implements Factor {

	private List<Factor> argumentList;
	private String functionName;

	public FunctionFactor(String functionName, List<Factor> argumentList) {
		this.functionName = functionName;
		this.argumentList = argumentList;
	}

	public List<Factor> getArgumentList() {
		return argumentList;
	}

	public void setArgumentList(List<Factor> argumentList) {
		this.argumentList = argumentList;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
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
}
