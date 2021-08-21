package ASTMCore.visitor;

import ASTMCore.ASTMSemantics.*;
import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.*;
import ASTMCore.ASTMSyntax.Expression.*;
import ASTMCore.ASTMSyntax.Statement.*;
import ASTMCore.ASTMSyntax.Types.*;

/**
 * This interface specifies the required methods to visit all of the voids related to the GAST.
 * @author AVIB team.
 */
public interface GASTVisitor {
	
	/**
	 * Method to visit the compilation unit, this is the beginning of the AST, it represents the file.
	 * @param compilationUnit The compilation unit (file with code) void, is like the root of the AST.
	 */
	void visitCompilationUnit(CompilationUnit compilationUnit);


	//**************//
	//* Definition *//
	//**************//
	/**
	 * Method to visit an aggregate type definition (classes...).
	 * @param aggregateTypeDefinition The aggregate type definition void in the GAST.
	 * @return 
	 */
	void visitAggregateTypeDefinition(AggregateTypeDefinition aggregateTypeDefinition);
	
	/**
	 * Method to visit an enum type definition (enums...).
	 * @param enumTypeDefinition The enum type definition void in the GAST.
	 */
	void visitEnumTypeDefinition(EnumTypeDefinition enumTypeDefinition);
	
	/**
	 * Method to visit an enum literal definition (enum literal...).
	 * @param enumLiteralDefinition The enum literal definition void in the GAST.
	 * @return 
	 */
	void visitEnumLiteralDefinition(EnumLiteralDefinition enumLiteralDefinition);
	
	/**
	 * Method to visit a function definition (methods...).
	 * @param functionDefintion The function definition void in the GAST.
	 * @return 
	 */
	void visitFunctionDefintion(FunctionDefintion functionDefintion);
	
	/**
	 * Method to visit a formal parameter definition (parameters for methods).
	 * @param formalParameterDefinition The formal parameter definition void in the GAST.
	 * @return 
	 */
	void visitFormalParameterDefinition(FormalParameterDefinition formalParameterDefinition);
	
	/**
	 * Method to visit a name space definition (namespaces, packages...).
	 * @param nameSpaceDefinition The name space definition void in the GAST.
	 */
	void visitNameSpaceDefinition(NameSpaceDefinition nameSpaceDefinition);
	
	/**
	 * Method to visit a label definition (for labeled statements).
	 * @param labelDefinition The label definition void in the GAST.
	 * @return 
	 */
	void visitLabelDefinition(LabelDefinition labelDefinition);
	
	
	//***************//
	//* Declaration *//
	//***************//
	/**
	 * Method to visit an import declaration (imports). 
	 * @param importDeclaration The import declaration void in the GAST.
	 * @return 
	 */
	void visitImportDeclaration(ImportDeclaration importDeclaration);
	

	//**********//
	//* Scopes *//
	//**********//
	// TODO: Most of the scopes are visited in the same way. So, is possible to factorize this function
	//  to just one, to visit the general class Scope.
	
	/**
	 * Method to visit the scope of a program (like files).
	 * @param programScope The program scope void in the GAST.
	 */
	void visitProgramScope(ProgramScope programScope);
	
	/**
	 * Method to visit the scope of a aggregate type (like classes).
	 * @param aggregateScope The aggregate type scope void in the GAST.
	 * @return 
	 */
	void visitAggregateScope(AggregateScope aggregateScope);
	
	
	//*****************************//
	//* Classes and enums (types) *//
	//*****************************//
	/**
	 * General method to visit a class.
	 * @param classType The class type void in the GAST. It contains all of the data
	 * required to model a class.
	 * @return 
	 */
	void visitClassType(ClassType classType);
	
	/**
	 * General method to visit a enum.
	 * @param enumType The enum type void in the GAST. It contains all of the data
	 * required to model a enum.
	 * @return 
	 */
	void visitEnumType(EnumType enumType);
	
	/**
	 * General method to visit the members of the class (attributes).
	 * @param membervoid The member void definition in the GAST. It contains all of the data
	 * required to model a class.
	 * @return 
	 */
	void visitMemberObject(MemberObject memberObject);
	
	
	//*******************//
	//* Data definition *//
	//*******************//
	/**
	 * General method to visit a variable definition. Used to represent an attribute for a Java class. 
	 * @param variableDefinition The variable definition in the GAST. It contains all of the data
	 * required to model an attribute.
	 * @return 
	 */
	void visitVariableDefinition(VariableDefinition variableDefinition);
	
	/**
	 * General method to visit a Fragment, this is the part on a variable declaration that specifies the name and the
	 * optional initial value: "int [value = 4]".
	 * @param fragment The fragment definition in the GAST.
	 * @return 
	 */
	void visitFragment(Fragment fragment);


