package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class SetLLStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor keyValue, nameValue;

	public SetLLStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Factor keyValue) {
		this.keyValue = keyValue;
	}

	public Factor getNameValue() {
		return nameValue;
	}

	public void setNameValue(Factor nameValue) {
		this.nameValue = nameValue;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		int identifierIndex = getSpecificChildIndex(parseTree, "Search_argContext");

		for (int i = identifierIndex; i < parseTree.getChildCount(); i++) {
			Factor factor = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(i));

			int normalizedIndex = i - identifierIndex;
			switch (normalizedIndex) {
			case 0:
				this.keyValue = factor;
				break;
			case 1:
				this.nameValue = factor;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.keyValue = getLeftFactor(parseTree);
		this.nameValue = getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
