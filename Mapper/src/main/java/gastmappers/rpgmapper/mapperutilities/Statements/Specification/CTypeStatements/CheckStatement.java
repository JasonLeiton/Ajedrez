package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsCHECKContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class CheckStatement extends CTypeStatement {

	private Factor checklist, baseValue, positions, indexStartPosition, found;

	public CheckStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getChecklist() {
		return checklist;
	}
	public Factor getFound() {
		return found;
	}


	public void setChecklist(Factor checklist) {
		this.checklist = checklist;
	}

	public Factor getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(Factor baseValue) {
		this.baseValue = baseValue;
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

		this.baseValue = this.getFactor(parseTree, "rightFactor");

		this.positions = this.getFactor(parseTree, "resultFactor");

		this.indexStartPosition = this.getFactorExtender(parseTree);
		
		CsCHECKContext check = (CsCHECKContext)parseTree.getChild(5).getChild(0);
		Cspec_fixed_standard_partsContext stdParts = (Cspec_fixed_standard_partsContext)check.getChild(1);
		if (!"  ".equalsIgnoreCase(stdParts.eq.getText()))
			found = new LiteralFactor("*IN"+stdParts.eq.getText());
		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
