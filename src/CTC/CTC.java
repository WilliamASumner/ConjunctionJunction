
public class CTC{
    TrainModel[] dispatchedTrains;
    TrainModel newTrain;
    TrainModel[] queuedTrains;
    int throughput;
    int currentTime;
//  TrackControllerMain trckCntrl;

    public CTC(){
//      trckCntrl = new TrackControllerMain();
    }
    
    public TrainModel addTrain(String trainName, String auth, String spd){
        // instantiate new train with given name, auth, and spd
        double speed = Double.parseDouble(spd);
        newTrain = new TrainModel(trainName, auth, speed);
        // add train to currently dispatched trains array
        //dispatchedTrains[0] = newTrain;
//      trckCntrl.dispatchTrainData(speed, auth);
      return newTrain;
    }
    
/*  
    public void queueNewTrain(String trainName){
        // Queue new train with given name
    }
    
    public void dispatchQueuedTrain(String trainName){
        // dispatch train
    }
    
    public int sendAuthority(int trainsCurBlockID, int newAuthorityBlkID){
        
    }
*/  
}
