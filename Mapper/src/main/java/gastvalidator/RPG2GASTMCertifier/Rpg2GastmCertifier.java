package gastvalidator.RPG2GASTMCertifier;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.rpgleparser.RpgParser;
import org.rpgleparser.RpgParser.C_freeContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.rpgleparser.RpgParserBaseVisitor;

import gastvalidator.RPG2GASTMCertifier.Constants.IBuiltInFunction;
import gastvalidator.RPG2GASTMCertifier.Expressions.BinaryExpression;
import gastvalidator.RPG2GASTMCertifier.Expressions.Expression;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionNode;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionOperator;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionType;
import gastvalidator.RPG2GASTMCertifier.Expressions.ExpressionWithOperator;
import gastvalidator.RPG2GASTMCertifier.Expressions.ParenthesizedExpression;
import gastvalidator.RPG2GASTMCertifier.Expressions.UnaryExpression;
import gastvalidator.RPG2GASTMCertifier.Statements.AssigmentStatement;
import gastvalidator.RPG2GASTMCertifier.Statements.BlockStatement;
import gastvalidator.RPG2GASTMCertifier.Statements.ElseIf;
import gastvalidator.RPG2GASTMCertifier.Statements.For;
import gastvalidator.RPG2GASTMCertifier.Statements.If;
import gastvalidator.RPG2GASTMCertifier.Statements.SelectWhen;
import gastvalidator.RPG2GASTMCertifier.Statements.Statement;
import gastvalidator.RPG2GASTMCertifier.Statements.When;

public class Rpg2GastmCertifier extends RpgParserBaseVisitor<IRpgSyntaxElement>{
	
	Stack<Expression> expressionStack;
	
