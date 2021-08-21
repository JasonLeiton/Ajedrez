package ASTMCore.ASTMSyntax.Expression;


import static ASTMCore.metadata.Tags.BY_VALUE_ACTUAL_PARAMETER_EXPRESSION;

public class ByValueActualParameterExpression extends ActualParameterExpression {

    public ByValueActualParameterExpression() {
        setTag(BY_VALUE_ACTUAL_PARAMETER_EXPRESSION);
    }
}
