package ASTMCore.visitor;

import ASTMCore.ASTMSemantics.*;
import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.*;
import ASTMCore.ASTMSyntax.Expression.*;
import ASTMCore.ASTMSyntax.Statement.*;
import ASTMCore.ASTMSyntax.Types.*;

/**
 * This interface specifies the required methods to visit all of the objects related to the GAST.
 * @author AVIB team.
 */
public interface GASTVisitorObject {
	
	/**
	 * Method to visit the compilation unit, this is the beginning of the AST, it represents the file.
	 * @param compilationUnit The compilation unit (file with code) object, is like the root of the AST.
	 */
	Object visitCompilationUnit(CompilationUnit compilationUnit);


	//**************//
	//* Definition *//
	//**************//
	/**
	 * Method to visit an aggregate type definition (classes...).
	 * @param aggregateTypeDefinition The aggregate type definition object in the GAST.
	 * @return 
	 */
	Object visitAggregateTypeDefinition(AggregateTypeDefinition aggregateTypeDefinition);
	
	/**
	 * Method to visit an enum type definition (enums...).
	 * @param enumTypeDefinition The enum type definition object in the GAST.
	 */
	Object visitEnumTypeDefinition(EnumTypeDefinition enumTypeDefinition);
	
	/**
	 * Method to visit an enum literal definition (enum literal...).
	 * @param enumLiteralDefinition The enum literal definition object in the GAST.
	 * @return 
	 */
	Object visitEnumLiteralDefinition(EnumLiteralDefinition enumLiteralDefinition);
	
	/**
	 * Method to visit a function definition (methods...).
	 * @param functionDefintion The function definition object in the GAST.
	 * @return 
	 */
	Object visitFunctionDefintion(FunctionDefintion functionDefintion);
	
	/**
	 * Method to visit a formal parameter definition (parameters for methods).
	 * @param formalParameterDefinition The formal parameter definition object in the GAST.
	 * @return 
	 */
	Object visitFormalParameterDefinition(FormalParameterDefinition formalParameterDefinition);
	
	/**
	 * Method to visit a name space definition (namespaces, packages...).
	 * @param nameSpaceDefinition The name space definition object in the GAST.
	 */
	Object visitNameSpaceDefinition(NameSpaceDefinition nameSpaceDefinition);
	
	/**
	 * Method to visit a label definition (for labeled statements).
	 * @param labelDefinition The label definition object in the GAST.
	 * @return 
	 */
	Object visitLabelDefinition(LabelDefinition labelDefinition);
	
	
	//***************//
	//* Declaration *//
	//***************//
	/**
	 * Method to visit an import declaration (imports). 
	 * @param importDeclaration The import declaration object in the GAST.
	 * @return 
	 */
	Object visitImportDeclaration(ImportDeclaration importDeclaration);
	

	//**********//
	//* Scopes *//
	//**********//
	// TODO: Most of the scopes are visited in the same way. So, is possible to factorize this function
	//  to just one, to visit the general class Scope.
	
	/**
	 * Method to visit the scope of a program (like files).
	 * @param programScope The program scope object in the GAST.
	 */
	Object visitProgramScope(ProgramScope programScope);
	
	/**
	 * Method to visit the scope of a aggregate type (like classes).
	 * @param aggregateScope The aggregate type scope object in the GAST.
	 * @return 
	 */
	Object visitAggregateScope(AggregateScope aggregateScope);
	
	
	//*****************************//
	//* Classes and enums (types) *//
	//*****************************//
	/**
	 * General method to visit a class.
	 * @param classType The class type object in the GAST. It contains all of the data
	 * required to model a class.
	 * @return 
	 */
	Object visitClassType(ClassType classType);
	
	/**
	 * General method to visit a enum.
	 * @param enumType The enum type object in the GAST. It contains all of the data
	 * required to model a enum.
	 * @return 
	 */
	Object visitEnumType(EnumType enumType);
	
