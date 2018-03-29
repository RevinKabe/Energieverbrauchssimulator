package application;
import java.io.IOException;
import java.util.ArrayList;
import Ger�tePackage.K�hlschrank;
import Haushalt.Haushalt;
import Haushalt.Person;
import Haushalt.Personentyp;
import Hilfsmethoden.Ausgabe;
import Hilfsmethoden.Einlesen;
import Wahrscheinlichkeiten.WahrDeckenLampe;
import Wahrscheinlichkeiten.WahrFernseher;
import Wahrscheinlichkeiten.WahrKaffeeMaschine;
import Wahrscheinlichkeiten.WahrK�hlschrank;
import Wahrscheinlichkeiten.WahrMikrowelle;
import Wahrscheinlichkeiten.WahrPc;
import Wahrscheinlichkeiten.WahrStaubsauger;
import Wahrscheinlichkeiten.WahrToaster;
import Wahrscheinlichkeiten.WahrTrockner;
import Wahrscheinlichkeiten.WahrWaschmaschine;
import Wahrscheinlichkeiten.WahrWasserKocher;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
/**
 * Diese Klasse regelt das User Interface der Anwendung.
 * @author Julian Gr�nker
 */	
public class Input extends Application {
	
	@FXML private Pane anchorFenster;
	@FXML private CheckBox checkToaster;
	@FXML private CheckBox checkStaubsauger;
	@FXML private CheckBox checkWasserkocher;
	@FXML private CheckBox checkDiagramm;
	@FXML private CheckBox checkK�hlschrank;
	@FXML private CheckBox checkPlasmaFernseher;
	@FXML private CheckBox checkLCDFernseher;
	@FXML private CheckBox checkMikrowelle;
	@FXML private CheckBox checkKaffeemaschine;
	@FXML private CheckBox checkWaschmaschine;
	@FXML private CheckBox checkTrockner;
	@FXML private CheckBox checkPc;
	@FXML private CheckBox checkDeckenLampe;
	@FXML private CheckBox checkStat;
	@FXML private Slider slider;
	@FXML private Label lblCountSlider;
	@FXML private ChoiceBox<String> choicePersTyp1;
	@FXML private ChoiceBox<String> choicePersTyp2;
	@FXML private ChoiceBox<String> choicePersTyp3;
	@FXML private ChoiceBox<String> choicePersTyp4;
	@FXML private Button btnStart;
	
	private ArrayList <String> ger�te = new ArrayList<String>();
	private ArrayList<Person> list = new ArrayList<Person>();
	private boolean diagramm = false;
	private boolean statData = false;

