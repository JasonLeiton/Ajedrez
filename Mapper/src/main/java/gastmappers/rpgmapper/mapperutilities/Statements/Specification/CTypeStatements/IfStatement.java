package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.BeginifContext;
import org.rpgleparser.RpgParser.CsIFxxContext;
import org.rpgleparser.RpgParser.ElseifstmtContext;

import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationManager;
import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.DefaultValues.BooleanOperation;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;
import gastmappers.rpgmapper.mapperutilities.Structure.Statements.Context.ContextParseTree;
import gastmappers.rpgmapper.mapperutilities.Structure.Statements.Context.StatementContext;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class IfStatement extends CTypeStatement {

	private Factor conditions;
	private GroupStatements bodyIf, bodyElse;

	public IfStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getConditions() {
		return conditions;
	}

	public void setConditions(Factor conditions) {
		this.conditions = conditions;
	}

	public GroupStatements getBodyIf() {
		return bodyIf;
	}

	public void setBodyIf(GroupStatements bodyIf) {
		this.bodyIf = bodyIf;
	}

	public GroupStatements getBodyElse() {
		return bodyElse;
	}

	public void setBodyElse(GroupStatements bodyElse) {
		this.bodyElse = bodyElse;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		boolean freeContext = false;

		if (parseTree.getClass().getSimpleName().contentEquals("BlockContext")) {
			parseTree = parseTree.getChild(0);
		}
		conditions = new ComparisonFactor();
		ParseTree allStatements = null;
		if (parseTree.getChild(0).getChildCount() == 1) {
			CsIFxxContext ifStatement;
			try {
				ifStatement = (CsIFxxContext) parseTree.getChild(0).getChild(0);
			} catch (Exception e) {
				ifStatement = (CsIFxxContext) parseTree.getChild(0).getChild(0).getChild(0);
			}

			// El factor izquierdo está fuera de los hijos, por eso se hace .factor()
			String leftFactorContent = ifStatement.factor().getText();
			Factor leftOperand = new LiteralFactor(leftFactorContent);

			Factor rightOperand = this.getFactor(ifStatement, "rightFactor");

			String operandString = BooleanOperation.getBooleanOperationSymbol(super.getOpcode().booleanOperation);
			Operand operand = new Operand(operandString);
			ComparisonFactor initialConditions = new ComparisonFactor();
			initialConditions.setOperands(leftOperand, rightOperand, operand);

			this.conditions = StatementContext.getAllConditionsStatement(ifStatement, initialConditions);

			allStatements = ifStatement.getParent().getParent();
		} else {
			String className = parseTree.getChild(0).getClass().getSimpleName();
			// Caso especial de que haya un else if dentro de un if
			if (className.contentEquals("ElseifstmtContext") || className.contentEquals("ElsestmtContext")) {
				try {
					if (parseTree.getChild(0).getChild(0).getClass().getSimpleName()
							.contentEquals("Op_elseifContext")) {
						ParseTree elseIfCondition = parseTree.getChild(0).getChild(0);
						elseIfCondition = elseIfCondition.getChild(elseIfCondition.getChildCount() - 1);
						this.conditions = FreeContextAnalyzer.getFreeContextFactor(elseIfCondition);
						freeContext = true;
					} else {
						ElseifstmtContext elseIfContext = (ElseifstmtContext) parseTree.getChild(0);
						this.conditions = (ComparisonFactor) FreeContextAnalyzer
								.getFreeContextFactor(elseIfContext.fixedexpression);
					}

				} catch (Exception e) {

				}
				allStatements = parseTree;
			} else {
				BeginifContext ifContext = (BeginifContext) parseTree.getChild(0);

				if (ifContext.getChild(0).getClass().getSimpleName().contentEquals("Op_ifContext")) {
					ParseTree ifCondition = ifContext.getChild(0);
					ifCondition = ifCondition.getChild(ifCondition.getChildCount() - 1);
					this.conditions = FreeContextAnalyzer.getFreeContextFactor(ifCondition);
					freeContext = true;
				} else {
					this.conditions = FreeContextAnalyzer.getFreeContextFactor(ifContext.fixedexpression);
				}

				allStatements = ifContext.getParent();
			}
		}

		this.bodyIf = new GroupStatements();
		this.bodyElse = new GroupStatements();
		boolean elseFlag = false;

		// El límite es el penúltimo porque el último es el END
		int j = 1;
		while (j < allStatements.getChildCount() - 1) {
			ParseTree child = allStatements.getChild(j);
			String className = child.getClass().getSimpleName();
			if (className.contains("ElseifstmtContext")) {
				ContextParseTree groupElseStatements = new ContextParseTree();
				while (j < allStatements.getChildCount()) {
					ParseTree elseChild = allStatements.getChild(j);
					groupElseStatements.addElement(elseChild);
					j++;
				}
				IfStatement elseStatement = new IfStatement(getOpcode());
				elseStatement.constructStatement(groupElseStatements);
				this.bodyElse.addStatement(elseStatement);
			} else if (className.contains("ElsestmtContext")) {
				elseFlag = true;
			} else {
				SpecificationManager statement = new SpecificationManager();
				Statement generatedStatement = statement.createStatement(child.getChild(0));
				if (elseFlag) {
					this.bodyElse.addStatement(generatedStatement);
				} else {
					this.bodyIf.addStatement(generatedStatement);
				}
			}
			j++;
		}

		if (!freeContext) {
			try {
				this.setControlIndicators(parseTree.getChild(0).getChild(0));
			} catch (Exception e) {
				this.setControlIndicators(parseTree.getChild(0));
			}
		}

		this.setType("IfStatement");
	}

}