	/**
	 * General method to visit the members of the class (attributes).
	 * @param memberObject The member object definition in the GAST. It contains all of the data
	 * required to model a class.
	 * @return 
	 */
	Object visitMemberObject(MemberObject memberObject);
	
	
	//*******************//
	//* Data definition *//
	//*******************//
	/**
	 * General method to visit a variable definition. Used to represent an attribute for a Java class. 
	 * @param variableDefinition The variable definition in the GAST. It contains all of the data
	 * required to model an attribute.
	 * @return 
	 */
	Object visitVariableDefinition(VariableDefinition variableDefinition);
	
	/**
	 * General method to visit a Fragment, this is the part on a variable declaration that specifies the name and the
	 * optional initial value: "int [value = 4]".
	 * @param fragment The fragment definition in the GAST.
	 * @return 
	 */
	Object visitFragment(Fragment fragment);


	//***************//
	//* Inheritance *//
	//***************//
	/**
	 * Method to visit a DerivesFrom object, here is the reference to a Type object, the class or interface from which it
	 * is inherited
	 * @param derivesFrom The derives from definition in the GAST.
	 */
	Object visitDerivesFrom(DerivesFrom derivesFrom);
	
	//**************//
	//* Interfaces *//
	//**************//
	/**
	 * Method to visit a ImplementsTo object, it contains the reference to a Type Object, the interface to be implemented.
	 * @param implementsTo The implements to definition in the GAST.
	 */
	Object visitImplementsTo(ImplementsTo implementsTo);
	
	//*********//
	//* Types *//
	//*********//
	/**
	 * Method to visit a named type reference (types: int, Float, ArrayList...)
	 * @param namedTypeReference The named type reference definition in the GAST.
	 * @return 
	 */
	Object visitNamedTypeReference(NamedTypeReference namedTypeReference);
	
	/**
	 * Method to visit a parameterized type (types that can be parameterized: ArrayList<Integer>)
	 * @param parameterizedType The parameterized type reference definition in the GAST.
	 */
	Object visitParameterizedType(ParameterizedType parameterizedType);
	
	/**
	 * Method to vist an array type (an array type Object[])
	 * @param arrayType The array type definition in the GAST.
	 */
	Object visitArrayType(ArrayType arrayType);
	
	/**
	 * Method to visit a type parameter (dinamic types in java classes...).
	 * @param typeParameter The type parameter definition in the GAST.
	 */
	Object visitTypeParameter(TypeParameter typeParameter);
	
	
	//*************//
	//* Statemets *//
	//*************//
	/**
	 * Method to visit a block statement (a block of statements)
	 * @param blockStatement The block statement definition in the GAST.
	 * @return 
	 */
	Object visitBlockStatement(BlockStatement blockStatement);
	Object visitLabeledStatement(LabeledStatement labeledStatement);
	Object visitEmptyStatement(EmptyStatement emptyStatement);
	Object visitIfStatement(IfStatement ifStatement);
	Object visitWhileStatement(WhileStatement whileStatement);
	Object visitDoWhileStatement(DoWhileStatement doWhileStatement);
	Object visitContinueStatement(ContinueStatement continueStatement);
	Object visitBreakStatement(BreakStatement breakStatement);
	Object visitReturnStatement(ReturnStatement returnStatement);
	Object visitThrowStatement(ThrowStatement throwStatement);
	Object visitExpressionStatement(ExpressionStatement expressionStatement);
	Object visitForCheckAfterStatement(ForCheckAfterStatement forCheckAfterStatement);
	Object visitForIterator(ForIterator forIterator);
	Object visitTryStatement(TryStatement tryStatement);
	Object visitCatchBlock(CatchBlock catchBlock);
	Object visitSwitchStatement(SwitchStatement switchStatement);
	Object visitSwitchCase(SwitchCase switchCase);
	Object visitCaseBlock(CaseBlock caseBlock);
	Object visitDefaultBlock(DefaultBlock defaultBlock);
	Object visitAssertStatement(AssertStatement assertStatement);
	Object visitSynchronizedStatement(SynchronizedStatement synchronizedStatement);
	Object visitDeclarationOrDefinitionStatement(DeclarationOrDefinitionStatement declarationOrDefinitionStatement);
	Object visitSuperInvocation(SuperInvocation superInvocation);
	Object visitCheckedStatement(CheckedStatement checkedStatement);
	Object visitUncheckedStatement(UncheckedStatement uncheckedStatement);
	
	
	/*
    TO DO:
    try ResourceSpecification Block [Catches] [Finally]
	 */
	

	
	//***************//
	//* Expressions *//
	//***************//
	/*
	TODO:
	EnumLiteral
	BitLiteral
	IntegerLiteral are not used currently.
	AddressOf
	AggregateExpression
	AnnotationExpression
	ByReferenceActualParameterExpression
	ByValueActualParameterExpression
	Deref
	Exponent
	MemberValuePair
	MissingActualParameter
	NameReference
	NormalAnnotation
	Point
	QualifiedIdentifierReference
	QualifiedOverPtr
	RangeExpression
	TypeQualifiedIdentifierReference
	VariableExpression
	LamdbaExpression
	*/
	
