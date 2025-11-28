package com.example.supermarket;

public class DealerItemsController extends AllItemsController{

    private Items dealerItems;

    public void setData(Items dealerItems) {
        this.dealerItems = dealerItems;
        itemName.setText(dealerItems.getName());
        itemBrand.setText(dealerItems.getBrand());
        itemPrice.setText("Rs. " + dealerItems.getPrice());
        itemQuantity.setText(Integer.toString(dealerItems.getQuantity()));
    }
}
