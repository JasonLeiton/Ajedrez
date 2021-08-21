package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class MxxzoStatement extends CTypeStatement {

	private Factor source, target;
	private String flags;

	public MxxzoStatement(Opcode opcode) {
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

	public String getFlags() {
		return flags;
	}

	public void setFlags(String flags) {
		this.flags = flags;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.source = getFactor(parseTree, "rightFactor");
		this.target = getFactor(parseTree, "resultFactor");

		this.flags = getOpcode().getOpcodeEnum().toString().substring(1, 3);

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
