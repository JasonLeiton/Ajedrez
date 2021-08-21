package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

import java.util.List;

public class MoveStatement extends CTypeStatement {

	private Factor source, target, dateFormat;
	private List<Statement> indicatorsStatements;

	public MoveStatement(Opcode opcode) {
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

	public Factor getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(Factor dateFormat) {
		this.dateFormat = dateFormat;
	}

	public List<Statement> getIndicatorsStatements() {
		return indicatorsStatements;
	}

	public void setIndicatorsStatements(List<Statement> indicatorsStatements) {
		this.indicatorsStatements = indicatorsStatements;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.dateFormat = getLeftFactor(parseTree);

		this.source = this.getFactor(parseTree, "rightFactor");
		this.target = this.getFactor(parseTree, "resultFactor");

		this.indicatorsStatements = getIndicatorsStatements(parseTree, this.target, new LiteralFactor("0"),
				"AssignStatement");

		this.setControlIndicators(parseTree);
		this.setType("Statement");

	}
}
