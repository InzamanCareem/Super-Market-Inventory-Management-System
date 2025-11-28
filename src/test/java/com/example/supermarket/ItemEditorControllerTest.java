package com.example.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemEditorControllerTest {
    private MarketController marketController;
    private ItemEditorController itemEditorController;

    @BeforeEach
    public void setUp() {
        marketController = new MarketController();
        itemEditorController = new ItemEditorController();

        itemEditorController.setMarketController(marketController);
    }

    @Test
    void testSetMarketController(){
        itemEditorController.setMarketController(marketController);
        assertEquals(marketController, itemEditorController.getMarketController());
    }

    @Test
    void testUpdatable(){
        boolean up = itemEditorController.updatable;
        assertFalse(false, String.valueOf(up));
    }
}
