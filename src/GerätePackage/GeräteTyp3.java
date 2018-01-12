package Ger�tePackage;

public abstract class Ger�teTyp3 
{
	protected double maxVerbrauch;
	protected double minVerbrauch;
	protected double standby;
	protected double aktuellerVerbrauch;
	protected double schwankung;
	protected int betriebsdauer = 0;
	protected double onWahrscheinlichkeit;
	protected double offWahrscheinlichkeit;
	protected double �nderungsWahrscheinlichkeit;		//Wahrscheinlichkeit, dass sich der Stromverbrauch �ndert
	protected final double const�nderWahrsch;			//kann und soll nicht ge�ndert werden
	protected int modusDauer = 0;						//Dauer mit gleichbleibendem Verbrauch (ohne Ber�cksichtigung von Schwankungen)
	protected boolean benutzt = false;
	
	public Ger�teTyp3(double Max_Verbrauch, double Min_Verbrauch, double Standby, double Schwankung, double �nderungs_Wahrscheinlichkeit, double Const_�nder_Wahrsch){
		this.maxVerbrauch = Max_Verbrauch;
		this.minVerbrauch = Min_Verbrauch;
		this.standby = Standby;
		this.schwankung = Schwankung;
		this.�nderungsWahrscheinlichkeit = �nderungs_Wahrscheinlichkeit;
		this.const�nderWahrsch = Const_�nder_Wahrsch;
	}
	
	public double randomisieren(){
		
		double tmp = this.aktuellerVerbrauch; //tmp damit aktuellerVerbrauch hier nicht ver�ndert wird
		if(Math.random() < 0.5){
			tmp += Math.random() * this.schwankung;
		}else{
			tmp -= Math.random() * this.schwankung;
		}
		return(tmp);
	}
	
	public double setAktuellerVerbrauch(){
		if(Math.random() < this.�nderungsWahrscheinlichkeit){
			this.aktuellerVerbrauch = Math.random() * (this.maxVerbrauch - this.minVerbrauch) + this.minVerbrauch;
			this.�nderungsWahrscheinlichkeit = this.const�nderWahrsch;
			this.modusDauer = 0;
		}
		return(this.aktuellerVerbrauch);
	}
	
	public double getStandby() {
		return standby;
	}

	public void setStandby(double standby) {
		this.standby = standby;
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
	
	public double get�nderungsWahrscheinlichkeit() {
		return �nderungsWahrscheinlichkeit;
	}

	public void set�nderungsWahrscheinlichkeit(double �nderungsWahrscheinlichkeit) {
		this.�nderungsWahrscheinlichkeit = �nderungsWahrscheinlichkeit;
	}
	
	public int getModusDauer() {
		return modusDauer;
	}

	public void setModusDauer(int modusDauer) {
		this.modusDauer = modusDauer;
	}
	
	public void setBenutzt(boolean Benutzt) {
		this.benutzt = Benutzt;
	}
	
	public boolean getBenutzt() {
		return benutzt;
	}
}
