package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public abstract class AllItemsController {
    @FXML
    protected Label itemName;
    @FXML
    protected Label itemBrand;
    @FXML
    protected Label itemPrice;
    @FXML
    protected Label itemQuantity;

    public abstract void setData(Items items);
}
