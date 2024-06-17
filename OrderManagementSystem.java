import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderManagementSystem {
    private ArrayList<Order> orderList;

    public OrderManagementSystem() {
        this.orderList = new ArrayList<>();
        readOrdersFromFile("orders.txt");
    }

    // Method to read orders from file
    private void readOrdersFromFile(String fileName) {
        String filePath = "C:\\Users\\aqeel\\Downloads\\" + fileName; // Update with your file path

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                int orderId = Integer.parseInt(data[0].trim());
                String customerName = data[1].trim();
                String customerAddress = data[2].trim();
                String productName = data[3].trim();
                int quantityOrdered = Integer.parseInt(data[4].trim());
                double pricePerUnit = Double.parseDouble(data[5].trim());
                String orderStatus = data[6].trim();

                Order order = new Order(orderId, customerName, customerAddress, productName,
                        quantityOrdered, pricePerUnit, orderStatus);
                orderList.add(order);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }
    }

    // Method to add a new order
    public void addOrder(Order newOrder) {
        // Check for duplicate order IDs
        for (Order order : orderList) {
            if (order.getOrderId() == newOrder.getOrderId()) {
                System.out.println("Order ID " + newOrder.getOrderId() + " already exists. Cannot add order.");
                return;
            }
        }
        orderList.add(newOrder);
        System.out.println("Order added successfully.");
    }

    // Method to update an existing order
    public void updateOrder(int orderId, Order updatedOrder) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getOrderId() == orderId) {
                orderList.set(i, updatedOrder);
                System.out.println("Order updated successfully.");
                return;
            }
        }
        System.out.println("Order ID " + orderId + " not found.");
    }

    // Method to delete an order
    public void deleteOrder(int orderId) {
        orderList.removeIf(order -> order.getOrderId() == orderId);
        System.out.println("Order ID " + orderId + " deleted successfully.");
    }

    // Method to display all orders
    public void displayAllOrders() {
        if (orderList.isEmpty()) {
            System.out.println("No orders to display.");
        } else {
            System.out.println("All Orders:");
            for (Order order : orderList) {
                System.out.println(order);
            }
        }
    }

    // Method to display orders by customer name
    public void displayOrdersByCustomer(String customerName) {
        boolean found = false;
        for (Order order : orderList) {
            if (order.getCustomerName().equalsIgnoreCase(customerName)) {
                System.out.println(order);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No orders found for customer: " + customerName);
        }
    }

    // Method to display orders by product name
    public void displayOrdersByProduct(String productName) {
        boolean found = false;
        for (Order order : orderList) {
            if (order.getProductName().equalsIgnoreCase(productName)) {
                System.out.println(order);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No orders found for product: " + productName);
        }
    }

    // Method to display orders by status
    public void displayOrdersByStatus(String orderStatus) {
        boolean found = false;
        for (Order order : orderList) {
            if (order.getOrderStatus().equalsIgnoreCase(orderStatus)) {
                System.out.println(order);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No orders found with status: " + orderStatus);
        }
    }

    // Main method to run the program
    public static void main(String[] args) {
        OrderManagementSystem system = new OrderManagementSystem();

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n----- MENU -----");
            System.out.println("1. Add a new order");
            System.out.println("2. Update an order");
            System.out.println("3. Delete an order");
            System.out.println("4. Display orders");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a new order
                    system.addOrder(createOrderFromUserInput(scanner));
                    break;
                case 2:
                    // Update an order
                    System.out.print("Enter order ID to update: ");
                    int updateOrderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    system.updateOrder(updateOrderId, createOrderFromUserInput(scanner));
                    break;
                case 3:
                    // Delete an order
                    System.out.print("Enter order ID to delete: ");
                    int deleteOrderId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    system.deleteOrder(deleteOrderId);
                    break;
                case 4:
                    // Display orders
                    system.displayOrdersMenu(scanner);
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting program... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        } while (choice != 5);
    }

    // Helper method to create an Order object from user input
    private static Order createOrderFromUserInput(Scanner scanner) {
        System.out.print("Enter order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter customer address: ");
        String customerAddress = scanner.nextLine();

        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter quantity ordered: ");
        int quantityOrdered = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter price per unit: ");
        double pricePerUnit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter order status: ");
        String orderStatus = scanner.nextLine();

        return new Order(orderId, customerName, customerAddress, productName,
                quantityOrdered, pricePerUnit, orderStatus);
    }

    // Method to display various options for displaying orders
    private void displayOrdersMenu(Scanner scanner) {
        int displayChoice;
        do {
            System.out.println("\n----- Display Orders Menu -----");
            System.out.println("1. Display all orders");
            System.out.println("2. Display orders by customer");
            System.out.println("3. Display orders by product");
            System.out.println("4. Display orders by status");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            displayChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (displayChoice) {
                case 1:
                    // Display all orders
                    displayAllOrders();
                    break;
                case 2:
                    // Display orders by customer
                    System.out.print("Enter customer name: ");
                    String customerName = scanner.nextLine();
                    displayOrdersByCustomer(customerName);
                    break;
                case 3:
                    // Display orders by product
                    System.out.print("Enter product name: ");
                    String productName = scanner.nextLine();
                    displayOrdersByProduct(productName);
                    break;
                case 4:
                    // Display orders by status
                    System.out.print("Enter order status: ");
                    String orderStatus = scanner.nextLine();
                    displayOrdersByStatus(orderStatus);
                    break;
                case 5:
                    // Back to main menu
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }

        } while (displayChoice != 5);
    }
}
