package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class TestStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor result, format;
	private boolean dateFormat, timeFormat, timestampFormat;

	public TestStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getResult() {
		return result;
	}

	public void setResult(Factor result) {
		this.result = result;
	}

	public Factor getFormat() {
		return format;
	}

	public void setFormat(Factor format) {
		this.format = format;
	}

	public boolean getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(boolean dateFormat) {
		this.dateFormat = dateFormat;
	}

	public boolean getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(boolean timeFormat) {
		this.timeFormat = timeFormat;
	}

	public boolean getTimestampFormat() {
		return timestampFormat;
	}

	public void setTimestampFormat(boolean timestampFormat) {
		this.timestampFormat = timestampFormat;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		this.dateFormat = hasExtender(parseTree, "D");
		this.timeFormat = hasExtender(parseTree, "T");
		this.timestampFormat = hasExtender(parseTree, "Z");

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		int identifierIndex = getSpecificChildIndex(parseTree, "IdentifierContext");

		if (identifierIndex + 1 < parseTree.getChildCount()) {
			this.format = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierIndex));
			identifierIndex++;
		}

		this.result = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierIndex));
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.format = getLeftFactor(parseTree);
		this.result = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}
}
