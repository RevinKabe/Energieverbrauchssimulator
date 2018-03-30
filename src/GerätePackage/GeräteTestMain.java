package Ger�tePackage;

/**
 * @author Kevin Rabe
 *
 */
public class Ger�teTestMain {

	public static void main(String[] args) {
		Toaster toaster = new Toaster();
		
		Wasserkocher wk = new Wasserkocher();
		
		Licht licht = new Licht();	//nur eine Gl�hbirne
		
		DeckenLampe dl = new DeckenLampe();
		
		Mikrowelle mw = new Mikrowelle();
		
		Waschmaschine wm = new Waschmaschine();
		
		PC pc = new PC();
		
		Staubsauger sbsg = new Staubsauger();
		
		K�hlschrank k�hl = new K�hlschrank();
		
		System.out.println("Toaster: "+toaster.randomisieren());
		System.out.println("Wasserkocher: "+wk.randomisieren());
		System.out.println("Licht: "+licht.randomisieren());
		System.out.println("Deckenlampe: "+dl.randomisieren());
		System.out.println("Mikrowelle: "+mw.randomisieren());
		
		wm.setModus(2);	//Modus 2: Wasser wird erhitzt (10 min) und schleudern wird schneller
		System.out.println("Waschmaschine: "+wm.randomisieren());
		
		pc.setModus(2);	//Modus 2: aktives browsen im Internet (Facebook und andere, komplexe Seiten)
		System.out.println("PC: "+pc.randomisieren());
		
		Plasmafernseher pf = new Plasmafernseher();
		System.out.println("Plasmafernseher: "+pf.randomisieren());
		
		LCDFernseher lf = new LCDFernseher();
		System.out.println("LCDFernseher: "+lf.randomisieren());
		
		/*//Staubsauger
		for(int i = 1; i < 30; i++){
			sbsg.modusDauer++;
			
			sbsg.modify�nderWahrsch();	//wahrscheinlichkeit wird mit betriebsdauer erh�ht
			
			//aktuellerVerbrauch wird ge�ndert wenn �nderungsWahrscheinlichkeit hoch genug ist (h�her als Math.random())
			sbsg.setAktuellerVerbrauch();	
			
			System.out.println("Staubsauger: "+sbsg.randomisieren()
					+"	�nderungswahrscheinlichkeit: "+ sbsg.�nderungsWahrscheinlichkeit);
		}
		*/
		
		/*//K�hlschrank
		for(int i = 0; i < 1440; i++){
			k�hl.betriebsdauer = i;
			System.out.println("K�hlschrank: "+k�hl.setAktuellerVerbrauch()+" Minute: "+i);
			
		}
		*/
	}

}
