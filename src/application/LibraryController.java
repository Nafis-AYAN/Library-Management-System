package application;

import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import library.CheckOutRecord;
import library.CheckedOutException;
import library.InvalidItemException;
import library.InvalidMemberException;
import library.Item;
import library.Member;

public class LibraryController {

    @FXML
    TextField itemidtf;
    @FXML
    TextField titletf;
    @FXML
    TextField cattf;
    @FXML
    TextField authortf;
    @FXML
    TextField pubNtf;
    @FXML
    TextField pubDtf;
    @FXML
    TextField memidtf;
    @FXML
    TextField memNtf;
    @FXML
    TextField jourtf;
    @FXML
    CheckBox box1;
    @FXML
    CheckBox box2;
    @FXML
    CheckBox box3;
    @FXML
    ListView<String> view;
    @FXML
    CheckBox box4;
    @FXML
    CheckBox box5;
    @FXML
    CheckBox box6;
    @FXML
    Label msg1;
    @FXML
    Label msg2;
    @FXML
    Label msg3;
    @FXML
    Label msg4;
    @FXML
    Label msg5;
    @FXML
    Label msg6;
    @FXML
    Label msg7;
    @FXML
    Label msg8;
    @FXML
    Label msg9;
    
    private static String type;

    public void Home(ActionEvent action) {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("Homepage.fxml"));
			Stage stage = (Stage)((Node)action.getSource()).getScene().getWindow();
		    Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    
	}
    
    public void exit(ActionEvent action) {
    	System.exit(0);
    }
    
    //To clear all the exception label message
    public void errorMessage() {
    	msg1.setText(null);
    	msg2.setText(null);
    	msg3.setText(null);
    	msg4.setText(null);
    	msg5.setText(null);
    	msg6.setText(null);
    	msg7.setText(null);
    	msg8.setText(null);
    	msg9.setText(null);
    }
    
    public void addItem(ActionEvent action) {
    	errorMessage();	
    	ArrayList<String> authors = new ArrayList<>();
    	if(box1.isSelected()) {
    		String iid = itemidtf.getText();
        	String tit = titletf.getText();
        	authors.add(authortf.getText());
        	String cat = cattf.getText();
        	int pubD = Integer.parseInt(pubDtf.getText()); 
        	String pubN = pubNtf.getText();
        	String itype = box1.getText();
        	Main.lib.addItem(itype, iid, tit, cat, authors, pubD, pubN);
        	Main.lib.saveData();     //To save data in File 
    	}
    	else if(box2.isSelected()) {
    		String iid = itemidtf.getText();
        	String tit = titletf.getText();
        	authors.add(authortf.getText());
        	String cat = cattf.getText();
        	int pubD = Integer.parseInt(pubDtf.getText()); 
        	String jourN = jourtf.getText();
        	String itype = box2.getText();
        	Main.lib.addItem(itype, iid, tit, cat, authors, pubD, jourN);
        	Main.lib.saveData();
    	}
    	else if(box3.isSelected()) {
    		String iid = itemidtf.getText();
        	String tit = titletf.getText();
        	authors.add(authortf.getText());
        	String cat = cattf.getText();
        	int pubD = Integer.parseInt(pubDtf.getText());
        	Main.lib.addItem(iid, tit, cat, authors, pubD);
        	Main.lib.saveData();
    	}
    	itemidtf.clear();
    	titletf.clear();
    	authortf.clear();
    	cattf.clear();
    	pubDtf.clear();
    	pubNtf.clear();
    	jourtf.clear();
    	box1.setSelected(false);
    	box2.setSelected(false);
    	box3.setSelected(false);
    }
    
    public void searchID(ActionEvent action) {
    	errorMessage();
    	String id = itemidtf.getText();
    	try {
    		ObservableList<Item> list = FXCollections.observableArrayList(Main.lib.findItem(id));
            for(int i=0; i < list.size();i++) {
                view.getItems().addAll(list.get(i).toString());
            }	
		} catch (InvalidItemException e) {
			msg1.setText("Invalid Item ID");
		}
    	itemidtf.clear();
    }
    
    public void searchWithTitleAndAuthor(ActionEvent action) {
    	errorMessage();
    	String t = titletf.getText();
    	String a = authortf.getText();
		//ArrayList<Item> item = new ArrayList<>();
    	ObservableList<Item> list = FXCollections.observableArrayList(Main.lib.findItems(t,a));
        for(int i=0; i < list.size();i++) {
            view.getItems().addAll(list.get(i).toString());
        }
		titletf.clear();
		authortf.clear();
	}


	public void checkoutItem(ActionEvent action) {
		errorMessage();
		String t = itemidtf.getText();
		String m = memidtf.getText();
		try {
			Main.lib.checkOut(t, m);
			Main.lib.saveData();
		} catch (CheckedOutException e) {
			msg3.setText("Item already checked out");
		} catch (InvalidItemException e) {
			msg2.setText("Invalid Item ID");
		} catch (InvalidMemberException e) {
			msg4.setText("Invalid Member ID");
		}
		itemidtf.clear();
		memidtf.clear();
	}

	public void extendCheckoutTime(ActionEvent action) {
		errorMessage();
		String t = itemidtf.getText();
		String m = memidtf.getText();
		try {
			Main.lib.extendCheckOut(t, m);
			Main.lib.saveData();
		} catch (CheckedOutException e) {
			msg5.setText("Item already checked out");
		} catch (InvalidItemException e) {
			msg6.setText("Invalid Item ID");
		}
		itemidtf.clear();
		memidtf.clear();
	}

	public void checkInItem(ActionEvent action) {
		errorMessage();
		String t = itemidtf.getText();
		try {
			Main.lib.checkIn(t);
			Main.lib.saveData();
		} catch (InvalidItemException e) {
			msg7.setText("Invalid Item ID");
			
		}
		itemidtf.clear();
	}
	
	public void checkoutRecordItemId(ActionEvent action) {
		errorMessage();
		String t = itemidtf.getText();
		try {
			ObservableList<Item> list = FXCollections.observableArrayList(Main.lib.findItem(t));
			for(int i=0; i < list.size();i++) {
	            view.getItems().addAll(list.get(i).toString());
	        }
		} catch (InvalidItemException e) {
			msg8.setText("Invalid Item ID");
		}
		itemidtf.clear();
	}


	public void checkoutRecordmemberId(ActionEvent action) {
		errorMessage();
		String m = memidtf.getText();
		    Member member;
			try {
				member = Main.lib.findMember(m);
                ObservableList<CheckOutRecord> list = FXCollections.observableArrayList(member.getChekOutRecords());
			    for(int i=0; i < list.size();i++) {
			       view.getItems().addAll(list.get(i).toString());
			    }
			} catch (InvalidMemberException e) {
				msg9.setText("Invalid Member ID");
			}
		
		memidtf.clear();
	}  


	public void displaySpecificTypesItems(ActionEvent action) {
		errorMessage();
		if (box4.isSelected()) {
			type = box4.getText();
		}
		else if (box5.isSelected()) {
			type = box5.getText();
		}
		else if (box6.isSelected()) {
			type = box6.getText();
		}
		ObservableList<Item> list = FXCollections.observableArrayList(Main.lib.findItems(type));
		for(int i=0; i < list.size(); i++) {
		    view.getItems().addAll(list.get(i).toString());
		}
		box4.setSelected(false);
    	box5.setSelected(false);
    	box6.setSelected(false);
	}

	public void displayAll(ActionEvent action) {
		errorMessage();
		String display = "";
		for (Item data : Main.lib.getItems()) {
			display = display + data.toString() + "\n";
		}
		view.getItems().addAll(display);
	}
	
	public void addMember(ActionEvent action) {
		errorMessage();
		String mn = memNtf.getText();
		String mid = memidtf.getText();
		Main.lib.addMember(mid, mn);
		Main.lib.saveData();
		
		memNtf.clear();
		memidtf.clear();
	}
}
