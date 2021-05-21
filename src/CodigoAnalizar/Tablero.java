package CodigoAnalizar;

public class Tablero {
	private Casilla tablero[][];

	
	public Tablero() {
		
		this.tablero = new Casilla[8][8];
		Inicializar();
	}
	
	public  Casilla[][] getTablero() {
		return tablero;
	}

	public void setTablero( Casilla[][] tablero) {
		this.tablero = tablero;
	}
	public void EstablecerPieza(PiezaAjedrez pieza, int x, int y) {
		tablero[x][y].setPiezaActual(pieza);
		tablero[x][y].setEstaOcupada(true);
	}
	
	public void Inicializar() {
		Casilla a=new Casilla (null, false);
		for (int i=0;i<8;i++) {
			for (int j=0;j<8;j++) {
				this.tablero[i][j]= a;
			}
		}
	}
	
	public boolean ValidarDiagonalDerechaAbajo(int x1, int y1, int x2, int y2) {
		boolean bandera=true;
		while (x1+1<=x2 && y1+1<=y2) {
			if (tablero[x1+1][y1+1].isEstaOcupada()) {
				bandera =false;
				break;
			}
			x1++;
			y1++;
		}
		return bandera;
	}
	
	public boolean ValidarDiagonalIzquierdaAbajo(int x1, int y1, int x2, int y2) {
		boolean bandera=true;
		while (x1-1>=x2 && y1-1>=y2) {
			if (tablero[x1-1][y1-1].isEstaOcupada()) {
				bandera =false;
				break;
			}
			x1++;
			y1++;
		}
		return bandera;
	}
	
	public boolean ValidarLineasRectaHorizontalD(int x1, int y1, int x2, int y2) {
		boolean bandera=true;
		for (int i=x1; i<x2+1;i++) {
			if(tablero[i][y1].isEstaOcupada()) {
				bandera=false;
			}
			i++;
		}
		return bandera;
	}
	
	public boolean ValidarLineasRectaVertical(int x1, int y1, int x2, int y2) {
		boolean bandera=true;
		for (int i=y1; i<y2+1;i++) {
			if(tablero[i][y1].isEstaOcupada()) {
				bandera=false;
			}
			i++;
		}
		return bandera;
	}
	
	public boolean ValidarL(int x1, int y1, int x2, int y2) {
		
		return !tablero[x2][y2].isEstaOcupada();
	}
	
	public boolean ValidarR(int x1, int y1, int x2, int y2) {
		
		return !tablero[x2][y2].isEstaOcupada();
	}
}
