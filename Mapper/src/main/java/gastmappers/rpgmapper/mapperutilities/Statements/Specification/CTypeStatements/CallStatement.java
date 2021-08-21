package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsCALLContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class CallStatement extends CTypeStatement {

	private Factor procedureName;
	private List<Statement> parmStatements;
	private String lr;

	public CallStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getProcedureName() {
		return procedureName;
	}
	
	public String getLR() {
		return lr;
	}


	public void setProcedureName(Factor procedureName) {
		this.procedureName = procedureName;
	}

	public List<Statement> getParmStatements() {
		return parmStatements;
	}

	public void setParmStatements(List<Statement> parmStatements) {
		this.parmStatements = parmStatements;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.procedureName = this.getFactor(parseTree, "rightFactor");

		CsCALLContext callContext = (CsCALLContext) parseTree.getChild(5).getChild(0);
		this.parmStatements = ParmStatement.searchParms(callContext);
		this.setControlIndicators(parseTree);
		Cspec_fixed_standard_partsContext stdParts = (Cspec_fixed_standard_partsContext) callContext.getChild(1);
		if (!"  ".equalsIgnoreCase(stdParts.eq.getText()))
			this.lr = "*IN"+stdParts.eq.getText();
		this.setType("Statement");
	}

}
