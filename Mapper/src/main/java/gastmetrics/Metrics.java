package gastmetrics;

public class Metrics {
    int lines_of_code;
    int cyclomatic_complexity;
    HalsteadMetrics halstead;

    public int getLines_of_code() {
        return lines_of_code;
    }

    public void setLines_of_code(int lines_of_code) {
        this.lines_of_code = lines_of_code;
    }

    public int getCyclomatic_complexity() {
        return cyclomatic_complexity;
    }

    public void setCyclomatic_complexity(int cyclomatic_complexity) {
        this.cyclomatic_complexity = cyclomatic_complexity;
    }

    public HalsteadMetrics getHalstead() {
        return halstead;
    }

    public void setHalstead(HalsteadMetrics halstead) {
        this.halstead = halstead;
    }
}
