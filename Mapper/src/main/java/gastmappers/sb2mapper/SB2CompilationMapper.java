package gastmappers.sb2mapper;

import ASTMCore.ASTMSemantics.AggregateScope;
import ASTMCore.ASTMSemantics.Scope;
import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.*;
import ASTMCore.ASTMSyntax.Expression.*;
import ASTMCore.ASTMSyntax.Statement.*;
import ASTMCore.ASTMSyntax.Types.*;
import ASTMCore.ASTMSyntax.Types.Boolean;
//import ASTMCore.metadata.SB2MapperSubTags.TagsSB2Mapper;
import gastmappers.sb2mapper.grammar.Scratch2Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.antlr.v4.runtime.tree.Tree;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

//import static ASTMCore.metadata.SB2MapperSubTags.TagsSB2Mapper.*;

/**
 * Interface that uses MapStruct to generate the mapping class automatically
 */
@Mapper
public interface SB2CompilationMapper {
    //Instance class SB2CompilationMapper
    SB2CompilationMapper INSTANCE = Mappers.getMapper(SB2CompilationMapper.class);

    /**
     * Initial SB2 mapping method
     *
     * @param compilationUnit The initial node from the sb2's AST.
     * @return A GAST compilation unit
     */
    @Mappings({
            @Mapping(target = "path", ignore = true),
            @Mapping(target = "fragments", ignore = true),
            @Mapping(target = "language", ignore = true),
            @Mapping(target = "gPackage", ignore = true),
            @Mapping(source = "SpriteObjectContext", target = "opensScope.declOrDefn"
                    , qualifiedByName = "spriteToDefinitionObject"),
            @Mapping(source = "childrenContexts", target = "opensScope.childScope"
                    , qualifiedByName = "listBlockContextToChildScope")
    })
    CompilationUnit compilationToCompilation(Scratch2Parser.RootContext compilationUnit,
                                             Scratch2Parser.SpriteObjectContext SpriteObjectContext,
                                             List<Scratch2Parser.ChildrenContext> childrenContexts);

    /**
     * This function maps a list of childrenContext in to arraylist of scope.
     *
     * @param list Children list from Scratch Stage.
     * @return Return ArrayList of Scope for the childScope.
     */
    @Named("listBlockContextToChildScope")
    default ArrayList<Scope> listBlockContextToChildScope(List<Scratch2Parser.ChildrenContext> list) {
        if (list == null) {
            return null;
        } else {
            // Create arrayList of Scopes
            ArrayList<Scope> scopes = new ArrayList();
            for (Scratch2Parser.ChildrenContext childrenContext : list) {
                //Get the spriteObject list for each children
                List<Scratch2Parser.SpriteObjectContext> listSprites = childrenContext.children_blocks().spriteObject();
                for (Scratch2Parser.SpriteObjectContext singleSprite : listSprites) {
                    //Create a Scope for each sprite
                    AggregateScope scope = new AggregateScope();
                    //Get the AggregateTypeDfinition Array from arrayListAggregateType()
                    ArrayList<AggregateTypeDefinition> arrayListAggregateType = arrayListAggregateType(singleSprite);
                    //Move the AggregateTypeDefinition ArrayList to the DefintionObject ArrayList, due the GAST model.
                    ArrayList<DefintionObject> listDefinition = new ArrayList<>();
                    //Convert the AggregateTypeDfinition Array in to DefintionObject Array.
                    listDefinition.addAll(arrayListAggregateType);

                    //Set the DefintionObject Array.
                    scope.setDeclOrDefn(listDefinition);
                    //Add ther scope in to Scopes Array
                    scopes.add(scope);
                }
            }
            //Return all sprites converts in to Scopes.
            return scopes;
        }
    }

    /**
     * This function transform the Sprite in to a AggregateTypeDefinition ArrayList.
     * It's necessary return an array for the GAST syntax, but each sprite is a single AggregateTypeDefinition.
     *
     * @param sprite The SpriteContext that you need transform.
     * @return An ArrayList of AggregateTypeDefinition from the Sprite.
     */
    default ArrayList<AggregateTypeDefinition> arrayListAggregateType(Scratch2Parser.SpriteObjectContext sprite) {
        //Create a ArrayList of AggregateTypeDefinition
        ArrayList<AggregateTypeDefinition> aggregateTypeDefinitionArrayList = new ArrayList<AggregateTypeDefinition>();
        // Create a single AggregateTypeDefinition
        AggregateTypeDefinition aggregateType = new AggregateTypeDefinition();

        //Create a new class. Each Sprite is converted in to a Class.
        ClassType newClass = new ClassType();
        Name name = new Name();
        //Extract the class name from the objName.
        name.setNameString(sprite.objName(0).literalString().getText());
        //Set the class name
        newClass.setNameString(name);
        //Extract all members of the class, extracting the variables and list
        ArrayList<MemberObject> listMembers = variablesAndListToMemberObject(sprite.variables(), sprite.list());
        //Set the ArrayList of Members. in to the class.
        newClass.setMembers(listMembers);
        AggregateScope openScope = createOpenScope(sprite.scripts());
        newClass.setOpensScope(openScope);

        //Add the class to the AggregateType
        aggregateType.setAggregateType(newClass);
        //Add the single AggregateType to the arrayList.
        aggregateTypeDefinitionArrayList.add(aggregateType);

        return aggregateTypeDefinitionArrayList;
    }

    /**
     * This function map the scripts of a sprite to a new Scope
     *
     * @param scripts A list of scriptsContext to be mapped.
     * @return A new AggregateScope with all information of the ScriptsContext.
     */
    default AggregateScope createOpenScope(List<Scratch2Parser.ScriptsContext> scripts) {
        //Create a new scope.
        AggregateScope openScope = new AggregateScope();
        ArrayList<DefintionObject> definitionObjects = new ArrayList<>();
        //Create a new DefinitionObject for each script block.
        for (Scratch2Parser.ScriptsContext script : scripts) {
            for (Scratch2Parser.Script_blockContext block : script.script_blocks().script_block()) {
                FunctionDefintion newFunction = createFunctionDefinition(block);
                definitionObjects.add(newFunction);
            }
        }
        //Set the DefinitionObject Array into the open scope.
        openScope.setDeclOrDefn(definitionObjects);
        return openScope;
    }

    /**
     * This functions takes the statementArray and transforms it into a new BlockStatement
     *
     * @param statementArray The statementArray to be mapped.
     * @return New BlockStatement with all Statements from the statementArray
     */
    default BlockStatement createBlockStatement(Scratch2Parser.StatementArrayContext statementArray) {
        //Define the body of the Function.
        BlockStatement newBlockStatement = new BlockStatement();
        //Create all sub statements for the block.
        ArrayList<Statement> newSubStatements = createSubStatementsArrayList(statementArray.statement());
        //Set the sub statements.
        newBlockStatement.setSubStatements(newSubStatements);

        return newBlockStatement;
    }

    /**
     * This function creates a new Function Definition using the ScriptBlock's information
     * Each block is like a Function Definition, they have sub statements into the block
     *
     * @param block This is the block to be mapped in to a FunctionDefinition
     * @return A FunctionDefinition with the block's information.
     */
    default FunctionDefintion createFunctionDefinition(Scratch2Parser.Script_blockContext block) {
        //Create new FunctionDefinition.
        FunctionDefintion newFunction = new FunctionDefintion();
        //TODO: Think about new name
        //The function doesn't have a name. For now, the function name is their position.
        newFunction.setIdentifierName(new Name(block.position().getText()));
        // Set the body to the Function
        newFunction.setBody(createBlockStatement(block.code().statementArray()));

        return newFunction;
    }

    /**
     * This function creates an array of statements with the input information
     *
     * @param statements All statements of a block to be mapped.
     * @return ArrayList of Statements. Each statement will be mapped.
     */
    default ArrayList<Statement> createSubStatementsArrayList(List<Scratch2Parser.StatementContext> statements) {
        ArrayList<Statement> newStatements = new ArrayList();
        //For each statement in the list.
        if(statements.size() >0){
            if(statements.get(0).functionBlock() != null){
                newStatements.add(createStatementFunctionBlock(statements.get(0).functionBlock(), statements));
            }else{
                for (Scratch2Parser.StatementContext statement : statements) {
                    newStatements.add(createSubStatement(statement));
                }
            }
        }
        return newStatements;
    }

    /**
     * This function create a single the statement
     *
     * @param statement The Scratch's statement to be transforms into Statement
     * @return A new Statement for the list.
     */
    default Statement createSubStatement(Scratch2Parser.StatementContext statement) {

        if (statement.controlBlock() != null) {
            return createStatementFromControlBlock(statement.controlBlock());
        }
        //TODO: This have to change for new statements like BlockStatement
        //Create a new ExpressionStatement
        ExpressionStatement newExpressionStatement = new ExpressionStatement();
        //Set a new Expression
        newExpressionStatement.setExpression(createExpression(statement));

        return newExpressionStatement;
    }

