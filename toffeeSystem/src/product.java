public class product{
    private int id;
    private double price;
    private String name;
    private double Discount;
    private String category;
    product(int id, double productPrice, String Name, String Categ, double discount){
        this.id = id;
        this.price = productPrice;
        this.name = Name;
        this.category = Categ;
        this.Discount = discount;
        this.price = price - price*this.Discount;
    }
    int getID(){
        return id;
    }
    void setPrice(double price){
        this.price = price - price*Discount;

    }
    double getPrice(){
        return this.price;
    }
    void setName(String Name){
        name = Name;
    }
    String getName(){
        return this.name;
    }
    void setDiscount(double discount){
        this.Discount = discount;
        setPrice(price);
    }
    double getDiscount(){
        return Discount;
    }
    String getCategory(){
        return this.category;
    }
};