package com.example.supermarket;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class DealerManagementController {
    @FXML
    private Button drawDealersButton;
    @FXML
    private Button back;
    @FXML
    private Label viewItemsLabel;
    @FXML
    private GridPane grid;
    @FXML
    private VBox box;

    private int selectedRow = -1;

    protected List<Dealer> selectedDealers = new ArrayList<>();
    protected List<Items> selectedDealerItemDetails = new ArrayList<>();

    private MarketController marketController;

    public void setMarketController(MarketController marketController) {
        this.marketController = marketController;
    }

    public MarketController getMarketController(){
        return marketController;
    }

    public void selectDealers(){
        Random random = new Random();
        while (selectedDealers.size() < 4){
            Dealer dealer = marketController.getAllDealers().get(random.nextInt(0, 6));
            if (!selectedDealers.contains(dealer)){
                selectedDealers.add(dealer);
            }
        }
    }

    public void sortData(){
        // Uses Bubble Sort algorithm to achieve sorting by location
        for (int i = 0; i < selectedDealers.size(); i++) {
            for (int j = 0; j < selectedDealers.size() - i - 1; j++) {
                if (selectedDealers.get(j).getLocation().compareToIgnoreCase(selectedDealers.get(j + 1).getLocation()) > 0){
                    // Swaps the two dealers
                    Dealer temp = selectedDealers.get(j);
                    selectedDealers.set(j, selectedDealers.get(j + 1));
                    selectedDealers.set(j + 1, temp);
                }
            }
        }
    }

    public void showData() {
        int column = 0;
        int row = 0;

        try {
            // Loads the 'dealer-columns.fxml' and places it on the GridPane
            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader2.setLocation(getClass().getResource("dealer-columns.fxml"));
            AnchorPane anchorPane1 = fxmlLoader2.load();

            grid.getChildren().clear();

            grid.add(anchorPane1, column, row++);

            for (Dealer selectedDealer : selectedDealers) {
                // Loads the 'dealer.fxml' and places it on the GridPane
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("dealer.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                DealerController dealerController = fxmlLoader.getController();
                dealerController.setData(selectedDealer);

                // Sets a mouse event handler on each dealer.fxml file's AnchorPane
                int finalRow = row;
                anchorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        selectedRow = finalRow;
                        // Highlights the selected row
                        selectDealer(selectedRow);
                    }
                });

                grid.add(anchorPane, column, row++);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(6, 0, 6, 0));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void selectDealer(int select){
        for (Node node : grid.getChildren()) {
            node.setStyle(null);
        }
        for (Node node : grid.getChildren()) {
            int nodeRow = GridPane.getRowIndex(node);
            if (nodeRow == select) {
                node.setStyle("-fx-background-color: lightblue;");
            }
        }
    }

    public void drawDealers() {
        selectedDealers.clear();
        box.setVisible(true);
        selectDealers();
        sortData();
        showData();
    }

    // Reads the selected dealers files' items
    public void loadSelectedDealerFile(String filename){
        selectedDealerItemDetails.clear();

        Items dealerItems;

        File file = new File("src/main/resources/com/example/supermarket/data/"+ filename +".txt");

        try{
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] itemInFile = scanner.nextLine().split(", ");

                dealerItems = new Items();
                dealerItems.setName(itemInFile[0]);
                dealerItems.setBrand(itemInFile[1]);
                dealerItems.setPrice(Double.parseDouble(itemInFile[2]));
                dealerItems.setQuantity(Integer.parseInt(itemInFile[3]));

                selectedDealerItemDetails.add(dealerItems);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void showDealerItemDetails(String dealerName){
        loadSelectedDealerFile(dealerName);

        viewItemsLabel.setText("Viewing Items of " + dealerName);
        drawDealersButton.setVisible(false);

        int column = 0;
        int row = 0;

        try {
            grid.getChildren().clear();

            FXMLLoader fxmlLoader2 = new FXMLLoader();
            fxmlLoader2.setLocation(getClass().getResource("selected-dealer-items-columns.fxml"));
            AnchorPane anchorPane1 = fxmlLoader2.load();
            grid.add(anchorPane1, column, row++);

            for (Items selectedDealerItemDetail : selectedDealerItemDetails) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("selected-dealer-items.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                DealerItemsController dealerItemsController = fxmlLoader.getController();
                dealerItemsController.setData(selectedDealerItemDetail);

                grid.add(anchorPane, column, row++);

                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(6, 0, 6, 0));
            }

            back.setText("Back");
            selectedRow = -1;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void select_backButton() {
        if (selectedRow >= 0) {
            showDealerItemDetails(selectedDealers.get(selectedRow - 1).getName());
        }
        else{
            showData();
            viewItemsLabel.setText("");
            drawDealersButton.setVisible(true);
            back.setText("Select Dealer");
        }
    }
}
