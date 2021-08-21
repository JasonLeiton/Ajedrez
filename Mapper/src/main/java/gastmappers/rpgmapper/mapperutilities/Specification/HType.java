package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.HTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class HType extends Specification {

	public HType(SpecificationEnum specificationType) {
		super(specificationType);
	}

	@Override
	public Statement constructStatement(ParseTree parseTree) {
		HTypeStatement htypeStatement = new HTypeStatement();
		htypeStatement.constructStatement(parseTree);
		return htypeStatement;
	}

	@Override
	public Statement constructSubroutine(ParseTree parseTree) {

		return null;
	}

}
