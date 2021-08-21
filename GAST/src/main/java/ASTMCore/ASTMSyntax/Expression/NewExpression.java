package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import static ASTMCore.metadata.Tags.NEW_EXPRESSION;

public class NewExpression extends Expression {

    public NewExpression() {
        setTag(NEW_EXPRESSION);
    }

    private TypeReference newType;

    public void setNewType(TypeReference value) {
        this.newType = value;
    }

    public TypeReference getNewType() {
        return this.newType;
    }

    private ArrayList<ActualParameter> actualParams;

    public ArrayList<ActualParameter> getActualParams() {
        if (this.actualParams == null) {
            this.actualParams = new ArrayList<ActualParameter>();
        }
        return this.actualParams;
    }

    public void setActualParams(ArrayList<ActualParameter> actualParams) {
        this.actualParams = actualParams;
    }

    private Expression initializer;

    public Expression getInitializer() {
        return initializer;
    }

    public void setInitializer(Expression initializer) {
        this.initializer = initializer;
    }

    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

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

    private String callerMethodName;

    public String getCallerMethodName() {
        return callerMethodName;
    }

    public void setCallerMethodName(String callerMethodName) {
        this.callerMethodName = callerMethodName;

    }

    public void accept(GASTVisitor v) {
        v.visitNewExpression(this);
    }

}
