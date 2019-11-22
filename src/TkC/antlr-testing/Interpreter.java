public class Interpreter extends TkcBaseListener
{
    ActionsList actionList;
    TrackController tkc;

    public Interpreter(TrackController trkcc) {
        tkc = trkcc;
        actionList = new ActionsList(tkc);
    }

    public ActionsList getList() {
        return actionList;
    }

    @Override
    public void enterProgram(TkcParser.ProgramContext ctx) {

    }
    @Override
    public void exitProgram(TkcParser.ProgramContext ctx) {

    }
    @Override
    public void enterIfsequence(TkcParser.IfsequenceContext ctx) {

    }
    @Override
    public void exitIfsequence(TkcParser.IfsequenceContext ctx) {

    }
    @Override
    public void enterIfstatement(TkcParser.IfstatementContext ctx) {

    }
    @Override
    public void exitIfstatement(TkcParser.IfstatementContext ctx) {

    }
    @Override
    public void enterCondition(TkcParser.ConditionContext ctx) {

    }
    @Override
    public void exitCondition(TkcParser.ConditionContext ctx) {

    }
    @Override
    public void enterStatementlist(TkcParser.StatementlistContext ctx) {

    }
    @Override
    public void exitStatementlist(TkcParser.StatementlistContext ctx) {

    }
    @Override
    public void enterStatement(TkcParser.StatementContext ctx) {

    }
    @Override
    public void exitStatement(TkcParser.StatementContext ctx) {

    }
    @Override
    public void enterAttribute(TkcParser.AttributeContext ctx) {

    }
    @Override
    public void exitAttribute(TkcParser.AttributeContext ctx) {

    }
    @Override
    public void enterAssignedattribute(TkcParser.AssignedattributeContext ctx) {

    }
    @Override
    public void exitAssignedattribute(TkcParser.AssignedattributeContext ctx) {

    }
    @Override
    public void enterValue(TkcParser.ValueContext ctx) {

    }
    @Override
    public void exitValue(TkcParser.ValueContext ctx) {

    }
}
