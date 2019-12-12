package cjunction; // conjunction junction package

import java.io.*; 
import javafx.stage.Stage;
import java.util.*; 
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.control.Tooltip;
public class CTC{
    int throughput;
    TrackControllerMain trckCntrl;
    TkM tkm;
    CTC_GUI gui = null;

    ArrayList<CTCTrain> dT = new ArrayList<CTCTrain>(5);
    CTCTrain nT, tempT;
    ArrayList<CTCTrain> qT = new ArrayList<CTCTrain>(5);

    public static HashMap<String,String> stationToBlockRed = new HashMap<String,String>();
    public static HashMap<String,String> stationToBlockGreen = new HashMap<String,String>();
	
    public static HashMap<String,String> blockToStationRed = new HashMap<String,String>();
    public static HashMap<String,String> blockToStationGreen = new HashMap<String,String>();
	
    public void init() {
		
        stationToBlockGreen.put("PIONEER","A2");
        stationToBlockGreen.put("EDGEBROOK","C9");
        stationToBlockGreen.put("GRANT STREET","D16");
        stationToBlockGreen.put("WHITED","F22");
        stationToBlockGreen.put("SOUTH BANK","G31");
        stationToBlockGreen.put("CENTRAL","I39");
        stationToBlockGreen.put("INGLEWOOD","I48");
        stationToBlockGreen.put("OVERBROOK","I57");
        stationToBlockGreen.put("GLENBURY","K65");
        stationToBlockGreen.put("DORMONT","L73");
        stationToBlockGreen.put("MT LEBANON","N77");
        stationToBlockGreen.put("POPLAR","O88");
        stationToBlockGreen.put("CASTLE SHANNON","P96");
        stationToBlockGreen.put("DORMONT2","T105");
        stationToBlockGreen.put("GLENBURY2","U114");
        stationToBlockGreen.put("OVERBROOK2","W123");
        stationToBlockGreen.put("INGLEWOOD2","W132");
        stationToBlockGreen.put("CENTRAL2","W141");
		stationToBlockGreen.put("yard","a0");
		
		blockToStationGreen.put("A2", "PIONEER");
        blockToStationGreen.put("C9", "EDGEBROOK");
        blockToStationGreen.put("D16", "GRANT STREET");
        blockToStationGreen.put("F22", "WHITED");
        blockToStationGreen.put("G31", "SOUTH BANK");
        blockToStationGreen.put("I39", "CENTRAL");
        blockToStationGreen.put("I48", "INGLEWOOD");
        blockToStationGreen.put("I57", "OVERBROOK");
        blockToStationGreen.put("K65", "GLENBURY");
        blockToStationGreen.put("L73", "DORMONT");
        blockToStationGreen.put("N77", "MT LEBANON");
        blockToStationGreen.put("O88", "POPLAR");
        blockToStationGreen.put("P96", "CASTLE SHANNON");
        blockToStationGreen.put("T105", "DORMONT2");
        blockToStationGreen.put("U114", "GLENBURY2");
        blockToStationGreen.put("W123", "OVERBROOK2");
        blockToStationGreen.put("W132", "INGLEWOOD2");
        blockToStationGreen.put("W141", "CENTRAL2");
		blockToStationGreen.put("a0", "yard");
		
		stationToBlockRed.put("SHADYSIDE", "C7");
		stationToBlockRed.put("HERRON AVE", "F16");
		stationToBlockRed.put("SWISSVILLE", "G21");
		stationToBlockRed.put("PENN STATION", "H25");
		stationToBlockRed.put("STEEL PLAZA", "H35");
		stationToBlockRed.put("FIRST AVE", "H45");
		stationToBlockRed.put("STATION SQUARE", "I48");
		stationToBlockRed.put("SOUTH HILLS JUNCTION", "L60");
		stationToBlockRed.put("yard","a0");
		
		blockToStationRed.put("C7", "SHADYSIDE");
		blockToStationRed.put("F16", "HERRON AVE");
		blockToStationRed.put("G21", "SWISSVILLE");
		blockToStationRed.put("H25", "PENN STATION");
		blockToStationRed.put("H35", "STEEL PLAZA");
		blockToStationRed.put("H45", "FIRST AVE");
		blockToStationRed.put("I48", "STATION SQUARE");
		blockToStationRed.put("L60", "SOUTH HILLS JUNCTION");
		blockToStationRed.put("a0", "yard");		
	}

    public CTC() { // for testing
        init();
    }

