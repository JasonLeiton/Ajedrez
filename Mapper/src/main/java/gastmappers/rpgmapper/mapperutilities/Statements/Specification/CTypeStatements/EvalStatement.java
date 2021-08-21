package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsEVALContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class EvalStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private boolean halfAdjust;
	private ComparisonFactor condition;

	public EvalStatement(Opcode opcode) {
		super(opcode);

	}

	public boolean isHalfAdjust() {
		return halfAdjust;
	}

	public void setHalfAdjust(boolean halfAdjust) {
		this.halfAdjust = halfAdjust;
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

		this.halfAdjust = hasExtender(parseTree, "H");

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		// Análisis para el caso en que sea FreeContext
		if (parseTree.getClass().getSimpleName().contentEquals("AssignmentExpressionContext")) {
			this.condition = (ComparisonFactor) FreeContextAnalyzer.getFreeContextFactor(parseTree);
		} else {
			this.condition = (ComparisonFactor) getLastChildFactor(parseTree);
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		try {
			CsEVALContext evalContext = (CsEVALContext) parseTree.getChild(5).getChild(0);
			this.condition = (ComparisonFactor) FreeContextAnalyzer.getFreeContextFactor(evalContext.fixedexpression);

			this.setControlIndicators(parseTree);
		} catch (Exception e) {
			//e.printStackTrace();
		}
	}
}
