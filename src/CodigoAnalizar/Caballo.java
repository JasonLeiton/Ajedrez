package CodigoAnalizar;

public class Caballo extends PiezaAjedrez {

	public Caballo(String nombre, int puntaje, String color, int posicionX, int posicionY) {
		super(nombre, puntaje, color, posicionX, posicionY);
	
	}

	public boolean ValidarMover(int x, int y, Tablero tablero) {
		int x1=getPosicionX();
		int y1=getPosicionY();
		return tablero.ValidarL(x1, y1, x, y);
	}

}
