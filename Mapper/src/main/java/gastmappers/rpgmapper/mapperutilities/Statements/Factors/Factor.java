package gastmappers.rpgmapper.mapperutilities.Statements.Factors;

/**
 * Interfaz para modelar un Factor. Tienen asociado valor y tipo.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public interface Factor {

	public String getValue();

	public String getType();

	public void setValue(String value);

	public void setType(String type);
}
