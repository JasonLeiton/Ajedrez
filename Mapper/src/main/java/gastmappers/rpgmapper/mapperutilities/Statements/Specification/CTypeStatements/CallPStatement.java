package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.C_freeContext;
import org.rpgleparser.RpgParser.Cspec_fixed_x2Context;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

/**
 * Mapeador de la instruccion CALLP.
 * 
 * Factor 2 presenta el nombre del procedimiento y los parámetros.
 * 
 * Se pueden tener extensiones M y R.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class CallPStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private String procedureName;
	private List<Factor> params;
	private boolean extenderM, extenderR;

	public CallPStatement(Opcode opcode) {
		super(opcode);
	}

	public String getProcedureName() {
		return procedureName;
	}

	public void setProcedureName(String procedureName) {
		this.procedureName = procedureName;
	}

	public List<Factor> getParams() {
		return params;
	}

	public void setParams(List<Factor> params) {
		this.params = params;
	}

	public boolean isExtenderM() {
		return extenderM;
	}

	public void setExtenderM(boolean extenderM) {
		this.extenderM = extenderM;
	}

	public boolean isExtenderR() {
		return extenderR;
	}

	public void setExtenderR(boolean extenderR) {
		this.extenderR = extenderR;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		// Establecer banderas en caso de que existan extensiones M y/o R
		this.extenderM = hasExtender(parseTree, "M");
		this.extenderR = hasExtender(parseTree, "R");

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		// Obtener indice que tiene el hijo con la información del identificador
		int procedureNameParseTreeIndex = getSpecificChildIndex(parseTree, "IdentifierContext");
		this.procedureName = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(procedureNameParseTreeIndex))
				.getValue();

		// Obtener indice donde inician los parámetros
		int paramsParseTreeIndex = getSpecificChildIndex(parseTree, "ExpressionContext");
		if (paramsParseTreeIndex > 0) {
			this.params = FreeContextAnalyzer.getListElements(parseTree, paramsParseTreeIndex);
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		// Factor 2 tiene la informacion en 'FREE' por lo que hay que obtener la
		// expresion
		C_freeContext callStatement = ((Cspec_fixed_x2Context) parseTree.getChild(5)).fixedexpression;
		ParseTree procedureAndParams = FreeContextAnalyzer.getRightFactorParseTree(callStatement);
		try {
			this.procedureName = FreeContextAnalyzer.getFreeContextFactor(procedureAndParams.getChild(0)).getValue();
			this.params = FreeContextAnalyzer.getListElements(procedureAndParams.getChild(1), 0);

			this.setControlIndicators(parseTree);
		} catch (Exception e) {

		}
	}

}
