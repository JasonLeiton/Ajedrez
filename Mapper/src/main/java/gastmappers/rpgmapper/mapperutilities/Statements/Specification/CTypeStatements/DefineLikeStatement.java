package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class DefineLikeStatement extends DefineStatement {

	private Factor basedOnVariable, variable;

	public DefineLikeStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getBasedOnVariable() {
		return basedOnVariable;
	}

	public void setBasedOnVariable(Factor basedOnVariable) {
		this.basedOnVariable = basedOnVariable;
	}

	public Factor getVariable() {
		return variable;
	}

	public void setVariable(Factor variable) {
		this.variable = variable;
	}

	public void setVariables(Factor basedOnVariable, Factor variable) {
		this.basedOnVariable = basedOnVariable;
		this.variable = variable;
	}

}
