package application;

import java.io.IOException;

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

public class MemberController {
	
	    @FXML
	    TextField itemtf;
	    @FXML
	    TextField titletf;
	    @FXML
	    TextField authortf;
	    @FXML
	    TextField memtf;
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
        }
        
        public void searchID(ActionEvent action) {
        	errorMessage();
        	String id = itemtf.getText();
        	try {
        		ObservableList<Item> list = FXCollections.observableArrayList(Main.lib.findItem(id));
                for(int i=0; i < list.size();i++) {
                    view.getItems().addAll(list.get(i).toString());
                }
    		} catch (InvalidItemException e) {
    			msg1.setText("Invalid Item ID");
    		}
        	itemtf.clear();
        }
        
        public void searchWithTitleAndAuthor(ActionEvent action) {
        	errorMessage();
        	String t = titletf.getText();
        	String a = authortf.getText();
        	ObservableList<Item> list = FXCollections.observableArrayList(Main.lib.findItems(t,a));
            for(int i=0; i < list.size();i++) {
                view.getItems().addAll(list.get(i).toString());
            }	
    		titletf.clear();
    		authortf.clear();
    	}

    
    	public void checkoutItem(ActionEvent action) {
    		errorMessage();
    		String t = itemtf.getText();
    		String m = memtf.getText();
    		try {
				Main.lib.checkOut(t, m);
				Main.lib.saveData();
			} catch (CheckedOutException e) {
				msg2.setText("Item already checked out");
			} catch (InvalidItemException e) {
				msg3.setText("Invalid Item ID");
			} catch (InvalidMemberException e) {
				msg4.setText("Invalid Member ID");
			}
    		itemtf.clear();
    		memtf.clear();
    	}

    	public void extendCheckoutTime(ActionEvent action) {
    		errorMessage();
    		String t = itemtf.getText();
    		String m = memtf.getText();
    		try {
				Main.lib.extendCheckOut(t, m);
				Main.lib.saveData();
			} catch (CheckedOutException e) {
				msg5.setText("Item already checked out");
			} catch (InvalidItemException e) {
				msg6.setText("Invalid Item ID");
			}
    		
    		itemtf.clear();
    		memtf.clear();
    	}

    	public void checkoutRecordItemId(ActionEvent action) {
    		errorMessage();
    		String t = itemtf.getText();
    		try {
    			ObservableList<Item> list = FXCollections.observableArrayList(Main.lib.findItem(t));
    			for(int i=0; i < list.size();i++) {
    	            view.getItems().addAll(list.get(i).toString());
    	        }
    		} catch (InvalidItemException e) {
    			msg7.setText("Invalid Item ID");
    		}
    		itemtf.clear();
    	}


    	public void checkoutRecordmemberId(ActionEvent action) {
    		errorMessage();
    		String m = memtf.getText();
   		    Member member;
    			try {
					member = Main.lib.findMember(m);
	                ObservableList<CheckOutRecord> list = FXCollections.observableArrayList(member.getChekOutRecords());
				    for(int i=0; i < list.size();i++) {
				       view.getItems().addAll(list.get(i).toString());
				    }
				} catch (InvalidMemberException e) {
					msg8.setText("Invalid Member ID");
				}
    			
    		memtf.clear();
    	}  

    
    	public void displaySpecificTypesItems(ActionEvent action) {
    		errorMessage();
    		String type = null;
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
    		for(int i=0; i < list.size();i++) {
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

}
