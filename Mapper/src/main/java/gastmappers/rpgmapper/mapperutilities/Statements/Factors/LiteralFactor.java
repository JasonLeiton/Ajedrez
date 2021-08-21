package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

import org.apache.commons.lang3.StringUtils;

/**
 * Factor que es simple. Si sus valores corresponden a una variable, número o
 * texto.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class LiteralFactor implements Factor {

	private String value, type;

	public LiteralFactor(String value, String type) {
		this.value = value;
		this.type = type;
	}

	public LiteralFactor(String value) {
		this.value = value;
		this.type = LiteralFactor.getTypeOfData(value);
	}

	@Override
	public String getValue() {

		return this.value;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public void setType(String type) {

		this.type = type;
	}

	/**
	 * Establecer el tipo de dato que contiene la variable
	 * 
	 * @param factor valor en el nodo
	 * @return tipo de dato en el nodo
	 */
	public static String getTypeOfData(String factor) {
		if (factor.contains("*")) {
			return getTypeOfVariable(factor);
		} else if (factor.contains("X'")) {
			return "Hexadecimal Value";
		} else if (factor.charAt(0) == '\'') {
			return "String";
		} else if (StringUtils.isNumeric(factor)) {
			return "Number";
		} else if (factor.length() >= 3) {
			if (factor.substring(0, 2).contentEquals("D'")) {
				return "Date";
			}
			return "Variable";
		} else {
			try {
				Double.parseDouble(factor);
				return "Number";
			} catch (Exception e) {
				// TODO: handle exception
			}
			return "Variable";
		}
	}

	private static String getTypeOfVariable(String factor) {
		factor = factor.substring(1).toUpperCase();
		switch (factor) {
		case "LOCK":
			return "Constant";
		case "JOBRUN":
		case "JOB":
		case "SYS":
		case "ISO":
		case "USA":
		case "EUR":
		case "JIS":
			return "Date/Time Format";
		case "MDY":
		case "DMY":
		case "YMD":
		case "CYMD":
		case "CMDY":
		case "CDMY":
		case "JUL":
		case "LONGJUL":
			return "Date Format";
		case "HMS":
			return "Time Format";
		default:
			return "Constant";
		}
	}
}
