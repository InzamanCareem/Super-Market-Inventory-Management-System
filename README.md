# 🛒 Super Market Inventory Management System

Welcome to the **Super Market Inventory Management System**! This JavaFX-based application is designed to help John’s Super Market efficiently manage its inventory, suppliers (dealers), and related functionalities. With an intuitive GUI and robust backend, the system ensures smooth operations for managing inventory items and dealer details.

---

## 📜 Overview

The application provides an easy-to-use graphical interface to manage inventory at John’s Super Market. It supports a range of functionalities, from adding, updating, and deleting inventory items, to viewing dealer details, all through a clean and intuitive **JavaFX** GUI.

This project is built with **Object-Oriented Programming (OOP)** principles, focusing on **modularity**, **scalability**, and **maintainability**.

---

## 🚀 Features

### **📦 Inventory Management**
- **Low Stock Alerts**: On startup, the system displays all inventory items where the quantity is below a user-configurable threshold.
- **Add New Item**: Users can add new inventory items with full details such as Item Code, Name, Brand, Price, Quantity, Category, Purchase Date, and an Item Image.
- **Delete Item**: Easily remove an item from the inventory via a search or selection interface.
- **Update Item Details**: Update existing inventory items with new details, including price, quantity, and more.
- **View All Items**: Display all inventory items sorted by category, showing the total item count and the overall inventory value.

### **🔄 Data Persistence**
- **Save & Load Data**: Save inventory and dealer details to a text file, and load them back when the application restarts. This ensures data persistence between sessions.

### **🤝 Supplier (Dealer) Management**
- **Random Dealer Selection**: Simulate a random selection of four dealers from a file and display their details.
- **View Dealer Items**: View all inventory items provided by a selected dealer, making it easy to track supplier-related stock.

### **🖥 Clean Exit**
- **Graceful Shutdown**: The system ensures that all changes (add, delete, update) are saved before closing.

---

## ⚙️ System Requirements

- **Java**: Version 8 or higher
- **JavaFX**: Required for the graphical user interface (GUI)
- **Operating System**: Cross-platform (Windows, macOS, Linux)

---

## 💻 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/supermarket-inventory-management.git
cd supermarket-inventory-management

