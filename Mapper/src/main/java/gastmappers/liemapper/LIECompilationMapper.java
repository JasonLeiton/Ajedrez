package gastmappers.liemapper;

import ASTMCore.ASTMSemantics.AggregateScope;
import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.*;
import ASTMCore.ASTMSyntax.Expression.*;
import ASTMCore.ASTMSyntax.Statement.*;
import ASTMCore.ASTMSyntax.Types.ClassType;
import ASTMCore.metadata.LIEMapperSubTags.TagsLIEMapper;
import ASTMCore.metadata.Tags;
import gastmappers.liemapper.grammar.fodcatParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface that uses MapStruct to generate the mapping class automatically
 */
@Mapper
public interface LIECompilationMapper {
    //Instance class LIECompilationMapper
    LIECompilationMapper INSTANCE = Mappers.getMapper(LIECompilationMapper.class);

    /**
     * Function form StructMap to wrap all, this run the whole AST and
     * assemble everything.
     *
     * @param programDeclaContext the second child of the AST, program.
     * @return returns the compilation unit build it.
     */
    @Mappings({
            @Mapping(target = "path", ignore = true),
            @Mapping(target = "fragments", ignore = true),
            @Mapping(target = "language", ignore = true),
            @Mapping(target = "gPackage", ignore = true),
            @Mapping(source = "programDeclaContext",
                    target = "opensScope.declOrDefn"
                    , qualifiedByName = "ProgramDecla")

    })
    CompilationUnit compilationToCompilation(fodcatParser.ProgramContext programDeclaContext);

    /**
     * Method to map a gpio command from the LIE++ AST,
     * this method is useful when you have to assign a value to
     * a led, or an angle to a servo.
     *
     * @param pGpioCommandCntx class object from the fodcat parser that
     *                         is a gpio command.
     * @return returns a binary expression object from the GAST that
     * holds the value of the gpio command
     */
    default Expression getGpioCmmAssValue(fodcatParser.GpioContext pGpioCommandCntx) {
        //creating the binary expression
        BinaryExpression outValue = new BinaryExpression();
        //setting the name of the gpio command.
        Name tempId = new Name();
        String tempName = pGpioCommandCntx.identifier().getText();
        String tempFun = "";
        //setting the name of the function call
        if (pGpioCommandCntx.func_led() != null && pGpioCommandCntx.func_led().VALUE() != null)
            tempFun = pGpioCommandCntx.func_led().VALUE().getText();
        else if (pGpioCommandCntx.func_servo() != null && pGpioCommandCntx.func_servo().ANGLE() != null)
            tempFun = pGpioCommandCntx.func_servo().ANGLE().getText();
        tempId.setNameString(tempName + "." + tempFun);
        //setting the left operand
        IdentifierReference temp = new IdentifierReference();
        temp.setIdentifierName(tempId);
        outValue.setLeftOperand(temp);

        //setting the operator
        outValue.setOperator(new Assign());

        //setting the right operand
        outValue.setRightOperand(ExpressionCheck(pGpioCommandCntx.expression()));
        return outValue;
    }

    /**
     * Method to map a gpio command from the LIE++ AST,
     * this method is useful when you have to call a function from
     * a button, led or servo.
     *
     * @param pGpioCommandCntx class object from the fodcat parser that
     *                         is a gpio command.
     * @return returns a function call expression object from the GAST
     * that holds the value of the gpio command
     */
    default Expression getGpioFuncCall(fodcatParser.GpioContext pGpioCommandCntx) {
        //setting the name of the gpio command.
        Name tempId = new Name();
        String tempName = pGpioCommandCntx.identifier().getText();
        //setting the name of the function call
        String tempFun = "";
        //function calls for the func_button
        if (pGpioCommandCntx.func_button() != null && pGpioCommandCntx.func_button().PRESS() != null)
            tempFun = pGpioCommandCntx.func_button().PRESS().getText();
            //function calls for the func_led
        else if (pGpioCommandCntx.func_led() != null && pGpioCommandCntx.func_led().BLINK() != null)
            tempFun = pGpioCommandCntx.func_led().BLINK().getText();
        else if (pGpioCommandCntx.func_led() != null && pGpioCommandCntx.func_led().OFF() != null)
            tempFun = pGpioCommandCntx.func_led().OFF().getText();
        else if (pGpioCommandCntx.func_led() != null && pGpioCommandCntx.func_led().ON() != null)
            tempFun = pGpioCommandCntx.func_led().ON().getText();
            //function calls for the func_servo
        else if (pGpioCommandCntx.func_servo() != null && pGpioCommandCntx.func_servo().MIN() != null)
            tempFun = pGpioCommandCntx.func_servo().MIN().getText();
        else if (pGpioCommandCntx.func_servo() != null && pGpioCommandCntx.func_servo().MID() != null)
            tempFun = pGpioCommandCntx.func_servo().MID().getText();
        else if (pGpioCommandCntx.func_servo() != null && pGpioCommandCntx.func_servo().MAX() != null)
            tempFun = pGpioCommandCntx.func_servo().MAX().getText();
        //setting the name that's going to be use in the function call
        tempId.setNameString(tempName + "." + tempFun);
        //setting the GAST expression
        FunctionCallExpression tempValue = new FunctionCallExpression();
        tempValue.setFunctionName(tempId);
        return tempValue;
    }

    /**
     * Method to map a gpio command from the LIE++ AST to a GAST
     * structure.
     *
     * @param pGpioCommandContx class object from the fodcat parser that
     *                          is a gpio command.
     * @return returns a expression statement object from the GAST that
     * holds the value of the gpio command
     */
    default ExpressionStatement getGpioCommand(fodcatParser.GpioContext pGpioCommandContx) {
        ExpressionStatement outValue = new ExpressionStatement();
        //checking if it has an expression
        if (pGpioCommandContx.expression() != null)
            outValue.setExpression(getGpioCmmAssValue(pGpioCommandContx));
            //otherwise it's a function call expression
        else
            outValue.setExpression(getGpioFuncCall(pGpioCommandContx));
        outValue.addSubTag(TagsLIEMapper.GPIO_STATEMENT);
        return outValue;
    }

