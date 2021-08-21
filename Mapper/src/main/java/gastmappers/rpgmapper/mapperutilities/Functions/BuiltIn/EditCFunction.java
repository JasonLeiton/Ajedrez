package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.Bif_editcContext;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory.BuiltInFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class EditCFunction extends BuiltInFunction implements Factor {

	private Factor expression, editCode, constantParam;

	public EditCFunction(String functionName) {
		super(functionName);
	}

	public Factor getExpression() {
		return expression;
	}

	public void setExpression(Factor expression) {
		this.expression = expression;
	}

	public Factor getEditCode() {
		return editCode;
	}

	public void setEditCode(Factor editCode) {
		this.editCode = editCode;
	}

	public Factor getConstantParam() {
		return constantParam;
	}

	public void setConstantParam(Factor constantParam) {
		this.constantParam = constantParam;
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
		Bif_editcContext editCContext = (Bif_editcContext) parseTree;
		this.expression = FreeContextAnalyzer.getFreeContextFactor(editCContext.numeric);
		this.editCode = FreeContextAnalyzer.getFreeContextFactor(editCContext.editcode);
		this.constantParam = FreeContextAnalyzer.getFreeContextFactor(editCContext.currency);
	}

}
