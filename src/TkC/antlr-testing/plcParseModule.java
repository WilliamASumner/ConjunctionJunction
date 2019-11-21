import java.io.*; // IO
import java.util.*; // Scanner and BufferedReader Class
import org.antlr.v4.runtime.*; // antlr4

public class plcParseModule
{
    public static void main (String args[]) throws IOException
    {
        // Create a scanner to read from keyboard
        ANTLRFileStream inFile = new ANTLRFileStream("test.plc");
        TkcLexer lex = new TkcLexer(null);
        lex.setInputStream(inFile);

        CommonTokenStream toks = new CommonTokenStream(lex);
        toks.fill();

        TkcParser parser = new TkcParser(null);
        parser.setBuildParseTree(true);
        parser.setTokenStream(tokens);

        ParserRulContext tree = parser.program();

    }
}
