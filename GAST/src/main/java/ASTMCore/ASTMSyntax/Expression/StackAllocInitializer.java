package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.List;

import static ASTMCore.metadata.Tags.STACK_ALLOC_INITIALIZER;

/**
 * Initializer to allocate a block of memory on the stack
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class StackAllocInitializer extends Expression {

    public StackAllocInitializer() {
        setTag(STACK_ALLOC_INITIALIZER);
    }

    /**
     * Type of object to be saved to memory
     */
    TypeReference definitionType;

    /**
     * Number of objects to be saved to memory
     */
    Expression dimension;

    /**
     * Expressions to initialize the blocks of memory
     */
    ArrayList<Expression> expressions;

    /**
     * Getter for the definition type
     *
     * @return TypeReference with the definition type
     */
    public TypeReference getDefinitionType() {
        return definitionType;
    }

    /**
     * Setter for the definition type
     *
     * @param reference TypeReference with the definition type
     */
    public void setDefinitionType(TypeReference reference) {
        this.definitionType = reference;
    }

    /**
     * Getter for the dimension
     *
     * @return int with the dimension number
     */
    public Expression getDimension() {
        return dimension;
    }

    /**
     * Setter for the dimension
     *
     * @param dimension int with the dimension number
     */
    public void setDimension(Expression dimension) {
        this.dimension = dimension;
    }

    /**
     * Getter for the list of expressions
     *
     * @return ArrayList of Expression with all expressions
     */
    public ArrayList<Expression> getExpressions() {
        return expressions;
    }

    /**
     * Setter for the list of expressions
     *
     * @param expressions ArrayList of Expression with all expressions
     */
    public void setExpressions(ArrayList<Expression> expressions) {
        this.expressions = expressions;
    }

    @Override
    public void accept(GASTVisitor v) {

    }
}
