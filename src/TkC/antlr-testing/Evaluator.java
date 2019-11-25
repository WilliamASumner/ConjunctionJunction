import java.io.IOException;
import org.antlr.v4.runtime.*; // TODO FIX THIS
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.CharStreams;
//import org.antlr.v4.runtime.ANTLRFileStream;
//import org.antlr.v4.runtime.CommonTokenStream;
//import org.antlr.v4.runtime.ParserTree;
//import org.antlr.v4.runtime.ParserTree.ParserTreeWalker;

public class Evaluator extends TkcBaseListener
{
    ActionList actionList;
    TrackController tkc;

    /*public Interpreter(TrackController trkcc) {
        tkc = trkcc;
        actionList = new ActionList(tkc);
    }*/

    public static void main (String args[]) throws IOException {
        // Create a scanner to read from keyboard
        CharStream inFile = CharStreams.fromFileName("test.plc");
        TkcLexer lex = new TkcLexer(null);
        lex.setInputStream(inFile);

        CommonTokenStream toks = new CommonTokenStream(lex);
        //toks.fill(); // what does this do?

        TkcParser parser = new TkcParser(toks);
        ParseTree tree = parser.program();
        parser.setBuildParseTree(true);

        ParseTreeWalker walker = new ParseTreeWalker();
        EvaluatorListener runner = new EvaluatorListener(parser);
        walker.walk(runner,tree);


    }
}
