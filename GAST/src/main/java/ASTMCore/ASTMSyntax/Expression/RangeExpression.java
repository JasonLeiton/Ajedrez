package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.RANGE_EXPRESSION;

public class RangeExpression extends Expression {

   public RangeExpression() {
      setTag(RANGE_EXPRESSION);
   }

   private Expression fromExpression;
   
   public void setFromExpression(Expression value) {
      this.fromExpression = value;
   }
   
   public Expression getFromExpression() {
      return this.fromExpression;
   }
   
 
   private Expression toExpression;
   
   public void setToExpression(Expression value) {
      this.toExpression = value;
   }
   
   public Expression getToExpression() {
      return this.toExpression;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
