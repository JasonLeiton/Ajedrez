package ASTMCore.ASTMSyntax.Statement;


import static ASTMCore.metadata.Tags.FOR_CHECK_BEFORE_STATEMENT;

public class ForCheckBeforeStatement extends ForStatement {

    public ForCheckBeforeStatement() {
        setTag(FOR_CHECK_BEFORE_STATEMENT);
    }
}
