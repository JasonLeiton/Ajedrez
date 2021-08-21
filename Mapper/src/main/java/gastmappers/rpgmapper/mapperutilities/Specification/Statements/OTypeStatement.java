package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public abstract class OTypeStatement extends Statement {

	protected String outputDescriptionType;

	@Override
	public void constructStatement(ParseTree parseTree) {
	}

	protected List<String> addControllingIndicators(ParseTree parseTree, int beginIndex, int endIndex) {
		List<String> controllingIndicators = new ArrayList<String>();
		for (int i = beginIndex; i <= endIndex; i++) {
			String indicator = getControllingIndicators(parseTree.getChild(i));
			if (!indicator.contentEquals("")) {
				controllingIndicators.add(indicator);
			}
		}
		return controllingIndicators;
	}

	protected String getControllingIndicators(ParseTree parseTree) {
		String indicator = "";
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			Factor column = getFactorColumn(parseTree, i);
			if (column != null) {
				indicator += column.getValue();
			}
		}
		return indicator;
	}

}
