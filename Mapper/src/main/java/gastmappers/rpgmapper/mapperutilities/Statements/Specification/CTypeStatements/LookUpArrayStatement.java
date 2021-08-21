package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ArrayFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class LookUpArrayStatement extends LookUpStatement {

	private Factor arrayName, searchIndex;

	public LookUpArrayStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getArrayName() {
		return arrayName;
	}

	public void setArrayName(Factor arrayName) {
		this.arrayName = arrayName;
	}

	public Factor getSearchIndex() {
		return searchIndex;
	}

	public void setSearchIndex(Factor searchIndex) {
		this.searchIndex = searchIndex;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		setSearchArgument(getLeftFactor(parseTree));
		this.setIndicators(parseTree);
		ArrayFactor arrayFactor = (ArrayFactor) getFactor(parseTree, "rightFactor");
		this.arrayName = arrayFactor.getArrayNameFactor();
		this.searchIndex = arrayFactor.getIndex();

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
