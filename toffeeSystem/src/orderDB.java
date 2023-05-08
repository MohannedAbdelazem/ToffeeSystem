import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class orderDB {
    private ArrayList<order> orders;
    private Connection c;
    void RefreshOrders(){
        orders.clear();
        try{
            // ORDER(orderid int, cartid int, customerID int, status String)
            String SQL = "SELECT orderID, CartID, CustomerID, Status from ORDER";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                order p = new order(rs.getInt("orderID"), rs.getInt("CartID"), rs.getInt("CustomerID"), rs.getString("Status"));
                orders.add(p);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    orderDB(){
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./OrderDB.db");
        }
        catch (Exception e){
            File f = new File("./OrderDB.db");
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
            st.execute("CREATE TABLE IF NOT EXISTS ORDER(orderid int, cartid int, customerID int, status String)");
        }
        catch (Exception e){
            System.out.println("Couldn't create table");
            System.out.println(e.getMessage());
        }

    }

    public void AddNewOrders(int orderID, int cartID, int customerID, String status){
        order pd = new order(orderID, customerID, cartID, status);
        boolean flag = true;
        if(this.orders != null){
            for(int i = 0;i<orders.size();i++){
                if(pd.getOrderID() == orders.get(i).getOrderID()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            try {
                orders.add(pd);
//String SQL = "SELECT orderID, CartID, CustomerID, Status from ORDER";
                String SQL = "INSERT INTO OrderDB (orderID, CartID, CustomerID, Status) VALUES(?, ?, ?, ?)";
                PreparedStatement stt = c.prepareStatement(SQL);
                stt.setInt(1, orderID);
                stt.setInt(2, cartID);
                stt.setInt(3, customerID );
                stt.setString(4, status);
                stt.executeUpdate();
                System.out.println("Order added successfully");
            }
            catch (Exception e){
                System.out.println("Didn't work lol");
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Can't add a new order since you have an order with the same ID");
        }

    }
    public void DisplayProducts(){
        if(orders.size() == 0){
            System.out.println("Order data base  is empty");
            return;
        }
        System.out.printf("OrderID                  CartID                CustomerID               Status\n");
        for(int i = 0;i<orders.size();i++){
            System.out.printf("%-20d%-20d%-20d%-20s%\n", orders.get(i).getOrderID(), orders.get(i).getCartID(), orders.get(i).getCustomerID(), orders.get(i).getStatus());
        }

    }

    public boolean CheckOrderByID(int orderID){
        boolean flag = false;
        if(this.orders != null){
            for(int i = 0;i<orders.size();i++){
                if(orderID == orders.get(i).getOrderID()){
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    public void deleteOrder(int orderID){
        if(!CheckOrderByID(orderID)){
            System.out.println("Invalid order ID");
            return;
        }
        try{
            String SQL = "DELETE FROM order WHERE orderID = ?";
            PreparedStatement st = c.prepareStatement(SQL);
            st.setInt(1, orderID);
            st.executeUpdate();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        RefreshOrders();
    }

    public static void main(String[] args) {
        orderDB dd = new orderDB();
    }
}
