package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationManager;
import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.OpcodeEnum;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;

public class SelectStatement extends CTypeStatement {

	private List<WhenStatement> cases;
	private GroupStatements defaultCase;

	public SelectStatement(Opcode opcode) {
		super(opcode);

	}

	public List<WhenStatement> getCases() {
		return cases;
	}

	public void setCases(List<WhenStatement> cases) {
		this.cases = cases;
	}

	public GroupStatements getDefaultCase() {
		return defaultCase;
	}

	public void setDefaultCase(GroupStatements defaultCase) {
		this.defaultCase = defaultCase;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		cases = new ArrayList<WhenStatement>();

		boolean freeContextFlag = parseTreeIsOpcodeContext(parseTree.getChild(0).getChild(0));

		for (int i = 1; i < parseTree.getChildCount() - 1; i++) {
			ParseTree child = parseTree.getChild(i);

			if (freeContextFlag) {
				child = child.getChild(0);
			}

			int indexOther = getOtherOpcodeIndex(child, freeContextFlag);
			WhenStatement caseStatemnt = new WhenStatement(null);
			caseStatemnt.createWhenStatement(child, indexOther);
			cases.add(caseStatemnt);
			// Caso donde existe un Other
			if (indexOther < child.getChildCount()) {
				indexOther++;
				this.defaultCase = new GroupStatements();
				for (int j = indexOther; j < child.getChildCount(); j++) {
					ParseTree grandChild = child.getChild(j);
					SpecificationManager statement = new SpecificationManager();
					this.defaultCase.addStatement(statement.createStatement(grandChild.getChild(0)));
				}
			}
		}

		if (!freeContextFlag) {
			this.setControlIndicators(parseTree);
		}

		this.setType("Statement");
	}

	private int getOtherOpcodeIndex(ParseTree parseTree, boolean freeContextFlag) {
		int index = 1;

		while (index < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(index);
			if (!child.getClass().getSimpleName().contentEquals("TerminalNodeImpl")) {
				if (freeContextFlag) {
					child = child.getChild(0);
				}
				Opcode opcode = Opcode.getOpcodefromParseTree(child.getChild(0), true);
				try {
					if (opcode.getOpcodeEnum().equals(OpcodeEnum.OTHER)) {
						break;
					}
				} catch (Exception e) {
					//e.printStackTrace();
				}
			}

			index++;
		}
		return index;
	}

}
