// Generated from TkcGrammar.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TkcGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, SwitchStateValue=3, CrossingStateValue=4, SignalStateValue=5, 
		OccupancyValue=6, BlockID=7, Whitespace=8, If=9, Then=10, Endif=11, Semicolon=12, 
		Period=13, Occupancy=14, SwitchState=15, CrossingState=16, Authority=17, 
		EEquals=18, Equals=19, FORK=20, MAIN=21, UP=22, DOWN=23, RED=24, YELLOW=25, 
		GREEN=26, OCCUPIED=27, UNOCCUPIED=28;
	public static final int
		RULE_program = 0, RULE_ifsequence = 1, RULE_ifstatement = 2, RULE_condition = 3, 
		RULE_statementlist = 4, RULE_statement = 5, RULE_attribute = 6, RULE_assignedattribute = 7, 
		RULE_value = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "ifsequence", "ifstatement", "condition", "statementlist", 
			"statement", "attribute", "assignedattribute", "value"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", null, null, null, null, null, null, null, null, null, 
			"';'", "'.'", null, null, null, null, "'=='", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "SwitchStateValue", "CrossingStateValue", "SignalStateValue", 
			"OccupancyValue", "BlockID", "Whitespace", "If", "Then", "Endif", "Semicolon", 
			"Period", "Occupancy", "SwitchState", "CrossingState", "Authority", "EEquals", 
			"Equals", "FORK", "MAIN", "UP", "DOWN", "RED", "YELLOW", "GREEN", "OCCUPIED", 
			"UNOCCUPIED"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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
	public String getGrammarFileName() { return "TkcGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TkcGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TkcGrammarParser.EOF, 0); }
		public IfsequenceContext ifsequence() {
			return getRuleContext(IfsequenceContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==If) {
				{
				setState(18);
				ifsequence(0);
				}
			}

			setState(21);
			match(EOF);
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

	public static class IfsequenceContext extends ParserRuleContext {
		public IfstatementContext ifstatement() {
			return getRuleContext(IfstatementContext.class,0);
		}
		public IfsequenceContext ifsequence() {
			return getRuleContext(IfsequenceContext.class,0);
		}
		public IfsequenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifsequence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterIfsequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitIfsequence(this);
		}
	}

	public final IfsequenceContext ifsequence() throws RecognitionException {
		return ifsequence(0);
	}

	private IfsequenceContext ifsequence(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		IfsequenceContext _localctx = new IfsequenceContext(_ctx, _parentState);
		IfsequenceContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_ifsequence, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(24);
			ifstatement();
			}
			_ctx.stop = _input.LT(-1);
			setState(30);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new IfsequenceContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_ifsequence);
					setState(26);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(27);
					ifstatement();
					}
					} 
				}
				setState(32);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class IfstatementContext extends ParserRuleContext {
		public TerminalNode If() { return getToken(TkcGrammarParser.If, 0); }
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode Then() { return getToken(TkcGrammarParser.Then, 0); }
		public StatementlistContext statementlist() {
			return getRuleContext(StatementlistContext.class,0);
		}
		public TerminalNode Endif() { return getToken(TkcGrammarParser.Endif, 0); }
		public TerminalNode Semicolon() { return getToken(TkcGrammarParser.Semicolon, 0); }
		public IfstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterIfstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitIfstatement(this);
		}
	}

	public final IfstatementContext ifstatement() throws RecognitionException {
		IfstatementContext _localctx = new IfstatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ifstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(If);
			setState(34);
			match(T__0);
			setState(35);
			condition();
			setState(36);
			match(T__1);
			setState(37);
			match(Then);
			setState(38);
			statementlist(0);
			setState(39);
			match(Endif);
			setState(40);
			match(Semicolon);
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

	public static class ConditionContext extends ParserRuleContext {
		public TerminalNode BlockID() { return getToken(TkcGrammarParser.BlockID, 0); }
		public TerminalNode Period() { return getToken(TkcGrammarParser.Period, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode EEquals() { return getToken(TkcGrammarParser.EEquals, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42);
			match(BlockID);
			setState(43);
			match(Period);
			setState(44);
			attribute();
			setState(45);
			match(EEquals);
			setState(46);
			value();
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

	public static class StatementlistContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementlistContext statementlist() {
			return getRuleContext(StatementlistContext.class,0);
		}
		public StatementlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterStatementlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitStatementlist(this);
		}
	}

	public final StatementlistContext statementlist() throws RecognitionException {
		return statementlist(0);
	}

	private StatementlistContext statementlist(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementlistContext _localctx = new StatementlistContext(_ctx, _parentState);
		StatementlistContext _prevctx = _localctx;
		int _startState = 8;
		enterRecursionRule(_localctx, 8, RULE_statementlist, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(49);
			statement();
			}
			_ctx.stop = _input.LT(-1);
			setState(55);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementlistContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_statementlist);
					setState(51);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(52);
					statement();
					}
					} 
				}
				setState(57);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
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

	public static class StatementContext extends ParserRuleContext {
		public TerminalNode BlockID() { return getToken(TkcGrammarParser.BlockID, 0); }
		public TerminalNode Period() { return getToken(TkcGrammarParser.Period, 0); }
		public AssignedattributeContext assignedattribute() {
			return getRuleContext(AssignedattributeContext.class,0);
		}
		public TerminalNode Equals() { return getToken(TkcGrammarParser.Equals, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(TkcGrammarParser.Semicolon, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			match(BlockID);
			setState(59);
			match(Period);
			setState(60);
			assignedattribute();
			setState(61);
			match(Equals);
			setState(62);
			value();
			setState(63);
			match(Semicolon);
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

	public static class AttributeContext extends ParserRuleContext {
		public TerminalNode SwitchState() { return getToken(TkcGrammarParser.SwitchState, 0); }
		public TerminalNode Occupancy() { return getToken(TkcGrammarParser.Occupancy, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_la = _input.LA(1);
			if ( !(_la==Occupancy || _la==SwitchState) ) {
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

	public static class AssignedattributeContext extends ParserRuleContext {
		public TerminalNode SwitchState() { return getToken(TkcGrammarParser.SwitchState, 0); }
		public TerminalNode Authority() { return getToken(TkcGrammarParser.Authority, 0); }
		public TerminalNode CrossingState() { return getToken(TkcGrammarParser.CrossingState, 0); }
		public TerminalNode Occupancy() { return getToken(TkcGrammarParser.Occupancy, 0); }
		public AssignedattributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignedattribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterAssignedattribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitAssignedattribute(this);
		}
	}

	public final AssignedattributeContext assignedattribute() throws RecognitionException {
		AssignedattributeContext _localctx = new AssignedattributeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_assignedattribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << Occupancy) | (1L << SwitchState) | (1L << CrossingState) | (1L << Authority))) != 0)) ) {
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

	public static class ValueContext extends ParserRuleContext {
		public TerminalNode SwitchStateValue() { return getToken(TkcGrammarParser.SwitchStateValue, 0); }
		public TerminalNode BlockID() { return getToken(TkcGrammarParser.BlockID, 0); }
		public TerminalNode CrossingStateValue() { return getToken(TkcGrammarParser.CrossingStateValue, 0); }
		public TerminalNode SignalStateValue() { return getToken(TkcGrammarParser.SignalStateValue, 0); }
		public TerminalNode OccupancyValue() { return getToken(TkcGrammarParser.OccupancyValue, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcGrammarListener ) ((TkcGrammarListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SwitchStateValue) | (1L << CrossingStateValue) | (1L << SignalStateValue) | (1L << OccupancyValue) | (1L << BlockID))) != 0)) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return ifsequence_sempred((IfsequenceContext)_localctx, predIndex);
		case 4:
			return statementlist_sempred((StatementlistContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean ifsequence_sempred(IfsequenceContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean statementlist_sempred(StatementlistContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 1);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36J\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\5\2\26"+
		"\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3\37\n\3\f\3\16\3\"\13\3\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6"+
		"\7\68\n\6\f\6\16\6;\13\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\n\2\4\4\n\13\2\4\6\b\n\f\16\20\22\2\5\3\2\20\21\3\2\20\23\3\2"+
		"\5\t\2C\2\25\3\2\2\2\4\31\3\2\2\2\6#\3\2\2\2\b,\3\2\2\2\n\62\3\2\2\2\f"+
		"<\3\2\2\2\16C\3\2\2\2\20E\3\2\2\2\22G\3\2\2\2\24\26\5\4\3\2\25\24\3\2"+
		"\2\2\25\26\3\2\2\2\26\27\3\2\2\2\27\30\7\2\2\3\30\3\3\2\2\2\31\32\b\3"+
		"\1\2\32\33\5\6\4\2\33 \3\2\2\2\34\35\f\3\2\2\35\37\5\6\4\2\36\34\3\2\2"+
		"\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!\5\3\2\2\2\" \3\2\2\2#$\7\13\2\2"+
		"$%\7\3\2\2%&\5\b\5\2&\'\7\4\2\2\'(\7\f\2\2()\5\n\6\2)*\7\r\2\2*+\7\16"+
		"\2\2+\7\3\2\2\2,-\7\t\2\2-.\7\17\2\2./\5\16\b\2/\60\7\24\2\2\60\61\5\22"+
		"\n\2\61\t\3\2\2\2\62\63\b\6\1\2\63\64\5\f\7\2\649\3\2\2\2\65\66\f\3\2"+
		"\2\668\5\f\7\2\67\65\3\2\2\28;\3\2\2\29\67\3\2\2\29:\3\2\2\2:\13\3\2\2"+
		"\2;9\3\2\2\2<=\7\t\2\2=>\7\17\2\2>?\5\20\t\2?@\7\25\2\2@A\5\22\n\2AB\7"+
		"\16\2\2B\r\3\2\2\2CD\t\2\2\2D\17\3\2\2\2EF\t\3\2\2F\21\3\2\2\2GH\t\4\2"+
		"\2H\23\3\2\2\2\5\25 9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}