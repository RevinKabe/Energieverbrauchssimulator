package Wahrscheinlichkeiten;

import Ger�tePackage.Toaster;

public class WahrToaster {

	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*2;
	
	public void getWahrToaster(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot) {
		Toaster ts = new Toaster();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < ts.getBetriebsdauer()) {
						ts.setOnWahrscheinlichkeit(1);
						ts.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == 2) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						ts.setOnWahrscheinlichkeit(0.0005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						ts.setOffWahrscheinlichkeit(0.99995*occupancy[timeSlot]);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Toaster gerade nicht benutzt
						if(timeSlot >= 390 && timeSlot <= 600) { // Benutzung zwischen 6:30 und 10 Uhr h�her
								ts.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
								ts.setOffWahrscheinlichkeit(0.95*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.0001);
							ts.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung �ber den Tag eher Unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							ts.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
						}
					}
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				ts.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
				ts.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Ver�nderung niemand zu Hause!
			if(anzahlAn < tmp) {
				ts.setOnWahrscheinlichkeit(0.0);
				ts.setOffWahrscheinlichkeit(0.0);
			}
		}
		if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < ts.getBetriebsdauer() && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(ts.getOnWahrscheinlichkeit() >=  Math.random() && (ts.getOnWahrscheinlichkeit()+ts.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer = 0;
			anzahlAn++;
		}		
	}
}
