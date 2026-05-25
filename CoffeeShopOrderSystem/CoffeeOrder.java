public class CoffeeOrder {
    String orderId;
    String customerId;
    String customerName;
    String orderDetails;
    String status;
    CoffeeOrder next;

    public CoffeeOrder(String orderId, String customerId, String customerName, String orderDetails) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerName = customerName;
        this.orderDetails = orderDetails;
        this.status = "Pending";
        this.next = null;
    }
}
