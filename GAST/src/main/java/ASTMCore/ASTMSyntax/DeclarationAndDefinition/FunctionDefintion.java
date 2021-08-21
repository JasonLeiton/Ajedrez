package ASTMCore.ASTMSyntax.DeclarationAndDefinition;


import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;

import java.util.ArrayList;
import java.util.List;

import ASTMCore.ASTMSemantics.FunctionScope;
import ASTMCore.ASTMSyntax.Statement.Statement;

import static ASTMCore.metadata.Tags.FUNCTION_DEFINITION;

/**
 * Esta clase modela la definici�n de una funci�n.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */

public class FunctionDefintion extends Definition {

	public FunctionDefintion() {
		setTag(FUNCTION_DEFINITION);
	}

	/**
	 * Tipo de retorno
	 */
	private TypeReference returnType;
	/**
	 * Establece el tipo de retorno.
	 * @param value Tipo de retorno.
	 */
	public void setReturnType(TypeReference value) {
		this.returnType = value;
	}
	/**
	 * Obtiene el tipo de retoro.
	 * @return Tipo de retorno.
	 */
	public TypeReference getReturnType() {
		return this.returnType;
	}

	/**
	 * Lista de par�metros de la funci�n.
	 */
	private ArrayList<FormalParameterDefinition> formalParameters;
	/**
	 * Obtiene la lista de par�metros de la funci�n.
	 * @return Lista de par�metros
	 */
	public ArrayList<FormalParameterDefinition> getFormalParameters() {
		if (this.formalParameters == null) {
			this.formalParameters = new ArrayList<FormalParameterDefinition>();
		}
		return this.formalParameters;
	}

	private FunctionMemberAttributes functionMemberAttributes;

	public void setFunctionMemberAttributes(FunctionMemberAttributes value) {
		this.functionMemberAttributes = value;
	}

	public FunctionMemberAttributes getFunctionMemberAttributes() {
		return this.functionMemberAttributes;
	}

	/**
	 * Modela el alcance de una funci�n.
	 */
	private FunctionScope opensScope;
	
	/**
	 * Establece el alcance de una funci�n.
	 * @param value Alcance de una funci�n.
	 */
	public void setOpensScope(FunctionScope value) {
		this.opensScope = value;
	}
	
	/**
	 * Obtiene el alcance de una funci�n.
	 * @return Alcance de una funci�n.
	 */
	public FunctionScope getOpensScope() {
		if (this.opensScope==null)
			this.opensScope=new FunctionScope();
		return this.opensScope;
	}

	/**
	 * Cuerpo de la funci�n.
	 */
	private Statement body;
	
	/**
	 * Establece el cuerpo de la funci�n.
	 * @param value Cuerpo de la funci�n.
	 */
	public void setBody(Statement value) {
		this.body = value;
	}
	
	/**
	 * Obtiene el cuerpo de la funci�n.
	 * @return Cuerpo de la funci�n de la funci�n. 
	 */
	public Statement getBody() {
		return this.body;
	}
	
	/**
	 * Establece la lista de par�metros. 
	 * @param formalParameters Par�metros.
	 */
	public void setFormalParameters(ArrayList<FormalParameterDefinition> formalParameters) {
		this.formalParameters = formalParameters;
	}
	
	/**
	 * Modificadores de la funci�n.
	 */
	private List<Modifiers> modifiers;
	
	/**
	 * Obtiene la lista de par�metros.
	 * @return Modificadores.
	 */
	public List<Modifiers> getModifiers() {
		return modifiers;
	}
	
	/**
	 * Establece los modificadores de la funci�n.
	 * @param modifiers Modificadores.
	 */
	public void setModifiers(List<Modifiers> modifiers) {
		this.modifiers = modifiers;
	}

	private List<Type> exception;

	public List<Type> getException() {
		return exception;
	}

	public void setException(List<Type> exception) {
		this.exception = exception;
	}

	/**
	 * Function's signature
	 */
	private String signature;

	public String getSignature() { return signature; }

	public void setSignature(String signature) { this.signature = signature; }

	private String className;

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	private String packageName;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	private String methodName;

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * Function's basic metrics values
	 */
	private Metrics metrics;

	public Metrics getMetrics() {
		return metrics;
	}

	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}

	public static class Metrics {

		private int    lines_of_code;                    // Function's number of lines-of-code
		private int    cyclomatic_complexity;            // Function's cyclomatic complexity number
		private HalsteadMetrics halstead;                // Function's halstead metrics

		public int getLines_of_code() {
			return lines_of_code;
		}

		public void setLines_of_code(int lines_of_code) {
			this.lines_of_code = lines_of_code;
		}

		public int getCyclomatic_complexity() {
			return cyclomatic_complexity;
		}

		public void setCyclomatic_complexity(int cyclomatic_complexity) {
			this.cyclomatic_complexity = cyclomatic_complexity;
		}

		public HalsteadMetrics getHalstead() {
			return halstead;
		}

		public void setHalstead(HalsteadMetrics halstead) {
			this.halstead = halstead;
		}

		public static class HalsteadMetrics {
			private int numberOfOperands;
			private int numberOfOperators;
			private int numberOfUniqueOperands;
			private int numberOfUniqueOperators;

			public int getNumberOfOperands() {
				return numberOfOperands;
			}

			public void setNumberOfOperands(int numberOfOperands) {
				this.numberOfOperands = numberOfOperands;
			}

			public int getNumberOfOperators() {
				return numberOfOperators;
			}

			public void setNumberOfOperators(int numberOfOperators) {
				this.numberOfOperators = numberOfOperators;
			}

			public int getNumberOfUniqueOperands() {
				return numberOfUniqueOperands;
			}

			public void setNumberOfUniqueOperands(int numberOfUniqueOperands) {
				this.numberOfUniqueOperands = numberOfUniqueOperands;
			}

			public int getNumberOfUniqueOperators() {
				return numberOfUniqueOperators;
			}

			public void setNumberOfUniqueOperators(int numberOfUniqueOperators) {
				this.numberOfUniqueOperators = numberOfUniqueOperators;
			}
		}

	}

	@Override
	public void accept(GASTVisitor v) {
		v.visitFunctionDefintion(this);
	}
}
