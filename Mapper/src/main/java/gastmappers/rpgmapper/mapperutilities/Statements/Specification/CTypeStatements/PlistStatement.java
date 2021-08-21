package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsPLISTContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class PlistStatement extends CTypeStatement {

	private Factor listName;
	private List<Statement> parmStatements;

	public PlistStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getListName() {
		return listName;
	}

	public void setListName(Factor listName) {
		this.listName = listName;
	}

	public List<Statement> getParmStatements() {
		return parmStatements;
	}

	public void setParmStatements(List<Statement> parmStatements) {
		this.parmStatements = parmStatements;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.listName = getLeftFactor(parseTree);

		CsPLISTContext plistContext = (CsPLISTContext) parseTree.getChild(5).getChild(0);
		this.parmStatements = ParmStatement.searchParms(plistContext);

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
