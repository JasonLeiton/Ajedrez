package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class DumpStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor descriptiveText;

	public DumpStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getDescriptiveText() {
		return descriptiveText;
	}

	public void setDescriptiveText(Factor descriptiveText) {
		this.descriptiveText = descriptiveText;
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
			this.descriptiveText = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierIndex));
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.descriptiveText = getLeftFactor(parseTree);

		this.setControlIndicators(parseTree);
	}

}
