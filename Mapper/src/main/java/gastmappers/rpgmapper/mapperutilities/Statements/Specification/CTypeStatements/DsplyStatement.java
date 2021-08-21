package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class DsplyStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor messageText, messageQueue, replyVariable;

	public DsplyStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getMessageText() {
		return messageText;
	}

	public void setMessageText(Factor messageText) {
		this.messageText = messageText;
	}

	public Factor getMessageQueue() {
		return messageQueue;
	}

	public void setMessageQueue(Factor messageQueue) {
		this.messageQueue = messageQueue;
	}

	public Factor getReplyVariable() {
		return replyVariable;
	}

	public void setReplyVariable(Factor replyVariable) {
		this.replyVariable = replyVariable;
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

		for (int i = 1; i < parseTree.getChildCount(); i++) {
			int normalizedIndex = i + extenderIndex;
			Factor childFactor = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(normalizedIndex));
			switch (i) {
			case 1:
				this.messageText = childFactor;
				break;
			case 2:
				this.messageQueue = childFactor;
				break;
			case 3:
				this.replyVariable = childFactor;
				break;
			default:
				break;
			}
		}

	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.messageText = this.getLeftFactor(parseTree);

		this.messageQueue = this.getFactor(parseTree, "rightFactor");
		this.replyVariable = this.getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}

}
