package com.example.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DealerManagementControllerTest {

    private MarketController marketController;
    private DealerManagementController dealerManagementController;

    @BeforeEach
    void setUp() {
        marketController = new MarketController();
        dealerManagementController = new DealerManagementController();
        dealerManagementController.setMarketController(marketController);
    }

    @Test
    void testSetMarketController(){
        dealerManagementController.setMarketController(marketController);
        assertEquals(marketController, dealerManagementController.getMarketController());
    }

    @Test
    void testDealerSelection(){
        marketController.getDataFromDealersFile();
        dealerManagementController.selectDealers();
        assertEquals(4, dealerManagementController.selectedDealers.size());
    }

    @Test
    void testLoadSelectedDealerFile_ReadsItemsCorrectly() {
        String filename = "Thomas Shelby";

        dealerManagementController.loadSelectedDealerFile(filename);
        List<Items> items = dealerManagementController.selectedDealerItemDetails;

        assertEquals(3, items.size());
        assertEquals("Computer Mouse", items.get(0).getName());
        assertEquals(12, items.get(0).getQuantity());
        assertEquals("Laser Printer", items.get(1).getName());
        assertEquals(3, items.get(1).getQuantity());
    }
}
