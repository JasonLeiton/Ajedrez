package gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.Factory;

import gastmappers.rpgmapper.mapperutilities.Functions.BuiltIn.*;

public class BuiltInFunctionFactory {

    public static BuiltInFunction buildFunction(String functionName) {
        BuiltInFunction function = null;
        switch (functionName) {
            // ABS
            case "ABS":
                function = new AbsFunction(functionName);
                break;
            // ADDR
            case "ADDR":
                function = new AddrFunction(functionName);
                break;
            // BITAND
            case "BITAND":
                function = new BitAndFunction(functionName);
                break;
            // BITNOT
            case "BITNOT":
                function = new BitNotFunction(functionName);
                break;
            // BITOR
            case "BITOR":
                function = new BitOrFunction(functionName);
                break;
            // BITXOR
            case "BITXOR":
                function = new BitXorFunction(functionName);
                break;
            // CHAR
            case "CHAR":
                function = new CharFunction(functionName);
                break;
            // CHECK y CHECKR
            case "CHECK":
            case "CHECKR":
                function = new CheckFunction(functionName);
                break;
            // DATE
            case "DATE":
                function = new DateFunction(functionName);
                break;
            // DAYS
            case "DAYS":
                function = new DaysFunction(functionName);
                break;
            // DIFF
            case "DIFF":
                function = new DiffFunction(functionName);
                break;
            // DEC & DECH
            case "DEC":
            case "DECH":
                function = new DecFunction(functionName);
                break;
            // DECPOS
            case "DECPOS":
                function = new DecPosFunction(functionName);
                break;
            // DIV
            case "DIV":
                function = new DivFunction(functionName);
                break;
            // EDITC
            case "EDITC":
                function = new EditCFunction(functionName);
                break;
            // EDITFLT
            case "EDITFLT":
                function = new EditFltFunction(functionName);
                break;
            // EDITW
            case "EDITW":
                function = new EditWFunction(functionName);
                break;
            // ELEM
            case "ELEM":
                function = new ElemFunction(functionName);
                break;
            // EOF
            case "EOF":
                function = new EofFunction(functionName);
                break;
            // EQUAL
            case "EQUAL":
                function = new EqualFunction(functionName);
                break;
            // ERROR
            case "ERROR":
                function = new ErrorFunction(functionName);
                break;
            // FIELDS
            case "FIELDS":
                function = new FieldsFunction(functionName);
                break;
            // FLOAT
            case "FLOAT":
                function = new FloatFunction(functionName);
                break;
            // FOUND
            case "FOUND":
                function = new FoundFunction(functionName);
                break;
            // GRAPH
            case "GRAPH":
                function = new GraphFunction(functionName);
                break;
			// HOURS
			case "HOURS":
				function = new HoursFunction(functionName);
				break;
            // INT y INTH
            case "INT":
            case "INTH":
                function = new IntFunction(functionName);
                break;
            //KDS
            case "KDS":
            	function = new KdsFunction(functionName);
            	break;
            // LEN
            case "LEN":
                function = new LenFunction(functionName);
                break;
            // LOOKUP
            case "LOOKUP":
            case "LOOKUPLT":
            case "LOOKUPGT":
            case "LOOKUPLE":
            case "LOOKUPGE":
                function = new LookUpFunction(functionName);
                break;
			// MINUTES
			case "MINUTES":
				function = new MinutesFunction(functionName);
				break;
			// MONTHS
			case "MONTHS":
				function = new MonthsFunction(functionName);
				break;
			// MSECONDS
			case "MSECONDS":
				function = new MSecondsFunction(functionName);
				break;
            // NULLIND
            case "NULLIND":
                function = new NullIndFunction(functionName);
                break;
            // OCCUR
            case "OCCUR":
                function = new OccurFunction(functionName);
                break;
            // OPEN
            case "OPEN":
                function = new OpenFunction(functionName);
                break;
            // PADDR
            case "PADDR":
                function = new PAddrFunction(functionName);
                break;
            // PARMS
            case "PARMS":
                function = new ParmsFunction(functionName);
                break;
            // REM
            case "REM":
                function = new RemFunction(functionName);
                break;
            // REPLACE
            case "REPLACE":
                function = new ReplaceFunction(functionName);
                break;
            // SCAN
            case "SCAN":
                function = new ScanFunction(functionName);
                break;
			// SECONDS
			case "SECONDS":
				function = new SecondsFunction(functionName);
				break;
            // SHTDN
            case "SHTDN":
                function = new ShtdnFunction(functionName);
                break;
            // SIZE
            case "SIZE":
                function = new SizeFunction(functionName);
                break;
            // SQRT
            case "SQRT":
                function = new SqrtFunction(functionName);
                break;
            // STATUS
            case "STATUS":
                function = new StatusFunction(functionName);
                break;
            // STR
            case "STR":
                function = new StrFunction(functionName);
                break;
            // SUBDT
            case "SUBDT":
                function = new SubdtFunction(functionName);
                break;
            // SUBST
            case "SUBST":
                function = new SubstFunction(functionName);
                break;
            // TIME
            case "TIME":
                function = new TimeFunction(functionName);
                break;
            // TIMESTAMP
            case "TIMESTAMP":
                function = new TimestampFunction(functionName);
                break;
            // TLOOKUP
            case "TLOOKUP":
            case "TLOOKUPLT":
            case "TLOOKUPGT":
            case "TLOOKUPLE":
            case "TLOOKUPGE":
                function = new TLookUpFunction(functionName);
                break;
            // TRIM
            case "TRIM":
            case "TRIML":
            case "TRIMR":
                function = new TrimFunction(functionName);
                break;
            // UCS2
            case "UCS2":
                function = new Ucs2Function(functionName);
                break;
            // UNS y UNSH
            case "UNS":
            case "UNSH":
                function = new UnsFunction(functionName);
                break;
            // XFOOT
            case "XFOOT":
                function = new XFootFunction(functionName);
                break;
            // XLATE
            case "XLATE":
                function = new XLateFunction(functionName);
                break;
			// YEARS
			case "YEARS":
				function = new YearsFunction(functionName);
				break;
            default:
                function = new CustomFunction(functionName);
                break;
        }
        return function;
    }

}
