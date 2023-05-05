// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.sql.*;
class Main{
    static void connectSQLServerr(){
        try{
            Connection CONNECTION = DriverManager.getConnection("jdbc:sqlite:./StockDB.db");
            System.out.print("Connection Succeeded");
        }
        catch (Exception e){
            System.out.print("Connection Failed");
        }
    }
    public static void main(String[] args) {
        connectSQLServerr();
    }
}