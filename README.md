#  Coffee Shop Management System

A Java-based console application that streamlines the day-to-day operations of a small coffee shop — placing orders, prioritizing urgent requests, tracking inventory, and managing order statuses. Built as a Data Structures course project (CCCS 224) to demonstrate practical use of linked lists, queues, and hash maps.

##  Overview

This system simulates a real coffee shop's workflow through a simple console menu. Orders flow through priority and regular queues, inventory updates automatically as drinks are sold, and everything is saved to disk so progress isn't lost between sessions.

##  Features

- **Order Placement** — Add regular or priority orders with Order ID, Customer ID, name, and selected beverage
- **Order Queue Management** — Two-tier queue system (priority + regular), fulfill next order, cancel by Order ID
- **Inventory Tracking** — 12 beverages (Espresso, Latte, Cappuccino, Americano, Mocha, Flat White, Macchiato, Cortado, Cold Brew, Frappe, Tea, Hot Chocolate), each starting at 10 units, with automatic stock deduction and out-of-stock detection
- **Status Tracking** — Every order is tagged as Pending, Completed, or Cancelled
- **Multiple Views** — Pending (priority + regular), completed, cancelled, and current stock
- **Data Persistence** — Orders and stock saved to `orders.txt` and `stock.txt`, automatically reloaded on startup
- **Console Menu** — Numbered options for placing/cancelling orders, fulfilling next, checking stock, and viewing history

##  Data Structures Used

| Structure | Where | Why |
|-----------|-------|-----|
| **Linked List** | `CoffeeOrder` (with `next` pointer) | Underlying node for all order lists |
| **Queue (linked-list backed)** | Priority, regular, completed, cancelled queues | Order processing with priority handling |
| **HashMap** | `CoffeeInventory` | O(1) lookup and update of beverage stock |
| **File I/O** | `stock.txt`, `orders.txt` | Persistent storage between sessions |

##  Project Structure

```
CoffeeShopOrderSystem/
├── CoffeeShopApp.java       # Main entry point + menu loop
├── CoffeeOrder.java         # Order node (linked-list element)
├── CoffeeOrderQueue.java    # Queue logic: add, fulfill, cancel, persist
└── CoffeeInventory.java     # Stock management with HashMap
```

##  Getting Started

### Prerequisites
- Java JDK 8 or later
- Any IDE (NetBeans, IntelliJ, Eclipse) or just the command line

### Run from the command line
```bash
cd CoffeeShopOrderSystem
javac *.java
java CoffeeShopApp
```

### Run in NetBeans
1. Open the project folder in NetBeans
2. Right-click `CoffeeShopApp.java` → **Run File**

##  Menu Options

```
--- Coffee Shop Menu ---
1. Place Order
2. Fulfill Next Order
3. Cancel Order
4. View Completed Orders
5. View Cancelled Orders
6. View Stock
7. View Pending Orders
0. Exit
```

##  Example Usage

**Placing a priority order:**
```
Choose an option: 1
Enter Order ID: order1
Enter Customer ID: jana1
Enter Customer Name: Jana Akkad
Enter Coffee Type: Tea
Is this a priority order? (yes/no): yes
Order added successfully.
```

**Fulfilling it:**
```
Choose an option: 2
Order fulfilled successfully. Status: Completed
```

Priority orders are always served before regular ones, regardless of placement time.

##  Authors

Course: **CCCS 224 — Data Structures** · Section E3

- Enas Hamed AlQarni (2314104)
- Jana Akkad (2313200)
- Abrar Alharbi (2317129)

##  License

This project was developed for academic purposes.
