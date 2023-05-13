import java.util.ArrayList;
import java.sql.*;
import java.io.*;
public class cartDB {
    private Connection c;
    cartDB(){
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./CartDB.db");


        }
        catch (Exception e){
            File f = new File("./CartDB.db");
            if(!(f.exists())){
                System.out.println("Couldn't create dataBase");
                System.out.println(e.getMessage());
            }
            else{
                System.out.println(e.getMessage());
            }
        }

    }
    public void AddNewCart(ShoppingCart ct, int custID, int cartID){
            try {
                for(int i = 0;i<ct.cart.size();i++){
                    String Tablee = Integer.toString(custID)+"_"+Integer.toString(cartID);
                    String SQL = "INSERT INTO " + Tablee + "(ID, name, price, discount, category, size) VALUES(?, ?, ?, ?, ?, ?)";
                    PreparedStatement stt = c.prepareStatement(SQL);
                    stt.setInt(1, ct.cart.get(i).getID());
                    stt.setString(2, ct.cart.get(i).getName());
                    stt.setDouble(3, ct.cart.get(i).getPrice());
                    stt.setDouble(4, ct.cart.get(i).getDiscount());
                    stt.setString(5, ct.cart.get(i).getCategory());
                    stt.setInt(6, ct.cart.get(i).getSize());
                    stt.executeUpdate();
//                    System.out.println("product added successfully");
                }
//                String SQL = "INSERT INTO STOCK (ID, name, price, discount, category) VALUES("+id+","+Name+","+productPrice+","+Discount+","+Categ+")";
            }
            catch (Exception e){
                System.out.println("Didn't work lol");
                System.out.println(e.getMessage());
            }

    }
    public void DisplayCustomerCart(int CustomerID, int cartID){
        System.out.printf("Name            Size\n");
        String V = Integer.toString(CustomerID)+"_"+Integer.toString(cartID);
        String SQL = "SELECT name, size from "+V;
        try{
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                System.out.printf("%s\n", rs.getString("name"), rs.getInt("size"));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}