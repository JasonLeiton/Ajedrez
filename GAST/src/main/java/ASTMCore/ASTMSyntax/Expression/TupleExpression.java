package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class TupleExpression extends Expression {
	
    public ArrayList<Expression> expressions;
    public ArrayList<Type> types;
    public ArrayList<String> refoutList;
    public ArrayList<Boolean> varType;
    public ArrayList<IdentifierReference> identifierReferences;
    //refout=(REF | OUT | IN)? type_ identifier
    @Override

    public void accept(GASTVisitor v) {

    }
    
	public void setExpressionList(ArrayList<Expression> definition) {
		this.expressions = definition;
	}
	
	public ArrayList<Expression> getExpressionList() {
		return this.expressions;
	}
}
