package Wahrscheinlichkeiten;

import java.util.ArrayList;

import Ger�tePackage.LCDFernseher;
import Ger�tePackage.Mikrowelle;
import Haushalt.Person;

public class WahrFernseher {
	private int betriebsDauer = 0;
	private int anzahlAn = 0;
	private double tmp = Math.random()*3;
	private int rndm = Math.random() < 0.5 ? -1 : 1;
	private double maxZeit = 15+(Math.random()*(rndm*5));
	private boolean hatKind = false;
	
	public void sucheKind(ArrayList<Person> personen) {
		if(personen.get(0).getPersonentyp().getTyp() == "Kind") {
			hatKind = true;
			tmp = tmp+2;
		}
	}
	//nicht 100% fertig
	public void getWahrFernseher(int [] occupancy, double [][] statAnalysis,double[][] ger�tAn,int aktGer�t, int timeSlot) {
		LCDFernseher lF = new LCDFernseher();
		if(occupancy[timeSlot] > 0) {	//Falls jemand Zuhause
			if(timeSlot > 0) {	//Falls nicht erster Eintrag
				if(anzahlAn < tmp) {
					if(ger�tAn[timeSlot-1][aktGer�t] == 1 && betriebsDauer < maxZeit) {
						lF.setOnWahrscheinlichkeit(1);
						lF.setOffWahrscheinlichkeit(0);
					}
					else if(betriebsDauer == maxZeit) { //Ger�t war bereits benutzt wahrscheinlichkeit sehr gering nochmal benutzt zu werden
						lF.setOnWahrscheinlichkeit(0.00005*occupancy[timeSlot]);		//Wahrscheinlichkeit * Anzahl der Personen die anwesend sind
						lF.setOffWahrscheinlichkeit(0.999995*occupancy[timeSlot]);
					}
					else if (ger�tAn[timeSlot-1][aktGer�t] == 0) {	//Wenn Fernseher gerade nicht benutzt
						if(hatKind == true) {
							if(timeSlot >= 420 && timeSlot <= 1200) { // Benutzung zwischen 17 und 24 Uhr h�her
								lF.setOnWahrscheinlichkeit(0.005*occupancy[timeSlot]);
								lF.setOffWahrscheinlichkeit(0.995*occupancy[timeSlot]);
							}
						}
						if(timeSlot >= 1020 && timeSlot <= 1440) { // Benutzung zwischen 17 und 24 Uhr h�her
								lF.setOnWahrscheinlichkeit(0.0005*occupancy[timeSlot]);
								lF.setOffWahrscheinlichkeit(0.9995*occupancy[timeSlot]);
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
