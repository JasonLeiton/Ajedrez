package gastmappers.rpgmapper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import ASTMCore.ASTMSyntax.Types.ArrayType;
import ASTMCore.ASTMSyntax.Types.Type;
import ASTMCore.ASTMSyntax.Types.TypeReference;
import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.ElemFunction;
import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.EofFunction;
import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.ErrorFunction;
import gastmappers.rpgmapper.mapperutilities.Specification.Statements.*;
import org.abego.treelayout.internal.util.java.lang.string.StringUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import ASTMCore.ASTMSemantics.AggregateScope;
import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.*;
import ASTMCore.ASTMSyntax.Expression.*;
import ASTMCore.ASTMSyntax.Statement.*;
import ASTMCore.ASTMSyntax.Types.ClassType;
import ASTMCore.visitor.GASTVisitor;
import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.CustomFunction;
import gastmappers.rpgmapper.mapperutilities.Statements.Factors.*;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.*;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DeleteStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ForStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.IfStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ReturnStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.*;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;
import gastmappers.rpgmapper.mapperutilities.Structure.RPGSyntaxTree;
import gastmappers.rpgmapper.mapperutilities.constants.IFunctionConstants;

import javax.swing.plaf.nimbus.State;

/**
 * La función de esta clase es proveer los métodos que se necesitan para mapear
 * los datos del AST de RPG al GAST.
 *
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */

@Mapper
public interface RPGCompilationMapper {

	RPGCompilationMapper INSTANCE = Mappers.getMapper(RPGCompilationMapper.class);

	/**
	 * Este método es el que mapea los elementos más externos del AST de RPG para
	 * generar la raiz del GAST.
	 *
	 * @param rpgTree AST de RPG
	 * @return La raíz del GAST con datos mapeados del AST de RPG.
	 */
	@Mappings({ @Mapping(target = "language", constant = "RPG"),
			@Mapping(source = "rpgTree.statementContext", target = "opensScope.declOrDefn"), //, qualifiedByName = "prueba"),
			@Mapping(constant = "body", target = "opensScope.tag"),
			@Mapping(constant = "compilationUnit", target = "tag"), @Mapping(ignore = true, target = "path") })
	CompilationUnit Compilationtocompilation(RPGSyntaxTree rpgTree);


	@Named("prueba")
	default <T> ArrayList<T> prueba(gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements value) {
		ArrayList<T> lista = new ArrayList<>();
		List<Statement> listaStatements = value.getStatements();
		for (int i = 0; i < listaStatements.size(); i++) {
			lista.add((T) listaStatements.get(i));
		}
		return lista;
	}

	/**
	 * Construye los elementos base del GAST a partir de los statements del AST de
	 * RPG
	 *
	 * @param rpgStatement grupo de statements de RPG
	 * @return lista de statements con la estructura del GAST
	 */
	default ArrayList<DefintionObject> ConstructTree(
			gastmappers.rpgmapper.mapperutilities.Statements.Types.GroupStatements rpgStatement) {

		// bloque de elementos dentro de un metodo
		BlockStatement blockStatement = GroupToBlock(rpgStatement);

		// metodo
		FunctionDefintion functionDefintion = new FunctionDefintion();
		functionDefintion.setBody(blockStatement);

		// grupo de metodos dentro de clase(solo 1)
		ArrayList<DefintionObject> definitionObjects = new ArrayList<DefintionObject>();
		definitionObjects.add(functionDefintion);

		// bloque de elementos dentro de clase
		AggregateScope aggregateScope = new AggregateScope();
		aggregateScope.setDeclOrDefn(definitionObjects);

		// clase
		ClassType classType = new ClassType();
		classType.setOpensScope(aggregateScope);

		// clase
		AggregateTypeDefinition typeDefinition = new AggregateTypeDefinition();
		typeDefinition.setAggregateType(classType);

		// clases en documento
		ArrayList<DefintionObject> definitionObjectsResult = new ArrayList<DefintionObject>();
		definitionObjectsResult.add(typeDefinition);

		return definitionObjectsResult;
	}

	/**
	 * Realiza el mapeo de un grupo de estaments de RPG a un bloque de statements
	 * del GAST
	 *
	 * @param groupStatements grupo de statements de RPG
	 * @return Bloque de estatements del GAST
	 */
	default BlockStatement GroupToBlock(GroupStatements groupStatements) {
		BlockStatement blockStatement = new BlockStatement();
		ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> setStatements = (groupStatements == null) ? null
				: GetStatements(groupStatements.getStatements());
		blockStatement.setSubStatements(setStatements);
		return blockStatement;
	}

	/**
	 * Realizar el mapeo de statement de RPG a statement de GAST
	 *
	 * @param statements lista de statements de RPG
	 * @return lista de statements del GAST
	 */
	default ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> GetStatements(List<Statement> statements) {
		ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> setStatements = new ArrayList<ASTMCore.ASTMSyntax.Statement.Statement>();
		for (gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement statement : statements) {
			try{
				setStatements.add(StatementToStatement(statement));
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}

		return setStatements;
	}

	/**
	 * Realiza el mapeo de un statement de RPG a una expresion del GAST
	 *
	 * @param statement statement de RPG
	 * @return expression del GAST
	 */
	default Expression StatementToExpression(Statement statement) {
		Expression newExpression = null;

		if (statement instanceof DefinitionStatement) {
			DefinitionStatement variable = (DefinitionStatement) statement;
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getVariable()));
			newCondicional.setRightOperand(FactorToExpression(variable.getValue()));
			GenericOperator newOperator = new GenericOperator();
			newOperator.setSimbol(variable.getSymbol());
			newCondicional.setOperator(newOperator);
			newCondicional.setTag("expression");
			newExpression = newCondicional;
		} else if (statement instanceof AssignStatement) {
			AssignStatement variable = (AssignStatement) statement;
			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
			conditionalResult.setRightOperand(newCondicional);
			conditionalResult.setOperator(new Assign());

			newCondicional.setTag("expression");
			newExpression = conditionalResult;
		}

