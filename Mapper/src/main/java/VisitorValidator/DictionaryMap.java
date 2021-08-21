package VisitorValidator;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class DictionaryMap {

	
	private String language;
	private Dictionary dictionary;
	private final String DIRECTORY_LOCATION = "./src/main/java/gastvalidator/dictionaries/";
	private HashMap<String,List<String>> map;
	
	public DictionaryMap(String targetLanguage){
		this.setLanguage(targetLanguage);
		fillFromFile(targetLanguage);
		setMap();
	}
	
	private void fillFromFile(String targetLanguaje) {
		String url = DIRECTORY_LOCATION + "dictionary_" + targetLanguaje + ".json";
		Gson gson = new Gson();
		try (Reader reader = new FileReader(url)) {
			Object v = gson.fromJson(reader, Dictionary.class);
			setDictionary((Dictionary) v);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	public HashMap<String,List<String>> getMap() {
		return map;
	}

	public void setMap() {
		this.map = new HashMap<String, List<String>>();
		for (DictionaryElement element : this.dictionary.getDictionary()) {
			map.put(element.getASTM(), element.getAST());
		}
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
