package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsORxxContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.DefaultValues.BooleanOperation;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class OrStatement extends CTypeStatement {

	public OrStatement(Opcode opcode) {
		super(opcode);
	}

	public ComparisonFactor createComparison(ParseTree parseTree, Opcode opcode) {
		ComparisonFactor comparisonFactor = new ComparisonFactor();

		CsORxxContext orStatement = (CsORxxContext) parseTree;

		String leftFactorContent = orStatement.factor().getText();
		Factor leftOperand = new LiteralFactor(leftFactorContent);

		Factor rightOperand = this.getFactor(parseTree, "rightFactor");
		String operandString = BooleanOperation.getBooleanOperationSymbol(opcode.booleanOperation);

		Operand operator = new Operand(operandString);

		comparisonFactor.setOperands(leftOperand, rightOperand, operator);
		return comparisonFactor;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
	}

}
