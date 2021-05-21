package CodigoAnalizar;

public abstract class PiezaAjedrez {

	private String nombre;
	private int puntaje;
	private String color;
	private int posicionX;
	private int posicionY;
	public PiezaAjedrez(String nombre, int puntaje, String color, int posicionX, int posicionY) {
		super();
		this.nombre = nombre;
		this.puntaje = puntaje;
		this.color = color;
		this.posicionX = posicionX;
		this.posicionY = posicionY;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getPosicionX() {
		return posicionX;
	}
	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public int getPosicionY() {
		return posicionY;
	}
	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	
	
	public  abstract boolean  ValidarMover (int x, int y, Tablero tablero);
	
}
