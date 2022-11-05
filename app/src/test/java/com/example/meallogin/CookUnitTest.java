package com.example.meallogin;
import org.junit.Test;

import static org.junit.Assert.*;
public class CookUnitTest {
   public void isCook(){
       Cook c = new Cook();
       assertEquals(c.getRole(), "Cook");
   }
}
