package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ExceptStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor exceptLabel;

	public ExceptStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getExceptLabel() {
		return exceptLabel;
	}

	public void setExceptLabel(Factor exceptLabel) {
		this.exceptLabel = exceptLabel;
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
		// Análisis para el caso en que sea FreeContext
		int identifierIndex = getSpecificChildIndex(parseTree, "IdentifierContext");
		if (identifierIndex > 0) {
			this.exceptLabel = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierIndex));
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.exceptLabel = getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
