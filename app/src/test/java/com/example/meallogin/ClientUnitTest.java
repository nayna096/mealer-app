package com.example.meallogin;
import org.junit.Test;

import static org.junit.Assert.*;
public class ClientUnitTest {
    public void isClient(){
        Client c = new Client();
        assertEquals(c.getRole(), "Client");
    }
}
