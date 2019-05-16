package it.polito.tdp.dizionariograph;

import it.polito.tdp.dizionariograph.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("DizionarioGraph.fxml"));
			BorderPane root = (BorderPane) loader.load();
			
			// Create here your model.
			// Assign here the model to the controller.
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			DizionarioGraphController controller = loader.getController();
			Model model = new Model();
			controller.setModel(model);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
