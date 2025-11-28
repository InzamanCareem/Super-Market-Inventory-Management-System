package com.example.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MarketControllerTest {
    private MarketController marketController;
    private Items item1;
    private Items item2;

    @BeforeEach
    void setUp() {
        marketController = new MarketController();

        item1 = new Items();
        item1.setCode(1);
        item1.setName("Onion");
        item1.setBrand("-");
        item1.setPrice(160.0);
        item1.setQuantity(12);
        item1.setMinimumStockLevel(30);
        item1.setCategory("Vegetable");
        item1.setPurchasedDate("7/19/2025");
        item1.setImgSrc("onion.jpg");

        item2 = new Items();
        item2.setCode(2);
        item2.setName("Potatoes");
        item2.setBrand("-");
        item2.setPrice(390.0);
        item2.setQuantity(18);
        item2.setMinimumStockLevel(20);
        item2.setCategory("Vegetable");
        item2.setPurchasedDate("7/19/2025");
        item2.setImgSrc("potatoes.jpg");
    }

    @Test
    void testAddItemAtBeginning() {
        marketController.addItem(0, item1);
        List<Items> items = marketController.getAllItems();

        assertEquals(1, items.size());
        assertEquals(item1, items.get(0));
    }

    @Test
    void testAddItemOutOfBounds() {
        try {
            marketController.addItem(-1, item1);
            fail("Expected IndexOutOfBoundsException to be thrown");
        } catch (IndexOutOfBoundsException e) {
            // Test passes because exception was thrown
        }
    }

    @Test
    void testRemoveExistingItem() {
        marketController.addItem(0, item1);

        marketController.removeItem(item1);
        List<Items> items = marketController.getAllItems();

        assertEquals(0, items.size());
        assertFalse(items.contains(item1));
    }

    @Test
    void testRemoveNonExistentItem() {
        marketController.addItem(0, item1);

        marketController.removeItem(item2);
        List<Items> items = marketController.getAllItems();

        assertEquals(1, items.size());
        assertTrue(items.contains(item1));
        assertFalse(items.contains(item2));
    }

    @Test
    void testGetAllItemsReturnsCopy() {
        marketController.addItem(0, item1);
        List<Items> copy = marketController.getAllItems();
        copy.clear();

        // Ensure original list is unaffected
        List<Items> actual = marketController.getAllItems();
        assertEquals(1, actual.size());
        assertEquals(item1, actual.get(0));
    }

    // test for reading the file

    @Test
    void testReadsItemDetailsFileCorrectly(){
        marketController.getDataFromItemDetailsFile();
        assertEquals(30, marketController.getAllItems().size());
        assertEquals("Onion", marketController.getAllItems().getFirst().getName());
    }

    @Test
    void testReadsDealerFileCorrectly(){
        marketController.getDataFromDealersFile();
        assertEquals(6, marketController.getAllDealers().size());
        assertEquals("Thomas Shelby", marketController.getAllDealers().getFirst().getName());
    }
}
