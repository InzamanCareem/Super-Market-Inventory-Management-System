package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.File;
import java.util.ArrayList;


public class ItemEditorController {
    @FXML
    private TextField searchBox;
    @FXML
    private TextField itemCode;
    @FXML
    private TextField itemName;
    @FXML
    private TextField itemBrand;
    @FXML
    private TextField itemPrice;
    @FXML
    private TextField itemQuantity;
    @FXML
    private TextField minimumStockLevel;
    @FXML
    private TextField itemCategory;
    @FXML
    private DatePicker purchasedDate;
    @FXML
    private TextField itemImage;
    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;

    protected boolean updatable = false;  // means initially adding Item

    private MarketController marketController;

    public void setMarketController(MarketController marketController) {
        this.marketController = marketController;
    }

    public MarketController getMarketController(){
        return marketController;
    }

    private void clearFields() {
        itemCode.setText("");
        itemName.setText("");
        itemBrand.setText("");
        itemPrice.setText("");
        itemQuantity.setText("");
        minimumStockLevel.setText("");
        itemCategory.setText("");
        purchasedDate.getEditor().setText("");
        itemImage.setText("");
        searchBox.setText("");
    }

    public void searchItem() {
        if (!searchBox.getText().isEmpty()) {
            for (int i = 0; i < marketController.getAllItems().size(); i++) {
                if (marketController.getAllItems().get(i).getCode() == Integer.parseInt(searchBox.getText())) {
                    itemCode.setText(Integer.toString(marketController.getAllItems().get(i).getCode()));
                    itemName.setText(marketController.getAllItems().get(i).getName());
                    itemBrand.setText(marketController.getAllItems().get(i).getBrand());
                    itemPrice.setText(Double.toString(marketController.getAllItems().get(i).getPrice()));
                    itemQuantity.setText(Integer.toString(marketController.getAllItems().get(i).getQuantity()));
                    minimumStockLevel.setText(Integer.toString(marketController.getAllItems().get(i).getMinimumStockLevel()));
                    itemCategory.setText(marketController.getAllItems().get(i).getCategory());
                    purchasedDate.getEditor().setText(marketController.getAllItems().get(i).getPurchasedDate());
                    itemImage.setText(marketController.getAllItems().get(i).getImgSrc().split("/")[1]);
                    updatable = true;
                    itemCode.setEditable(false);
                    addButton.setDisable(true);
                    updateButton.setDisable(false);
                    break;
                }
            }
        } else {
            clearFields();
            addButton.setDisable(false);
            updateButton.setDisable(true);
        }
    }

