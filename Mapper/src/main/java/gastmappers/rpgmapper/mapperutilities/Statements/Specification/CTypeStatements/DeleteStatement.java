package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class DeleteStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor keyValue, fileName;

	public DeleteStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(Factor keyValue) {
		this.keyValue = keyValue;
	}

	public Factor getFileName() {
		return fileName;
	}

	public void setFileName(Factor fileName) {
		this.fileName = fileName;
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
		this.fileName = getLastChildFactor(parseTree);

		int indexArgs = getSpecificChildIndex(parseTree, "Search_argContext");
		if (indexArgs > 0) {
			this.keyValue = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(indexArgs));
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.keyValue = this.getLeftFactor(parseTree);
		this.fileName = this.getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
