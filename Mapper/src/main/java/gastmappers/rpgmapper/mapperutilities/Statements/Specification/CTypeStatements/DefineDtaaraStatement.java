package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class DefineDtaaraStatement extends DefineStatement {

	private Factor source, target;

	public DefineDtaaraStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getSource() {
		return source;
	}

	public void setSource(Factor source) {
		this.source = source;
	}

	public Factor getTarget() {
		return target;
	}

	public void setTarget(Factor target) {
		this.target = target;
	}

	public void setVariables(Factor source, Factor target) {
		this.source = source;
		this.target = target;
	}

}
