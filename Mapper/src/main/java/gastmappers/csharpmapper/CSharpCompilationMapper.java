package gastmappers.csharpmapper;

import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSource.SourceLocation;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.ImportDeclaration;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.*;
import ASTMCore.ASTMSyntax.Expression.*;
import ASTMCore.ASTMSyntax.Expression.ArrayAccess;
import ASTMCore.ASTMSyntax.Expression.BooleanLiteral;
import ASTMCore.ASTMSyntax.Expression.CastExpression;
import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.ASTMSyntax.Expression.ParenthesizedExpression;
import ASTMCore.ASTMSyntax.Expression.StringLiteral;
import ASTMCore.ASTMSyntax.Statement.*;
import ASTMCore.ASTMSyntax.Statement.BreakStatement;
import ASTMCore.ASTMSyntax.Statement.ContinueStatement;
import ASTMCore.ASTMSyntax.Statement.EmptyStatement;
import ASTMCore.ASTMSyntax.Statement.ExpressionStatement;
import ASTMCore.ASTMSyntax.Statement.IfStatement;
import ASTMCore.ASTMSyntax.Statement.ReturnStatement;
import ASTMCore.ASTMSyntax.Statement.Statement;
import ASTMCore.ASTMSyntax.Statement.SwitchCase;
import ASTMCore.ASTMSyntax.Statement.SwitchStatement;
import ASTMCore.ASTMSyntax.Statement.SynchronizedStatement;
import ASTMCore.ASTMSyntax.Statement.ThrowStatement;
import ASTMCore.ASTMSyntax.Statement.TryStatement;
import ASTMCore.ASTMSyntax.Statement.WhileStatement;
import ASTMCore.ASTMSyntax.Types.*;

import ASTMCore.ASTMSyntax.Types.ArrayType;
import ASTMCore.ASTMSyntax.Types.Boolean;
import ASTMCore.ASTMSyntax.Types.Byte;
import ASTMCore.ASTMSyntax.Types.Character;
import ASTMCore.ASTMSyntax.Types.Double;
import ASTMCore.ASTMSyntax.Types.Float;
import ASTMCore.ASTMSyntax.Types.Integer;
import ASTMCore.ASTMSyntax.Types.Void;
import gastmappers.MappedMethod;
import gastmappers.csharpmapper.ANTLR.CSharpParser.*;
import gastmappers.csharpmapper.bindings.*;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * This interface provides all methods required for the mapping
 * of the C# AST from an ANTLR grammar to the GAST.
 *
 * @author AVIB team.
 * @version 1.0
 * @since 1.0
 */

@Mapper
public interface CSharpCompilationMapper {

    CSharpCompilationMapper INSTANCE = Mappers.getMapper(CSharpCompilationMapper.class);
    Verifier VERIFIER = Verifier.getInstance();
    boolean semantic = false;

    /**
     * This method checks a node as mapped in the verifier
     *
     * @param source original node in the ANTLR ParseTree
     * @param target target node in the GAST
     * @param <T1> any node in the ANTLR ParseTree
     * @param <T2> any node in the GAST
     */

    /**
     * Este Hashmap mantiene informacion semántica de bindings
     */
    // JNS: bindings dictionary
    Hashtable<String, Object> BINDINGS = new Hashtable<String, Object>();
    CallBinding SOURCEFILE = new CallBinding();

    @AfterMapping
    default <T1, T2> void logMapping(T1 source, @MappingTarget T2 target) {
        VERIFIER.check(source, target);
    }


    /**
     * This method generates the root of a GAST (CompilationUnit) for each namespace
     * in the file
     *
     * @param namespace Root of the C# AST.
     * @param imports   All imports made in this file.
     * @param tipos     All types defined in this file.
     * @return A GAST root with all values mapped into it.
     */
    @Mappings({@Mapping(target = "path", ignore = true), @Mapping(target = "fragments", ignore = true),
            @Mapping(target = "language", ignore = true), @Mapping(source = "tipos", target = "opensScope.declOrDefn"),
            @Mapping(source = "imports", target = "imports"),
            @Mapping(source = "namespace", target = "gPackage")})
    CompilationUnit compilationtocompilation(IdentifierContext namespace,
                                             List<Using_directiveContext> imports,
                                             List<Namespace_member_declarationContext> tipos);


    /**
     * Map a StringLiteral from C# AST to a String in the GAST.
     *
     * @param value value of the variable.
     * @return String object with all values mapped into it.
     */
    @Mappings({@Mapping(source = "value", target = "value")})
    StringLiteral stringtostring(String value);

    /**
     * Map a CharLiteral from C# AST to a String in the GAST.
     *
     * @param charValue value of the variable.
     * @return String object with all values mapped into it.
     */
    @Mappings({@Mapping(source = "charValue", target = "value")})
    StringLiteral chartochar(String charValue);

    /**
     * Map an integer literal from C# AST to a RealLiteral in the GAST.
     *
     * @param value value of the variable.
     * @return RealLiteral object with all values mapped into it.
     */
    @Mappings({@Mapping(source = "value", target = "value")})
    RealLiteral integertointeger(String value);

    /**
     * Map a boolean literal from C# AST to a BooleanLiteral in the GAST.
     *
     * @param boolValue value of the variable.
     * @return BooleanLiteral object with all values mapped into it.
     */
    @Mappings({@Mapping(source = "boolValue", target = "value")})
    BooleanLiteral booleantoboolean(String boolValue);

    /**
     * Map a null value from C# AST to a StringLiteral in the GAST.
     *
     * @param value value of the variable.
     * @return StringLiteral object with all values mapped into it.
     */
    @Mappings({@Mapping(constant = "null", target = "value")})
    StringLiteral nulltonull(String value);

    /**
     * Maps a namespace member declaration to a TypeDefinition in the GAST.
     * The member can be a class, enum, annotation
     *
     * @param memberDeclaration The namespace member declaration from the C# AST.
     * @return A TypeDefinition object with all values mapped into it.
     */
    // TODO fix confusion between classtoclass and TypeDeclarationToClass in checking if is class, interface, etc
    default TypeDefinition classtoclass(Namespace_member_declarationContext memberDeclaration) {
        Type_declarationContext type_declarationContext = memberDeclaration.type_declaration();
        if (type_declarationContext.class_definition() != null) {
            if (semantic) {
                // JNS: binding package in class definition
                AggregateTypeDefinition aggregateTypeDefinition = TypeDeclarationtoClassType(type_declarationContext);
                aggregateTypeDefinition.setLocationInfo(getSourceFromContext(type_declarationContext));
                String path = SOURCEFILE.getPath();
                int line = memberDeclaration.getStart().getLine();
                String classKey = path + "||" + String.valueOf(line);
                if (BINDINGS.containsKey(classKey)) {
                    ClassType classType = (ClassType) aggregateTypeDefinition.getAggregateType();
                    String packageName = ((ClassBinding) BINDINGS.get(classKey)).getNamespace();
                    classType.setPackageName(packageName);
                }
                aggregateTypeDefinition.setLocationInfo(getSourceFromContext(type_declarationContext));
                return aggregateTypeDefinition;
            }

            AggregateTypeDefinition typeDeclaration = TypeDeclarationtoClassType(type_declarationContext);
            typeDeclaration.setLocationInfo(getSourceFromContext(type_declarationContext));
            return typeDeclaration;

        } else if (type_declarationContext.enum_definition() != null) {
            Enum_definitionContext enu = type_declarationContext.enum_definition();
            EnumTypeDefinition newEnum = TypeDeclarationToEnumTypeDefinition(enu.identifier().getText(), type_declarationContext.all_member_modifiers().all_member_modifier(), enu.enum_body());
            newEnum.setLocationInfo(getSourceFromContext(enu));
            newEnum.getDefinitionType().setEnumLiterals(enumLiteralListToList(enu.enum_body().enum_member_declaration()));
            newEnum.setLocationInfo(getSourceFromContext(enu));
            return newEnum;
        }
//		else if (tipo instanceof AnnotationTypeDeclaration) {
//			AnnotationTypeDeclaration annotation = (AnnotationTypeDeclaration) tipo;
//			AnnotationType newAnnotation = TypeDeclarationToAnnotatioDeclaration(
//					new Name(annotation.getName().toString()), annotation.modifiers(), annotation.bodyDeclarations());
//
//			return newAnnotation;
//		}
        else
            return null;
    }

    /**
     * Maps the definition of a type declaration to an AggregateType in the GAST.
     * The type declaration can be a class, struct, interface, enum or delegate
     *
     * @param type The type declaration object from the C# AST.
     * @return An AggregateType object with all the values mapped into it
     */
    default AggregateType TypeDeclarationtoClass(Type_declarationContext type) {
        // If the type declaration is a class
        if (type.class_definition() != null) {
            List<All_member_modifierContext> modifiers;
            if (type.all_member_modifiers() != null) {
                modifiers = type.all_member_modifiers().all_member_modifier();
            } else {
                modifiers = null;
            }
            Namespace_or_type_nameContext inherency;
            if (type.class_definition().class_base() != null) {
                inherency = type.class_definition().class_base().class_type().namespace_or_type_name();
            } else {
                inherency = null;
            }
            List<Namespace_or_type_nameContext> implementes;
            if (type.class_definition().class_base() != null && inherency != null) {
                implementes = type.class_definition().class_base().namespace_or_type_name();
            } else {
                implementes = null;
            }

            List<Type_parameterContext> parameters;
            if (type.class_definition().type_parameter_list() != null) {
                parameters = type.class_definition().type_parameter_list().type_parameter();
            } else {
                parameters = null;
            }
            return MappingClass(type.class_definition().identifier(),
                    modifiers,
                    type.class_definition().class_body().class_member_declarations().class_member_declaration(),
                    inherency,
                    implementes,
                    type.class_definition().class_body().class_member_declarations().class_member_declaration(),
                    parameters,
                    false);

            // If the type declarations is an interface
        } else if (type.interface_definition() != null) {
            List<All_member_modifierContext> modifiers;
            if (type.all_member_modifiers() != null) {
                modifiers = type.all_member_modifiers().all_member_modifier();
            } else {
                modifiers = null;
            }
            Namespace_or_type_nameContext inherency;
            if (type.interface_definition().interface_base() != null) {
                inherency = type.interface_definition().interface_base().interface_type_list().namespace_or_type_name(0); // Puede inherit de varias interfaces?
            } else {
                inherency = null;
            }
            List<Type_parameterContext> parameters;
//			if (tipo.c != null) {
//				parameters = tipo.class_definition().type_parameter_list().type_parameter();
//			} else {
//				parameters = null;
//			}
            ClassType classType = MappingClass(type.interface_definition().identifier(),
                    modifiers,
                    null,
//					tipo.interface_definition().interface_body().interface_member_declaration(),
                    inherency,
                    null,
                    null,
//					tipo.class_definition().class_body().class_member_declarations().class_member_declaration(),
                    null,
                    true);
            classType.setLocationInfo(getSourceFromContext(type));
            return classType;
            //parameters);
        } else {
            return null;
        }
    }


    /**
     * Maps and all member modifier to a Modifier in the GAST.
     *
     * @param modifier All member modifier from the C# AST.
     * @return Modifier object with all values mapped into it.
     */

    default Modifiers modifiertomodifier(All_member_modifierContext modifier) {
        Modifiers newmodifier = null;
        String tipo = "";
        tipo = modifier.getText();
        switch (tipo) {
            case "public":
                newmodifier = new PublicModifier();
                break;
            case "private":
                newmodifier = new PrivateModifier();
                break;
            case "protected":
                newmodifier = new ProtectedModifier();
                break;
            case "abstract":
                newmodifier = new AbstractModifier();
                break;
            case "static":
                newmodifier = new StaticModifier();
                break;
            case "sealed":
            case "readonly":
                newmodifier = new FinalModifier();
                break;
            case "extern":
                newmodifier = new NativeModifier();
                break;
            case "volatile":
                newmodifier = new VolatileModifier();
                break;
            case "internal": // No es una conversion exacta
                newmodifier = new DefaultModifier();
                break;
            case "strictfp":
                newmodifier = new StrictfpModifier();
                break;
            default:
                newmodifier = new PublicModifier();
                break;
        }

//		else if (modifier instanceof MarkerAnnotation) {
//			tipo = ((MarkerAnnotation) modifier).getTypeName().getFullyQualifiedName();
//			newmodifier = new AnnotationModifier(tipo);
//		} else if (modifier instanceof SingleMemberAnnotation) {
//			SingleMemberAnnotation annotation = (SingleMemberAnnotation) modifier;
//			ASTMCore.ASTMSyntax.DeclarationAndDefinition.SingleMemberAnnotation newAnnotation = new ASTMCore.ASTMSyntax.DeclarationAndDefinition.SingleMemberAnnotation();
//			newAnnotation.setName(ExpresionToExpresion(annotation.getTypeName()));
//			newAnnotation.setValue(ExpresionToExpresion(annotation.getValue()));
//
//			newmodifier = newAnnotation;
//		}
        newmodifier.setLocationInfo(getSourceFromContext(modifier));
        return newmodifier;

    }
    //Done

    /**
     * Maps class member declarations to a MemberObject in the GAST.
     *
     * @param member class member declaration from the C# AST.
     * @return MemberObject with all values mapped into it.
     */

    default MemberObject memberToMember(Class_member_declarationContext member) {
        if (member.common_member_declaration() != null &&
                member.common_member_declaration().typed_member_declaration() != null &&
                member.common_member_declaration().typed_member_declaration().field_declaration() != null) {
            VariableDefinition variable = new VariableDefinition();
            variable.setDefinitionType(TypeReferencetoType(member.common_member_declaration().typed_member_declaration().type_()));
            variable.setFragments(fragmentsToFragments(member.common_member_declaration().typed_member_declaration().field_declaration().variable_declarators().variable_declarator()));
            variable.setLocationInfo(getSourceFromContext(member.common_member_declaration().typed_member_declaration()));
            MemberObject newMember = new MemberObject();
            try{
                newMember.setModifiers(ListModifiersToListModifiers(member.all_member_modifiers().all_member_modifier()));
            } catch (Exception e) {
                //e.printStackTrace();
            }
            newMember.setMember(variable);
            newMember.setLocationInfo(getSourceFromContext(member));
            return newMember;
        } else {
            return null;
        }
    }

    /**
     * Maps the prefix operator of an unary expression to a UnaryOperator in the GAST.
     *
     * @param operator string with the symbol of the operator from the C# AST.
     * @return UnaryOperator object with all values mapped into it.
     */
    default UnaryOperator UnaryExpressionOperatorToPrefixOperator(String operator) {
        UnaryOperator newOperand = null;
        switch (operator) {
            case "++":
                newOperand = new Increment();
                break;
            case "--":
                newOperand = new Decrement();
                break;
            case "+":
                newOperand = new UnaryPlus();
                break;
            case "-":
                newOperand = new UnaryMinus();
                break;
            case "~":
                newOperand = new BitNot();
                break;
            case "!":
                newOperand = new Not();
                break;
            default:
                newOperand = new DefaultUnaryOperator();
        }
        // Assigns the operator's simbol
        newOperand.setSimbolo(operator);
        return newOperand;
    }

    /**
     * Maps the postfix operator of a primary expression to a UnaryOperator in the GAST.
     *
     * @param operator string with the symbol of the operator from the C# AST.
     * @return UnaryOperator object with all values mapped into it.
     */
    default UnaryOperator PrimaryExpressionOperatorToPostfixOperator(String operator) {
        UnaryOperator newOperand = null;
        switch (operator) {
            case "++":
                newOperand = new PostIncrement();
                break;
            case "--":
                newOperand = new PostDecrement();
                break;
            default:
                newOperand = new DefaultUnaryOperator();
        }
        // Assigns the operator's simbol
        newOperand.setSimbolo(operator);
        return newOperand;
    }

    /**
     * Maps the binary operator of a binary expression to a BinaryOperator in the GAST.
     *
     * @param operator string with the symbol of the operator from the C# AST.
     * @return BinaryOperator object with all values mapped into it.
     */
    default BinaryOperator OperatorToOperator(String operator) {
        BinaryOperator newOperador = null;
        switch (operator) {
            case "==":
                newOperador = new Equal();
                break;
            case "=":
                newOperador = new Assign();
                break;
            case ">":
                newOperador = new Greater();
                break;
            case "<":
                newOperador = new Less();
                break;
            case ">=":
                newOperador = new NotLess();
                break;
            case "<=":
                newOperador = new NotGreater();
                break;
            case "!=":
                newOperador = new NotEqual();
                break;
            case "+":
                newOperador = new Add();
                break;
            case "-":
                newOperador = new Subtract();
                break;
            case "*":
                newOperador = new Multiply();
                break;
            case "/":
                newOperador = new Divide();
                break;
            case "&&":
                newOperador = new And();
                break;
            case "||":
                newOperador = new Or();
                break;
            default:
                GenericOperator a = new GenericOperator();
                a.setSimbol(operator);
                newOperador = a;
                break;
        }
        return newOperador;
    }


