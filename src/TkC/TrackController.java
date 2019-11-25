import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.StringBuilder;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;


public class TrackController
{
    String plcName;
    String line;
    ArrayList<Block> lineBlocks;
    String name;
    ArrayList<Block> controlledBlocks;
    String mode = "";
    StringBuilder log;
    boolean encounteredError;
    boolean plcInitialized;

    EvalList parserOneOutput;
    EvalList parserTwoOutput;

    TkM tm = null;
    TrackControllerMain tkcm;

    public TrackController(String l, String n, ArrayList<Block> blocks,
            TkM tkmodel,TrackControllerMain m) {
        plcName = "None";
        mode = "Automatic";
        line = l;
        name = n;
        controlledBlocks = blocks;
        tkcm = m;

        encounteredError = false;
        plcInitialized = false;

        parserOneOutput = null;
        parserTwoOutput = null;

        log = new StringBuilder();

        if(tkmodel != null) {
            tm = tkmodel;
            if (line.equals("GREEN")) {
                lineBlocks = tm.getGreen();
            }
            else {
                lineBlocks = tm.getRed();
            }
        } else {
            lineBlocks = controlledBlocks;
        }
    }

    public boolean controlsBlock(String blockID) {
        for (Block b: controlledBlocks) {
            if (b.toString().equals(blockID))
                return true;
        }
        return false;
    }

    public String[] getControlledBlocks() {
        String[] blks = new String[controlledBlocks.size()];
        for (int i = 0; i < controlledBlocks.size(); i++) {
            blks[i] = controlledBlocks.get(i).toString();
        }
        return blks;
    }

    public String getLine() {
        return line;
    }

    public String getName() {
        return name;
    }

    public void setMode(String m) {
        mode = m;
    }

    public String getMode(){
        return mode;
    }

    public void setPLCName(String s) {
        plcName = s;
    }

    public String getPLCName() {
        return plcName;
    }


    public boolean setPLC(FileInputStream f) {
        // open plc and parse
        CharStream inFile;
        try {
            inFile = CharStreams.fromStream(f);
        } catch (IOException e) {
            encounteredError = true;
            System.out.println("Error initiliazing filestream");
            return false;
        }

        TkcLexer lex = new TkcLexer(null); // TODO check that this is not too slow
        lex.setInputStream(inFile);        // should be ok... run once a PLC load

        CommonTokenStream toks = new CommonTokenStream(lex);

        TkcParser parser = new TkcParser(toks);
        ParseTree tree = parser.program(); // parse!
        parser.setBuildParseTree(true);

        ParseTreeWalker walker = new ParseTreeWalker();
        EvaluatorListener runner = new EvaluatorListener(parser,this);
        walker.walk(runner,tree);
        parserOneOutput = runner.getEvalList();


        //TkcLeftParser parserTwo = new TkcLeftParser(toks);
        //ParseTree treeTwo = parser.program(); // parse!
        //parser.setBuildParseTree(true);

        //ParseTreeWalker walkerTwo = new ParseTreeWalker();
        //EvaluatorListener runnerTwo = new EvaluatorListener(parser,this);
        //walkerTwo.walk(runnerTwo,tree);
        //parserTwoOutput = runnerTwo.getEvalList();

        if (runner.encounteredError()) { // parsing error
            encounteredError = true;
            plcInitialized = false;
            return false;
        }
        plcInitialized = true;
        return true;
    }

    public void runPLC() {
        if (plcInitialized) { // a valid PLC has been loaded
            ActionList thingsToDo = parserOneOutput.evaluate(this); // find which actions need to be done
            //ActionList thingsToDoCopy = parserTwoOutput.evaluate();
            //if (thingsToDo.equivalentTo(thingsToDoCopy)) {
            //  System.out.println("working voting");
            //  thingsToDo.execute();
            //} else {
            //   System.out.println("ERROR VOTING");
            // }
            thingsToDo.execute();

            return;
        }
    }

    public void addToLog(String s) {
        log.append(s);
    }

    public String showLog() {
        return log.toString();
    }

    public void clearLog() {
        log.setLength(0); // reset to 0
    }

    public Block getBlock(String blockID) {
        if (tkcm == null) {
            System.out.println("ERROR: empty tkcm"); // todo make these exceptions
            Block b = new Block();
            b.setBlockID(blockID);
            return b;
        } else if (tm == null) {
            System.out.println("ERROR: empty trackmodel");
            Block b = new Block();
            b.setBlockID(blockID);
            return b;
        }
        return tkcm.tm.getBlock(blockID,line);
    }

    public boolean setSwitchState(String blockID, SwitchState s) {
        Block b = getBlock(blockID);
        b.setSwitchState(s);
        return true;
    }

    public boolean setCrossingState(String blockID, CrossingState c) {
        Block b = getBlock(blockID);
        b.setCrossingState(c);
        return true;
    }

    public boolean sendSuggestedSpeed(String blockID, double speed) {
        Block b = getBlock(blockID);
        b.setAuditedSpeed(speed);
        return true;
    }

    public boolean sendSuggestedAuthority(String blockID, String blockIDAuthority) {
        Block b = getBlock(blockID);
        Block a = getBlock(blockIDAuthority);
        b.setAuditedAuthority(a);
        return true;
    }

    public double getSpeedLimit(String blockID) {
        return lineBlocks.get(Block.blockIDToNum(blockID)).getSpeedLimit();
    }

    public void update() {
        if (mode.equals("Automatic")) {
            runPLC();
        }
        //run plc
    }

    public String toString() {
        return name;
    }

}
