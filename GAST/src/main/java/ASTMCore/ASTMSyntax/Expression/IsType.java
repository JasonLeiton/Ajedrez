package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.NamedTypeReference;
import ASTMCore.ASTMSyntax.Types.Type;

import java.util.ArrayList;

public class IsType extends NamedTypeReference {
    ArrayList<Expression> identifierPatternArm;
    ArrayList<Expression> expressionsPatternArm;
    Expression identifier;
    Type baseType;

    public void setIdentifierPatternArm(ArrayList<Expression> identifierPatternArm) {
        this.identifierPatternArm = identifierPatternArm;
    }

    public void setExpressionsPatternArm(ArrayList<Expression> expressionsPatternArm) {
        this.expressionsPatternArm = expressionsPatternArm;
    }

    public void setIdentifier(Expression identifier) {
        this.identifier = identifier;
    }

    public void setBaseType(Type baseType) {
        this.baseType = baseType;
    }

    public ArrayList<Expression> getIdentifierPatternArm() {
        return identifierPatternArm;
    }

    public ArrayList<Expression> getExpressionsPatternArm() {
        return expressionsPatternArm;
    }

    public Expression getIdentifier() {
        return identifier;
    }

    public Type getBaseType() {
        return baseType;
    }
}
