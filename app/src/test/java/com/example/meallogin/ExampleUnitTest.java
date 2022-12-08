package com.example.meallogin;

import static androidx.core.content.ContextCompat.startActivity;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;
import android.content.Intent;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ExampleUnitTest {
    private static final String VALID_STRING = "Success";
    private static final String INVALID_STRING = "Login failed";

    @Mock
    Context mMockContext;

    @Test
    public void testAdminLogin() {
        assertEquals(MainActivity.validate("admin", "admin"), VALID_STRING);
        assertEquals(MainActivity.validate("x", "x"), INVALID_STRING);
    }

    @Test
    public void testSignUp() {
        assertEquals(SignUp.validate("admin", "x", "x", "email"), INVALID_STRING);
        assertEquals(SignUp.validate("ekoro", "x", "x", "email"), INVALID_STRING);
        assertEquals(SignUp.validate("gdupu", "x", "x", "email"), INVALID_STRING);
        assertEquals(SignUp.validate("nayna", "x", "x", "email"), INVALID_STRING);
        assertEquals(SignUp.validate("ikarr", "x", "x", "email"), INVALID_STRING);
        assertEquals(SignUp.validate("user", "x", "y", "email"), INVALID_STRING);
        assertEquals(SignUp.validate("user", "y", "x", "email"), INVALID_STRING);
        assertEquals(SignUp.validate("admin", "x", "x", ""), INVALID_STRING);
        assertEquals(SignUp.validate("admin", "x", "x", "email"), VALID_STRING);
    }

    @Test
    public void testIsCookSuspended() {

        Cook cookSuspended = new Cook();
        cookSuspended.setStatus(true);
        Cook cookNotSuspended = new Cook();

        assertTrue(cookSuspended.Suspended());
        assertFalse(cookNotSuspended.Suspended());
    }

    @Test
    public void testRoles() {
        Cook cook = new Cook();
        Client client = new Client();
        Administrator admin = new Administrator();

        assertTrue(cook.getRole().equals("Cook"));
        assertTrue(client.getRole().equals("Client"));
        assertTrue(admin.getRole().equals("Admin"));
    }
}