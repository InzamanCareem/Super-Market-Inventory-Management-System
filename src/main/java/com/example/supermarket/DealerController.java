package com.example.supermarket;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DealerController {
    @FXML
    private Label dealerName;
    @FXML
    private Label dealerContact;
    @FXML
    private Label dealerLocation;

    private Dealer dealer;

    public void setData(Dealer dealer) {
        this.dealer = dealer;
        dealerName.setText(dealer.getName());
        dealerContact.setText(dealer.getContact());
        dealerLocation.setText(dealer.getLocation());
    }
}
