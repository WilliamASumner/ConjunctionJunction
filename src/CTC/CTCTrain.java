import java.io.*; 
import java.util.*; 
import java.lang.Math;
import java.util.HashMap; 
import java.util.Map;  
public class CTCTrain{
		private String trainName;
		private double curSpeed;
		private String departTime;
		private String curBlkNum;
		private String curAuthority;
		ArrayList<String> schedule;
/*		HashMap<String, String> mapStationBlocks = new HashMap<>();
		private String[] stationNames = {
				"PIONEER","EDGEBROOK","STATION", "WHITED", 
				"SOUTH BANK", "CENTRAL", "INGLEWOOD",
				"OVERBROOK", "GLENBURY", "DORMONT",
				"MT LEBANON", "POPLAR", "CASTLE SHANNON",
				"SHADYSIDE","HERRON AVE","SWISSVILLE", "PENN STATION", 
				"STEEL PLAZA", "FIRST AVE", "STATION SQUARE",
				"SOUTH HILLS JUNCTION"
		};
		
		private String[] stationBlock = {
			
			
		}
*/		
		/**
		 * Default constructor.
		 */
		public CTCTrain(){
			trainName = null;
			curSpeed = 0.0;
			curBlkNum = null;
			curAuthority = null;
			schedule = null;
//			for(int i = 0; i < 21; i++)
//				mapStationBlocks.put()
		}
		
		/**
		 * Set Train name.
		 */		
		public void setName(String newName){
			trainName = newName;
		}

		/**
		 * Set Train speed.
		 */			
		public void setSpeed(String newSpeed){
			curSpeed = Double.parseDouble(newSpeed);
		}

		/**
		 * Set Train cur block num.
		 */	
		public void setCurBlkNum(String newBlkNum){
			curBlkNum = newBlkNum;
		}

		/**
		 * Set Train authority.
		 */			
		public void setAuthority(String authority){
			curAuthority = authority;
		}

		/**
		 * Set Train departure time.
		 */			
		public void setDepartureTime(String departT){
			departTime = departT;
		}	

		/**
		 * Get Train departure name.
		 */			
		public String getTrainName(){
			return trainName;
		}

		/**
		 * Get Train's current block num.
		 */			
		public String getCurBlkNum(){
			return curBlkNum;
		}

		/**
		 * Get Train authority.
		 */	
		public String getCurAuthority(){
			return curAuthority;
		}	

		/**
		 * Get Train speed.
		 */	
		public String getCurSpeed(){
			return ""+curSpeed;
		}			
		
		public boolean isAdjacentBlock(String newBlkNum){
			boolean isAdjacent = false;
			// extract number portion from string
			int newNum = Integer.parseInt(newBlkNum.replaceAll("[^0-9]", ""));
			int curNum = Integer.parseInt(curBlkNum.replaceAll("[^0-9]", ""));
			// if is the same, or adjacent
			if(Math.abs(newNum - curNum) == 0 || Math.abs(newNum - curNum) == 1)
				isAdjacent = true;
			return isAdjacent;
		}
	
		public void progressTrainAtBlock(){
			
		}

		/**
		 * Set Train's schedule.
		 */		
		public void setSchedule(ArrayList<String> sched){
			// set schedule length
			schedule = new ArrayList<String>(5);
			// add stops to schedule
			for(int i = 0; i < sched.size(); i++)
				schedule.add(i, sched.get(i));
			// Set first authority
			this.setAuthority(schedule.get(0));
			// NEED TO CHECK WHICH LINE TRAIN IS ON
			// TO GET SPECIFIC YARD BLOCK
	//		if()
			this.setCurBlkNum("yard");
		}
		
		/**
		 * Search to see if given block is in schedule.
		 */
		public boolean containsBlock(String blk){
			// check to see if schedule contains items
			boolean result = false;
			if(schedule.size() == 0)
				result = false;
			else{
				// Search for block
				for(String element: schedule){
					if(element.equals(blk)){
						result = true;
						break;
					}
				}
			}
			return result;
		}
		
		public String toString(){
			return (trainName + " will dispatch at " 
					+ departTime);
		}
}