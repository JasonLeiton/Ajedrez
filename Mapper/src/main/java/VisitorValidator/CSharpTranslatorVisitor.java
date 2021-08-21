package VisitorValidator;

import java.util.ArrayList;
import java.util.List;

import ASTMCore.ASTMSemantics.AggregateScope;
import ASTMCore.ASTMSemantics.ProgramScope;
import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSyntax.GASTMSyntaxObject;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.AbstractModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.AggregateTypeDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.AnnotationModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DefaultModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.DefintionObject;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.EnumLiteralDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.EnumTypeDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.FinalModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.FormalParameterDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Fragment;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.FunctionDefintion;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.ImportDeclaration;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.LabelDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.NameSpaceDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.NativeModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.PrivateModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.ProtectedModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.PublicModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.RefModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.SingleMemberAnnotation;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.StaticModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.StrictfpModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.SynchronizedModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.TransientModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.VariableDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.VolatileModifier;
import ASTMCore.ASTMSyntax.Expression.ActualParameter;
import ASTMCore.ASTMSyntax.Expression.ActualParameterExpression;
import ASTMCore.ASTMSyntax.Expression.Add;
import ASTMCore.ASTMSyntax.Expression.And;
import ASTMCore.ASTMSyntax.Expression.AnnotationExpression;
import ASTMCore.ASTMSyntax.Expression.ArrayAccess;
import ASTMCore.ASTMSyntax.Expression.ArrayCreation;
import ASTMCore.ASTMSyntax.Expression.Assign;
import ASTMCore.ASTMSyntax.Expression.AssignAdd;
import ASTMCore.ASTMSyntax.Expression.AssignBitAnd;
import ASTMCore.ASTMSyntax.Expression.AssignBitLeftShift;
import ASTMCore.ASTMSyntax.Expression.AssignBitOr;
import ASTMCore.ASTMSyntax.Expression.AssignBitSignedRightShift;
import ASTMCore.ASTMSyntax.Expression.AssignBitUnsignedRightShift;
import ASTMCore.ASTMSyntax.Expression.AssignBitXor;
import ASTMCore.ASTMSyntax.Expression.AssignDivide;
import ASTMCore.ASTMSyntax.Expression.AssignModulus;
import ASTMCore.ASTMSyntax.Expression.AssignMultiply;
import ASTMCore.ASTMSyntax.Expression.AssignSubtract;
import ASTMCore.ASTMSyntax.Expression.BinaryExpression;
import ASTMCore.ASTMSyntax.Expression.BinaryOperator;
import ASTMCore.ASTMSyntax.Expression.BitAnd;
import ASTMCore.ASTMSyntax.Expression.BitLeftShift;
import ASTMCore.ASTMSyntax.Expression.BitNot;
import ASTMCore.ASTMSyntax.Expression.BitOr;
import ASTMCore.ASTMSyntax.Expression.BitSignedRightShift;
import ASTMCore.ASTMSyntax.Expression.BitUnsignedRightShift;
import ASTMCore.ASTMSyntax.Expression.BitXor;
import ASTMCore.ASTMSyntax.Expression.BooleanLiteral;
import ASTMCore.ASTMSyntax.Expression.CastExpression;
import ASTMCore.ASTMSyntax.Expression.CharLiteral;
import ASTMCore.ASTMSyntax.Expression.CheckedExpression;
import ASTMCore.ASTMSyntax.Expression.CollectionExpression;
import ASTMCore.ASTMSyntax.Expression.ConditionalExpression;
import ASTMCore.ASTMSyntax.Expression.Decrement;
import ASTMCore.ASTMSyntax.Expression.DefaultUnaryOperator;
import ASTMCore.ASTMSyntax.Expression.Divide;
import ASTMCore.ASTMSyntax.Expression.Equal;
import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.ASTMSyntax.Expression.FunctionCallExpression;
import ASTMCore.ASTMSyntax.Expression.GenericOperator;
import ASTMCore.ASTMSyntax.Expression.Greater;
import ASTMCore.ASTMSyntax.Expression.IdentifierReference;
import ASTMCore.ASTMSyntax.Expression.Increment;
import ASTMCore.ASTMSyntax.Expression.InstanceOfExpression;
import ASTMCore.ASTMSyntax.Expression.IntegerLiteral;
import ASTMCore.ASTMSyntax.Expression.LabelAccess;
import ASTMCore.ASTMSyntax.Expression.Less;
import ASTMCore.ASTMSyntax.Expression.MemberAccess;
import ASTMCore.ASTMSyntax.Expression.Modulus;
import ASTMCore.ASTMSyntax.Expression.Multiply;
import ASTMCore.ASTMSyntax.Expression.NewExpression;
import ASTMCore.ASTMSyntax.Expression.Not;
import ASTMCore.ASTMSyntax.Expression.NotEqual;
import ASTMCore.ASTMSyntax.Expression.NotGreater;
import ASTMCore.ASTMSyntax.Expression.NotLess;
import ASTMCore.ASTMSyntax.Expression.NullLiteral;
import ASTMCore.ASTMSyntax.Expression.Or;
import ASTMCore.ASTMSyntax.Expression.ParenthesizedExpression;
import ASTMCore.ASTMSyntax.Expression.PointerAccess;
import ASTMCore.ASTMSyntax.Expression.PostDecrement;
import ASTMCore.ASTMSyntax.Expression.PostIncrement;
import ASTMCore.ASTMSyntax.Expression.PostfixUnaryExpression;
import ASTMCore.ASTMSyntax.Expression.PrefixUnaryExpression;
import ASTMCore.ASTMSyntax.Expression.QualifiedOverData;
import ASTMCore.ASTMSyntax.Expression.RealLiteral;
import ASTMCore.ASTMSyntax.Expression.StringLiteral;
import ASTMCore.ASTMSyntax.Expression.Subtract;
import ASTMCore.ASTMSyntax.Expression.SuperMemberAccess;
import ASTMCore.ASTMSyntax.Expression.SuperMethodInvocation;
import ASTMCore.ASTMSyntax.Expression.UnaryMinus;
import ASTMCore.ASTMSyntax.Expression.UnaryOperator;
import ASTMCore.ASTMSyntax.Expression.UnaryPlus;
import ASTMCore.ASTMSyntax.Expression.UncheckedExpression;
import ASTMCore.ASTMSyntax.Expression.VariableExpression;
import ASTMCore.ASTMSyntax.Statement.AssertStatement;
import ASTMCore.ASTMSyntax.Statement.BlockStatement;
import ASTMCore.ASTMSyntax.Statement.BreakStatement;
import ASTMCore.ASTMSyntax.Statement.CaseBlock;
import ASTMCore.ASTMSyntax.Statement.CatchBlock;
import ASTMCore.ASTMSyntax.Statement.CheckedStatement;
import ASTMCore.ASTMSyntax.Statement.ContinueStatement;
import ASTMCore.ASTMSyntax.Statement.DeclarationOrDefinitionStatement;
import ASTMCore.ASTMSyntax.Statement.DefaultBlock;
import ASTMCore.ASTMSyntax.Statement.DoWhileStatement;
import ASTMCore.ASTMSyntax.Statement.EmptyStatement;
import ASTMCore.ASTMSyntax.Statement.ExpressionStatement;
import ASTMCore.ASTMSyntax.Statement.ForCheckAfterStatement;
import ASTMCore.ASTMSyntax.Statement.ForIterator;
import ASTMCore.ASTMSyntax.Statement.IfStatement;
import ASTMCore.ASTMSyntax.Statement.LabeledStatement;
import ASTMCore.ASTMSyntax.Statement.ReturnStatement;
import ASTMCore.ASTMSyntax.Statement.Statement;
import ASTMCore.ASTMSyntax.Statement.SuperInvocation;
import ASTMCore.ASTMSyntax.Statement.SwitchCase;
import ASTMCore.ASTMSyntax.Statement.SwitchStatement;
import ASTMCore.ASTMSyntax.Statement.SynchronizedStatement;
import ASTMCore.ASTMSyntax.Statement.ThrowStatement;
import ASTMCore.ASTMSyntax.Statement.TryStatement;
import ASTMCore.ASTMSyntax.Statement.UncheckedStatement;
import ASTMCore.ASTMSyntax.Statement.WhileStatement;
import ASTMCore.ASTMSyntax.Types.ArrayType;
import ASTMCore.ASTMSyntax.Types.ClassType;
import ASTMCore.ASTMSyntax.Types.DerivesFrom;
import ASTMCore.ASTMSyntax.Types.EnumType;
import ASTMCore.ASTMSyntax.Types.ImplementsTo;
import ASTMCore.ASTMSyntax.Types.MemberObject;
import ASTMCore.ASTMSyntax.Types.NamedTypeReference;
import ASTMCore.ASTMSyntax.Types.ParameterizedType;
import ASTMCore.ASTMSyntax.Types.TypeParameter;
import ASTMCore.ASTMSyntax.Types.TypeReference;
import ASTMCore.visitor.GASTVisitor;
import ASTMCore.visitor.GASTVisitorObject;
import gastmappers.csharpmapper.ANTLR.CSharpParser.Compilation_unitContext;
import gastmappers.csharpmapper.ANTLR.CSharpParser.Labeled_StatementContext;
import gastmappers.csharpmapper.ANTLR.CSharpParser.Type_declarationContext;


