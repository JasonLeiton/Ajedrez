package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.FreeAndColumnarStatement;
import gastmappers.rpgmapper.mapperutilities.freecontext.FreeContextAnalyzer;

public class PostStatement extends CTypeStatement implements FreeAndColumnarStatement {

	private Factor workstnID, workstnFile, fileInfo;

	public PostStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getWorkstnID() {
		return workstnID;
	}

	public void setWorkstnID(Factor workstnID) {
		this.workstnID = workstnID;
	}

	public Factor getWorkstnFile() {
		return workstnFile;
	}

	public void setWorkstnFile(Factor workstnFile) {
		this.workstnFile = workstnFile;
	}

	public Factor getFileInfo() {
		return fileInfo;
	}

	public void setFileInfo(Factor fileInfo) {
		this.fileInfo = fileInfo;
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
		int identifierIndex = getSpecificChildIndex(parseTree, "IdentifierContext");

		if (identifierIndex + 1 < parseTree.getChildCount()) {
			this.workstnID = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierIndex));
			identifierIndex++;
		}
		this.workstnFile = FreeContextAnalyzer.getFreeContextFactor(parseTree.getChild(identifierIndex));
	}

	@Override
	public void buildColumnarStatement(ParseTree parseTree) {
		this.workstnID = getLeftFactor(parseTree);
		this.workstnFile = getFactor(parseTree, "rightFactor");
		this.fileInfo = getFactor(parseTree, "resultFactor");

		this.setControlIndicators(parseTree);
	}

}