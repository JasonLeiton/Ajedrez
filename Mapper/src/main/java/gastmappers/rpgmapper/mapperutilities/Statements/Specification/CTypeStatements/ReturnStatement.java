package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsRETURNContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ReturnStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private boolean halfAdjust, maximumDigits, resultValueDigits;
	private Factor returnFactor;

	public ReturnStatement(Opcode opcode) {
		super(opcode);

	}

	public boolean isHalfAdjust() {
		return halfAdjust;
	}

	public void setHalfAdjust(boolean halfAdjust) {
		this.halfAdjust = halfAdjust;
	}

	public boolean isMaximumDigits() {
		return maximumDigits;
	}

	public void setMaximumDigits(boolean maximumDigits) {
		this.maximumDigits = maximumDigits;
	}

	public boolean isResultValueDigits() {
		return resultValueDigits;
	}

	public void setResultValueDigits(boolean resultValueDigits) {
		this.resultValueDigits = resultValueDigits;
	}

	public Factor getReturnFactor() {
		return returnFactor;
	}

	public void setReturnFactor(Factor returnFactor) {
		this.returnFactor = returnFactor;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.halfAdjust = hasExtender(parseTree, "H");
		this.maximumDigits = hasExtender(parseTree, "M");
		this.resultValueDigits = hasExtender(parseTree, "R");

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		this.returnFactor = getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		CsRETURNContext returnStament = (CsRETURNContext) parseTree.getChild(5).getChild(0);
		this.returnFactor = FreeContextAnalyzer.getFreeContextFactor(returnStament.fixedexpression);

		this.setControlIndicators(parseTree);
	}
}
