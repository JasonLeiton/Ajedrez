package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;


import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

/**
 * Mapeador de la instrucción ACQ.
 * 
 * El ID del device se encuentra en el factor 1. El nombre del archivo se
 * encuentra en el factor 2.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class AcqStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor deviceID, deviceFilename;

	public AcqStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(Factor deviceID) {
		this.deviceID = deviceID;
	}

	public Factor getDeviceFilename() {
		return deviceFilename;
	}

	public void setDeviceFilename(Factor deviceFilename) {
		this.deviceFilename = deviceFilename;
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
		// Análisis para el caso en que sea FreeContext
		int extenderIndex = getFreeContextOpcodeExtendersIndex(parseTree);

		this.deviceID = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(1 + extenderIndex));
		this.deviceFilename = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(2 + extenderIndex));
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.deviceID = this.getLeftFactor(parseTree);
		this.deviceFilename = this.getFactor(parseTree, "rightFactor");

		this.setControlIndicators(parseTree);
	}

}
