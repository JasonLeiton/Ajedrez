package gastmetrics;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public abstract class NumberOfLines {
    public static NumberOfLines INSTANCE;
    public abstract int getNumberOfLines(MethodDeclaration methodDeclaration);
}
