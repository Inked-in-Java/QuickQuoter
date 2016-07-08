
package quickquoter.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

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
        //Thread thread = new Thread();
       // thread.run();
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
    private void saveCustomer() {
        
        File customerXml = new File("src\\quickquoter\\app_data\\xml\\customers.xml");
        customerList = FXCollections.observableArrayList();
        
        try {
            if (validateInput()) {
                showAlert("Input Error", "All Fields Required", AlertType.WARNING);
               
            }
            else {
                if (!customerXml.exists()) {
                    DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document doc = docBuilder.newDocument();
                  //Root Element  
                    Element root = doc.createElement("Customers");
                    doc.appendChild(root);

                  //Customer Element
                   root.appendChild(setCustomer(doc, txtCompanyName.getText(), txtId.getText(), txtContact.getText(),
                                   txtEmail.getText(), txtPhone.getText(), txtAddress.getText(), txtCity.getText(), txtState.getText(),
                                   txtZipcode.getText()));

                    TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "Yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(customerXml);
                    transformer.transform(source, result);
                }
                else {
                    if (validateCustomer(txtCompanyName.getText())) {
                        showAlert("Customer Already Exists", "Customer Already Exists,\r\nPlaese choose a different Name!", AlertType.WARNING);
                    }
                    else {              
                        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                        Document document = dBuilder.parse(customerXml);
                        Element root = document.getDocumentElement();

                        root.appendChild(setCustomer(document, txtCompanyName.getText(), txtId.getText(), txtContact.getText(),
                               txtEmail.getText(), txtPhone.getText(), txtAddress.getText(), txtCity.getText(), txtState.getText(),
                               txtZipcode.getText()));

                        DOMSource source = new DOMSource(document);

                        TransformerFactory transformerFactory = TransformerFactory.newInstance();
                        Transformer transformer = transformerFactory.newTransformer();
                        StreamResult result = new StreamResult(customerXml);
                        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                        transformer.transform(source, result);
                    }
                } 
            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    @FXML
    private void resetInput() {
        //Loop over all the text fields and set the background color to red if the text field is empty
          for (javafx.scene.Node node : mainAnchor.getChildren()) {
            if (node instanceof GridPane) {
                for(javafx.scene.Node node1 : gpCustomerFields.getChildren()) {
                    if (node1 instanceof TextField) {
                        ((TextField)node1).setStyle("-fx-background-color: linear-gradient(#fa7b7b, #fa3741);" +
                                                     "-fx-border-color: #2e2e2e;" + 
                                                     "-fx-border-radius: 4px;");
                      
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
                            isEmpty = true;
                             ((TextField)node1).setStyle("-fx-background-color: linear-gradient(#fa7b7b, #fa3741);" +
                                                     "-fx-border-color: #2e2e2e;" + 
                                                     "-fx-border-radius: 4px;");
                           //for testing only.
                            System.out.println(node1.getId());
                        }
                    }
                }
            }
        }
      
        return isEmpty;
    }
    
    private boolean validateCustomer(String custName) {
        
        //make sure the customer doesnt already exist.
         File customerXml = new File("src\\quickquoter\\app_data\\xml\\customers.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(customerXml);
            Element root = document.getDocumentElement();
            
            NodeList nodes = root.getElementsByTagName("Customer");
            //list of [Customer] Nodes
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                NodeList childNodes = node.getChildNodes(); 
                
                //list of Child Nodes of [Customer]
                for (int y = 0; y < childNodes.getLength(); y++) {
                    Node currentNode = childNodes.item(y);
                    //for testing only
                    System.out.println(currentNode.getNodeType());
                    if (currentNode.getTextContent().equals(custName)) {
                        System.out.println("Found Customer - [" + currentNode.getTextContent() + "]");
                        //customer matches, so the customer already exists
                        return true;
                    }
                } 
            }     
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        //customer does not exist.
        return false;
    }
    
    @FXML
    private void searchCustomer() {
          //make sure the customer doesnt already exist.
         File customerXml = new File("src\\quickquoter\\app_data\\xml\\customers.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;
         String _name = txtSearch.getText();
         
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(customerXml);
            Element root = document.getDocumentElement();
            
            NodeList nodes = root.getElementsByTagName("Customer");
            //list of [Customer] Nodes
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element customer = (Element) node;
                    NodeList childNodes = customer.getChildNodes(); 
                    for(int x = 0; x < childNodes.getLength(); x++) {
                        Node custName = childNodes.item(x);

                        if (custName.getNodeType() == Node.ELEMENT_NODE) {
                            Element c = (Element) custName;

                            if (c.getTextContent().equals(_name)) {  
                                txtId.setText(childNodes.item(3).getTextContent());
                                txtCompanyName.setText(childNodes.item(1).getTextContent());
                                txtContact.setText(childNodes.item(5).getTextContent());
                                txtEmail.setText(childNodes.item(7).getTextContent());
                                txtPhone.setText(childNodes.item(9).getTextContent());
                                txtAddress.setText(childNodes.item(11).getTextContent());
                                txtCity.setText(childNodes.item(13).getTextContent());
                                txtState.setText(childNodes.item(15).getTextContent());
                                txtZipcode.setText(childNodes.item(17).getTextContent());
                            }
                            
                        }
                    }
                }
            }     
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void showAlert(String title, String message, AlertType alerttype) {
        
        Alert alert = new Alert(alerttype);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
        
    }
    
    private Element setCustomer(Document doc, String custName, String custID, String contactName,
            String email, String phone, String address, String city, String state, String zip) {
    
        Element customer = doc.createElement("Customer");
        
        customer.appendChild(setCustomerElements(doc, customer, "CompanyName", custName));
        customer.appendChild(setCustomerElements(doc, customer, "Id", custID));
        customer.appendChild(setCustomerElements(doc, customer, "Contact", contactName));
        customer.appendChild(setCustomerElements(doc, customer, "Email", email));
        customer.appendChild(setCustomerElements(doc, customer, "Phone", phone));
        customer.appendChild(setCustomerElements(doc, customer, "Address", address));
        customer.appendChild(setCustomerElements(doc, customer, "City", city));
        customer.appendChild(setCustomerElements(doc, customer, "State", state));
        customer.appendChild(setCustomerElements(doc, customer, "Zipcode", zip));
  
        return customer;
    }
    
    private Element setCustomerElements(Document doc, Element element, String name, String value) {
    
        element = doc.createElement(name);
        element.appendChild(doc.createTextNode(value));
        return  element;
    }

 
    private void addNode(String tagName, String value, Node parent) {
    Document dom = parent.getOwnerDocument();
     
    // Create a new Node with the given tag name
    Node node = dom.createElement(tagName);
     
    // Add the node value as a child text node
    Text nodeVal = dom.createTextNode(value);
    Node c = node.appendChild(nodeVal);
     
    // Add the new node structure to the parent node
    parent.appendChild(node);
}
 
  
    
    
    private class Thread implements Runnable {

        @Override
        public void run() {
            //LoadCustomer();
        }
        
    }
    
}
