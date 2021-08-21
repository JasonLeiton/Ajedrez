package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static ASTMCore.metadata.Tags.MEMBER_VALUE_PAIR;

public class MemberValuePair extends Expression {

	public MemberValuePair() {
		setTag(MEMBER_VALUE_PAIR);
	}

	private Expression name;
		private Expression value;
		public Expression getName() {
			return name;
		}
		public void setName(Expression name) {
			this.name = name;
		}
		public Expression getValue() {
			return value;
		}
		public void setValue(Expression value) {
			this.value = value;
		}


	@Override
	public void accept(GASTVisitor v) {
		throw new NotImplementedException();
	}
}
