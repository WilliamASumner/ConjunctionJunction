// Generated from TkcGrammar.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TkcGrammarParser}.
 */
public interface TkcGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TkcGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TkcGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#ifsequence}.
	 * @param ctx the parse tree
	 */
	void enterIfsequence(TkcGrammarParser.IfsequenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#ifsequence}.
	 * @param ctx the parse tree
	 */
	void exitIfsequence(TkcGrammarParser.IfsequenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void enterIfstatement(TkcGrammarParser.IfstatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#ifstatement}.
	 * @param ctx the parse tree
	 */
	void exitIfstatement(TkcGrammarParser.IfstatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(TkcGrammarParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(TkcGrammarParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#statementlist}.
	 * @param ctx the parse tree
	 */
	void enterStatementlist(TkcGrammarParser.StatementlistContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#statementlist}.
	 * @param ctx the parse tree
	 */
	void exitStatementlist(TkcGrammarParser.StatementlistContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(TkcGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(TkcGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#attribute}.
	 * @param ctx the parse tree
	 */
	void enterAttribute(TkcGrammarParser.AttributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#attribute}.
	 * @param ctx the parse tree
	 */
	void exitAttribute(TkcGrammarParser.AttributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#assignedattribute}.
	 * @param ctx the parse tree
	 */
	void enterAssignedattribute(TkcGrammarParser.AssignedattributeContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#assignedattribute}.
	 * @param ctx the parse tree
	 */
	void exitAssignedattribute(TkcGrammarParser.AssignedattributeContext ctx);
	/**
	 * Enter a parse tree produced by {@link TkcGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(TkcGrammarParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link TkcGrammarParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(TkcGrammarParser.ValueContext ctx);
}