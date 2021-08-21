package gastvalidator.RPG2GASTMCertifier.Statements;

import java.util.ArrayList;

import gastvalidator.RPG2GASTMCertifier.IRpgSyntaxElement;

public class Statement implements IRpgSyntaxElement{
	
	private ArrayList<Indicator> _indicator;

	public ArrayList<Indicator> getIndicator() {
		return _indicator;
	}

	public void setIndicator(ArrayList<Indicator> _indicator) {
		this._indicator = _indicator;
	}
	public void addIndicator(Indicator ind) {
		this._indicator.add(ind);
	}
	
	public boolean hasIndicators() {
		return this._indicator != null && this._indicator.size()>0;
	}
	
	
	
}
