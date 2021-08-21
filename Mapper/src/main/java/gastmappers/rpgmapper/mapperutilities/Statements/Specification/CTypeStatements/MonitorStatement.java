package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class MonitorStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private GroupStatements monitorBody;

	public MonitorStatement(Opcode opcode) {
		super(opcode);
	}

	public GroupStatements getMonitorBody() {
		return monitorBody;
	}

	public void setMonitorBody(GroupStatements monitorBody) {
		this.monitorBody = monitorBody;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		this.monitorBody = new GroupStatements();

		if (!parseTreeIsOpcodeContext(parseTree.getChild(0).getChild(0))) {
			// Se determinar que el árbol NO es Free
			this.setControlIndicators(parseTree);
		}

		// El límite es -1 porque el último elemento será el END
		int j = 1;
		while (j < parseTree.getChildCount() - 1) {
			ParseTree child = parseTree.getChild(j);
			this.monitorBody.addStatement(Statement.generateStatement(child, child.getChild(0)));
			j++;
		}

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		// TODO Auto-generated method stub

	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		// TODO Auto-generated method stub

	}

}