    /**
     * Maps expressions to a Expression in the GAST.
     *
     * @param expression expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */
    default Expression expressionToExpression(ExpressionContext expression) {
        Expression newExpression = null;

        if (expression != null && expression.assignment() != null) {
            // TODO check if it isn't a variable declaration
            AssignmentContext assignmentExpression = expression.assignment();
            BinaryExpression newAssignmentExpression = new BinaryExpression();
            if (assignmentExpression.assignment_operator() != null) {
                newAssignmentExpression.setOperator(OperatorToOperator(assignmentExpression.assignment_operator().getText()));
                newAssignmentExpression.setLeftOperand(unaryToExpression(assignmentExpression.unary_expression()));
                newAssignmentExpression.setRightOperand(expressionToExpression(assignmentExpression.expression()));
            } else {
                newAssignmentExpression.setLeftOperand(unaryToExpression(assignmentExpression.unary_expression()));
                newAssignmentExpression.setRightOperand(throwableToExpression(assignmentExpression.throwable_expression()));
            }
            newExpression = newAssignmentExpression;

        } else if (expression != null && expression.non_assignment_expression() != null) {
            newExpression = nonAssignmentToExpression(expression.non_assignment_expression());
        }
        logMapping(expression, newExpression);
        try{
            newExpression.setLocationInfo(getSourceFromContext(expression));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }

    /**
     * Maps non assignment expressions to a Expression in the GAST.
     *
     * @param nonAssignmentExpression non assignment expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression nonAssignmentToExpression(Non_assignment_expressionContext nonAssignmentExpression) {
        Expression newExpression = null;

        // Conditional expression
        if (nonAssignmentExpression.conditional_expression() != null) {
            Conditional_expressionContext conditionalExpression = nonAssignmentExpression.conditional_expression();
            // TODO check if it really is conditional
            if (conditionalExpression.throwable_expression().size() > 0) {
                ConditionalExpression newConditionalExpression = new ConditionalExpression();
                newConditionalExpression.setCondition(nullCoalescingToExpression(conditionalExpression.null_coalescing_expression()));
                newConditionalExpression.setOnTrueOperand(throwableToExpression(conditionalExpression.throwable_expression(0)));
                newConditionalExpression.setOnFalseOperand(throwableToExpression(conditionalExpression.throwable_expression(1)));
                newExpression = newConditionalExpression;
                newExpression.setLocationInfo(getSourceFromContext(conditionalExpression));
            } else {
                newExpression = nullCoalescingToExpression(conditionalExpression.null_coalescing_expression());
            }
        } else if (nonAssignmentExpression.lambda_expression() != null) {
            Lambda_expressionContext lambda_expression = nonAssignmentExpression.lambda_expression();
            LambdaExpression lambdaExpression = new LambdaExpression();
            if (lambda_expression.anonymous_function_signature().explicit_anonymous_function_parameter_list() != null) {
                lambdaExpression.setExplicitAnonymousParameter(explicitAnonymousParameterContextListToAnonymousParameter(lambda_expression.anonymous_function_signature().explicit_anonymous_function_parameter_list()));
            } else if (lambda_expression.anonymous_function_signature().implicit_anonymous_function_parameter_list() != null) {
                //We have to check this casting
                lambdaExpression.setIdentifierReferenceArrayList((ArrayList<IdentifierReference>) identifierContextListToIdentifierList(lambda_expression.anonymous_function_signature().implicit_anonymous_function_parameter_list().identifier()));
            } else if (
                    lambda_expression.anonymous_function_signature().identifier() != null) {
                lambdaExpression.setIdentifier(identifierToIdentifierReference(lambda_expression.anonymous_function_signature().identifier()));
            }
            if (lambda_expression.anonymous_function_body().throwable_expression() != null) {
                lambdaExpression.setThrowExpression(throwableToExpression(lambda_expression.anonymous_function_body().throwable_expression()));
            } else if (lambda_expression.anonymous_function_body().block() != null) {
                BlockStatement blockStatement = BlocktoBlockStatement(lambda_expression.anonymous_function_body().block());
                blockStatement.setLocationInfo(getSourceFromContext(lambda_expression.anonymous_function_body().block()));
                lambdaExpression.setBlockStatement(blockStatement);
            }
            newExpression = lambdaExpression;
            newExpression.setLocationInfo(getSourceFromContext(lambda_expression));
            newExpression.setTag("LambdaExpression");
            // TODO
        } else if (nonAssignmentExpression.query_expression() != null) {
            Query_expressionContext query_expression = nonAssignmentExpression.query_expression();
            Expression expression = queryExpressionContextToExpression(query_expression);
            newExpression = expression;

            newExpression.setTag("QueryExpression");
            //TODO
        }
        try{
            newExpression.setLocationInfo(getSourceFromContext(nonAssignmentExpression));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }

    /**
     * Maps the query expression into an expression
     *
     * @param context contoex to be mapped
     * @return the expression mapped
     */

    default Expression queryExpressionContextToExpression(Query_expressionContext context) {
        QueryExpression queryExpression = new QueryExpression();
        if (context.from_clause().type_() != null) {
            queryExpression.setReferenceFor(TypeReferencetoType(context.from_clause().type_()));
        }
        queryExpression.setIdentifierReferenceFrom(identifierToIdentifierReference(context.from_clause().identifier()));
        queryExpression.setExpressionFrom(expressionToExpression(context.from_clause().expression()));
        queryExpression.setBody(queryExpressionBodyToExpression(context.query_body()));
        queryExpression.setLocationInfo(getSourceFromContext(context));
        return queryExpression;
    }

    /**
     * Maps the body of the expression in a recursive way, sometimes the context has another inside and maps it too inside the
     * queryExpression class
     *
     * @param context context to be mapped
     * @return the context mapped into an expression
     */

    default QueryExpressionBody queryExpressionBodyToExpression(Query_bodyContext context) {
        QueryExpressionBody queryExpressionBody = new QueryExpressionBody();
        if (context.query_continuation() != null) {
            queryExpressionBody.setNextIdentifier(identifierToIdentifierReference(context.query_continuation().identifier()));
            queryExpressionBody.setNextBody(queryExpressionBodyToExpression(context.query_continuation().query_body()));
        }
        if (context.select_or_group_clause().SELECT() != null) {
            queryExpressionBody.setSelectGroupClause(context.select_or_group_clause().SELECT().getText());
            queryExpressionBody.setSelectGroupClauseFirst(expressionToExpression(context.select_or_group_clause().expression(0)));
        } else {
            queryExpressionBody.setSelectGroupClause(context.select_or_group_clause().GROUP().getText());
            queryExpressionBody.setSelectGroupClauseFirst(expressionToExpression(context.select_or_group_clause().expression(0)));
            queryExpressionBody.setSelectGroupClauseSecond(expressionToExpression(context.select_or_group_clause().expression(1)));
        }
        queryExpressionBody.setClauses(queryExpressionClauseListToExpressionList(context.query_body_clause()));
        queryExpressionBody.setLocationInfo(getSourceFromContext(context));
        return queryExpressionBody;
    }

    /**
     * Maps the clause into an expression
     *
     * @param context the context of the clause
     * @return the clause mapped
     */

    default QueryExpressionClause queryExpressionClauseToExpresion(Query_body_clauseContext context) {
        QueryExpressionClause clause = new QueryExpressionClause();
        ArrayList<IdentifierReference> references = new ArrayList<>();
        ArrayList<String> orderingDirs = new ArrayList<>();
        ArrayList<Expression> expressions = new ArrayList<>();
        if (context.from_clause() != null) {
            clause.setClauseName(context.from_clause().FROM().getText());
            if (context.from_clause().identifier() != null) {
                references.add(identifierToIdentifierReference(context.from_clause().identifier()));
                clause.setIdentifierReferences(references);
            }
            expressions.add(expressionToExpression(context.from_clause().expression()));
            clause.setExpressions(expressions);
            return clause;
        }
        if (context.let_clause() != null) {
            clause.setClauseName(context.let_clause().LET().getText());
            references.add(identifierToIdentifierReference(context.let_clause().identifier()));
            expressions.add(expressionToExpression(context.let_clause().expression()));
            clause.setExpressions(expressions);
            clause.setIdentifierReferences(references);
            return clause;
        }
        if (context.where_clause() != null) {
            clause.setClauseName(context.where_clause().WHERE().getText());
            expressions.add(expressionToExpression(context.where_clause().expression()));
            clause.setExpressions(expressions);
            return clause;
        }
        if (context.combined_join_clause() != null) {
            clause.setClauseName(context.combined_join_clause().JOIN().getText());
            if (context.combined_join_clause().type_() != null) {
                clause.setTypeCombined(TypeReferencetoType(context.combined_join_clause().type_()));
            }
            for (IdentifierContext identifier : context.combined_join_clause().identifier()
            ) {
                references.add(identifierToIdentifierReference(identifier));
            }
            clause.setIdentifierReferences(references);
            for (ExpressionContext expression : context.combined_join_clause().expression()
            ) {
                expressions.add(expressionToExpression(expression));
            }
            clause.setExpressions(expressions);
            clause.setLocationInfo(getSourceFromContext(context));
            return clause;
        } else if (context.orderby_clause() != null) {
            clause.setClauseName(context.orderby_clause().ORDERBY().getText());
            for (OrderingContext ordering : context.orderby_clause().ordering()
            ) {
                expressions.add(expressionToExpression(ordering.expression()));
                orderingDirs.add(ordering.dir.getText());
            }
            clause.setExpressions(expressions);
            clause.setOrderingDirs(orderingDirs);
            clause.setLocationInfo(getSourceFromContext(context));
            return clause;
        }
        return null;

    }

    /**
     * Maps a list of clauses into a list of clause expressions
     *
     * @param query_body_clauseContexts the list of clauses contexts
     * @return the list mapped into an arraylist
     */


    default ArrayList<QueryExpressionClause> queryExpressionClauseListToExpressionList(List<Query_body_clauseContext> query_body_clauseContexts) {
        ArrayList<QueryExpressionClause> clauses = new ArrayList<>();
        for (Query_body_clauseContext context : query_body_clauseContexts
        ) {
            clauses.add(queryExpressionClauseToExpresion(context));
        }
        return clauses;
    }

    /**
     * Maps null coalescing expressions to a Expression in the GAST.
     *
     * @param null_coalescing_expression null coalescing expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression nullCoalescingToExpression(Null_coalescing_expressionContext null_coalescing_expression) {
        Expression newExpression = null;
        if (null_coalescing_expression.null_coalescing_expression() != null) {
            NullCoalescingExpression expression = new NullCoalescingExpression();
            expression.setConditionalOrExpression(conditionalOrToExpression(null_coalescing_expression.conditional_or_expression(), 0));
            expression.setExpression(nullCoalescingToExpression(null_coalescing_expression.null_coalescing_expression()));
            newExpression = expression;
            newExpression.setTag("NullCoalescing");
        } else if (null_coalescing_expression.throw_expression() != null) {
            NullCoalescingExpression expression = new NullCoalescingExpression();
            expression.setConditionalOrExpression(conditionalOrToExpression(null_coalescing_expression.conditional_or_expression(), 0));
            expression.setThrowExpression(throwToExpression(null_coalescing_expression.throw_expression()));
            newExpression = expression;
            newExpression.setTag("NullCoalescing");
        } else {
            newExpression = conditionalOrToExpression(null_coalescing_expression.conditional_or_expression(), 0);
        }
        try{
            newExpression.setLocationInfo(getSourceFromContext(null_coalescing_expression));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }

    /**
     * Maps throwable expressions to a Expression in the GAST.
     *
     * @param throwable_expression throwable expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression throwableToExpression(Throwable_expressionContext throwable_expression) {
        Expression newExpression = null;
        if (throwable_expression.expression() != null) {
            newExpression = expressionToExpression(throwable_expression.expression());
        } else if (throwable_expression.throw_expression() != null) {
            newExpression = throwToExpression(throwable_expression.throw_expression());
        }
        return newExpression;
    }

    /**
     * Maps throw expressions to a Expression in the GAST.
     *
     * @param throw_expression throw expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression throwToExpression(Throw_expressionContext throw_expression) {
        ThrowExpression newExpression = new ThrowExpression();
        newExpression.setExpression(expressionToExpression(throw_expression.expression()));
        newExpression.setThrowable(throw_expression.THROW().getText());
        newExpression.setLocationInfo(getSourceFromContext(throw_expression));
//        System.out.println("Falta throw expression en el GAST");
        return newExpression;
    }

    /**
     * Maps conditional OR expressions to a Expression in the GAST.
     *
     * @param conditional_or_expression conditional OR expression from the C# AST.
     * @param currentOperand            value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */

    default Expression conditionalOrToExpression(Conditional_or_expressionContext conditional_or_expression, int currentOperand) {
        Expression newExpression = null;
        List<Conditional_and_expressionContext> operands = conditional_or_expression.conditional_and_expression();
        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(conditionalAndToExpression(operands.get(currentOperand), 0));
            newBinary.setOperator(OperatorToOperator(conditional_or_expression.OP_OR(0).toString()));
//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(conditionalAndToExpression(operands.get(currentOperand + 1), 0));
            } else {
                newBinary.setRightOperand(conditionalOrToExpression(conditional_or_expression, currentOperand + 1));
            }
            newExpression = newBinary;
            newExpression.setLocationInfo(getSourceFromContext(conditional_or_expression));
        } else {
            newExpression = conditionalAndToExpression(operands.get(0), 0);
        }
        return newExpression;
    }

    /**
     * Maps conditional AND expressions to a Expression in the GAST.
     *
     * @param conditional_and_expression conditional AND expression from the C# AST.
     * @param currentOperand             value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */

    default Expression conditionalAndToExpression(Conditional_and_expressionContext conditional_and_expression, int currentOperand) {
        Expression newExpression = null;
        List<Inclusive_or_expressionContext> operands = conditional_and_expression.inclusive_or_expression();
        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(inclusiveOrToExpression(operands.get(currentOperand), 0));
            newBinary.setOperator(OperatorToOperator(conditional_and_expression.OP_AND(0).toString()));
//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(inclusiveOrToExpression(operands.get(currentOperand + 1), 0));
            } else {
                newBinary.setRightOperand(conditionalAndToExpression(conditional_and_expression, currentOperand + 1));
            }
            newExpression = newBinary;
            newExpression.setLocationInfo(getSourceFromContext(conditional_and_expression));
        } else {
            newExpression = inclusiveOrToExpression(operands.get(0), 0);
        }
        return newExpression;
    }

    /**
     * Maps inclusive OR expressions to a Expression in the GAST.
     *
     * @param inclusive_or_expression inclusive OR expression from the C# AST.
     * @param currentOperand          value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */
    default Expression inclusiveOrToExpression(Inclusive_or_expressionContext inclusive_or_expression, int currentOperand) {
        Expression newExpression = null;
        List<Exclusive_or_expressionContext> operands = inclusive_or_expression.exclusive_or_expression();
        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(exclusiveOrToExpression(operands.get(currentOperand), 0));
            newBinary.setOperator(OperatorToOperator(inclusive_or_expression.BITWISE_OR(0).toString()));
//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(exclusiveOrToExpression(operands.get(currentOperand + 1), 0));
            } else {
                newBinary.setRightOperand(inclusiveOrToExpression(inclusive_or_expression, currentOperand + 1));
            }

            newExpression = newBinary;
            newExpression.setLocationInfo(getSourceFromContext(inclusive_or_expression));
        } else {
            newExpression = exclusiveOrToExpression(operands.get(0), 0);
        }
        return newExpression;
    }

    /**
     * Maps exclusive OR expressions to a Expression in the GAST.
     *
     * @param exclusiveOrExpression exclusive OR expression from the C# AST.
     * @param currentOperand        value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */

    default Expression exclusiveOrToExpression(Exclusive_or_expressionContext exclusiveOrExpression, int currentOperand) {
        Expression newExpression = null;
        List<And_expressionContext> operands = exclusiveOrExpression.and_expression();
        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(andToExpression(operands.get(currentOperand), 0));
            newBinary.setOperator(OperatorToOperator(exclusiveOrExpression.CARET(0).toString()));
//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(andToExpression(operands.get(currentOperand + 1), 0));
            } else {
                newBinary.setRightOperand(exclusiveOrToExpression(exclusiveOrExpression, currentOperand + 1));
            }
            try {
                newExpression.setLocationInfo(getSourceFromContext(exclusiveOrExpression));
            } catch (Exception e) {
                //e.printStackTrace();
            }
            newExpression = newBinary;
        } else {
            newExpression = andToExpression(operands.get(0), 0);
        }
        return newExpression;
    }

    /**
     * Maps AND expressions to a Expression in the GAST.
     *
     * @param andExpression  AND expression from the C# AST.
     * @param currentOperand value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */

    default Expression andToExpression(And_expressionContext andExpression, int currentOperand) {
        Expression newExpression = null;
        List<Equality_expressionContext> operands = andExpression.equality_expression();
        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(equalityToExpression(operands.get(currentOperand), 0));
            newBinary.setOperator(OperatorToOperator(andExpression.AMP(0).toString()));
//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(equalityToExpression(operands.get(currentOperand + 1), 0));
            } else {
                newBinary.setRightOperand(andToExpression(andExpression, currentOperand + 1));
            }
            newExpression = newBinary;
            newExpression.setLocationInfo(getSourceFromContext(andExpression));
        } else {
            newExpression = equalityToExpression(operands.get(0), 0);
        }
        return newExpression;
    }

    /**
     * Maps equality expressions to a Expression in the GAST.
     *
     * @param equalityExpression equality expression from the C# AST.
     * @param currentOperand     value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */
    default Expression equalityToExpression(Equality_expressionContext equalityExpression, int currentOperand) {
        Expression newExpression = null;
        List<Relational_expressionContext> operands = equalityExpression.relational_expression();
        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(relationalToExpression(operands.get(currentOperand)));

            // Assigns the operator, must be done manually to mantain the operator order
            try {
                String expressionString = equalityExpression.getText().replaceFirst(operands.get(0).getText(), "");
                for (int i = 1; i <= currentOperand; i++) {
                    expressionString = expressionString.substring(2);
                    expressionString = expressionString.replaceFirst(operands.get(i).getText(), "");
                }
                String operator = expressionString.substring(0, 2);
                newBinary.setOperator(OperatorToOperator(operator));
            } catch (Exception e) {
                //e.printStackTrace();
            }

//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(relationalToExpression(operands.get(currentOperand + 1)));
            } else {
                newBinary.setRightOperand(equalityToExpression(equalityExpression, currentOperand + 1));
            }
            newExpression = newBinary;
            newExpression.setLocationInfo(getSourceFromContext(equalityExpression));
        } else {
            newExpression = relationalToExpression(operands.get(0));
        }
        return newExpression;
    }


    /**
     * Maps relational expressions to a Expression in the GAST.
     *
     * @param relationalExpression relational expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression relationalToExpression(Relational_expressionContext relationalExpression) {
        Expression newExpression = shiftToExpression(relationalExpression.shift_expression(0), 0);
        List<ParseTree> operands = relationalExpression.children;

        if (operands.size() > 1) {
            // Si es un relational tiene mas de un shift
            int currentOperand = 0;
            ParseTree nextOperand;
            while (currentOperand < operands.size() - 2) {
                nextOperand = operands.get(currentOperand + 2);
                if (nextOperand instanceof Shift_expressionContext) {
                    String operator = operands.get(currentOperand + 1).getText();
                    BinaryExpression newBinary = new BinaryExpression();
                    newBinary.setLeftOperand(newExpression);
                    newBinary.setOperator(OperatorToOperator(operator));
                    newBinary.setRightOperand(shiftToExpression((Shift_expressionContext) nextOperand, 0));
//                newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
                    newExpression = newBinary;
                    newExpression.setTag("infix");

                } else if (nextOperand instanceof IsTypeContext) {
                    IsTypeContext instance = (IsTypeContext) nextOperand;
                    InstanceOfExpression newInstance = new InstanceOfExpression();
                    newInstance.setLeftOperand(newExpression);
                    NamedTypeReference newType;
                    if (instance.base_type() != null && instance.isTypePatternArms() == null && instance.identifier() == null) {
                        if (instance.rank_specifier().size() > 0) {
                            newType = new NamedTypeReference(new Name("Array"));
                            ArrayType array = new ArrayType();
                            array.setAmountDimension(instance.rank_specifier().size());
                            array.setBaseType(baseTypeToType(instance.base_type()));
                            newType.setDataType(array);// asigna el vector
                        } else if (instance.isTypePatternArms() == null && instance.identifier() == null) {
                            newType = new NamedTypeReference(new Name(instance.base_type().getText()));
                        } else {
                            newType = isTypeContextToExpression(instance);
                        }
                        newInstance.setRightOperand(newType);
                    }
                    newExpression = newInstance;
                    newInstance.addSubTag("isType");
                    newExpression.setTag("instanceof");
                } else if (nextOperand instanceof Type_Context) {
                    InstanceOfExpression newInstance = new InstanceOfExpression();
                    newInstance.setLeftOperand(newExpression);
                    newInstance.setRightOperand(TypeReferencetoType((Type_Context) nextOperand));
                    newExpression = newInstance;
                    newExpression.setTag("asType");
                    newExpression.addSubTag("asType");
                }
                newExpression.addSubTag(String.valueOf(currentOperand));
                currentOperand += 2;
            }
        }
        try{
            newExpression.setLocationInfo(getSourceFromContext(relationalExpression));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }


    default NamedTypeReference isTypeContextToExpression(IsTypeContext context) {
        IsType newExpression = new IsType();
        if (context.isTypePatternArms() != null) {
            ArrayList<Expression> identifierPatterArm = new ArrayList<>();
            ArrayList<Expression> expressionPatterArm = new ArrayList<>();
            for (IsTypePatternArmContext isTypePatternArmsContext :
                    context.isTypePatternArms().isTypePatternArm()) {
                identifierPatterArm.add(identifierToIdentifierReference(isTypePatternArmsContext.identifier()));
                expressionPatterArm.add(expressionToExpression(isTypePatternArmsContext.expression()));
            }
            newExpression.setIdentifierPatternArm(identifierPatterArm);
            newExpression.setExpressionsPatternArm(expressionPatterArm);
        } else if (context.identifier() != null) {
            newExpression.setIdentifier(identifierToIdentifierReference(context.identifier()));
//            newExpression = newBinary;
//        } else {
//            newExpression = shiftToExpression(operands.get(0), 0);
        }
        newExpression.setLocationInfo(getSourceFromContext(context));
        newExpression.setBaseType(baseTypeToType(context.base_type()));
        newExpression.addSubTag("isType");
        return newExpression;
    }

    /**
     * Maps shift expressions to a Expression in the GAST.
     *
     * @param shiftExpression shift expression from the C# AST.
     * @param currentOperand  value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */

    default Expression shiftToExpression(Shift_expressionContext shiftExpression, int currentOperand) {
        Expression newExpression = null;
        List<Additive_expressionContext> operands = shiftExpression.additive_expression();

        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            List<Additive_expressionContext> list = shiftExpression.additive_expression();
            List<Right_shiftContext> list2 = shiftExpression.right_shift();
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(additiveToExpression(operands.get(currentOperand), 0));

            // Assigns the operator, must be done manually to mantain the operator order
            String expressionString = shiftExpression.getText().replaceFirst(operands.get(0).getText(), "");
            for (int i = 1; i <= currentOperand; i++) {
                expressionString = expressionString.substring(2);
                expressionString = expressionString.replaceFirst(operands.get(i).getText(), "");
            }
            String operator = expressionString.substring(0, 2);
            newBinary.setOperator(OperatorToOperator(operator));

//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(additiveToExpression(operands.get(currentOperand + 1), 0));
            } else {
                newBinary.setRightOperand(shiftToExpression(shiftExpression, currentOperand + 1));
            }
            try{
                newExpression.setLocationInfo(getSourceFromContext(shiftExpression));
            }catch (Exception e) {
                //e.printStackTrace();
            }
            newExpression = newBinary;
        } else {
            newExpression = additiveToExpression(operands.get(0), 0);
        }
        return newExpression;
    }

    /**
     * Maps additive expressions to a Expression in the GAST.
     *
     * @param additiveExpression additive expression from the C# AST.
     * @param currentOperand     value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */

    default Expression additiveToExpression(Additive_expressionContext additiveExpression, int currentOperand) {
        Expression newExpression = null;
        List<Multiplicative_expressionContext> operands = additiveExpression.multiplicative_expression();

        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(multiplicativeToExpression(operands.get(currentOperand), 0));

            // Assigns the operator, must be done manually to mantain the operator order
            try {
                String expressionString = additiveExpression.getText().replaceFirst(operands.get(0).getText(), "");
                for (int i = 1; i <= currentOperand; i++) {
                    expressionString = expressionString.substring(1);
                    try {
                        expressionString = expressionString.replaceFirst(operands.get(i).getText(), "");
                    } catch (Exception e) {
                        //e.printStackTrace();
                    }
                }
                String operator = expressionString.substring(0, 1);
                newBinary.setOperator(OperatorToOperator(operator));
            } catch (Exception e) {
                //e.printStackTrace();
            }

//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(multiplicativeToExpression(operands.get(currentOperand + 1), 0));
            } else {
                newBinary.setRightOperand(additiveToExpression(additiveExpression, currentOperand + 1));
            }
            if (newExpression != null) {
                newExpression.setLocationInfo(getSourceFromContext(additiveExpression));
            }
            newExpression = newBinary;
        } else {
            newExpression = multiplicativeToExpression(operands.get(0), 0);
        }
        return newExpression;
    }

    /**
     * Maps multiplicative expressions to a Expression in the GAST.
     *
     * @param multiplicativeExpression multiplicative expression from the C# AST.
     * @param currentOperand           value of the current operand for recursive mapping
     * @return Expression object with all values mapped into it.
     */
    default Expression multiplicativeToExpression(Multiplicative_expressionContext multiplicativeExpression, int currentOperand) {
        Expression newExpression = null;
        List<Switch_expressionContext> operands = multiplicativeExpression.switch_expression();

        if (operands.size() > 1) {
            // If it really is this type of expression, it has more than one expression inside
            BinaryExpression newBinary = new BinaryExpression();
            newBinary.setLeftOperand(switchToExpression(operands.get(currentOperand)));

            // Assigns the operator, must be done manually to mantain the operator order
            String expressionString = multiplicativeExpression.getText().replaceFirst(operands.get(0).getText(), "");
            for (int i = 1; i <= currentOperand; i++) {
                expressionString = expressionString.substring(1);
                expressionString = expressionString.replaceFirst(operands.get(i).getText(), "");
            }
            String operator = expressionString.substring(0, 1);
            newBinary.setOperator(OperatorToOperator(operator));

//            newBinary.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            if (currentOperand == operands.size() - 2) {
                newBinary.setRightOperand(switchToExpression(operands.get(currentOperand + 1)));
            } else {
                newBinary.setRightOperand(multiplicativeToExpression(multiplicativeExpression, currentOperand + 1));
            }
            newExpression = newBinary;
            newExpression.setLocationInfo(getSourceFromContext(multiplicativeExpression));
        } else {
            newExpression = switchToExpression(operands.get(0));
        }
        return newExpression;
    }
