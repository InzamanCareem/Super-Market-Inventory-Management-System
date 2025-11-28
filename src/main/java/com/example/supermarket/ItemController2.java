package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class ItemController2 extends AllItemsController{
    @FXML
    private ImageView itemImage;
    @FXML
    private Label itemCode;
    @FXML
    private Label itemCategory;
    @FXML
    private Label itemPurchasedDate;

    private Items myItems;

    @Override
    public void setData(Items items) {
        this.myItems = items;
        Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(items.getImgSrc())));
        itemImage.setImage(img);
        itemCode.setText(Integer.toString(items.getCode()));
        itemName.setText(items.getName());
        itemBrand.setText(items.getBrand());
        itemPrice.setText("Rs. " + items.getPrice());
        itemQuantity.setText(Integer.toString(items.getQuantity()));
        itemCategory.setText(items.getCategory());
        itemPurchasedDate.setText(items.getPurchasedDate());
    }
}
