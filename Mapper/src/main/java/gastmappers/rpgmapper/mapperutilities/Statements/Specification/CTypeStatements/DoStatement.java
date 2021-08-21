package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.BegindoContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.DefaultValues.BooleanOperation;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.AssignStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.DefinitionStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class DoStatement extends CTypeStatement {

	private DefinitionStatement definition;
	private ComparisonFactor condition;
	private GroupStatements bodyDo;
	private AssignStatement increment;

	public DoStatement(Opcode opcode) {
		super(opcode);

	}

	public DefinitionStatement getDefinition() {
		return definition;
	}

	public void setDefinition(DefinitionStatement definition) {
		this.definition = definition;
	}

	public ComparisonFactor getCondition() {
		return condition;
	}

	public void setCondition(ComparisonFactor condition) {
		this.condition = condition;
	}

	public GroupStatements getBodyDo() {
		return bodyDo;
	}

	public void setBodyDo(GroupStatements bodyDo) {
		this.bodyDo = bodyDo;
	}

	public AssignStatement getIncrement() {
		return increment;
	}

	public void setIncrement(AssignStatement increment) {
		this.increment = increment;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		BegindoContext beginDoContext = (BegindoContext) parseTree.getChild(0);
		this.condition = new ComparisonFactor();
		this.definition = new DefinitionStatement();

		Factor leftOperand = getDoLeftFactor(beginDoContext);
		Factor rightOperand = this.getFactor(beginDoContext, "rightFactor");
		if (rightOperand == null) {
			rightOperand = new LiteralFactor("1");
		}
		Factor resultOperand = this.getFactor(beginDoContext, "resultFactor");

		String operandString = BooleanOperation.getBooleanOperationSymbol("LT");
		Operand operand = new Operand(operandString);
		this.condition.setOperands(resultOperand, rightOperand, operand);

		this.definition.setValues(resultOperand, leftOperand, 0);

		this.bodyDo = new GroupStatements();
		// El límite es -1 porque el último elemento será el END
		int j = 1;
		while (j < parseTree.getChildCount() - 1) {
			ParseTree child = parseTree.getChild(j);
			this.bodyDo.addStatement(Statement.generateStatement(child, child.getChild(0)));
			j++;
		}

		ParseTree endDoParseTree = parseTree.getChild(j);
		EndStatement endDoStatement = (EndStatement) Statement.generateStatement(endDoParseTree, endDoParseTree);
		Factor incrementVariable = endDoStatement.getEndDoFactor();
		if (incrementVariable == null) {
			incrementVariable = new LiteralFactor("1");
		}

		this.increment = new AssignStatement();
		this.increment.setOperands(resultOperand, incrementVariable, resultOperand.getValue(), 4);

		this.setControlIndicators(beginDoContext);
		this.setType("DoUntilLoop");
	}

	private Factor getDoLeftFactor(ParseTree parseTree) {
		BegindoContext statement = (BegindoContext) parseTree;
		String leftFactorContent = statement.factor().getText();
		if (!leftFactorContent.contentEquals("              ")) {
			return new LiteralFactor(leftFactorContent);
		}
		return new LiteralFactor("1");
	}
}