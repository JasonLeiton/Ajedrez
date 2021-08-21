package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.OTypeFactory;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class OType extends Specification {

	public OType(SpecificationEnum specificationType) {
		super(specificationType);
	}

	@Override
	public Statement constructStatement(ParseTree parseTree) {
		Statement otypeStatement = OTypeFactory.buildStatement(parseTree);
		otypeStatement.constructStatement(parseTree);
		return otypeStatement;
	}

	@Override
	public Statement constructSubroutine(ParseTree parseTree) {
		return null;
	}

}
