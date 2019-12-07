
/**
 * Action
 * This class provides the basic unit of function for a program.
 * Every action produces either a boolean value or sets the state
 * of the TrackMap.
 */

public class Action { // hopefully one of a kind
    private Block         block;
    private ActionType    actionType;
    private AttributeType attrib;
    private String        value;
    //private TrackController tkc;

    public Action() {
    }
    public Action(Block blk, ActionType a, AttributeType atr, String v) {
        block = blk;
        actionType = a;
        attrib = atr;
        value = v;
    }

    public void setBlock(Block b) {
        block = b;
    }

    public void setActionType(ActionType a) {
        actionType = a;
    }

    public void setAttrib(AttributeType attr) {
        attrib = attr;
    }

    public void setValue(String val) {
        value = val;
    }

    public Block getBlock() {
        return block;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public AttributeType getAttrib() {
        return attrib;
    }

    public String getValue() {
        return value;
    }

    public boolean equals(Action other) {
        return block == other.getBlock() && // we want the same obj
            other.getActionType().equals(actionType) &&
            other.getAttrib().equals(attrib) &&
            other.getValue().equals(value);
    }

    private boolean getOccupancyVal(String value) {
        value = value.toLowerCase();
        if (value.equals("occupied")) {
            return true;
        } else if (value.equals("unoccupied")) {
            return false;
        }
        // else error!!!! TODO FIXME
        return false;
    }

    private double getSpeedVal(String value) {
        double dval = Double.parseDouble(value);
        if (dval > block.getSpeedLimit()) { // ensure safe speed
            return block.getSpeedLimit();
        }
        return Double.parseDouble(value);
    }

    private Block getAuthorityVal(String value) {
        Block b = null;// TODO add this in
        return b;
    }

    private CrossingState getCrossingVal(String value) {
        value = value.toLowerCase();
        if (value.equals("up")) {
            return CrossingState.UP;
        } else if (value.equals("down")) {
            return CrossingState.DOWN;
        }
        // else error!!!! TODO FIXME
        return CrossingState.DOWN;
    }

    private SwitchState getSwitchVal(String value) {
        value = value.toLowerCase();
        if (value.equals("main")) {
            return SwitchState.MAIN;
        } else if (value.equals("fork")) {
            return SwitchState.FORK;
        }
        // else error!!!! TODO FIXME
        return SwitchState.FORK;
    }

    public void perform() {
        switch(actionType) {
            case ASSIGN:
                switch (attrib) {
                    case OCCUPANCY:
                        block.setIsOccupied(getOccupancyVal(value));
                        break;
                    case SPEED:
                        block.setAuditedSpeed(getSpeedVal(value));
                        break;
                    case AUTHORITY:
                        block.setAuditedAuthority(getAuthorityVal(value));
                        break;
                    case CROSSINGSTATE:
                        block.setCrossingState(getCrossingVal(value));
                        break;
                    case SWITCHSTATE:
                        block.setSwitchState(getSwitchVal(value));
                        break;
                    default:
                        //System.out.println("ERROR: invalid attribute");
                        break;
                }
                break;
            default:
                //System.out.println("ERROR: invalid operation on block");
                break;
        }
    }

    @Override
    public String toString() {
        if (block != null) {
            return block.getBlockID() + '.' + attrib+ " " + actionType + " " + value;
    } else {
            return "NULL" + '.' + attrib +" " + actionType + " " + value;
        }
    }

}
