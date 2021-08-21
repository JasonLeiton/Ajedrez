package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_replaceContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ReplaceFunction extends BuiltInFunction implements Factor {

	private Factor replacementString, sourceString, startPosition, replacementLength;

	public ReplaceFunction(String functionName) {
		super(functionName);
	}

	public Factor getReplacementString() {
		return replacementString;
	}

	public void setReplacementString(Factor replacementString) {
		this.replacementString = replacementString;
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

	public Factor getReplacementLength() {
		return replacementLength;
	}

	public void setReplacementLength(Factor replacementLength) {
		this.replacementLength = replacementLength;
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
		Bif_replaceContext replaceContext = (Bif_replaceContext) parseTree;

		this.replacementString = FreeContextAnalyzer.getFreeContextFactor(replaceContext.replacement);
		this.sourceString = FreeContextAnalyzer.getFreeContextFactor(replaceContext.source);
		this.startPosition = FreeContextAnalyzer.getFreeContextFactor(replaceContext.start);
		this.replacementLength = FreeContextAnalyzer.getFreeContextFactor(replaceContext.length);
	}
}
