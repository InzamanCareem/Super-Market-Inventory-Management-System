package com.example.supermarket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemsTest {

    private Items item;

    @BeforeEach
    public void setUp() {
        item = new Items();
    }

    @Test
    public void testSetAndGetCode() {
        item.setCode(1);
        assertEquals(1, item.getCode());
    }

    @Test
    public void testSetAndGetName() {
        item.setName("Milk");
        assertEquals("Milk", item.getName());
    }

    @Test
    public void testSetAndGetBrand() {
        item.setBrand("Astra");
        assertEquals("Astra", item.getBrand());
    }

    @Test
    public void testSetAndGetPrice() {
        item.setPrice(150.0);
        assertEquals(150.0, item.getPrice());
    }

    @Test
    public void testSetAndGetQuantity() {
        item.setQuantity(30);
        assertEquals(30, item.getQuantity());
    }

    @Test
    public void testSetAndGetMinimumStockLevel() {
        item.setMinimumStockLevel(5);
        assertEquals(5, item.getMinimumStockLevel());
    }

    @Test
    public void testSetAndGetCategory() {
        item.setCategory("Fruit");
        assertEquals("Fruit", item.getCategory());
    }

    @Test
    public void testSetAndGetPurchasedDate() {
        item.setPurchasedDate("7/19/2025");
        assertEquals("7/19/2025", item.getPurchasedDate());
    }

    @Test
    public void testSetAndGetImgSrc() {
        item.setImgSrc("garlic.jpg");
        assertEquals("garlic.jpg", item.getImgSrc());
    }
}
