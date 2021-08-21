package gastmappers.rpgmapper.mapperutilities.Statements.Types;

import org.antlr.v4.runtime.tree.ParseTree;

public interface FreeAndColumnarStatement {

	public abstract void buildFreeContextStatement(ParseTree parseTree);

	public abstract void buildColumnarStatement(ParseTree parseTree);
}
