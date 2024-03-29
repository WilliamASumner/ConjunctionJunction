package cjunction;
import java.util.ArrayList;
import java.util.Collections;
public class IfNode {
    ArrayList<Condition> conditions;
    ArrayList<Action> actions;

    public IfNode() {
        conditions = new ArrayList<Condition>();
        actions = new ArrayList<Action>();
    }

    public void addCondition(Condition c) {
        conditions.add(c);
    }

    public void addAction(Action a) {
        actions.add(a);
    }

    public void addActions(ArrayList<Action> arr) {
        actions.addAll(arr);
    }

    public void reverseConditions() {
        Collections.reverse(conditions);
    }

    public boolean evaluate() {
        boolean accumulator = false;
        for (Condition condition : conditions) {
            boolean result = condition.evaluate();
            //System.out.println("condition: " + condition + "=" + result);
            switch(condition.getChainOp()) {
                case OR:
                    accumulator = accumulator || result;
                    //System.out.println("OR="+accumulator);
                    break;
                case AND:
                    accumulator = accumulator && result;
                    //System.out.println("AND="+accumulator);
                    break;
                case NONE:
                    accumulator = result;
                    //System.out.println("NONE="+accumulator);
                    break;
                default:
                    //System.out.println("ERROR: bad chain operator");
                    return false;
            }
        }
        return accumulator;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    @Override
    public String toString() {
        return "<IfNode; conditions: " + conditions.size() + "; actions: "+ actions.size() +">";
    }

}
