package ASTMCore.ASTMSyntax.Expression;

import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;

public class SwitchExpression extends Expression {

    @Override
    public void accept(GASTVisitor v) {

    }
    private ArrayList<SwitchArmExpression> switchArmExpressions;
    private Expression rangeExpression;

    public ArrayList<SwitchArmExpression> getSwitchArmExpressions() {
        return switchArmExpressions;
    }

    public Expression getRangeExpression() {
        return rangeExpression;
    }

    public void setSwitchArmExpressions(ArrayList<SwitchArmExpression> switchArmExpressions) {
        this.switchArmExpressions = switchArmExpressions;
    }

    public void setRangeExpression(Expression rangeExpression) {
        this.rangeExpression = rangeExpression;
    }
}