    @SuppressWarnings("unchecked")
    public void update() {
		if(gui == null)
			return;
		
		//update clock
		gui.stackAddClock.getChildren().remove(gui.digitalClock);
		gui.stackAddClock.getChildren().addAll(gui.updateClock());

//		update throughput
//		tkm.getThroughput();
//		gui.stackAddThroughput.getChildren().remove(gui.throughput);
//		gui.stackAddThroughput.getChildren().addAll(gui.updateThroughput());
		
		// Check 'Select a Queued Train' view for any trains ready to auto dispatch
		if(qT.size() != 0 && !gui.queuedTrainsView.getItems().isEmpty()){
			for(int i = 0; i < qT.size(); i++){
				CTCTrain tempTrain = qT.get(i);
				String dispatchTime = tempTrain.departTime;
				String curTime = gui.digitalClock.getText();
				String[] splitDepartTime = curTime.split(":");
				String hourMinDepartTime = splitDepartTime[0] + ":" + splitDepartTime[1];
				// If train's disptach time equals current time, dispatch it (auto)
				if(dispatchTime.equals(hourMinDepartTime)){
					gui.table.getItems().add(tempTrain);
					// Add to disptached trains
					gui.newCTC.dispatchQueuedTrain(tempTrain);
				
					// Remove 'queuedTrain' from 'Select a Queued Train' view
					gui.queuedItems.remove(tempTrain.toString());
					gui.queuedTrainsView.setItems(gui.queuedItems);
				
					// check to see if there are any trains queued
					if(gui.queuedTrainsView.getItems().isEmpty())
						gui.selectedTrain.getItems().clear();   // no queued trains, remove all 
														//  selected schedules from view
					gui.newCTC.deleteQueuedTrain(tempTrain); // delete train from queued trains
				}
			}
		}
		String tempId = "";
        // Update train locations
        if(dT != null && dT.size() != 0){
			//check if cur block of a train is yard...if so remove
			for(int i = 0; i < dT.size(); i++)
				if(dT.get(i).getCurBlkID().equals("a0"))
					dT.remove(i);
					
				
			// Remove the table items	
            gui.table.getItems().removeAll(gui.table.getItems());
			// Add the table items
            for(int i = 0; i < dT.size(); i++){
				CTCTrain tempT = dT.get(i);		
				
				// Reset blocks after train's path
				String blkType = tempT.getPrevBlkID();	
				if(tempT.getLine().equals("green")){
					// get rectangle in CTC train viewer that matches with train's prev block
					tempId = "#g" + tempT.getPrevBlkID();
					gui.tempRec = (Rectangle)gui.root.lookup(tempId);						
					// Check if block is a station, switch, or track
					if(blockToStationGreen.containsKey(blkType))
						gui.setRectangleToBlockType(blkType, gui.tempRec, "greenstation");
					else if(gui.switchArrayGreen.contains(blkType))
						gui.setRectangleToBlockType(blkType, gui.tempRec, "greenswitch");							
					else
						gui.resetRectangleTrackBlock(gui.tempRec, "green");							
				}
				else{
					// get rectangle in CTC train viewer that matches with train's prev block
					tempId = "#r" + tempT.getPrevBlkID();
//					System.out.println(tempId);
					gui.tempRec = (Rectangle)gui.root.lookup(tempId);	
					// Check if block is a station, switch, or track						
					if(blockToStationRed.containsKey(blkType))
						gui.setRectangleToBlockType(blkType, gui.tempRec, "redstation");
					else if(gui.switchArrayRed.contains(blkType))
						gui.setRectangleToBlockType(blkType, gui.tempRec, "redswitch");							
					else
						gui.resetRectangleTrackBlock(gui.tempRec, "red");	
				}						

				// Update current train location on dynamic map
				if(tempT.getLine().equals("green"))
					tempId = "#g" + tempT.getCurBlkID();// get rectangle that matches with train's cur block
				else
					tempId = "#r" + tempT.getCurBlkID();// get rectangle that matches with train's cur block
				gui.tempRec = (Rectangle)gui.root.lookup(tempId);	
				// Paint the rect a different color
				gui.paintTrainRect(tempT.getName(), gui.tempRec); // BUG HERE
				// Save cur block to change back later
				tempT.setPrevBlkID(tempT.getCurBlkID());				
				
				// update dispatched trains table
                gui.table.getItems().add(tempT);
				
				if(tempT.getAuthority().equals("a0"));//Dont need to update its authority...skip		
				// check if dispatched trains cur authority == cur block
				else if(tempT.getCurBlkID().equals(tempT.getAuthority())){
					//
					// DWELL AT STATION, COUNT FOR 4 mins across all trains
					//TODO: FIXME, ADD DWELL TIME AT STATIONS
//					if(){
//	
//					}
//					else{
//
//					}

					// Set new authority
					tempT.setAuthority(tempT.getNextscheduleStop());
					// Send authority to track controller
					trckCntrl.sendSuggestedAuthority(tempT.getCurBlkID(), tempT.getLine(), tempT.getAuthority());
				}
				// Send authority and cur block constantly
				trckCntrl.sendSuggestedAuthority(tempT.getCurBlkID(), tempT.getLine(), tempT.getAuthority());
			}
        }			
    }

