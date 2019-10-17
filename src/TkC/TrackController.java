import javafx.stage.Stage;

public class TrackController
{
    String PLCProgram;
    Block[] ControlledBlocks;

    TrackControllerGUI tkcg;

    public TrackController() {
        PLCProgram = "";
        ControlledBlocks = null;
        initGUI();
    }

    public TrackController(String plc, Block[] blocks) {
        PLCProgram = plc;
        ControlledBlocks = blocks;

    }

    public void initGUI() {
        tkcg = new TrackControllerGUI();
    }

    public void showGUI(Stage newStage) {
        tkcg.start(newStage);
    }

    public void updateGUI() {
        tkcg.update(/* updated values */);
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

    public boolean SendSuggestedSpeedAndAuthority(int blockID, double speed, int blockIDAuthority) {
        return true;
    }

    public boolean SendSuggestedSpeed(int blockID, double speed) {
        return true;
    }

    public boolean SendSuggestedAuthority(int blockID, int blockIDAuthority) {
        return true;
    }

}
