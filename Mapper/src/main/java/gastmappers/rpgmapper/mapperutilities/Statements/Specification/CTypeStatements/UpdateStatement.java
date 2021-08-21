package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class UpdateStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor name, dataStructure;

	public UpdateStatement(Opcode opcode) {
		super(opcode);
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
				this.name = factor;
				break;
			case 1:
				this.dataStructure = factor;
				break;
			default:
				break;
			}
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.name = this.getFactor(parseTree, "rightFactor");
		this.dataStructure = this.getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}

}
