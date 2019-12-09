package cjunction; // conjunction junction package

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
    Block nextBlockIDFork;
    Block prevBlockID;

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

    String[] getFailures() {
        String[] failures = null;
        if (!isWorking()) {
            failures = new String[numFailures];
            int i = 0;
            if (circuit == ErrorState.FAIL) {
                failures[i] = "circuit";
                i++;
            }
            if (power == ErrorState.FAIL) {
                power = ErrorState.FAIL;
                failures[i] = "power";
                i++;
            }
            if (signal == ErrorState.FAIL) {
                signal = ErrorState.FAIL;
                failures[i] = "signal";
            }
        }
        return failures;
    }

    Block getNextBlock() {
        return nextBlockID;
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

    public void setSwitchState(SwitchState s) {
        switchState = s;
    }

    public CrossingState getCrossingState() {
        return crossingState;
    }

    
    public void setCrossingState(CrossingState c) {
         crossingState = c;
    }

}
