package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_substContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class SubstFunction extends BuiltInFunction implements Factor {

	private Factor stringText, start, length;

	public SubstFunction(String functionName) {
		super(functionName);
	}

	public Factor getStringText() {
		return stringText;
	}

	public void setStringText(Factor stringText) {
		this.stringText = stringText;
	}

	public Factor getStart() {
		return start;
	}

	public void setStart(Factor start) {
		this.start = start;
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
		Bif_substContext subStringContext = (Bif_substContext) parseTree;
		this.length = FreeContextAnalyzer.getFreeContextFactor(subStringContext.length);
		this.start = FreeContextAnalyzer.getFreeContextFactor(subStringContext.start);
		this.stringText = FreeContextAnalyzer.getFreeContextFactor(subStringContext.string);
	}

}
