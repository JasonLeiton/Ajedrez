package gastmetrics;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import java.util.*;

public class JavaHalstead extends Halstead {
    public static Halstead INSTANCE = new JavaHalstead();

    public final int[] Operands = new int[] {
            ITerminalSymbols.TokenNameCharacterLiteral,
            ITerminalSymbols.TokenNameDoubleLiteral,
            ITerminalSymbols.TokenNameFloatingPointLiteral,
            ITerminalSymbols.TokenNameIdentifier,
            ITerminalSymbols.TokenNameIntegerLiteral,
            ITerminalSymbols.TokenNameLongLiteral,
            ITerminalSymbols.TokenNameStringLiteral,
            ITerminalSymbols.TokenNameassert,
            ITerminalSymbols.TokenNameboolean,
            ITerminalSymbols.TokenNamebreak,
            ITerminalSymbols.TokenNamebyte,
            ITerminalSymbols.TokenNamecase,
            ITerminalSymbols.TokenNamecatch,
            ITerminalSymbols.TokenNamechar,
            ITerminalSymbols.TokenNameclass,
            ITerminalSymbols.TokenNameconst,
            ITerminalSymbols.TokenNamecontinue,
            ITerminalSymbols.TokenNamedefault,
            ITerminalSymbols.TokenNamedo,
            ITerminalSymbols.TokenNamedouble,
            ITerminalSymbols.TokenNameelse,
            ITerminalSymbols.TokenNameenum,
            ITerminalSymbols.TokenNameextends,
            ITerminalSymbols.TokenNamefalse,
            ITerminalSymbols.TokenNamefinal,
            ITerminalSymbols.TokenNamefinally,
            ITerminalSymbols.TokenNamefloat,
            ITerminalSymbols.TokenNamefor,
            ITerminalSymbols.TokenNamegoto,
            ITerminalSymbols.TokenNameif,
            ITerminalSymbols.TokenNameimplements,
            ITerminalSymbols.TokenNameimport,
            ITerminalSymbols.TokenNameinstanceof,
            ITerminalSymbols.TokenNameint,
            ITerminalSymbols.TokenNameinterface,
            ITerminalSymbols.TokenNamelong,
            ITerminalSymbols.TokenNamenative,
            ITerminalSymbols.TokenNamenew,
            ITerminalSymbols.TokenNamenull,
            ITerminalSymbols.TokenNamepackage,
            ITerminalSymbols.TokenNameprivate,
            ITerminalSymbols.TokenNameprotected,
            ITerminalSymbols.TokenNamepublic,
            ITerminalSymbols.TokenNamereturn,
            ITerminalSymbols.TokenNameshort,
            ITerminalSymbols.TokenNamestatic,
            ITerminalSymbols.TokenNamestrictfp,
            ITerminalSymbols.TokenNamesuper,
            ITerminalSymbols.TokenNameswitch,
            ITerminalSymbols.TokenNamesynchronized,
            ITerminalSymbols.TokenNamethis,
            ITerminalSymbols.TokenNamethrow,
            ITerminalSymbols.TokenNamethrows,
            ITerminalSymbols.TokenNametransient,
            ITerminalSymbols.TokenNametrue,
            ITerminalSymbols.TokenNametry,
            ITerminalSymbols.TokenNamevoid,
            ITerminalSymbols.TokenNamevolatile,
            ITerminalSymbols.TokenNamewhile
    };
    public final int[] Operators = new int[] {
            ITerminalSymbols.TokenNameAND,
            ITerminalSymbols.TokenNameAND_AND,
            ITerminalSymbols.TokenNameAND_EQUAL,
            ITerminalSymbols.TokenNameAT,
            ITerminalSymbols.TokenNameCOLON,
            ITerminalSymbols.TokenNameCOMMA,
            ITerminalSymbols.TokenNameDIVIDE,
            ITerminalSymbols.TokenNameDIVIDE_EQUAL,
            ITerminalSymbols.TokenNameDOT,
            ITerminalSymbols.TokenNameELLIPSIS,
            ITerminalSymbols.TokenNameEQUAL,
            ITerminalSymbols.TokenNameEQUAL_EQUAL,
            ITerminalSymbols.TokenNameERROR,
            ITerminalSymbols.TokenNameGREATER,
            ITerminalSymbols.TokenNameGREATER_EQUAL,
            ITerminalSymbols.TokenNameLBRACE,
            ITerminalSymbols.TokenNameLBRACKET,
            ITerminalSymbols.TokenNameLEFT_SHIFT,
            ITerminalSymbols.TokenNameLEFT_SHIFT_EQUAL,
            ITerminalSymbols.TokenNameLESS,
            ITerminalSymbols.TokenNameLESS_EQUAL,
            ITerminalSymbols.TokenNameLPAREN,
            ITerminalSymbols.TokenNameMINUS,
            ITerminalSymbols.TokenNameMINUS_EQUAL,
            ITerminalSymbols.TokenNameMINUS_MINUS,
            ITerminalSymbols.TokenNameMULTIPLY,
            ITerminalSymbols.TokenNameMULTIPLY_EQUAL,
            ITerminalSymbols.TokenNameNOT,
            ITerminalSymbols.TokenNameNOT_EQUAL,
            ITerminalSymbols.TokenNameOR,
            ITerminalSymbols.TokenNameOR_EQUAL,
            ITerminalSymbols.TokenNameOR_OR,
            ITerminalSymbols.TokenNamePLUS,
            ITerminalSymbols.TokenNamePLUS_EQUAL,
            ITerminalSymbols.TokenNamePLUS_PLUS,
            ITerminalSymbols.TokenNameQUESTION,
            ITerminalSymbols.TokenNameREMAINDER,
            ITerminalSymbols.TokenNameREMAINDER_EQUAL,
            ITerminalSymbols.TokenNameRIGHT_SHIFT,
            ITerminalSymbols.TokenNameRIGHT_SHIFT_EQUAL,
            ITerminalSymbols.TokenNameSEMICOLON,
            ITerminalSymbols.TokenNameTWIDDLE,
            ITerminalSymbols.TokenNameUNSIGNED_RIGHT_SHIFT,
            ITerminalSymbols.TokenNameUNSIGNED_RIGHT_SHIFT_EQUAL,
            ITerminalSymbols.TokenNameXOR,
            ITerminalSymbols.TokenNameXOR_EQUAL
    };

