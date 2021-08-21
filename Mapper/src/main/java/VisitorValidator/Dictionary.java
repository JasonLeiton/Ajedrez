package VisitorValidator;

import java.util.List;

public class Dictionary {
	
	private String language;
	private List<DictionaryElement> dictionary;
	
	
	public Dictionary() {
	
	}

	public List<DictionaryElement> getDictionary() {
		return dictionary;
	}

	public void setDictionary(List<DictionaryElement> dictionary) {
		this.dictionary = dictionary;
	}
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	
}
