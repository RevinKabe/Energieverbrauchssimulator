package Wahrscheinlichkeiten;

import Ger�tePackage.K�hlschrank;

public class Wahrscheinlichkeit_Typ4 {

	public void getWahrK�hlschrank(int timeSlot,double[][] ger�tAn,int aktGer�t) {
		K�hlschrank ks = new K�hlschrank();
		ks.setBetriebsdauer(timeSlot);
		ger�tAn[timeSlot][aktGer�t] = ks.getAktuellerVerbrauch();
	}
}