	//**********************//
	//* Binary expressions *//
	//**********************//
	Object visitBinaryExpression(BinaryExpression binaryExpression);
	Object visitMultiply(Multiply multiply);
	Object visitModulus(Modulus modulus);
	Object visitDivide(Divide divide);
	Object visitAdd(Add add);
	Object visitSubtract(Subtract subtract);
	Object visitBitLeftShift(BitLeftShift bitLeftShift);
	Object visitBitSignedRightShift(BitSignedRightShift bitSignedRightShift);
	Object visitBitUnsignedRightShift(BitUnsignedRightShift bitUnsignedRightShift);
	Object visitLess(Less less);
	Object visitGreater(Greater greater);
	Object visitNotGreater(NotGreater greater);
	Object visitNotLess(NotLess less);
	Object visitEqual(Equal equal);
	Object visitNotEqual(NotEqual notEqual);
	Object visitBitAnd(BitAnd bitAnd);
	Object visitBitXor(BitXor bitXor);
	Object visitBitOr(BitOr bitOr);
	Object visitAnd(And and);
	Object visitOr(Or or);
	Object visitAssign(Assign assign);
	Object visitAssignMultiply(AssignMultiply assignMultiply);
	Object visitAssignDivide(AssignDivide assignDivide);
	Object visitAssignModulus(AssignModulus assignModulus);
	Object visitAssignAdd(AssignAdd assignAdd);
	Object visitAssignSubtract(AssignSubtract assignSubtract);
	Object visitAssignBitLeftShift(AssignBitLeftShift assignBitLeftShift);
	Object visitAssignBitSignedRightShift(AssignBitSignedRightShift assignBitSignedRightShift);
	Object visitAssignBitUnsignedRightShift(AssignBitUnsignedRightShift assignBitUnsignedRightShift);
	Object visitAssignBitAnd(AssignBitAnd assignBitAnd);
	Object visitAssignBitXor(AssignBitXor assignBitXor);
	Object visitAssignBitOr(AssignBitOr assignBitOr);
	Object visitGenericOperator(GenericOperator genericOperator);
	Object visitInstanceOfExpression(InstanceOfExpression instanceOfExpression);
	
