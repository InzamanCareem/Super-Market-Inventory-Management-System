package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Parent;
import javafx.scene.layout.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class MarketController implements Initializable {
    @FXML
    private BorderPane rootPane;

    private List<Items> allMyItems = new ArrayList<>();
    private List<Dealer> allDealers = new ArrayList<>();

    public List<Items> getAllItems() {
        return new ArrayList<>(allMyItems);
    }
    public void addItem(int index, Items myItems) {
        allMyItems.add(index, myItems);
    }
    public void removeItem(Items myItems) {
        allMyItems.remove(myItems);
    }
    public List<Dealer> getAllDealers() {
        return new ArrayList<>(allDealers);
    }

    // Scene Manager
    @FXML
    protected void changeToInventoryScene() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("my-inventory.fxml"));
        Parent root = loader.load();
        MyInventoryController inventoryController = loader.getController();
        inventoryController.setMarketController(this);
        rootPane.setCenter(root);
    }

    @FXML
    protected void changeToItemEditorScene() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("item-editor.fxml"));
        Parent root = loader.load();

        ItemEditorController itemEditorController = loader.getController();
        itemEditorController.setMarketController(this);

        rootPane.setCenter(root);
    }

    @FXML
    protected void changeToItemDetailsScene() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("item-details.fxml"));
        Parent root = loader.load();

        ItemDetailsController itemDetailsController = loader.getController();
        itemDetailsController.setMarketController(this);

        rootPane.setCenter(root);
    }

    @FXML
    protected void changeToDealerManagementScene() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dealer-management.fxml"));
        Parent root = loader.load();

        DealerManagementController dealerManagementController = loader.getController();
        dealerManagementController.setMarketController(this);

        rootPane.setCenter(root);
    }

    // Reading files
    public void getDataFromItemDetailsFile() {
        Items myItems;

        File file = new File("src/main/resources/com/example/supermarket/data/item-details.txt");

        try{
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] itemInFile = scanner.nextLine().split(", ");

                myItems = new Items();
                myItems.setCode(Integer.parseInt(itemInFile[0]));
                myItems.setName(itemInFile[1]);
                myItems.setBrand(itemInFile[2]);
                myItems.setPrice(Double.parseDouble(itemInFile[3]));
                myItems.setQuantity(Integer.parseInt(itemInFile[4]));
                myItems.setMinimumStockLevel(Integer.parseInt(itemInFile[5]));
                myItems.setCategory(itemInFile[6]);
                myItems.setPurchasedDate(itemInFile[7]);
                myItems.setImgSrc("images/" + itemInFile[8].replace(",", ""));

                allMyItems.add(myItems);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public void getDataFromDealersFile(){
        Dealer dealer;

        File file = new File("src/main/resources/com/example/supermarket/data/dealers.txt");

        try{
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] dealerInFile = scanner.nextLine().split(", ");

                dealer = new Dealer();
                dealer.setName(dealerInFile[0]);
                dealer.setContact(dealerInFile[1]);
                dealer.setLocation(dealerInFile[2].replace(",", ""));

                allDealers.add(dealer);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Save Details to files
    public void saveDetails(){
        try (FileWriter writer = new FileWriter("src/main/resources/com/example/supermarket/data/item-details.txt")) {

            for (Items allMyItem : allMyItems) {
                String[] details = new String[9];
                details[0] = Integer.toString(allMyItem.getCode());
                details[1] = allMyItem.getName();
                details[2] = allMyItem.getBrand();
                details[3] = Double.toString(allMyItem.getPrice());
                details[4] = Integer.toString(allMyItem.getQuantity());
                details[5] = Integer.toString(allMyItem.getMinimumStockLevel());
                details[6] = allMyItem.getCategory();
                details[7] = allMyItem.getPurchasedDate();
                details[8] = allMyItem.getImgSrc().split("/")[1];

                for (String detail : details) {
                    writer.write(detail);
                    writer.write(", ");
                }
                writer.write("\n");
            }

            AlertManager.showInformation("Saving", "Saving item details to file", "Item Details have been saved!");

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        getDataFromItemDetailsFile();
        getDataFromDealersFile();

        try {
            changeToInventoryScene();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}