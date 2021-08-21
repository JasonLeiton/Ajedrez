package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_editwContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class EditWFunction extends BuiltInFunction implements Factor {

	private Factor editWord, numeric;

	public EditWFunction(String functionName) {
		super(functionName);
	}

	public Factor getEditWord() {
		return editWord;
	}

	public void setEditWord(Factor editWord) {
		this.editWord = editWord;
	}

	public Factor getNumeric() {
		return numeric;
	}

	public void setNumeric(Factor numeric) {
		this.numeric = numeric;
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

	@Override
	public void generateFactor(ParseTree parseTree) {
		Bif_editwContext editWContext = (Bif_editwContext) parseTree;
		this.editWord = FreeContextAnalyzer.getFreeContextFactor(editWContext.editword);
		this.numeric = FreeContextAnalyzer.getFreeContextFactor(editWContext.numeric);
	}
}
