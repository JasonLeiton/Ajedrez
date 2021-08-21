package gastmappers.javamapper.mapperutilities;

import java.util.ArrayList;
import java.util.List;

import ASTMCore.ASTMSyntax.Types.NamedTypeReference;
import org.eclipse.jdt.core.dom.*;

import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.VariableDefinition;
import ASTMCore.ASTMSyntax.Expression.Expression;
import ASTMCore.ASTMSyntax.Statement.SwitchCase;
import ASTMCore.ASTMSyntax.Types.DerivesFrom;
import ASTMCore.ASTMSyntax.Types.MemberObject;
import gastmappers.javamapper.JavaCompilationMapper;

/**
 * La función de esta clase es ser un intermediario para los mapeos con alto anidamiento de
 * objetos y con clases abstractas. La idea fundamental es dividir dichos mapeos en más
 * pequeños y simples.
 *
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class JavaCompilatioUniti {
    /**
     * Constructor de la clase, no posee lógica
     */
    public JavaCompilatioUniti() {

    }

    /**
     * Se encarga de tomar las clases de las cuales deriva una determinada clase, además de
     * además llama a varios mapeos individuales para obtner el más complejo y grande el cual
     * corresponde a las clases derivadas del GAST
     *
     * @param objeto Nodo del AST de java correspondiente a una clase
     * @return lista de clases derivadas, las cuales son un atributo del nodo Clase del GAST
     */
    public List<DerivesFrom> SuperClasstoDerives(TypeDeclaration objeto) {
        DerivesFrom derivesFrom;
        List<DerivesFrom> mapped = new ArrayList<DerivesFrom>();

        // JNS: superclass binding mapped
        if (!objeto.isInterface() && objeto.getSuperclassType() != null) {
            derivesFrom = new DerivesFrom();
            derivesFrom.setSuperclass(JavaCompilationMapper.INSTANCE.TypeReferencetoType(objeto.getSuperclassType()));
            ITypeBinding typeBinding = objeto.resolveBinding();
            if (typeBinding != null) {
                String className = ((NamedTypeReference) derivesFrom.getSuperclass()).getTypeName().getNameString();
                String packageName = typeBinding.getPackage().toString().replace("package ", "");
                String signature = packageName
                        .concat(".")
                        .concat(className);
                derivesFrom.setClassName(className);
                derivesFrom.setPackageName(packageName);
                derivesFrom.setSignature(signature);
            }
            mapped.add(derivesFrom);
        //} else if (objeto.isInterface() && objeto.superInterfaceTypes().size() > 0) {
        //    for (int i = 0; i < objeto.superInterfaceTypes().size(); i++) {
        //        derivesFrom = new DerivesFrom();
        //        derivesFrom.setSuperclass(JavaCompilationMapper.INSTANCE.TypeReferencetoType((Type)objeto.superInterfaceTypes().get(i)));
        //        ITypeBinding typeBinding = objeto.resolveBinding();
        //        String className = ((NamedTypeReference) derivesFrom.getSuperclass()).getTypeName().getNameString();
        //        String packageName = typeBinding.getPackage().toString().replace("package ", "");
        //        String signature = packageName
        //                .concat(".")
        //                .concat(className);
        //        derivesFrom.setClassName(className);
        //        derivesFrom.setPackageName(packageName);
        //        derivesFrom.setSignature(signature);
        //        mapped.add(derivesFrom);
        //    }
        } else {
            mapped = null;
        }
        return mapped;
    }
	/*OLD CODE FOR SuperClasstoDerives, this wors with the old DerivesFrom definition.
	public List<DerivesFrom> SuperClasstoDerives(TypeDeclaration objeto){
		DerivesFrom inherency=new DerivesFrom();
		List<DerivesFrom> mapped=new ArrayList<DerivesFrom>();
		if(objeto.getSuperclassType() instanceof SimpleType) {
			SimpleType namestring=(SimpleType) objeto.getSuperclassType();
			Name name=new Name(namestring.getName().getFullyQualifiedName());
			inherency.setDirectClassDerived(name);
			mapped.add(inherency);
		}
		else if (objeto.getSuperclassType()!=null) {
			List<Type> classes=new ArrayList<Type>();
			classes.add(objeto.getSuperclassType());
			inherency.setClassName(CompilationMapper.INSTANCE.ListTypeReferencetoListType(classes));
			mapped.add(inherency);
		}
		else
			mapped=null;
		//inherency.setDirectClassDerived(name);
		return mapped;
		}
	 */

    /**
     * Se encarga de descomponer los mapeos de los atributos de una clase en pequeños
     * simples, con el fin simplificar las tareas de mapeo.
     *
     * @param atributos Lista de atributos de un nodo clase del AST de Java
     * @return Lista de atributos, la cual es un atributo del objeto clase del GAST, es decir,
     * los atributos de una clase del GAST
     */
    // Este método es para mapear los atributos
    public List<MemberObject> FieldtoMemberObject(FieldDeclaration[] atributos) {
        List<MemberObject> lista = new ArrayList<MemberObject>();// lista de los atributos a retornar
        MemberObject atributo1; // lo que es un atributo
        VariableDefinition value = new VariableDefinition();

        for (int i = 0; i < atributos.length; i++) { // para cada atributo
            value.setDefinitionType(JavaCompilationMapper.INSTANCE.TypeReferencetoType(atributos[i].getType()));
            value.setFragments(JavaCompilationMapper.INSTANCE.fragmentsToFragments(atributos[i].fragments()));
            //value=CompilationMapper.INSTANCE.variabletovariable // cree la variable
            //((VariableDeclarationFragment) atributos[i].fragments().get(0),  atributos[i].getType());

            MemberObject atributo = JavaCompilationMapper.INSTANCE.fieldDeclarationtoMemberObject
                    (value, atributos[i].modifiers());
            lista.add(atributo);
            value = new VariableDefinition();
        }
        return lista;
    }

    /**
     * Este es método mapea lista de modificadores para una variable local
     *
     * @param lista modificadores de la variable
     * @return lista de modificadores correspondientes a la lista de entradas con objetos del GAST
     */
    public List<Modifiers> ListModifierToListModifier(List<IExtendedModifier> lista) {
        List<Modifiers> modificadores = new ArrayList<Modifiers>();
        for (IExtendedModifier i : lista) {
            modificadores.add(JavaCompilationMapper.INSTANCE.modifiertomodifier(i));
        }
        return modificadores;
    }

    /**
     * This method is for mapping switch and cases
     *
     * @param lista it is the list of stataments which they are into cases
     * @return list of cases with hierarchy
     */
    public List<SwitchCase> ListSwitchToListSwitch(List<Statement> lista) {
        ArrayList<SwitchCase> listaCases = new ArrayList<SwitchCase>();
        ArrayList<Statement> listaStatements = new ArrayList<Statement>();
        Expression evaluation = null;
        boolean bandera = true; // identifies where is the case
        for (Statement i : lista) {
            if (i instanceof org.eclipse.jdt.core.dom.SwitchCase) { // it means that is case
                if (bandera) {
                    bandera = false;
                    evaluation = JavaCompilationMapper.INSTANCE.ExpresionToExpresion(((org.eclipse.jdt.core.dom.SwitchCase) i).getExpression());
                } else {// begin other case
                    SwitchCase caseStatement = new SwitchCase();
                    caseStatement.setBody(JavaCompilationMapper.INSTANCE.listToListStatement(listaStatements));
                    // mapear la condición
                    caseStatement.setEvaluation(evaluation);
                    evaluation = JavaCompilationMapper.INSTANCE.ExpresionToExpresion(((org.eclipse.jdt.core.dom.SwitchCase) i).getExpression());
                    listaCases.add(caseStatement);
                    listaStatements = new ArrayList<Statement>();
                }
            } else {
                listaStatements.add(i);
            }
        }
        // last case
        SwitchCase caseStatement = new SwitchCase();
        caseStatement.setBody(JavaCompilationMapper.INSTANCE.listToListStatement(listaStatements));
        caseStatement.setEvaluation(evaluation);
        listaCases.add(caseStatement);
        return listaCases;
        // ready
    }

    /**
     * This method is to extract and map all members of a enumarate
     *
     * @param bodyDeclaration List of all declaration in enumerate
     * @return List of mapped members of this enumerate
     */
    public List<MemberObject> AddMembersToEnums(List<BodyDeclaration> bodyDeclaration) {
        ArrayList<FieldDeclaration> members = new ArrayList<>();
        for (BodyDeclaration declaration : bodyDeclaration) {
            if ((declaration instanceof FieldDeclaration)) {
                members.add((FieldDeclaration) declaration);
            }
        }
        FieldDeclaration[] finalMembers = new FieldDeclaration[members.size()];
        return FieldtoMemberObject(members.toArray(finalMembers));
    }
}