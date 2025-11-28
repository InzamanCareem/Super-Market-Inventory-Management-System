package com.example.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MyInventoryControllerTest {
    private MarketController marketController;
    private MyInventoryController myInventoryController;
    private Items item1;
    private Items item2;
    private Items item3;
    private Items item4;

    @BeforeEach
    void setUp() {
        marketController = new MarketController();
        myInventoryController = new MyInventoryController();

        item1 = new Items();
        item1.setCode(1);
        item1.setQuantity(10);
        item1.setMinimumStockLevel(20);

        item2 = new Items();
        item2.setCode(2);
        item2.setQuantity(30);
        item2.setMinimumStockLevel(20);

        item3 = new Items();
        item3.setCode(3);
        item3.setQuantity(16);
        item3.setMinimumStockLevel(20);

        item4 = new Items();
        item4.setCode(4);
        item4.setQuantity(5);
        item4.setMinimumStockLevel(20);
    }

    @Test
    void testSetMarketController(){
        myInventoryController.setMarketController(marketController);
        assertEquals(marketController, myInventoryController.getMarketController());
    }

    @Test
    void testSort(){
        List<Items> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);

        List<Items> itemsCopy = new ArrayList<>();
        itemsCopy.add(item4);
        itemsCopy.add(item1);
        itemsCopy.add(item3);

        List<Items> sortedItems = myInventoryController.sort(items);

        assertEquals(itemsCopy, sortedItems);
    }
}