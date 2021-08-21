
/*******************************************************************************
*
* @date   : 25-mar-2010, 17:39:58
* @file   : SCMDAO
* @author : Antonio Gonzalez Torres
* Contents:
*
* Purpose :
*
*
*******************************************************************************/

package scm;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;

import scm.elem.LogEntry;
import scm.exceptions.FileNotFoundedOnRevision;

/**
 * 
 * @author Antonio Gonzalez Torres
 * The SCM interface is used for retrieve the changes in a version controlled project. Classes like GitSCM or SCMSVN implement from
 * the current interface. 
 * 
 */
public interface SCM {
	// Metadatos - logs - número de commit, fecha, hora, autor 
	// Estructura - branches, commits en los branches y el árbol del proyecto - carpetas y clases 
	// Información sobre cambios - cuáles archivos cambiaron y cuáles lineas fueron cambiadas
	// Código
	// tipos de retornos y métodos

	/**
	 * retrieveRevisionsID. 
	 * No recibe parametros, retorna todos los Identificadores asociados a "commits" 
	 * (cambio realizado sobre algun controlador de versiones como Git o SVN) de un proyecto de algun controlador 
	 * de versiones especifico. El orden de los ID se realiza por la antigüedad del cambio. Los cambios mas recientes 
	 * son los primeros, mientras que los cambios antigüos son los últimos.
	 * @return Lista de los identificadores de los cambios realizados sobre el repositorio analizado, ordenados por antigüedad, los
	 * mas antigüos al final y los mas recientes al inicio de la lista.
	 */
	public ArrayList<String> retrieveRevisionsID();
	/**
	 * retrieveCommits. Recupera los cambios realizados en un proyecto de controlador de versiones en un HashMap, asociando el 
	 * identificador del cambio, con el cambio realizado encapsulado en un LogEntry. 
	 * @param params
	 * @return Un HashMap que contiene los cambios como LogEntry que pueden ser accedidos mediante los identificadores de cada cambio
	 * @see LogEntry
	 * @see scm.SCM.retrieveRevisionsID
	 */
	public HashMap<String, LogEntry> retrieveCommits(String... params);
	
	
	/**
	 * getFile. Returns a file from a change (also called as "commit") as a ByteArrayOutputStream
	 * @param file the name of the file. Is given as local path, for example if the file is inside on a folder, the path will be given 
	 * as "FolderA/FolderB/... other folders .../filename"
	 * @param revision the identifiers associated to a change
	 * @return a {@link ByteArrayOutputStream} with the content of the file on the revision setted.
	 * @exception if the file doesn't exists (applied too, if doesn't exists for certain revision) an error is given.
	 */
    public ByteArrayOutputStream getFile(String file, String revision) throws FileNotFoundedOnRevision;
    
    
    /**
     * retrieveRevisionsDiff. It return the changes realized in two different revisions. File paths changed, files deleted, files contents
     * changed, etc.
     * @param startRevision the first revision ID.
     * @param endRevision the second revision ID.
     * @return 
     */
    public Hashtable<String, String> retrieveRevisionsDiff(String startRevision, String endRevision);
	
    
    /**
     * 
     * @return
     */
    public ArrayList<String> fetchHighLevelRepositoryLayout();
    
    
    /**
     * 
     * @param params
     * @return
     */
    public Collection  retrieveProjectStructure(String... params);
    
    
    /**
     * getPreviousPath. Returns the previous file path of a path in a given revision 
     * @param path the file path which the user wants to get the previous file path
     * @param revision the revision ID which the file is allocated
     * @return if the file exists, the method returns the value of the previous file path of the given path.
     */
    public String getPreviousPath(String path, String revision);
}
