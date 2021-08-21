package ASTMCore.ASTMSyntax.Expression;

import java.util.Set;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.HashSet;

import static ASTMCore.metadata.Tags.FUNCTION_CALL_EXPRESSION;

public class FunctionCallExpression extends Expression {

	public FunctionCallExpression() {
		setTag(FUNCTION_CALL_EXPRESSION);
	}

	private Expression calledFunction;

	public void setCalledFunction(Expression value) {
		this.calledFunction = value;
	}

	public Expression getCalledFunction() {
		return this.calledFunction;
	}


	private ArrayList<ActualParameter> actualParams;

	public void setActualParams(ArrayList<ActualParameter> actualParams) {
		this.actualParams = actualParams;
	}

	public ArrayList<ActualParameter> getActualParams() {
		if (this.actualParams == null) {
			this.actualParams = new ArrayList<ActualParameter>();
		}
		return this.actualParams;
	}


	private Name functionName;

	public Name getFunctionName() {
		return functionName;
	}

	public void setFunctionName(Name functionName) {
		this.functionName = functionName;
	}

	private String signature;

	public String getSignature() { return signature; }

	public void setSignature(String signature) { this.signature = signature; }

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	private String packageName;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	private String methodName;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	private String callerClassName;
	private String callerPackageName;
	private String callerSignature;
	private String callerMethodName;

	public String getCallerClassName() {
		return callerClassName;
	}

	public void setCallerClassName(String callerClassName) {
		this.callerClassName = callerClassName;
	}

	public String getCallerPackageName() {
		return callerPackageName;
	}

	public void setCallerPackageName(String callerPackageName) {
		this.callerPackageName = callerPackageName;
	}

	public String getCallerSignature() {
		return callerSignature;
	}

	public void setCallerSignature(String callerSignature) {
		this.callerSignature = callerSignature;
	}

	public String getCallerMethodName() {
		return callerMethodName;
	}

	public void setCallerMethodName(String callerMethodName) {
		this.callerMethodName = callerMethodName;
	}

	public void accept(GASTVisitor v) {
		v.visitFunctionCallExpression(this);
	}

}
