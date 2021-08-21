package gastvalidator.RPG2GASTMCertifier;
import java.util.ArrayList;
import java.util.List;

import org.rpgleparser.RpgParser;

import gastvalidator.RPG2GASTMCertifier.Expressions.*;

public class BooleanExpressionsHelper {
	
	public static String getAndFactor2(RpgParser.CsANDxxContext and) {
		if(and.csANDEQ() != null) return and.csANDEQ().cspec_fixed_standard_parts().factor().getText();
		else if(and.csANDGE() != null) return and.csANDGE().cspec_fixed_standard_parts().factor().getText();
		else if(and.csANDGT() != null) return and.csANDGT().cspec_fixed_standard_parts().factor().getText();
		else if(and.csANDLE() != null) return and.csANDLE().cspec_fixed_standard_parts().factor().getText();
		else if(and.csANDLT() != null) return and.csANDLT().cspec_fixed_standard_parts().factor().getText();
		else if(and.csANDNE() != null) return and.csANDNE().cspec_fixed_standard_parts().factor().getText();
		return "";
	}
	
	public static String getOrFactor2(RpgParser.CsORxxContext or) {
		if(or.csOREQ() != null) return or.csOREQ().cspec_fixed_standard_parts().factor().getText();
		else if(or.csORGE() != null) return or.csORGE().cspec_fixed_standard_parts().factor().getText();
		else if(or.csORGT() != null) return or.csORGT().cspec_fixed_standard_parts().factor().getText();
		else if(or.csORLE() != null) return or.csORLE().cspec_fixed_standard_parts().factor().getText();
		else if(or.csORLT() != null) return or.csORLT().cspec_fixed_standard_parts().factor().getText();
		else if(or.csORNE() != null) return or.csORNE().cspec_fixed_standard_parts().factor().getText();
		return "";
	}
	
	public static String getAndOperator(RpgParser.CsANDxxContext and) {
		if(and.csANDEQ() != null) return "=";
		else if(and.csANDGE() != null) return ">=";
		else if(and.csANDGT() != null) return ">";
		else if(and.csANDLE() != null) return "<=";
		else if(and.csANDLT() != null) return "<";
		else if(and.csANDNE() != null) return "!=";
		return "";
	}
	
	public static String getOrOperator(RpgParser.CsORxxContext or) {
		if(or.csOREQ() != null) return "=";
		else if(or.csORGE() != null) return ">=";
		else if(or.csORGT() != null) return ">";
		else if(or.csORLE() != null) return "<=";
		else if(or.csORLT() != null) return "<";
		else if(or.csORNE() != null) return "!=";
		return "";
	}
	
	public static Expression 
	expressionAndBuilder(List<RpgParser.CsANDxxContext> ands, BinaryExpression seed) {
		List<BinaryExpression> beList = new ArrayList<BinaryExpression>();
		BinaryExpression be;
		RpgParser.CsANDxxContext and;
		if (seed != null)
			beList.add(seed);
		for (int i = 0; i < ands.size();i++ ) {
			and = ands.get(i);
			be = new BinaryExpression(ExpressionType.LOGICAL_EXPRESSION);
			ExpressionNode en1 = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
			ExpressionNode en2 = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
			en1.setValue(and.factor().getText());
			en2.setValue(getAndFactor2(and));
			be.setLeftOperand(en1);
			be.setRightOperand(en2);
			String operator = getAndOperator(and);
			ExpressionOperator eo = ExpressionWithOperator.getBinaryType(operator);
			be.setOperator(eo);
			beList.add(be);
		}
		be = beList.get(0);
		BinaryExpression andElement;
		for (int i = 1; i < beList.size();i++) {
			andElement =  new BinaryExpression(ExpressionType.LOGICAL_EXPRESSION);
			andElement.setLeftOperand(be);
			andElement.setRightOperand(beList.get(i));
			andElement.setOperator(ExpressionWithOperator.getBinaryType("and"));
			be = andElement;
		}
		
		return be;
	}
	
	public static Expression 
	expressionOrBuilder(List<RpgParser.CsORxxContext> ors, Expression seed) {
		List<Expression> beList = new ArrayList<Expression>();
		Expression be;
		RpgParser.CsORxxContext or;
		if (seed != null)
			beList.add(seed);
		for (int i = 0; i < ors.size();i++ ) {
			or = ors.get(i);
			BinaryExpression leftOr = new BinaryExpression(ExpressionType.LOGICAL_EXPRESSION);
			ExpressionNode en1 = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
			ExpressionNode en2 = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
			en1.setValue(or.factor().getText());
			en2.setValue(getOrFactor2(or));
			leftOr.setLeftOperand(en1);
			leftOr.setRightOperand(en2);
			String operator = getOrOperator(or);
			ExpressionOperator eo = ExpressionWithOperator.getBinaryType(operator);
			leftOr.setOperator(eo);
			beList.add(expressionAndBuilder(or.csANDxx(), leftOr));
		}
		
		be = beList.get(0);
		BinaryExpression andElement;
		for (int i = 1; i < beList.size();i++) {
			andElement =  new BinaryExpression(ExpressionType.LOGICAL_EXPRESSION);
			andElement.setLeftOperand(be);
			andElement.setRightOperand(beList.get(i));
			andElement.setOperator(ExpressionWithOperator.getBinaryType("or"));
			be = andElement;
		}
		
		return be;
	}
	

}
