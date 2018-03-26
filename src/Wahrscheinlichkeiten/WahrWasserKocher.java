package Wahrscheinlichkeiten;

import java.util.ArrayList;

import Ger�tePackage.Wasserkocher;
import Haushalt.Person;

public class WahrWasserKocher {

	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*4;
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
	
	public void getWahrWasserKocher(double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot,boolean statData) {
		Wasserkocher wk = new Wasserkocher();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) { //l�uft maximal tmp mal am Tag
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < wk.getBetriebsdauer()-1) { 
						wk.setOnWahrscheinlichkeit(1);
						wk.setOffWahrscheinlichkeit(0);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Wasserkocher gerade nicht benutzt
						if(timeSlot >= 360 && timeSlot <= 540 || timeSlot >= 720 && timeSlot <= 840 || timeSlot >= 1080 && timeSlot <= 1200) { // Benutzung zwischen 8-9 Uhr,12 und 14 Uhr und 18-20 Uhr h�her (evtl Random mit einbinden)
								wk.setOnWahrscheinlichkeit(0.001*occupancy[timeSlot]);
								wk.setOffWahrscheinlichkeit(0.999*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							wk.setOnWahrscheinlichkeit(0.00005);
							wk.setOffWahrscheinlichkeit(0.99995);
						}
						else { //Benutzung �ber den Tag eher Unwarscheinlich
							wk.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
							wk.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
						}
					}	
				}
			}
			else if(timeSlot == 0 && anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
				wk.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
			}
		}
		else { //Keine Ver�nderung niemand zu Hause!
			if(anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(0.0);
				wk.setOffWahrscheinlichkeit(0.0);
			}
		}
		
		if(statAnalysis[timeSlot][aktGer�t] >= 1 && occupancy[timeSlot] > 0 && statData == true) {
			if(anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(wk.getOnWahrscheinlichkeit()+0.01);
				wk.setOffWahrscheinlichkeit(1-wk.getOffWahrscheinlichkeit()+0.01);
			}
		}
		
		if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < wk.getBetriebsdauer()-1 && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(wk.getOnWahrscheinlichkeit() >=  Math.random() && (wk.getOnWahrscheinlichkeit()+wk.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer = 0;
			anzahlAn++;
		}		
	}
	
}
