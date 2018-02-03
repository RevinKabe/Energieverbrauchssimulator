package Ger�tePackage;

public class Trockner extends Ger�teTyp2 {

	public Trockner() {
		super(2660, 175, 1990, 175, 0.1);

		// Koch-/Buntw�sche, Extra Schranktrocken, volle W�scheladung
		// Pausen zumindest bisher nicht ber�cksichtigt
		// alle Modi f�r den Durchlauf in zeitlich chronologischer Reihenfolge
		
		this.verbrauchsWerte.add(1990.0); // Modus 0: Start (ersten 2 min) schleudern + erhitzen
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);

		this.verbrauchsWerte.add(2660.0); // Modus 1: schnelleres schleudern und/oder erh�hte temperatur
		this.schwankungsWerte.add(25.0);
		this.modiDauer.add(24);

		this.verbrauchsWerte.add(1980.0); // Modus 2: �hnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(4);
		
		this.verbrauchsWerte.add(2660.0); // Modus 3: schnelleres schleudern und/oder erh�hte temperatur
		this.schwankungsWerte.add(25.0);
		this.modiDauer.add(4);
		
		this.verbrauchsWerte.add(1990.0); // Modus 4: �hnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(5);
		
		this.verbrauchsWerte.add(910.0); // Modus 5: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(1990.0); // Modus 6: �hnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(8);

		this.verbrauchsWerte.add(910.0); // Modus 7: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(1990.0); // Modus 8: �hnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(3);
		
		this.verbrauchsWerte.add(910.0); // Modus 9: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(2000.0); // Modus 10: �hnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(3);
		
		this.verbrauchsWerte.add(910.0); // Modus 11: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(1990.0); // Modus 12: �hnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(915.0); // Modus 13: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(2000.0); // Modus 14: �hnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(905.0); // Modus 15: weniger erhitzen(?)
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(1);
		
		this.verbrauchsWerte.add(2010.0); // Modus 16: �hnliches Verhalten wie beim start
		this.schwankungsWerte.add(10.0);
		this.modiDauer.add(2);
		
		this.verbrauchsWerte.add(175.0); // Modus 17: ende
		this.schwankungsWerte.add(5.0);
		this.modiDauer.add(7);
	}
}