public class CSharpTranslatorVisitor implements GASTVisitorObject{

	private DictionaryMap dictionary; 
	private String Translation;
	private List<Difference> differencesList;
	
	public String getTranslation() {
		return this.Translation;
	}
	
	public List<Difference> getDifferences(){
		return this.differencesList;
	}
	
	CSharpTranslatorVisitor (DictionaryMap dictionary, CompilationUnit gast){
		this.dictionary = dictionary;
		this.Translation  = "";
		this.differencesList = new ArrayList<Difference>();
		visitCompilationUnit(gast);
		return;
	}
	
	@Override
	public Object visitCompilationUnit(CompilationUnit compilationUnit) {
		try{
			for (ImportDeclaration importDcl : compilationUnit.getImports()) {
				Translation += visitImportDeclaration(importDcl);
			}
			visitProgramScope(compilationUnit.getOpensScope());
		} catch (Exception e) {
			//e.printStackTrace();
		}

		return null;
	}
	
	@Override
	public String visitImportDeclaration(ImportDeclaration importDeclaration) {
		String GastTranslation = dictionary.getMap().get(importDeclaration.getClass().getSimpleName()).get(0);;
		GastTranslation += " ";
		if (importDeclaration.isStatic()) {
			GastTranslation += visitStaticModifier(new StaticModifier());
		}
		GastTranslation += importDeclaration.getIdentifierName().getNameString();
		//USING identifier '=' namespace_or_type_name ';' only gets the namespace_or_type_name, id is left behind
		GastTranslation += ";\n";
		return GastTranslation;
	}
	
	
	@Override
	public Object visitProgramScope(ProgramScope programScope) {
		Translation += "namespace eje{\n";
		if(programScope != null){
		for (DefintionObject GastClassOrEnum : programScope.getDeclOrDefn()) {
			try {
				if (GastClassOrEnum.getClass().equals(EnumTypeDefinition.class)) {
					Translation += visitEnumTypeDefinition((EnumTypeDefinition) GastClassOrEnum);
				} else {
					Translation += visitAggregateTypeDefinition((AggregateTypeDefinition) GastClassOrEnum);
				}
			} catch(NullPointerException e) {
				differencesList.add(new Difference(null,GastClassOrEnum,"","null","warning, null object in programScope"));
			}
			
		}
		}
		Translation += "}\n";
		return null;
	}
	
	@Override
	public String visitEnumTypeDefinition(EnumTypeDefinition enumTypeDefinition) {
		return visitEnumType(enumTypeDefinition.getDefinitionType());
	}

	@Override
	public String visitEnumType(EnumType enumType) {
		String enumTranslation = "";
		enumTranslation += translateAnnotations(enumType);
		for (Modifiers modifier : enumType.getModifiers()) {
			enumTranslation += dictionary.getMap().get(modifier.getClass().getSimpleName()).get(0);
			enumTranslation += " ";
		}
		enumTranslation += dictionary.getMap().get(enumType.getClass().getSimpleName()).get(0) +" ";
		try {
			enumTranslation += enumType.getIdentifier();
		} catch(NullPointerException e) {
			differencesList.add(new Difference(null,enumType,"","null","Enum Name not mapped"));
		}
		if (enumType.getImplementesTo() != null) {
			enumTranslation += ":";
			enumTranslation += ((NamedTypeReference) enumType.getImplementesTo().get(0).getImplementsTo()).getTypeName().getNameString();
		}
		enumTranslation += "{\n";
		String enumLiterals = "";
		if (!enumType.getEnumLiterals().isEmpty()) {
			for (EnumLiteralDefinition enumLiteral : enumType.getEnumLiterals()) {
				enumLiterals += visitEnumLiteralDefinition(enumLiteral);
				enumLiterals += ",";
			}
		}
		enumTranslation += enumLiterals;
		enumTranslation += "}\n";
		return enumTranslation;
	}
	
