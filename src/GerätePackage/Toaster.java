package Ger�tePackage;

public class Toaster extends Ger�teTyp1{

	int betriebsdauer = 2;
	
	public Toaster() {		
		super(825, 0.1, 4.0);
	}

	public int getBetriebsdauer() {
		return betriebsdauer;
	}
}
