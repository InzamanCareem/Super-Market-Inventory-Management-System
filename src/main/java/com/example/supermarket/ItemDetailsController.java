package com.example.supermarket;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;

import java.io.IOException;

public class ItemDetailsController {
    @FXML
    private TextField searchBox;
    @FXML
    private GridPane grid;
    @FXML
    private Label totalItemCount;
    @FXML
    private Label totalMonetaryValue;

    private int selectedRow = -1;

    private int deletedItemIndex = 0;

    private MarketController marketController;

    public void setMarketController(MarketController marketController) {
        this.marketController = marketController;

        sortData();

        getRelevantData(searchBox.getText());

        totalItemCount.setText("Total Item Count: " + getTotalItemCount());
        totalMonetaryValue.setText("Total Monetary Value: Rs. " + String.format("%.2f", getTotalMonetaryValue()));
    }

    public void sortData() {
        // Uses Bubble Sort algorithm to achieve sorting by category
        for (int i = 0; i < marketController.getAllItems().size(); i++) {
            for (int j = 0; j < marketController.getAllItems().size() - i - 1; j++) {
                if (marketController.getAllItems().get(j).getCategory().compareToIgnoreCase(marketController.getAllItems().get(j + 1).getCategory()) > 0) {
                    Items temp = marketController.getAllItems().get(j);
                    marketController.removeItem(temp);
                    marketController.addItem(j + 1, temp);
                }
            }
        }

        // Uses Bubble Sort algorithm to achieve sorting by id for each category
        for (int i = 0; i < marketController.getAllItems().size(); i++) {
            for (int j = 0; j < marketController.getAllItems().size() - i - 1; j++) {
                if (marketController.getAllItems().get(j).getCategory().equals(marketController.getAllItems().get(j + 1).getCategory())) {
                    if (marketController.getAllItems().get(j).getCode() > marketController.getAllItems().get(j + 1).getCode()) {
                        Items temp = marketController.getAllItems().get(j);
                        marketController.removeItem(temp);
                        marketController.addItem(j + 1, temp);
                    }
                }
            }
        }
    }

    private void getRelevantData(String searchedItem) {
        int column = 0;
        int row = 0;

        try {
            // Loads the 'item2-columns.fxml' and places it on the GridPane
            FXMLLoader fxmlLoader2 = new FXMLLoader();

            fxmlLoader2.setLocation(getClass().getResource("item2-columns.fxml"));
            AnchorPane anchorPane1 = fxmlLoader2.load();

            grid.getChildren().clear();

            grid.add(anchorPane1, 0, row++);

            for (int i = 0; i < marketController.getAllItems().size(); i++) {
                // Loads the 'item2.fxml' and places it on the GridPane
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item2.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController2 itemController2 = fxmlLoader.getController();
                itemController2.setData(marketController.getAllItems().get(i));

                // Sets a mouse event handler on each item2.fxml file's AnchorPane
                int finalRow = row;
                int finalI = i;
                anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        selectedRow = finalRow;
                        selectItem(selectedRow);
                        deletedItemIndex = finalI;
                    }
                });

                if (searchedItem.isEmpty()) {

                    grid.add(anchorPane, column, row++);

                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(6, 0, 6, 0));

                } else if (Integer.parseInt(searchedItem) == marketController.getAllItems().get(i).getCode()) {
                    grid.getChildren().clear();

                    grid.add(anchorPane1, 0, 0);

                    grid.add(anchorPane, 0, 1);

                    grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                    grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    grid.setMaxWidth(Region.USE_PREF_SIZE);

                    grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                    grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    grid.setMaxHeight(Region.USE_PREF_SIZE);

                    GridPane.setMargin(anchorPane, new Insets(6, 0, 6, 0));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected int getTotalItemCount() {
        int totalItemCount = 0;
        for (int i = 0; i < marketController.getAllItems().size(); i++) {
            totalItemCount += marketController.getAllItems().get(i).getQuantity();
        }
        return totalItemCount;
    }

    protected double getTotalMonetaryValue() {
        double totalMonetaryValue = 0;
        for (int i = 0; i < marketController.getAllItems().size(); i++) {
            totalMonetaryValue += marketController.getAllItems().get(i).getPrice() * marketController.getAllItems().get(i).getQuantity();
        }
        return totalMonetaryValue;
    }

    public void selectItem(int finalRow){
        for (Node node : grid.getChildren()) {
            node.setStyle(null);
        }

        for (Node node : grid.getChildren()) {
            int nodeRow = GridPane.getRowIndex(node);
            if (nodeRow == finalRow) {
                node.setStyle("-fx-background-color: #1eff00;");
            }
        }
    }

    public void searchButton() {
        getRelevantData(searchBox.getText());
    }

    public void deleteItem() {
       if (selectedRow > 0){

           ButtonType confirm = AlertManager.showConfirmation("Confirmation", "Deletion of item", "Are you sure you want to delete this item?");

           if (confirm == ButtonType.OK){
               marketController.removeItem(marketController.getAllItems().get(deletedItemIndex));
               selectedRow = -1;
               getRelevantData(searchBox.getText());

               totalItemCount.setText("Total Item Count: " + getTotalItemCount());
               totalMonetaryValue.setText("Total Monetary Value: Rs. " + getTotalMonetaryValue());
           }
       }
    }
}
