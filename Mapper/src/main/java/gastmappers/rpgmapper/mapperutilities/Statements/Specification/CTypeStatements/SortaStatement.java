package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsSORTAContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class SortaStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor arrayName;

	public SortaStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getArrayName() {
		return arrayName;
	}

	public void setArrayName(Factor arrayName) {
		this.arrayName = arrayName;
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
		this.arrayName = getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		CsSORTAContext sortaContext = (CsSORTAContext) parseTree.getChild(5).getChild(0);
		this.arrayName = FreeContextAnalyzer.getFreeContextFactor(sortaContext.fixedexpression);

		this.setControlIndicators(parseTree);
	}

}
