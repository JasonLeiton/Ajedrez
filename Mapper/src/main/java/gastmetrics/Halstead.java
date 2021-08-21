package gastmetrics;

import org.eclipse.jdt.core.dom.MethodDeclaration;

public abstract class Halstead {
    public static Halstead INSTANCE;
    public abstract HalsteadMetrics getHalstead(MethodDeclaration methodDeclaration);
}
