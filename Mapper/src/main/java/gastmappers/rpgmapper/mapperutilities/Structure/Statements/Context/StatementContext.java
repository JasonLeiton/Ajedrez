package gastmappers.rpgmapper.mapperutilities.Structure.Statements.Context;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ComparisonFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.AndStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.OrStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

/**
 * Clase para extraer y formar las condiciones de una sentencia con múltiples
 * condiciones.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class StatementContext {

	/**
	 * Obtiene las condiciones ordenadas por el respectivo operador de manera que se
	 * contiene una dentro de otra
	 * 
	 * @param parseTree         Árbol del IF, Dow, etc
	 * @param initialConditions condición inicial que trae por defecto la sentencia
	 * @return un soo factor con todas las condiciones de la sentencia
	 */
	public static ComparisonFactor getAllConditionsStatement(ParseTree parseTree, ComparisonFactor initialConditions) {
		List<ParseTree> listConditions = getConditionalStatements(parseTree);
		if (listConditions.size() > 0) {
			int i = listConditions.size() - 1;
			boolean oneConditionRemain = false;
			ComparisonFactor allConditions = getTypeCondition(listConditions.get(i));
			Operand nextOperand = getNextOperand(listConditions.get(i));
			while (i >= 0) {
				if (i - 1 >= 0) {
					ComparisonFactor secondToLastCondition = getTypeCondition(listConditions.get(i - 1));
					ComparisonFactor temporalComparisonFactor = new ComparisonFactor(allConditions);
					allConditions = new ComparisonFactor();
					allConditions.setOperands(secondToLastCondition, temporalComparisonFactor, nextOperand);
					nextOperand = getNextOperand(listConditions.get(i - 1));
				} else {
					oneConditionRemain = true;
					break;
				}
				i--;
			}

			if (oneConditionRemain) {
				ComparisonFactor temporalComparisonFactor = new ComparisonFactor(allConditions);
				allConditions = new ComparisonFactor();
				allConditions.setOperands(initialConditions, temporalComparisonFactor, nextOperand);
				return allConditions;
			}
		} else {
			return initialConditions;
		}
		return null;
	}

	/**
	 * Crea una instrucción dependiendo del código de operación que presente el
	 * código
	 * 
	 * @param parseTree árbol del Statement
	 * @return una sentencia de tipo AND o OR.
	 */
	private static ComparisonFactor getTypeCondition(ParseTree parseTree) {
		Opcode conditionOpcode = Opcode.getOpcodefromParseTree(parseTree, true);
		switch (conditionOpcode.opcodeEnum) {
		case OR:
			return new OrStatement(conditionOpcode).createComparison(parseTree, conditionOpcode);
		case AND:
			return new AndStatement(conditionOpcode).createComparison(parseTree, conditionOpcode);
		default:
			return null;
		}
	}

	/**
	 * La siguiente operación va a depender del factor, por lo que se debe extraer
	 * para su posterior uso
	 * 
	 * @param parseTree árbol con la sentencia
	 * @return operando dependiendo del tipo
	 */
	private static Operand getNextOperand(ParseTree parseTree) {
		String className = parseTree.getClass().getSimpleName();
		if (className.contains("CsAND")) {
			return new Operand(Operand.getMetaSymbol("and"));
		} else {
			return new Operand(Operand.getMetaSymbol("or"));
		}
	}

	/**
	 * Saca específicamente todas las sentencias OR o AND del árbol del IF, DOW,
	 * etc.
	 * 
	 * @param parseTree árbol de la instrucción con múltiples hijos
	 * @return lista de solo sentencias OR o AND
	 */
	private static List<ParseTree> getConditionalStatements(ParseTree parseTree) {
		List<ParseTree> listConditions = new ArrayList<ParseTree>();
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contains("CsOR") || className.contains("CsAND")) {
				listConditions.add(child);
			}
		}
		return listConditions;
	}

}