	@Override
	public String visitEnumLiteralDefinition(EnumLiteralDefinition enumLiteralDefinition) {
		String literal = "";
		if (enumLiteralDefinition.getIdentifier() != null) {
			literal += enumLiteralDefinition.getIdentifier().getNameString();
		} else {
			differencesList.add(new Difference(null,enumLiteralDefinition,"","null","Enum Literal Name not mapped"));
		}
		//TODO enum initial value
		return literal;
	}
	

	@Override
	public String visitAggregateTypeDefinition(AggregateTypeDefinition aggregateTypeDefinition) {
		if (aggregateTypeDefinition.getAggregateType().getClass().getSimpleName().equals("ClassType")) {
			return visitClassType((ClassType) aggregateTypeDefinition.getAggregateType());
		}
		return null;
	}

	@Override
	public String visitClassType(ClassType classType) {
		String classTranslation = "";
		classTranslation += translateAnnotations(classType);
		if (classType.getModifiers() != null) {
			for (Modifiers modifier : classType.getModifiers()) {
				classTranslation += dictionary.getMap().get(modifier.getClass().getSimpleName()).get(0);
				classTranslation += " ";
			}
		}

		classTranslation += dictionary.getMap().get(classType.getClass().getSimpleName()).get(0) + " ";
		classTranslation += classType.getNameString().getNameString();
		if (classType.getParameters() != null) {
			classTranslation += "<";
			for (TypeParameter typeParam: classType.getParameters()) {
				classTranslation += typeParam.getIdentifier().getNameString();
				classTranslation += ",";
			}
			classTranslation = (String) classTranslation.subSequence(0, classTranslation.length()-1);
			classTranslation += ">";
		}
		boolean hasParent = false;
		if (classType.getDerivesFrom() != null) {
			if (!classType.getDerivesFrom().isEmpty()) {
				classTranslation += " : ";
				hasParent = true;
				classTranslation += ((NamedTypeReference) classType.getDerivesFrom().get(0).getSuperclass()).getTypeName().getNameString();
			}
		}
		if (classType.getImplementesTo() != null) {
			if (!classType.getImplementesTo().isEmpty()) {
				if (hasParent) {
					classTranslation += ","; 
				} else {
					classTranslation += ":"; 
				}
				for (ImplementsTo implementedInterface : classType.getImplementesTo()) {
					classTranslation += ((NamedTypeReference) implementedInterface.getImplementsTo()).getTypeName().getNameString();
					classTranslation += ","; 
				}
				classTranslation = (String) classTranslation.subSequence(0, classTranslation.length()-1);
			}
		}
		classTranslation += "{\n";
		for (MemberObject miembro : classType.getMembers()) {
			classTranslation += visitMemberObject(miembro);
		}
		for (DefintionObject defObject : classType.getOpensScope().getDeclOrDefn()) {
			if (defObject != null) {
				switch (defObject.getClass().getSimpleName()) {
					case "FunctionDefintion":
						classTranslation += visitFunctionDefintion((FunctionDefintion) defObject);
						//TODO class dcls inside class
				}
			} else {
				differencesList.add(new Difference(null,classType.getOpensScope(),"","null","Warning Null object in DefintionObject List"));
			}
		}
		classTranslation += "}\n";
		return classTranslation;
	}

	@Override
	public String visitMemberObject(MemberObject memberObject) {
		String newMember = "";
		if (memberObject.getAnnotations() != null) {
			newMember += translateAnnotations(memberObject);
		}
		if (memberObject.getModifiers() != null) {
			if (!memberObject.getModifiers().isEmpty() ) {
				for (Modifiers modifier : memberObject.getModifiers()) {
					newMember += dictionary.getMap().get(modifier.getClass().getSimpleName()).get(0);
					newMember += " ";
				}
			}
		}
		newMember += visitVariableDefinition((VariableDefinition) memberObject.getMember());
		return newMember;
	}
	
	@Override
	public String visitFunctionDefintion(FunctionDefintion functionDefintion) {
		String translatedMethod = "";
		if (functionDefintion.getAnnotations() != null) {
			translatedMethod += translateAnnotations(functionDefintion);
		}
		if (functionDefintion.getModifiers() != null) {
			if (!functionDefintion.getModifiers().isEmpty()) {
				for (Modifiers modifier : functionDefintion.getModifiers()) {
					translatedMethod += dictionary.getMap().get(modifier.getClass().getSimpleName()).get(0);
					translatedMethod += " ";
				}
			}
		}
		translatedMethod += ((NamedTypeReference) functionDefintion.getReturnType()).getTypeName().getNameString();
		translatedMethod += " ";
		translatedMethod += functionDefintion.getIdentifierName().getNameString();
		translatedMethod += "(";
		if (functionDefintion.getFormalParameters() != null) {
			if (!functionDefintion.getFormalParameters().isEmpty()) {
				for (FormalParameterDefinition param : functionDefintion.getFormalParameters()) {
					translatedMethod += visitFormalParameterDefinition(param);
					translatedMethod += ",";
				}
				translatedMethod = (String) translatedMethod.subSequence(0, translatedMethod.length()-1);
			}
		}
		translatedMethod += ")";
		translatedMethod += visitBlockStatement((BlockStatement) functionDefintion.getBody());
		return translatedMethod;
	}

	public String translateAnnotations(GASTMSyntaxObject objeto) {
		String translated = "";
		if (objeto.getAnnotations() != null) {
			for (AnnotationExpression annotation : objeto.getAnnotations() ) {
				translated += "[";
				if (annotation.getAnnotationtype() != null) {
					translated += visitTypeReference(annotation.getAnnotationtype()) + ":";
				}
				if (annotation.getMemberValues() != null) {
					for (Expression expr : annotation.getMemberValues()) {
						translated += visitExpression(expr);
					}
				}
				translated += "]\n";
			}
		}
		return translated;
	}
	
