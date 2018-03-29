package Wahrscheinlichkeiten;

import Ger�tePackage.PC;
/**
 * Diese Klasse regelt die Wahrscheinlichkeit der PC.
 * @author Julian Gr�nker
 */	
public class WahrPc {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*3;
	private double dauer = Math.floor((Math.random() * 100) + 10);
	
	public void getWahrPc(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot, boolean statData) {
		PC pC = new PC();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < dauer) {
						pC.setOnWahrscheinlichkeit(1);
						pC.setOffWahrscheinlichkeit(0);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn PC gerade nicht benutzt
						if(timeSlot >= 1080 && timeSlot <= 1440) { // Benutzung zwischen 18 und 24 Uhr am h�chsten
								pC.setOnWahrscheinlichkeit(0.003*occupancy[timeSlot]);
								pC.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 840 && timeSlot <= 1079) { // Benutzung zwischen 14 und 18 Uhr h�her
							pC.setOnWahrscheinlichkeit(0.002*occupancy[timeSlot]);
							pC.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else { //Benutzung �ber den Tag eher Unwarscheinlich
							pC.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
							pC.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
						}
					}
					else if(betriebsDauer == dauer) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						pC.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						pC.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				pC.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
				pC.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
			}
		}
		else { //Keine Ver�nderung niemand zu Hause!
			if(anzahlAn < tmp) {
				pC.setOnWahrscheinlichkeit(0.0);
				pC.setOffWahrscheinlichkeit(0.0);
			}
		}
		
		if(statAnalysis[timeSlot][aktGer�t] >= 1 && occupancy[timeSlot] != 0 && statData == true) {	//Wert ver�ndern
			if(anzahlAn < tmp) {
				pC.setOnWahrscheinlichkeit(pC.getOnWahrscheinlichkeit()+0.002);
				pC.setOffWahrscheinlichkeit(1-pC.getOffWahrscheinlichkeit()+0.002);
			}
		}
		
		if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < dauer && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(pC.getOnWahrscheinlichkeit() >=  Math.random() && (pC.getOnWahrscheinlichkeit()+pC.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer = 0;
			anzahlAn++;
			dauer = Math.floor((Math.random() * 100) + 10);
		}		
	}
}
