package Ger�tePackage;

public class Mikrowelle extends Ger�teTyp1{

	private int betriebsdauer = 3;
	
	public Mikrowelle() {
		super(1430, 0.6, 15);
	}

	public int getBetriebsdauer() {
		return betriebsdauer;
	}

}
