package CodigoAnalizar;

public class Torre extends PiezaAjedrez {

	public Torre(String nombre, int puntaje, String color, int posicionX, int posicionY) {
		super(nombre, puntaje, color, posicionX, posicionY);
		
	}

	public boolean ValidarMover(int x, int y, Tablero tablero) {
		int x1=getPosicionX();
		int y1=getPosicionY();
		boolean horizontal1= tablero.ValidarLineasRectaHorizontalD(x1, y1, x, y);
		boolean horizontal2=tablero.ValidarLineasRectaHorizontalD(x, y, x1, y1);
		boolean vertical1=tablero.ValidarLineasRectaVertical(x1, y1, x, y);
		boolean vertical2=tablero.ValidarLineasRectaVertical(x, y, x1, y1);
		return horizontal1&&horizontal2&&vertical1&&vertical2;
	}

}
