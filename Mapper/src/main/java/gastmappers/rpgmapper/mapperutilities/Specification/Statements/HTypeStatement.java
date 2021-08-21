package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class HTypeStatement extends Statement {

	private List<Factor> listKeywords;

	public HTypeStatement() {
	}

	public List<Factor> getListKeywords() {
		return listKeywords;
	}

	public void setListKeywords(List<Factor> listKeywords) {
		this.listKeywords = listKeywords;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.listKeywords = this.getKeywordList(parseTree);
	}

}
