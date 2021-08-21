package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ReadStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private boolean noLock;
	private Factor name, dataStructure;

	public ReadStatement(Opcode opcode) {
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
		int identifierIndex = getSpecificChildIndex(parseTree, "IdentifierContext");

		this.name = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierIndex));

		identifierIndex++;
		if (identifierIndex < parseTree.getChildCount()) {
			this.dataStructure = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierIndex));
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.name = getFactor(parseTree, "rightFactor");
		this.dataStructure = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}
}
