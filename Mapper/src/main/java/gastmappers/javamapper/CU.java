package gastmappers.javamapper;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class CU {
    public CompilationUnit getCu() {
        return cu;
    }

    public void setCu(CompilationUnit cu) {
        this.cu = cu;
    }

    //This class is used to have access to change the interface mapper Compilation Unit
    //The Compilation Unit in the mapper will provide access to the number of line.
    CompilationUnit cu;
}
