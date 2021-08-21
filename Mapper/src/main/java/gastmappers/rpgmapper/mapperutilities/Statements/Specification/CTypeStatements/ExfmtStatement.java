package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;

public class ExfmtStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor workstnFormat;

	public ExfmtStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getWorkstnFormat() {
		return workstnFormat;
	}

	public void setWorkstnFormat(Factor workstnFormat) {
		this.workstnFormat = workstnFormat;
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
		this.workstnFormat = getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.workstnFormat = getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
