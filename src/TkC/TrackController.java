import javafx.stage.Stage;
import java.util.ArrayList;

public class TrackController
{
    String PLCProgram;
    String line;
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

    public boolean DispatchTrainData(double speed, String authority) {
        tm.setIsOccupied(true);
        tm.setSpeed(speed);
        tm.setAuthority(authority);

        //tkcg.setIsOccupied(true);
        //tkcg.setSpeed(speed);
        //tkcg.setAuthority(authority);

        return true;
    }

    public boolean SendSuggestedSpeed(String blockID, double speed) {
        return true;
    }

    public boolean SendSuggestedAuthority(String blockID, String blockIDAuthority) {
        return true;
    }

    public void update() {

    }

    public String toString() {
        return name;
    }

}
