package CodigoAnalizar;

public class Alfil extends PiezaAjedrez {

	public Alfil(String nombre, int puntaje, String color, int posicionX, int posicionY) {
		super(nombre, puntaje, color, posicionX, posicionY);
		
	}
	public boolean ValidarMover (int x, int y, Tablero tablero ) {
		int x1=getPosicionX();
		int y1=getPosicionY();
		boolean diagonal1=tablero.ValidarDiagonalDerechaAbajo(x, y, x1, y1);
		boolean diagonal2=tablero.ValidarDiagonalIzquierdaAbajo(x,y, x1, y1);
		return diagonal1&&diagonal2; 		
	}
	
}
