
package quickquoter.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import quickquoter.model.Customer;
import quickquoter.model.Quote;

public class QuoteViewController implements Initializable {

    ObservableList<String> obList;
    ObservableList<Quote> obQuoteList;
    List<String> custNameList;
    List<Customer> custList;
    String currentCustName;
    
    @FXML
    ComboBox<String> cbCustomers, cbStatus;
    @FXML TextField txtCustomerId, txtQuoteId;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            LoadCustomer();
            cbCustomers.getItems().addAll(obList);
            txtQuoteId.setText(generateQuoteId());
            cbStatus.getItems().add("In Progress");
            cbStatus.getItems().add("Complete");
            cbStatus.getItems().add("On Hold");
            cbStatus.getItems().add("Shipped");
            
        } catch (SAXException ex) {
            Logger.getLogger(QuoteViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(QuoteViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    public void LoadCustomer() throws org.xml.sax.SAXException, IOException {
        //TODO load customer.
        File xmlFile = new File("src\\quickquoter\\app_data\\xml\\customers.xml");
        custNameList = new ArrayList();
        custList = new ArrayList();
        
        if (xmlFile.exists())
        {
            try
            {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(xmlFile);
                doc.getDocumentElement().normalize();
                
                System.out.println("Root Element - " + doc.getDocumentElement().getNodeName());
                
                NodeList nList = doc.getElementsByTagName("Customer");
                System.out.println("_____________________");
                
                for (int temp = 0; temp < nList.getLength(); temp++) {
                    Node nNode = nList.item(temp);
                    
                    Element eElement = (Element) nNode;

                    String cName = getTagValue("CompanyName", eElement);
                    String cID = getTagValue("Id", eElement);
                    String cContact = getTagValue("Contact", eElement);
                    String cEmail = getTagValue("Email", eElement);
                    String cPhone = getTagValue("Phone", eElement);
                    String cAddress = getTagValue("Address", eElement);
                    String cCity = getTagValue("City", eElement);
                    String cState = getTagValue("State", eElement);
                    String cZip = getTagValue("Zipcode", eElement);
                    
                    System.out.println("Company Name - " + cName + "\r\nCustomer ID - " + cID + "\r\nContact Name - " + cContact +
                                      "\r\nEmail - " + cEmail + "\r\nPhone - " + cPhone + "\r\nAddress - " + cAddress + 
                                      "\r\nCity - " + cCity + "\r\nState - " + cState + "\r\nZipcode - " + cZip);
                    System.out.println("_______________________");
                    
                    custNameList.add(cName);
                    custList.add(new Customer(null, cName, cID, cContact, cEmail, cPhone, cAddress, cCity, cState, cZip));
                  
                }
                obList = FXCollections.observableList(custNameList);
              
            }
            catch(Exception ex) {
                showAlert("Unknown Error", "Unknown Error Occurred", Alert.AlertType.ERROR);
                return;
            }
           
        }
        else
        {
            showAlert("No Customers", "No Customers to Load!", Alert.AlertType.INFORMATION);
            return;
        }
 
    }
    
    @FXML
    private void loadQuotes() {
        
        //initialize the xml file
        File quoteXml = new File("src\\quickquoter\\app_data\\xml\\quotes.xml");
        
        //make sure the file exists, if it exists load the quotes, if not tell the user there are no quotes to load.
        if (quoteXml.exists()) {
            
            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(quoteXml);
                doc.getDocumentElement().normalize();
                
                NodeList quoteNodes = doc.getElementsByTagName("quote");
                
                for (int i = 0; i < quoteNodes.getLength(); i++) {
                    Node qNode = quoteNodes.item(i);
                    
                    Element qElement = (Element) qNode;
                    
                    int qId = Integer.parseInt(getTagValue("Id", qElement));
                    int custId = Integer.parseInt(getTagValue("CustomerId", qElement));
                    String qTitle = getTagValue("Title", qElement);
                    String qCreatedBy = getTagValue("CreatedBy", qElement);
                    String qDueDate = getTagValue("DueDate", qElement);
                    String qTotal = getTagValue("Total", qElement);
                    
                    //obQuoteList.add(new Quote(null, custId,))
                }
                
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(QuoteViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(QuoteViewController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(QuoteViewController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        else {
            
        }
    }
    
    @FXML
    private void getCustomerID() {

        //check to make sure the customer list has customers loaded
        if (custList.size() > 0) {
            //loop over the list to find the selected customer
            for (Customer custs : custList) {
                if (custs.getCustName().equals(cbCustomers.getSelectionModel().getSelectedItem())) {
                    txtCustomerId.setText(custs.getCustID());
                }
            }
        }

    }
    
    private String generateQuoteId() {
        
        String id = "";
        StringBuilder builder = new StringBuilder();
        Random rnd = new Random();
        
        for (int i = 0; i <= 8; i++) {
            builder.append(rnd.nextInt(9));
        }
        return id = builder.toString();
    }
    
    private String getTagValue(String sTag, Element eElement) {
        NodeList nList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        
        return nValue.getNodeValue(); 
    }
      
    private void showAlert(String title, String message, Alert.AlertType alerttype) {
        
        Alert alert = new Alert(alerttype);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
        
    }
}
