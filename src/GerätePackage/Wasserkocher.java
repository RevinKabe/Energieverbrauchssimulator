package Ger�tePackage;

public class Wasserkocher extends Ger�teTyp1{

	int betriebsdauer = 2;

	public Wasserkocher() {
		super(1850, 0.1, 9.0);	// l�uft bei 1 Liter F�llung etwa 3 minuten
	}
	
	public int getBetriebsdauer() {
		return betriebsdauer;
	}

}