	public String visitExpression(Expression expr) {
		String newExpr = "";
		if (expr.getClass().getSimpleName().equals("FunctionCallExpression")) {//TODO keep adding stuff
			newExpr = visitFunctionCallExpression((FunctionCallExpression) expr);
		} else if (expr.getClass().getSimpleName().equals("BinaryExpression")) {
			newExpr = visitBinaryExpression((BinaryExpression) expr);
		} else if (expr.getClass().getSimpleName().equals("RealLiteral")) {
			newExpr = visitRealLiteral((RealLiteral) expr);
		} else if (expr.getClass().getSimpleName().equals("IdentifierReference")) {
			newExpr = visitIdentifierReference((IdentifierReference) expr);
		} else if (expr.getClass().getSimpleName().equals("QualifiedOverData")) {
			newExpr = visitQualifiedOverData((QualifiedOverData) expr);
		} else if (expr.getClass().getSimpleName().equals("CastExpression")) {
			newExpr = visitCastExpression((CastExpression) expr);
		} else if (expr.getClass().getSimpleName().equals("StringLiteral")) {
			newExpr = visitStringLiteral((StringLiteral) expr);
		} else if (expr.getClass().getSimpleName().equals("NewExpression")) {
			newExpr = visitNewExpression((NewExpression) expr);
		} else if (expr.getClass().getSimpleName().equals("PrefixUnaryExpression")) {
			newExpr = visitPrefixUnaryExpression((PrefixUnaryExpression) expr);
		} else if (expr.getClass().getSimpleName().equals("DefaultUnaryExpression")) {
			//newExpr = visitDefaultUnaryExpression((NewExpression) expr);
		} else if (expr.getClass().getSimpleName().equals("CollectionExpression")) {
			newExpr = visitCollectionExpression((CollectionExpression) expr);
		} else if (expr.getClass().getSimpleName().equals("ArrayAccess")) {
			newExpr = visitArrayAccess((ArrayAccess) expr);
		} else if (expr.getClass().getSimpleName().equals("ParenthesizedExpression")) {
			newExpr = visitParenthesizedExpression((ParenthesizedExpression) expr);
		} else if (expr.getClass().getSimpleName().equals("MemberAccess")) {
			newExpr = visitMemberAccess((MemberAccess) expr);
		} else if (expr.getClass().getSimpleName().equals("BooleanLiteral")) {
			newExpr = visitBooleanLiteral((BooleanLiteral) expr);
		} else if (expr.getClass().getSimpleName().equals("ConditionalExpression")) {
			newExpr = visitConditionalExpression((ConditionalExpression) expr);
		}
		
		return newExpr;
	}
	
	public String visitDefintionObject(DefintionObject defObject) {
		String newDCL = "";
		if (defObject.getClass().getSimpleName().equals("VariableDefinition")) {
			newDCL = visitVariableDefinition((VariableDefinition) defObject);
		} else if (defObject.getClass().getSimpleName().equals("")) {
			//TODO do not know what's gonna be mapped to
		}
		return newDCL;
	}
	
	@Override
	public String visitFunctionCallExpression(FunctionCallExpression functionCallExpression) {
		String callExpr = "";
		if (functionCallExpression.getCalledFunction() != null) {
			if (functionCallExpression.getCalledFunction() instanceof IdentifierReference) {
				callExpr += visitIdentifierReference((IdentifierReference) functionCallExpression.getCalledFunction()) + ".";
			} else if (functionCallExpression.getCalledFunction() instanceof ParenthesizedExpression) {
				callExpr += visitParenthesizedExpression((ParenthesizedExpression) functionCallExpression.getCalledFunction()) + ".";
			}else if (functionCallExpression.getCalledFunction() instanceof QualifiedOverData) {
				callExpr += visitQualifiedOverData((QualifiedOverData) functionCallExpression.getCalledFunction()) + ".";
			}else if (functionCallExpression.getCalledFunction() instanceof FunctionCallExpression) {
				callExpr += visitFunctionCallExpression((FunctionCallExpression) functionCallExpression.getCalledFunction()) + ".";
			}
			
		}
		if (functionCallExpression.getFunctionName() != null) {
			callExpr += visitName(functionCallExpression.getFunctionName());
		}
		if (functionCallExpression.getCallerClassName() != null) {
			
		}
		callExpr += "(";
		if (functionCallExpression.getActualParams() != null) {
			if (!functionCallExpression.getActualParams().isEmpty()) {
				for (ActualParameter param : functionCallExpression.getActualParams()) {
					callExpr += visitActualParameterExpression((ActualParameterExpression) param);
					callExpr += ",";
				}
				callExpr =  callExpr.substring(0,callExpr.length()-1);
			}
		}
		callExpr += ")";
		return callExpr ;
	}
	
	@Override
	public String visitActualParameterExpression(ActualParameterExpression actualParameterExpression) {
		String param = "";
		if (actualParameterExpression.getValue() != null) {
			param += visitExpression(actualParameterExpression.getValue());
		}
		return param;
		
	}
	
	@Override
	public String visitLabelDefinition(LabelDefinition labelDefinition) {
		return visitName(labelDefinition.getLabelname());
	}

	@Override
	public String visitAggregateScope(AggregateScope aggregateScope) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitVariableDefinition(VariableDefinition variableDefinition) {
		String dcl = "";
		dcl += visitNamedTypeReference((NamedTypeReference) variableDefinition.getDefinitionType());
		dcl += " ";
		for (Fragment frag : variableDefinition.getFragments()) {
			dcl += visitFragment(frag);
			dcl += ",";
		}
		dcl = (String) dcl.subSequence(0, dcl.length()-1);
		dcl += ";\n";
		return dcl;
	}

	@Override
	public String visitFragment(Fragment fragment) {
		String frag = "";
		frag += fragment.getIdentifierName().getNameString();
		if (fragment.getInitialValue() != null) {
			frag += "=" + visitExpression(fragment.getInitialValue());
		}
		return frag;
	}
	
	@Override
	public String visitNamedTypeReference(NamedTypeReference namedTypeReference) {
		String type = namedTypeReference.getTypeName().getNameString();
		if (type.equals("Array")) {
			return visitNamedTypeReference((NamedTypeReference) ((ArrayType)namedTypeReference.getDataType()).getBaseType()) + "[]";
		}
		return namedTypeReference.getTypeName().getNameString();
	}


