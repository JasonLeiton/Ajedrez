package gastmappers.rpgmapper.mapperutilities.Structure.Statements.Context;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public class ContextParseTree implements ParseTree {

	private List<ParseTree> listElements;

	public ContextParseTree() {

		listElements = new ArrayList<ParseTree>();
	}

	@Override
	public Interval getSourceInterval() {

		return null;
	}

	@Override
	public int getChildCount() {

		return listElements.size();
	}

	@Override
	public Object getPayload() {

		return null;
	}

	@Override
	public String toStringTree() {

		return null;
	}

	@Override
	public <T> T accept(ParseTreeVisitor<? extends T> arg0) {

		return null;
	}

	@Override
	public ParseTree getChild(int arg0) {

		return listElements.get(arg0);
	}

	@Override
	public ParseTree getParent() {

		return null;
	}

	@Override
	public String getText() {

		return null;
	}

	@Override
	public String toStringTree(Parser arg0) {

		return null;
	}

	public void addElement(ParseTree child) {
		this.listElements.add(child);
	}

}
