import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Toffee {
    public static String generateOTP(){
        int otpl = 6;
        String numbers = "0123456789";
        Random random = new Random();
        char[] otp = new char[otpl];
        for(int i = 0;i<otpl;i++){
            otp[i] = numbers.charAt(random.nextInt(numbers.length()));

        }
        return new String(otp);
    }
    public String sendOTP(String mail)throws Exception{
        String OPT = generateOTP();
        String from = "niceuser619@gmail.com";
        String password = "ourteampassword";
        String host = "smtp.gmail.com";
        Properties props = new Properties();

        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            protected  PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail));
            message.setText("Your OTP is: "+ OPT);
            Transport.send(message);
            System.out.println("Check you email");
        }
        catch (Exception e){
            throw new RuntimeException((e));
        }
        return OPT;
    }


private Scanner scan = new Scanner(System.in);

    public Toffee() {
//        TEMPORARY USED FOR TESTING
//        admins.addNewAdmin(1,"abdo","123",32,"Male","32 oggabogga street","onga@email.bonga");
//        adminInterface(admins.getAdmin("abdo"));
        boolean loop = true;
        System.out.println("####################################################################################");
        System.out.println("####################################################################################");
        System.out.println("######  ########      ######       ########   ########   ########   ########  ######");
        System.out.println("######     ##       ##      ##     ##         ##         ##         ##        ######");
        System.out.println("######     ##       #        #     ########   ########   ########   ########  ######");
        System.out.println("######     ##       ##     ##      ##         ##         ##         ##        ######");
        System.out.println("######     ##        ######        ##         ##         ########   ########  ######");
        System.out.println("####################################################################################");
        System.out.println("####################################################################################");
        while (loop) {


            unregisteredUser guest = new unregisteredUser();
            label:System.out.println("\n");
            System.out.println("Welcome to our Toffee application");
            System.out.println("\"Choose One Of The Following Options:");
            System.out.println("1.View products");
            System.out.println("2.Search for an item");
            System.out.println("3.Register");
            System.out.println("4.Login");
            System.out.println("5.Exit");
            System.out.print("-->");
            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1: {
                    guest.viewProducts();
                    break;
                }

                case 2: {
                    System.out.print("Please Enter the name of the item you want to search for: ");
                    String p = scan.nextLine();
                    guest.searchItem(p);
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5:{
                    System.out.println("Goodbye, come again!");
                    loop = false;
                    break;
                }
                default:{
                    System.out.println("Invalid option please enter a valid option");
                    break;
                }

            }
        }
    }

    void login (){
        AdminDB admins = new AdminDB();
        System.out.println("------Welcome To Toffee------");
        System.out.println("-----------Log-IN-----------\n\n\n");
        System.out.print("Username: ");
        String name = scan.nextLine();

        System.out.print("password: ");
        String pass = scan.nextLine();

        if(admins.getAdmin(name).getPassword() == pass ){
            adminInterface(admins.getAdmin(name));

        //need to add another if to check if the credentials is for a customer
        }
        else{
            System.out.println("Wrong Username Or Password");
        }
    }

    public void adminInterface(Admin admin){
        while (true) {
            System.out.println("\n------Welcome To Toffee------");
            System.out.println("-----------Admin-----------\n\n");
            System.out.println("Choose One Of The Following Options\n");
            System.out.println("1.View Products");
            System.out.println("2.Search For Product");
            System.out.println("3.Edit Product");
            System.out.println("4.Add Product");
            System.out.println("5.Remove Product");
            System.out.println("6.Edit Voucher");
            System.out.println("7.Add Voucher");
            System.out.println("8.Remove Voucher");
            System.out.println("9.View Vouchers");
            System.out.println("10.View Statistics");
            System.out.println("11.Set Loyalty Point Scheme");
            System.out.println("12.Log Out");
            System.out.println("13.Exit");

            int choice = Integer.parseInt(scan.nextLine());
            switch (choice) {
                case 1: {
                    System.out.println("------View Products------\n");

                    admin.viewProducts();
                    break;
                }
                case 2: {
                    System.out.println("------Search For Product------\n");

                    System.out.print("\nEnter Product Name: ");
                    String name = scan.nextLine();

                    admin.searchItem(name);
                    break;
                }
                case 3: {
                    System.out.println("------Edit Product------\n");

                    System.out.print("Enter Product ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    System.out.print("\nEnter Attribute to change (Name, Price, Size, Category, Discount, Size): ");
                    String attribute = scan.nextLine();

                    System.out.print("\nEnter The New Value: ");
                    String value = scan.nextLine();

                    admin.editProduct(id, attribute, value);
                    break;
                }
                case 4: {
                    System.out.println("------Add Product------\n");
                    System.out.print("Enter Product ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    System.out.print("\nEnter Product Name: ");
                    String name = scan.nextLine();

                    System.out.print("\nEnter Product Price: ");
                    double price = Double.parseDouble(scan.nextLine());

                    System.out.print("\nEnter Product Discount: ");
                    double discount = Double.parseDouble(scan.nextLine());

                    System.out.print("\nEnter Product Category: ");
                    String category = scan.nextLine();

                    System.out.print("\nEnter Product Size: ");
                    int size = Integer.parseInt(scan.nextLine());

                    admin.addProduct(id, price, name, category, discount, size);
                    break;
                }
                case 5: {
                    System.out.println("------Remove Product------\n");

                    System.out.print("Enter Product ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    admin.removeProduct(id);
                    break;
                }
                case 6: {
                    System.out.println("------Edit Voucher------\n");

                    System.out.print("Enter Voucher ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    System.out.print("\nEnter Attribute to change (Discount)");
                    String attribute = scan.nextLine();

                    System.out.print("\nEnter The New Value: ");
                    String value = scan.nextLine();

                    admin.editVoucher(id, attribute, value);
                    break;
                }
                case 7: {
                    System.out.println("------Add Voucher------\n");

                    System.out.print("Enter Voucher ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    System.out.print("\nEnter Discount Amount: ");
                    double discount = Double.parseDouble(scan.nextLine());

                    admin.addVoucher(id, discount);
                    break;
                }
                case 8: {
                    System.out.println("------Remove Voucher------\n");

                    System.out.print("Enter Voucher ID: ");
                    int id = Integer.parseInt(scan.nextLine());

                    admin.removeVoucher(id);
                    break;
                }
                case 9:{
                    System.out.println("------View Vouchers------\n");

                    admin.viewVouchers();
                    break;
                }
                case 10: {
                    System.out.println("------View Statistics------\n");

                    System.out.println("1.Customer Statistics");
                    System.out.println("2.Stock Statistics");
                    int n = Integer.parseInt(scan.nextLine());
                    switch (n) {
                        case 1:
                            //waiting for customerDB class
                            break;
                        case 2:
                            admin.viewStockStatistics();
                            break;
                    }
                    break;
                }
                case 11: {
                    System.out.println("------Set Loyalty Point Scheme------\n");

                    System.out.print("Enter The Amount Gained From The Scheme: ");
                    int amount = Integer.parseInt(scan.nextLine());

                    admin.setLoyaltyPointSchemeAmount(amount);
                    break;
                }
                case 12: {
                    return;
                }
                case 13: {
                    System.exit(0);
                }
                case 69:{
                    System.out.println("⠀⣠⣴⣶⣿⣿⣷⣶⣄⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣾⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⡟⠁⣰⣿⣿⣿⡿⠿⠻⠿⣿⣿⣿⣿⣧⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⠏⠀⣴⣿⣿⣿⠉⠀⠀⠀⠀⠀⠈⢻⣿⣿⣇⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⢀⣠⣼⣿⣿⡏⠀⢠⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⡀⠀⠀\n" +
                            "⠀⠀⠀⣰⣿⣿⣿⣿⣿⡇⠀⢸⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⡇⠀⠀\n" +
                            "⠀⠀⢰⣿⣿⡿⣿⣿⣿⡇⠀⠘⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⢀⣸⣿⣿⣿⠁⠀⠀\n" +
                            "⠀⠀⣿⣿⣿⠁⣿⣿⣿⡇⠀⠀⠻⣿⣿⣿⣷⣶⣶⣶⣶⣶⣿⣿⣿⣿⠃⠀⠀⠀\n" +
                            "⠀⢰⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⠁⠀⠀⠀⠀\n" +
                            "⠀⢸⣿⣿⡇⠀⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠉⠛⠛⠛⠉⢉⣿⣿⠀⠀⠀⠀⠀⠀\n" +
                            "⠀⢸⣿⣿⣇⠀⣿⣿⣿⠀⠀⠀⠀⠀⢀⣤⣤⣤⡀⠀⠀⢸⣿⣿⣿⣷⣦⠀⠀⠀\n" +
                            "⠀⠀⢻⣿⣿⣶⣿⣿⣿⠀⠀⠀⠀⠀⠈⠻⣿⣿⣿⣦⡀⠀⠉⠉⠻⣿⣿⡇⠀⠀\n" +
                            "⠀⠀⠀⠛⠿⣿⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠈⠹⣿⣿⣇⣀⠀⣠⣾⣿⣿⡇⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠹⣿⣿⣿⣿⣦⣤⣤⣤⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣿⣿⣿⣿⣿⣿⠿⠋⠉⠛⠋⠉⠉⠁⠀⠀⠀⠀\n" +
                            "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠁");
                }
                default:{
                    System.out.println("Error: Invalid Input Please Try Again");
                    break;
                }
            }
        }
    }


    public void customerInterface(){

    }

    public static void main(String[] args) {
        Toffee toffee = new Toffee();
//        System.out.println("leeeeeeeeeeeeeeeeh");
    }
}
