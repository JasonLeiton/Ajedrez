package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;

public class ReadCStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor subFileFormat;

	public ReadCStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getSubFileFormat() {
		return subFileFormat;
	}

	public void setSubFileFormat(Factor subFileFormat) {
		this.subFileFormat = subFileFormat;
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
		this.subFileFormat = getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.subFileFormat = getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
