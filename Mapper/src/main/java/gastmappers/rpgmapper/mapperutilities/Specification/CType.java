package gastmappers.rpgmapper.mapperutilities.Specification;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatementFactory;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.OpcodeEnum;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

/**
 * Clase para describir las hojas C.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class CType extends Specification {

	/**
	 * Constructor donde el parámetro es la hoja C.
	 * 
	 */
	public CType(SpecificationEnum specificationType) {
		super(specificationType);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * rpg.Specification.Specification#constructStatement(org.antlr.v4.runtime.tree.
	 * ParseTree)
	 */
	@Override
	public Statement constructStatement(ParseTree parseTree) {
		String className = parseTree.getChild(0).getClass().getSimpleName();
		ParseTree parseTreeOpcode = parseTree;
		// Para el caso del 'for' se debe bajar un nivel para obtener el opcode. Sino es
		// nulo.
		if (className.contentEquals("BeginforContext") || className.contains("CsDOW") || className.contains("CsDOU")
				|| className.contains("BeginselectContext") || className.contains("BegindoContext")
				|| className.contentEquals("BaseExpressionContext") || className.contentEquals("BegsrContext") || className.contentEquals("BeginProcedureContext")
				|| className.contentEquals("BeginmonitorContext") || className.contentEquals("Op_on_errorContext")) {
				
			parseTreeOpcode = parseTree.getChild(0);

		} else if (className.contentEquals("BeginifContext")) {

			parseTreeOpcode = parseTree.getChild(0);
			if (parseTreeOpcode.getChildCount() == 1) {
				parseTreeOpcode = parseTreeOpcode.getChild(0);
			}
		} 
		Opcode opcode = Opcode.getOpcodefromParseTree(parseTreeOpcode, true);
		// Se genera el Statement de acuerdo al opcode.
		Statement statement = CTypeStatementFactory.buildStatement(opcode);
		
		if (statement != null) {

			if (FreeContextAnalyzer.parseTreeIsFreeContext(parseTree)) {
				parseTree = FreeContextAnalyzer.getOpcodeElements(parseTree.getChild(0));
			}
			
			statement.constructStatement(parseTree);
		}
		return statement;
	}

	@Override
	public Statement constructSubroutine(ParseTree parseTree) {

		Opcode opcode = new Opcode(OpcodeEnum.BEGSR, "");
		Statement statement = CTypeStatementFactory.buildStatement(opcode);
		statement.constructStatement(parseTree);
		return statement;
	}
}
