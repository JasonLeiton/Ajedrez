package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser.CsKLISTContext;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.OpcodeEnum;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

public class KlistStatement extends CTypeStatement {

	private Factor keyListName;
	private List<Statement> listKeyField;

	public KlistStatement(Opcode opcode) {
		super(opcode);

	}

	public Factor getKeyListName() {
		return keyListName;
	}

	public void setKeyListName(Factor keyListName) {
		this.keyListName = keyListName;
	}

	public List<Statement> getListKeyField() {
		return listKeyField;
	}

	public void setListKeyField(List<Statement> listKeyField) {
		this.listKeyField = listKeyField;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {

		CsKLISTContext klistContext = (CsKLISTContext) parseTree.getChild(5).getChild(0);
		this.keyListName = getLeftFactor(parseTree);
		this.listKeyField = new ArrayList<Statement>();
		for (int i = 0; i < klistContext.getChildCount(); i++) {
			ParseTree child = klistContext.getChild(i);
			String className = child.getClass().getSimpleName();
			if (className.contentEquals("CsKFLDContext")) {
				KfldStatement kfldStatement = new KfldStatement(new Opcode(OpcodeEnum.KFLD, ""));
				kfldStatement.constructStatement(child.getChild(6));
				listKeyField.add(kfldStatement);
			}
		}

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}

}
