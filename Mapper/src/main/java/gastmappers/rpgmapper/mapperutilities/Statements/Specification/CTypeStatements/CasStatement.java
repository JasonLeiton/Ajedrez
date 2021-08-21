package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CasestatementContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.DefaultValues.BooleanOperation;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Operand;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

/**
 * Modela la operación Cas.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class CasStatement extends CTypeStatement {

	private Factor leftOperand, rightOperand;
	private Operand operand;
	private String subroutineResultField;
	private String flag;
	private List<Statement> indicatorsStatements;
	private CasStatement next;
	
	public String getFlag() {
		return flag;
	}
	public void setFlag(String f) {
		flag = f;
	}
	
	
	public void setNext(CasStatement n) {
		this.next =  n;
	}
	public CasStatement getNext() {
		return next;
	}

	public CasStatement(Opcode opcode) {
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

	public Operand getOperand() {
		return operand;
	}

	public void setOperand(Operand operand) {
		this.operand = operand;
	}

	public String getSubroutineResultField() {
		return subroutineResultField;
	}

	public void setSubroutineResultField(String subroutineResultField) {
		this.subroutineResultField = subroutineResultField;
	}

	public List<Statement> getIndicatorsStatements() {
		return indicatorsStatements;
	}

	public void setIndicatorsStatements(List<Statement> indicatorsStatements) {
		this.indicatorsStatements = indicatorsStatements;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.next = null;

		CasestatementContext cas =(CasestatementContext)parseTree;

		ArrayList<CasStatement> list = new ArrayList<CasStatement>();
		CasStatement currentCas = null;
		String factor1,factor2,result,op;
		int x;
		for (x = 0; x < cas.getChildCount()-cas.getChildCount()%6;x+=6) {
			ParseTree parseTree4 = cas.getChild(x+4);
			ParseTree parseTree5 = cas.getChild(x+5);
			factor1 = parseTree4.getText();//factor 1

			op = parseTree5.getChild(0).getText();//operacion
			factor2 = parseTree5.getChild(1).getChild(0).getText();//factor2
			result = parseTree5.getChild(1).getChild(1).getText();//resultado
			currentCas = new CasStatement(getOpcode());			
			currentCas.subroutineResultField = (new LiteralFactor(result)).getValue();
			currentCas.setLeftOperand(new LiteralFactor(factor1));
			currentCas.setRightOperand(new LiteralFactor(factor2));
			currentCas.setType("ComparisonBranch");
			currentCas.indicatorsStatements = getIndicatorsStatementsByIndicatorList(parseTree5.getChild(1), this.leftOperand, this.rightOperand,"AssignStatement");
			String booleanop = op.replaceAll(op.substring(0, 2), "");
			String operand = BooleanOperation.getBooleanOperationSymbol("".equals(booleanop)?"EQ":booleanop);
			this.operand = new Operand(operand);
			list.add(currentCas);
			
		}
		currentCas = list.get(0);
		this.leftOperand = currentCas.leftOperand;
		this.rightOperand = currentCas.leftOperand;
		this.subroutineResultField = currentCas.subroutineResultField;
		this.operand = currentCas.operand;
		this.indicatorsStatements = currentCas.indicatorsStatements;
		this.setType(currentCas.getType());
		currentCas = this;
		for (int i = 1;i<list.size();i++) {
			currentCas.setNext(list.get(i));
			currentCas = i+1>=list.size() ? null:list.get(i);
		}
		
	}

}
