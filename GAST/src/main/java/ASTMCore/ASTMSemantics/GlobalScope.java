package ASTMCore.ASTMSemantics;

import static ASTMCore.metadata.Tags.GLOBAL_SCOPE;

/**
 * Esta clase modela el alcance global de un elemento.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class GlobalScope extends Scope {

    public GlobalScope() {
        setTag(GLOBAL_SCOPE);
    }
}
