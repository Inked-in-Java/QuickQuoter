
package quickquoter.controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class CustomerViewController implements Initializable {

    private ObservableList customerList;
    
    @FXML
    TextField txtId, txtCompanyName, txtContact, txtEmail, txtPhone, txtAddress, txtCity, 
              txtState, txtZipcode, txtSearch;
    
    @FXML
    AnchorPane mainAnchor;
    @FXML
    GridPane gpCustomerFields;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void clearTextFields() {
        
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
    private void LoadCustomer() {
        
        File customerXml = new File("\\quickquoter\\app_data\\xml\\customers.xml");
        customerList = FXCollections.observableArrayList();
        
        
    }
    
}
