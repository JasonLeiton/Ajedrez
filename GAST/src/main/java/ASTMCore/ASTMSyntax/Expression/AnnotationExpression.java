package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import static ASTMCore.metadata.Tags.ANNOTATION_EXPRESSION;

public class AnnotationExpression extends Expression {

   public AnnotationExpression() {
      setTag(ANNOTATION_EXPRESSION);
   }

   private TypeReference annotationtype;
   
   public void setAnnotationtype(TypeReference value) {
      this.annotationtype = value;
   }
   
   public TypeReference getAnnotationtype() {
      return this.annotationtype;
   }
   
   
   private ArrayList<Expression> memberValues;
   
   public ArrayList<Expression> getMemberValues() {
      if (this.memberValues == null) {
         this.memberValues = new ArrayList<Expression>();
      }
      return this.memberValues;
   }

@Override
public void accept(GASTVisitor v) {
	// TODO Auto-generated method stub
	
}
   
   }