	//***************//
	//* Inheritance *//
	//***************//
	/**
	 * Method to visit a DerivesFrom void, here is the reference to a Type void, the class or interface from which it
	 * is inherited
	 * @param derivesFrom The derives from definition in the GAST.
	 */
	void visitDerivesFrom(DerivesFrom derivesFrom);
	
	//**************//
	//* Interfaces *//
	//**************//
	/**
	 * Method to visit a ImplementsTo void, it contains the reference to a Type void, the interface to be implemented.
	 * @param implementsTo The implements to definition in the GAST.
	 */
	void visitImplementsTo(ImplementsTo implementsTo);
	
	//*********//
	//* Types *//
	//*********//
	/**
	 * Method to visit a named type reference (types: int, Float, ArrayList...)
	 * @param namedTypeReference The named type reference definition in the GAST.
	 * @return 
	 */
	void visitNamedTypeReference(NamedTypeReference namedTypeReference);
	
	/**
	 * Method to visit a parameterized type (types that can be parameterized: ArrayList<Integer>)
	 * @param parameterizedType The parameterized type reference definition in the GAST.
	 */
	void visitParameterizedType(ParameterizedType parameterizedType);
	
	/**
	 * Method to vist an array type (an array type void[])
	 * @param arrayType The array type definition in the GAST.
	 */
	void visitArrayType(ArrayType arrayType);
	
	/**
	 * Method to visit a type parameter (dinamic types in java classes...).
	 * @param typeParameter The type parameter definition in the GAST.
	 */
	void visitTypeParameter(TypeParameter typeParameter);
	
	
	//*************//
	//* Statemets *//
	//*************//
	/**
	 * Method to visit a block statement (a block of statements)
	 * @param blockStatement The block statement definition in the GAST.
	 * @return 
	 */
	void visitBlockStatement(BlockStatement blockStatement);
	void visitLabeledStatement(LabeledStatement labeledStatement);
	void visitEmptyStatement(EmptyStatement emptyStatement);
	void visitIfStatement(IfStatement ifStatement);
	void visitWhileStatement(WhileStatement whileStatement);
	void visitDoWhileStatement(DoWhileStatement doWhileStatement);
	void visitContinueStatement(ContinueStatement continueStatement);
	void visitBreakStatement(BreakStatement breakStatement);
	void visitReturnStatement(ReturnStatement returnStatement);
	void visitThrowStatement(ThrowStatement throwStatement);
	void visitExpressionStatement(ExpressionStatement expressionStatement);
	void visitForCheckAfterStatement(ForCheckAfterStatement forCheckAfterStatement);
	void visitForIterator(ForIterator forIterator);
	void visitTryStatement(TryStatement tryStatement);
	void visitCatchBlock(CatchBlock catchBlock);
	void visitSwitchStatement(SwitchStatement switchStatement);
	void visitSwitchCase(SwitchCase switchCase);
	void visitCaseBlock(CaseBlock caseBlock);
	void visitDefaultBlock(DefaultBlock defaultBlock);
	void visitAssertStatement(AssertStatement assertStatement);
	void visitSynchronizedStatement(SynchronizedStatement synchronizedStatement);
	void visitDeclarationOrDefinitionStatement(DeclarationOrDefinitionStatement declarationOrDefinitionStatement);
	void visitSuperInvocation(SuperInvocation superInvocation);
	void visitCheckedStatement(CheckedStatement checkedStatement);
	void visitUncheckedStatement(UncheckedStatement uncheckedStatement);
	
	
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
	void visitBinaryExpression(BinaryExpression binaryExpression);
	void visitMultiply(Multiply multiply);
	void visitModulus(Modulus modulus);
	void visitDivide(Divide divide);
	void visitAdd(Add add);
	void visitSubtract(Subtract subtract);
	void visitBitLeftShift(BitLeftShift bitLeftShift);
	void visitBitSignedRightShift(BitSignedRightShift bitSignedRightShift);
	void visitBitUnsignedRightShift(BitUnsignedRightShift bitUnsignedRightShift);
	void visitLess(Less less);
	void visitGreater(Greater greater);
	void visitNotGreater(NotGreater greater);
	void visitNotLess(NotLess less);
	void visitEqual(Equal equal);
	void visitNotEqual(NotEqual notEqual);
	void visitBitAnd(BitAnd bitAnd);
	void visitBitXor(BitXor bitXor);
	void visitBitOr(BitOr bitOr);
	void visitAnd(And and);
	void visitOr(Or or);
	void visitAssign(Assign assign);
	void visitAssignMultiply(AssignMultiply assignMultiply);
	void visitAssignDivide(AssignDivide assignDivide);
	void visitAssignModulus(AssignModulus assignModulus);
	void visitAssignAdd(AssignAdd assignAdd);
	void visitAssignSubtract(AssignSubtract assignSubtract);
	void visitAssignBitLeftShift(AssignBitLeftShift assignBitLeftShift);
	void visitAssignBitSignedRightShift(AssignBitSignedRightShift assignBitSignedRightShift);
	void visitAssignBitUnsignedRightShift(AssignBitUnsignedRightShift assignBitUnsignedRightShift);
	void visitAssignBitAnd(AssignBitAnd assignBitAnd);
	void visitAssignBitXor(AssignBitXor assignBitXor);
	void visitAssignBitOr(AssignBitOr assignBitOr);
	void visitGenericOperator(GenericOperator genericOperator);
	void visitInstanceOfExpression(InstanceOfExpression instanceOfExpression);
	
