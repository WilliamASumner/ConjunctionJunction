package cjunction;
import java.io.IOException;
import java.util.ArrayList;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

/**Evaluator
 * A simple test class for evaluating plc programs.
 */

public class Evaluator extends TkcBaseListener
{

    public static void main (String args[]) throws IOException {

        ArrayList<Block> dummyMap = new ArrayList<Block>();
        RandomBlockGenerator g = new RandomBlockGenerator(100);
        for (int i = 0; i < 100; i++) {
            dummyMap.add(g.generateBlock());
        }

        CharStream inFile = CharStreams.fromFileName("rsrc/test.plc");
        TkcLexer lex = new TkcLexer(null);
        lex.setInputStream(inFile);

        TrackController tkc = // TODO fix this line
            new TrackController("green", "Tk0", dummyMap,null,null);

        CommonTokenStream toks = new CommonTokenStream(lex);

        TkcParser parser = new TkcParser(toks);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.program(); // parse!

        ParseTreeWalker walker = new ParseTreeWalker();
        EvaluatorListener runner = new EvaluatorListener(parser,tkc);
        walker.walk(runner,tree);

        if (runner.encounteredError()) {
            //System.out.println("ERROR PARSING");
            return;
        }
        inFile.seek(0);
        tree = parser.program(); // parse!
        walker = new ParseTreeWalker();
        runner = new EvaluatorListener(parser,tkc);
        walker.walk(runner,tree);

        EvalList parserOneOutput = runner.getEvalList();
        ActionList thingsToDo = parserOneOutput.evaluate(tkc); // find which actions need to be done
        //EvalList parserTwoOutput = runnerTwo.getEvalList(tkc);
        //ActionList thingsToDoCopy = parserTwoOutput.evaluate();
        //if (thingsToDo.equivalentTo(thingsToDoCopy)) {
        ////  System.out.println("working voting");
        //  thingsToDo.execute();
        //} else {
        ////   System.out.println("ERROR VOTING");
        // }
        //System.out.println(thingsToDo);
        thingsToDo.execute();

    }
}
