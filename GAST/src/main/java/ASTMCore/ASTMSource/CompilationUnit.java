package ASTMCore.ASTMSource;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DefintionObject;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.ImportDeclaration;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.NameSpaceDefinition;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.List;

import ASTMCore.ASTMSemantics.ProgramScope;

import static ASTMCore.metadata.Tags.COMPILATION_UNIT;

/**
 * Esta clase modela cada archivo de código, con su respectivas características.
 * 
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class CompilationUnit extends SourceFile {

	public CompilationUnit() {
		setTag(COMPILATION_UNIT);
	}

	private String language;

	public void setLanguage(String value) {
		this.language = value;
	}

	public String getLanguage() {
		return this.language;
	}

	/**
	 * Define el objeto de paquete.
	 */
	private NameSpaceDefinition gPackage;

	/**
	 * 
	 * @return Se obtiene el objeto paquete del GAST.
	 */
	public NameSpaceDefinition getgPackage() {
		return gPackage;
	}

	/**
	 * Se establece el paquete al que pertenece el archivo.
	 * 
	 * @param gPackage Paquete a establecer.
	 */
	public void setgPackage(NameSpaceDefinition gPackage) {
		this.gPackage = gPackage;
	}

	/**
	 * Fragmentos de código.
	 */
	private ArrayList<DefintionObject> fragments;

	public ArrayList<DefintionObject> getFragments() {
		if (this.fragments == null) {
			this.fragments = new ArrayList<DefintionObject>();
		}
		return this.fragments;
	}

	/**
	 * Alcance del archivo. Se refiere al conjuto de declaraciones y definiciones.
	 */
	private ProgramScope opensScope;

	/**
	 * Establece el alcance.
	 * 
	 * @param value Alcance.
	 */
	public void setOpensScope(ProgramScope value) {
		this.opensScope = value;
	}

	/**
	 * Se obtiene el alcance del archivo
	 * 
	 * @return Se obtiene el acance del archivo
	 */
	public ProgramScope getOpensScope() {
		return this.opensScope;
	}

	public void setFragments(ArrayList<DefintionObject> fragments) {
		this.fragments = fragments;
	}

	/**
	 * Lista de importaciones que tiene un archivo.
	 */
	private List<ImportDeclaration> imports;

	/**
	 * Se obtiene las importaciones de un archivo.
	 * 
	 * @return Lista de importaciones de un archivo.
	 */
	public List<ImportDeclaration> getImports() {
		return imports;
	}

	/**
	 * Establece el conjunto de importaciones de un archivo.
	 * 
	 * @param imports Conjunto de importaciones de un archivo.
	 */
	public void setImports(List<ImportDeclaration> imports) {
		this.imports = imports;
	}
	
	public void accept(GASTVisitor v) {
		v.visitCompilationUnit(this);
	}

}
