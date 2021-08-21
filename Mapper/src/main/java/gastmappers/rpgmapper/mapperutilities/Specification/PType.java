package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.PTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class PType extends Specification {

	public PType(SpecificationEnum specificationType) {
		super(specificationType);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Statement constructStatement(ParseTree parseTree) {
		PTypeStatement ptypeStatement = new PTypeStatement();
		ptypeStatement.constructStatement(parseTree);
		return ptypeStatement;
	}

	@Override
	public Statement constructSubroutine(ParseTree parseTree) {
		// TODO Auto-generated method stub
		return null;
	}

}
