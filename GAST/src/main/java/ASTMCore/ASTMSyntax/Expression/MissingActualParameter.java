package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.MISSING_ACTUAL_PARAMETER;

public class MissingActualParameter extends ActualParameter {

    public MissingActualParameter() {
        setTag(MISSING_ACTUAL_PARAMETER);
    }

    private String parameter="missing";

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		System.err.println("MissingActualParameter");
	}
    
    
}
