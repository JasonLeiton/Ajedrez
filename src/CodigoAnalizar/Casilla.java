package CodigoAnalizar;

public class Casilla {
	private PiezaAjedrez piezaActual;
	private boolean estaOcupada;
	public Casilla(PiezaAjedrez piezaActual, boolean estaOcupada) {
		super();
		this.piezaActual = piezaActual;
		this.estaOcupada = estaOcupada;
	}
	public PiezaAjedrez getPiezaActual() {
		return piezaActual;
	}
	public void setPiezaActual(PiezaAjedrez piezaActual) {
		this.piezaActual = piezaActual;
	}
	public boolean isEstaOcupada() {
		return estaOcupada;
	}
	public void setEstaOcupada(boolean estaOcupada) {
		this.estaOcupada = estaOcupada;
	}
	
}
