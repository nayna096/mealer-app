package com.example.meallogin;
import org.junit.Test;

import static org.junit.Assert.*;
public class AdminUnitTest {
    public void isAdmin(){
        Administrator a = new Administrator();
        assertEquals(a.getRole(), "Administrator");
    }

}
