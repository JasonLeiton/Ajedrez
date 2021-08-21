package VisitorValidator;

import org.antlr.v4.runtime.tree.ParseTree;

import ASTMCore.GASTMObject;

public class Difference {

	private ParseTree NodeAstm;
	private GASTMObject NodeGast;
	private ParseTree newNode;
	private String difference;
	private String focus;
	
	public Difference(ParseTree oldNode, ParseTree newNode, String expected, String got,String focus) {
		setNodeAstm(oldNode);
		setNodeNew(newNode);
		setDifference(expected,got);
		setFocus(focus);
	}
	
	public Difference(ParseTree oldNode, GASTMObject gastNode, String expected, String got,String focus) {
		setNodeAstm(oldNode);
		setNodeGast(gastNode);
		setDifference(expected,got);
		setFocus(focus);
	}
	
	public ParseTree getNodeAstm() {
		return NodeAstm;
	}
	public void setNodeAstm(ParseTree nodeAstm) {
		NodeAstm = nodeAstm;
	}
	
	public ParseTree getNodeNew() {
		return newNode;
	}
	public void setNodeNew(ParseTree nodeGast) {
		newNode = nodeGast;
	}
	
	public GASTMObject getNodeGast() {
		return NodeGast;
	}
	public void setNodeGast(GASTMObject nodeGast) {
		NodeGast = nodeGast;
	}
	
	public String getDifference() {
		return difference;
	}
	public void setDifference(String expected, String got) {
		this.difference = "Expected: " + expected + ", Got: " + got;
	}

	public String getFocus() {
		return focus;
	}

	public void setFocus(String focus) {
		this.focus = focus;
	}
	
	
	
}
