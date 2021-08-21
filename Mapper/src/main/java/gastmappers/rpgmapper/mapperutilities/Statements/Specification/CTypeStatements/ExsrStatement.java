package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;

/**
 * Modela la declaración de un Exsr.
 * 
 * Esta operación tiene la forma:
 * 
 * Label1 Opcode Label2 - EXSR Subrutina
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class ExsrStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private String subroutineLabel;

	public ExsrStatement(Opcode opcode) {
		super(opcode);
	}

	public String getSubroutineLabel() {
		return subroutineLabel;
	}

	public void setSubroutineLabel(String subroutineLabel) {
		this.subroutineLabel = subroutineLabel;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		this.subroutineLabel = getLastChildFactor(parseTree).getValue();
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.subroutineLabel = this.getFactor(parseTree, "rightFactor").getValue();

		this.setControlIndicators(parseTree);
	}

}
