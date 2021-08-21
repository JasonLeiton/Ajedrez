package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;


import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.FactorContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.DurationCodes;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

/**
 * Mapeador de la instruccion ADDDUR
 * 
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class AddDurStatement extends CTypeStatement {

	private Factor leftOperand, rightOperand, result;
	private Operand operand;
	private String duration;

	public AddDurStatement(Opcode opcode) {
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.leftOperand = this.getLeftFactor(parseTree);
		this.rightOperand = this.getFactor(parseTree, "rightFactor");
		this.result = this.getFactor(parseTree, "resultFactor");
		if (this.leftOperand == null) {
			this.leftOperand = this.result;
		}
		this.operand = new Operand("+");
		this.duration = getDurationCode(parseTree);

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

	/**
	 * 
	 * Factor 2 es de la forma duration:dur code.
	 * 
	 * Esta funcion obtiene la variable 'dur code'
	 * 
	 * @param parseTree arbol con toda la instruccion
	 * @return Texto con la información de la duracion.
	 */
	private String getDurationCode(ParseTree parseTree) {
		parseTree = parseTree.getChild(5).getChild(0).getChild(1);
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("FactorContext")) {
				String duration = ((FactorContext) child).constant2.getText();
				return DurationCodes.getDurationCode(duration);
			}
			i++;
		}
		return "";
	}


}
