package Ger�tePackage;

public class Staubsauger extends Ger�teTyp3{
	
	public Staubsauger() {
	super(690, 110, 0.2, 6.5, 0.0015, 0.0015);
	this.aktuellerVerbrauch = this.maxVerbrauch;
	}
	
	//die �nderungsWahrscheinlichkeit �ndert sich mit der Betriebsdauer
	//sie steigt, bis eine �nderung eintritt und wird dann zur�ckgesetzt
	public void modify�nderWahrsch(int Modus_Dauer, double �nderungs_Wahrscheinlichkeit){	
		this.�nderungsWahrscheinlichkeit += Modus_Dauer * this.const�nderWahrsch;
	}

}
