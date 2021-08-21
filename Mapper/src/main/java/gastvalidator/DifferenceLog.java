/**
 * 
 */
package gastvalidator;

import java.util.ArrayList;

/**
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 *
 */
public class DifferenceLog {
	private String filePath;
	private ArrayList<Long> idNodes;
	private int differenceType; // 0: Valores distintos, 1: Valores únicos en ASTM.
	private String astmValue;
	private String astValue;

	/**
	 * Constructor de un mensaje con una diferencia.
	 * 
	 * @param urlFile        dirección del archivo .java que se analizó.
	 * @param idNode         nodo donde se encontró una diferencia.
	 * @param differenceType tipo de diferencia. 0: valores distintos entre nodos.
	 *                       1: nodo único en ASTM.
	 * @param astmValue      valor encontrado en el ASTM.
	 * @param astValue       valor encontrado en el AST.
	 */
	public DifferenceLog(String urlFile, long idNode, int differenceType, String astmValue, String astValue) {
		this.filePath = urlFile;
		this.idNodes = new ArrayList<Long>();
		this.idNodes.add(idNode);
		this.differenceType = differenceType;
		this.astmValue = astmValue;
		this.astValue = astValue;
	}

	/**
	 * Genera el mensaje para enviar al documento de salida.
	 * 
	 * @return mensaje detallando la diferencia.
	 */
	public String printMessage() {
		String text = "";
		String nodesQuantity = (this.idNodes.size() == 1) ? "en el nodo " : "en los nodos ";
		switch (this.differenceType) {
		case 0:
			text += this.filePath + " valores distintos en un nodo del ASTM. Valor en AST: '" + this.astValue
					+ "'. Valor en ASTM: '" + this.astmValue + "' " + nodesQuantity + printIdNodes();
			break;
		case 1:
			text += this.filePath + " valor no encontrado en AST. Valor en ASTM: '" + this.astmValue + "' "
					+ nodesQuantity + printIdNodes();
			break;
		default:
			break;
		}
		return text;
	}

	/**
	 * Genera los nodos separados por comas y el último nodo se indica con un 'y',
	 * para que sea más agradable de leer.
	 * 
	 * @return lista de nodos ordenados.
	 */
	private String printIdNodes() {
		String nodes = "";
		for (int i = 0; i < this.idNodes.size(); i++) {
			nodes += this.idNodes.get(i);
			if (i + 2 == this.idNodes.size()) {
				nodes += " y ";
			} else {
				nodes += ", ";
			}
		}
		nodes = nodes.substring(0, nodes.length() - 2);
		nodes += ".";
		return nodes;
	}

	/**
	 * Añade un id de un nodo a la lista.
	 * 
	 * @param idNode nodo donde se encuentra una diferencia.
	 */
	public void addIdNode(long idNode) {
		this.idNodes.add(idNode);
	}

	/**
	 * Retorna dirección del archivo analizado.
	 * 
	 * @return ubicación del archivo analizado.
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * Retorna tipo de diferencia.
	 * 
	 * @return tipo de diferencia.
	 */
	public int getDifferenceType() {
		return this.differenceType;
	}

	/**
	 * Retorna el valor encontrado en el ASTM.
	 * 
	 * @return valor encontrado en el ASTM.
	 */
	public String getASTMValue() {
		return this.astmValue;
	}
}
