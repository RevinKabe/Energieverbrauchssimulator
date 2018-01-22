/**
 * Klasse f�r den Haushalt
 */
package Haushalt;

import java.util.ArrayList;

public class Haushalt {
	
	private ArrayList<Person> personen = new ArrayList<Person>();
	private int[] occupancy = new int[1440];

	public Haushalt(ArrayList<Person> personen){
		this.personen = personen;
		initializeOccupancy();
	}
	
	public Haushalt() {
		// TODO Auto-generated constructor stub
	}
	
	public int[] getOccupancy(){
		return occupancy;
	}
	
	//Methode zum errechnen wie viele personen vom eingegebenen typ daheim sind zu einem bestimmten zeitpunkt
	public int countHome(String typ, int timeslot){
		int count = 0;
		for(int i = 0; i < personen.size(); i++){
			if(personen.get(i).getPersonentyp().getTyp().equals(typ) && personen.get(i).getRealAwayTime(timeslot) == 1){
				count++;
			}
		}
		return count;
	}
	
	
	//berechnet wie viele Personen da sind, 0 = keiner, 1 = einer und so weiter
	//erstellt jedes mal ein array mit anderen werten f�r jede person und gesamt
	public void calcOccupancy(){
		for(int j = 0; j < personen.size() ; j++){
			personen.get(j).initializeAwayTime();
			for(int i = 0; i < 1440 ; i++){
				if( personen.get(j).getRealAwayTime(i) == 1)
					occupancy[i]++;
			}
		}
	}
	
	//setzt die Werte am Anfang auf 0, so dass sie sp�ter hoch gez�hlt werden k�nnen
	public void initializeOccupancy(){
		for(int i=0; i< 1440; i++)
			occupancy[i] = 0;
	}
	
	@Override
	public String toString(){
		String ausgabe = "";
		for(int i = 0; i < 1440; i++){
			ausgabe += "Minute: " + (i+1) + "\t" + occupancy[i] + "\t";
			for(int j = 0; j < personen.size(); j++){
				ausgabe += personen.get(j).getRealAwayTime(i) + "\t";
			}
			ausgabe += "\n";
		}
		return ausgabe;
	}

}
