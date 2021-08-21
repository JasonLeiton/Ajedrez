package ASTMCore.ASTMSyntax.Expression;

import java.util.List;

import ASTMCore.visitor.GASTVisitor;

public class DictionaryExpression extends Expression{
	private List<DictValue> ValueList;

	
	public void accept(GASTVisitor v) {
		
	}


	public List<DictValue> getValueList() {
		return ValueList;
	}


	public void setValueList(List<DictValue> valueList) {
		ValueList = valueList;
	}


}
