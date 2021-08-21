package gastmappers.rpgmapper.mapperutilities.Specification.Statements;

import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.AcqStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.AddDurStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.AddStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.AllocStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.BegsrStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.BitOnStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CabStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CallBStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CallPStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CallStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CasStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CatStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ChainStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CheckRStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CheckStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ClearStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CloseStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CommitStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.CompStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DeAllocStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DefineStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DeleteStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DivStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DoStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DouStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DowStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DsplyStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.DumpStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ElseStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.EndStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.EvalRStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.EvalStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ExceptStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ExfmtStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ExsrStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ExtrctStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.FeodStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ForStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ForceStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.GotoStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.IfStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.InStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.IterStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.KlistStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.LeaveSrStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.LeaveStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.MonitorStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.MoveAStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.MoveStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.MultStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.MxxzoStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.NextStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.OccurStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.OnErrorStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.OpenStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.OutStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ParmStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.PlistStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.PostStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ReadCStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ReadEStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ReadStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ReallocStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.RelStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ResetStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ReturnStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.RolbkStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ScanStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SelectStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SetGTStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SetLLStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SetOffStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SetOnStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ShtdnStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SortaStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SqrtStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SubDurStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SubStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.SubstStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.TagStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.TestBStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.TestNStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.TestStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.TestZStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.TimeStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.UnlockStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.UpdateStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.WriteStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.XFootStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.XLateStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ZAddStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.ZSubStatement;
import gastmappers.rpgmapper.mapperutilities.Statements.Specification.CTypeStatements.Opcodes.*;
import gastmappers.rpgmapper.mapperutilities.Statements.Types.Statement;

