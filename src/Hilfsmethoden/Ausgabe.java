package Hilfsmethoden;
import java.io.IOException;
import java.util.ArrayList;

import Ger�tePackage.*;

public class Ausgabe {
	
	private static String [][] ausgabe;
	private static double [] gesamtLast = new double [1440];
	private static int [] occupancy;
	private static double [][] ger�tAn;
	static ArrayList <String> names;
	
	/**
	 * Erstellt das Array f�r die CSV-Datei
	 * @param occupancy
	 * @param ger�teAn
	 * @param names
	 */
	public static void erstelleArr(int [] Occupancy, double [][] Ger�tAn, ArrayList <String> Names) {
		occupancy = Occupancy;
		ger�tAn = Ger�tAn;
		names = Names;
		ausgabe = new String [1441][ger�tAn[0].length +2];
		for (int i = 0; i < gesamtLast.length; i++) {	//Initialisiert das Array mit 0
			gesamtLast[i] = 0;
		}
		geraete();
		gesamt();
		fuelle();
		try {
			String csv = Create_CSV.create(ausgabe);
			Diagramm.erzeuge(csv);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Fuellt das ausgabe Array mit den gesammelten und berechneten Werten
	 */
	private static void fuelle() {
		ausgabe[0][0] = "Occupancy";
		ausgabe[0][1] = "GesamtLast";
		for (int i = 0; i < names.size(); i++) {		//Schreibt die Namen der Ger�te in die erste Reihe des Arrays
			ausgabe[0][i+2] =  names.get(i);
		}
		
		for (int i = 0; i < ausgabe.length-1; i++) {					//Reihe
			for (int c = 0; c < ausgabe[0].length; c++) {				//Spalte
				if (c == 0) {
					ausgabe[i+1][c] = String.valueOf(occupancy[i]);		//Schreibt die aktuelle Occupancy f�r die Minute i in die erste Spalte des ausgabe Array
				}
				else if (c == 1) {
					ausgabe[i+1][c] = String.valueOf(gesamtLast[i]);	//Schreibt die aktuelle GesamtLast f�r die Minute i in die zweite Spalte des ausgabe Array
				}
				else {
					ausgabe[i+1][c] = String.valueOf(ger�tAn[i][c-2]);	//Schreibt den aktuellen Verbrauch von Ger�t c f�r die Minute i in das ausgabe Array
				}
			}
		}
	}
	
	/**
	 * Berechnet die GesamtLast jeder Minute durch die Geraete
	 * @param ger�teAn
	 * @param gesamtLast
	 */
	private static void gesamt() {
		for (int i = 0; i < ger�tAn.length; i++) {			//Reihe des Arrays
			for (int c = 0; c < ger�tAn[0].length; c++) {	//Spalte des Arrays
				gesamtLast[i] += ger�tAn[i][c];				//Die GesamtLast einer Reihe wird um den Verbrauch jedes Ger�tes dieser Minute erh�ht
				gesamtLast[i] = Math.round(100.0 * gesamtLast[i]) / 100.0;
			}
		}
	}
	
	/**
	 * �berschreibt das ger�teAn Array mit den Verbrauchswerten der Ger�te
	 * @param ger�teAn
	 * @param names
	 */
	private static void geraete() {
		K�hlschrank k�hl = new K�hlschrank();
		//Immer wieder neu den aktuellen Verbrauch des Ger�ts berechnen 
		for (int i = 0; i < names.size(); i++) {	//Spalte des Arrays
			String s = names.get(i);
			switch (s){
			case "toaster" :
				Toaster ts = new Toaster();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = ts.getStandby();	 	//Wenn das Ger�t nicht aktiv genutzt wird, wird der Standbyverbrauch genommen
					}
					else {
						ger�tAn[c][i] *= Math.round(100.0 * ts.randomisieren()) / 100.0;	//Ist das Ger�t aktiv genutzt, berechne den aktuellen Verbrauch. Mit nur 2 Nachkommastellen
					}
				}
				break;
			case "wasserkocher" :
				Wasserkocher wk = new Wasserkocher();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = wk.getStandby();	 	
					}
					else {
						ger�tAn[c][i] *= Math.round(100.0 * wk.randomisieren()) / 100.0;	
					}
				}
				break;
			case "mikrowelle" :
				Mikrowelle mw = new Mikrowelle();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = mw.getStandby();	 	
					}
					else {
						ger�tAn[c][i] *= Math.round(100.0 * mw.randomisieren()) / 100.0;	
					}
				}
				break;
			case "deckenlampe" :
				DeckenLampe dl = new DeckenLampe();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = dl.getStandby();	 	
					}
					else {
						ger�tAn[c][i] *= Math.round(100.0 * dl.randomisieren()) / 100.0;	
					}
				}
				break;
			case "pc":
				PC pc = new PC();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					//Hier noch den Modus vom pc setzen
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = pc.getStandby();
					}
					else {
						ger�tAn[c][i] *= Math.round(100.0 * pc.randomisieren()) / 100.0;
					}
				}
				break;
			case "kaffeemaschine":
				Kaffeemaschine kf = new Kaffeemaschine();
				boolean l�uft = false;
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = kf.getStandby();
						kf.setModus(0);
						l�uft = false;
					}
					else if (ger�tAn[c][i] == 1 && l�uft == false){
						ger�tAn[c][i] *= Math.round(100.0 * kf.randomisieren()) / 100.0;
						kf.setModus(kf.getModus() +1);
						l�uft = true;
					}
					else {
						ger�tAn[c][i] *= Math.round(100.0 * kf.randomisieren()) / 100.0;
					}
				}
				break;
			case "waschmaschine":
				Waschmaschine wm = new Waschmaschine();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = wm.getStandby();
					}
					else {
						for (int t2 = 0; t2 < wm.getModiDauer().size(); t2++) {
							for (int dauer = 0; dauer < (int) wm.getModiDauer().get(t2); dauer++) {
								ger�tAn[c][i] *= Math.round(100.0 * wm.randomisieren()) / 100.0;
								c++;
							}
							wm.setModus(wm.getModus() +1);
						}
						wm.setModus(0);
					}
				}
				break;
			case "trockner":
				Trockner tr = new Trockner();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = tr.getStandby();
					}
					else {
						for (int t2 = 0; t2 < tr.getModiDauer().size(); t2++) {
							for (int dauer = 0; dauer < (int) tr.getModiDauer().get(t2); dauer++) {
								ger�tAn[c][i] *= Math.round(100.0 * tr.randomisieren()) / 100.0;
								c++;
							}
							tr.setModus(tr.getModus() +1);
						}
						tr.setModus(0);
					}
				}
				break;
			case "staubsauger":
				Staubsauger ssg = new Staubsauger();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ssg.setModusDauer(0);
						ssg.setAktuellerVerbrauch(ssg.getMaxVerbrauch());
					}
					else {
						ssg.setModusDauer(ssg.getModusDauer() +1);
						ssg.modify�nderWahrsch();
						ssg.setAktuellerVerbrauch();
						ger�tAn[c][i] *= Math.round(100.0 * ssg.randomisieren()) / 100.0;
					}
				}
				break;
			case "lcdFernseher":
				LCDFernseher lcdF = new LCDFernseher();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						lcdF.setModusDauer(0);
						lcdF.setAktuellerVerbrauch(lcdF.getMaxVerbrauch());
					}
					else {
						lcdF.setModusDauer(lcdF.getModusDauer() +1);
						lcdF.modify�nderWahrsch();
						lcdF.setAktuellerVerbrauch();
						ger�tAn[c][i] *= Math.round(100.0 * lcdF.randomisieren()) / 100.0;
					}
				}
				break;
			case "plasmaFernseher":
				Plasmafernseher pF = new Plasmafernseher();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						pF.setModusDauer(0);
						pF.setAktuellerVerbrauch(pF.getMaxVerbrauch());
					}
					else {
						pF.setModusDauer(pF.getModusDauer() +1);
						pF.modify�nderWahrsch();
						pF.setAktuellerVerbrauch();
						ger�tAn[c][i] *= Math.round(100.0 * pF.randomisieren()) / 100.0;
					}
				}
				break;
			case "k�hlschrank":
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					ger�tAn[c][i] = Math.round(100.0 * k�hl.setAktuellerVerbrauch()) / 100.0;
					k�hl.setBetriebsdauer(k�hl.getBetriebsdauer()+1);
				}
				break;
			case "licht":
				Licht l = new Licht();
				for (int c = 0; c < 1440; c++) { 		//Reihe des Arrays
					if (ger�tAn[c][i] == 0) {
						ger�tAn[c][i] = l.getStandby(); //hat licht einen Standby verbrauch?
					}
					else {
						ger�tAn[c][i] *= Math.round(100.0 * l.randomisieren()) / 100.0;
					}
				}
				break;
			default:
				break;
			}
		}
	}
}
