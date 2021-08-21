package gastvalidator.RPG2GASTMCertifier;

import java.util.ArrayList;

import org.rpgleparser.RpgParser.*;

import gastvalidator.RPG2GASTMCertifier.Constants.IBuiltInFunction;
import gastvalidator.RPG2GASTMCertifier.Constants.IFunctionConstants;
import gastvalidator.RPG2GASTMCertifier.Expressions.BinaryExpression;
import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionNode;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionType;
import gastvalidator.RPG2GASTMCertifier.Expressions.FunctionCallExpression;
import gastvalidator.RPG2GASTMCertifier.Statements.AssigmentStatement;
import gastvalidator.RPG2GASTMCertifier.Statements.Indicator;
import gastvalidator.RPG2GASTMCertifier.Statements.Statement;

public class LineStatementsHandler {
	
	public static Cs_operationExtenderContext getExtenders(Cspec_fixed_standardContext fixed) {
		if (fixed.csADD() != null) {
			return fixed.csADD().cs_operationExtender();
		}
		else if (fixed.csSUB() != null) {
			return fixed.csSUB().cs_operationExtender();
		}
		else if (fixed.csZ_ADD() != null) {
			return fixed.csZ_ADD().cs_operationExtender();
		}
		else if (fixed.csZ_SUB() != null) {
			return fixed.csZ_SUB().cs_operationExtender();
		}
		else if (fixed.csEVAL() != null) {
			return fixed.csEVAL().cs_operationExtender();
		}
		else if (fixed.csTEST() != null) {
			return fixed.csTEST().cs_operationExtender();
		}
		return null;
	}
	
	public static Expression handleTest(Cs_operationExtenderContext extenderCtx, Expression e, ExpressionType et, String factor1) {
		ArrayList<Expression> args;
		if (extenderCtx != null) {
			String extender;
			for(int i=0;i < extenderCtx.CS_OperationAndExtender().size();i++) {
				extender = extenderCtx.CS_OperationAndExtender().get(i).getText().trim().toLowerCase();
				args = new ArrayList<Expression>();
				//adding the first parameter;
				factor1 = factor1!= null && !"".equals(factor1)?factor1:IBuiltInFunction.ISO;
				args.add(new ExpressionNode(ExpressionType.NODE_EXPRESSION, factor1));
				
				args.add(e);
				if ("d".equals(extender)) {
					return new FunctionCallExpression(et, IFunctionConstants.DATE_TEST,args);
				}
				else if ("z".equals(extender)) {
					return  new FunctionCallExpression(et, IFunctionConstants.TIMESTAMP_TEST,args);
				}
				else if ("t".equals(extender)) {
					return  new FunctionCallExpression(et, IFunctionConstants.TIME_TEST,args);
				}
			}
		}
		args = new ArrayList<Expression>();
		args.add(e);
		return new FunctionCallExpression(et, IFunctionConstants.TEST,args);
	}
	
	
	public static Expression handleTest(Cspec_fixed_standardContext fixed, Expression e, ExpressionType et, String factor1) {
		Cs_operationExtenderContext extenderCtx = getExtenders(fixed);
		return handleTest(extenderCtx, e, et, factor1);
	}
	
	public static void loadIndicators(Statement stmt, Cspec_fixed_standard_partsContext stdparts ) {
		Indicator hi = new Indicator("hi",stdparts.hi.getText().trim());
		Indicator lo = new Indicator("lo",stdparts.lo.getText().trim());
		Indicator eq = new Indicator("eq",stdparts.eq.getText().trim());
		ArrayList<Indicator> indicators = new ArrayList<Indicator>();
		if(!hi.getIndicatorValue().equals(""))indicators.add(hi);
		if(!lo.getIndicatorValue().equals(""))indicators.add(lo);
		if(!eq.getIndicatorValue().equals(""))indicators.add(eq);
		
		stmt.setIndicator(indicators);
	}
	
	public static Expression handleHExtensor(Cspec_fixed_standardContext fixed, Expression e, ExpressionType et) {
		Cs_operationExtenderContext extenderCtx = getExtenders(fixed);
		if (extenderCtx != null) {
			String extender = extenderCtx.CS_OperationAndExtender().get(0).getText().trim();
			if ("H".equals(extender)) {
				ArrayList<Expression> args = new ArrayList<Expression>();
				args.add(e);
				return new FunctionCallExpression(et, IFunctionConstants.ROUND_UP,args);
			}
		}
		return e;
	}
	
	public static Statement handleArithmeticalOperation
	(String factor1, String operation, Cspec_fixed_standardContext fixed, Cspec_fixed_standard_partsContext stdparts ) {
		String factor2 = stdparts.factor2.getText().trim();
		String result = stdparts.result.getText().trim();
		//fixed.csADD().cs_operationExtender().extender.toString();
		ExpressionType arith = ExpressionType.ARITMETICAL_EXPRESSION;
		//de acuerdo a la documentación si factor 1 es omitido entonces factor1 es reemplazado por result
		factor1 = "".equals(factor1)?result:factor1; 
		Expression addBinary = new BinaryExpression(arith, factor1, operation, factor2);
		addBinary = handleHExtensor(fixed, addBinary, arith);
		AssigmentStatement assigment = new AssigmentStatement(result,addBinary);
		loadIndicators(assigment, stdparts);
		return assigment;
	}
	public static Statement handleArithmeticalZOperation
	(String operation, Cspec_fixed_standardContext fixed, Cspec_fixed_standard_partsContext stdparts ) {
		String factor2 = stdparts.factor2.getText().trim();
		String result = stdparts.result.getText().trim();
		//fixed.csADD().cs_operationExtender().extender.toString();
		ExpressionType arith = ExpressionType.ARITMETICAL_EXPRESSION;
		//de acuerdo a la documentación si factor 1 es omitido entonces factor1 es reemplazado por result
		Expression addBinary = new BinaryExpression(arith, "0", operation, factor2);
		addBinary = handleHExtensor(fixed, addBinary, arith);
		AssigmentStatement assigment = new AssigmentStatement(result,addBinary);
		loadIndicators(assigment, stdparts);
		return assigment;
	}
}
