import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import Haushalt.Haushalt;
import Haushalt.Person;
import Haushalt.Personentyp;
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
		
		Person person = new Person(new Personentyp());
		//System.out.print(person);
		ArrayList<Person> list = new ArrayList<Person>();
		list.add(person);
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		//System.out.print(haushalt);
		
		Wahrscheinlichkeit w1 = new Wahrscheinlichkeit();
		statAnalysis = Einlesen.GetAll(auswertDaten);
		for(int i = 0;i<statAnalysis.length;i++) { //Durchl�uft alle TimeSlots
			for(int ger�teListe = 0;ger�teListe<ger�te;ger�teListe++) { //Durchl�uft alle Ger�te
			//System.out.println(list.get(0).getPercentAwayTime(i));
			w1.checkStatus(list,statAnalysis,ger�tAn,auswertDaten,"Toaster",ger�teListe,i);
			if(ger�tAn[i][0] == 1)
			{
				System.out.println("TimeSlot: " + i + " : "+ ger�tAn[i][0]);
			}
			}
		}
	}
}
