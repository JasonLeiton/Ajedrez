package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;


import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class BitOnStatement extends CTypeStatement {

	private Factor charVariable, bitsSetOn;

	public BitOnStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getCharVariable() {
		return charVariable;
	}

	public void setCharVariable(Factor charVariable) {
		this.charVariable = charVariable;
	}

	public Factor getBitsSetOn() {
		return bitsSetOn;
	}

	public void setBitsSetOn(Factor bitsSetOn) {
		this.bitsSetOn = bitsSetOn;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.bitsSetOn = this.getFactor(parseTree, "rightFactor");
		this.charVariable = this.getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}


}
