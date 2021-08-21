package ASTMCore.ASTMSyntax.Expression;


import static ASTMCore.metadata.Tags.BIT_LITERAL;

public class BitLiteral extends Literal {

    public BitLiteral() {
        setTag(BIT_LITERAL);
    }

}
