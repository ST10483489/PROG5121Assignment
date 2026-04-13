/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assigssmentprototype2;

import java.util.regex.Pattern;

/**
 *
 * @author lab_services_student
 */
public class Login {
    private User registeredUser;

    public boolean checkUserName(String username) {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
        if (password == null || password.length() < 8) return false;
        boolean hasUpper = false, hasDigit = false, hasSpecial = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        return hasUpper && hasDigit && hasSpecial;
    }

    public boolean checkCellPhoneNumber(String phone) {
        Pattern pattern = Pattern.compile("^\\+27[0-9]{9,10}$");
        return phone != null && pattern.matcher(phone).matches();
    }

    public String registerUser(String username, String password, String cellPhone,
                               String firstName, String lastName) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Cell phone number incorrectly formatted or does not contain international code.";
        }
        registeredUser = new User(firstName, lastName, username, password, cellPhone);
        return "User successfully registered.";
    }

    public boolean loginUser(String username, String password) {
        if (registeredUser == null) return false;
        return registeredUser.getUsername().equals(username) &&
               registeredUser.getPassword().equals(password);
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess && registeredUser != null) {
            return "Welcome " + registeredUser.getFirstName() + " " +
                   registeredUser.getLastName() + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}
