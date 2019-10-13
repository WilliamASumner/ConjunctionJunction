public class TrackController
{
    String PLCProgram;
    Block[] ControlledBlocks;

    public TrackController() {
        PLCProgram = "";
        ControlledBlocks = null;
    }

    public TrackController(String plc, Block[] blocks) {
        PLCProgram = plc;
        ControlledBlocks = blocks;
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

    public boolean SendSuggestedSpeed(int blockID) {
        return true;
    }

    public boolean SendSuggestedAuthority(int blockID) {
        return true;
    }

}
