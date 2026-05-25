import java.util.Scanner;
import java.io.*;

public class CoffeeShopApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeInventory inventory = new CoffeeInventory();
        inventory.loadStockFromFile("stock.txt");
        CoffeeOrderQueue queue = new CoffeeOrderQueue();
        queue.loadOrdersFromFile("orders.txt");

        while (true) {
            System.out.println("\n--- Coffee Shop Menu ---");
            System.out.println("1. Place Order");
            System.out.println("2. Fulfill Next Order");
            System.out.println("3. Cancel Order");
            System.out.println("4. View Completed Orders");
            System.out.println("5. View Cancelled Orders");
            System.out.println("6. View Stock");
            System.out.println("7. View Pending Orders");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Order ID: ");
                    String orderId = scanner.nextLine();
                    System.out.print("Enter Customer ID: ");
                    String customerId = scanner.nextLine();
                    System.out.print("Enter Customer Name: ");
                    String customerName = scanner.nextLine();
                    inventory.viewStock();
                    System.out.print("Enter Coffee Type (choose from above): ");
                    String orderDetails = scanner.nextLine();
                    System.out.print("Is this a priority order? (yes/no): ");
                    String priority = scanner.nextLine();

                    if (inventory.isAvailable(orderDetails)) {
                        inventory.deduct(orderDetails);
                        inventory.saveStockToFile("stock.txt");
                        CoffeeOrder order = new CoffeeOrder(orderId, customerId, customerName, orderDetails);
                        queue.addOrder(order, priority.equalsIgnoreCase("yes"));
                        System.out.println("Order added successfully.");
                    } else {
                        System.out.println("Item is out of stock.");
                    }
                    break;
                case 2:
                    queue.fulfillOrder();
                    break;
                case 3:
                    System.out.print("Enter Order ID to cancel: ");
                    String cancelId = scanner.nextLine();
                    queue.cancelOrder(cancelId);
                    break;
                case 4:
                    queue.viewCompletedOrders();
                    break;
                case 5:
                    queue.viewCancelledOrders();
                    break;
                case 6:
                    inventory.viewStock();
                    break;
                case 7:
                    queue.viewPendingOrders();
                    break;
                case 0:
                    queue.saveOrdersToFile("orders.txt");
                    inventory.saveStockToFile("stock.txt");
                    System.out.println("Exiting system...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
