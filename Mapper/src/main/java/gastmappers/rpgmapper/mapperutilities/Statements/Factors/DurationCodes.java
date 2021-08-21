package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

public class DurationCodes {

	public static String getDurationCode(String code) {
		code = code.substring(1).toUpperCase();
		switch (code) {

		case "Y":
		case "YEARS":
			return "Years";

		case "M":
		case "MONTHS":
			return "Months";

		case "D":
		case "DAYS":
			return "Days";

		case "H":
		case "HOURS":
			return "Hours";

		case "MN":
		case "MINUTES":
			return "Minutes";

		case "S":
		case "SECONDS":
			return "Seconds";

		case "MS":
		case "MSECONDS":
			return "Miliseconds";

		default:
			return code;
		}
	}
}
