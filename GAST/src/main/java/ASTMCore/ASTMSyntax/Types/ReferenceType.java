package ASTMCore.ASTMSyntax.Types;


import static ASTMCore.metadata.Tags.REFERENCE_TYPE;

public class ReferenceType extends ConstructedType {

    public ReferenceType() {
        setTag(REFERENCE_TYPE);
    }
}
