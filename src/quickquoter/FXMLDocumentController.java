
package quickquoter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLDocumentController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void loadCustomerView() {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/quickquoter/view/customerView.fxml"));
            Scene scene = new Scene(root);
            
            Stage stage = new Stage();
            stage.setTitle("Quick Quoter   -Manage Customer-");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void loadQuoteView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/quickquoter/view/quoteView.fxml"));
            Scene scene = new Scene(root);
            
            Stage stage = new Stage();
            stage.setTitle("Quick Quouter    -Quote-");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @FXML
    private void loadSettingsView() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/quickquoter/view/settingsView.fxml"));
            Scene scene = new Scene(root);
            
            Stage stage = new Stage();
            stage.setTitle("Quick Quoter   -Settings-");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
