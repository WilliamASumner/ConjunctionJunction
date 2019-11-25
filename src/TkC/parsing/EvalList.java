import java.util.ArrayList;

public class EvalList
{
    private boolean accumulator;
    private ArrayList<IfNode> ifs;

    public EvalList() {
        ifs = new ArrayList<IfNode>();
    }

    public void addIf(IfNode c) {
        ifs.add(c);
    }

    public ActionList evaluate(TrackController tkc) { // evaluate the program
        System.out.println("evaluating");
        ActionList generatedActions = new ActionList(tkc);
        for (IfNode condition : ifs ) {
            System.out.println("checking " + condition);
            if (condition.evaluate()) {
                System.out.println("adding actions");
                generatedActions.addActions(condition.getActions());
            }
        }
        return generatedActions;
    }

    @Override
    public String toString() {
        String temp = "";
        for (IfNode n : ifs) {
            temp += n + "\n";
        }
        return temp;
    }
}
