package gastmappers.javamapper;

import java.util.ArrayList;
import java.util.List;

import ASTMCore.ASTMSource.SourceLocation;
import ASTMCore.ASTMSyntax.Types.*;
import ASTMCore.ASTMSyntax.Types.ArrayType;
import gastmappers.MappedMethod;
import gastmappers.sb2mapper.grammar.Scratch2Parser;
import gastmetrics.JavaCyclomaticComplexity;
import gastmetrics.JavaHalstead;
import gastmetrics.JavaNumberOfLines;
import gastmetrics.Metrics;
import gastmetrics.HalsteadMetrics;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.dom.*;
//import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import gastmappers.javamapper.mapperutilities.JavaCompilatioUniti;
import ASTMCore.ASTMSource.CompilationUnit;
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
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.LabelDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Modifiers;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.Name;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.NameSpaceDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.NativeModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.PrivateModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.ProtectedModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.PublicModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.StaticModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.StrictfpModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.SynchronizedModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.TransientModifier;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.TypeDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.VariableDefinition;
import ASTMCore.ASTMSyntax.DeclarationAndDefinition.VolatileModifier;
import ASTMCore.ASTMSyntax.Expression.ActualParameter;
import ASTMCore.ASTMSyntax.Expression.ActualParameterExpression;
import ASTMCore.ASTMSyntax.Expression.Add;
import ASTMCore.ASTMSyntax.Expression.And;
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
import ASTMCore.ASTMSyntax.Expression.CastExpression;
import ASTMCore.ASTMSyntax.Expression.CollectionExpression;
import ASTMCore.ASTMSyntax.Expression.Decrement;
import ASTMCore.ASTMSyntax.Expression.DefaultUnaryOperator;
import ASTMCore.ASTMSyntax.Expression.Divide;
import ASTMCore.ASTMSyntax.Expression.Equal;
import ASTMCore.ASTMSyntax.Expression.FunctionCallExpression;
import ASTMCore.ASTMSyntax.Expression.GenericOperator;
import ASTMCore.ASTMSyntax.Expression.Greater;
import ASTMCore.ASTMSyntax.Expression.IdentifierReference;
import ASTMCore.ASTMSyntax.Expression.Increment;
import ASTMCore.ASTMSyntax.Expression.InstanceOfExpression;
import ASTMCore.ASTMSyntax.Expression.LabelAccess;
import ASTMCore.ASTMSyntax.Expression.Less;
import ASTMCore.ASTMSyntax.Expression.MemberAccess;
import ASTMCore.ASTMSyntax.Expression.MemberValuePair;
import ASTMCore.ASTMSyntax.Expression.Modulus;
import ASTMCore.ASTMSyntax.Expression.Multiply;
import ASTMCore.ASTMSyntax.Expression.NewExpression;
import ASTMCore.ASTMSyntax.Expression.Not;
import ASTMCore.ASTMSyntax.Expression.NotEqual;
import ASTMCore.ASTMSyntax.Expression.NotGreater;
import ASTMCore.ASTMSyntax.Expression.NotLess;
import ASTMCore.ASTMSyntax.Expression.Or;
import ASTMCore.ASTMSyntax.Expression.PostDecrement;
import ASTMCore.ASTMSyntax.Expression.PostIncrement;
import ASTMCore.ASTMSyntax.Expression.PostfixUnaryExpression;
import ASTMCore.ASTMSyntax.Expression.QualifiedIdentifierReference;
import ASTMCore.ASTMSyntax.Expression.QualifiedOverData;
import ASTMCore.ASTMSyntax.Expression.Subtract;
import ASTMCore.ASTMSyntax.Expression.SuperMemberAccess;
import ASTMCore.ASTMSyntax.Expression.PrefixUnaryExpression;
import ASTMCore.ASTMSyntax.Expression.UnaryMinus;
import ASTMCore.ASTMSyntax.Expression.UnaryOperator;
import ASTMCore.ASTMSyntax.Expression.UnaryPlus;
import ASTMCore.ASTMSyntax.Expression.VariableExpression;
import ASTMCore.ASTMSyntax.Statement.BlockStatement;
import ASTMCore.ASTMSyntax.Statement.CatchBlock;
import ASTMCore.ASTMSyntax.Statement.DeclarationOrDefinitionStatement;
import ASTMCore.ASTMSyntax.Statement.DoWhileStatement;
import ASTMCore.ASTMSyntax.Statement.ForCheckAfterStatement;
import ASTMCore.ASTMSyntax.Statement.ForIterator;
import ASTMCore.ASTMSyntax.Statement.Statement;
import ASTMCore.ASTMSyntax.Statement.SuperInvocation;
import ASTMCore.ASTMSyntax.Statement.SwitchStatement;

import javax.sound.midi.SysexMessage;

/**
 * La función de esta clase es proveer los métodos que se necesitan para mapear
 * los datos del AST de Java, proveniente de JDT, al GAST.
 *
 * @author Equipo de trabajo AVIB.
 * @version 1.0
 * @since 1.0
 */

@Mapper
public interface JavaCompilationMapper {

    JavaCompilationMapper INSTANCE = Mappers.getMapper(JavaCompilationMapper.class);
    //To identify if it is a semantic mapped.
    Semantic semantic = new Semantic();
    // It is necessary to have the compilationUnit in order to identify the line number.
    CU compilation = new CU();
    /**
     * Este método es el que mapea los elementos más externos del AST de Java para
     * generar la raiz del GAST .
     *
     * @param compilation Raiz del AST de java.
     * @param imports     Las importaciones que se hacen en la clase.
     * @param tipos       Los tipos agregados, por ejemplo clase y enumerados.
     * @return La raíz del GAST con datos mapeados del AST de Java.
     */
    @Mappings({
            @Mapping(target = "path", ignore = true),
            @Mapping(target = "fragments", ignore = true),
            @Mapping(target = "language", ignore = true),
            @Mapping(source = "tipos", target = "opensScope.declOrDefn"),
            @Mapping(source = "imports", target = "imports"),
            @Mapping(source = "compilation.package", target = "gPackage"),
            @Mapping(constant = "compilationUnit", target = "tag"),
            @Mapping(constant = "body", target = "opensScope.tag")
    })
    CompilationUnit compilationtocompilation(org.eclipse.jdt.core.dom.CompilationUnit compilation,
                                             List<ImportDeclaration> imports, List<AbstractTypeDeclaration> tipos);

    /////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////// Mapeo de los valores
    ///////////////////////////////////////////////////////////////////////////////////////// iniciales////////////////////

