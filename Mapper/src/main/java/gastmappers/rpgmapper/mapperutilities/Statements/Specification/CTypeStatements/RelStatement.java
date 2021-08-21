package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class RelStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor workstnId, workstnFileName;

	public RelStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getWorkstnId() {
		return workstnId;
	}

	public void setWorkstnId(Factor workstnId) {
		this.workstnId = workstnId;
	}

	public Factor getWorkstnFileName() {
		return workstnFileName;
	}

	public void setWorkstnFileName(Factor workstnFileName) {
		this.workstnFileName = workstnFileName;
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
		int identifierIndex = getSpecificChildIndex(parseTree, "IdentifierContext");

		for (int i = identifierIndex; i < parseTree.getChildCount(); i++) {
			Factor factor = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(i));

			int normalizedIndex = i - identifierIndex;
			switch (normalizedIndex) {
			case 0:
				this.workstnId = factor;
				break;
			case 1:
				this.workstnFileName = factor;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.workstnId = getLeftFactor(parseTree);
		this.workstnFileName = getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
