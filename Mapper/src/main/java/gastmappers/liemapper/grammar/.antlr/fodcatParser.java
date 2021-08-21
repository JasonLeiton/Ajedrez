// Generated from /home/alecastrillo/TEC/Asistencia/Mapper/src/main/java/gastmappers/liemapper/grammar/fodcat.g4 by ANTLR 4.7.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class fodcatParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, PLUS=8, MINUS=9, 
		MULTIPLICATION=10, DIVISION=11, POWER=12, MODULE=13, ASSIGN_OP=14, PI=15, 
		EULER=16, GREATER=17, GREATEREQ=18, LESS=19, LESSEQ=20, EQUAL=21, DIFFERENT=22, 
		OR=23, AND=24, NOT=25, COMMA=26, COLON=27, WHILE=28, REPEAT=29, DO=30, 
		TIMES=31, BREAK=32, CONTINUE=33, IF=34, THEN=35, ELSE=36, FUNCTION_DEC=37, 
		MAIN_NAME=38, END=39, RETURN=40, PRINT=41, BUTTON=42, LED=43, PWMLED=44, 
		SERVO=45, SLEEP=46, PRESS=47, ON=48, OFF=49, BLINK=50, VALUE=51, MIN=52, 
		MID=53, MAX=54, ANGLE=55, IDENTIFIER=56, INTEGER=57, STRING=58, WS=59, 
		COMMENT=60, LINE_COMMENT=61;
	public static final int
		RULE_program = 0, RULE_main_function = 1, RULE_command = 2, RULE_ifstat = 3, 
		RULE_elsestat = 4, RULE_func_call = 5, RULE_flow_exp = 6, RULE_assignment = 7, 
		RULE_gpio = 8, RULE_sleep = 9, RULE_expression = 10, RULE_arithmetic_exp = 11, 
		RULE_relational_exp = 12, RULE_logic_exp = 13, RULE_array_call = 14, RULE_function_declaration = 15, 
		RULE_array_declaration = 16, RULE_gpio_declaration = 17, RULE_parameters_declaration = 18, 
		RULE_parameters_called = 19, RULE_int_literal = 20, RULE_bool_literal = 21, 
		RULE_float_literal = 22, RULE_strg = 23, RULE_constant = 24, RULE_relational_op = 25, 
		RULE_logic_op = 26, RULE_arithmetic_op = 27, RULE_print_op = 28, RULE_sign = 29, 
		RULE_func_button = 30, RULE_func_led = 31, RULE_func_servo = 32, RULE_identifier = 33;
	public static final String[] ruleNames = {
		"program", "main_function", "command", "ifstat", "elsestat", "func_call", 
		"flow_exp", "assignment", "gpio", "sleep", "expression", "arithmetic_exp", 
		"relational_exp", "logic_exp", "array_call", "function_declaration", "array_declaration", 
		"gpio_declaration", "parameters_declaration", "parameters_called", "int_literal", 
		"bool_literal", "float_literal", "strg", "constant", "relational_op", 
		"logic_op", "arithmetic_op", "print_op", "sign", "func_button", "func_led", 
		"func_servo", "identifier"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'('", "')'", "'.'", "'['", "']'", "'verdadero'", "'falso'", "'+'", 
		"'-'", "'*'", "'/'", "'**'", "'mod'", "':='", "'pi'", "'euler'", "'>'", 
		"'>='", "'<'", "'<='", null, null, null, null, null, "','", "':'", "'mientras'", 
		"'repetir'", "'haga'", "'veces'", "'finciclo'", "'continuar'", "'si'", 
		"'entonces'", "'sino'", "'para'", "'principal'", "'fin'", "'devolver'", 
		"'imprimir'", "'boton'", "'led'", "'regulable'", "'servo'", "'esperar'", 
		"'presionado'", "'encendido'", "'apagado'", "'parpadear'", "'brillo'", 
		"'minimo'", "'medio'", "'maximo'", "'angulo'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, "PLUS", "MINUS", "MULTIPLICATION", 
		"DIVISION", "POWER", "MODULE", "ASSIGN_OP", "PI", "EULER", "GREATER", 
		"GREATEREQ", "LESS", "LESSEQ", "EQUAL", "DIFFERENT", "OR", "AND", "NOT", 
		"COMMA", "COLON", "WHILE", "REPEAT", "DO", "TIMES", "BREAK", "CONTINUE", 
		"IF", "THEN", "ELSE", "FUNCTION_DEC", "MAIN_NAME", "END", "RETURN", "PRINT", 
		"BUTTON", "LED", "PWMLED", "SERVO", "SLEEP", "PRESS", "ON", "OFF", "BLINK", 
		"VALUE", "MIN", "MID", "MAX", "ANGLE", "IDENTIFIER", "INTEGER", "STRING", 
		"WS", "COMMENT", "LINE_COMMENT"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "fodcat.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public fodcatParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public Main_functionContext main_function() {
			return getRuleContext(Main_functionContext.class,0);
		}
		public List<AssignmentContext> assignment() {
			return getRuleContexts(AssignmentContext.class);
		}
		public AssignmentContext assignment(int i) {
			return getRuleContext(AssignmentContext.class,i);
		}
		public List<Gpio_declarationContext> gpio_declaration() {
			return getRuleContexts(Gpio_declarationContext.class);
		}
		public Gpio_declarationContext gpio_declaration(int i) {
			return getRuleContext(Gpio_declarationContext.class,i);
		}
		public List<Array_declarationContext> array_declaration() {
			return getRuleContexts(Array_declarationContext.class);
		}
		public Array_declarationContext array_declaration(int i) {
			return getRuleContext(Array_declarationContext.class,i);
		}
		public List<Function_declarationContext> function_declaration() {
			return getRuleContexts(Function_declarationContext.class);
		}
		public Function_declarationContext function_declaration(int i) {
			return getRuleContext(Function_declarationContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					setState(72);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
					case 1:
						{
						setState(68);
						assignment();
						}
						break;
					case 2:
						{
						setState(69);
						gpio_declaration();
						}
						break;
					case 3:
						{
						setState(70);
						array_declaration();
						}
						break;
					case 4:
						{
						setState(71);
						function_declaration();
						}
						break;
					}
					} 
				}
				setState(76);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			setState(77);
			main_function();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Main_functionContext extends ParserRuleContext {
		public TerminalNode FUNCTION_DEC() { return getToken(fodcatParser.FUNCTION_DEC, 0); }
		public TerminalNode MAIN_NAME() { return getToken(fodcatParser.MAIN_NAME, 0); }
		public TerminalNode END() { return getToken(fodcatParser.END, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public Main_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_main_function; }
	}

	public final Main_functionContext main_function() throws RecognitionException {
		Main_functionContext _localctx = new Main_functionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_main_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			match(FUNCTION_DEC);
			setState(80);
			match(MAIN_NAME);
			setState(81);
			match(T__0);
			setState(82);
			match(T__1);
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHILE) | (1L << REPEAT) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << RETURN) | (1L << PRINT) | (1L << SLEEP) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(83);
				command();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(89);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CommandContext extends ParserRuleContext {
		public IfstatContext ifstat() {
			return getRuleContext(IfstatContext.class,0);
		}
		public TerminalNode END() { return getToken(fodcatParser.END, 0); }
		public List<ElsestatContext> elsestat() {
			return getRuleContexts(ElsestatContext.class);
		}
		public ElsestatContext elsestat(int i) {
			return getRuleContext(ElsestatContext.class,i);
		}
		public TerminalNode WHILE() { return getToken(fodcatParser.WHILE, 0); }
		public TerminalNode DO() { return getToken(fodcatParser.DO, 0); }
		public Logic_expContext logic_exp() {
			return getRuleContext(Logic_expContext.class,0);
		}
		public GpioContext gpio() {
			return getRuleContext(GpioContext.class,0);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public TerminalNode REPEAT() { return getToken(fodcatParser.REPEAT, 0); }
		public TerminalNode TIMES() { return getToken(fodcatParser.TIMES, 0); }
		public Int_literalContext int_literal() {
			return getRuleContext(Int_literalContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Arithmetic_expContext arithmetic_exp() {
			return getRuleContext(Arithmetic_expContext.class,0);
		}
		public TerminalNode PRINT() { return getToken(fodcatParser.PRINT, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<Print_opContext> print_op() {
			return getRuleContexts(Print_opContext.class);
		}
		public Print_opContext print_op(int i) {
			return getRuleContext(Print_opContext.class,i);
		}
		public TerminalNode RETURN() { return getToken(fodcatParser.RETURN, 0); }
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Flow_expContext flow_exp() {
			return getRuleContext(Flow_expContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Array_declarationContext array_declaration() {
			return getRuleContext(Array_declarationContext.class,0);
		}
		public SleepContext sleep() {
			return getRuleContext(SleepContext.class,0);
		}
		public CommandContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_command; }
	}

	public final CommandContext command() throws RecognitionException {
		CommandContext _localctx = new CommandContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_command);
		int _la;
		try {
			setState(157);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				ifstat();
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ELSE) {
					{
					setState(93); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(92);
						elsestat();
						}
						}
						setState(95); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==ELSE );
					}
				}

				setState(99);
				match(END);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(101);
				match(WHILE);
				setState(102);
				match(T__0);
				setState(105);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
				case 1:
					{
					setState(103);
					logic_exp(0);
					}
					break;
				case 2:
					{
					setState(104);
					gpio();
					}
					break;
				}
				setState(107);
				match(T__1);
				setState(108);
				match(DO);
				setState(112);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHILE) | (1L << REPEAT) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << RETURN) | (1L << PRINT) | (1L << SLEEP) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(109);
					command();
					}
					}
					setState(114);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(115);
				match(END);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(117);
				match(REPEAT);
				setState(118);
				match(T__0);
				setState(122);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
				case 1:
					{
					setState(119);
					int_literal();
					}
					break;
				case 2:
					{
					setState(120);
					identifier();
					}
					break;
				case 3:
					{
					setState(121);
					arithmetic_exp(0);
					}
					break;
				}
				setState(124);
				match(T__1);
				setState(125);
				match(TIMES);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHILE) | (1L << REPEAT) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << RETURN) | (1L << PRINT) | (1L << SLEEP) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(126);
					command();
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132);
				match(END);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(134);
				match(PRINT);
				setState(135);
				match(T__0);
				setState(136);
				expression();
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==PLUS) {
					{
					{
					setState(137);
					print_op();
					setState(138);
					expression();
					}
					}
					setState(144);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(145);
				match(T__1);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(147);
				match(RETURN);
				setState(149);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(148);
					expression();
					}
					break;
				}
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(151);
				func_call();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(152);
				flow_exp();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(153);
				assignment();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(154);
				array_declaration();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(155);
				gpio();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(156);
				sleep();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfstatContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(fodcatParser.IF, 0); }
		public TerminalNode THEN() { return getToken(fodcatParser.THEN, 0); }
		public Logic_expContext logic_exp() {
			return getRuleContext(Logic_expContext.class,0);
		}
		public GpioContext gpio() {
			return getRuleContext(GpioContext.class,0);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public IfstatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstat; }
	}

	public final IfstatContext ifstat() throws RecognitionException {
		IfstatContext _localctx = new IfstatContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifstat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(IF);
			setState(160);
			match(T__0);
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				{
				setState(161);
				logic_exp(0);
				}
				break;
			case 2:
				{
				setState(162);
				gpio();
				}
				break;
			}
			setState(165);
			match(T__1);
			setState(166);
			match(THEN);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHILE) | (1L << REPEAT) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << RETURN) | (1L << PRINT) | (1L << SLEEP) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(167);
				command();
				}
				}
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElsestatContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(fodcatParser.ELSE, 0); }
		public IfstatContext ifstat() {
			return getRuleContext(IfstatContext.class,0);
		}
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public ElsestatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elsestat; }
	}

	public final ElsestatContext elsestat() throws RecognitionException {
		ElsestatContext _localctx = new ElsestatContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_elsestat);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(ELSE);
			setState(181);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				{
				setState(174);
				ifstat();
				}
				break;
			case 2:
				{
				setState(178);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHILE) | (1L << REPEAT) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << RETURN) | (1L << PRINT) | (1L << SLEEP) | (1L << IDENTIFIER))) != 0)) {
					{
					{
					setState(175);
					command();
					}
					}
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_callContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Parameters_calledContext parameters_called() {
			return getRuleContext(Parameters_calledContext.class,0);
		}
		public Func_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_call; }
	}

	public final Func_callContext func_call() throws RecognitionException {
		Func_callContext _localctx = new Func_callContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_func_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			identifier();
			setState(184);
			match(T__0);
			setState(185);
			parameters_called();
			setState(186);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Flow_expContext extends ParserRuleContext {
		public TerminalNode BREAK() { return getToken(fodcatParser.BREAK, 0); }
		public TerminalNode CONTINUE() { return getToken(fodcatParser.CONTINUE, 0); }
		public Flow_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_flow_exp; }
	}

	public final Flow_expContext flow_exp() throws RecognitionException {
		Flow_expContext _localctx = new Flow_expContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_flow_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			_la = _input.LA(1);
			if ( !(_la==BREAK || _la==CONTINUE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode ASSIGN_OP() { return getToken(fodcatParser.ASSIGN_OP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Array_callContext array_call() {
			return getRuleContext(Array_callContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(192);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				setState(190);
				identifier();
				}
				break;
			case 2:
				{
				setState(191);
				array_call();
				}
				break;
			}
			setState(194);
			match(ASSIGN_OP);
			setState(195);
			expression();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GpioContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Func_buttonContext func_button() {
			return getRuleContext(Func_buttonContext.class,0);
		}
		public Func_ledContext func_led() {
			return getRuleContext(Func_ledContext.class,0);
		}
		public Func_servoContext func_servo() {
			return getRuleContext(Func_servoContext.class,0);
		}
		public TerminalNode ASSIGN_OP() { return getToken(fodcatParser.ASSIGN_OP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GpioContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gpio; }
	}

	public final GpioContext gpio() throws RecognitionException {
		GpioContext _localctx = new GpioContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_gpio);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(197);
			identifier();
			setState(198);
			match(T__2);
			setState(202);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PRESS:
				{
				setState(199);
				func_button();
				}
				break;
			case ON:
			case OFF:
			case BLINK:
			case VALUE:
				{
				setState(200);
				func_led();
				}
				break;
			case MIN:
			case MID:
			case MAX:
			case ANGLE:
				{
				setState(201);
				func_servo();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(208);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				{
				setState(204);
				match(T__0);
				setState(205);
				match(T__1);
				}
				}
				break;
			case ASSIGN_OP:
				{
				setState(206);
				match(ASSIGN_OP);
				setState(207);
				expression();
				}
				break;
			case T__1:
			case WHILE:
			case REPEAT:
			case BREAK:
			case CONTINUE:
			case IF:
			case ELSE:
			case END:
			case RETURN:
			case PRINT:
			case SLEEP:
			case IDENTIFIER:
				break;
			default:
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SleepContext extends ParserRuleContext {
		public TerminalNode SLEEP() { return getToken(fodcatParser.SLEEP, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public SleepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sleep; }
	}

	public final SleepContext sleep() throws RecognitionException {
		SleepContext _localctx = new SleepContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_sleep);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(SLEEP);
			setState(211);
			match(T__0);
			setState(212);
			expression();
			setState(213);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Array_callContext array_call() {
			return getRuleContext(Array_callContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public StrgContext strg() {
			return getRuleContext(StrgContext.class,0);
		}
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Arithmetic_expContext arithmetic_exp() {
			return getRuleContext(Arithmetic_expContext.class,0);
		}
		public Logic_expContext logic_exp() {
			return getRuleContext(Logic_expContext.class,0);
		}
		public Relational_expContext relational_exp() {
			return getRuleContext(Relational_expContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expression);
		try {
			setState(223);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(215);
				identifier();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				array_call();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(217);
				constant();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(218);
				strg();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(219);
				func_call();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(220);
				arithmetic_exp(0);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(221);
				logic_exp(0);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(222);
				relational_exp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arithmetic_expContext extends ParserRuleContext {
		public List<Arithmetic_expContext> arithmetic_exp() {
			return getRuleContexts(Arithmetic_expContext.class);
		}
		public Arithmetic_expContext arithmetic_exp(int i) {
			return getRuleContext(Arithmetic_expContext.class,i);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public Int_literalContext int_literal() {
			return getRuleContext(Int_literalContext.class,0);
		}
		public Float_literalContext float_literal() {
			return getRuleContext(Float_literalContext.class,0);
		}
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Array_callContext array_call() {
			return getRuleContext(Array_callContext.class,0);
		}
		public Arithmetic_opContext arithmetic_op() {
			return getRuleContext(Arithmetic_opContext.class,0);
		}
		public Arithmetic_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_exp; }
	}

	public final Arithmetic_expContext arithmetic_exp() throws RecognitionException {
		return arithmetic_exp(0);
	}

	private Arithmetic_expContext arithmetic_exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Arithmetic_expContext _localctx = new Arithmetic_expContext(_ctx, _parentState);
		Arithmetic_expContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_arithmetic_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(226);
				sign();
				}
			}

			setState(240);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case PI:
			case EULER:
			case IDENTIFIER:
			case INTEGER:
				{
				setState(234);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
				case 1:
					{
					setState(229);
					int_literal();
					}
					break;
				case 2:
					{
					setState(230);
					float_literal();
					}
					break;
				case 3:
					{
					setState(231);
					constant();
					}
					break;
				case 4:
					{
					setState(232);
					identifier();
					}
					break;
				case 5:
					{
					setState(233);
					array_call();
					}
					break;
				}
				}
				break;
			case T__0:
				{
				setState(236);
				match(T__0);
				setState(237);
				arithmetic_exp(0);
				setState(238);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(248);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Arithmetic_expContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_arithmetic_exp);
					setState(242);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(243);
					arithmetic_op();
					setState(244);
					arithmetic_exp(2);
					}
					} 
				}
				setState(250);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,23,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Relational_expContext extends ParserRuleContext {
		public Relational_opContext relational_op() {
			return getRuleContext(Relational_opContext.class,0);
		}
		public List<Arithmetic_expContext> arithmetic_exp() {
			return getRuleContexts(Arithmetic_expContext.class);
		}
		public Arithmetic_expContext arithmetic_exp(int i) {
			return getRuleContext(Arithmetic_expContext.class,i);
		}
		public List<Bool_literalContext> bool_literal() {
			return getRuleContexts(Bool_literalContext.class);
		}
		public Bool_literalContext bool_literal(int i) {
			return getRuleContext(Bool_literalContext.class,i);
		}
		public List<Relational_expContext> relational_exp() {
			return getRuleContexts(Relational_expContext.class);
		}
		public Relational_expContext relational_exp(int i) {
			return getRuleContext(Relational_expContext.class,i);
		}
		public Relational_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational_exp; }
	}

	public final Relational_expContext relational_exp() throws RecognitionException {
		return relational_exp(0);
	}

	private Relational_expContext relational_exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Relational_expContext _localctx = new Relational_expContext(_ctx, _parentState);
		Relational_expContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_relational_exp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(254);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case PLUS:
				case MINUS:
				case PI:
				case EULER:
				case IDENTIFIER:
				case INTEGER:
					{
					setState(252);
					arithmetic_exp(0);
					}
					break;
				case T__5:
				case T__6:
					{
					setState(253);
					bool_literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(256);
				relational_op();
				setState(259);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__0:
				case PLUS:
				case MINUS:
				case PI:
				case EULER:
				case IDENTIFIER:
				case INTEGER:
					{
					setState(257);
					arithmetic_exp(0);
					}
					break;
				case T__5:
				case T__6:
					{
					setState(258);
					bool_literal();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 2:
				{
				setState(261);
				match(T__0);
				setState(262);
				relational_exp(0);
				setState(263);
				match(T__1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(273);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Relational_expContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_relational_exp);
					setState(267);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(268);
					relational_op();
					setState(269);
					relational_exp(3);
					}
					} 
				}
				setState(275);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Logic_expContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Array_callContext array_call() {
			return getRuleContext(Array_callContext.class,0);
		}
		public Bool_literalContext bool_literal() {
			return getRuleContext(Bool_literalContext.class,0);
		}
		public Relational_expContext relational_exp() {
			return getRuleContext(Relational_expContext.class,0);
		}
		public List<Logic_expContext> logic_exp() {
			return getRuleContexts(Logic_expContext.class);
		}
		public Logic_expContext logic_exp(int i) {
			return getRuleContext(Logic_expContext.class,i);
		}
		public TerminalNode NOT() { return getToken(fodcatParser.NOT, 0); }
		public Logic_opContext logic_op() {
			return getRuleContext(Logic_opContext.class,0);
		}
		public Logic_expContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_exp; }
	}

	public final Logic_expContext logic_exp() throws RecognitionException {
		return logic_exp(0);
	}

	private Logic_expContext logic_exp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Logic_expContext _localctx = new Logic_expContext(_ctx, _parentState);
		Logic_expContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_logic_exp, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NOT) {
				{
				setState(277);
				match(NOT);
				}
			}

			setState(288);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				{
				setState(280);
				identifier();
				}
				break;
			case 2:
				{
				setState(281);
				array_call();
				}
				break;
			case 3:
				{
				setState(282);
				bool_literal();
				}
				break;
			case 4:
				{
				setState(283);
				relational_exp(0);
				}
				break;
			case 5:
				{
				setState(284);
				match(T__0);
				setState(285);
				logic_exp(0);
				setState(286);
				match(T__1);
				}
				break;
			}
			}
			_ctx.stop = _input.LT(-1);
			setState(296);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Logic_expContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_logic_exp);
					setState(290);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(291);
					logic_op();
					setState(292);
					logic_exp(2);
					}
					} 
				}
				setState(298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,30,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class Array_callContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Arithmetic_expContext arithmetic_exp() {
			return getRuleContext(Arithmetic_expContext.class,0);
		}
		public Array_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_call; }
	}

	public final Array_callContext array_call() throws RecognitionException {
		Array_callContext _localctx = new Array_callContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_array_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(299);
			identifier();
			setState(300);
			match(T__3);
			setState(301);
			arithmetic_exp(0);
			setState(302);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_declarationContext extends ParserRuleContext {
		public TerminalNode FUNCTION_DEC() { return getToken(fodcatParser.FUNCTION_DEC, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public Parameters_declarationContext parameters_declaration() {
			return getRuleContext(Parameters_declarationContext.class,0);
		}
		public TerminalNode END() { return getToken(fodcatParser.END, 0); }
		public List<CommandContext> command() {
			return getRuleContexts(CommandContext.class);
		}
		public CommandContext command(int i) {
			return getRuleContext(CommandContext.class,i);
		}
		public Function_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_declaration; }
	}

	public final Function_declarationContext function_declaration() throws RecognitionException {
		Function_declarationContext _localctx = new Function_declarationContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_function_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(FUNCTION_DEC);
			setState(305);
			identifier();
			setState(306);
			match(T__0);
			setState(307);
			parameters_declaration();
			setState(308);
			match(T__1);
			setState(312);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << WHILE) | (1L << REPEAT) | (1L << BREAK) | (1L << CONTINUE) | (1L << IF) | (1L << RETURN) | (1L << PRINT) | (1L << SLEEP) | (1L << IDENTIFIER))) != 0)) {
				{
				{
				setState(309);
				command();
				}
				}
				setState(314);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(315);
			match(END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Array_declarationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN_OP() { return getToken(fodcatParser.ASSIGN_OP, 0); }
		public Arithmetic_expContext arithmetic_exp() {
			return getRuleContext(Arithmetic_expContext.class,0);
		}
		public Array_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_declaration; }
	}

	public final Array_declarationContext array_declaration() throws RecognitionException {
		Array_declarationContext _localctx = new Array_declarationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_array_declaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(317);
			identifier();
			setState(318);
			match(ASSIGN_OP);
			setState(319);
			match(T__3);
			setState(320);
			arithmetic_exp(0);
			setState(321);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Gpio_declarationContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN_OP() { return getToken(fodcatParser.ASSIGN_OP, 0); }
		public Parameters_calledContext parameters_called() {
			return getRuleContext(Parameters_calledContext.class,0);
		}
		public TerminalNode BUTTON() { return getToken(fodcatParser.BUTTON, 0); }
		public TerminalNode LED() { return getToken(fodcatParser.LED, 0); }
		public TerminalNode SERVO() { return getToken(fodcatParser.SERVO, 0); }
		public TerminalNode PWMLED() { return getToken(fodcatParser.PWMLED, 0); }
		public Gpio_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_gpio_declaration; }
	}

	public final Gpio_declarationContext gpio_declaration() throws RecognitionException {
		Gpio_declarationContext _localctx = new Gpio_declarationContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_gpio_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(323);
			identifier();
			setState(324);
			match(ASSIGN_OP);
			setState(332);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BUTTON:
				{
				setState(325);
				match(BUTTON);
				}
				break;
			case LED:
				{
				setState(326);
				match(LED);
				setState(329);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__2) {
					{
					setState(327);
					match(T__2);
					setState(328);
					match(PWMLED);
					}
				}

				}
				break;
			case SERVO:
				{
				setState(331);
				match(SERVO);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(334);
			match(T__0);
			setState(335);
			parameters_called();
			setState(336);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameters_declarationContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(fodcatParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(fodcatParser.COMMA, i);
		}
		public Parameters_declarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters_declaration; }
	}

	public final Parameters_declarationContext parameters_declaration() throws RecognitionException {
		Parameters_declarationContext _localctx = new Parameters_declarationContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_parameters_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(354);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(338);
				identifier();
				setState(341);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(339);
					match(T__3);
					setState(340);
					match(T__4);
					}
				}

				setState(351);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(343);
					match(COMMA);
					setState(344);
					identifier();
					setState(347);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==T__3) {
						{
						setState(345);
						match(T__3);
						setState(346);
						match(T__4);
						}
					}

					}
					}
					setState(353);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parameters_calledContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(fodcatParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(fodcatParser.COMMA, i);
		}
		public Parameters_calledContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameters_called; }
	}

	public final Parameters_calledContext parameters_called() throws RecognitionException {
		Parameters_calledContext _localctx = new Parameters_calledContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_parameters_called);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(364);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__5) | (1L << T__6) | (1L << PLUS) | (1L << MINUS) | (1L << PI) | (1L << EULER) | (1L << NOT) | (1L << IDENTIFIER) | (1L << INTEGER) | (1L << STRING))) != 0)) {
				{
				setState(356);
				expression();
				setState(361);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(357);
					match(COMMA);
					setState(358);
					expression();
					}
					}
					setState(363);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Int_literalContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(fodcatParser.INTEGER, 0); }
		public Int_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_int_literal; }
	}

	public final Int_literalContext int_literal() throws RecognitionException {
		Int_literalContext _localctx = new Int_literalContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_int_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bool_literalContext extends ParserRuleContext {
		public Bool_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_literal; }
	}

	public final Bool_literalContext bool_literal() throws RecognitionException {
		Bool_literalContext _localctx = new Bool_literalContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_bool_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Float_literalContext extends ParserRuleContext {
		public List<TerminalNode> INTEGER() { return getTokens(fodcatParser.INTEGER); }
		public TerminalNode INTEGER(int i) {
			return getToken(fodcatParser.INTEGER, i);
		}
		public Float_literalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_float_literal; }
	}

	public final Float_literalContext float_literal() throws RecognitionException {
		Float_literalContext _localctx = new Float_literalContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_float_literal);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(370);
			match(INTEGER);
			setState(371);
			match(T__2);
			setState(372);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StrgContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(fodcatParser.STRING, 0); }
		public StrgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_strg; }
	}

	public final StrgContext strg() throws RecognitionException {
		StrgContext _localctx = new StrgContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_strg);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(374);
			match(STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode PI() { return getToken(fodcatParser.PI, 0); }
		public TerminalNode EULER() { return getToken(fodcatParser.EULER, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(376);
			_la = _input.LA(1);
			if ( !(_la==PI || _la==EULER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Relational_opContext extends ParserRuleContext {
		public TerminalNode GREATER() { return getToken(fodcatParser.GREATER, 0); }
		public TerminalNode GREATEREQ() { return getToken(fodcatParser.GREATEREQ, 0); }
		public TerminalNode LESS() { return getToken(fodcatParser.LESS, 0); }
		public TerminalNode LESSEQ() { return getToken(fodcatParser.LESSEQ, 0); }
		public TerminalNode EQUAL() { return getToken(fodcatParser.EQUAL, 0); }
		public TerminalNode DIFFERENT() { return getToken(fodcatParser.DIFFERENT, 0); }
		public Relational_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relational_op; }
	}

	public final Relational_opContext relational_op() throws RecognitionException {
		Relational_opContext _localctx = new Relational_opContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_relational_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(378);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GREATER) | (1L << GREATEREQ) | (1L << LESS) | (1L << LESSEQ) | (1L << EQUAL) | (1L << DIFFERENT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Logic_opContext extends ParserRuleContext {
		public TerminalNode AND() { return getToken(fodcatParser.AND, 0); }
		public TerminalNode OR() { return getToken(fodcatParser.OR, 0); }
		public TerminalNode NOT() { return getToken(fodcatParser.NOT, 0); }
		public Logic_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_logic_op; }
	}

	public final Logic_opContext logic_op() throws RecognitionException {
		Logic_opContext _localctx = new Logic_opContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_logic_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(380);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << AND) | (1L << NOT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Arithmetic_opContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(fodcatParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(fodcatParser.MINUS, 0); }
		public TerminalNode MULTIPLICATION() { return getToken(fodcatParser.MULTIPLICATION, 0); }
		public TerminalNode DIVISION() { return getToken(fodcatParser.DIVISION, 0); }
		public TerminalNode POWER() { return getToken(fodcatParser.POWER, 0); }
		public TerminalNode MODULE() { return getToken(fodcatParser.MODULE, 0); }
		public Arithmetic_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arithmetic_op; }
	}

	public final Arithmetic_opContext arithmetic_op() throws RecognitionException {
		Arithmetic_opContext _localctx = new Arithmetic_opContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_arithmetic_op);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << PLUS) | (1L << MINUS) | (1L << MULTIPLICATION) | (1L << DIVISION) | (1L << POWER) | (1L << MODULE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Print_opContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(fodcatParser.PLUS, 0); }
		public Print_opContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_print_op; }
	}

	public final Print_opContext print_op() throws RecognitionException {
		Print_opContext _localctx = new Print_opContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_print_op);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(384);
			match(PLUS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(fodcatParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(fodcatParser.MINUS, 0); }
		public SignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sign; }
	}

	public final SignContext sign() throws RecognitionException {
		SignContext _localctx = new SignContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_buttonContext extends ParserRuleContext {
		public TerminalNode PRESS() { return getToken(fodcatParser.PRESS, 0); }
		public Func_buttonContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_button; }
	}

	public final Func_buttonContext func_button() throws RecognitionException {
		Func_buttonContext _localctx = new Func_buttonContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_func_button);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			match(PRESS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_ledContext extends ParserRuleContext {
		public TerminalNode ON() { return getToken(fodcatParser.ON, 0); }
		public TerminalNode OFF() { return getToken(fodcatParser.OFF, 0); }
		public TerminalNode BLINK() { return getToken(fodcatParser.BLINK, 0); }
		public TerminalNode VALUE() { return getToken(fodcatParser.VALUE, 0); }
		public Func_ledContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_led; }
	}

	public final Func_ledContext func_led() throws RecognitionException {
		Func_ledContext _localctx = new Func_ledContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_func_led);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ON) | (1L << OFF) | (1L << BLINK) | (1L << VALUE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Func_servoContext extends ParserRuleContext {
		public TerminalNode MIN() { return getToken(fodcatParser.MIN, 0); }
		public TerminalNode MID() { return getToken(fodcatParser.MID, 0); }
		public TerminalNode MAX() { return getToken(fodcatParser.MAX, 0); }
		public TerminalNode ANGLE() { return getToken(fodcatParser.ANGLE, 0); }
		public Func_servoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_servo; }
	}

	public final Func_servoContext func_servo() throws RecognitionException {
		Func_servoContext _localctx = new Func_servoContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_func_servo);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(392);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << MIN) | (1L << MID) | (1L << MAX) | (1L << ANGLE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(fodcatParser.IDENTIFIER, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 11:
			return arithmetic_exp_sempred((Arithmetic_expContext)_localctx, predIndex);
		case 12:
			return relational_exp_sempred((Relational_expContext)_localctx, predIndex);
		case 13:
			return logic_exp_sempred((Logic_expContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean arithmetic_exp_sempred(Arithmetic_expContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean relational_exp_sempred(Relational_expContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean logic_exp_sempred(Logic_expContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3?\u018f\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\2\3\2\7\2K\n\2\f\2\16\2N\13\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\7\3W\n\3\f\3\16\3Z\13\3\3\3\3\3\3\4\3\4\6\4`\n\4\r\4"+
		"\16\4a\5\4d\n\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4l\n\4\3\4\3\4\3\4\7\4q\n\4"+
		"\f\4\16\4t\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4}\n\4\3\4\3\4\3\4\7\4\u0082"+
		"\n\4\f\4\16\4\u0085\13\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4\u008f\n\4"+
		"\f\4\16\4\u0092\13\4\3\4\3\4\3\4\3\4\5\4\u0098\n\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\5\4\u00a0\n\4\3\5\3\5\3\5\3\5\5\5\u00a6\n\5\3\5\3\5\3\5\7\5\u00ab"+
		"\n\5\f\5\16\5\u00ae\13\5\3\6\3\6\3\6\7\6\u00b3\n\6\f\6\16\6\u00b6\13\6"+
		"\5\6\u00b8\n\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\5\t\u00c3\n\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\5\n\u00cd\n\n\3\n\3\n\3\n\3\n\5\n\u00d3\n\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\5\f\u00e2\n"+
		"\f\3\r\3\r\5\r\u00e6\n\r\3\r\3\r\3\r\3\r\3\r\5\r\u00ed\n\r\3\r\3\r\3\r"+
		"\3\r\5\r\u00f3\n\r\3\r\3\r\3\r\3\r\7\r\u00f9\n\r\f\r\16\r\u00fc\13\r\3"+
		"\16\3\16\3\16\5\16\u0101\n\16\3\16\3\16\3\16\5\16\u0106\n\16\3\16\3\16"+
		"\3\16\3\16\5\16\u010c\n\16\3\16\3\16\3\16\3\16\7\16\u0112\n\16\f\16\16"+
		"\16\u0115\13\16\3\17\3\17\5\17\u0119\n\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\5\17\u0123\n\17\3\17\3\17\3\17\3\17\7\17\u0129\n\17\f\17\16"+
		"\17\u012c\13\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\7\21\u0139\n\21\f\21\16\21\u013c\13\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u014c\n\23\3\23\5\23\u014f"+
		"\n\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\5\24\u0158\n\24\3\24\3\24\3\24"+
		"\3\24\5\24\u015e\n\24\7\24\u0160\n\24\f\24\16\24\u0163\13\24\5\24\u0165"+
		"\n\24\3\25\3\25\3\25\7\25\u016a\n\25\f\25\16\25\u016d\13\25\5\25\u016f"+
		"\n\25\3\26\3\26\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3\33"+
		"\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3#\3"+
		"#\3#\2\5\30\32\34$\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BD\2\13\3\2\"#\3\2\b\t\3\2\21\22\3\2\23\30\3\2\31\33\3\2"+
		"\n\17\3\2\n\13\3\2\62\65\3\2\669\2\u01af\2L\3\2\2\2\4Q\3\2\2\2\6\u009f"+
		"\3\2\2\2\b\u00a1\3\2\2\2\n\u00af\3\2\2\2\f\u00b9\3\2\2\2\16\u00be\3\2"+
		"\2\2\20\u00c2\3\2\2\2\22\u00c7\3\2\2\2\24\u00d4\3\2\2\2\26\u00e1\3\2\2"+
		"\2\30\u00e3\3\2\2\2\32\u010b\3\2\2\2\34\u0116\3\2\2\2\36\u012d\3\2\2\2"+
		" \u0132\3\2\2\2\"\u013f\3\2\2\2$\u0145\3\2\2\2&\u0164\3\2\2\2(\u016e\3"+
		"\2\2\2*\u0170\3\2\2\2,\u0172\3\2\2\2.\u0174\3\2\2\2\60\u0178\3\2\2\2\62"+
		"\u017a\3\2\2\2\64\u017c\3\2\2\2\66\u017e\3\2\2\28\u0180\3\2\2\2:\u0182"+
		"\3\2\2\2<\u0184\3\2\2\2>\u0186\3\2\2\2@\u0188\3\2\2\2B\u018a\3\2\2\2D"+
		"\u018c\3\2\2\2FK\5\20\t\2GK\5$\23\2HK\5\"\22\2IK\5 \21\2JF\3\2\2\2JG\3"+
		"\2\2\2JH\3\2\2\2JI\3\2\2\2KN\3\2\2\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3"+
		"\2\2\2OP\5\4\3\2P\3\3\2\2\2QR\7\'\2\2RS\7(\2\2ST\7\3\2\2TX\7\4\2\2UW\5"+
		"\6\4\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y[\3\2\2\2ZX\3\2\2\2[\\"+
		"\7)\2\2\\\5\3\2\2\2]c\5\b\5\2^`\5\n\6\2_^\3\2\2\2`a\3\2\2\2a_\3\2\2\2"+
		"ab\3\2\2\2bd\3\2\2\2c_\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\7)\2\2f\u00a0\3\2"+
		"\2\2gh\7\36\2\2hk\7\3\2\2il\5\34\17\2jl\5\22\n\2ki\3\2\2\2kj\3\2\2\2l"+
		"m\3\2\2\2mn\7\4\2\2nr\7 \2\2oq\5\6\4\2po\3\2\2\2qt\3\2\2\2rp\3\2\2\2r"+
		"s\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7)\2\2v\u00a0\3\2\2\2wx\7\37\2\2x|\7\3"+
		"\2\2y}\5*\26\2z}\5D#\2{}\5\30\r\2|y\3\2\2\2|z\3\2\2\2|{\3\2\2\2}~\3\2"+
		"\2\2~\177\7\4\2\2\177\u0083\7!\2\2\u0080\u0082\5\6\4\2\u0081\u0080\3\2"+
		"\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7)\2\2\u0087\u00a0\3\2"+
		"\2\2\u0088\u0089\7+\2\2\u0089\u008a\7\3\2\2\u008a\u0090\5\26\f\2\u008b"+
		"\u008c\5:\36\2\u008c\u008d\5\26\f\2\u008d\u008f\3\2\2\2\u008e\u008b\3"+
		"\2\2\2\u008f\u0092\3\2\2\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0093\3\2\2\2\u0092\u0090\3\2\2\2\u0093\u0094\7\4\2\2\u0094\u00a0\3\2"+
		"\2\2\u0095\u0097\7*\2\2\u0096\u0098\5\26\f\2\u0097\u0096\3\2\2\2\u0097"+
		"\u0098\3\2\2\2\u0098\u00a0\3\2\2\2\u0099\u00a0\5\f\7\2\u009a\u00a0\5\16"+
		"\b\2\u009b\u00a0\5\20\t\2\u009c\u00a0\5\"\22\2\u009d\u00a0\5\22\n\2\u009e"+
		"\u00a0\5\24\13\2\u009f]\3\2\2\2\u009fg\3\2\2\2\u009fw\3\2\2\2\u009f\u0088"+
		"\3\2\2\2\u009f\u0095\3\2\2\2\u009f\u0099\3\2\2\2\u009f\u009a\3\2\2\2\u009f"+
		"\u009b\3\2\2\2\u009f\u009c\3\2\2\2\u009f\u009d\3\2\2\2\u009f\u009e\3\2"+
		"\2\2\u00a0\7\3\2\2\2\u00a1\u00a2\7$\2\2\u00a2\u00a5\7\3\2\2\u00a3\u00a6"+
		"\5\34\17\2\u00a4\u00a6\5\22\n\2\u00a5\u00a3\3\2\2\2\u00a5\u00a4\3\2\2"+
		"\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\7\4\2\2\u00a8\u00ac\7%\2\2\u00a9\u00ab"+
		"\5\6\4\2\u00aa\u00a9\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ac"+
		"\u00ad\3\2\2\2\u00ad\t\3\2\2\2\u00ae\u00ac\3\2\2\2\u00af\u00b7\7&\2\2"+
		"\u00b0\u00b8\5\b\5\2\u00b1\u00b3\5\6\4\2\u00b2\u00b1\3\2\2\2\u00b3\u00b6"+
		"\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6"+
		"\u00b4\3\2\2\2\u00b7\u00b0\3\2\2\2\u00b7\u00b4\3\2\2\2\u00b8\13\3\2\2"+
		"\2\u00b9\u00ba\5D#\2\u00ba\u00bb\7\3\2\2\u00bb\u00bc\5(\25\2\u00bc\u00bd"+
		"\7\4\2\2\u00bd\r\3\2\2\2\u00be\u00bf\t\2\2\2\u00bf\17\3\2\2\2\u00c0\u00c3"+
		"\5D#\2\u00c1\u00c3\5\36\20\2\u00c2\u00c0\3\2\2\2\u00c2\u00c1\3\2\2\2\u00c3"+
		"\u00c4\3\2\2\2\u00c4\u00c5\7\20\2\2\u00c5\u00c6\5\26\f\2\u00c6\21\3\2"+
		"\2\2\u00c7\u00c8\5D#\2\u00c8\u00cc\7\5\2\2\u00c9\u00cd\5> \2\u00ca\u00cd"+
		"\5@!\2\u00cb\u00cd\5B\"\2\u00cc\u00c9\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cc"+
		"\u00cb\3\2\2\2\u00cd\u00d2\3\2\2\2\u00ce\u00cf\7\3\2\2\u00cf\u00d3\7\4"+
		"\2\2\u00d0\u00d1\7\20\2\2\u00d1\u00d3\5\26\f\2\u00d2\u00ce\3\2\2\2\u00d2"+
		"\u00d0\3\2\2\2\u00d2\u00d3\3\2\2\2\u00d3\23\3\2\2\2\u00d4\u00d5\7\60\2"+
		"\2\u00d5\u00d6\7\3\2\2\u00d6\u00d7\5\26\f\2\u00d7\u00d8\7\4\2\2\u00d8"+
		"\25\3\2\2\2\u00d9\u00e2\5D#\2\u00da\u00e2\5\36\20\2\u00db\u00e2\5\62\32"+
		"\2\u00dc\u00e2\5\60\31\2\u00dd\u00e2\5\f\7\2\u00de\u00e2\5\30\r\2\u00df"+
		"\u00e2\5\34\17\2\u00e0\u00e2\5\32\16\2\u00e1\u00d9\3\2\2\2\u00e1\u00da"+
		"\3\2\2\2\u00e1\u00db\3\2\2\2\u00e1\u00dc\3\2\2\2\u00e1\u00dd\3\2\2\2\u00e1"+
		"\u00de\3\2\2\2\u00e1\u00df\3\2\2\2\u00e1\u00e0\3\2\2\2\u00e2\27\3\2\2"+
		"\2\u00e3\u00e5\b\r\1\2\u00e4\u00e6\5<\37\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6"+
		"\3\2\2\2\u00e6\u00f2\3\2\2\2\u00e7\u00ed\5*\26\2\u00e8\u00ed\5.\30\2\u00e9"+
		"\u00ed\5\62\32\2\u00ea\u00ed\5D#\2\u00eb\u00ed\5\36\20\2\u00ec\u00e7\3"+
		"\2\2\2\u00ec\u00e8\3\2\2\2\u00ec\u00e9\3\2\2\2\u00ec\u00ea\3\2\2\2\u00ec"+
		"\u00eb\3\2\2\2\u00ed\u00f3\3\2\2\2\u00ee\u00ef\7\3\2\2\u00ef\u00f0\5\30"+
		"\r\2\u00f0\u00f1\7\4\2\2\u00f1\u00f3\3\2\2\2\u00f2\u00ec\3\2\2\2\u00f2"+
		"\u00ee\3\2\2\2\u00f3\u00fa\3\2\2\2\u00f4\u00f5\f\3\2\2\u00f5\u00f6\58"+
		"\35\2\u00f6\u00f7\5\30\r\4\u00f7\u00f9\3\2\2\2\u00f8\u00f4\3\2\2\2\u00f9"+
		"\u00fc\3\2\2\2\u00fa\u00f8\3\2\2\2\u00fa\u00fb\3\2\2\2\u00fb\31\3\2\2"+
		"\2\u00fc\u00fa\3\2\2\2\u00fd\u0100\b\16\1\2\u00fe\u0101\5\30\r\2\u00ff"+
		"\u0101\5,\27\2\u0100\u00fe\3\2\2\2\u0100\u00ff\3\2\2\2\u0101\u0102\3\2"+
		"\2\2\u0102\u0105\5\64\33\2\u0103\u0106\5\30\r\2\u0104\u0106\5,\27\2\u0105"+
		"\u0103\3\2\2\2\u0105\u0104\3\2\2\2\u0106\u010c\3\2\2\2\u0107\u0108\7\3"+
		"\2\2\u0108\u0109\5\32\16\2\u0109\u010a\7\4\2\2\u010a\u010c\3\2\2\2\u010b"+
		"\u00fd\3\2\2\2\u010b\u0107\3\2\2\2\u010c\u0113\3\2\2\2\u010d\u010e\f\4"+
		"\2\2\u010e\u010f\5\64\33\2\u010f\u0110\5\32\16\5\u0110\u0112\3\2\2\2\u0111"+
		"\u010d\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2"+
		"\2\2\u0114\33\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0118\b\17\1\2\u0117\u0119"+
		"\7\33\2\2\u0118\u0117\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0122\3\2\2\2"+
		"\u011a\u0123\5D#\2\u011b\u0123\5\36\20\2\u011c\u0123\5,\27\2\u011d\u0123"+
		"\5\32\16\2\u011e\u011f\7\3\2\2\u011f\u0120\5\34\17\2\u0120\u0121\7\4\2"+
		"\2\u0121\u0123\3\2\2\2\u0122\u011a\3\2\2\2\u0122\u011b\3\2\2\2\u0122\u011c"+
		"\3\2\2\2\u0122\u011d\3\2\2\2\u0122\u011e\3\2\2\2\u0123\u012a\3\2\2\2\u0124"+
		"\u0125\f\3\2\2\u0125\u0126\5\66\34\2\u0126\u0127\5\34\17\4\u0127\u0129"+
		"\3\2\2\2\u0128\u0124\3\2\2\2\u0129\u012c\3\2\2\2\u012a\u0128\3\2\2\2\u012a"+
		"\u012b\3\2\2\2\u012b\35\3\2\2\2\u012c\u012a\3\2\2\2\u012d\u012e\5D#\2"+
		"\u012e\u012f\7\6\2\2\u012f\u0130\5\30\r\2\u0130\u0131\7\7\2\2\u0131\37"+
		"\3\2\2\2\u0132\u0133\7\'\2\2\u0133\u0134\5D#\2\u0134\u0135\7\3\2\2\u0135"+
		"\u0136\5&\24\2\u0136\u013a\7\4\2\2\u0137\u0139\5\6\4\2\u0138\u0137\3\2"+
		"\2\2\u0139\u013c\3\2\2\2\u013a\u0138\3\2\2\2\u013a\u013b\3\2\2\2\u013b"+
		"\u013d\3\2\2\2\u013c\u013a\3\2\2\2\u013d\u013e\7)\2\2\u013e!\3\2\2\2\u013f"+
		"\u0140\5D#\2\u0140\u0141\7\20\2\2\u0141\u0142\7\6\2\2\u0142\u0143\5\30"+
		"\r\2\u0143\u0144\7\7\2\2\u0144#\3\2\2\2\u0145\u0146\5D#\2\u0146\u014e"+
		"\7\20\2\2\u0147\u014f\7,\2\2\u0148\u014b\7-\2\2\u0149\u014a\7\5\2\2\u014a"+
		"\u014c\7.\2\2\u014b\u0149\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014f\3\2"+
		"\2\2\u014d\u014f\7/\2\2\u014e\u0147\3\2\2\2\u014e\u0148\3\2\2\2\u014e"+
		"\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\7\3\2\2\u0151\u0152\5("+
		"\25\2\u0152\u0153\7\4\2\2\u0153%\3\2\2\2\u0154\u0157\5D#\2\u0155\u0156"+
		"\7\6\2\2\u0156\u0158\7\7\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158"+
		"\u0161\3\2\2\2\u0159\u015a\7\34\2\2\u015a\u015d\5D#\2\u015b\u015c\7\6"+
		"\2\2\u015c\u015e\7\7\2\2\u015d\u015b\3\2\2\2\u015d\u015e\3\2\2\2\u015e"+
		"\u0160\3\2\2\2\u015f\u0159\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u015f\3\2"+
		"\2\2\u0161\u0162\3\2\2\2\u0162\u0165\3\2\2\2\u0163\u0161\3\2\2\2\u0164"+
		"\u0154\3\2\2\2\u0164\u0165\3\2\2\2\u0165\'\3\2\2\2\u0166\u016b\5\26\f"+
		"\2\u0167\u0168\7\34\2\2\u0168\u016a\5\26\f\2\u0169\u0167\3\2\2\2\u016a"+
		"\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c\u016f\3\2"+
		"\2\2\u016d\u016b\3\2\2\2\u016e\u0166\3\2\2\2\u016e\u016f\3\2\2\2\u016f"+
		")\3\2\2\2\u0170\u0171\7;\2\2\u0171+\3\2\2\2\u0172\u0173\t\3\2\2\u0173"+
		"-\3\2\2\2\u0174\u0175\7;\2\2\u0175\u0176\7\5\2\2\u0176\u0177\7;\2\2\u0177"+
		"/\3\2\2\2\u0178\u0179\7<\2\2\u0179\61\3\2\2\2\u017a\u017b\t\4\2\2\u017b"+
		"\63\3\2\2\2\u017c\u017d\t\5\2\2\u017d\65\3\2\2\2\u017e\u017f\t\6\2\2\u017f"+
		"\67\3\2\2\2\u0180\u0181\t\7\2\2\u01819\3\2\2\2\u0182\u0183\7\n\2\2\u0183"+
		";\3\2\2\2\u0184\u0185\t\b\2\2\u0185=\3\2\2\2\u0186\u0187\7\61\2\2\u0187"+
		"?\3\2\2\2\u0188\u0189\t\t\2\2\u0189A\3\2\2\2\u018a\u018b\t\n\2\2\u018b"+
		"C\3\2\2\2\u018c\u018d\7:\2\2\u018dE\3\2\2\2*JLXackr|\u0083\u0090\u0097"+
		"\u009f\u00a5\u00ac\u00b4\u00b7\u00c2\u00cc\u00d2\u00e1\u00e5\u00ec\u00f2"+
		"\u00fa\u0100\u0105\u010b\u0113\u0118\u0122\u012a\u013a\u014b\u014e\u0157"+
		"\u015d\u0161\u0164\u016b\u016e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}