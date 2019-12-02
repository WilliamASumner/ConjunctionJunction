import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.misc.Interval;

public class EvaluatorListener extends TkcBaseListener {

    TkcParser parser;
    EvalList list;
    TrackController tkc;

    IfNode currentIf;
    Condition currentCondition;
    Action currentAction;
    ChainType chainOp = ChainType.NONE;
    boolean parsingError = false;

    public EvaluatorListener(TkcParser p, TrackController t) {

        p=parser;
        list = new EvalList();
        tkc = t;

        currentIf = null;
        currentCondition = null;
        currentAction = null;

        chainOp = ChainType.NONE;
        parsingError = false;
    }

    public boolean encounteredError() {
        return parsingError;
    }

    public EvalList getEvalList() {
        if (!parsingError) {
            return list;
        }
        return null;
    }

    @Override
    public void enterIfstatement(TkcParser.IfstatementContext ctx) {
        currentIf = new IfNode();

    }

    @Override
    public void exitIfstatement(TkcParser.IfstatementContext ctx) {
        if (currentIf != null) {
            list.addIf(currentIf);
        }
        currentIf = null;
    }

    @Override
    public void enterSingleCondition(TkcParser.SingleConditionContext ctx) {
        currentCondition = new Condition();
        currentCondition.setChainOp(ChainType.OR);
    }

    @Override
    public void enterConditionListOr(TkcParser.ConditionListOrContext ctx) {
        currentCondition = new Condition();
        currentCondition.setChainOp(ChainType.OR);
    }

    @Override
    public void enterConditionListAnd(TkcParser.ConditionListAndContext ctx) {
        currentCondition = new Condition();
        currentCondition.setChainOp(ChainType.AND);
    }

    @Override
    public void enterCondition(TkcParser.ConditionContext ctx) {
        currentCondition.setBlock(tkc.getBlock(ctx.BlockID().toString()));
    }

    @Override
    public void exitCondition(TkcParser.ConditionContext ctx) {
        currentIf.addCondition(currentCondition);
        currentCondition = null;
    }

    @Override
    public void exitComparison(TkcParser.ComparisonContext ctx) {
        if (ctx.getText().equals("==")) {
            currentCondition.setComparison(CompType.EQ);
        } else {
            currentCondition.setComparison(CompType.NEQ);
        }
    }
    
    @Override
    public void enterStatement(TkcParser.StatementContext ctx) {
        currentAction = new Action();
        currentAction.setActionType(ActionType.ASSIGN); // for now just assignments
        currentAction.setBlock(tkc.getBlock(ctx.BlockID().toString()));
    }

    @Override
    public void exitStatement(TkcParser.StatementContext ctx) {
        if (currentAction != null) {
            currentIf.addAction(currentAction);
        }
        currentAction = null;
    }

    @Override
    public void exitAttribute(TkcParser.AttributeContext ctx) {
        String value = ctx.getText().toLowerCase();
        if (currentCondition != null ) { // condition
            if (value.equals("occupancy")) {
                currentCondition.setAttrib(AttributeType.OCCUPANCY);
            } else if (value.equals("switchstate")) {
                currentCondition.setAttrib(AttributeType.SWITCHSTATE);
            }
        } 
    }

    @Override
    public void exitAssignedattribute(TkcParser.AssignedattributeContext ctx) {
        String value = ctx.getText().toLowerCase();
        if (currentAction != null ) {
            if (value.equals("occupancy")) {
                currentAction.setAttrib(AttributeType.OCCUPANCY);
            } else if (value.equals("speed")) {
                currentAction.setAttrib(AttributeType.SPEED);
            } else if (value.equals("authority")) {
                currentAction.setAttrib(AttributeType.AUTHORITY);
            } else if (value.equals("crossingstate")) {
                currentAction.setAttrib(AttributeType.CROSSINGSTATE);
            } else if (value.equals("switchstate")) {
                currentAction.setAttrib(AttributeType.SWITCHSTATE);
            }
        }
    }

    @Override
    public void exitValue(TkcParser.ValueContext ctx) {
        String value = ctx.getText().toLowerCase();
        if (currentCondition != null) { // condition value
            if (value.equals("occupied")) {
                currentCondition.setValue(true);
            } else if (value.equals("unoccupied")) {
                currentCondition.setValue(false);
            } else if (value.equals("main")) {
                currentCondition.setValue(true);
            } else if (value.equals("fork")) {
                currentCondition.setValue(false);
            }
        } else if (currentAction != null ) {
            currentAction.setValue(value);
        }
    }
    @Override
    public void visitErrorNode(ErrorNode node) {
        parsingError = true;
    }
}
