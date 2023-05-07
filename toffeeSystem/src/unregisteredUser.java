public class unregisteredUser {
    private Stock st;
    unregisteredUser(){
        st = new Stock();
    }
    public  void viewProducts(){
        st.DisplayProducts();
    }
    public  void searchItem(String productName){
        st.printProduct(productName);
    }

    public static void main(String[] args) {
        unregisteredUser uu = new unregisteredUser();
//        uu.viewProducts();
        uu.searchItem("Niggaz");
    }
}