	public String visitStatement(Statement stmt) {
		switch (stmt.getClass().getSimpleName()) {
			case "BlockStatement":
				return visitBlockStatement((BlockStatement) stmt);
			case "BreakStatement":
				return visitBreakStatement((BreakStatement) stmt);
			case "CheckedStatement":
				return visitCheckedStatement((CheckedStatement) stmt);
			case "ContinueStatement":
				return visitContinueStatement((ContinueStatement) stmt);
			case "DeclarationOrDefinitionStatement":
				return visitDeclarationOrDefinitionStatement((DeclarationOrDefinitionStatement) stmt);
			case "DoWhileStatement":
				return visitDoWhileStatement((DoWhileStatement) stmt);
			case "EmptyStatement":
				return visitEmptyStatement((EmptyStatement) stmt);
			case "ExpressionStatement":
				return visitExpressionStatement((ExpressionStatement) stmt) + ";\n";
			case "FixedStatement":
				//visitFixedStatement(stmt);
			case "ForIterator":
				return visitForIterator((ForIterator) stmt);
			case "ForCheckAfterStatement":
				return visitForCheckAfterStatement((ForCheckAfterStatement) stmt);
			case "IfStatement":
				return visitIfStatement((IfStatement) stmt);
			case "LabeledStatement":
				return visitLabeledStatement((LabeledStatement) stmt);
			case "ReturnStatement":
				return visitReturnStatement((ReturnStatement) stmt);
			case "SwitchStatement": //SwitchCase list
				return visitSwitchStatement((SwitchStatement) stmt);
			case "SynchronizedStatement":
				return visitSynchronizedStatement((SynchronizedStatement) stmt);
			case "ThrowStatement":
				return visitThrowStatement((ThrowStatement) stmt);
			case "TryStatement":
				return visitTryStatement((TryStatement) stmt);
			case "UncheckedStatement":
				return visitUncheckedStatement((UncheckedStatement) stmt);
			case "UnsafeStatement":
				//	visitUnsafeStatement(stmt);
				return "null";
			case "UsingStatement":
				//	visitUsingStatement(stmt);
				return "null";
			case "WhileStatement":
				return visitWhileStatement((WhileStatement) stmt);
			case "YieldBreakStatement":
				//	visitYieldBreakStatement(stmt);
				return "null";
			case "YieldReturnExpressionStatement":
				//	visitYieldReturnExpressionStatement(stmt);
				return "null";
			default:
				return "null";
		}
		
	}
	
	@Override
	public String visitBlockStatement(BlockStatement blockStatement) {
		String stmtBlock = "{\n";
		for (Statement stmt : blockStatement.getSubStatements()) {
			if (stmt != null) {
				stmtBlock += visitStatement(stmt);
			}
		}
		return stmtBlock + "}\n";
	}

	@Override
	public String visitLabeledStatement(LabeledStatement labeledStatement) {
		String labelStmtTranslation = "";
		labelStmtTranslation += visitLabelDefinition(labeledStatement.getLabel());
		labelStmtTranslation += " :\n ";
		labelStmtTranslation += visitStatement(labeledStatement.getStatement());
		return labelStmtTranslation;
	}

	@Override
	public String visitEmptyStatement(EmptyStatement emptyStatement) {
		return ";\n";
	}

	@Override
	public String visitIfStatement(IfStatement ifStatement) {
		String ifTranslation = "if (";
		ifTranslation += visitExpression(ifStatement.getCondition());
		ifTranslation += ")";
		ifTranslation += visitStatement(ifStatement.getThenBody());
		if (ifStatement.getElseBody() != null) {
			ifTranslation += " else ";
			ifTranslation += visitStatement(ifStatement.getElseBody());
		}
		return ifTranslation;
	}

	@Override
	public String visitWhileStatement(WhileStatement whileStatement) {
		String whileTranslation = "while (";
		whileTranslation += visitExpression(whileStatement.getCondition());
		whileTranslation += ")";
		whileTranslation += visitStatement(whileStatement.getBody());
		return whileTranslation;
	}

	@Override
	public String visitDoWhileStatement(DoWhileStatement doWhileStatement) {
		String doWhileTranslation = "do";
		doWhileTranslation += visitStatement(doWhileStatement.getBody());
		doWhileTranslation += "while (";
		doWhileTranslation += visitExpression(doWhileStatement.getCondition());
		doWhileTranslation += ")\n";
		return doWhileTranslation;
	}

	@Override
	public String visitContinueStatement(ContinueStatement continueStatement) {
		return "continue;\n";
	}

	@Override
	public String visitBreakStatement(BreakStatement breakStatement) {
		return "break;\n";
	}

	@Override
	public String visitReturnStatement(ReturnStatement returnStatement) {
		String returnTranslation = "return ";
		if (returnStatement.getReturnValue() != null) {
			returnTranslation += visitExpression(returnStatement.getReturnValue());
		}
		return returnTranslation + ";\n";
	}

	@Override
	public String visitThrowStatement(ThrowStatement throwStatement) {
		String throwTranslation = "throw ";
		if (throwStatement.getException() != null) {
			throwTranslation += visitExpression(throwStatement.getException());
		}
		return throwTranslation+ ";\n";
	}

	@Override
	public String visitExpressionStatement(ExpressionStatement expressionStatement) {
		return visitExpression(expressionStatement.getExpression());
	}

	@Override
	public String visitForCheckAfterStatement(ForCheckAfterStatement forCheckAfterStatement) {
		String forTranslation = "for ( ";
		if (forCheckAfterStatement.getInitBody() != null) {
			for (Expression initExpr : forCheckAfterStatement.getInitBody()) {
				forTranslation += visitExpression(initExpr) + ",";
			}
			forTranslation = (String) forTranslation.subSequence(0, forTranslation.length()-1);
		}
		forTranslation+= ";";
		if (forCheckAfterStatement.getCondition() != null) {
			forTranslation += visitExpression(forCheckAfterStatement.getCondition());
		}
		forTranslation+= ";";
		if (forCheckAfterStatement.getIterationBody() != null) {
			for (Expression iterExpr : forCheckAfterStatement.getIterationBody()) {
				forTranslation += visitExpression(iterExpr) + ",";
			}
			forTranslation = (String) forTranslation.subSequence(0, forTranslation.length()-1);
		}
		forTranslation += ")";
		forTranslation += visitStatement(forCheckAfterStatement.getBody());
		return forTranslation;
	}

	@Override
	public String visitForIterator(ForIterator forIterator) {
		String forEachTranslation = "foreach (";
		forEachTranslation += visitFormalParameterDefinition(forIterator.getParameter());
		forEachTranslation += " in " + visitExpression(forIterator.getCondition());
		forEachTranslation += ")";
		forEachTranslation += visitStatement(forIterator.getBody());
		return forEachTranslation;
	}

