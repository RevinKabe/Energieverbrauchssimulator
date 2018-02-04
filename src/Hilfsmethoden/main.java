package Hilfsmethoden;
import java.io.IOException;
import java.util.ArrayList;
import Haushalt.Haushalt;
import Haushalt.Person;
/**
 *
 */
public class main {

	public static void main(String[] args) throws IOException {
		/*ArrayList <String> ger�te = new ArrayList<String>();
		ger�te.add("toaster");
		ger�te.add("wasserkocher");
		ger�te.add("staubsauger");
		double [][] ger�tAn = new double [1440][ger�te.size()];
		double [][] statAnalysis = new double [1440][ger�te.size()];
		ArrayList<Person> list = new ArrayList<Person>();

		Person person = new Person(new Arbeiter());
		Person person2 = new Person(new Arbeiter());
		Person person3 = new Person(new Arbeiter());
		list.add(person);
		list.add(person2);
		list.add(person3);
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		
		Wahrscheinlichkeit_Typ1 w1 = new Wahrscheinlichkeit_Typ1();
		Wahrscheinlichkeit_Typ3 w2 = new Wahrscheinlichkeit_Typ3();
		getStatData(statAnalysis,ger�te);
		
		for(int tSlot = 0;tSlot < statAnalysis.length;tSlot++) { 			//Durchl�uft alle TimeSlots
			for(int aktGer�t = 0;aktGer�t < ger�te.size();aktGer�t++) { 	//Durchl�uft alle Ger�te
				if(aktGer�t == 0) {
					w1.getWahrToaster(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot);
					if(tSlot == statAnalysis.length-1)
					{
						w1.reset();
					}
				}
				if(aktGer�t == 1) {
					w1.getWahrWasserKocher(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot);
					if(tSlot == statAnalysis.length-1)
					{
						w1.reset();
					}
				}
				if(aktGer�t == 2) {
					w2.getWahrStaubsauger(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot);
				}
				if(ger�tAn[tSlot][aktGer�t] == 1)
				{
					System.out.println("TimeSlot: " + tSlot + " " + ger�te.get(aktGer�t) + " : "+ ger�tAn[tSlot][aktGer�t]);
				}
			}
		}
		Ausgabe.erstelleArr(haushalt.getOccupancy(), ger�tAn, ger�te);
		*/
		
		double[] tmpDataLampe = new double[1440];
		double[] tmpDataFern = new double[1440];
		double[] tmpDataKaffee = new double[1440];
		double[] tmpDataK�hl = new double[1440];
		double[] tmpDataMikro = new double[1440];
		double[] tmpDataPc = new double[1440];
		double[] tmpDataTrock = new double[1440];
		double[] tmpDataWasch = new double[1440];
		double[] tmpDataWass = new double[1440];

		double[][] tmpData2 = new double[1440][9];
		ArrayList <String> ger�te = new ArrayList<String>();
		ger�te.add("deckenlampe");
		ger�te.add("fernseher");
		ger�te.add("kaffeemaschine");
		ger�te.add("k�hlschrank");
		ger�te.add("mikrowelle");
		ger�te.add("pc");
		ger�te.add("trockner");
		ger�te.add("waschmaschine");
		ger�te.add("wasserkocher");

		tmpDataLampe = Eco.GetSpecific(5, "deckenlampe");
		tmpDataFern = Eco.GetSpecific(5, "fernseher");
		tmpDataKaffee = Eco.GetSpecific(5, "kaffeemaschine");
		tmpDataK�hl = Eco.GetSpecific(5, "k�hlschrank");
		tmpDataMikro = Eco.GetSpecific(5, "mikrowelle");
		tmpDataPc = Eco.GetSpecific(5, "pc");
		tmpDataTrock = Eco.GetSpecific(5, "trockner");
		tmpDataWasch = Eco.GetSpecific(5, "waschmaschine");
		tmpDataWass = Eco.GetSpecific(5, "wasserkocher");
		for(int i = 0; i<tmpDataK�hl.length;i++)
		{
			tmpData2[i][0] = tmpDataLampe[i];
			tmpData2[i][1] = tmpDataFern[i];
			tmpData2[i][2] = tmpDataKaffee[i];
			tmpData2[i][3] = tmpDataK�hl[i];
			tmpData2[i][4] = tmpDataMikro[i];
			tmpData2[i][5] = tmpDataPc[i];
			tmpData2[i][6] = tmpDataTrock[i];
			tmpData2[i][7] = tmpDataWasch[i];
			tmpData2[i][8] = tmpDataWass[i];
			//System.out.println(tmpData[i]);
		}
		int[] test = new int[1440];
		Ausgabe.erstelleArr(test, tmpData2, ger�te);
		//String csv = Create_CSV.create(tmpData2);
		//Diagramm.erzeugeEco(csv);
	}
	
	public static void getStatData(double [][] statAnalysis,ArrayList <String> ger�te) throws IOException {
		int auswertDaten = 24;
		double[] tmpData = new double[1440];
		for(int i = 0; i<ger�te.size();i++) {
			if(ger�te.get(i) == "wasserkocher") {
				tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			}
			for(int j = 0; j<1440;j++) {
				statAnalysis[j][i] = tmpData[j];
			}
		}
	}
}
