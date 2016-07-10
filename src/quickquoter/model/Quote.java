
package quickquoter.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Quote {
    
    private ObservableList<Item> itemList = FXCollections.observableArrayList();
    
    private int Id;
    private int custId;
    private int Quantity;
    private int Status;
    
    private String Title;
    private String Desc;
    private String Comment;
    private String CreatedBy;
    private String DueDate;
    
    
    
    private double Total;

    public Quote(ObservableList<Item> itemList, int Id, int custId, int Quantity, int Status, String Title, String Desc, String Comment, String CreatedBy, String DueDate, double Total) {
        this.Id = Id;
        this.custId = custId;
        this.Quantity = Quantity;
        this.Status = Status;
        this.Title = Title;
        this.Desc = Desc;
        this.Comment = Comment;
        this.CreatedBy = CreatedBy;
        this.DueDate = DueDate;
        this.Total = Total;
    }

    public ObservableList<Item> getItemList() {
        return itemList;
    }

    public void setItemList(ObservableList<Item> itemList) {
        this.itemList = itemList;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String Desc) {
        this.Desc = Desc;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String Comment) {
        this.Comment = Comment;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }
    
      public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }
    
}
