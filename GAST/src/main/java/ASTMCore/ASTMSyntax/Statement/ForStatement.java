package ASTMCore.ASTMSyntax.Statement;

import ASTMCore.ASTMSyntax.Expression.Expression;
import java.util.ArrayList;

public abstract class ForStatement extends LoopStatement {
   public void setInitBody(ArrayList<Expression> initBody) {
		this.initBody = initBody;
	}

	public void setIterationBody(ArrayList<Expression> iterationBody) {
		this.iterationBody = iterationBody;
	}

/**
    * <pre>
    *           1..1          1..*
    * ForStatement ------------------------> Expression
    *                   &lt;       initBody
    * </pre>
    */
   private ArrayList<Expression> initBody;
   
   public ArrayList<Expression> getInitBody() {
      if (this.initBody == null) {
         this.initBody = new ArrayList<Expression>();
      }
      return this.initBody;
   }
   
   /**
    * <pre>
    *           1..1          1..*
    * ForStatement ------------------------> Expression
    *                   &lt;       iterationBody
    * </pre>
    */
   private ArrayList<Expression> iterationBody;
   
   public ArrayList<Expression> getIterationBody() {
      if (this.iterationBody == null) {
         this.iterationBody = new ArrayList<Expression>();
      }
      return this.iterationBody;
   }
   
   }
