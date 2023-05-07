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

}
