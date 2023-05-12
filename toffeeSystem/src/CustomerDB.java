import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDB {
    private ArrayList<Customer> customers;
    private Connection c;
    CustomerDB(){
        customers = new ArrayList<Customer>();
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./customerDB.db");
        }
        catch (Exception e){
            File f = new File("./customerDB.db");
            if(!(f.exists())){
                System.out.println("Couldn't create dataBase");
                System.out.println(e.getMessage());
            }
            else{
                System.out.println(e.getMessage());
            }
        }
        try{
            Statement st = c.createStatement();
                                                            // Customer(, , , , , , , ){
            st.execute("CREATE TABLE IF NOT EXISTS CustomersDB(ID integer primary key, name string,Password String, age INTEGER, gender String, address String, email String, loyaltyPoints integer)");

        }
        catch (Exception e){
            System.out.println("Couldn't create table");
            System.out.println(e.getMessage());
        }
        try{
            String SQL = "SELECT ID, name, Password, age, gender, address, email, loyaltyPoints from CustomersDB";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                //Customer(int ID, String Name, int Age, String G, String Address, String Email, int loyalityPoints, String password){
                Customer cust = new Customer(rs.getInt("id"), rs.getString("Name"), rs.getInt("Age"), rs.getString("gender"), rs.getString("address"), rs.getString("email"), rs.getInt("loyaltyPoints"), rs.getString("password"));
                customers.add(cust);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    void refreshCustomers(){
        customers.clear();
        try{
            String SQL = "SELECT ID, name, Password, age, gender, address, email, loyaltyPoints from CustomersDB";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                Customer cust = new Customer(rs.getInt("id"), rs.getString("Name"), rs.getInt("Age"), rs.getString("gender"), rs.getString("address"), rs.getString("email"), rs.getInt("loyaltyPoints"), rs.getString("password"));
                customers.add(cust);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addNewCustomer(int ID, String Name, int Age, String G, String Address, String Email, int loyalityPoints, String password){
        Customer cust = new Customer(ID, Name, Age, G, Address, Email, loyalityPoints, password);
        boolean flag = true;
        if(this.customers != null){
            for(int i = 0;i<customers.size();i++){
                if(cust.getID() == customers.get(i).getID()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            try {
                customers.add(cust);
//                int ID, String Name, int Age, String G, String Address, String Email, int loyalityPoints, String password){
//                SELECT ID, name, Password, age, gender, address, email, loyaltyPoints from CustomersDB";
                String SQL = "INSERT INTO CustomersDB (ID, name, Password, age, gender, address, email, loyaltyPoints) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stt = c.prepareStatement(SQL);
                stt.setInt(1, ID);
                stt.setString(2, Name);
                stt.setString(3, password);
                stt.setInt(4, Age);
                stt.setString(5, G);
                stt.setString(6, Address);
                stt.setString(7, Email);
                stt.setInt(8, loyalityPoints);
                stt.executeUpdate();
                System.out.println("Customer added successfully");
            }
            catch (Exception e){
                System.out.println("Didn't work lol");
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Can't add a Customer since you have a customer with the same ID");
        }
    }
    public void DisplayCustomers(){
        if(customers.size() == 0){
            System.out.println("There is no Customers");
            return;
        }
        System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "ID", "Name", "Age", "Gender", "Address", "Email", "Loyalty points");
        for(int i = 0;i<customers.size();i++){
            System.out.printf("%-20d%-20s%-20d%-20s%-20s%-20s%-20s\n", customers.get(i).getID(), customers.get(i).getName(), customers.get(i).getAge(), customers.get(i).getGender(), customers.get(i).getAddress(), customers.get(i).getEmail(), customers.get(i).getloyalityPoints());
        }
    }
    public boolean checkCustomerByID(int AdminID){
        boolean flag = false;
        if(this.customers != null){
            for(int i = 0;i<customers.size();i++){
                if(AdminID == customers.get(i).getID()){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public boolean checkCustomerByName(String name){
        boolean flag = false;
        if(this.customers != null){
            for(int i = 0;i<customers.size();i++){
                if(name.equalsIgnoreCase(customers.get(i).getName())){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    public Customer getCustomerByName(String name){
        if(!checkCustomerByName(name)){
            System.out.println("Admin of Name "+ name + " does not exist");
        }
        for(int i = 0;i<customers.size();i++){
            if(name.equalsIgnoreCase(customers.get(i).getName())){
                return customers.get(i);
            }
        }
        return null;
    }

    public void DeleteCustomer(int adminId){
        if(!checkCustomerByID(adminId)){
            System.out.println("Invalid Admin ID");
            return;
        }
        try{
            String SQL = "DELETE FROM CustomersDB WHERE ID = ?";
            PreparedStatement st = c.prepareStatement(SQL);
            st.setInt(1, adminId);
            st.executeUpdate();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        refreshCustomers();
    }
}
