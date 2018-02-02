package Wahrscheinlichkeiten;

import Ger�tePackage.Mikrowelle;

public class WahrMikrowelle {

	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*3;
	
	public void getWahrMikrowelle(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot) {
		Mikrowelle mw = new Mikrowelle();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < mw.getBetriebsdauer()) {
						System.out.println("test");
						mw.setOnWahrscheinlichkeit(1);
						mw.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == 3) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						mw.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						mw.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Toaster gerade nicht benutzt
						if(timeSlot >= 660 && timeSlot <= 780) { // Benutzung zwischen 11 und 13 Uhr h�her
								mw.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
								mw.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1080 && timeSlot <= 1200) { // Benutzung zwischen 18 und 20 Uhr h�her
							mw.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
							mw.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							mw.setOnWahrscheinlichkeit(0.0001);
							mw.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung �ber den Tag eher Unwarscheinlich
							mw.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							mw.setOffWahrscheinlichkeit(0.99998*occupancy[timeSlot]);
						}
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				mw.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				mw.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Ver�nderung niemand zu Hause!
			if(anzahlAn < tmp) {
				mw.setOnWahrscheinlichkeit(0.0);
				mw.setOffWahrscheinlichkeit(0.0);
			}
		}
		if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < mw.getBetriebsdauer() && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(mw.getOnWahrscheinlichkeit() >=  Math.random() && (mw.getOnWahrscheinlichkeit()+mw.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer = 0;
			anzahlAn++;
		}		
	}
}
