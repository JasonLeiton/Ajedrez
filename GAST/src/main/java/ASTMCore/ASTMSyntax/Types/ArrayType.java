package ASTMCore.ASTMSyntax.Types;


import java.util.ArrayList;
import java.util.List;

import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.visitor.GASTVisitor;

import static ASTMCore.metadata.Tags.ARRAY_TYPE;

/**
 * Esta clase modela el tipo vectoy.
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */
public class ArrayType extends ConstructedType {

	public ArrayType() {
		setTag(ARRAY_TYPE);
	}

	/**
	 * Dimensiones.
	 */
	private List<Expression> dimension;
	/**
	 * Obtiene las dimensiones.
	 * @return Las dimensiones.
	 */
	public List<Expression> getDimension() {
		if (this.dimension == null) {
			this.dimension = new ArrayList<Expression>();
		}
		return this.dimension;
	}
	/**
	 * Establece las dimensiones.
	 * @param ranks Las dimensiones.
	 */
	public void setDimension(List<Expression> ranks) {
		this.dimension = ranks;
	}
	/**
	 * cantidad de dimensiones que poesee el areglo.
	 */
	private int amountDimension;
	/**
	 * Obtiene la cantidad de dimensiones.
	 * @return Cantidad de dimensiones.
	 */
	public int getAmountDimension() {
		return amountDimension;
	}
	/**
	 * Establece la cantidad de dimensiones del arreglo.
	 * @param amountDimension Cantidad de dimensiones.
	 */
	public void setAmountDimension(int amountDimension) {
		this.amountDimension = amountDimension;
	}
	

	@Override
	public void accept(GASTVisitor v) {
		v.visitArrayType(this);
	}

}
