import java.util.ArrayList;

import Ger�tePackage.Toaster;
import Haushalt.Person;

public class Wahrscheinlichkeit 
{
	int betriebsDauer = 0;
	int tmp = 0;
	public Wahrscheinlichkeit() {
		
	}	
	
	//�berp�rft die Wahrscheinlichkeit eines bestimmten Zeitslots
	//Benutzt zus�tzlich V�rg�nger (momentan f�r Toaster zu geschnitten zum test)
	public void checkStatus(ArrayList<Person> pList, double [] statAnalysis,double[][] ger�tAn,int auswertDaten,String ger�t,int ger�te, int timeSlot) {
		Toaster ts = new Toaster();	
		for(int person = 0; person<pList.size();person++)	//Durchlaufe alle Personen um zu checken ob jemand zu Hause ist
		{
			if(pList.get(person).getRealAwayTime(ger�te)== 1) {	//Wenn jemand zu Hause
				if(timeSlot > 0 && person == 0) {
					//Nach Ger�teTyp filter um betriebsDauer festzustellen !
					if(ger�tAn[timeSlot-1][ger�te] == 1 && betriebsDauer < ts.getBetriebsdauer() && betriebsDauer >= 0) {
						ts.setOnWahrscheinlichkeit(1);
						ts.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == 2) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						ts.setOnWahrscheinlichkeit(0.005);
						ts.setOffWahrscheinlichkeit(0.9995);
						betriebsDauer = 0;
						ts.setBenutzt(false);
					}
					else if (ger�tAn[timeSlot-1][ger�te] == 0) {	//Wenn Toaster gerade nicht benutzt
						if(timeSlot >= 390 && timeSlot <= 600) { // Benutzung zwischen 6:30 und 10 Uhr h�her
								ts.setOnWahrscheinlichkeit(0.003);
								ts.setOffWahrscheinlichkeit(0.997);
						}
						else if(timeSlot >= 1200 || timeSlot <= 300) { //Benutzung nach 20 Uhr unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.001);
							ts.setOffWahrscheinlichkeit(0.999);
						}
						else { //Benutzung �ber den Tag eher Unwarscheinlich
							ts.setOnWahrscheinlichkeit(0.002);
							ts.setOffWahrscheinlichkeit(0.998);
						}
					}
				}
				else if(person >= 1 && timeSlot > 1) {
					if(ger�tAn[timeSlot-2][ger�te] == 1 && ger�tAn[timeSlot-1][ger�te] == 1) { //Ger�t wurde bereits benutzt, da mehr Personen, wahrscheinlichkeit gr��er nochmal benutzt zu werden
						ts.setOnWahrscheinlichkeit(0.050*person);
						ts.setOffWahrscheinlichkeit(1-(0.050*person));
						tmp = tmp+1;
					}
					else if(ger�tAn[timeSlot-1][ger�te] == 1 && ger�tAn[timeSlot-2][ger�te] == 0 && betriebsDauer < ts.getBetriebsdauer() && betriebsDauer >= 0 || tmp == 1) {
						ts.setOnWahrscheinlichkeit(1);
						ts.setOffWahrscheinlichkeit(0);
						tmp = 0;
					}
					else if(betriebsDauer == 2) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						ts.setOnWahrscheinlichkeit(0.001);
						ts.setOffWahrscheinlichkeit(0.999);
						betriebsDauer = 0;
						ts.setBenutzt(false);
					}
					else if (ger�tAn[timeSlot-1][ger�te] == 0) {	//Wenn Toaster gerade nicht benutzt
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
		
		if(ts.getOnWahrscheinlichkeit() >=  Math.random() && (ts.getOnWahrscheinlichkeit()+ts.getOffWahrscheinlichkeit() != 0)) {
			betriebsDauer++;
			ts.setBenutzt(true);
			ger�tAn[timeSlot][ger�te] = 1;
		}		
	}
}