    /**
     * This function create a new Expression classifying the statement by block type
     *
     * @param statement The statement to be converted into a Expression
     * @return
     */
    default Expression createExpression(Scratch2Parser.StatementContext statement) {
        //Classifying the block type
        if (statement.hatBlock() != null) {
            return createExpressionFromHatBlock(statement.hatBlock());
        }
        if (statement.capBlock() != null) {
            return createExpressionFromCapBlock(statement.capBlock());
        }
        if (statement.booleanBlock() != null) {
            return createExpressionFromBooleanBlock(statement.booleanBlock());
        }
        if (statement.reporterBlock() != null) {
            return createExpressionFromReporterBlock(statement.reporterBlock());
        }
        if (statement.stackBlock() != null) {
            return createExpressionFromStackBlock(statement.stackBlock());
        }
        if (statement.functionBlock() != null) {
            return createExpressionFromFunctionBlock(statement.functionBlock());
        }
        return null;
    }

    /**
     * This function create a new Statement according its type.
     * @param functionBlock The functionBlock has the type of Statement to be mapped
     * @return A new statement is created with the control block information.
     */
    default Statement createStatementFunctionBlock(Scratch2Parser.FunctionBlockContext functionBlock, List<Scratch2Parser.StatementContext> statements) {
        //Get the control block type.
        String functionBlockKind = functionBlock.getChild(0).getChild(1).getText();
        //System.out.println(controlBlockType);
        switch (functionBlockKind) {
            case "\"procDef\"":
                Scratch2Parser.ProcDefContext procDef = functionBlock.procDef();
                //Function declared by student
                DeclarationOrDefinitionStatement newFunctionByStudent = new DeclarationOrDefinitionStatement();
                FunctionDefintion newFunc = new FunctionDefintion();
                //All statement are in the same array, so we need to take all except the first.
                //The first statement is the procDef.
                statements = statements.subList(1,statements.size());
                //Define the body of the Function.
                BlockStatement newBlockStatement = new BlockStatement();
                //Create all sub statements for the block.
                ArrayList<Statement> newSubStatements =
                        createSubStatementsArrayList(statements);
                //Set the sub statements.
                newBlockStatement.setSubStatements(newSubStatements);
                //Add the body
                newFunc.setBody(newBlockStatement);
                //Set the formal parameters
                newFunc.setFormalParameters(formalParameterDefinitionList(procDef.name_function(), procDef.param_type_definition()));
                //Set identifier name
                newFunc.setIdentifierName(literalStringToName(procDef.name_function().literalString()));
                //Add subtag
                //newFunc.addSubTag(TagsSB2Mapper.PROC_DEF);
                //newFunc
                newFunctionByStudent.setDeclOrDefn(newFunc);
                return newFunctionByStudent;
        }
        return null;
    }


    /**
     * This function create a new Statement according its type.
     *
     * @param controlBlock The controlBlock has the type of Statement to be mapped
     * @return A new statement is created with the control block information.
     */
    default Statement createStatementFromControlBlock(Scratch2Parser.ControlBlockContext controlBlock) {
        //Get the control block type.
        String controlBlockType = controlBlock.getChild(0).getChild(1).getText();
        //System.out.println(controlBlockType);
        switch (controlBlockType) {
            case "\"doForever\"":
                //DoForever will be mapped like WhileStatement with true condition.
                Scratch2Parser.DoForeverContext doForever = controlBlock.doForever();
                //Create new WhileStatement
                WhileStatement newDoWhile = new WhileStatement();
                //Create new BooleanLiteral
                BooleanLiteral booleanLiteral = new BooleanLiteral();
                //Set always the value condition true.
                booleanLiteral.setValue("true");
                //Set the condition to the WhileStatement.
                newDoWhile.setCondition(booleanLiteral);

                //Check if the body is null
                if (doForever.statementArray() != null) {
                    //Set the body to the WhileStatement.
                    newDoWhile.setBody(createBlockStatement(doForever.statementArray()));
                }
                return newDoWhile;
            case "\"doUntil\"":
                //DoUntil will be mapped like WhileStatement to, but with different condition.
                Scratch2Parser.DoUntilContext doUntil = controlBlock.doUntil();
                //Create new WhileStatement
                WhileStatement doUntilStatement = new WhileStatement();
                //Check if the condition is a expression.
                if (doUntil.statement() != null) {
                    //Set the condition
                    doUntilStatement.setCondition(createExpression(doUntil.statement()));
                } else {
                    //Check if the condition is a literalBoolean
                    if (doUntil.literalBoolean() != null) {
                        //Set the condition
                        doUntilStatement.setCondition(createBooleanLiteral(doUntil.literalBoolean()));
                    }
                }

                //Check if the Body is null
                if (doUntil.statementArray() != null) {
                    //Set the condition.
                    doUntilStatement.setBody(createBlockStatement(doUntil.statementArray()));
                }
                return doUntilStatement;
            case "\"doIf\"":
                //doIf will be mapped like ifStatement
                Scratch2Parser.DoIfContext doIf = controlBlock.doIf();
                //Create new IfStatement
                IfStatement ifStatement = new IfStatement();
                // Check if the condition is an expression.
                if (doIf.statement() != null) {
                    //Set the condition
                    ifStatement.setCondition(createExpression(doIf.statement()));
                } else {
                    //Check if the condition is a literalBoolean
                    if (doIf.literalBoolean() != null) {
                        //Set the condition
                        ifStatement.setCondition(createBooleanLiteral(doIf.literalBoolean()));
                    }
                }

                //Check if the then body is null
                if (doIf.thenBody() != null) {
                    //Set the then body
                    ifStatement.setThenBody(createBlockStatement(doIf.thenBody().statementArray()));
                }
                return ifStatement;
            case "\"doIfElse\"":
                //DoIfElse will be mapped as IfStatement, but with the else body
                Scratch2Parser.DoIfElseContext doIfElse = controlBlock.doIfElse();
                //Create new IfStatement
                IfStatement ifElseStatement = new IfStatement();
                // Check if the condition is an expression.
                if (doIfElse.statement() != null) {
                    //Set the condition
                    ifElseStatement.setCondition(createExpression(doIfElse.statement()));
                } else {
                    //Check if the condition is a literalBoolean
                    if (doIfElse.literalBoolean() != null) {
                        //Set the condition
                        ifElseStatement.setCondition(createBooleanLiteral(doIfElse.literalBoolean()));
                    }
                }
                // Check if the then body is null
                if (doIfElse.thenBody() != null) {
                    //Set the then body
                    ifElseStatement.setThenBody(createBlockStatement(doIfElse.thenBody().statementArray()));
                }
                //Check if the else body is null
                if (doIfElse.elseBody() != null) {
                    //Set the else body
                    ifElseStatement.setElseBody(createBlockStatement(doIfElse.elseBody().statementArray()));
                }
                return ifElseStatement;
            case "\"doRepeat\"":
                //DoRepeat will be mapped like ForCheckAfterStatement
                Scratch2Parser.DoRepeatContext doRepeat = controlBlock.doRepeat();
                //Create new ForCheckAfterStatement
                ForCheckAfterStatement forStatement = new ForCheckAfterStatement();
                //Check if the condition is an expression
                if (doRepeat.statement() != null) {
                    //Set the condition
                    forStatement.setCondition(createExpression(doRepeat.statement()));
                } else {
                    //Check if the condition is a Number
                    if (doRepeat.NUMBER() != null) {
                        //Set the condition
                        forStatement.setCondition(createRealLiteral(doRepeat.NUMBER().getText()));
                    }
                }

                //Check if the body is null
                if (doRepeat.statementArray() != null) {
                    //Set the body.
                    forStatement.setBody(createBlockStatement(controlBlock.doRepeat().statementArray()));
                }
                //TODO: Review the InitBody
                return forStatement;
        }
        return null;
    }

