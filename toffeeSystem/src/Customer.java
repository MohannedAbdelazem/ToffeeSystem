public class Customer extends unregisteredUser {
    private int CustomerID;
    private String name;
    private int age;
    private char Gender;
    private String address;
    private String email;
    private int loyalityPoints;
    Customer(int ID, String Name, int Age, char G, String Address, String Email, int loyalityPoints){
        CustomerID = ID;
        this.name = Name;
        this.age = Age;
        this.Gender = G;
        this.address = Address;
        this.email = Email;
        this.loyalityPoints = loyalityPoints;
    }
    int getID(){
        return this.CustomerID;
    }
    void setName(String Name){
        this.name = Name;
    }
    String getName(){
        return this.name;
    }
    void setAge(int Age){
        this.age = Age;
    }
    int getAge(){
        return this.age;
    }
    void SetGender(char G){
        this.Gender = G;
    }
    char getGender(){
        return this.Gender;
    }
    String getAddress(){
        return this.address;
    }
    void updateAddress(String newAddress){
        this.address = newAddress;
    }
    void updateEmail(String Email){
        this.email = Email;
    }
    String getEmail(){
        return this.email;
    }
    void setLoyalityPoints(int LP){
        this.loyalityPoints = LP;
    }
    int getloyalityPoints(){
        return this.loyalityPoints;
    }

}
