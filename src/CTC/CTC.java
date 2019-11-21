import java.io.*; 
import java.util.*; 

public class CTC{
    TrainModel[] dispatchedTrains;
    TrainModel newTrain;
    TrainModel[] queuedTrains;
    int throughput;
    int currentTime;
    TrackControllerMain trckCntrl;
	
	ArrayList<CTCTrain> dT = new ArrayList<CTCTrain>(5);
	CTCTrain nT, tempT;
	ArrayList<CTCTrain> qT = new ArrayList<CTCTrain>(5);

    public CTC(TrackControllerMain tkcm){
        dispatchedTrains = new TrainModel[1];
        trckCntrl = tkcm;
    }
    public CTC() { // for testing
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
				tempTrain.setCurBlkNum(newBlkNum);
		}
	}
/*	
	public String getBlockSpeed(String blockID){
		String speedLimit = trckCntrl.getSpeedLimit(blockID);
		return speedLimit;
	}*/
}
