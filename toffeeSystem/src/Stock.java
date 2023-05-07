import com.sun.source.tree.TryTree;

import java.util.ArrayList;
import java.sql.*;
import java.io.*;
public class Stock {
    private ArrayList<product> products;
    Connection c;
    void RefreshProducts(){
        products.clear();
        try{
            String SQL = "SELECT ID, name, price, discount, category from STOCK";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                product p = new product(rs.getInt("ID"), rs.getDouble("price"), rs.getString("name"), rs.getString("category"), rs.getDouble(("discount")));
                products.add(p);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    Stock(){
        products = new ArrayList<product>();
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./StockDB.db");
            File f = new File("./StockDB.db");

        }
        catch (Exception e){
            File f = new File("./StockDB.db");
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
            st.execute("CREATE TABLE IF NOT EXISTS STOCK(ID integer primary key, name string, price Double, discount Double, category String )");

        }
        catch (Exception e){
            System.out.println("Couldn't create table");
            System.out.println(e.getMessage());
        }
        try{
            String SQL = "SELECT ID, name, price, discount, category from STOCK";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                product p = new product(rs.getInt("ID"), rs.getDouble("price"), rs.getString("name"), rs.getString("category"), rs.getDouble(("discount")));
                products.add(p);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public void AddNewProduct(int id, double productPrice, String Name, String Categ, double Discount){
        product pd = new product(id, productPrice, Name, Categ, Discount);
        boolean flag = true;
        if(this.products != null){
            for(int i = 0;i<products.size();i++){
                if(pd.getID() == products.get(i).getID()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            try {
                products.add(pd);
//                String SQL = "INSERT INTO STOCK (ID, name, price, discount, category) VALUES("+id+","+Name+","+productPrice+","+Discount+","+Categ+")";
                String SQL = "INSERT INTO STOCK (ID, name, price, discount, category) VALUES(?, ?, ?, ?, ?)";
                PreparedStatement stt = c.prepareStatement(SQL);
                stt.setInt(1, id);
                stt.setString(2, Name);
                stt.setDouble(3, productPrice);
                stt.setDouble(4, Discount);
                stt.setString(5, Categ);
                stt.executeUpdate();
                System.out.println("product added successfully");
            }
            catch (Exception e){
                System.out.println("Didn't work lol");
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Can't add a new product since you have a product with the same ID");
        }
        RefreshProducts();
    }
    public void DisplayProducts(){
            if(products.size() == 0){
                System.out.println("Stock is empty");
                return;
            }
            System.out.printf("ID                  Name                Price               Discount            Category\n");
            for(int i = 0;i<products.size();i++){
                System.out.printf("%-20d%-20s%-20.2f%-20.2f%-20s\n", products.get(i).getID(), products.get(i).getName(), products.get(i).getPrice(), products.get(i).getDiscount(), products.get(i).getCategory());
            }

    }
    public void UpdateProduct(int IDcpy, String Attribute, String Value){
        boolean flag = true;
        if(this.products != null){
            for(int i = 0;i<products.size();i++){
                if(IDcpy == products.get(i).getID()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            System.out.println("The ID you entered is not valid");
            return;
        }
        String SQL = "UPDATE STOCK SET " + Attribute + " = ? WHERE ID = ?";
        try{
            PreparedStatement st = c.prepareStatement(SQL);
            if(Attribute.equalsIgnoreCase("name") || Attribute.equalsIgnoreCase("category")) {
                st.setString(1, Value);
                st.setInt(2, IDcpy);
                st.executeUpdate();
                RefreshProducts();
            }
            else if(Attribute.equalsIgnoreCase("price") || Attribute.equalsIgnoreCase("discount")){
                st.setDouble(1, Double.parseDouble(Value));
                st.setInt(2, IDcpy);
                st.executeUpdate();
                RefreshProducts();

            }
            else{
                System.out.println("Please specify a valid entry (name/price/category/discount) to update");
            }
        }
        catch (Exception e){
            System.out.println("Update failed");
            System.out.println(e.getMessage());
        }
    }
    public boolean CheckProductByID(int productID){
        boolean flag = false;
        if(this.products != null){
            for(int i = 0;i<products.size();i++){
                if(productID == products.get(i).getID()){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    public boolean CheckProductByName(String productName){
        boolean flag = false;
        if(this.products != null){
            for(int i = 0;i<products.size();i++){
                if(productName.equalsIgnoreCase(products.get(i).getName())){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
    public void deleteProduct(int productID){
        if(!CheckProductByID(productID)){
            System.out.println("Invalid product ID");
            return;
        }
        try{
            String SQL = "DELETE FROM STOCK WHERE ID = ?";
            PreparedStatement st = c.prepareStatement(SQL);
            st.setInt(1, productID);
            st.executeUpdate();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        RefreshProducts();
    }
    public static void main(String[] args) {
        Stock st = new Stock();
//        st.DisplayProducts();
//        st.deleteProduct(2);
        st.DisplayProducts();
    }
};