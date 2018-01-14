package Wahrscheinlichkeiten;
import Ger�tePackage.Toaster;
import Ger�tePackage.Wasserkocher;

public class Wahrscheinlichkeit_Typ1 
{
	int betriebsDauer = 0;
	int anzahlAn = 0;
	double tmp = Math.random()*5;

	public Wahrscheinlichkeit_Typ1() {
		
	}	
	//�berp�rft die Wahrscheinlichkeit eines bestimmten Zeitslots
	//Benutzt zus�tzlich V�rg�nger
	//Jahreszeit f�r (Licht,Computer,Fernseher)
	
	//Abstand verbessern

	public void reset() {
		betriebsDauer = 0;
		anzahlAn = 0;
		tmp = Math.random()*5;
	}
	public void getWahrWasserKocher(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot) {
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
								wk.setOnWahrscheinlichkeit(0.002*occupancy[timeSlot]);
								wk.setOffWahrscheinlichkeit(0.998*occupancy[timeSlot]);
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
		if (timeSlot > 0 && occupancy[timeSlot] == 0 && occupancy[timeSlot-1] == 0 && occupancy[timeSlot+1] == 0){ //Keine Ver�nderung niemand zu Hause!
			if(anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(0.0);
				wk.setOffWahrscheinlichkeit(0.0);
			}
		}
		else if(occupancy[timeSlot] == 0)
		{
			if(anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(0.0);
				wk.setOffWahrscheinlichkeit(0.0);
			}
		}
		if(timeSlot > 200 && betriebsDauer == 0 && anzahlAn == 0) {
			for(int i = 200; i>0 ;i--) {
				if(ger�tAn[timeSlot-i][aktGer�t] == 1) {
					wk.setOnWahrscheinlichkeit(0.00001*occupancy[timeSlot]);
					wk.setOffWahrscheinlichkeit(0.99999*occupancy[timeSlot]);
				}
			}
		}
		if(statAnalysis[timeSlot][aktGer�t] >= 1) {	//Wert ver�ndern
			if(anzahlAn < tmp) {
				wk.setOnWahrscheinlichkeit(wk.getOnWahrscheinlichkeit()+0.02);
				wk.setOffWahrscheinlichkeit(wk.getOffWahrscheinlichkeit()+0.02);
			}
		}
		if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < wk.getBetriebsdauer()-1) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(wk.getOnWahrscheinlichkeit() >=  Math.random() && (wk.getOnWahrscheinlichkeit()+wk.getOffWahrscheinlichkeit() != 0)) {
			//wk.setBenutzt(true);
			ger�tAn[timeSlot][aktGer�t] = 1;
			//if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < wk.getBetriebsdauer()) {
			//	betriebsDauer++;
			//}
			//else
			//{
				betriebsDauer = 0;
				anzahlAn++;
				//wk.setBenutzt(false);
			//}
			if(anzahlAn > tmp) //bearbeiten !!!
			{
				//anzahlAn--;
			}
			//System.out.println("TimeSlot: " + timeSlot);
			//System.out.println("AnzahlAn: " + anzahlAn + " , tmp: " +tmp);
			//System.out.println(betriebsDauer);
		}		
	}
	
	public void getWahrToaster(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot) {
		Toaster ts = new Toaster();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < ts.getBetriebsdauer()-1) {
						ts.setOnWahrscheinlichkeit(1);
						ts.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == 2) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						ts.setOnWahrscheinlichkeit(0.0005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						ts.setOffWahrscheinlichkeit(0.99995*occupancy[timeSlot]);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Toaster gerade nicht benutzt
						if(timeSlot >= 390 && timeSlot <= 600) { // Benutzung zwischen 6:30 und 10 Uhr h�her
								ts.setOnWahrscheinlichkeit(0.05*occupancy[timeSlot]);
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
		if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < ts.getBetriebsdauer()-1) {
			ger�tAn[timeSlot][aktGer�t] = 1;
			betriebsDauer++;
			//System.out.println("TimeSlot: " + timeSlot);
		}
		else if(ts.getOnWahrscheinlichkeit() >=  Math.random() && (ts.getOnWahrscheinlichkeit()+ts.getOffWahrscheinlichkeit() != 0)) {
			//wk.setBenutzt(true);
			ger�tAn[timeSlot][aktGer�t] = 1;
			//if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < wk.getBetriebsdauer()) {
			//	betriebsDauer++;
			//}
			//else
			//{
				betriebsDauer = 0;
				anzahlAn++;
				//wk.setBenutzt(false);
			//}
			if(anzahlAn > tmp) //bearbeiten !!!
			{
				//anzahlAn--;
			}
			//System.out.println("TimeSlot: " + timeSlot);
			//System.out.println("AnzahlAn: " + anzahlAn + " , tmp: " +tmp);
			//System.out.println(betriebsDauer);
		}		
	}
}
