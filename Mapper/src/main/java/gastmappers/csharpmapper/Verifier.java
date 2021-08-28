package gastmappers.csharpmapper;


import gastmappers.csharpmapper.ANTLR.CSharpParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import java.util.HashMap;
import java.util.Map;

public class Verifier {
    private static Verifier instance;
    private HashMap<Object, NodeVerifier> list = new HashMap<>();

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private Verifier() { }

    /* Static 'instance' method */
    public static Verifier getInstance( ) {
        if(instance == null){
            instance = new Verifier();
        }
        return instance;
    }

    public <T> void add(T source) {
        String description = null;
        if(source instanceof ParseTree) {
            ParseTree node = (ParseTree) source;
            if(node instanceof CSharpParser.Using_directiveContext) {
                CSharpParser.Using_directiveContext context = (CSharpParser.Using_directiveContext) node;
                description = "Import: " + context.getText().substring(5);
            }else if(node instanceof CSharpParser.Type_declarationContext){
                CSharpParser.Type_declarationContext context = (CSharpParser.Type_declarationContext) node;
                if(context.class_definition() != null) {
                    description = "Clase: " + context.class_definition().identifier().getText();
                }else if(context.enum_definition() != null){
                    description = "Enum: " + context.enum_definition().identifier().getText();
                }
            }else if(node instanceof CSharpParser.Method_declarationContext){
                CSharpParser.Method_declarationContext context = (CSharpParser.Method_declarationContext) node;
                description = "Metodo: " + context.method_member_name().identifier(0).getText();
            }else if(node instanceof CSharpParser.StatementContext){
                if(!(node instanceof CSharpParser.Embedded_statementContext)) {
                    description = "Statement: " + node.getClass().getSimpleName().replace("StatementContext", "");
                }
            }else if(node instanceof CSharpParser.Embedded_statementContext && ((CSharpParser.Embedded_statementContext) node).simple_embedded_statement() != null){
                description = "Statement: " + ((CSharpParser.Embedded_statementContext) node).simple_embedded_statement().getClass().getSimpleName().replace("StatementContext", "");
            }
            else if(node instanceof CSharpParser.ExpressionContext){
                CSharpParser.ExpressionContext context = (CSharpParser.ExpressionContext) node;
                if(context.assignment() != null){
                    description = "Expression: Assignment";
                }else if(context.non_assignment_expression() != null){
                    description = "Expression: NonAssignment";
                }
            }else if(node instanceof CSharpParser.Primary_expression_startContext){
                description = "Expression start: " + node.getClass().getSimpleName().replace("ExpressionContext", "");
            }
            if(description != null) {
                list.put(source, new NodeVerifier(description));
            }
        }
    }

    public <T1, T2> void check(T1 source, T2 target){
        if(list.containsKey(source)) {
            NodeVerifier checking = list.get(source);
            checking.checked = true;
            if(target != null) {
                checking.description += " --> " + target.getClass().getSimpleName();
            }else {
                checking.description += " --> null.";
            }
        }
    }

    public String getAnalysis(boolean showAll){
        //System.out.println("Size: " + list.size());
        String analysis = "";
        for (Map.Entry<Object, NodeVerifier> node : list.entrySet()) {
            if(!node.getValue().checked){
                int line = -1;
                String parent = null;
                if(node.getKey() instanceof ParserRuleContext){
                    line = ((ParserRuleContext) node.getKey()).getStart().getLine();
                    parent = ((ParserRuleContext) node.getKey()).getParent().getClass().getSimpleName();
                }
                analysis += "Valor no mapeado (linea " + line + "): " + node.getValue().description + " hijo de " + parent + "\n";
            }else if(showAll) {
                int line = -1;
                if(node.getKey() instanceof ParserRuleContext){
                    line = ((ParserRuleContext) node.getKey()).getStart().getLine();
                }
                analysis += "Valor mapeado: " + node.getValue().description + " en la linea " + line + "\n";
            }
        }
        return analysis;
    }
}
