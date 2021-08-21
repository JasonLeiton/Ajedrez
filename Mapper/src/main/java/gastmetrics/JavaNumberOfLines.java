package gastmetrics;

import org.eclipse.jdt.core.dom.*;

public class JavaNumberOfLines extends NumberOfLines {
    public static NumberOfLines INSTANCE = new JavaNumberOfLines();

    @Override
    public int getNumberOfLines(MethodDeclaration methodDeclaration) {
        final int[] loc = new int[1];
        ASTVisitor astVisitor = new ASTVisitor() {
            @Override
            public boolean visit(MethodDeclaration node) {
                loc[0] = 0;
                return super.visit(node);
            }

            @Override
            public boolean visit(AssertStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(BreakStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(ContinueStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(DoStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(EmptyStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(EnhancedForStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(ExpressionStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(ForStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(IfStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(LabeledStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(ReturnStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(SwitchStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(SynchronizedStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(ThrowStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(TryStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(TypeDeclarationStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(VariableDeclarationStatement node) {
                loc[0]++;
                return super.visit(node);
            }

            @Override
            public boolean visit(WhileStatement node) {
                loc[0]++;
                return super.visit(node);
            }
        };
        methodDeclaration.accept(astVisitor);
        return loc[0];
    }
}
