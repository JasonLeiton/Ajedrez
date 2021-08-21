package gastmappers.rpgmapper.mapperutilities.SourceCodeRetriever;

import java.util.BitSet;
import java.util.List;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;

/**
 * Obtiene el árbol haciendo uso de rpgParser.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class ErrorListener implements ANTLRErrorListener {

	private final List<String> errors;
	@SuppressWarnings("unused")
	private final RpgLexer lexer;
	private final RpgParser parser;

	public ErrorListener(List<String> errors, RpgLexer lexer, RpgParser parser) {
		this.errors = errors;
		this.lexer = lexer;
		this.parser = parser;
	}

	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		if (recognizer instanceof RpgParser) {
			errors.add("Line: " + line + " " + msg + parser.getDFAStrings());
		} else if (recognizer instanceof RpgLexer) {
			errors.add("Line: " + line + " " + msg);
		}
	}

	public void reportContextSensitivity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, int prediction,
			ATNConfigSet configs) {
	}

	public void reportAttemptingFullContext(Parser recognizer, DFA dfa, int startIndex, int stopIndex,
			BitSet conflictingAlts, ATNConfigSet configs) {
	}

	public void reportAmbiguity(Parser recognizer, DFA dfa, int startIndex, int stopIndex, boolean exact,
			BitSet ambigAlts, ATNConfigSet configs) {
	}

}
