/**
 * Erfasst alle Ger�te Daten.
 */
public class Ger�te 
{
	String Name;
	int Max_Verbrauch;
	int Durch_Verbrauch;
	int Standby;
	int Anlaufzeit;
	int Auslaufzeit;
	Ger�tetype Ger�tetype;
	
	public Ger�te(String Name,int Max_Verbrauch,int Durch_Verbrauch,int Standby, int Anlaufzeit, int Auslaufzeit, int Type){
		this.Name = Name;
		this.Max_Verbrauch = Max_Verbrauch;
		this.Durch_Verbrauch = Durch_Verbrauch;
		this.Standby = Standby;
		this.Anlaufzeit = Anlaufzeit;
		this.Auslaufzeit = Auslaufzeit;
		Ger�tetype = new Ger�tetype(Type);
	}
}
