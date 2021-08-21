package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsEVALRContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class EvalRStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private ComparisonFactor condition;

	public EvalRStatement(Opcode opcode) {
		super(opcode);

	}

	public ComparisonFactor getCondition() {
		return condition;
	}

	public void setCondition(ComparisonFactor condition) {
		this.condition = condition;
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
		this.condition = (ComparisonFactor) getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		CsEVALRContext evalContext = (CsEVALRContext) parseTree.getChild(5).getChild(0);
		this.condition = (ComparisonFactor) FreeContextAnalyzer.getFreeContextFactor(evalContext.fixedexpression);

		this.setControlIndicators(parseTree);
	}
}