    public void editItemButton() {
        String updatedItemCode = itemCode.getText().trim();
        String updatedItemName = itemName.getText().trim();
        String updatedItemBrand = itemBrand.getText().trim();
        String updatedItemPrice = itemPrice.getText().trim();
        String updatedItemQuantity = itemQuantity.getText().trim();
        String updatedMinimumStockLevel = minimumStockLevel.getText().trim();
        String updatedItemCategory = itemCategory.getText().trim();
        String updatedPurchasedDate = purchasedDate.getEditor().getText();
        String updatedItemImage = itemImage.getText().trim();

        boolean makeChanges = true;

        // Validation of Fields

        if (updatedItemCode.isEmpty() || updatedItemName.isEmpty() || updatedItemBrand.isEmpty() ||
                updatedItemPrice.isEmpty() || updatedItemQuantity.isEmpty() || updatedMinimumStockLevel.isEmpty() ||
                updatedItemCategory.isEmpty() || updatedPurchasedDate.isEmpty() || updatedItemImage.isEmpty()) {
            AlertManager.showError("Error", "Insufficient Details", "Please enter all fields!");
            makeChanges = false;

        } else {
            try {
                int itemCodeInt = Integer.parseInt(updatedItemCode);

                if (itemCodeInt < 0) {
                    AlertManager.showError("Error", "Unexpected Format", "Item code cannot be negative!");
                    makeChanges = false;
                }

                if (!updatable){
                    // Get the codes
                    ArrayList<String> itemCodes = new ArrayList<>();
                    for (int i = 0; i < marketController.getAllItems().size(); i++) {
                        itemCodes.add(Integer.toString(marketController.getAllItems().get(i).getCode()));
                    }

                    if (itemCodes.contains(updatedItemCode)) {
                        AlertManager.showError("Error", "Unexpected Format", "Item code '" + updatedItemCode + "' already exists!");
                        makeChanges = false;
                    }
                }

            } catch (NumberFormatException e) {
                AlertManager.showError("Error", "Unexpected Format", "Item code should be a whole number.");
                makeChanges = false;
            }

            try {
                int itemQuantityInt = Integer.parseInt(updatedItemQuantity);

                if (itemQuantityInt < 0) {
                    AlertManager.showError("Error", "Unexpected Format", "Quantity cannot be negative!");
                    makeChanges = false;
                }
            } catch (NumberFormatException e) {
                AlertManager.showError("Error", "Unexpected Format", "Quantity should be a whole number.");
                makeChanges = false;
            }

            try {
                int minimumStockLevelInt = Integer.parseInt(updatedMinimumStockLevel);

                if (minimumStockLevelInt < 0) {
                    AlertManager.showError("Error", "Unexpected Format", "Minimum Stock Level cannot be negative!");
                    makeChanges = false;
                }
            } catch (NumberFormatException e) {
                AlertManager.showError("Error", "Unexpected Format", "Minimum Stock Level should be a whole number.");
                makeChanges = false;
            }

            try {
                double itemPriceDouble = Double.parseDouble(updatedItemPrice);

                if (itemPriceDouble < 0) {
                    AlertManager.showError("Error", "Unexpected Format", "Price cannot be negative!");
                    makeChanges = false;
                }
            } catch (NumberFormatException e) {
                AlertManager.showError("Error", "Unexpected Format", "Price should be a decimal value!");
                makeChanges = false;
            }

            if (!updatedItemImage.endsWith(".jpg")) {
                AlertManager.showError("Error", "Unexpected Format", "Image should end with .jpg!");
                makeChanges = false;
            } else {
                File imageFile = new File("src/main/resources/com/example/supermarket/images/" + updatedItemImage);

                if (!imageFile.exists()) {
                    AlertManager.showError("Error", "Unexpected Format", updatedItemImage + " does not exist!");
                    makeChanges = false;
                }
            }
        }

        // Make changes to allItems list in marketController

        if (makeChanges) {

            Items myItems = new Items();

            myItems.setCode(Integer.parseInt(updatedItemCode));
            myItems.setName(updatedItemName);
            myItems.setBrand(updatedItemBrand);
            myItems.setPrice(Double.parseDouble(updatedItemPrice));
            myItems.setQuantity(Integer.parseInt(updatedItemQuantity));
            myItems.setMinimumStockLevel(Integer.parseInt(updatedMinimumStockLevel));
            myItems.setCategory(updatedItemCategory);
            myItems.setPurchasedDate(updatedPurchasedDate);
            myItems.setImgSrc("images/" + updatedItemImage);

            if (!updatable){
                marketController.addItem(marketController.getAllItems().size(), myItems);
                AlertManager.showInformation("Item Inventory", "New Item Added to Inventory", "Success: Item '" + updatedItemName + "' with item code " + updatedItemCode + " has been added to the inventory with " + updatedItemQuantity + " units.");
            }
            else{
                updatable = false;
                AlertManager.showInformation("Item Inventory", "Inventory Item Updated", "Success: Item '" + itemName.getText() + "' with item code " + itemCode.getText() + " has been updated in the inventory with " + itemQuantity.getText() + " units.");
            }

            clearFields();

            itemCode.setEditable(true);
            addButton.setDisable(false);
            updateButton.setDisable(true);
        }
    }
}
