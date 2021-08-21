package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_equalContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class EqualFunction extends BuiltInFunction implements Factor {

	private Factor fileName;

	public EqualFunction(String functionName) {
		super(functionName);
	}

	public Factor getFileName() {
		return fileName;
	}

	public void setFileName(Factor fileName) {
		this.fileName = fileName;
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
		Bif_equalContext equalContext = (Bif_equalContext) parseTree;
		this.fileName = FreeContextAnalyzer.getFreeContextFactor(equalContext.filenameident);
	}

}
