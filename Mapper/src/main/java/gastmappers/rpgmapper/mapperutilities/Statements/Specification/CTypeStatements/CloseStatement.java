package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;

public class CloseStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private String fileName;
	private boolean allFiles;

	public CloseStatement(Opcode opcode) {
		super(opcode);

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public boolean isAllFiles() {
		return allFiles;
	}

	public void setAllFiles(boolean allFiles) {
		this.allFiles = allFiles;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		if (parseTreeIsOpcodeContext(parseTree)) {
			buildFreeContextStatement(parseTree);
		} else {
			buildColumnarStatement(parseTree);
		}

		if (this.fileName.toUpperCase().contentEquals("*ALL")) {
			this.allFiles = true;
			this.fileName = null;
		}

		this.setType("Statement");
	}

	@Override
	public void buildFreeContextStatement(ParseTree parseTree) {
		this.fileName = getLastChildFactor(parseTree).getValue();
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.fileName = this.getFactor(parseTree, "rightFactor").getValue();

		this.setControlIndicators(parseTree);
	}

}
