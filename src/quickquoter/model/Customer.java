
package quickquoter.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    
    ObservableList<Quote> quoteList = FXCollections.observableArrayList();
    
    private String CustName;
   
    private String CustID;
   
    private String ContactName;
   
    private String Email;
   
    private String Phone;
    
    private String Address;
    
    private String City;
   
    private String State;
   
    private String Zip;

    public Customer()
    {
        
    }

    public Customer(ObservableList<Quote> quotelist, String CustName, String CustID, String ContactName, String Email, String Phone, String Address, String City, String State, String Zip) {
        this.quoteList = quotelist;
        this.CustName = CustName;
        this.CustID = CustID;
        this.ContactName = ContactName;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
        this.City = City;
        this.State = State;
        this.Zip = Zip;
    }
    

    /**
     * @return the CustName
     */
   
    public String getCustName() {
        return CustName;
    }

    /**
     * @param CustName the CustName to set
     */
    public void setCustName(String CustName) {
        this.CustName = CustName;
    }

    /**
     * @return the CustID
     */
    public String getCustID() {
        return CustID;
    }

    /**
     * @param CustID the CustID to set
     */
    public void setCustID(String CustID) {
        this.CustID = CustID;
    }

    /**
     * @return the ContactName
     */
    public String getContactName() {
        return ContactName;
    }

    /**
     * @param ContactName the ContactName to set
     */
    public void setContactName(String ContactName) {
        this.ContactName = ContactName;
    }

    /**
     * @return the Email
     */
    public String getEmail() {
        return Email;
    }

    /**
     * @param Email the Email to set
     */
    public void setEmail(String Email) {
        this.Email = Email;
    }

    /**
     * @return the Phone
     */
    public String getPhone() {
        return Phone;
    }

    /**
     * @param Phone the Phone to set
     */
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    /**
     * @return the Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * @param Address the Address to set
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * @return the City
     */
    public String getCity() {
        return City;
    }

    /**
     * @param City the City to set
     */
    public void setCity(String City) {
        this.City = City;
    }

    /**
     * @return the State
     */
    public String getState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    public void setState(String State) {
        this.State = State;
    }

    /**
     * @return the Zip
     */
    public String getZip() {
        return Zip;
    }

    /**
     * @param Zip the Zip to set
     */
    public void setZip(String Zip) {
        this.Zip = Zip;
    }
    
}
