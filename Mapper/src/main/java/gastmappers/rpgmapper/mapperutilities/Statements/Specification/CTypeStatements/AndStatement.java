package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;


import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsANDxxContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.DefaultValues.BooleanOperation;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

/**
 * Modela la declaración de un And.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class AndStatement extends CTypeStatement {

	public AndStatement(Opcode opcode) {
		super(opcode);

	}

	/**
	 * Se genera de manera manual porque forma parte de un añadido a las
	 * instrucciones que soportan AND. Por lo tanto, se devuelve una operación con
	 * los diferentes factores.
	 *
	 * @param parseTree AST con los datos de AND
	 * @param opcode
	 * @return operación de comparación con los factores
	 */
	public ComparisonFactor createComparison(ParseTree parseTree, Opcode opcode) {
		ComparisonFactor comparisonFactor = new ComparisonFactor();
		CsANDxxContext andStatement = (CsANDxxContext) parseTree;

		String leftFactorContent = andStatement.factor().getText();
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
