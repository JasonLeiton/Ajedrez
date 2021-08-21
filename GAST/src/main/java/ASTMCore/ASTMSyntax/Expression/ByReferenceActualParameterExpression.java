package ASTMCore.ASTMSyntax.Expression;


import static ASTMCore.metadata.Tags.BY_REFERENCE_ACTUAL_PARAMETER_EXPRESSION;

public class ByReferenceActualParameterExpression extends ActualParameterExpression {

    public ByReferenceActualParameterExpression() {
        setTag(BY_REFERENCE_ACTUAL_PARAMETER_EXPRESSION);
    }
}
