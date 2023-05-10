
public class Admin extends unregisteredUser {
    private int id;
    private String name;
    private String password;
    private int age;
    private String gender;
    private String address;
    private String email;
    private  Stock stock;
    private VoucherDB voucherDB;
    private LoyaltyPointScheme loyaltyscheme;


    public Admin(int id, String name,String password, int age, String gender, String address, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.email = email;
        stock = new Stock();
        voucherDB = new VoucherDB();
        loyaltyscheme = new LoyaltyPointScheme();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void editProduct(int id, String attribute, String value){
        stock.UpdateProduct(id,attribute,value);
    }

    public void addProduct(int id, double price, String name, String category, double discount, int size){
        stock.AddNewProduct(id,price,name,category,discount,size);
    }
    public void removeProduct(int id){

        stock.deleteProduct(id);
    }
    public void editVoucher(int id, String attribute, String value){
        voucherDB.UpdateVoucher(id,attribute,value);
    }

    public void addVoucher(int id, double discount){
        voucherDB.addVoucher(id,discount);
    }

    public void removeVoucher(int id){
        voucherDB.removeVoucher(id);
    }
    public void viewVouchers(){
        voucherDB.displayVouchers();
    }
    public void setLoyaltyPointSchemeAmount(int amount){
        loyaltyscheme.setPointsGained(amount);
    }

    public void viewStockStatistics(){
        stock.stockStatistics();
    }
}
