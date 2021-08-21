package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.DELETE_STATEMENT;

public class DeleteStatement extends Statement {

   public DeleteStatement() {
      setTag(DELETE_STATEMENT);
   }

   /**
    * <pre>
    *           1..1          1..1
    * DeleteStatement ------------------------> Expression
    *                   &lt;       operand
    * </pre>
    */
   private Expression operand;
   
   public void setOperand(Expression value) {
      this.operand = value;
   }
   
   public Expression getOperand() {
      return this.operand;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
