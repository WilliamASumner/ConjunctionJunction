package cjunction;
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

    public TrackController getController() {
        return tkc;
    }

    public boolean containsAction(Action other) {
        for (Action a: actions) {
            if(a.equals(other)) {
                return true;
            }
        }
        return false;
    }

    public boolean equivalentTo(ActionList other) {
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

    public void addAction(Block blk, ActionType act, AttributeType attr, String val) {
        actions.add(new Action(blk,act,attr,val));
    }

    public void addActions(ArrayList<Action> acts) {
        actions.addAll(acts);
    }

    public void execute() {
        for (Action a : actions) {
            a.perform();
            //tkc.addToLog(a.toString());
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
