package Wahrscheinlichkeiten;

import java.util.ArrayList;

import Ger�tePackage.Kaffeemaschine;
import Haushalt.Person;

public class WahrKaffeeMaschine {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*5;
	private int [] occupancy = new int[1440];

	public void sucheKind(ArrayList<Person> personen,int [] occupancy) {
		for(int i = 0;i<occupancy.length;i++) {
			this.occupancy[i] = occupancy[i];
		}
		for(int a = 0; a <1440;a++) {
			for(int i = 0; i<personen.size();i++) {
				if(personen.get(i).getPersonentyp().getTyp() == "Kind") {				
					this.occupancy[a] --;
				}
			}
		}
	}
	
	public void getWahrKaffeemaschine(double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot,boolean statData) {
		Kaffeemaschine kM = new Kaffeemaschine();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					//System.out.println("BD: " + betriebsDauer + " MD: " + modiDauer);
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < 1) {
						kM.setOnWahrscheinlichkeit(1);
						kM.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == 1 && ger�tAn[timeSlot-1][aktGer�t] == 1) { //Ger�t war bereits benutzt 
						kM.setOnWahrscheinlichkeit(0.03*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						kM.setOffWahrscheinlichkeit(0.97*occupancy[timeSlot]);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Kaffeemaschine gerade nicht benutzt
						if(timeSlot >= 360 && timeSlot <= 540) { // Benutzung zwischen 6 und 9 Uhr h�her
								kM.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
								kM.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 900 && timeSlot <= 1020) { // Benutzung zwischen 15 und 17 Uhr h�her
							kM.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
							kM.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							kM.setOnWahrscheinlichkeit(0.0001);
							kM.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung �ber den Tag eher Unwarscheinlich
							kM.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							kM.setOffWahrscheinlichkeit(0.99998*occupancy[timeSlot]);
						}
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				kM.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				kM.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Ver�nderung niemand zu Hause!
			if(anzahlAn < tmp) {
				kM.setOnWahrscheinlichkeit(0.0);
				kM.setOffWahrscheinlichkeit(0.0);
			}
		}
		
		if(statAnalysis[timeSlot][aktGer�t] >= 1 && occupancy[timeSlot] != 0) {	//Wert ver�ndern
			if(anzahlAn < tmp) {
				kM.setOnWahrscheinlichkeit(kM.getOnWahrscheinlichkeit()+0.01);
				kM.setOffWahrscheinlichkeit(1-kM.getOffWahrscheinlichkeit()+0.01);
			}
		}
		
		if(timeSlot > 1 && ger�tAn[timeSlot-1][aktGer�t] == 1 && ger�tAn[timeSlot-2][aktGer�t] != 1 && betriebsDauer < 1 && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer++;
			System.out.println("TimeSlot: " + timeSlot);
		}
		else if(kM.getOnWahrscheinlichkeit() >=  Math.random() && (kM.getOnWahrscheinlichkeit()+kM.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer = 0;
			anzahlAn++;
			System.out.println("TimeSlot2: " + timeSlot);
		}		
	}
}