    /**
     * Method to get value from the relational opeator map to a binaryoperator
     * GAST object.
     *
     * @param pRelaOperaCntx object from the AST that represents the relational
     *                       operation.
     * @return return the binary operator object form the gast with a relational
     * operation value.
     */
    default BinaryOperator getRelaOp(fodcatParser.Relational_opContext pRelaOperaCntx) {
        BinaryOperator outValue = null;
        String relaOp = pRelaOperaCntx.getText();
        if (relaOp.compareTo(">") == 0) {
            outValue = new Greater();
        } else if (relaOp.compareTo(">=") == 0) {
            GenericOperator temp = new GenericOperator();
            temp.setSimbol(">=");
            outValue = temp;
        } else if (relaOp.compareTo("<") == 0) {
            outValue = new Less();
        } else if (relaOp.compareTo("<=") == 0) {
            GenericOperator temp = new GenericOperator();
            temp.setSimbol("<=");
            outValue = temp;
        } else if (relaOp.compareTo("=") == 0 || relaOp.compareTo("igual") == 0) {
            outValue = new Equal();
        } else if (relaOp.compareTo("!=") == 0 || relaOp.compareTo("diferente") == 0) {
            outValue = new NotEqual();
        }
        return outValue;
    }

    /**
     * Method to set the relational second scenario where are two relational
     * expressions.
     * second scenario relational_exp relational_op relational_exp
     *
     * @param pRelaCntxList list with all the left and right rela_expre.
     * @param pRelaOpCntx   object with the relational operator of the relaa_expre
     * @return returns a binaryExpression with the value left, right and operator
     * object from the GAST.
     */
    default Expression getRelationalSecond(List<fodcatParser.Relational_expContext> pRelaCntxList,
                                           fodcatParser.Relational_opContext pRelaOpCntx) {
        BinaryExpression outValue = new BinaryExpression();
        //setting the relational operator value.
        outValue.setOperator(getRelaOp(pRelaOpCntx));
        //setting the left side operand in the relational expression
        outValue.setLeftOperand(getRelationalExp(pRelaCntxList.get(0)));
        //setting the right side operand in the relational expression
        outValue.setRightOperand(getRelationalExp(pRelaCntxList.get(1)));
        return outValue;
    }

    /**
     * Method to get the map of the relational expression when the first
     * escenario appears
     * first escenario = (arithmetic_exp | bool_literal) relational_op (arithmetic_exp | bool_literal)
     *
     * @param pRelationalExp relational expression object form the AST language.
     * @return returns the binary expression that has a representation of the AST clases.
     */
    default Expression getRelationalFirst(fodcatParser.Relational_expContext pRelationalExp) {
        //defining locally the lists for the arith_exp and bool_exp and rela_op.
        List<fodcatParser.Arithmetic_expContext> pArithExpCntx = pRelationalExp.arithmetic_exp();
        List<fodcatParser.Bool_literalContext> pBoolExpCntx = pRelationalExp.bool_literal();
        fodcatParser.Relational_opContext pRelationalOp = pRelationalExp.relational_op();
        //setting the return binary expression value.
        BinaryExpression outValue = new BinaryExpression();
        //setting the binary operator for the binary expre.(already have tag)
        outValue.setOperator(getRelaOp(pRelationalOp));

        Expression leftOp = null, rightOp = null;
        if (pArithExpCntx != null && pArithExpCntx.size() == 2) {
            //there are only arithmetic operators
            //left operand
            leftOp = getArithmeticExp(pArithExpCntx.get(0));
            //right operand
            rightOp = getArithmeticExp(pArithExpCntx.get(1));
        } else if (pBoolExpCntx != null && pBoolExpCntx.size() == 2) {
            //there are only bool operators
            //left operand
            leftOp = getBoolLiteralExp(pBoolExpCntx.get(0));
            //right operand
            rightOp = getBoolLiteralExp(pBoolExpCntx.get(1));
        } else {
            //it has to be one and one, but which one came first?.
            String temp = pRelationalExp.getText();
            System.out.println(temp);
            if (temp.startsWith("verdadero") || temp.startsWith("falso")) {
                //left operand
                leftOp = getBoolLiteralExp(pBoolExpCntx.get(0));
                //right operand
                rightOp = getArithmeticExp(pArithExpCntx.get(0));
            } else {
                //left operand
                leftOp = getArithmeticExp(pArithExpCntx.get(0));
                //right operand
                rightOp = getBoolLiteralExp(pBoolExpCntx.get(0));
            }
        }
        outValue.setLeftOperand(leftOp);
        outValue.setRightOperand(rightOp);
        return outValue;
    }

    /**
     * Method to get the relational expression map into a GAST
     * object.
     *
     * @param pRelationalExp object from the language AST that represents
     *                       a relational expression.
     * @return returns an expression object form the GAST.
     */
    default Expression getRelationalExp(fodcatParser.Relational_expContext pRelationalExp) {
        //Making the prefix expression of the assignment.
        Expression outValue = null;
        switch (pRelationalExp.relational_exp().size()) {
            case 2:
                //checking the second scenario, has multiple
                // logical expressions on the same level
                // logic_exp logic_op logic_exp
                outValue = getRelationalSecond(pRelationalExp.relational_exp(),
                        pRelationalExp.relational_op());
                break;
            case 1:
                // checking the first scenario, when has just one relational_exp
                // and parentesis
                ParenthesizedExpression parentesis = new ParenthesizedExpression();
                parentesis.setInternalExpression(getRelationalExp(pRelationalExp.relational_exp(0)));
                outValue = parentesis;
                break;
            default:
                outValue = getRelationalFirst(pRelationalExp);
                break;
        }
        return outValue;
    }

    /**
     * Method to get bool literal value transform into a GAST
     * object.
     *
     * @param pBoolLiteExp object from the language AST that represents
     *                     a boolean literal.
     * @return returns a Literal object from the GAST.
     */
    default Literal getBoolLiteralExp(fodcatParser.Bool_literalContext pBoolLiteExp) {
        Literal tempValue = new BooleanLiteral();
        tempValue.setValue(pBoolLiteExp.getText());
        if(pBoolLiteExp.getText().compareTo("verdadero")==0){
            return tempValue;
        }
        return tempValue;
    }

    /**
     * checking second scenario
     * second scenario = logic_exp logic_op logic_exp
     *
     * @param pLogicExp class object from the fodcatparser class that represent a
     *                  list of logic_expressions.
     * @param pLogicOp  class object from the fodcatparser class that represent
     *                  the operation in a binary expression from the GAST.
     * @return return the binary expression as a expression object from the GAST.
     */
    default Expression getLogicSecond(List<fodcatParser.Logic_expContext> pLogicExp,
                                      fodcatParser.Logic_opContext pLogicOp) {
        BinaryExpression outValue = new BinaryExpression();
        //we need to get the logic left side operator
        outValue.setLeftOperand(getLogicExp(pLogicExp.get(0)));
        //we need to get the logic right side operator
        outValue.setRightOperand(getLogicExp(pLogicExp.get(1)));
        //we need to get the logic_operator.
        String arithOpValue = pLogicOp.getText();
        if (arithOpValue.compareTo("|") == 0 || arithOpValue.compareTo("o") == 0){
            outValue.setOperator(new Or());
        }
        else if (arithOpValue.compareTo("&") == 0 || arithOpValue.compareTo("y") == 0){
            outValue.setOperator(new And());
        }
        return outValue;
    }

