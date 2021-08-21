package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;


import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsBEGSRContext;
import org.rpgleparser.RpgParser.SubroutineContext;

import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationManager;
import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class BegsrStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private String subroutineLabel;
	private GroupStatements body;
	private Factor returningPoint;

	public BegsrStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getReturningPoint() {
		return returningPoint;
	}

	public void setReturningPoint(Factor returningPoint) {
		this.returningPoint = returningPoint;
	}

	public String getSubroutineLabel() {
		return subroutineLabel;
	}

	public void setSubroutineLabel(String subroutineLabel) {
		this.subroutineLabel = subroutineLabel;
	}

	public GroupStatements getBody() {
		return body;
	}

	public void setBody(GroupStatements body) {
		this.body = body;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		String className = ((SubroutineContext) parseTree).begin.getChild(0).getClass().getSimpleName();

		if (className.contains("Free")) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.body = new GroupStatements();

		for (int i = 1; i < parseTree.getChildCount() - 1; i++) {
			ParseTree child = parseTree.getChild(i);
			SpecificationManager statement = new SpecificationManager();
			this.body.addStatement(statement.createStatement(child.getChild(0)));
		}

		this.setType("Statement");
	}

	private Factor getFreeContextReturningPoint(ParseTree parseTree) {
		parseTree = ((SubroutineContext) parseTree).end.getChild(0);
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (!className.contentEquals("TerminalNodeImpl")) {
				return FreeContextAnalyzer.getFreeContextFactor(child);
			}
			i++;
		}
		return null;
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		this.subroutineLabel = FreeContextAnalyzer
				.getFreeContextFactor(((SubroutineContext) parseTree).begin.getChild(0)).getValue();
		this.returningPoint = getFreeContextReturningPoint(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		CsBEGSRContext beginSubRoutine = (CsBEGSRContext) ((SubroutineContext) parseTree).begin.getChild(0);
		this.subroutineLabel = beginSubRoutine.factor1.getText();

		this.setControlIndicators(beginSubRoutine);
	}

}
