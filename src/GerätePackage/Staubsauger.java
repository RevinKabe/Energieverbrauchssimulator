package Ger�tePackage;

public class Staubsauger extends Ger�teTyp3{
	
	public Staubsauger() {
	super(690, 110, 0.2, 6.5, 0.001, 0.001, 125);
	this.aktuellerVerbrauch = this.maxVerbrauch;
	}
	
	//die �nderungsWahrscheinlichkeit �ndert sich mit der Betriebsdauer
	//sie steigt, bis eine �nderung eintritt und wird dann zur�ckgesetzt
	public void modify�nderWahrsch(int Modus_Dauer){	
		this.�nderungsWahrscheinlichkeit += Modus_Dauer * this.const�nderWahrsch;
	}

}
