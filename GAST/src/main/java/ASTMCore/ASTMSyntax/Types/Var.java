package ASTMCore.ASTMSyntax.Types;

import static ASTMCore.metadata.Tags.VAR;

/**
 * Variable type that accepts any object
 *
 * @author AVIB team
 * @version 1.1
 * @since 1.1
 */
public class Var extends PrimitiveType{

    public Var() {
        setTag(VAR);
    }
}
