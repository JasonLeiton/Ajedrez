package gastvalidator.RPG2GASTMCertifier.Statements;

import java.util.ArrayList;


public class SelectWhen extends Statement{

	
	private ArrayList<When> whenOptions;
	
	private BlockStatement otherBody;



	public BlockStatement getOtherBody() {
		return otherBody;
	}

	public ArrayList<When> getWhenOptions() {
		return whenOptions;
	}

	public void setWhenOptions(ArrayList<When> whenOptions) {
		this.whenOptions = whenOptions;
	}

	public void setOtherBody(BlockStatement otherBody) {
		this.otherBody = otherBody;
	}

}
