package gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements;

import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgParser;

import gastmappers.rpgmapper.mapperutilities.Specification.Statements.CTypeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.Factor;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.LiteralFactor;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.Opcode;

public class XLateStatement extends CTypeStatement {

	private Factor fromValue, toValue, source, startPosition, target;
	private boolean padExtender;

	public XLateStatement(Opcode opcode) {
		super(opcode);
	}

	public Factor getFromValue() {
		return fromValue;
	}

	public void setFromValue(Factor fromValue) {
		this.fromValue = fromValue;
	}

	public Factor getToValue() {
		return toValue;
	}

	public void setToValue(Factor toValue) {
		this.toValue = toValue;
	}

	public Factor getSource() {
		return source;
	}

	public void setSource(Factor source) {
		this.source = source;
	}

	public Factor getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Factor startPosition) {
		this.startPosition = startPosition;
	}

	public Factor getTarget() {
		return target;
	}

	public void setTarget(Factor target) {
		this.target = target;
	}

	public boolean isPadExtender() {
		return padExtender;
	}

	public void setPadExtender(boolean padExtender) {
		this.padExtender = padExtender;
	}

	@Override
	public void constructStatement(ParseTree parseTree) {
		RpgParser.Cspec_fixedContext xlateStatement = (RpgParser.Cspec_fixedContext) parseTree;
		String leftFactorContent = xlateStatement.factor().getText();

		String[] leftFactorElements = leftFactorContent.split(":");

		this.fromValue = new LiteralFactor(leftFactorElements[0]);
		this.toValue = new LiteralFactor(leftFactorElements[1]);

		this.source = this.getFactor(parseTree, "rightFactor");
		this.startPosition = this.getFactorExtender(parseTree);
		this.target = this.getFactor(parseTree, "resultFactor");

		this.padExtender = hasExtender(parseTree, "P");

		this.setControlIndicators(parseTree);
		this.setType("Statement");
	}
}