    /**
     * checking first scenario for a logic expression
     * first scenario = identifier | array_call | bool_literal | relational_exp
     *
     * @param pLogicExp class object from the fodcatparser class that represent an
     *                  logic_expressions.
     * @return the value of the logic expression as a expression.
     */
    default Expression getLogicFirst(fodcatParser.Logic_expContext pLogicExp) {
        Expression outValue = null;
        if (pLogicExp.bool_literal() != null) {
            outValue = getBoolLiteralExp(pLogicExp.bool_literal());
        } else if (pLogicExp.identifier() != null) {
            IdentifierReference tempValue = new IdentifierReference();
            tempValue.setIdentifierName(getIdentifier(pLogicExp.identifier()));
            outValue = tempValue;
        } else if (pLogicExp.array_call() != null) {
            outValue = ArrayCallDecla(pLogicExp.array_call());
        } else if (pLogicExp.relational_exp() != null) {
            outValue = getRelationalExp(pLogicExp.relational_exp());
        }
        return outValue;
    }

    /**
     * Method recives the list of the logic_expressions assigned to a variable.
     * This function needs to be recursive.
     * it has multiple scenarios that can begin with a NOT
     * first scenario = (identifier | array_call | bool_literal | relational_exp | '(' logic_exp ')')
     * Second scenario = logic_exp logic_op logic_exp
     *
     * @param pLogicExp class object from the fodcatparser class that represent an
     *                  logic_expressions.
     * @return return the whole logic expression as a one.
     */
    default Expression getLogicExp(fodcatParser.Logic_expContext pLogicExp) {
        //Making the prefix expression of the assignment.
        PrefixUnaryExpression negationValue = new PrefixUnaryExpression();
        Expression outValue = null;
        switch (pLogicExp.logic_exp().size()) {
            case 2:
                //checking the second scenario, has multiple
                // logical expressions on the same level
                // logic_exp logic_op logic_exp
                outValue = getLogicSecond(pLogicExp.logic_exp(), pLogicExp.logic_op());
                break;
            case 1:
                // checking the first scenario, when has just one logic_exp
                // and parentesis
                ParenthesizedExpression parentesis = new ParenthesizedExpression();
                parentesis.setInternalExpression(getLogicExp(pLogicExp.logic_exp(0)));
                outValue = parentesis;
                break;
            default:
                outValue = getLogicFirst(pLogicExp);
                break;
        }
        if (pLogicExp.NOT() != null) {
            negationValue.setOperator(new Not());
            negationValue.getOperator().setSimbolo("!");
            negationValue.setOperand(outValue);
            return negationValue;
        }
        return outValue;
    }

    /**
     * Method to map the uniry plus or minus in front of the
     * arithmetic expression.
     *
     * @param pSignContx class form the fodcatparse class that represent a context
     *                   of a sign token.
     * @return return the value of the sign as a prefix unary expression form the GAST.
     */
    default PrefixUnaryExpression getSignValue(fodcatParser.SignContext pSignContx) {
        String tempSign = pSignContx.getText();
        PrefixUnaryExpression signValue = new PrefixUnaryExpression();
        switch (tempSign) {
            case "+":
                signValue.setOperator(new UnaryPlus());
            case "-":
                signValue.setOperator(new UnaryMinus());
        }
        signValue.getOperator().setSimbolo(tempSign);
        return signValue;
    }

    /**
     * checking second scenario
     * second scenario = arithmetic_exp arithmetic_op arithmetic_exp
     *
     * @param pArithmeticExp class object from the fodcatparser class that represent a
     *                       list of arithmetic_expressions.
     * @param pArithmeticOp  class object from the fodcatparser class that represent
     *                       the operation in a binary expression from the GAST.
     * @return return the binary expression as a expression object from the GAST.
     */
    default Expression getArithmeticSecond(List<fodcatParser.Arithmetic_expContext> pArithmeticExp,
                                           fodcatParser.Arithmetic_opContext pArithmeticOp) {
        BinaryExpression outValue = new BinaryExpression();
        //we need to get the arithmetic left side operator
        outValue.setLeftOperand(getArithmeticExp(pArithmeticExp.get(0)));
        //we need to get the arithmetic right side operator
        outValue.setRightOperand(getArithmeticExp(pArithmeticExp.get(1)));
        //we need to get the arith_operator.
        String arithOpValue = pArithmeticOp.getText();
        if (arithOpValue.compareTo("+") == 0) {
            outValue.setOperator(new Add());
        }else if (arithOpValue.compareTo("-") == 0){
            outValue.setOperator(new Subtract());
        }else if (arithOpValue.compareTo("*") == 0) {
            outValue.setOperator(new Multiply());
        }else if (arithOpValue.compareTo("/") == 0){
            outValue.setOperator(new Divide());
        }else if (arithOpValue.compareTo("**") == 0){
            outValue.setOperator(new Exponent());
        }else if (arithOpValue.compareTo("mod") == 0){
            outValue.setOperator(new Modulus());
        }
        return outValue;
    }

    /**
     * checking first scenario
     * first scenario = (int_literal | float_literal | constant | identifier | array_call ) | '(' arithmetic_exp ')'
     *
     * @param pArithmeticExp class object from the fodcatparser class that represent an
     *                       arithmetic_expressions.
     * @return the value of the arithmetic expression as a expression.
     */
    default Expression getArithmeticFirst(fodcatParser.Arithmetic_expContext pArithmeticExp) {
        Expression outValue = null;
        if (pArithmeticExp.int_literal() != null) {
            outValue = IntegerDecla(pArithmeticExp.int_literal());
        } else if (pArithmeticExp.float_literal() != null) {
            outValue = FloatDecla(pArithmeticExp.float_literal());
        } else if (pArithmeticExp.constant() != null) {
            outValue = getConstantExp(pArithmeticExp.constant());
        } else if (pArithmeticExp.array_call() != null) {
            outValue = ArrayCallDecla(pArithmeticExp.array_call());
        } else if (pArithmeticExp.identifier() != null) {
            IdentifierReference tempValue = new IdentifierReference();
            tempValue.setIdentifierName(getIdentifier(pArithmeticExp.identifier()));
            outValue = tempValue;
        }
        return outValue;
    }

