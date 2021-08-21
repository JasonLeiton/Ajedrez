package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Modela el código de operación. Va a contener la operación y el valor del
 * opcode.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class Opcode {

	public OpcodeEnum opcodeEnum;
	public String booleanOperation;

	public Opcode(OpcodeEnum opcodeEnum, String extendedOperation) {
		this.opcodeEnum = opcodeEnum;
		this.booleanOperation = extendedOperation;
	}

	public OpcodeEnum getOpcodeEnum() {
		return opcodeEnum;
	}

	public String getExtendedOperation() {
		return booleanOperation;
	}

	/**
	 * Se obtiene el código de operación de los nodos.
	 * 
	 * @param parseTree subárbol con la información
	 * @return código de operación
	 */
	public static Opcode getOpcodefromParseTree(ParseTree parseTree, boolean isRoot) {
		String className = parseTree.getClass().getSimpleName();
		try{
		if (className.contentEquals("BaseExpressionContext")) {
			return getFreeContextOpcode(parseTree.getChild(0));
		} else if (className.contentEquals("BlockContext")
				|| (parseTree.getChild(0).getClass().getSimpleName().contentEquals("TerminalNodeImpl"))
						&& !parseTree.getChild(0).getText().contentEquals("C")) {
			isRoot = false;
		} else if (className.contentEquals("BegsrContext")
				|| (className.contains("Begin") && parseTree.getChild(0).getClass().getSimpleName().contains("Op_"))) {
			parseTree = parseTree.getChild(0);
			isRoot = false;
		}
		} catch (Exception e) {}

		if (isRoot) {
			// Si la entrada es raíz se debe bajar donde se encuentra la información del
			// opcode
			try {
				ParseTree parseTreeCopy = parseTree.getChild(5);
				if (!parseTreeCopy.getClass().getSimpleName().contentEquals("TerminalNodeImpl")) {
					parseTree = parseTreeCopy.getChild(0);
				} else if (parseTreeCopy != null) {
					parseTree = parseTreeCopy;
				}
			} catch (Exception e) {
				try{
					parseTree = parseTree.getChild(0).getChild(5).getChild(0);
				} catch (Exception e1) {
					parseTree = null;
				}

			}
		}
		Opcode opcode = null;
		int i = 0;
		int numberChildren = 0;
		if (parseTree != null) {
			parseTree.getChildCount();

			while (i < numberChildren) {
				ParseTree child = parseTree.getChild(i);
				// Busca el nodo con clase TerminalNodeImpl
				if (child.getClass().getSimpleName().contentEquals("TerminalNodeImpl")) {
					opcode = getOpcodeInformation(child.getText().toUpperCase());
					return opcode;
				}

				i++;
			}

			// Caso especial donde no se puede navegar, sino que se analiza el nodo terminal
			if (parseTree.getClass().getSimpleName().contentEquals("TerminalNodeImpl") && numberChildren == 0) {
				opcode = getOpcodeInformation(parseTree.getText().toUpperCase());
			} else if (opcode == null) {
				// Caso donde se debe ingresar a uno de los nodos
				String classNameChild = parseTree.getChild(0).getClass().getSimpleName();
				boolean flag = classNameChild.contains("Begin");
				opcode = getOpcodefromParseTree(parseTree.getChild(0), flag);
			}
		}
		return opcode;
	}

	/**
	 * Método para obtener el opcode para el caso de declaraciones FREE.
	 * 
	 * @param parseTree
	 * @return
	 */
	public static Opcode getFreeContextOpcode(ParseTree parseTree) {
		if (parseTree == null) {
			return null;
		}

		Opcode opcode = null;

		String className = parseTree.getClass().getSimpleName();
		if (className.contentEquals("OpContext")) {
			String childClassName = parseTree.getChild(0).getClass().getSimpleName();
			String opcodeString = childClassName.replace("Op_", "");
			opcodeString = opcodeString.replace("Context", "");
			opcode = getOpcodeInformation(opcodeString.toUpperCase());
		} else if (className.contentEquals("TerminalNodeImpl")) {
			opcode = getOpcodeInformation(parseTree.getText());
		} else {
			return getFreeContextOpcode(parseTree.getChild(0));
		}

		if (opcode == null) {
			opcode = new Opcode(OpcodeEnum.FREECONTEXT, "");
		}
		return opcode;
	}

	/**
	 * Método para obtener el código de operación con la información del nodo
	 * 
	 * @param opcodeTree Información del nodo con el opcode
	 * @return opcode según el Enum
	 */
	public static Opcode getOpcodeInformation(String opcodeTree) {
		Opcode opcode = null;
		opcodeTree = opcodeTree.replaceAll("-", "");
		List<String> opcodeOptions = new ArrayList<String>();
		int sizeOpcode = opcodeTree.length();
		// Se debe recortar el string para eliminar algunos casos de opcode que tiene
		// elementos de comparación. CASxx o BEQxx.
		opcodeOptions.add(opcodeTree);
		try {
			opcodeOptions.add(opcodeTree.substring(0, sizeOpcode - 2));
			opcodeOptions.add(opcodeTree.substring(0, sizeOpcode - 3));
		} catch (Exception e) {
			// TODO: handle exception
		}
		int i = 0;
		while (i < opcodeOptions.size()) {
			try {
				OpcodeEnum opcodeEnum = OpcodeEnum.valueOf(opcodeOptions.get(i));
				String alternativeOperation = opcodeTree.substring(opcodeEnum.toString().length());
				opcode = new Opcode(opcodeEnum, alternativeOperation);
				break;
			} catch (Exception e) {
				// TODO: handle exception
			}
			i++;
		}

		return opcode;
	}

}
