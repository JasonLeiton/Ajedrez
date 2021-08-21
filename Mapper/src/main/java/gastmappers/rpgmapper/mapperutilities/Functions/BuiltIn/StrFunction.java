package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_strContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class StrFunction extends BuiltInFunction implements Factor {

	private Factor basePointer, length;

	public StrFunction(String functionName) {
		super(functionName);
	}

	public Factor getBasePointer() {
		return basePointer;
	}

	public void setBasePointer(Factor basePointer) {
		this.basePointer = basePointer;
	}

	public Factor getLength() {
		return length;
	}

	public void setLength(Factor length) {
		this.length = length;
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
		Bif_strContext strContext = (Bif_strContext) parseTree;
		this.basePointer = FreeContextAnalyzer.getFreeContextFactor(strContext.basingpointer);
		this.length = FreeContextAnalyzer.getFreeContextFactor(strContext.maxlength);
	}

}
