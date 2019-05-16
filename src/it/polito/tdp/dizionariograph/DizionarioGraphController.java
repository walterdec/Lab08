package it.polito.tdp.dizionariograph;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DizionarioGraphController {
	
	Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtFieldLettere;

    @FXML
    private TextField txtFieldParole;

    @FXML
    private Button btnGeneraGrafo;

    @FXML
    private Button btnTrovaVicini;

    @FXML
    private Button btnTrovaGradoMax;

    @FXML
    private TextArea txtArea;

    @FXML
    private Button btnReset;

    @FXML
    void doGeneraGrafo(ActionEvent event) {
    	int numLettere;
    	try {
    		numLettere = Integer.parseInt(txtFieldLettere.getText());
    		model.createGraph(numLettere);
    	} catch(NumberFormatException e) {
    		txtArea.setText("Inserire un numero valido");
    		return;
    	}
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtFieldParole.clear();
    	txtFieldLettere.clear();
    	txtArea.clear();

    }

    @FXML
    void doTrovaGradoMax(ActionEvent event) {
    	txtArea.clear();
    	txtArea.appendText("Grado massimo: "+model.findMaxDegree()+"\n");
    	txtArea.appendText("Vertice con grado massimo: "+model.getVerticeGradoMax()+"\n");
    	txtArea.appendText("Vicini del vertice con grado massimo:\n");
    	for(String vicino : model.displayNeighbours(model.getVerticeGradoMax())) {
    		txtArea.appendText(vicino+" - ");
    	}
    }

    @FXML
    void doTrovaVicini(ActionEvent event) {
    	txtArea.clear();
    	String parola = txtFieldParole.getText();
    	if(!model.getGrafo().containsVertex(parola)) {
    		txtArea.setText("Parola cercata inesistente");
    		return;
    	}
    	List<String> vicini = model.displayNeighbours(parola);
    	if(vicini == null) {
    		txtArea.setText("Parola inserita non valida o grafo non generato");
    		return;
    	}
    	for(String v : vicini) {
    		txtArea.appendText(v+" - ");
    	}
    }

    @FXML
    void initialize() {
        assert txtFieldLettere != null : "fx:id=\"txtFieldLettere\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtFieldParole != null : "fx:id=\"txtFieldParole\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnGeneraGrafo != null : "fx:id=\"btnGeneraGrafo\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaVicini != null : "fx:id=\"btnTrovaVicini\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnTrovaGradoMax != null : "fx:id=\"btnTrovaGrafoMax\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'DizionarioGraph.fxml'.";

    }
    
	public void setModel(Model model){
		this.model = model;
	}
}
