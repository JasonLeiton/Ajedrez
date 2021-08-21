package gastmetrics;

public class HalsteadMetrics {

    private final int numberOfOperands;
    private final int numberOfOperators;
    private final int numberOfUniqueOperands;
    private final int numberOfUniqueOperators;

    public String PrintString() {
        return  "n1: " + numberOfUniqueOperators +
                ", n2: " + numberOfUniqueOperands +
                ", N1: " + numberOfOperators +
                ", N2: " + numberOfOperands;
    }

    public int getNumberOfOperands() {
        return numberOfOperands;
    }

    public int getNumberOfOperators() {
        return numberOfOperators;
    }

    public int getNumberOfUniqueOperands() {
        return numberOfUniqueOperands;
    }

    public int getNumberOfUniqueOperators() {
        return numberOfUniqueOperators;
    }

    public HalsteadMetrics(int numOperands, int numOperators, int numUniqueOperands, int numUniqueOperators)
    {
        numberOfOperands = numOperands;
        numberOfOperators = numOperators;
        numberOfUniqueOperands = numUniqueOperands;
        numberOfUniqueOperators = numUniqueOperators;
    }

    public HalsteadMetrics Merge(HalsteadMetrics other) {
        if (other == null)
            return this;
        return new HalsteadMetrics(
                getNumberOfOperands() + other.getNumberOfOperands(),
                getNumberOfOperators() + other.getNumberOfOperators(),
                getNumberOfUniqueOperands() + other.getNumberOfUniqueOperands(),
                getNumberOfUniqueOperators() + other.getNumberOfUniqueOperators());
    }

    public int getBugs() {
        return (int)(getVolume() / 3000);
    }

    public double getDifficulty() {
        return getNumberOfUniqueOperands() == 0
                ? 0
                : ((getNumberOfUniqueOperators() / 2.0) * (getNumberOfOperands() / ((double)getNumberOfUniqueOperands())));
    }

    public double getEffort() {
        return ((getDifficulty() * getVolume()) / 18.0);
    }

    public int getLength() {
        return getNumberOfOperators() + getNumberOfOperands();
    }

    public int getVocabulary() {
        return getNumberOfUniqueOperators() + getNumberOfUniqueOperands();
    }

    public double getVolume() {
        if (getVocabulary() == 0)
            return 0.0;
        return ((double) getLength()) * Math.log(getVocabulary()) / Math.log(2.0);
    }
}