    public void showGUI(Stage primaryStage) {
        if (gui == null)
            gui = new CTC_GUI(this, primaryStage);
		gui.start(primaryStage);
    }


    public void addTrackController(TrackControllerMain tkc) {
        trckCntrl = tkc;
    }

    public void addTrackModel(TkM t) {
        tkm = t;
    }

    public TrackControllerMain getTrckCntrl() {
        return trckCntrl;
    }

    public TkM getTkM() {
        return tkm;
    }
    
    public void repairBlock(String block, String line){
		//TODO
		trckCntrl.repairBlock(block,line);
        //System.out.println("REPAIRING->"+block);
    }
    
    public void closeBlock(String block, String line){
		//TODO
		trckCntrl.closeBlock(block, line);		
        //System.out.println("CLOSING->"+block);
    }
    
    public void switchBlock(String block, String line){
		//TODO
		trckCntrl.flipSwitchState(block, line);				
        System.out.println("SWITCHING->"+block);
    }
    
    /**
     * Add given train to qT 
     */   
    public void queueNewTrain(CTCTrain tempT){
        qT.add(tempT);
    }

    /**
     * Search for given train's .toString() info against queued trains' 
     * .toString() to see if qT contains the train, return found train if so.
     */     
    public CTCTrain findQueuedTrain(String info){
        for(int i = 0; i < qT.size(); i++){
            if(qT.get(i).toString().equals(info)){
                tempT = qT.get(i);
                break;
            }
        }
        return tempT;
    }

    /**
     * Search for given train against queued trains to see 
     * if qT contains the train, delete found train if so.
     */ 
    public void deleteQueuedTrain(CTCTrain deleteT){
        for(int i = 0; i < qT.size(); i++){
            if(qT.get(i).toString().equals(deleteT.toString())){
                qT.remove(i);
                break;
            }
        }       
    }

    /**
     * Add given train to dT, this train will be forwarded to TrkCntrl
     */       
    public void dispatchQueuedTrain(CTCTrain tempT){
        dT.add(tempT);
		System.out.println(tempT.getAuthority());
        trckCntrl.requestNewTrain(tempT.getName(), tempT.getSpeed(), tempT.getAuthority(), tempT.getCurrentBlock());
    }
	
    /**
     * Search for given train's .toString() info against dispatched trains' 
     * .toString() to see if dT contains the train, return found train if so.
     */     
    public CTCTrain findDispatchedTrain(String info){
        for(int i = 0; i < dT.size(); i++){
            if(dT.get(i).toString().equals(info)){
                tempT = dT.get(i);
                break;
            }
        }
        return tempT;
    }	
	
/*    
TODO:FIXME, BLOCK ID STRING AND LINE STRING
    public int sendSpeedAuthority(int trainsCurBlockID, int newAuthorityBlkID){
		trckCntrl.send
    }
*/  
    /**
     * Update the occupancy of a train given new block number.
     * The function first searches currently dispatched trains for the train whose 
     * current block is adjacent to the new block. 
     */ 
    public void updateOccupancy(String newBlkNum){
//TODO: FIXME, ADD LINE COLOR		
        // NEED TO DETERMINE WHICH TRAIN THE GIVEN BLOCK CORRESPONDS TO
        for(int i = 0; i < dT.size(); i++){
            CTCTrain tempTrain = dT.get(i);
            if(tempTrain.isAdjacentBlock(newBlkNum))
                tempTrain.setCurBlkID(newBlkNum);
        }
    }
/*  
    public String getBlockSpeed(String blockID){
        String speedLimit = trckCntrl.getSpeedLimit(blockID); // TODO replace with block get speedlimit
        return speedLimit;
    }*/
}
