package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ParenthesizedFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class CatStatement extends CTypeStatement {

	private Factor leftOperand, rightOperand, result;
	private Operand operand;

	public CatStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getLeftOperand() {
		return leftOperand;
	}

	public void setLeftOperand(Factor leftOperand) {
		this.leftOperand = leftOperand;
	}

	public Factor getRightOperand() {
		return rightOperand;
	}

	public void setRightOperand(Factor rightOperand) {
		this.rightOperand = rightOperand;
	}

	public Factor getResult() {
		return result;
	}

	public void setResult(Factor result) {
		this.result = result;
	}

	public Operand getOperand() {
		return operand;
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.leftOperand = this.getLeftFactor(parseTree);

		this.rightOperand = this.getFactor(parseTree, "rightFactor");
		this.result = this.getFactor(parseTree, "resultFactor");
		// Hay que contar los 0 que pueden haber en uno de los factores
		int blankCount = Integer.parseInt(this.getFactorExtender(parseTree).getValue());

		if (blankCount > 0) {
			generateNewRightOperand(blankCount);
		}

		// Manejo en caso de que el factor izquierdo sea nulo
		if (this.leftOperand == null) {
			this.leftOperand = this.result;
		}

		this.operand = new Operand("+");
		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

	/**
	 * Generar nuevo factor derecho de acuerdo a la cantidad de 0.
	 * 
	 * @param blankCount número de 0 en el parámetro correspondiente.
	 */
	private void generateNewRightOperand(int blankCount) {
		String typeVariable = this.rightOperand.getType();
		String blankSpaces = new String(new char[blankCount]).replace('\0', ' ');
		switch (typeVariable) {
		case "String": // Si es String se hace la suma directa.
			String newValue = addBlankSpaces(this.rightOperand.getValue(), blankSpaces);
			this.rightOperand.setValue(newValue);
			break;
		case "Variable": // En caso de variable hay que sumar las dos variables
			Factor leftFactor = new LiteralFactor(this.rightOperand.getValue());
			Factor rightFactor = new LiteralFactor(this.rightOperand.getValue());
			rightFactor.setValue(blankSpaces);
			rightFactor.setType("String");
			Operand addOperand = new Operand("+");
			ParenthesizedFactor parenthesizedFactor = new ParenthesizedFactor(leftFactor, rightFactor, addOperand);
			parenthesizedFactor.getRightOperand().setValue(blankSpaces);
			this.rightOperand = parenthesizedFactor;
		default:
			break;
		}
	}

	/**
	 * Genera un nuevo valor si es un String
	 * 
	 * @param value       valor del factor
	 * @param blankSpaces cantidad de espacios en blanco
	 * @return nuevo valor para que sea factor
	 */
	private String addBlankSpaces(String value, String blankSpaces) {
		String tempValue = value.substring(0, value.length() - 1);
		return tempValue + blankSpaces + "'";
	}
}