    default Expression createExpressionFromFunctionBlock(Scratch2Parser.FunctionBlockContext functionBlock) {
        //Create a new ExpressionStatement
        FunctionCallExpression newFunctionCallExpression = new FunctionCallExpression();
        //Get the type of Stack Block
        String functionBlockType = functionBlock.getChild(0).getChild(1).getText();
        //Create an array for the FunctionCallExpressions' parameters.
        ArrayList<ActualParameter> actualParameters = new ArrayList<>();
        ActualParameterExpression actualParameter = null;

        switch (functionBlockType) {
            case "\"call\"":

                Scratch2Parser.CallContext call = functionBlock.call();
                Scratch2Parser.Params_valueContext params = call.params_value();
                //Get the parameters ordered
                for(int i = 0; i<params.getChildCount(); i++){
                    actualParameter = new ActualParameterExpression();
                    //Compare the class
                    if (params.getChild(i).getClass() == Scratch2Parser.LiteralStringContext.class){
                        Scratch2Parser.LiteralStringContext stringL = (Scratch2Parser.LiteralStringContext)params.getChild(i);
                        actualParameter.setValue(createStringLiteral(stringL));
                        //Add the parameter to the array.
                        actualParameters.add(actualParameter);
                        continue;
                    }
                    //Compare the class
                    if (params.getChild(i).getClass() == Scratch2Parser.LiteralBooleanContext.class){
                        Scratch2Parser.LiteralBooleanContext booleanL = (Scratch2Parser.LiteralBooleanContext)params.getChild(i);
                        actualParameter.setValue(createBooleanLiteral(booleanL));
                        //Add the parameter to the array.
                        actualParameters.add(actualParameter);
                        continue;
                    }
                    //Compare the class
                    if (params.getChild(i).getClass() == Scratch2Parser.StatementContext.class){
                        //Check if is statement
                        Scratch2Parser.StatementContext statement = (Scratch2Parser.StatementContext)params.getChild(i);
                        actualParameter.setValue(createExpression(statement));
                        //Add the parameter to the array.
                        actualParameters.add(actualParameter);
                        continue;
                    }
                    if (params.getChild(i).getClass() == TerminalNodeImpl.class){
                        TerminalNodeImpl terminal = (TerminalNodeImpl)params.getChild(i);
                        //Check if the terminal is a number
                        if(terminal.getSymbol().getType() == Scratch2Parser.NUMBER) {
                            actualParameter.setValue(createRealLiteral(terminal.getText()));
                            //Add the parameter to the array.
                            actualParameters.add(actualParameter);
                            continue;
                        }
                    }
                }

                //Add the parameters to the FunctionCallExpression
                newFunctionCallExpression.setActualParams(actualParameters);
                //Set the function's name
                newFunctionCallExpression.setFunctionName(new Name(functionBlock.call().name_function().getText().replace("\"", "")));

                //newFunctionCallExpression.addSubTag(CALL);
                return newFunctionCallExpression;
        }
        return null;
    }

