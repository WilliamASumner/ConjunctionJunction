import java.io.*; // IO
import java.util.*; // Scanner and BufferedReader Class

public class Block
{
    boolean isOccupied;
    String LineColor;
    String BlockID;
    double Grade;
    double Elevation;
    double SpeedLimit;
    ArrayList<String> failures;
    String nextBlockID;
    String prevBlockID;
    Boolean IsBidirectional;
    double Length;
    double AuditedSpeed;
    String AuditedAuthority;
    boolean IsUnderground;
    BlockType type;
    String stationName;

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

    public Block() { // empty for testing
        LineColor = "RED";
        BlockID = "A1";
        Grade = 0.0;
        Elevation = 0.0;
        SpeedLimit = 0.0;
        failures = new ArrayList<String>();
        nextBlockID = "A2";
        prevBlockID = "A3";
        IsBidirectional = true;
        Length = 1.0;
        AuditedSpeed = 50.0;
        AuditedAuthority = "G1";
        IsUnderground = true;
        type = BlockType.REGBLOCK;
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

    public void  setFailure(String newValue) {
        failures.add(newValue);
    }

    public void resetFailures() {
        failures.clear();
    }

    public void removeFailure(String failure) {
        failures.remove(failure);
    }
    public void setNextBlockID(String newValue) {
        nextBlockID = newValue;
    }

    public void setPrevBlockID(String newValue) {
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

    public void setAuditedAuthority(String newValue) {
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

    ArrayList<String> getFailures() {
        return failures;
    }

    String getNextBlockID() {
        return nextBlockID;
    }

    String getPrevBlockID() {
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

    String getAuditedAuthority() {
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

    public static int blockIDToNum(String ID) {
        return Integer.parseInt(ID.substring(1,ID.length()));
    }
}
