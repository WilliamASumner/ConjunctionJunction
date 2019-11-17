// Java includes
import java.util.HashMap;
import java.io.*; // IO

public class TrackControllerMain
{
    private HashMap<TrackController,String> controllers;
    private String currentController = "Tk0";
    private TrackModel tm;

    // GREEN LINE
    String[] Tk0List = {"A","B","C","D","E","F","G","Z","Y"};
    String[] Tk1List = {"X","H","I","W","J","V","U","K","T","L"};
    String[] Tk2List = {"S","M","R","N","O","Q","P"};


    // RED LINE
    String[] Tk3List = {"A","B","C","D","E","F"};
    String[] Tk4List = {"G","T","S","R","Q","H","P","O"};
    String[] Tk5List = {"I","J","N","M","L","K"};

    public TrackControllerMain() {
        currentBlock = null;
        controllers = new HashMap<TrackController,String>();
        OccupiedBlocks = null;
        TrainPositions = null;
    }
    public TrackController setController(String newController) { // updated by gui
        currentController = newController;

    }

    public TrackController getController() {
        return controllers.get(currentController);
    }

    public void createControllers() {
        // Create definitions of the controllers
        TrackController tk0,tk1,tk2,tk3,tk4,tk5;

        ArrayList<Block> blocks0 = ArrayList<Block>();
        for (String blockLetter : Tk0List)
            blocks.append(tm.getBlocks(blockLetter,"GREEN"));

        ArrayList<Block> blocks1 = ArrayList<Block>();
        for (String blockLetter : Tk1List)
            blocks.append(tm.getBlocks(blockLetter,"GREEN"));

        ArrayList<Block> blocks2 = ArrayList<Block>();
        for (String blockLetter : Tk2List)
            blocks.append(tm.getBlocks(blockLetter,"GREEN"));

        ArrayList<Block> blocks3 = ArrayList<Block>();
        for (String blockLetter : Tk3List)
            blocks.append(tm.getBlocks(blockLetter,"RED"));

        ArrayList<Block> blocks4 = ArrayList<Block>();
        for (String blockLetter : Tk4List)
            blocks.append(tm.getBlocks(blockLetter,"RED"));

        ArrayList<Block> blocks5 = ArrayList<Block>();
        for (String blockLetter : Tk5List)
            blocks.append(tm.getBlocks(blockLetter,"RED"));

        tk0 = new TrackController(blocks0);
        tk1 = new TrackController(blocks1);
        tk2 = new TrackController(blocks2);
        tk3 = new TrackController(blocks3);
        tk4 = new TrackController(blocks4);
        tk5 = new TrackController(blocks5);

        controllers.put("Tk0",tk0);
        controllers.put("Tk1",tk1);
        controllers.put("Tk2",tk2);
        controllers.put("Tk3",tk3);
        controllers.put("Tk4",tk4);
        controllers.put("Tk5",tk5);
    }

    public boolean SendSuggestedSpeed(String BlockID,double speed) {
        return true;
    }

    public boolean SendSuggestedAuthority(String BlockID, String authorityID) {
        return true;
    }

    public boolean NotifyNewOccupancy(String BlockID, String trainName) {
        return true;
    }

    public boolean SetSwitchState(SwitchBlock s, boolean SwitchState) {
        return true;
    }

    public boolean SetCrossingState(CrossingBlock c, boolean crossState) {
        return true;
    }

    private TrackController FindController(String trainName) {
        return null;
    }

    public TrackController BlockToTrackController(String BlockID) {
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
