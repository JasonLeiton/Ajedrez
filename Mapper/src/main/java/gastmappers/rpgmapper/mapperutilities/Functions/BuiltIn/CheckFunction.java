package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class CheckFunction extends BuiltInFunction implements Factor {

	private Factor verificationList, searchedValue, startPosition;
	private boolean reversed;

	public CheckFunction(String functionName) {
		super(functionName);
		this.reversed = functionName.contentEquals("CHECKR");
	}

	public Factor getVerificationList() {
		return verificationList;
	}

	public void setVerificationList(Factor verificationList) {
		this.verificationList = verificationList;
	}

	public Factor getSearchedValue() {
		return searchedValue;
	}

	public void setSearchedValue(Factor searchedValue) {
		this.searchedValue = searchedValue;
	}

	public Factor getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Factor startPosition) {
		this.startPosition = startPosition;
	}

	public boolean isReversed() {
		return reversed;
	}

	public void setReversed(boolean reversed) {
		this.reversed = reversed;
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
		this.verificationList = this.getReflectionFactor(parseTree, "comparator");
		this.searchedValue = this.getReflectionFactor(parseTree, "base");
		this.startPosition = this.getReflectionFactor(parseTree, "start");
	}

}
