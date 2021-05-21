package CodigoAnalizar;

public class Rey extends PiezaAjedrez{

	public Rey(String nombre, int puntaje, String color, int posicionX, int posicionY) {
		super(nombre, puntaje, color, posicionX, posicionY);


	}

	public boolean ValidarMover(int x, int y, Tablero tablero) {
		
		return tablero.ValidarR(x, y, x, y);
	}

}
