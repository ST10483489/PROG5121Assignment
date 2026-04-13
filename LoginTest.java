/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.assigssmentprototype2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class LoginTest {
    
    private final Login login = new Login();
    
    public LoginTest() {
    }

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        // Valid username cases
        assertTrue(login.checkUserName("kyl_1"), "Username with underscore and length 5 should be valid");
        assertTrue(login.checkUserName("a_1"), "Short valid username");
        // Invalid username cases
        assertFalse(login.checkUserName("kyle!!!!!!!"), "No underscore, too long");
        assertFalse(login.checkUserName("kyle1"), "No underscore");
        assertFalse(login.checkUserName("kyle_12"), "Too long (6 chars)");
        assertFalse(login.checkUserName(null), "Null should be invalid");
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        // Valid password
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"), "Complex password with all requirements");
        assertTrue(login.checkPasswordComplexity("Abc123!@#"), "Another valid password");
        // Invalid passwords
        assertFalse(login.checkPasswordComplexity("password"), "No uppercase, no digit, no special");
        assertFalse(login.checkPasswordComplexity("Password1"), "No special character");
        assertFalse(login.checkPasswordComplexity("Password!"), "No digit");
        assertFalse(login.checkPasswordComplexity("pass1!"), "Length < 8");
        assertFalse(login.checkPasswordComplexity(""), "Empty string");
        assertFalse(login.checkPasswordComplexity(null), "Null");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        // Valid SA numbers
        assertTrue(login.checkCellPhoneNumber("+27838968976"), "Valid +27 followed by 11 digits total? Actually +27 + 9 digits = 12 chars? Let's verify: +27 83 896 8976 is 12 chars including +. The regex expects +27 then 9 or 10 digits. +27838968976 has +27 then 9 digits (838968976) -> valid");
        assertTrue(login.checkCellPhoneNumber("+27123456789"), "Valid 9-digit after code");
        assertTrue(login.checkCellPhoneNumber("+271234567890"), "Valid 10-digit after code");
        // Invalid numbers
        assertFalse(login.checkCellPhoneNumber("08966553"), "No country code");
        assertFalse(login.checkCellPhoneNumber("0831234567"), "Missing +27");
        assertFalse(login.checkCellPhoneNumber("+2712345678901"), "Too many digits (11 after code)");
        assertFalse(login.checkCellPhoneNumber("+278"), "Too short");
        assertFalse(login.checkCellPhoneNumber(null), "Null");
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        // Test username failure
        String result1 = login.registerUser("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result1);
        
        // Test password failure
        String result2 = login.registerUser("kyl_1", "password", "+27838968976", "John", "Doe");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result2);
        
        // Test cell phone failure
        String result3 = login.registerUser("kyl_1", "Ch&&sec@ke99!", "08966553", "John", "Doe");
        assertEquals("Cell phone number incorrectly formatted or does not contain international code.", result3);
        
        // Test successful registration
        String result4 = login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        assertEquals("User successfully registered.", result4);
        // Also verify that login works after registration
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        // First register a user
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        
        // Correct credentials
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"), "Correct login should return true");
        
        // Incorrect password
        assertFalse(login.loginUser("kyl_1", "wrongpass"), "Wrong password should return false");
        
        // Incorrect username
        assertFalse(login.loginUser("wronguser", "Ch&&sec@ke99!"), "Wrong username should return false");
        
        // Before registration, login should be false (but we registered above, so create new Login instance for that test)
        Login emptyLogin = new Login();
        assertFalse(emptyLogin.loginUser("any", "any"), "No user registered should return false");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        // Register a user
        login.registerUser("kyl_1", "Ch&&sec@ke99!", "+27838968976", "John", "Doe");
        
        // Successful login status
        boolean success = login.loginUser("kyl_1", "Ch&&sec@ke99!");
        String successMsg = login.returnLoginStatus(success);
        assertEquals("Welcome John Doe, it is great to see you again.", successMsg);
        
        // Failed login status
        boolean fail = login.loginUser("kyl_1", "wrong");
        String failMsg = login.returnLoginStatus(fail);
        assertEquals("Username or password incorrect, please try again.", failMsg);
        
        // Also test with a login that never had a user (use a fresh Login object)
        Login emptyLogin = new Login();
        String noUserMsg = emptyLogin.returnLoginStatus(false);
        assertEquals("Username or password incorrect, please try again.", noUserMsg);
    }
}