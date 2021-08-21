package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_xlateContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class XLateFunction extends BuiltInFunction implements Factor {

	private Factor fromPattern, toPattern, baseValue, startPosition;

	public XLateFunction(String functionName) {
		super(functionName);
	}

	public Factor getFromPattern() {
		return fromPattern;
	}

	public void setFromPattern(Factor fromPattern) {
		this.fromPattern = fromPattern;
	}

	public Factor getToPattern() {
		return toPattern;
	}

	public void setToPattern(Factor toPattern) {
		this.toPattern = toPattern;
	}

	public Factor getBaseValue() {
		return baseValue;
	}

	public void setBaseValue(Factor baseValue) {
		this.baseValue = baseValue;
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
		Bif_xlateContext xlateContext = (Bif_xlateContext) parseTree;
		this.fromPattern = FreeContextAnalyzer.getFreeContextFactor(xlateContext.from);
		this.toPattern = FreeContextAnalyzer.getFreeContextFactor(xlateContext.to);
		this.startPosition = FreeContextAnalyzer.getFreeContextFactor(xlateContext.startpos);
		this.baseValue = FreeContextAnalyzer.getFreeContextFactor(xlateContext.string);
	}

}
