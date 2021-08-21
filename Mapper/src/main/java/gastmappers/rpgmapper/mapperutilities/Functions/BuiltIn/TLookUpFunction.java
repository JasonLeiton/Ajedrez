package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_tlookupargsContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class TLookUpFunction extends BuiltInFunction implements Factor {

	private Factor searchValue, tableName, altTableValue;
	private String extender;

	public TLookUpFunction(String functionName) {
		super(functionName);
		this.extender = functionName.replace("TLOOKUP", "");
	}

	public Factor getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(Factor searchValue) {
		this.searchValue = searchValue;
	}

	public Factor getTableName() {
		return tableName;
	}

	public void setTableName(Factor tableName) {
		this.tableName = tableName;
	}

	public Factor getAltTableValue() {
		return altTableValue;
	}

	public void setAltTableValue(Factor altTableValue) {
		this.altTableValue = altTableValue;
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
		Bif_tlookupargsContext tlookContext = (Bif_tlookupargsContext) parseTree;
		this.searchValue = FreeContextAnalyzer.getFreeContextFactor(tlookContext.arg);
		this.tableName = FreeContextAnalyzer.getFreeContextFactor(tlookContext.searchtable);
		this.altTableValue = FreeContextAnalyzer.getFreeContextFactor(tlookContext.alttable);
	}

}
