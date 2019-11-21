// Generated from Tkc.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class TkcLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "SwitchStateValue", "CrossingStateValue", "SignalStateValue", 
			"OccupancyValue", "BlockID", "Whitespace", "If", "Then", "Endif", "Semicolon", 
			"Period", "Occupancy", "SwitchState", "CrossingState", "Authority", "EEquals", 
			"Equals", "FORK", "MAIN", "UP", "DOWN", "RED", "YELLOW", "GREEN", "OCCUPIED", 
			"UNOCCUPIED", "OR", "AND", "LineComment", "LParen", "RParen", "DIGIT"
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


	public TkcLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Tkc.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2!\u0177\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\3\3\3\3\4\3\4\5\4N\n\4\3\5\3\5\5\5R\n\5\3"+
		"\6\3\6\3\6\5\6W\n\6\3\7\3\7\5\7[\n\7\3\b\3\b\6\b_\n\b\r\b\16\b`\3\t\6"+
		"\td\n\t\r\t\16\te\3\t\3\t\3\n\3\n\3\n\3\n\5\nn\n\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13x\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\5\f\u0084\n\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u009c\n\17"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\5\20\u00b4\n\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u00d0\n\21\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\5\22\u00e4\n\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\5\25\u00f3\n\25\3\26\3\26\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\5\26\u00fd\n\26\3\27\3\27\3\27\3\27\5\27\u0103\n\27\3\30\3\30\3"+
		"\30\3\30\3\30\3\30\3\30\3\30\5\30\u010d\n\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\5\31\u0115\n\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\5\32\u0123\n\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\5\33\u012f\n\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\5\34\u0141\n\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\5\35\u0157\n\35\3\36\3\36\3\36\3\36\5\36\u015d\n\36\3\37\3\37\3"+
		"\37\3\37\3\37\3\37\5\37\u0165\n\37\3 \3 \3 \3 \7 \u016b\n \f \16 \u016e"+
		"\13 \3 \3 \3!\3!\3\"\3\"\3#\3#\2\2$\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\2C\2E\2\3\2\6\3\2C\\\5\2\13"+
		"\f\17\17\"\"\4\2\f\f\17\17\3\2\62;\2\u018d\2\3\3\2\2\2\2\5\3\2\2\2\2\7"+
		"\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2"+
		"\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2"+
		"\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2"+
		"\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2"+
		"\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2"+
		"\3G\3\2\2\2\5I\3\2\2\2\7M\3\2\2\2\tQ\3\2\2\2\13V\3\2\2\2\rZ\3\2\2\2\17"+
		"\\\3\2\2\2\21c\3\2\2\2\23m\3\2\2\2\25w\3\2\2\2\27\u0083\3\2\2\2\31\u0085"+
		"\3\2\2\2\33\u0087\3\2\2\2\35\u009b\3\2\2\2\37\u00b3\3\2\2\2!\u00cf\3\2"+
		"\2\2#\u00e3\3\2\2\2%\u00e5\3\2\2\2\'\u00e8\3\2\2\2)\u00f2\3\2\2\2+\u00fc"+
		"\3\2\2\2-\u0102\3\2\2\2/\u010c\3\2\2\2\61\u0114\3\2\2\2\63\u0122\3\2\2"+
		"\2\65\u012e\3\2\2\2\67\u0140\3\2\2\29\u0156\3\2\2\2;\u015c\3\2\2\2=\u0164"+
		"\3\2\2\2?\u0166\3\2\2\2A\u0171\3\2\2\2C\u0173\3\2\2\2E\u0175\3\2\2\2G"+
		"H\7*\2\2H\4\3\2\2\2IJ\7+\2\2J\6\3\2\2\2KN\5)\25\2LN\5+\26\2MK\3\2\2\2"+
		"ML\3\2\2\2N\b\3\2\2\2OR\5-\27\2PR\5/\30\2QO\3\2\2\2QP\3\2\2\2R\n\3\2\2"+
		"\2SW\5\61\31\2TW\5\63\32\2UW\5\65\33\2VS\3\2\2\2VT\3\2\2\2VU\3\2\2\2W"+
		"\f\3\2\2\2X[\5\67\34\2Y[\59\35\2ZX\3\2\2\2ZY\3\2\2\2[\16\3\2\2\2\\^\t"+
		"\2\2\2]_\5E#\2^]\3\2\2\2_`\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\20\3\2\2\2bd\t"+
		"\3\2\2cb\3\2\2\2de\3\2\2\2ec\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\b\t\2\2h\22"+
		"\3\2\2\2ij\7k\2\2jn\7h\2\2kl\7K\2\2ln\7H\2\2mi\3\2\2\2mk\3\2\2\2n\24\3"+
		"\2\2\2op\7v\2\2pq\7j\2\2qr\7g\2\2rx\7p\2\2st\7V\2\2tu\7J\2\2uv\7G\2\2"+
		"vx\7P\2\2wo\3\2\2\2ws\3\2\2\2x\26\3\2\2\2yz\7g\2\2z{\7p\2\2{|\7f\2\2|"+
		"}\7k\2\2}\u0084\7h\2\2~\177\7G\2\2\177\u0080\7P\2\2\u0080\u0081\7F\2\2"+
		"\u0081\u0082\7K\2\2\u0082\u0084\7H\2\2\u0083y\3\2\2\2\u0083~\3\2\2\2\u0084"+
		"\30\3\2\2\2\u0085\u0086\7=\2\2\u0086\32\3\2\2\2\u0087\u0088\7\60\2\2\u0088"+
		"\34\3\2\2\2\u0089\u008a\7q\2\2\u008a\u008b\7e\2\2\u008b\u008c\7e\2\2\u008c"+
		"\u008d\7w\2\2\u008d\u008e\7r\2\2\u008e\u008f\7c\2\2\u008f\u0090\7p\2\2"+
		"\u0090\u0091\7e\2\2\u0091\u009c\7{\2\2\u0092\u0093\7Q\2\2\u0093\u0094"+
		"\7E\2\2\u0094\u0095\7E\2\2\u0095\u0096\7W\2\2\u0096\u0097\7R\2\2\u0097"+
		"\u0098\7C\2\2\u0098\u0099\7P\2\2\u0099\u009a\7E\2\2\u009a\u009c\7[\2\2"+
		"\u009b\u0089\3\2\2\2\u009b\u0092\3\2\2\2\u009c\36\3\2\2\2\u009d\u009e"+
		"\7u\2\2\u009e\u009f\7y\2\2\u009f\u00a0\7k\2\2\u00a0\u00a1\7v\2\2\u00a1"+
		"\u00a2\7e\2\2\u00a2\u00a3\7j\2\2\u00a3\u00a4\7u\2\2\u00a4\u00a5\7v\2\2"+
		"\u00a5\u00a6\7c\2\2\u00a6\u00a7\7v\2\2\u00a7\u00b4\7g\2\2\u00a8\u00a9"+
		"\7U\2\2\u00a9\u00aa\7Y\2\2\u00aa\u00ab\7K\2\2\u00ab\u00ac\7V\2\2\u00ac"+
		"\u00ad\7E\2\2\u00ad\u00ae\7J\2\2\u00ae\u00af\7U\2\2\u00af\u00b0\7V\2\2"+
		"\u00b0\u00b1\7C\2\2\u00b1\u00b2\7V\2\2\u00b2\u00b4\7G\2\2\u00b3\u009d"+
		"\3\2\2\2\u00b3\u00a8\3\2\2\2\u00b4 \3\2\2\2\u00b5\u00b6\7e\2\2\u00b6\u00b7"+
		"\7t\2\2\u00b7\u00b8\7q\2\2\u00b8\u00b9\7u\2\2\u00b9\u00ba\7u\2\2\u00ba"+
		"\u00bb\7k\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7i\2\2\u00bd\u00be\7u\2\2"+
		"\u00be\u00bf\7v\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7v\2\2\u00c1\u00d0"+
		"\7g\2\2\u00c2\u00c3\7E\2\2\u00c3\u00c4\7T\2\2\u00c4\u00c5\7Q\2\2\u00c5"+
		"\u00c6\7U\2\2\u00c6\u00c7\7U\2\2\u00c7\u00c8\7K\2\2\u00c8\u00c9\7P\2\2"+
		"\u00c9\u00ca\7I\2\2\u00ca\u00cb\7U\2\2\u00cb\u00cc\7V\2\2\u00cc\u00cd"+
		"\7C\2\2\u00cd\u00ce\7V\2\2\u00ce\u00d0\7G\2\2\u00cf\u00b5\3\2\2\2\u00cf"+
		"\u00c2\3\2\2\2\u00d0\"\3\2\2\2\u00d1\u00d2\7c\2\2\u00d2\u00d3\7w\2\2\u00d3"+
		"\u00d4\7v\2\2\u00d4\u00d5\7j\2\2\u00d5\u00d6\7q\2\2\u00d6\u00d7\7t\2\2"+
		"\u00d7\u00d8\7k\2\2\u00d8\u00d9\7v\2\2\u00d9\u00e4\7{\2\2\u00da\u00db"+
		"\7C\2\2\u00db\u00dc\7W\2\2\u00dc\u00dd\7V\2\2\u00dd\u00de\7J\2\2\u00de"+
		"\u00df\7Q\2\2\u00df\u00e0\7T\2\2\u00e0\u00e1\7K\2\2\u00e1\u00e2\7V\2\2"+
		"\u00e2\u00e4\7[\2\2\u00e3\u00d1\3\2\2\2\u00e3\u00da\3\2\2\2\u00e4$\3\2"+
		"\2\2\u00e5\u00e6\7?\2\2\u00e6\u00e7\7?\2\2\u00e7&\3\2\2\2\u00e8\u00e9"+
		"\7?\2\2\u00e9(\3\2\2\2\u00ea\u00eb\7H\2\2\u00eb\u00ec\7Q\2\2\u00ec\u00ed"+
		"\7T\2\2\u00ed\u00f3\7M\2\2\u00ee\u00ef\7h\2\2\u00ef\u00f0\7q\2\2\u00f0"+
		"\u00f1\7t\2\2\u00f1\u00f3\7m\2\2\u00f2\u00ea\3\2\2\2\u00f2\u00ee\3\2\2"+
		"\2\u00f3*\3\2\2\2\u00f4\u00f5\7O\2\2\u00f5\u00f6\7C\2\2\u00f6\u00f7\7"+
		"K\2\2\u00f7\u00fd\7P\2\2\u00f8\u00f9\7o\2\2\u00f9\u00fa\7c\2\2\u00fa\u00fb"+
		"\7k\2\2\u00fb\u00fd\7p\2\2\u00fc\u00f4\3\2\2\2\u00fc\u00f8\3\2\2\2\u00fd"+
		",\3\2\2\2\u00fe\u00ff\7W\2\2\u00ff\u0103\7R\2\2\u0100\u0101\7w\2\2\u0101"+
		"\u0103\7r\2\2\u0102\u00fe\3\2\2\2\u0102\u0100\3\2\2\2\u0103.\3\2\2\2\u0104"+
		"\u0105\7F\2\2\u0105\u0106\7Q\2\2\u0106\u0107\7Y\2\2\u0107\u010d\7P\2\2"+
		"\u0108\u0109\7f\2\2\u0109\u010a\7q\2\2\u010a\u010b\7y\2\2\u010b\u010d"+
		"\7p\2\2\u010c\u0104\3\2\2\2\u010c\u0108\3\2\2\2\u010d\60\3\2\2\2\u010e"+
		"\u010f\7T\2\2\u010f\u0110\7G\2\2\u0110\u0115\7F\2\2\u0111\u0112\7t\2\2"+
		"\u0112\u0113\7g\2\2\u0113\u0115\7f\2\2\u0114\u010e\3\2\2\2\u0114\u0111"+
		"\3\2\2\2\u0115\62\3\2\2\2\u0116\u0117\7[\2\2\u0117\u0118\7G\2\2\u0118"+
		"\u0119\7N\2\2\u0119\u011a\7N\2\2\u011a\u011b\7Q\2\2\u011b\u0123\7Y\2\2"+
		"\u011c\u011d\7{\2\2\u011d\u011e\7g\2\2\u011e\u011f\7n\2\2\u011f\u0120"+
		"\7n\2\2\u0120\u0121\7q\2\2\u0121\u0123\7y\2\2\u0122\u0116\3\2\2\2\u0122"+
		"\u011c\3\2\2\2\u0123\64\3\2\2\2\u0124\u0125\7I\2\2\u0125\u0126\7T\2\2"+
		"\u0126\u0127\7G\2\2\u0127\u0128\7G\2\2\u0128\u012f\7P\2\2\u0129\u012a"+
		"\7i\2\2\u012a\u012b\7t\2\2\u012b\u012c\7g\2\2\u012c\u012d\7g\2\2\u012d"+
		"\u012f\7p\2\2\u012e\u0124\3\2\2\2\u012e\u0129\3\2\2\2\u012f\66\3\2\2\2"+
		"\u0130\u0131\7Q\2\2\u0131\u0132\7E\2\2\u0132\u0133\7E\2\2\u0133\u0134"+
		"\7W\2\2\u0134\u0135\7R\2\2\u0135\u0136\7K\2\2\u0136\u0137\7G\2\2\u0137"+
		"\u0141\7F\2\2\u0138\u0139\7q\2\2\u0139\u013a\7e\2\2\u013a\u013b\7e\2\2"+
		"\u013b\u013c\7w\2\2\u013c\u013d\7r\2\2\u013d\u013e\7k\2\2\u013e\u013f"+
		"\7g\2\2\u013f\u0141\7f\2\2\u0140\u0130\3\2\2\2\u0140\u0138\3\2\2\2\u0141"+
		"8\3\2\2\2\u0142\u0143\7W\2\2\u0143\u0144\7P\2\2\u0144\u0145\7Q\2\2\u0145"+
		"\u0146\7E\2\2\u0146\u0147\7E\2\2\u0147\u0148\7W\2\2\u0148\u0149\7R\2\2"+
		"\u0149\u014a\7K\2\2\u014a\u014b\7G\2\2\u014b\u0157\7F\2\2\u014c\u014d"+
		"\7w\2\2\u014d\u014e\7p\2\2\u014e\u014f\7q\2\2\u014f\u0150\7e\2\2\u0150"+
		"\u0151\7e\2\2\u0151\u0152\7w\2\2\u0152\u0153\7r\2\2\u0153\u0154\7k\2\2"+
		"\u0154\u0155\7g\2\2\u0155\u0157\7f\2\2\u0156\u0142\3\2\2\2\u0156\u014c"+
		"\3\2\2\2\u0157:\3\2\2\2\u0158\u0159\7q\2\2\u0159\u015d\7t\2\2\u015a\u015b"+
		"\7Q\2\2\u015b\u015d\7T\2\2\u015c\u0158\3\2\2\2\u015c\u015a\3\2\2\2\u015d"+
		"<\3\2\2\2\u015e\u015f\7c\2\2\u015f\u0160\7p\2\2\u0160\u0165\7f\2\2\u0161"+
		"\u0162\7C\2\2\u0162\u0163\7P\2\2\u0163\u0165\7F\2\2\u0164\u015e\3\2\2"+
		"\2\u0164\u0161\3\2\2\2\u0165>\3\2\2\2\u0166\u0167\7\61\2\2\u0167\u0168"+
		"\7\61\2\2\u0168\u016c\3\2\2\2\u0169\u016b\n\4\2\2\u016a\u0169\3\2\2\2"+
		"\u016b\u016e\3\2\2\2\u016c\u016a\3\2\2\2\u016c\u016d\3\2\2\2\u016d\u016f"+
		"\3\2\2\2\u016e\u016c\3\2\2\2\u016f\u0170\b \3\2\u0170@\3\2\2\2\u0171\u0172"+
		"\7*\2\2\u0172B\3\2\2\2\u0173\u0174\7+\2\2\u0174D\3\2\2\2\u0175\u0176\t"+
		"\5\2\2\u0176F\3\2\2\2\34\2MQVZ`emw\u0083\u009b\u00b3\u00cf\u00e3\u00f2"+
		"\u00fc\u0102\u010c\u0114\u0122\u012e\u0140\u0156\u015c\u0164\u016c\4\b"+
		"\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}