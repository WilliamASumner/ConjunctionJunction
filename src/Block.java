package cjunction; // conjunction junction package

import java.util.ArrayList;

public class Block
{
    String LineColor;
    String BlockID;

    double Grade;
    double Elevation;
    double SpeedLimit;
    double Length;
    boolean IsUnderground;

    BlockType type;
    Block nextBlockID;
    Block prevBlockID;

    Block nextBlockIDMain;
    Block prevBlockIDMain;
    Block nextBlockIDFork;
    Block prevBlockIDFork;

    boolean switching; // block is currently switching

    boolean IsBidirectional;
    boolean isOccupied;
    double AuditedSpeed;
    Block AuditedAuthority;
    String stationName;

    SwitchState switchState;
    CrossingState crossingState;

    ErrorState circuit;
    ErrorState power;
    ErrorState signal;
    int numFailures;

    public Block() {
        isOccupied = false; // dummy values
        LineColor = "green";
        BlockID = "A1";
        Grade = 0.0;
        Elevation = 0.0;
        SpeedLimit = 50.0;
        nextBlockID = null;
        prevBlockID = null;
        nextBlockIDMain = null;
        prevBlockIDMain = null;
        nextBlockIDFork = null;
        prevBlockIDFork = null;
        switching = false;
        IsBidirectional = false;
        Length = 0.0;
        AuditedSpeed = 0.0;
        AuditedAuthority = null;
        IsUnderground = false;
        type = BlockType.REGBLOCK;
        stationName = "Uninitialized";
        switchState = SwitchState.MAIN;
        crossingState = CrossingState.UP;

        circuit = ErrorState.GOOD; // start as functioning
        power = ErrorState.GOOD;
        signal = ErrorState.GOOD;
        numFailures = 0;
    }



    public String getStationName() {
      if (this.type == BlockType.STATIONBLOCK) {
        return stationName;
      }
      else {
        return "";
      }
    }

    public void setStationName(String newStationName) {
      if (this.type == BlockType.STATIONBLOCK) {
        stationName = newStationName;
      }
    }

    public void setIsOccupied(boolean newValue) {
        isOccupied = newValue;
    }

    public void setLineColor(String newValue) {
        LineColor = newValue;
    }

    public void setBlockID(String newValue) {
        BlockID = newValue;
    }

    public void setGrade(double newValue) {
        Grade = newValue;
    }

    public void setElevation(double newValue) {
        Elevation = newValue;
    }

    public void setSpeedLimit(double newValue) {
        SpeedLimit = newValue;
    }

    public void  addFailure(String newValue) {
    }

    public void setNextBlock(Block newValue) {
        nextBlockID = newValue;
    }

    public void setPrevBlock(Block newValue) {
        prevBlockID = newValue;
    }

    public void setIsBidirectional(Boolean newValue) {
        IsBidirectional = newValue;
    }

    public void setLength(double newValue) {
        Length = newValue;
    }

    public void setAuditedSpeed(double newValue) {
        AuditedSpeed = newValue;
    }

    public void setAuditedAuthority(Block newValue) {
        AuditedAuthority = newValue;
    }

    public void setIsUnderground(boolean newValue) {
        IsUnderground = newValue;
    }

