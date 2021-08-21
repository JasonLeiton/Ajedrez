package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class MoveAStatement extends CTypeStatement {

	private Factor source, target;
	private boolean clearBefore;
	private List<Statement> indicatorsStatements;

	public MoveAStatement(Opcode opcode) {
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

	public boolean isClearBefore() {
		return clearBefore;
	}

	public void setClearBefore(boolean clearBefore) {
		this.clearBefore = clearBefore;
	}

	public List<Statement> getIndicatorsStatements() {
		return indicatorsStatements;
	}

	public void setIndicatorsStatements(List<Statement> indicatorsStatements) {
		this.indicatorsStatements = indicatorsStatements;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.source = getFactor(parseTree, "rightFactor");
		this.target = getFactor(parseTree, "resultFactor");

		this.indicatorsStatements = getIndicatorsStatements(parseTree, this.target, new LiteralFactor("0"),
				"AssignStatement");

		this.clearBefore = hasExtender(parseTree, "P");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}
}
