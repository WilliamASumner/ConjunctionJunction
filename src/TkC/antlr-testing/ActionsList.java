
import java.util.ArrayList;

public class ActionsList {

    ArrayList<Action> actions;
    TrackController tkc;
    boolean accumulator;

    public ActionsList(TrackController trkCntrl) {
        actions =  new ArrayList<Action>();
        tkc = trkCntrl;
    }

    public boolean equivalent(ActionsList other) {
        return true;
    }

    public void addAction(String blk, String act, String attr, String val) {
        actions.add(new Action(blk,act,attr,val));
    }

    private class Action {
        private String blockID;
        private String action;
        private String attrib;
        private String value;

        public Action(String blk, String act, String attr, String val) {
            blockID = blk;
            action = act;
            attrib = attr;
            value = val;
        }

        public String getBlockID() {
            return blockID;
        }

        public String getAction() {
            return action;
        }
    
        public String getAttrib() {
            return attrib;
        }

        public String getValue() {
            return value;
        }

        public boolean equals(Action other) {
            return other.getAction().equals(action) &&
                   other.getAttrib().equals(attrib) &&
                   other.getValue().equals(value);
        }

        public void perform() {
            Block b = tkc.getBlock(blockID);
            if (action.toLowerCase().equals("assign")) {
                if (attrib.toLowerCase().equals("occupancy"))  {
                    if (value.toLowerCase().equals("occupied"))
                        b.setIsOccupied(true);
                    else
                        b.setIsOccupied(false);
                } else if (attrib.toLowerCase().equals("authority")) {
                    // TODO TO CHECK VALID
                    b.setAuditedAuthority(tkc.getBlockID(value);
                } else if (attrib.toLowerCase().equals("switchstate")) {
                    if (value.toLowerCase().equals("fork") {
                        b.setSwitchState(SwitchState.FORK);
                    } else {
                        b.setSwitchState(SwitchState.MAIN);
                    }
                } else if (attrib.toLowerCase().equals("crossingstate")) {
                    if (value.toLowerCase().equals("up") {
                        b.setSwitchState(SwitchState.UP);
                    } else {
                        b.setSwitchState(SwitchState.DOWN);
                    }
                }
            }

            else if (action.toLowerCase().equals("if")) {
                if (attrib.toLowerCase().equals("occupancy"))  {
                    if (value.toLowerCase().equals("occupied") {
                        accumulator = b.getIsOccupied();
                    } else{ 
                        accumulator = not(b.getIsOccupied());
                    }
                } else if (attrib.toLowerCase().equals("switchstate") {
                    if (value.toLowerCase().equals("fork")) {
                        accumulator = b.getSwitchState.toString().equals("FORK");
                    } else {
                        accumulator = b.getSwitchState.toString().equals("MAIN");
                    }
                }
        }
    }

    public void execute() {
        for (Action a : actions) {
            a.perform();
        }
    }
}