    /**
     * Mapea un valor string del AST de Java al un String del GAST.
     *
     * @param value valor de la variable.
     * @return el objeto string del GAST con el valor de la variable.
     */
    @Mappings({@Mapping(source = "literalValue", target = "value"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(value))")})
    ASTMCore.ASTMSyntax.Expression.StringLiteral stringtostring(StringLiteral value);

    default SourceLocation createSourceLocation(org.eclipse.jdt.core.dom.ASTNode node){

        SourceLocation location = new SourceLocation();
        //location.setStartLine(compilation.getCu().getLineNumber(node.getStartPosition()));
        //location.setStartPosition(compilation.getCu().getColumnNumber(node.getStartPosition()) + 1);

        //System.out.println("Name: " + node.toString());
        //System.out.println("Line: " + compilation.getCu().getLineNumber(node.getStartPosition()));
        //System.out.println("Column: " + (compilation.getCu().getColumnNumber(node.getStartPosition()) + 1));
        return location;
    }

    /**
     * Mapea un valor char del AST de Java a un String del GAST.
     *
     * @param value     valor de la literal.
     * @param charValue Valor del dato inicial.
     * @return el objeto string del GAST con el valor de la variable charValue.
     */
    @Mappings({@Mapping(source = "charValue", target = "value"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(value))")})
    ASTMCore.ASTMSyntax.Expression.CharLiteral chartochar(CharacterLiteral value, String charValue);

    /**
     * Mapea un valor numérico del AST de Java a un valor numérico del GAST.
     *
     * @param value Valor de la variable.
     * @return El objeto string del GAST con el valor de la variable .
     */
    @Mappings({@Mapping(source = "token", target = "value"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(value))")})
    ASTMCore.ASTMSyntax.Expression.RealLiteral integertointeger(NumberLiteral value);

    /**
     * Mapea un valor booleano del AST de Java a un valor booleano del GAST.
     *
     * @param value     .
     * @param boolValue Valor de la variable.
     * @return El objeto booleno del GAST con el valor de la variable.
     */
    @Mappings({@Mapping(source = "boolValue", target = "value"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(value))")})
    ASTMCore.ASTMSyntax.Expression.BooleanLiteral booleantoboolean(BooleanLiteral value, boolean boolValue);

    /**
     * Mapea un valor nulo del AST de Java a un valor string(null) del GAST.
     *
     * @param value Valor de la variable.
     * @return El objeto String del GAST con el valor de la variable (null).
     */
    @Mappings({@Mapping(constant = "null", target = "value"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(value))")})
    ASTMCore.ASTMSyntax.Expression.NullLiteral nulltonull(NullLiteral value);

    /**
     * Se encarga mapear un tipo abstracto agregado como es el caso de clase o
     * enumerado.
     *
     * @param tipo El objeto a mapear.
     * @return El tipo de objeto agregado del GAST.
     */
    ///////////////// Metodo default porque DeclarationTpe es
    ///////////////// abstrac//////////////////////
    default TypeDefinition classtoclass(AbstractTypeDeclaration tipo) {

        if (tipo instanceof TypeDeclaration) {
            if (semantic.isSemantic()) {
                // JNS: binding package in class definition
                AggregateTypeDefinition aggregateTypeDefinition = TypeDeclarationtoClassType((TypeDeclaration) tipo);
                ClassType classType = (ClassType)aggregateTypeDefinition.getAggregateType();
                ITypeBinding typeBinding = tipo.resolveBinding();
                classType.setPackageName(bindClassPackage(typeBinding, typeBinding.getPackage().getName()));

                aggregateTypeDefinition.setLocationInfo(createSourceLocation(tipo));
                return aggregateTypeDefinition;
            }
            return TypeDeclarationtoClassType((TypeDeclaration) tipo);
        } else if (tipo instanceof EnumDeclaration) {
            EnumDeclaration enu = (EnumDeclaration) tipo;
            JavaCompilatioUniti api = new JavaCompilatioUniti();
            EnumTypeDefinition newEnum = TypeDeclarationToEnumTypeDefinition(enu.getName().toString(), enu.modifiers(),
                    enu.bodyDeclarations(), enu.superInterfaceTypes());
            newEnum.getDefinitionType().setEnumLiterals(BodyToBody(enu.enumConstants()));
            newEnum.getDefinitionType().setMembers(api.AddMembersToEnums(enu.bodyDeclarations()));
            newEnum.setLocationInfo(createSourceLocation(tipo));
            return newEnum;
        } else if (tipo instanceof AnnotationTypeDeclaration) {
            AnnotationTypeDeclaration annotation = (AnnotationTypeDeclaration) tipo;
            AnnotationType newAnnotation = TypeDeclarationToAnnotatioDeclaration(
                    new Name(annotation.getName().toString()), annotation.modifiers(), annotation.bodyDeclarations());
            newAnnotation.setLocationInfo(createSourceLocation(tipo));
            return newAnnotation;

        } else
            return null;
    }

    /**
     * Se encarga mapear la definición de un tipo abstracto agregado como es el caso
     * de clase o enumerado
     *
     * @param tipo El objeto a mapear (clase, enumerados, anotaciones).
     * @return El tipo de definición del objeto agregado del GAST.
     */
    ///////////////// Método default para (clases, enums y
    ///////////////// anotaciones)///////////////////////////////////////////////////////
    default AggregateType TypeDeclarationtoClass(AbstractTypeDeclaration tipo) {
        if (tipo instanceof TypeDeclaration) {
            TypeDeclaration objeto = (TypeDeclaration) tipo;
            JavaCompilatioUniti api = new JavaCompilatioUniti();
            // JNS: implemented interfaces binded in class declaration
            ClassType mappingClass = MappingClass(objeto, objeto.modifiers(), objeto.bodyDeclarations(), api.SuperClasstoDerives(objeto),
                    objeto.superInterfaceTypes(), api.FieldtoMemberObject(objeto.getFields()), objeto.typeParameters());

            for (int itf = 0; itf < mappingClass.getImplementesTo().size() && semantic.isSemantic(); itf++) {
                ImplementsTo anInterface = mappingClass.getImplementesTo().get(itf);
                SimpleType typeDeclaration;
                try {
                    typeDeclaration = (SimpleType) objeto.superInterfaceTypes().get(itf);
                    ITypeBinding interfaceBinding = typeDeclaration.resolveBinding();
                    String interfaceName = interfaceBinding.getName();
                    String packageName = bindClassPackage(interfaceBinding, interfaceBinding.getPackage().toString().replace("package ", ""));
                    String signature = packageName.concat(".").concat(interfaceName);
                    anInterface.setInterfaceName(interfaceName);
                    anInterface.setPackageName(packageName);
                    anInterface.setSignature(signature);
                }
                catch (ClassCastException ex) {
                    boolean stopHere = true;
                }
            }
            return mappingClass;
        } else
            return null;
    }

    ////////////////// Método que mapea la especificación del
    ////////////////// enumerado/////////////////////////////////////////////////////////////////
    default EnumType EnumtoEnum(EnumDeclaration enu) {
        EnumType newEnu = new EnumType();
        newEnu.setImplementesTo(implementstoimplementsList(enu.superInterfaceTypes()));
        newEnu.setIdentifier(enu.getName().getFullyQualifiedName());
        newEnu.setLocationInfo(createSourceLocation(enu));
        return newEnu;
    }

    /**
     * Realiza el mapeo de un modificador del AST de Java a un modificador del GAST.
     *
     * @param modifier Nodo modificador del JDT.
     * @return Modificador del GAST con los datos provenientes de JDT.
     */
    //////////////// Método para los modificadores y saber cuales son///////////
    default Modifiers modifiertomodifier(IExtendedModifier modifier) {
        Modifiers newmodifier = null;
        String tipo = "";
        if (modifier instanceof Modifier) {
            tipo = ((Modifier) modifier).getKeyword().toString();
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
                case "final":
                    newmodifier = new FinalModifier();
                    break;
                case "native":
                    newmodifier = new NativeModifier();
                    break;
                case "synchronized":
                    newmodifier = new SynchronizedModifier();
                    break;
                case "transient":
                    newmodifier = new TransientModifier();
                    break;
                case "volatile":
                    newmodifier = new VolatileModifier();
                    break;
                case "default":
                    newmodifier = new DefaultModifier();
                    break;
                case "strictfp":
                    newmodifier = new StrictfpModifier();
                    break;
                default:
                    newmodifier = new PublicModifier();
                    break;
            }
        } else if (modifier instanceof MarkerAnnotation) {
            tipo = ((MarkerAnnotation) modifier).getTypeName().getFullyQualifiedName();
            newmodifier = new AnnotationModifier(tipo);
        } else if (modifier instanceof SingleMemberAnnotation) {
            SingleMemberAnnotation annotation = (SingleMemberAnnotation) modifier;
            ASTMCore.ASTMSyntax.DeclarationAndDefinition.SingleMemberAnnotation newAnnotation = new ASTMCore.ASTMSyntax.DeclarationAndDefinition.SingleMemberAnnotation();
            newAnnotation.setName(ExpresionToExpresion(annotation.getTypeName()));
            newAnnotation.setValue(ExpresionToExpresion(annotation.getValue()));

            newmodifier = newAnnotation;
        }
        newmodifier.setLocationInfo(createSourceLocation((ASTNode) modifier));
        return newmodifier;

    }
    ///////////////////////////////////////////////////////////////////////////

    // default ImplementsTo implementstoimplemetsobject(Type tipo) {
    // return superinterfacetoimplementsto((SimpleType)tipo);
    // }
    ////////////////////////////////////////////////////////////////////////////////
    //////////////////////// Método para operadores unarios/////////////////////////
    default UnaryOperator PrefixOperadorUnaryToPrefixOperadorUnary(String operador) {
        // True is for a postfix operator, false is for a prefix operator.
        UnaryOperator newOperand = null;
        switch (operador) {
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
        newOperand.setSimbolo(operador);
        return newOperand;
    }

    default UnaryOperator PostfixOperadorUnaryToPostfixOperadorUnary(String operador) {
        // True is for a postfix operator, false is for a prefix operator.
        UnaryOperator newOperand = null;
        switch (operador) {
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
        newOperand.setSimbolo(operador);
        return newOperand;
    }

    //////////////////////// Método para operandos/////////////////////////////////
    default BinaryOperator OperatorToOperator(String operador) {
        BinaryOperator newOperador = null;
        switch (operador) {
            // Multiplicative operators.
            case "*":
                newOperador = new Multiply();
                break;
            case "/":
                newOperador = new Divide();
                break;
            case "%":
                newOperador = new Modulus();
                break;
            // Additive operators.
            case "+":
                newOperador = new Add();
                break;
            case "-":
                newOperador = new Subtract();
                break;
            // Shift operators.
            case "<<":
                newOperador = new BitLeftShift();
                break;
            case ">>":
                newOperador = new BitSignedRightShift();
                break;
            case ">>>":
                newOperador = new BitUnsignedRightShift();
                break;
            // Relational operators.
            case "<":
                newOperador = new Less();
                break;
            case ">":
                newOperador = new Greater();
                break;
            case "<=":
                newOperador = new NotGreater();
                break;
            case ">=":
                newOperador = new NotLess();
                break;
            // Equality operators.
            case "==":
                newOperador = new Equal();
                break;
            case "!=":
                newOperador = new NotEqual();
                break;
            // Bitwise and Logical operators.
            case "&":
                newOperador = new BitAnd();
                break;
            case "^":
                newOperador = new BitXor();
                break;
            case "|":
                newOperador = new BitOr();
                break;
            // Conditional operators.
            case "&&":
                newOperador = new And();
                break;
            case "||":
                newOperador = new Or();
                break;
            // Assignment operators.
            case "=":
                newOperador = new Assign();
                break;
            case "*=":
                newOperador = new AssignMultiply();
                break;
            case "/=":
                newOperador = new AssignDivide();
                break;
            case "%=":
                newOperador = new AssignModulus();
                break;
            case "+=":
                newOperador = new AssignAdd();
                break;
            case "-=":
                newOperador = new AssignSubtract();
                break;
            case "<<=":
                newOperador = new AssignBitLeftShift();
                break;
            case ">>=":
                newOperador = new AssignBitSignedRightShift();
                break;
            case ">>>=":
                newOperador = new AssignBitUnsignedRightShift();
                break;
            case "&=":
                newOperador = new AssignBitAnd();
                break;
            case "^=":
                newOperador = new AssignBitXor();
                break;
            case "|=":
                newOperador = new AssignBitOr();
                break;
            default:
                // poner la del genérico
                GenericOperator a = new GenericOperator();
                a.setSimbol(operador);
                newOperador = a;
                break;
        }
        return newOperador;
    }

    // JNS: FIX - add nested classes to signature
    default String bindClassPackage(ITypeBinding declaringClass, String packageName) {
        String innerClasses = "";
        ITypeBinding parent = declaringClass.getDeclaringClass();
        while (parent instanceof ITypeBinding) {
            innerClasses = "." + parent.getName().toString() + innerClasses;
            parent = parent.getDeclaringClass();
        }
        return packageName + innerClasses;
    }

    /**
     * Este método se encarga de mapear el objeto abstracto de expresiones de los
     * AST.
     *
     * @param expression Objeto de expresión del AST de Java que se desea mapear.
     * @return Expresión del GAST .
     */
    /////////////////////// Método que realiza el mapeo de expresion a
    /////////////////////// expresion////////////////////////////////////////////////////////
    default ASTMCore.ASTMSyntax.Expression.Expression ExpresionToExpresion(Expression expression) {
        ASTMCore.ASTMSyntax.Expression.Expression newexpression;
        if (expression instanceof ArrayInitializer) {
            CollectionExpression a = new CollectionExpression();// objeto al que se quiere llegar
            ArrayInitializer b = ((ArrayInitializer) expression);
            List<ASTMCore.ASTMSyntax.Expression.Expression> definition = expressionListToExpressionList(
                    b.expressions());
            a.setExpressionList(definition);
            newexpression = a;
        } else if (expression instanceof NormalAnnotation) {
            NormalAnnotation normalAnnotation = (NormalAnnotation) expression;
            ASTMCore.ASTMSyntax.Expression.NormalAnnotation newNormalAnnotation = new ASTMCore.ASTMSyntax.Expression.NormalAnnotation();
            newNormalAnnotation.setName(ExpresionToExpresion(normalAnnotation.getTypeName()));
            newNormalAnnotation.setValue(ListValuePairToListValuePair(normalAnnotation.values()));
            newexpression = newNormalAnnotation;

        } else if (expression instanceof VariableDeclarationExpression) {
            VariableDeclarationExpression variable = (VariableDeclarationExpression) expression;
            JavaCompilatioUniti api = new JavaCompilatioUniti();
            VariableDefinition newVariable = new VariableDefinition();
            newVariable.setDefinitionType(TypeReferencetoType(variable.getType()));
            newVariable.setModifiers(api.ListModifierToListModifier(variable.modifiers()));
            newVariable.setFragments(fragmentsToFragments(variable.fragments()));
            // this variable is to indicate that is an expression a no a statement
            VariableExpression aux = new VariableExpression();
            aux.setVariable(newVariable);
            newexpression = aux;

        } else if (expression instanceof ClassInstanceCreation) {
            if (semantic.isSemantic()) {
                // JNS: TODO: Mapping for lambda expressions
                ClassInstanceCreation object = (ClassInstanceCreation) expression;
                // JNS: constructor call binding (className, package, methodName, signature) bot for caller and callee
                IMethodBinding methodBinding = object.resolveConstructorBinding();
                String declaringClassName = "";
                String declaringClassPackage = "";
                String declaringClassMethodName = "";
                String signature = "";
                if (methodBinding != null) {
                    // TODO: Resolve binding for generic types like: new GenericType<ArrayList<CompilationUnit>>(){}
                    ITypeBinding declaringClass = methodBinding.getDeclaringClass();
                    declaringClassName = declaringClass.getName();
                    if (declaringClassName.length() > 0) {
                        declaringClassPackage = bindClassPackage(declaringClass, declaringClass.getPackage().toString().replace("package ", ""));
                        String parameters = "";
                        ITypeBinding[] params = methodBinding.getParameterTypes();
                        int nparams = params.length;
                        for (int p = 0; p < nparams; p++) {
                            parameters = parameters.concat(params[p].getName());
                            if (p < nparams-1)
                                parameters = parameters.concat(",");
                        }
                        signature =
                                declaringClassPackage
                                        .concat(".")
                                        .concat(declaringClass.getName())
                                        .concat(".")
                                        .concat(declaringClass.getName())
                                        .concat("(").concat(parameters).concat(")");
                        declaringClassMethodName = declaringClass.getName().concat("(").concat(parameters).concat(")");
                    }
                }

                // JNS: constructor call binding setting
                NewExpression newObject = newToNewExpression(object, object.arguments());
                newObject.setSignature(signature);
                newObject.setClassName(declaringClassName);
                newObject.setPackageName(declaringClassPackage);
                newObject.setMethodName(declaringClassMethodName);
                newObject.setCallerClassName(mappedMethod.getClassName());
                newObject.setCallerPackageName(mappedMethod.getPackageName());
                newObject.setCallerSignature(mappedMethod.getSignature());
                newObject.setCallerMethodName(mappedMethod.getMethodName());
                newexpression = newObject;
                newexpression.setTag("InstanceCreation");

            }else{
                ClassInstanceCreation object = (ClassInstanceCreation) expression;
                NewExpression newObject = newToNewExpression(object, object.arguments());
                newexpression = newObject;
                newexpression.setTag("InstanceCreation");
            }

        } else if (expression instanceof StringLiteral) {
            newexpression = stringtostring((StringLiteral) expression);

        } else if (expression instanceof NumberLiteral) {
            newexpression = integertointeger((NumberLiteral) expression);

        } else if (expression instanceof BooleanLiteral) {
            BooleanLiteral valor = (BooleanLiteral) expression;
            newexpression = booleantoboolean(valor, valor.booleanValue());

        } else if (expression instanceof NullLiteral)
            newexpression = nulltonull((NullLiteral) expression);

        else if (expression instanceof CharacterLiteral) {
            CharacterLiteral valor = (CharacterLiteral) expression;
            newexpression = chartochar(valor, valor.getEscapedValue().toString());

        } else if (expression instanceof SimpleName) {
            // Cast the expression to a SimpleName JDT object.
            SimpleName identifier = (SimpleName) expression;
            // Map the SimpleName and assign it to the newExpression variable.
            newexpression = SimpleNameToIdentifierReference(identifier);

        } else if (expression instanceof QualifiedName) {
            // Cast the expression to a QualifiedName JDT object.
            QualifiedName qualifiedName = (QualifiedName) expression;
            // Create a QualifiedOverData GAST object, the equivalent for the QualifiedName.
            QualifiedIdentifierReference newQualifiedName = new QualifiedOverData();
            // Set the referenced member by te qualifer.
            newQualifiedName.setMember(SimpleNameToIdentifierReference(qualifiedName.getName()));
            // Set the qualifier.
            newQualifiedName.setQualifiers(ExpresionToExpresion(qualifiedName.getQualifier()));
            // Assign the neeExpression with the new QualifiedOverData object created.
            newexpression = newQualifiedName;
            // Set the tag for the expression.

        } else if (expression instanceof MethodInvocation) {
            if(semantic.isSemantic()) {
                // JNS: method call binding (signature, name, class, package for caller and callee)
                MethodInvocation methodInvocation = (MethodInvocation) expression;
                FunctionCallExpression newMethodInvocation = CalltoCallInvocation(methodInvocation,
                        methodInvocation.arguments());
                IMethodBinding methodBinding = methodInvocation.resolveMethodBinding();
                newexpression = newMethodInvocation;// retorna la llamada con todos los parámetros
                String signature;
                if (methodBinding != null) {
                    String parameters = "";
                    ITypeBinding[] params = methodBinding.getParameterTypes();
                    int nparams = params.length;
                    for (int p = 0; p < nparams; p++) {
                        parameters = parameters.concat(params[p].getName());
                        if (p < nparams-1)
                            parameters = parameters.concat(",");
                    }
                    ITypeBinding declaringClass = methodBinding.getDeclaringClass();
                    String packageName = bindClassPackage(declaringClass, declaringClass.getPackage().toString().replace("package ", ""));
                    // DEGUG
                    if ("BeanShell".equals(declaringClass.getName()) && "runScript".equals(methodInvocation.getName().getIdentifier())) {
                        boolean stopHere = true;
                    }
                    signature = packageName
                            .concat(".")
                            .concat(declaringClass.getName())
                            .concat(".")
                            .concat(methodInvocation.getName().getIdentifier())
                            .concat("(").concat(parameters).concat(")");
                    newMethodInvocation.setSignature(signature);
                    newMethodInvocation.setPackageName(packageName);
                    newMethodInvocation.setClassName(declaringClass.getName());
                    newMethodInvocation.setMethodName(methodInvocation.getName().getIdentifier().concat("(").concat(parameters).concat(")"));
                    newMethodInvocation.setCallerClassName(mappedMethod.getClassName());
                    newMethodInvocation.setCallerPackageName(mappedMethod.getPackageName());
                    newMethodInvocation.setCallerSignature(mappedMethod.getSignature());
                    newMethodInvocation.setCallerMethodName(mappedMethod.getMethodName());
                }
                newexpression.setTag("methodInvocation");
            }
            else {
                MethodInvocation methodInvocation = (MethodInvocation) expression;
                FunctionCallExpression newMethodInvocation = CalltoCallInvocation(methodInvocation, methodInvocation.arguments());
                newexpression = newMethodInvocation;// retorna la llamada con todos los parámetros
            }
        } else if (expression instanceof InfixExpression) {
            InfixExpression condicional = (InfixExpression) expression;
            BinaryExpression newCondicional = new BinaryExpression();
            newCondicional.setLeftOperand(ExpresionToExpresion(condicional.getLeftOperand()));// manda a mapear el
            // operando izquierdo y
            // lo asigna
            newCondicional.setRightOperand(ExpresionToExpresion(condicional.getRightOperand()));
            newCondicional.setOperator(OperatorToOperator(condicional.getOperator().toString()));
            newCondicional.setExtendOperands(expressionListToExpressionList(condicional.extendedOperands()));
            newexpression = newCondicional;

        } else if (expression instanceof ConditionalExpression) {
            ConditionalExpression condicionalExpression = (ConditionalExpression) expression;
            ASTMCore.ASTMSyntax.Expression.ConditionalExpression newCondicionalExpression = new ASTMCore.ASTMSyntax.Expression.ConditionalExpression();
            newCondicionalExpression.setCondition(ExpresionToExpresion(condicionalExpression.getExpression()));
            newCondicionalExpression.setOnTrueOperand(ExpresionToExpresion(condicionalExpression.getThenExpression()));
            newCondicionalExpression.setOnFalseOperand(ExpresionToExpresion(condicionalExpression.getElseExpression()));
            newexpression = newCondicionalExpression;
        } else if (expression instanceof PostfixExpression) {
            PostfixExpression postExpression = (PostfixExpression) expression;
            PostfixUnaryExpression newPostExpression = new PostfixUnaryExpression();
            newPostExpression.setOperator(PostfixOperadorUnaryToPostfixOperadorUnary(postExpression.getOperator().toString()));
            newPostExpression.setOperand(ExpresionToExpresion(postExpression.getOperand()));
            newexpression = newPostExpression;
        } else if (expression instanceof PrefixExpression) {
            PrefixExpression postExpression = (PrefixExpression) expression;
            PrefixUnaryExpression newPostExpression = new PrefixUnaryExpression();
            newPostExpression.setOperator(PrefixOperadorUnaryToPrefixOperadorUnary(postExpression.getOperator().toString()));
            newPostExpression.setOperand(ExpresionToExpresion(postExpression.getOperand()));
            newexpression = newPostExpression;
        } else if (expression instanceof Assignment) {
            Assignment asignacion = (Assignment) expression;
            BinaryExpression newAsignacion = new BinaryExpression();
            newAsignacion.setLeftOperand(ExpresionToExpresion(asignacion.getLeftHandSide()));
            newAsignacion.setRightOperand(ExpresionToExpresion(asignacion.getRightHandSide()));
            newAsignacion.setOperator(OperatorToOperator(asignacion.getOperator().toString()));
            newexpression = newAsignacion;
        } else if (expression instanceof ParenthesizedExpression) {
            ParenthesizedExpression parenthesizedExpression = (ParenthesizedExpression) expression;
            ASTMCore.ASTMSyntax.Expression.ParenthesizedExpression gastParenthesizedExpression = new ASTMCore.ASTMSyntax.Expression.ParenthesizedExpression();
            gastParenthesizedExpression.setInternalExpression(ExpresionToExpresion(parenthesizedExpression.getExpression()));
            newexpression = gastParenthesizedExpression;
        } else if (expression instanceof FieldAccess) {
            FieldAccess accessMember = (FieldAccess) expression;
            MemberAccess newAccessMember = new MemberAccess();
            newAccessMember.setIdentifierName(ExpresionToExpresion(accessMember.getName()));
            newAccessMember.setQualifiedName(ExpresionToExpresion(accessMember.getExpression()));
            newexpression = newAccessMember;
        } else if (expression instanceof org.eclipse.jdt.core.dom.CastExpression) {
            org.eclipse.jdt.core.dom.CastExpression castExpression = (org.eclipse.jdt.core.dom.CastExpression) expression;
            CastExpression newCastExpression = new CastExpression();
            newCastExpression.setCastType(TypeReferencetoType(castExpression.getType()));
            newCastExpression.setExpression(ExpresionToExpresion(castExpression.getExpression()));
            newexpression = newCastExpression;
        } else if (expression instanceof ArrayCreation) { // creation of array
            ArrayCreation array = (ArrayCreation) expression;
            ArrayType arrayType = arrayToArray(array.getType());
            ASTMCore.ASTMSyntax.Expression.ArrayCreation newArray = new ASTMCore.ASTMSyntax.Expression.ArrayCreation();
            newArray.setType(arrayType);
            newArray.setDimension(expressionListToExpressionList(array.dimensions()));
            newexpression = newArray;
        } else if (expression instanceof ArrayAccess) {
            ArrayAccess arrayAcces = (ArrayAccess) expression;
            ASTMCore.ASTMSyntax.Expression.ArrayAccess newArrayAccess = new ASTMCore.ASTMSyntax.Expression.ArrayAccess();
            newArrayAccess.setArrayName(ExpresionToExpresion(arrayAcces.getArray()));
            newArrayAccess.setIndex(ExpresionToExpresion(arrayAcces.getIndex()));
            newexpression = newArrayAccess;
        } else if (expression instanceof SuperMethodInvocation) {
            SuperMethodInvocation invocation = (SuperMethodInvocation) expression;
            ASTMCore.ASTMSyntax.Expression.SuperMethodInvocation newInvocation = new ASTMCore.ASTMSyntax.Expression.SuperMethodInvocation();
            newInvocation.setName(ExpresionToExpresion(invocation.getName()));
            newInvocation.setArguments(expressionListToExpressionList(invocation.arguments()));
            newInvocation.setQualifiedName(ExpresionToExpresion(invocation.getQualifier()));
            newexpression = newInvocation;
        } else if (expression instanceof InstanceofExpression) {
            InstanceofExpression instance = (InstanceofExpression) expression;
            InstanceOfExpression newInstance = new InstanceOfExpression();
            newInstance.setLeftOperand(ExpresionToExpresion(instance.getLeftOperand()));
            newInstance.setRightOperand(TypeReferencetoType(instance.getRightOperand()));
            newexpression = newInstance;
        } else if (expression instanceof SuperFieldAccess) {
            SuperFieldAccess superFieldAccess = (SuperFieldAccess) expression;
            ASTMCore.ASTMSyntax.Expression.SuperMemberAccess gastSuperFiledAccess = new SuperMemberAccess();
            gastSuperFiledAccess.setFieldName(ExpresionToExpresion(superFieldAccess.getName()));
            gastSuperFiledAccess.setQualifiedName(ExpresionToExpresion(superFieldAccess.getQualifier()));
            newexpression = gastSuperFiledAccess;
        } else {
            newexpression = null;
        }
        if(expression != null && newexpression != null){
            newexpression.setLocationInfo(createSourceLocation(expression));
        }
        return newexpression;
    }




    /**
     * This method maps the JDT object ("SimpleName") to the equivalent  GAST object ("IdentifierReference").
     *
     * @param simpleName The SimpleName to be mapped.
     * @return The new IdentifierReference mapped from the SimpleName.
     */
    default IdentifierReference SimpleNameToIdentifierReference(org.eclipse.jdt.core.dom.SimpleName simpleName) {
        IdentifierReference newName = new IdentifierReference();
        newName.setIdentifierName(new Name(simpleName.getIdentifier()));
        newName.setLocationInfo(createSourceLocation(simpleName));
        return newName;
    }


    /**
     * Se encarga de mapear los objetos abstractos de sentencias.
     *
     * @param statement Sentencia de Java que se desea mapear.
     * @return Sentancia del GAST.
     */
    ////////////// Este método realiza el mapeo de los
    ////////////// statements//////////////////////
    default Statement StatementToStatement(org.eclipse.jdt.core.dom.Statement statement) {
        Statement newStatement = null;
        if (statement instanceof Block) {
            Block block = (Block) statement;
            BlockStatement newBlock = BlocktoBlockStatement(block, block.statements());
            newStatement = newBlock;
        } else if (statement instanceof ExpressionStatement) {
            ExpressionStatement expressionStatement = (ExpressionStatement) statement;
            ASTMCore.ASTMSyntax.Statement.ExpressionStatement newExpressionStatement = new ASTMCore.ASTMSyntax.Statement.ExpressionStatement();
            ASTMCore.ASTMSyntax.Expression.Expression newExpresion = ExpresionToExpresion(
                    expressionStatement.getExpression());
            newExpressionStatement.setExpression(newExpresion);
            // NamedTypeReference newType=new NamedTypeReference(new Name("Expression")); //
            // sirve para indicar que tipo de expresión es
            newStatement = newExpressionStatement;
        } else if (statement instanceof IfStatement) {
            IfStatement ifStament = (IfStatement) statement; // obtiene el if
            ASTMCore.ASTMSyntax.Statement.IfStatement newif = new ASTMCore.ASTMSyntax.Statement.IfStatement();
            newif.setThenBody(StatementToStatement(ifStament.getThenStatement())); // manda a mapear el then y lo asigna
            newif.setElseBody(StatementToStatement(ifStament.getElseStatement()));// manda a mapear el else y lo asigna
            newif.setCondition(ExpresionToExpresion(ifStament.getExpression()));// manda a mapear la expresión y lo																	// asigna
            newStatement = newif;

        } else if (statement instanceof WhileStatement) {
            WhileStatement whileStatement = (WhileStatement) statement;
            ASTMCore.ASTMSyntax.Statement.WhileStatement newWhile = new ASTMCore.ASTMSyntax.Statement.WhileStatement();
            newWhile.setBody(StatementToStatement(whileStatement.getBody()));
            newWhile.setCondition(ExpresionToExpresion(whileStatement.getExpression()));
            newStatement = newWhile;
        } else if (statement instanceof VariableDeclarationStatement) {
            JavaCompilatioUniti api = new JavaCompilatioUniti();
            VariableDeclarationStatement variable = (VariableDeclarationStatement) statement;
            VariableDefinition newVariable = new VariableDefinition();
            newVariable.setDefinitionType(TypeReferencetoType(variable.getType()));
            newVariable.setModifiers(api.ListModifierToListModifier(variable.modifiers()));
            newVariable.setFragments(fragmentsToFragments(variable.fragments()));

            DeclarationOrDefinitionStatement definition = new DeclarationOrDefinitionStatement();
            definition.setDeclOrDefn(newVariable);
            newStatement = definition;
        } else if (statement instanceof ReturnStatement) {
            ReturnStatement returnStatement = (ReturnStatement) statement;
            ASTMCore.ASTMSyntax.Statement.ReturnStatement newReturn = new ASTMCore.ASTMSyntax.Statement.ReturnStatement();
            newReturn.setReturnValue(ExpresionToExpresion(returnStatement.getExpression()));
            newStatement = newReturn;
        } else if (statement instanceof ForStatement) {
            ForStatement forStatement = (ForStatement) statement;
            ForCheckAfterStatement newFor = new ForCheckAfterStatement();
            newFor.setBody(StatementToStatement(forStatement.getBody())); // map the for body
            newFor.setCondition(ExpresionToExpresion(forStatement.getExpression()));
            newFor.setInitBody(new ArrayList<ASTMCore.ASTMSyntax.Expression.Expression>(
                    expressionListToExpressionList((forStatement.initializers()))));
            newFor.setIterationBody(new ArrayList<ASTMCore.ASTMSyntax.Expression.Expression>(
                    expressionListToExpressionList(forStatement.updaters())));
            newStatement = newFor;

        } else if (statement instanceof EnhancedForStatement) {
            EnhancedForStatement forStatement = (EnhancedForStatement) statement;
            ForIterator newFor = new ForIterator();
            newFor.setParameter(parameterToParameter(forStatement.getParameter()));
            newFor.setBody(StatementToStatement(forStatement.getBody()));
            newFor.setCondition(ExpresionToExpresion(forStatement.getExpression()));
            newStatement = newFor;

        } else if (statement instanceof org.eclipse.jdt.core.dom.SwitchStatement) {
            org.eclipse.jdt.core.dom.SwitchStatement switchStatement = (org.eclipse.jdt.core.dom.SwitchStatement) statement;
            SwitchStatement newSwitch = new SwitchStatement();// it is the new switch
            JavaCompilatioUniti api = new JavaCompilatioUniti();// uses the api
            newSwitch.setCases(api.ListSwitchToListSwitch(switchStatement.statements()));//
            newSwitch.setSwitchExpression(ExpresionToExpresion(switchStatement.getExpression()));
            newStatement = newSwitch;
        } else if (statement instanceof DoStatement) {
            DoStatement doStatement = (DoStatement) statement;
            DoWhileStatement newDo = new DoWhileStatement();
            newDo.setBody(StatementToStatement(doStatement.getBody()));
            newDo.setCondition(ExpresionToExpresion(doStatement.getExpression()));
            newStatement = newDo;
        } else if (statement instanceof TryStatement) {
            TryStatement tryStatement = (TryStatement) statement;
            ASTMCore.ASTMSyntax.Statement.TryStatement newTry = new ASTMCore.ASTMSyntax.Statement.TryStatement();
            newTry.setBody(StatementToStatement(tryStatement.getBody()));
            newTry.setFinalStatement(StatementToStatement(tryStatement.getFinally()));
            newTry.setCatchBlocks(ListCatchToListCatch(tryStatement.catchClauses()));
            newStatement = newTry;
        } else if (statement instanceof BreakStatement) {
            BreakStatement breakStatament = (BreakStatement) statement;
            ASTMCore.ASTMSyntax.Statement.BreakStatement newBreak = new ASTMCore.ASTMSyntax.Statement.BreakStatement();
            if (breakStatament.getLabel() != null) {
                LabelAccess target = new LabelAccess();
                target.setLabelName(new Name(breakStatament.getLabel().getIdentifier()));
                newBreak.setTarget(target);
            }
            newStatement = newBreak;
        } else if (statement instanceof ContinueStatement) {
            ContinueStatement breakStatament = (ContinueStatement) statement;
            ASTMCore.ASTMSyntax.Statement.ContinueStatement newBreak = new ASTMCore.ASTMSyntax.Statement.ContinueStatement();
            if (breakStatament.getLabel() != null) {
                LabelAccess target = new LabelAccess();
                target.setLabelName(new Name(breakStatament.getLabel().getIdentifier()));
                newBreak.setTarget(target);
            }
            newStatement = newBreak;
        } else if (statement instanceof AssertStatement) {
            AssertStatement assertStatement = (AssertStatement) statement;
            ASTMCore.ASTMSyntax.Statement.AssertStatement newAssert = new ASTMCore.ASTMSyntax.Statement.AssertStatement();
            newAssert.setExpression(ExpresionToExpresion(assertStatement.getExpression()));
            newAssert.setMessage(ExpresionToExpresion(assertStatement.getMessage()));
            newStatement = newAssert;
        } else if (statement instanceof SynchronizedStatement) {
            SynchronizedStatement syncstatement = (SynchronizedStatement) statement;
            ASTMCore.ASTMSyntax.Statement.SynchronizedStatement newSync = new ASTMCore.ASTMSyntax.Statement.SynchronizedStatement();
            newSync.setBody(StatementToStatement(syncstatement.getBody()));
            newSync.setExpression(ExpresionToExpresion(syncstatement.getExpression()));
            newStatement = newSync;
        } else if (statement instanceof SuperConstructorInvocation) {
            SuperConstructorInvocation invocation = (SuperConstructorInvocation) statement;
            SuperInvocation newInvocation = new SuperInvocation();
            newInvocation.setArguments(expressionListToExpressionList(invocation.arguments()));
            newInvocation.setExpression(ExpresionToExpresion(invocation.getExpression()));
            // newInvocation.setArgumentsTypes(ListTypeReferencetoListType(invocation.typeArguments()));//
            newStatement = newInvocation;
        } else if (statement instanceof ThrowStatement) {
            ThrowStatement throwStatement = (ThrowStatement) statement;
            ASTMCore.ASTMSyntax.Statement.ThrowStatement newThrow = new ASTMCore.ASTMSyntax.Statement.ThrowStatement();
            newThrow.setException(ExpresionToExpresion(throwStatement.getExpression()));
            newStatement = newThrow;
        } else if (statement instanceof LabeledStatement) {
            LabeledStatement label = (LabeledStatement) statement;
            ASTMCore.ASTMSyntax.Statement.LabeledStatement newLabel = new ASTMCore.ASTMSyntax.Statement.LabeledStatement();
            LabelDefinition target = new LabelDefinition();
            target.setLabelname(new Name(label.getLabel().getIdentifier()));
            newLabel.setLabel(target);
            newLabel.setStatement(StatementToStatement(label.getBody()));
            newStatement = newLabel;
        }
        if(statement != null)
        {
            newStatement.setLocationInfo(createSourceLocation(statement));
        }
        return newStatement;
    }

    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * This method is for mapping the catch clauses
     *
     * @param catchStatement The catch clause
     * @return The catch cluse mapped into GAST
     */
    default CatchBlock CatchToCatch(CatchClause catchStatement) {
        CatchBlock newCatch = new CatchBlock();
        newCatch.setBody(StatementToStatement(catchStatement.getBody()));
        newCatch.setException(parameterToParameter(catchStatement.getException()));
        newCatch.setLocationInfo(createSourceLocation(catchStatement));
        return newCatch;
    }

    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * Realiza la función de mapear el tipo de referencia del AST de Java.
     *
     * @param tipo El tipo de objeto Java para el GAST.
     * @return El tipo de referencia del GAST.
     */
    default TypeReference TypeReferencetoType(Type tipo) {
        NamedTypeReference newType;
        if (tipo instanceof SimpleType) {
            SimpleType name = (SimpleType) tipo;
            newType = new NamedTypeReference(new Name(name.getName().getFullyQualifiedName()));
        } else if (tipo instanceof PrimitiveType) {
            //TODO ESTO LO PUEDO CAMBIAR PARA MAPEAR LOS PRIMITIVIOS.
            PrimitiveType name = (PrimitiveType) tipo;
            newType = new NamedTypeReference(new Name(name.getPrimitiveTypeCode().toString()));
        } else if (tipo instanceof org.eclipse.jdt.core.dom.ArrayType) {
            org.eclipse.jdt.core.dom.ArrayType name = (org.eclipse.jdt.core.dom.ArrayType) tipo;
            newType = new NamedTypeReference(new Name("Array"));
            ArrayType array = arrayToArray(name);// pasa como parámetro el array
            newType.setDataType(array);// asigna el vector
        } else if (tipo instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) tipo;
            ASTMCore.ASTMSyntax.Types.ParameterizedType newParameterizeType = new ASTMCore.ASTMSyntax.Types.ParameterizedType();
            newParameterizeType.setArguments(ListTypeReferencetoListType(((ParameterizedType) tipo).typeArguments()));
            newParameterizeType.setType(TypeReferencetoType(parameterizedType.getType()));
            newType = new NamedTypeReference(new Name("Parameterized"));
            newType.setDataType(newParameterizeType);
        } else {
            return null;
        }
        if(tipo != null) {
            newType.setLocationInfo(createSourceLocation(tipo));
        }
        return newType;

    }

    /**
     * Este método se encarga de mapear los parámetros de un método.
     *
     * @param parameter Parámetro (Objeto de JDT que describe un parámetro).
     * @return Parametro del GAST (Objeto ActualParameter del GAST).
     */
    ///////////////////////// Este método es para los parametros de una expresion
    ///////////////////////// new/////////
    default ActualParameter ParametrestoParametres(Expression parameter) {
        if (parameter != null) {
            ActualParameterExpression newParametre = new ActualParameterExpression();
            newParametre.setValue(ExpresionToExpresion(parameter));
            newParametre.setLocationInfo(createSourceLocation(parameter));
            return newParametre;
        } else
            return null;
    }

    /**
     * Estas variables mantienen informacion sobre la instancia del método actual mapeado
     */
    // JNS: method instance currently being mapped
    MappedMethod mappedMethod = new MappedMethod();

    /**
     * Se encarga de decidir el subtipo de declaración que es el objeto.
     *
     * @param element Declaracion de un elemento en Java (Objeto de JDT).
     * @return El tipo de declaración correspondiente al elemento de entrada.
     */
    ///////// Este método es para el cuerpo de la clas
    ///////// (métodos)////////////////////////////
    default DefintionObject bodyClassToBodyClass(BodyDeclaration element) {
        DefintionObject newElement = null;
        if (element instanceof MethodDeclaration) {
            // JNS: Metrics and binding for declared method
            if (semantic.isSemantic()){
                MethodDeclaration method = (MethodDeclaration) element;
                FunctionDefintion newMethod = new FunctionDefintion();

                // JNS: Metrics and binding for declared method

                Metrics metrics = new Metrics();
                metrics.setLines_of_code(JavaNumberOfLines.INSTANCE.getNumberOfLines(method));
                metrics.setCyclomatic_complexity(JavaCyclomaticComplexity.INSTANCE.getCyclomaticComplexity(method));
                metrics.setHalstead(JavaHalstead.INSTANCE.getHalstead(method));

                String parameters = "";
                List params = method.parameters();
                int nparams = params.size();
                for (int p = 0; p < nparams; p++) {
                    parameters = parameters.concat(((SingleVariableDeclaration)params.get(p)).getType().toString());
                    if (p < nparams-1)
                        parameters = parameters.concat(",");
                }
                IMethodBinding methodBinding = method.resolveBinding();
                ITypeBinding declaringClass = methodBinding.getDeclaringClass();
                String packageName = bindClassPackage(declaringClass, declaringClass.getPackage().toString().replace("package ", ""));
                String signature = packageName
                        .concat(".")
                        .concat(declaringClass.getName())
                        .concat(".")
                        .concat(method.getName().getIdentifier())
                        .concat("(").concat(parameters).concat(")");
                String methodName = method.getName().getIdentifier().concat("(").concat(parameters).concat(")");
                //System.out.println(signature);

                mappedMethod.setClassName(declaringClass.getName());
                mappedMethod.setPackageName(packageName);
                mappedMethod.setSignature(signature);
                mappedMethod.setMethodName(methodName);

                newMethod = methodToMethod(method.getName(), method.getBody(), method.modifiers(), method.parameters(),
                        method.getReturnType2(), methodName, signature, declaringClass.getName(), packageName, metrics);
                // JNS: upgrading to JLS14
                //newMethod.setException(ListTypeReferencetoListType(method.thrownExceptionTypes()));
                newMethod.setException(expressionListToExpressionList(method.thrownExceptions()));
                newMethod.setTag("method");
                newElement = newMethod;

                mappedMethod.setClassName(null);
                mappedMethod.setPackageName(null);
                mappedMethod.setSignature(null);
                mappedMethod.setMethodName(null);
            }else{
                MethodDeclaration method = (MethodDeclaration) element;
                FunctionDefintion newMethod = new FunctionDefintion();

                newMethod = methodToMethod(method.getName(), method.getBody(), method.modifiers(), method.parameters(),
                        method.getReturnType2());
                // JNS: upgrading to JLS14
                newMethod.setException(ListTypeReferencetoListType(method.thrownExceptionTypes()));
                //newMethod.setException(expressionListToExpressionList(method.thrownExceptions()));
                newMethod.setTag("method");
                newElement = newMethod;
            }

        }
        // else if (element instanceof EnumDeclaration) {
        // System.out.println("entrando al metodo de body Declaration");
        // }
        else if (element instanceof EnumConstantDeclaration) {
            EnumConstantDeclaration constant = (EnumConstantDeclaration) element;
            EnumLiteralDefinition newConstant = new EnumLiteralDefinition();
            newConstant.setModifiers(ListModifiersToListModiers(constant.modifiers()));
            newConstant.setArguments(expressionListToExpressionList(constant.arguments()));
            newConstant.setIdentifier(new Name(constant.getName().getIdentifier()));
            newElement = newConstant;
        } else if (element instanceof TypeDeclaration) { // class into class
            newElement = classtoclass((TypeDeclaration) element);
        } else if (element instanceof EnumDeclaration) {
            newElement = classtoclass((EnumDeclaration) element);
        } //else if (element instanceof AnnotationTypeDeclaration) {

        //}
        if(element != null && newElement != null){
            newElement.setLocationInfo(createSourceLocation(element));
        }
        return newElement;
    }

    ///////////////////////////////////////////////////////////////////////////////////////
    /////////////////// Método para mapear los parametros de una clase/////////////////////
    default ASTMCore.ASTMSyntax.Types.TypeParameter ClassParameterToClassParameter(TypeParameter parameter) {
        ASTMCore.ASTMSyntax.Types.TypeParameter newParameter = new ASTMCore.ASTMSyntax.Types.TypeParameter();
        newParameter.setIdentifier(new Name(parameter.getName().getIdentifier()));
        newParameter.setProperty(ListTypeReferencetoListType(parameter.typeBounds()));
        newParameter.setLocationInfo(createSourceLocation(parameter));
        return newParameter;
    }

    //////////////////////////////////////////////////////////////////////////////////////

    /**
     * Mapea los pares de valores de las anotaciones en un método
     *
     * @param value el par ordenado con los valores
     * @return El par ordenado mapeado.
     */
    @Mappings({@Mapping(source = "name", target = "name"),
              @Mapping(source = "value", target = "value"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(value))")})
    MemberValuePair ValuePairToValuePair(org.eclipse.jdt.core.dom.MemberValuePair value);

    ///////////////////////////////////////////////////////////////////////////////////////

    /**
     * Mapea las invocaciones de los métodos.
     *
     * @param invocation La llamada al método.
     * @param parameters Lista de parámetros de la llamada al método.
     * @return Expresión de la llamada a un método en el GAST.
     */
    ///////////// Método que mapea las invocaciones de
    ///////////// funciones///////////////////////////////////////////////
    @Mappings({@Mapping(source = "invocation.name", target = "functionName"),
            @Mapping(source = "invocation.expression", target = "calledFunction"),
            @Mapping(source = "parameters", target = "actualParams"),
            @Mapping(constant = "call", target = "tag"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(invocation))")})
    FunctionCallExpression CalltoCallInvocation(MethodInvocation invocation, List<Expression> parameters);

    ////////////////////////// Este método realiza el mapeo de new /////////////////

    /**
     * Mapea la expresión de una instancia de una clase (new).
     *
     * @param expression La expresión del new.
     * @param arguments  Los atributos del constructor.
     * @return La expresión de new del GAST.
     */
    @Mappings({@Mapping(source = "expression.type", target = "newType"),
            @Mapping(source = "arguments", target = "actualParams"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(expression))")})
    NewExpression newToNewExpression(ClassInstanceCreation expression, List<Expression> arguments);

    ////////////////////////// Método que Mapea los
    ////////////////////////// vectores////////////////////////////

    /**
     * Mapea el tipo de dato llamado arreglo.
     *
     * @param array Vector que se desea mapear.
     * @return Objeto que representa un arreglo en el GAST.
     */
    @Mappings({@Mapping(source = "elementType", target = "baseType"),
            @Mapping(source = "dimensions", target = "amountDimension"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(array))")})
    ArrayType arrayToArray(org.eclipse.jdt.core.dom.ArrayType array);

    //////////////////////////////////////////////////////////////////////////////////

    /**
     * Se encarga de mapear los identificadores de los elementos del código.
     *
     * @param name Nombre del elemento (identificador).
     * @return Objeto que representa el nombre del elemento en el GAST.
     */
    // este mapeo es para el nombre, uno de los elementos más básicos
    @Mapping(target = "ofTypeReference", ignore = true)
    @Mapping(target = "annotations", ignore = true)
    @Mapping(target = "preProcessorElements", ignore = true)
    @Mapping(source = "name.fullyQualifiedName", target = "nameString")
    @Mapping(target = "locationInfo", expression = "java(createSourceLocation(name))")
    Name NameToName(org.eclipse.jdt.core.dom.Name name);

    /////////////////////////////////////////////////////////////////////////////////
    ///////////// Mapeo del objeto package//////////////////////////////////////////

    /**
     * Se encarga de mapear la sentecia de paquete.
     *
     * @param namespace Nombre del paquete.
     * @return Objeto que representa el paquete en el GAST
     */
    @Mappings({@Mapping(source = "name", target = "nameSpace"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(namespace))")})
    NameSpaceDefinition packagetopackage(PackageDeclaration namespace);
    ////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////// Mapeo de la declaración de importación//

    /**
     * Se encarga de mapear las sentencias de importaciones de archivos y
     * bibliotecas.
     *
     * @param imports Lista de importaciones de la clase.
     * @return Lista de importaciones representadas por un objeto del GAST.
     */
    @Mappings({@Mapping(source = "name", target = "identifierName"),
            @Mapping(source = "static", target = "static"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(imports))")})
    ASTMCore.ASTMSyntax.DeclarationAndDefinition.ImportDeclaration importtoimport(ImportDeclaration imports);

    ///////////////////////////// Mapeo de las definiciones de
    ///////////////////////////// variables/////////////////

    /**
     * Se encarga de mapear las definiciones de variables.
     *
     * @param variable La variable que se desea mapear.
     * @return Objeto que representa la variable y tipo de un elemento en el GAST.
     */
    @Mappings({@Mapping(source = "variable.name", target = "identifierName"),
            @Mapping(source = "variable.initializer", target = "initialValue"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(variable))")})
    Fragment variabletovariable(VariableDeclarationFragment variable);

    ////////////////////////////// Mapeo de los parámetros de un
    ////////////////////////////// método//////////////////

    //SPP: Change made because the parameterToParameter below take errors
    //     with modifiers even if they are not used.
    @Mappings({@Mapping(source = "name", target = "identifierName"),
            @Mapping(source = "type", target = "definitionType"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(variable))")})
    default FormalParameterDefinition parameterToParameter(SingleVariableDeclaration variable) {
        FormalParameterDefinition newFormalParam = new FormalParameterDefinition();
        newFormalParam.setIdentifierName(NameToName(variable.getName()));
        newFormalParam.setDefinitionType(TypeReferencetoType(variable.getType()));
        return newFormalParam;
    }

    /*This throw errors with modifiers*/
    /*@Mappings({@Mapping(source = "name", target = "identifierName"),
            @Mapping(source = "type", target = "definitionType")})
    FormalParameterDefinition parameterToParameter(SingleVariableDeclaration variable);*/

    /////////////////////////////// Mapeo de los
    /////////////////////////////// atributos(Member)///////////////////////

    /**
     * Se encarga de mapear los atributos de una clase.
     *
     * @param value     Valor inicial del atributo.
     * @param modifiers Modificadores del atributo.
     * @return Atributo del GAST.
     */
    @Mappings({@Mapping(target = "offset", ignore = true),
            @Mapping(target = "annotations", ignore = true), @Mapping(target = "preProcessorElements", ignore = true),
            @Mapping(source = "modifiers", target = "modifiers"), @Mapping(source = "value", target = "member"),
            //TODO: Error here
            //@Mapping(target = "locationInfo", expression = "java(createSourceLocation(value))")
    })
    MemberObject fieldDeclarationtoMemberObject(VariableDefinition value, List<IExtendedModifier> modifiers);

    /////////////////////////////// Mapeos de los métdos de una
    /////////////////////////////// clase//////////////////////////////////

    /**
     * Se encarga de mapear los métodos de una clase.
     *
     * @param name        Nombre del método.
     * @param bloque      Alcance del método (cuerpo).
     * @param modifiers   Modificadores del método.
     * @param parameters  Parámetros del método.
     * @param type        Tipo del método, representa el tipo de retorno
     * @param signature   Firma del método
     * @param className   Clase del método
     * @param packageName Paquete del método
     * @param metrics     Metricas basicas del metodo
     * @return Método del GAST.
     */
    // JNS: method binding and metrics added
    @Mappings({@Mapping(source = "type", target = "returnType"), @Mapping(source = "modifiers", target = "modifiers"),
            @Mapping(target = "locationInfo", ignore = true), @Mapping(target = "accessKind", ignore = true),
            @Mapping(target = "linkageSpecifier", ignore = true), @Mapping(target = "storageSpecifier", ignore = true),
            @Mapping(target = "definitionType", ignore = true), @Mapping(source = "name", target = "identifierName"),
            @Mapping(target = "ofDeclaration", ignore = true),
            @Mapping(source = "parameters", target = "formalParameters"),
            @Mapping(target = "functionMemberAttributes", ignore = true),
            @Mapping(target = "opensScope", ignore = true), @Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true), @Mapping(source = "bloque", target = "body")})
    FunctionDefintion methodToMethod(SimpleName name, Block bloque, List<IExtendedModifier> modifiers,
                                     List<SingleVariableDeclaration> parameters, Type type, String methodName,
                                     String signature, String className, String packageName, Metrics metrics);

    /**
     * Se encarga de mapear los métodos de una clase.
     *
     * @param name       Nombre del método.
     * @param bloque     Alcance del método (cuerpo).
     * @param modifiers  Modificadores del método.
     * @param parameters Parámetros del método.
     * @param type       Tipo del método, representa el tipo de retorno
     * @return Método del GAST.
     */
    @Mappings({@Mapping(source = "type", target = "returnType"), @Mapping(source = "modifiers", target = "modifiers"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(bloque))"),
            @Mapping(target = "accessKind", ignore = true),
            @Mapping(target = "linkageSpecifier", ignore = true), @Mapping(target = "storageSpecifier", ignore = true),
            @Mapping(target = "definitionType", ignore = true), @Mapping(source = "name", target = "identifierName"),
            @Mapping(target = "ofDeclaration", ignore = true),
            @Mapping(source = "parameters", target = "formalParameters"),
            @Mapping(target = "functionMemberAttributes", ignore = true),
            @Mapping(target = "opensScope", ignore = true), @Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true), @Mapping(source = "bloque", target = "body"),
            @Mapping(constant = "annotation", target = "tag")})
    FunctionDefintion methodToMethod(SimpleName name, Block bloque, List<IExtendedModifier> modifiers,
                                     List<SingleVariableDeclaration> parameters, Type type);

    ////////////////////////////////// Mapeos de implements
    ////////////////////////////////// to/////////////////////////////////////////

    /**
     * Se encarga de mapear la característica de interface de una clase.
     *
     * @param clase Clase con la característica de si es interface o no.
     * @return Característica con el valor que indica si la clase es abstracta o no
     * .
     */
    @Mappings({@Mapping(source = "clase", target = "implementsTo"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(clase))")})
    // de alguna forma hay que meter typereference
    ImplementsTo superinterfacetoimplementsto(Type clase);
    /////////////////// Mapeo del bloque de un
    /////////////////// bloque/////////////////////////////////////////

    /**
     * Se encarga de mapear un bloque de código con instrucciones.
     *
     * @param block      Representa el bloque donde se encuentra el conjunto de
     *                   instrucciones.
     * @param statements Representa las instrucciones que se encuentran en el
     *                   bloque.
     * @return Bloque de código del GAST.
     */
    @Mappings({@Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true), @Mapping(target = "opensScope", ignore = true),
            @Mapping(source = "statements", target = "subStatements"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(block))")})
    BlockStatement BlocktoBlockStatement(Block block, List<org.eclipse.jdt.core.dom.Statement> statements);

    /////////////////////////// Mapeo de cada uno de los atributos de la clase/////

    /**
     * Este método se encarga de distribuir los mapeos individuales de cada elemento
     * que posee la clase de Java.
     *
     * @param clase           Objeto que contiene todos los elementos de la clase.
     * @param modificadores   lista de modificadores de la clase.
     * @param body            Las declaraciones dentro de la clase.
     * @param inherency       Los datos de herencia, en caso de que posea.
     * @param implementations Datos de las implementaciones.
     * @param members         Atributos de la clase.
     * @return El objeto que representa una clase en el GAST.
     */
    @Mappings({@Mapping(source = "body", target = "opensScope.declOrDefn"),
            @Mapping(source = "clase.name", target = "nameString"),
            @Mapping(source = "implementations", target = "implementesTo"),
            @Mapping(source = "modificadores", target = "modifiers"),
            @Mapping(target = "isConst", ignore = true), @Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true), @Mapping(source = "members", target = "members"),
            @Mapping(source = "inherency", target = "derivesFrom"),
            @Mapping(source = "parameters", target = "parameters"),
            @Mapping(source = "clase.interface", target = "interface"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(clase))")})
    ClassType MappingClass(TypeDeclaration clase, List<IExtendedModifier> modificadores, List<BodyDeclaration> body,
                           List<DerivesFrom> inherency, List<Type> implementations, List<MemberObject> members,
                           List<TypeParameter> parameters);

    ////////////// Mapeo de clase a clase con nombre y
    ////////////// clase//////////////////////////////////////////////

    /**
     * Se encarga de mapear los elementos de una clase.
     *
     * @param clase Clase que se desea mapear.
     * @return Clase con los datos representados por medio de objetos del GAST.
     */
    @Mappings({@Mapping(target = "annotations", ignore = true),
            @Mapping(target = "preProcessorElements", ignore = true), @Mapping(target = "typeName", ignore = true),
            @Mapping(constant = "Class", target = "tag"),
            @Mapping(source = "clase", target = "aggregateType"),
            @Mapping(target = "locationInfo", expression = "java(createSourceLocation(clase))")})
    AggregateTypeDefinition TypeDeclarationtoClassType(TypeDeclaration clase);

    //////////////////////////// Mapeo de los
    //////////////////////////// enumerados/////////////////////////////////////////////////////////
    @Mappings({@Mapping(source = "name", target = "definitionType.identifier"),
            @Mapping(source = "implementsTo", target = "definitionType.implementesTo"),
            @Mapping(source = "modifiers", target = "definitionType.modifiers"),
            @Mapping(source = "body", target = "definitionType.opensScope.declOrDefn")
            //TODO Error here
            //@Mapping(target = "locationInfo", expression = "java(createSourceLocation(name))")
    })
    EnumTypeDefinition TypeDeclarationToEnumTypeDefinition(String name, List<IExtendedModifier> modifiers,
                                                           List<BodyDeclaration> body, List<Type> implementsTo);

    //////////////////////////// Mapeo de los
    //////////////////////////// enumerados/////////////////////////////////////////////////////////
    @Mappings({@Mapping(source = "name", target = "name"), @Mapping(source = "modifiers", target = "modifiers"),
            @Mapping(source = "body", target = "opensScope.declOrDefn")
            //TODO: Error here
            //@Mapping(target = "locationInfo", expression = "java(createSourceLocation(name))")
    })
    AnnotationType TypeDeclarationToAnnotatioDeclaration(Name name, List<IExtendedModifier> modifiers,
                                                         List<BodyDeclaration> body);
    ////////////////////////// Mapeo de las
    ////////////////////////// anotaciones///////////////////////////////////////////////////////
	/*@Mappings({ @Mapping(target = "locationInfo", ignore = true), @Mapping(target = "annotations", ignore = true),
			@Mapping(target = "preProcessorElements", ignore = true),
			// @Mapping(source="name" , target="typeName"),
			@Mapping(constant = "Annotation", target = "typeName.nameString"),
			@Mapping(source = "annotation", target = "aggregateType") })
	AggregateTypeDefinition TypeDeclarationtoClassType(AnnotationType annotation);
	 */
    /////////////////////// Mapeo de expresion
    /////////////////////// individual/////////////////////////////////////
    // ASTMCore.ASTMSyntax.Expression.Expression expressionToExpression(Expression
    /////////////////////// expression);
    ////////////// Mapeo de la lista de
    /////////////////////// imports//////////////////////////////////////

    List<ASTMCore.ASTMSyntax.DeclarationAndDefinition.ImportDeclaration> ListimportstoListimports(
            List<ImportDeclaration> imports);

    ///////////////////////////////////////////////////////////////////////////////
    ///////////////// Mapeo de la lista de tipos de declaraciones a la de openscope
    List<DefintionObject> tipostotipos(List<AbstractTypeDeclaration> tipos);

    ///////////////////////////////////////////////////////////////////////////////
    List<ImplementsTo> implementstoimplementsList(List<Type> implementation);

    //////////////////////////////////////////////////////////////////////////////
    List<ASTMCore.ASTMSyntax.Expression.Expression> expressionListToExpressionList(List<Expression> list);

    // JNS: Compiler and JLS upgrade
    //////////////////////////////////////////////////////////////////////////////
    //List<ASTMCore.ASTMSyntax.Types.ExceptionType> exceptionListToExceptionList(List<SimpleType> list);
    //
    //default ASTMCore.ASTMSyntax.Types.ExceptionType exceptionToException(SimpleType type) {
    //    ExceptionType excType = new ExceptionType();
    //    excType.
    //}

    ///////////////////////////////// Mapea la lista de elementos en el cuerpo de la
    ///////////////////////////////// clase/////////////////////////////////////////////
    List<DefintionObject> BodyToBody(List<BodyDeclaration> body);

    ////////////////////// Mapea la lista de los parámetros de los
    ////////////////////// métodos////////////////////////////////////////////////////////
    List<FormalParameterDefinition> listToListParameters(List<SingleVariableDeclaration> parameters);

    ///////////////////// Mapea la lista de
    ///////////////////// Statements//////////////////////////////////
    ArrayList<Statement> listToListStatement(List<org.eclipse.jdt.core.dom.Statement> statements);

    ///////////////////// Mapea la lista de fragmentos de un conjunto de
    ///////////////////// variables///////////////
    List<Fragment> fragmentsToFragments(List<VariableDeclarationFragment> fragments);

    // Mapea los catches de una sentencia try
    List<CatchBlock> ListCatchToListCatch(List<CatchClause> catches);

    // Mapea lista de tipos a lista de tipos
    List<TypeReference> ListTypeReferencetoListType(List<Type> tipo);

    // Mapea lista de modificadores
    List<Modifiers> ListModifiersToListModiers(List<IExtendedModifier> modifiers);

    // Mapea lista de pares de valores de las anotaciones
    List<MemberValuePair> ListValuePairToListValuePair(List<org.eclipse.jdt.core.dom.MemberValuePair> value);
}