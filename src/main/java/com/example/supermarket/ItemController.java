package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ItemController extends AllItemsController {
    @FXML
    private ImageView itemImage;
    @FXML
    private Label minimumStockLevel;

    private Items myItems;

    @Override
    public void setData(Items myItems) {
        this.myItems = myItems;
        itemName.setText(myItems.getName());
        itemPrice.setText("Rs. " + myItems.getPrice());
        minimumStockLevel.setText(Integer.toString(myItems.getMinimumStockLevel()));
        itemQuantity.setText(Integer.toString(myItems.getQuantity()));
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(myItems.getImgSrc())));
        itemImage.setImage(img);
    }
}
