package cjunction;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.misc.Interval;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.TerminalNode;

public class EvaluatorListener implements TkcListener,TkcLeftListener {

    TkcParser parser;
    EvalList list;
    TrackController tkc;

    IfNode currentIf;
    Condition currentCondition;
    Action currentAction;
    ChainType chainOp = ChainType.NONE;

    boolean parsingError = false;
    String errMsg;

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

    public String getErrMsg() {
        return errMsg;
    }

    public EvalList getEvalList() {
        if (!parsingError) {
            return list;
        }
        return null;
    }

    @Override
    public void enterProgram(TkcParser.ProgramContext ctx) {}

    @Override
    public void exitProgram(TkcParser.ProgramContext ctx) {}

    @Override
    public void enterIfsequence(TkcParser.IfsequenceContext ctx) {}

    @Override
    public void exitIfsequence(TkcParser.IfsequenceContext ctx) {}

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
    public void exitSingleCondition(TkcParser.SingleConditionContext ctx) {}

    @Override
    public void enterConditionListOr(TkcParser.ConditionListOrContext ctx) {
        currentCondition = new Condition();
        currentCondition.setChainOp(ChainType.OR);
    }

    @Override
    public void exitConditionListOr(TkcParser.ConditionListOrContext ctx) {}

    @Override
    public void enterConditionListAnd(TkcParser.ConditionListAndContext ctx) {
        currentCondition = new Condition();
        currentCondition.setChainOp(ChainType.AND);
    }

    @Override
    public void  exitConditionListAnd(TkcParser.ConditionListAndContext ctx) {}

    @Override
    public void enterCondition(TkcParser.ConditionContext ctx) {
        Block b = tkc.getBlock(ctx.BlockID().toString());
        if (b != null) {
            currentCondition.setBlock(b);
        } else {
            errMsg = "Block " + ctx.BlockID().toString() + " not found";
            parsingError = true;
        }
    }

    @Override
    public void exitCondition(TkcParser.ConditionContext ctx) {
        currentIf.addCondition(currentCondition);
        currentCondition = null;
    }

    @Override
    public void enterStatementlist(TkcParser.StatementlistContext ctx){}

    @Override
    public void exitStatementlist(TkcParser.StatementlistContext ctx) {}

    @Override
    public void enterComparison(TkcParser.ComparisonContext ctx) {}

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
        Block b = tkc.getBlock(ctx.BlockID().toString());
        if (b != null) {
            currentAction.setBlock(b);
        } else {
            errMsg = "Block " + ctx.BlockID().toString() + " not found";
            parsingError = true;
        }
    }

    @Override
    public void exitStatement(TkcParser.StatementContext ctx) {
        if (currentAction != null) {
            currentIf.addAction(currentAction);
        }
        currentAction = null;
    }

    @Override
    public void enterAttribute(TkcParser.AttributeContext ctx) {}

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
    public void enterAssignedattribute(TkcParser.AssignedattributeContext ctx) {}

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
        errMsg = "Error parsing tree";
        parsingError = true;
    }
    @Override public void enterValue(TkcParser.ValueContext ctx) { }
    @Override public void enterEveryRule(ParserRuleContext ctx) { }
    @Override public void exitEveryRule(ParserRuleContext ctx) { }
    @Override public void visitTerminal(TerminalNode node) { }

    @Override
    public void enterProgram(TkcLeftParser.ProgramContext ctx) {}

    @Override
    public void exitProgram(TkcLeftParser.ProgramContext ctx) {}

    @Override
    public void enterIfsequence(TkcLeftParser.IfsequenceContext ctx) {}

    @Override
    public void exitIfsequence(TkcLeftParser.IfsequenceContext ctx) {}

    @Override
    public void enterIfstatement(TkcLeftParser.IfstatementContext ctx) {
        currentIf = new IfNode();
    }

    @Override
    public void exitIfstatement(TkcLeftParser.IfstatementContext ctx) {
        if (currentIf != null) {
            list.addIf(currentIf);
            currentIf = null;
        }
    }

    @Override
    public void enterSingleCondition(TkcLeftParser.SingleConditionContext ctx) {
        currentCondition = new Condition();
        currentCondition.setChainOp(ChainType.OR);
    }

    @Override
    public void exitSingleCondition(TkcLeftParser.SingleConditionContext ctx) {}

    @Override
    public void enterConditionListOr(TkcLeftParser.ConditionListOrContext ctx) {
        currentCondition = new Condition();
        currentCondition.setChainOp(ChainType.OR);
    }

    @Override
    public void exitConditionListOr(TkcLeftParser.ConditionListOrContext ctx) {}

    @Override
    public void enterConditionListAnd(TkcLeftParser.ConditionListAndContext ctx) {
        currentCondition = new Condition();
        currentCondition.setChainOp(ChainType.AND);
    }

    @Override
    public void  exitConditionListAnd(TkcLeftParser.ConditionListAndContext ctx) {}

    @Override
    public void enterCondition(TkcLeftParser.ConditionContext ctx) {
        Block b = tkc.getBlock(ctx.BlockID().toString());
        if (b != null) {
            currentCondition.setBlock(b);
        } else {
            errMsg = "Block " + ctx.BlockID().toString() + " not found";
            parsingError = true;
        }
    }

    @Override
    public void exitCondition(TkcLeftParser.ConditionContext ctx) {
        currentIf.addCondition(currentCondition);
        currentCondition = null;
    }

    @Override
    public void enterStatementlist(TkcLeftParser.StatementlistContext ctx){}

    @Override
    public void exitStatementlist(TkcLeftParser.StatementlistContext ctx) {}

    @Override
    public void enterComparison(TkcLeftParser.ComparisonContext ctx) {}

    @Override
    public void exitComparison(TkcLeftParser.ComparisonContext ctx) {
        if (ctx.getText().equals("==")) {
            currentCondition.setComparison(CompType.EQ);
        } else {
            currentCondition.setComparison(CompType.NEQ);
        }
    }
    
    @Override
    public void enterStatement(TkcLeftParser.StatementContext ctx) {
        currentAction = new Action();
        currentAction.setActionType(ActionType.ASSIGN); // for now just assignments
        currentAction.setBlock(tkc.getBlock(ctx.BlockID().toString()));
    }

    @Override
    public void exitStatement(TkcLeftParser.StatementContext ctx) {
        if (currentAction != null) {
            currentIf.addAction(currentAction);
        }
        currentAction = null;
    }

    @Override
    public void enterAttribute(TkcLeftParser.AttributeContext ctx) {}

    @Override
    public void exitAttribute(TkcLeftParser.AttributeContext ctx) {
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
    public void enterAssignedattribute(TkcLeftParser.AssignedattributeContext ctx) {}

    @Override
    public void exitAssignedattribute(TkcLeftParser.AssignedattributeContext ctx) {
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
    public void exitValue(TkcLeftParser.ValueContext ctx) {
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
    @Override public void enterValue(TkcLeftParser.ValueContext ctx) { }
}
