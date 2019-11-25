import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.Interval;

public class EvaluatorListener extends TkcBaseListener {

    TkcParser parser;

    public EvaluatorListener(TkcParser p) {

        p=parser;
    }


    @Override
    public void enterProgram(TkcParser.ProgramContext ctx) {
        System.out.println("Entering Program");
    }

    @Override
    public void exitProgram(TkcParser.ProgramContext ctx) {
        System.out.println("Exiting Program");
    }

    /*@Override
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
    public void enterConditionlist(TkcParser.ConditionlistContext ctx) {
    }

    @Override
    public void exitConditionlist(TkcParser.ConditionlistContext ctx) {
    }*/

    @Override
    public void enterCondition(TkcParser.ConditionContext ctx) {
        System.out.println("Condition: ");
        System.out.println("Condition: " + ctx.BlockID());
        System.out.println("Attribute: " + ctx.attribute());
        System.out.println("value: " + ctx.value());
    }

    /*
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


    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
    }

    @Override
    public void visitTerminal(TerminalNode node) {
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
    }

*/

}
