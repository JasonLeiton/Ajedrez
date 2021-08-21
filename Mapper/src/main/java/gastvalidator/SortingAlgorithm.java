package gastvalidator;

import java.util.Comparator;

/**
 * Algoritmo comparador para ordenar los nodos según el id.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class SortingAlgorithm implements Comparator<Object> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Object o1, Object o2) {
		long id1 = 0;
		long id2 = 0;
		if (o1 != null) {
			id1 = ((ASTMCore.GASTMObject) o1).getId();
		}

		if (o2 != null) {
			id2 = ((ASTMCore.GASTMObject) o2).getId();
		}
		return (int) (id1 - id2);
	}

}
