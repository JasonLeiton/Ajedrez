package gastmappers.rpgmapper.mapperutilities.SourceCodeRetriever;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenSource;
import org.antlr.v4.runtime.Vocabulary;
import org.antlr.v4.runtime.tree.ParseTree;
import org.rpgleparser.RpgLexer;
import org.rpgleparser.RpgParser;
import org.rpgleparser.RpgParserListener;
import org.rpgleparser.RpgParserVisitor;
import org.rpgleparser.tokens.PreprocessTokenSource;
import org.rpgleparser.utils.FixedWidthBufferedReader;

//import gastmappers.rpgmapper.rpg2GASTMCertifier.Rpg2GastmVerifier;

/**
 * Encargado de obtener el AST de RPG.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class RPGCodeRetriever {

	static Vocabulary vocabulary;

	public ParseTree getParserFromFilePath(String path) throws IOException {
		String file = loadFileByPath(path);
		List<String> errors = new LinkedList<String>();
		RpgParser parseTree = initialiseParser(file, errors);
		for (String err : errors) {
			System.out.println(err);
		}
		return parseTree.r();
	}

	public String loadFileByPath(String path) throws IOException {
		return loadFile(new File(path));
	}

	public static String loadFile(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		byte[] b = new byte[inputStream.available()];
		inputStream.read(b);
		inputStream.close();
		return new String(b);
	}

	public static RpgParser initialiseParser(String inputString, List<String> errors) throws IOException {
		final ANTLRInputStream input = new ANTLRInputStream(new FixedWidthBufferedReader(inputString));
		final RpgLexer rpglexer = new RpgLexer(input);
		final TokenSource lexer = new PreprocessTokenSource(rpglexer);
		final CommonTokenStream tokens = new CommonTokenStream(lexer);

		final RpgParser parser = new RpgParser(tokens);

		final ErrorListener errorListener = new ErrorListener(errors, rpglexer, parser);
		rpglexer.addErrorListener(errorListener);
		parser.addErrorListener(errorListener);

		return parser;
	}
	
	public RpgParserListener getVisitor(String path) {
		try {
			String file;
			file = loadFileByPath(path);
			List<String> errors = new LinkedList<String>();
			final ANTLRInputStream input = new ANTLRInputStream(new FixedWidthBufferedReader(file));
			final RpgLexer rpglexer = new RpgLexer(input);
			final TokenSource lexer = new PreprocessTokenSource(rpglexer);
			final CommonTokenStream tokens = new CommonTokenStream(lexer);
			final RpgParser parser = new RpgParser(tokens);
			//parser.addParseListener(new Rpg2GastmVerifier());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

		return null;
	}

}
