package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_eofContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class EofFunction extends BuiltInFunction implements Factor {

	private Factor fileName;

	public EofFunction(String functionName) {
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
		Bif_eofContext eofContext = (Bif_eofContext) parseTree;
		if(eofContext.filenameident != null) {
			this.fileName = FreeContextAnalyzer.getFreeContextFactor(eofContext.filenameident);
		}
	}

}
