package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class SetOffStatement extends CTypeStatement {

	private List<Statement> definitionIndicators;

	public SetOffStatement(Opcode opcode) {
		super(opcode);

	}

	public List<Statement> getDefinitionIndicators() {
		return definitionIndicators;
	}

	public void setDefinitionIndicators(List<Statement> definitionIndicators) {
		this.definitionIndicators = definitionIndicators;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		Factor zeroValue = new LiteralFactor("0");
		this.definitionIndicators = getIndicatorsStatements(parseTree, zeroValue, null, "DefinitionStatement");

		this.setControlIndicators(parseTree);
		this.setType("Statement");

	}

}