//WORKING ON

    /**
     * Maps switch expressions to a Expression in the GAST.
     *
     * @param switch_expression switch expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */
    default Expression switchToExpression(Switch_expressionContext switch_expression) {
        Expression newExpression = null;

        // Switch expresion
        if (switch_expression.switch_expression_arms() != null) {
            SwitchExpression newSwitchExpression = new SwitchExpression();
            newSwitchExpression.setRangeExpression(rangeToExpression(switch_expression.range_expression()));
            newSwitchExpression.setSwitchArmExpressions(switchArmsToExpression(switch_expression.switch_expression_arms()));
            newExpression = newSwitchExpression;
            newExpression.setTag("SwitchExpression");

            // Not switch expression
        } else {
            newExpression = rangeToExpression(switch_expression.range_expression());
        }
        try {
            newExpression.setLocationInfo(getSourceFromContext(switch_expression));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }

    /**
     * Maps switch expressions arms to a Expression in the GAST. Takes into account if its throwable or just has a expression or case_guard
     *
     * @param switch_expression_arms switch expression arms from the C# AST.
     * @return ArrayList of SwitchArmExpresion with all values mapped into it.
     */

    default ArrayList<SwitchArmExpression> switchArmsToExpression(Switch_expression_armsContext switch_expression_arms) {
        ArrayList<SwitchArmExpression> expressionArrayList = new ArrayList<>();
        for (Switch_expression_armContext context : switch_expression_arms.switch_expression_arm()) {
            SwitchArmExpression expression = new SwitchArmExpression();
            if (context.case_guard() != null) {
                expression.setCaseArrow(expressionToExpression(context.case_guard().expression()));
            }
            if (context.throwable_expression() != null) {
                expression.setThrowableExpression(throwableToExpression(context.throwable_expression()));
            }
            if (context.expression() != null) {
                expression.setExpression(expressionToExpression(context.expression()));
            }
            expression.setTag("SwitchArmExpresion");
            expression.setLocationInfo(getSourceFromContext(context));
            expressionArrayList.add(expression);
        }
        return expressionArrayList;
    }

    /**
     * Maps range expressions to a Expression in the GAST. If the RangeExpression only has one expression it is
     * assign to the from expression and the To remains null
     *
     * @param range_expression range expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression rangeToExpression(Range_expressionContext range_expression) {
        Expression newExpression = null;

        // Range expression
        if (range_expression.OP_RANGE() != null) {
            RangeExpression newRangeExpression = new RangeExpression();
            newRangeExpression.setFromExpression(unaryToExpression(range_expression.unary_expression(0)));
            newRangeExpression.setToExpression(unaryToExpression(range_expression.unary_expression(1)));
            newExpression = newRangeExpression;
            newExpression.setTag("RangeExpression");
            // Not range expression
        } else {
            newExpression = unaryToExpression(range_expression.unary_expression(0));
        }
        try{
            newExpression.setLocationInfo(getSourceFromContext(range_expression));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }

    /**
     * Maps unary expressions to a Expression in the GAST.
     *
     * @param unary_expression unary expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression unaryToExpression(Unary_expressionContext unary_expression) {
        Expression newExpression = null;
        if (unary_expression.primary_expression() != null) {
            newExpression = primaryExpressionToExpression(unary_expression.primary_expression());
        } else {
            Unary_expressionContext sub_unary_expression = unary_expression.unary_expression();
            if (unary_expression.type_() != null) {
                // Cast Expression
                CastExpression newCastExpression = new CastExpression();
                newCastExpression.setCastType(TypeReferencetoType(unary_expression.type_()));
                newCastExpression.setExpression(unaryToExpression(sub_unary_expression));
                newExpression = newCastExpression;
            } else {
                // Prefix Unary Expression
                PrefixUnaryExpression newPreExpression = new PrefixUnaryExpression();
                newPreExpression.setOperator(UnaryExpressionOperatorToPrefixOperator(unary_expression.getText().replaceAll(" " + sub_unary_expression.getText(), "")));
                newPreExpression.setOperand(unaryToExpression(sub_unary_expression));
                newExpression = newPreExpression;
                newExpression.setLocationInfo(getSourceFromContext(unary_expression));
            }
        }
        try{
            newExpression.setLocationInfo(getSourceFromContext(unary_expression));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }

    /**
     * Maps the touple expression into a expression, taking into account all the possible null values
     *
     * @param tupleExpressionContext The context of the expression
     * @return the parsed expression
     */

    default Expression tupleExpressionToExpression(TupleExpressionContext tupleExpressionContext) {
        TupleExpression tupleExpression = new TupleExpression();
        tupleExpression.expressions = new ArrayList<>();
        tupleExpression.identifierReferences = new ArrayList<>();
        tupleExpression.refoutList = new ArrayList<>();
        tupleExpression.types = new ArrayList<>();
        //El vartype me dice si es VAR y no otro tipo en especifico
        tupleExpression.varType = new ArrayList<>();
        for (ArgumentContext argumentContext : tupleExpressionContext.argument()) {
            if (argumentContext.expression() != null) {
                tupleExpression.expressions.add(expressionToExpression(argumentContext.expression()));
            } else {
                tupleExpression.expressions.add(null);
            }
            if (argumentContext.identifier() != null) {
                tupleExpression.identifierReferences.add(identifierToIdentifierReference(argumentContext.identifier()));
            } else {
                tupleExpression.identifierReferences.add(null);
            }
            if (argumentContext.type_() != null) {
                tupleExpression.types.add(TypeReferencetoType(argumentContext.type_()));
            } else {
                tupleExpression.types.add(null);
            }
            if (argumentContext.VAR() != null) {
                tupleExpression.varType.add(true);
            } else {
                tupleExpression.varType.add(false);
            }
            if (argumentContext.IN() != null) {
                tupleExpression.refoutList.add(argumentContext.IN().getText());
            } else if (argumentContext.OUT() != null) {
                tupleExpression.refoutList.add(argumentContext.OUT().getText());

            } else if (argumentContext.REF() != null) {
                tupleExpression.refoutList.add(argumentContext.REF().getText());

            } else {
                tupleExpression.refoutList.add(null);
            }
        }
        tupleExpression.setTag("tuple");
        tupleExpression.setLocationInfo(getSourceFromContext(tupleExpressionContext));
        return tupleExpression;
    }

    /**
     * Maps primary expressions to a Expression in the GAST.
     *
     * @param primary_expression primary expression from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression primaryExpressionToExpression(Primary_expressionContext primary_expression) {
        Expression newExpression = primaryExpressionStartToExpression(primary_expression.primary_expression_start());
        try{
            newExpression.setLocationInfo(getSourceFromContext(primary_expression.primary_expression_start()));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        Expression lastExpression = null;
        int childIndex = 1;

        if (primary_expression.getChild(1) instanceof Member_accessContext &&
                primary_expression.primary_expression_start() instanceof SimpleNameExpressionContext) {

            String identifier = primary_expression.primary_expression_start().getText();
            String lastIdentifier = null; // In case the last name is a method
            childIndex = 2;
            ArrayList<Member_accessContext> member_accessContexts = new ArrayList<>();
            member_accessContexts.add((Member_accessContext) primary_expression.getChild(1));
            while (primary_expression.getChild(childIndex) instanceof Member_accessContext) {
                Member_accessContext memberAccess = (Member_accessContext) primary_expression.getChild(childIndex - 1);
                member_accessContexts.add((Member_accessContext) primary_expression.getChild(childIndex - 1));
                lastIdentifier = identifier;
                identifier += primary_expression.getChild(childIndex - 1).getText();
                childIndex++;
            }

            if (lastIdentifier != null) {
                Member_accessContext member_access = (Member_accessContext) primary_expression.getChild(childIndex - 2);// este es el ante penultimo, por ejemplo serial el segundo last de
                //_________________________________
                MemberAccess memberAccess = (MemberAccess) memberAccessListToAnidatedMemberAccess(member_accessContexts);
                // Create a QualifiedOverData GAST object, the equivalent for the QualifiedName.
                ArrayList<TypeReference> typeReferences = new ArrayList<>();
                if (member_access.type_argument_list() != null) {
                    for (Type_Context type_context : member_access.type_argument_list().type_()
                    ) {
                        typeReferences.add(TypeReferencetoType(type_context));
                    }
                } else {
                    typeReferences = null;
                }
                MemberAccess newAccessMember = new MemberAccess();
                newAccessMember.setLocationInfo(getSourceFromContext(member_access));
                newAccessMember.setIdentifierName(identifierToIdentifierReference(member_access.identifier()));
                newAccessMember.setQualifiedName(memberAccess);
                newAccessMember.setIdentifiers(typeReferences);
                newAccessMember.setQualifiedName(memberAccess);
                lastExpression = newAccessMember;
                lastExpression.setTag("QualifiedName");
            } else {
                lastExpression = newExpression;
            }
            Member_accessContext member_access = (Member_accessContext) primary_expression.getChild(childIndex - 1);
            ArrayList<TypeReference> typeReferences = new ArrayList<>();
            if (member_access.type_argument_list() != null) {
                for (Type_Context type_context : member_access.type_argument_list().type_()
                ) {
                    typeReferences.add(TypeReferencetoType(type_context));
                }
            } else {
                typeReferences = null;
            }
            MemberAccess newAccessMember = new MemberAccess();
            newAccessMember.setIdentifierName(identifierToIdentifierReference(member_access.identifier()));
            newAccessMember.setQualifiedName(lastExpression);
            newAccessMember.setIdentifiers(typeReferences);
            newExpression = newAccessMember;
            newExpression.setLocationInfo(getSourceFromContext(member_access));
            // Set the tag for the expression.
            newExpression.setTag("MemberAccess");

        }

        for (int i = childIndex; i < primary_expression.children.size(); i++) {

            ParseTree child = primary_expression.getChild(i);
            if (child instanceof Bracket_expressionContext) {
                ArrayAccess newArrayAccess = new ArrayAccess();
                newArrayAccess.setArrayName(newExpression);
                newArrayAccess.setIndex(expressionToExpression(primary_expression.bracket_expression(0).indexer_argument(0).expression())); // Revisar
                newExpression = newArrayAccess;
                newExpression.setLocationInfo(getSourceFromContext((Bracket_expressionContext) child));
            } else if (child instanceof Member_accessContext) {
                MemberAccess newAccessMember = new MemberAccess();
                newAccessMember.setIdentifierName(identifierToIdentifierReference(((Member_accessContext) child).identifier()));
                newAccessMember.setQualifiedName(newExpression);
                lastExpression = newExpression;
                newExpression = newAccessMember;
                newExpression.setLocationInfo(getSourceFromContext((Member_accessContext) child));
            } else if (child instanceof Method_invocationContext) {
                Method_invocationContext invocation = (Method_invocationContext) child;
                Name functionName = new Name(primary_expression.getChild(i - 1).getText().replace(".", ""));
                FunctionCallExpression newMethodInvocation = new FunctionCallExpression();
                newMethodInvocation.setFunctionName(functionName);
                newMethodInvocation.setCalledFunction(lastExpression);
                if (invocation.argument_list() != null) {
                    List<ArgumentContext> parameters = invocation.argument_list().argument();
                    newMethodInvocation.setActualParams(argumentListToActualParameterList(parameters));
                }
                newExpression = newMethodInvocation;// retorna la llamada con todos los par�metros
                newExpression.setLocationInfo(getSourceFromContext((ParserRuleContext) primary_expression.getChild(i - 1)));

            } else if (child.getText().contains("++") || child.getText().contains("--")) {
                PostfixUnaryExpression newPostExpression = new PostfixUnaryExpression();
                newPostExpression.setOperator(PrimaryExpressionOperatorToPostfixOperator(child.getText()));
                newPostExpression.setOperand(newExpression);
                newExpression = newPostExpression;
            } else if (child.getText().contains("->")) {
                i += 1;
                PointerAccess pointerAccess = new PointerAccess();
                pointerAccess.setIdentifierName(identifierToIdentifierReference((IdentifierContext) primary_expression.getChild(i)));
                pointerAccess.setQualifiedName(newExpression);
                lastExpression = newExpression;
                newExpression = pointerAccess;
                newExpression.setTag("pointerAccess");
            }
        }
        return newExpression;
    }

    /**
     * Maps the member acces inside each other to maintain an order
     *
     * @param member_accessContextArrayList
     * @return
     */

    default Expression memberAccessListToAnidatedMemberAccess(ArrayList<Member_accessContext> member_accessContextArrayList) {
        MemberAccess memberAccess = null;
        int len = member_accessContextArrayList.size();

        for (int i = 0; i < len - 1; i++) {
            Member_accessContext memberAccessContext = member_accessContextArrayList.get(i);
            ArrayList<TypeReference> typeReferences = null;
            if (memberAccessContext.type_argument_list() != null) {
                typeReferences = new ArrayList<>();
                for (Type_Context type_context : memberAccessContext.type_argument_list().type_()
                ) {
                    typeReferences.add(TypeReferencetoType(type_context));
                }
            }
            MemberAccess newAccessMember = new MemberAccess();
            newAccessMember.setIdentifierName(identifierToIdentifierReference(memberAccessContext.identifier()));
            newAccessMember.setQualifiedName(memberAccess);
            newAccessMember.setIdentifiers(typeReferences);
            memberAccess = newAccessMember;
            memberAccess.addSubTag("MemberAccessDebug");
            memberAccess.setLocationInfo(getSourceFromContext(memberAccessContext));
        }
        return memberAccess;

    }

    /**
     * Maps primary expression starts to a Expression in the GAST.
     *
     * @param expression primary expression start from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression primaryExpressionStartToExpression(Primary_expression_startContext expression) {
        Expression newExpression = null;

        //Literal expression
        if (expression instanceof LiteralExpressionContext) {
            LiteralContext literal = ((LiteralExpressionContext) expression).literal();
            if (literal.boolean_literal() != null) {
                newExpression = booleantoboolean(literal.boolean_literal().getText());
            } else if (literal.string_literal() != null) {
                newExpression = stringtostring(literal.string_literal().getText());
            } else if (literal.INTEGER_LITERAL() != null) {
                newExpression = integertointeger(literal.INTEGER_LITERAL().toString());
            } else if (literal.CHARACTER_LITERAL() != null) {
                newExpression = chartochar(literal.CHARACTER_LITERAL().toString());
            } else if (literal.NULL() != null) {
                newExpression = nulltonull(literal.NULL().toString());
            }
            try{
                newExpression.setLocationInfo(getSourceFromContext(literal));
            } catch (Exception e) {
                //e.printStackTrace();
            }
        } else if (expression instanceof SimpleNameExpressionContext) {
            // Cast the expression to a SimpleNameExpressionContext.
            SimpleNameExpressionContext simpleName = (SimpleNameExpressionContext) expression;
            // Map the SimpleName and assign it to the newExpression variable.
            newExpression = identifierToIdentifierReference(simpleName.identifier());
        } else if (expression instanceof ParenthesisExpressionsContext) {
            ParenthesisExpressionsContext parenthesisExpressionsContext = (ParenthesisExpressionsContext) expression;
            ParenthesizedExpression parenthesizedExpression = new ParenthesizedExpression();
            parenthesizedExpression.setInternalExpression(expressionToExpression(parenthesisExpressionsContext.expression()));
            newExpression = parenthesizedExpression;
        } else if (expression instanceof MemberAccessExpressionContext) {
            MemberAccessExpressionContext memberAccessExpression = (MemberAccessExpressionContext) expression;
            if (memberAccessExpression.predefined_type() != null) {
                PredefinedMemberAccessExpression predefinedMemberAccessExpression = new PredefinedMemberAccessExpression();
                NamedTypeReference type = new NamedTypeReference();
                type.setTypeName(new Name(memberAccessExpression.predefined_type().getText()));
                predefinedMemberAccessExpression.setNamedTypeReference(type);
                newExpression = predefinedMemberAccessExpression;
            } else if (memberAccessExpression.qualified_alias_member() != null) {
                QualifedMemberAccessExpression qualifedMemberAccessExpression = new QualifedMemberAccessExpression();
                qualifedMemberAccessExpression.setLeftIdentifier(identifierToIdentifierReference(memberAccessExpression.qualified_alias_member().identifier(0)));
                qualifedMemberAccessExpression.setRightIdentifier(identifierToIdentifierReference(memberAccessExpression.qualified_alias_member().identifier(2)));
                //123
                ArrayList<TypeReference> typeReferences = new ArrayList<>();
                if (memberAccessExpression.qualified_alias_member().type_argument_list() != null) {
                    for (Type_Context type_context : memberAccessExpression.qualified_alias_member().type_argument_list().type_()
                    ) {
                        typeReferences.add(TypeReferencetoType(type_context));
                    }
                } else {
                    typeReferences = null;
                }
                qualifedMemberAccessExpression.setTypeReferences(typeReferences);
                newExpression = qualifedMemberAccessExpression;
            }
            //WORKINGON
        } else if (expression instanceof LiteralAccessExpressionContext) {
            newExpression = new LiteralEccessExpression();
            newExpression.setTag("LiteralAccessExpression");

        } else if (expression instanceof ThisReferenceExpressionContext) {
            newExpression = new ThisReferenceExpression();
            newExpression.setTag("ThisReferenceExpression");
        } else if (expression instanceof BaseAccessExpressionContext) {
            BaseAccessExpressionContext baseAccess = (BaseAccessExpressionContext) expression;

            // BASE.identifier type_argument_list?
            if (baseAccess.identifier() != null) {
                SuperMemberAccess newBaseAccess = new SuperMemberAccess();
                newBaseAccess.setFieldName(identifierToIdentifierReference(baseAccess.identifier()));
                newExpression = newBaseAccess;
                newExpression.setTag("superFieldAccess");

                // BASE [ expression_list ]
            } else {

                // Couldn't find code examples for this use case
            }
        } else if (expression instanceof ObjectCreationExpressionContext) {
            ObjectCreationExpressionContext objectCreationExpressionContext = (ObjectCreationExpressionContext) expression;
            // NEW type_ object_creation_expression
            // Creates a new instance of a class
            if (objectCreationExpressionContext.object_creation_expression() != null) {
                Argument_listContext argument_listContext = objectCreationExpressionContext.object_creation_expression().argument_list();
                newExpression = newToNewExpression(objectCreationExpressionContext.type_(),
                        argument_listContext != null ? argument_listContext.argument() : null,
                        objectCreationExpressionContext.object_creation_expression().object_or_collection_initializer(),
                        null);
                newExpression.setTag("InstanceCreation");

                // NEW type_ '[' expression_list ']' rank_specifier* array_initializer?
                // Creates an array of certain length
                // Ex: new string[2];

            } else if (objectCreationExpressionContext.expression_list() != null) {
                if (objectCreationExpressionContext.array_initializer() != null) {
                    // If array initializer exists, expression list only has to match the initializer but is redundant
                    // Only mapping initializer
                    newExpression = arrayInitializerToExpression(objectCreationExpressionContext.array_initializer());
                } else {
                    int dimensions = objectCreationExpressionContext.expression_list().expression().size();

                    ArrayType arrayType = new ArrayType();
                    arrayType.setAmountDimension(dimensions);
                    arrayType.setBaseType(TypeReferencetoType(objectCreationExpressionContext.type_()));

                    MultidimensionalArrayCreation newArray = new MultidimensionalArrayCreation();
                    newArray.setType(arrayType);
                    newArray.setDimension(expressionListToExpressionList(objectCreationExpressionContext.expression_list().expression()));
                    if (objectCreationExpressionContext.rank_specifier() != null) {
                        // Multidimensional jagged array
                        newArray.setJaggedDimensionsAmount(objectCreationExpressionContext.rank_specifier().size());
                        List<java.lang.Integer> dimensionsSizes = new ArrayList<>();
                        for (Rank_specifierContext dimension : objectCreationExpressionContext.rank_specifier()) {
                            dimensionsSizes.add(dimension.getChildCount() - 1);
                        }
                        newArray.setJaggedDimensions(dimensionsSizes);
                    }
                    newExpression = newArray;
                    newExpression.setTag("multidimensionalArrayCreation");
                }

                // NEW type_ rank_specifier+ array_initializer
                // NEW rank_specifier array_initializer
                // Creates an array with initializer
                // Ex: new string[] { "A", "B" };
                // Falta establecer el verdadero conteo de dimensiones
                ArrayType arrayType = new ArrayType();
                arrayType.setBaseType(TypeReferencetoType(objectCreationExpressionContext.type_()));
                arrayType.setAmountDimension(objectCreationExpressionContext.expression_list().expression().size());
                ArrayCreation newArray = new ArrayCreation();
                newArray.setType(arrayType);
                newArray.setDimension(expressionListToExpressionList(objectCreationExpressionContext.expression_list().expression()));
                newExpression = newArray;
            } else if (objectCreationExpressionContext.array_initializer() != null) {
                // mapping only initializer, assuming good code and other info not needed for code generation
                newExpression = arrayInitializerToExpression(objectCreationExpressionContext.array_initializer());
                // TODO
                // Falta el rank specifier para arrays multidimensionales
//                ArrayType arrayType = newArrayToArray(objectCreationExpressionContext.type_(), );
//                ASTMCore.ASTMSyntax.Expression.ArrayCreation newArray = new ASTMCore.ASTMSyntax.Expression.ArrayCreation();
//                newArray.setType(arrayType);
//                newArray.setDimension(expressionListToExpressionList(array.dimensions()));
//                newexpression = newArray;
                // NEW type_ object_or_collection_initializer
                // Ex object init: new Cat{ Name = "Sylvester", Age=8 }
                // Ex collection init: new List<int> { 0, 1, 2, 3}
            } else if (objectCreationExpressionContext.object_or_collection_initializer() != null) {
                Object_or_collection_initializerContext initializer = objectCreationExpressionContext.object_or_collection_initializer();
                newExpression = newToNewExpression(objectCreationExpressionContext.type_(), null, initializer, null);

                // NEW anonymous_object_initializer
                // Ex: new { Age = 10, Name = "Fluffy" };
            } else if (objectCreationExpressionContext.anonymous_object_initializer() != null) {
                List<Member_declaratorContext> member_initializer = objectCreationExpressionContext.anonymous_object_initializer().member_declarator_list().member_declarator();
                newExpression = newToNewExpression(null, null, null, member_initializer);
                newExpression.setTag("AnonymousInstanceCreation");
            }

        } else if (expression instanceof TypeofExpressionContext) {
            TypeofExpressionContext typeofExpressionContext = (TypeofExpressionContext) expression;
            if (typeofExpressionContext.unbound_type_name() != null) {
                UnboundTypeOfExpression unboundTypeOfExpression = new UnboundTypeOfExpression();
                unboundTypeOfExpression.setIdentifierReferenceList(identifierContextListToIdentifierList(typeofExpressionContext.unbound_type_name().identifier()));
                newExpression = unboundTypeOfExpression;
                newExpression.setTag("unboundtypeof");
            } else {
                TypeOfExpression typeOfExpression = new TypeOfExpression();
                typeOfExpression.setTypeReference(TypeReferencetoType(typeofExpressionContext.type_()));
                newExpression = typeOfExpression;
                newExpression.setTag("typeof");
            }
//             TypeofExpressionContext instance = (TypeofExpressionContext) expression;
//             InstanceOfExpression newInstance = new InstanceOfExpression();
//             // Falta conseguir el left operand
// //            newInstance.setLeftOperand(expressionToExpression(instance.));
//             if (instance.type_() != null) {
//                 newInstance.setRightOperand(TypeReferencetoType(instance.type_()));
//             } else if (instance.unbound_type_name() != null) {
//                 // TODO
//             } else if (instance.VOID() != null) {
//                 NamedTypeReference newType;
//                 newType = new NamedTypeReference(new Name("void"));
//                 newInstance.setRightOperand(newType);
//             }
//             newExpression = newInstance;
        } else if (expression instanceof CheckedExpressionContext) {
            CheckedExpressionContext checkedExpression = (CheckedExpressionContext) expression;
            CheckedExpression newChecked = new CheckedExpression();
            newChecked.setInternalExpression(expressionToExpression(checkedExpression.expression()));
            newExpression = newChecked;
            newExpression.setTag("checked");

        } else if (expression instanceof UncheckedExpressionContext) {
            UncheckedExpressionContext uncheckedExpression = (UncheckedExpressionContext) expression;
            UncheckedExpression newUnchecked = new UncheckedExpression();
            newUnchecked.setInternalExpression(expressionToExpression(uncheckedExpression.expression()));
            newExpression = newUnchecked;
            newExpression.setTag("unchecked");

        } else if (expression instanceof DefaultValueExpressionContext) {
            DefaultValueExpressionContext defaultValueExpressionContext = (DefaultValueExpressionContext) expression;
            DefaultValueExpression defaultValueExpression = new DefaultValueExpression();
            defaultValueExpression.setTypeReference(TypeReferencetoType(defaultValueExpressionContext.type_()));
            newExpression = defaultValueExpression;
            newExpression.setTag("defaultvalue");

        } else if (expression instanceof AnonymousMethodExpressionContext) {
            AnonymousMethodExpressionContext anonymousMethodExpressionContext = (AnonymousMethodExpressionContext) expression;
            AnonymousMethodExpression anonymousMethodExpression = new AnonymousMethodExpression();

            if (anonymousMethodExpressionContext.block() != null) {
                anonymousMethodExpression.blockStatement = BlocktoBlockStatement(anonymousMethodExpressionContext.block());
                anonymousMethodExpression.blockStatement.setLocationInfo(getSourceFromContext(anonymousMethodExpressionContext.block()));
            }
            ArrayList<ExplicitAnonymousParameter> explicitAnonymousParameters = explicitAnonymousParameterContextListToAnonymousParameter(anonymousMethodExpressionContext.explicit_anonymous_function_parameter_list());
            newExpression = anonymousMethodExpression;
            newExpression.setTag("anonymousMethod");

        } else if (expression instanceof SizeofExpressionContext) {
            SizeofExpressionContext sizeofExpressionContext = (SizeofExpressionContext) expression;
            SizeofExpression sizeofExpression = new SizeofExpression();
            sizeofExpression.setTypeReference(TypeReferencetoType(sizeofExpressionContext.type_()));
            newExpression = sizeofExpression;
            newExpression.setTag("sizeof");

        } else if (expression instanceof NameofExpressionContext) {
            NameofExpressionContext nameofExpressionContext = (NameofExpressionContext) expression;
            NameOfExpression nameOfExpression = new NameOfExpression();
            nameOfExpression.setIdentifierReferenceList(identifierContextListToIdentifierList(nameofExpressionContext.identifier()));
            newExpression = nameOfExpression;
            newExpression.setTag("nameof");

        }
        try {
            logMapping(expression, newExpression);
            newExpression.setLocationInfo(getSourceFromContext(expression));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }


    default Expression memberInitializerToExpression(Member_initializerContext memberInitializer) {
        Expression newExpression = null;
        MemberInitializer newMemberInitializer = new MemberInitializer();
        if (memberInitializer.identifier() != null) {
            newMemberInitializer.setIdentifier(IdentifierContextToName(memberInitializer.identifier()));
        } else {
            newMemberInitializer.setIndex(expressionToExpression(memberInitializer.expression()));
        }

        if (memberInitializer.initializer_value().expression() != null) {
            newMemberInitializer.setValue(expressionToExpression(memberInitializer.initializer_value().expression()));
        } else if (memberInitializer.initializer_value().object_or_collection_initializer() != null) {
            newMemberInitializer.setValue(newToNewExpression(null, null, memberInitializer.initializer_value().object_or_collection_initializer(), null));
        }

        try {
            newExpression = newMemberInitializer;
            newExpression.setTag("memberInitializer");
            newExpression.setLocationInfo(getSourceFromContext(memberInitializer));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }


    default Expression memberDeclaratorToExpression(Member_declaratorContext memberDeclarator) {
        Expression newExpression = null;
        if (memberDeclarator.primary_expression() != null) {
            newExpression = expressionToExpression(memberDeclarator.expression());
        } else {
            MemberInitializer newMemberInitializer = new MemberInitializer();
            newMemberInitializer.setIdentifier(IdentifierContextToName(memberDeclarator.identifier()));
            newMemberInitializer.setValue(expressionToExpression(memberDeclarator.expression()));
            newExpression = newMemberInitializer;
            newExpression.setLocationInfo(getSourceFromContext(memberDeclarator));
            newExpression.setTag("memberInitializer");
        }

        return newExpression;
    }

    //
    default Expression newToNewExpression(Type_Context type, List<ArgumentContext> arguments, Object_or_collection_initializerContext initializer, List<Member_declaratorContext> anonymous_member_declarators) {
        Expression newExpression = null;

        // Collection initializer
        if (initializer != null && initializer.collection_initializer() != null) {
            newExpression = collectionInitializerToExpression(initializer.collection_initializer());
            // ObjectCreationExpression, Object initializer or Anonymous object initializer
        } else {
            NewExpression newObject = new NewExpression();
            //newObject.setNewType(typeToBaseType(type)); TODO fix after merging with Statements branch
            newObject.setActualParams(argumentListToActualParameterList(arguments));

            ArrayList<Expression> initializerList = null;
            // ObjectCreationExpression or Object initializer
            if (initializer != null && initializer.object_initializer() != null) {
                List<Member_initializerContext> member_initializer = initializer.object_initializer().member_initializer_list().member_initializer();
                initializerList = memberInitializerListToExpressionList(member_initializer);

                // Anonymous object initializer
            } else if (anonymous_member_declarators != null) {
                initializerList = memberDeclaratorListToExpressionList(anonymous_member_declarators);
            }
            ObjectInitializer objectInitializer = new ObjectInitializer();
            objectInitializer.setMember_initializers(initializerList);
            newObject.setInitializer(objectInitializer);

            newExpression = newObject;
            newExpression.setTag("InstanceCreation");
        }
        return newExpression;
    }

    /**
     * Maps the Explicit_anonymous_function_parameter_listContext into a new list of ExplicitAnonymousParameter
     *
     * @param parameterList
     * @return
     */

    default ArrayList<ExplicitAnonymousParameter> explicitAnonymousParameterContextListToAnonymousParameter(Explicit_anonymous_function_parameter_listContext parameterList) {
        ArrayList<ExplicitAnonymousParameter> explicitAnonymousParameters = new ArrayList<>();
        for (Explicit_anonymous_function_parameterContext context : parameterList.explicit_anonymous_function_parameter()) {
            ExplicitAnonymousParameter parameter = new ExplicitAnonymousParameter();
            parameter.setLocationInfo(getSourceFromContext(context));
            if (context.identifier() != null) {
                parameter.setIdentifierReference(identifierToIdentifierReference(context.identifier()));
            } else {
                parameter.setIdentifierReference(null);
            }
            if (context.type_() != null) {
                parameter.setType(TypeReferencetoType(context.type_()));
            } else {
                parameter.setType(null);
            }
            if (context.IN() != null) {
                parameter.setRefout(context.IN().getText());
            } else if (context.OUT() != null) {
                parameter.setRefout(context.OUT().getText());
            } else if (context.REF() != null) {
                parameter.setRefout(context.OUT().getText());

            } else {
                parameter.setRefout((null));
            }
            explicitAnonymousParameters.add(parameter);
        }
        return explicitAnonymousParameters;
    }


    default List<IdentifierReference> identifierContextListToIdentifierList(List<IdentifierContext> identifierContextList) {
        ArrayList<IdentifierReference> identifierReferenceList = new ArrayList<>();
        for (IdentifierContext context : identifierContextList) {
            identifierReferenceList.add(identifierToIdentifierReference(context));
        }
        return identifierReferenceList;
    }

    /**
     * Maps a local variable initializer to a Expression in the GAST.
     *
     * @param variable local variable initializer from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression localVariableInitializerToExpression(Local_variable_initializerContext variable) {
        if (variable.array_initializer() != null) {
            return arrayInitializerToExpression(variable.array_initializer());
        } else if (variable.expression() != null) {
            return expressionToExpression(variable.expression());
        }
        return null;
    }

    /**
     * Maps a fixed pointer initializer to a Expression in the GAST.
     *
     * @param variable fixed pointer initializer from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression fixedPointerInitializerToExpression(Fixed_pointer_initializerContext variable) {
        if (variable.stackalloc_initializer() != null) {
            return stackallocToExpression(variable.stackalloc_initializer());
        } else {
            // & Prefix unary expression
            Expression newExpression;
            PrefixUnaryExpression newPreExpression = new PrefixUnaryExpression();
            newPreExpression.setOperator(UnaryExpressionOperatorToPrefixOperator("&"));
            newPreExpression.setOperand(expressionToExpression(variable.expression()));
            newExpression = newPreExpression;
            newExpression.setTag("prefix");
            newExpression.setLocationInfo(getSourceFromContext(variable));
            return newExpression;
        }
    }

    /**
     * Maps an array initializer to a Expression in the GAST.
     *
     * @param arrayInitializer array initializer from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression arrayInitializerToExpression(Array_initializerContext arrayInitializer) {
        Expression newExpression;
        CollectionExpression collectionExpression = new CollectionExpression();
        List<Expression> definition = variableInitializerListToExpressionList(arrayInitializer.variable_initializer());
        collectionExpression.setExpressionList(definition);
        newExpression = collectionExpression;
        try{
            newExpression.setLocationInfo(getSourceFromContext(arrayInitializer));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }

    /**
     * Maps a collection initializer to a Expression in the GAST.
     *
     * @param collectionInitializer collection initializer from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression collectionInitializerToExpression(Collection_initializerContext collectionInitializer) {
        Expression newExpression;
        CollectionExpression collectionExpression = new CollectionExpression();// objeto al que se quiere llegar
        List<Expression> definition = elementInitializerListToExpressionList(collectionInitializer.element_initializer());
        collectionExpression.setExpressionList(definition);
        newExpression = collectionExpression;
        try{
            newExpression.setLocationInfo(getSourceFromContext(collectionInitializer));
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return newExpression;
    }

    /**
     * Maps a variable initializer to a Expression in the GAST.
     *
     * @param variableInitializer variable initializer from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression variableInitializerToExpression(Variable_initializerContext variableInitializer) {
        Expression newExpression = null;
        if (variableInitializer.array_initializer() != null) {
            newExpression = arrayInitializerToExpression(variableInitializer.array_initializer());
        } else {
            newExpression = expressionToExpression(variableInitializer.expression());
        }
        newExpression.setLocationInfo(getSourceFromContext(variableInitializer));
        return newExpression;
    }

    /**
     * Maps an element initializer to a Expression in the GAST.
     *
     * @param elementInitializer element initializer from the C# AST.
     * @return Expression object with all values mapped into it.
     */

    default Expression elementInitializerToExpression(Element_initializerContext elementInitializer) {
        if (elementInitializer.non_assignment_expression() != null) {
            return nonAssignmentToExpression(elementInitializer.non_assignment_expression());
        } else if (elementInitializer.expression_list() != null) {
            Expression newExpression;
            CollectionExpression collectionExpression = new CollectionExpression();// objeto al que se quiere llegar
            List<Expression> definition = expressionListToExpressionList(elementInitializer.expression_list().expression());
            collectionExpression.setExpressionList(definition);
            newExpression = collectionExpression;
            newExpression.setLocationInfo(getSourceFromContext(elementInitializer));
            return newExpression;
        }
        return null;
    }

    /**
     * Maps a simple name expression to an IdentifierReference in the GAST.
     *
     * @param identifierContext IdentifierContext expression from the C# AST.
     * @return IdentifierReference with all values mapped into it.
     */

    default IdentifierReference identifierToIdentifierReference(IdentifierContext identifierContext) {
        IdentifierReference newName = new IdentifierReference();
        newName.setIdentifierName(IdentifierContextToName(identifierContext));
        newName.setTag("simpleName");
        newName.setLocationInfo(getSourceFromContext(identifierContext));
        return newName;
    }

    /**
     * Maps a member access to an IdentifierReference in the GAST.
     *
     * @param memberAccess member access from the C# AST.
     * @return IdentifierReference with all values mapped into it.
     */
    default IdentifierReference memberAccessToIdentifierReference(Member_accessContext memberAccess) {
        IdentifierReference newName = new IdentifierReference();
        newName.setIdentifierName(IdentifierContextToName(memberAccess.identifier()));
        return newName;
    }

    /**
     * Maps a statement to a Statement in the GAST.
     *
     * @param statement statement from the C# AST.
     * @return Statement with all values mapped into it.
     */
    default Statement StatementToStatement(StatementContext statement) {
        Statement newStatement = null;

        // Labeled statement
        if (statement.labeled_Statement() != null) {
            Labeled_StatementContext labeledStatement = statement.labeled_Statement();
            LabeledStatement newLabel = new LabeledStatement();
            LabelDefinition target = new LabelDefinition();
            target.setLabelname(IdentifierContextToName(labeledStatement.identifier()));
            newLabel.setLabel(target);
            newLabel.setStatement(StatementToStatement(labeledStatement.statement()));
            newStatement = newLabel;
            newStatement.setLocationInfo(getSourceFromContext(statement.labeled_Statement()));

            // Declaration statement
        } else if (statement.declarationStatement() != null) {
            DeclarationStatementContext declarationStatement = statement.declarationStatement();

            // Local variable declaration
            if (declarationStatement.local_variable_declaration() != null) {
                Local_variable_declarationContext variableDeclaration = declarationStatement.local_variable_declaration();
                VariableDefinition newVariable = new VariableDefinition();

                // Local variable type
                if (variableDeclaration.local_variable_type() != null) {

                    // VAR type
                    if (variableDeclaration.local_variable_type().VAR() != null) {
                        NamedTypeReference newType = new NamedTypeReference(new Name("var"));
                        newType.setDataType(new Var());
                        newType.setTag("typeName");
                        newVariable.setDefinitionType(newType);
                        // Map other type
                    } else if (variableDeclaration.local_variable_type().type_() != null) {
                        newVariable.setDefinitionType(TypeReferencetoType(variableDeclaration.local_variable_type().type_()));
                    }
                    ArrayList<Modifiers> modifiers = new ArrayList<>();
                    switch (variableDeclaration.getChild(0).getText().toLowerCase()) {
                        case "using":
                            // TODO
                            System.out.println("USING keyword in variable declaration");
                            break;
                        case "ref":
                            modifiers.add(new RefModifier());
                            break;
                        case "ref readonly":
                            modifiers.add(new RefModifier());
                            modifiers.add(new FinalModifier());
                            break;
                    }
                    newVariable.setModifiers(modifiers);
                    newVariable.setFragments(localFragmentsToFragments(variableDeclaration.local_variable_declarator()));
                    newVariable.setLocationInfo(getSourceFromContext(variableDeclaration));
                    // Pointer type
                } else if (variableDeclaration.pointer_type() != null) {
                    // TODO not sure if this type of pointer is possible, only found fixed buffer in structs example: fixed char name[30];
                    System.out.println("Local variable declaration Fixed pointer");
                }
                DeclarationOrDefinitionStatement definition = new DeclarationOrDefinitionStatement();
                definition.setDeclOrDefn(newVariable);
                newStatement = definition;

                // Local constante declaration
            } else if (declarationStatement.local_constant_declaration() != null) {
                Local_constant_declarationContext constant = declarationStatement.local_constant_declaration();
                VariableDefinition newVariable = new VariableDefinition();
                if (constant.type_() != null) {
                    newVariable.setDefinitionType(TypeReferencetoType(constant.type_()));
                }
                ArrayList<Modifiers> modifiers = new ArrayList<>();
                modifiers.add(new FinalModifier());
                newVariable.setModifiers(modifiers); // <== Modificador Constant
                newVariable.setFragments(constFragmentsToFragments(constant.constant_declarators().constant_declarator()));
                DeclarationOrDefinitionStatement definition = new DeclarationOrDefinitionStatement();
                definition.setDeclOrDefn(newVariable);
                newStatement = definition;
            } else if (declarationStatement.local_function_declaration() != null) {
//                TODO
            }

            // Embedded statement
        } else if (statement.embedded_statement() != null) {
            Embedded_statementContext embeddedStatement = statement.embedded_statement();
            newStatement = embeddedStatementToStatement(embeddedStatement);
            newStatement.setLocationInfo(getSourceFromContext(embeddedStatement));
        }
        logMapping(statement, newStatement);
        return newStatement;
    }

    /**
     * Maps an embedded statement to a Statement in the GAST.
     *
     * @param embeddedStatement embedded statement from the C# AST.
     * @return Statement with all values mapped into it.
     */

    default Statement embeddedStatementToStatement(Embedded_statementContext embeddedStatement) {
        Statement newStatement = null;

        // Block statement
        if (embeddedStatement.block() != null) {
            newStatement = BlocktoBlockStatement(embeddedStatement.block());
            newStatement.setLocationInfo(getSourceFromContext(embeddedStatement.block()));

            // Simple embedded statement
        } else if (embeddedStatement.simple_embedded_statement() != null) {
            Simple_embedded_statementContext simple_embedded_statement = embeddedStatement.simple_embedded_statement();

            // The Empty statement
            if (simple_embedded_statement instanceof TheEmptyStatementContext) {
                newStatement = new EmptyStatement();
            } else if (simple_embedded_statement instanceof ExpressionStatementContext) {
                ExpressionStatementContext expressionStatement = (ExpressionStatementContext) simple_embedded_statement;
                ExpressionStatement newExpressionStatement = new ExpressionStatement();
                newExpressionStatement.setExpression(expressionToExpression(expressionStatement.expression()));
                newStatement = newExpressionStatement;
            } else if (simple_embedded_statement instanceof IfStatementContext) {
                IfStatementContext ifStatement = (IfStatementContext) simple_embedded_statement;
                IfStatement newif = new IfStatement();
                BlockStatement blockStatement = BlocktoBlockStatement(ifStatement.if_body(0).block());
                try{
                    blockStatement.setLocationInfo(getSourceFromContext(ifStatement.if_body(0).block()));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                newif.setThenBody(blockStatement);
                if (ifStatement.if_body().size() > 1) {
                    blockStatement = BlocktoBlockStatement(ifStatement.if_body(1).block());
                    try{
                        blockStatement.setLocationInfo(getSourceFromContext(ifStatement.if_body(1).block()));
                    }catch (Exception e) {
                        //e.printStackTrace();
                    }
                    newif.setElseBody(blockStatement);
                }
                newif.setCondition(expressionToExpression(ifStatement.expression()));
                newStatement = newif;
            } else if (simple_embedded_statement instanceof SwitchStatementContext) {
                SwitchStatementContext switchStatement = (SwitchStatementContext) simple_embedded_statement;
                SwitchStatement newSwitch = new SwitchStatement();
                newSwitch.setCases(ListSwitchToListSwitch(switchStatement.switch_section()));
                newSwitch.setSwitchExpression(expressionToExpression(switchStatement.expression()));
                newStatement = newSwitch;
            } else if (simple_embedded_statement instanceof WhileStatementContext) {
                WhileStatementContext whileStatement = (WhileStatementContext) simple_embedded_statement;
                WhileStatement newWhile = new WhileStatement();
                newWhile.setBody(embeddedStatementToStatement(whileStatement.embedded_statement()));
                newWhile.setCondition(expressionToExpression(whileStatement.expression()));
                newStatement = newWhile;
            } else if (simple_embedded_statement instanceof DoStatementContext) {
                DoStatementContext doStatement = (DoStatementContext) simple_embedded_statement;
                DoWhileStatement newDo = new DoWhileStatement();
                newDo.setBody(embeddedStatementToStatement(doStatement.embedded_statement()));
                newDo.setCondition(expressionToExpression(doStatement.expression()));
                newStatement = newDo;
            } else if (simple_embedded_statement instanceof ForStatementContext) {
                ForStatementContext forStatement = (ForStatementContext) simple_embedded_statement;
                ForCheckAfterStatement newFor = new ForCheckAfterStatement();
                newFor.setBody(embeddedStatementToStatement(forStatement.embedded_statement()));
                newFor.setCondition(expressionToExpression(forStatement.expression()));
                newFor.setInitBody(new ArrayList<>(
                        expressionListToExpressionList((forStatement.for_initializer().expression()))));
                try{
                    newFor.setIterationBody(new ArrayList<>(expressionListToExpressionList(forStatement.for_iterator().expression())));
                } catch (Exception e) {
                    //e.printStackTrace();
                }
                newStatement = newFor;
            } else if (simple_embedded_statement instanceof ForeachStatementContext) {
                ForeachStatementContext forStatement = (ForeachStatementContext) simple_embedded_statement;
                ForIterator newFor = new ForIterator();
                FormalParameterDefinition forStatement1 = forStatementToParameter(forStatement);
                forStatement1.setLocationInfo(getSourceFromContext(forStatement));
                newFor.setParameter(forStatement1);
                newFor.setBody(embeddedStatementToStatement(forStatement.embedded_statement()));
                newFor.setCondition(expressionToExpression(forStatement.expression()));
                newStatement = newFor;
            } else if (simple_embedded_statement instanceof BreakStatementContext) {
                BreakStatement newBreak = new BreakStatement();
                LabelAccess target = new LabelAccess();
                target.setLabelName(new Name("break"));
                newBreak.setTarget(target);
                newStatement = newBreak;
            } else if (simple_embedded_statement instanceof ContinueStatementContext) {
                ContinueStatement newBreak = new ContinueStatement();
                LabelAccess target = new LabelAccess();
                target.setLabelName(new Name("continue"));
                newBreak.setTarget(target);
                newStatement = newBreak;
            } else if (simple_embedded_statement instanceof GotoStatementContext) {
                GotoStatementContext gotoStatement = (GotoStatementContext) simple_embedded_statement;

                // Go to label statement
                if (gotoStatement.identifier() != null) {
                    LabelGoToStatement labelGoToStatement = new LabelGoToStatement();
                    labelGoToStatement.setIdentifier(IdentifierContextToName(gotoStatement.identifier()));
                    newStatement = labelGoToStatement;

                    // Go to case expression / default statement
                } else {
                    CaseGoToStatement caseGoToStatement = new CaseGoToStatement();
                    if (gotoStatement.expression() != null) {
                        caseGoToStatement.setExpression(expressionToExpression(gotoStatement.expression()));
                    } else {
                        caseGoToStatement.setExpression(null);
                    }
                    newStatement = caseGoToStatement;
                }
                newStatement.setTag("goto");

                // Return statement
            } else if (simple_embedded_statement instanceof ReturnStatementContext) {
                ReturnStatementContext returnStatement = (ReturnStatementContext) simple_embedded_statement;
                ReturnStatement newReturn = new ReturnStatement();
                if (returnStatement.expression() != null) {
                    newReturn.setReturnValue(expressionToExpression(returnStatement.expression()));
                }
                newStatement = newReturn;
            } else if (simple_embedded_statement instanceof ThrowStatementContext) {
                ThrowStatementContext throwStatement = (ThrowStatementContext) simple_embedded_statement;
                ThrowStatement newThrow = new ThrowStatement();
                if (throwStatement.expression() != null) {
                    newThrow.setException(expressionToExpression(throwStatement.expression()));
                }
                newStatement = newThrow;
            } else if (simple_embedded_statement instanceof TryStatementContext) {
                TryStatementContext tryStatement = (TryStatementContext) simple_embedded_statement;
                TryStatement newTry = new TryStatement();
                BlockStatement blockStatement = BlocktoBlockStatement(tryStatement.block());
                blockStatement.setLocationInfo(getSourceFromContext(tryStatement.block()));
                newTry.setBody(blockStatement);
                if (tryStatement.finally_clause() != null) {
                    blockStatement = BlocktoBlockStatement(tryStatement.finally_clause().block());
                    blockStatement.setLocationInfo(getSourceFromContext(tryStatement.finally_clause().block()));
                    newTry.setFinalStatement(blockStatement);
                }
                if (tryStatement.catch_clauses() != null) {
                    List<CatchBlock> catchBlocks = new ArrayList<>();
                    if (tryStatement.catch_clauses().general_catch_clause() != null) {
                        CatchBlock newCatch = new CatchBlock();
                        blockStatement = BlocktoBlockStatement(tryStatement.catch_clauses().general_catch_clause().block());
                        blockStatement.setLocationInfo(getSourceFromContext(tryStatement.catch_clauses().general_catch_clause().block()));
                        newCatch.setBody(blockStatement);
                        catchBlocks.add(newCatch);
                    }
                    catchBlocks.addAll(ListCatchToListCatch(tryStatement.catch_clauses().specific_catch_clause()));
                    newTry.setCatchBlocks(catchBlocks);
                }
                newStatement = newTry;
            } else if (simple_embedded_statement instanceof CheckedStatementContext) {
                CheckedStatementContext checkedStatement = (CheckedStatementContext) simple_embedded_statement;
                CheckedStatement newCheckedStatement = new CheckedStatement();
                if (checkedStatement.block() != null) {
                    BlockStatement blockStatement = BlocktoBlockStatement(checkedStatement.block());
                    blockStatement.setLocationInfo(getSourceFromContext(checkedStatement.block()));
                    newCheckedStatement.setBody(blockStatement);
                }
                newStatement = newCheckedStatement;
                newStatement.setTag("checked");

                // Unchecked statement
            } else if (simple_embedded_statement instanceof UncheckedStatementContext) {
                UncheckedStatementContext uncheckedStatement = (UncheckedStatementContext) simple_embedded_statement;
                UncheckedStatement newUnCheckedStatement = new UncheckedStatement();
                if (uncheckedStatement.block() != null) {
                    BlockStatement blockStatement = BlocktoBlockStatement(uncheckedStatement.block());
                    blockStatement.setLocationInfo(getSourceFromContext(uncheckedStatement.block()));
                    newUnCheckedStatement.setBody(blockStatement);
                }
                newStatement = newUnCheckedStatement;
                newStatement.setTag("unchecked");

                // Lock statement
            } else if (simple_embedded_statement instanceof LockStatementContext) {
                LockStatementContext lockStatement = (LockStatementContext) simple_embedded_statement;
                SynchronizedStatement newSync = new SynchronizedStatement();
                newSync.setBody(embeddedStatementToStatement(lockStatement.embedded_statement()));
                newSync.setExpression(expressionToExpression(lockStatement.expression()));
                newStatement = newSync;
            } else if (simple_embedded_statement instanceof UsingStatementContext) {
                UsingStatementContext usingStatement = (UsingStatementContext) simple_embedded_statement;
                UsingStatement newUsingStatement = new UsingStatement();
                newUsingStatement.setBody(embeddedStatementToStatement(usingStatement.embedded_statement()));
                if (usingStatement.resource_acquisition().expression() != null) {
                    newUsingStatement.setExpression(expressionToExpression(usingStatement.resource_acquisition().expression()));
                } else {
                    newUsingStatement.setLocal_variable(localFragmentsToFragments(usingStatement.resource_acquisition().local_variable_declaration().local_variable_declarator()));
                }
                newStatement = newUsingStatement;
                newStatement.setTag("using");

                // Yield statement
            } else if (simple_embedded_statement instanceof YieldStatementContext) {
                YieldStatementContext yieldStatement = (YieldStatementContext) simple_embedded_statement;

                // Yield return
                if (yieldStatement.expression() != null) {
                    YieldReturnExpressionStatement newYieldStatement = new YieldReturnExpressionStatement();
                    newYieldStatement.setExpression(expressionToExpression(yieldStatement.expression()));
                    newStatement = newYieldStatement;
                    newStatement.setTag("yieldReturn");

                    // Yield break
                } else {
                    YieldBreakStatement newYieldStatement = new YieldBreakStatement();
                    newStatement = newYieldStatement;
                    newStatement.setTag("yieldBreak");
                }

                // Unsafe statement
            } else if (simple_embedded_statement instanceof UnsafeStatementContext) {
                UnsafeStatementContext unsafeStatement = (UnsafeStatementContext) simple_embedded_statement;
                UnsafeStatement newUnsafeStatement = new UnsafeStatement();
                BlockStatement blockStatement = BlocktoBlockStatement(unsafeStatement.block());
                blockStatement.setLocationInfo(getSourceFromContext(unsafeStatement.block()));
                newUnsafeStatement.setBody(blockStatement);
                newStatement = newUnsafeStatement;
                newStatement.setTag("unsafe");

                // Fixed statement
            } else if (simple_embedded_statement instanceof FixedStatementContext) {
                FixedStatementContext fixedStatement = (FixedStatementContext) simple_embedded_statement;
                FixedStatement newFixed = new FixedStatement();
                DefintionObject defintionObject = pointerToVariableDeclaration(fixedStatement.pointer_type(), fixedStatement.fixed_pointer_declarators());
                defintionObject.setLocationInfo(getSourceFromContext(fixedStatement.fixed_pointer_declarators()));
                newFixed.setPointerDeclaration(defintionObject);
                newFixed.setBody(embeddedStatementToStatement(fixedStatement.embedded_statement()));
                newFixed.setTag("fixed");
                newStatement = newFixed;

            }
            newStatement.setLocationInfo(getSourceFromContext(simple_embedded_statement));
        }
        logMapping(embeddedStatement, newStatement);
        return newStatement;
    }

    /**
     * Maps a pointer to a DefintionObject in the GAST.
     *
     * @param type        pointer type from the C# AST.
     * @param declarators fixed pointer declarators from the C# AST.
     * @return DefintionObject with all values mapped into it.
     */
    default DefintionObject pointerToVariableDeclaration(Pointer_typeContext type, Fixed_pointer_declaratorsContext declarators) {
        VariableDefinition newVariable = new VariableDefinition();
        newVariable.setDefinitionType(pointerTypeToType(type));
        newVariable.setFragments(pointerFragmentsToFragments(declarators.fixed_pointer_declarator()));
        newVariable.setTag("pointerDeclaration");
        SourceLocation locationBeginning = getSourceFromContext(type);
        SourceLocation locationEnd = getSourceFromContext(declarators.fixed_pointer_declarator(declarators.fixed_pointer_declarator().size() - 1));
        SourceLocation location = new SourceLocation();
        location.setStartPosition(locationBeginning.getStartPosition());
        location.setStartLine(locationBeginning.getStartLine());
        location.setEndLine(locationEnd.getEndLine());
        newVariable.setLocationInfo(location);
        return newVariable;
    }


    /**
     * Maps pointer types to a TypeReference in the GAST.
     *
     * @param type pointer type from the C# AST.
     * @return TypeReference object with all values mapped into it.
     */
    default TypeReference pointerTypeToType(Pointer_typeContext type) {
        NamedTypeReference newType;
        // Simple type
        if (type.simple_type() != null) {
            newType = simpleTypeToType(type.simple_type());
            newType.setTypeName(new Name(type.simple_type().getText()));
            newType.setLocationInfo(getSourceFromContext(type.simple_type()));
            // Class type
        } else if (type.class_type() != null) {
            newType = ClassTypeToType(type.class_type());

            // VOID
        } else if (type.VOID() != null) {
            newType = new NamedTypeReference(new Name("void"));
            newType.setDataType(new Void());
            // Other
        } else {
            newType = new NamedTypeReference(new Name(type.getText()));
        }

        if (type.rank_specifier().size() > 0) {
            NamedTypeReference newArrayType = new NamedTypeReference(new Name("Array"));
            ArrayType array = new ArrayType();
            array.setBaseType(newType);
            array.setAmountDimension(type.rank_specifier().size());
            newArrayType.setDataType(array);
            newType = newArrayType;
            SourceLocation location = new SourceLocation();
            location.setEndLine(type.rank_specifier(type.rank_specifier().size() - 1).getStop().getLine());
            location.setStartLine(type.rank_specifier().get(0).getStart().getLine());
            location.setStartPosition(type.rank_specifier().get(0).getStart().getCharPositionInLine());
            newType.setLocationInfo(location);
        }

        newType.setTag("typeName");
//
//		} else if (stament instanceof AssertStatement) {
//			AssertStatement assertStatement = (AssertStatement) stament;
//			ASTMCore.ASTMSyntax.Statement.AssertStatement newAssert = new ASTMCore.ASTMSyntax.Statement.AssertStatement();
//			newAssert.setExpression(ExpresionToExpresion(assertStatement.getExpression()));
//			newAssert.setMessage(ExpresionToExpresion(assertStatement.getMessage()));
//			newStatement = newAssert;
//		}

        PointerType pointerType = new PointerType();
        pointerType.setType(newType);
        NamedTypeReference pointerTypeReference = new NamedTypeReference(new Name("Pointer"));
        pointerTypeReference.setDataType(pointerType);
        pointerTypeReference.setTag("typeName");
        pointerType.setLocationInfo(getSourceFromContext(type));
        return pointerTypeReference;
    }

    /**
     * Gets the source location from a context of a parser rule context
     *
     * @param context context that extracts the value
     * @return the source location with the beginning char position, the beginning line and the end line
     */
    default SourceLocation getSourceFromContext(ParserRuleContext context) {
        SourceLocation sourceLocation = new SourceLocation();
        try {
            sourceLocation.setStartLine(context.getStart().getLine());
            sourceLocation.setStartPosition(context.getStart().getCharPositionInLine() + 1);
            sourceLocation.setEndLine(context.getStop().getLine());
            sourceLocation.setEndPosition(context.getStop().getCharPositionInLine() + context.getStop().getText().length() - 1);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return sourceLocation;
    }

    /**
     * Maps stackalloc initializers to a StackAllocInitializer from the GAST.
     *
     * @param stackalloc_initializer stackalloc initializer from the C# AST.
     * @return StackAllocInitializer object with all values mapped into it.
     */
    default StackAllocInitializer stackallocToExpression(Stackalloc_initializerContext stackalloc_initializer) {
        StackAllocInitializer stackAllocInitializer = new StackAllocInitializer();
        if (stackalloc_initializer.type_() != null) {
            stackAllocInitializer.setDefinitionType(TypeReferencetoType(stackalloc_initializer.type_()));
            ParseTree child = stackalloc_initializer.getChild(3);
            if (child instanceof ExpressionContext) {
                stackAllocInitializer.setDimension(expressionToExpression((ExpressionContext) child));
            }
        } else {
            ParseTree child = stackalloc_initializer.getChild(2);
            if (child instanceof ExpressionContext) {
                stackAllocInitializer.setDimension(expressionToExpression((ExpressionContext) child));
            }
        }
        List<ExpressionContext> expressions = stackalloc_initializer.expression();
        if (stackAllocInitializer.getDimension() != null) {
            expressions.remove(0);
        }
        stackAllocInitializer.setExpressions(expressionListToExpressionList(expressions));
        stackAllocInitializer.setTag("stackalloc");
        stackAllocInitializer.setLocationInfo(getSourceFromContext(stackalloc_initializer));
        return stackAllocInitializer;
    }


    // Isn't this just a method invocation??
//		} else if (stament instanceof SuperConstructorInvocation) {
//			SuperConstructorInvocation invocation = (SuperConstructorInvocation) stament;
//			SuperInvocation newInvocation = new SuperInvocation();
//			newInvocation.setArguments(expressionListToExpressionList(invocation.arguments()));
//			newInvocation.setExpression(ExpresionToExpresion(invocation.getExpression()));
//			// newInvocation.setArgumentsTypes(ListTypeReferencetoListType(invocation.typeArguments()));//
//			newStatement = newInvocation;
//		}


    /**
     * Maps specific catch clauses to a CatchBlock from the GAST.
     *
     * @param catchStatement specific catch clause from the C# AST.
     * @return CatchBlock object with all values mapped into it.
     */
    default CatchBlock CatchToCatch(Specific_catch_clauseContext catchStatement) {
        CatchBlock newCatch = new CatchBlock();
        newCatch.setBody(BlocktoBlockStatement(catchStatement.block()));
//        newCatch.setException();
        newCatch.setLocationInfo(getSourceFromContext(catchStatement));
        return newCatch;
    }

    /**
     * Maps types to a TypeReference from the GAST.
     *
     * @param type type from the C# AST.
     * @return TypeReference object with all values mapped into it.
     */
    default TypeReference TypeReferencetoType(Type_Context type) {
        NamedTypeReference newType;
        if (type.rank_specifier().size() > 0) {
            newType = new NamedTypeReference(new Name("Array"));
            ArrayType array = new ArrayType();
            array.setAmountDimension(type.rank_specifier().size());
            array.setBaseType(baseTypeToType(type.base_type()));
            newType.setDataType(array);// asigna el vector
        } else {
            // TODO Mapping of ? and *
            newType = baseTypeToType(type.base_type());
            for (ParseTree node : type.children
            ) {
                if (node.getText().equals("?") || node.getText().equals("*")) {
                    newType.addSubTag(node.getText());
                }
            }
        }
        newType.setLocationInfo(getSourceFromContext(type));
        return newType;
    }

    /**
     * Maps base types to a NamedTypeReference from the GAST.
     *
     * @param base base type from the C# AST.
     * @return NamedTypeReference object with all values mapped into it.
     */
    default NamedTypeReference baseTypeToType(Base_typeContext base) {
        NamedTypeReference newType;
        // Simple types must be mapped as data type
        if (base.simple_type() != null) {
            newType = simpleTypeToType(base.simple_type());
        } else {
            newType = new NamedTypeReference();
        }
        // Types other than simple types mapped just as names
        // TODO parameterized type
        newType.setTypeName(new Name(base.getText()));
        newType.setTag("typeName");
        return newType;
    }

    /**
     * Maps simple types to a NamedTypeReference from the GAST.
     *
     * @param simpleType simple type from the C# AST.
     * @return NamedTypeReference object with all values mapped into it.
     */
    default NamedTypeReference simpleTypeToType(Simple_typeContext simpleType) {
        NamedTypeReference newType = new NamedTypeReference();
        if (simpleType.numeric_type() != null) {
            Numeric_typeContext numType = simpleType.numeric_type();

            // Integer types
            if (numType.integral_type() != null) {
                Integral_typeContext intType = numType.integral_type();

                // Signed byte
                if (intType.SBYTE() != null) {
                    Byte sbyte = new Byte();
                    sbyte.setIsSigned(true);
                    newType.setDataType(sbyte);

                    // Unsigned byte
                } else if (intType.BYTE() != null) {
                    Byte _byte = new Byte();
                    _byte.setIsSigned(false);
                    newType.setDataType(_byte);

                    // Signed short
                } else if (intType.SHORT() != null) {
                    ShortInteger shortInteger = new ShortInteger();
                    shortInteger.setIsSigned(true);
                    newType.setDataType(shortInteger);

                    // Unsigned short
                } else if (intType.USHORT() != null) {
                    ShortInteger ushort = new ShortInteger();
                    ushort.setIsSigned(false);
                    newType.setDataType(ushort);

                    // Signed integer
                } else if (intType.INT() != null) {
                    Integer integer = new Integer();
                    integer.setIsSigned(true);
                    newType.setDataType(integer);

                    // Unsigned integer
                } else if (intType.UINT() != null) {
                    Integer uint = new Integer();
                    uint.setIsSigned(false);
                    newType.setDataType(uint);

                    // Signed long
                } else if (intType.LONG() != null) {
                    LongInteger longInteger = new LongInteger();
                    longInteger.setIsSigned(true);
                    newType.setDataType(longInteger);

                    // Unsigned long
                } else if (intType.ULONG() != null) {
                    LongInteger ulong = new LongInteger();
                    ulong.setIsSigned(false);
                    newType.setDataType(ulong);

                    // Char
                } else if (intType.CHAR() != null) {
                    newType.setDataType(new Character());
                }

                // Floating point types
            } else if (numType.floating_point_type() != null) {
                Floating_point_typeContext floatType = numType.floating_point_type();

                // Float
                if (floatType.FLOAT() != null) {
                    newType.setDataType(new Float());

                    // Double
                } else if (floatType.DOUBLE() != null) {
                    newType.setDataType(new Double());
                }
            } else {
                // Decimal type
                newType.setDataType(new Real());
            }
        } else {
            // Boolean
            newType.setDataType(new Boolean());
        }
        return newType;
    }

    /**
     * Maps class types to a NamedTypeReference from the GAST.
     *
     * @param type class type from the C# AST.
     * @return NamedTypeReference object with all values mapped into it.
     */
    default NamedTypeReference ClassTypeToType(Class_typeContext type) {
        NamedTypeReference newType = new NamedTypeReference(new Name(type.getText()));
        newType.setTag("typeName");
        return newType;
    }


    /**
     * Maps arguments to a ActualParameter from the GAST.
     *
     * @param argument argument from the C# AST.
     * @return ActualParameter object with all values mapped into it.
     */
    default ActualParameter argumentToActualParameter(ArgumentContext argument) {
        // TODO falta anadir el resto de partes de argument
        ActualParameterExpression parameter = new ActualParameterExpression();
        parameter.setValue(expressionToExpression(argument.expression()));
        return parameter;
    }

    /**
     * Maps class member declarations to a DefintionObject from the GAST.
     *
     * @param element class member declaration from the C# AST.
     * @return DefintionObject object with all values mapped into it.
     */
    default DefintionObject bodyClassToBodyClass(Class_member_declarationContext element) {
        DefintionObject newElement = null;

        // Common member declaration
        if (element.common_member_declaration() != null) {
            Common_member_declarationContext member = element.common_member_declaration();
            Method_declarationContext method;

            if (member.typed_member_declaration() == null) {
                method = element.common_member_declaration().method_declaration();
            } else {
                method = element.common_member_declaration().typed_member_declaration().method_declaration();
            }
            Enum_definitionContext enu = element.common_member_declaration().enum_definition();

            if (method != null) {
                FunctionDefintion newMethod;
                List<All_member_modifierContext> modifiers;
                if (element.all_member_modifiers() != null) {
                    modifiers = element.all_member_modifiers().all_member_modifier();
                } else {
                    modifiers = null;
                }
                List<Fixed_parameterContext> parameters = null;
                if (method.formal_parameter_list() != null) {
                    if (method.formal_parameter_list().fixed_parameters() != null) {
                        parameters = method.formal_parameter_list().fixed_parameters().fixed_parameter();
                    } else if (method.formal_parameter_list().parameter_array() != null) {
                        // TODO
                        System.out.println("Falta parameter array");
                        //public static Bookmark From(params string[] values)
                        //C:\Users\jasso\OneDrive\AVIB\neo4j-dotnet-driver-4.1\Neo4j.Driver\Neo4j.Driver\Bookmark.cs
                    }
                }
                Type_Context type;
                if (member.typed_member_declaration() != null) {
                    type = member.typed_member_declaration().type_();
                } else {
                    type = null;
                }

                newMethod = methodToMethod(method.method_member_name().identifier(0),
                        method.method_body().block(),
                        modifiers,
                        parameters,
                        type);
                newMethod.setLocationInfo(getSourceFromContext(method));

                if (type == null) {
                    newMethod.setReturnType(new NamedTypeReference(new Name("void")));
                }
                logMapping(method, newMethod);
                newElement = newMethod;
            } else if (enu != null) {
                EnumTypeDefinition newEnum = TypeDeclarationToEnumTypeDefinition(enu.identifier().getText(), element.all_member_modifiers().all_member_modifier(), enu.enum_body());
                newEnum.getDefinitionType().setEnumLiterals(enumLiteralListToList(enu.enum_body().enum_member_declaration()));
                newEnum.setLocationInfo(getSourceFromContext(enu));
                newElement = newEnum;
            } else if (element.common_member_declaration().constructor_declaration() != null) {
                Constructor_declarationContext constructor_declarationContext = element.common_member_declaration().constructor_declaration();
//                ConstructorDefinition constructorDefinition = new ConstructorDefinition();
//                if (constructor_declarationContext.formal_parameter_list() != null) {
//                    List<FormalParameterDefinition> formalParameterDefinitions = listToListParameters(constructor_declarationContext.formal_parameter_list().fixed_parameters().fixed_parameter());
//                    constructorDefinition.setParametersDefinitions(formalParameterDefinitions);
//                }
                ConstructorInitializerDefinition constructorInitializerDefinition = null;
                if (constructor_declarationContext.constructor_initializer() != null) {
                    constructorInitializerDefinition = new ConstructorInitializerDefinition();
                    if (constructor_declarationContext.constructor_initializer().argument_list() != null) {
                        constructorInitializerDefinition.setArgumentList(argumentListToActualParameterList(constructor_declarationContext.constructor_initializer().argument_list().argument()));
                    }
                    if (constructor_declarationContext.constructor_initializer().BASE() != null) {
                        constructorInitializerDefinition.setType(constructor_declarationContext.constructor_initializer().BASE().getText());
                    } else {
                        constructorInitializerDefinition.setType(constructor_declarationContext.constructor_initializer().THIS().getText());
                    }
//                    constructorDefinition.setConstructorInitializerDefinition(constructorInitializerDefinition);

                }
                Formal_parameter_listContext parameter_listContext =  constructor_declarationContext.formal_parameter_list();
                FunctionDefintion newMethod = null;
                if(parameter_listContext !=null){
                    newMethod = methodToMethod(constructor_declarationContext.identifier(),
                            constructor_declarationContext.body().block(),
                            null,parameter_listContext.fixed_parameters().fixed_parameter()
                            , null);
                    BlockStatement blockStatement = (BlockStatement) newMethod.getBody();
                    blockStatement.getSubStatements().add(0,constructorInitializerDefinition);
                }else {
                    newMethod = methodToMethod(constructor_declarationContext.identifier(),
                            constructor_declarationContext.body().block(),
                            null,null
                            , null);
                    BlockStatement blockStatement = (BlockStatement) newMethod.getBody();
                    blockStatement.getSubStatements().add(0,constructorInitializerDefinition);
                }
                //_______________

                //_________________
                newElement = newMethod;
//                constructorDefinition.setIdentifier(identifierToIdentifierReference(constructor_declarationContext.identifier()));
//                constructorDefinition.setBlockStatement(BlocktoBlockStatement(constructor_declarationContext.body().block()));
            }

            // Destructor definition
        } else if (element.destructor_definition() != null) {
            // TODO
        }

//		else if (element instanceof TypeDeclaration) { // class into class
//			newElement = classtoclass((TypeDeclaration) element);

//		} // else if (element instanceof AnnotationTypeDeclaration) {
//
//		// }

        return newElement;
    }

    /**
     * Maps enum member declaration to a EnumLiteralDefinition from the GAST.
     *
     * @param literal enum member declaration from the C# AST.
     * @return EnumLiteralDefinition object with all values mapped into it.
     */
    default EnumLiteralDefinition enumMemberToLiteral(Enum_member_declarationContext literal) {
        EnumLiteralDefinition newConstant = new EnumLiteralDefinition();
        newConstant.setIdentifier(IdentifierContextToName(literal.identifier()));
        return newConstant;
    }


//	default ASTMCore.ASTMSyntax.Types.TypeParameter ClassParameterToClassParameter(TypeParameter parameter) {
//		ASTMCore.ASTMSyntax.Types.TypeParameter newParameter = new ASTMCore.ASTMSyntax.Types.TypeParameter();
//		newParameter.setIdentifier(new Name(parameter.getName().getIdentifier()));
//		newParameter.setProperty(ListTypeReferencetoListType(parameter.typeBounds()));
//		return newParameter;
//	}
//
//	//////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * Mapea los pares de valores de las anotaciones en un m�todo
//	 *
//	 * @param value el par ordenado con los valores
//	 * @return El par ordenado mapeado.
//	 */
//	@Mappings({ @Mapping(source = "name", target = "name"), @Mapping(source = "value", target = "value") })
//	MemberValuePair ValuePairToValuePair(org.eclipse.jdt.core.dom.MemberValuePair value);
//
//	///////////////////////////////////////////////////////////////////////////////////////
//	/**
//	 * Mapea las invocaciones de los m�todos.
//	 *
//	 * @param invocation La llamada al m�todo.
//	 * @param parameters Lista de par�metros de la llamada al m�todo.
//	 * @return Expresi�n de la llamada a un m�todo en el GAST.
//	 */
//	///////////// M�todo que mapea las invocaciones de
//	///////////// funciones///////////////////////////////////////////////
//	@Mappings({ @Mapping(source = "invocation.name", target = "functionName"),
//			@Mapping(source = "invocation.expression", target = "calledFunction"),
//			@Mapping(source = "parameters", target = "actualParams") })
//	FunctionCallExpression CalltoCallInvocation(MethodInvocation invocation, List<Expression> parameters);
//

    // /**
    //  * Maps a new object expression to a NewExpression from the GAST.
    //  *
    //  * @param type type from the C# AST.
    //  * @param arguments list of arguments from the C# AST.
    //  * @return NewExpression object with all values mapped into it.
    //  */
    // @Mappings({@Mapping(source = "type", target = "newType"),
    //         @Mapping(source = "arguments", target = "actualParams")})
    // NewExpression newToNewExpression(Type_Context type, List<ArgumentContext> arguments);

    /**
     * Maps common identifiers to a Name from the GAST.
     *
     * @param identifier identifier from the C# AST.
     * @return Name object with all values mapped into it.
     */
    @Mapping(target = "locationInfo", ignore = true)
    @Mapping(target = "ofTypeReference", ignore = true)
    @Mapping(target = "annotations", ignore = true)
    @Mapping(target = "preProcessorElements", ignore = true)
    @Mapping(expression = "java(identifier.getText())", target = "nameString")
    Name IdentifierContextToName(IdentifierContext identifier);

    /**
     * Maps identifiers of a package to a NameSpaceDefinition from the GAST.
     *
     * @param identifier identifier from the C# AST.
     * @return NameSpaceDefinition object with all values mapped into it.
     */
    @Mappings({@Mapping(source = "identifier", target = "nameSpace")})
    NameSpaceDefinition packagetopackage(IdentifierContext identifier);

    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// Mapeo de la declaraci�n de importaci�n//

    /**
     * Maps using directives to a ImportDeclaration from the GAST.
     *
     * @param imports using directives from the C# AST.
     * @return ImportDeclaration object with all values mapped into it.
     */
    @Mappings({@Mapping(source = "imports", target = "identifierName", qualifiedByName = "ImportToIdentifier"),
            @Mapping(source = "imports", target = "static", qualifiedByName = "importToStatic")
    })
    ImportDeclaration importtoimport(Using_directiveContext imports);

    /**
     * Maps using directives' name to a Name from the GAST.
     *
     * @param imports using directives from the C# AST.
     * @return Name object with all values mapped into it.
     */
    @Named("importToIdentifier")
    public static Name importToIdentifier(Using_directiveContext imports) {
        if (imports == null) {
            return null;
        }
        String identifier = "";

        Namespace_or_type_nameContext namespace = null;

        if (imports instanceof UsingAliasDirectiveContext) {
            namespace = ((UsingAliasDirectiveContext) imports).namespace_or_type_name();
        } else if (imports instanceof UsingNamespaceDirectiveContext) {
            namespace = ((UsingNamespaceDirectiveContext) imports).namespace_or_type_name();
        } else if (imports instanceof UsingStaticDirectiveContext) {
            namespace = ((UsingStaticDirectiveContext) imports).namespace_or_type_name();
        }

        for (IdentifierContext id : namespace.identifier()) {
            if (identifier.isEmpty()) {
                identifier += id.getText();
            } else {
                identifier += ".";
                identifier += id.getText();
            }
        }

        Name name = new Name();
        name.setNameString(identifier);
        return name;
    }

    /**
     * Indicates if the using directive has static modifier.
     *
     * @param imports using directives from the C# AST.
     * @return boolean that indicates if it is a using static directive.
     */
    @Named("importToStatic")
    public static boolean importToStatic(Using_directiveContext imports) {
        if (imports instanceof UsingStaticDirectiveContext) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Maps variable declarators to a Fragment from the GAST.
     *
     * @param variable variable declarator from the C# AST.
     * @return Fragment object with all values mapped into it.
     */
    @Mappings({@Mapping(expression = "java(IdentifierContextToName(variable.identifier()))", target = "identifierName"),
            @Mapping(expression = "java(variable.variable_initializer() != null ? expressionToExpression(variable.variable_initializer().expression()) : null)", target = "initialValue")})
    Fragment variabletovariable(Variable_declaratorContext variable);

    /**
     * Maps local variable declarators to a Fragment from the GAST.
     *
     * @param variable local variable declarator from the C# AST.
     * @return Fragment object with all values mapped into it.
     */
    @Mappings({@Mapping(expression = "java(IdentifierContextToName(variable.identifier()))", target = "identifierName"),
            @Mapping(expression = "java(variable.local_variable_initializer() != null ? localVariableInitializerToExpression(variable.local_variable_initializer()): null)", target = "initialValue")})
    Fragment localVariabletovariable(Local_variable_declaratorContext variable);

    /**
     * Maps fixed pointer declarators to a Fragment from the GAST.
     *
     * @param variable fixed pointer declarator from the C# AST.
     * @return Fragment object with all values mapped into it.
     */
    @Mappings({@Mapping(expression = "java(IdentifierContextToName(variable.identifier()))", target = "identifierName"),
            @Mapping(expression = "java(variable.fixed_pointer_initializer() != null ? fixedPointerInitializerToExpression(variable.fixed_pointer_initializer()): null)", target = "initialValue"),
            @Mapping(constant = "variable", target = "tag")})
    Fragment pointertovariable(Fixed_pointer_declaratorContext variable);

    /**
     * Maps fixed parameters to a FormalParameterDefinition from the GAST.
     *
     * @param variable fixed parameter from the C# AST.
     * @return FormalParameterDefinition object with all values mapped into it.
     */
    @Mappings({@Mapping(expression = "java(IdentifierContextToName(variable.arg_declaration().identifier()))", target = "identifierName"),
            @Mapping(expression = "java(TypeReferencetoType(variable.arg_declaration().type_()))", target = "definitionType")})
    FormalParameterDefinition parameterToParameter(Fixed_parameterContext variable);

    /**
     * Maps exceptions from catch blocks to a FormalParameterDefinition from the GAST.
     *
     * @param type       class type from the C# AST.
     * @param identifier identifier from the C# AST.
     * @return FormalParameterDefinition object with all values mapped into it.
     */
    @Mappings({@Mapping(source = "identifier", target = "identifierName"),
            @Mapping(source = "type", target = "definitionType"), @Mapping(constant = "parameter", target = "tag")})
    FormalParameterDefinition exceptionToParameter(Class_typeContext type, IdentifierContext identifier);

    /**
     * Maps parameters from a for each statement to a FormalParameterDefinition from the GAST.
     *
     * @param forStatement for each statement from the C# AST.
     * @return FormalParameterDefinition object with all values mapped into it.
     */
    @Mappings({@Mapping(expression = "java(IdentifierContextToName(forStatement.identifier()))", target = "identifierName"),
            @Mapping(expression = "java(forStatement.local_variable_type().type_() != null? TypeReferencetoType(forStatement.local_variable_type().type_()) : null)", target = "definitionType")
    })
    FormalParameterDefinition forStatementToParameter(ForeachStatementContext forStatement);

    /**
     * Maps a method definition to a FunctionDefinition from the GAST.
     *
     * @param name       name of the method as a identifier from the C# AST.
     * @param block      body of the method as a block from the C# AST.
     * @param modifiers  modifiers of the method as a list of all member modifiers from the C# AST.
     * @param parameters parameters of the method a a list of fixed parameters from the C# AST.
     * @param type       return type of the method as a type from the C# AST.
     * @return FunctionDefintion with all values mapped into it.
     */
    @Mappings({@Mapping(source = "type", target = "returnType"),
            @Mapping(source = "modifiers", target = "modifiers"),
            @Mapping(target = "locationInfo", ignore = true), @Mapping(target = "accessKind", ignore = true),
            @Mapping(target = "linkageSpecifier", ignore = true), @Mapping(target = "storageSpecifier", ignore = true),
            @Mapping(target = "definitionType", ignore = true), @Mapping(source = "name", target = "identifierName"),
            @Mapping(target = "ofDeclaration", ignore = true),
            @Mapping(source = "parameters", target = "formalParameters"),
            @Mapping(target = "functionMemberAttributes", ignore = true),
            @Mapping(target = "opensScope", ignore = true), @Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true),
            @Mapping(source = "block", target = "body"),
            @Mapping(target = "exception", ignore = true)})
    FunctionDefintion methodToMethod(IdentifierContext name, BlockContext block, List<All_member_modifierContext> modifiers,
                                     List<Fixed_parameterContext> parameters, Type_Context type);

    /**
     * Maps a namespace or type name from a interface to a ImplementsTo from the GAST.
     *
     * @param _class namespace or type name from the C# AST.
     * @return ImplementsTo object with all values mapped into it.
     */
    // TODO include TypeReference
    @Mappings({@Mapping(source = "_class", target = "implementsTo")})
    ImplementsTo superinterfacetoimplementsto(Namespace_or_type_nameContext _class);

    /**
     * Maps a namespace or type name to a TypeReference from the GAST.
     *
     * @param type namespace or type name from the C# AST.
     * @return TypeReference object with all values mapped into it.
     */
    default TypeReference ClassNameToTypeReference(Namespace_or_type_nameContext type) {
        NamedTypeReference newType;
        newType = new NamedTypeReference(IdentifierContextToName(type.identifier(0)));
        newType.setLocationInfo(getSourceFromContext(type));
        return newType;
    }

    /**
     * Maps a block to a BlockStatement from the GAST.
     *
     * @param block block from the C# AST.
     * @return BlockStatement object with all values mapped into it.
     */
    @Mappings({@Mapping(target = "locationInfo", ignore = true), @Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true), @Mapping(target = "opensScope", ignore = true),
            @Mapping(expression = "java(block.statement_list() != null? listToListStatement(block.statement_list().statement()):null)", target = "subStatements")})
    BlockStatement BlocktoBlockStatement(BlockContext block);

    /**
     * Maps a class definition to a ClassType from the GAST.
     *
     * @param _class          name of the class as a identifier from the C# AST.
     * @param modifiers       modifiers of the class as a list of all member modifiers from the C# AST.
     * @param body            body of the class as a list of class member declarations from the C# AST.
     * @param inherency       name of the inherited class as a namespace or type name from the C# AST.
     * @param implementations name of the implemented interfaces as a list of namespace or type names from the C# AST.
     * @param members         attributes of the class as a list of class member declarations from the C# AST.
     * @return ClassType with all values mapped into it.
     */
    @Mappings({@Mapping(source = "body", target = "opensScope.declOrDefn"),
            @Mapping(source = "_class", target = "nameString"),
            @Mapping(source = "implementations", target = "implementesTo"),
            @Mapping(source = "modifiers", target = "modifiers"), @Mapping(target = "locationInfo", ignore = true),
            @Mapping(target = "isConst", ignore = true), @Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true),
            @Mapping(source = "members", target = "members"),
            @Mapping(source = "inherency", target = "derivesFrom", qualifiedByName = "inherencyToDerivesFrom"),
            @Mapping(target = "parameters", ignore = true), //TODO
            @Mapping(source = "isInterface", target = "interface")})
    ClassType MappingClass(IdentifierContext _class, List<All_member_modifierContext> modifiers, List<Class_member_declarationContext> body,
                           Namespace_or_type_nameContext inherency, List<Namespace_or_type_nameContext> implementations, List<Class_member_declarationContext> members,
                           List<Type_parameterContext> parameters, boolean isInterface);

    /**
     * Maps the name of a derived class as a namespace or type name to a DerivesFrom from the GAST.
     *
     * @param classDerived name of the derived class as a namespaces or type name from the C# AST.
     * @return list with a single DerivesFrom object with all values mapped into it.
     */
    @Named("inherencyToDerivesFrom")
    public static List<DerivesFrom> inherencyToDerivesFrom(Namespace_or_type_nameContext classDerived) {
        DerivesFrom derivesFrom = new DerivesFrom();
        // Join all identifiers of the path of the class
        String identifier = "";
        for (IdentifierContext id : classDerived.identifier()) {
            if (identifier.isEmpty()) {
                identifier += id.getText();
            } else {
                identifier += ".";
                identifier += id.getText();
            }
        }

        Name name = new Name();
        name.setNameString(identifier);
        NamedTypeReference type = new NamedTypeReference(name);

        derivesFrom.setSuperclass(type);
        List<DerivesFrom> derivesFromList = new ArrayList<>();
        derivesFromList.add(derivesFrom);
        derivesFrom.setLocationInfo(INSTANCE.getSourceFromContext(classDerived));
        return derivesFromList;
    }

    /**
     * Maps class declarations to a AggregateTypeDefinition from the GAST.
     *
     * @param _class type declaration of the class from the C# AST.
     * @return AggregateTypeDefinition object with all values mapped into it.
     */
    @Mappings({@Mapping(target = "locationInfo", ignore = true), @Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true), @Mapping(target = "typeName", ignore = true),
            @Mapping(source = "_class", target = "aggregateType")})
    AggregateTypeDefinition TypeDeclarationtoClassType(Type_declarationContext _class);

    /**
     * Maps enum declarations to a EnumTypeDefinition from the GAST.
     *
     * @param name      name of the enum as a String\
     * @param modifiers modifiers of the enum as a list of all member modifiers from the C# AST.
     * @param body      enum body from the C# AST.
     * @return EnumTypeDefinition object with all values mapped into it.
     */
    @Mappings({@Mapping(source = "name", target = "definitionType.identifier"),
            @Mapping(target = "definitionType.implementesTo", ignore = true),
            @Mapping(source = "modifiers", target = "definitionType.modifiers"),
            @Mapping(target = "definitionType.opensScope.declOrDefn", ignore = true)})
    EnumTypeDefinition TypeDeclarationToEnumTypeDefinition(String name, List<All_member_modifierContext> modifiers,
                                                           Enum_bodyContext body);
