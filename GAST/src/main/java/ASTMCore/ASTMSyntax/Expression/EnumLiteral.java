package ASTMCore.ASTMSyntax.Expression;


import static ASTMCore.metadata.Tags.ENUM_LITERAL;

public class EnumLiteral extends Literal {

    public EnumLiteral() {
        setTag(ENUM_LITERAL);
    }
}
