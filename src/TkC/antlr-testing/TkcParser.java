// Generated from Tkc.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TkcParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, SwitchStateValue=3, CrossingStateValue=4, SignalStateValue=5, 
		OccupancyValue=6, BlockID=7, Whitespace=8, If=9, Then=10, Endif=11, Semicolon=12, 
		Period=13, Occupancy=14, SwitchState=15, CrossingState=16, Authority=17, 
		EEquals=18, Equals=19, FORK=20, MAIN=21, UP=22, DOWN=23, RED=24, YELLOW=25, 
		GREEN=26, OCCUPIED=27, UNOCCUPIED=28, OR=29, AND=30, LineComment=31;
	public static final int
		RULE_program = 0, RULE_ifsequence = 1, RULE_ifstatement = 2, RULE_conditionlist = 3, 
		RULE_condition = 4, RULE_statementlist = 5, RULE_statement = 6, RULE_attribute = 7, 
		RULE_assignedattribute = 8, RULE_value = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "ifsequence", "ifstatement", "conditionlist", "condition", 
			"statementlist", "statement", "attribute", "assignedattribute", "value"
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
			"UNOCCUPIED", "OR", "AND", "LineComment"
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
	public String getGrammarFileName() { return "Tkc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public TkcParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(TkcParser.EOF, 0); }
		public IfsequenceContext ifsequence() {
			return getRuleContext(IfsequenceContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitProgram(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==If) {
				{
				setState(20);
				ifsequence(0);
				}
			}

			setState(23);
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
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterIfsequence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitIfsequence(this);
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
			setState(26);
			ifstatement();
			}
			_ctx.stop = _input.LT(-1);
			setState(32);
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
					setState(28);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(29);
					ifstatement();
					}
					} 
				}
				setState(34);
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
		public TerminalNode If() { return getToken(TkcParser.If, 0); }
		public ConditionlistContext conditionlist() {
			return getRuleContext(ConditionlistContext.class,0);
		}
		public TerminalNode Then() { return getToken(TkcParser.Then, 0); }
		public StatementlistContext statementlist() {
			return getRuleContext(StatementlistContext.class,0);
		}
		public TerminalNode Endif() { return getToken(TkcParser.Endif, 0); }
		public TerminalNode Semicolon() { return getToken(TkcParser.Semicolon, 0); }
		public IfstatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifstatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterIfstatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitIfstatement(this);
		}
	}

	public final IfstatementContext ifstatement() throws RecognitionException {
		IfstatementContext _localctx = new IfstatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ifstatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(If);
			setState(36);
			match(T__0);
			setState(37);
			conditionlist();
			setState(38);
			match(T__1);
			setState(39);
			match(Then);
			setState(40);
			statementlist(0);
			setState(41);
			match(Endif);
			setState(42);
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

	public static class ConditionlistContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public TerminalNode OR() { return getToken(TkcParser.OR, 0); }
		public ConditionlistContext conditionlist() {
			return getRuleContext(ConditionlistContext.class,0);
		}
		public TerminalNode AND() { return getToken(TkcParser.AND, 0); }
		public ConditionlistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionlist; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterConditionlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitConditionlist(this);
		}
	}

	public final ConditionlistContext conditionlist() throws RecognitionException {
		ConditionlistContext _localctx = new ConditionlistContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_conditionlist);
		try {
			setState(53);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(44);
				condition();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(45);
				condition();
				setState(46);
				match(OR);
				setState(47);
				conditionlist();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(49);
				condition();
				setState(50);
				match(AND);
				setState(51);
				conditionlist();
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

	public static class ConditionContext extends ParserRuleContext {
		public TerminalNode BlockID() { return getToken(TkcParser.BlockID, 0); }
		public TerminalNode Period() { return getToken(TkcParser.Period, 0); }
		public AttributeContext attribute() {
			return getRuleContext(AttributeContext.class,0);
		}
		public TerminalNode EEquals() { return getToken(TkcParser.EEquals, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			match(BlockID);
			setState(56);
			match(Period);
			setState(57);
			attribute();
			setState(58);
			match(EEquals);
			setState(59);
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
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterStatementlist(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitStatementlist(this);
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
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_statementlist, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(62);
			statement();
			}
			_ctx.stop = _input.LT(-1);
			setState(68);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementlistContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_statementlist);
					setState(64);
					if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
					setState(65);
					statement();
					}
					} 
				}
				setState(70);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		public TerminalNode BlockID() { return getToken(TkcParser.BlockID, 0); }
		public TerminalNode Period() { return getToken(TkcParser.Period, 0); }
		public AssignedattributeContext assignedattribute() {
			return getRuleContext(AssignedattributeContext.class,0);
		}
		public TerminalNode Equals() { return getToken(TkcParser.Equals, 0); }
		public ValueContext value() {
			return getRuleContext(ValueContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(TkcParser.Semicolon, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(BlockID);
			setState(72);
			match(Period);
			setState(73);
			assignedattribute();
			setState(74);
			match(Equals);
			setState(75);
			value();
			setState(76);
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
		public TerminalNode SwitchState() { return getToken(TkcParser.SwitchState, 0); }
		public TerminalNode Occupancy() { return getToken(TkcParser.Occupancy, 0); }
		public AttributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_attribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterAttribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitAttribute(this);
		}
	}

	public final AttributeContext attribute() throws RecognitionException {
		AttributeContext _localctx = new AttributeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_attribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
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
		public TerminalNode SwitchState() { return getToken(TkcParser.SwitchState, 0); }
		public TerminalNode Authority() { return getToken(TkcParser.Authority, 0); }
		public TerminalNode CrossingState() { return getToken(TkcParser.CrossingState, 0); }
		public TerminalNode Occupancy() { return getToken(TkcParser.Occupancy, 0); }
		public AssignedattributeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignedattribute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterAssignedattribute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitAssignedattribute(this);
		}
	}

	public final AssignedattributeContext assignedattribute() throws RecognitionException {
		AssignedattributeContext _localctx = new AssignedattributeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignedattribute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
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
		public TerminalNode SwitchStateValue() { return getToken(TkcParser.SwitchStateValue, 0); }
		public TerminalNode BlockID() { return getToken(TkcParser.BlockID, 0); }
		public TerminalNode CrossingStateValue() { return getToken(TkcParser.CrossingStateValue, 0); }
		public TerminalNode SignalStateValue() { return getToken(TkcParser.SignalStateValue, 0); }
		public TerminalNode OccupancyValue() { return getToken(TkcParser.OccupancyValue, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).enterValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof TkcListener ) ((TkcListener)listener).exitValue(this);
		}
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
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
		case 5:
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!W\4\2\t\2\4\3\t\3"+
		"\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3\2"+
		"\5\2\30\n\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\7\3!\n\3\f\3\16\3$\13\3\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\5\5"+
		"8\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\7\7E\n\7\f\7\16\7H\13"+
		"\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\2\4\4\f"+
		"\f\2\4\6\b\n\f\16\20\22\24\2\5\3\2\20\21\3\2\20\23\3\2\5\t\2Q\2\27\3\2"+
		"\2\2\4\33\3\2\2\2\6%\3\2\2\2\b\67\3\2\2\2\n9\3\2\2\2\f?\3\2\2\2\16I\3"+
		"\2\2\2\20P\3\2\2\2\22R\3\2\2\2\24T\3\2\2\2\26\30\5\4\3\2\27\26\3\2\2\2"+
		"\27\30\3\2\2\2\30\31\3\2\2\2\31\32\7\2\2\3\32\3\3\2\2\2\33\34\b\3\1\2"+
		"\34\35\5\6\4\2\35\"\3\2\2\2\36\37\f\3\2\2\37!\5\6\4\2 \36\3\2\2\2!$\3"+
		"\2\2\2\" \3\2\2\2\"#\3\2\2\2#\5\3\2\2\2$\"\3\2\2\2%&\7\13\2\2&\'\7\3\2"+
		"\2\'(\5\b\5\2()\7\4\2\2)*\7\f\2\2*+\5\f\7\2+,\7\r\2\2,-\7\16\2\2-\7\3"+
		"\2\2\2.8\5\n\6\2/\60\5\n\6\2\60\61\7\37\2\2\61\62\5\b\5\2\628\3\2\2\2"+
		"\63\64\5\n\6\2\64\65\7 \2\2\65\66\5\b\5\2\668\3\2\2\2\67.\3\2\2\2\67/"+
		"\3\2\2\2\67\63\3\2\2\28\t\3\2\2\29:\7\t\2\2:;\7\17\2\2;<\5\20\t\2<=\7"+
		"\24\2\2=>\5\24\13\2>\13\3\2\2\2?@\b\7\1\2@A\5\16\b\2AF\3\2\2\2BC\f\3\2"+
		"\2CE\5\16\b\2DB\3\2\2\2EH\3\2\2\2FD\3\2\2\2FG\3\2\2\2G\r\3\2\2\2HF\3\2"+
		"\2\2IJ\7\t\2\2JK\7\17\2\2KL\5\22\n\2LM\7\25\2\2MN\5\24\13\2NO\7\16\2\2"+
		"O\17\3\2\2\2PQ\t\2\2\2Q\21\3\2\2\2RS\t\3\2\2S\23\3\2\2\2TU\t\4\2\2U\25"+
		"\3\2\2\2\6\27\"\67F";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}