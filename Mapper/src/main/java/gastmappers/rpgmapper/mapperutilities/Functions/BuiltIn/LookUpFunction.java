package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_lookupargsContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class LookUpFunction extends BuiltInFunction implements Factor {

	private Factor arg, array, numberElements, startIndex;
	private String extender;

	public LookUpFunction(String functionName) {
		super(functionName);
		this.extender = functionName.replace("LOOKUP", "");
	}

	public Factor getArg() {
		return arg;
	}

	public void setArg(Factor arg) {
		this.arg = arg;
	}

	public Factor getArray() {
		return array;
	}

	public void setArray(Factor array) {
		this.array = array;
	}

	public Factor getNumberElements() {
		return numberElements;
	}

	public void setNumberElements(Factor numberElements) {
		this.numberElements = numberElements;
	}

	public Factor getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Factor startIndex) {
		this.startIndex = startIndex;
	}

	public String getExtender() {
		return extender;
	}

	public void setExtender(String extender) {
		this.extender = extender;
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
		Bif_lookupargsContext lookUpArgs = (Bif_lookupargsContext) parseTree.getChild(1);
		this.arg = FreeContextAnalyzer.getFreeContextFactor(lookUpArgs.arg);
		this.array = FreeContextAnalyzer.getFreeContextFactor(lookUpArgs.array);
		this.numberElements = FreeContextAnalyzer.getFreeContextFactor(lookUpArgs.numberelements);
		this.startIndex = FreeContextAnalyzer.getFreeContextFactor(lookUpArgs.startindex);
	}

}
