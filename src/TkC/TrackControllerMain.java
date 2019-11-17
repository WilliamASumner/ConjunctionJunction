// Java includes
import java.util.HashMap;
import java.util.Iterator;
import java.io.*; // IO

public class TrackControllerMain
{
    private HashMap<TrackController,String> controllers;
    private ArrayList<TrackControllerGUI> guis;
    private TrackModel tm;

    private String[] TkNames = {"Tk0","Tk1","Tk2","Tk3","Tk4","Tk5"};
    public currentController = TkNames[0];

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
        guis = null;
        OccupiedBlocks = null;
        TrainPositions = null;
    }

    public void showGUI(Stage window) { // just make a new gui
        TrackController tkc = GetController();
        TrackControllerGUI newgui = 
        guis.add(newgui);
        return

    }

    public String[] GetControllerNames() {
        return TkNames;
    }


    public TrackController SetController(String newController) { // updated by gui, this value is the controller for which the gui will be opened
        currentController = newController;

    }

    public TrackController GetController() {
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

        tk0 = new TrackController("GREEN",TkNames[0],blocks0,tm);
        tk1 = new TrackController("GREEN",TkNames[1],blocks1,tm);
        tk2 = new TrackController("GREEN",TkNames[2],blocks2,tm);
        tk3 = new TrackController("RED",  TkNames[3],blocks3,tm);
        tk4 = new TrackController("RED",  TkNames[4],blocks4,tm);
        tk5 = new TrackController("RED",  TkNames[5],blocks5,tm);

        controllers.put(TkNames[0],tk0);
        controllers.put(TkNames[1],tk1);
        controllers.put(TkNames[2],tk2);
        controllers.put(TkNames[3],tk3);
        controllers.put(TkNames[4],tk4);
        controllers.put(TkNames[5],tk5);
    }

    public boolean SendSuggestedSpeed(String blockID,double speed) {
        return true;
    }

    public boolean SendSuggestedAuthority(String blockID, String authorityID) {
        return true;
    }

    public boolean NotifyNewOccupancy(String blockID, String trainName) {
        //CTC.notifynewoccupancy TODO fix this
        return true;
    }

    public boolean SetSwitchState(SwitchBlock s, boolean SwitchState) {
        return true;
    }

    public boolean SetCrossingState(CrossingBlock c, boolean crossState) {
        return true;
    }

    private TrackController FindController(String blockID) {
        TrackController controller = null;
        for(TrackController tkc : controllers.values()) {
            if (tkc.ControlsBlock(blockID)) {
                controller = tkc;
                break;
            }

        }
        return controller;
    }

    public TrackController BlockToTrackController(String blockID) {
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
