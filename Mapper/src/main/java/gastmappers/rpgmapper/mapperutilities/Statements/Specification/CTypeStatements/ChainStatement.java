package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ChainStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor keyValue, name, dataStructure;
	private boolean noLockFlag;

	public ChainStatement(Opcode opcode) {
		super(opcode);

	}

	public boolean isNoLockFlag() {
		return noLockFlag;
	}

	public void setNoLockFlag(boolean noLockFlag) {
		this.noLockFlag = noLockFlag;
	}

	public Factor getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Factor keyValue) {
		this.keyValue = keyValue;
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

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		// Establecer banderas en caso de que existan la extension N
		this.noLockFlag = hasExtender(parseTree, "N");

		this.setType("Statement");

	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		// Análisis para el caso en que sea FreeContext
		int searchArgIndex = getSpecificChildIndex(parseTree, "Search_argContext");

		for (int i = 0; i < parseTree.getChildCount(); i++) {
			int normalizedIndex = i + searchArgIndex;
			Factor childFactor = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(normalizedIndex));
			switch (i) {
			case 0:
				this.keyValue = childFactor;
				break;
			case 1:
				this.name = childFactor;
				break;
			case 2:
				this.dataStructure = childFactor;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.keyValue = this.getLeftFactor(parseTree);

		this.name = this.getFactor(parseTree, "rightFactor");

		this.dataStructure = this.getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}

}
