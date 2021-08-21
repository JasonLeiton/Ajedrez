package ASTMCore.ASTMSemantics;

import static ASTMCore.metadata.Tags.BLOCK_SCOPE;

/**
 * Esta clase modela un bloque de un alcance general.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class BlockScope extends Scope {

    public BlockScope() {
        setTag(BLOCK_SCOPE);
    }
}
