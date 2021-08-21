package ASTMCore.ASTMSemantics;

import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.PROGRAM_SCOPE;

/**
 * Esta clase es para el alcance del programa.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class ProgramScope extends Scope {

	public ProgramScope() {
		setTag(PROGRAM_SCOPE);
	}

	public void accept(GASTVisitor v) {
		v.visitProgramScope(this);
	}
	
}
