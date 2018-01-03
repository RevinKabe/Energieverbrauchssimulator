
public class Wahrscheinlichkeit 
{
	int AktWahrscheinlichkeit = 0;
	int x = 90; //Ger�telauf dauer muss von Ger�t importiert werden
	
	public Wahrscheinlichkeit() {
		
	}	
	
	//�berp�rft die Wahrscheinlichkeit eines bestimmten Zeitslots
	//Benutzt zus�tzlich Nachbarn
	public void checkStatus(double [] statAnalysis,double[][] ger�tAn,int auswertDaten,int ger�te, int timeSlot) {
		for(int i = 0;i<ger�te;i++) {
			if(timeSlot > 0) //Wenn nicht erstes Elelemnt
			{
				if(ger�tAn[timeSlot-1][i] == 1) {	//Falls Ger�t in vorherigen Element an war
					//Ben�tigt check auf Ger�teTyp und Ger�t
					//Variable je nach dem wie lange das Ger�t an
					AktWahrscheinlichkeit = +75;
				}
				else if(ger�tAn[timeSlot-1][i] == 0) { //Falls Ger�t nicht im vorherigen Element an war
					if(statAnalysis[timeSlot-1] >= (auswertDaten/2)) { //Falls Ger�t in statistischen Daten an war
						AktWahrscheinlichkeit = +75;
					}
					else {
						AktWahrscheinlichkeit = +25;
					}
				}
			}
			if(AktWahrscheinlichkeit >= 70) {
				if(Math.random()*x >= 70) {
					ger�tAn[timeSlot][i] = 1;
				}
			}
		}
	}
}
