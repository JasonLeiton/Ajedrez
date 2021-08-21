package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;

public class OpenStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor fileName;

	public OpenStatement(Opcode opcode) {
		super(opcode);
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
		this.fileName = getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.fileName = getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
