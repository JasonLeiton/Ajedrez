package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.AssignStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

/**
 * Mapeador de la instrucción DIV.
 * 
 * Factor1: dividendo, Factor2: divisor, Resultado: cociente o dividendo y
 * cociente
 * 
 * Si Factor1 no se establece, el dividendo toma el valor del Resultado
 * 
 * Hay indicadores para comparar si el resultado es menor, igual o mayor a 0.
 * 
 * Se mezcla con el opcode MVR para determinar si se guarda el residuo
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class DivStatement extends CTypeStatement {

	private Factor leftOperand, rightOperand, result;
	private Operand operand;
	private AssignStatement remainder;
	private List<Statement> indicatorsStatements;
	private boolean halfAdjust;

	public DivStatement(Opcode opcode) {
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

	public AssignStatement getRemainder() {
		return remainder;
	}

	public void setRemainder(AssignStatement remainder) {
		this.remainder = remainder;
	}

	public List<Statement> getIndicatorsStatements() {
		return indicatorsStatements;
	}

	public void setIndicatorsStatements(List<Statement> indicatorsStatements) {
		this.indicatorsStatements = indicatorsStatements;
	}

	public boolean isHalfAdjust() {
		return halfAdjust;
	}

	public void setHalfAdjust(boolean halfAdjust) {
		this.halfAdjust = halfAdjust;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.leftOperand = this.getLeftFactor(parseTree);

		this.rightOperand = this.getFactor(parseTree, "rightFactor");
		this.result = this.getFactor(parseTree, "resultFactor");
		if (this.leftOperand == null) {
			this.leftOperand = this.result;
		}

		this.operand = new Operand("/");
		this.indicatorsStatements = getIndicatorsStatements(parseTree, this.result, new LiteralFactor("0"),
				"AssignStatement");

		if (checkRemainder(parseTree)) {
			MvrStatement mvrStatement = new MvrStatement();
			this.remainder = mvrStatement.buildRemainderStatement(getRemainderTree(parseTree), this.leftOperand,
					this.rightOperand);
		}

		this.halfAdjust = hasExtender(parseTree, "H");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

	private boolean checkRemainder(ParseTree parseTree) {
		if (getRemainderTree(parseTree).getClass().getSimpleName().contentEquals("CsMVRContext")) {
			return true;
		}
		return false;
	}

	private ParseTree getRemainderTree(ParseTree parseTree) {
		int maxNumberChild = parseTree.getChild(5).getChild(0).getChildCount() - 1;
		return parseTree.getChild(5).getChild(0).getChild(maxNumberChild);
	}

}
