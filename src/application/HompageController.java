package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class HompageController {
	
	
	@FXML
	RadioButton lib;
	@FXML
	RadioButton mem;
	@FXML
	ToggleGroup rb;
	
	public void confirm(ActionEvent action) throws IOException {
		if (lib.isSelected()) {
			Parent root = FXMLLoader.load(getClass().getResource("LibraryMenu.fxml"));
		    Stage stage = (Stage)((Node)action.getSource()).getScene().getWindow();
		    Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
			
		}
		else if (mem.isSelected()) {
			    Parent root1 = FXMLLoader.load(getClass().getResource("MemberMenu.fxml"));
			    Stage stage1 = (Stage)((Node)action.getSource()).getScene().getWindow();
			    Scene scene1 = new Scene(root1);
				stage1.setScene(scene1);
				stage1.show();
		}
		
	}
	
	public void close (ActionEvent action1) {
		System.exit(0);
	}
}
