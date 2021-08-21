package gastmetrics;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public abstract class CyclomaticComplexity {
    public static CyclomaticComplexity INSTANCE;

    public abstract int getCyclomaticComplexity(MethodDeclaration methodDeclaration);
}
