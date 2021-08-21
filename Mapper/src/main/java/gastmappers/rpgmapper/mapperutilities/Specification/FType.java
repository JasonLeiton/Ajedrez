package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.FTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class FType extends Specification {

	public FType(SpecificationEnum specificationType) {
		super(specificationType);

	}

	@Override
	public Statement constructStatement(ParseTree parseTree) {
		FTypeStatement ftypeStatement = new FTypeStatement();
		ftypeStatement.constructStatement(parseTree);
		return ftypeStatement;
	}

	@Override
	public Statement constructSubroutine(ParseTree parseTree) {

		return null;
	}

}
