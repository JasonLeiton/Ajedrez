package ASTMCore.ASTMSyntax.Expression;


//import java.util.ArrayList;
import java.util.List;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.COLLECTION_EXPRESSION;

public class CollectionExpression extends Expression {

	public CollectionExpression() {
		setTag(COLLECTION_EXPRESSION);
	}

	private List<Expression> expressionList;

	public List<Expression> getExpressionList() {
		// if (this.expressionList == null) {
		//  this.expressionList = new ArrayList<Expression>();
		//}
		return this.expressionList;
	}

	public void setExpressionList(List<Expression> expressionList) {
		this.expressionList = expressionList;
	}
	
	public void accept(GASTVisitor v) {
		v.visitCollectionExpression(this);
	}

}
