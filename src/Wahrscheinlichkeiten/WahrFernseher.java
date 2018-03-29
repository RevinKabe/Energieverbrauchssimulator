package Wahrscheinlichkeiten;

import java.util.ArrayList;

import Ger�tePackage.LCDFernseher;
import Ger�tePackage.Mikrowelle;
import Ger�tePackage.Plasmafernseher;
import Haushalt.Person;
/**
 * Diese Klasse regelt die Wahrscheinlichkeit der Fernsehr.
 * @author Julian Gr�nker
 */	
public class WahrFernseher {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*3;
	private int rndm = Math.random() < 0.5 ? -1 : 1;
	private double maxZeit = Math.floor((Math.random() * 250) + 15);
	private boolean hatKind = false;
	private int anzKind = 0;
	
	public void sucheKind(ArrayList<Person> personen) {
		for(int i = 0;i<personen.size();i++) {
			if(personen.get(0).getPersonentyp().getTyp() == "Kind") {
				hatKind = true;
				anzKind++;
				tmp = tmp+2;
				maxZeit = maxZeit+4*anzKind;
			}
		}
	}
	public void getWahrFernseher(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot,ArrayList<Person> personen,boolean statData, String type) {
		if(type == "LCD") {
			LCDFernseher lF = new LCDFernseher();
			if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
				if(timeSlot > 0) {	//Falls nicht erster Eintrag
					if(anzahlAn < tmp) {
						if(ger�tAn[timeSlot-1][aktGer�t] == 0) {
							maxZeit = Math.floor((Math.random() * 250) + 15);
						}
						if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < maxZeit) {
							lF.setOnWahrscheinlichkeit(1);
							lF.setOffWahrscheinlichkeit(0);
						}
						else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Fernseher gerade nicht benutzt
							if(hatKind == true) {
								if(timeSlot >= 420 && timeSlot <= 1200) { // Benutzung zwischen 17 und 24 Uhr h�her
									lF.setOnWahrscheinlichkeit(0.005*occupancy[timeSlot]);
									lF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
								}
							}
							if(timeSlot >= 1020 && timeSlot <= 1440) { // Benutzung zwischen 17 und 24 Uhr h�her
									lF.setOnWahrscheinlichkeit(0.003*occupancy[timeSlot]);
									lF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
							}
							else if(timeSlot >= 1441 || timeSlot <= 420) { //Benutzung nach 24 Uhr unwarscheinlich
								lF.setOnWahrscheinlichkeit(0.00005);
								lF.setOffWahrscheinlichkeit(0.99995);
							}
							else { //Benutzung �ber den Tag eher Unwarscheinlich
								lF.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
								lF.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
							}
						}
						else if(betriebsDauer == maxZeit) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
							lF.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
							lF.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
						}
					}
				}
				else if(timeSlot == 0 && anzahlAn < tmp) {
					lF.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
					lF.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
				}
			}
			else { //Keine Ver�nderung niemand zu Hause!
				if(anzahlAn < tmp) {
					lF.setOnWahrscheinlichkeit(0.0);
					lF.setOffWahrscheinlichkeit(0.0);
				}
			}
			if(personen.contains("Kind")) {
				for(int i = 0;i<personen.size();i++) {
					lF.setOnWahrscheinlichkeit(lF.getOnWahrscheinlichkeit()+0.01*anzKind);
					lF.setOffWahrscheinlichkeit(1-lF.getOffWahrscheinlichkeit()+0.01*anzKind);
				}
			}
			
			//if(statAnalysis[timeSlot][aktGer�t] >= 1 && occupancy[timeSlot] != 0 && statData == true) { Falsche ECO Daten ?
			//	if(anzahlAn < tmp) {
			//		lF.setOnWahrscheinlichkeit(lF.getOnWahrscheinlichkeit()+0.01);
			//		lF.setOffWahrscheinlichkeit(1-lF.getOffWahrscheinlichkeit()+0.01);
			//	}
			//}
			
			if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < maxZeit && occupancy[timeSlot] != 0) {
				ger�tAn[timeSlot][aktGer�t] = 1;
				betriebsDauer++;
				//System.out.println("TimeSlot: " + timeSlot);
			}
			else if(lF.getOnWahrscheinlichkeit() >=  Math.random() && (lF.getOnWahrscheinlichkeit()+lF.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
				ger�tAn[timeSlot][aktGer�t] = 1;
				betriebsDauer = 0;
				anzahlAn++;
			}		
		}
		else if(type == "Plasma") {
			Plasmafernseher pF = new Plasmafernseher();
			if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
				if(timeSlot > 0) {	//Falls nicht erster Eintrag
					if(anzahlAn < tmp) {
						if(ger�tAn[timeSlot-1][aktGer�t] == 0) {
							maxZeit = Math.floor((Math.random() * 250) + 15);
						}
						if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < maxZeit) {
							pF.setOnWahrscheinlichkeit(1);
							pF.setOffWahrscheinlichkeit(0);
						}
						else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Fernseher gerade nicht benutzt
							if(hatKind == true) {
								if(timeSlot >= 420 && timeSlot <= 1200) { // Benutzung zwischen 17 und 24 Uhr h�her
									pF.setOnWahrscheinlichkeit(0.005*occupancy[timeSlot]);
									pF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
								}
							}
							if(timeSlot >= 1020 && timeSlot <= 1440) { // Benutzung zwischen 17 und 24 Uhr h�her
								pF.setOnWahrscheinlichkeit(0.003*occupancy[timeSlot]);
								pF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
							}
							else if(timeSlot >= 1441 || timeSlot <= 420) { //Benutzung nach 24 Uhr unwarscheinlich
								pF.setOnWahrscheinlichkeit(0.00005);
								pF.setOffWahrscheinlichkeit(0.99995);
							}
							else { //Benutzung �ber den Tag eher Unwarscheinlich
								pF.setOnWahrscheinlichkeit(0.0001*occupancy[timeSlot]);
								pF.setOffWahrscheinlichkeit(0.9999*occupancy[timeSlot]);
							}
						}
						else if(betriebsDauer == maxZeit) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
							pF.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
							pF.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
						}
					}
				}
				else if(timeSlot == 0 && anzahlAn < tmp) {
					pF.setOnWahrscheinlichkeit(0.0002*occupancy[timeSlot]);
					pF.setOffWahrscheinlichkeit(0.9998*occupancy[timeSlot]);
				}
			}
			else { //Keine Ver�nderung niemand zu Hause!
				if(anzahlAn < tmp) {
					pF.setOnWahrscheinlichkeit(0.0);
					pF.setOffWahrscheinlichkeit(0.0);
				}
			}
			if(personen.contains("Kind"))
			{
				for(int i = 0;i<personen.size();i++) {
					pF.setOnWahrscheinlichkeit(pF.getOnWahrscheinlichkeit()+0.01*anzKind);
					pF.setOffWahrscheinlichkeit(1-pF.getOffWahrscheinlichkeit()+0.01*anzKind);
				}
			}
			
			//if(statAnalysis[timeSlot][aktGer�t] >= 1 && occupancy[timeSlot] != 0 && statData == true) { Falsche ECO Daten ?
			//	if(anzahlAn < tmp) {
			//		lF.setOnWahrscheinlichkeit(lF.getOnWahrscheinlichkeit()+0.01);
			//		lF.setOffWahrscheinlichkeit(1-lF.getOffWahrscheinlichkeit()+0.01);
			//	}
			//}
			
			if(timeSlot > 0 && ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < maxZeit && occupancy[timeSlot] != 0) {
				ger�tAn[timeSlot][aktGer�t] = 1;
				betriebsDauer++;
				//System.out.println("TimeSlot: " + timeSlot);
			}
			else if(pF.getOnWahrscheinlichkeit() >=  Math.random() && (pF.getOnWahrscheinlichkeit()+pF.getOffWahrscheinlichkeit() != 0) && occupancy[timeSlot] != 0) {
				ger�tAn[timeSlot][aktGer�t] = 1;
				betriebsDauer = 0;
				anzahlAn++;
			}		
		}
	}

	public int getRndm() {
		return rndm;
	}

	public void setRndm(int rndm) {
		this.rndm = rndm;
	}

	public double getMaxZeit() {
		return maxZeit;
	}

	public void setMaxZeit(double maxZeit) {
		this.maxZeit = maxZeit;
	}
}
