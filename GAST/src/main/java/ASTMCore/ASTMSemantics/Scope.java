package ASTMCore.ASTMSemantics;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DefintionObject;
import ASTMCore.ASTMSyntax.Statement.BlockStatement;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import static ASTMCore.metadata.Tags.SCOPE;

/**
 * Modela el alcance de una estructura del código fuente, por ejemplo un if o while.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class Scope extends GASTMSemanticObject {
	
	public Scope() {
		setTag(SCOPE);
	}
	/**
	 * Conjunto de declaraciones y definiciones.
	 */
	private ArrayList<DefintionObject> declOrDefn;
	/**
	 * Obtiene el conjunto de declaraciones y funciones.
	 * @return Conjunto de declaraciones contenidas en el alcance.
	 */
	public ArrayList<DefintionObject> getDeclOrDefn() {
		if (this.declOrDefn == null) {
			this.declOrDefn = new ArrayList<DefintionObject>();
		}
		return this.declOrDefn;
	}
	/**
	 * Establece las declaraciones y definiciones.
	 * @param declOrDefn Conjunto de declaraciones y definiciones
	 */
	public void setDeclOrDefn(ArrayList<DefintionObject> declOrDefn) {
		this.declOrDefn = declOrDefn;
	}

	public void setChildScope(ArrayList<Scope> childScope) {
		this.childScope = childScope;
	}

	/**
	 * Modela que un alcance puede tener subalcance dentro del mismo.
	 */
	private ArrayList<Scope> childScope;

	public ArrayList<Scope> getChildScope() {
		if (this.childScope == null) {
			this.childScope = new ArrayList<Scope>();
		}
		return this.childScope;
	}
	@Override
	public void accept(GASTVisitor v) {
		// TODO Auto-generated method stub
		
	}

}
