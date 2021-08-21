package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsWHENContext;
import org.rpgleparser.RpgParser.CsWHENxxContext;

import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationManager;
import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.DefaultValues.BooleanOperation;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.Structure.Statements.Context.StatementContext;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class WhenStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor conditions;
	private GroupStatements body;

	@Override
	public void constructStatement(ParseTree parseTree) {

	}

	public WhenStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getConditions() {
		return conditions;
	}

	public void setConditions(Factor conditions) {
		this.conditions = conditions;
	}

	public GroupStatements getBody() {
		return body;
	}

	public void setBody(GroupStatements body) {
		this.body = body;
	}

	public void createWhenStatement(ParseTree parseTree, int indexLimit) {

		if (parseTreeIsOpcodeContext(parseTree.getChild(0))) {
			buildFreeContextStatement(parseTree.getChild(0));
		} else {
			buildColumnarStatement(parseTree);
		}

		this.body = new GroupStatements();
		for (int i = 1; i < indexLimit; i++) {
			ParseTree child = parseTree.getChild(i);
			if (!child.getClass().getSimpleName().contentEquals("TerminalNodeImpl")) {
				SpecificationManager statement = new SpecificationManager();
				this.body.addStatement(statement.createStatement(child.getChild(0)));
			}
		}

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		this.conditions = getLastChildFactor(parseTree);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		try {
			CsWHENxxContext whenContext = (CsWHENxxContext) parseTree.getChild(0);
			super.setOpcode(Opcode.getOpcodefromParseTree(whenContext, true));
			conditions = new ComparisonFactor();

			// El factor izquierdo está fuera de los hijos, por eso se hace .factor()
			String leftFactorContent = whenContext.factor().getText();
			Factor leftOperand = new LiteralFactor(leftFactorContent);

			Factor rightOperand = this.getFactor(whenContext, "rightFactor");

			String operandString = BooleanOperation.getBooleanOperationSymbol(super.getOpcode().booleanOperation);
			Operand operand = new Operand(operandString);
			ComparisonFactor initialConditions = new ComparisonFactor();
			initialConditions.setOperands(leftOperand, rightOperand, operand);

			this.conditions = StatementContext.getAllConditionsStatement(whenContext, initialConditions);

		} catch (Exception e) {
			super.setOpcode(Opcode.getOpcodefromParseTree(parseTree.getChild(0), true));
			CsWHENContext whenFreeContext = (CsWHENContext) parseTree.getChild(0).getChild(5);
			this.conditions = (ComparisonFactor) FreeContextAnalyzer
					.getFreeContextFactor(whenFreeContext.fixedexpression);
		}

		//this.setControlIndicators(parseTree);
	}
}
