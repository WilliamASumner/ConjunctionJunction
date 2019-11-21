import java.io.*; 
import javafx.stage.Stage;
import java.util.*; 

public class CTC{
    TrainModel[] dispatchedTrains;
    TrainModel newTrain;
    TrainModel[] queuedTrains;
    int throughput;
    int currentTime;
    TrackControllerMain trckCntrl;
    TkM tkm;
    CTC_GUI gui = null;



    public static HashMap<String,String> stationToBlockRed = new HashMap<String,String>();
    public static HashMap<String,String> stationToBlockGreen = new HashMap<String,String>();
    public void init() {
        stationToBlockGreen.put("PIONEER","A2");
        stationToBlockGreen.put("EDGEBROOK","C9");
        stationToBlockGreen.put("STATION","D16");
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
    }

    
    ArrayList<CTCTrain> dT = new ArrayList<CTCTrain>(5);
    CTCTrain nT, tempT;
    ArrayList<CTCTrain> qT = new ArrayList<CTCTrain>(5);

    public CTC() { // for testing
        init();
    }

    public void update() {
        // Update train locations
        if(dT != null && dT.size() != 0){
            gui.table.getItems().removeAll(gui.table.getItems());
            for(int i = 0; i < dT.size(); i++)
                gui.table.getItems().add(dT.get(i));
        }
        //gui.XXX
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
    
    public void repairBlock(String block){
        
    }
    
    public void closeBlock(String block){
        
    }
    
    public void switchBlock(String block){
        
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
        System.out.println("TRACK CONTROL IS" + trckCntrl);
        System.out.println("TEMPT CONTROL IS" + tempT);
        
        trckCntrl.requestNewTrain(tempT.getName(), tempT.getSpeed(), tempT.getAuthority(), tempT.getCurrentBlock());
    }
/*    
    public int sendSpeedAuthority(int trainsCurBlockID, int newAuthorityBlkID){

    }
*/  
    /**
     * Update the occupancy of a train given new block number.
     * The function first searches currently dispatched trains for the train whose 
     * current block is adjacent to the new block. 
     */ 
    public void updateOccupancy(String newBlkNum){
        // NEED TO DETERMINE WHICH TRAIN THE GIVEN BLOCK CORRESPONDS TO
        for(int i = 0; i < dT.size(); i++){
            CTCTrain tempTrain = dT.get(i);
            if(tempTrain.isAdjacentBlock(newBlkNum))
                tempTrain.setCurBlkID(newBlkNum);
        }
    }
/*  
    public String getBlockSpeed(String blockID){
        String speedLimit = trckCntrl.getSpeedLimit(blockID);
        return speedLimit;
    }*/
}
