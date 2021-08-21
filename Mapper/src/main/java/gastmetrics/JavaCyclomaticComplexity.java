package gastmetrics;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.*;

public class JavaCyclomaticComplexity extends CyclomaticComplexity {
    public static CyclomaticComplexity INSTANCE = new JavaCyclomaticComplexity();

    @Override
    public int getCyclomaticComplexity(MethodDeclaration methodDeclaration) {
        final int[] cyc = new int[1];
        ASTVisitor astVisitor = new ASTVisitor() {

            @Override
            public boolean visit(MethodDeclaration node) {
                cyc[0] = 1;
                return super.visit(node);
            }

            @Override
            public boolean visit(CatchClause node) {
                cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(ConditionalExpression node) {
                cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(ContinueStatement node) {
                cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(EnhancedForStatement node) {
                cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(ForStatement node) {
                cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(IfStatement node) {
                cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(InfixExpression node) {
                if (node.getOperator() == InfixExpression.Operator.CONDITIONAL_AND ||
                    node.getOperator() == InfixExpression.Operator.CONDITIONAL_OR)
                    cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(PrefixExpression node) {
                if (node.getOperator() == PrefixExpression.Operator.NOT)
                    cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(SwitchCase node) {
                cyc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(WhileStatement node) {
                cyc[0]++;
                return super.visit(node);
            }
        };
        methodDeclaration.accept(astVisitor);
        return cyc[0];
    }
}
