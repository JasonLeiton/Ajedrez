package VisitorValidator;

import java.util.List;

public class DictionaryElement {

	private String ASTM;
	private List<String> AST;
	
	public DictionaryElement() {
		
	}
	
	public List<String> getAST() {
		return AST;
	}
	public void setAST(List<String> aST) {
		AST = aST;
	}
	public String getASTM() {
		return ASTM;
	}
	public void setASTM(String aSTM) {
		ASTM = aSTM;
	}
	
	
}
