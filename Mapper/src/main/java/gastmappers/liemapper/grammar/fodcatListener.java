// Generated from .\fodcat.g4 by ANTLR 4.8
package  gastmappers.liemapper.grammar;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link fodcatParser}.
 */
public interface fodcatListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link fodcatParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(fodcatParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(fodcatParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#main_function}.
	 * @param ctx the parse tree
	 */
	void enterMain_function(fodcatParser.Main_functionContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#main_function}.
	 * @param ctx the parse tree
	 */
	void exitMain_function(fodcatParser.Main_functionContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#command}.
	 * @param ctx the parse tree
	 */
	void enterCommand(fodcatParser.CommandContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#command}.
	 * @param ctx the parse tree
	 */
	void exitCommand(fodcatParser.CommandContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#ifstat}.
	 * @param ctx the parse tree
	 */
	void enterIfstat(fodcatParser.IfstatContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#ifstat}.
	 * @param ctx the parse tree
	 */
	void exitIfstat(fodcatParser.IfstatContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#elsestat}.
	 * @param ctx the parse tree
	 */
	void enterElsestat(fodcatParser.ElsestatContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#elsestat}.
	 * @param ctx the parse tree
	 */
	void exitElsestat(fodcatParser.ElsestatContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#func_call}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(fodcatParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#func_call}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(fodcatParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#flow_exp}.
	 * @param ctx the parse tree
	 */
	void enterFlow_exp(fodcatParser.Flow_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#flow_exp}.
	 * @param ctx the parse tree
	 */
	void exitFlow_exp(fodcatParser.Flow_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(fodcatParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(fodcatParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#gpio}.
	 * @param ctx the parse tree
	 */
	void enterGpio(fodcatParser.GpioContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#gpio}.
	 * @param ctx the parse tree
	 */
	void exitGpio(fodcatParser.GpioContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#sleep}.
	 * @param ctx the parse tree
	 */
	void enterSleep(fodcatParser.SleepContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#sleep}.
	 * @param ctx the parse tree
	 */
	void exitSleep(fodcatParser.SleepContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(fodcatParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(fodcatParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#arithmetic_exp}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_exp(fodcatParser.Arithmetic_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#arithmetic_exp}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_exp(fodcatParser.Arithmetic_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#relational_exp}.
	 * @param ctx the parse tree
	 */
	void enterRelational_exp(fodcatParser.Relational_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#relational_exp}.
	 * @param ctx the parse tree
	 */
	void exitRelational_exp(fodcatParser.Relational_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#logic_exp}.
	 * @param ctx the parse tree
	 */
	void enterLogic_exp(fodcatParser.Logic_expContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#logic_exp}.
	 * @param ctx the parse tree
	 */
	void exitLogic_exp(fodcatParser.Logic_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#array_call}.
	 * @param ctx the parse tree
	 */
	void enterArray_call(fodcatParser.Array_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#array_call}.
	 * @param ctx the parse tree
	 */
	void exitArray_call(fodcatParser.Array_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(fodcatParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(fodcatParser.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#array_declaration}.
	 * @param ctx the parse tree
	 */
	void enterArray_declaration(fodcatParser.Array_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#array_declaration}.
	 * @param ctx the parse tree
	 */
	void exitArray_declaration(fodcatParser.Array_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#gpio_declaration}.
	 * @param ctx the parse tree
	 */
	void enterGpio_declaration(fodcatParser.Gpio_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#gpio_declaration}.
	 * @param ctx the parse tree
	 */
	void exitGpio_declaration(fodcatParser.Gpio_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#parameters_declaration}.
	 * @param ctx the parse tree
	 */
	void enterParameters_declaration(fodcatParser.Parameters_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#parameters_declaration}.
	 * @param ctx the parse tree
	 */
	void exitParameters_declaration(fodcatParser.Parameters_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#parameters_called}.
	 * @param ctx the parse tree
	 */
	void enterParameters_called(fodcatParser.Parameters_calledContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#parameters_called}.
	 * @param ctx the parse tree
	 */
	void exitParameters_called(fodcatParser.Parameters_calledContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void enterInt_literal(fodcatParser.Int_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#int_literal}.
	 * @param ctx the parse tree
	 */
	void exitInt_literal(fodcatParser.Int_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#bool_literal}.
	 * @param ctx the parse tree
	 */
	void enterBool_literal(fodcatParser.Bool_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#bool_literal}.
	 * @param ctx the parse tree
	 */
	void exitBool_literal(fodcatParser.Bool_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#float_literal}.
	 * @param ctx the parse tree
	 */
	void enterFloat_literal(fodcatParser.Float_literalContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#float_literal}.
	 * @param ctx the parse tree
	 */
	void exitFloat_literal(fodcatParser.Float_literalContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#strg}.
	 * @param ctx the parse tree
	 */
	void enterStrg(fodcatParser.StrgContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#strg}.
	 * @param ctx the parse tree
	 */
	void exitStrg(fodcatParser.StrgContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(fodcatParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(fodcatParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#relational_op}.
	 * @param ctx the parse tree
	 */
	void enterRelational_op(fodcatParser.Relational_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#relational_op}.
	 * @param ctx the parse tree
	 */
	void exitRelational_op(fodcatParser.Relational_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#logic_op}.
	 * @param ctx the parse tree
	 */
	void enterLogic_op(fodcatParser.Logic_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#logic_op}.
	 * @param ctx the parse tree
	 */
	void exitLogic_op(fodcatParser.Logic_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#arithmetic_op}.
	 * @param ctx the parse tree
	 */
	void enterArithmetic_op(fodcatParser.Arithmetic_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#arithmetic_op}.
	 * @param ctx the parse tree
	 */
	void exitArithmetic_op(fodcatParser.Arithmetic_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#print_op}.
	 * @param ctx the parse tree
	 */
	void enterPrint_op(fodcatParser.Print_opContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#print_op}.
	 * @param ctx the parse tree
	 */
	void exitPrint_op(fodcatParser.Print_opContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#sign}.
	 * @param ctx the parse tree
	 */
	void enterSign(fodcatParser.SignContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#sign}.
	 * @param ctx the parse tree
	 */
	void exitSign(fodcatParser.SignContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#func_button}.
	 * @param ctx the parse tree
	 */
	void enterFunc_button(fodcatParser.Func_buttonContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#func_button}.
	 * @param ctx the parse tree
	 */
	void exitFunc_button(fodcatParser.Func_buttonContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#func_led}.
	 * @param ctx the parse tree
	 */
	void enterFunc_led(fodcatParser.Func_ledContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#func_led}.
	 * @param ctx the parse tree
	 */
	void exitFunc_led(fodcatParser.Func_ledContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#func_servo}.
	 * @param ctx the parse tree
	 */
	void enterFunc_servo(fodcatParser.Func_servoContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#func_servo}.
	 * @param ctx the parse tree
	 */
	void exitFunc_servo(fodcatParser.Func_servoContext ctx);
	/**
	 * Enter a parse tree produced by {@link fodcatParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(fodcatParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link fodcatParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(fodcatParser.IdentifierContext ctx);
}