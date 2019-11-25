import java.util.ArrayList;

/**
 * ActionList
 * A list of assignments or evaluations to perform, generated from a plc program.
 */

public class ActionList {

    ArrayList<Action> actions;
    TrackController tkc;
    boolean accumulator;

    public ActionList(TrackController trkCntrl) {
        actions =  new ArrayList<Action>();
        tkc = trkCntrl;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public boolean containsAction(Action other) {
        for (Action a: actions) {
            if(a.equals(other)) {
                return true;
            }
        }
        return false;
    }

    public boolean equivalent(ActionList other) {
        if (actions.size() != other.getActions().size())
            return false;
        int foundActions = 0;
        for (Action a : actions) {
            if (other.containsAction(a)) {
                foundActions++;
            }
        }

        if (foundActions == actions.size()) {
            return true;
        }

        return false;
    }

    public void addAction(String blk, ActionType act, AttributeType attr, String val) {
        actions.add(new Action(blk,act,attr,val));
    }

    /**
     * Action
     * This class provides the basic unit of function for a program.
     * Every action produces either a boolean value or sets the state
     * of the TrackMap.
     */

    private class Action {
        private String        blockID;
        private ActionType    actionType;
        private AttributeType attrib;
        private String        value;

        public Action(String blk, ActionType actT, AttributeType attr, String val) {
            blockID = blk;
            actionType = actT;
            attrib = attr;
            value = val;
        }

        public String getBlockID() {
            return blockID;
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
            return other.getActionType().equals(actionType) &&
                other.getAttrib().equals(attrib) &&
                other.getValue().equals(value);
        }

        public void perform() {
            Block b = tkc.getBlock(blockID);
            switch(actionType) {
                case ASSIGN:
                    break;
                case COMPARE:
                    break;
                default:
                    break;
            }

        }

        public void execute() {
            accumulator = false; // initialize accumulator
            for (Action a : actions) {
                a.perform();
            }
        }

        @Override
        public String toString() {
            return blockID + '.' + attrib + actionType + value;
        }

    }

    @Override
    public String toString() {
        String temp = "ActionList: {";
        for (Action a: actions) {
            temp += a.toString() + "\n";
        }
        temp += "}";
        return temp;
    }
}
