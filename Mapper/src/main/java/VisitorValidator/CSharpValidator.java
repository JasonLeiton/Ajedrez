package VisitorValidator;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import ASTMCore.ASTMSource.CompilationUnit;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.ASTMSyntax.Expression.LabelAccess;
import ASTMCore.ASTMSyntax.Statement.BreakStatement;
import ASTMCore.ASTMSyntax.Statement.CatchBlock;
import ASTMCore.ASTMSyntax.Statement.ContinueStatement;
import ASTMCore.ASTMSyntax.Statement.DoWhileStatement;
import ASTMCore.ASTMSyntax.Statement.EmptyStatement;
import ASTMCore.ASTMSyntax.Statement.ExpressionStatement;
import ASTMCore.ASTMSyntax.Statement.ForCheckAfterStatement;
import ASTMCore.ASTMSyntax.Statement.ForIterator;
import ASTMCore.ASTMSyntax.Statement.IfStatement;
import ASTMCore.ASTMSyntax.Statement.ReturnStatement;
import ASTMCore.ASTMSyntax.Statement.SwitchStatement;
import ASTMCore.ASTMSyntax.Statement.SynchronizedStatement;
import ASTMCore.ASTMSyntax.Statement.ThrowStatement;
import ASTMCore.ASTMSyntax.Statement.TryStatement;
import ASTMCore.ASTMSyntax.Statement.WhileStatement;
import gastmappers.csharpmapper.ANTLR.CSharpLexer;
import gastmappers.csharpmapper.ANTLR.CSharpParser;
import gastmappers.csharpmapper.ANTLR.CSharpParser.*;

public class CSharpValidator{

	private DictionaryMap dictionary; 
	private List<Difference> differencesList;
	
	public CSharpValidator(CompilationUnit gast, Compilation_unitContext csharpTree) {
		this.setDictionary(new DictionaryMap("CSharp"));
		this.setDifferencesList(new ArrayList<Difference>());
		CSharpTranslatorVisitor translator = new CSharpTranslatorVisitor(this.getDictionary(),gast);
		Compilation_unitContext newTree =  parseSourceLanguage(translator.getTranslation());
		visitCompilationUnit(csharpTree,newTree);
		this.differencesList.addAll(translator.getDifferences());
	}
	
	public String getDiffOutput() {
		String salida = "";
		for (Difference diff : this.differencesList) {
			salida += diff.getDifference() + " : " + diff.getFocus();
			salida += "\n";
		}
		return salida;
	}
	
	private Compilation_unitContext parseSourceLanguage(String translation) {
		CSharpLexer lexer = new CSharpLexer(CharStreams.fromString(translation));
        CSharpParser parser = new CSharpParser(new CommonTokenStream(lexer));
		return parser.compilation_unit();
	}

	public void visitCompilationUnit(Compilation_unitContext csharpTree, Compilation_unitContext gastTranslated) {
		if (csharpTree.using_directives() != null) {
			if (!csharpTree.using_directives().isEmpty()) {
				List<Using_directiveContext> oldList = csharpTree.using_directives().using_directive();
				int  i = 0;
				try {
					List<Using_directiveContext> newList = gastTranslated.using_directives().using_directive();
					for (i = 0; i < oldList.size(); i++) {
						visitUsing_directive(oldList.get(i),newList.get(i));
					}
				} catch (NullPointerException e) {
					differencesList.add(new Difference(csharpTree.using_directives(),gastTranslated.using_directives(),"using directives","null","none mapped"));
				} catch (IndexOutOfBoundsException e){
					differencesList.add(new Difference(oldList.get(i),gastTranslated.using_directives(),"using directive","null","not mapped"));
				}
				
			}
		}
		if (csharpTree.global_attribute_section() != null) {
			if (!csharpTree.global_attribute_section().isEmpty()) {
				List<Global_attribute_sectionContext> oldList = csharpTree.global_attribute_section();
				int  i = 0;
				try {
					List<Global_attribute_sectionContext> newList = gastTranslated.global_attribute_section();
					for (i = 0; i < oldList.size(); i++) {
						visitGlobal_attribute_sectionContext(oldList.get(i),newList.get(i));
					}
				} catch (NullPointerException e) {
					differencesList.add(new Difference(csharpTree,gastTranslated,"global directives","null","none mapped"));
				} catch (IndexOutOfBoundsException e){
					differencesList.add(new Difference(oldList.get(i),gastTranslated,"global directive","null","not mapped"));
				}
			}
		}
		if (csharpTree.namespace_member_declarations() != null) {
			try {
				visitNamespace_member_declarations(csharpTree.namespace_member_declarations(),gastTranslated.namespace_member_declarations());
			} catch (NullPointerException e) {
				
			}
		}
	}

	public void visitNamespace_member_declarations(Namespace_member_declarationsContext old, Namespace_member_declarationsContext newOne) {
		List<Namespace_member_declarationContext> oldDcls = old.namespace_member_declaration();
		List<Namespace_member_declarationContext> newDcls = newOne.namespace_member_declaration();
		int i = 0;
		try {
			for (i = 0; i < oldDcls.size(); i++) {
				visitNamespace_member_declaration(oldDcls.get(i),newDcls.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldDcls.get(i),newOne,"namespace declaration","null","namespace declaration not mapped"));
		}
	}

	public void visitNamespace_member_declaration(Namespace_member_declarationContext old, Namespace_member_declarationContext newOne) {
		if (old.namespace_declaration() != null) {
			if (newOne.namespace_declaration() != null) {
				visitNameSpaceDeclaration(old.namespace_declaration(),newOne.namespace_declaration());
			} else {
				differencesList.add(new Difference(old.namespace_declaration(),newOne,"nameSpace declaration","null","nameSpace declaration not mapped"));
			}
		} else if (old.type_declaration() != null) {
			if (newOne.type_declaration() != null) {
				visitTypeDeclaration(old.type_declaration(),newOne.type_declaration());
			} else {
				differencesList.add(new Difference(old.type_declaration(),newOne,"type declaration","null","type declaration not mapped"));
			}
		}
	}

	private void visitTypeDeclaration(Type_declarationContext old,Type_declarationContext newOne) {
		if (old.attributes() != null) {
			if (newOne.attributes() != null) {
				visitAttributeContext(old.attributes(),newOne.attributes());
			} else {
				differencesList.add(new Difference(old.attributes(),newOne,old.attributes().getText(),"null","attributes in type declaration not mapped"));
			}
		}
		if (old.all_member_modifiers() != null) {
			try {
				visitModifiers(old.all_member_modifiers(), newOne.all_member_modifiers());
			} catch (NullPointerException e) {
				differencesList.add(new Difference(old,newOne,"","null","Modifiers not mapped"));
			}
		}
		if (old.class_definition() != null) {
			if (newOne.class_definition() != null) {
				visitClass_definition(old.class_definition(),newOne.class_definition());
			} else {
				differencesList.add(new Difference(old.class_definition(),newOne,"","null","Class not mapped"));
			}
		} else if (old.struct_definition() != null) {
			if (newOne.struct_definition() != null) {
				visitStructDefinition(old.struct_definition(),newOne.struct_definition());
			} else {
				differencesList.add(new Difference(old.class_definition(),newOne,"","null","strcut not mapped"));
			}
		} else if (old.interface_definition() != null) {
			if (newOne.interface_definition() != null) {
				visitInterfaceDefinition(old.interface_definition(),newOne.interface_definition());
			} else {
				differencesList.add(new Difference(old.interface_definition(),newOne,"","null","interface not mapped"));
			}
		} else if (old.enum_definition() != null) {
			if (newOne.enum_definition() != null) {
				visitEnumDefinition(old.enum_definition(),newOne.enum_definition());
				return;
			}else {
				differencesList.add(new Difference(old.enum_definition(),newOne,"","null","enum not mapped"));
			}
		} else if (old.delegate_definition() != null) {
			//TODO
		}
		
	}
	
