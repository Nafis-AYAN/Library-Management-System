package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import library.*;


public class Main extends Application {
	
	static Library lib = new Library("UAP Central Library");
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Library");
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		try {
			lib = DataHandler.readData();  // To read data from file
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		launch(args);
	}
}