	@Override
	public String visitTryStatement(TryStatement tryStatement) {
		String tryStmt = "try";
		tryStmt += visitStatement(tryStatement.getBody());
		if (tryStatement.getCatchBlocks() != null) {
			if (!tryStatement.getCatchBlocks().isEmpty()) {
				for (CatchBlock block : tryStatement.getCatchBlocks()) {
					tryStmt += visitCatchBlock(block);
				}
			}
		}
		if (tryStatement.getFinalStatement() != null) {
			tryStmt += "finally";
			tryStmt += visitStatement(tryStatement.getFinalStatement());
		}
		return tryStmt;
	}

	@Override
	public String visitCatchBlock(CatchBlock catchBlock) {
		String catchBlockTranslation = "catch ";
		if (catchBlock.getException() != null) {
			catchBlockTranslation += "(";
			//TODO catchBlockTranslation 
			catchBlockTranslation += ")";
		}
		if (catchBlock.getExpressionFilter() != null) {
			catchBlockTranslation += "when (";
			catchBlockTranslation += visitExpression(catchBlock.getExpressionFilter());
			catchBlockTranslation += ")";
		}
		catchBlockTranslation += visitStatement(catchBlock.getBody());
		return catchBlockTranslation;
	}

	@Override
	public String visitSwitchStatement(SwitchStatement switchStatement) {
		String switchTranslation = "switch (";
		switchTranslation += visitExpression(switchStatement.getSwitchExpression());
		switchTranslation += ")";
		for (SwitchCase caseBlock : switchStatement.getCases()) {
			switchTranslation += visitSwitchCase(caseBlock);
		}
		return switchTranslation;
	}

	@Override
	public String visitSwitchCase(SwitchCase switchCase) {
		String caseBlock;
		if (switchCase.getClass().getSimpleName().equals("DefaultBlock")) {
			caseBlock = visitDefaultBlock((DefaultBlock) switchCase);
		} else {
			caseBlock = visitCaseBlock((CaseBlock) switchCase);
		}
		for (Statement stmt : switchCase.getBody()) {
			caseBlock += visitStatement(stmt);
		}
		return caseBlock;
	}

	@Override
	public String visitCaseBlock(CaseBlock caseBlock) {
		String caseLabel = "case " + visitExpression(caseBlock.getEvaluation());
		//TODO limits: when j < 1
		caseLabel += " : \n";
		return caseLabel;
	}

	@Override
	public String visitDefaultBlock(DefaultBlock defaultBlock) {
		return "default :\n";
	}

	@Override
	public Object visitAssertStatement(AssertStatement assertStatement) {
		return null;
		
	}

	@Override
	public String visitSynchronizedStatement(SynchronizedStatement synchronizedStatement) {
		String syncTranslation = "";
		//TODO async for
		syncTranslation += "lock (";
		syncTranslation += visitExpression(synchronizedStatement.getExpression());
		syncTranslation += ")";
		syncTranslation += visitStatement(synchronizedStatement.getBody());
		return syncTranslation;
		
	}

	@Override
	public String visitDeclarationOrDefinitionStatement(
			DeclarationOrDefinitionStatement declarationOrDefinitionStatement) {
		String dclStmtTranslation = "";
		if (!declarationOrDefinitionStatement.getAnnotations().isEmpty()) {
			dclStmtTranslation += translateAnnotations(declarationOrDefinitionStatement) + "\n";
		}
		dclStmtTranslation += visitDefintionObject(declarationOrDefinitionStatement.getDeclOrDefn());
		return dclStmtTranslation;
		
	}

