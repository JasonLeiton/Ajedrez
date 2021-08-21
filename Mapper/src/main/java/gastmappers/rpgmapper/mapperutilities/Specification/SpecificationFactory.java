package gastmappers.rpgmapper.mapperutilities.Specification;

/**
 * Fábrica para construir una manejador según el tipo de hoja.
 * 
 * @author Equipo de trabajo AVIB
 * @version 1.0
 * @since 1.0
 */
public class SpecificationFactory {

	/**
	 * Método para construir especificación según el tipo de hoja
	 * 
	 * @param specificationType Tipo de hoja
	 * @return Objeto con todas las declaraciones según la hoja
	 */
	public static Specification buildSpecification(SpecificationEnum specificationType) {
		Specification specification = null;
		switch (specificationType) {
		case C:
			specification = new CType(specificationType);
			break;
		case H:
			specification = new HType(specificationType);
			break;
		case F:
			specification = new FType(specificationType);
			break;
		case D:
			specification = new DType(specificationType);
			break;
		case I:
			specification = new IType(specificationType);
			break;
		case O:
			specification = new OType(specificationType);
			break;
		case P:
			specification = new PType(specificationType);
			break;
		default:
			break;
		}
		return specification;
	}
}
