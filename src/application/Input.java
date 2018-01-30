package application;
	
import java.io.IOException;
import java.util.ArrayList;

import Ger�tePackage.K�hlschrank;
import Haushalt.Haushalt;
import Haushalt.Person;
import Haushalt.Personentyp;
import Hilfsmethoden.Ausgabe;
import Hilfsmethoden.Einlesen;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ1;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ2;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ3;
import Wahrscheinlichkeiten.Wahrscheinlichkeit_Typ4;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class Input extends Application {
	
	@FXML private Pane anchorFenster;
	@FXML private CheckBox checkToaster;
	@FXML private CheckBox checkStaubsauger;
	@FXML private CheckBox checkWasserkocher;
	@FXML private CheckBox checkDiagramm;
	@FXML private CheckBox checkK�hlschrank;
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
		Wahrscheinlichkeit_Typ1 w1 = new Wahrscheinlichkeit_Typ1();
		Wahrscheinlichkeit_Typ2 w2 = new Wahrscheinlichkeit_Typ2();
		Wahrscheinlichkeit_Typ3 w3 = new Wahrscheinlichkeit_Typ3();
		Wahrscheinlichkeit_Typ4 w4 = new Wahrscheinlichkeit_Typ4();
		
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
		if(choicePersTyp1.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(choicePersTyp2.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(choicePersTyp3.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(choicePersTyp4.isDisabled() == false) {
			list.add(new Person(new Personentyp(choicePersTyp1.getValue())));
		}
		if(checkDiagramm.isSelected() == true) {
			diagramm = true;
		}
		double [][] ger�tAn = new double [1440][ger�te.size()];
		double [][] statAnalysis = new double [1440][ger�te.size()];
		getStatData(statAnalysis,ger�te);
		erstelle(w1,w2,w3,w4,ger�tAn,statAnalysis);
	}
	public void erstelle(Wahrscheinlichkeit_Typ1 w1,Wahrscheinlichkeit_Typ2 w2, Wahrscheinlichkeit_Typ3 w3,Wahrscheinlichkeit_Typ4 w4,double [][] ger�tAn,double[][]statAnalysis) {
		Haushalt haushalt = new Haushalt(list);
		haushalt.calcOccupancy();
		for(int tSlot = 0;tSlot < statAnalysis.length;tSlot++) { 			//Durchl�uft alle TimeSlots
			for(int aktGer�t = 0;aktGer�t < ger�te.size();aktGer�t++) { 	//Durchl�uft alle Ger�te
				if(ger�te.get(aktGer�t) == "toaster") {
					w1.getWahrToaster(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot);
					if(tSlot == statAnalysis.length-1)
					{
						w1.reset();
					}
				}
				if(ger�te.get(aktGer�t) == "wasserkocher") {
					w1.getWahrWasserKocher(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot);
					if(tSlot == statAnalysis.length-1)
					{
						w1.reset();
					}
				}
				if(ger�te.get(aktGer�t) == "staubsauger") {
					w3.getWahrStaubsauger(haushalt.getOccupancy(),statAnalysis,ger�tAn,aktGer�t,tSlot);
				}
				if(ger�te.get(aktGer�t) == "k�hlschrank") {
					w4.getWahrK�hlschrank(tSlot,ger�tAn,aktGer�t);
				}
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
			//if(ger�te.get(i) == "k�hlschrank") { //fehlt noch
			//	tmpData = Einlesen.GetAll(auswertDaten,ger�te.get(i));
			//}
			for(int j = 0; j<1440;j++) {
				statAnalysis[j][i] = tmpData[j];
			}
		}
	}
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Input.fxml"));
			loader.setController(this);
			root = loader.load();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Energieverbrauchssimulator");
			primaryStage.show();
			primaryStage.setResizable(false);
			initialize();
			btnStart.setOnAction(new EventHandler<ActionEvent>() {
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
