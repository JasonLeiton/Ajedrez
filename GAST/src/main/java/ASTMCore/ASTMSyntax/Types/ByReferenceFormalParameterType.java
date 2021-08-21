package ASTMCore.ASTMSyntax.Types;


import static ASTMCore.metadata.Tags.BY_REFERENCE_FORMAL_PARAMETER_TYPE;

public class ByReferenceFormalParameterType extends FormalParameterType {

    public ByReferenceFormalParameterType() {
        setTag(BY_REFERENCE_FORMAL_PARAMETER_TYPE);
    }
}
