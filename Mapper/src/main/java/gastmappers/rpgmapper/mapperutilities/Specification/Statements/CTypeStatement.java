package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Cs_operationExtenderContext;
import org.rpgleparser.RpgParser.Cspec_fixedContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;
import org.rpgleparser.RpgParser.FactorContext;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.ArrayFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.AssignStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.DefinitionStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public abstract class CTypeStatement extends Statement {

	private Opcode opcode;
	private Factor controlLevel, conditioningIndicator;
	private boolean negateCondition;
	
	
	
	public CTypeStatement(Opcode opcode) {
		this.opcode = opcode;
	}

	public Factor getControlLevel() {
		return controlLevel;
	}

	public void setControlLevel(Factor controlLevel) {
		this.controlLevel = controlLevel;
	}

	public Factor getConditioningIndicator() {
		return conditioningIndicator;
	}

	public void setConditioningIndicator(Factor conditioningIndicator) {
		this.conditioningIndicator = conditioningIndicator;
	}

	public boolean isNegateCondition() {
		return negateCondition;
	}

	public void setNegateCondition(boolean negateCondition) {
		this.negateCondition = negateCondition;
	}

	protected void setControlIndicators(ParseTree parseTree) {
		this.controlLevel = getFactorColumn(parseTree, 1);
		this.negateCondition = getBooleanColumn(parseTree, 2, "N");
		this.conditioningIndicator = getFactorColumn(parseTree, 3);
	}

	public Opcode getOpcode() {
		return opcode;
	}

	public void setOpcode(Opcode opcode) {
		this.opcode = opcode;
	}

	public Factor getFactor(ParseTree parseTree, String factor) {
		// Tanto el factor derecho como el resultado, están dentro de los hijos, por lo
		// que se debe bajar de nivel
		int maxNumberChild = parseTree.getChild(5).getChild(0).getChildCount() - 1;
		String className;
		Cspec_fixed_standard_partsContext operands;
		if (maxNumberChild <= 0) {
			maxNumberChild = parseTree.getChild(5).getChildCount() - 1;
			className = parseTree.getChild(5).getChild(maxNumberChild).getClass().getSimpleName();
			operands = (Cspec_fixed_standard_partsContext) parseTree.getChild(5).getChild(maxNumberChild);
		} else {
			className = parseTree.getChild(5).getChild(0).getChild(maxNumberChild).getClass().getSimpleName();
			if (className.contentEquals("CsMVRContext")) {
				maxNumberChild--;
			}
			try {
				operands = (Cspec_fixed_standard_partsContext) parseTree.getChild(5).getChild(0)
						.getChild(maxNumberChild);
			} catch (Exception e) {
				// TODO: handle exception
				operands = (Cspec_fixed_standard_partsContext) parseTree.getChild(5).getChild(0).getChild(1);
			}
		}

		String factorContent = "              ";
		switch (factor) {
		case "rightFactor":
			try {
				factorContent = operands.factor().content.getText();

			} catch (Exception e) {
				try {
					factorContent = operands.factor2.getText();
				} catch (Exception e2) {
					factorContent = "              ";
				}
			}
			break;
		case "resultFactor":
			factorContent = operands.result.getText();
			break;
		default:
			break;
		}
		if (!factorContent.contentEquals("              ")) {
			if (ArrayFactor.isArray(factorContent)) {
				return new ArrayFactor(factorContent);
			} else {
				return new LiteralFactor(factorContent);
			}
		}
		return null;
	}

	/**
	 * En los factores donde se encuentre una extensión :xx, se obtiene el número xx
	 * 
	 * @param parseTree
	 * @return número de 0
	 */
	public Factor getFactorExtender(ParseTree parseTree) {
		int maxNumberChild = parseTree.getChild(5).getChild(0).getChildCount() - 1;
		Cspec_fixed_standard_partsContext operands = (Cspec_fixed_standard_partsContext) parseTree.getChild(5)
				.getChild(0).getChild(maxNumberChild);
		try {
			String value = operands.factor().content2.getText();
			return new LiteralFactor(value);
		} catch (Exception e) {
			return new LiteralFactor("0");
		}
	}

	public Factor getLeftFactor(ParseTree parseTree) {
		Cspec_fixedContext statement = (Cspec_fixedContext) parseTree;
		String leftFactorContent = statement.factor().getText();
		if (!leftFactorContent.contentEquals("              ")) {
			return new LiteralFactor(leftFactorContent);
		}
		return null;
	}

	public Factor getLeftFactorExtender(ParseTree parseTree, String pos) {
		FactorContext factorContext = ((Cspec_fixedContext) parseTree).factor();
		String value = "              ";
		switch (pos) {
		case "left":
			value = factorContext.content.getText();
			break;
		case "right":
			value = factorContext.content2.getText();
		default:
			break;
		}
		if (!value.contentEquals("              ")) {
			return new LiteralFactor(value);
		}
		return null;
	}

	public List<Statement> getIndicatorsStatements(ParseTree parseTree, Factor leftFactor, Factor rightFactor,
			String typeStatement) {
		int numberChild = parseTree.getChild(5).getChild(0).getChildCount();
		Cspec_fixed_standard_partsContext operands = null;
		for (int i = 0; i < numberChild; i++) {
			if (parseTree.getChild(5).getChild(0).getChild(i) instanceof Cspec_fixed_standard_partsContext) {
				operands = (Cspec_fixed_standard_partsContext)parseTree.getChild(5).getChild(0).getChild(i);
			}
		}
		if (operands == null)throw new NullPointerException("Operands at get indicator statements is null");
		//Cspec_fixed_standard_partsContext operands = (Cspec_fixed_standard_partsContext) parseTree.getChild(5)
		//		.getChild(0).getChild(numberChild - 1);
		List<Statement> indicatorsStatements = new ArrayList<Statement>();
		int indicatorCount = 0;
		for (int i = 0; i < operands.getChildCount(); i++) {
			ParseTree child = operands.getChild(i);
			String className = child.getClass().getSimpleName();
			switch (className) {
			case "ResultIndicatorContext":
				Statement indicatorStatement = createIndicatorStatement(child.getChild(0), indicatorCount, leftFactor,
						rightFactor, typeStatement);
				if (indicatorStatement != null) {
					indicatorsStatements.add(indicatorStatement);
				}
				indicatorCount++;
				break;
			default:
				break;
			}
		}
		if (indicatorsStatements.size() > 0) {
			return indicatorsStatements;
		} else {
			return null;
		}
		
	}
	
	public List<Statement> getIndicatorsStatementsByIndicatorList(ParseTree parseTree, Factor leftFactor, Factor rightFactor,
			String typeStatement) {
		ParseTree operands = parseTree;
		List<Statement> indicatorsStatements = new ArrayList<Statement>();
		int indicatorCount = 0;
		for (int i = 0; i < operands.getChildCount(); i++) {
			ParseTree child = operands.getChild(i);
			String className = child.getClass().getSimpleName();
			switch (className) {
			case "ResultIndicatorContext":
				Statement indicatorStatement = createIndicatorStatement(child.getChild(0), indicatorCount, leftFactor,
						rightFactor, typeStatement);
				if (indicatorStatement != null) {
					indicatorsStatements.add(indicatorStatement);
				}
				indicatorCount++;
				break;
			default:
				break;
			}
		}
		if (indicatorsStatements.size() > 0) {
			return indicatorsStatements;
		} else {
			return null;
		}
		
	}
	
	

	public boolean hasExtender(ParseTree parseTree, String extender) {
		String parseTreeClassName = parseTree.getClass().getSimpleName();
		if (!parseTreeClassName.contains("Op_")) {
			try {
				parseTree = parseTree.getChild(5).getChild(0);
			} catch (Exception e) {
				return false;
			}
		}
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("Cs_operationExtenderContext")) {
				return ((Cs_operationExtenderContext) child).getText().toUpperCase().contains(extender);
			}
			i++;
		}
		return false;
	}

	private Statement createIndicatorStatement(ParseTree indicator, int indicatorCount, Factor leftFactor,
			Factor rightFactor, String typeStatement) {
		String indicatorNodeText = indicator.getText();
		if (!indicatorNodeText.contentEquals("  ")) {
			switch (typeStatement) {
			case "AssignStatement":
				AssignStatement assignStatement = new AssignStatement();
				assignStatement.setOperands(leftFactor, rightFactor, indicatorNodeText, indicatorCount);
				return assignStatement;
			case "DefinitionStatement":
				Factor variable = new LiteralFactor("*IN" + indicatorNodeText);
				DefinitionStatement definitionStatement = new DefinitionStatement();
				definitionStatement.setValues(variable, leftFactor, 0);
				return definitionStatement;
			default:
				return null;
			}
		}
		return null;
	}

	// Metodos para análisis de Free Context

	public int getSpecificChildIndex(ParseTree parseTree, String classToSearch) {
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals(classToSearch)) {
				return i;
			}
			i++;
		}
		return 0;
	}

	/**
	 * Método para verificar que el árbol corresponde a un contexto de un opcode en
	 * FreeContext.
	 * 
	 * @param parseTree
	 * @return
	 */
	public boolean parseTreeIsOpcodeContext(ParseTree parseTree) {
		return parseTree.getClass().getSimpleName().contains("Op_")
				|| (parseTree.getChild(0) != null && parseTree.getChild(0).getClass().getSimpleName().contains("Op_"))
				|| (parseTree.getClass().getSimpleName().contains("TerminalNodeImpl")
						&& !parseTree.getText().contentEquals("C"))
				|| parseTree.getClass().getSimpleName().contains("AssignmentExpressionContext");
	}

	/**
	 * Método para determinar el índice en el árbol donde se encuentran los
	 * extenders.
	 * 
	 * @param parseTree
	 * @return
	 */
	public int getFreeContextOpcodeExtendersIndex(ParseTree parseTree) {
		return getSpecificChildIndex(parseTree, "Cs_operationExtenderContext");
	}

	/**
	 * Método para obtener el último elemento como factor en el árbol. En
	 * instrucciones que sólo tienen un elemento (ej. commit boundary), se facilita
	 * la extracción de la variable (ej. boundary).
	 * 
	 * @param parseTree
	 * @return
	 */
	public Factor getLastChildFactor(ParseTree parseTree) {
		return FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(parseTree.getChildCount() - 1));
	}

	/*
	 * Método para obtener los indicadores para los que aplica, por default retorna null
	 * 
	 * @return null Retorna null cuando no hay lista de indicadores, las clases que manejan indicadores
	 * 				le hacen override para retornar la lista
	 */
	public List<Statement> getIndicatorsStatements(){
		return null;
	}

}
