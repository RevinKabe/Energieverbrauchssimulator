import Ger�tePackage.*;

public class Ausgabe {
	
	public static void main(String [] args) {
		
	}
	
	/**
	 * Erstellt das Array f�r die CSV-Datei
	 * @param occupancy
	 * @param ger�teAn
	 * @param names
	 */
	public static void erstelleArr(int [] occupancy, double [][] ger�teAn, String [] names) {
		String [][] ausgabe = new String [1441][ger�teAn[0].length +2];
		ausgabe[0][0] = "Occupancy";
		ausgabe[0][1] = "GesamtLast";
		for (int i = 0; i < names.length; i++) {	//Schreibt die Namen der Ger�te in die erste Reihe des Arrays
			ausgabe[0][i+2] =  names[i];
		}
		ger�te(ger�teAn, names);
	}
	
	/**
	 * �berschreibt das ger�teAn Array mit den Verbrauchswerten der Ger�te
	 * @param ger�teAn
	 * @param names
	 */
	private static void ger�te(double [][] ger�teAn, String [] names) {
		//Immer wieder neu den aktuellen Verbrauch des Ger�ts berechnen 
		for (int i = 0; i < names.length; i++) {	//Spalte des Arrays
			String s = names[i];
			for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
				switch (s){
				case "Toaster" :
					Toaster ts = new Toaster();
					if (ger�teAn[c][i] == 0) {
						ger�teAn[c][i] = ts.getStandby();	 	//Wenn das Ger�t nicht aktiv genutzt wird, wird der Standbyverbrauch genommen
					}
					else {
						ger�teAn[c][i] *= ts.randomisieren();	//Ist das Ger�t aktiv genutzt, berechne den aktuellen Verbrauch
					}
					break;
				case "PC":
					PC pc = new PC();
					//Hier noch den Modus vom pc setzen
					if (ger�teAn[c][i] == 0) {
						ger�teAn[c][i] = pc.getStandby();
					}
					else {
						ger�teAn[c][i] *= pc.randomisieren();
					}
					break;
				case "Staubsauger":
					Staubsauger ssg = new Staubsauger();
					//woher soll ich den aktuellen verbrauch bekommen?
					if (ger�teAn[c][i] == 0) {
						ger�teAn[c][i] = ssg.getStandby(); //macht das hier Sinn, weil der Staubsauger nur im Gebrauch angeschlossen ist
					}
					else {
						ger�teAn[c][i] *= ssg.randomisieren(Aktueller_Verbrauch, Schwankung);
					}
				case "K�hlschrank":
					//K�hlschrakn erstellen
					break;
				case "Licht":
					Licht l = new Licht();
					if (ger�teAn[c][i] == 0) {
						ger�teAn[c][i] = l.getStandby(); //hat licht einen Standby verbrauch?
					}
					else {
						ger�teAn[c][i] *= l.randomisieren(Aktueller_Verbrauch, Schwankung);
					}
					break;
				default:
					break;
			}
			}
		}
	}
}
