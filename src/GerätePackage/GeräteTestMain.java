package Ger�tePackage;

public class Ger�teTestMain {

	public static void main(String[] args) {
		Toaster toaster = new Toaster();
		Licht licht = new Licht();
		Staubsauger sbsg = new Staubsauger();
		PC pc = new PC();
		K�hlschrank k�hl = new K�hlschrank();
		
		System.out.println("Toaster: "+toaster.randomisieren());
		System.out.println("Licht: "+licht.randomisieren());
		
		pc.setModus(2);
		System.out.println("PC: "+pc.randomisieren());
		
		/*
		for(int i = 1; i < 30; i++){
			sbsg.modusDauer++;
			
			sbsg.modify�nderWahrsch();	//wahrscheinlichkeit wird mit betriebsdauer erh�ht
			
			//aktuellerVerbrauch wird ge�ndert wenn �nderungsWahrscheinlichkeit hoch genug ist (h�her als Math.random())
			sbsg.setAktuellerVerbrauch();	
			
			System.out.println("Staubsauger: "+sbsg.randomisieren()
					+"	�nderungswahrscheinlichkeit: "+ sbsg.�nderungsWahrscheinlichkeit);
		}
		*/
		
		for(int i = 0; i < 1440; i++){
			k�hl.betriebsdauer = i;
			System.out.println("K�hlschrank: "+k�hl.setAktuellerVerbrauch()+" Minute: "+i);
			
		}
		
	}

}
