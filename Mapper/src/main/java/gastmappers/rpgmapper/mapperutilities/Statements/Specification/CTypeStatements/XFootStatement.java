package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

import java.util.List;

public class XFootStatement extends CTypeStatement {

	private Factor sourceArray, result;
	private List<Statement> indicatorsStatements;
	private boolean halfAdjust;

	public XFootStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getSourceArray() {
		return sourceArray;
	}

	public void setSourceArray(Factor sourceArray) {
		this.sourceArray = sourceArray;
	}

	public Factor getResult() {
		return result;
	}

	public void setResult(Factor result) {
		this.result = result;
	}

	public List<Statement> getIndicatorsStatements() {
		return indicatorsStatements;
	}

	public void setIndicatorsStatements(List<Statement> indicatorsStatements) {
		this.indicatorsStatements = indicatorsStatements;
	}

	public boolean getHalfAdjust() {
		return halfAdjust;
	}

	public void setHalfAdjust(boolean halfAdjust) {
		this.halfAdjust = halfAdjust;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.sourceArray = this.getFactor(parseTree, "rightFactor");
		this.result = this.getFactor(parseTree, "resultFactor");

		this.indicatorsStatements = getIndicatorsStatements(parseTree, this.result, new LiteralFactor("0"),
				"AssignStatement");

		this.halfAdjust = hasExtender(parseTree, "H");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}
}
