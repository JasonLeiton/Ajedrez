package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class OccurStatement extends CTypeStatement {

	private Factor ocurrenceToSet, dataStructure, ocurrence;

	public OccurStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getOcurrenceToSet() {
		return ocurrenceToSet;
	}

	public void setOcurrenceToSet(Factor ocurrenceToSet) {
		this.ocurrenceToSet = ocurrenceToSet;
	}

	public Factor getDataStructure() {
		return dataStructure;
	}

	public void setDataStructure(Factor dataStructure) {
		this.dataStructure = dataStructure;
	}

	public Factor getOcurrence() {
		return ocurrence;
	}

	public void setOcurrence(Factor ocurrence) {
		this.ocurrence = ocurrence;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.ocurrenceToSet = getLeftFactor(parseTree);
		this.dataStructure = getFactor(parseTree, "rightFactor");
		this.ocurrence = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
