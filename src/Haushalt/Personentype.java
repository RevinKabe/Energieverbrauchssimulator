/**
 * Klasse mit den Typen der Pesonen
 */
package Haushalt;

public class Personentype {

	private int[] awayTime = new int[1440]; //Wann am Tag gearbeitet wird
	
	public Personentype() {
		initializeAwayTime();
	}
	
	
	public int[] getAwayTime(){
		return awayTime;
	}

	
	
	/*f�gt die arbeitszeiten in das array rein und berechnet daran die chance , dass die person daheim ist,
	 *  es m�ssen noch richtige werte hinzugef�gt werden
	*/
	public void initializeAwayTime(){
		for(int i = 0; i< awayTime.length; i++){
			awayTime[i] = 0;
		}
		for(int i = awayTime.length/4; i < awayTime.length; i++){
			awayTime[i] = 1;
		}
	}

}