    /**
     * Method recives the list of the arith_expressions
     * assigned to a variable.
     * This function needs to be recursive.
     * it has multiple scenarios that can begin with a sing
     * first scenario = (int_literal | float_literal | constant | identifier | array_call ) | '(' arithmetic_exp ')'
     * Second scenario = arithmetic_exp arithmetic_op arithmetic_exp
     *
     * @param pArithmeticExp class object from the fodcatparser class that represent an
     *                       arithmetic_expressions.
     * @return return the whole arithmetic expression as a one.
     */
    default Expression getArithmeticExp(fodcatParser.Arithmetic_expContext pArithmeticExp) {
        //Making the prefix expression of the assignment.
        PrefixUnaryExpression signValue = new PrefixUnaryExpression();
        Expression outValue = null;
        switch (pArithmeticExp.arithmetic_exp().size()) {
            case 2:
                //checking the second scenario, has multiple
                // arithmetic expressions on the same level
                // arithmetic_exp arithmetic_op arithmetic_exp
                outValue = getArithmeticSecond(pArithmeticExp.arithmetic_exp(), pArithmeticExp.arithmetic_op());
                break;
            case 1:
                // checking the first scenario, when has just one arith_exp
                // and parentesis
                ParenthesizedExpression parentesis = new ParenthesizedExpression();
                parentesis.setInternalExpression(getArithmeticExp(pArithmeticExp.arithmetic_exp(0)));
                outValue = parentesis;
                break;
            default:
                outValue = getArithmeticFirst(pArithmeticExp);
                break;
        }
        if (pArithmeticExp.sign() != null) {
            signValue = getSignValue(pArithmeticExp.sign());
            signValue.setOperand(outValue);
            return signValue;
        }
        return outValue;
    }

    /**
     * getting PI value if PI constant was assigned to the variable,
     * otherwise assigns EULER value, because it's a fact that a
     * constant was assigned.
     *
     * @param pConstValue class object from the fodcatparser class that represent
     *                    a constant context.
     * @return a double value, it can be iter PI or Euler.
     */
    default Expression getConstantExp(fodcatParser.ConstantContext pConstValue) {
        /*getting PI value if PI constant was assigned to the variable,
         otherwise assigns EULER value, because it's a fact that a
         constant was assigned.*/
        double temp = (pConstValue.PI() != null) ? Math.PI : Math.E;
        Literal tempValue = new RealLiteral();
        tempValue.setValue(String.valueOf(temp));
        return tempValue;
    }

    /**
     * Method to map the parameters when we call a function.
     *
     * @param pFunParaCntx object form the fodcat parser that contain all
     *                     the parameters from a function called.
     * @return returns an array with all the parameters map to the GAST.
     */
    default ArrayList<ActualParameter> getFunctionParameterCall(fodcatParser.Parameters_calledContext pFunParaCntx) {
        //list in which we are going to keep all the GAST parameters
        ArrayList<ActualParameter> outValue = new ArrayList<>();
        //list with the parameters form the AST.
        List<fodcatParser.ExpressionContext> parameters = pFunParaCntx.expression();
        //iterating
        int i = 0;
        for (fodcatParser.ExpressionContext temp : parameters) {
            //capturing the expression form a parameter
            ActualParameterExpression tempPara = new ActualParameterExpression();
            //setting the value expression value form the expression.
            tempPara.setValue(ExpressionCheck(temp));
            //adding the parameter to the array of parameters.
            outValue.add(tempPara);
            i++;
        }
        return outValue;
    }

    /**
     * Method to map function call and set as an expression
     *
     * @param pFuncCallContext objecto from the function called context AST.
     * @return returns a Function call expression to the GAST.
     */
    default FunctionCallExpression getFunCallExp(fodcatParser.Func_callContext pFuncCallContext) {
        FunctionCallExpression outValue = new FunctionCallExpression();
        //setting the name of the function.
        outValue.setFunctionName(getIdentifier(pFuncCallContext.identifier()));
        //setting the parameters for the function.
        outValue.setActualParams(getFunctionParameterCall(pFuncCallContext.parameters_called()));
        //returning value.
        return outValue;
    }

    /**
     * Method to get the identifier converted into
     * an object from the GAST.
     *
     * @param pIdenContx class object from the fodcatparser class that
     *                   represent an identifier from the AST.
     * @return returns an object NAME, contains the value of an Identifier.
     */
    default Name getIdentifier(fodcatParser.IdentifierContext pIdenContx) {
        Name temp = new Name(pIdenContx.getText());
        return temp;
    }

    /**
     * Method to return the value of an Expression
     * This are going to be the possible tokens identified on a expression
     * identifier | strg | constant | arithmetic_exp
     * | array_call | func_call | logic_exp | relational_exp
     * for the assignment of a variable we need to map everyone of those.
     *
     * @param pExpContext class object from the fodcatparser class that
     *                    represent an expression form the AST.
     * @return returns a DataDefinition object the has the whole expression
     * from an assignment.
     */
    default Expression ExpressionCheck(fodcatParser.ExpressionContext pExpContext) {
        Expression value = null;
        if (pExpContext.identifier() != null) {
            IdentifierReference tempValue = new IdentifierReference();
            tempValue.setIdentifierName(getIdentifier(pExpContext.identifier()));
            value = tempValue;
        }else if (pExpContext.strg() != null) {
            StringLiteral tempValue = new StringLiteral();
            tempValue.setValue(pExpContext.strg().getText());
            value = tempValue;
        } else if (pExpContext.constant() != null) {
            value = getConstantExp(pExpContext.constant());
        } else if (pExpContext.array_call() != null) {
            value = ArrayCallDecla(pExpContext.array_call());
        } else if (pExpContext.func_call() != null) {
            value = getFunCallExp(pExpContext.func_call());
        } else if (pExpContext.arithmetic_exp() != null) {
            value = getArithmeticExp(pExpContext.arithmetic_exp());
        } else if (pExpContext.logic_exp() != null) {
            value = getLogicExp(pExpContext.logic_exp());
        } else if (pExpContext.relational_exp() != null) {
            value = getRelationalExp(pExpContext.relational_exp());
        }
        return value;
    }

    /**
     * Method to add an array call, based on the AST, it can be call form an
     * assignment, expression, map an array_call using GAST objects.
     *
     * @param pArrayContx an array_call context object from the fodcat parser
     *                    class.
     * @return returns an arrayAccess object from the GAST.
     */
    default ArrayAccess ArrayCallDecla(fodcatParser.Array_callContext pArrayContx) {
        ArrayAccess outValue = new ArrayAccess();
        if (pArrayContx.identifier() != null) {
            //setting the name of the array.
            IdentifierReference arrayName = new IdentifierReference();
            arrayName.setIdentifierName(getIdentifier(pArrayContx.identifier()));
            outValue.setArrayName(arrayName);
        }
        //time to set the value in the brackets on the array(index).
        Expression arithExpre = getArithmeticExp(pArrayContx.arithmetic_exp());
        outValue.setIndex(arithExpre);
        return outValue;
    }

