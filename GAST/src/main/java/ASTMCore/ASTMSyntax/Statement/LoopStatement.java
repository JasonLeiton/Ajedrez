package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.LOOP_STATEMENT;

public class LoopStatement extends Statement {

   public LoopStatement() {
      setTag(LOOP_STATEMENT);
   }

   /**
    * <pre>
    *           1..1          1..1
    * LoopStatement ------------------------> Expression
    *                   &lt;       condition
    * </pre>
    */
   private Expression condition;
   
   public void setCondition(Expression value) {
      this.condition = value;
   }
   
   public Expression getCondition() {
      return this.condition;
   }
   
   /**
    * <pre>
    *           1..1          1..1
    * LoopStatement ------------------------> Statement
    *                   &lt;       body
    * </pre>
    */
   private Statement body;
   
   public void setBody(Statement value) {
      this.body = value;
   }
   
   public Statement getBody() {
      return this.body;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
