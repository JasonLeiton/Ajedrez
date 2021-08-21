package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

/**
 * Modela un operador, por lo que se puede obtener el String con el símbolo.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class Operand {

	private String symbol;

	public Operand(String symbol) {

		this.symbol = getMetaSymbol(symbol);
	}

	public String getSymbol() {
		return this.symbol;
	}

	public static String getMetaSymbol(String operation) {
		switch (operation.toUpperCase()) {
		case "OR":
			return "||";
		case "AND":
			return "&&";
		case "<>":
			return "!=";
		default:
			return operation;
		}
	}
}
