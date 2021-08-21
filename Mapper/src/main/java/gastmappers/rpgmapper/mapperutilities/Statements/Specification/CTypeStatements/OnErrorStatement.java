package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationManager;
import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class OnErrorStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private List<Factor> errorStatusList;
	private GroupStatements onErrorStatements;

	public OnErrorStatement(Opcode opcode) {
		super(opcode);

	}

	public GroupStatements getBody() {
		return onErrorStatements;
	}

	public void setBody(GroupStatements onErrorStatements) {
		this.onErrorStatements = onErrorStatements;
	}
	
	public List<Factor> getErrorStatusList(){
		return this.errorStatusList;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		this.errorStatusList = new ArrayList<Factor>();
		ParseTree errorContext = null;

		if (parseTreeIsOpcodeContext(parseTree.getChild(0))) {
			errorContext = parseTree.getChild(0);
			buildFreeContextStatement(parseTree);
		} else {
			errorContext = parseTree.getChild(5);
			this.setControlIndicators(parseTree);
		}

		for (int i = 0; i < errorContext.getChildCount(); i++) {
			ParseTree child = errorContext.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("OnErrorCodeContext")) {
				this.errorStatusList.add(FreeContextAnalyzer.getFreeContextFactor(child));
			}
		}

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		this.onErrorStatements = new GroupStatements();
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			ParseTree child = parseTree.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("StatementContext")) {
				SpecificationManager statement = new SpecificationManager();
				this.onErrorStatements.addStatement(statement.createStatement(child.getChild(0)));
			}
		}
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		// TODO Auto-generated method stub

	}
}
