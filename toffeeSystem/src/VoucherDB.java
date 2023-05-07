import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class VoucherDB {
    private ArrayList<Voucher> vouchers;
    private Connection c;

    VoucherDB(){
        vouchers = new ArrayList<Voucher>();
        try{
            c = DriverManager.getConnection("jdbc:sqlite:./VoucherDB.db");
        }
        catch (Exception e){
            File f = new File("./VoucherDB.db");
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
            st.execute("CREATE TABLE IF NOT EXISTS Voucher(ID integer primary key, DiscountAmount Double)");
        }
        catch (Exception e){
            System.out.println("Couldn't create table");
            System.out.println(e.getMessage());
        }
        try{
            String SQL = "SELECT ID, DiscountAmount from Voucher";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                Voucher v = new Voucher(rs.getInt("ID"), rs.getDouble("DiscountAmount"));
                vouchers.add(v);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void refreshVouchers(){
        vouchers.clear();
        try{
            String SQL = "SELECT ID, DiscountAmount from Voucher";
            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery(SQL);
            while(rs.next()){
                Voucher v = new Voucher(rs.getInt("ID"), rs.getDouble("DiscountAmount"));
                vouchers.add(v);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void addVoucher(int id, double DiscountAmount){
        Voucher v = new Voucher(id, DiscountAmount);
        boolean flag = true;
        if(this.vouchers != null){
            for(int i = 0;i<vouchers.size();i++){
                if(v.getId() == vouchers.get(i).getId()){
                    flag = false;
                    break;
                }
            }
        }
        if(flag){
            try {
                vouchers.add(v);
                String SQL = "INSERT INTO Voucher (ID, DiscountAmount) VALUES(?, ?)";
                PreparedStatement stt = c.prepareStatement(SQL);
                stt.setInt(1, id);
                stt.setDouble(2, DiscountAmount);
                stt.executeUpdate();
                System.out.println("product added successfully");
            }
            catch (Exception e){
                System.out.println("Didn't work lol");
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Can't add a new product since you have a product with the same ID");
        }
    }

    public void removeVoucher(int id){

        if(this.vouchers != null){
            for(int i = 0;i<vouchers.size();i++){
                if( id == vouchers.get(i).getId()){

                    vouchers.remove(i);

                    try{
                        String SQL = "DELETE FROM Voucher WHERE ID = ?";
                        PreparedStatement stt = c.prepareStatement(SQL);
                        stt.setInt(1,id);
                        stt.executeUpdate();
                        System.out.println("Voucher Deleted Successfully");
                    }catch(Exception e) {
                        System.out.println("It Failed For Some Reason IDK ");
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    public boolean checkVoucher(int id){
        refreshVouchers();
        for (int i = 0; i < vouchers.size() ; i++) {
            if(id == vouchers.get(i).getId()){
                return true;
            }
        }
        return false;
    }

    public double getVoucher(int id){
        refreshVouchers();
        for (int i = 0; i < vouchers.size() ; i++) {
            if(id == vouchers.get(i).getId()){
                double discount = vouchers.get(i).getDiscountAmount();
                removeVoucher(id);
                return discount;
            }
        }
        return 0;
    }

//  Used for Admin Testing Only
    public void displayVouchers(){
        if(vouchers.size() == 0){
            System.out.println("No Vouchers In The Data Base");
            return;
        }
        System.out.printf("ID                  DiscountAmount\n");
        for(int i = 0;i<vouchers.size();i++){
            System.out.printf("%-20d%-20.2f\n", vouchers.get(i).getId(), vouchers.get(i).getDiscountAmount());
        }
    }


}
