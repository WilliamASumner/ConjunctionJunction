import javafx.stage.Stage;

public class TrackController
{
    String PLCProgram;
    ArrayList<Block> controlledBlocks;

    TkM tm = null;
    TrackControllerGUI tkcg;

    public TrackController(ArrayList<blocks> blocks) {
        PLCProgram = "";
        controlledBlocks = null;
        initGUI();
    }

    public void setControlledBlocks(ArrayList<Block> blocks) {
        controlledBlocks = blocks;
    }

    public void setTrackModel(TkM trackmodel) {
        tm = trackmodel;
    }

    public void setPLC(String plc) {
        PLCProgram = plc;
        // open plc and parse
        runPLC();
    }

    public void initGUI() {
        tkcg = new TrackControllerGUI();
    }

    public void showGUI(Stage newStage) {
        tkcg.start(newStage);
    }

    public void updateGUI(String BlockAuthority, double speed) {
        tkcg.update(BlockAuthority,speed);
    }

    public boolean VerifySafeConditions() {
        return true;
    }

    public void UpdateOnTick() {
        return;
    }

    public void runPLC() {
        return;
    }

    public boolean SetSwitchState(SwitchBlock s, boolean state) {
        return true;
    }

    public boolean SetCrossingState(CrossingBlock s, boolean state) {
        return true;
    }

    public boolean dispatchTrainData(double speed, String authority) {
        tm.setIsOccupied(true);
        tm.setSpeed(speed);
        tm.setAuthority(authority);

        tkcg.setIsOccupied(true);
        tkcg.setSpeed(speed);
        tkcg.setAuthority(authority);

        updateGUI(authority,speed);

        return true;
    }

    public boolean SendSuggestedSpeed(String blockID, double speed) {
        return true;
    }

    public boolean SendSuggestedAuthority(String blockID, String blockIDAuthority) {
        return true;
    }

}
