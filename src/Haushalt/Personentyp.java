/**
 * Klasse mit den Typen der Pesonen
 */
package Haushalt;


public class Personentyp {

	private int startzeit;
	private int arbeitszeit;
	private int[] awayTime = new int[1440]; //Wann am Tag gearbeitet werden soll
	private String typ;
	private boolean isAway = false;
	
	public Personentyp(String typ) {
		this.typ = typ;
		chooseTyp(typ);
		initializeAwayTime();
	}
	
	public int getStartzeit(){
		return startzeit;
	}
	
	public int[] getAwayTime(){
		return awayTime;
	}
	
	
	public int getAwayTime(int i){
		return awayTime[i];
	}
	
	public String getTyp(){
		return typ;
	}
	
	
	/*f�gt die arbeitszeiten in das array rein und berechnet daran die chance , dass die person daheim ist
	 *  es m�ssen noch richtige werte hinzugef�gt werden
	 *  1 = daheim, 0 = nicht daheim
	*/
	public void initializeAwayTime(){
		double schwankungWeg = 0, schwankungDaheim;
		int lateness = 0;
		if(typ.equals("Student"))
			schwankungDaheim = 0.00005;
		else
			schwankungDaheim = 0.0001;
		for(int i = 0; i< awayTime.length; i++){
			awayTime[i] = 1;
		}
		/* Anpassung des Arrays mit der Arbeitszeit, in der die Person nicht Zuhause ist
		 * Inklusive einer "Schwankung", da man nicht immer genau gleich nach Hause kommt durch Verkehr z.B. oder losgeht
		 * Schwankung z�hlt immer h�her, bis sie maximal eine 0.5% chance wird, dass die Person jetzt daheim ist, anstatt
		 * die Schleife zu Ende zu laufen
		 */
		for(int i = startzeit; i <= arbeitszeit + startzeit; i++){
			if(isAway == false){
				if(schwankungDaheim <= Math.random()){
					lateness++;
					schwankungDaheim = schwankungDaheim * 2;
				}
				else
					isAway = true;
			}
			else{
				schwankungWeg += 0.0004/(arbeitszeit - lateness);
				if(schwankungWeg >= Math.random())
					break;
				else
					awayTime[i] = 0;
			}
		}
		isAway = false;
		
	}
	
	
	//Setzt die Arbeitszeit und Zeit ab wann gearbeitet wird auf die entsprechenden Werte
	//zu jeder arbeitszeit wird eine wegzeit von 30 minuten hinzugef�gt
	//startzeit ist nach meinen alten schulzeiten gerichtet und arbeitszeiten bei meiner arbeit
	public void chooseTyp(String typ){
		switch(typ){
		case "Arbeiter":if(0.11 <= Math.random())
							arbeitszeit = 522; //Arbeitszeit nach destatis 2016, 41 stunden woche aufgeteilt auf 5 arbeitstage
						else
							arbeitszeit = 606; //Nach destatis, 11% aller Arbeiter arbeiten 48 stunden in der woche
						startzeit = 450;
						break;
		case "Kind":	arbeitszeit = 492; //Schulzeit pro Woche laut Unicef Umfrage
						startzeit = 450;
						break;
		case "Student":	arbeitszeit = 441; //Studienzeit pro woche laut spiegel
						startzeit = 510;
						break;
		case "Arbeitslos": break;
		default:		System.out.println("Error: Falsche Typeneingabe");
						break;
		}
	}
	

}