	/**
	 * Visit a parse tree produced by {@link RpgParser#r}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	
	public IRpgSyntaxElement visitR(RpgParser.RContext ctx) {
		//System.out.println("visitR: " + ctx.getText());
		BlockStatement block = new BlockStatement();
		for (int i = 0; i < ctx.statement().size();i++) {
			block.addStatements((Statement)visit(ctx.statement(i)));
		}
		return block;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitStatement(RpgParser.StatementContext ctx) {
		ExpressionType arith = ExpressionType.ARITMETICAL_EXPRESSION;
		//System.out.println("visitStatement: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		IRpgSyntaxElement statement;
		if (ctx.free() != null) {
			statement = visit(ctx.free());
			if (statement instanceof Expression ) {
				AssigmentStatement test = new AssigmentStatement(IBuiltInFunction.ERROR,arith, (Expression)statement);
				return test;
			}
			return statement;
		}
		statement = visitChildren(ctx);
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writeValueAsString(statement);
			if (statement!= null)
				System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
		if (ctx.cspec_fixed()== null || ctx.cspec_fixed().cspec_fixed_standard() == null){
			return statement;
		}
		
		//standard parts
		String factor1 = ctx.cspec_fixed().factor1.getText().trim();
		RpgParser.Cspec_fixed_standardContext fixed = ctx.cspec_fixed().cspec_fixed_standard();
		if (fixed.csADD() != null) {
			RpgParser.Cspec_fixed_standard_partsContext stdparts = fixed.csADD().cspec_fixed_standard_parts();
			Statement add = LineStatementsHandler.handleArithmeticalOperation(factor1, "+", fixed, stdparts);
			return add;
		}
		else if (fixed.csSUB() != null){
			RpgParser.Cspec_fixed_standard_partsContext stdparts = fixed.csSUB().cspec_fixed_standard_parts();
			Statement sub = LineStatementsHandler.handleArithmeticalOperation(factor1, "-", fixed, stdparts);
			return sub;
		}
		else if (fixed.csZ_ADD() != null){
			RpgParser.Cspec_fixed_standard_partsContext stdparts = fixed.csZ_ADD().cspec_fixed_standard_parts();
			Statement sub = LineStatementsHandler.handleArithmeticalZOperation("+", fixed, stdparts);
			return sub;
		}
		else if (fixed.csZ_SUB() != null){
			RpgParser.Cspec_fixed_standard_partsContext stdparts = fixed.csZ_SUB().cspec_fixed_standard_parts();
			Statement sub = LineStatementsHandler.handleArithmeticalZOperation("-", fixed, stdparts);
			return sub;
		}
		else if (fixed.csEVAL() != null){
			C_freeContext evalFree = fixed.csEVAL().c_free();
			Expression result  = (Expression)visit(evalFree.expression().expression(0));
			Expression expression = (Expression)visit(evalFree.expression().expression(1));
			expression = LineStatementsHandler.handleHExtensor(fixed, expression, arith);
			AssigmentStatement eval = new AssigmentStatement(result,arith, expression);
			return eval;
		}
		else if (fixed.csTEST() != null){
			String result  = fixed.csTEST().cspec_fixed_standard_parts().result.getText().trim();
			Expression expression = new ExpressionNode(arith, result);
			expression = LineStatementsHandler.handleTest(fixed, expression, arith, factor1);
			AssigmentStatement test = new AssigmentStatement(IBuiltInFunction.ERROR,arith, expression);
			LineStatementsHandler.loadIndicators(test, fixed.csTEST().cspec_fixed_standard_parts());
			return test;
		}
		else if (fixed.csCALL() != null){
			String result  = fixed.csTEST().cspec_fixed_standard_parts().result.getText().trim();
			Expression expression = new ExpressionNode(arith, result);
			expression = LineStatementsHandler.handleTest(fixed, expression, arith, factor1);
			AssigmentStatement test = new AssigmentStatement(IBuiltInFunction.ERROR,arith, expression);
			LineStatementsHandler.loadIndicators(test, fixed.csTEST().cspec_fixed_standard_parts());
			return test;
		}
		
		
		//System.out.println("==================================================");
		try {
			String jsonString = mapper.writeValueAsString(statement);
			if (statement!= null)
				System.out.println(jsonString);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return statement;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endSource}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndSource(RpgParser.EndSourceContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endSourceHead}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndSourceHead(RpgParser.EndSourceHeadContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endSourceLine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndSourceLine(RpgParser.EndSourceLineContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#star_comments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitStar_comments(RpgParser.Star_commentsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#free_comments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFree_comments(RpgParser.Free_commentsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#free_linecomments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFree_linecomments(RpgParser.Free_linecommentsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#comments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitComments(RpgParser.CommentsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dspec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDspec(RpgParser.DspecContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dspecConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDspecConstant(RpgParser.DspecConstantContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#datatype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDatatype(RpgParser.DatatypeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword(RpgParser.KeywordContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dspec_bif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDspec_bif(RpgParser.Dspec_bifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_alias}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_alias(RpgParser.Keyword_aliasContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_align}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_align(RpgParser.Keyword_alignContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_alt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_alt(RpgParser.Keyword_altContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_altseq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_altseq(RpgParser.Keyword_altseqContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ascend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_ascend(RpgParser.Keyword_ascendContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_based}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_based(RpgParser.Keyword_basedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ccsid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_ccsid(RpgParser.Keyword_ccsidContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_class(RpgParser.Keyword_classContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_const}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_const(RpgParser.Keyword_constContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ctdata}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_ctdata(RpgParser.Keyword_ctdataContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_datfmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_datfmt(RpgParser.Keyword_datfmtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dateSeparator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDateSeparator(RpgParser.DateSeparatorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_descend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_descend(RpgParser.Keyword_descendContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_dim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_dim(RpgParser.Keyword_dimContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_dtaara}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_dtaara(RpgParser.Keyword_dtaaraContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_export}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_export(RpgParser.Keyword_exportContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ext}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_ext(RpgParser.Keyword_extContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extfld}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extfld(RpgParser.Keyword_extfldContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extfmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extfmt(RpgParser.Keyword_extfmtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extname(RpgParser.Keyword_extnameContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extpgm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extpgm(RpgParser.Keyword_extpgmContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extproc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extproc(RpgParser.Keyword_extprocContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_fromfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_fromfile(RpgParser.Keyword_fromfileContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_import}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_import(RpgParser.Keyword_importContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_inz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_inz(RpgParser.Keyword_inzContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_len}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_len(RpgParser.Keyword_lenContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_like}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_like(RpgParser.Keyword_likeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_likeds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_likeds(RpgParser.Keyword_likedsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_likefile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_likefile(RpgParser.Keyword_likefileContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_likerec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_likerec(RpgParser.Keyword_likerecContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_noopt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_noopt(RpgParser.Keyword_nooptContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_occurs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_occurs(RpgParser.Keyword_occursContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_opdesc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_opdesc(RpgParser.Keyword_opdescContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_options}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_options(RpgParser.Keyword_optionsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_overlay}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_overlay(RpgParser.Keyword_overlayContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_packeven}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_packeven(RpgParser.Keyword_packevenContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_perrcd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_perrcd(RpgParser.Keyword_perrcdContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_prefix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_prefix(RpgParser.Keyword_prefixContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_pos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_pos(RpgParser.Keyword_posContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_procptr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_procptr(RpgParser.Keyword_procptrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_qualified}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_qualified(RpgParser.Keyword_qualifiedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_rtnparm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_rtnparm(RpgParser.Keyword_rtnparmContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_static}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_static(RpgParser.Keyword_staticContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_sqltype}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_sqltype(RpgParser.Keyword_sqltypeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_template}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_template(RpgParser.Keyword_templateContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_timfmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_timfmt(RpgParser.Keyword_timfmtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_tofile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_tofile(RpgParser.Keyword_tofileContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_value(RpgParser.Keyword_valueContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_varying}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_varying(RpgParser.Keyword_varyingContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_psds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_psds(RpgParser.Keyword_psdsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_block(RpgParser.Keyword_blockContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_commit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_commit(RpgParser.Keyword_commitContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_devid}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_devid(RpgParser.Keyword_devidContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extdesc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extdesc(RpgParser.Keyword_extdescContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extfile(RpgParser.Keyword_extfileContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extind(RpgParser.Keyword_extindContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_extmbr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_extmbr(RpgParser.Keyword_extmbrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_formlen}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_formlen(RpgParser.Keyword_formlenContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_formofl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_formofl(RpgParser.Keyword_formoflContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_ignore}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_ignore(RpgParser.Keyword_ignoreContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_include}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_include(RpgParser.Keyword_includeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_indds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_indds(RpgParser.Keyword_inddsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_infds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_infds(RpgParser.Keyword_infdsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_infsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_infsr(RpgParser.Keyword_infsrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_keyloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_keyloc(RpgParser.Keyword_keylocContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_maxdev}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_maxdev(RpgParser.Keyword_maxdevContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_oflind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_oflind(RpgParser.Keyword_oflindContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_pass}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_pass(RpgParser.Keyword_passContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_pgmname}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_pgmname(RpgParser.Keyword_pgmnameContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_plist}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_plist(RpgParser.Keyword_plistContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_prtctl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_prtctl(RpgParser.Keyword_prtctlContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_rafdata}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_rafdata(RpgParser.Keyword_rafdataContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_recno}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_recno(RpgParser.Keyword_recnoContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_rename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_rename(RpgParser.Keyword_renameContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_saveds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_saveds(RpgParser.Keyword_savedsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_saveind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_saveind(RpgParser.Keyword_saveindContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_sfile}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_sfile(RpgParser.Keyword_sfileContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_sln}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_sln(RpgParser.Keyword_slnContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_usropn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_usropn(RpgParser.Keyword_usropnContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_disk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_disk(RpgParser.Keyword_diskContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_workstn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_workstn(RpgParser.Keyword_workstnContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_printer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_printer(RpgParser.Keyword_printerContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_special}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_special(RpgParser.Keyword_specialContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_keyed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_keyed(RpgParser.Keyword_keyedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#keyword_usage}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitKeyword_usage(RpgParser.Keyword_usageContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#like_lengthAdjustment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitLike_lengthAdjustment(RpgParser.Like_lengthAdjustmentContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitSign(RpgParser.SignContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_ds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDcl_ds(RpgParser.Dcl_dsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_ds_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDcl_ds_field(RpgParser.Dcl_ds_fieldContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#end_dcl_ds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEnd_dcl_ds(RpgParser.End_dcl_dsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_pr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDcl_pr(RpgParser.Dcl_prContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_pr_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDcl_pr_field(RpgParser.Dcl_pr_fieldContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#end_dcl_pr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEnd_dcl_pr(RpgParser.End_dcl_prContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_pi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDcl_pi(RpgParser.Dcl_piContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_pi_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDcl_pi_field(RpgParser.Dcl_pi_fieldContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#end_dcl_pi}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEnd_dcl_pi(RpgParser.End_dcl_piContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dcl_c}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDcl_c(RpgParser.Dcl_cContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#ctl_opt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCtl_opt(RpgParser.Ctl_optContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#datatypeName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDatatypeName(RpgParser.DatatypeNameContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBlock(RpgParser.BlockContext ctx) {
		if (ctx.begindo()!=null) {
			For _for = (For) visit(ctx.begindo());
			
			String factor2 = ctx.enddo().csENDDO().cspec_fixed_standard_parts().factor2.getText().trim();
			factor2 = "".equals(factor2)?"1":factor2;
			ExpressionNode increment = new ExpressionNode(ExpressionType.ARITMETICAL_EXPRESSION);
			increment.setValue(factor2);
			((BinaryExpression)_for.getIncrement().getExpresion()).setRightOperand(increment);
			
			if (ctx.statement().size() > 0) {
				for (RpgParser.StatementContext stmt:ctx.statement()) {
					_for.getBody().addStatements((Statement)visit(stmt));
				}
			}
			
			//System.out.println(_for);
			return _for;
		}
		else if (ctx.begindou()!=null) {
			//visit(ctx.begindou());
		}
		else if (ctx.begindow()!=null) {
			//visit(ctx.begindow())
		}
		else if (ctx.csDOUxx() !=null) {
			//visit(ctx.csDOUxx())
		}
		else if (ctx.csDOWxx() !=null) {
			//visit(ctx.csDOWxx())
		}
		else if(ctx.forstatement() != null) {
			For _for = (For)visit(ctx.forstatement().beginfor());
			for (RpgParser.StatementContext stmt:ctx.forstatement().statement()) {
				try{
					_for.getBody().addStatements((Statement)visit(stmt));
				} catch (Exception e) {

				}
			}
			
			return _for;
			//visit(ctx.forstatement())
		}
		//CASxx
		else if (ctx.casestatement() != null) {
			
		}
		else if (ctx.ifstatement() != null) {
			return visit(ctx.ifstatement());
		}
		else if (ctx.monitorstatement() != null) {
			
		}
		else if (ctx.selectstatement() != null) {
			return visit(ctx.selectstatement());
		}

		//System.out.println("visitBlock: " + ctx.getText());
		return visitChildren(ctx);
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#ifstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIfstatement(RpgParser.IfstatementContext ctx) {
		ctx.statement();
		
		If ifstmt = (If)visit(ctx.beginif());
		ArrayList<ElseIf> elseifs = new ArrayList<ElseIf>();
		for (RpgParser.ElseifstmtContext elseif:ctx.elseifstmt()) {
			elseifs.add((ElseIf)visit(elseif));
		}
		ifstmt.setElseIf(elseifs);
		//ifstmt.setElseBody((BlockStatement)visit(ctx.elsestmt()));
		ifstmt.setBody(new BlockStatement());
		ifstmt.setElseBody(new BlockStatement ());
		int elseIfIndex = 0;
		boolean elseFlag = false;
		boolean elseIfFlag = false;
		
		for(int i = 1 ; i < ctx.getChildCount()-1; i++) {
			if(elseIfIndex < ctx.elseifstmt().size() && ctx.getChild(i) == ctx.elseifstmt().get(elseIfIndex)) {
				elseifs.get(elseIfIndex).setBody(new BlockStatement());
				elseIfFlag = true;
				elseIfIndex++;
			}
			else if(ctx.getChild(i) == ctx.elsestmt()) {
				elseFlag = true;
			}
			else if(elseFlag) {
				ifstmt.getElseBody().addStatements((Statement)visit(ctx.getChild(i)));
			}
			else if(elseIfFlag) {
				elseifs.get(elseIfIndex-1).getBody().addStatements((Statement)visit(ctx.getChild(i)));
			}
			else {
				ifstmt.getBody().addStatements((Statement)visit(ctx.getChild(i)));
			}
		}
		return ifstmt;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#casestatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCasestatement(RpgParser.CasestatementContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#casestatementend}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCasestatementend(RpgParser.CasestatementendContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#monitorstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitMonitorstatement(RpgParser.MonitorstatementContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginmonitor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBeginmonitor(RpgParser.BeginmonitorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endmonitor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndmonitor(RpgParser.EndmonitorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#onError}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOnError(RpgParser.OnErrorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#selectstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitSelectstatement(RpgParser.SelectstatementContext ctx) {
		//System.out.println("visitSelectstatement: " + ctx.getText());
		List<RpgParser.WhenstatementContext> whens = ctx.whenstatement();
		ArrayList<When> whenOptions = new ArrayList<When>();
		SelectWhen selectWhen = new SelectWhen();
		for(RpgParser.WhenstatementContext when:whens) {
			whenOptions.add((When)visit(when));
		}	
		List<RpgParser.StatementContext> statementsOthers = whens.get(whens.size()-1).statement();
		BlockStatement bstatement = new BlockStatement();
		boolean otherFound = false;
		
		//FREE-FORMAT
		if (whens.get(whens.size()-1).when() != null && statementsOthers.size() == 0) {
			//System.out.println("last when");
			statementsOthers = whens.get(whens.size()-1).when().statement();
			for(RpgParser.StatementContext s:statementsOthers) {
				String isOther = null;
				if (!otherFound && s.free() != null && null != s.free().baseExpression()) {
					isOther = s.free().baseExpression().getText().toLowerCase();
					if("other".equals(isOther))otherFound = true;
				}
				else if(otherFound)bstatement.addStatements((Statement)visit(s));
			}
		}
		//NON-FREE FORMAT
		else {
			RpgParser.StatementContext statementContext;
			for(int i = 0; i < statementsOthers.size();i++) {
				statementContext = statementsOthers.get(i);
				RpgParser.Cspec_fixedContext csFixed = statementContext.cspec_fixed();
				try {
					RpgParser.Cspec_fixed_standardContext stdContext = csFixed.cspec_fixed_standard();
					if (stdContext.csOTHER() != null) otherFound = true;
					else if (otherFound) bstatement.addStatements((Statement) visit(statementsOthers.get(i)));
				} catch (Exception e) {

				}
			}
		}
		
		selectWhen.setWhenOptions(whenOptions);
		selectWhen.setOtherBody(bstatement);
		return selectWhen;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#other}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOther(RpgParser.OtherContext ctx) {
		//System.out.println("visitOther: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginselect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBeginselect(RpgParser.BeginselectContext ctx) {
		//System.out.println("visitBeginselect: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#whenstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitWhenstatement(RpgParser.WhenstatementContext ctx) {
		//System.out.println("visitWhenstatement" + ctx.getText());
		When when = null;
		if (ctx.when() != null) {
			//System.out.println("When");
			when = (When) visit(ctx.when());
		}
		else if (ctx.csWHENxx()!= null) {
			//System.out.println("csWHENxx");
			for(int i = 0; i < ctx.getChildCount();i++) {
				//System.out.println("children number " + i + " " + ctx.getChild(i).getText());
			}
			when = (When) visit(ctx.csWHENxx());
		}
		
		List<RpgParser.StatementContext> rpgStatements = ctx.statement();
		Statement stmt;
		RpgParser.StatementContext statementContext;
		for (int i = 0; i < rpgStatements.size();i++) {
			statementContext = rpgStatements.get(i);
			//System.out.println(statementContext.getText());
			stmt = (Statement)visit(statementContext);
			RpgParser.Cspec_fixedContext csFixed = statementContext.cspec_fixed();
			try {
				RpgParser.Cspec_fixed_standardContext stdContext = csFixed.cspec_fixed_standard();
				if (stdContext.csOTHER() != null) break;
			} catch (Exception e) {

			}
			if (when != null)
				when.getBody().addStatements(stmt);
		}
		return when;

		
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#when}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitWhen(RpgParser.WhenContext ctx) {
		//System.out.println("visitWhen: " + ctx.getText());
		if (ctx.csWHEN() != null) {
			return visit(ctx.csWHEN());
		}
		else if(ctx.op_when()!=null) {
			Statement statement = null;
			When when = (When)visit(ctx.op_when());
			List<RpgParser.StatementContext> stmst = ctx.statement();
			for (RpgParser.StatementContext s:stmst) {
				String isOther = null;
				if (s.free() != null && null != s.free().baseExpression()) {
					isOther = s.free().baseExpression().getText().toLowerCase();
					if("other".equals(isOther))break;
				}
				statement = (Statement) visit(s);
				when.getBody().addStatements(statement);
			}
			return when;
		}
		
		
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWHENxx(RpgParser.CsWHENxxContext ctx) {
		String factor2 = "";
		String operator = "";
		
		if (ctx.csWHENEQ() != null) {
			factor2 = ctx.csWHENEQ().cspec_fixed_standard_parts().factor().getText();
			operator = "=";
		}
		else if (ctx.csWHENGE() != null) {
			factor2 = ctx.csWHENGE().cspec_fixed_standard_parts().factor().getText();
			operator = ">=";
		}
		else if (ctx.csWHENGT() != null) {
			factor2 = ctx.csWHENGT().cspec_fixed_standard_parts().factor().getText();
			operator = ">";
		}
		else if (ctx.csWHENLE() != null) {
			factor2 = ctx.csWHENLE().cspec_fixed_standard_parts().factor().getText();
			operator = "<=";
		}
		else if (ctx.csWHENLT() != null) {
			factor2 = ctx.csWHENLT().cspec_fixed_standard_parts().factor().getText();
			operator = "<";
		}
		else if (ctx.csWHENNE() != null) {
			factor2 = ctx.csWHENNE().cspec_fixed_standard_parts().factor().getText();
			operator = "!=";
		}
		
		BinaryExpression whenSeed = new BinaryExpression(ExpressionType.LOGICAL_EXPRESSION);
		ExpressionNode left  = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
		ExpressionNode right = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
		left.setValue(ctx.factor().getText());
		right.setValue(factor2);
		whenSeed.setOperator(ExpressionWithOperator.getBinaryType(operator));
		whenSeed.setLeftOperand(left);
		whenSeed.setRightOperand(right);
		
		Expression initialExpression = BooleanExpressionsHelper.expressionAndBuilder(ctx.csANDxx(), whenSeed);
		
		Expression secondExpression;
		secondExpression = BooleanExpressionsHelper.expressionOrBuilder(ctx.csORxx(), initialExpression);
		
		////System.out.println(printExpr(secondExpression));
		When when = new When();
		when.setCondition(secondExpression);
		
		return when;
		
	}
	
	@SuppressWarnings("unused")
	private String printExpr(Expression expr) {
		if (expr instanceof BinaryExpression) {
			BinaryExpression be = (BinaryExpression)expr;
			return printExpr(be.getLeftOperand()) + " " + be.getOperator().toString() + " " + printExpr(be.getRightOperand());
		}
		else if (expr instanceof ExpressionNode){
			return ((ExpressionNode)expr).getValue();
		}
		return "";
	}
	
	/**
	 * Visit a parse tree produced by {@link RpgParser#endselect}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndselect(RpgParser.EndselectContext ctx) {
		
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBeginif(RpgParser.BeginifContext ctx) {
		//System.out.println(ctx.getText());
		If ifstmt = new If();
		//NON-FREE
		if (ctx.csIFxx() != null) {
			RpgParser.CsIFxxContext ifFixed = ctx.csIFxx();
			
			String factor2 = "";
			String operator = "";
			if (ifFixed.csIFEQ() != null) {
				factor2 = ifFixed.csIFEQ().cspec_fixed_standard_parts().factor().getText();
				operator = "=";
			}
			else if (ifFixed.csIFGE() != null) {
				factor2 = ifFixed.csIFGE().cspec_fixed_standard_parts().factor().getText();
				operator = ">=";
			}
			else if (ifFixed.csIFGT() != null) {
				factor2 = ifFixed.csIFGE().cspec_fixed_standard_parts().factor().getText();
				operator = ">";
			}
			else if (ifFixed.csIFLE() != null) {
				factor2 = ifFixed.csIFLE().cspec_fixed_standard_parts().factor().getText();
				operator = "<=";
			}
			else if (ifFixed.csIFLT() != null) {
				factor2 = ifFixed.csIFLT().cspec_fixed_standard_parts().factor().getText();
				operator = "<";
			}
			else if (ifFixed.csIFNE() != null) {
				factor2 = ifFixed.csIFNE().cspec_fixed_standard_parts().factor().getText();
				operator = "!=";
			}
			
			BinaryExpression ifSeed = new BinaryExpression(ExpressionType.LOGICAL_EXPRESSION);
			ExpressionNode left  = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
			ExpressionNode right = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
			left.setValue(ifFixed.factor().getText());
			right.setValue(factor2);
			ifSeed.setOperator(ExpressionWithOperator.getBinaryType(operator));
			ifSeed.setLeftOperand(left);
			ifSeed.setRightOperand(right);
			
			Expression initialExpression = BooleanExpressionsHelper.expressionAndBuilder(ifFixed.csANDxx(), ifSeed);
			
			Expression secondExpression;
			secondExpression = BooleanExpressionsHelper.expressionOrBuilder(ifFixed.csORxx(), initialExpression);
			ifstmt.setCondition(secondExpression);
			
		}
		//SEMI-FREE
		else if (ctx.OP_IF() != null) {
			//System.out.println("OP_IF");
			Expression condition = (Expression)visit(ctx.c_free().expression());
			ifstmt.setCondition(condition);
			//if (condition != null){
			//	ifstmt.setExtender(ctx.cs_operationExtender().getText());
			//}
		}
		//FREE
		else if (ctx.op_if() != null) {
			//System.out.println("op_if");
			Expression condition = (Expression)visit(ctx.op_if().expression());
			ifstmt.setCondition(condition);
			try{
				ifstmt.setExtender(ctx.op_if().cs_operationExtender().getText());
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		
		
		return ifstmt;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#begindou}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBegindou(RpgParser.BegindouContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#begindow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBegindow(RpgParser.BegindowContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#begindo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBegindo(RpgParser.BegindoContext ctx) {
		////System.out.println(ctx.getText());

		RpgParser.CsDOContext _do = ctx.csDO();
		RpgParser.Cspec_fixed_standard_partsContext stdparts = _do.cspec_fixed_standard_parts();
		String initialValue = (ctx.factor()!= null && !ctx.factor().getText().trim().equals(""))?ctx.factor().getText():"1";
		String upperLimit = (stdparts.factor2 != null && !stdparts.factor2.getText().trim().equals(""))?stdparts.factor2.getText():"1";
		String variable = (stdparts.result != null && !stdparts.result.getText().trim().equals(""))?stdparts.result.getText():"1";
		For _for = BlockFunctionHelper.buildFor(initialValue, upperLimit, variable);
		
		return _for;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#elseifstmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitElseifstmt(RpgParser.ElseifstmtContext ctx) {
		ElseIf elseif = new ElseIf();
		if (ctx.OP_ELSEIF() != null) {
			Expression condition = (Expression)visit(ctx.c_free().expression());
			elseif.setCondition(condition);
			if (ctx.cs_operationExtender()!=null)
				elseif.setExtender(ctx.cs_operationExtender().getText());
		}
		else if (ctx.op_elseif() != null) {
			Expression condition = (Expression)visit(ctx.op_elseif().indicator_expr().expression());
			elseif.setCondition(condition);
			if (ctx.op_elseif().cs_operationExtender()!=null)
				elseif.setExtender(ctx.op_elseif().cs_operationExtender().getText());
		}
		return elseif;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#elsestmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitElsestmt(RpgParser.ElsestmtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIFxx(RpgParser.CsIFxxContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOUxx(RpgParser.CsDOUxxContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOWxx(RpgParser.CsDOWxxContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#complexCondxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitComplexCondxx(RpgParser.ComplexCondxxContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsANDxx(RpgParser.CsANDxxContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsORxx(RpgParser.CsORxxContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#forstatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitForstatement(RpgParser.ForstatementContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginfor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBeginfor(RpgParser.BeginforContext ctx) {
		ExpressionType arith = ExpressionType.ARITMETICAL_EXPRESSION;
		ExpressionType logical = ExpressionType.LOGICAL_EXPRESSION;
		boolean isThereBy = true;
		boolean isLimitTheUpper = true;
		RpgParser.ExpressionContext initial = null;
		RpgParser.ExpressionContext increment = null;
		RpgParser.ExpressionContext limit = null;
		String variable = null;
		if (ctx.csFOR() != null) {
			//System.out.println("csFOR(): " + ctx.csFOR().getText());
			isThereBy = ctx.csFOR().FREE_BY()!=null;
			isLimitTheUpper = ctx.csFOR().FREE_TO() != null;
			initial = ctx.csFOR().expression(0);
			increment = isThereBy?ctx.csFOR().expression(1):null;
			limit = ctx.csFOR().expression(isThereBy?2:1);
			if (!ctx.csFOR().getChild(2).getText().trim().toLowerCase().equals("by")) {
				RpgParser.ExpressionContext tmp = increment;
				increment = limit;
				limit = tmp;
				//System.out.println("TO");
			}
			variable = initial.expression(0).getText().trim();
			
		}
		else if(ctx.op_for() != null) {
			isThereBy = ctx.op_for().FREE_BY()!=null;
			isLimitTheUpper = ctx.op_for().FREE_TO() != null;
			
			initial = ctx.op_for().expression(0);
			increment = isThereBy?ctx.op_for().expression(1):null;
			limit = ctx.op_for().expression(isThereBy?2:1);
			// this is the count value
			variable = initial.expression(0).getText().trim();
			
		}
		//initial Expression an statement
		Expression initialExpression = (Expression)visit(initial.expression(1));
		AssigmentStatement assigmentInitial = new AssigmentStatement();
		assigmentInitial.setResult(variable);
		assigmentInitial.setExpresion(initialExpression);
		
		//increment expression
		Expression incrementExpression = isThereBy?(Expression)visit(increment): new ExpressionNode(arith,"1");
					
		String incrementOperand = isLimitTheUpper?"+":"-";
		BinaryExpression binaryIncrement = new BinaryExpression(arith, variable, incrementOperand, incrementExpression);
		AssigmentStatement assigmentIncrement = new AssigmentStatement(variable, binaryIncrement);

		//limit expression
		String limitOperand = isLimitTheUpper?"<=":">=";
		try {
			Expression limitExpression = (Expression) visit(limit);
			BinaryExpression binaryLimit = new BinaryExpression(logical, variable, limitOperand, limitExpression);

			For _for = BlockFunctionHelper.buildFor(assigmentInitial, binaryLimit, assigmentIncrement);
			return _for;
		} catch (Exception e) {
			return null;
		}

	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndif(RpgParser.EndifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#enddo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEnddo(RpgParser.EnddoContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endfor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndfor(RpgParser.EndforContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dspec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDspec_fixed(RpgParser.Dspec_fixedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#ds_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDs_name(RpgParser.Ds_nameContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#ospec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOspec_fixed(RpgParser.Ospec_fixedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#os_fixed_pgmdesc1}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOs_fixed_pgmdesc1(RpgParser.Os_fixed_pgmdesc1Context ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#outputConditioningOnOffIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOutputConditioningOnOffIndicator(RpgParser.OutputConditioningOnOffIndicatorContext ctx) {
		//System.out.println("visitOutputConditioningOnOffIndicator");
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#outputConditioningIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOutputConditioningIndicator(RpgParser.OutputConditioningIndicatorContext ctx) {
		//System.out.println("visitOutputConditioningOnOffIndicator");
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#os_fixed_pgmdesc_compound}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOs_fixed_pgmdesc_compound(RpgParser.Os_fixed_pgmdesc_compoundContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#os_fixed_pgmdesc2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOs_fixed_pgmdesc2(RpgParser.Os_fixed_pgmdesc2Context ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#os_fixed_pgmfield}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOs_fixed_pgmfield(RpgParser.Os_fixed_pgmfieldContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#ps_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitPs_name(RpgParser.Ps_nameContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#fspec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFspec(RpgParser.FspecContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#filename}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFilename(RpgParser.FilenameContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#fs_parm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFs_parm(RpgParser.Fs_parmContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#fs_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFs_string(RpgParser.Fs_stringContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#fs_keyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFs_keyword(RpgParser.Fs_keywordContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#fspec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFspec_fixed(RpgParser.Fspec_fixedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCspec_fixed(RpgParser.Cspec_fixedContext ctx) {
		//System.out.println("visitCspec_fixed: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_continuedIndicators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCspec_continuedIndicators(RpgParser.Cspec_continuedIndicatorsContext ctx) {
		//System.out.println("visitCspec_continuedIndicators: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_blank}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCspec_blank(RpgParser.Cspec_blankContext ctx) {
		//System.out.println("visitCspec_blank: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#blank_spec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBlank_spec(RpgParser.Blank_specContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#piBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitPiBegin(RpgParser.PiBeginContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#parm_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitParm_fixed(RpgParser.Parm_fixedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#pr_parm_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitPr_parm_fixed(RpgParser.Pr_parm_fixedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#pi_parm_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitPi_parm_fixed(RpgParser.Pi_parm_fixedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#procedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitProcedure(RpgParser.ProcedureContext ctx) {
		
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBeginProcedure(RpgParser.BeginProcedureContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndProcedure(RpgParser.EndProcedureContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#psBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitPsBegin(RpgParser.PsBeginContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#freeBeginProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFreeBeginProcedure(RpgParser.FreeBeginProcedureContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#psEnd}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitPsEnd(RpgParser.PsEndContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#freeEndProcedure}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFreeEndProcedure(RpgParser.FreeEndProcedureContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#prBegin}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitPrBegin(RpgParser.PrBeginContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#subroutine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitSubroutine(RpgParser.SubroutineContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#subprocedurestatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitSubprocedurestatement(RpgParser.SubprocedurestatementContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#begsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBegsr(RpgParser.BegsrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndsr(RpgParser.EndsrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csBEGSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsBEGSR(RpgParser.CsBEGSRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#freeBEGSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFreeBEGSR(RpgParser.FreeBEGSRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsENDSR(RpgParser.CsENDSRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#freeENDSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFreeENDSR(RpgParser.FreeENDSRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#onOffIndicatorsFlag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOnOffIndicatorsFlag(RpgParser.OnOffIndicatorsFlagContext ctx) {
		//System.out.println("visitOnOffIndicatorsFlag: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cs_controlLevel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCs_controlLevel(RpgParser.Cs_controlLevelContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cs_indicators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCs_indicators(RpgParser.Cs_indicatorsContext ctx) {
		//System.out.println("visitCs_indicators: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#resultIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitResultIndicator(RpgParser.ResultIndicatorContext ctx) {
		//System.out.println("visitResultIndicator: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed_sql}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCspec_fixed_sql(RpgParser.Cspec_fixed_sqlContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed_standard}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCspec_fixed_standard(RpgParser.Cspec_fixed_standardContext ctx) {
		//System.out.println("visitCspec_fixed_standard: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed_standard_parts}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCspec_fixed_standard_parts(RpgParser.Cspec_fixed_standard_partsContext ctx) {
		
		//System.out.println("visitCspec_fixed_standard_parts: " + ctx.getText());
		//System.out.println(ctx.factor2.getText() + " " + ctx.result.getText() + " " + ctx.hi.getText() + " " + ctx.eq.getText() + " " + ctx.lo.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csACQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsACQ(RpgParser.CsACQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csADD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsADD(RpgParser.CsADDContext ctx) {
		//System.out.println(ctx.getText());
		//System.out.println("Visiting add and indicator are: " + ctx.cspec_fixed_standard_parts().hi.getText());
		//visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csADDDUR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsADDDUR(RpgParser.CsADDDURContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csALLOC}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsALLOC(RpgParser.CsALLOCContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsANDEQ(RpgParser.CsANDEQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsANDNE(RpgParser.CsANDNEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsANDLE(RpgParser.CsANDLEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsANDLT(RpgParser.CsANDLTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsANDGE(RpgParser.CsANDGEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csANDGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsANDGT(RpgParser.CsANDGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csBITOFF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsBITOFF(RpgParser.CsBITOFFContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csBITON}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsBITON(RpgParser.CsBITONContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABxx}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCABxx(RpgParser.CsCABxxContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCABEQ(RpgParser.CsCABEQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCABNE(RpgParser.CsCABNEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCABLE(RpgParser.CsCABLEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCABLT(RpgParser.CsCABLTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCABGE(RpgParser.CsCABGEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCABGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCABGT(RpgParser.CsCABGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCALL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCALL(RpgParser.CsCALLContext ctx) {
		//System.out.println("visitCsCALL: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCALLB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCALLB(RpgParser.CsCALLBContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCALLP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCALLP(RpgParser.CsCALLPContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCASEQ(RpgParser.CsCASEQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCASNE(RpgParser.CsCASNEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCASLE(RpgParser.CsCASLEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCASLT(RpgParser.CsCASLTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCASGE(RpgParser.CsCASGEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCASGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCASGT(RpgParser.CsCASGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCAS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCAS(RpgParser.CsCASContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCAT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCAT(RpgParser.CsCATContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCHAIN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCHAIN(RpgParser.CsCHAINContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCHECK}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCHECK(RpgParser.CsCHECKContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCHECKR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCHECKR(RpgParser.CsCHECKRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCLEAR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCLEAR(RpgParser.CsCLEARContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCLOSE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCLOSE(RpgParser.CsCLOSEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCOMMIT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCOMMIT(RpgParser.CsCOMMITContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csCOMP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsCOMP(RpgParser.CsCOMPContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDEALLOC}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDEALLOC(RpgParser.CsDEALLOCContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDEFINE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDEFINE(RpgParser.CsDEFINEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDELETE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDELETE(RpgParser.CsDELETEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDIV}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDIV(RpgParser.CsDIVContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDO(RpgParser.CsDOContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOU}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOU(RpgParser.CsDOUContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOUEQ(RpgParser.CsDOUEQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOUNE(RpgParser.CsDOUNEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOULE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOULE(RpgParser.CsDOULEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOULT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOULT(RpgParser.CsDOULTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOUGE(RpgParser.CsDOUGEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOUGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOUGT(RpgParser.CsDOUGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOW}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOW(RpgParser.CsDOWContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOWEQ(RpgParser.CsDOWEQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOWNE(RpgParser.CsDOWNEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOWLE(RpgParser.CsDOWLEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOWLT(RpgParser.CsDOWLTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOWGE(RpgParser.CsDOWGEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDOWGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDOWGT(RpgParser.CsDOWGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDSPLY}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDSPLY(RpgParser.CsDSPLYContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csDUMP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsDUMP(RpgParser.CsDUMPContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csELSE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsELSE(RpgParser.CsELSEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csELSEIF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsELSEIF(RpgParser.CsELSEIFContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEND}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsEND(RpgParser.CsENDContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDCS}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsENDCS(RpgParser.CsENDCSContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDDO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsENDDO(RpgParser.CsENDDOContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDFOR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsENDFOR(RpgParser.CsENDFORContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDIF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsENDIF(RpgParser.CsENDIFContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDMON}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsENDMON(RpgParser.CsENDMONContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csENDSL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsENDSL(RpgParser.CsENDSLContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEVAL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsEVAL(RpgParser.CsEVALContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEVAL_CORR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsEVAL_CORR(RpgParser.CsEVAL_CORRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEVALR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsEVALR(RpgParser.CsEVALRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEXCEPT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsEXCEPT(RpgParser.CsEXCEPTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEXFMT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsEXFMT(RpgParser.CsEXFMTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEXSR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsEXSR(RpgParser.CsEXSRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csEXTRCT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsEXTRCT(RpgParser.CsEXTRCTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csFEOD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsFEOD(RpgParser.CsFEODContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csFOR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsFOR(RpgParser.CsFORContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csFORCE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsFORCE(RpgParser.CsFORCEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csGOTO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsGOTO(RpgParser.CsGOTOContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIF(RpgParser.CsIFContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIFEQ(RpgParser.CsIFEQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIFNE(RpgParser.CsIFNEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIFLE(RpgParser.CsIFLEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIFLT(RpgParser.CsIFLTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIFGE(RpgParser.CsIFGEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIFGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIFGT(RpgParser.CsIFGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csIN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsIN(RpgParser.CsINContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csITER}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsITER(RpgParser.CsITERContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csKLIST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsKLIST(RpgParser.CsKLISTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csKFLD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsKFLD(RpgParser.CsKFLDContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csLEAVE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsLEAVE(RpgParser.CsLEAVEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csLEAVESR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsLEAVESR(RpgParser.CsLEAVESRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csLOOKUP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsLOOKUP(RpgParser.CsLOOKUPContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMHHZO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMHHZO(RpgParser.CsMHHZOContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMHLZO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMHLZO(RpgParser.CsMHLZOContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMLHZO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMLHZO(RpgParser.CsMLHZOContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMLLZO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMLLZO(RpgParser.CsMLLZOContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMONITOR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMONITOR(RpgParser.CsMONITORContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMOVE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMOVE(RpgParser.CsMOVEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMOVEA}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMOVEA(RpgParser.CsMOVEAContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMOVEL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMOVEL(RpgParser.CsMOVELContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMULT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMULT(RpgParser.CsMULTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csMVR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsMVR(RpgParser.CsMVRContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csNEXT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsNEXT(RpgParser.CsNEXTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOCCUR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsOCCUR(RpgParser.CsOCCURContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csON_ERROR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsON_ERROR(RpgParser.CsON_ERRORContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#onErrorCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOnErrorCode(RpgParser.OnErrorCodeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOPEN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsOPEN(RpgParser.CsOPENContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOREQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsOREQ(RpgParser.CsOREQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsORNE(RpgParser.CsORNEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsORLE(RpgParser.CsORLEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsORLT(RpgParser.CsORLTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsORGE(RpgParser.CsORGEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csORGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsORGT(RpgParser.CsORGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOTHER}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsOTHER(RpgParser.CsOTHERContext ctx) {
		//System.out.println("visitCsOTHER: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOUT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsOUT(RpgParser.CsOUTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csPARM}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsPARM(RpgParser.CsPARMContext ctx) {
		//System.out.println("visitCsPARM: " + ctx.getText());
		visitChildren(ctx);
		
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csPLIST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsPLIST(RpgParser.CsPLISTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csPOST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsPOST(RpgParser.CsPOSTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREAD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsREAD(RpgParser.CsREADContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREADC}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsREADC(RpgParser.CsREADCContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREADE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsREADE(RpgParser.CsREADEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREADP}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsREADP(RpgParser.CsREADPContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREADPE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsREADPE(RpgParser.CsREADPEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREALLOC}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsREALLOC(RpgParser.CsREALLOCContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csREL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsREL(RpgParser.CsRELContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csRESET}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsRESET(RpgParser.CsRESETContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csRETURN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsRETURN(RpgParser.CsRETURNContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csROLBK}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsROLBK(RpgParser.CsROLBKContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSCAN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSCAN(RpgParser.CsSCANContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSELECT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSELECT(RpgParser.CsSELECTContext ctx) {
		//System.out.println("visitCsSELECT: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSETGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSETGT(RpgParser.CsSETGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSETLL}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSETLL(RpgParser.CsSETLLContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSETOFF}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSETOFF(RpgParser.CsSETOFFContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSETON}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSETON(RpgParser.CsSETONContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSHTDN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSHTDN(RpgParser.CsSHTDNContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSORTA}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSORTA(RpgParser.CsSORTAContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSQRT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSQRT(RpgParser.CsSQRTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSUB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSUB(RpgParser.CsSUBContext ctx) {
		//System.out.println("visitCsSUB: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSUBDUR}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSUBDUR(RpgParser.CsSUBDURContext ctx) {
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csSUBST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsSUBST(RpgParser.CsSUBSTContext ctx) {
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTAG}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsTAG(RpgParser.CsTAGContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTEST}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsTEST(RpgParser.CsTESTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTESTB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsTESTB(RpgParser.CsTESTBContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTESTN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsTESTN(RpgParser.CsTESTNContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTESTZ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsTESTZ(RpgParser.CsTESTZContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csTIME}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsTIME(RpgParser.CsTIMEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csUNLOCK}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsUNLOCK(RpgParser.CsUNLOCKContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csUPDATE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsUPDATE(RpgParser.CsUPDATEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHEN}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWHEN(RpgParser.CsWHENContext ctx) {
		ctx.cs_operationExtender();
		When when = new When();
		if (ctx.cs_operationExtender() != null)
			when.setExtender(ctx.cs_operationExtender().getText());
		IRpgSyntaxElement condition = visit(ctx.c_free());
		when.setCondition((Expression)condition);
		return when;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENEQ}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWHENEQ(RpgParser.CsWHENEQContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENNE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWHENNE(RpgParser.CsWHENNEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENLE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWHENLE(RpgParser.CsWHENLEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENLT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWHENLT(RpgParser.CsWHENLTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENGE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWHENGE(RpgParser.CsWHENGEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWHENGT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWHENGT(RpgParser.CsWHENGTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csWRITE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsWRITE(RpgParser.CsWRITEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csXFOOT}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsXFOOT(RpgParser.CsXFOOTContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csXLATE}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsXLATE(RpgParser.CsXLATEContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csXML_INTO}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsXML_INTO(RpgParser.CsXML_INTOContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csXML_SAX}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsXML_SAX(RpgParser.CsXML_SAXContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csZ_ADD}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsZ_ADD(RpgParser.CsZ_ADDContext ctx) {
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csZ_SUB}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsZ_SUB(RpgParser.CsZ_SUBContext ctx) {
		//System.out.println("ZSUB ret " + visitChildren(ctx));
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cs_operationExtender}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCs_operationExtender(RpgParser.Cs_operationExtenderContext ctx) {
		//System.out.println("visitCs_operationExtender: " + ctx.getText());
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFactor(RpgParser.FactorContext ctx) {
		//RpgParser.ruleNames[ctx.invokingState];
		//System.out.println("Factor("+ctx.invokingState+"): " + ctx.getText());
		visitChildren(ctx);
		return null;
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#factorContent}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFactorContent(RpgParser.FactorContentContext ctx) {
		//System.out.println("visitFactorContent: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#resultType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitResultType(RpgParser.ResultTypeContext ctx) {
		//System.out.println("visitResultType: " + ctx.getText());
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cs_fixed_comments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCs_fixed_comments(RpgParser.Cs_fixed_commentsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#cspec_fixed_x2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCspec_fixed_x2(RpgParser.Cspec_fixed_x2Context ctx) {
		//System.out.println("visitCspec_fixed_x2");
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#csOperationAndExtendedFactor2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCsOperationAndExtendedFactor2(RpgParser.CsOperationAndExtendedFactor2Context ctx) {
		//System.out.println("visitCsOperationAndExtendedFactor2");
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#ispec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIspec_fixed(RpgParser.Ispec_fixedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#fieldRecordRelation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFieldRecordRelation(RpgParser.FieldRecordRelationContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#fieldIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFieldIndicator(RpgParser.FieldIndicatorContext ctx) {
		//System.out.println("visitFieldIndicator.");
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#is_external_rec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIs_external_rec(RpgParser.Is_external_recContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#is_rec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIs_rec(RpgParser.Is_recContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#recordIdIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitRecordIdIndicator(RpgParser.RecordIdIndicatorContext ctx) {
		//System.out.println("visitRecordIdIndicator");
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#is_external_field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIs_external_field(RpgParser.Is_external_fieldContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#controlLevelIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitControlLevelIndicator(RpgParser.ControlLevelIndicatorContext ctx) {
		//System.out.println("visitControlLevelIndicator");
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#matchingFieldsIndicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitMatchingFieldsIndicator(RpgParser.MatchingFieldsIndicatorContext ctx) {
		//System.out.println("visitControlLevelIndicator");
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#hspec_fixed}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitHspec_fixed(RpgParser.Hspec_fixedContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#hs_expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitHs_expression(RpgParser.Hs_expressionContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#hs_parm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitHs_parm(RpgParser.Hs_parmContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#hs_string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitHs_string(RpgParser.Hs_stringContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#blank_line}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBlank_line(RpgParser.Blank_lineContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDirective(RpgParser.DirectiveContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#space_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitSpace_directive(RpgParser.Space_directiveContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_copy}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_copy(RpgParser.Dir_copyContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_include}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_include(RpgParser.Dir_includeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_if(RpgParser.Dir_ifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_elseif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_elseif(RpgParser.Dir_elseifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_else(RpgParser.Dir_elseContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_endif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_endif(RpgParser.Dir_endifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_define}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_define(RpgParser.Dir_defineContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_undefine}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_undefine(RpgParser.Dir_undefineContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#dir_eof}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDir_eof(RpgParser.Dir_eofContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#beginfree_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBeginfree_directive(RpgParser.Beginfree_directiveContext ctx) {
		//System.out.println("visitBeginfree_directive: " + ctx.getText());
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#endfree_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEndfree_directive(RpgParser.Endfree_directiveContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#copyText}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitCopyText(RpgParser.CopyTextContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#trailing_ws}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitTrailing_ws(RpgParser.Trailing_wsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#title_directive}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitTitle_directive(RpgParser.Title_directiveContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#title_text}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitTitle_text(RpgParser.Title_textContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp(RpgParser.OpContext ctx) {
		//System.out.println("visitOp: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		return visitChildren(ctx);
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_acq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_acq(RpgParser.Op_acqContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_callp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_callp(RpgParser.Op_callpContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_chain}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_chain(RpgParser.Op_chainContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_clear}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_clear(RpgParser.Op_clearContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_close}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_close(RpgParser.Op_closeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_commit}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_commit(RpgParser.Op_commitContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dealloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_dealloc(RpgParser.Op_deallocContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_delete}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_delete(RpgParser.Op_deleteContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dou}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_dou(RpgParser.Op_douContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_dow(RpgParser.Op_dowContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dsply}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_dsply(RpgParser.Op_dsplyContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_dump}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_dump(RpgParser.Op_dumpContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_else}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_else(RpgParser.Op_elseContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_elseif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_elseif(RpgParser.Op_elseifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_enddo}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_enddo(RpgParser.Op_enddoContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_endfor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_endfor(RpgParser.Op_endforContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_endif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_endif(RpgParser.Op_endifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_endmon}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_endmon(RpgParser.Op_endmonContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_endsl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_endsl(RpgParser.Op_endslContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_eval}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_eval(RpgParser.Op_evalContext ctx) {
		//System.out.println("visitOp_eval: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		
		return visitChildren(ctx);
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_evalr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_evalr(RpgParser.Op_evalrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_eval_corr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_eval_corr(RpgParser.Op_eval_corrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_except}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_except(RpgParser.Op_exceptContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_exfmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_exfmt(RpgParser.Op_exfmtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_exsr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_exsr(RpgParser.Op_exsrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_feod}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_feod(RpgParser.Op_feodContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_for}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_for(RpgParser.Op_forContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_force}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_force(RpgParser.Op_forceContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_if}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_if(RpgParser.Op_ifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_in}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_in(RpgParser.Op_inContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_iter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_iter(RpgParser.Op_iterContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_leave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_leave(RpgParser.Op_leaveContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_leavesr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_leavesr(RpgParser.Op_leavesrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_monitor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_monitor(RpgParser.Op_monitorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_next}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_next(RpgParser.Op_nextContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_on_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_on_error(RpgParser.Op_on_errorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_open}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_open(RpgParser.Op_openContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_other}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_other(RpgParser.Op_otherContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_out}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_out(RpgParser.Op_outContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_post}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_post(RpgParser.Op_postContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_read}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_read(RpgParser.Op_readContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_readc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_readc(RpgParser.Op_readcContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_reade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_reade(RpgParser.Op_readeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_readp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_readp(RpgParser.Op_readpContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_readpe}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_readpe(RpgParser.Op_readpeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_rel}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_rel(RpgParser.Op_relContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_reset2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_reset2(RpgParser.Op_reset2Context ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_reset}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_reset(RpgParser.Op_resetContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_return}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_return(RpgParser.Op_returnContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_rolbk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_rolbk(RpgParser.Op_rolbkContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_select}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_select(RpgParser.Op_selectContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_setgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_setgt(RpgParser.Op_setgtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_setll}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_setll(RpgParser.Op_setllContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_sorta}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_sorta(RpgParser.Op_sortaContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_test}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_test(RpgParser.Op_testContext ctx) {
		String factor1 = null;
		String date = null;
		if (ctx.identifier().size() == 2) {
			factor1 = ctx.identifier(0).getText().trim();
			date = ctx.identifier(1).getText().trim();
		}
		else date = ctx.identifier(0).getText().trim();
		
		ExpressionType et = ExpressionType.ARITMETICAL_EXPRESSION;
		Expression e = new ExpressionNode(et, date);
		
		return LineStatementsHandler.handleTest(ctx.cs_operationExtender(), e, et, factor1);
		
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_unlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_unlock(RpgParser.Op_unlockContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_update}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_update(RpgParser.Op_updateContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_when}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_when(RpgParser.Op_whenContext ctx) {
		//System.out.println("visitOp_when: "+ctx.getText());
		Expression condition = (Expression) visitChildren(ctx);
		When when = new When();
		when.setCondition(condition);
		
		return when;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_write}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_write(RpgParser.Op_writeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_xml_into}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_xml_into(RpgParser.Op_xml_intoContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_xml_sax}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_xml_sax(RpgParser.Op_xml_saxContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#search_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitSearch_arg(RpgParser.Search_argContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#op_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOp_code(RpgParser.Op_codeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif(RpgParser.BifContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#optargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOptargs(RpgParser.OptargsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_charformat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_charformat(RpgParser.Bif_charformatContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_dateformat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_dateformat(RpgParser.Bif_dateformatContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_timeformat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_timeformat(RpgParser.Bif_timeformatContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_editccurrency}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_editccurrency(RpgParser.Bif_editccurrencyContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookupargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_lookupargs(RpgParser.Bif_lookupargsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#durationCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitDurationCode(RpgParser.DurationCodeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_timestampargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_timestampargs(RpgParser.Bif_timestampargsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookupargs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_tlookupargs(RpgParser.Bif_tlookupargsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_abs}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_abs(RpgParser.Bif_absContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_addr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_addr(RpgParser.Bif_addrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_alloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_alloc(RpgParser.Bif_allocContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_bitand}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_bitand(RpgParser.Bif_bitandContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_bitnot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_bitnot(RpgParser.Bif_bitnotContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_bitor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_bitor(RpgParser.Bif_bitorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_bitxor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_bitxor(RpgParser.Bif_bitxorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_char(RpgParser.Bif_charContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_check}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_check(RpgParser.Bif_checkContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_checkr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_checkr(RpgParser.Bif_checkrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_date}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_date(RpgParser.Bif_dateContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_days}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_days(RpgParser.Bif_daysContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_dec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_dec(RpgParser.Bif_decContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_dech}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_dech(RpgParser.Bif_dechContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_decpos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_decpos(RpgParser.Bif_decposContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_diff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_diff(RpgParser.Bif_diffContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_div}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_div(RpgParser.Bif_divContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_editc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_editc(RpgParser.Bif_editcContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_editflt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_editflt(RpgParser.Bif_editfltContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_editw}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_editw(RpgParser.Bif_editwContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_elem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_elem(RpgParser.Bif_elemContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_eof}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_eof(RpgParser.Bif_eofContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_equal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_equal(RpgParser.Bif_equalContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_error}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_error(RpgParser.Bif_errorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_fields}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_fields(RpgParser.Bif_fieldsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_float}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_float(RpgParser.Bif_floatContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_found}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_found(RpgParser.Bif_foundContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_graph}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_graph(RpgParser.Bif_graphContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_handler}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_handler(RpgParser.Bif_handlerContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_hours}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_hours(RpgParser.Bif_hoursContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_int(RpgParser.Bif_intContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_inth}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_inth(RpgParser.Bif_inthContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_kds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_kds(RpgParser.Bif_kdsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_len}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_len(RpgParser.Bif_lenContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_lookup(RpgParser.Bif_lookupContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookuplt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_lookuplt(RpgParser.Bif_lookupltContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_lookuple(RpgParser.Bif_lookupleContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookupgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_lookupgt(RpgParser.Bif_lookupgtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_lookupge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_lookupge(RpgParser.Bif_lookupgeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_minutes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_minutes(RpgParser.Bif_minutesContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_months}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_months(RpgParser.Bif_monthsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_mseconds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_mseconds(RpgParser.Bif_msecondsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_nullind}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_nullind(RpgParser.Bif_nullindContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_occur}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_occur(RpgParser.Bif_occurContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_open}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_open(RpgParser.Bif_openContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_paddr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_paddr(RpgParser.Bif_paddrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_parms}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_parms(RpgParser.Bif_parmsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_parmnum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_parmnum(RpgParser.Bif_parmnumContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_realloc}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_realloc(RpgParser.Bif_reallocContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_rem}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_rem(RpgParser.Bif_remContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_replace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_replace(RpgParser.Bif_replaceContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_scan}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_scan(RpgParser.Bif_scanContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_scanrpl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_scanrpl(RpgParser.Bif_scanrplContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_seconds}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_seconds(RpgParser.Bif_secondsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_shtdn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_shtdn(RpgParser.Bif_shtdnContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_size}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_size(RpgParser.Bif_sizeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_sqrt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_sqrt(RpgParser.Bif_sqrtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_status}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_status(RpgParser.Bif_statusContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_str}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_str(RpgParser.Bif_strContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_subarr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_subarr(RpgParser.Bif_subarrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_subdt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_subdt(RpgParser.Bif_subdtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_subst}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_subst(RpgParser.Bif_substContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_this}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_this(RpgParser.Bif_thisContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_time}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_time(RpgParser.Bif_timeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_timestamp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_timestamp(RpgParser.Bif_timestampContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_tlookup(RpgParser.Bif_tlookupContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookuplt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_tlookuplt(RpgParser.Bif_tlookupltContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookuple}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_tlookuple(RpgParser.Bif_tlookupleContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookupgt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_tlookupgt(RpgParser.Bif_tlookupgtContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_tlookupge}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_tlookupge(RpgParser.Bif_tlookupgeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_trim}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_trim(RpgParser.Bif_trimContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_triml}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_triml(RpgParser.Bif_trimlContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_trimr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_trimr(RpgParser.Bif_trimrContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_ucs2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_ucs2(RpgParser.Bif_ucs2Context ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_uns}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_uns(RpgParser.Bif_unsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_unsh}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_unsh(RpgParser.Bif_unshContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_xfoot}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_xfoot(RpgParser.Bif_xfootContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_xlate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_xlate(RpgParser.Bif_xlateContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_xml}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_xml(RpgParser.Bif_xmlContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_years}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_years(RpgParser.Bif_yearsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#bif_code}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBif_code(RpgParser.Bif_codeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#free}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFree(RpgParser.FreeContext ctx) {
		//System.out.println("visitFree: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		try {
			return visit(ctx.baseExpression());
		} catch (Exception e) {
			return null;
		}

	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#c_free}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitC_free(RpgParser.C_freeContext ctx) {
		//System.out.println("visitC_free: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		return visitChildren(ctx);
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#control}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitControl(RpgParser.ControlContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#exec_sql}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitExec_sql(RpgParser.Exec_sqlContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#baseExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitBaseExpression(RpgParser.BaseExpressionContext ctx) {
		//System.out.println("visitBaseExpression: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		return visitChildren(ctx);
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#indicator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIndicator(RpgParser.IndicatorContext ctx) {
		//System.out.println("visitIndicator");
		return visitChildren(ctx);
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#assignmentExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitAssignmentExpression(RpgParser.AssignmentExpressionContext ctx) {
		//System.out.println("visitAssignmentExpression: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		Expression expr = (Expression)visit(ctx.expression());
		AssigmentStatement assign = new AssigmentStatement();
		assign.setResult(ctx.simpleExpression().getText());
		assign.setExpresion(expr);
		return assign;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#assignOperatorExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitAssignOperatorExpression(RpgParser.AssignOperatorExpressionContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#evalExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitEvalExpression(RpgParser.EvalExpressionContext ctx) {
		//System.out.println("visitEvalExpression: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		IRpgSyntaxElement value = visitChildren(ctx);
		return value;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitSimpleExpression(RpgParser.SimpleExpressionContext ctx) {
		//System.out.println("visitSimpleExpression: " + ctx.getText());
		return visitChildren(ctx);
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#unaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitUnaryExpression(RpgParser.UnaryExpressionContext ctx) {
		//System.out.println("visitUnaryExpression: " + ctx.getText());
		String operator = ctx.sign().getText();
		ExpressionOperator eo = ExpressionWithOperator.getUnaryType(operator);
		
		boolean isArith = ExpressionWithOperator.operatorIsArithmetical(operator);
		ExpressionType expresionType= isArith?ExpressionType.ARITMETICAL_EXPRESSION:ExpressionType.LOGICAL_EXPRESSION;
		UnaryExpression ue = new UnaryExpression(expresionType);
		ue.setOperator(eo);
		ue.setTarget((Expression)visitChildren(ctx));
		return ue;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitExpression(RpgParser.ExpressionContext ctx) {
		//System.out.println("visitExpression: ("+ctx.getChildCount()+")"+ ctx.getText());
		if (ctx.getChildCount() == 3) { // operand operator operand
			if (ctx.getChild(1) instanceof org.antlr.v4.runtime.tree.TerminalNodeImpl
					|| ctx.getChild(1) instanceof RpgParser.ComparisonOperatorContext) {
				String operator = ctx.getChild(1).getText();
				Expression left  = (Expression) visit(ctx.getChild(0));
				Expression right = (Expression) visit(ctx.getChild(2));
				ExpressionOperator eo = ExpressionWithOperator.getBinaryType(operator);
				boolean isArith = ExpressionWithOperator.operatorIsArithmetical(operator);
				ExpressionType expresionType= isArith?ExpressionType.ARITMETICAL_EXPRESSION:ExpressionType.LOGICAL_EXPRESSION;
				BinaryExpression be = new BinaryExpression(expresionType);
				be.setOperator(eo);
				be.setLeftOperand(left);
				be.setRightOperand(right);
				return be;
			}
			else {
				ParenthesizedExpression pe = new ParenthesizedExpression(ExpressionType.PARENTHESIZED_EXPRESSION);
				pe.setTarget((Expression) visit(ctx.getChild(1)));
				return pe;
			}
			
		}
		else if (ctx.getChildCount() == 1){//unary expression;
			
			if (ctx.getChild(0) instanceof RpgParser.UnaryExpressionContext) {
				return visitChildren(ctx);
			}
			else if (ctx.getChild(0) instanceof RpgParser.NumberContext) {
				String value = ctx.getChild(0).getText();
				ExpressionNode en = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
				en.setValue(value);
				return en;
			}
			else if (ctx.getChild(0) instanceof RpgParser.IdentifierContext) {
				String value = ctx.getChild(0).getText();
				ExpressionNode en = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
				en.setValue(value);
				return en;
			}
			else if (ctx.getChild(0) instanceof RpgParser.LiteralContext) {
				String value = ctx.getChild(0).getText();
				ExpressionNode en = new ExpressionNode(ExpressionType.NODE_EXPRESSION);
				en.setValue(value);
				return en;
			}
		}
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#indicator_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIndicator_expr(RpgParser.Indicator_exprContext ctx) {
		//System.out.println("visitIndicator_expr");
		IRpgSyntaxElement ind = visitChildren(ctx);
		return ind;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFunction(RpgParser.FunctionContext ctx) {
		//System.out.println("visitFunction: " + ctx.getText());
		for (int i=0; i < ctx.getChildCount();i++) {
			//System.out.println(ctx.getChild(i).getClass());
		}
		visitChildren(ctx);
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#comparisonOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitComparisonOperator(RpgParser.ComparisonOperatorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#assignmentOperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitAssignmentOperator(RpgParser.AssignmentOperatorContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitArgs(RpgParser.ArgsContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitLiteral(RpgParser.LiteralContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIdentifier(RpgParser.IdentifierContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#all}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitAll(RpgParser.AllContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#functionName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFunctionName(RpgParser.FunctionNameContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#multipart_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitMultipart_identifier(RpgParser.Multipart_identifierContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#indexed_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIndexed_identifier(RpgParser.Indexed_identifierContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#opCode}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitOpCode(RpgParser.OpCodeContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitNumber(RpgParser.NumberContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#free_identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitFree_identifier(RpgParser.Free_identifierContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#continuedIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitContinuedIdentifier(RpgParser.ContinuedIdentifierContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#idOrKeyword}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitIdOrKeyword(RpgParser.IdOrKeywordContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitArgument(RpgParser.ArgumentContext ctx) {
		return null;
		
	}
	/**
	 * Visit a parse tree produced by {@link RpgParser#symbolicConstants}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	public IRpgSyntaxElement visitSymbolicConstants(RpgParser.SymbolicConstantsContext ctx) {
		return null;
		
	}
//	@Override
//	public IRpgSyntaxElement visit(ParseTree arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public IRpgSyntaxElement visitChildren(RuleNode arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public IRpgSyntaxElement visitErrorNode(ErrorNode arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	@Override
//	public IRpgSyntaxElement visitTerminal(TerminalNode arg0) {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
