import java.io.*;

public class CoffeeOrderQueue {
    CoffeeOrder priorityOrders = null;
    CoffeeOrder regularOrders = null;
    CoffeeOrder completedOrders = null;
    CoffeeOrder cancelledOrders = null;

    public void addOrder(CoffeeOrder order, boolean isPriority) {
        if (isPriority) {
            order.next = priorityOrders;
            priorityOrders = order;
        } else {
            order.next = regularOrders;
            regularOrders = order;
        }
    }

    public void fulfillOrder() {
        CoffeeOrder current;
        if (priorityOrders != null) {
            current = priorityOrders;
            priorityOrders = priorityOrders.next;
        } else if (regularOrders != null) {
            current = regularOrders;
            regularOrders = regularOrders.next;
        } else {
            System.out.println("No orders to fulfill.");
            return;
        }
        current.status = "Completed";
        current.next = completedOrders;
        completedOrders = current;
        System.out.println("Order fulfilled successfully. Status: Completed");
    }

    public void cancelOrder(String orderId) {
        CoffeeOrder order = findAndRemove(orderId, true);
        if (order == null) {
            order = findAndRemove(orderId, false);
        }
        if (order == null) {
            System.out.println("Order ID not found.");
        }
    }

    private CoffeeOrder findAndRemove(String orderId, boolean fromPriority) {
        CoffeeOrder head = fromPriority ? priorityOrders : regularOrders;
        CoffeeOrder prev = null;
        CoffeeOrder current = head;
        while (current != null) {
            if (current.orderId.equals(orderId)) {
                if (prev == null) {
                    if (fromPriority) priorityOrders = current.next;
                    else regularOrders = current.next;
                } else {
                    prev.next = current.next;
                }
                current.status = "Cancelled";
                current.next = cancelledOrders;
                cancelledOrders = current;
                System.out.println("Order cancelled successfully. Status: Cancelled");
                return current;
            }
            prev = current;
        current = current.next;
        }
        return null;
    }

    public void viewCompletedOrders() {
        System.out.println("Completed Orders:");
        CoffeeOrder current = completedOrders;
        while (current != null) {
            System.out.println("Order ID: " + current.orderId
                + " | Customer ID: " + current.customerId
                + " | Name: " + current.customerName
                + " | Status: " + current.status);
            current = current.next;
        }
    }

    public void viewCancelledOrders() {
        System.out.println("Cancelled Orders:");
        CoffeeOrder current = cancelledOrders;
        while (current != null) {
            System.out.println("Order ID: " + current.orderId
                + " | Customer ID: " + current.customerId
                + " | Name: " + current.customerName
                + " | Status: " + current.status);
            current = current.next;
        }
    }

    public void viewPendingOrders() {
        System.out.println("Pending Priority Orders:");
        CoffeeOrder current = priorityOrders;
        while (current != null) {
            System.out.println("Order ID: " + current.orderId
                + " | Customer ID: " + current.customerId
                + " | Name: " + current.customerName
                + " | Details: " + current.orderDetails
                + " | Status: " + current.status);
            current = current.next;
        }
        System.out.println("Pending Regular Orders:");
        current = regularOrders;
        while (current != null) {
            System.out.println("Order ID: " + current.orderId
                + " | Customer ID: " + current.customerId
                + " | Name: " + current.customerName
                + " | Details: " + current.orderDetails
                + " | Status: " + current.status);
            current = current.next;
        }
    }

    public void saveOrdersToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writeList(writer, priorityOrders, "PRIORITY");
            writeList(writer, regularOrders, "REGULAR");
            writeList(writer, completedOrders, "COMPLETED");
            writeList(writer, cancelledOrders, "CANCELLED");
        } catch (IOException e) {
            System.out.println("Error saving orders: " + e.getMessage());
        }
    }

    private void writeList(PrintWriter writer, CoffeeOrder current, String type) {
        while (current != null) {
            writer.println(current.orderId + "," + current.customerId + ","
                + current.customerName + "," + current.orderDetails + ","
                + current.status + "," + type);
            current = current.next;
        }
    }

    public void loadOrdersFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    CoffeeOrder order = new CoffeeOrder(parts[0], parts[1], parts[2], parts[3]);
                    order.status = parts[4];
                    String type = parts[5];
                    switch (type) {
                        case "PRIORITY":
                            order.next = priorityOrders;
                            priorityOrders = order;
                            break;
                        case "REGULAR":
                            order.next = regularOrders;
                            regularOrders = order;
                            break;
                        case "COMPLETED":
                            order.next = completedOrders;
                            completedOrders = order;
                            break;
                        case "CANCELLED":
                            order.next = cancelledOrders;
                            cancelledOrders = order;
                            break;
                    }
                }
            }
        } catch (IOException e) {
            // No previous file
        }
    }
}
