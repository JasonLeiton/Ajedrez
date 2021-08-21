package gastmappers.rpgmapper.mapperutilities.ParseTree;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Structure.RPGSyntaxTree;

/**
 * Clase generar el árbol intermedio de RPG.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class TreeParser {

	public RPGSyntaxTree getSyntaxTree(ParseTree treeParse) {
		RPGSyntaxTree rpgTree = new RPGSyntaxTree();
		rpgTree.generateTree(treeParse);
		return rpgTree;
	}

}
