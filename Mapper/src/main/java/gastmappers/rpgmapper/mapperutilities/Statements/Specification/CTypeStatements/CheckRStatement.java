package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class CheckRStatement extends CTypeStatement {

	private Factor checklist, charValue, positions, indexStartPosition;

	public CheckRStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getChecklist() {
		return checklist;
	}

	public void setChecklist(Factor checklist) {
		this.checklist = checklist;
	}

	public Factor getCharValue() {
		return charValue;
	}

	public void setCharValue(Factor charValue) {
		this.charValue = charValue;
	}

	public Factor getPositions() {
		return positions;
	}

	public void setPositions(Factor positions) {
		this.positions = positions;
	}

	public Factor getIndexStartPosition() {
		return indexStartPosition;
	}

	public void setIndexStartPosition(Factor indexStartPosition) {
		this.indexStartPosition = indexStartPosition;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.checklist = this.getLeftFactor(parseTree);

		this.charValue = this.getFactor(parseTree, "rightFactor");

		this.positions = this.getFactor(parseTree, "resultFactor");

		this.indexStartPosition = this.getFactorExtender(parseTree);

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
