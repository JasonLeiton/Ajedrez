package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.ITypeFactory;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class IType extends Specification {

	public IType(SpecificationEnum specificationType) {
		super(specificationType);

	}

	@Override
	public Statement constructStatement(ParseTree parseTree) {
		try {
			Statement itypeStatement = ITypeFactory.buildStatement(parseTree);
			itypeStatement.constructStatement(parseTree);
			return itypeStatement;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public Statement constructSubroutine(ParseTree parseTree) {

		return null;
	}

}
