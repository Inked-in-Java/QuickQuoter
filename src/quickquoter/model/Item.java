
package quickquoter.model;

public class Item {
    
    private int Id;
    private int Quantity;
   
    private String Name;
    private String Desc;
    private String Comment;
    
    private double Price;

    public Item(int Id, int Quantity, String Name, String Desc, String Comment, double Price) {
        this.Id = Id;
        this.Quantity = Quantity;
        this.Name = Name;
        this.Desc = Desc;
        this.Comment = Comment;
        this.Price = Price;
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

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    
    
    
    
}