    /**
     * method to get an assignment at anywhere.
     * An assignment can be form by the blocks
     * (identifier | array_call) ASSIGN_OP expression;
     *
     * @param pAssignmentContx class object from the fodcatparser class that
     *                         represent an assignment object form the AST.
     * @return returns a Definition object tha has a DataDefinitio with its
     * expressions.
     */
    default BinaryExpression AssignmentDecla(fodcatParser.AssignmentContext pAssignmentContx) {
        BinaryExpression outValue = new BinaryExpression();
        //we need to get the arithmetic left side operator
        if (pAssignmentContx.identifier() != null) {
            IdentifierReference tempIden = new IdentifierReference();
            tempIden.setIdentifierName(getIdentifier(pAssignmentContx.identifier()));
            outValue.setLeftOperand(tempIden);
        } else {
            ArrayAccess arrCallValue = ArrayCallDecla(pAssignmentContx.array_call());
            outValue.setLeftOperand(arrCallValue);
        }

        //defining the assignment operator to the binary operator.
        outValue.setOperator(new Assign());

        //we are defining the right side of the binary operation
        outValue.setRightOperand(ExpressionCheck(pAssignmentContx.expression()));
        return outValue;
    }

    /**
     * Method to get an assignment at the goblal scope as a definition object.
     *
     * @param pAssignmentDeclaSorted class object from the fodcatparser class that represent a
     *                               list of assignment declarations.
     * @return return an assignments declared as a definition object.
     */
    default DefintionObject AssignmentDeclaSorted(fodcatParser.AssignmentContext pAssignmentDeclaSorted) {
        //TODO : we need to find a better container for the Expression
        // in order to coupe with a DefinitionObject.
        VariableDefinition tempVar = new VariableDefinition();
        List<Fragment> fragmentsList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Fragment valueFrag = new Fragment();
            valueFrag.setInitialValue(AssignmentDecla(pAssignmentDeclaSorted));
            fragmentsList.add(valueFrag);
        }
        tempVar.setFragments(fragmentsList);
        return tempVar;
    }

    /**
     * Method to map the value that's is going to be assigned to the
     * gpio variable.
     *
     * @param pGpioAssignValue an object from the AST fodcatparser that
     *                         represents the value.
     * @return returns an expression from the GAST that's a function call
     * expression that has the name of the gpio object and the
     * parameters that carry alongside.
     */
    default Expression getGpioAssignedValue(fodcatParser.Gpio_declarationContext pGpioAssignValue) {
        FunctionCallExpression outValue = new FunctionCallExpression();
        //setting the name of the function.
        Name tempId = new Name();
        if (pGpioAssignValue.BUTTON() != null)
            tempId.setNameString(pGpioAssignValue.BUTTON().getText());
        else if (pGpioAssignValue.LED() != null) {
            String tempName = pGpioAssignValue.LED().getText();
            if (pGpioAssignValue.PWMLED() != null) {
                tempName = tempName + "." + pGpioAssignValue.PWMLED().getText();
            }
            tempId.setNameString(tempName);
        } else if (pGpioAssignValue.SERVO() != null)
            tempId.setNameString(pGpioAssignValue.SERVO().getText());
        outValue.setFunctionName(tempId);
        //setting the parameters for the function.
        outValue.setActualParams(getFunctionParameterCall(pGpioAssignValue.parameters_called()));
        //returning value.
        outValue.addSubTag(TagsLIEMapper.GPIO_ASSIGNMENT);
        return outValue;
    }

    /**
     * Method to map a gpio declaration from the lie++ language.
     *
     * @param pGpioContx an AST object from the fodcatparser, that
     *                   carry the name of the variable and the
     *                   assigned value.
     * @return returns a binary expression from the GAST, that represent
     * a gpio declaration, that's in fact, a variable that receive
     * a function call expression.
     */
    default BinaryExpression GpioDecla(fodcatParser.Gpio_declarationContext pGpioContx) {
        BinaryExpression outValue = new BinaryExpression();
        //we need to get the arithmetic left side operator
        if (pGpioContx.identifier() != null) {
            IdentifierReference tempIden = new IdentifierReference();
            tempIden.setIdentifierName(getIdentifier(pGpioContx.identifier()));
            outValue.setLeftOperand(tempIden);
        }
        //defining the assignment operator to the binary operator.
        outValue.setOperator(new Assign());

        //we are defining the right side of the binary operation
        outValue.setRightOperand(getGpioAssignedValue(pGpioContx));
        outValue.addSubTag(TagsLIEMapper.GPIO_DECLARATION);
        return outValue;
    }

    /**
     * Method to map a gpio declarations and pack into a definition object.
     *
     * @param pGpioDeclaSorted an object from the fodcatparser AST that
     *                         holds a gpio declaration
     * @return returns a list with definition object's from the GAST that
     * holds a gpio declaration.
     */
    default DefintionObject GpioDeclaSorted(fodcatParser.Gpio_declarationContext pGpioDeclaSorted) {
        //TODO : we need to find a better container for the Expression
        // in order to coupe with a DefinitionObject.
        VariableDefinition tempVar = new VariableDefinition();
        List<Fragment> fragmentsList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Fragment valueFrag = new Fragment();
            valueFrag.setInitialValue(GpioDecla(pGpioDeclaSorted));
            fragmentsList.add(valueFrag);
        }
        tempVar.setFragments(fragmentsList);
        return tempVar;
    }

    /**
     * Method to map the value that's is going to be assigned to the
     * gpio variable.
     *
     * @param pArryAssignValue an object from the AST fodcatparser that
     *                         represents the value.
     * @return returns an expression from the GAST that's a function call
     * expression that has the name of the gpio object and the
     * parameters that carry alongside.
     */
    default Expression getArrayAssignedValue(fodcatParser.Array_declarationContext pArryAssignValue) {
        ArrayCreation outValue = new ArrayCreation();
        //getting the arithmetic expression assigned
        List<Expression> tempListAssign = new ArrayList<>();
        tempListAssign.add(getArithmeticExp(pArryAssignValue.arithmetic_exp()));
        outValue.setDimension(tempListAssign);
        //returning value.
        return outValue;
    }

    /**
     * Method to map a array declaration from the lie++ language.
     *
     * @param pArrayContx an AST object from the fodcatparser, that
     *                    carry the name of the variable and the
     *                    assigned value.
     * @return returns a binary expression from the GAST, that represent
     * a array declaration, that's in fact, a variable that receive
     * an array creation expression.
     */
    default BinaryExpression ArrayDecla(fodcatParser.Array_declarationContext pArrayContx) {
        BinaryExpression outValue = new BinaryExpression();
        //we need to get the arithmetic left side operator
        if (pArrayContx.identifier() != null) {
            IdentifierReference tempIden = new IdentifierReference();
            tempIden.setIdentifierName(getIdentifier(pArrayContx.identifier()));
            outValue.setLeftOperand(tempIden);
        }
        //defining the assignment operator to the binary operator.
        outValue.setOperator(new Assign());

        //we are defining the right side of the binary operation
        outValue.setRightOperand(getArrayAssignedValue(pArrayContx));
        return outValue;
    }

    /**
     * Method to map an array declaration and pack into a definition objects.
     *
     * @param pArrayDeclaSorted an object from the fodcatparser AST that
     *                          holds an array declaration
     * @return returns a definition object from the GAST that holds an
     * array declaration
     */
    default DefintionObject ArrayDeclaSorted(fodcatParser.Array_declarationContext pArrayDeclaSorted) {
        //TODO : we need to find a better container for the Expression
        // in order to coupe with a DefinitionObject.
        //setting the variable definition
        VariableDefinition tempVar = new VariableDefinition();
        List<Fragment> fragmentsList = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            Fragment valueFrag = new Fragment();
            valueFrag.setInitialValue(ArrayDecla(pArrayDeclaSorted));
            fragmentsList.add(valueFrag);
        }
        //setting the binary expression into the variable
        tempVar.setFragments(fragmentsList);
        return tempVar;
    }

    /**
     * Method to map the flow expressions from LIE into the break and continue statements from GAST.
     *
     * @param pFlowStatementContext flow expression context object from fodcatParser (LIE object)
     * @return returns a break or continue statement from GAST
     */
    default Statement FlowStatementDecla(fodcatParser.Flow_expContext pFlowStatementContext) {
        if (pFlowStatementContext.BREAK()!=null)
            return new BreakStatement();
        else
            return new ContinueStatement();
    }

    /**
     * Method to map a literal of type integer from LIE into a literal of type integer in GAST.
     *
     * @param pIntegerDeclaContext object from fodcatparser AST that holds an integer
     * @return returns an integer expression from GAST.
     */
    default Expression IntegerDecla(fodcatParser.Int_literalContext pIntegerDeclaContext) {
        IntegerLiteral integer = new IntegerLiteral();
        integer.setValue(pIntegerDeclaContext.INTEGER().getText());
        return integer;
    }

    /**
     * Method to map a literal of type float from LIE into a literal of type real in GAST.
     *
     * @param pFloatDeclaContext object from fodcatparser AST that holds an integer
     * @return returns an integer expression from GAST.
     */
    default Expression FloatDecla(fodcatParser.Float_literalContext pFloatDeclaContext) {
        Literal floating = new RealLiteral();
        floating.setValue(pFloatDeclaContext.getText());
        return floating;
    }

    /**
     * Method to map a list of fodcatparser commands into a BlockStatement from GAST.
     * @param pCommandContext list of commandContext objects from fodcatparser
     * @return returns a BlockStatement that holds all the mapped statements.
     */
    default BlockStatement BodyStatementDecla (List<fodcatParser.CommandContext> pCommandContext) {
        BlockStatement body = new BlockStatement();

        ArrayList<Statement> commands = new ArrayList<>();
        Statement command;
        fodcatParser.CommandContext temp;
        int length = pCommandContext.size();
        for (int i = 0; i < length; i++){
            temp = pCommandContext.get(i);
            command = CommandDecla(temp);
            commands.add(command);
        }
        body.setSubStatements(commands);
        return body;
    }

    /**
     * Method to map a Repeat command from fodcatparser into a For Statement from GAST
     * @param pIntLiteralContext integer parameter of for expression in fodcatparser, it is one of the ways to indicate
     *                           the amount of iterations
     * @param pIdentifierContext identifier parameter of for expression in fodcatparser, it is one of the ways to
     *                           indicate the amount of iterations
     * @param pArithmeticExpressionContext artithmetic expression parameter of for expression in fodcatparser, it is one
     *                                     of the ways to indicate the amount of iterations
     * @param pCommandContext list of commands within the body of the for statement
     * @return returns a ForIterator statement from GAST
     */
    default Statement RepeatStatementDecla (fodcatParser.Int_literalContext pIntLiteralContext,
                                            fodcatParser.IdentifierContext pIdentifierContext,
                                            fodcatParser.Arithmetic_expContext pArithmeticExpressionContext,
                                            List<fodcatParser.CommandContext> pCommandContext) {
        ForIterator forStatement = new ForIterator();
        ArrayList<Expression> initBody = new ArrayList<>();
        if (pIntLiteralContext!=null){
            Expression intLiteral = IntegerDecla(pIntLiteralContext);
            initBody.add(intLiteral);
        }
        if (pIdentifierContext!=null){
            IdentifierReference identifier = new IdentifierReference();
            identifier.setIdentifierName(getIdentifier(pIdentifierContext)); // refers to ???
            initBody.add(identifier);
        }
        if  (pArithmeticExpressionContext!=null){
            Expression arithmeticExpression = getArithmeticExp(pArithmeticExpressionContext);
            initBody.add(arithmeticExpression);
        }
        forStatement.setInitBody(initBody);
        forStatement.setBody(BodyStatementDecla(pCommandContext));
        return forStatement;
    }

    /**
     * Method to map the while command from fodcatparset into a while statement from GAST.
     * @param pLogicExpressionContext logic expression parameter of while expression from fodcatparser, is one of the
     *                                two ways to set the while execution flag.
     * @param pGPIOContext gpio expression parameter of while expression from fodcatparser, is one of the two ways to
     *                     set the while execution flag.
     * @param pCommandContext list of commands within the body of the while statement.
     * @return returs a WhileStatement from GAST.
     */
    default Statement WhileStatementDecla (fodcatParser.Logic_expContext pLogicExpressionContext,
                                           fodcatParser.GpioContext pGPIOContext,
                                           List<fodcatParser.CommandContext> pCommandContext) {
        WhileStatement whileStatement = new WhileStatement();

        if (pLogicExpressionContext!=null) {
            Expression logicExpression = getLogicExp(pLogicExpressionContext);
            whileStatement.setCondition(logicExpression);
        } else {
            Expression gpioExpression = getGpioCommand(pGPIOContext).getExpression();
            whileStatement.setCondition(gpioExpression);
        }

        whileStatement.setBody(BodyStatementDecla(pCommandContext));
        return whileStatement;
    }

    /**
     * Method to map a set of if and else commands from fodcatparser into an If Statement from GAST .
     * @param pIfStatementContext first if statement from fodcatparser to map.
     * @param pElseStatementContext array of else and else if  statements from fodcatparser to map.
     * @return returns an IfStatement from GAST.
     */
    default IfStatement IfStatementDecla (fodcatParser.IfstatContext pIfStatementContext,
                                          List<fodcatParser.ElsestatContext> pElseStatementContext) {
        IfStatement ifStatement = new IfStatement();
        // Mapping the condition
        Expression condition;
        if (pIfStatementContext.logic_exp()!=null){
            condition = getLogicExp(pIfStatementContext.logic_exp());
        } else {
            condition = getGpioCommand(pIfStatementContext.gpio()).getExpression();
        }

        // Mapping the elseBody
        if (pElseStatementContext!=null && pElseStatementContext.size()!=0) {
            Statement elseBody;
            //checking if it's an "else if", or just an "else".
            if (pElseStatementContext.get(0).ifstat()!=null){
                //else if
                elseBody = IfStatementDecla(pElseStatementContext.get(0).ifstat(),
                        pElseStatementContext.subList(1,pElseStatementContext.size()));
                elseBody.addSubTag(TagsLIEMapper.ELSE_IF_STATEMENT);
            } else {
                //else
                elseBody = BodyStatementDecla(pElseStatementContext.get(0).command());
                elseBody.addSubTag(TagsLIEMapper.ELSE_STATEMENT);
            }
            ifStatement.setElseBody(elseBody);
        }
        //mapping the then & condition for the IF.
        ifStatement.setCondition(condition);
        ifStatement.setThenBody(BodyStatementDecla(pIfStatementContext.command()));
        return ifStatement;
    }

    /**
     * Method to map a Return command from fodcatparser into a Return statement from GAST.
     * @param pExpressionContext expression to return by the fodcatparser command.
     * @return returns a ReturnStatement from GAST.
     */
    default Statement ReturnStatementDecla (fodcatParser.ExpressionContext pExpressionContext) {
        ReturnStatement returnStatement = new ReturnStatement();
        returnStatement.setReturnValue(ExpressionCheck(pExpressionContext));

        return returnStatement;
    }

    /**
     * Iterative method to map the print parameter with its operands and expressions from fodcatpaser into a
     * BinaryExpression from GAST. This method takes the first operand from the rightOperands list and maps it into a
     * GAST expression to create a BinaryExpression with this as the right operand and the pLeftOperand as the left
     * operand of the expression.
     * @param pLeftOperand expression associated with the left operand, this one is already a mapped GAST expression.
     * @param pRightOperandContext list of operands to be mapped.
     * @return returns a BinaryExpression from GAST.
     */
    default Expression getPrintExpression (Expression pLeftOperand,
                                           List<fodcatParser.ExpressionContext> pRightOperandContext) {
        BinaryExpression printExpression = new BinaryExpression();
        //left operand
        printExpression.setLeftOperand(pLeftOperand);
        //right operand
        Expression rightOperand = ExpressionCheck(pRightOperandContext.get(0));
        printExpression.setRightOperand(rightOperand);

        List<fodcatParser.ExpressionContext> newList = pRightOperandContext.subList(1, pRightOperandContext.size());

        //print operand
        Add printOperand = new Add();
        printOperand.setSimbol("+");
        printExpression.setOperator(printOperand);

        if (newList.size()==0) {
            return printExpression;
        }
        return getPrintExpression(printExpression, newList);
    }

    /**
     * Method to map the print command's paramenter into an ActualParamenterExpression from GAST.
     * @param pExpressionContext list of fodcatparser expressions that represent what needs to be printed.
     * @return returns an ActualParamenterExpression from GAST.
     */
    default ActualParameterExpression getPrintParameter (List<fodcatParser.ExpressionContext> pExpressionContext) {
        ActualParameterExpression printParameter = new ActualParameterExpression();
        Expression printExpression;
        if (pExpressionContext.size()>1) {
            List<fodcatParser.ExpressionContext> temp = pExpressionContext.subList(1,pExpressionContext.size());
            printExpression = getPrintExpression(ExpressionCheck(pExpressionContext.get(0)), temp);
        } else {
            printExpression = ExpressionCheck(pExpressionContext.get(0));
        }
        printParameter.setValue(printExpression);
        return printParameter;
    }

    /**
     * Method to map a print command from fodcatparser into an ExpressionStatement
     * that holds the call to the print function
     * through a FunctionCallExpression from GAST.
     * @param pName name of the print function.
     * @param pExpressionContext list of expressions to be printed, all binded by the print operand (+)
     * @return returns an Statement that holds the call to the print function.
     */
    default Statement PrintStatementDecla (String pName,
                                           List<fodcatParser.ExpressionContext> pExpressionContext) {
        ExpressionStatement printCallStatement = new ExpressionStatement();
        FunctionCallExpression printCallExpression = new FunctionCallExpression();

        printCallExpression.setFunctionName(new Name(pName));

        ArrayList<ActualParameter> printParameter = new ArrayList<>();
        printParameter.add(getPrintParameter(pExpressionContext));
        printCallExpression.setActualParams(printParameter);

        printCallStatement.setExpression(printCallExpression);
        return printCallStatement;
    }

    /**
     * Method to map the sleep command from fodcatparser into an ExpressionStatement
     * that holds the call to the sleep function
     * through a FunctionCallExpression from GAST.
     * @param pSleepContext sleep command from the fodcatparser.
     * @return returns an Statements that holds the call to the sleep function.
     */
    default Statement SleepStatementDecla (fodcatParser.SleepContext pSleepContext) {
        ExpressionStatement sleepCallStatement = new ExpressionStatement();
        FunctionCallExpression sleepCallExpression = new FunctionCallExpression();

        // Setting name
        sleepCallExpression.setFunctionName(new Name(pSleepContext.SLEEP().getText()));

        // Setting parameter
        ArrayList<ActualParameter> sleepParameter = new ArrayList<>();
        ActualParameterExpression tmp = new ActualParameterExpression();
        tmp.setValue(ExpressionCheck(pSleepContext.expression()));
        sleepParameter.add(tmp);
        sleepCallExpression.setActualParams(sleepParameter);

        // Setting statement
        sleepCallStatement.setExpression(sleepCallExpression);
        return sleepCallStatement;
    }

    /**
     * Method to map every command from fodcatparser into an statement from GAST.
     * @param pCommandContext command from fodcatparser to be mapped.
     * @return returns a GAST statement with the mapped command.
     */
    default Statement CommandDecla (fodcatParser.CommandContext pCommandContext) {
        Statement command = new EmptyStatement();
        if (pCommandContext.ifstat()!=null) {
            command = IfStatementDecla(pCommandContext.ifstat(), pCommandContext.elsestat());
        }else if (pCommandContext.WHILE() != null) {
            command = WhileStatementDecla(pCommandContext.logic_exp(), pCommandContext.gpio(),
                    pCommandContext.command());
        }else if (pCommandContext.REPEAT()!=null) {
            command =  RepeatStatementDecla(pCommandContext.int_literal(), pCommandContext.identifier(),
                    pCommandContext.arithmetic_exp(), pCommandContext.command());
        }else if (pCommandContext.PRINT()!=null) {
            command = PrintStatementDecla(pCommandContext.PRINT().getText(),
                    pCommandContext.expression());
        }else if (pCommandContext.RETURN()!=null) {
            command = ReturnStatementDecla(pCommandContext.expression(0));
        }else if (pCommandContext.gpio()!=null) {
            command = getGpioCommand(pCommandContext.gpio());
        }else if (pCommandContext.sleep()!=null) {
            command = SleepStatementDecla(pCommandContext.sleep());
        }else if (pCommandContext.flow_exp()!=null) {
            command = FlowStatementDecla(pCommandContext.flow_exp());
        }else if (pCommandContext.func_call()!=null) {
            ExpressionStatement funcCallStatement = new ExpressionStatement();
            funcCallStatement.setExpression(getFunCallExp(pCommandContext.func_call()));
            command = funcCallStatement;
        }else if (pCommandContext.assignment()!=null) {
            ExpressionStatement assignmentStatement = new ExpressionStatement();
            assignmentStatement.setExpression(AssignmentDecla(pCommandContext.assignment()));
            command = assignmentStatement;
        }else if (pCommandContext.array_declaration()!=null) {
            ExpressionStatement arrayStatement = new ExpressionStatement();
            arrayStatement.setExpression(ArrayDecla(pCommandContext.array_declaration()));
            command = arrayStatement;
        }
        return command;
    }

    /**
     * Method to map a list of parameters from a function into an array of parameters from GAST.
     * @param pParametersContext objecto from fodcatparser that holds the list of parameters.
     * @return returns an Array of FormalParameterDefinition from GAST.
     */
    default ArrayList<FormalParameterDefinition> ParametersDeclaList (fodcatParser.Parameters_declarationContext pParametersContext){
        // GAST array of parameters
        ArrayList<FormalParameterDefinition> parameters = new ArrayList<>();
        // LIE array of parameters
        List<fodcatParser.IdentifierContext> ids = pParametersContext.identifier();
        // Parse every LIE parameter into a GAST parameter and add it to the GAST array of parameters one by one
        for(fodcatParser.IdentifierContext temp : ids){
            FormalParameterDefinition parameter = new FormalParameterDefinition();
            parameter.setIdentifierName(getIdentifier(temp));
            parameters.add(parameter);
        }
        // return GAST array of parameters
        return parameters;
    }

    /**
     * Method to map the main function from the fodcatparser into a GAST Function.
     * @param pMainFunctionContext main function object from fodcatparser.
     * @return returns a FunctionDefinition from GAST.
     */
    default DefintionObject MainFunctionDecla (fodcatParser.Main_functionContext pMainFunctionContext){
        FunctionDefintion myFunction = new FunctionDefintion();
        // formalParameters
        ArrayList<FormalParameterDefinition> parameters = new ArrayList<>();
        myFunction.setFormalParameters(parameters);

        // Statement body
        BlockStatement body = BodyStatementDecla(pMainFunctionContext.command());
        myFunction.setBody(body);

        myFunction.setIdentifierName(new Name(pMainFunctionContext.MAIN_NAME().getText()));
        return myFunction;
    }

    /**
     * Method to map a fodcatparser function into a GAST Function.
     * @param pFunctionContext pMainFunctionContext main function object from fodcatparser.
     * @return returns a FunctionDefinition from GAST.
     */
    default DefintionObject FunctionDecla (fodcatParser.Function_declarationContext pFunctionContext){
        FunctionDefintion myFunction = new FunctionDefintion();

        // formalParameters
        ArrayList<FormalParameterDefinition> parameters =
                ParametersDeclaList(pFunctionContext.parameters_declaration());
        myFunction.setFormalParameters(parameters);

        // Statement body
        BlockStatement body = BodyStatementDecla(pFunctionContext.command());
        myFunction.setBody(body);
        //setting the function name
        myFunction.setIdentifierName(getIdentifier(pFunctionContext.identifier()));
        return myFunction;
    }

    /**
     * Function to build the whole program declaration into the GAST.
     * @param programDeclaContext the root of the program coming from the AST of
     *                            LIE++, this is going to be transformed into a
     *                            GAST ArrayList of definition objects.
     * @return returns an array list of definition objects that belongs to the GAST,
     *         and they are going to be merge into a compilation unit.
     */
    @Named("ProgramDecla")
    default ArrayList<DefintionObject> ProgramDecla (fodcatParser.ProgramContext programDeclaContext){
        //defining a variable to hold the parse tree variable, &
        // an array to keep al the arrays
        ParseTree temp = null ;
        ArrayList<DefintionObject> finalValues = new ArrayList<>();
        DefintionObject outValue = null;
        //iterating over the nodes to map them in the correct order
        int length = (programDeclaContext.getChildCount());
        for (int i = 0; i < length; i++){
            temp = programDeclaContext.getChild(i);
            //here we are going to map the assignments
            if(temp.getClass()==fodcatParser.AssignmentContext.class)
                outValue = AssignmentDeclaSorted((fodcatParser.AssignmentContext)temp);
                //here we're going to map the gpio declaration
            else if(temp.getClass()==fodcatParser.Gpio_declarationContext.class)
                outValue = GpioDeclaSorted((fodcatParser.Gpio_declarationContext)temp);
                //here we're going to map the array declaration
            else if(temp.getClass()==fodcatParser.Array_declarationContext.class)
                outValue = ArrayDeclaSorted((fodcatParser.Array_declarationContext)temp);
                //here we're going to map the function declaration
            else if(temp.getClass()==fodcatParser.Function_declarationContext.class)
                outValue = FunctionDecla((fodcatParser.Function_declarationContext)temp);
                //here we're going to map the main function declaration
            else if(temp.getClass()==fodcatParser.Main_functionContext.class)
                outValue = MainFunctionDecla((fodcatParser.Main_functionContext)temp);
            //TODO: not implemented yet!!
            finalValues.add(outValue);
        }
        /**
         *  Creating the main class, <<this is to conform with the java standard>>.
         *  Then we create the aggregate scope, then add the declaration or
         *  definition in which we are going to add all the actual lie code.
         *  Then we add the class to an AggregateTypeDefinition value, as an
         *  aggregate type, and then add definition to an array of the same kind,
         *  add the definition, and lastly, return the array.
         */
        ClassType MainClass = new ClassType();
        MainClass.setNameString(new Name (Tags.CLASS_TYPE));
        MainClass.setOpensScope(new AggregateScope());
        MainClass.getOpensScope().setDeclOrDefn(finalValues);
        AggregateTypeDefinition returnValue = new AggregateTypeDefinition();
        returnValue.setAggregateType(MainClass);
        ArrayList<DefintionObject> returnValues = new ArrayList<>();
        returnValues.add(returnValue);
        return returnValues;
    }
}