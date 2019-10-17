
public class CTC{
	public static void main(String[] args){
		
		Trains[] dispatchedTrains;
		Train newTrain;
		Trains[] queuedTrains;
		int throughput;
		int currentTime;
		
		// main constructor
		public CTC(){
			// create trains for future use
			dispatchedTrains = new Trains[10];
		}
		
		public void addTrain(String trainName){
			// instantiate new train with given name
			newTrain = new Train(trainName);
			// add train to currently dispatched trains array
			dispatchedTrains[0] = newTrain;
		}
		
		public void queueNewTrain(String trainName){
			// Queue new train with given name
		}
		
		public void dispatchQueuedTrain(String trainName){
			// dispatch train
			dispatchedTrains[0] = ;
		}
		
		public int sendAuthority(int trainsCurBlockID, int newAuthorityBlkID){
			
		}
	}
}