import CodigoAnalizar.Alfil;
import CodigoAnalizar.Caballo;
import CodigoAnalizar.Dama;
import CodigoAnalizar.Rey;
import CodigoAnalizar.Tablero;
import CodigoAnalizar.Torre;

public class Main {

	public static void main(String[] args) {
		
		Tablero tablero= new Tablero();
		Alfil alfil= new Alfil("Alfil",3,"negro",0,2);
		Caballo caballo=new Caballo("Caballo",3,"blanco", 0, 1);
		Torre torre=new Torre("Torre",5,"blanco",0,0);
		Dama dama=new Dama("Dama",9,"blaco",0,4);
		Rey rey = new Rey("Rey",100,"blanco",0,3);
		
		tablero.EstablecerPieza(torre, 0, 0);
		tablero.EstablecerPieza(caballo, 0, 1);
		tablero.EstablecerPieza(alfil, 0, 2);
		tablero.EstablecerPieza(rey, 0, 3);
		tablero.EstablecerPieza(dama, 0, 4);
		
		
		System.out.println("Todo bien");
	}

}
