package gastmappers.rpgmapper.mapperutilities.Statements.DefaultValues;

/**
 * Mapea una operación a su símbolo.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class BooleanOperation {

	/**
	 * Obtiene el símbolo según la operación.
	 * 
	 * @param operation Operación en la declaración.
	 * @return Símbolo de acuerdo a la operación.
	 */
	public static String getBooleanOperationSymbol(String operation) {
		switch (operation) {
		case "EQ":
			return "==";
		case "GE":
			return ">=";
		case "GT":
			return ">";
		case "LE":
			return "<=";
		case "LT":
			return "<";
		case "NE":
			return "!=";
		default:
			break;
		}
		return "";
	}

}
