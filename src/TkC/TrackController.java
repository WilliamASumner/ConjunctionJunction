import javafx.stage.Stage;
import java.io.FileInputStream;
import java.util.ArrayList;
import org.antlr.v4.runtime.*; // antlr4

public class TrackController
{
    FileInputStream plcProgram;
    String line;
    ArrayList<Block> lineBlocks;
    String name;
    ArrayList<Block> controlledBlocks;
    String mode = "";

    TkM tm = null;
    TrackControllerMain tkcm;

    public TrackController(String l, String n, ArrayList<Block> blocks,
            TkM tkmodel,TrackControllerMain m) {
        plcProgram = null;
        mode = "Automatic";
        line = l;
        name = n;
        controlledBlocks = blocks;
        tm = tkmodel;
        tkcm = m;
        if (line.equals("GREEN")) {
            lineBlocks = tm.getGreen();
        }
        else {
            lineBlocks = tm.getRed();
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


    public void setPLC(FileInputStream plcFile) {
        // open plc and parse
        plcProgram = plcFile;
    }

    public void runPLC() {

        return;
    }

    public Block getBlock(String blockID) {
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
