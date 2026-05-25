import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CoffeeInventory {
    private Map<String, Integer> stock;

    public CoffeeInventory() {
        stock = new HashMap<>();
        stock.put("Espresso", 10);
        stock.put("Latte", 10);
        stock.put("Cappuccino", 10);
        stock.put("Americano", 10);
        stock.put("Mocha", 10);
        stock.put("Flat White", 10);
        stock.put("Macchiato", 10);
        stock.put("Cortado", 10);
        stock.put("Cold Brew", 10);
        stock.put("Frappe", 10);
        stock.put("Tea", 10);
        stock.put("Hot Chocolate", 10);
    }

    public boolean isAvailable(String item) {
        return stock.getOrDefault(item, 0) > 0;
    }

    public void deduct(String item) {
        stock.put(item, stock.get(item) - 1);
    }

    public void viewStock() {
        System.out.println("Current Stock:");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println("- " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public void saveStockToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Integer> entry : stock.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error saving stock: " + e.getMessage());
        }
    }

    public void loadStockFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    stock.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            // No previous stock file or error reading
        }
    }
}
