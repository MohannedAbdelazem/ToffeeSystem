class order{
    private int orderID, CustomerID, CartID;
    private String status;
    order(int orderID, int customerID, int cartID, String status){
        this.CartID = cartID;
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

    public int getCartID() {
        return CartID;
    }

    public void setCartID(int cartID) {
        CartID = cartID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
};