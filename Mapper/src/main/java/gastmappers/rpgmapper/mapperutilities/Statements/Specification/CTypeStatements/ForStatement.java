package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsFORContext;

import gastmappers.rpgmapper.mapperutilities.Specification.SpecificationManager;
import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.AssignStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.DefinitionStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements;
import gastmappers.rpgmapper.mapperutilities.Structure.Statements.Context.StatementContextBuilder;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class ForStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private GroupStatements body;
	private DefinitionStatement indexStart, indexLimit;
	private AssignStatement incrementNumber;
	private boolean increment = true; // True es que el sentido es sumando. Falso si se debe restar para llegar al
	// valor

	public ForStatement(Opcode opcode) {
		super(opcode);

	}

	public GroupStatements getBody() {
		return body;
	}

	public void setBody(GroupStatements body) {
		this.body = body;
	}

	public DefinitionStatement getIndexStart() {
		return indexStart;
	}

	public void setIndexStart(DefinitionStatement indexStart) {
		this.indexStart = indexStart;
	}

	public DefinitionStatement getIndexLimit() {
		return indexLimit;
	}

	public void setIndexLimit(DefinitionStatement indexLimit) {
		this.indexLimit = indexLimit;
	}

	public AssignStatement getIncrementNumber() {
		return incrementNumber;
	}

	public void setIncrementNumber(AssignStatement incrementNumber) {
		this.incrementNumber = incrementNumber;
	}

	public boolean isIncrement() {
		return increment;
	}

	public void setIncrement(boolean increment) {
		this.increment = increment;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree.getChild(0).getChild(0))) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.body = new GroupStatements();
		for (int j = 1; j < parseTree.getChildCount() - 1; j++) {
			ParseTree child = parseTree.getChild(j);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("ContextParseTree")) {
				this.body.addStatement(new StatementContextBuilder().createStatement(child));
			} else {
				this.body.addStatement(new SpecificationManager().createStatement(child.getChild(0)));
			}
		}

		this.setType("Statement");
	}

	private boolean isTerminalNode(ParseTree parseTree) {
		return parseTree.getClass().getSimpleName().contentEquals("TerminalNodeImpl");
	}

	private DefinitionStatement getIndexStart(ParseTree parseTree) {
		Factor leftSide = null;
		Factor rightSide = null;
		for (int i = 0; i < parseTree.getChildCount(); i++) {
			String nodeValue = getTerminalNodeValue(parseTree.getChild(i));
			switch (i) {
			case 0: // Caso variable
				leftSide = new LiteralFactor(nodeValue);
			case 2:
				rightSide = new LiteralFactor(nodeValue);
				break;
			default:
				break;
			}
		}
		DefinitionStatement index = new DefinitionStatement();
		index.setValues(leftSide, rightSide, 0);
		return index;
	}

	private DefinitionStatement getIndexLimit(ParseTree parseTree, Factor indexFor) {
		String nodeValue = getTerminalNodeValue(parseTree);
		Factor limitIndex = new LiteralFactor(nodeValue);
		DefinitionStatement indexLimit = new DefinitionStatement();

		int symbolIncrement = 1;
		if (!this.increment) {
			symbolIncrement = 2;
			this.increment = false;
		}

		indexLimit.setValues(indexFor, limitIndex, symbolIncrement);
		return indexLimit;
	}

	private AssignStatement getStepIncrement(ParseTree parseTree, Factor indexFor) {
		int indexSymbol = 4; // Tomado del arreglo de simbolos
		if (!this.increment) {
			indexSymbol = 5;
		}

		String nodeValue = getTerminalNodeValue(parseTree);
		Factor stepIncrement = new LiteralFactor(nodeValue);

		AssignStatement assignIncrement = new AssignStatement();
		assignIncrement.setOperands(indexFor, stepIncrement, indexFor.getValue(), indexSymbol);
		return assignIncrement;
	}

	private String getTerminalNodeValue(ParseTree parseTree) {
		if (isTerminalNode(parseTree)) {
			return parseTree.getText();
		} else {
			return getTerminalNodeValue(parseTree.getChild(0));
		}
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		ParseTree forConditions = FreeContextAnalyzer.getOpcodeElements(parseTree.getChild(0).getChild(0));
		setForVariables(forConditions);
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		CsFORContext forStatement = (CsFORContext) parseTree.getChild(0).getChild(5);
		setForVariables(forStatement);

		this.setControlIndicators(parseTree.getChild(0));
	}

	private void setForVariables(ParseTree parseTree) {
		int i = 0;
		while (i < parseTree.getChildCount()) {
			ParseTree child = parseTree.getChild(i);
			if (isTerminalNode(child)) {
				String nodeText = child.getText().toUpperCase();
				i++;
				switch (nodeText) {
				case "FOR":
					this.indexStart = getIndexStart(parseTree.getChild(i));
					break;
				case "DOWNTO":
					this.increment = false;
				case "TO":
					this.indexLimit = getIndexLimit(parseTree.getChild(i), this.indexStart.getVariable());
					break;
				case "BY":
					this.incrementNumber = getStepIncrement(parseTree.getChild(i), this.indexStart.getVariable());
					break;
				default:
					break;
				}
			}
			i++;
		}
	}
}
