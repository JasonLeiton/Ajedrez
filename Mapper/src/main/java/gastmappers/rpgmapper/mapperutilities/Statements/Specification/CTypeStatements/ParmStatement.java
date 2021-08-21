package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsPARMContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class ParmStatement extends CTypeStatement {

	private Factor input, output, parameter;

	public ParmStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getInput() {
		return input;
	}

	public void setInput(Factor input) {
		this.input = input;
	}

	public Factor getOutput() {
		return output;
	}

	public void setOutput(Factor output) {
		this.output = output;
	}

	public Factor getParameter() {
		return parameter;
	}

	public void setParameter(Factor parameter) {
		this.parameter = parameter;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		Cspec_fixed_standard_partsContext context = (Cspec_fixed_standard_partsContext) parseTree.getChild(6);
		this.input = getLeftParmFactor(parseTree);
		this.output = getRightParmFactor(context);
		this.parameter = getResultParmFactor(context);

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

	public static List<Statement> searchParms(ParseTree parseTree) {
		List<Statement> parmList = new ArrayList<Statement>();
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("CsPARMContext")) {
				parmList.add(Statement.generateStatement(child, child.getChild(0)));
			}
		}
		return parmList;
	}

	public Factor getLeftParmFactor(ParseTree parseTree) {
		CsPARMContext statement = (CsPARMContext) parseTree;
		String leftFactorContent = statement.factor().getText();
		if (!leftFactorContent.contentEquals("              ")) {
			return new LiteralFactor(leftFactorContent);
		}
		return null;
	}

	public Factor getRightParmFactor(Cspec_fixed_standard_partsContext parseTree) {
		try {
			String value = parseTree.factor2.getText();
			return generateFactor(value);
		} catch (Exception e) {
			return null;
		}
	}

	public Factor getResultParmFactor(Cspec_fixed_standard_partsContext parseTree) {
		try {
			String value = parseTree.result.getText();
			return generateFactor(value);
		} catch (Exception e) {
			return null;
		}
	}

	private Factor generateFactor(String value) {
		if (!value.contentEquals("              ")) {
			return new LiteralFactor(value);
		} else {
			return null;
		}
	}

}
