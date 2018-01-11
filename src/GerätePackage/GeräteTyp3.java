package Ger�tePackage;

public abstract class Ger�teTyp3 
{
	double maxVerbrauch;
	double minVerbrauch;
	double standby;
	double aktuellerVerbrauch;
	double schwankung;
	int betriebsdauer = 0;
	double onWahrscheinlichkeit;
	double offWahrscheinlichkeit;
	boolean benutzt = false;
	
	public Ger�teTyp3(double Max_Verbrauch, double Min_Verbrauch, double Standby, double Schwankung){
		this.maxVerbrauch = Max_Verbrauch;
		this.minVerbrauch = Min_Verbrauch;
		this.standby = Standby;
		this.schwankung = Schwankung;
	}
	
	public double randomisieren(double Aktueller_Verbrauch, double Schwankung){
		
		this.aktuellerVerbrauch = Aktueller_Verbrauch;
		if(Math.random() < 0.5){
			this.aktuellerVerbrauch += Math.random() * schwankung;
		}else{
			this.aktuellerVerbrauch -= Math.random() * schwankung;
		}
		return(this.aktuellerVerbrauch);
	}
	
	public void setBenutzt(boolean Benutzt) {
		this.benutzt = Benutzt;
	}
	
	//toDo Markov-Kette beif�gen
	public double setAktuellerVerbrauch(double Max, double Min){
		this.aktuellerVerbrauch = Math.random() * (Max - Min) + Min;
		return(this.aktuellerVerbrauch);
	}
	
	public void setBetriebsdauer(int Betriebs_Dauer){
		this.betriebsdauer = Betriebs_Dauer;
	}
	
	public void setOffWahrscheinlichkeit(double Off_Wahrscheinlichkeit){
		this.offWahrscheinlichkeit = Off_Wahrscheinlichkeit;		
	}
	
	public void setOnWahrscheinlichkeit(double On_Wahrscheinlichkeit){
		this.onWahrscheinlichkeit = On_Wahrscheinlichkeit;		
	}
}
