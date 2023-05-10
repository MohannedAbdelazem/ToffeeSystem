class order{
    private int orderID, CustomerID;
    String CartID;
    private String status;
    order(int orderID,int customerID, String status){
        this.CartID = customerID+"_"+orderID;
        this.orderID = orderID;
        this.CustomerID = customerID;
        this.status = status;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getCartID() {
        return CartID;
    }

    public void setCartID(String cartID) {
        CartID = cartID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
};