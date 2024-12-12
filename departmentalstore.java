import java.util.*;

class SellingPriceException extends Exception {
    public SellingPriceException(String message) {
        super(message);
    }
}

class Item {
    private int itemNumber;
    private String description;
    private int quantity;
    private double costPrice;
    
    public Item(int itemNumber, String description, int quantity, double costPrice) {
        this.itemNumber = itemNumber;
        this.description = description;
        this.quantity = quantity;
        this.costPrice = costPrice;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public String getDescription() {
        return description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double calculateSellingPrice() {
        return costPrice * 1.2;
    }

    @Override
    public String toString() {
        return "Item Number: " + itemNumber + ", Description: " + description +
               ", Quantity: " + quantity + ", Cost Price: " + costPrice;
    }
}

public class DepartmentalStore {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Item> items = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            System.out.println("\n--- Departmental Store Menu ---");
            System.out.println("1. Add Item");
            System.out.println("2. Update Stock");
            System.out.println("3. Display Items");
            System.out.println("4. Calculate Selling Price");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1 -> addItem();
                case 2 -> updateStock();
                case 3 -> displayItems();
                case 4 -> calculateSellingPrice();
                case 5 -> exit = true;
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private static void addItem() {
        System.out.print("Enter Item Number: ");
        int itemNumber = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Item Description: ");
        String description = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Cost Price: ");
        double costPrice = scanner.nextDouble();

        items.add(new Item(itemNumber, description, quantity, costPrice));
        System.out.println("Item added successfully.");
    }

    private static void updateStock() {
        System.out.print("Enter Item Number to Update: ");
        int itemNumber = scanner.nextInt();
        
        for (Item item : items) {
            if (item.getItemNumber() == itemNumber) {
                System.out.print("Enter New Quantity: ");
                int newQuantity = scanner.nextInt();
                item.setQuantity(newQuantity);

                System.out.print("Enter New Cost Price: ");
                double newCostPrice = scanner.nextDouble();
                item.setCostPrice(newCostPrice);

                System.out.println("Stock updated successfully.");
                return;
            }
        }
        System.out.println("Item not found.");
    }

    private static void displayItems() {
        if (items.isEmpty()) {
            System.out.println("No items available.");
            return;
        }

        for (Item item : items) {
            System.out.println(item);
        }
    }

    private static void calculateSellingPrice() {
        System.out.print("Enter Item Number: ");
        int itemNumber = scanner.nextInt();
        
        System.out.print("Enter Maximum Allowed Selling Price: ");
        double maxPrice = scanner.nextDouble();

        for (Item item : items) {
            if (item.getItemNumber() == itemNumber) {
                double sellingPrice = item.calculateSellingPrice();
                try {
                    if (sellingPrice > maxPrice) {
                        throw new SellingPriceException("Selling price exceeds the allowed limit of " + maxPrice);
                    }
                    System.out.println("Selling Price: " + sellingPrice);
                } catch (SellingPriceException e) {
                    System.out.println(e.getMessage());
                }
                return;
            }
        }
        System.out.println("Item not found.");
    }
}
