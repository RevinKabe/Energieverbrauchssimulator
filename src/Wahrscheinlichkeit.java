import java.util.ArrayList;

import Ger�tePackage.Toaster;
import Haushalt.Person;

public class Wahrscheinlichkeit 
{
	int betriebsDauer = 0;
	public Wahrscheinlichkeit() {
		
	}	
	
	//�berp�rft die Wahrscheinlichkeit eines bestimmten Zeitslots
	//Benutzt zus�tzlich V�rg�nger (momentan f�r Toaster zu geschnitten zum test)
	public void checkStatus(ArrayList<Person> pList, double [] statAnalysis,double[][] ger�tAn,int auswertDaten,String ger�t,int ger�te, int timeSlot) {
		Toaster ts = new Toaster();	
		double randomNum = Math.random();
		for(int person = 0; person<pList.size();person++)	//Durchlaufe alle Personen um zu checken ob jemand zu Hause ist
		{
			if(pList.get(person).getRealAwayTime(ger�te)== 1) {	//Wenn jemand zu Hause
				if(timeSlot > 0) {
					//Nach Ger�teTyp filter um betriebsDauer festzustellen !
					if(ger�tAn[timeSlot-1][ger�te] == 1 && betriebsDauer < ts.getBetriebsdauer() && betriebsDauer >= 0) {
						ts.setOnWahrscheinlichkeit(1);
						ts.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == 2) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						ts.setOnWahrscheinlichkeit(0.0001);
						ts.setOffWahrscheinlichkeit(0.9999);
						betriebsDauer = 0;
						ts.setBenutzt(false);
					}
					else if (ger�tAn[timeSlot-1][ger�te] == 0 && pList.size() == 1) {	//Wenn Toaster gerade nicht benutzt
						if(timeSlot >= 390 && timeSlot <= 600) { // Benutzung zwischen 6:30 und 10 Uhr h�her
								ts.setOnWahrscheinlichkeit(0.003);
								ts.setOffWahrscheinlichkeit(0.997);
						}
						else if(timeSlot >= 1200 || timeSlot <= 300) { //Benutzung nach 20 Uhr unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.0001);
							ts.setOffWahrscheinlichkeit(0.9999);
						}
						else { //Benutzung �ber den Tag eher Unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.002);
							ts.setOffWahrscheinlichkeit(0.998);
						}
					}
					else if(pList.size() > 1 && ger�tAn[timeSlot-1][ger�te] == 0){	//mehr als 1 Person im Haushalt, Toaster evtl. 2 mal laufen lassen
						if(timeSlot >= 390 && timeSlot <= 600) {
							ts.setOnWahrscheinlichkeit(0.003*pList.size());
							ts.setOffWahrscheinlichkeit(1-(0.003*pList.size()));
						}
						else {
							ts.setOnWahrscheinlichkeit(0.0002*pList.size());
							ts.setOffWahrscheinlichkeit(1-0.0002*pList.size());
						}
					}
				}
				else if(timeSlot == 0) {
					ts.setOnWahrscheinlichkeit(0.002);
					ts.setOffWahrscheinlichkeit(0.998);
				}
			}
			else { //Keine Ver�nderung niemand zu Hause!
				ts.setOnWahrscheinlichkeit(0.0);
				ts.setOffWahrscheinlichkeit(0.0);
			}
		}
		//EVTL mit statistischen Daten draufrechnen um Genauigkeit zu erh�hen!!
		
		//System.out.println(ts.getOnWahrscheinlichkeit());
		//System.out.println(ts.getOffWahrscheinlichkeit());
		//System.out.println(randomNum);
		
		if(ts.getOffWahrscheinlichkeit() <= randomNum && (ts.getOnWahrscheinlichkeit()+ts.getOffWahrscheinlichkeit() != 0)) {
			betriebsDauer++;
			ts.setBenutzt(true);
			ger�tAn[timeSlot][ger�te] = 1;
		}		
	}
}
