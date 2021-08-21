package gastvalidator.RPG2GASTMCertifier;

import java.util.ArrayList;

import org.rpgleparser.RpgParser;
import org.rpgleparser.RpgParser.CsCALLContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastvalidator.RPG2GASTMCertifier.Expressions.BinaryExpression;
import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionNode;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionOperator;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionType;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionWithOperator;
import gastvalidator.RPG2GASTMCertifier.Expressions.FunctionCallExpression;
import gastvalidator.RPG2GASTMCertifier.Statements.AssigmentStatement;
import gastvalidator.RPG2GASTMCertifier.Statements.For;

public class BlockFunctionHelper {
	
	public static For buildFor(String initialValue, String upperLimit, String variable) {
		For _for = new For();
		
		//variable initialization
		AssigmentStatement initialStatement = new AssigmentStatement();
		initialStatement.setResult(variable);
		ExpressionNode initialEpression = new ExpressionNode(ExpressionType.ARITMETICAL_EXPRESSION);
		initialEpression.setValue(initialValue);
		initialStatement.setExpresion(initialEpression);
		
		//limit comparison
		
		BinaryExpression limitComparisonExpression = new BinaryExpression(ExpressionType.LOGICAL_EXPRESSION);
		ExpressionNode leftLimit = new ExpressionNode(ExpressionType.ARITMETICAL_EXPRESSION);
		ExpressionNode rightLimit = new ExpressionNode(ExpressionType.ARITMETICAL_EXPRESSION);
		
		leftLimit.setValue(variable);
		rightLimit.setValue(upperLimit);
		
		limitComparisonExpression.setLeftOperand(leftLimit);
		limitComparisonExpression.setRightOperand(rightLimit);
		ExpressionOperator limitOperator = ExpressionWithOperator.getBinaryType("<=");
		limitComparisonExpression.setOperator(limitOperator);
		
		//increment initialization
		AssigmentStatement increment = new AssigmentStatement();
		increment.setResult(variable);
		BinaryExpression binaryIncrementExpression = new BinaryExpression(ExpressionType.ARITMETICAL_EXPRESSION);
		ExpressionNode leftIncrement = new ExpressionNode(ExpressionType.ARITMETICAL_EXPRESSION);
		leftIncrement.setValue(variable);
		ExpressionNode rightIncrement = new ExpressionNode(ExpressionType.ARITMETICAL_EXPRESSION);
		binaryIncrementExpression.setLeftOperand(leftIncrement);
		binaryIncrementExpression.setRightOperand(rightIncrement);
		ExpressionOperator incrementOperator = ExpressionWithOperator.getBinaryType("+");
		binaryIncrementExpression.setOperator(incrementOperator);
		increment.setExpresion(binaryIncrementExpression);
		
		_for.setIncrement(increment);
		_for.setInitialCondition(initialStatement);
		_for.setStopCondition(limitComparisonExpression);
		
		return _for;
	}
	
	public static For buildFor(AssigmentStatement initialValue, Expression upperLimit, AssigmentStatement variable) {
		For _for = new For();
		
		_for.setIncrement(variable);
		_for.setInitialCondition(initialValue);
		_for.setStopCondition(upperLimit);
		
		return _for;
	}
	
	
	public static FunctionCallExpression functionCall(CsCALLContext funct) {
		Cspec_fixed_standard_partsContext stdparts = funct.cspec_fixed_standard_parts();
		String parameterList = stdparts.result.getText().trim();
		ArrayList<Expression> args;
		args = new ArrayList<Expression>();
		//inicializa los paramtros
		if (!"".equals(parameterList))args.add(new ExpressionNode(ExpressionType.NODE_EXPRESSION, parameterList));
		
		FunctionCallExpression fce = new FunctionCallExpression(null, stdparts.factor2.getText());
		return null;
		
	}
	

}
