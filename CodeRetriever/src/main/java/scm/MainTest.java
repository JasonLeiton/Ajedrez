package scm;

public class MainTest {

	public static void main(String[] args) {
		SCMFactory factory = SCMFactory.getInstance(1);
		factory.setupConnection("http://svn.code.sf.net/p/jedit/svn/jEdit/trunk", "", "");
		SCM scm = factory.getSCM();
		scm.retrieveCommits();

		 

//		    ByteArrayOutputStream out = new ByteArrayOutputStream();
//		     
//		    try
//		    {
//		      RawText rt1 = new RawText(new File("C:\\test\\Test1.txt"));
//		      RawText rt2 = new RawText(new File("C:\\test\\Test2.txt"));
//		      EditList diffList = new EditList();
//		      diffList.addAll(new HistogramDiff().diff(RawTextComparator.DEFAULT, rt1, rt2));
//		      new DiffFormatter(out).format(diffList, rt1, rt2);
//		    } catch (IOException e)
//		    {
//		      e.printStackTrace();
//		    }
//		    System.out.println(out.toString());
	}
}