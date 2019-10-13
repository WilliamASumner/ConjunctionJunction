import java.io.*; // IO
import java.util.*; // Scanner and BufferedReader Class

public class TrackControllerMain
{
    public static void main (String args[]) throws IOException
    {
        // Create a scanner to read from keyboard
        File infile = new File("input.txt");
        Scanner infileScanner = new Scanner(infile);
        String line;
        while (infileScanner.hasNextLine())
        {
            line = infileScanner.nextLine();
        }
        Scanner kbd = new Scanner (System.in);
        // Template Screen Output
        System.out.println("Hello world!");
    }
}
