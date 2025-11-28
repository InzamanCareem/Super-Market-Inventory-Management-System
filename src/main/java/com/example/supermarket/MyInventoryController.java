package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyInventoryController {
    @FXML
    private GridPane grid;
    private final List<Items> inventoryMyItems = new ArrayList<>();
    private MarketController marketController;

    public void setMarketController(MarketController marketController) {
        this.marketController = marketController;
        loadItems();
        showData();
    }

    public MarketController getMarketController(){
        return marketController;
    }

    private void loadItems() {
        // Sort Items: below certain stock level and add them to inventoryList
        inventoryMyItems.addAll(sort(new ArrayList<>(marketController.getAllItems())));
    }

    public List<Items> sort(List<Items> myItemsDetails){
        List<Items> sortedMyItems = new ArrayList<>();

        // Uses Bubble sort algorithm to achieve sorting by quantity
        for (int i = 0; i < myItemsDetails.size() - 1; i++) {
            for (int j = 0; j < myItemsDetails.size() - i - 1; j++) {
                if (myItemsDetails.get(j).getQuantity() > myItemsDetails.get(j + 1).getQuantity()){
                    Items temp = myItemsDetails.get(j);
                    myItemsDetails.set(j, myItemsDetails.get(j + 1));
                    myItemsDetails.set(j + 1, temp);
                }
            }
        }

        // Adds items which has a quantity below minimum quantity to the sortedMyItems list
        for (Items myItems : myItemsDetails) {
            if (myItems.getQuantity() < myItems.getMinimumStockLevel()) {
                sortedMyItems.add(myItems);
            }
        }
        return sortedMyItems;
    }

    public void showData(){
        int column = 0;
        int row = 1;

        try {
            for (Items inventoryMyItem : inventoryMyItems) {
                // Loads the 'item.fxml' and places it on the GridPane
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(inventoryMyItem);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
