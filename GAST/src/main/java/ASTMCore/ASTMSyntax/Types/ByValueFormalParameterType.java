package ASTMCore.ASTMSyntax.Types;


import static ASTMCore.metadata.Tags.BY_VALUE_FORMAL_PARAMETER_TYPE;

public class ByValueFormalParameterType extends FormalParameterType {

    public ByValueFormalParameterType() {
        setTag(BY_VALUE_FORMAL_PARAMETER_TYPE);
    }
}