	//*********************//
	//* Unary expressions *//
	//*********************//
	Object visitPrefixUnaryExpression(PrefixUnaryExpression prefixUnaryExpression);
	Object visitPostfixUnaryExpression(PostfixUnaryExpression postfixUnaryExpression);
	Object visitPostIncrement(PostIncrement postIncrement);
	Object visitPostDecrement(PostDecrement postDecrement);
	Object visitUnaryPlus(UnaryPlus unaryPlus);
	Object visitUnaryMinus(UnaryMinus unaryMinus);
	Object visitIncrement(Increment increment);
	Object visitDecrement(Decrement decrement);
	Object visitBitNot(BitNot bitNot);
	Object visitNot(Not not);
	Object visitDefaultUnaryOperator(DefaultUnaryOperator defaultUnaryOperator);
	Object visitCastExpression(CastExpression castExpression);
	
	//***********************//
	//* Primary expressions *//
	//***********************//
	/*
	Object accept(IEncoder v) {
		v.visit(this, frame);
	}
	Object visit();
	*/
	/*
	Literal
	EnumLiteral
	BitLiteral
	*/
	Object visitBooleanLiteral(BooleanLiteral booleanLiteral);
	Object visitCharLiteral(CharLiteral charLiteral);
	Object visitIntegerLiteral(IntegerLiteral integerLiteral);
	Object visitStringLiteral(StringLiteral stringLiteral);
	Object visitRealLiteral(RealLiteral realLiteral);
	Object visitNullLiteral(NullLiteral nullLiteral);
	Object visitIdentifierReference(IdentifierReference identifierReference);
	Object visitParenthesizedExpression(ParenthesizedExpression parenthesizedExpression);
	
	//******************//
	//* Function calls *//
	//******************//
	Object visitFunctionCallExpression(FunctionCallExpression functionCallExpression);
	Object visitActualParameterExpression(ActualParameterExpression actualParameterExpression);
	Object visitSuperMethodInvocation(SuperMethodInvocation superMethodInvocation);
	
	//*********************//
	//* Array expressions *//
	//*********************//
	Object visitArrayCreation(ArrayCreation arrayCreation);
	Object visitCollectionExpression(CollectionExpression collectionExpression);
	Object visitArrayAccess(ArrayAccess arrayAccess);
	
	
	//*********************//
	//* Other expressions *//
	//*********************//
	Object visitConditionalExpression(ConditionalExpression conditionalExpression);
	Object visitNewExpression(NewExpression newExpression);
	Object visitQualifiedOverData(QualifiedOverData qualifiedOverData);
	Object visitLabelAccess(LabelAccess labelAccess);
	Object visitMemberAccess(MemberAccess memberAccess);
	Object visitSuperMemberAccess(SuperMemberAccess superMemberAccess);
	Object visitVariableExpression(VariableExpression variableExpression);
	Object visitPointerAccess(PointerAccess pointerAccess);
	Object visitCheckedExpression(CheckedExpression checkedExpression);
	Object visitUncheckedExpression(UncheckedExpression uncheckedExpression);
	
	
	//********//
	//* Name *//
	//********//
	Object visitName(Name name);
	
	
	//*************//
	//* Modifiers *//
	//*************//
	Object visitAbstractModifier(AbstractModifier abstractModifier);

	Object visitAnnotationModifier(AnnotationModifier annotationModifier);

	Object visitDefaultModifier(DefaultModifier defaultModifier);

	Object visitFinalModifier(FinalModifier finalModifier);

	Object visitNativeModifier(NativeModifier nativeModifier);

	Object visitPrivateModifier(PrivateModifier privateModifier);

	Object visitProtectedModifier(ProtectedModifier protectedModifier);

	Object visitPublicModifier(PublicModifier sublicModifier);

	Object visitSingleMemberAnnotation(SingleMemberAnnotation singleMemberAnnotation);

	Object visitStaticModifier(StaticModifier staticModifier);

	Object visitStrictfpModifier(StrictfpModifier strictfpModifier);

	Object visitSynchronizedModifier(SynchronizedModifier synchronizedModifier);

	Object visitTransientModifier(TransientModifier transientModifier);

	Object visitVolatileModifier(VolatileModifier volatileModifier);

	Object visitRefModifier(RefModifier refModifier);
}