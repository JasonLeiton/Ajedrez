package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsDOUContext;
import org.rpgleparser.RpgParser.CsDOUxxContext;

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

public class DouStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor conditions;
	private GroupStatements bodyDou;

	public DouStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getConditions() {
		return conditions;
	}

	public void setConditions(Factor conditions) {
		this.conditions = conditions;
	}

	public GroupStatements getBodyDou() {
		return bodyDou;
	}

	public void setBodyDou(GroupStatements bodyDou) {
		this.bodyDou = bodyDou;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.conditions = new ComparisonFactor();
		if (parseTreeIsOpcodeContext(parseTree.getChild(0))) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.bodyDou = new GroupStatements();
		// El límite es -1 porque el último elemento será el END
		for (int j = 1; j < parseTree.getChildCount() - 1; j++) {
			ParseTree child = parseTree.getChild(j);
			SpecificationManager statement = new SpecificationManager();
			this.bodyDou.addStatement(statement.createStatement(child.getChild(0)));
		}

		this.setType("DoUntilLoop");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		ParseTree parseTreeConditions = FreeContextAnalyzer.getOpcodeElements(parseTree.getChild(0).getChild(0));
		parseTreeConditions = parseTreeConditions.getChild(parseTreeConditions.getChildCount() - 1);
		this.conditions = FreeContextAnalyzer.getFreeContextFactor(parseTreeConditions);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		String className = parseTree.getChild(0).getClass().getSimpleName();
		switch (className) {
		// DOUxx es factor1 DOUxx factor2
		case "CsDOUxxContext":
			CsDOUxxContext compareBranch = (CsDOUxxContext) parseTree.getChild(0);
			String leftFactorContent = compareBranch.factor().getText();
			Factor leftOperand = new LiteralFactor(leftFactorContent);
			Factor rightOperand = this.getFactor(compareBranch, "rightFactor");
			String operandString = BooleanOperation.getBooleanOperationSymbol(super.getOpcode().booleanOperation);
			Operand operand = new Operand(operandString);
			ComparisonFactor initialConditions = new ComparisonFactor();
			initialConditions.setOperands(leftOperand, rightOperand, operand);
			this.conditions = StatementContext.getAllConditionsStatement(parseTree.getChild(0), initialConditions);
			break;
		// DOU es: DOU expresion
		case "BegindouContext":
			CsDOUContext compareBranchFree = (CsDOUContext) parseTree.getChild(0).getChild(5);
			// Obtener la condición free
			this.conditions = FreeContextAnalyzer.getFreeContextFactor(compareBranchFree.fixedexpression);
			break;
		default:
			break;
		}

		this.setControlIndicators(parseTree.getChild(0));
	}

}