	@Override
	public Object visitSuperInvocation(SuperInvocation superInvocation) {
		return dictionary.getMap().get(superInvocation.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitCheckedStatement(CheckedStatement checkedStatement) {
		String checkedTranslation  = "checked";
		checkedTranslation += visitBlockStatement((BlockStatement) checkedStatement.getBody());
		return checkedTranslation;
	}

	@Override
	public String visitUncheckedStatement(UncheckedStatement uncheckedStatement) {
		String uncheckedTranslation = "unchecked ";
		uncheckedTranslation += visitStatement(uncheckedStatement.getBody());
		return uncheckedTranslation;
	}

	@Override
	public String visitBinaryExpression(BinaryExpression binaryExpression) {
		String exprResult = "";
		if (binaryExpression.getLeftOperand() != null) {
			exprResult += visitExpression(binaryExpression.getLeftOperand());
		} else {
			exprResult += "null";
			differencesList.add(new Difference(null,binaryExpression,"","null","Warning Null object in binaryExpression"));
		}
		if (binaryExpression.getOperator() != null) {
			exprResult += visitOperator(binaryExpression.getOperator());
		}
		if (binaryExpression.getRightOperand() != null) {
			exprResult += visitExpression(binaryExpression.getRightOperand());
		} else {
		exprResult += "null";
		differencesList.add(new Difference(null,binaryExpression,"","null","Warning Null object in binaryExpression"));
		}
		return exprResult;
	}

	public String visitOperator(BinaryOperator oper) {
		switch (oper.getClass().getSimpleName()) {
			case "Multiply":
				return visitMultiply((Multiply) oper);
			case "Modulus":
				return visitModulus((Modulus) oper);
			case "Divide":
				return visitDivide((Divide) oper);
			case "Add":
				return visitAdd((Add) oper);
			case "Substract":
				return visitSubtract((Subtract) oper);
			case "BitLeftShift":
				return visitBitLeftShift((BitLeftShift) oper);
			case "BitSignedRightShift":
				return visitBitSignedRightShift((BitSignedRightShift) oper);
			case "BitUnsignedRightShift":
				return visitBitUnsignedRightShift((BitUnsignedRightShift) oper);
			case "Less":
				return visitLess((Less) oper);
			case "Greater":
				return visitGreater((Greater) oper);
			case "NotGreater":
				return visitNotGreater((NotGreater) oper);
			case "NotLess":
				return visitNotLess((NotLess) oper);
			case "Equal":
				return visitEqual((Equal) oper);
			case "NotEqual":
				return visitNotEqual((NotEqual) oper);
			case "BitAnd":
				return visitBitAnd((BitAnd) oper);
			case "BitOr":
				return visitBitOr((BitOr) oper);
			case "AssignMultiply":
				return visitAssignMultiply((AssignMultiply) oper);
			case "AssignDivide":
				return visitAssignDivide((AssignDivide) oper);
			case "AssignModulus":
				return visitAssignModulus((AssignModulus) oper);
			case "AssignAdd":
				return visitAssignAdd((AssignAdd) oper);
			case "AssignSubtract":
				return visitAssignSubtract((AssignSubtract) oper);
			case "AssignBitLeftShift":
				return visitAssignBitLeftShift((AssignBitLeftShift) oper);
			case "AssignBitUnsignedRightShift":
				return visitAssignBitUnsignedRightShift((AssignBitUnsignedRightShift) oper);
			case "AssignBitAnd":
				return visitAssignBitAnd((AssignBitAnd) oper);
			case "AssignBitXor":
				return visitAssignBitXor((AssignBitXor) oper);
			case "AssignBitOr":
				return visitAssignBitOr((AssignBitOr) oper);
			case "Assign":
				return visitAssign((Assign) oper);
			default:
				return visitGenericOperator((GenericOperator) oper);
		}
	}

	@Override
	public String visitEqual(Equal equal) {
		return dictionary.getMap().get(equal.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitNotEqual(NotEqual notEqual) {
		return dictionary.getMap().get(notEqual.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitBitAnd(BitAnd bitAnd) {
		return dictionary.getMap().get(bitAnd.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitLess(Less less) {
		return dictionary.getMap().get(less.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitGreater(Greater greater) {
		return dictionary.getMap().get(greater.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitNotGreater(NotGreater greater) {
		return greater.getSimbol();
	}

	@Override
	public String visitNotLess(NotLess less) {
		return less.getSimbol();
	}
	
	@Override
	public String visitBitLeftShift(BitLeftShift bitLeftShift) {
		return dictionary.getMap().get(bitLeftShift.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitBitSignedRightShift(BitSignedRightShift bitSignedRightShift) {
		return null;
	}

	@Override
	public String visitBitUnsignedRightShift(BitUnsignedRightShift bitUnsignedRightShift) {
		return dictionary.getMap().get(bitUnsignedRightShift.getClass().getSimpleName()).get(0);
	}
	
	@Override
	public String visitMultiply(Multiply multiply) {
		return dictionary.getMap().get(multiply.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitModulus(Modulus modulus) {
		return dictionary.getMap().get(modulus.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitDivide(Divide divide) {
		return dictionary.getMap().get(divide.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAdd(Add add) {
		return dictionary.getMap().get(add.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitSubtract(Subtract subtract) {
		return dictionary.getMap().get(subtract.getClass().getSimpleName()).get(0);
	}
	
	@Override
	public String visitBitXor(BitXor bitXor) {
		return dictionary.getMap().get(bitXor.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitBitOr(BitOr bitOr) {
		return null;
	}

	@Override
	public String visitAnd(And and) {
		return dictionary.getMap().get(and.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitOr(Or or) {
		return dictionary.getMap().get(or.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssign(Assign assign) {
		return dictionary.getMap().get(assign.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignMultiply(AssignMultiply assignMultiply) {
		return dictionary.getMap().get(assignMultiply.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignDivide(AssignDivide assignDivide) {
		return dictionary.getMap().get(assignDivide.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignModulus(AssignModulus assignModulus) {
		return dictionary.getMap().get(assignModulus.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignAdd(AssignAdd assignAdd) {
		return dictionary.getMap().get(assignAdd.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignSubtract(AssignSubtract assignSubtract) {
		return dictionary.getMap().get(assignSubtract.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignBitLeftShift(AssignBitLeftShift assignBitLeftShift) {
		return dictionary.getMap().get(assignBitLeftShift.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignBitSignedRightShift(AssignBitSignedRightShift assignBitSignedRightShift) {
		return null;
	}

	@Override
	public String visitAssignBitUnsignedRightShift(AssignBitUnsignedRightShift assignBitUnsignedRightShift) {
		return dictionary.getMap().get(assignBitUnsignedRightShift.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignBitAnd(AssignBitAnd assignBitAnd) {
		return dictionary.getMap().get(assignBitAnd.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignBitXor(AssignBitXor assignBitXor) {
		return dictionary.getMap().get(assignBitXor.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAssignBitOr(AssignBitOr assignBitOr) {
		return dictionary.getMap().get(assignBitOr.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitGenericOperator(GenericOperator genericOperator) {
		return genericOperator.getSimbol();
	}

	@Override
	public String visitInstanceOfExpression(InstanceOfExpression instanceOfExpression) {
		return dictionary.getMap().get(instanceOfExpression.getClass().getSimpleName()).get(0) + visitExpression(instanceOfExpression.getLeftOperand());
	}

	@Override
	public String visitPrefixUnaryExpression(PrefixUnaryExpression prefixUnaryExpression) {
		return visitUnary(prefixUnaryExpression.getOperator()) + visitExpression(prefixUnaryExpression.getOperand());
	}

	@Override
	public String visitPostfixUnaryExpression(PostfixUnaryExpression postfixUnaryExpression) {
		return visitExpression(postfixUnaryExpression.getOperand()) +  visitUnary(postfixUnaryExpression.getOperator());
	}

	private String visitUnary(UnaryOperator operator) {
		switch (operator.getClass().getSimpleName()) {
			case "Not":
				return visitNot((Not) operator);
			case "UnaryPlus":
				return visitUnaryPlus((UnaryPlus) operator);
			case "UnaryMinus":
				return visitUnaryMinus((UnaryMinus) operator);
			case "PostIncrement":
				return visitPostIncrement((PostIncrement) operator);
			case "PostDecrement":
				return visitPostDecrement((PostDecrement) operator);
			case "Increment":
				return visitIncrement((Increment) operator);
			case "Decrement":
				return visitDecrement((Decrement) operator);
		}
		return visitDefaultUnaryOperator((DefaultUnaryOperator) operator);
	}

	@Override
	public String visitPostIncrement(PostIncrement postIncrement) {
		return dictionary.getMap().get(postIncrement.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitPostDecrement(PostDecrement postDecrement) {
		return dictionary.getMap().get(postDecrement.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitUnaryPlus(UnaryPlus unaryPlus) {
		return dictionary.getMap().get(unaryPlus.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitUnaryMinus(UnaryMinus unaryMinus) {
		return dictionary.getMap().get(unaryMinus.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitIncrement(Increment increment) {
		return dictionary.getMap().get(increment.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitDecrement(Decrement decrement) {
		return dictionary.getMap().get(decrement.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitBitNot(BitNot bitNot) {
		return null;
	}

	@Override
	public String visitNot(Not not) {
		return dictionary.getMap().get(not.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitDefaultUnaryOperator(DefaultUnaryOperator defaultUnaryOperator) {
		return defaultUnaryOperator.getSimbolo();
	}

	@Override
	public String visitCastExpression(CastExpression castExpression) {
		return "(" + visitTypeReference(castExpression.getCastType()) + ") " + visitExpression(castExpression.getExpression());
	}

	private String visitTypeReference(TypeReference typeRef) {
		if (typeRef instanceof NamedTypeReference) {
			return visitNamedTypeReference((NamedTypeReference) typeRef);
		}
		return null;
	}

	@Override
	public String visitNullLiteral(NullLiteral nullLiteral) {
		return dictionary.getMap().get(nullLiteral.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitIdentifierReference(IdentifierReference identifierReference) {
		return identifierReference.getIdentifierName().getNameString();
	}

	@Override
	public String visitParenthesizedExpression(ParenthesizedExpression parenthesizedExpression) {
		return "(" + visitExpression(parenthesizedExpression.getInternalExpression()) + ")";
	}

	@Override
	public Object visitSuperMethodInvocation(SuperMethodInvocation superMethodInvocation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitArrayCreation(ArrayCreation arrayCreation) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitCollectionExpression(CollectionExpression collectionExpression) {
		String lista = "{";
		for (Expression expr : collectionExpression.getExpressionList()){
			lista += visitExpression(expr);
			lista += ",";
		}
		lista = (String) lista.subSequence(0, lista.length()-1);
		return lista + "}";
		
	}

	@Override
	public String visitArrayAccess(ArrayAccess arrayAccess) {
		return visitExpression(arrayAccess.getArrayName()) + "[" + visitExpression(arrayAccess.getIndex()) + "]";
	}

	@Override
	public String visitConditionalExpression(ConditionalExpression conditionalExpression) {
		String translation = visitExpression(conditionalExpression.getCondition());
		translation += "?";
		translation += visitExpression(conditionalExpression.getOnTrueOperand()) + " : " + visitExpression(conditionalExpression.getOnFalseOperand());
		return translation;
	}

	@Override
	public String visitNewExpression(NewExpression newExpression) {
		return "new " + visitNamedTypeReference((NamedTypeReference) newExpression.getNewType()) + "()";
	}

	@Override
	public String visitQualifiedOverData(QualifiedOverData qualifiedOverData) {
		String translation = visitIdentifierReference(qualifiedOverData.getMember());
		if (qualifiedOverData.getQualifiers() instanceof IdentifierReference) {
			translation += "."+ visitIdentifierReference((IdentifierReference) qualifiedOverData.getQualifiers());
		}
		return translation;
	}

	@Override
	public Object visitLabelAccess(LabelAccess labelAccess) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitMemberAccess(MemberAccess memberAccess) {
		String translation = visitExpression(memberAccess.getIdentifierName());
		if (memberAccess.getQualifiedName() != null) {
			translation =  visitExpression(memberAccess.getQualifiedName()) + "." + translation;
		}
		return translation;
	}

	@Override
	public Object visitSuperMemberAccess(SuperMemberAccess superMemberAccess) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitVariableExpression(VariableExpression variableExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String visitName(Name name) {
		return name.getNameString();
	}

	@Override
	public String visitFormalParameterDefinition(FormalParameterDefinition formalParameterDefinition) {
		String param = "";
		param += visitNamedTypeReference((NamedTypeReference) formalParameterDefinition.getDefinitionType());
		param += " ";
		param += visitName(formalParameterDefinition.getIdentifierName());
		return param;
	}
	
	@Override
	public String visitAbstractModifier(AbstractModifier abstractModifier) {
		return dictionary.getMap().get(abstractModifier.getClass().getSimpleName()).get(0);
	}

	@Override
	public String visitAnnotationModifier(AnnotationModifier annotationModifier) {
		return dictionary.getMap().get(annotationModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitDefaultModifier(DefaultModifier defaultModifier) {
		return dictionary.getMap().get(defaultModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitFinalModifier(FinalModifier finalModifier) {
		return dictionary.getMap().get(finalModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitNativeModifier(NativeModifier nativeModifier) {
		return dictionary.getMap().get(nativeModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitPrivateModifier(PrivateModifier privateModifier) {
		return dictionary.getMap().get(privateModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitProtectedModifier(ProtectedModifier protectedModifier) {
		return dictionary.getMap().get(protectedModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitPublicModifier(PublicModifier sublicModifier) {
		return dictionary.getMap().get(sublicModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitSingleMemberAnnotation(SingleMemberAnnotation singleMemberAnnotation) {
		return dictionary.getMap().get(singleMemberAnnotation.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitStaticModifier(StaticModifier staticModifier) {
		return dictionary.getMap().get(staticModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitStrictfpModifier(StrictfpModifier strictfpModifier) {
		return dictionary.getMap().get(strictfpModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitSynchronizedModifier(SynchronizedModifier synchronizedModifier) {
		return dictionary.getMap().get(synchronizedModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitTransientModifier(TransientModifier transientModifier) {
		return dictionary.getMap().get(transientModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitVolatileModifier(VolatileModifier volatileModifier) {
		return dictionary.getMap().get(volatileModifier.getClass().getSimpleName()).get(0);
		
	}

	@Override
	public String visitRefModifier(RefModifier refModifier) {
		return dictionary.getMap().get(refModifier.getClass().getSimpleName()).get(0);
		
	}
	
	@Override
	public String visitBooleanLiteral(BooleanLiteral booleanLiteral) {
		return booleanLiteral.getValue();
		
	}

	@Override
	public String visitCharLiteral(CharLiteral charLiteral) {
		return charLiteral.getValue();
		
	}

	@Override
	public String visitIntegerLiteral(IntegerLiteral integerLiteral) {
		return integerLiteral.getValue();
		
	}

	@Override
	public String visitStringLiteral(StringLiteral stringLiteral) {
		return stringLiteral.getValue();
		
	}

	@Override
	public String visitRealLiteral(RealLiteral realLiteral) {
		return realLiteral.getValue();
		
	}

	@Override
	public Object visitNameSpaceDefinition(NameSpaceDefinition nameSpaceDefinition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitDerivesFrom(DerivesFrom derivesFrom) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitImplementsTo(ImplementsTo implementsTo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitParameterizedType(ParameterizedType parameterizedType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitArrayType(ArrayType arrayType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitTypeParameter(TypeParameter typeParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitPointerAccess(PointerAccess pointerAccess) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitCheckedExpression(CheckedExpression checkedExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visitUncheckedExpression(UncheckedExpression uncheckedExpression) {
		// TODO Auto-generated method stub
		return null;
	}

}
