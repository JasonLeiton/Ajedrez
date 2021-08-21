package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ReadEStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private boolean noLock;
	private Factor name, dataStructure, keyValue;

	public ReadEStatement(Opcode opcode) {
		super(opcode);

	}

	public boolean isNoLock() {
		return noLock;
	}

	public void setNoLock(boolean noLock) {
		this.noLock = noLock;
	}

	public Factor getName() {
		return name;
	}

	public void setName(Factor name) {
		this.name = name;
	}

	public Factor getDataStructure() {
		return dataStructure;
	}

	public void setDataStructure(Factor dataStructure) {
		this.dataStructure = dataStructure;
	}

	public Factor getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Factor keyValue) {
		this.keyValue = keyValue;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.noLock = hasExtender(parseTree, "N");

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		int identifierIndex = getSpecificChildIndex(parseTree, "Search_argContext");

		for (int i = identifierIndex; i < parseTree.getChildCount(); i++) {
			Factor factor = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(i));

			int normalizeIndex = i - identifierIndex;
			switch (normalizeIndex) {
			case 0:
				this.keyValue = factor;
				break;
			case 1:
				this.name = factor;
				break;
			case 2:
				this.dataStructure = factor;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.keyValue = getLeftFactor(parseTree);
		this.name = getFactor(parseTree, "rightFactor");
		this.dataStructure = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}
}
