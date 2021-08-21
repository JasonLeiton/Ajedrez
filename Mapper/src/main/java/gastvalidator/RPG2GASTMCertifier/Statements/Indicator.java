package gastvalidator.RPG2GASTMCertifier.Statements;


public class Indicator {
	String _indicatorTagName;
	String _indicatorValue;
	
	public Indicator() {
		
	}
	public Indicator(String tagName, String value) {
		setIndicatorTagName(tagName);
		setIndicatorValue(value);
	}

	public String getIndicatorValue() {
		return _indicatorValue;
	}

	public void setIndicatorValue(String pindicatorValue) {
		this._indicatorValue = pindicatorValue;
	}
	
	public String getIndicatorTagName() {
		return _indicatorTagName;
	}

	public void setIndicatorTagName(String pindicatorValue) {
		this._indicatorTagName = pindicatorValue;
	}
	
}
