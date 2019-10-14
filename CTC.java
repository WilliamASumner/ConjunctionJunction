
public class CTC{
	public static void main(String[] args){
		
		Trains[] dispatchedTrains;
		Train newTrain;
		Trains[] queuedTrains;
		int throughput;
		int currentTime;
		
		// main constructor
		public CTC(){
			// run CTC GUI
		//	CTC_GUI runCTCGUI = new CTC_GUI();
			
			// Create 10 trains at start of application
			for(int i =0; i <= 9; i++)
				queuedTrains[i] = new Train();
		}
		
		public void queueNewTrain(String trainName){
			// Queue new train with given name
			queuedTrains[0].setName(trainName);
		}
		
		public void dispatchQueuedTrain(String trainName){
			// dispatch train
			dispatchedTrains[0] = queuedTrains[0];
		}
		
		public int sendAuthority(int trainsCurBlockID, int newAuthorityBlkID){
			for(int i = 0; i <= 9; i++)
				if(dispatchedTrains[i].)
			dispatchedTrains
		}
	}
}