/**
 * Fábrica para crear declaraciones según el tipo de instrucción.
 *
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class CTypeStatementFactory {

	public static Statement buildStatement(Opcode opcode) {
		Statement statement = null;
		if (opcode == null) {
			return null;
		}
		switch (opcode.getOpcodeEnum()) {
		case ACQ:
			statement = new AcqStatement(opcode);
			break;
		case ADD:
			statement = new AddStatement(opcode);
			break;
		case ADDDUR:
			statement = new AddDurStatement(opcode);
			break;
		case ALLOC:
			statement = new AllocStatement(opcode);
			break;
		case BEGSR:
			statement = new BegsrStatement(opcode);
			break;
		case BITON:
			statement = new BitOnStatement(opcode);
			break;
		case CAT:
			statement = new CatStatement(opcode);
			break;
		case CAB:
			statement = new CabStatement(opcode);
			break;
		case CALL:
			statement = new CallStatement(opcode);
			break;
		case CALLB:
			statement = new CallBStatement(opcode);
			break;
		case CALLP:
			statement = new CallPStatement(opcode);
			break;
		case CAS:
			statement = new CasStatement(opcode);
			break;
		case CHAIN:
			statement = new ChainStatement(opcode);
			break;
		case CHECK:
			statement = new CheckStatement(opcode);
			break;
		case CHECKR:
			statement = new CheckRStatement(opcode);
			break;
		case CLEAR:
			statement = new ClearStatement(opcode);
			break;
		case CLOSE:
			statement = new CloseStatement(opcode);
			break;
		case COMMIT:
			statement = new CommitStatement(opcode);
			break;
		case COMP:
			statement = new CompStatement(opcode);
			break;
		case DEALLOC:
			statement = new DeAllocStatement(opcode);
			break;
		case DEFINE:
			statement = new DefineStatement(opcode);
			break;
		case DELETE:
			statement = new DeleteStatement(opcode);
			break;
		case DIV:
			statement = new DivStatement(opcode);
			break;
		case DSPLY:
			statement = new DsplyStatement(opcode);
			break;
		case DUMP:
			statement = new DumpStatement(opcode);
			break;
		case END:
			statement = new EndStatement(opcode);
			break;
		case EVAL:
			statement = new EvalStatement(opcode);
			break;
		case EVALR:
			statement = new EvalRStatement(opcode);
			break;
		case EXCEPT:
			statement = new ExceptStatement(opcode);
			break;
		case EXFMT:
			statement = new ExfmtStatement(opcode);
			break;
		case EXTRCT:
			statement = new ExtrctStatement(opcode);
			break;
		case DO:
			statement = new DoStatement(opcode);
			break;
		case DOU:
			statement = new DouStatement(opcode);
			break;
		case DOW:
			statement = new DowStatement(opcode);
			break;
		case EXSR:
			statement = new ExsrStatement(opcode);
			break;
		case FEOD:
			statement = new FeodStatement(opcode);
			break;
		case FOR:
			statement = new ForStatement(opcode);
			break;
		case FORCE:
			statement = new ForceStatement(opcode);
			break;
		case GOTO:
			statement = new GotoStatement(opcode);
			break;
		case IF:
			statement = new IfStatement(opcode);
			break;
		case IN:
			statement = new InStatement(opcode);
			break;
		case ITER:
			statement = new IterStatement(opcode);
			break;
		case KLIST:
			statement = new KlistStatement(opcode);
			break;
		case ELSE:
			statement = new ElseStatement(opcode);
			break;
		case LEAVE:
			statement = new LeaveStatement(opcode);
			break;
		case LEAVESR:
			statement = new LeaveSrStatement(opcode);
			break;
		case MOVEA:
			statement = new MoveAStatement(opcode);
			break;
		case MOVE:
		case MOVEL:
			statement = new MoveStatement(opcode);
			break;
		case MHLZO:
		case MLLZO:
		case MLHZO:
		case MHHZO:
			statement = new MxxzoStatement(opcode);
			break;
		case MONITOR:
			statement = new MonitorStatement(opcode);
			break;
		case MULT:
			statement = new MultStatement(opcode);
			break;
		case NEXT:
			statement = new NextStatement(opcode);
			break;
		case OCCUR:
			statement = new OccurStatement(opcode);
			break;
		case ONERROR:
			statement = new OnErrorStatement(opcode);
			break;
		case OPEN:
			statement = new OpenStatement(opcode);
			break;
		case OUT:
			statement = new OutStatement(opcode);
			break;
		case PARM:
			statement = new ParmStatement(opcode);
			break;
		case PLIST:
			statement = new PlistStatement(opcode);
			break;
		case POST:
			statement = new PostStatement(opcode);
			break;
		case READ:
		case READP:
			statement = new ReadStatement(opcode);
			break;
		case READC:
			statement = new ReadCStatement(opcode);
			break;
		case READE:
		case READPE:
			statement = new ReadEStatement(opcode);
			break;
		case REALLOC:
			statement = new ReallocStatement(opcode);
			break;
		case REL:
			statement = new RelStatement(opcode);
			break;
		case RESET:
			statement = new ResetStatement(opcode);
			break;
		case RETURN:
			statement = new ReturnStatement(opcode);
			break;
		case ROLBK:
			statement = new RolbkStatement(opcode);
			break;
		case SUB:
			statement = new SubStatement(opcode);
			break;
		case TAG:
			statement = new TagStatement(opcode);
			break;
		case SCAN:
			statement = new ScanStatement(opcode);
			break;
		case SELECT:
			statement = new SelectStatement(opcode);
			break;
		case SETGT:
			statement = new SetGTStatement(opcode);
			break;
		case SETLL:
			statement = new SetLLStatement(opcode);
			break;
		case SETOFF:
			statement = new SetOffStatement(opcode);
			break;
		case SETON:
			statement = new SetOnStatement(opcode);
			break;
		case SHTDN:
			statement = new ShtdnStatement(opcode);
			break;
		case SORTA:
			statement = new SortaStatement(opcode);
			break;
		case SQRT:
			statement = new SqrtStatement(opcode);
			break;
		case SUBDUR:
			statement = new SubDurStatement(opcode);
			break;
		case SUBST:
			statement = new SubstStatement(opcode);
			break;
		case TEST:
			statement = new TestStatement(opcode);
			break;
		case TESTN:
			statement = new TestNStatement(opcode);
			break;
		case TESTZ:
			statement = new TestZStatement(opcode);
			break;
		case TESTB:
			statement = new TestBStatement(opcode);
			break;
		case TIME:
			statement = new TimeStatement(opcode);
			break;
		case UNLOCK:
			statement = new UnlockStatement(opcode);
			break;
		case UPDATE:
			statement = new UpdateStatement(opcode);
			break;
		case WRITE:
			statement = new WriteStatement(opcode);
			break;
		case XFOOT:
			statement = new XFootStatement(opcode);
			break;
		case XLATE:
			statement = new XLateStatement(opcode);
			break;
		case ZADD:
			statement = new ZAddStatement(opcode);
			break;
		case ZSUB:
			statement = new ZSubStatement(opcode);
			break;
		default:
			break;
		}
		return statement;
	}

}
