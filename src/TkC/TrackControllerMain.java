// Java includes
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.*; // IO

public class TrackControllerMain
{
    private HashMap<String,TrackController> controllers = null;
    private ArrayList<TrackControllerGUI> guis = null;
    public TkM tm = null;
    private CTC ctc = null;

    private String[] TkNames = {"Tk0","Tk1","Tk2","Tk3","Tk4","Tk5"};
    public String CurrentController = TkNames[0];

    // GREEN LINE
    String[] Tk0List = {"A","B","C","D","E","F","G","Z","Y"};
    String[] Tk1List = {"X","H","I","W","J","V","U","K","T","L"};
    String[] Tk2List = {"S","M","R","N","O","Q","P"};


    // RED LINE
    String[] Tk3List = {"A","B","C","D","E","F"};
    String[] Tk4List = {"G","T","S","R","Q","H","P","O"};
    String[] Tk5List = {"I","J","N","M","L","K"};

    public TrackControllerMain() {
        controllers = new HashMap<String,TrackController>();
        guis = new ArrayList<TrackControllerGUI>();
    }

    // Connection to other modules
    public void setTrackModel(TkM trackModel) {
        tm = trackModel;
    }

    public void setCTC(CTC ctcModule) {
        ctc = ctcModule;
    }

    public void showGUI(Stage window) { // just make a new gui
        TrackController tkc = getCurrController();
        TrackControllerGUI newgui = new TrackControllerGUI(this, tkc);
        guis.add(newgui);
        newgui.start(window);
    }

    public void removeGUI(TrackControllerGUI gui) {
        guis.remove(gui);
    }

    public void stop() {
        for (TrackControllerGUI gui : guis) {
            try {
                gui.stop();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    public String[] getControllerNames() {
        return TkNames;
    }

    public void update() {
        for (TrackController tkc : controllers.values()) {
            tkc.update();
        }
        for (TrackControllerGUI gui : guis) {
            gui.update();
        }
    }


    public void Block(String newController) { // updated by gui, this value is the controller for which the gui will be opened
        CurrentController = newController;

    }

    public TrackController getCurrController() {
        return controllers.get(CurrentController);
    }

    public TrackController getController(String name) {
        return controllers.get(name);
    }

    public void setController(String name) {
        CurrentController = name;
    }

    public void createControllers() {
        // Create definitions of the controllers
        TrackController tk0,tk1,tk2,tk3,tk4,tk5;

        ArrayList<Block> blocks0 = new ArrayList<Block>();
        for (String blockLetter : Tk0List)
            blocks0.addAll(tm.green.getBlocksBySection(blockLetter));

        ArrayList<Block> blocks1 = new ArrayList<Block>();
        for (String blockLetter : Tk1List)
            blocks1.addAll(tm.green.getBlocksBySection(blockLetter));

        ArrayList<Block> blocks2 = new ArrayList<Block>();
        for (String blockLetter : Tk2List)
            blocks2.addAll(tm.green.getBlocksBySection(blockLetter));

        ArrayList<Block> blocks3 = new ArrayList<Block>();
        for (String blockLetter : Tk3List)
            blocks3.addAll(tm.red.getBlocksBySection(blockLetter));

        ArrayList<Block> blocks4 = new ArrayList<Block>();
        for (String blockLetter : Tk4List)
            blocks4.addAll(tm.red.getBlocksBySection(blockLetter));

        ArrayList<Block> blocks5 = new ArrayList<Block>();
        for (String blockLetter : Tk5List)
            blocks5.addAll(tm.red.getBlocksBySection(blockLetter));

        tk0 = new TrackController("green",TkNames[0],blocks0,tm,this);
        tk1 = new TrackController("green",TkNames[1],blocks1,tm,this);
        tk2 = new TrackController("green",TkNames[2],blocks2,tm,this);
        tk3 = new TrackController("red",  TkNames[3],blocks3,tm,this);
        tk4 = new TrackController("red",  TkNames[4],blocks4,tm,this);
        tk5 = new TrackController("red",  TkNames[5],blocks5,tm,this);

        controllers.put(TkNames[0],tk0);
        controllers.put(TkNames[1],tk1);
        controllers.put(TkNames[2],tk2);
        controllers.put(TkNames[3],tk3);
        controllers.put(TkNames[4],tk4);
        controllers.put(TkNames[5],tk5);
    }

    public boolean sendSuggestedSpeed(String blockID,double speed) {
        TrackController t = findController(blockID);
        t.sendSuggestedSpeed(blockID,speed);
        return true;
    }

    public boolean sendSuggestedAuthority(String blockID, String authorityID) {
        TrackController t = findController(blockID);
        t.sendSuggestedAuthority(blockID,authorityID);
        return true;
    }

    public boolean setSwitchState(String blockID, SwitchState s) {
        TrackController t = findController(blockID);
        t.setSwitchState(blockID,s);
        return true;
    }

    public boolean setCrossingState(String blockID, CrossingState c) {
        TrackController t = findController(blockID);
        t.setCrossingState(blockID,c);
        return true;
    }

    private TrackController findController(String blockID) {
        TrackController controller = null;
        for(TrackController tkc : controllers.values()) {
            if (tkc.controlsBlock(blockID)) {
                controller = tkc;
                break;
            }
        }
        return controller;
    }

    public boolean sendSuggestedSpeedAndAuthority(Block b, double speed, Block authorityBlock) {
        b.setAuditedSpeed(speed);
        b.setAuditedAuthority(authorityBlock);
        return true;
    }

    public boolean sendSuggestedSpeed(Block b, double speed) {
        b.setAuditedSpeed(speed);
        return true;
    }

    public boolean sendSuggestedAuthority(Block b, Block authorityBlock) {
        b.setAuditedAuthority(authorityBlock);
        return true;
    }

    public void updateOccupancy(Block blockID) {
        ctc.updateOccupancy(blockID.getBlockID());
    }

    public void requestNewTrain(String name, double speed, String authority, Block startBlock) {
        startBlock.setIsOccupied(true);
        startBlock.setAuditedSpeed(speed);
        startBlock.setAuditedAuthority(tm.getBlock(authority,startBlock.getLineColor()));
        tm.createTrain(name,authority,startBlock,speed,tm.tnC);
    }

}
