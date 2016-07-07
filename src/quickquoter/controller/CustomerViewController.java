
package quickquoter.controller;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CustomerViewController implements Initializable {

    private ObservableList customerList;
    
    @FXML
    TextField txtId, txtCompanyName, txtContact, txtEmail, txtPhone, txtAddress, txtCity, 
              txtState, txtZipcode, txtSearch;
    
    @FXML
    Label lblMessage;
    
    @FXML
    AnchorPane mainAnchor;
    @FXML
    GridPane gpCustomerFields;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Thread thread = new Thread();
        thread.run();
    }  
    
    @FXML
    private void clearTextFields() {
        
        //validateInput();
        //loop over AnchorPane to find the textfields and clear them.
        for (javafx.scene.Node node : mainAnchor.getChildren()) {
            if (node instanceof GridPane) {
                for(javafx.scene.Node node1 : gpCustomerFields.getChildren()) {
                    if (node1 instanceof TextField) {
                        ((TextField)node1).setText("");
                        System.out.println(node1.getId());
                    }
                }
            }
        }
        
       
    }
    
    @FXML
    private void generateId() {
        
        if ("".equals(txtId.getText())) {
            StringBuilder idBuilder = new StringBuilder();
            Random rnd = new Random();
        
            for (int i = 0; i <= 8; i++) {
                idBuilder.append(rnd.nextInt(9));
            }

            txtId.setText("01-" + idBuilder);
        }
        else {
            
        }
      
    }
    
    @FXML
    private void LoadCustomer() {
        
        File customerXml = new File("\\quickquoter\\app_data\\xml\\customers.xml");
        customerList = FXCollections.observableArrayList();
        
        
    }
    
    @FXML
    private void resetInput() {
        
          for (javafx.scene.Node node : mainAnchor.getChildren()) {
            if (node instanceof GridPane) {
                for(javafx.scene.Node node1 : gpCustomerFields.getChildren()) {
                    if (node1 instanceof TextField) {
                        if (((TextField)node1).getText().equals("")) {
                            ((TextField)node1).setStyle("-fx-background-color: #c7c7c7;");
                        }
                    }
                }
            }
        }
    }

    private boolean validateInput() {
         //check the input value.
         boolean isEmpty = false;
         for (javafx.scene.Node node : mainAnchor.getChildren()) {
            if (node instanceof GridPane) {
                for(javafx.scene.Node node1 : gpCustomerFields.getChildren()) {
                    if (node1 instanceof TextField) {
                        if (((TextField)node1).getText().equals("")) {
                            ((TextField)node1).setStyle("-fx-background-color: linear-gradient(#fa7b7b, #fa3741);" +
                                                        "-fx-border-color: #2e2e2e;" + 
                                                        "-fx-border-radius: 4px;");
                                                        
                          
                            isEmpty = true;
                        }
                        System.out.println(node1.getId());
                    }
                }
            }
        }
         //has empty inputs - let the user know the input fields are required.
         if (isEmpty) {
          showAlert("Input Error", "Fields in Red are Required!", AlertType.WARNING);
         }
        return isEmpty;
    }
    
    private void showAlert(String title, String message, AlertType alerttype) {
        
        Alert alert = new Alert(alerttype);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
        
    }
    
    
    private class Thread implements Runnable {

        @Override
        public void run() {
            LoadCustomer();
        }
        
    }
    
}
