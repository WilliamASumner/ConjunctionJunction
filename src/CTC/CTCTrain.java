package cjunction; // conjunction junction package

import java.io.*; 
import java.util.*; 
import java.lang.Math;
import java.util.HashMap; 
import java.util.Map;  
public class CTCTrain{
        private String line;
        private String trainName;
        private double curSpeed;
        String departTime;
        private String curBlkID;
		private String prevBlkID;
        public String curAuthority;
		private int totalNumOfStationsLeftToArriveTo;
        private TkM tkm;
        ArrayList<String> schedule;
/*      HashMap<String, String> mapStationBlocks = new HashMap<>();
        private String[] stationNames = {
                "PIONEER","EDGEBROOK","STATION", "WHITED", 
                "SOUTH BANK", "CENTRAL", "INGLEWOOD",
                "OVERBROOK", "GLENBURY", "DORMONT",
                "MT LEBANON", "POPLAR", "CASTLE SHANNON",
                "SHADYSIDE","HERRON AVE","SWISSVILLE", "PENN STATION", 
                "STEEL PLAZA", "FIRST AVE", "STATION SQUARE",
                "SOUTH HILLS JUNCTION"
        };
        stationToBlockGreen
        private String[] stationBlock = {
            
            
        }
*/      
        /**
         * Default constructor.
         */
        public CTCTrain(TkM trackmodel){
            trainName = null;
            curSpeed = 0.0;
            curBlkID = null;
            curAuthority = null;
            schedule = new ArrayList<String>(5);
            tkm = trackmodel;
//          for(int i = 0; i < 21; i++)
//              mapStationBlocks.put()
        }

        /**
         * Set Line color
         */
        public void setLine(String l) {
            line = l;
        }

        /**
         * Get Line color
         */
        public String getLine() {
            return line;
        }
        
        /**
         * Set Train name.
         */     
        public void setName(String newName){
            trainName = newName;
        }

        public String getName() {
            return trainName;
        }

        /**
         * Set Train speed.
         */         
        public void setSpeed(String newSpeed){
            curSpeed = Double.parseDouble(newSpeed);
        }

        public double getSpeed(){
            return curSpeed;
        }

        /**
         * Set Train cur block num.
         */ 
        public void setCurBlkID(String newBlkID){
            curBlkID = newBlkID;
        }

        /**
         * Set Train prev block num.
         */ 
        public void setPrevBlkID(String newBlkID){
            prevBlkID = newBlkID;
        }

        /**
         * Get Block object from current block ID
         */
        public Block getCurrentBlock() {
            return tkm.getBlock(curBlkID,line);
        }

        /**
         * Set Train authority.
         */         
        public void setAuthority(String authority){
            curAuthority = authority;
        }

        public String getAuthority(){
			if(line.equals("green"))
				return CTC.stationToBlockGreen.get(curAuthority);
			else
				return CTC.stationToBlockRed.get(curAuthority);
        }
		
		public String getCurAuthority(){
			return curAuthority;
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
        public String getCurBlkID(){
            return curBlkID;
        }

        /**
         * Get Train's previous block num.
         */         
        public String getPrevBlkID(){
            return prevBlkID;
        }

        /**
         * Get Train speed.
         */ 
        public String getCurSpeed(){
            return ""+curSpeed;
        }           
        
        public boolean isAdjacentBlock(String newBlkNum){
            /*boolean isAdjacent = false;
            // extract number portion from string
            int newNum = Integer.parseInt(newBlkNum.replaceAll("[^0-9]", ""));
            int curNum = Integer.parseInt(curBlkID.replaceAll("[^0-9]", ""));
            // if is the same, or adjacent
            if(Math.abs(newNum - curNum) == 0 || Math.abs(newNum - curNum) == 1)
                isAdjacent = true;
            return isAdjacent; */
            Block curBlk = tkm.getBlock(newBlkNum,line);
            //System.out.println("THIS IS NEW BLOCK");
            //tkm.getBlock(newBlkNum,line).prettyPrint();
            //System.out.println("THIS IS CUR BLOCK");
            //curBlk.prettyPrint();
            if (tkm.getBlock(newBlkNum,line).getNextBlockVal() == curBlk || tkm.getBlock(newBlkNum,line).getPrevBlock() == curBlk)
                System.out.println("MATCHES");
            return (true);
        }

		public String getNextscheduleStop(){
			// Calculates current station train is traveling to
			int curStation = schedule.size() - totalNumOfStationsLeftToArriveTo;
			totalNumOfStationsLeftToArriveTo--;
			return schedule.get(curStation);
		}

        /**
         * Add a station to Train's schedule.
         */ 
		public void addToSchedule(String station, int index){
			if(schedule.contains(station))
				schedule.add(index, station+"2");
			else
				schedule.add(index, station);
		}

        /**
         * Set Train's schedule.
         */     
        public void setSchedule(ArrayList<String> sched){
            schedule = new ArrayList<String>(5);
            // add stops to schedule
            for(int i = 0; i < sched.size(); i++)
                schedule.add(i, sched.get(i));
			finalizeTrain();
        }
		
		public void finalizeTrain(){
			//Add yard as final authority
			// a0 is block for yard
			schedule.add("yard");
			
			totalNumOfStationsLeftToArriveTo = schedule.size();
			
			curSpeed = 11.0;
            // Set first authority
            curAuthority = schedule.get(0);

            // NEED TO CHECK WHICH LINE TRAIN IS ON
            // TO GET SPECIFIC YARD BLOCK
            if (line.equals("green")){
                this.setCurBlkID("J62");
				this.setPrevBlkID("J62");
			}
            else{
                this.setCurBlkID("C9");
				this.setPrevBlkID("C9");	
			}			
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