	private void visitInterfaceDefinition(Interface_definitionContext old,
			Interface_definitionContext newOne) {
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier for interface not mapped"));
			}
		}
		if (old.interface_base() != null) {
			if (newOne.interface_base() != null) {
				visitInterfeceBase(old.interface_base(),newOne.interface_base());
			} else {
				differencesList.add(new Difference(old.interface_base(),newOne,old.interface_base().getText(),"null","base for interface not mapped"));
			}
		}
		if (old.variant_type_parameter_list() != null) {
			if (newOne.variant_type_parameter_list() != null) {
				visitVariant_type_parameter_list(old.variant_type_parameter_list(),newOne.variant_type_parameter_list());
			} else {
				differencesList.add(new Difference(old.variant_type_parameter_list(),newOne,old.variant_type_parameter_list().getText(),"null","variant_type_parameter_list for interface not mapped"));
			}
		}
		if (old.type_parameter_constraints_clauses() != null) {
			if (newOne.type_parameter_constraints_clauses() != null) {
				visitType_parameter_constraints_clauses(old.type_parameter_constraints_clauses(),newOne.type_parameter_constraints_clauses());
			} else {
				differencesList.add(new Difference(old.type_parameter_constraints_clauses(),newOne,old.type_parameter_constraints_clauses().getText(),"null","type_parameter_constraints_clauses for interface not mapped"));
			}
		}
		visitClassBody(old.class_body(),newOne.class_body());
	}

	private void visitVariant_type_parameter_list(Variant_type_parameter_listContext old,
			Variant_type_parameter_listContext newOne) {
		// TODO Auto-generated method stub
		
	}

	private void visitInterfeceBase(Interface_baseContext old, Interface_baseContext newOne) {
		List<Namespace_or_type_nameContext> oldList = old.interface_type_list().namespace_or_type_name();
		int i = 0;
		try {
			List<Namespace_or_type_nameContext> newList = newOne.interface_type_list().namespace_or_type_name();
			try {
				for (i = 0; i < oldList.size(); i++) {
					visitNamespace_or_type_nameContext(oldList.get(i),newList.get(i));
				}
			} catch (IndexOutOfBoundsException e){
				differencesList.add(new Difference(oldList.get(i),newOne,"","null","interface base not mapped"));
			}
		}catch (NullPointerException e) {
			differencesList.add(new Difference(old,newOne,"","null","nterface bases not mapped"));
		}
	}

	private void visitNamespace_or_type_nameContext(Namespace_or_type_nameContext old,
			Namespace_or_type_nameContext newOne) {
		// namespace_or_type_name 
		//: (identifier type_argument_list? | qualified_alias_member) ('.' identifier type_argument_list?)*
		
	}

	public void visitStructDefinition(Struct_definitionContext old, Struct_definitionContext newOne) {
		if (old.REF() != null) {
			if (newOne.REF() == null) {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","struct modifier not mapped"));
			}
		}
		if (old.READONLY() != null) {
			if (newOne.READONLY() == null) {
				differencesList.add(new Difference(old.READONLY(),newOne,old.READONLY().getText(),"null","struct modifier not mapped"));
			}
		}
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier for stuct not mapped"));
			}
		}
		if (old.type_parameter_list() != null) {
			if (newOne.type_parameter_list() != null) {
				visitType_parameterListContext(old.type_parameter_list(),newOne.type_parameter_list());
			} else {
				differencesList.add(new Difference(old,newOne,"","null","TypeParams not mapped"));
			}
		}
		if (old.struct_interfaces() != null) {
			if (newOne.struct_interfaces() != null) {
				visitStruct_interfaces(old.struct_interfaces(),newOne.struct_interfaces());
			} else {
				differencesList.add(new Difference(old,newOne,"","null","Struct_interfaces not mapped"));
			}
		}
		if (old.type_parameter_constraints_clauses() != null) {
			if (newOne.type_parameter_constraints_clauses() != null) {
				visitType_parameter_constraints_clauses(old.type_parameter_constraints_clauses(),newOne.type_parameter_constraints_clauses());
			} else {
				differencesList.add(new Difference(old.type_parameter_constraints_clauses(),newOne,"type_parameter_constraints_clauses params","null","type_parameter_constraints_clauses params not mapped"));
			}
		}
		if (old.struct_body() != null) {
			if (newOne.struct_body() != null) {
				visitStruct_body(old.struct_body(),newOne.struct_body());
			} else {
				differencesList.add(new Difference(old,newOne,"","null","Struct_body not mapped"));
			}
		}
		
	}
	
	private void visitStruct_body(Struct_bodyContext old, Struct_bodyContext newOne) {
		List<Struct_member_declarationContext> oldList = old.struct_member_declaration();
		int i = 0;
		try {
			List<Struct_member_declarationContext> newList = newOne.struct_member_declaration();
			try {
				for (i = 0; i < oldList.size(); i++) {
					visitStruct_member_declaration(oldList.get(i),newList.get(i));
				}
			} catch (IndexOutOfBoundsException e){
				differencesList.add(new Difference(oldList.get(i),newOne,"","null","Struct member not mapped"));
			}
		}catch (NullPointerException e) {
			differencesList.add(new Difference(old,newOne,"","null","Struct members not mapped"));
		}
	}

	private void visitStruct_member_declaration(Struct_member_declarationContext old,Struct_member_declarationContext newOne) {
		if (old.attributes() != null) {
			if (newOne.attributes() != null) {
				visitAttributeContext(old.attributes(),newOne.attributes());
			} else {
				differencesList.add(new Difference(old,newOne,"annotation","null","struct member annotations not mapped"));
			}
		}
		if (old.all_member_modifiers() != null) {
			if (newOne.all_member_modifiers() != null) {
				visitModifiers(old.all_member_modifiers(),newOne.all_member_modifiers());
			} else {
				differencesList.add(new Difference(old.all_member_modifiers(),newOne,"modifier","null","struct member declaration modifiers not mapped"));
			}
		}
		if (old.common_member_declaration() != null) {
			if (newOne.common_member_declaration() != null) {
				visitCommon_member_declaration(old.common_member_declaration(),newOne.common_member_declaration());
			} else {
				differencesList.add(new Difference(old.all_member_modifiers(),newOne,"modifier","null","struct member declaration not mapped"));
			}
			
		} else if (old.FIXED() != null) {
			if (newOne.FIXED() != null) {
				visitType(old.type_(),newOne.type_());
				int i = 0;
				List<Fixed_size_buffer_declaratorContext> oldList = old.fixed_size_buffer_declarator();
				try {
					List<Fixed_size_buffer_declaratorContext> newList = newOne.fixed_size_buffer_declarator();
					try {
						for (i = 0; i < oldList.size(); i++) {
							visitFixed_size_buffer_declaratorContext(oldList.get(i),newList.get(i));
						}
					} catch (IndexOutOfBoundsException e){
						differencesList.add(new Difference(oldList.get(i),newOne,"fixed_size_buffer_declarator","null","some fixed_size_buffer_declarator not mapped"));
					}
				}catch (NullPointerException e) {
					differencesList.add(new Difference(old,newOne,"fixed_size_buffer_declarators","null","fixed_size_buffer_declarators not mapped"));
				}
			}else {
				differencesList.add(new Difference(old.all_member_modifiers(),newOne,"fixed","null","struct fixed member declaration not mapped"));
			}
			
		}
	}

	private void visitFixed_size_buffer_declaratorContext(Fixed_size_buffer_declaratorContext old,Fixed_size_buffer_declaratorContext newOne) {
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier for struct buffer dcl not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression for struct buffer dcl not mapped"));
			}
		}
	}

	private void visitStruct_interfaces(Struct_interfacesContext struct_interfaces,
			Struct_interfacesContext struct_interfaces2) {
		// TODO Auto-generated method stub
		
	}

	public void visitType_parameterListContext(Type_parameter_listContext old, Type_parameter_listContext newOne) {
		List<Type_parameterContext> oldTypes = old.type_parameter();
		try {
			List<Type_parameterContext> translatedTypes = newOne.type_parameter();
			try {
				for (int i = 0; i < oldTypes.size(); i++) {
					Type_parameterContext oldType = oldTypes.get(i);
					Type_parameterContext newType = translatedTypes.get(i);
					if (!oldType.getText().equals(newType.getText())) {
						differencesList.add(new Difference(old,newOne,oldType.getText(),newType.getText(),"Type Params"));
					}
				}
			} catch (IndexOutOfBoundsException e){
				differencesList.add(new Difference(old,newOne,"","null","Some TypeParams not mapped"));
			}
		}catch (NullPointerException e) {
			differencesList.add(new Difference(old,newOne,"","null","TypeParams not mapped"));
		}
	}
	
	public void visitClass_definition(Class_definitionContext old, Class_definitionContext newOne) {
		String oldName = old.identifier().getText();
		String gastName = newOne.identifier().getText();
		if (!oldName.equals(gastName)) {
			differencesList.add(new Difference(old,newOne,oldName,gastName,"class name"));
		}
		
		if (old.type_parameter_list() != null) {
			if (newOne.type_parameter_list() != null) {
				visitType_parameterListContext(old.type_parameter_list(),newOne.type_parameter_list());
			} else {
				differencesList.add(new Difference(old,newOne,"","null","TypeParams not mapped"));
			}
		}
		
		if (old.class_base() != null) {
			String newParent = newOne.class_base().class_type().getText();
			if (old.class_base().class_type() != null) {
				String oldParent = old.class_base().class_type().getText();
				if (!oldParent.equals(newParent)) {
					differencesList.add(new Difference(old.class_base().class_type(),newOne,oldParent,newParent,"parent name"));
				}
				if (old.class_base().namespace_or_type_name() != null) {
					if (newOne.class_base().namespace_or_type_name() != null) {
						List<Namespace_or_type_nameContext> oldIntterfaces = old.class_base().namespace_or_type_name();
						List<Namespace_or_type_nameContext> newInterfaces = newOne.class_base().namespace_or_type_name();
						try {
							for (int i = 0; i < oldIntterfaces.size(); i++) {
								String oldInterface = oldIntterfaces.get(i).getText();
								String newInterface = newInterfaces.get(i).getText();
								if (!oldInterface.equals(newInterface)) {
									differencesList.add(new Difference(old.type_parameter_list(),newOne,oldInterface,newInterface,"Interface"));
								}
							}
						} catch (IndexOutOfBoundsException e){
							differencesList.add(new Difference(old.type_parameter_list(),newOne,"","null","Some Interfaces not mapped"));
						}
					} else {
						differencesList.add(new Difference(old.class_base(),newOne,"","null","Implemented interfaces not mapped"));
					}
				}
			} else {
				differencesList.add(new Difference(old.class_base(),newOne,"","null","Class parent not mapped"));
			}
		}
		if (old.class_body() != null) {
			if (newOne.class_body() != null) {
				visitClassBody(old.class_body(),newOne.class_body() );
			} else {
				differencesList.add(new Difference(old.class_body(),newOne,"","null","Class body not mapped"));
			}
		}
		
	}

	public void visitClassBody(Class_bodyContext old, Class_bodyContext newOne) {
		if (old.class_member_declarations() != null) {
			if (newOne.class_member_declarations() != null) {
				visitClass_member_declarations(old.class_member_declarations(),newOne.class_member_declarations());
			} else {
				differencesList.add(new Difference(old.class_member_declarations(),newOne,"","null","Class body not mapped"));
			}
		}
	}
	
	public void visitClass_member_declarations(Class_member_declarationsContext old, Class_member_declarationsContext newOne) {
		List<Class_member_declarationContext> oldDcls = old.class_member_declaration();
		int i = 0;
		try {
			List<Class_member_declarationContext> newDcls = newOne.class_member_declaration();
			for (i = 0; i < oldDcls.size(); i++) {
				visitClass_member_declaration(oldDcls.get(i),newDcls.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(old,newOne,"index: " + String.valueOf(i),"null","Class member declaration not mapped"));
		}
	}
	
	public void visitClass_member_declaration(Class_member_declarationContext old, Class_member_declarationContext newOne) {
		if (old.attributes() != null) {
			if (newOne.attributes() != null) {
				visitAttributeContext(old.attributes(),newOne.attributes());
			} else {
				differencesList.add(new Difference(old,newOne,"annotations","null","member annotations not mapped"));
			}
		}
		if (old.all_member_modifiers() != null) {
			if (newOne.all_member_modifiers() != null) {
				visitModifiers(old.all_member_modifiers(),newOne.all_member_modifiers());
			} else {
				differencesList.add(new Difference(old.all_member_modifiers(),newOne,"modifier","null","member declaration modifiers not mapped"));
			}
		}
		if (old.common_member_declaration() != null) {
			visitCommon_member_declaration(old.common_member_declaration(),newOne.common_member_declaration());
		} else if (old.destructor_definition() != null) {
			visitDestructor_definition(old.destructor_definition(),newOne.destructor_definition());
		}
	}
	
	public void visitCommon_member_declaration(Common_member_declarationContext old, Common_member_declarationContext newOne) {
		if (old.constant_declaration() != null) {
			if (newOne.constant_declaration() != null) {
				//TODO
				return;
			}
		} else if (old.typed_member_declaration() != null) {
			if (newOne.typed_member_declaration() != null) {
				visitTyped_member_declarationContext(old.typed_member_declaration(),newOne.typed_member_declaration());
				return;
			}
		} else if (old.conversion_operator_declarator() != null) {
			if (newOne.conversion_operator_declarator() != null) {
				//conversion_operator_declarator (body | right_arrow throwable_expression ';') // C# 6
				//TODO
				return;
			}
		} else if (old.method_declaration() != null) {
			if (newOne.method_declaration() != null) {
				visitMethod_declaration(old.method_declaration(),newOne.method_declaration());
				return;
			}
		} else if (old.class_definition() != null) {
			if (newOne.class_definition() != null) {
				//TODO
				return;
			}
		} else if (old.struct_definition() != null) {
			if (newOne.struct_definition() != null) {
				visitStructDefinition(old.struct_definition(),newOne.struct_definition());
			} else {
				differencesList.add(new Difference(old.class_definition(),newOne,"","null","struct not mapped"));
			}
		} else if (old.interface_definition() != null) {
			if (newOne.interface_definition() != null) {
				//TODO
				return;
			}
		} else if (old.enum_definition() != null) {
			if (newOne.enum_definition() != null) {
				visitEnumDefinition(old.enum_definition(),newOne.enum_definition());
				return;
			}else {
				differencesList.add(new Difference(old.enum_definition(),newOne,"","null","enum not mapped"));
			}
		} else if (old.delegate_definition() != null) {
			if (newOne.delegate_definition() != null) {
				//TODO
				return;
			}
		}
	}
	
	private void visitEnumDefinition(Enum_definitionContext old, Enum_definitionContext newOne) {
		// TODO ENUM identifier enum_base? enum_body ';'?
		if (!old.identifier().getText().equals(newOne.identifier().getText())){
			differencesList.add(new Difference(old.identifier(),newOne.identifier(),old.identifier().getText(),newOne.identifier().getText(),"enum name not mapped right"));
		}
		if (old.enum_base() != null) {
			if (newOne.enum_base() != null) {
				visitEnumBase(old.enum_base(),newOne.enum_base());
			}
			else {
				differencesList.add(new Difference(old.enum_base(),newOne,"enum base","null","enum base not mapped"));
			}
		}
		if (newOne.enum_body() != null) {
			visitEnumBody(old.enum_body(),newOne.enum_body());
		} else {
			differencesList.add(new Difference(old.enum_body(),newOne,"enum body","null","enum body not mapped"));
		}
	}

	private void visitEnumBody(Enum_bodyContext old, Enum_bodyContext newOne) {
		int i = 0;
		List<Enum_member_declarationContext> oldList = old.enum_member_declaration();
		List<Enum_member_declarationContext> newList = newOne.enum_member_declaration();
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitEnum_member_declarationContext(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newOne,"Enum_member_declarationContext","null","Enum_member_declarationContext not mapped"));
		}
	}

	private void visitEnum_member_declarationContext(Enum_member_declarationContext old,
			Enum_member_declarationContext newOne) {
		if (old.attributes() != null) {
			if (newOne.attributes() != null) {
				visitAttributeContext(old.attributes(),newOne.attributes());
			} else {
				differencesList.add(new Difference(old.attributes(),newOne,old.attributes().getText(),"null","attributtes for enum member not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,"expr","null","enum member initial value expr not mapped"));
			}
			return;
		}
		if (newOne.identifier() != null) {
			visitIdentifier(old.identifier(),newOne.identifier());
		} else {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier enum member not mapped"));
		}
	}

	private void visitEnumBase(Enum_baseContext old, Enum_baseContext newOne) {
		if (!old.type_().getText().equals(newOne.type_().getText())) {
			differencesList.add(new Difference(old.type_(),newOne.type_(),old.type_().getText(),newOne.type_().getText(),"enum base not mapped right"));
		}
	}

	private void visitTyped_member_declarationContext(Typed_member_declarationContext old,Typed_member_declarationContext newOne) {
		if (old.REF() != null) {
			if (newOne.REF() == null) {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","member modifier not mapped"));
			}
		}
		if (old.READONLY() != null) {
			if (newOne.READONLY() == null) {
				differencesList.add(new Difference(old.READONLY(),newOne,old.READONLY().getText(),"null","member modifier not mapped"));
			}
		}
		if (old.type_() != null) {
			if (newOne.type_() == null) {
				differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","member type not mapped"));
			}
		}
		if (old.namespace_or_type_name() != null) {
			
		} else if (old.method_declaration() != null) {
			if (newOne.method_declaration() != null) {
				visitMethod_declaration(old.method_declaration(),newOne.method_declaration());
			} else {
				differencesList.add(new Difference(old.method_declaration(),newOne,old.method_declaration().getText(),"null","method dcl not mapped"));
			}
		} else if (old.property_declaration() != null) {
			//TODO
		} else if (old.indexer_declaration() != null) {
			//TODO
		} else if (old.operator_declaration() != null) {
			//TODO
		} else if (old.field_declaration() != null) {
			if (newOne.field_declaration() != null) {
				visirFieldDeclaration(old.field_declaration(),newOne.field_declaration());
			} else {
				differencesList.add(new Difference(old.field_declaration(),newOne,old.field_declaration().getText(),"null","member dcl not mapped"));
			}
		}
	}

	private void visitMethod_declaration(Method_declarationContext old,Method_declarationContext newOne) {
		if (!old.method_member_name().getText().equals(newOne.method_member_name().getText())) {
			differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"method name not the same"));
		}
		if (old.type_parameter_list() != null) {
			if (newOne.type_parameter_list() != null) {
				visitType_parameterListContext(old.type_parameter_list(),newOne.type_parameter_list());
			} else {
				differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"method name not the same"));
			}
		}
		if (old.formal_parameter_list() != null) {
			if (newOne.formal_parameter_list() != null) {
				visitFormal_parameter_list(old.formal_parameter_list(),newOne.formal_parameter_list());
			} else {
				differencesList.add(new Difference(old.formal_parameter_list(),newOne,"Formal params","null","method params not mapped"));
			}
		}
		if (old.type_parameter_constraints_clauses() != null) {
			if (newOne.type_parameter_constraints_clauses() != null) {
				visitType_parameter_constraints_clauses(old.type_parameter_constraints_clauses(),newOne.type_parameter_constraints_clauses());
			} else {
				differencesList.add(new Difference(old.type_parameter_constraints_clauses(),newOne,"type_parameter_constraints_clauses params","null","type_parameter_constraints_clauses params not mapped"));
			}
		}
		if (old.method_body() != null) {
			if (newOne.method_body() != null) {
				visitMethod_body(old.method_body(),newOne.method_body());
			} else {
				differencesList.add(new Difference(old.method_body(),newOne,"method_body","null","method_body not mapped"));
			}
		}
		if (old.throwable_expression() != null) {
			if (newOne.throwable_expression() != null) {
				visitThrowable_expression(old.throwable_expression(),newOne.throwable_expression());
			} else {
				differencesList.add(new Difference(old.throwable_expression(),newOne,"throws exception","null","throws exception not mapped"));
			}
		}
	}
	
	private void visitThrowable_expression(Throwable_expressionContext old, Throwable_expressionContext newOne) {
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,"throws exception expression","null","throws exception expression not mapped"));
			}
			return;
		}
		if (old.throw_expression() != null) {
			if (newOne.throw_expression() != null) {
				visitThrow_expression(old.throw_expression(),newOne.throw_expression());
			} else {
				differencesList.add(new Difference(old.throw_expression(),newOne,"throwable expression","null","throwable expression not mapped"));
			}
		}
	}

	private void visitThrow_expression(Throw_expressionContext old, Throw_expressionContext newOne) {
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,"throws expression","null","throws expression not mapped"));
		}
	}

	private void visitMethod_body(Method_bodyContext old, Method_bodyContext newOne) {
		if (old.block() != null) {
			if (newOne.block() != null) {
				visitBlock(old.block(),newOne.block());
			} else {
				differencesList.add(new Difference(old.block(),newOne,"method body","null","method body not mapped"));
			}
		}
	}

	private void visitBlock(BlockContext old, BlockContext newOne) {
		if (old.statement_list() != null) {
			if (newOne.statement_list() != null) {
				visitStatement_list(old.statement_list(),newOne.statement_list());
			} else {
				differencesList.add(new Difference(old.statement_list(),newOne,"body","null","statement_list not mapped"));
			}
		}
	}

	private void visitStatement_list(Statement_listContext old, Statement_listContext newOne) {
		int i = 0;
		List<StatementContext> oldList = old.statement();
		List<StatementContext> newList = newOne.statement();
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitStatementContext(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newOne,"statement","null","statement not mapped"));
		}
	}

	private void visitStatementContext(StatementContext old, StatementContext newOne) {
		if (old.labeled_Statement() != null) {
			if (newOne.labeled_Statement() != null) {
				visitLabeled_Statement(old.labeled_Statement(),newOne.labeled_Statement());
			} else {
				differencesList.add(new Difference(old.labeled_Statement(),newOne,"statement","null","labeled_Statement not mapped"));
			}
		} else if (old.declarationStatement() != null) {
			if (newOne.declarationStatement() != null) {
				visitDeclarationStatement(old.declarationStatement(),newOne.declarationStatement());
			} else {
				differencesList.add(new Difference(old.declarationStatement(),newOne,"statement","null","declarationStatement not mapped"));
			}
		} else if (old.embedded_statement() != null) {
			if (newOne.embedded_statement() != null) {
				visitEmbedded_statement(old.embedded_statement(),newOne.embedded_statement());
			} else {
				differencesList.add(new Difference(old.embedded_statement(),newOne,"statement","null","embedded_statement not mapped"));
			}
		}
	}

	private void visitEmbedded_statement(Embedded_statementContext old,Embedded_statementContext newOne) {
		if (old.block() != null) {
			if (newOne.block() != null) {
				visitBlock(old.block(),newOne.block());
			} else {
				differencesList.add(new Difference(old.block(),newOne,"block","null","block not mapped"));
			}
			return;
		} 
		if (old.simple_embedded_statement() != null) {
			if (newOne.simple_embedded_statement() != null) {
				visitSimple_embedded_statement(old.simple_embedded_statement(),newOne.simple_embedded_statement());
			} else {
				differencesList.add(new Difference(old.simple_embedded_statement(),newOne,"simple_embedded_statement","null","simple_embedded_statement not mapped"));
			}
		}
	}
	
	private void visitSimple_embedded_statement(Simple_embedded_statementContext old,Simple_embedded_statementContext newOne) {
		if (old instanceof TheEmptyStatementContext) {
			if (!(newOne.getChildCount() < 1 && newOne.getChild(0) instanceof TerminalNode)) {
				differencesList.add(new Difference(old,newOne,"TheEmptyStatementContext","","TheEmptyStatementContext not mapped"));
			}
        } else if (old instanceof ExpressionStatementContext) {
			try {
				if (((ExpressionStatementContext) newOne).expression() != null) {
					visitExpression(((ExpressionStatementContext) old).expression(), ((ExpressionStatementContext) newOne).expression());
				} else {
					differencesList.add(new Difference(old, newOne, "ExpressionStatementContext", "null", "ExpressionStatementContext not mapped"));
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		} else if (old instanceof IfStatementContext) {
        	if (newOne != null) {
            	try{
            		visitIfStatementContext((IfStatementContext) old,(IfStatementContext) newOne);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			} else {
        		differencesList.add(new Difference(old,newOne,"IfStatementContext","null","IfStatementContext not mapped"));
        	}
        } else if (old instanceof SwitchStatementContext) {
        	if (newOne != null) {
            	visitSwitchStatementContext((SwitchStatementContext) old,(SwitchStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"SwitchStatementContext","null","SwitchStatementContext not mapped"));
        	}
        } else if (old instanceof WhileStatementContext) {
        	if (newOne != null) {
        		visitWhileStatementContext((WhileStatementContext) old,(WhileStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"WhileStatementContext","null","WhileStatementContext not mapped"));
        	}
        } else if (old instanceof DoStatementContext) {
        	if (newOne != null) {
        		visitDoStatementContext((DoStatementContext) old,(DoStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"DoStatementContext","null","DoStatementContext not mapped"));
        	}
        } else if (old instanceof ForStatementContext) {
        	if (newOne != null) {
        		visitForStatementContext((ForStatementContext) old,(ForStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"ForStatementContext","null","ForStatementContext not mapped"));
        	}
        } else if (old instanceof ForeachStatementContext) {
        	if (newOne != null) {
        		visitForeachStatementContext((ForeachStatementContext) old,(ForeachStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"ForeachStatementContext","null","ForeachStatementContext not mapped"));
        	}
        } else if (old instanceof BreakStatementContext) {
        	if (newOne == null) {
        		differencesList.add(new Difference(old,newOne,"BreakStatementContext","null","BreakStatementContext not mapped"));
        	}
        } else if (old instanceof ContinueStatementContext) {
        	if (newOne == null) {
        		differencesList.add(new Difference(old,newOne,"ContinueStatementContext","null","ContinueStatementContext not mapped"));
        	}
        } else if (old instanceof GotoStatementContext) {
        	if (newOne != null) {
        		visitGOTO((GotoStatementContext) old,(GotoStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"GotoStatementContext","null","GotoStatementContext not mapped"));
        	}
        } else if (old instanceof ReturnStatementContext) {
        	if (newOne == null) {
        		differencesList.add(new Difference(old,newOne,"ReturnStatementContext","null","ReturnStatementContext not mapped"));
        	} else {
        		if (((ReturnStatementContext) old).expression() != null) {
        			try{
						if (((ReturnStatementContext) newOne).expression() != null) {
							visitExpression(((ReturnStatementContext) old).expression(),((ReturnStatementContext) newOne).expression());
						} else {
							differencesList.add(new Difference(((ReturnStatementContext) old).expression(),newOne,((ReturnStatementContext) old).expression().getText(),"null","ReturnStatementContext expression not mapped"));
						}
        			} catch (Exception e) {
						//e.printStackTrace();
					}
				}
        	}
        } else if (old instanceof ThrowStatementContext) {
        	if (newOne == null) {
        		differencesList.add(new Difference(old,newOne,"ThrowStatementContext","null","ThrowStatementContext not mapped"));
        	} else {
        		if (((ThrowStatementContext) old).expression() != null) {
        			try {
						if (((ThrowStatementContext) newOne).expression() != null) {
							visitExpression(((ThrowStatementContext) old).expression(), ((ThrowStatementContext) newOne).expression());
						} else {
							differencesList.add(new Difference(((ThrowStatementContext) old).expression(), newOne, ((ThrowStatementContext) old).expression().getText(), "null", "ThrowStatementContext expression not mapped"));
						}
					} catch (Exception e) {
						//e.printStackTrace();
					}
				}
        	}
        } else if (old instanceof TryStatementContext) {
        	if (newOne != null) {
        		try{
        			visitTryStatementContext((TryStatementContext) old,(TryStatementContext) newOne);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			} else {
        		differencesList.add(new Difference(old,newOne,"TryStatementContext","null","TryStatementContext not mapped"));
        	}
        } else if (old instanceof CheckedStatementContext) {
        	if (newOne == null) {
        		differencesList.add(new Difference(old,newOne,"CheckedStatementContext","null","CheckedStatementContext not mapped"));
        	} else {
        		if (((CheckedStatementContext) old).block() != null) {
        			if (((CheckedStatementContext) newOne).block() != null) {
        				visitBlock(((CheckedStatementContext) old).block(),((CheckedStatementContext) newOne).block());
        			} else {
        				differencesList.add(new Difference(((CheckedStatementContext) old).block(),newOne,"Block","null","CheckedStatementContext block not mapped"));
        			}
        		}
        	}
        } else if (old instanceof UncheckedStatementContext) {
        	if (newOne == null) {
        		differencesList.add(new Difference(old,newOne,"UncheckedStatementContext","null","UncheckedStatementContext not mapped"));
        	} else {
        		if (((UncheckedStatementContext) old).block() != null) {
        			if (((UncheckedStatementContext) newOne).block() != null) {
        				visitBlock(((UncheckedStatementContext) old).block(),((UncheckedStatementContext) newOne).block());
        			} else {
        				differencesList.add(new Difference(((UncheckedStatementContext) old).block(),newOne,"Block","null","UncheckedStatementContext block not mapped"));
        			}
        		}
        	}
        } else if (old instanceof LockStatementContext) {
        	if (newOne != null) {
        		visitLockStatementContext((LockStatementContext) old,(LockStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"LockStatementContext","null","LockStatementContext not mapped"));
        	}
        } else if (old instanceof UsingStatementContext) {
        	if (newOne != null) {
        		try{
        			visitUsingStatementContext((UsingStatementContext) old,(UsingStatementContext) newOne);
				} catch (Exception e) {
					//e.printStackTrace();
				}
			} else {
        		differencesList.add(new Difference(old,newOne,"UsingStatementContext","null","UsingStatementContext not mapped"));
        	}
        } else if (old instanceof YieldStatementContext) {
        	if (newOne != null) {
        		visitYieldStatementContext((YieldStatementContext) old,(YieldStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"YieldStatementContext","null","YieldStatementContext not mapped"));
        	}
        } else if (old instanceof UnsafeStatementContext) {
        	if (newOne == null) {
        		differencesList.add(new Difference(old,newOne,"UnsafeStatementContext","null","UnsafeStatementContext not mapped"));
        	} else {
        		if (((UnsafeStatementContext) old).block() != null) {
        			if (((UnsafeStatementContext) newOne).block() != null) {
        				visitBlock(((UnsafeStatementContext) old).block(),((UnsafeStatementContext) newOne).block());
        			} else {
        				differencesList.add(new Difference(((UnsafeStatementContext) old).block(),newOne,"Block","null","UnsafeStatementContext block not mapped"));
        			}
        		}
        	}
        } else if (old instanceof FixedStatementContext) { 
        	if (newOne != null) {
        		visitFixedStatementContext((FixedStatementContext) old,(FixedStatementContext) newOne);
        	} else {
        		differencesList.add(new Difference(old,newOne,"FixedStatementContext","null","FixedStatementContext not mapped"));
        	}
        }
	}
	
	private void visitFixedStatementContext(FixedStatementContext old, FixedStatementContext newOne) {
		if (old.FIXED() != null) {
			if (newOne.FIXED() == null) {
				differencesList.add(new Difference(old.FIXED(),newOne,old.FIXED().getText(),"null","FIXED not mapped"));
			}
		}
		if (old.pointer_type() != null) {
			if (newOne.pointer_type() != null) {
				visitPointer_type(old.pointer_type(),newOne.pointer_type());
			} else {
				differencesList.add(new Difference(old.pointer_type(),newOne,"pointer_type","null","pointer_type in fixed stmt not mapped"));
			}
		}
		if (old.fixed_pointer_declarators() != null) {
			if (newOne.fixed_pointer_declarators() != null) {
				visitFixed_pointer_declarators(old.fixed_pointer_declarators(),newOne.fixed_pointer_declarators());
			} else {
				differencesList.add(new Difference(old.fixed_pointer_declarators(),newOne,"fixed_pointer_declarators","null","fixed_pointer_declarators in fixed stmt not mapped"));
			}
		}
		if (old.embedded_statement() != null) {
			if (newOne.embedded_statement() != null) {
				visitEmbedded_statement(old.embedded_statement(),newOne.embedded_statement());
			} else {
				differencesList.add(new Difference(old.embedded_statement(),newOne,"embedded_statement","null","embedded_statement in fixed stmt not mapped"));
			}
		}
	}

	private void visitFixed_pointer_declarators(Fixed_pointer_declaratorsContext old,Fixed_pointer_declaratorsContext newOne) {
		// TODO Auto-generated method stub
		/*
		 * fixed_pointer_declarators
	: fixed_pointer_declarator (','  fixed_pointer_declarator)*
	;

fixed_pointer_declarator
	: identifier '=' fixed_pointer_initializer
	;

fixed_pointer_initializer
	: '&'? expression
	| stackalloc_initializer
	;

fixed_size_buffer_declarator
	: identifier '[' expression ']'
	;

stackalloc_initializer
	: STACKALLOC type_ '[' expression ']'
	| STACKALLOC type_? '[' expression? ']' OPEN_BRACE expression (',' expression)* ','? CLOSE_BRACE
		 */
	}

	private void visitPointer_type(Pointer_typeContext old, Pointer_typeContext newOne) {
		// TODO Auto-generated method stub
		/*
		 *  (simple_type | class_type) (rank_specifier | '?')* '*'
	| VOID '*'
		 */
		if (!old.getText().equals(newOne.getText())){
			differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"Fixed_pointer_declaratorsContext name not right"));
		}
	}

	private void visitYieldStatementContext(YieldStatementContext old, YieldStatementContext newOne) {
		if (old.YIELD() != null) {
			if (newOne.YIELD() == null) {
				differencesList.add(new Difference(old.YIELD(),newOne,old.YIELD().getText(),"null","YIELD not mapped"));
			}
		}
		if (old.RETURN() != null) {
			if (newOne.RETURN() == null) {
				differencesList.add(new Difference(old.RETURN(),newOne,old.RETURN().getText(),"null","RETURN not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
			}
		}
		if (old.BREAK() != null) {
			if (newOne.BREAK() == null) {
				differencesList.add(new Difference(old.BREAK(),newOne,old.BREAK().getText(),"null","BREAK not mapped"));
			}
		}
	}

	private void visitUsingStatementContext(UsingStatementContext old, UsingStatementContext newOne) { 
		if (old.resource_acquisition() != null) {
			if (newOne.resource_acquisition() != null) {
				visitResource_acquisition(old.resource_acquisition(),newOne.resource_acquisition());
			} else {
				differencesList.add(new Difference(old.resource_acquisition(),newOne,"resource_acquisition","null","resource_acquisition in using stmt not mapped"));
			}
		}
		if (old.embedded_statement() != null) {
			if (newOne.embedded_statement() != null) {
				visitEmbedded_statement(old.embedded_statement(),newOne.embedded_statement());
			} else {
				differencesList.add(new Difference(old.embedded_statement(),newOne,"embedded_statement","null","embedded_statement in using stmt not mapped"));
			}
		}
	}
	
	private void visitResource_acquisition(Resource_acquisitionContext old,Resource_acquisitionContext newOne) {
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
			}
		}
		if (old.local_variable_declaration() != null) {
			if (newOne.local_variable_declaration() != null) {
				visitLocal_variable_declaration(old.local_variable_declaration(),newOne.local_variable_declaration());
			} else {
				differencesList.add(new Difference(old.local_variable_declaration(),newOne,"local_variable_declaration","null","local_variable_declaration not mapped"));
			}
			return;
		}
	}

	private void visitLockStatementContext(LockStatementContext old, LockStatementContext newOne) {
		if (newOne.LOCK() == null) {
			differencesList.add(new Difference(old.LOCK(),newOne,old.LOCK().getText(),"null","LOCK not mapped"));
		}
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
		if (old.embedded_statement() != null) {
			if (newOne.embedded_statement() != null) {
				visitEmbedded_statement(old.embedded_statement(),newOne.embedded_statement());
			} else {
				differencesList.add(new Difference(old.embedded_statement(),newOne,"embedded_statement","null","embedded_statement not mapped"));
			}
		}
	}

	private void visitTryStatementContext(TryStatementContext old, TryStatementContext newOne) {
		if (old.block() != null) {
			if (newOne.block() != null) {
				visitBlock(old.block(),newOne.block());
			} else {
				differencesList.add(new Difference(old.block(),newOne,"block","null","block not mapped"));
			}
		} 
		if (old.catch_clauses() != null) {
			if (newOne.catch_clauses() != null) {
				visitCatch_clauses(old.catch_clauses(),newOne.catch_clauses());
			} else {
				differencesList.add(new Difference(old.catch_clauses(),newOne,"catch_clauses","null","catch_clauses not mapped"));
			}
		} 
		if (old.finally_clause() != null) {
			if (newOne.finally_clause() != null) {
				visitFinally_clause(old.finally_clause(),newOne.finally_clause());
			} else {
				differencesList.add(new Difference(old.finally_clause(),newOne,"finally_clause","null","finally_clause not mapped"));
			}
		}
	}
	
	private void visitCatch_clauses(Catch_clausesContext old, Catch_clausesContext newOne) {
		if (old.specific_catch_clause() != null) {
			if (old.specific_catch_clause().size() != 0) {
				if (newOne.specific_catch_clause() != null) {
					if (newOne.specific_catch_clause().size() != 0) {
						visitSpecific_catch_clause(old.specific_catch_clause(),newOne.specific_catch_clause());
					} else {
						differencesList.add(new Difference(old,newOne,"specific_catch_clause","null","specific_catch_clause from catch not mapped"));
					}
					
				} else {
					differencesList.add(new Difference(old,newOne,"specific_catch_clause","null","specific_catch_clause from catch not mapped"));
				}
			}
			
		}
		if (old.general_catch_clause() != null) {
			if (newOne.general_catch_clause() != null) {
				visitGeneral_catch_clause(old.general_catch_clause(),newOne.general_catch_clause());
			} else {
				differencesList.add(new Difference(old,newOne,"general_catch_clause","null","general_catch_clause from catch not mapped"));
			}
		}
		
	}

	private void visitGeneral_catch_clause(General_catch_clauseContext old,General_catch_clauseContext newOne) {
		if (old.exception_filter() != null) {
			if (newOne.exception_filter() != null) {
				visitException_filter(old.exception_filter(),newOne.exception_filter());
			} else {
				differencesList.add(new Difference(old.exception_filter(),newOne,old.exception_filter().getText(),"null","exception_filter not mapped"));
			}
			return;
		}
		if (newOne.block() != null) {
			visitBlock(old.block(),newOne.block());
		} else {
			differencesList.add(new Difference(old.block(),newOne,"Block","null","block from catch not mapped"));
		}
	}

	private void visitSpecific_catch_clause(List<Specific_catch_clauseContext> oldList,List<Specific_catch_clauseContext> newList) {
		int i = 0;
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitSpecific_catch_clauseContext(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newList.get(0).getParent(),"Specific_catch_clauseContext","null","Specific_catch_clauseContext not mapped"));
		}
	}

	private void visitSpecific_catch_clauseContext(Specific_catch_clauseContext old,Specific_catch_clauseContext newOne) {
		if (newOne.class_type() != null) {
			visitClass_type(old.class_type(),newOne.class_type());
		} else {
			differencesList.add(new Difference(old.class_type(),newOne,"class_type","null","class_type from catch not mapped"));
		}
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier label not mapped"));
			}
			return;
		}
		if (old.exception_filter() != null) {
			if (newOne.exception_filter() != null) {
				visitException_filter(old.exception_filter(),newOne.exception_filter());
			} else {
				differencesList.add(new Difference(old.exception_filter(),newOne,old.exception_filter().getText(),"null","exception_filter not mapped"));
			}
			return;
		}
		if (newOne.block() != null) {
			visitBlock(old.block(),newOne.block());
		} else {
			differencesList.add(new Difference(old.block(),newOne,"Block","null","block from catch not mapped"));
		}
		
	}

	private void visitException_filter(Exception_filterContext old,Exception_filterContext newOne) {
		if (newOne.WHEN() == null) {
			differencesList.add(new Difference(old.WHEN(),newOne,"WHEN","null","WHEN from catch not mapped"));
		}
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
	}

	private void visitClass_type(Class_typeContext old, Class_typeContext newOne) {
		// TODO Auto-generated method stub
		if (!old.getText().equals(newOne.getText())){
			differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"class_type name not right"));
		}
	}

	private void visitFinally_clause(Finally_clauseContext old, Finally_clauseContext newOne) {
		if (newOne.block() != null) {
			visitBlock(old.block(),newOne.block());
		} else {
			differencesList.add(new Difference(old.block(),newOne,"Block","null","block from finally not mapped"));
		}
	}

	private void visitGOTO(GotoStatementContext old, GotoStatementContext newOne) {
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier label not mapped"));
			}
			return;
		}
		if (old.DEFAULT() != null) {
			if (newOne.DEFAULT() == null) {
				differencesList.add(new Difference(old.DEFAULT(),newOne,old.DEFAULT().getText(),"null","DEFAULT label not mapped"));
			}
			return;
		}
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
		
	}

	private void visitForeachStatementContext(ForeachStatementContext old, ForeachStatementContext newOne) {
		if (old.AWAIT() != null) {
			if (newOne.AWAIT() == null) {
				differencesList.add(new Difference(old.AWAIT(),newOne,old.AWAIT().getText(),"null","AWAIT not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
			}
		}
		if (newOne.identifier() != null) {
			visitIdentifier(old.identifier(),newOne.identifier());
		} else {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier not mapped"));
		}
		if (old.embedded_statement() != null) {
			if (newOne.embedded_statement() != null) {
				visitEmbedded_statement(old.embedded_statement(),newOne.embedded_statement());
			} else {
				differencesList.add(new Difference(old.embedded_statement(),newOne,"embedded_statement","null","embedded_statement not mapped"));
			}
		}
		if (old.local_variable_type() != null) {
			if (newOne.local_variable_type() != null) {
				visitLocal_variable_type(old.local_variable_type(),newOne.local_variable_type());
			} else {
				differencesList.add(new Difference(old.local_variable_type(),newOne,old.local_variable_type().getText(),"null","local_variable_type not mapped"));
			}
		}
	}

	private void visitForStatementContext(ForStatementContext old, ForStatementContext newOne) {
		if (old.for_initializer() != null) {
			if (newOne.for_initializer() != null) {
				visitFor_initializer(old.for_initializer(),newOne.for_initializer());
			} else {
				differencesList.add(new Difference(old.for_initializer(),newOne,old.for_initializer().getText(),"null","for_initializer not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
			}
		}
		if (old.for_iterator() != null) {
			if (newOne.for_iterator() != null) {
				visitFor_iterator(old.for_iterator(),newOne.for_iterator());
			} else {
				differencesList.add(new Difference(old.for_iterator(),newOne,old.for_iterator().getText(),"null","for_iterator not mapped"));
			}
		}
		if (old.embedded_statement() != null) {
			if (newOne.embedded_statement() != null) {
				visitEmbedded_statement(old.embedded_statement(),newOne.embedded_statement());
			} else {
				differencesList.add(new Difference(old.embedded_statement(),newOne,"embedded_statement","null","embedded_statement not mapped"));
			}
		}
	}

	private void visitFor_iterator(For_iteratorContext old, For_iteratorContext newOne) {
		int i = 0;
		List<ExpressionContext> oldList = old.expression();
		List<ExpressionContext> newList = newOne.expression();
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitExpression(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newOne,"ExpressionContext","null","ExpressionContext not mapped"));
		}
	}

	private void visitFor_initializer(For_initializerContext old, For_initializerContext newOne) {
		if (old.local_variable_declaration() != null) {
			if (newOne.local_variable_declaration() != null) {
				visitLocal_variable_declaration(old.local_variable_declaration(),newOne.local_variable_declaration());
			} else {
				differencesList.add(new Difference(old.local_variable_declaration(),newOne,"local_variable_declaration","null","local_variable_declaration not mapped"));
			}
			return;
		}
		int i = 0;
		List<ExpressionContext> oldList = old.expression();
		List<ExpressionContext> newList = newOne.expression();
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitExpression(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newOne,"ExpressionContext","null","ExpressionContext not mapped"));
		}
	}

	private void visitDoStatementContext(DoStatementContext old, DoStatementContext newOne) {
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
		if (old.embedded_statement() != null) {
			if (newOne.embedded_statement() != null) {
				visitEmbedded_statement(old.embedded_statement(),newOne.embedded_statement());
			} else {
				differencesList.add(new Difference(old.embedded_statement(),newOne,"embedded_statement","null","embedded_statement not mapped"));
			}
		}
	}

	private void visitWhileStatementContext(WhileStatementContext old, WhileStatementContext newOne) {
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
		if (old.embedded_statement() != null) {
			if (newOne.embedded_statement() != null) {
				visitEmbedded_statement(old.embedded_statement(),newOne.embedded_statement());
			} else {
				differencesList.add(new Difference(old.embedded_statement(),newOne,"embedded_statement","null","embedded_statement not mapped"));
			}
		}
	}

	private void visitSwitchStatementContext(SwitchStatementContext old, SwitchStatementContext newOne) {
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
		if (newOne.switch_section() != null) {
			visitSwitch_section(old.switch_section(),newOne.switch_section());
		} else {
			differencesList.add(new Difference(old,newOne,"switch_section","null","switch_section not mapped"));
		}
	}

	private void visitSwitch_section(List<Switch_sectionContext> oldList,List<Switch_sectionContext> newList) {
		int i = 0;
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitSwitch_sectionContext(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newList.get(0).getParent(),"Switch_sectionContext","null","Switch_sectionContext not mapped"));
		}
	}

	private void visitSwitch_sectionContext(Switch_sectionContext old,Switch_sectionContext newOne) {
		if (newOne.switch_label() != null) {
			visitSwitch_label(old.switch_label(),newOne.switch_label());
		} else {
			differencesList.add(new Difference(old,newOne,"switch_label","null","switch_label not mapped"));
		}
		if (newOne.statement_list() != null) {
			visitStatement_list(old.statement_list(),newOne.statement_list());
		} else {
			differencesList.add(new Difference(old,newOne,"statement_list","null","statement_list not mapped"));
		}
	}

	private void visitSwitch_label(List<Switch_labelContext> oldList, List<Switch_labelContext> newList) {
		int i = 0;
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitSwitch_labelContext(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newList.get(0).getParent(),"Switch_labelContext","null","Switch_labelContext not mapped"));
		}
		
	}

	private void visitSwitch_labelContext(Switch_labelContext old,Switch_labelContext newOne) {
		if (old.DEFAULT() != null) {
			if (newOne.DEFAULT() == null) {
				differencesList.add(new Difference(old.DEFAULT(),newOne,old.DEFAULT().getText(),"null","DEFAULT case not mapped"));
			}
			return;
		}
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
		if (old.case_guard() != null) {
			if (newOne.case_guard() != null) {
				visitCase_guard(old.case_guard(),newOne.case_guard());
			} else {
				differencesList.add(new Difference(old.case_guard(),newOne,old.case_guard().getText(),"null","case_guard not mapped"));
			}
		}
	}

	private void visitCase_guard(Case_guardContext old, Case_guardContext newOne) {
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
	}

	private void visitIfStatementContext(IfStatementContext old,IfStatementContext newOne) {
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
		if (newOne.if_body(0) != null) {
			visitIf_body(old.if_body(0),newOne.if_body(0));
		} else {
			differencesList.add(new Difference(old.if_body(0),newOne,"if_body","null","if_body not mapped"));
		}
		if (old.ELSE() != null) {
			if (newOne.if_body(1) != null) {
				visitIf_body(old.if_body(1),newOne.if_body(1));
			} else {
				differencesList.add(new Difference(old.if_body(1),newOne,"else_body","null","else_body not mapped"));
			}
		}
	}

	private void visitIf_body(If_bodyContext old, If_bodyContext newOne) {
		if (old.block() != null) {
			if (newOne.block() != null) {
				visitBlock(old.block(),newOne.block());
			} else {
				differencesList.add(new Difference(old.block(),newOne,"block","null","block not mapped"));
			}
			return;
		} 
		if (old.simple_embedded_statement() != null) {
			if (newOne.simple_embedded_statement() != null) {
				visitSimple_embedded_statement(old.simple_embedded_statement(),newOne.simple_embedded_statement());
			} else {
				differencesList.add(new Difference(old.simple_embedded_statement(),newOne,"simple_embedded_statement","null","simple_embedded_statement not mapped"));
			}
		}
	}

	private void visitDeclarationStatement(DeclarationStatementContext old,DeclarationStatementContext newOne) {
		if (old.local_variable_declaration() != null) {
			if (newOne.local_variable_declaration() != null) {
				visitLocal_variable_declaration(old.local_variable_declaration(),newOne.local_variable_declaration());
			} else {
				differencesList.add(new Difference(old.local_variable_declaration(),newOne,"local_variable_declaration","null","local_variable_declaration not mapped"));
			}
			return;
		}
		if (old.local_constant_declaration() != null) {
			if (newOne.local_constant_declaration() != null) {
				visitLocal_constant_declaration(old.local_constant_declaration(),newOne.local_constant_declaration());
			} else {
				differencesList.add(new Difference(old.local_constant_declaration(),newOne,"local_constant_declaration","null","local_constant_declaration not mapped"));
			}
			return;
		}
		if (old.local_function_declaration() != null) {
			if (newOne.local_function_declaration() != null) {
				visitLocal_function_declaration(old.local_function_declaration(),newOne.local_function_declaration());
			} else {
				differencesList.add(new Difference(old.local_function_declaration(),newOne,"local_function_declaration","null","local_function_declaration not mapped"));
			}
			return;
		}
	}

	private void visitLocal_function_declaration(Local_function_declarationContext old,Local_function_declarationContext newOne) {
		if (newOne.local_function_header() != null) {
			visitLocal_function_header(old.local_function_header(),newOne.local_function_header());
		} else {
			differencesList.add(new Difference(old.local_function_header(),newOne,"local_function_header","null","local_function_header not mapped"));
		}
		if (newOne.local_function_body() != null) {
			visitLocal_function_body(old.local_function_body(),newOne.local_function_body());
		} else {
			differencesList.add(new Difference(old.local_function_body(),newOne,"local_function_body","null","local_function_body not mapped"));
		}
	}

	private void visitLocal_function_body(Local_function_bodyContext old,Local_function_bodyContext newOne) {
		if (old.block() != null) {
			if (newOne.block() != null) {
				visitBlock(old.block(),newOne.block());
			} else {
				differencesList.add(new Difference(old.block(),newOne,"block","null","block not mapped"));
			}
			return;
		} 
		if (old.throwable_expression() != null) {
			if (newOne.throwable_expression() != null) {
				visitThrowable_expression(old.throwable_expression(),newOne.throwable_expression());
			} else {
				differencesList.add(new Difference(old.throwable_expression(),newOne,"throws exception","null","throws exception not mapped"));
			}
		}
	}

	private void visitLocal_function_header(Local_function_headerContext old,Local_function_headerContext newOne) {
		if (old.local_function_modifiers() != null) {
			if (newOne.local_function_modifiers() != null) {
				visitLocal_function_modifiers(old.local_function_modifiers(),newOne.local_function_modifiers());
			} else {
				differencesList.add(new Difference(old.local_function_modifiers(),newOne,"local_function_modifiers","null","local_function_modifiers not mapped"));
			}
		}
		visitReturn_type(old.return_type(),newOne.return_type());
		visitIdentifier(old.identifier(),newOne.identifier());
		if (old.type_parameter_list() != null) {
			if (newOne.type_parameter_list() != null) {
				visitType_parameterListContext(old.type_parameter_list(),newOne.type_parameter_list());
			} else {
				differencesList.add(new Difference(old,newOne,"","null","TypeParams not mapped"));
			}
		}
		if (old.formal_parameter_list() != null) {
			if (newOne.formal_parameter_list() != null) {
				visitFormal_parameter_list(old.formal_parameter_list(),newOne.formal_parameter_list());
			} else {
				differencesList.add(new Difference(old.formal_parameter_list(),newOne,"Formal params","null","method params not mapped"));
			}
		}
		if (old.type_parameter_constraints_clauses() != null) {
			if (newOne.type_parameter_constraints_clauses() != null) {
				visitType_parameter_constraints_clauses(old.type_parameter_constraints_clauses(),newOne.type_parameter_constraints_clauses());
			} else {
				differencesList.add(new Difference(old.type_parameter_constraints_clauses(),newOne,"type_parameter_constraints_clauses params","null","type_parameter_constraints_clauses params not mapped"));
			}
		}
	}
	
	private void visitReturn_type(Return_typeContext old, Return_typeContext newOne) {
		if (old.type_() != null) {
			if (newOne.type_() != null) {
				visitType(old.type_(),newOne.type_());
			} else {
				differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","return type not mapped"));
			}
			return;
		}
		if (newOne.VOID() == null) {
			differencesList.add(new Difference(old.VOID(),newOne,old.VOID().getText(),"null","void return type not mapped"));
		}
	}

	private void visitType(Type_Context old, Type_Context newOne) {
		if (newOne.base_type() != null) {
			visitBase_type(old.base_type(),newOne.base_type());
		} else {
			differencesList.add(new Difference(old.base_type(),newOne,old.base_type().getText(),"null","array type not mapped"));
		}
		if (old.INTERR() != null) {
			if (newOne.INTERR() != null) {
				if (old.INTERR().size() != newOne.INTERR().size()) {
					differencesList.add(new Difference(old,newOne,"?","","simbol not mapped n times"));
				}
			} else {
				differencesList.add(new Difference(old,newOne,"?","null","simbol not mapped"));
			}
		}
		if (old.STAR() != null) {
			if (newOne.STAR() != null) {
				if (old.STAR().size() != newOne.STAR().size()) {
					differencesList.add(new Difference(old,newOne,"*","","simbol not mapped n times"));
				}
			} else {
				differencesList.add(new Difference(old,newOne,"?","null","simbol not mapped"));
			}
		}
		if (old.rank_specifier() != null) {
			if (newOne.rank_specifier() != null) {
				if (old.rank_specifier().size() != newOne.rank_specifier().size()) {
					differencesList.add(new Difference(old,newOne,String.valueOf(old.rank_specifier().size()),String.valueOf(newOne.rank_specifier().size()),"array dimensions not mapped right"));
				}
			} else {
				differencesList.add(new Difference(old,newOne,String.valueOf(old.rank_specifier().size()),"null","array dimensions not mapped"));
			}
		}
	}

	private void visitLocal_function_modifiers(Local_function_modifiersContext old,Local_function_modifiersContext newOne) {
		if (!old.getText().equals(newOne.getText())) {
			differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"local_function_modifiers not mapped right"));
		}
	}

	private void visitLocal_constant_declaration(Local_constant_declarationContext old,	Local_constant_declarationContext newOne) {
		if (newOne.type_() != null) {
			if (!old.type_().getText().equals(newOne.type_().getText())) {
				differencesList.add(new Difference(old.type_(),newOne.type_(),old.type_().getText(),newOne.type_().getText(),"type name not mapped right"));
			}
		} else {
			differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","type name not mapped"));
		}
		if (newOne.constant_declarators() != null) {
			visitConstant_declarators(old.constant_declarators(),newOne.constant_declarators());
		} else {
			differencesList.add(new Difference(old.constant_declarators(),newOne,old.constant_declarators().getText(),"null","constant_declarators not mapped"));
		}
	}

	private void visitConstant_declarators(Constant_declaratorsContext old,Constant_declaratorsContext newOne) {
		int i = 0;
		List<Constant_declaratorContext> oldList = old.constant_declarator();
		List<Constant_declaratorContext> newList = newOne.constant_declarator();
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitConstant_declarator(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newOne,"constant_declarator","null","constant_declarator not mapped"));
		}
		
	}
	
	private void visitConstant_declarator(Constant_declaratorContext old,Constant_declaratorContext newOne) {
		if (newOne.identifier() != null) {
			visitIdentifier(old.identifier(),newOne.identifier());
		} else {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier not mapped"));
		}
		if (newOne.expression() != null) {
			visitExpression(old.expression(),newOne.expression());
		} else {
			differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
		}
	}

	private void visitLocal_variable_declaration(Local_variable_declarationContext old,Local_variable_declarationContext newOne) {
		/*
		 * 
(USING | REF | REF READONLY)? local_variable_type local_variable_declarator ( ','  local_variable_declarator)* | FIXED pointer_type fixed_pointer_declarators	;

		 */
		if (old.REF() != null) {
			if (newOne.REF() == null) {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","variable modifier not mapped"));
			}
		}
		if (old.READONLY() != null) {
			if (newOne.READONLY() == null) {
				differencesList.add(new Difference(old.READONLY(),newOne,old.READONLY().getText(),"null","variable modifier not mapped"));
			}
		}
		if (old.USING() != null) {
			if (newOne.USING() == null) {
				differencesList.add(new Difference(old.USING(),newOne,old.USING().getText(),"null","variable modifier not mapped"));
			}
		}
		if (old.local_variable_type() != null) {
			if (newOne.local_variable_type() != null) {
				visitLocal_variable_type(old.local_variable_type(),newOne.local_variable_type());
			} else {
				differencesList.add(new Difference(old.local_variable_type(),newOne,old.local_variable_type().getText(),"null","local_variable_type not mapped"));
			}
		}
		if (old.local_variable_declarator() != null) {
			if (newOne.local_variable_declarator() != null) {
				visitLocal_variable_declarators(old.local_variable_declarator(),newOne.local_variable_declarator());
			} else {
				differencesList.add(new Difference(old,newOne,"local_variable_declarator","null","local_variable_declarator not mapped"));
			}
		}
	}

	private void visitLocal_variable_declarators(List<Local_variable_declaratorContext> oldList,List<Local_variable_declaratorContext> newList) {
		int i = 0;
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitLocal_variable_declarator(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newList.get(0).getParent(),oldList.get(i).getText(),"null","local_variable_declarator not mapped"));
		}
		
	}

	private void visitLocal_variable_declarator(Local_variable_declaratorContext old,Local_variable_declaratorContext newOne) {
		if (newOne.identifier() != null) {
			visitIdentifier(old.identifier(),newOne.identifier());
		} else {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier not mapped"));
		}
		if (old.REF() != null) {
			if (newOne.REF() == null) {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","REF kw not mapped"));
			}
		}
		if (old.local_variable_initializer() != null) {
			if (newOne.local_variable_initializer() != null) {
				visitLocal_variable_initializer(old.local_variable_initializer(),newOne.local_variable_initializer());
			} else {
				differencesList.add(new Difference(old.local_variable_initializer(),newOne,old.local_variable_initializer().getText(),"null","local_variable_initializer not mapped"));
			}
		}
	}

	private void visitLocal_variable_initializer(Local_variable_initializerContext old,Local_variable_initializerContext newOne) {
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","param expression not mapped"));
			}
			return;
		}
		if (old.array_initializer() != null) {
			if (newOne.array_initializer() != null) {
				visitArray_initializer(old.array_initializer(),newOne.array_initializer());
			} else {
				differencesList.add(new Difference(old.array_initializer(),newOne,old.array_initializer().getText(),"null","array_initializer not mapped"));
			}
			return;
		}
		if (old.stackalloc_initializer() != null) {
			if (newOne.stackalloc_initializer() != null) {
				visitStackalloc_initializer(old.stackalloc_initializer(),newOne.stackalloc_initializer());
			} else {
				differencesList.add(new Difference(old.stackalloc_initializer(),newOne,old.stackalloc_initializer().getText(),"null","stackalloc_initializer not mapped"));
			}
			return;
		}
	}
	
	private void visitStackalloc_initializer(Stackalloc_initializerContext old,Stackalloc_initializerContext newOne) {
		if (old.STACKALLOC() != null) {
			if (newOne.STACKALLOC() == null) {
				differencesList.add(new Difference(old.STACKALLOC(),newOne,old.STACKALLOC().getText(),"null","STACKALLOC kw not mapped"));
			}
			return;
		}
		if (old.type_() != null) {
			if (newOne.type_() != null) {
				visitType(old.type_(),newOne.type_());
			} else {
				differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","type_ not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpressions(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old,newOne,"expr list","null","expressions not mapped"));
			}
		}
	}

	private void visitExpressions(List<ExpressionContext> oldList, List<ExpressionContext> newList) {
		int i = 0;
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitExpression(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newList.get(0).getParent(),oldList.get(i).getText(),"null","expression not mapped"));
		}
	}

	private void visitLocal_variable_type(Local_variable_typeContext old,Local_variable_typeContext newOne) {
		if (old.VAR() != null) {
			if (newOne.VAR() == null) {
				differencesList.add(new Difference(old.VAR(),newOne,old.VAR().getText(),"null","type not mapped"));
			}
			return;
		}
		if (newOne.type_() != null) {
			visitType(old.type_(),newOne.type_());
		}
	}

	private void visitLabeled_Statement(Labeled_StatementContext old,Labeled_StatementContext newOne) {
		if (newOne.identifier() != null) {
			visitIdentifier(old.identifier(),newOne.identifier());
		} else {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier for Labeled_StatementContext not mapped"));
		}
		if (newOne.statement() != null) {
			visitStatementContext(old.statement(),newOne.statement());
		} else {
			differencesList.add(new Difference(old.statement(),newOne,old.statement().getText(),"null","statement for Labeled_StatementContext not mapped"));
		}
		
	}

	private void visitType_parameter_constraints_clauses(Type_parameter_constraints_clausesContext old,Type_parameter_constraints_clausesContext newOne) {
		//WHERE identifier ':' type_parameter_constraints
	}

	private void visitFormal_parameter_list(Formal_parameter_listContext old,Formal_parameter_listContext newOne) {
		if (old.fixed_parameters() != null) {
			if (newOne.fixed_parameters() != null) {
				visitFixed_parameters(old.fixed_parameters(),newOne.fixed_parameters());
			} else {
				differencesList.add(new Difference(old.fixed_parameters(),newOne,old.fixed_parameters().getText(),"null","method params not mapped"));
			}
		} else {
			if (newOne.parameter_array() != null) {
				visitParameter_array(old.parameter_array(),newOne.parameter_array());
			} else {
				differencesList.add(new Difference(old.parameter_array(),newOne,old.parameter_array().getText(),"null","array method params not mapped"));
			}
		}
	}

	private void visitParameter_array(Parameter_arrayContext old, Parameter_arrayContext newOne) {
		if (old.attributes() != null) {
			if (newOne.attributes() != null) {
				visitAttributeContext(old.attributes(),newOne.attributes());
			} else {
				differencesList.add(new Difference(old.attributes(),newOne,old.attributes().getText(),"null","attributtes for array method params not mapped"));
			}
		}
		if (newOne.array_type() != null) {
			visitArray_type(old.array_type(),newOne.array_type());
		} else {
			differencesList.add(new Difference(old.attributes(),newOne,old.attributes().getText(),"null","attributtes for array method params not mapped"));
		}
		if (newOne.identifier() != null) {
			visitIdentifier(old.identifier(),newOne.identifier());
		} else {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier for array method params not mapped"));
		}
	}

	private void visitArray_type(Array_typeContext old, Array_typeContext newOne) {
		if (newOne.base_type() != null) {
			visitBase_type(old.base_type(),newOne.base_type());
		} else {
			differencesList.add(new Difference(old.base_type(),newOne,old.base_type().getText(),"null","array type not mapped"));
		}
		if (old.INTERR() != null) {
			if (newOne.INTERR() != null) {
				if (old.INTERR().size() != newOne.INTERR().size()) {
					differencesList.add(new Difference(old,newOne,"?","","simbol not mapped n times"));
				}
			} else {
				differencesList.add(new Difference(old,newOne,"?","null","simbol not mapped"));
			}
		}
		if (old.STAR() != null) {
			if (newOne.STAR() != null) {
				if (old.STAR().size() != newOne.STAR().size()) {
					differencesList.add(new Difference(old,newOne,"*","","simbol not mapped n times"));
				}
			} else {
				differencesList.add(new Difference(old,newOne,"?","null","simbol not mapped"));
			}
		}
		if (old.rank_specifier() != null) {
			if (newOne.rank_specifier() != null) {
				if (old.rank_specifier().size() != newOne.rank_specifier().size()) {
					differencesList.add(new Difference(old,newOne,String.valueOf(old.rank_specifier().size()),String.valueOf(newOne.rank_specifier().size()),"array dimensions not mapped right"));
				}
			} else {
				differencesList.add(new Difference(old,newOne,String.valueOf(old.rank_specifier().size()),"null","array dimensions not mapped"));
			}
		}
		
	}

	private void visitBase_type(Base_typeContext old, Base_typeContext newOne) {
		//  simple_type
		//  | class_type  // represents types: enum, class, interface, delegate, type_parameter
	//	    | VOID '*'
	//	    | tuple_type
		//TODO do it right
		if (!old.getText().equals(newOne.getText())) {
			differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"type not mapped right"));
		}
	}

	private void visitFixed_parameters(Fixed_parametersContext old,Fixed_parametersContext newOne) {
		List<Fixed_parameterContext> oldList = old.fixed_parameter();
		List<Fixed_parameterContext> newList = newOne.fixed_parameter();
		int i = 0;
		try {
			for (i = 0; i < oldList.size(); i++) {
				visitFixed_parameterContext(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newOne,oldList.get(i).getText(),"null","method param not mapped"));
		}
	}
	
	private void visitFixed_parameterContext(Fixed_parameterContext old,Fixed_parameterContext newOne) {
		if (old.ARGLIST() != null) {
			if (newOne.ARGLIST() == null) {
				differencesList.add(new Difference(newOne.ARGLIST(),newOne,old.ARGLIST().getText(),"null","ARGLIST not mapped"));
			}
			return;
		}
		if (old.attributes() != null) {
			if (newOne.attributes() != null) {
				visitAttributeContext(old.attributes(),newOne.attributes());
			} else {
				differencesList.add(new Difference(old.attributes(),newOne,old.attributes().getText(),"null","attributes not mapped"));
			}
		}
		if (old.parameter_modifier() != null) {
			if (newOne.parameter_modifier() != null) {
				visitParameter_modifier(old.parameter_modifier(),newOne.parameter_modifier());
			} else {
				differencesList.add(new Difference(old.parameter_modifier(),newOne,old.parameter_modifier().getText(),"null","parameter modifier not mapped"));
			}
		}
		if (old.arg_declaration() != null) {
			if (newOne.arg_declaration() != null) {
				visitArg_declaration(old.arg_declaration(),newOne.arg_declaration());
			} else {
				differencesList.add(new Difference(old.parameter_modifier(),newOne,old.parameter_modifier().getText(),"null","parameter modifier not mapped"));
			}
		}
	}

	private void visitArg_declaration(Arg_declarationContext old, Arg_declarationContext newOne) {
		if (newOne.type_() != null) {
			if (!old.type_().getText().equals(newOne.type_().getText())) {
				differencesList.add(new Difference(old.type_(),newOne.type_(),old.type_().getText(),newOne.type_().getText(),"type name not mapped right"));
			}
		} else {
			differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","type name not mapped"));
		}
		if (newOne.identifier() != null) {
			visitIdentifier(old.identifier(),newOne.identifier());
		} else {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"nul","identifier not mapped"));
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","param expression not mapped"));
			}
		}
	}

	private void visitParameter_modifier(Parameter_modifierContext old,Parameter_modifierContext newOne) {
		if (!old.getText().equals(newOne.getText())) {
			differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"parameter modifier name not mapped"));
		}
	}

	private void visirFieldDeclaration(Field_declarationContext old, Field_declarationContext newOne) {
		List<Variable_declaratorContext> oldDcls = old.variable_declarators().variable_declarator();
		int i = 0;
		try {
			List<Variable_declaratorContext> newDcls = newOne.variable_declarators().variable_declarator();
			for (i = 0; i <  oldDcls.size(); i ++) {
				visitVariable_declaratorContext(oldDcls.get(i),newDcls.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldDcls.get(i),newOne,oldDcls.get(i).getText(),"null","inline member dcl not mapped"));
		}
	}

	private void visitVariable_declaratorContext(Variable_declaratorContext old,Variable_declaratorContext newOne) {
		if (newOne.identifier() != null) {
			visitIdentifier(old.identifier(),newOne.identifier());
		} else {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","variable name not mapped"));
		}
		if (old.variable_initializer() != null) {
			if (newOne.variable_initializer() != null) {
				visitVariable_InitializarContext(old.variable_initializer(),newOne.variable_initializer());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","variable name not mapped"));
			}
		}
	}

	private void visitVariable_InitializarContext(Variable_initializerContext old,Variable_initializerContext newOne) {
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","variable initial value not mapped"));
			}
			
		} else if (old.array_initializer() != null) {
			if (newOne.array_initializer() != null) {
				visitArray_initializer(old.array_initializer(),newOne.array_initializer());
			} else {
				differencesList.add(new Difference(old.array_initializer(),newOne,old.array_initializer().getText(),"null","array_initializer not mapped"));
			}
		}
	}

	private void visitArray_initializer(Array_initializerContext old, Array_initializerContext newOne) {
		List<Variable_initializerContext> oldDcls = old.variable_initializer();
		int i = 0;
		try {
			List<Variable_initializerContext> newDcls = newOne.variable_initializer();
			for (i = 0; i <  oldDcls.size(); i ++) {
				visitVariable_InitializarContext(oldDcls.get(i),newDcls.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldDcls.get(i),newOne,oldDcls.get(i).getText(),"null","inline member dcl not mapped"));
		}
	}

	private void visitIdentifier(IdentifierContext old, IdentifierContext newOne) {
		if (!old.getText().equals(newOne.getText())) {
			differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"variable name not mapped right"));
		}
	}
	
	public void visitDestructor_definition(Destructor_definitionContext old, Destructor_definitionContext newOne) {
		
	}

	public void visitModifiers(All_member_modifiersContext old, All_member_modifiersContext newOne) {
		List<All_member_modifierContext> newModifierList = newOne.all_member_modifier();
		List<All_member_modifierContext> oldModifierList = old.all_member_modifier();
		try {
			oldModifierList = old.all_member_modifier();
			for (int i = 0; i < oldModifierList.size(); i++) {
				try {
					All_member_modifierContext newModifier = newModifierList.get(i);
					All_member_modifierContext oldModifier = oldModifierList.get(i);
					if (!newModifier.getText().equals(oldModifier.getText())) {
						differencesList.add(new Difference(oldModifier,newModifier,oldModifier.getText(),newModifier.getText(),"Modifier"));
					}
				} catch (IndexOutOfBoundsException e){
					differencesList.add(new Difference(old,newOne,old.getText(),"null","Modifier not mapped"));
				}
			}
		} catch (NullPointerException e) {
			differencesList.add(new Difference(old,newOne,old.getText(),"null","Modifiers not mapped"));
		}
	}
	
	private void visitNameSpaceDeclaration(Namespace_declarationContext old,Namespace_declarationContext newOne) {
		visitQualified_IdentifierCotnext(old.qualified_identifier(),newOne.qualified_identifier());
		visitNameSpace_bodyContext(old.namespace_body(),newOne.namespace_body());//TODO check name
	}
	
	private void visitQualified_IdentifierCotnext(Qualified_identifierContext old,Qualified_identifierContext newOne) {
		List<IdentifierContext> oldList = old.identifier();
		List<IdentifierContext> newList;
		int i = 0;
		try{
			newList = newOne.identifier();
			for (i = 0; i < oldList.size(); i++) {
				visitIdentifier(oldList.get(i),newList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldList.get(i),newOne,"namespace declaration id","null","namespace declaration id not mapped"));
		} catch (NullPointerException f) {
			differencesList.add(new Difference(old,newOne,"namespace declarations id","null","namespace declarations identifiers not mapped"));
		}
	}

	private void visitNameSpace_bodyContext(Namespace_bodyContext old,Namespace_bodyContext newOne) {
		List<Namespace_member_declarationContext> oldMembers = old.namespace_member_declarations().namespace_member_declaration();
		List<Namespace_member_declarationContext> newMembers;
		int i = 0;
		try{
			newMembers = newOne.namespace_member_declarations().namespace_member_declaration();
			for (i = 0; i < oldMembers.size(); i++) {
				visitNamespace_member_declaration(oldMembers.get(i),newMembers.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldMembers.get(i),newOne,"namespace declaration","null","namespace declaration not mapped"));
		} catch (NullPointerException f) {
			differencesList.add(new Difference(old.namespace_member_declarations(),newOne,"namespace declarations","null","namespace declarations not mapped"));
		}
		
	}

	public void visitAttributeContext(AttributesContext old, AttributesContext newOne) {
		List<Attribute_sectionContext> oldAttList = old.attribute_section();
		List<Attribute_sectionContext> newAttList = newOne.attribute_section();
		int i = 0;
		try {
			for (i = 0; i < oldAttList.size(); i++) {
				visitAttribute_sectionContext(oldAttList.get(i),newAttList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldAttList.get(i),newOne,oldAttList.get(i).getText(),"null","attribute not mapped"));
		}
	}
	
	public void visitAttribute_sectionContext(Attribute_sectionContext old, Attribute_sectionContext newOne) {
		if (old.attribute_target() != null) {
			if (newOne.attribute_target() != null) {
				String oldName = old.attribute_target().getText();
				String newName = newOne.attribute_target().getText();
				if (!oldName.equals(newName)) {
					differencesList.add(new Difference( old.attribute_target(),newOne.attribute_target(),oldName,newName,"attribute name not mapped right"));
				}
			}else {
				differencesList.add(new Difference(old,newOne,old.attribute_target().getText(),"null","attribute name not mapped"));
			}
			
		} 
		if (old.attribute_list() != null) {
			if (!old.attribute_list().isEmpty()) {
				List<AttributeContext> oldArgs = old.attribute_list().attribute();
				try {
					List<AttributeContext> newArgs = old.attribute_list().attribute();
					int i = 0;
					try {
						for (i = 0; i < oldArgs.size(); i++) {
							visitAttribute(oldArgs.get(i),newArgs.get(i));
						}
					} catch (IndexOutOfBoundsException e) {
						differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","attribute arg not mapped"));
					}					
				} catch (NullPointerException e) {
					differencesList.add(new Difference(old,newOne,"argument list","null","attribute args not mapped"));
				}
			}
		}
	}
	
	public void visitGlobal_attribute_sectionContext(Global_attribute_sectionContext old, Global_attribute_sectionContext newOne) {
		String oldTarget = old.global_attribute_target().getText();
		String newTarget = newOne.global_attribute_target().getText();
		if (!oldTarget.equals(newTarget)) {
			differencesList.add(new Difference(old,newOne,oldTarget,newTarget,"target name doesn't matches"));
		}
		try {
			visitAttribute_listContext(old.attribute_list(),newOne.attribute_list());
		} catch (NullPointerException e) {
			differencesList.add(new Difference(old.attribute_list(),newOne,"attribute list","null","attributes not mapped"));
		}

	}
	
	public void visitAttribute_listContext(Attribute_listContext old, Attribute_listContext newOne) {
		List<AttributeContext> oldAttList = old.attribute();
		List<AttributeContext> newAttList = newOne.attribute();
		int i = 0;
		try {
			for (i = 0; i < oldAttList.size(); i++) {
				visitAttribute(oldAttList.get(i),newAttList.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldAttList.get(i),newOne,oldAttList.get(i).getText(),"null","attribute not mapped"));
		}
	}
	
	public void visitAttribute(AttributeContext old, AttributeContext newOne) {
		if (newOne.namespace_or_type_name() != null) {
			String oldName = old.namespace_or_type_name().getText();
			String newName = newOne.namespace_or_type_name().getText();
			if (!oldName.equals(newName)) {
				differencesList.add(new Difference( old.namespace_or_type_name(),newOne.namespace_or_type_name(),oldName,newName,"attribute name not mapped right"));
			}
		} else {
			differencesList.add(new Difference(old,newOne,old.namespace_or_type_name().getText(),"null","attribute name not mapped"));
		}
		if (old.attribute_argument() != null) {
			if (!old.attribute_argument().isEmpty()) {
				List<Attribute_argumentContext> oldArgs = old.attribute_argument();
				try {
					List<Attribute_argumentContext> newArgs = newOne.attribute_argument();
					int i = 0;
					try {
						for (i = 0; i < oldArgs.size(); i++) {
							visitAttribute_argumentContext(oldArgs.get(i),newArgs.get(i));
						}
					} catch (IndexOutOfBoundsException e) {
						differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","attribute arg not mapped"));
					}					
				} catch (NullPointerException e) {
					differencesList.add(new Difference(old,newOne,"argument list","null","attribute args not mapped"));
				}
			}
		}
	}
	
	public void visitAttribute_argumentContext(Attribute_argumentContext old, Attribute_argumentContext newOne) {
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				String oldId = old.identifier().getText();
				String newId = newOne.identifier().getText();
				if (!oldId.equals(newId)) {
					differencesList.add(new Difference(old.identifier(),newOne.identifier(),oldId,newId,"attributeArd id not mapped right"));
				}
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","attributeArg id not mapped"));
			}
		}
		try {
			visitExpression(old.expression(),newOne.expression());
		} catch (NullPointerException e) {
			differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","attributeArg expr not mapped"));
		}
	}
	
	public void visitExpression(ExpressionContext old, ExpressionContext newOne) {//TODO here
		if (old.assignment() != null) {
			if (newOne.assignment() != null) {
				visitAssignment(old.assignment(),newOne.assignment());
			} else {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","expr not mapped"));
			}
		} else if (old.non_assignment_expression() != null) {
			if (newOne.non_assignment_expression() != null) {
				visitNon_assignment_expression(old.non_assignment_expression(),newOne.non_assignment_expression());
			} else {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","expr not mapped"));
			}
			
		} else if (old.REF() != null) {
			if (newOne.REF() != null) {
				
			} else {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","ref kw not mapped"));
			}
		}
	}
	
	private void visitAssignment(AssignmentContext old, AssignmentContext newOne) {
		if (old.unary_expression() != null) {
			if (newOne.unary_expression() != null) {
				visitUnary_expression(old.unary_expression(),newOne.unary_expression());
			} else {
				differencesList.add(new Difference(old.unary_expression(),newOne,old.unary_expression().getText(),"null","Expression not mapped"));
			}
		}
		if (old.assignment_operator() != null) {
			if (newOne.assignment_operator() != null) {
				if (!old.assignment_operator().getText().equals(newOne.assignment_operator().getText())) {
					differencesList.add(new Difference(old.assignment_operator(),newOne,old.assignment_operator().getText(),"null","Expression oper not mapped right"));
				}
			} else {
				differencesList.add(new Difference(old.assignment_operator(),newOne,old.assignment_operator().getText(),"null","Expression oper not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression in assign not mapped"));
			}
		}
		if (old.throwable_expression() != null) {
			if (newOne.throwable_expression() != null) {
				visitThrowable_expression(old.throwable_expression(),newOne.throwable_expression());
			} else {
				differencesList.add(new Difference(old.throwable_expression(),newOne,"throws exception","null","throws exception not mapped"));
			}
		}
	}

	private void visitNon_assignment_expression(Non_assignment_expressionContext old,Non_assignment_expressionContext newOne) {
		if (old.query_expression() != null) {
			
		} else if (old.conditional_expression() != null) {
			if (newOne.conditional_expression() != null) {
				visitConditiona_expression(old.conditional_expression(),newOne.conditional_expression());
			} else {
				differencesList.add(new Difference(old.conditional_expression(),newOne,old.conditional_expression().getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitConditiona_expression(Conditional_expressionContext old,Conditional_expressionContext newOne) {
		if (old.null_coalescing_expression() != null) {
			if (newOne.null_coalescing_expression() != null) {
				visitNull_coalescing_expression(old.null_coalescing_expression(),newOne.null_coalescing_expression());
				if (old.throwable_expression() != null){
					if (!old.throwable_expression().isEmpty()){
						if (newOne.throwable_expression() != null) {
							int i = 0;
							if (!old.throwable_expression().isEmpty()) {
								try {
									
									for (i = 0; i < old.throwable_expression().size(); i ++) {
										visitThrowable_expression(old.throwable_expression(i),newOne.throwable_expression(i));
									}
								} catch(IndexOutOfBoundsException e) {
									differencesList.add(new Difference(old.throwable_expression(i),newOne,"throws","null","Expression not mapped"));
								}
							}else {
								differencesList.add(new Difference(old,newOne,"throws","null","Expression not mapped"));
							}
						}else {
							differencesList.add(new Difference(old,newOne,"throws","null","Expression not mapped"));
						}
					}
				}
			} else{
				differencesList.add(new Difference(old.null_coalescing_expression(),newOne,old.null_coalescing_expression().getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitNull_coalescing_expression(Null_coalescing_expressionContext old, Null_coalescing_expressionContext newOne) {
		//conditional_or_expression ('??' (null_coalescing_expression | throw_expression))?
		if (old.conditional_or_expression() != null) {
			if (newOne.conditional_or_expression() != null) {
				visitConditional_or_expression(old.conditional_or_expression(), newOne.conditional_or_expression());
			} else {
				differencesList.add(new Difference(old.conditional_or_expression(),newOne,old.conditional_or_expression().getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitConditional_or_expression(Conditional_or_expressionContext old, Conditional_or_expressionContext newOne) {
		//conditional_and_expression (OP_OR conditional_and_expression)*
		if (old.conditional_and_expression(0) != null) {
			if (newOne.conditional_and_expression(0) != null) {
				if (newOne.conditional_and_expression(0) != null) {
					visitConditional_and_expression(old.conditional_and_expression(0),newOne.conditional_and_expression(0));
				} else {
					differencesList.add(new Difference(old.conditional_and_expression(0),newOne,old.conditional_and_expression(0).getText(),"null","Expression not mapped"));
				}
			} else {
				differencesList.add(new Difference(old.conditional_and_expression(0),newOne,old.conditional_and_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitConditional_and_expression(Conditional_and_expressionContext old,Conditional_and_expressionContext newOne) {
		//inclusive_or_expression (OP_AND inclusive_or_expression)*
		if (old.inclusive_or_expression(0) != null) {
			if (newOne.inclusive_or_expression(0) != null) {
				visitInclusive_or_expression(old.inclusive_or_expression(0),newOne.inclusive_or_expression(0));
			} else {
				differencesList.add(new Difference(old.inclusive_or_expression(0),newOne,old.inclusive_or_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitInclusive_or_expression(Inclusive_or_expressionContext old,Inclusive_or_expressionContext newOne) {
		// exclusive_or_expression ('|' exclusive_or_expression)*
		if (old.exclusive_or_expression(0) != null) {
			if (newOne.exclusive_or_expression(0) != null) {
				visitExclusive_or_expression(old.exclusive_or_expression(0),newOne.exclusive_or_expression(0));
			} else {
				differencesList.add(new Difference(old.exclusive_or_expression(0),newOne,old.exclusive_or_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitExclusive_or_expression(Exclusive_or_expressionContext old, Exclusive_or_expressionContext newOne) {
		// exclusive_or_expression
		if (old.and_expression(0) != null) {
			if (newOne.and_expression(0) != null) {
				visitAnd_expression(old.and_expression(0),newOne.and_expression(0));
			} else {
				differencesList.add(new Difference(old.and_expression(0),newOne,old.and_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitAnd_expression(And_expressionContext old, And_expressionContext newOne) {
		// equality_expression ('&' equality_expression)*
		if (old.equality_expression(0) != null) {
			if (newOne.equality_expression(0) != null) {
				visitEquality_expression(old.equality_expression(0),newOne.equality_expression(0));
			} else {
				differencesList.add(new Difference(old.equality_expression(0),newOne,old.equality_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitEquality_expression(Equality_expressionContext old,Equality_expressionContext newOne) {
		// relational_expression ((OP_EQ | OP_NE)  relational_expression)*
		if (old.relational_expression(0) != null) {
			if (newOne.relational_expression(0) != null) {
				visitRelational_expression(old.relational_expression(0),newOne.relational_expression(0));
			} else {
				differencesList.add(new Difference(old.relational_expression(0),newOne,old.relational_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitRelational_expression(Relational_expressionContext old,Relational_expressionContext newOne) {
		// shift_expression (('<' | '>' | '<=' | '>=') shift_expression | IS isType | AS type_)*
		if (old.shift_expression(0) != null) {
			if (newOne.shift_expression(0) != null) {
				visitShift_expression(old.shift_expression(0),newOne.shift_expression(0));
			} else {
				differencesList.add(new Difference(old.shift_expression(0),newOne,old.shift_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitShift_expression(Shift_expressionContext old,Shift_expressionContext newOne) {
		// additive_expression (('<<' | right_shift)  additive_expression)*
		if (old.additive_expression(0) != null) {
			if (newOne.additive_expression(0) != null) {
				visitAdditive_expression(old.additive_expression(0),newOne.additive_expression(0));
			} else {
				differencesList.add(new Difference(old.additive_expression(0),newOne,old.additive_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitAdditive_expression(Additive_expressionContext old,Additive_expressionContext newOne) {
		// multiplicative_expression (('+' | '-')  multiplicative_expression)*
		if (old.multiplicative_expression(0) != null) {
			if (newOne.multiplicative_expression(0) != null) {
				visitMultiplicative_expression(old.multiplicative_expression(0),newOne.multiplicative_expression(0));
			} else {
				differencesList.add(new Difference(old.multiplicative_expression(0),newOne,old.multiplicative_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitMultiplicative_expression(Multiplicative_expressionContext old,Multiplicative_expressionContext newOne) {
		// switch_expression (('*' | '/' | '%')  switch_expression)*
		if (old.switch_expression(0) != null) {
			if (newOne.switch_expression(0) != null) {
				visitSwitch_expression(old.switch_expression(0),newOne.switch_expression(0));
			} else {
				differencesList.add(new Difference(old.switch_expression(0),newOne,old.switch_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitSwitch_expression(Switch_expressionContext old,Switch_expressionContext newOne) {
		//  range_expression ('switch' '{' (switch_expression_arms ','?)? '}')?
		if (old.range_expression() != null) {
			if (newOne.range_expression() != null) {
				visitRange_expression(old.range_expression(),newOne.range_expression());
			} else {
				differencesList.add(new Difference(old.range_expression(),newOne,old.range_expression().getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitRange_expression(Range_expressionContext old,Range_expressionContext newOne) {
		// unary_expression | unary_expression? OP_RANGE unary_expression?
		if (old.unary_expression(0) != null) {
			if (newOne.unary_expression(0) != null) {
				visitUnary_expression(old.unary_expression(0),newOne.unary_expression(0));
			} else {
				differencesList.add(new Difference(old.unary_expression(0),newOne,old.unary_expression(0).getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitUnary_expression(Unary_expressionContext old,Unary_expressionContext newOne) {
		/*
		primary_expression
		| '+' unary_expression
		| '-' unary_expression
		| BANG unary_expression
		| '~' unary_expression
		| '++' unary_expression
		| '--' unary_expression
		| OPEN_PARENS type_ CLOSE_PARENS unary_expression
		| AWAIT unary_expression // C# 5
		| '&' unary_expression
		| '*' unary_expression
		| '^' unary_expression // C# 8 ranges
		*/
		if (old.primary_expression() != null) {
			if (newOne.primary_expression() != null) {
				visitPrimary_expression(old.primary_expression(),newOne.primary_expression());
			} else {
				differencesList.add(new Difference(old.primary_expression(),newOne,old.primary_expression().getText(),"null","Expression not mapped"));
			}
		}
	}

	private void visitPrimary_expression(Primary_expressionContext old,Primary_expressionContext newOne) {
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				List<IdentifierContext> oldArgs = old.identifier();
				List<IdentifierContext> newArgs = newOne.identifier();
				int i = 0;
				try {
					for (i = 0; i < oldArgs.size(); i++) {
						visitIdentifier(oldArgs.get(i),newArgs.get(i));
					}
				} catch (IndexOutOfBoundsException e) {
					differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","idenfier not mapped"));
				}	
			} else {
				differencesList.add(new Difference(old,newOne,"identifier list","null","identifiers for not mapped"));
			}
		}
		if (old.pe != null) {
			if (newOne.pe != null) {
				visitPrimary_expression_start(old.pe,newOne.pe);
			} else {
				differencesList.add(new Difference(old.pe,newOne,old.pe.getText(),"null","Expression not mapped"));
			}
		}
		if (old.bracket_expression() != null){
			if (newOne.bracket_expression() != null) {
				List<Bracket_expressionContext> oldArgs = old.bracket_expression();
				List<Bracket_expressionContext> newArgs = newOne.bracket_expression();
				int i = 0;
				try {
					for (i = 0; i < oldArgs.size(); i++) {
						visitBracket_expressionContext(oldArgs.get(i),newArgs.get(i));
					}
				} catch (IndexOutOfBoundsException e) {
					differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","Bracket Expression not mapped"));
				}					
			} else {
				differencesList.add(new Difference(old.pe,newOne,old.pe.getText(),"null","Bracket Expressions not mapped"));
			}
		}
		if (old.member_access() != null) {
			if (newOne.member_access() != null) {
				List<Member_accessContext> oldArgs = old.member_access();
				List<Member_accessContext> newArgs = newOne.member_access();
				int i = 0;
				try {
					for (i = 0; i < oldArgs.size(); i++) {
						visitMember_accessContext(oldArgs.get(i),newArgs.get(i));
					}
				} catch (IndexOutOfBoundsException e) {
					differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","Member_accessContext Expression not mapped"));
				}					
			} else {
				differencesList.add(new Difference(old,newOne,"Member_accessContext","null","Member_accessContext Expressions not mapped"));
			}
		}
		if (old.method_invocation() != null) {
			if (newOne.method_invocation() != null) {
				List<Method_invocationContext> oldArgs = old.method_invocation();
				List<Method_invocationContext> newArgs = newOne.method_invocation();
				int i = 0;
				try {
					for (i = 0; i < oldArgs.size(); i++) {
						visitMethod_invocationContext(oldArgs.get(i),newArgs.get(i));
					}
				} catch (IndexOutOfBoundsException e) {
					differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","method_invocation Expression not mapped"));
				}					
			} else {
				differencesList.add(new Difference(old.pe,newOne,"Method_invocationContext","null","method_invocation Expressions not mapped"));
			}
		}
		
	}
	
	private void visitArgumentContext(ArgumentContext old, ArgumentContext newOne) {
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier for index not mapped"));
			}
		}
		if (old.REF() != null) {
			if (newOne.REF() == null) {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","arg modifier not mapped"));
			}
		}
		if (old.OUT() != null) {
			if (newOne.OUT() == null) {
				differencesList.add(new Difference(old.OUT(),newOne,old.OUT().getText(),"null","arg modifier not mapped"));
			}
		}
		if (old.IN() != null) {
			if (newOne.IN() == null) {
				differencesList.add(new Difference(old.IN(),newOne,old.IN().getText(),"null","arg modifier not mapped"));
			}
		}
		if (old.VAR() != null) {
			if (newOne.VAR() == null) {
				differencesList.add(new Difference(old.VAR(),newOne,old.VAR().getText(),"null","VAR type not mapped"));
			}
		}
		if (old.type_() != null) {
			if (newOne.type_() == null) {
				differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","type not mapped"));
			} else {
				if (!old.getText().equals(newOne.getText())) {
					differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),newOne.type_().getText(),"type not mapped right"));
				}
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old,newOne,"expr","null","arg expression not mapped"));
			}
		}
		
	}

	private void visitMethod_invocationContext(Method_invocationContext old,Method_invocationContext newOne) {
		if (old.argument_list() != null) {
			if (newOne.argument_list() != null) {
				List<ArgumentContext> oldArgs = old.argument_list().argument();
				List<ArgumentContext> newArgs = newOne.argument_list().argument();
				int i = 0;
				try {
					for (i = 0; i < oldArgs.size(); i++) {
						visitArgumentContext(oldArgs.get(i),newArgs.get(i));
					}
				} catch (IndexOutOfBoundsException e) {
					differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","method_invocation argument not mapped"));
				}					
			} else {
				differencesList.add(new Difference(old,newOne,"Method_invocationContext","null","method_invocation argument_list not mapped"));
			}
		}
		
	}

	private void visitMember_accessContext(Member_accessContext old,Member_accessContext newOne) {
		if (old.DOT() != null) {
			if (newOne.DOT() == null) {
				differencesList.add(new Difference(old.DOT(),newOne,old.DOT().getText(),"null","DOT not mapped"));
			}
		}
		if (old.INTERR() != null){
			if (newOne.INTERR() == null) {
				differencesList.add(new Difference(old.INTERR(),newOne,old.INTERR().getText(),"null","INTERR type not mapped"));
			}
		}
		if (old.type_argument_list() != null) {
			if (newOne.type_argument_list() != null) {
				visitType_argument_list(old.type_argument_list(),newOne.type_argument_list());
			} else {
				differencesList.add(new Difference(old,newOne,"expr","null","type_argument_list not mapped"));
			}
		}
	}

	private void visitType_argument_list(Type_argument_listContext old,Type_argument_listContext newOne) {
		List<Type_Context> oldArgs = old.type_();
		List<Type_Context> newArgs = newOne.type_();
		int i = 0;
		try {
			for (i = 0; i < oldArgs.size(); i++) {
				Type_Context oldType = oldArgs.get(i);
				Type_Context newType = newArgs.get(i);
				if (!oldType.getText().equals(newType.getText())) {
						differencesList.add(new Difference(oldType,newType,oldType.getText(),newType.getText(),"type not mapped right"));
				}
			}
		} catch (IndexOutOfBoundsException e) {
				differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","method_invocation argument not mapped"));
		}					
	}

	private void visitBracket_expressionContext(Bracket_expressionContext old,Bracket_expressionContext newOne) {
		if (old.INTERR() != null) {
			if (newOne.INTERR() == null) {
				differencesList.add(new Difference(old.INTERR(),newOne,old.INTERR().getText(),"null","Bracket Expression '?' not mapped"));
			}
		}
		if (old.indexer_argument() != null){
			if (newOne.indexer_argument() != null) {
				List<Indexer_argumentContext> oldArgs = old.indexer_argument();
				List<Indexer_argumentContext> newArgs = newOne.indexer_argument();
				int i = 0;
				try {
					for (i = 0; i < oldArgs.size(); i++) {
						visitIndexer_argumentContext(oldArgs.get(i),newArgs.get(i));
					}
				} catch (IndexOutOfBoundsException e) {
					differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","Indexer_argumentContext not mapped"));
				}					
			} else {
				differencesList.add(new Difference(old,newOne,"indexList","null","Indexer_argumentContexts not mapped"));
			}
		}

	}

	private void visitIndexer_argumentContext(Indexer_argumentContext old,Indexer_argumentContext newOne) {
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier for index not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old,newOne,"expr list","null","index expression not mapped"));
			}
		}
	}

	private void visitPrimary_expression_start(Primary_expression_startContext old,Primary_expression_startContext newOne) {
		/*TODO
	| OPEN_PARENS argument ( ',' argument )+ CLOSE_PARENS           #tupleExpression
	// C# 6: https://msdn.microsoft.com/en-us/library/dn986596.aspx	
		 */
		 if (old instanceof LiteralExpressionContext) {
			 visitLiteralExpressionContext(old,newOne);
		 } else if (old instanceof SimpleNameExpressionContext) {
			 visitSimpleNameExpressionContext(old,newOne);
		 } else if (old instanceof ParenthesisExpressionsContext) {
			 if (newOne  instanceof ParenthesisExpressionsContext) {
				 visitParenthesisExpressionsContext((ParenthesisExpressionsContext)old, (ParenthesisExpressionsContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"ParenthesisExpressionsContext not mapped right"));
				}

		 } else if (old instanceof MemberAccessExpressionContext) {
			 if (newOne  instanceof MemberAccessExpressionContext) {
				 visitMemberAccessExpressionContext((MemberAccessExpressionContext)old, (MemberAccessExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"MemberAccessExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof LiteralAccessExpressionContext) {
			 if (newOne  instanceof LiteralAccessExpressionContext) {
				 visitLiteralAccessExpressionContext((LiteralAccessExpressionContext)old, (LiteralAccessExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"LiteralAccessExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof ThisReferenceExpressionContext) {
			 if (newOne  instanceof ThisReferenceExpressionContext) {
				 visitThisReferenceExpressionContext((ThisReferenceExpressionContext)old, (ThisReferenceExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"ThisReferenceExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof BaseAccessExpressionContext) {
			 if (newOne  instanceof BaseAccessExpressionContext) {
				 visitBaseAccessExpressionContext((BaseAccessExpressionContext)old, (BaseAccessExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"BaseAccessExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof ObjectCreationExpressionContext) {
			 if (newOne  instanceof ObjectCreationExpressionContext) {
				 visitObjectCreationExpressionContext((ObjectCreationExpressionContext)old, (ObjectCreationExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"ObjectCreationExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof TypeofExpressionContext) {
			 if (newOne  instanceof TypeofExpressionContext) {
				 visitTypeofExpressionContext((TypeofExpressionContext)old, (TypeofExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"TypeofExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof CheckedExpressionContext) {
			 if (newOne  instanceof CheckedExpressionContext) {
				 visitCheckedExpressionContext((CheckedExpressionContext)old, (CheckedExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"CheckedExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof UncheckedExpressionContext) {
			 if (newOne  instanceof UncheckedExpressionContext) {
				 visitUncheckedExpressionContext((UncheckedExpressionContext)old, (UncheckedExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"UncheckedExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof DefaultValueExpressionContext) {
			 if (newOne  instanceof DefaultValueExpressionContext) {
				 visitDefaultValueExpressionContext((DefaultValueExpressionContext)old, (DefaultValueExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"DefaultValueExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof AnonymousMethodExpressionContext) {
			 if (newOne  instanceof AnonymousMethodExpressionContext) {
				 visitAnonymousMethodExpressionContext((AnonymousMethodExpressionContext)old, (AnonymousMethodExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"AnonymousMethodExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof SizeofExpressionContext) {
			 if (newOne  instanceof SizeofExpressionContext) {
				 visitSizeofExpressionContext((SizeofExpressionContext)old, (SizeofExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"SizeofExpressionContext not mapped right"));
				}
			 
		 } else if (old instanceof NameofExpressionContext) {
			 if (newOne  instanceof NameofExpressionContext) {
				 visitNameofExpressionContext((NameofExpressionContext)old, (NameofExpressionContext)newOne);
			 } else {
					differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"NameofExpressionContext not mapped right"));
				}
		 }
	}

	private void visitMemberAccessExpressionContext(MemberAccessExpressionContext old,
			MemberAccessExpressionContext newOne) {
		if (old.predefined_type() != null) {
			if (newOne.predefined_type() == null) {
				differencesList.add(new Difference(old.predefined_type(),newOne,old.predefined_type().getText(),"null","type not mapped"));
			} else {
				if (!old.getText().equals(newOne.getText())) {
					differencesList.add(new Difference(old.predefined_type(),newOne,old.predefined_type().getText(),newOne.predefined_type().getText(),"type not mapped right"));
				}
			}
		}
		
		if (old.qualified_alias_member() != null) {
			if (newOne.qualified_alias_member() != null) {
				visitQualified_alias_member(old.qualified_alias_member(),newOne.qualified_alias_member());
			} else {
				differencesList.add(new Difference(old.qualified_alias_member(),newOne,old.qualified_alias_member().getText(),newOne.qualified_alias_member().getText(),"qualified_alias_member not mapped right"));
			}
		}
	}
	private void visitQualified_alias_member(Qualified_alias_memberContext qualified_alias_member,
			Qualified_alias_memberContext qualified_alias_member2) {
		// TODO Auto-generated method stub
		
	}

	/*
	qualified_alias_member
	: identifier '::' identifier type_argument_list?
	*/
	private void visitLiteralAccessExpressionContext(LiteralAccessExpressionContext old,
			LiteralAccessExpressionContext newOne) {
		if (old.LITERAL_ACCESS() != null) {
			if (newOne.LITERAL_ACCESS() == null) {
				differencesList.add(new Difference(old.LITERAL_ACCESS(),newOne,old.LITERAL_ACCESS().getText(),"null","LITERAL_ACCESS not mapped"));
			}
		}
	}

	private void visitThisReferenceExpressionContext(ThisReferenceExpressionContext old,
			ThisReferenceExpressionContext newOne) {
		if (old.THIS() != null) {
			if (newOne.THIS() == null) {
				differencesList.add(new Difference(old.THIS(),newOne,old.THIS().getText(),"null","THIS not mapped"));
			}
		}
	}

	private void visitBaseAccessExpressionContext(BaseAccessExpressionContext old, BaseAccessExpressionContext newOne) {
		if (old.BASE() != null) {
			if (newOne.BASE() == null) {
				differencesList.add(new Difference(old.BASE(),newOne,old.BASE().getText(),"null","BASE not mapped"));
			}
		}
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier not mapped"));
			}
		}
		if (old.type_argument_list() != null) {
			if (newOne.type_argument_list() != null) {
				visitType_argument_list(old.type_argument_list(),newOne.type_argument_list());
			} else {
				differencesList.add(new Difference(old,newOne,"expr","null","type_argument_list not mapped"));
			}
		}
		if (old.expression_list() != null) {
			if (newOne.expression_list() != null) {
				visitExpressions(old.expression_list().expression(),newOne.expression_list().expression());
			} else {
				differencesList.add(new Difference(old,newOne,"expr","null","expression_list not mapped"));
			}
		}
	}

	private void visitObjectCreationExpressionContext(ObjectCreationExpressionContext old,
			ObjectCreationExpressionContext newOne) {
		/*TODO
		 * NEW (type_ (object_creation_expression
	             | object_or_collection_initializer
	             | '['  ']' rank_specifier* array_initializer?
	             | rank_specifier+ array_initializer)
	      | anonymous_object_initializer
		 */
		if (old.expression_list() != null) {
			if (newOne.expression_list() != null) {
				visitExpressions(old.expression_list().expression(),newOne.expression_list().expression());
			} else {
				differencesList.add(new Difference(old,newOne,"expr","null","expression_list not mapped"));
			}
		}
		if (old.rank_specifier() != null) {
			if (newOne.rank_specifier() != null) {
				if (old.rank_specifier().size() != newOne.rank_specifier().size()) {
					differencesList.add(new Difference(old,newOne,String.valueOf(old.rank_specifier().size()),String.valueOf(newOne.rank_specifier().size()),"array dimensions not mapped right"));
				}
			} else {
				differencesList.add(new Difference(old,newOne,String.valueOf(old.rank_specifier().size()),"null","array dimensions not mapped"));
			}
		}
		if (old.array_initializer() != null) {
			if (newOne.array_initializer() != null) {
				visitArray_initializer(old.array_initializer(),newOne.array_initializer());
			} else {
				differencesList.add(new Difference(old.array_initializer(),newOne,old.array_initializer().getText(),"null","array_initializer not mapped"));
			}
		}
		
	}

	private void visitTypeofExpressionContext(TypeofExpressionContext old, TypeofExpressionContext newOne) {
		if (old.TYPEOF() != null) {
			if (newOne.TYPEOF() == null) {
				differencesList.add(new Difference(old.TYPEOF(),newOne,old.TYPEOF().getText(),"null","TYPEOF not mapped"));
			}
		}
		if (old.unbound_type_name() != null) {
			if (newOne.unbound_type_name() != null) {
				visitUnbound_type_name(old.unbound_type_name(),newOne.unbound_type_name());
			} else {
				differencesList.add(new Difference(old.unbound_type_name(),newOne,old.unbound_type_name().getText(),"null","unbound_type_name not mapped"));
			}
		}
		if (old.type_() != null) {
			if (newOne.type_() == null) {
				differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","type not mapped"));
			} else {
				if (!old.getText().equals(newOne.getText())) {
					differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),newOne.type_().getText(),"type not mapped right"));
				}
			}
		}
		if (old.VOID() != null) {
			if (newOne.VOID() == null) {
				differencesList.add(new Difference(old.VOID(),newOne,old.VOID().getText(),"null","VOID not mapped"));
			}
		}
	}
	
	private void visitUnbound_type_name(Unbound_type_nameContext old,Unbound_type_nameContext newOne) {
		// TODO Auto-generated method stub
		
	}

	/*
	 * unbound_type_name
	: identifier ( generic_dimension_specifier? | '::' identifier generic_dimension_specifier?)('.' identifier generic_dimension_specifier?)*
	;

generic_dimension_specifier
	: '<' ','* '>'
	;
	 */

	private void visitCheckedExpressionContext(CheckedExpressionContext old, CheckedExpressionContext newOne) {
		if (old.CHECKED() != null) {
			if (newOne.CHECKED() == null) {
				differencesList.add(new Difference(old.CHECKED(),newOne,old.CHECKED().getText(),"null","CHECKED not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
			}
		}
	}

	private void visitUncheckedExpressionContext(UncheckedExpressionContext old, UncheckedExpressionContext newOne) {
		if (old.UNCHECKED() != null) {
			if (newOne.UNCHECKED() == null) {
				differencesList.add(new Difference(old.UNCHECKED(),newOne,old.UNCHECKED().getText(),"null","UNCHECKED not mapped"));
			}
		}
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
			}
		}
	}

	private void visitDefaultValueExpressionContext(DefaultValueExpressionContext old,
			DefaultValueExpressionContext newOne) {
		if (old.DEFAULT() != null) {
			if (newOne.DEFAULT() == null) {
				differencesList.add(new Difference(old.DEFAULT(),newOne,old.DEFAULT().getText(),"null","DEFAULT modifier not mapped"));
			}
		}
		if (old.type_() != null) {
			if (newOne.type_() == null) {
				differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","type not mapped"));
			} else {
				if (!old.getText().equals(newOne.getText())) {
					differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),newOne.type_().getText(),"type not mapped right"));
				}
			}
		}
		
	}

	private void visitAnonymousMethodExpressionContext(AnonymousMethodExpressionContext old,
			AnonymousMethodExpressionContext newOne) {
		if (old.ASYNC() != null) {
			if (newOne.ASYNC() == null) {
				differencesList.add(new Difference(old.ASYNC(),newOne,old.ASYNC().getText(),"null","ASYNC not mapped"));
			}
		}
		if (old.DELEGATE() != null) {
			if (newOne.DELEGATE() == null) {
				differencesList.add(new Difference(old.DELEGATE(),newOne,old.DELEGATE().getText(),"null","DELEGATE not mapped"));
			}
		}
		if (old.explicit_anonymous_function_parameter_list() != null) {
			if (newOne.explicit_anonymous_function_parameter_list() != null) {
				visitExplicit_anonymous_function_parameter_list(old.explicit_anonymous_function_parameter_list(),newOne.explicit_anonymous_function_parameter_list());
			} else {
				differencesList.add(new Difference(old.explicit_anonymous_function_parameter_list(),newOne,old.explicit_anonymous_function_parameter_list().getText(),"null","explicit_anonymous_function_parameter_list not mapped"));
			}
		}
		
	}

	private void visitExplicit_anonymous_function_parameter_list(
			Explicit_anonymous_function_parameter_listContext old,
			Explicit_anonymous_function_parameter_listContext newOne) {
		List<Explicit_anonymous_function_parameterContext> oldArgs = old.explicit_anonymous_function_parameter();
		List<Explicit_anonymous_function_parameterContext> newArgs = newOne.explicit_anonymous_function_parameter();
		int i = 0;
		try {
			for (i = 0; i < oldArgs.size(); i++) {
				visitExplicit_anonymous_function_parameterContext(oldArgs.get(i),newArgs.get(i));
			}
		} catch (IndexOutOfBoundsException e) {
			differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","Explicit_anonymous_function_parameterContext not mapped"));
		}	
		
	}

	private void visitExplicit_anonymous_function_parameterContext(
			Explicit_anonymous_function_parameterContext old,
			Explicit_anonymous_function_parameterContext newOne) {
		if (old.REF() != null) {
			if (newOne.REF() == null) {
				differencesList.add(new Difference(old.REF(),newOne,old.REF().getText(),"null","REF modifier not mapped"));
			}
		}
		if (old.OUT() != null) {
			if (newOne.OUT() == null) {
				differencesList.add(new Difference(old.OUT(),newOne,old.OUT().getText(),"null","OUT modifier not mapped"));
			}
		}
		if (old.type_() != null) {
			if (newOne.type_() == null) {
				differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","type not mapped"));
			} else {
				if (!old.getText().equals(newOne.getText())) {
					differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),newOne.type_().getText(),"type not mapped right"));
				}
			}
		}
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				visitIdentifier(old.identifier(),newOne.identifier());
			} else {
				differencesList.add(new Difference(old.identifier(),newOne,old.identifier().getText(),"null","identifier not mapped"));
			}
		}
	}

	private void visitSizeofExpressionContext(SizeofExpressionContext old, SizeofExpressionContext newOne) {
		if (old.SIZEOF() != null) {
			if (newOne.SIZEOF() == null) {
				differencesList.add(new Difference(old.SIZEOF(),newOne,old.SIZEOF().getText(),"null","SIZEOF not mapped"));
			}
		}
		if (old.type_() != null) {
			if (newOne.type_() == null) {
				differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),"null","type not mapped"));
			} else {
				if (!old.getText().equals(newOne.getText())) {
					differencesList.add(new Difference(old.type_(),newOne,old.type_().getText(),newOne.type_().getText(),"type not mapped right"));
				}
			}
		}
	}

	private void visitNameofExpressionContext(NameofExpressionContext old, NameofExpressionContext newOne) {
		if (old.NAMEOF() != null) {
			if (newOne.NAMEOF() == null) {
				differencesList.add(new Difference(old.NAMEOF(),newOne,old.NAMEOF().getText(),"null","NAMEOF not mapped"));
			}
		}
		if (old.identifier() != null) {
			if (newOne.identifier() != null) {
				List<IdentifierContext> oldArgs = old.identifier();
				List<IdentifierContext> newArgs = newOne.identifier();
				int i = 0;
				try {
					for (i = 0; i < oldArgs.size(); i++) {
						visitIdentifier(oldArgs.get(i),newArgs.get(i));
					}
				} catch (IndexOutOfBoundsException e) {
					differencesList.add(new Difference(oldArgs.get(i),newOne,oldArgs.get(i).getText(),"null","idenfier not mapped"));
				}	
			} else {
				differencesList.add(new Difference(old,newOne,"identifier list","null","identifiers for not mapped"));
			}
		}
		
		
	}

	private void visitParenthesisExpressionsContext(ParenthesisExpressionsContext old,ParenthesisExpressionsContext newOne) {
		if (old.expression() != null) {
			if (newOne.expression() != null) {
				visitExpression(old.expression(),newOne.expression());
			} else {
				differencesList.add(new Difference(old.expression(),newOne,old.expression().getText(),"null","expression not mapped"));
			}
		}
		
	}

	private void visitSimpleNameExpressionContext(Primary_expression_startContext old,Primary_expression_startContext newOne) {
		if (newOne != null) {
			if (!old.getText().equals(newOne.getText())) {
				differencesList.add(new Difference(old,newOne,old.getText(),"null","Literal not mapped"));
			}
		} else {
			differencesList.add(new Difference(old,newOne,old.getText(),"null","Literal not mapped"));
		}
	}

	private void visitLiteralExpressionContext(Primary_expression_startContext old,Primary_expression_startContext newOne) {
		if (newOne != null) {
			if (!old.getText().equals(newOne.getText())) {
				differencesList.add(new Difference(old,newOne,old.getText(),newOne.getText(),"Literal not mapped right"));
			}
		} else {
			differencesList.add(new Difference(old,newOne,old.getText(),"null","Literal not mapped"));
		}
	}

	public void visitUsing_directive(Using_directiveContext oldOne, Using_directiveContext newOne) {
		if (oldOne.getClass().getSimpleName().equals("UsingAliasDirectiveContext")) {
			try {
				visitUsingAliasDirectiveContext((UsingAliasDirectiveContext) oldOne, (UsingAliasDirectiveContext) newOne);
			} catch (ClassCastException e) {
				differencesList.add(new Difference(oldOne,newOne,"UsingAliasDirectiveContext",newOne.getClass().getSimpleName(),"mapped from alias directive to " + newOne.getClass().getSimpleName()));
			}
			
		} else if (oldOne.getClass().getSimpleName().equals("UsingNamespaceDirectiveContext")) {
			try {
				visitUsingNamespaceDirectiveContext((UsingNamespaceDirectiveContext) oldOne, (UsingNamespaceDirectiveContext) newOne);
			} catch (ClassCastException e) {
				differencesList.add(new Difference(oldOne,newOne,"UsingNamespaceDirectiveContext",newOne.getClass().getSimpleName(),"mapped from normal directive to " + newOne.getClass().getSimpleName()));
			}
			
		} else if (oldOne.getClass().getSimpleName().equals("UsingStaticDirectiveContext")) {
			try {
				visitUsingStaticDirectiveContext((UsingStaticDirectiveContext) oldOne, (UsingStaticDirectiveContext) newOne);
			} catch (ClassCastException e) {
				differencesList.add(new Difference(oldOne,newOne,"UsingStaticDirectiveContext",newOne.getClass().getSimpleName(),"mapped from alias directive to " + newOne.getClass().getSimpleName()));
			}
		}
	}
	
	public void visitUsingStaticDirectiveContext(UsingStaticDirectiveContext old, UsingStaticDirectiveContext newOne){
		if (!old.namespace_or_type_name().getText().equals(newOne.namespace_or_type_name().getText())) {
			differencesList.add(new Difference(old,newOne,old.namespace_or_type_name().getText(),newOne.namespace_or_type_name().getText(),"name not mapped right"));
		}
	}
	
	public void visitUsingNamespaceDirectiveContext(UsingNamespaceDirectiveContext old, UsingNamespaceDirectiveContext newOne) {
		if (!old.namespace_or_type_name().getText().equals(newOne.namespace_or_type_name().getText())) {
			differencesList.add(new Difference(old,newOne,old.namespace_or_type_name().getText(),newOne.namespace_or_type_name().getText(),"name not mapped right"));
		}
	}
	
	public void visitUsingAliasDirectiveContext(UsingAliasDirectiveContext old, UsingAliasDirectiveContext newOne) {
		if (!old.identifier().getText().equals(newOne.identifier().getText())) {
			differencesList.add(new Difference(old,newOne,old.identifier().getText(),newOne.identifier().getText(),"identifier not mapped right"));
		}
		if (!old.namespace_or_type_name().getText().equals(newOne.namespace_or_type_name().getText())) {
			differencesList.add(new Difference(old,newOne,old.namespace_or_type_name().getText(),newOne.namespace_or_type_name().getText(),"name not mapped right"));
		}
	}
	
	public DictionaryMap getDictionary() {
		return dictionary;
	}

	public void setDictionary(DictionaryMap dictionary) {
		this.dictionary = dictionary;
	}
	
	public List<Difference> getDifferencesList() {
		return differencesList;
	}

	public void setDifferencesList(List<Difference> differencesList) {
		this.differencesList = differencesList;
	}

}
