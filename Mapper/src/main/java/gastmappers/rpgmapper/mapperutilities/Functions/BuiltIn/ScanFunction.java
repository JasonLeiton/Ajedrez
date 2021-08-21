package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_scanContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ScanFunction extends BuiltInFunction implements Factor {

	private Factor searchArgument, sourceString, startPosition;

	public ScanFunction(String functionName) {
		super(functionName);
	}

	public Factor getSearchArgument() {
		return searchArgument;
	}

	public void setSearchArgument(Factor searchArgument) {
		this.searchArgument = searchArgument;
	}

	public Factor getSourceString() {
		return sourceString;
	}

	public void setSourceString(Factor sourceString) {
		this.sourceString = sourceString;
	}

	public Factor getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Factor startPosition) {
		this.startPosition = startPosition;
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
		Bif_scanContext scanContext = (Bif_scanContext) parseTree;
		this.searchArgument = FreeContextAnalyzer.getFreeContextFactor(scanContext.searcharg);
		this.sourceString = FreeContextAnalyzer.getFreeContextFactor(scanContext.source);
		this.startPosition = FreeContextAnalyzer.getFreeContextFactor(scanContext.start);
	}

}