		return newExpression;
	}

	default boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	default CustomFunction getHExtensor() {
		return new CustomFunction(IFunctionConstants.ROUND_UP);
	}

	/**
	 * Realiza el mapeo de determinado statement de RPG a uno especifico del GAST
	 * segun corresponda
	 *
	 * @param statement statement de RPG
	 * @return statement del GAST
	 */
	default ASTMCore.ASTMSyntax.Statement.Statement StatementToStatement(
			gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement statement) {
		ASTMCore.ASTMSyntax.Statement.Statement newStatement = null;
		if (statement instanceof AcqStatement) {
			AcqStatement variable = (AcqStatement) statement;
			Factor[] listFactor = { variable.getDeviceID(), variable.getDeviceFilename() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("AcqStatement");
		} else if (statement instanceof AddDurStatement) {
			/*AddDurStatement variable = (AddDurStatement) statement;
			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			CustomFunction customFunction = new CustomFunction(variable.getDuration());
			Factor[] args = { variable.getRightOperand() };
			List<Factor> arguments = Arrays.asList(args);
			customFunction.setArguments(arguments);
			FunctionCallExpression functionCall = CustomToMethod(customFunction);
			newCondicional.setRightOperand(functionCall);
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
			conditionalResult.setRightOperand(newCondicional);
			conditionalResult.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(conditionalResult);
			newStatement = newExpressionStatement;*/
			// newStatement.setTag("AddDurStatement");
		} else if (statement instanceof AddStatement) {
			AddStatement variable = (AddStatement) statement;
			variable.isRoundUp();
			BinaryExpression conditionalResult = new BinaryExpression();
			CustomFunction customFunction = getHExtensor();

			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));
			Factor fact = new ParenthesizedFactor(variable.getLeftOperand(), variable.getRightOperand(),
					variable.getOperand());
			Factor[] arguments = { fact };
			customFunction.setArguments(Arrays.asList(arguments));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
			conditionalResult.setRightOperand(variable.isRoundUp() ? CustomToMethod(customFunction) : newCondicional);
			conditionalResult.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(conditionalResult);
			newStatement = newExpressionStatement;
			// newStatement.setTag("AddStatement");
		} else if (statement instanceof AllocStatement) {
			AllocStatement variable = (AllocStatement) statement;
			LiteralFactor lengthBytes = new LiteralFactor("" + variable.getLengthBytes());
			Factor[] listFactor = { lengthBytes, variable.getPointerVariable() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("AllocStatement");
		} else if (statement instanceof AssignStatement) {
			AssignStatement variable = (AssignStatement) statement;

			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(StatementToExpression(variable));
			newStatement = newExpressionStatement;
			// newStatement.setTag("AssignStatement");
		} else if (statement instanceof BegsrStatement) {
			BegsrStatement variable = (BegsrStatement) statement;
			FunctionDefintion subroutineDefinition = new FunctionDefintion();
			FunctionDeclaration subroutineDeclaration = new FunctionDeclaration();
			Name subroutineName = new Name();
			subroutineName.setNameString(variable.getSubroutineLabel());
			subroutineDeclaration.setIdentifierName(subroutineName);
			subroutineDeclaration.setDefRef(subroutineDefinition);
			subroutineDefinition.setBody(GroupToBlock(variable.getBody()));
//			BlockStatement blockStatement = GroupToBlock(variable.getBody());
			DeclarationOrDefinitionStatement dod = new DeclarationOrDefinitionStatement();
			dod.setDeclOrDefn(subroutineDeclaration);
			newStatement = dod;
			// newStatement.setTag("BegsrStatement");
		} else if (statement instanceof BitOffStatement) {
			BitOffStatement variable = (BitOffStatement) statement;
			Factor[] listFactor = { variable.getBitsSetOff(), variable.getCharVariable() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("BitOffStatement");
		} else if (statement instanceof BitOnStatement) {
			BitOnStatement variable = (BitOnStatement) statement;
			Factor[] listFactor = { variable.getBitsSetOn(), variable.getCharVariable() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("BitOnStatement");
		} else if (statement instanceof CabStatement) {
			CabStatement variable = (CabStatement) statement;
			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));
			Literal literal = new Literal();
			literal.setValue(variable.getLabelResultField());
			conditionalResult.setLeftOperand(literal);
			conditionalResult.setRightOperand(newCondicional);
			conditionalResult.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.IfStatement ifStatement = new ASTMCore.ASTMSyntax.Statement.IfStatement();

			ConditionalExpression conditionalExpression = new ConditionalExpression();

			conditionalExpression.setCondition(newCondicional);

			ifStatement.setCondition(conditionalExpression);

			LabelGoToStatement goTo = new LabelGoToStatement();
			goTo.setIdentifier(new Name(variable.getLabelResultField()));

			// bstatement.setSubStatements(subStatements);

			ifStatement.setThenBody(goTo);

//			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
//			newExpressionStatement.setExpression(conditionalResult);
//			newStatement = newExpressionStatement;
			newStatement = ifStatement;
			// newStatement.setTag("CabStatement");
		} else if (statement instanceof CallStatement) {
			BlockStatement bstatement = new BlockStatement();
			ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> substatements = new ArrayList<>();

			FunctionCallExpression newVariable = new FunctionCallExpression();
			newVariable.setFunctionName(new Name(((CallStatement) statement).getProcedureName().getValue()));
			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(newVariable);
			substatements.add(newExpressionStatement);

			if (((CallStatement) statement).getLR() != null) {
				BinaryExpression newCondicional = new BinaryExpression();
				newCondicional
						.setLeftOperand(FactorToExpression(new LiteralFactor(((CallStatement) statement).getLR())));
				newCondicional.setRightOperand(FactorToExpression(new LiteralFactor("1")));
				newCondicional.setOperator(new Assign());
				ASTMCore.ASTMSyntax.Statement.ExpressionStatement expressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
				expressionStatement.setExpression(newCondicional);
				substatements.add(expressionStatement);
			}

			bstatement.setSubStatements(substatements);
			newStatement = bstatement;
			// newStatement.setTag("CallStatement");

		} else if (statement instanceof CallPStatement) {
			CallPStatement variable = (CallPStatement) statement;
			/*FunctionCallExpression newMethodInvocation = CallPToCommand(variable);
			CustomFunction customFunction = new CustomFunction("SetPrecision");

			Factor[] args = new Factor[0];

			List<Factor> arguments = Arrays.asList(args);
			customFunction.setArguments(arguments);

			FunctionCallExpression fae = this.CustomToMethod(customFunction);
			ActualParameterExpression ace = new ActualParameterExpression();
			ace.setValue(newMethodInvocation);

			ArrayList<ActualParameter> parameters = new ArrayList<ActualParameter>();
			for (ActualParameter ap : newMethodInvocation.getActualParams()) {
				parameters.add(ap);
			}
			parameters.add(ace);
			fae.setActualParams(parameters);

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression((variable.isExtenderM()) ? fae : newMethodInvocation);
			newStatement = newExpressionStatement;*/
			newStatement.setTag("CallPStatement");
		} else if (statement instanceof CallBStatement) {
			BlockStatement bstatement = new BlockStatement();
			ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> substatements = new ArrayList<>();

			FunctionCallExpression newVariable = new FunctionCallExpression();
			newVariable.setFunctionName(new Name(((CallBStatement) statement).getProcedureName().getValue()));
			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(newVariable);

			substatements.add(newExpressionStatement);
			if (((CallBStatement) statement).getLR() != null) {
				BinaryExpression newCondicional = new BinaryExpression();
				newCondicional
						.setLeftOperand(FactorToExpression(new LiteralFactor(((CallBStatement) statement).getLR())));
				newCondicional.setRightOperand(FactorToExpression(new LiteralFactor("1")));
				newCondicional.setOperator(new Assign());
				ASTMCore.ASTMSyntax.Statement.ExpressionStatement expressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
				expressionStatement.setExpression(newCondicional);
				substatements.add(expressionStatement);

			}

			bstatement.setSubStatements(substatements);
			newStatement = bstatement;
			newStatement.setTag("CallBStatement");
		} else if (statement instanceof CasStatement) {
			BlockStatement bstatement = new BlockStatement();
			ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> substatements = new ArrayList<>();

			CasStatement variable = (CasStatement) statement;
			while (variable != null) {
				BinaryExpression conditionalResult = new BinaryExpression();
				BinaryExpression newCondicional = new BinaryExpression();
				newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
				newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
				newCondicional.setOperator(OperatorToOperator(variable.getOperand()));
				Literal literal = new Literal();
				literal.setValue(variable.getSubroutineResultField());
				conditionalResult.setLeftOperand(literal);
				conditionalResult.setRightOperand(newCondicional);
				conditionalResult.setOperator(new Assign());

				ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
				newExpressionStatement.setExpression(conditionalResult);
				substatements.add(newExpressionStatement);
				variable = variable.getNext();
			}

			bstatement.setSubStatements(substatements);
			newStatement = bstatement;
			newStatement.setTag("CasStatement");
		} else if (statement instanceof CatStatement) {
			CatStatement variable = (CatStatement) statement;

			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
			conditionalResult.setRightOperand(newCondicional);
			conditionalResult.setOperator(new Assign());

			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(conditionalResult);
			newStatement = newExpressionStatement;
			newStatement.setTag("CatStatement");
		} else if (statement instanceof ChainStatement) {
			ChainStatement variable = (ChainStatement) statement;
			Factor[] listFactor = { variable.getKeyValue(), variable.getName(), variable.getDataStructure() };
			newStatement = createStatement(listFactor, statement);
		} else if (statement instanceof CheckRStatement) {
			CheckRStatement variable = (CheckRStatement) statement;
			Factor[] listFactor = { variable.getChecklist(), variable.getCharValue(), variable.getPositions(),
					variable.getIndexStartPosition() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("CheckRStatement");
		} else if (statement instanceof CheckStatement) {

			BlockStatement bstatement = new BlockStatement();
			ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> substatements = new ArrayList<>();

			CheckStatement check = (CheckStatement) statement;
			if (check.getFound() != null) {

				Factor[] arguments = { check.getBaseValue(), check.getChecklist() };
				CustomFunction customFunction = new CustomFunction("found");
				customFunction.setArguments(Arrays.asList(arguments));

				BinaryExpression newCondicional = new BinaryExpression();
				newCondicional.setLeftOperand(FactorToExpression(((CheckStatement) statement).getFound()));
				newCondicional.setRightOperand(CustomToMethod(customFunction));
				newCondicional.setOperator(new Assign());
				ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
				newExpressionStatement.setExpression(newCondicional);
				substatements.add(newExpressionStatement);
			}

			bstatement.setSubStatements(substatements);
			newStatement = bstatement;
			newStatement.setTag("CheckStatement");

		} else if (statement instanceof ClearStatement) {
			ClearStatement variable = (ClearStatement) statement;
			Factor[] listFactor = { variable.getVariable() };
			newStatement = createStatement(listFactor, statement);

			// newStatement.setTag("ClearStatement");
		} else if (statement instanceof CloseStatement) {
			CloseStatement variable = (CloseStatement) statement;
			LiteralFactor fileName = new LiteralFactor(variable.getFileName());
			Factor[] listFactor = { fileName };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("CloseStatement");
		} else if (statement instanceof CommitStatement) {
			CommitStatement variable = (CommitStatement) statement;
			Factor[] listFactor = { variable.getBoundary() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("CommitStatement");
		} else if (statement instanceof CompStatement) {
			CompStatement variable = (CompStatement) statement;
			Factor[] listFactor = { variable.getLeftOperand(), variable.getRightOperand() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("CompStatement");
		} else if (statement instanceof DeAllocStatement) {
			DeAllocStatement variable = (DeAllocStatement) statement;
			Factor[] listFactor = { variable.getPointerVariable() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("DeAllocStatement");
		} else if (statement instanceof DefineStatement) {
			DefineStatement variable = (DefineStatement) statement;
			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(StatementToExpression(variable));
			newStatement = newExpressionStatement;
			// newStatement.setTag("DefineStatement");
		} else if (statement instanceof DefineDtaaraStatement) {
			DefineDtaaraStatement variable = (DefineDtaaraStatement) statement;
			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(StatementToExpression(variable));
			newStatement = newExpressionStatement;
			// newStatement.setTag("DefineDtaaraStatement");
		} else if (statement instanceof DefineLikeStatement) {
			DefineLikeStatement variable = (DefineLikeStatement) statement;
			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(StatementToExpression(variable));
			newStatement = newExpressionStatement;
			// newStatement.setTag("DefineLikeStatement");
		} else if (statement instanceof DefinitionStatement) {
			DefinitionStatement variable = (DefinitionStatement) statement;

			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(StatementToExpression(variable));
			newStatement = newExpressionStatement;
			// newStatement.setTag("DefinitionStatement");
		} else if (statement instanceof DeleteStatement) {
			DeleteStatement variable = (DeleteStatement) statement;
			Factor[] listFactor = { variable.getKeyValue(), variable.getFileName() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("DeleteStatement");
		} else if (statement instanceof DivStatement) {

			BlockStatement bstatement = new BlockStatement();
			ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> substatements = new ArrayList<>();

			DivStatement variable = (DivStatement) statement;

			CustomFunction customFunction = getHExtensor();

			Factor fact = new ParenthesizedFactor(variable.getLeftOperand(), variable.getRightOperand(),
					variable.getOperand());
			Factor[] arguments = { fact };
			customFunction.setArguments(Arrays.asList(arguments));

			// division DIV
			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
			conditionalResult
					.setRightOperand(variable.isHalfAdjust() ? CustomToMethod(customFunction) : newCondicional);
			conditionalResult.setOperator(new Assign());

			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(conditionalResult);
			substatements.add(newExpressionStatement);

			// remain MVR
			if (variable.getRemainder() != null) {
				BinaryExpression conditionalResultMVR = new BinaryExpression();
				BinaryExpression newCondicionalMVR = new BinaryExpression();

				AssignStatement as = variable.getRemainder();

				newCondicionalMVR.setLeftOperand(FactorToExpression(as.getLeftOperand()));
				newCondicionalMVR.setRightOperand(FactorToExpression(as.getRightOperand()));
				newCondicionalMVR.setOperator(OperatorToOperator(as.getOperand()));

				conditionalResultMVR.setLeftOperand(FactorToExpression(as.getResult()));
				conditionalResultMVR.setRightOperand(newCondicionalMVR);
				conditionalResultMVR.setOperator(new Assign());
				ExpressionStatement newExpressionStatementMVR = new ExpressionStatement();
				newExpressionStatementMVR.setExpression(conditionalResultMVR);
				substatements.add(newExpressionStatementMVR);

			}

			bstatement.setSubStatements(substatements);

			newStatement = bstatement;
			// newStatement.setTag("DivStatement");
		} else if (statement instanceof DoStatement) {
			DoStatement variable = (DoStatement) statement;
			ForCheckAfterStatement newFor = new ForCheckAfterStatement();
			BlockStatement blockStatement = GroupToBlock(variable.getBodyDo());
			newFor.setBody(blockStatement);
			newFor.setCondition(FactorToExpression(variable.getCondition()));
			ArrayList<Expression> list = new ArrayList<Expression>();
			list.add(StatementToExpression(variable.getDefinition()));
			newFor.setInitBody(list);
			list = new ArrayList<Expression>();
			list.add(StatementToExpression(variable.getIncrement()));
			newFor.setIterationBody(list);
			newStatement = newFor;
			newStatement.setTag("DoStatement");
		} else if (statement instanceof DouStatement) {
			DouStatement variable = (DouStatement) statement;
			WhileStatement newWhile = new WhileStatement();
			BlockStatement blockStatement = GroupToBlock(variable.getBodyDou());
			newWhile.setBody(blockStatement);
			newWhile.setCondition(FactorToExpression(variable.getConditions()));

			newStatement = newWhile;
			newStatement.setTag("DouStatement");
		} else if (statement instanceof DowStatement) {
			DowStatement variable = (DowStatement) statement;
			WhileStatement newWhile = new WhileStatement();
			BlockStatement blockStatement = GroupToBlock(variable.getBodyDow());
			newWhile.setBody(blockStatement);
			newWhile.setCondition(FactorToExpression(variable.getConditions()));

			newStatement = newWhile;
			newStatement.setTag("DowStatement");
		} else if (statement instanceof DsplyStatement) {
			DsplyStatement variable = (DsplyStatement) statement;
			Factor[] listFactor = { variable.getMessageText(), variable.getMessageQueue(),
					variable.getReplyVariable() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("DsplyStatement");
		} else if (statement instanceof DumpStatement) {
			DumpStatement variable = (DumpStatement) statement;
			Factor[] listFactor = { variable.getDescriptiveText() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("DumpStatement");
		} else if (statement instanceof EndStatement) {
			EndStatement variable = (EndStatement) statement;
			Factor[] listFactor = { variable.getFactor() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("EndStatement");
		} else if (statement instanceof EvalStatement) {
			EvalStatement variable = (EvalStatement) statement;

			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(FactorToExpression(variable.getCondition()));

			newStatement = newExpressionStatement;
			// newStatement.setTag("EvalStatement");
		} else if (statement instanceof EvalRStatement) {
			EvalRStatement variable = (EvalRStatement) statement;

			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(FactorToExpression(variable.getCondition()));

			newStatement = newExpressionStatement;
			// newStatement.setTag("EvalRStatement");
		} else if (statement instanceof ExceptStatement) {
			ExceptStatement variable = (ExceptStatement) statement;
			Factor[] listFactor = { variable.getExceptLabel() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ExceptStatement");
		} else if (statement instanceof ExfmtStatement) {
			ExfmtStatement variable = (ExfmtStatement) statement;
			Factor[] listFactor = { variable.getWorkstnFormat() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ExfmtStatement");
		} else if (statement instanceof ExsrStatement) {
			ExsrStatement variable = (ExsrStatement) statement;
			LiteralFactor subroutineLabel = new LiteralFactor(variable.getSubroutineLabel());
			Factor[] listFactor = { subroutineLabel };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ExsrStatement");
		} else if (statement instanceof ExtrctStatement) {
			ExtrctStatement variable = (ExtrctStatement) statement;
			LiteralFactor duration = new LiteralFactor(variable.getDuration());
			Factor[] listFactor = { variable.getDate(), variable.getNumericValue(), duration };
			// newStatement.setTag("ExtrctStatement");
		} else if (statement instanceof FeodStatement) {
			FeodStatement variable = (FeodStatement) statement;
			Factor[] listFactor = { variable.getFileName() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("FeodStatement");
		} else if (statement instanceof ForStatement) {
			ForStatement variable = (ForStatement) statement;
			ForCheckAfterStatement newFor = new ForCheckAfterStatement();

			BlockStatement blockStatement = GroupToBlock(variable.getBody());
			newFor.setBody(blockStatement);
			newFor.setCondition(StatementToExpression(variable.getIndexLimit()));
			ArrayList<Expression> list = new ArrayList<Expression>();
			list.add(StatementToExpression(variable.getIndexStart()));
			newFor.setInitBody(list);
			list = new ArrayList<Expression>();

			list.add(StatementToExpression(variable.getIncrementNumber()));
			newFor.setIterationBody(list);

			newStatement = newFor;
			// newStatement.setTag("ForStatement");
		} else if (statement instanceof ForceStatement) {
			ForceStatement variable = (ForceStatement) statement;
			Factor[] listFactor = { variable.getFileName() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ForceStatement");
		} else if (statement instanceof GotoStatement) {
			GotoStatement variable = (GotoStatement) statement;
			LiteralFactor branchLabel = new LiteralFactor(variable.getBranchLabel());
			Factor[] listFactor = { branchLabel };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("GotoStatement");
		} else if (statement instanceof IfStatement) {
			IfStatement variable = (IfStatement) statement;
			ASTMCore.ASTMSyntax.Statement.IfStatement newIf = new ASTMCore.ASTMSyntax.Statement.IfStatement();

			BlockStatement blockStatement = GroupToBlock(variable.getBodyIf());
			newIf.setThenBody(blockStatement);
			blockStatement = GroupToBlock(variable.getBodyElse());
			newIf.setElseBody(blockStatement);
			newIf.setCondition(FactorToExpression(variable.getConditions()));
			newStatement = newIf;
			// newStatement.setTag("IfStatement");
		} else if (statement instanceof InStatement) {
			InStatement variable = (InStatement) statement;
			LiteralFactor constantLock = new LiteralFactor(variable.isConstantLock() ? "1" : "0");
			Factor[] listFactor = { constantLock, variable.getDataArea() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("InStatement");
		} else if (statement instanceof KfldStatement) {
			KfldStatement variable = (KfldStatement) statement;
			Factor[] listFactor = { variable.getKeyField() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("KfldStatement");
		} else if (statement instanceof KlistStatement) {
			KlistStatement variable = (KlistStatement) statement;
			BlockStatement bs = new BlockStatement();

			LabeledStatement ls = new LabeledStatement();
			LabelDefinition labelDefinition = new LabelDefinition();
			labelDefinition.setLabelname(new Name(variable.getKeyListName().getValue()));

			List<Statement> list = variable.getListKeyField();

			ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> arrayList = new ArrayList<ASTMCore.ASTMSyntax.Statement.Statement>();
			arrayList.add((ASTMCore.ASTMSyntax.Statement.Statement) ls);

			for (int i = 0; i < list.size(); i++) {
				arrayList.addAll((Collection<? extends ASTMCore.ASTMSyntax.Statement.Statement>) list.get(i));
			}
			bs.setSubStatements(arrayList);

			// newStatement.setTag("KlistStatement");
		} else if (statement instanceof LeaveStatement) {
			LeaveStatement variable = (LeaveStatement) statement;
			LiteralFactor label = new LiteralFactor(variable.getLabel());
			Factor[] listFactor = { label };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("LeaveStatement");
		} else if (statement instanceof LeaveSrStatement) {
			LeaveSrStatement variable = (LeaveSrStatement) statement;
			LiteralFactor label = new LiteralFactor(variable.getLabel());
			Factor[] listFactor = { label };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("LeaveSrStatement");
		} else if (statement instanceof LookUpStatement) {
			LookUpStatement variable = (LookUpStatement) statement;
			Factor[] listFactor = { variable.getSearchArgument() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("LookUpStatement");
		} else if (statement instanceof LookUpArrayStatement) {
			LookUpArrayStatement variable = (LookUpArrayStatement) statement;
			Factor[] listFactor = { variable.getArrayName(), variable.getSearchIndex() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("LookUpArrayStatement");
		} else if (statement instanceof LookUpTableStatement) {
			LookUpTableStatement variable = (LookUpTableStatement) statement;
			Factor[] listFactor = { variable.getTableName(), variable.getAlternateTableName() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("LookUpTableStatement");
		} else if (statement instanceof MonitorStatement) {
			MonitorStatement variable = (MonitorStatement) statement;
			BlockStatement blockStatement = GroupToBlock(variable.getMonitorBody());
			newStatement = blockStatement;
			// newStatement.setTag("MonitorStatement");
		} else if (statement instanceof MoveStatement) {
			MoveStatement variable = (MoveStatement) statement;

			BinaryExpression binExpression = new BinaryExpression();
			binExpression.setLeftOperand(FactorToExpression(variable.getTarget()));
			binExpression.setOperator(new Assign());
			CustomFunction customFunction = new CustomFunction("DateFormat");
			Factor[] arguments = { variable.getDateFormat(), variable.getSource() };
			customFunction.setArguments(Arrays.asList(arguments));
			binExpression.setRightOperand((variable.getDateFormat() != null) ? CustomToMethod(customFunction)
					: FactorToExpression(variable.getSource()));

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(binExpression);
			newStatement = newExpressionStatement;
			// newStatement.setTag("MoveStatement");
		} else if (statement instanceof MoveAStatement) {
			MoveAStatement variable = (MoveAStatement) statement;

			CustomFunction customFunction = new CustomFunction("ClearBefore");
			Factor[] arguments = { variable.getTarget() };
			customFunction.setArguments(Arrays.asList(arguments));

			BinaryExpression binExpression = new BinaryExpression();
			binExpression.setLeftOperand(variable.isClearBefore() ? CustomToMethod(customFunction)
					: FactorToExpression(variable.getTarget()));
			binExpression.setRightOperand(FactorToExpression(variable.getSource()));
			binExpression.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(binExpression);
			newStatement = newExpressionStatement;
			// newStatement.setTag("MoveAStatement");
		} else if (statement instanceof MultStatement) {
			MultStatement variable = (MultStatement) statement;

			CustomFunction customFunction = getHExtensor();

			Factor fact = new ParenthesizedFactor(variable.getLeftOperand(), variable.getRightOperand(),
					variable.getOperand());
			Factor[] arguments = { fact };
			customFunction.setArguments(Arrays.asList(arguments));

			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
			conditionalResult
					.setRightOperand(variable.isHalfAdjust() ? CustomToMethod(customFunction) : newCondicional);
			conditionalResult.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(conditionalResult);
			newStatement = newExpressionStatement;
			// newStatement.setTag("MultStatement");
		} else if (statement instanceof MxxzoStatement) {
			MxxzoStatement variable = (MxxzoStatement) statement;
			// MHLZO
			// A = A&240 | ((B&240)>>4); 1111 0000

			LiteralFactor Mask1 = new LiteralFactor((variable.getFlags().charAt(0) == 'H') ? "240" : "15");
			LiteralFactor Mask2 = new LiteralFactor((variable.getFlags().charAt(1) == 'L') ? "240" : "15");

			BinaryExpression maskedLeft = new BinaryExpression();
			maskedLeft.setLeftOperand(FactorToExpression(variable.getSource()));
			maskedLeft.setRightOperand(FactorToExpression(Mask1));
			maskedLeft.setOperator(new BitAnd());

			BinaryExpression shifted = new BinaryExpression();
			shifted.setLeftOperand(maskedLeft);
			shifted.setRightOperand(FactorToExpression(new LiteralFactor("4")));
			shifted.setOperator(("LH".equals(variable.getFlags())) ? new BitLeftShift() : new BitUnsignedRightShift());

			BinaryExpression maskedRight = new BinaryExpression();
			maskedRight.setLeftOperand(FactorToExpression(variable.getTarget()));
			maskedRight.setRightOperand(FactorToExpression(Mask2));
			maskedRight.setOperator(new BitAnd());

			BinaryExpression masked = new BinaryExpression();
			masked.setLeftOperand(maskedLeft);
			masked.setRightOperand(
					("LH".equals(variable.getFlags()) || "HL".equals(variable.getFlags())) ? shifted : maskedRight);
			masked.setOperator(new BitOr());

			BinaryExpression binExpression = new BinaryExpression();
			binExpression.setLeftOperand(FactorToExpression(variable.getTarget()));
			binExpression.setRightOperand(masked);
			binExpression.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(binExpression);
			newStatement = newExpressionStatement;
			// newStatement.setTag("MxxzoStatement");
		} else if (statement instanceof NextStatement) {
			NextStatement variable = (NextStatement) statement;
			Factor[] listFactor = { variable.getWorkstnID(), variable.getWorkstnFile() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("NextStatement");
		} else if (statement instanceof OccurStatement) {
			OccurStatement variable = (OccurStatement) statement;
			Factor[] listFactor = { variable.getOcurrenceToSet(), variable.getDataStructure(),
					variable.getOcurrence() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("OccurStatement");
		} else if (statement instanceof OnErrorStatement) {
			OnErrorStatement variable = (OnErrorStatement) statement;
			Factor[] listFactor = { (Factor) variable.getErrorStatusList(), (Factor) variable.getBody() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("OnErrorStatement");
		} else if (statement instanceof OpenStatement) {
			OpenStatement variable = (OpenStatement) statement;
			Factor[] listFactor = { variable.getFileName() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("OpenStatement");
		} else if (statement instanceof OutStatement) {
			OutStatement variable = (OutStatement) statement;
			LiteralFactor constantLock = new LiteralFactor(variable.isConstantLock() ? "1" : "0");
			Factor[] listFactor = { variable.getDataArea(), constantLock };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("OutStatement");
		} else if (statement instanceof ParmStatement) {
			ParmStatement variable = (ParmStatement) statement;
			Factor[] listFactor = { variable.getInput(), variable.getOutput(), variable.getParameter() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ParmStatement");
		} else if (statement instanceof PlistStatement) {
			PlistStatement variable = (PlistStatement) statement;

			BlockStatement bs = new BlockStatement();

			LabeledStatement ls = new LabeledStatement();
			LabelDefinition labelDefinition = new LabelDefinition();
			labelDefinition.setLabelname(new Name(variable.getListName().getValue()));

			List<Statement> list = variable.getParmStatements();

			ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> arrayList = new ArrayList<ASTMCore.ASTMSyntax.Statement.Statement>();
			arrayList.add((ASTMCore.ASTMSyntax.Statement.Statement) ls);

			for (int i = 0; i < list.size(); i++) {
                ASTMCore.ASTMSyntax.Statement.Statement test = StatementToStatement(list.get(i));
                arrayList.add(test);
				//arrayList.addAll((Collection<? extends ASTMCore.ASTMSyntax.Statement.Statement>) StatementToStatement(list.get(i)));
			}
			bs.setSubStatements(arrayList);
			// newStatement.setTag("PlistStatement");
		} else if (statement instanceof PostStatement) {
			PostStatement variable = (PostStatement) statement;
			Factor[] listFactor = { variable.getWorkstnID(), variable.getWorkstnFile(), variable.getFileInfo() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("PostStatement");
		} else if (statement instanceof ReadStatement) {
			ReadStatement variable = (ReadStatement) statement;

			CustomFunction customFunction = new CustomFunction("NoLock");

			Factor[] arguments = { variable.getName() };
			newStatement = createStatement(arguments, statement);
			/*customFunction.setArguments(Arrays.asList(arguments));

			BinaryExpression binExpression = new BinaryExpression();
			binExpression.setLeftOperand(FactorToExpression(variable.getDataStructure()));
			binExpression.setRightOperand(
					variable.isNoLock() ? CustomToMethod(customFunction) : FactorToExpression(variable.getName()));
			binExpression.setOperator(new Assign());

			variable.isNoLock();

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(binExpression);
			newStatement = newExpressionStatement;*/
			// newStatement.setTag("ReadStatement");
		} else if (statement instanceof ReadCStatement) {
			System.out.println("entro a ReadC");
			ReadCStatement variable = (ReadCStatement) statement;
			Factor[] listFactor = { variable.getSubFileFormat() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ReadCStatement");
		} else if (statement instanceof ReadEStatement) {
			ReadEStatement variable = (ReadEStatement) statement;

			variable.getKeyValue();

			CustomFunction customFunctionByKeyValue = new CustomFunction("ReadByKeyValue");

			Factor[] argumentsbyKeyValue = { variable.getName(), variable.getKeyValue() };
			customFunctionByKeyValue.setArguments(Arrays.asList(argumentsbyKeyValue));

			CustomFunction customFunctionByKeyValueNL = new CustomFunction("ReadNoLockByKeyValue");

			Factor[] argumentByKeyValueNL = { variable.getName(), variable.getKeyValue() };
			customFunctionByKeyValue.setArguments(Arrays.asList(argumentByKeyValueNL));

			CustomFunction customFunction = new CustomFunction("NoLock");

			Factor[] arguments = { variable.getName(), variable.getKeyValue() };
			customFunction.setArguments(Arrays.asList(arguments));

			CustomFunction callableFunction = null;
			if ("".equals(variable.getKeyValue().getValue())) {
				if (variable.isNoLock())
					callableFunction = customFunction;
			} else {
				if (variable.isNoLock())
					callableFunction = customFunctionByKeyValueNL;
				else
					callableFunction = customFunctionByKeyValue;
			}

			BinaryExpression binExpression = new BinaryExpression();
			binExpression.setLeftOperand(FactorToExpression(variable.getDataStructure()));
			binExpression.setRightOperand((callableFunction != null) ? CustomToMethod(callableFunction)
					: FactorToExpression(variable.getName()));
			binExpression.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(binExpression);
			newStatement = newExpressionStatement;
			// newStatement.setTag("ReadEStatement");
		} else if (statement instanceof ReallocStatement) {
			ReallocStatement variable = (ReallocStatement) statement;
			Factor[] listFactor = { variable.getLengthBytes(), variable.getPointerVariable() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ReallocStatement");
		} else if (statement instanceof RelStatement) {
			RelStatement variable = (RelStatement) statement;
			Factor[] listFactor = { variable.getWorkstnId(), variable.getWorkstnFileName() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("RelStatement");
		} else if (statement instanceof ResetStatement) {
			ResetStatement variable = (ResetStatement) statement;
			LiteralFactor noKey = new LiteralFactor(variable.isNoKey() ? "1" : "0");
			LiteralFactor allKey = new LiteralFactor(variable.isAll() ? "1" : "0");
			Factor[] listFactor = { noKey, allKey, variable.getVariableReset() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ResetStatement");
		} else if (statement instanceof ReturnStatement) {
			ReturnStatement variable = (ReturnStatement) statement;
			LiteralFactor halfAdjust = new LiteralFactor(variable.isHalfAdjust() ? "1" : "0");
			LiteralFactor maximumDigits = new LiteralFactor(variable.isMaximumDigits() ? "1" : "0");
			LiteralFactor resultValueDigits = new LiteralFactor(variable.isResultValueDigits() ? "1" : "0");
			Factor[] listFactor = { halfAdjust, maximumDigits, resultValueDigits, variable.getReturnFactor() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ReturnStatement");
		} else if (statement instanceof ScanStatement) {
			ScanStatement variable = (ScanStatement) statement;
			Factor[] listFactor = { variable.getArgumentValue(), variable.getArgumentLength(),
					variable.getSearchVarValue(), variable.getStartValue(), variable.getPositionValue() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ScanStatement");
		} else if (statement instanceof SelectStatement) {
			SelectStatement variable = (SelectStatement) statement;
			SwitchStatement newVariable = new SwitchStatement();
			List<SwitchCase> listSwitchCase = new ArrayList<>();
			for(int i = 0; i < variable.getCases().size(); i++){
				WhenStatement whenStatement = variable.getCases().get(i);
				listSwitchCase.add(convertSwitchCase(whenStatement));
			}
			newVariable.setCases(listSwitchCase);
			newStatement = newVariable;
			// newStatement.setTag("SelectStatement");
		} else if (statement instanceof SetGTStatement) {
			SetGTStatement variable = (SetGTStatement) statement;
			Factor[] listFactor = { variable.getKeyValue(), variable.getNameValue() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("SetGTStatement");
		} else if (statement instanceof SetLLStatement) {
			SetLLStatement variable = (SetLLStatement) statement;
			Factor[] listFactor = { variable.getKeyValue(), variable.getNameValue() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("SetLLStatement");
		} else if (statement instanceof SetOffStatement) {
			SetOffStatement variable = (SetOffStatement) statement;
			BlockStatement newVariable = new BlockStatement();
			newVariable = SetOffStatements(variable);
			newStatement = newVariable;
			// newStatement.setTag("SetOffStatement");
		} else if (statement instanceof SetOnStatement) {
			SetOnStatement variable = (SetOnStatement) statement;
			BlockStatement newVariable = new BlockStatement();
			newVariable = SetOnStatements(variable);
			newStatement = newVariable;
			// newStatement.setTag("SetOnStatement");
		} else if (statement instanceof ShtdnStatement) {
			ShtdnStatement variable = (ShtdnStatement) statement;
			Factor[] listFactor = { variable.getLabel(), variable.getFlagValue() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("ShtdnStatement");
		} else if (statement instanceof SortaStatement) {
			SortaStatement variable = (SortaStatement) statement;
			Factor[] listFactor = { variable.getArrayName() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("SortaStatement");
		} else if (statement instanceof SqrtStatement) {
			SqrtStatement variable = (SqrtStatement) statement;
			CustomFunction customFunction = getHExtensor();
			ExpressionStatement newExpressionStatement = new ExpressionStatement();
			newExpressionStatement.setExpression(StatementToExpression(variable.getOperation()));

			Factor[] sqrtArguments = { variable.getOperation().getLeftOperand() };
			CustomFunction ff = new CustomFunction("sqrt");
			ff.setArguments(Arrays.asList(sqrtArguments));

			Factor[] ffArguments = { ff };

			customFunction.setArguments(Arrays.asList(ffArguments));

			BinaryExpression conditionalResult = new BinaryExpression();
			conditionalResult.setLeftOperand(FactorToExpression(variable.getOperation().getResult()));
			conditionalResult
					.setRightOperand(variable.isHalfAdjust() ? CustomToMethod(customFunction) : FactorToExpression(ff));
			conditionalResult.setOperator(new Assign());
			ExpressionStatement newSqrtStatement = new ExpressionStatement();
			newSqrtStatement.setExpression(conditionalResult);

			newStatement = newSqrtStatement;
			// newStatement.setTag("SqrtStatement");
		} else if (statement instanceof SubStatement) {
			SubStatement variable = (SubStatement) statement;
			CustomFunction customFunction = getHExtensor();
			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

			Factor fact = new ParenthesizedFactor(variable.getLeftOperand(), variable.getRightOperand(),
					variable.getOperand());
			Factor[] arguments = { fact };
			customFunction.setArguments(Arrays.asList(arguments));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
			conditionalResult
					.setRightOperand(variable.isHalfAdjust() ? CustomToMethod(customFunction) : newCondicional);
			conditionalResult.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(conditionalResult);
			newStatement = newExpressionStatement;
			// newStatement.setTag("SubStatement");
		} else if (statement instanceof SubDurStatement) {
			SubDurStatement variable = (SubDurStatement) statement;
			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));

			CustomFunction customFunction = new CustomFunction(variable.getDuration());
			Factor[] args = { variable.getRightOperand() };
			List<Factor> arguments = Arrays.asList(args);
			customFunction.setArguments(arguments);
			FunctionCallExpression functionCall = CustomToMethod(customFunction);
			newCondicional.setRightOperand(functionCall);
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResultingDate()));
			conditionalResult.setRightOperand(newCondicional);
			conditionalResult.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(conditionalResult);
			newStatement = newExpressionStatement;
			// newStatement.setTag("SubStatement");
		} else if (statement instanceof SubstStatement) {
			SubstStatement variable = (SubstStatement) statement;
			Factor[] listFactor = { variable.getLengthValue(), variable.getSource(), variable.getStartValue(),
					variable.getTarget() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("SubstStatement");
		} else if (statement instanceof TagStatement) {
			TagStatement variable = (TagStatement) statement;
			Factor[] listFactor = { variable.getLabel() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("TagStatement");
		} else if (statement instanceof TestStatement) {
			TestStatement variable = (TestStatement) statement;
			Factor[] listFactor = { variable.getResult(), variable.getFormat() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("TestStatement");
		} else if (statement instanceof TestBStatement) {
			TestBStatement variable = (TestBStatement) statement;
			Factor[] listFactor = { variable.getResult(), variable.getTestBits() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("TestBStatement");
		} else if (statement instanceof TestNStatement) {
			TestNStatement variable = (TestNStatement) statement;
			Factor[] listFactor = { variable.getResult() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("TestNStatement");
		} else if (statement instanceof TestZStatement) {
			TestZStatement variable = (TestZStatement) statement;
			Factor[] listFactor = { variable.getResult() };
			newStatement = createStatement(listFactor, statement);
			newStatement.setTag("TestZStatement");
		} else if (statement instanceof TimeStatement) {
			TimeStatement variable = (TimeStatement) statement;
			Factor[] listFactor = { variable.getResult() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("TimeStatement");
		} else if (statement instanceof UnlockStatement) {
			UnlockStatement variable = (UnlockStatement) statement;
			Factor[] listFactor = { variable.getUnlockArea() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("UnlockStatement");
		} else if (statement instanceof UpdateStatement) {
			UpdateStatement variable = (UpdateStatement) statement;
			Factor[] listFactor = { variable.getName(), variable.getDataStructure() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("UpdateStatement");
		} else if (statement instanceof WhenStatement) {

			// newStatement.setTag("WhenStatement");
		} else if (statement instanceof WriteStatement) {
			WriteStatement variable = (WriteStatement) statement;
			Factor[] listFactor = { variable.getName(), variable.getDataStructure() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("WriteStatement");
		} else if (statement instanceof XFootStatement) {
			XFootStatement variable = (XFootStatement) statement;
			Factor[] listFactor = { variable.getSourceArray(), variable.getResult() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("XFootStatement");
		} else if (statement instanceof XLateStatement) {
			XLateStatement variable = (XLateStatement) statement;
			Factor[] listFactor = { variable.getFromValue(), variable.getToValue(), variable.getSource(),
					variable.getStartPosition(), variable.getTarget() };
			newStatement = createStatement(listFactor, statement);
			// newStatement.setTag("XLateStatement");
		} else if (statement instanceof ZAddStatement) {
			ZAddStatement variable = (ZAddStatement) statement;
			CustomFunction customFunction = getHExtensor();
			BinaryExpression conditionalResult = new BinaryExpression();
			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

			Factor fact = new ParenthesizedFactor(variable.getLeftOperand(), variable.getRightOperand(),
					variable.getOperand());
			Factor[] arguments = { fact };
			customFunction.setArguments(Arrays.asList(arguments));

			conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
			conditionalResult
					.setRightOperand(variable.isHalfAdjust() ? CustomToMethod(customFunction) : newCondicional);
			conditionalResult.setOperator(new Assign());

			ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
			newExpressionStatement.setExpression(conditionalResult);
			newStatement = newExpressionStatement;
			newStatement.setTag("ZAddStatement");
		} else if (statement instanceof ZSubStatement) {
            ZSubStatement variable = (ZSubStatement) statement;
            CustomFunction customFunction = getHExtensor();
            BinaryExpression conditionalResult = new BinaryExpression();
            BinaryExpression newCondicional = new BinaryExpression();
            newCondicional.setLeftOperand(FactorToExpression(variable.getLeftOperand()));
            newCondicional.setRightOperand(FactorToExpression(variable.getRightOperand()));
            newCondicional.setOperator(OperatorToOperator(variable.getOperand()));

            Factor fact = new ParenthesizedFactor(variable.getLeftOperand(), variable.getRightOperand(),
                    variable.getOperand());
            Factor[] arguments = {fact};
            customFunction.setArguments(Arrays.asList(arguments));

            conditionalResult.setLeftOperand(FactorToExpression(variable.getResult()));
            conditionalResult
                    .setRightOperand(variable.isHalfAdjust() ? CustomToMethod(customFunction) : newCondicional);
            conditionalResult.setOperator(new Assign());

            ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
            newExpressionStatement.setExpression(conditionalResult);
            newStatement = newExpressionStatement;
            newStatement.setTag("ZSubStatement");
            /*
             * } else if (statement instanceof DTypeStatement) { CommandExecution
             * newVariable = new CommandExecution();
             * newVariable.setCStatement(DTypeToStatement((DTypeStatement) statement));
             * newStatement = newVariable; newStatement.setTag("DTypeStatement");
             */
        } else if(statement instanceof HTypeStatement) {
			HTypeStatement variable = (HTypeStatement) statement;
			if (variable.getListKeywords().size() > 0) {
				ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> listStatement = new ArrayList<>();
				for (int i = 0; i < variable.getListKeywords().size(); i++) {
					KeywordFactor singleKey = (KeywordFactor) variable.getListKeywords().get(i);
					MemberAccess member = new MemberAccess();
					Literal literalValue = new StringLiteral();
					literalValue.setValue(singleKey.getKeyword());
					member.setIdentifierName(literalValue);
					Literal literalName = new StringLiteral();
					try {
						literalName.setValue(singleKey.getArgumentList().get(0).getValue());
					} catch (Exception e) {
						//e.printStackTrace();
					}
					member.setQualifiedName(literalName);
					ExpressionStatement expressionStatement = new ExpressionStatement();
					expressionStatement.setExpression(member);
					listStatement.add((ASTMCore.ASTMSyntax.Statement.Statement) expressionStatement);
				}
				BlockStatement blockStatement = new BlockStatement();
				blockStatement.setSubStatements(listStatement);
				newStatement = blockStatement;
			}
		} else if(statement instanceof FTypeStatement){
			FTypeStatement variable = (FTypeStatement) statement;
			Literal literal = new StringLiteral();
			literal.setValue(variable.getFileName());
			ExpressionStatement expressionStatement = new ExpressionStatement();
			expressionStatement.setExpression(literal);
			newStatement = expressionStatement;
		} else if(statement instanceof DTypeStatement){
			DTypeStatement variable = (DTypeStatement) statement;
			if(variable.getListKeywords().size() > 0){
				KeywordFactor specificKeyword = (KeywordFactor) variable.getListKeywords().get(0);
				switch (specificKeyword.getKeyword()){
					case "Dim":
						Literal arraySize = new IntegerLiteral();
						arraySize.setValue(specificKeyword.getArgumentList().get(0).getValue());
						List<Expression> list = new ArrayList<>();
						list.add(arraySize);

						ASTMCore.ASTMSyntax.Expression.ArrayCreation newArray = new ASTMCore.ASTMSyntax.Expression.ArrayCreation();
						newArray.setDimension(list);

						BinaryExpression arrayCreation = new BinaryExpression();

						arrayCreation.setLeftOperand(FactorToExpression(new LiteralFactor(variable.getFileName())));
						arrayCreation.setRightOperand(newArray);
						arrayCreation.setOperator(new Assign());

						ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
						newExpressionStatement.setExpression(arrayCreation);
						newStatement = newExpressionStatement;
						break;
					case "Const":
						BinaryExpression constCreation = new BinaryExpression();

						constCreation.setLeftOperand(FactorToExpression(new LiteralFactor(variable.getFileName())));
						try {
							constCreation.setRightOperand(FactorToExpression(new LiteralFactor(((KeywordFactor) variable.getListKeywords().get(0)).getArgumentList().get(0).getValue())));
						} catch (Exception e) {
							ElemFunction functionName = (ElemFunction) (((KeywordFactor) variable.getListKeywords().get(0)).getArgumentList().get(0));
							List<Factor> listArguments = new ArrayList<>();
							listArguments.add(functionName.getDataStructure());
							constCreation.setRightOperand(FactorToExpression(new FunctionFactor(functionName.getFunctionName(), listArguments)));
						}

						constCreation.setOperator(new Assign());

						ASTMCore.ASTMSyntax.Statement.ExpressionStatement newConstExpression = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
						newConstExpression.setExpression(constCreation);
						newStatement = newConstExpression;
					default:
						break;
				}
			}
		}
		return newStatement;
	}

	/**
	 * Realiza el mapeo de un factor de RPG a una expresion del GAST segun
	 * corresponda
	 *
	 * @param factor factor de RPG
	 * @return expresion del GAST
	 */
	default ASTMCore.ASTMSyntax.Expression.Expression FactorToExpression(Factor factor) {
		ASTMCore.ASTMSyntax.Expression.Expression newexpression = null;

		if (factor instanceof LiteralFactor) {
			newexpression = LiteralToLiteralFactor((LiteralFactor) factor);
		} else if (factor instanceof ComparisonFactor) {
			ComparisonFactor comparisonFactor = (ComparisonFactor) factor;

			BinaryExpression newCondicional = new BinaryExpression();
			newCondicional.setLeftOperand(FactorToExpression(comparisonFactor.getLeftOperand()));
			newCondicional.setRightOperand(FactorToExpression(comparisonFactor.getRightOperand()));
			newCondicional.setOperator(OperatorToOperator(comparisonFactor.getOperand()));
			newexpression = newCondicional;
			newexpression.setTag("comparisonFactor");
		} else if (factor instanceof NegateFactor){
			NegateFactor newFactor = (NegateFactor) factor;
			PrefixUnaryExpression expression = new PrefixUnaryExpression();
			UnaryOperator notOperator = new Not();
			notOperator.setSimbolo("!");
			expression.setOperator(notOperator);
			Literal literal = new StringLiteral();
			if(newFactor.getFactor() instanceof EofFunction){
				literal.setValue(((EofFunction) newFactor.getFactor()).getFunctionName());
			}else if(newFactor.getFactor() instanceof ErrorFunction){
				literal.setValue(((ErrorFunction) newFactor.getFactor()).getFunctionName());
			}
			expression.setOperand(literal);
			newexpression = expression;
		} else if (factor instanceof ArrayFactor) {
			ArrayFactor newFactor = (ArrayFactor) factor;
			ASTMCore.ASTMSyntax.Expression.ArrayAccess arrayFactor = new ASTMCore.ASTMSyntax.Expression.ArrayAccess();
			StringLiteral newLiteral = new StringLiteral();
			newLiteral.setValue(newFactor.getValue());
			arrayFactor.setArrayName(newLiteral);
			arrayFactor.setIndex(FactorToExpression(((ArrayFactor) factor).getIndex()));
			newexpression = arrayFactor;
			// newexpression.setTag("arrayFactor");
		} else if (factor instanceof ParenthesizedFactor) {
			newexpression = ParenthesizedToParenthesized((ParenthesizedFactor) factor);
		} else if (factor instanceof KeywordFactor) {
			// newexpression = KeywordFactorToKeyword((KeywordFactor) factor);
		} else if (factor instanceof CustomFunction) {
			CustomFunction newFactor = (CustomFunction) factor;
			FunctionCallExpression newMethodInvocation = CustomToMethod(newFactor);
			if (((CustomFunction) factor).getArguments() != null) {
				List<Factor> parameters = ((CustomFunction) factor).getArguments();
				ArrayList<ActualParameter> actualParam = new ArrayList<>();
				for (int i = 0; i < parameters.size(); i++) {
					ActualParameterExpression newParametre = new ActualParameterExpression();
					newParametre.setValue(FactorToExpression(parameters.get(i)));
					actualParam.add(newParametre);
				}
				newMethodInvocation.setActualParams(actualParam);
			}
			newexpression = newMethodInvocation;
		} else if (factor instanceof  FunctionFactor){
			Name functionName = new Name(((FunctionFactor) factor).getFunctionName());
			FunctionCallExpression newMethodInvocation = new FunctionCallExpression();
			newMethodInvocation.setFunctionName(functionName);
			if (((FunctionFactor) factor).getArgumentList() != null) {
				List<Factor> parameters = ((FunctionFactor) factor).getArgumentList();
				ArrayList<ActualParameter> actualParam = new ArrayList<>();
				for (int i = 0; i < parameters.size(); i++) {
					ActualParameterExpression newParametre = new ActualParameterExpression();
					newParametre.setValue(FactorToExpression(parameters.get(i)));
					actualParam.add(newParametre);
				}
				newMethodInvocation.setActualParams(actualParam);
			}
			newexpression = newMethodInvocation;// retorna la llamada con todos los par�metros
			System.out.println();
		}
		return newexpression;
	}

	default SwitchCase convertSwitchCase(Statement statement){
		WhenStatement whenStatement = (WhenStatement) statement;
		SwitchCase switchCase = new SwitchCase();
		ArrayList<ASTMCore.ASTMSyntax.Statement.Statement> body = new ArrayList<>();
		for(int i = 0; i < whenStatement.getBody().getStatements().size(); i++){
			body.add(StatementToStatement(whenStatement.getBody().getStatements().get(i)));
		}
		switchCase.setEvaluation(FactorToExpression(whenStatement.getConditions()));
		switchCase.setBody(body);
		return switchCase;
	}

	default ActualParameter createParameter(Factor factor) {
		Literal parameter = new Literal();
		parameter.setValue(factor.getValue());
		ActualParameterExpression parameterExpression = new ActualParameterExpression();
		parameterExpression.setValue(parameter);
		return parameterExpression;
	}

	default ExpressionStatement createStatement(Factor[] listFactor, Statement statement) {
		ArrayList<ActualParameter> listParameters = new ArrayList<ActualParameter>();
		for (int i = 0; i < listFactor.length; i++) {
			if (listFactor[i] != null) {
				listParameters.add(createParameter(listFactor[i]));
			}
		}
		FunctionCallExpression newVariable = new FunctionCallExpression();
		newVariable.setActualParams(listParameters);
		newVariable.setFunctionName(new Name(statement.getClass().getSimpleName().replace("Statement","")));
		ExpressionStatement newExpressionStatement = new ExpressionStatement();
		newExpressionStatement.setExpression(newVariable);

		return newExpressionStatement;
	}

	/**
	 * Mapeo un factor a un parametro del GAST
	 *
	 * @param factor factor de RPG
	 * @return parametro en el GAST
	 */
	default ActualParameter ParametrestoParametres(Factor factor) {
		ActualParameterExpression newParametre = new ActualParameterExpression();
		newParametre.setValue(FactorToExpression(factor));
		newParametre.setTag("actualParameter");
		return newParametre;
	}

	/**
	 * Mapeo un operador de RPG a uno en el GAST
	 *
	 * @param operand operador del RPG
	 * @return operador en el GAST
	 */
	@Mappings({ @Mapping(target = "tag", constant = "operand"), @Mapping(target = "simbol", source = "symbol"),
			@Mapping(target = "locationInfo", ignore = true), @Mapping(target = "annotations", ignore = true),
			@Mapping(target = "preProcessorElements", ignore = true) })
	GenericOperator OperatorToOperator(Operand operand);

	/**
	 * Convierte un factor literal de RPG a un literal del GAST
	 *
	 * @param literalFactor
	 * @return
	 */
	@Mappings({ @Mapping(target = "tag", constant = "literalFactor"), @Mapping(target = "locationInfo", ignore = true),
			@Mapping(target = "expressionType", ignore = true), @Mapping(target = "annotations", ignore = true),
			@Mapping(target = "preProcessorElements", ignore = true) })
	Literal LiteralToLiteralFactor(LiteralFactor literalFactor);

	/**
	 * Realiza el mapeo de un elemento statement CustomFunction de RPG al GAST
	 *
	 * @param statement statement en RPG
	 * @return estructura en el GAST
	 */
	@Mappings({ @Mapping(target = "functionName.nameString", source = "functionName"),
			//@Mapping(target = "actualParams", source = "arguments"),
			@Mapping(target = "tag", constant = "customFunction"), @Mapping(target = "locationInfo", ignore = true),
			@Mapping(target = "annotations", ignore = true), @Mapping(target = "preProcessorElements", ignore = true) })
	FunctionCallExpression CustomToMethod(CustomFunction statement);

	/**
	 * Realiza el mapeo de un elemento statement SetOff de RPG al GAST
	 *
	 * @param statement statement en RPG
	 * @return estructura en el GAST
	 */
	@Mappings({ @Mapping(target = "subStatements", source = "definitionIndicators") })
	BlockStatement SetOffStatements(SetOffStatement statement);

	/**
	 * Realiza el mapeo de un elemento statement SetOn de RPG al GAST
	 *
	 * @param statement statement en RPG
	 * @return estructura en el GAST
	 */
	@Mappings({ @Mapping(target = "subStatements", source = "definitionIndicators") })
	BlockStatement SetOnStatements(SetOnStatement statement);

	/**
	 * Realiza el mapeo de un elemento statement ParenthesizedFactor de RPG al GAST
	 *
	 * @param statement en RPG
	 * @return estructura en el GAST
	 */
	@Mappings({ /*@Mapping(target = "value.value", source = "value"), @Mapping(target = "type.value", source = "type"),
			@Mapping(target = "tag", constant = "parenthesizedFactor"),
			@Mapping(target = "locationInfo", ignore = true), @Mapping(target = "expressionType", ignore = true),
			@Mapping(target = "annotations", ignore = true),*/ @Mapping(target = "preProcessorElements", ignore = true) })
	ASTMCore.ASTMSyntax.Expression.ParenthesizedExpression ParenthesizedToParenthesized(ParenthesizedFactor factor);

	/**
	 * Realiza el mapeo de un elemento statement Operand de RPG al GAST
	 *
	 * @param statement statement en RPG
	 * @return estructura en el GAST
	 */
	@Mappings({ @Mapping(target = "value", source = "symbol"), @Mapping(target = "tag", constant = "operand"),
			@Mapping(target = "locationInfo", ignore = true), @Mapping(target = "expressionType", ignore = true),
			@Mapping(target = "annotations", ignore = true), @Mapping(target = "preProcessorElements", ignore = true) })
	Literal OperandToOperand(Operand operand);


	/**
	 * Realiza el mapeo de un elemento statement CallPStatement de RPG al GAST
	 *
	 * @param statement statement en RPG
	 * @return estructura en el GAST
	 */
	@Mappings({ @Mapping(target = "functionName.nameString", source = "procedureName"),
			//@Mapping(target = "actualParams", source = "params"),
			@Mapping(target = "tag", constant = "callP"),
			@Mapping(target = "locationInfo", ignore = true), @Mapping(target = "annotations", ignore = true),
			@Mapping(target = "preProcessorElements", ignore = true) })
	FunctionCallExpression CallPToCommand(CallPStatement statement);

}
