package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.JUMP_STATEMENT;

public class JumpStatement extends Statement {

   public JumpStatement() {
      setTag(JUMP_STATEMENT);
   }

   /**
    * <pre>
    *           1..1          1..1
    * JumpStatement ------------------------> Expression
    *                   &lt;       target
    * </pre>
    */
   private Expression target;
   
   public void setTarget(Expression value) {
      this.target = value;
   }
   
   public Expression getTarget() {
      return this.target;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
