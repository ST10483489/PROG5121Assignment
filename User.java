/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.assigssmentprototype2;

/**
 *
 * @author lab_services_student
 */
public class User {
     private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String cellPhone;

    public User(String firstName, String lastName, String username,
                String password, String cellPhone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
    }

    public String getFirstName() { return firstName; }
    public String getLastName()  { return lastName;  }
    public String getUsername()  { return username;  }
    public String getPassword()  { return password;  }
    public String getCellPhone() { return cellPhone; }
    
}
