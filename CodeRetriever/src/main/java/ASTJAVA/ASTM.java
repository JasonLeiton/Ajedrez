/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ASTJAVA;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import gastmappers.Mapper;
import gastmappers.MapperFactory;
import gastmappers.Language;
import gastmappers.exceptions.UnsupportedLanguageException;
import org.apache.commons.io.FileUtils;

import UIMaker.DirectoryPicker;
//import scm.test.GitTest;
import scm.test.GitTest;

/**
 * Esta clase contiene el main, donde se pasa la ruta y se llama a analizar los
 * archivos.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class ASTM {

	/**
	 * Instancia el objeto donde se inica el flujo de la información.
	 * 
	 * @param args Lista de argmentos.
	 * @throws IOException               En caso de que ocurra un error en la
	 *                                   lectura de algun archivo.
	 * @throws SecurityException         si se trata de acceder a un valor
	 *                                   protegido.
	 * @throws NoSuchMethodException     si se invoca un método que no existe en el
	 *                                   objeto.
	 * @throws InvocationTargetException si se invoca un método y al parámetro se
	 *                                   encuentra incompleto.
	 * @throws IllegalArgumentException  si se invoca un método y los parámetros
	 *                                   asociados no corresponden a los que el
	 *                                   método recibe.
	 * @throws IllegalAccessException    si se accede a un método que no se tienen
	 *                                   los permisos.
	 * @throws ParseException 
	 * @throws HeadlessException 
	 */
	public static void main(String[] args) throws IOException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, HeadlessException, ParseException, UnsupportedLanguageException {
		int choose=JOptionPane.showConfirmDialog(null, "Desea obtener el código desde git?");
		if (choose==0) {
			String url= JOptionPane.showInputDialog(null, "Introduzca el url del reposositorio");
			String outputDirectory= JOptionPane.showInputDialog(null, "Introduzca la carpeta de salida");
			//salida C:\Users\ljjas\Desktop\Meta\Resultados
			//repositorio: https://github.com/JasonLeiton/JDTTEST
			// si es repositorio
			GitTest git = new GitTest();
			git.GetGitProject(url, "user", "password", outputDirectory);
		}
		else if (choose==1)  {
			UIMaker.DirectoryPicker windowFrame = new DirectoryPicker();
			JButton beginAnalysisButton = windowFrame.createGUI();
			beginAnalysisButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ae) {
					String inputDirectory = windowFrame.getInputDirectory();
					String outputDirectory = windowFrame.getOutputDirectory();
					try {
						windowFrame.closeFrame();
						System.out.println(inputDirectory);
						System.out.println(outputDirectory);
						beginTransformation(inputDirectory, outputDirectory);

					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| NoSuchMethodException | SecurityException | IOException | UnsupportedLanguageException
							| ParseException | HeadlessException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	private static void beginTransformation(String inputDirectory, String outputDirectory)
			throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, HeadlessException, ParseException, UnsupportedLanguageException {

		MapperFactory factory = new MapperFactory();

		// Build the Java mapper.
		Mapper mapper = factory.createMapper(Language.JAVA);

		Inicio a = new Inicio(inputDirectory, outputDirectory, mapper);
		a.ParseFilesInDir(a.getPath());
		a.WriteTranslation();
		JOptionPane.showMessageDialog(null, "Proceso finalizó correctamente");

	}
}
