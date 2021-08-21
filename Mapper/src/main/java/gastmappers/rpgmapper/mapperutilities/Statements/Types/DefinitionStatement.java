package gastmappers.rpgmapper.mapperutilities.Statements.Types;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;

public class DefinitionStatement extends Statement {

	private Factor variable, value;
	private String symbol;

	public DefinitionStatement() {
	}

	public void setVariable(Factor variable) {
		this.variable = variable;
	}

	public void setValue(Factor value) {
		this.value = value;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
	}

	public Factor getVariable() {
		return variable;
	}

	public Factor getValue() {
		return value;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setValues(Factor variable, Factor value, int symbolIndex) {
		this.value = value;
		this.variable = variable;
		this.symbol = mapSymbol(symbolIndex);
	}

	private String mapSymbol(int symbolIndex) {
		String[] listSymbol = { "=", "<=", ">=" };
		return listSymbol[symbolIndex];
	}

}
