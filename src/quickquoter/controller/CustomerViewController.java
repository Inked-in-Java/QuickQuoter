
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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
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
import quickquoter.model.Customer;
import quickquoter.model.Quote;

public class CustomerViewController implements Initializable {

    ObservableList<Customer> customerList;
    List<Quote> quotesList;
    ObservableList<Quote> obQuoteList;
    
    @FXML TextField txtId, txtCompanyName, txtContact, txtEmail, txtPhone, txtAddress, txtCity, 
              txtState, txtZipcode, txtSearch;
    
    @FXML Label lblMessage;
    
    @FXML AnchorPane mainAnchor;
    @FXML GridPane gpCustomerFields;
    @FXML TableView tvQuotes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Thread thread = new Thread();
       // thread.run();
       
        txtId.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtId.setStyle("-fx-background-color: #bfbfbf");
                generateId();
            }
        });
        
          txtCompanyName.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtCompanyName.setStyle("-fx-background-color: #bfbfbf");
            }
        });
          
          txtContact.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtContact.setStyle("-fx-background-color: #bfbfbf");
            }
        });
          
          txtEmail.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtEmail.setStyle("-fx-background-color: #bfbfbf");
            }
        });
          
          txtPhone.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtPhone.setStyle("-fx-background-color: #bfbfbf");
            }
        });
          
          txtAddress.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtAddress.setStyle("-fx-background-color: #bfbfbf");
            }
        });
          
          txtCity.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtCity.setStyle("-fx-background-color: #bfbfbf");
            }
        });
          
          txtState.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtState.setStyle("-fx-background-color: #bfbfbf");
            }
        });
          
          txtZipcode.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               txtZipcode.setStyle("-fx-background-color: #bfbfbf");
            }
        });
      }
      
    
    @FXML
    private void clearTextFields() {

        //loop over AnchorPane to find the textfields and clear them.
        for (javafx.scene.Node node : mainAnchor.getChildren()) {
            if (node instanceof GridPane) {
                for(javafx.scene.Node node1 : gpCustomerFields.getChildren()) {
                    if (node1 instanceof TextField) {
                        ((TextField)node1).setText("");
                        //System.out.println(node1.getId());
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
                   // System.out.println(currentNode.getNodeType());
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
                            if (c.getNodeName().equals("CompanyName")) {
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

                                    loadQuotes(childNodes.item(3).getTextContent());
                                 }
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
    
    private void loadQuotes(String custId) {
        
        File quoteXml = new File("src\\quickquoter\\app_data\\xml\\quotes.xml");
        quotesList = new ArrayList<>();

        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(quoteXml);
            
            Element root = doc.getDocumentElement();
            
            NodeList quoteList = root.getElementsByTagName("quote");
            for (int i = 0; i < quoteList.getLength(); i++) {
                Node quoteNode = quoteList.item(i);
                 
                if (quoteNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element node = (Element) quoteNode;
                    NodeList qChildNodes = node.getChildNodes();
                    
                    for (int x = 0; x < qChildNodes.getLength(); x++) {
                        Node itemNode = qChildNodes.item(x);
                        
                    
                        if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element itemElement = (Element) itemNode;
                            String id_final = custId.replace("-", "");

                            if (itemElement.getTextContent().equals(id_final)) {
                              
                               String custid = qChildNodes.item(1).getTextContent();
                               String id = qChildNodes.item(3).getTextContent();
                               String quantity = qChildNodes.item(5).getTextContent();
                               String status = qChildNodes.item(7).getTextContent();
                               String title = qChildNodes.item(9).getTextContent();
                               String desc = qChildNodes.item(11).getTextContent();
                               String comment = qChildNodes.item(13).getTextContent();
                               String createdby = qChildNodes.item(15).getTextContent();
                               String duedate = qChildNodes.item(17).getTextContent();
                               double total = Double.parseDouble(qChildNodes.item(19).getTextContent());
                               
                               //add quote to quoteList.
                                quotesList.add(new Quote(null, Integer.parseInt(id), Integer.parseInt(custid), Integer.parseInt(quantity), 
                                         Integer.parseInt(status), title,  desc, comment, createdby, duedate, total));
                            }
                        }
                    }
                }
            }
            obQuoteList = FXCollections.observableArrayList(quotesList);
            buildQuoteTable();
            
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String getTagValue(String sTag, Element eElement) {
        
        NodeList nList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = (Node) nList.item(0);
        
        return nValue.getNodeValue(); 
    }
    
    private void buildQuoteTable() {
       
        //id col
        TableColumn<Quote, Integer> idCol = new TableColumn<>("Id");
        idCol.setMinWidth(75);
        idCol.setCellValueFactory(new PropertyValueFactory<>("Id"));
        
        //custId col
        TableColumn<Quote, Integer> custIdCol = new TableColumn<>("Customer Id");
        custIdCol.setMinWidth(100);
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("custId"));
        
        //Quantity col
        TableColumn<Quote, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setMinWidth(75);
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        
        //Status col
        TableColumn<Quote, Integer> statusCol = new TableColumn<>("Status");
        statusCol.setMinWidth(75);
        statusCol.setCellValueFactory(new PropertyValueFactory<>("Status"));
        
        //Title col
        TableColumn<Quote, Integer> titleCol = new TableColumn<>("Title");
        titleCol.setMinWidth(150);
        titleCol.setCellValueFactory(new PropertyValueFactory<>("Title"));
        
        //Description col
        TableColumn<Quote, Integer> descCol = new TableColumn<>("Description");
        descCol.setMinWidth(300);
        descCol.setCellValueFactory(new PropertyValueFactory<>("Desc"));
        
        //Comment col
        TableColumn<Quote, Integer> commentCol = new TableColumn<>("Comment");
        commentCol.setMinWidth(200);
        commentCol.setCellValueFactory(new PropertyValueFactory<>("Comment"));
        
         //Created by col
        TableColumn<Quote, Integer> createdbyCol = new TableColumn<>("Created By");
        createdbyCol.setMinWidth(75);
        createdbyCol.setCellValueFactory(new PropertyValueFactory<>("CreatedBy"));
        
         //Due Date col
        TableColumn<Quote, Integer> duedateCol = new TableColumn<>("Due Date");
        duedateCol.setMinWidth(100);
        duedateCol.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        
        //Total col
        TableColumn<Quote, Integer> totalCol = new TableColumn<>("Total");
        totalCol.setMinWidth(75);
        totalCol.setCellValueFactory(new PropertyValueFactory<>("Total"));
        
        
        tvQuotes.setItems(obQuoteList);
        tvQuotes.getColumns().addAll(idCol, custIdCol, quantityCol, statusCol, titleCol, descCol, commentCol,
                                     createdbyCol, duedateCol, totalCol);
    }
 
  
  
    
    
    private class Thread implements Runnable {

        @Override
        public void run() {
            //LoadCustomer();
        }
        
    }
    
}
