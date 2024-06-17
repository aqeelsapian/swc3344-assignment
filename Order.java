public class Order {
    private int orderId;
    private String customerName;
    private String customerAddress;
    private String productName;
    private int quantityOrdered;
    private double pricePerUnit;
    private double totalPrice;
    private String orderStatus;

    // Constructor
    public Order(int orderId, String customerName, String customerAddress, String productName,
                 int quantityOrdered, double pricePerUnit, String orderStatus) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.productName = productName;
        this.quantityOrdered = quantityOrdered;
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = quantityOrdered * pricePerUnit; // Calculate total price
        this.orderStatus = orderStatus;
    }

    // Getters and setters
    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantityOrdered() {
        return quantityOrdered;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setQuantityOrdered(int quantityOrdered) {
        this.quantityOrdered = quantityOrdered;
        this.totalPrice = quantityOrdered * pricePerUnit; // Update total price
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
        this.totalPrice = quantityOrdered * pricePerUnit; // Update total price
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId +
                ", Customer: " + customerName +
                ", Address: " + customerAddress +
                ", Product: " + productName +
                ", Quantity: " + quantityOrdered +
                ", Price per Unit: $" + pricePerUnit +
                ", Total Price: $" + totalPrice +
                ", Status: " + orderStatus;
    }
}
