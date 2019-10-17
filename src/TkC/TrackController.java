import javafx.stage.Stage;

public class TrackController
{
    String PLCProgram;
    Block[] ControlledBlocks;
    TkM tm = null;

    TrackControllerGUI tkcg;

    public TrackController() {
        PLCProgram = "";
        ControlledBlocks = null;
        initGUI();
    }

    public TrackController(String plc, Block[] blocks, TkM tkm) {
        PLCProgram = plc;
        ControlledBlocks = blocks;
        tm = tkm;
        initGUI();

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

    public void RunPLC() {
        return;
    }

    public boolean SetSwitchState(SwitchBlock s, boolean state) {
        return true;
    }

    public boolean SetCrossingState(CrossingBlock s, boolean state) {
        return true;
    }

    public boolean SetBeaconData(StationBlock s, String data) {
        return true;
    }

    public double AuditSpeed(double suggestedSpeed) {
        return 0.0;
    }

    public double AuditAuthority(int suggestedAuthority) {
        return 0.0;
    }

    public boolean dispatchTrainData(double speed, String authority) {
        System.out.println("IN TRACKCONTROLLER: DISPATCHING TRAIN");
        System.out.println(this.toString());
        tm.setIsOccupied(true);
        tm.setSpeed(speed);
        //tm.setAuthority(authority);

        tkcg.setIsOccupied(true);
        tkcg.setSpeed(speed);
        tkcg.setAuthority(authority);

        updateGUI(authority,speed);

        return true;
    }

    public boolean SendSuggestedSpeed(int blockID, double speed) {
        return true;
    }

    public boolean SendSuggestedAuthority(int blockID, int blockIDAuthority) {
        return true;
    }

}
