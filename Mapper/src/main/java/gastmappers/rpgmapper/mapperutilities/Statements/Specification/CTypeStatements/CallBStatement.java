package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsCALLBContext;
import org.rpgleparser.RpgParser.Cspec_fixed_standard_partsContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class CallBStatement extends CTypeStatement {

	private Factor procedureName;
	private List<Statement> parmStatements;
	private boolean operationalDescriptor;
	private String lr;

	public CallBStatement(Opcode opcode) {
		super(opcode);

	}
	
	public String getLR() {
		return lr;
	}

	public Factor getProcedureName() {
		return procedureName;
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

	public boolean isOperationalDescriptor() {
		return operationalDescriptor;
	}

	public void setOperationalDescriptor(boolean operationalDescriptor) {
		this.operationalDescriptor = operationalDescriptor;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.procedureName = this.getFactor(parseTree, "rightFactor");

		CsCALLBContext callContext = (CsCALLBContext) parseTree.getChild(5).getChild(0);
		this.parmStatements = ParmStatement.searchParms(callContext);

		this.operationalDescriptor = hasExtender(parseTree, "D");
		Cspec_fixed_standard_partsContext stdParts = (Cspec_fixed_standard_partsContext) callContext.getChild(1);
		if (!"  ".equalsIgnoreCase(stdParts.eq.getText()))
			this.lr = "*IN"+stdParts.eq.getText();
		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
