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
    String[] failures;
    String nextBlockID;
    String prevBlockID;
    Boolean IsBidirectional;
    double Length;
    double AuditedSpeed;
    Block AuditedAuthority;
    boolean IsUnderground;
    BlockType type;

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
        failures[0] = newValue;
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

    String[] getFailures() {
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

}
