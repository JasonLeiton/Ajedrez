package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Fragment;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;
import java.util.List;

import static ASTMCore.metadata.Tags.USING_STATEMENT;

/**
 * Statement that executes code using an object and disposes it when exited.
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class UsingStatement extends Statement {


    public UsingStatement() {
        setTag(USING_STATEMENT);
    }

	/**
     * Modifiers of the statement
     */
	private List<Modifiers> modifiers;
    /**
     * Body of the statement with code to execute in a unsafe context
     */
    private Statement body;

    /**
     * Expression with the object to use inside the statement
     */
    private Expression expression;

    /**
     * List of variables to use inside the statement
     */
    private List<Fragment> local_variable;

    /**
     * Setter for the expression
     *
     * @param expression Expression with the object to use inside the statement
     */
    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    /**
     * Getter for the expression
     *
     * @return Expression with the object to use inside the statement
     */
    public Expression getExpression() {
        return expression;
    }

	/**
     * Getter for the expression
     *
     * @return Expression with the object to use inside the statement
     */
    public List<Fragment>  getLocal_variable() {
        return local_variable;
    }

    /**
     * Setter for the list of variables
     *
     * @param local_variable List of Fragment with variables
     */
    public void setLocal_variable(List<Fragment> local_variable) {
        this.local_variable = local_variable;
    }
    /**
     * Setter for the body of the statement
     *
     * @param body Statement with the body
     */
    public void setBody(Statement body) {
        this.body = body;
    }

    /**
     * Getter for the body of the statement
     *
     * @return Statement with the body
     */
    public Statement getBody() {
        return body;
    }

	/**
     * Getter for the list of modifiers
     *
     * @return List with the modifiers
     */
	public List<Modifiers> getModifiers() {
		return modifiers;
	}

	/**
     * Setter for list of modifiers
     *
     * @param modifiers List with the modifiers
     */
	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

    @Override
    public void accept(GASTVisitor v) {

    }

}
