package UIMaker;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Esta clase contiene tiene todos los elementos necesarios para crear una
 * ventana para obtener dos direcciones de directorios.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class DirectoryPicker extends javax.swing.JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String outputDirectory;
	private String inputDirectory;
	private JButton buttonBeginAnalysis;

	/**
	 * Constructor e inicializa las variables de los directorios de entrada y
	 * salida.
	 */
	public DirectoryPicker() {
		this.outputDirectory = "";
		this.inputDirectory = "";
	}

	/**
	 * Crea la ventana con todos los componentes necesarios.
	 * 
	 * @return bot�n para obtener las acciones sobre el mismo.
	 */
	public JButton createGUI() {
		// Caracter�sticas del frame
		setTitle("Transformador AST");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 100, 0, 0);
		setPreferredSize(new Dimension(350, 350));
		setMinimumSize(new Dimension(350, 350));

		// Panel para agregar los componentes
		JPanel containerPanel = new JPanel();
		containerPanel.setLayout(new GridLayout(0, 1));

		// Crea un label + boton + label con la direcci�n elegida
		createButton(containerPanel, "Seleccionar directorio con c�digo para transformar", "Entrada");
		createButton(containerPanel, "Seleccionar directorio para archivos de salida", "Salida");

		// Bot�n para iniciar el an�lisis
		buttonBeginAnalysis = new JButton("Iniciar an�lisis");
		buttonBeginAnalysis.setEnabled(false);

		containerPanel.add(buttonBeginAnalysis);
		add(containerPanel);

		pack();
		setVisible(true);
		return buttonBeginAnalysis;
	}

	/**
	 * Funci�n gen�rica para crear el conjunto asociado a un bot�n.
	 * 
	 * @param containerPanel panel donde est�n los componentes.
	 * @param messageButton  mensaje para poner en el texto del bot�n.
	 * @param typeDirectory  indica si el directorio es de entrada o salida.
	 */
	private void createButton(JPanel containerPanel, String messageButton, String typeDirectory) {
		JLabel titleButton = new JLabel("Directorio con la " + typeDirectory.toLowerCase() + " del programa");
		JButton selectDirectoryButton = new JButton(messageButton);
		JLabel directoryResultText = new JLabel("");

		// Acci�n del bot�n para abrir ventana
		selectDirectoryButton.addActionListener(e -> {
			selectFile(directoryResultText, typeDirectory);
		});

		containerPanel.add(titleButton);
		containerPanel.add(selectDirectoryButton);
		containerPanel.add(directoryResultText);
	}

	/**
	 * Funci�n para abrir la ventana para seleccionar la carpeta.
	 * 
	 * @param directoryResultLabel label donde se va a almacenar la ruta elegida.
	 * @param typeDirectory        indica si el directorio es de entrada o salida.
	 */
	private void selectFile(JLabel directoryResultLabel, String typeDirectory) {
		JFileChooser fileChooser = new JFileChooser();
		// Debe ser solo de directorios
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File fileName = fileChooser.getSelectedFile();
			directoryResultLabel.setText(fileName.toString());

			// Establece las variables seg�n las rutas elegidas
			switch (typeDirectory) {
			case "Entrada":
				this.inputDirectory = fileName.toString();
				break;
			case "Salida":
				this.outputDirectory = fileName.toString();
			default:
				break;
			}

			// El bot�n se habilita si las dos directores presentan valores
			this.buttonBeginAnalysis.setEnabled(checkDirectoriesEmpty());
		}
	}

	/**
	 * Revisa las dos variables con los directorios para establecer si las dos
	 * contienen datos.
	 * 
	 * @return falso si uno de los dos est� vac�o. verdadero si ambas tienen
	 *         informaci�n.
	 */
	private boolean checkDirectoriesEmpty() {
		return !this.inputDirectory.contentEquals("") && !this.outputDirectory.contentEquals("");
	}

	/**
	 * Obtiene el directorio de entrada.
	 * 
	 * @return directorio de entrada.
	 */
	public String getInputDirectory() {
		return this.inputDirectory;
	}

	/**
	 * Obtiene el directorio de salida.
	 * 
	 * @return directorio de salida.
	 */
	public String getOutputDirectory() {
		return this.outputDirectory;
	}

	/**
	 * Cierra el frame para iniciar con el an�lisis.
	 */
	public void closeFrame() {
		dispose();
	}
}
