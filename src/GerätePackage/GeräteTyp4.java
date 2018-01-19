package Ger�tePackage;

import java.util.ArrayList;
import java.util.List;

public abstract class Ger�teTyp4 
{
	protected double maxVerbrauch;
	protected double minVerbrauch;
	protected double aktuellerVerbrauch;
	protected double maxSchwankung;
	protected double minSchwankung;
	protected int betriebsdauer = 0;
	protected int modus;
	protected int modusDauer;
	protected List <Double> modusVerbrauch = new ArrayList<>();
	
	//Dauer f�r jeden Modus mit gleichbleibendem Verbrauch (ohne Ber�cksichtigung von Schwankungen)
	protected List <Integer> maxModusDauer = new ArrayList<>();
	protected boolean benutzt = true;
	
	
	public Ger�teTyp4(double Max_Verbrauch, double Min_Verbrauch, double Max_Schwankung, double Min_Schwankung){
		
		this.maxVerbrauch = Max_Verbrauch;
		this.minVerbrauch = Min_Verbrauch;
		this.maxSchwankung = Max_Schwankung;
		this.minSchwankung = Min_Schwankung;
	}
	
	public void setBetriebsdauer(int Betriebs_Dauer){
		this.betriebsdauer = Betriebs_Dauer;
	}
	
	public void setBenutzt(boolean Benutzt) {
		this.benutzt = Benutzt;
	}
	
	public boolean getBenutzt() {
		return benutzt;
	}
	
	public double getMaxVerbrauch() {
		return maxVerbrauch;
	}

	public void setMaxVerbrauch(double maxVerbrauch) {
		this.maxVerbrauch = maxVerbrauch;
	}

	public double getAktuellerVerbrauch() {
		return aktuellerVerbrauch;
	}

	public void setAktuellerVerbrauch(double aktuellerVerbrauch) {
		this.aktuellerVerbrauch = aktuellerVerbrauch;
	}
}

