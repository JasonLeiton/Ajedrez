package scm.test;

import java.awt.HeadlessException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JOptionPane;

import gastmappers.Language;
import gastmappers.Mapper;
import gastmappers.MapperFactory;
import gastmappers.exceptions.UnsupportedLanguageException;
import org.apache.http.impl.io.SocketOutputBuffer;

import ASTJAVA.Inicio;
import scm.SCM;
import scm.SCMFactory;
import scm.elem.ISCMDirectory;
import scm.elem.ISCMFileHasNotChilds;
import scm.elem.ISCMFileSystemElement;
import scm.exceptions.FileNotFoundedOnRevision;;

public class GitTest {
	
	public  void GetGitProject(String url,String user, String password,String outputPath) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, HeadlessException, ParseException, UnsupportedLanguageException {
		SCMFactory scmf = SCMFactory.getInstance(SCMFactory.GIT);
		//scmf.setupConnection("https://github.com/tallerDisenoDigital/vallhalla-armv4.git", "user", "password");
		//scmf.setupConnection("https://github.com/TallerDigitales/lab4.git", "user", "password");
		scmf.setupConnection(url, user, password);	
		SCM scm = scmf.getSCM();

		ArrayList<String> listOfCommits =  scm.retrieveRevisionsID();
		Collection<ISCMFileSystemElement> f;
		Iterator<ISCMFileSystemElement> it;	
		int contador=0;
		for (String commit : listOfCommits) { // recorre la lista de los commits			
			if(contador%20==0) {
				System.out.println("Commit numero: "+contador);
			f = scm.retrieveProjectStructure(commit);
			it = f.iterator();	
			ISCMFileSystemElement fse = it.next();

				MapperFactory factory = new MapperFactory();

				// Build the Java mapper.
				Mapper mapper = factory.createMapper(Language.JAVA);

				Inicio a = new Inicio(scmf.getAbsoluteTempPath(), outputPath, mapper);
				a.ParseFilesInDir(a.getPath());
				a.WriteTranslation();

			}
			
			contador++;
		}
		JOptionPane.showMessageDialog(null, "Proceso finalizó correctamente");
		
		
		


		
		
		//scm.retrieveRevisionsDiff("1f21220e334840588d2a7773c7496679678df186", "ce987b67a616dbe74550f66184c7430a90cebbaa");
		
		//System.out.println(scm.getPreviousPath("src/listpack/CircularList.java", "5157928192b899be28749e32e52b75142849c90d"));;
		
		System.out.println("-- end test --");
		/*
		Set<String> commits = entries.keySet();
		
		for (String string : commits) {
			System.out.println(string);
			System.out.println(entries.get(string).getCommitMessage());
		}
		*/
	}
}