    public void setType(BlockType newValue) {
        type = newValue;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    String getLineColor() {
        return LineColor;
    }

    String getBlockID() {
        return BlockID;
    }

    double getGrade() {
        return Grade;
    }

    double getElevation() {
        return Elevation;
    }

    double getSpeedLimit() {
        return SpeedLimit;
    }

    boolean isWorking() { // check all possible errors
        return (circuit == ErrorState.GOOD) &&
               (power   == ErrorState.GOOD) &&
               (signal  == ErrorState.GOOD);
    }

    void setFailure(String failure) {
        if (failure.toLowerCase().equals("circuit")) {
            circuit = ErrorState.FAIL;
        } else if (failure.toLowerCase().equals("power")) {
            power = ErrorState.FAIL;
        } else if (failure.toLowerCase().equals("signal")) {
            signal = ErrorState.FAIL;
        } /*else {
            throw Exception(); // invalid setFailure
        } */
    }

    void repairBlock() {
        circuit = ErrorState.GOOD;
        power   = ErrorState.GOOD;
        signal  = ErrorState.GOOD;
    }

    ArrayList<String> getFailures() { // TODO CHANGE
        ArrayList<String> failures = new ArrayList<String>();
        if (!isWorking()) {
            int i = 0;
            if (circuit == ErrorState.FAIL) {
                circuit = ErrorState.FAIL;
                failures.add("circuit");
                i++;
            }
            if (power == ErrorState.FAIL) {
                power = ErrorState.FAIL;
                failures.add("power");
                i++;
            }
            if (signal == ErrorState.FAIL) {
                signal = ErrorState.FAIL;
                failures.add("signal");
            }
        }
        return failures;
    }

    void swapNext() {
        Block temp  = nextBlockID;
        nextBlockID = prevBlockID;
        prevBlockID = temp;
    }

    void adjust(Block incoming) { // flip blocks as train comes
        /*System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        System.out.println("Current Block: " + incoming);
        incoming.prettyPrint();
        System.out.println("---------------------------------------------");
        System.out.println("adjusting " + this);
        prettyPrint();
        System.out.println("nextBlockID:::::: " + getNextBlockVal()); */
        if (incoming == getNextBlockVal() ) { // this is a safe == because we are checking unique blocks
            swapNext();

            /*System.out.println("SWAPPED");
            System.out.println("#############################################");
            System.out.println("NEWVAL");
            prettyPrint();
            System.out.println("#############################################");
            System.out.println("NEW nextBlockID:::::: " + getNextBlockVal());*/
        }
        //System.out.println("---------------------------------------------");
    }

    Block getNextBlockVal() {
        return nextBlockID;
    }

    Block getNextBlock() {
        getNextBlockVal().adjust(this);
        return getNextBlockVal();
    }

    void setNextBlockMain(Block newNextMain) {
        nextBlockIDMain = newNextMain;
    }

    void setPrevBlockMain(Block newPrevMain) {
        prevBlockIDMain = newPrevMain;
    }

    void setNextBlockFork(Block newNextFork) {
        nextBlockIDFork = newNextFork;
    }

    void setPrevBlockFork(Block newPrevFork) {
        prevBlockIDFork = newPrevFork;
    }

    Block getPrevBlock() {
        return prevBlockID;
    }

    Boolean getIsBidirectional() {
        return IsBidirectional;
    }

    double getLength() {
        return Length;
    }

    double getAuditedSpeed() {
        return AuditedSpeed;
    }

    Block getAuditedAuthority() {
        return AuditedAuthority;
    }

    boolean getIsUnderground() {
        return IsUnderground;
    }

    BlockType getType() {
        return type;
    }

    public String toString() {
      return this.getBlockID();
    }

    public static int blockIDToNum(String id){
        return (Integer.parseInt(id.substring(1,id.length())));
    }

    public SwitchState getSwitchState() {
        return switchState;
    }

    public void setSwitching(){
        switching = true;
    }
    public void stopSwitching(){
        switching = false;
    }

    public boolean getSwitching() {
        return switching;
    }

    public void setSwitchState(SwitchState s) {
        if (switchState != s && !isOccupied) { // if allowed to switch and is given new position
            switchState = s;
            switch (s) {
                case MAIN:
                    nextBlockID = nextBlockIDMain;
                    prevBlockID = prevBlockIDMain;
                    break;
                case FORK:
                    nextBlockID = nextBlockIDFork;
                    prevBlockID = prevBlockIDFork;
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }

    public CrossingState getCrossingState() {
        return crossingState;
    }


    public void setCrossingState(CrossingState c) {
         crossingState = c;
    }

    public void prettyPrint() {
        System.out.println("---------- Block " + BlockID + " -------");
        System.out.print("Type: ");
        System.out.println(type);
        System.out.println(LineColor);

        System.out.print("Speed Limit: ");
        System.out.println(String.valueOf(SpeedLimit));

        System.out.print("Grade: ");
        System.out.println(String.valueOf(Grade));
        System.out.print("Elevation: ");
        System.out.println(String.valueOf(Elevation));
        System.out.print("Length: ");
        System.out.println(String.valueOf(Length));
        System.out.print("Is underground: ");
        System.out.println(String.valueOf(IsUnderground));

        System.out.println();
        System.out.print("Next block ID: ");
        System.out.println(nextBlockID);
        System.out.print("Prev block ID: ");
        System.out.println(prevBlockID);


        if(type == BlockType.SWITCHBLOCK) {
            System.out.print("Next block ID (if main): ");
            System.out.println(nextBlockIDMain);
            System.out.print("Prev block ID (if main): ");
            System.out.println(prevBlockIDMain);

            System.out.print("Next block ID (if fork): ");
            System.out.println(nextBlockIDFork);
            System.out.print("Prev block ID (if fork): ");
            System.out.println(prevBlockIDFork);
            System.out.print("Switch State: ");
            System.out.println(switchState);
        }
        System.out.print("Occupied: ");
        System.out.println(isOccupied);

        System.out.println();

        System.out.print("Is bidirectional: ");
        System.out.println(IsBidirectional);
        System.out.print("Occupied: ");
        System.out.println(isOccupied);
        System.out.print("AuditedSpeed: ");
        System.out.println(String.valueOf(AuditedSpeed));
        System.out.print("Audited Authority: ");
        System.out.println(AuditedAuthority);

        System.out.println();

        if (type == BlockType.STATIONBLOCK) {
            System.out.print("Station name: ");
            System.out.println(stationName);
        }

        System.out.println();

        if (type == BlockType.CROSSBLOCK) {
            System.out.print("Crossing State: ");
            System.out.println(crossingState);
        }

        System.out.println();

        System.out.print("Circuit status: ");
        System.out.println(circuit);
        System.out.print("Power status: ");
        System.out.println(power);
        System.out.print("Signal status: ");
        System.out.println(signal);
    }

}