	@FXML public void initialize() {
		ObservableList<String> personenTypen = FXCollections.observableArrayList("Arbeitslos","Arbeiter","Kind","Student");
		choicePersTyp1.setItems(personenTypen);
		choicePersTyp1.setValue("Arbeitslos");
		choicePersTyp2.setItems(personenTypen);
		choicePersTyp2.setValue("Arbeitslos");
		choicePersTyp3.setItems(personenTypen);
		choicePersTyp3.setValue("Arbeitslos");
		choicePersTyp4.setItems(personenTypen);
		choicePersTyp4.setValue("Arbeitslos");
		slider.setMin(1);
		slider.setMax(4);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(1);
		slider.setMinorTickCount(0);
		slider.setBlockIncrement(10);
		slider.setSnapToTicks(true);
		lblCountSlider.setText("1");
		choicePersTyp1.setDisable(false);
    	choicePersTyp2.setDisable(true);
    	choicePersTyp3.setDisable(true);
    	choicePersTyp4.setDisable(true);
		slider.valueProperty().addListener((observable, oldValue, newValue) -> { {
                lblCountSlider.textProperty().setValue(
                        String.valueOf((int) slider.getValue()));
                if(slider.getValue() == 1) {
                	choicePersTyp1.setDisable(false);
                	choicePersTyp2.setDisable(true);
                	choicePersTyp3.setDisable(true);
                	choicePersTyp4.setDisable(true);
                }
                if(slider.getValue() == 2) {
                	choicePersTyp1.setDisable(false);
                	choicePersTyp2.setDisable(false);
                	choicePersTyp3.setDisable(true);
                	choicePersTyp4.setDisable(true);                }
                if(slider.getValue() == 3) {
                	choicePersTyp1.setDisable(false);
                	choicePersTyp2.setDisable(false);
                	choicePersTyp3.setDisable(false);
                	choicePersTyp4.setDisable(true);                }
                if(slider.getValue() == 4) {
                	choicePersTyp1.setDisable(false);
                	choicePersTyp2.setDisable(false);
                	choicePersTyp3.setDisable(false);
                	choicePersTyp4.setDisable(false);                }
            }
		});
	}
	public void bearbeite() throws IOException {	
		if(checkToaster.isSelected() == true) {
			ger�te.add("toaster");
		}
		if(checkWasserkocher.isSelected() == true) {
			ger�te.add("wasserkocher");
		}
		if(checkStaubsauger.isSelected() == true) {
			ger�te.add("staubsauger");
		}
		if(checkK�hlschrank.isSelected() == true) {
			ger�te.add("k�hlschrank");
		}
		if(checkPlasmaFernseher.isSelected() == true) {
			ger�te.add("plasmaFernseher");
		}
		if(checkLCDFernseher.isSelected() == true) {
			ger�te.add("lcdFernseher");
		}
		if(checkMikrowelle.isSelected() == true) {
			ger�te.add("mikrowelle");
		}
		if(checkKaffeemaschine.isSelected() == true) {
			ger�te.add("kaffeemaschine");
		}
		if(checkWaschmaschine.isSelected() == true) {
			ger�te.add("waschmaschine");
		}
		if(checkTrockner.isSelected() == true) {
			ger�te.add("trockner");
		}
		if(checkDeckenLampe.isSelected() == true) {
			ger�te.add("deckenlampe");
		}
		if(checkPc.isSelected() == true) {
			ger�te.add("pc");
		}
		if(checkStat.isSelected() == true) {
			statData = true;
		}
		if(choicePersTyp1.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(choicePersTyp2.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp2.getValue())));
		}
		if(choicePersTyp3.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp3.getValue())));
		}
		if(choicePersTyp4.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp4.getValue())));
		}
		if(checkDiagramm.isSelected() == true) {
			diagramm = true;
		}
		double [][] ger�tAn = new double [1440][ger�te.size()];
		double [][] statAnalysis = new double [1440][ger�te.size()];
		getStatData(statAnalysis,ger�te);
		erstelle(ger�tAn,statAnalysis);
	}
	public void erstelle(double [][] ger�tAn,double[][]statAnalysis) {
		K�hlschrank ks = new K�hlschrank();
		WahrWasserKocher wahrWk = new WahrWasserKocher();	//verbessern !!!
		WahrMikrowelle wahrMw = new WahrMikrowelle();
		WahrToaster wahrTs = new WahrToaster();
		WahrK�hlschrank wahrKs = new WahrK�hlschrank();
		WahrStaubsauger wahrSs = new WahrStaubsauger();
		WahrKaffeeMaschine wahrKm = new WahrKaffeeMaschine();
		WahrFernseher wahrFs = new WahrFernseher();
		WahrFernseher wahrFs2 = new WahrFernseher();
		WahrWaschmaschine wahrWm = new WahrWaschmaschine();
		WahrTrockner wahrTo = new WahrTrockner();
		WahrDeckenLampe wahrDl = new WahrDeckenLampe();
		WahrPc wahrPc = new WahrPc();

		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		int waschMaAn = 0;
		boolean firstRun = true;
		
		for(int tSlot = 0;tSlot < statAnalysis.length;tSlot++) { 			//Durchl�uft alle TimeSlots
			for(int aktGer�t = 0;aktGer�t < ger�te.size();aktGer�t++) { 	//Durchl�uft alle Ger�te
				if(ger�te.get(aktGer�t) == "toaster") {
					wahrTs.getWahrToaster(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot);
				}
				if(ger�te.get(aktGer�t) == "wasserkocher") {
					wahrWk.sucheKind(haushalt.getPersonen(), haushalt.getOccupancy());
					wahrWk.getWahrWasserKocher(statAnalysis,ger�tAn,aktGer�t,tSlot,statData);
				}
				if(ger�te.get(aktGer�t) == "mikrowelle") {
					wahrMw.sucheKind(haushalt.getPersonen(), haushalt.getOccupancy());
					wahrMw.getWahrMikrowelle(statAnalysis,ger�tAn,aktGer�t,tSlot,statData);
				}
				if(ger�te.get(aktGer�t) == "staubsauger") {
					wahrSs.getWahrStaubsauger(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot);
				}
				if(ger�te.get(aktGer�t) == "k�hlschrank") {
					wahrKs.getWahrK�hlschrank(tSlot,ger�tAn,aktGer�t,ks);
				}
				if(ger�te.get(aktGer�t) == "kaffeemaschine") {
					wahrKm.sucheKind(haushalt.getPersonen(), haushalt.getOccupancy());
					wahrKm.getWahrKaffeemaschine(statAnalysis,ger�tAn,aktGer�t,tSlot,statData);
				}
				if(ger�te.get(aktGer�t) == "lcdFernseher") {
					if(firstRun == true) {
						wahrFs.sucheKind(haushalt.getPersonen());
					}
					wahrFs.getWahrFernseher(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot,haushalt.getPersonen(),statData,"LCD");
				}
				if(ger�te.get(aktGer�t) == "plasmaFernseher") {
					if(firstRun == true) {
						wahrFs2.sucheKind(haushalt.getPersonen());
					}
					wahrFs2.getWahrFernseher(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot,haushalt.getPersonen(),statData,"Plasma");
				}
				if(ger�te.get(aktGer�t) == "waschmaschine") {
					wahrWm.sucheKind(haushalt.getPersonen(), haushalt.getOccupancy());
					waschMaAn = wahrWm.getWahrWaschmaschine(statAnalysis,ger�tAn,aktGer�t,tSlot,statData);
				}
				if(ger�te.get(aktGer�t) == "trockner") {
					if(waschMaAn != 0) {
						wahrTo.getWahrTrockner(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot,waschMaAn);
					}
				}
				if(ger�te.get(aktGer�t) == "deckenlampe") {
					wahrDl.getWahrDeckenLampe(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot,statData);
				}
				if(ger�te.get(aktGer�t) == "pc") {
					wahrPc.getWahrPc(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot,statData);
				}
				firstRun = false;
			}
		}
		if(diagramm == true) {
			Ausgabe.erstelleArr(haushalt.getOccupancy(), ger�tAn, ger�te);
		}
	}
	
	public static void getStatData(double [][] statAnalysis,ArrayList <String> ger�te) throws IOException {
		int auswertDaten = 24;
		double[] tmpData = new double[1440];
		for(int i = 0; i<ger�te.size();i++) {
			if(ger�te.get(i) == "wasserkocher") {
				tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			}
			if(ger�te.get(i) == "k�hlschrank") { 
				tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			}
			if(ger�te.get(i) == "kaffeemaschine") { 
				tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			}
			if(ger�te.get(i) == "pc") { 
				tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			}
			if(ger�te.get(i) == "trockner") { 
				tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			}
			//Nicht 100 Prozent funktionsf�hig!
			//if(ger�te.get(i) == "waschmaschine") {  
			//	tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			//}
			//if(ger�te.get(i) == "fernseher") {  
			//	tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			//}
			//if(ger�te.get(i) == "mikrowelle") { 
			//	tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			//}
			if(ger�te.get(i) == "deckenlampe") { 
				tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			}
			for(int j = 0; j<1440;j++) {
				statAnalysis[j][i] = tmpData[j];
			}
		}
	}
	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Input.fxml"));
			loader.setController(this);
			root = loader.load();
			Scene scene = new Scene(root,522,425);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Energieverbrauchssimulator");
			primaryStage.show();
			primaryStage.setResizable(false);
			initialize();
			btnStart.setOnAction(new EventHandler<ActionEvent>() {
			    @Override
				public void handle(ActionEvent e) {
			    	try {
						bearbeite();
						primaryStage.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			    }
			});
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
