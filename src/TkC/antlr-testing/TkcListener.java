// Generated from Tkc.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TkcParser}.
 */
public interface TkcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TkcParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TkcParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TkcParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#ifsequence}.
	 * @param ctx the parse tree
	 */
	void enterIfsequence(TkcParser.IfsequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#ifsequence}.
	 * @param ctx the parse tree
	 */
	void exitIfsequence(TkcParser.IfsequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void enterIfstatement(TkcParser.IfstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void exitIfstatement(TkcParser.IfstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#conditionlist}.
	 * @param ctx the parse tree
	 */
	void enterConditionlist(TkcParser.ConditionlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#conditionlist}.
	 * @param ctx the parse tree
	 */
	void exitConditionlist(TkcParser.ConditionlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(TkcParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(TkcParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#statementlist}.
	 * @param ctx the parse tree
	 */
	void enterStatementlist(TkcParser.StatementlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#statementlist}.
	 * @param ctx the parse tree
	 */
	void exitStatementlist(TkcParser.StatementlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(TkcParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(TkcParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(TkcParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(TkcParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#assignedattribute}.
	 * @param ctx the parse tree
	 */
	void enterAssignedattribute(TkcParser.AssignedattributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#assignedattribute}.
	 * @param ctx the parse tree
	 */
	void exitAssignedattribute(TkcParser.AssignedattributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(TkcParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(TkcParser.ValueContext ctx);
}