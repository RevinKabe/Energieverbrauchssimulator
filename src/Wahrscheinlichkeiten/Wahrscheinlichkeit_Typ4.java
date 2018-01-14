package Wahrscheinlichkeiten;

import Ger�tePackage.K�hlschrank;

public class Wahrscheinlichkeit_Typ4 {

	private int betriebsDauer = 0;
	private int anzahlAn = 0;

	//Nicht Fertig !!!!!
	public void getWahrK�hlschrank(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot) {
		K�hlschrank ks = new K�hlschrank();
		int tempZahl = 0;
		
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < Math.random()*5)
					{
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer <= Math.random()*45) {
						ks.setOnWahrscheinlichkeit(0.5*occupancy[timeSlot]);
						ks.setOffWahrscheinlichkeit(0.5*occupancy[timeSlot]);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Toaster gerade nicht benutzt
						if(timeSlot >= 900 && timeSlot <= 1140 || timeSlot >= 540 && timeSlot <= 720) { // Benutzung zwischen 15-19 Uhr und 9-12 Uhr h�her (evtl Random mit einbinden)
								ks.setOnWahrscheinlichkeit(0.002*occupancy[timeSlot]);
								ks.setOffWahrscheinlichkeit(0.998*occupancy[timeSlot]);
						}
						else if(timeSlot >= 1200 || timeSlot <= 330) { //Benutzung nach 20 Uhr unwarscheinlich
							ks.setOnWahrscheinlichkeit(0.0001);
							ks.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung �ber den Tag eher Unwarscheinlich
							ks.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
							ks.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
						}
					}	
				}
				else if(timeSlot == 0) {
					ks.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
					ks.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
				}
			}
			else {
				ks.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
				ks.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
			}
		}
		else { //Keine Ver�nderung niemand zu Hause!
			ks.setOnWahrscheinlichkeit(0.0);
			ks.setOffWahrscheinlichkeit(0.0);
		}
		if(timeSlot > 100 && betriebsDauer == 0 && anzahlAn == 0) {
			for(int i = 100; i>0 ;i--)
			{
				if(ger�tAn[timeSlot-i][aktGer�t] == 1)
				{
					sb.setOnWahrscheinlichkeit(0.00001*occupancy[timeSlot]);
					sb.setOffWahrscheinlichkeit(0.99999*occupancy[timeSlot]);
				}
			}
		}
		//EVTL mit statistischen Daten draufrechnen um Genauigkeit zu erh�hen!! (Keine Daten f�r Staubsauger)!!
		
		if(ks.getOnWahrscheinlichkeit() >=  Math.random() && (sb.getOnWahrscheinlichkeit()+sb.getOffWahrscheinlichkeit() != 0)) {
			ks.setBenutzt(true);
			ger�tAn[timeSlot][aktGer�t] = 1;
			anzahlAn++;
			if(ger�tAn[timeSlot-1][aktGer�t] == 1) {
				anzahlAn--;
				betriebsDauer++;
			}
			else {
				betriebsDauer = 0;
			}
		}		
	}
}
