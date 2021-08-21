package gastmappers.csharpmapper.bindings;

public class CallBinding {
    String path;
    int line;
    int column;
    String callerNamespace;
    String callerClassName;
    String callerMethodName;
    String calleeNamespace;
    String calleeClassName;
    String calleeMethodName;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public String getCallerNamespace() {
        return callerNamespace;
    }

    public void setCallerNamespace(String callerNamespace) {
        this.callerNamespace = callerNamespace;
    }

    public String getCallerClassName() {
        return callerClassName;
    }

    public void setCallerClassName(String callerClassName) {
        this.callerClassName = callerClassName;
    }

    public String getCallerMethodName() {
        return callerMethodName;
    }

    public void setCallerMethodName(String callerMethodName) {
        this.callerMethodName = callerMethodName;
    }

    public String getCalleeNamespace() {
        return calleeNamespace;
    }

    public void setCalleeNamespace(String calleeNamespace) {
        this.calleeNamespace = calleeNamespace;
    }

    public String getCalleeClassName() {
        return calleeClassName;
    }

    public void setCalleeClassName(String calleeClassName) {
        this.calleeClassName = calleeClassName;
    }

    public String getCalleeMethodName() {
        return calleeMethodName;
    }

    public void setCalleeMethodName(String calleeMethodName) {
        this.calleeMethodName = calleeMethodName;
    }

    public String getKey() { return path + "||" + String.valueOf(line) + "||" + String.valueOf(column) + "||" + calleeMethodName; }
}
