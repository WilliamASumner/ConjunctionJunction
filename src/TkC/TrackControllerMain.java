// Java includes
import java.util.HashMap;
import java.io.*; // IO

public class TrackControllerMain
{
    Block currentBlock;
    TrackController[] controllers;
    Block[] OccupiedBlocks;
    HashMap TrainPositions;
    public TrackControllerMain() {
        currentBlock = null;
        controllers = new TrackController[3];
        OccupiedBlocks = null;
        TrainPositions = null;
    }
    private void addController(TrackController tkc) {
        controllers[0] = tkc;
    }
    public TrackController getController() { // TODO: change this into finding a controller
        return controllers[0];
    }

    public TrackController createTrackController() {
        controllers[0] = new TrackController();
        return controllers[0];
    }
    public TrackController createTrackController(String plc, Block[] blocks,TkM tkm ) {
        controllers[0] = new TrackController(plc,blocks,tkm);
        return controllers[0];
    }

    public boolean SendSuggestedSpeed(String trainName,double speed) {
        return true;
    }

    public boolean SendSuggestedAuthority(String trainName, Block authority) {
        return true;
    }

    public boolean NotifyNewOccupancy(Block b, String trainName) {
        return true;
    }

    public boolean SetSwitchState(SwitchBlock s, boolean SwitchState) {
        return true;
    }

    public boolean SetCrossingState(CrossingBlock c, boolean crossState) {
        return true;
    }

    public boolean SetBeaconData(StationBlock s, String data) {
        return true;
    }

    private TrackController FindController(String trainName) {
        return null;
    }

    public Block ConvertNameToBlock(String trainName) {
        return null;
    }

    public TrackController BlockToTrackController(Block b) {
        return null;
    }

    public boolean UpdateOccupiedBlocks() {
        return true;
    }
    
    public boolean VerifySafeConditions() {
        return true;
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

    public boolean dispatchTrainData(double speed, String authority) {
        if (controllers[0] == null)
            createTrackController();
        controllers[0].dispatchTrainData(speed,authority);
        return true;
    }


    

}
