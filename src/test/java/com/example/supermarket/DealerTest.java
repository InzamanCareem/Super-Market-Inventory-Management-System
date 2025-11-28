package com.example.supermarket;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DealerTest {

    @Test
    void testSetAndGetName() {
        Dealer dealer = new Dealer();
        dealer.setName("Thomas Shelby");

        assertEquals("Thomas Shelby", dealer.getName());
    }

    @Test
    void testSetAndGetContact() {
        Dealer dealer = new Dealer();
        dealer.setContact("0123456789");

        assertEquals("0123456789", dealer.getContact());
    }

    @Test
    void testSetAndGetLocation() {
        Dealer dealer = new Dealer();
        dealer.setLocation("New York");

        assertEquals("New York", dealer.getLocation());
    }
}