//
//	//////////////////////////// Mapeo de los
//	//////////////////////////// enumerados/////////////////////////////////////////////////////////
//	@Mappings({ @Mapping(source = "name", target = "name"), @Mapping(source = "modifiers", target = "modifiers"),
//			@Mapping(source = "body", target = "opensScope.declOrDefn") })
//	AnnotationType TypeDeclarationToAnnotatioDeclaration(Name name, List<IExtendedModifier> modifiers,
//                                                         List<BodyDeclaration> body);
//
    ////////////////////////// Mapeo de las
    ////////////////////////// anotaciones///////////////////////////////////////////////////////
    /*
     * @Mappings({ @Mapping(target = "locationInfo", ignore = true), @Mapping(target
     * = "annotations", ignore = true),
     *
     * @Mapping(target = "preProcessorElements", ignore = true),
     * // @Mapping(source="name" , target="typeName"),
     *
     * @Mapping(constant = "Annotation", target = "typeName.nameString"),
     *
     * @Mapping(source = "annotation", target = "aggregateType") })
     * AggregateTypeDefinition TypeDeclarationtoClassType(AnnotationType
     * annotation);
     */

    /**
     * Maps switch's case to a SwitchCase from the GAST.
     *
     * @param switch_section switch section from the C# AST.
     * @return SwitchCase object with all values mapped into it.
     */
    default SwitchCase caseToCase(Switch_sectionContext switch_section) {
        if (switch_section.switch_label().size() >= 1) {
            SwitchCase switchCase;
            if (switch_section.switch_label(0).expression() != null) {
                switchCase = new CaseBlock();
                switchCase.setEvaluation(expressionToExpression(switch_section.switch_label(0).expression()));
            } else {
                switchCase = new DefaultBlock();
            }
            switchCase.setBody(listToListStatement(switch_section.statement_list().statement()));
            switchCase.setLocationInfo(getSourceFromContext(switch_section));
            return switchCase;
        }
        return null;
    }

    //////////////////////// Automatic mapping of lists to lists ///////////////////////////////

    /**
     * Maps a list of namespace member declarations to a list of DefintionObject from the GAST.
     *
     * @param types list of namespace member declarations from the C# AST.
     * @return list of DefintionObject with all values mapped into it.
     */
    List<DefintionObject> typestotypes(List<Namespace_member_declarationContext> types);

    /**
     * Maps a list of namespace or type names to a list of ImplementsTo from the GAST.
     *
     * @param implementation list of namespace or type names from the C# AST.
     * @return list of ImplementsTo with all values mapped into it.
     */
    List<ImplementsTo> implementstoimplementsList(List<Namespace_or_type_nameContext> implementation);

    /**
     * Maps a list of expressions to an ArrayList of Expression from the GAST.
     *
     * @param list list of expressions from the C# AST.
     * @return ArrayList of Expression with all values mapped into it.
     */
    ArrayList<Expression> expressionListToExpressionList(List<ExpressionContext> list);

    /**
     * Maps a list of expressions to an ArrayList of Expression from the GAST.
     *
     * @param list list of expressions from the C# AST.
     * @return ArrayList of Expression with all values mapped into it.
     */
    List<Expression> variableInitializerListToExpressionList(List<Variable_initializerContext> list);

    /**
     * Maps a list of element initializers to a list of Expression from the GAST.
     *
     * @param list list of element initializers from the C# AST.
     * @return list of Expression with all values mapped into it.
     */
    List<Expression> elementInitializerListToExpressionList(List<Element_initializerContext> list);

    /**
     * Maps a list of enum member declarations to a list of Expression from the GAST.
     *
     * @param list list of enum member declarations from the C# AST.
     * @return list of Expression with all values mapped into it.
     */
    List<Expression> enumListToExpressionList(List<Enum_member_declarationContext> list);

    /**
     * Maps enum member declarations to a Expression from the GAST.
     *
     * @param member enum member declaration from the C# AST.
     * @return Expression with all values mapped into it.
     */
    default Expression enumToExpression(Enum_member_declarationContext member) {
        return expressionToExpression(member.expression());
    }

    /**
     * Maps a list of enum member declarations to a list of EnumLiteralDefinition from the GAST.
     *
     * @param literals list of enum member declarations from the C# AST.
     * @return list of EnumLiteralDefinition with all values mapped into it.
     */
    List<EnumLiteralDefinition> enumLiteralListToList(List<Enum_member_declarationContext> literals);

    /**
     * Maps the body of a class (list of class member declarations) to a list of DefintionObject from the GAST.
     *
     * @param body list of class member declarations from the C# AST.
     * @return list of DefintionObject with all values mapped into it.
     */
    List<DefintionObject> BodyToBody(List<Class_member_declarationContext> body);

    /**
     * Maps a method's parameters (list of fixed parameters) to a list of FormalParameterDefinition from the GAST.
     *
     * @param parameters list of fixed parameters from the C# AST.
     * @return list of FormalParameterDefinition with all values mapped into it.
     */
    List<FormalParameterDefinition> listToListParameters(List<Fixed_parameterContext> parameters);

    /**
     * Maps a list of statements to a list of Statement from the GAST.
     *
     * @param statements list of statements from the C# AST.
     * @return list of Statement with all values mapped into it.
     */
    ArrayList<Statement> listToListStatement(List<StatementContext> statements);


    /**
     * Maps a list of switch sections to a list of SwitchCase from the GAST.
     *
     * @param list list of switch sections from the C# AST.
     * @return list of SwitchCase with all values mapped into it.
     */
    List<SwitchCase> ListSwitchToListSwitch(List<Switch_sectionContext> list);

    /**
     * Maps a list of attributes (class member declarations) to a list of MemberObject from the GAST.
     *
     * @param members list of class member declarations from the C# AST.
     * @return list of MemberObject with all values mapped into it.
     */
    default List<MemberObject> memberListToMemberList(List<Class_member_declarationContext> members) {
        List<MemberObject> list = new ArrayList<MemberObject>();

        for (Class_member_declarationContext member : members) {
            MemberObject newMember = memberToMember(member);
            if (newMember != null) {
                list.add(newMember);
                newMember.setLocationInfo(getSourceFromContext(member));
            }
        }
        return list;
    }

    /**
     * Maps a list of variable declarators to a list of Fragment from the GAST.
     *
     * @param fragments list of variable declarators from the C# AST.
     * @return list of Fragment with all values mapped into it.
     */
    List<Fragment> fragmentsToFragments(List<Variable_declaratorContext> fragments);

    /**
     * Maps a list of local variable declarators to a list of Fragment from the GAST.
     *
     * @param fragments list of local variable declarators from the C# AST.
     * @return list of Fragment with all values mapped into it.
     */
    List<Fragment> localFragmentsToFragments(List<Local_variable_declaratorContext> fragments);

    /**
     * Maps a list of constant variable declarators to a list of Fragment from the GAST.
     *
     * @param fragments list of constant variable declarators from the C# AST.
     * @return list of Fragment with all values mapped into it.
     */
    List<Fragment> constFragmentsToFragments(List<Constant_declaratorContext> fragments);

    /**
     * Maps a list of fixed pointer declarators to a list of Fragment from the GAST.
     *
     * @param fragments list of fixed pointer declarators from the C# AST.
     * @return list of Fragment with all values mapped into it.
     */
    List<Fragment> pointerFragmentsToFragments(List<Fixed_pointer_declaratorContext> fragments);

    /**
     * Maps a list of specific catch clauses to a list of CatchBlock from the GAST.
     *
     * @param catches list of specific catch clauses from the C# AST.
     * @return list of CatchBlock with all values mapped into it.
     */
    List<CatchBlock> ListCatchToListCatch(List<Specific_catch_clauseContext> catches);

    /**
     * Maps a list of all member modifiers to a list of Modifiers from the GAST.
     *
     * @param modifiers list of all member modifiers from the C# AST.
     * @return list of Modifiers with all values mapped into it.
     */
    List<Modifiers> ListModifiersToListModifiers(List<All_member_modifierContext> modifiers);

    /**
     * Maps a list of arguments to a list of ActualParameter from the GAST.
     *
     * @param argumentList list of arguments from the C# AST.
     * @return list of ActualParameter with all values mapped into it.
     */
    ArrayList<ActualParameter> argumentListToActualParameterList(List<ArgumentContext> argumentList);

    /**
     * Maps a list of member initializers to a list of MemberInitializer from GAST for the ObjectInitializer in a NewExpression
     *
     * @param memberList list of member initializer contexts from C# AST.
     * @return ArrayList of MemberInitializer from GAST.
     */
    ArrayList<Expression> memberInitializerListToExpressionList(List<Member_initializerContext> memberList);

    /**
     * Maps a list of member declarator to a list of MemberInitializer from GAST for the ObjectInitializer in a NewExpression
     *
     * @param memberList list of member declarators contexts from C# AST.
     * @return ArrayList of MemberInitializer from GAST.
     */
    ArrayList<Expression> memberDeclaratorListToExpressionList(List<Member_declaratorContext> memberList);
}
