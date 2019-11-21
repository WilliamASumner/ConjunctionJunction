import javafx.stage.Stage;
import java.util.ArrayList;

public class TrackController
{
    String PLCProgram;
    String line;
    ArrayList<Block> lineBlocks;
    String name;
    ArrayList<Block> controlledBlocks;

    TkM tm = null;
    TrackControllerMain tkcm;

    public TrackController(String l, String n, ArrayList<Block> blocks,
            TkM tkmodel,TrackControllerMain m) {
        PLCProgram = "";
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

    public boolean ControlsBlock(String blockID) {
        for (Block b: controlledBlocks) {
            if (b.toString().equals(blockID))
                return true;
        }
        return false;
    }

    public String[] GetControlledBlocks() {
        String[] blks = new String[controlledBlocks.size()];
        for (int i = 0; i < controlledBlocks.size(); i++) {
            blks[i] = controlledBlocks.get(i).toString();
        }
        return blks;
    }

    public String GetLine() {
        return line;
    }

    public String GetName() {
        return name;
    }


    public void SetPLC(String plc) {
        PLCProgram = plc;
        // open plc and parse
        RunPLC();
    }

    //public void InitGUI() {
        //tkcg = new TrackControllerGUI();
    //}

    //public void ShowGUI(Stage newStage) {
        //tkcg.start(newStage);
    //}

    //public void UpdateGUI(String BlockAuthority, double speed) {
        //tkcg.update(BlockAuthority,speed);
    //}

    public boolean VerifySafeConditions() {
        return true;
    }

    public void UpdateOnTick() {
        return;
    }

    public void RunPLC() {
        return;
    }

    public boolean SetSwitchState(Block s, boolean state) {
        return true;
    }

    public boolean SetCrossingState(Block s, boolean state) {
        return true;
    }

    public void RequestNewTrain(String name, double speed, String authority) {
        ArrayList<Block> line = null;
        Block startBlock = null;
        if (line.equals("GREEN")) {
            line = tm.getGreen();
            startBlock = line.get(Block.blockIDToNum("J62"));
        }
        else {
            line = tm.getRed();
            startBlock = line.get(Block.blockIDToNum("C9"));
        }
        startBlock.setIsOccupied(true);
        startBlock.setSpeed(speed);
        startBlock.setAuthority(authority);

        tm.createTrain(name,authority,speed);

        return true;
    }

    public boolean SendSuggestedSpeed(String blockID, double speed) {
        return true;
    }

    public boolean SendSuggestedAuthority(String blockID, String blockIDAuthority) {
        return true;
    }

    public double getSpeedLimit(String blockID) {
        return line.get(Block.blockIDToNum(blockID)).getSpeedLimit();
    }

    public void update() {

    }

    public String toString() {
        return name;
    }

}
