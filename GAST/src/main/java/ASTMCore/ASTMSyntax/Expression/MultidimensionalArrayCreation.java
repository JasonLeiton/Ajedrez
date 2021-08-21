package ASTMCore.ASTMSyntax.Expression;

import java.util.List;

public class MultidimensionalArrayCreation extends ArrayCreation{
    int jaggedDimensionsAmount;
    List<Integer> jaggedDimensions;

    public int getJaggedDimensionsAmount() {
        return jaggedDimensionsAmount;
    }

    public void setJaggedDimensionsAmount(int jaggedDimensionsAmount) {
        this.jaggedDimensionsAmount = jaggedDimensionsAmount;
    }

    public List<Integer> getJaggedDimensions() {
        return jaggedDimensions;
    }

    public void setJaggedDimensions(List<Integer> jaggedDimensions) {
        this.jaggedDimensions = jaggedDimensions;
    }
}
