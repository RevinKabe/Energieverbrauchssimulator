import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Haushalt.Haushalt;
import Haushalt.Person;
import Haushalt.Personentype;
/**
 *
 */
public class main {

	public static void main(String[] args) throws IOException {
		//Ger�te pc = new Ger�te("pc1",100,10,1,2,1,1);
		int ger�te = 1;
		int auswertDaten = 10;
		double [] statAnalysis = new double [1440];
		double [][] ger�tAn = new double [1440][ger�te];
		Wahrscheinlichkeit w1 = new Wahrscheinlichkeit();
		
		statAnalysis = Einlesen.GetAll(auswertDaten);
		for(int i = 0;i<statAnalysis.length;i++) {
			w1.checkStatus(statAnalysis,ger�tAn,auswertDaten,ger�te,i);
			System.out.println(ger�tAn[i][0]);
		}
		
		Person person = new Person(15, new Personentype());
		person.calcTime();
		//System.out.print(person);
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(person);
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		//System.out.print(haushalt);
	}
}
