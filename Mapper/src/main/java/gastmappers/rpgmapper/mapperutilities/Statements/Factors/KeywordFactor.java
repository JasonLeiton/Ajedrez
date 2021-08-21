package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class KeywordFactor implements Factor {

	private List<Factor> argumentList;
	private String keyword;

	public KeywordFactor(ParseTree parseTree) {
		this.argumentList = createList(parseTree);
		this.keyword = FreeContextAnalyzer.getChild0String(parseTree);
	}

	public List<Factor> getArgumentList() {
		return argumentList;
	}

	public void setArgumentList(List<Factor> argumentList) {
		this.argumentList = argumentList;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	private List<Factor> createList(ParseTree parseTree) {
		List<Factor> argumentList = new ArrayList<Factor>();
		ParseTree newTree = downTraverseTree(parseTree);
		for (int i = 0; i < newTree.getChildCount(); i++) {
			ParseTree child = newTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (!className.contentEquals("TerminalNodeImpl")) {
				argumentList.add(FreeContextAnalyzer.getFreeContextFactor(child));
			}
		}
		return argumentList;

	}

	private ParseTree downTraverseTree(ParseTree parseTree) {
		if (parseTree.getChildCount() > 1) {
			return parseTree;
		} else {
			return parseTree.getChild(0);
		}
	}

	@Override
	public String getValue() {

		return null;
	}

	@Override
	public String getType() {

		return null;
	}

	@Override
	public void setValue(String value) {

	}

	@Override
	public void setType(String type) {

	}

}
