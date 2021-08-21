package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArrayFactor implements Factor {

	private String value;
	private Factor index;

	public ArrayFactor(String factorContent) {
		this.value = getArrayName(factorContent);
		this.index = getIndexValue(factorContent);
		this.setType("Array");
	}

	@Override
	public String getValue() {

		return this.value;
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

	public void setIndex(Factor index) {
		this.index = index;
	}

	public Factor getIndex() {
		return this.index;
	}

	public Factor getArrayNameFactor() {
		return new LiteralFactor(this.value);
	}

	/**
	 * Método para encontrar si el factor es un arreglo. Para ello, revisa si está
	 * de la forma nombre(argumento)
	 * 
	 * @param factorContent
	 * @return
	 */
	public static boolean isArray(String factorContent) {
		String pattern = "(\\w*)\\((.*?)\\)";
		return Pattern.matches(pattern, factorContent);
	}

	private String getArrayName(String content) {
		Pattern regex = Pattern.compile("(\\w*[^(\\()])");
		Matcher regexMatcher = regex.matcher(content);
		if (regexMatcher.find()) {
			return regexMatcher.group();
		}
		return "";
	}

	private Factor getIndexValue(String content) {
		Pattern regex = Pattern.compile("\\((.*?)\\)");
		Matcher regexMatcher = regex.matcher(content);
		if (regexMatcher.find()) {
			String value = regexMatcher.group(1);
			return new LiteralFactor(value);
		}
		return null;
	}

}
