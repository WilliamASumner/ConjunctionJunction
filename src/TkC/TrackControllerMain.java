package cjunction;
// // conjunction junction package
//
// Java includes
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;

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
        // track model did not implement expected switch functionality at all
        // this code is to make this project work
        // Green line blocks
        // Switches at N77, C12, G30
        // J58, J62 are to the yard
        //
        Block yard = tm.getBlock("A0","green");

        System.out.println("HERE setting blocks");

        // 
        Block M75  = tm.getBlock("M75","green");
        Block M76  = tm.getBlock("M76","green"); // switch
        Block N77  = tm.getBlock("N77","green");
        Block R101 = tm.getBlock("R101","green");

        M75.setNextBlock(M76); // one way into the switch
        M76.setNextBlock(N77); // if main
        M76.setNextBlockFork(R101); // if fork
        N77.setNextBlock(R101);

        Block C11 =  tm.getBlock("C11","green");
        Block C12 = tm.getBlock("C12","green"); // switch
        Block D13 = tm.getBlock("D13","green");
        Block A1  = tm.getBlock("A1","green");

        D13.setNextBlock(C12);
        C12.setNextBlock(C11);
        C12.setNextBlockFork(D13);
        A1.setNextBlock(C12);


        Block F28  = tm.getBlock("F28","green");
        Block G29  = tm.getBlock("G29","green"); // switch
        Block G30  = tm.getBlock("G30","green");
        Block Z150 = tm.getBlock("Z150","green");

        //F28.setNextBlock(G29);
        G29.setNextBlock(G30);
        G29.setNextBlockFork(F28);
        Z150.setNextBlock(G29);

        Block N85   = tm.getBlock("N85","green");
        Block O86   = tm.getBlock("O86","green"); // switch
        Block O87   = tm.getBlock("O87","green");
        Block Q100  = tm.getBlock("Q100","green");

        //N85.setNextBlock(O86);
        O86.setNextBlock(O87);
        O86.setNextBlockFork(Q100);
        Q100.setNextBlock(Q100);

        Block J58 = tm.getBlock("J58","green"); // switch to yard
        J58.setNextBlock(yard);

        Block J62 = tm.getBlock("J62","green"); // switch from yard
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

        ArrayList<Block> blocks0 = new ArrayList<Block>(); // construct block lists
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

        tk0 = new TrackController("green",TkNames[0],blocks0,tm,this); // create the controllers
        tk1 = new TrackController("green",TkNames[1],blocks1,tm,this);
        tk2 = new TrackController("green",TkNames[2],blocks2,tm,this);
        tk3 = new TrackController("red",  TkNames[3],blocks3,tm,this);
        tk4 = new TrackController("red",  TkNames[4],blocks4,tm,this);
        tk5 = new TrackController("red",  TkNames[5],blocks5,tm,this);

        controllers.put(TkNames[0],tk0); // save the controllers
        controllers.put(TkNames[1],tk1);
        controllers.put(TkNames[2],tk2);
        controllers.put(TkNames[3],tk3);
        controllers.put(TkNames[4],tk4);
        controllers.put(TkNames[5],tk5);
    }

    public boolean sendSuggestedSpeed(String blockID, String line, double speed) {
        TrackController t = findController(blockID,line);
        if (t != null) {
            t.sendSuggestedSpeed(blockID,speed);
            return true;
        }
        return false;
    }

    public boolean sendSuggestedAuthority(String blockID, String line, String authorityID) {
        TrackController t = findController(blockID,line);
        if (t != null) {
            t.sendSuggestedAuthority(blockID,authorityID);
            return true;
        }
        return false;
    }

    public boolean setSwitchState(String blockID, String line, SwitchState s) {
        TrackController t = findController(blockID,line);
        if (t != null) {
            t.setSwitchState(blockID,s);
            return true;
        }
        return false;
    }

    public boolean repairBlock(String blockID, String line) {
        TrackController t = findController(blockID,line);
        if (t != null) {
            t.repairBlock(blockID);
        }
        return true;
    }

    public boolean closeBlock(String blockID, String line) {
        TrackController t = findController(blockID,line);
        if (t != null) {
            t.closeBlock(blockID);
        }
        return true;
    }

    private TrackController findController(String blockID, String line) {
        TrackController controller = null;
        for(TrackController tkc : controllers.values()) {
            if (tkc.controlsBlock(blockID) && tkc.getLine().equals(line)) {
                controller = tkc;
                break;
            }
        }
        return controller;
    }

    public boolean sendSuggestedSpeedAndAuthority(String blockID, String line, double speed, Block authorityBlock) {
        Block b = tm.getBlock(blockID,line);
        b.setAuditedSpeed(speed);
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