    @Override
    public HalsteadMetrics getHalstead(MethodDeclaration methodDeclaration) {
        final HalsteadMetrics[] hal = new HalsteadMetrics[1];
        ASTVisitor astVisitor = new ASTVisitor() {

            @Override
            public boolean visit(MethodDeclaration node) {
                IScanner scanner = ToolFactory.createScanner(false, false, false, false);
                if (node.getBody() == null) {
                    hal[0] = new HalsteadMetrics(0, 0, 0, 0);
                }
                else {
                    scanner.setSource(node.getBody().toString().toCharArray());
                    Map<Integer, Integer> operandsFilter = OperandsFilter();
                    Map<Integer, Integer> operatorsFilter = OperatorsFilter();
                    scanner.resetTo(0, node.getLength());
                    Map<Integer, ArrayList<String>> operands = ParseTokens(scanner, operandsFilter);
                    scanner.resetTo(0, node.getLength());
                    Map<Integer, ArrayList<String>> operators = ParseTokens(scanner, operatorsFilter);
                    hal[0] = new HalsteadMetrics(
                            (int)operands.values().stream().mapToInt(x->x.size()).sum(),
                            (int)operators.values().stream().mapToInt(x->x.size()).sum(),
                            (int)operands.values().stream().distinct().mapToInt(x->(int)x.stream().distinct().count()).sum(),
                            (int)operators.values().stream().distinct().mapToInt(x->(int)x.stream().distinct().count()).sum());
                }
                return false;
            }

            private Map<Integer, Integer> OperatorsFilter() {
                Map<Integer, Integer> opers = new HashMap<>();
                for (int oper : Operators) {
                    opers.put(oper, oper);
                }
                return opers;
            }

            private Map<Integer, Integer> OperandsFilter() {
                Map<Integer, Integer> opnds = new HashMap<>();
                for (int oper : Operands) {
                    opnds.put(oper, oper);
                }
                return opnds;
            }

            private Map<Integer, ArrayList<String>> ParseTokens(IScanner scanner, Map<Integer, Integer> filter) {
                Map<Integer, ArrayList<String>> dictionary = new HashMap<>();
                int token;
                try {
                    while ((token = scanner.getNextToken()) != ITerminalSymbols.TokenNameEOF) {
                        String value = String.valueOf(scanner.getCurrentTokenSource());
                        if (filter.get(token) != null) {
                            List<String> list = dictionary.get(token);
                            if (list == null) {
                                dictionary.put(token, new ArrayList<>());
                                list = dictionary.get(token);
                            }
                            list.add(value);
                        }
                    }
                } catch (InvalidInputException e) {
                    e.printStackTrace();
                }
                return dictionary;
            }
        };
        methodDeclaration.accept(astVisitor);
        return hal[0];
    }
}
