package Wahrscheinlichkeiten;

import Ger�tePackage.K�hlschrank;

public class WahrK�hlschrank {
	
	public void getWahrK�hlschrank(int timeSlot,double[][] ger�tAn,int aktGer�t,K�hlschrank ks) {
		ks.setBetriebsdauer(timeSlot);
		ger�tAn[timeSlot][aktGer�t] = ks.setAktuellerVerbrauch();
	}
}