	//*********************//
	//* Unary expressions *//
	//*********************//
	void visitPrefixUnaryExpression(PrefixUnaryExpression prefixUnaryExpression);
	void visitPostfixUnaryExpression(PostfixUnaryExpression postfixUnaryExpression);
	void visitPostIncrement(PostIncrement postIncrement);
	void visitPostDecrement(PostDecrement postDecrement);
	void visitUnaryPlus(UnaryPlus unaryPlus);
	void visitUnaryMinus(UnaryMinus unaryMinus);
	void visitIncrement(Increment increment);
	void visitDecrement(Decrement decrement);
	void visitBitNot(BitNot bitNot);
	void visitNot(Not not);
	void visitDefaultUnaryOperator(DefaultUnaryOperator defaultUnaryOperator);
	void visitCastExpression(CastExpression castExpression);
	
	//***********************//
	//* Primary expressions *//
	//***********************//
	/*
	void accept(IEncoder v) {
		v.visit(this, frame);
	}
	void visit();
	*/
	/*
	Literal
	EnumLiteral
	BitLiteral
	*/
	void visitBooleanLiteral(BooleanLiteral booleanLiteral);
	void visitCharLiteral(CharLiteral charLiteral);
	void visitIntegerLiteral(IntegerLiteral integerLiteral);
	void visitStringLiteral(StringLiteral stringLiteral);
	void visitRealLiteral(RealLiteral realLiteral);
	void visitNullLiteral(NullLiteral nullLiteral);
	void visitIdentifierReference(IdentifierReference identifierReference);
	void visitParenthesizedExpression(ParenthesizedExpression parenthesizedExpression);
	
	//******************//
	//* Function calls *//
	//******************//
	void visitFunctionCallExpression(FunctionCallExpression functionCallExpression);
	void visitActualParameterExpression(ActualParameterExpression actualParameterExpression);
	void visitSuperMethodInvocation(SuperMethodInvocation superMethodInvocation);
	
	//*********************//
	//* Array expressions *//
	//*********************//
	void visitArrayCreation(ArrayCreation arrayCreation);
	void visitCollectionExpression(CollectionExpression collectionExpression);
	void visitArrayAccess(ArrayAccess arrayAccess);
	
	
	//*********************//
	//* Other expressions *//
	//*********************//
	void visitConditionalExpression(ConditionalExpression conditionalExpression);
	void visitNewExpression(NewExpression newExpression);
	void visitQualifiedOverData(QualifiedOverData qualifiedOverData);
	void visitLabelAccess(LabelAccess labelAccess);
	void visitMemberAccess(MemberAccess memberAccess);
	void visitSuperMemberAccess(SuperMemberAccess superMemberAccess);
	void visitVariableExpression(VariableExpression variableExpression);
	void visitPointerAccess(PointerAccess pointerAccess);
	void visitCheckedExpression(CheckedExpression checkedExpression);
	void visitUncheckedExpression(UncheckedExpression uncheckedExpression);
	
	
	//********//
	//* Name *//
	//********//
	void visitName(Name name);
	
	
	//*************//
	//* Modifiers *//
	//*************//
	void visitAbstractModifier(AbstractModifier abstractModifier);

	void visitAnnotationModifier(AnnotationModifier annotationModifier);

	void visitDefaultModifier(DefaultModifier defaultModifier);

	void visitFinalModifier(FinalModifier finalModifier);

	void visitNativeModifier(NativeModifier nativeModifier);

	void visitPrivateModifier(PrivateModifier privateModifier);

	void visitProtectedModifier(ProtectedModifier protectedModifier);

	void visitPublicModifier(PublicModifier sublicModifier);

	void visitSingleMemberAnnotation(SingleMemberAnnotation singleMemberAnnotation);

	void visitStaticModifier(StaticModifier staticModifier);

	void visitStrictfpModifier(StrictfpModifier strictfpModifier);

	void visitSynchronizedModifier(SynchronizedModifier synchronizedModifier);

	void visitTransientModifier(TransientModifier transientModifier);

	void visitVolatileModifier(VolatileModifier volatileModifier);

	void visitRefModifier(RefModifier refModifier);
}