    /**
     * This function create an Expression with the stackBlock information
     * The StackBlock is the largest kind of block that Scratch has.
     *
     * All functions with single or two operands have the same way of mapping, read the first example for
     * so as not repeat the same documentation for all blocks.
     *
     * The only change is for the functions of data, that have an IdentifierName as an input parameter.
     *
     * @param stackBlock The stackBlock to be mapped.
     * @return An Expression with the information of the stack block.
     */
    default Expression createExpressionFromStackBlock(Scratch2Parser.StackBlockContext stackBlock) {
        //Create a new ExpressionStatement
        FunctionCallExpression newFunctionCallExpression = new FunctionCallExpression();
        //Get the type of Stack Block
        String stackBlockType = stackBlock.getChild(0).getChild(1).getText();
        //Create an array for the FunctionCallExpressions' parameters.
        ArrayList<ActualParameter> actualParameters = new ArrayList<>();
        ActualParameterExpression actualParameter;
        switch (stackBlockType) {
            case "\"forward:\"":
                //Instance the ActualParameterExpression
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ForwardContext forward = stackBlock.forward();
                //Get the single operad.
                if (forward.singleOperand() != null) {
                    //Add the ExpressionOperand to the actualParameter.
                    actualParameter.setValue(createExpressionOperand(forward.singleOperand()));
                }
                //Add the parameter to the array.
                actualParameters.add(actualParameter);
                //Add the parameters to the FunctionCallExpression
                newFunctionCallExpression.setActualParams(actualParameters);
                //Set the function's name
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"turnRight:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.TurnRightContext turnRight = stackBlock.turnRight();
                if (turnRight.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(turnRight.singleOperand()));
                }
                actualParameters.add(actualParameter);
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"turnLeft:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.TurnLeftContext turnLeft = stackBlock.turnLeft();
                if (turnLeft.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(turnLeft.singleOperand()));
                }
                actualParameters.add(actualParameter);
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"heading:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.HeadingContext heading = stackBlock.heading();
                if (heading.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(heading.singleOperand()));
                }
                actualParameters.add(actualParameter);
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"pointTowards:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.PointTowardsContext pointTowards = stackBlock.pointTowards();
                if (pointTowards.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(pointTowards.singleOperand()));
                }
                actualParameters.add(actualParameter);
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"gotoX:y:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.GoToXYContext goToXY = stackBlock.goToXY();
                //Get the leftOperand
                if (goToXY.leftOperand() != null) {
                    //Set the leftOperand to the actualParameter
                    actualParameter.setValue(createExpressionOperand(goToXY.leftOperand()));
                    actualParameters.add(actualParameter);
                }
                //Get the rightOperand
                if (goToXY.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(goToXY.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                //Set the actualParameters to the FunctionCallExpression.
                newFunctionCallExpression.setActualParams(actualParameters);
                //Set the Function's name.
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"gotoSpriteOrMouse:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.GoToSpriteOrMouseContext spriteOrMouseContext = stackBlock.goToSpriteOrMouse();
                if (spriteOrMouseContext.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(spriteOrMouseContext.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"glideSecs:toX:y:elapsed:from:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.GlideSecToXYElapsedFromContext glideSec = stackBlock.glideSecToXYElapsedFrom();
                if (glideSec.leftOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(glideSec.leftOperand()));
                    actualParameters.add(actualParameter);
                }
                if (glideSec.centerOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(glideSec.centerOperand()));
                    actualParameters.add(secondParameter);
                }
                if (glideSec.rightOperand() != null) {
                    ActualParameterExpression thirdParameter = new ActualParameterExpression();
                    thirdParameter.setValue(createExpressionOperand(glideSec.rightOperand()));
                    actualParameters.add(thirdParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"changeXposBy:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangeXPosByContext changeXPosBy = stackBlock.changeXPosBy();
                if (changeXPosBy.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(changeXPosBy.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"xpos:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.XPosContext xPos = stackBlock.xPos();
                if (xPos.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(xPos.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"changeYposBy:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangeYposByContext changeYPosBy = stackBlock.changeYposBy();
                if (changeYPosBy.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(changeYPosBy.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"ypos:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.YPosContext yPos = stackBlock.yPos();
                if (yPos.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(yPos.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"bounceOffEdge\"":
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
            case "\"setRotationStyle\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetRotationStyleContext setRotationStyle = stackBlock.setRotationStyle();
                if (setRotationStyle.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setRotationStyle.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));

                return newFunctionCallExpression;
                //STACK BLOCK LOOK
            case "\"say:duration:elapsed:from:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SayDurationElapsedFromContext sayDurationElapsedFrom = stackBlock.sayDurationElapsedFrom();
                if (sayDurationElapsedFrom.leftOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(sayDurationElapsedFrom.leftOperand()));
                    actualParameters.add(actualParameter);
                }
                if (sayDurationElapsedFrom.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(sayDurationElapsedFrom.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"say:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SayTokenContext sayToken = stackBlock.sayToken();
                if (sayToken.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(sayToken.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"think:duration:elapsed:from:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ThinkDurationElapsedFromContext thinkDurationElapsedFrom = stackBlock.thinkDurationElapsedFrom();
                if (thinkDurationElapsedFrom.leftOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(thinkDurationElapsedFrom.leftOperand()));
                    actualParameters.add(actualParameter);
                }
                if (thinkDurationElapsedFrom.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(thinkDurationElapsedFrom.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"think:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ThinkContext think = stackBlock.think();
                if (think.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(think.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"lookLike:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.LookLikeContext lookLike = stackBlock.lookLike();
                if (lookLike.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(lookLike.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"startScene\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.StartSceneContext startScene = stackBlock.startScene();
                if (startScene.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(startScene.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"changeGraphicEffect:by:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangeGraphicEffectByContext changeGraphic = stackBlock.changeGraphicEffectBy();
                if (changeGraphic.leftOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(changeGraphic.leftOperand()));
                    actualParameters.add(actualParameter);
                }
                if (changeGraphic.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(changeGraphic.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setGraphicEffect:to:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetGraphicEffectContext setGraphicEffect = stackBlock.setGraphicEffect();
                if (setGraphicEffect.leftOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setGraphicEffect.leftOperand()));
                    actualParameters.add(actualParameter);
                }
                if (setGraphicEffect.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(setGraphicEffect.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"changeSizeBy:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangeSizeByContext changeSizeBy = stackBlock.changeSizeBy();
                if (changeSizeBy.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(changeSizeBy.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setSizeTo:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetSizeToContext setSizeTo = stackBlock.setSizeTo();
                if (setSizeTo.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setSizeTo.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"goBackByLayers:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.GoBackByLayersContext goBackByLayers = stackBlock.goBackByLayers();
                if (goBackByLayers.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(goBackByLayers.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
                //SOUNDS
            case "\"playSound:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.PlaySoundContext playSound = stackBlock.playSound();
                if (playSound.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(playSound.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"doPlaySoundAndWait\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.DoPlaySoundAndWaitContext doPlay = stackBlock.doPlaySoundAndWait();
                if (doPlay.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(doPlay.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"playDrum\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.PlayDrumContext playDrum = stackBlock.playDrum();
                if (playDrum.leftOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(playDrum.leftOperand()));
                    actualParameters.add(actualParameter);
                }
                if (playDrum.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(playDrum.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"rest:elapsed:from:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.RestElapsedFromContext restElapsedFrom = stackBlock.restElapsedFrom();
                if (restElapsedFrom.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(restElapsedFrom.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"noteOn:duration:elapsed:from:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.NoteOnDurationElapsedFromContext note = stackBlock.noteOnDurationElapsedFrom();
                if (note.leftOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(note.leftOperand()));
                    actualParameters.add(actualParameter);
                }
                if (note.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(note.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"instrument:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.InstrumentTokenContext instrumentToken = stackBlock.instrumentToken();
                if (instrumentToken.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(instrumentToken.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"changeVolumeBy:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangeVolumeByContext volumeBy = stackBlock.changeVolumeBy();
                if (volumeBy.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(volumeBy.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setVolumeTo:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetVolumeToContext setVolumeTo = stackBlock.setVolumeTo();
                if (setVolumeTo.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setVolumeTo.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"changeTempoBy:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangeTempoByContext changeTempoBy = stackBlock.changeTempoBy();
                if (changeTempoBy.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(changeTempoBy.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setTempoTo:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetTempoToContext setTempoBy = stackBlock.setTempoTo();
                if (setTempoBy.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setTempoBy.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
                //PEN
            case "\"penColor:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.PenColorContext penColor = stackBlock.penColor();
                if (penColor.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(penColor.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"changePenHueBy:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangePenHueByContext penHueBy = stackBlock.changePenHueBy();
                if (penHueBy.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(penHueBy.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setPenHueTo:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetPenHueToContext setPenHueTo = stackBlock.setPenHueTo();
                if (setPenHueTo.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setPenHueTo.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"changePenShadeBy:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangePenShadeByContext changePenShadeBy = stackBlock.changePenShadeBy();
                if (changePenShadeBy.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(changePenShadeBy.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setPenShadeTo:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetPenShadeToContext setPenShadeTo = stackBlock.setPenShadeTo();
                if (setPenShadeTo.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setPenShadeTo.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"changePenSizeBy:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangePenSizeByContext changePenSizeByContext = stackBlock.changePenSizeBy();
                if (changePenSizeByContext.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(changePenSizeByContext.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"penSize:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.PenSizeContext penSize = stackBlock.penSize();
                if (penSize.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(penSize.singleOperand()));
                    actualParameters.add(actualParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setVar:to:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetVariableToContext setVariableTo = stackBlock.setVariableTo();
                if (setVariableTo.leftOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(setVariableTo.leftOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                if (setVariableTo.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(setVariableTo.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"changeVar:by:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ChangeVariableByContext changeVariableBy = stackBlock.changeVariableBy();
                if (changeVariableBy.leftOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(changeVariableBy.leftOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                if (changeVariableBy.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(changeVariableBy.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"showVariable:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ShowVariableContext showVariable = stackBlock.showVariable();
                if (showVariable.singleOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(showVariable.singleOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"hideVariable:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.HideVariableContext hideVariable = stackBlock.hideVariable();
                if (hideVariable.singleOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(hideVariable.singleOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"append:toList:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.AppendToListContext appendToList = stackBlock.appendToList();
                if (appendToList.leftOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(appendToList.leftOperand()));
                    actualParameters.add(secondParameter);
                }
                if (appendToList.rightOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(appendToList.rightOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"deleteLine:ofList:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.DeleteLineOfListContext deleteLineOfList = stackBlock.deleteLineOfList();
                if (deleteLineOfList.leftOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(deleteLineOfList.leftOperand()));
                    actualParameters.add(secondParameter);
                }
                if (deleteLineOfList.rightOperand() != null) {
                    // This function have an IdentifierReference as an input parameter.
                    // This is a variable define in Scratch.
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(deleteLineOfList.rightOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"insert:at:ofList:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.InsertAtOfListContext insertAtOfList = stackBlock.insertAtOfList();
                if (insertAtOfList.leftOperand() != null) {
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    firstParameter.setValue(createExpressionOperand(insertAtOfList.leftOperand()));
                    actualParameters.add(firstParameter);
                }
                if (insertAtOfList.centerOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(insertAtOfList.centerOperand()));
                    actualParameters.add(secondParameter);
                }
                if (insertAtOfList.rightOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(insertAtOfList.rightOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setLine:ofList:to:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetLineOfListToContext setLineOfListTo = stackBlock.setLineOfListTo();
                if (setLineOfListTo.leftOperand() != null) {
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    firstParameter.setValue(createExpressionOperand(setLineOfListTo.leftOperand()));
                    actualParameters.add(firstParameter);
                }
                if (setLineOfListTo.centerOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(setLineOfListTo.centerOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                if (setLineOfListTo.rightOperand() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpressionOperand(setLineOfListTo.rightOperand()));
                    actualParameters.add(secondParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"showList:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.ShowListContext showList = stackBlock.showList();
                if (showList.singleOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(showList.singleOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"hideList:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.HideListContext hideList = stackBlock.hideList();
                if (hideList.singleOperand() != null) {
                    IdentifierReference identifierReference = new IdentifierReference();
                    identifierReference.setIdentifierName(new Name(hideList.singleOperand().literalString().getText()));
                    actualParameter.setValue(identifierReference);
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
                //Events
            case "\"broadcast:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.BroadcastContext broadcast = stackBlock.broadcast();
                if (broadcast.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(broadcast.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"doBroadcastAndWait\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.DoBroadcastAndWaitContext doBroadcast = stackBlock.doBroadcastAndWait();
                if (doBroadcast.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(doBroadcast.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
                //Control
            case "\"wait:elapsed:from:\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.WaitElapsedFromContext waitElapsed = stackBlock.waitElapsedFrom();
                if (waitElapsed.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(waitElapsed.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"doWaitUntil\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.DoWaitUntilContext doWaitUntil = stackBlock.doWaitUntil();
                if (doWaitUntil.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(doWaitUntil.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"createCloneOf\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.CreateCloneOfContext createCloneOf = stackBlock.createCloneOf();
                if (createCloneOf.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(createCloneOf.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
                //Sensing
            case "\"doAsk\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.DoAskContext doAsk = stackBlock.doAsk();
                if (doAsk.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(doAsk.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setVideoState\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetVideoStateContext setVideoState = stackBlock.setVideoState();
                if (setVideoState.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setVideoState.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
            case "\"setVideoTransparency\"":
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.SetVideoTransparencyContext setVideoTransparency = stackBlock.setVideoTransparency();
                if (setVideoTransparency.singleOperand() != null) {
                    actualParameter.setValue(createExpressionOperand(setVideoTransparency.singleOperand()));
                    actualParameters.add(actualParameter);
                }
                newFunctionCallExpression.setActualParams(actualParameters);
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
                //All these functions have not parameters, so its mapping is in the same way.
            case "\"show\"":
            case "\"hide\"":
            case "\"nextCostume\"":
            case "\"filterReset\"":
            case "\"comeToFront\"":
            case "\"stopAllSounds\"":
            case "\"clearPenTrails\"":
            case "\"stampCostume\"":
            case "\"putPenDown\"":
            case "\"putPenUp\"":
            case "\"timerReset\"":
                //Set the function name.
                newFunctionCallExpression.setFunctionName(new Name(stackBlockType.replace("\"", "")));
                return newFunctionCallExpression;
        }
        return null;
    }

    /**
     * This function transforms the reportBlock into an Expression
     *
     * @param reporterBlock The Reporter Block to be mapped.
     * @return A new Expression with the Reporter Block information.
     */
    default Expression createExpressionFromReporterBlock(Scratch2Parser.ReporterBlockContext reporterBlock) {
        //Create a new BinaryExpression.
        String reporterBlockKind = reporterBlock.getChild(0).getChild(1).getText();
        //Binary expression.
        BinaryExpression newBinaryExpression = new BinaryExpression();
        //This is for the FunctionCallExpressions.
        ArrayList<ActualParameter> parameters = null;
        FunctionCallExpression newFunctionCallExpression = null;
        switch (reporterBlockKind) {
            case "\"+\"":
                if (reporterBlock.plus().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.plus().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                if (reporterBlock.plus().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.plus().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(reporterBlockKind));
                return newBinaryExpression;
            case "\"*\"":
                if (reporterBlock.multiply().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.multiply().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                if (reporterBlock.multiply().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.multiply().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(reporterBlockKind));
                return newBinaryExpression;

            case "\"\\/\"":
                if (reporterBlock.divider().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.divider().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                if (reporterBlock.divider().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.divider().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(reporterBlockKind));
                return newBinaryExpression;

            case "\"-\"":
                if (reporterBlock.minus().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.minus().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                if (reporterBlock.minus().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.minus().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(reporterBlockKind));
                return newBinaryExpression;
            case "\"%\"":
                if (reporterBlock.mod().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.mod().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                if (reporterBlock.mod().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.mod().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(reporterBlockKind));
                return newBinaryExpression;
            case "\"randomFrom:to:\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.randomFromTo().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.randomFromTo().leftOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(leftOperand));
                    parameters.add(parameter);
                }
                if (reporterBlock.randomFromTo().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.randomFromTo().rightOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(rightOperand));
                    parameters.add(parameter);
                }
                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"letter:of:\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.letterOf().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.letterOf().leftOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(leftOperand));
                    parameters.add(parameter);
                }
                if (reporterBlock.letterOf().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.letterOf().rightOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(rightOperand));
                    parameters.add(parameter);
                }
                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"computeFunction:of:\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.computeFunctionOf().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.computeFunctionOf().leftOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(leftOperand));
                    parameters.add(parameter);
                }
                if (reporterBlock.computeFunctionOf().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.computeFunctionOf().rightOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(rightOperand));
                    parameters.add(parameter);
                }
                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"senseVideoMotion\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.senseVideoMotion().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.senseVideoMotion().leftOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(leftOperand));
                    parameters.add(parameter);
                }
                if (reporterBlock.senseVideoMotion().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.senseVideoMotion().rightOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(rightOperand));
                    parameters.add(parameter);
                }
                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"getAttribute:of:\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.getAttributeOf().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.getAttributeOf().leftOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(leftOperand));
                    parameters.add(parameter);
                }
                if (reporterBlock.getAttributeOf().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.getAttributeOf().rightOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(rightOperand));
                    parameters.add(parameter);
                }
                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"getLine:ofList:\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.getLineOfList().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = reporterBlock.getLineOfList().leftOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(leftOperand));
                    parameters.add(parameter);
                }
                if (reporterBlock.getLineOfList().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = reporterBlock.getLineOfList().rightOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(rightOperand));
                    parameters.add(parameter);
                }
                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"readVariable\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.readVariable().singleOperand() != null) {
                    Scratch2Parser.SingleOperandContext singleOperand = reporterBlock.readVariable().singleOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(singleOperand));
                    parameters.add(parameter);
                }

                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"stringLength:\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.stringLength().singleOperand() != null) {
                    Scratch2Parser.SingleOperandContext singleOperand = reporterBlock.stringLength().singleOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(singleOperand));
                    parameters.add(parameter);
                }

                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"rounded\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.rounded().singleOperand() != null) {
                    Scratch2Parser.SingleOperandContext singleOperand = reporterBlock.rounded().singleOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(singleOperand));
                    parameters.add(parameter);
                }

                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"lineCountOfList:\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.lineCountOfList().singleOperand() != null) {
                    Scratch2Parser.SingleOperandContext singleOperand = reporterBlock.lineCountOfList().singleOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(singleOperand));
                    parameters.add(parameter);
                }

                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"contentsOfList:\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.contentsOfList().singleOperand() != null) {
                    Scratch2Parser.SingleOperandContext singleOperand = reporterBlock.contentsOfList().singleOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(singleOperand));
                    parameters.add(parameter);
                }

                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            case "\"timeAndDate\"":
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (reporterBlock.timeAndDate().singleOperand() != null) {
                    Scratch2Parser.SingleOperandContext singleOperand = reporterBlock.timeAndDate().singleOperand();
                    ActualParameterExpression parameter = new ActualParameterExpression();
                    //Set the function's parameter.
                    parameter.setValue(createExpressionOperand(singleOperand));
                    parameters.add(parameter);
                }

                newFunctionCallExpression.setActualParams(parameters);
                newFunctionCallExpression.setFunctionName(new Name(reporterBlockKind));
                return newFunctionCallExpression;
            //All of these report blocks have the same mapping to the GAST. They are Identifiers.
            case "\"xpos\"":
            case "\"ypos\"":
            case "\"heading\"":
            case "\"costumeIndex\"":
            case "\"sceneName\"":
            case "\"scale\"":
            case "\"volume\"":
            case "\"tempo\"":
            case "\"answer\"":
            case "\"mouseX\"":
            case "\"mouseY\"":
            case "\"soundLevel\"":
            case "\"timer\"":
            case "\"timestamp\"":
            case "\"getUserName\"":
                IdentifierReference identifierReference = new IdentifierReference();
                identifierReference.setIdentifierName(new Name(reporterBlockKind));
                return identifierReference;
        }
        return newBinaryExpression;
    }

    /**
     * This function takes the a BooleanBlockContext and transforms it into a Expression.
     * There are three expression types: BinaryExpression, PrefixUnaryExpression and newFunctionCallExpression
     *
     * @param booleanBlock
     * @return
     */
    default Expression createExpressionFromBooleanBlock(Scratch2Parser.BooleanBlockContext booleanBlock) {
        //Take the boolean block type
        String booleanBlockKind = booleanBlock.getChild(0).getChild(1).getText();
        //Create a new BinaryExpression
        BinaryExpression newBinaryExpression = new BinaryExpression();
        //Define the FunctionCallExpression variable.
        FunctionCallExpression newFunctionCallExpression = null;

        //Define the FunctionCallExpression's parameters array.
        ArrayList<ActualParameter> parameters = null;
        switch (booleanBlockKind) {
            case "\"<\"":
                //Get the left operand.
                if (booleanBlock.lower().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = booleanBlock.lower().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                //Get the right operand.
                if (booleanBlock.lower().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = booleanBlock.lower().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(booleanBlockKind));
                return newBinaryExpression;
            case "\"=\"":

                //Get the left operand.
                if (booleanBlock.equal().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = booleanBlock.equal().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                //Get the right operand.
                if (booleanBlock.equal().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = booleanBlock.equal().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(booleanBlockKind));
                return newBinaryExpression;
            case "\">\"":

                //Get the left operand.
                if (booleanBlock.higher().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = booleanBlock.higher().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                //Get the right operand.
                if (booleanBlock.higher().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = booleanBlock.higher().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(booleanBlockKind));
                return newBinaryExpression;
            case "\"&\"":
                //Get the left operand.
                if (booleanBlock.and().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = booleanBlock.and().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                //Get the right operand.
                if (booleanBlock.and().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = booleanBlock.and().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(booleanBlockKind));
                return newBinaryExpression;
            case "\"|\"":
                //Get the left operand.
                if (booleanBlock.or().leftOperand() != null) {
                    Scratch2Parser.LeftOperandContext leftOperand = booleanBlock.or().leftOperand();
                    //Set the leftOperand.
                    newBinaryExpression.setLeftOperand(createExpressionOperand(leftOperand));
                }
                //Get the right operand.
                if (booleanBlock.or().rightOperand() != null) {
                    Scratch2Parser.RightOperandContext rightOperand = booleanBlock.or().rightOperand();
                    //Set the rightOperand.
                    newBinaryExpression.setRightOperand(createExpressionOperand(rightOperand));
                }
                //Set the operator.
                newBinaryExpression.setOperator(createBinaryOperator(booleanBlockKind));
                return newBinaryExpression;
            case "\"not\"":
                //Get the single operand.
                PrefixUnaryExpression prefixUnaryExpression = new PrefixUnaryExpression();
                if (booleanBlock.not().singleOperand() != null) {
                    Scratch2Parser.SingleOperandContext singleOperand = booleanBlock.not().singleOperand();
                    //Get the single Operand
                    prefixUnaryExpression.setOperand(createExpressionOperand(singleOperand));
                }
                //Set the operator.
                prefixUnaryExpression.setOperator(createUnaryOperator(booleanBlockKind));
                return prefixUnaryExpression;
            case "\"touching:\"":
                //Initialize variables.
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                //Get the single operand.
                if (booleanBlock.touching().singleOperand() != null) {
                    //Get the single Operand
                    Scratch2Parser.SingleOperandContext singleOperand = booleanBlock.touching().singleOperand();
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    //Get the ExpressionOperand
                    firstParameter.setValue(createExpressionOperand(singleOperand));
                    //Add the ActualParameterExpression to the parameters.
                    parameters.add(firstParameter);

                }
                //Take the name of the event and set as FunctionName.
                newFunctionCallExpression.setFunctionName(new Name(booleanBlockKind.replace("\"", "")));
                //Set the parameters into the FunctionCallExpression.
                newFunctionCallExpression.setActualParams(parameters);
                return newFunctionCallExpression;
            case "\"distanceTo:\"":
                //Initialize variables.
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (booleanBlock.distanceTo().singleOperand() != null) {
                    //Get the single Operand
                    Scratch2Parser.SingleOperandContext singleOperand = booleanBlock.distanceTo().singleOperand();
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    //Get the ExpressionOperand
                    firstParameter.setValue(createExpressionOperand(singleOperand));
                    //Add the ActualParameterExpression to the parameters.
                    parameters.add(firstParameter);

                }
                //Take the name of the event and set as FunctionName.
                newFunctionCallExpression.setFunctionName(new Name(booleanBlockKind.replace("\"", "")));
                //Set the parameters into the FunctionCallExpression.
                newFunctionCallExpression.setActualParams(parameters);
                return newFunctionCallExpression;
            case "\"keyPressed:\"":
                //Initialize variables.
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (booleanBlock.whenKeyPressedSensing().singleOperand() != null) {
                    //Get the single Operand
                    Scratch2Parser.SingleOperandContext singleOperand = booleanBlock.whenKeyPressedSensing().singleOperand();
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    //Set the ExpressionOperand
                    firstParameter.setValue(createExpressionOperand(singleOperand));
                    //Add the ActualParameterExpression to the parameters.
                    parameters.add(firstParameter);

                }
                //Take the name of the event and set as FunctionName.
                newFunctionCallExpression.setFunctionName(new Name(booleanBlockKind.replace("\"", "")));
                //Set the parameters into the FunctionCallExpression.
                newFunctionCallExpression.setActualParams(parameters);
                return newFunctionCallExpression;
            case "\"mousePressed\"":
                //Initialize variables.
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                //Take the name of the event and set as FunctionName.
                newFunctionCallExpression.setFunctionName(new Name(booleanBlockKind.replace("\"", "")));
                //Set the parameters into the FunctionCallExpression.
                newFunctionCallExpression.setActualParams(parameters);
                return newFunctionCallExpression;

            case "\"list:contains:\"":
                //Initialize variables.
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (booleanBlock.listContains().leftOperand() != null) {

                    Scratch2Parser.LeftOperandContext leftOperand = booleanBlock.listContains().leftOperand();
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    //Set the ExpressionOperand
                    firstParameter.setValue(createIdentifierReferenceFromLiteralString(leftOperand.literalString()));
                    //Add the ActualParameterExpression to the parameters.
                    parameters.add(firstParameter);

                }
                if (booleanBlock.listContains().rightOperand() != null) {

                    Scratch2Parser.RightOperandContext rightOperand = booleanBlock.listContains().rightOperand();
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    //Set the ExpressionOperand
                    firstParameter.setValue(createExpressionOperand(rightOperand));
                    //Add the ActualParameterExpression to the parameters.
                    parameters.add(firstParameter);

                }
                //Take the name of the event and set as FunctionName.
                newFunctionCallExpression.setFunctionName(new Name(booleanBlockKind.replace("\"", "")));
                //Set the parameters into the FunctionCallExpression.
                newFunctionCallExpression.setActualParams(parameters);
                return newFunctionCallExpression;
            case "\"color:sees:\"":
                //Initialize variables.
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (booleanBlock.colorSees().leftOperand() != null) {
                    //Set the ExpressionOperand
                    Scratch2Parser.LeftOperandContext leftOperand = booleanBlock.colorSees().leftOperand();
                    ActualParameterExpression firstParameter = new ActualParameterExpression();

                    firstParameter.setValue(createExpressionOperand(leftOperand));
                    //Add the ActualParameterExpression to the parameters.
                    parameters.add(firstParameter);

                }
                if (booleanBlock.colorSees().rightOperand() != null) {

                    Scratch2Parser.RightOperandContext rightOperand = booleanBlock.colorSees().rightOperand();
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    //Set the ExpressionOperand
                    firstParameter.setValue(createExpressionOperand(rightOperand));
                    //Add the ActualParameterExpression to the parameters.
                    parameters.add(firstParameter);

                }
                //Take the name of the event and set as FunctionName.
                newFunctionCallExpression.setFunctionName(new Name(booleanBlockKind.replace("\"", "")));


                //Set the parameters into the FunctionCallExpression.
                newFunctionCallExpression.setActualParams(parameters);
                return newFunctionCallExpression;
            case "\"touchingColor:\"":
                //Initialize variables.
                newFunctionCallExpression = new FunctionCallExpression();
                parameters = new ArrayList<>();
                if (booleanBlock.touchingColor().singleOperand() != null) {
                    Scratch2Parser.SingleOperandContext singleOperand = booleanBlock.touchingColor().singleOperand();
                    ActualParameterExpression firstParameter = new ActualParameterExpression();
                    //Set the ExpressionOperand
                    firstParameter.setValue(createExpressionOperand(singleOperand));
                    //Add the ActualParameterExpression to the parameters.
                    parameters.add(firstParameter);

                }
                //Take the name of the event and set as FunctionName.
                newFunctionCallExpression.setFunctionName(new Name(booleanBlockKind.replace("\"", "")));
                //Set the parameters into the FunctionCallExpression.
                newFunctionCallExpression.setActualParams(parameters);
                return newFunctionCallExpression;

            default:
                throw new IllegalStateException("Unexpected value: " + booleanBlockKind);
        }
    }

    /**
     * This function takes a leftOperand and transforms it into a new Expression. This function classify the leftOperand
     *
     * @param leftOperand The leftOperan to be transformed.
     * @return A new Expression with the leftOperand's information.
     */
    default Expression createExpressionOperand(Scratch2Parser.LeftOperandContext leftOperand) {
        if (leftOperand.literalString() != null) {
            return createStringLiteral(leftOperand.literalString());
        }
        if (leftOperand.literalBoolean() != null) {
            return createBooleanLiteral(leftOperand.literalBoolean());
        }
        if (leftOperand.NUMBER() != null) {
            return createRealLiteral(leftOperand.NUMBER().getText());
        }
        if (leftOperand.statement() != null) {
            return createExpression(leftOperand.statement());
        }
        return null;
    }
    /**
     * This function takes a leftOperand and transforms it into a new Expression. This function classify the centerOperand
     * @param centerOperand The centerOperand to be transformed.
     * @return A new Expression with the centerOperand's information.
     */
    default Expression createExpressionOperand(Scratch2Parser.CenterOperandContext centerOperand) {
        if (centerOperand.literalString() != null) {
            return createStringLiteral(centerOperand.literalString());
        }
        if (centerOperand.literalBoolean() != null) {
            return createBooleanLiteral(centerOperand.literalBoolean());
        }
        if (centerOperand.NUMBER() != null) {
            return createRealLiteral(centerOperand.NUMBER().getText());
        }
        if (centerOperand.statement() != null) {
            return createExpression(centerOperand.statement());
        }
        return null;
    }

    /**
     * This function takes a rightOperand and transforms it into a new Expression. This function classify the leftOperand
     *
     * @param rightOperand The rightOperand to be transformed.
     * @return A new Expression with the rightOperand's information.
     */
    default Expression createExpressionOperand(Scratch2Parser.RightOperandContext rightOperand) {
        if (rightOperand.literalString() != null) {
            return createStringLiteral(rightOperand.literalString());
        }
        if (rightOperand.literalBoolean() != null) {
            return createBooleanLiteral(rightOperand.literalBoolean());
        }
        if (rightOperand.NUMBER() != null) {
            return createRealLiteral(rightOperand.NUMBER().getText());
        }
        if (rightOperand.statement() != null) {
            return createExpression(rightOperand.statement());
        }
        return null;
    }

    /**
     * This function takes a singleOperand and transforms it into a new Expression. This function classify the leftOperand
     *
     * @param singleOperand The singleOperand to be transformed.
     * @return A new Expression with the singleOperand's information.
     */
    default Expression createExpressionOperand(Scratch2Parser.SingleOperandContext singleOperand) {
        if (singleOperand.literalString() != null) {
            return createStringLiteral(singleOperand.literalString());
        }
        if (singleOperand.literalBoolean() != null) {
            return createBooleanLiteral(singleOperand.literalBoolean());
        }
        if (singleOperand.NUMBER() != null) {
            return createRealLiteral(singleOperand.NUMBER().getText());
        }
        if (singleOperand.statement() != null) {
            return createExpression(singleOperand.statement());
        }
        return null;
    }

    /**
     * This function creates a new UnaryOperator classifying the operator.
     *
     * @param operator The Scratch's unary operator.
     * @return A new UnaryOperator.
     */
    default UnaryOperator createUnaryOperator(String operator) {
        UnaryOperator unaryOperator = null;
        switch (operator) {
            case "\"not\"":
                unaryOperator = new Not();
                break;
        }
        unaryOperator.setSimbolo(operator);
        return unaryOperator;
    }

    /**
     * This function creates a new BinaryOperator classifying the operator.
     *
     * @param operator The Scratch's binary operator.
     * @return
     */
    default BinaryOperator createBinaryOperator(String operator) {
        BinaryOperator newOperator = null;
        switch (operator) {
            case "\"<\"":
                newOperator = new Less();
                break;
            case "\">\"":
                newOperator = new Greater();
                break;
            case "\"=\"":
                newOperator = new Equal();
                break;
            case "\"&\"":
                newOperator = new And();
                break;
            case "\"|\"":
                newOperator = new Or();
                break;
            case "\"+\"":
                newOperator = new Add();
                break;
            case "\"-\"":
                newOperator = new Subtract();
                break;
            case "\"\\/\"":
                newOperator = new Divide();
                break;
            case "\"*\"":
                newOperator = new Multiply();
                break;
            case "\"%\"":
                newOperator = new Modulus();
                break;
            /*default:
                GenericOperator a = new GenericOperator();
                a.setSimbol(operator);
                newOperator = a;
                break;*/
        }
        return newOperator;
    }

    /**
     * This function takes the capBlock and transforms it in to a Expression.
     *
     * @param capBlock The capBlock to be mapped.
     * @return A new Expression object with all information of the capBlock.
     */
    default Expression createExpressionFromCapBlock(Scratch2Parser.CapBlockContext capBlock) {
        //Create a new ExpressionStatement
        FunctionCallExpression newFunctionCallExpression = new FunctionCallExpression();
        //Get the kind of Hat Block.
        String capBlockKind = capBlock.getChild(0).getChild(1).getText();
        ArrayList<ActualParameter> actualParameters = new ArrayList<>();
        ActualParameterExpression actualParameter;
        switch (capBlockKind) {
            case "\"stopScripts\"":
                // stopScripts
                //      : '[' STOP_SCRIPTS_TOKEN ',' literalString ']';
                actualParameter = new ActualParameterExpression();
                //Extract the literalString parameter and set into ActualParameterExpression.
                actualParameter.setValue(createStringLiteral(capBlock.stopScripts().literalString()));
                //Add the ActualParameterExpression to the parameters.
                actualParameters.add(actualParameter);
                // Set the tag
                //Add the parameters to the FunctionCall
                newFunctionCallExpression.setActualParams(actualParameters);
                break;
            case "\"deleteClone\"":
                //deleteClone
                //      : '[' DELETE_CLONE_TOKEN ']';
                break;
        }
        //Take the name of the event and set as FunctionName.
        newFunctionCallExpression.setFunctionName(new Name(capBlockKind.replace("\"", "")));
        //Set the expression.
        return newFunctionCallExpression;
    }

    /**
     * This function takes a HatBlock and transforms it in to a Expression.
     * All events of Scratch will be mapped to a
     *
     * @param hatBlock The Hat Block to be mapped
     * @return A Expression with all information of the Hat Block.
     */
    default Expression createExpressionFromHatBlock(Scratch2Parser.HatBlockContext hatBlock) {

        FunctionCallExpression newFunctionCallExpression = new FunctionCallExpression();

        //Get the kind of Hat Block.
        String hatBlockKind = hatBlock.getChild(0).getChild(1).getText();
        //All functions are events, so you may or may not have parameters.
        ArrayList<ActualParameter> actualParameters = new ArrayList<>();
        ActualParameterExpression actualParameter;
        switch (hatBlockKind) {
            case "\"whenGreenFlag\"":
                //  whenGreenFlag
                //      : '[' WHEN_GREEN_FLAG_TOKEN ']';
                //newFunctionCallExpression.addSubTag(WHEN_GREEN_FLAG);
                break;
            case "\"whenKeyPressed\"":
                //  whenKeyPressed
                //      : '[' WHEN_KEY_PRESSED_TOKEN  ',' literalString ']';

                actualParameter = new ActualParameterExpression();
                //Extract the literalString parameter and set into ActualParameterExpression.
                actualParameter.setValue(createStringLiteral(hatBlock.whenKeyPressed().literalString()));
                //Add the ActualParameterExpression to the parameters.
                actualParameters.add(actualParameter);
                //Add the parameters to the FunctionCall
                newFunctionCallExpression.setActualParams(actualParameters);
                //newFunctionCallExpression.addSubTag(WHEN_KEY_PRESSED);
                break;
            case "\"whenClicked\"":
                //whenClicked
                //      : '[' WHEN_CLICKED_TOKEN ']';
                //newFunctionCallExpression.addSubTag(WHEN_CLICKED);
                break;
            case "\"whenSceneStarts\"":
                //whenSceneStarts
                //      : '[' WHEN_SCENE_STARTS_TOKEN  ',' literalString  ']';
                actualParameter = new ActualParameterExpression();
                //Extract the literalString parameter and set into ActualParameterExpression.
                actualParameter.setValue(createStringLiteral(hatBlock.whenSceneStarts().literalString()));
                //Add the ActualParameterExpression to the parameters.
                actualParameters.add(actualParameter);
                //Add the parameters to the FunctionCall
                newFunctionCallExpression.setActualParams(actualParameters);
                //newFunctionCallExpression.addSubTag(WHEN_SCENE_STARTS);
                break;
            case "\"whenSensorGreaterThan\"":
                //
                // whenSensorGreaterThan
                //      : '[' WHEN_SENSOR_GREATER_THAN_TOKEN  ',' literalString ',' NUMBER ']'
                //      | '[' WHEN_SENSOR_GREATER_THAN_TOKEN  ',' literalString ',' statement ']';
                actualParameter = new ActualParameterExpression();
                Scratch2Parser.WhenSensorGreaterThanContext whenSensor = hatBlock.whenSensorGreaterThan();
                //Extract the literalString parameter and set into ActualParameterExpression.
                actualParameter.setValue(createStringLiteral(whenSensor.literalString()));
                //Add the ActualParameterExpression to the parameters.
                actualParameters.add(actualParameter);

                // If the second parameter is a Number
                if (whenSensor.NUMBER() != null) {
                    //Create the second parameter.
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    //Extract the number parameter and set into ActualParameterExpression.
                    secondParameter.setValue(createRealLiteral(whenSensor.NUMBER().getText()));

                    //Add the ActualParameterExpression to the parameters.
                    actualParameters.add(secondParameter);
                }
                // If the second parameter is a statement
                if (whenSensor.statement() != null) {
                    ActualParameterExpression secondParameter = new ActualParameterExpression();
                    secondParameter.setValue(createExpression(whenSensor.statement()));
                    actualParameters.add(secondParameter);
                }

                newFunctionCallExpression.setActualParams(actualParameters);
                //newFunctionCallExpression.addSubTag(WHEN_SENSOR_GREATER_THAN);
                break;
            case "\"whenIReceive\"":
                //whenIReceive
                //      : '[' WHEN_I_RECEIVE_TOKEN  ',' literalString  ']';
                actualParameter = new ActualParameterExpression();
                //Extract the literalString parameter and set into ActualParameterExpression.
                actualParameter.setValue(createStringLiteral(hatBlock.whenIReceive().literalString()));
                //Add the ActualParameterExpression to the parameters.
                actualParameters.add(actualParameter);
                newFunctionCallExpression.setActualParams(actualParameters);
                //newFunctionCallExpression.addSubTag(WHEN_I_RECEIVE);
                break;
            case "\"whenCloned\"":
                //whenCloned
                //      : '[' WHEN_CLONED_TOKEN ']';
                //newFunctionCallExpression.addSubTag(WHEN_CLONED);
                break;
        }
        //Take the name of the event and set as FunctionName.
        newFunctionCallExpression.setFunctionName(new Name(hatBlockKind.replace("\"", "")));

        return newFunctionCallExpression;
    }

    /**
     * This functions takes a literalString and transforms it into a new IdentiferReference. This can be used to map a variable's name
     *
     * @param literalString The variable's name to be mapped.
     * @return A new IdentifierReference with the literalString's information.
     */
    default IdentifierReference createIdentifierReferenceFromLiteralString(Scratch2Parser.LiteralStringContext literalString) {
        IdentifierReference newName = new IdentifierReference();
        newName.setIdentifierName(new Name(literalString.getText()));
        return newName;
    }

    /**
     * This function takes a String and transforms it in to a new RealLiteral Expression
     * It is not possible pass by parameters a NUMBER because it is a terminal node.
     *
     * @param number A String that represents the number to be mapped.
     * @return A new RealLiteral Expression with the input.
     */
    default Expression createRealLiteral(String number) {
        RealLiteral realLiteral = new RealLiteral();
        realLiteral.setValue(number);

        return realLiteral;
    }

    /**
     * This function takes a literalString and transforms it in a new StringLiteral Expression.
     *
     * @param literalString The name to be transformed.
     * @return A new Expression with the literalString's information.
     */
    default Expression createStringLiteral(Scratch2Parser.LiteralStringContext literalString) {
        StringLiteral newStringLiteral = new StringLiteral();
        //TODO: Use of the " in the getText
        newStringLiteral.setValue(literalString.getText());
        return newStringLiteral;
    }

    /**
     * This function takes a literalBoolean and transforms it in a new BooleanLiteral Expression.
     *
     * @param literalBoolean The name to be transformed.
     * @return A new Expression with the literalBoolean's information.
     */
    default Expression createBooleanLiteral(Scratch2Parser.LiteralBooleanContext literalBoolean) {
        BooleanLiteral newStringLiteral = new BooleanLiteral();
        //TODO: Use of the " in the getText
        newStringLiteral.setValue(literalBoolean.getText());
        return newStringLiteral;
    }

    default ArrayList<MemberObject> variablesAndListToMemberObject(List<Scratch2Parser.VariablesContext> variablesContexts
            , List<Scratch2Parser.ListContext> listContexts) {
        //Create an arraylist of MemberObject
        ArrayList<MemberObject> memberObjectArrayList = new ArrayList();
        //For each variableContext extract all variables block
        for (Scratch2Parser.VariablesContext variableContext : variablesContexts) {
            for (Scratch2Parser.VariableBlockContext variableBlock : variableContext.variableArray().variableBlock()) {
                //Add the MemberObject's result of a Variable Block in to the arraylist.
                memberObjectArrayList.add(variableBlockToMemberObject(variableBlock));
            }
        }
        //For each ListContext extract all ListBlockContext
        for (Scratch2Parser.ListContext listContext : listContexts) {
            for (Scratch2Parser.ListBlockContext listBlockContext : listContext.listArray().listBlock()) {

                //Add the MemberObject's result of a List Block in to the arraylist.
                memberObjectArrayList.add(listBlockToMemberObject(listBlockContext));
            }
        }
        //Return the ArrayList with all MemberObjects
        return memberObjectArrayList;
    }


    default ArrayList<FormalParameterDefinition> formalParameterDefinitionList(Scratch2Parser.Name_functionContext name, Scratch2Parser.Param_type_definitionContext paramNames) {
        ArrayList<FormalParameterDefinition> formalParameterArrayList = new ArrayList();
        String[] possiblesType = name.getText().replace("\"", "").split(" ");
        int i = 0;
        for (String param: possiblesType) {
            if(param.contentEquals("%b")) {
                FormalParameterDefinition newFP = new FormalParameterDefinition();
                UnnamedTypeReference unnamed = new UnnamedTypeReference();
                unnamed.setType(new Boolean());
                newFP.setDefinitionType(unnamed);
                newFP.setIdentifierName(new Name(paramNames.literalString().get(i).getText()));
                i++;
                formalParameterArrayList.add(newFP);
            }else{
                if(param.contentEquals("%s")) {
                    FormalParameterDefinition newFP = new FormalParameterDefinition();
                    UnnamedTypeReference unnamed = new UnnamedTypeReference();
                    //unnamed.setType(new StringType());
                    newFP.setDefinitionType(unnamed);
                    newFP.setIdentifierName(new Name(paramNames.literalString().get(i).getText()));
                    i++;
                    formalParameterArrayList.add(newFP);
                }else{
                    if(param.contentEquals("%n")) {
                        FormalParameterDefinition newFP = new FormalParameterDefinition();
                        UnnamedTypeReference unnamed = new UnnamedTypeReference();
                        unnamed.setType(new Real());
                        newFP.setDefinitionType(unnamed);
                        newFP.setIdentifierName(new Name(paramNames.literalString().get(i).getText()));
                        i++;
                        formalParameterArrayList.add(newFP);
                    }
                }
            }
        }

        return formalParameterArrayList;
    }

    /**
     * This function takes the listBlockContext and transforms it in to a MemberObject
     *
     * @param list The listBlockContext to be transformed.
     * @return Return a MemberObject with all the information of the list.
     */
    default MemberObject listBlockToMemberObject(Scratch2Parser.ListBlockContext list) {
        //Create the list of fragments
        List<Fragment> fragmentList = new ArrayList<>();

        //Create a single fragment.
        Fragment fragment = new Fragment();
        //Set the name of the fragment with the name of the list.
        fragment.setIdentifierName(literalStringToName(list.listName(0).literalString()));

        //Add to the fragment list.
        fragmentList.add(fragment);

        //Create a variable Definition to store the list of fragments.
        VariableDefinition variableDefinition = new VariableDefinition();
        //Set the list of fragments
        variableDefinition.setFragments(fragmentList);
        //Add a DefinitionType.
        variableDefinition.setDefinitionType(createNamedTypeReference());

        MemberObject newMember = new MemberObject();
        //Add the variableDefinition to the newMember.
        newMember.setMember(variableDefinition);


        return newMember;
    }

    /**
     * This function takes a varaibleBlockContext and transforms it in to a MemberObject.
     *
     * @param variable This is the VariablBlockContext to be transformed.
     * @return Return a MemberObject with all the information in the VariableContext.
     */
    default MemberObject variableBlockToMemberObject(Scratch2Parser.VariableBlockContext variable) {
        //Create the list of fragments
        List<Fragment> listFragments = new ArrayList<>();
        //Create a single fragment.
        Fragment fragment = new Fragment();
        //Set the name of the fragment with the name of the list.
        fragment.setIdentifierName(literalStringToName(variable.name(0).literalString()));

        //Add to the fragment list.
        listFragments.add(fragment);

        //Create a variable Definition to store the list of fragments.
        VariableDefinition variableDefinition = new VariableDefinition();
        //Set the list of fragments
        variableDefinition.setFragments(listFragments);

        MemberObject newMember = new MemberObject();
        //Add the variableDefinition to the newMember.
        newMember.setMember(variableDefinition);

        return newMember;
    }

    /**
     * This function creates a NamedTypeReference for a list.
     *
     * @return A new NamedTypeReference with ArrayName and the DataType
     */
    default NamedTypeReference createNamedTypeReference() {
        //Create a NamedTypeReference
        NamedTypeReference newType = new NamedTypeReference();
        //Store the ArrayType from the function arrayToArrayType();
        ArrayType array = arrayToArrayType();
        //Set the DataType.
        newType.setDataType(array);
        //Set the Name.
        newType.setTypeName(new Name("Array"));
        return newType;
    }

    /**
     * This function creates the ArrayType for a list
     *
     * @return
     */
    default ArrayType arrayToArrayType() {
        //Create a new ArrayType
        ArrayType newArrayType = new ArrayType();
        // Scratch list doesn't have amount dimension when it is created.
        newArrayType.setAmountDimension(1);
        return newArrayType;
    }

    /**
     * This function transform the initial Sprite in to ArrayList of DefintionObject.
     *
     * @param sprite
     * @return
     */
    @Named("spriteToDefinitionObject")
    default ArrayList<DefintionObject> spriteToDefinitionObject(Scratch2Parser.SpriteObjectContext sprite) {
        // Store the AggregateTypeDefinition list from the function arrayListAggregateType.
        ArrayList<AggregateTypeDefinition> arrayListAggregateType = arrayListAggregateType(sprite);
        //Move the AggregateTypeDefinition ArrayList to the DefintionObject ArrayList, due the GAST model.
        ArrayList<DefintionObject> listDefinition = new ArrayList<>();
        for (AggregateTypeDefinition aggregate : arrayListAggregateType) {
            listDefinition.add(aggregate);
        }

        return listDefinition;
    }


    @Mapping(expression = "java(literalString.getText())", target = "nameString")
    /**
     * This function takes a LiteralStringContext and transforms it in to a Name object.
     */
    Name literalStringToName(Scratch2Parser.LiteralStringContext literalString);
}
