/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.assigssmentprototype2;

import java.util.Scanner;

/**
 *
 * @author lab_services_student
 */
public class AssigssmentPrototype2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Login login = new Login();
        boolean registered = false;

        while (true) {
            System.out.println("\n===== Registration & Login System =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (registered) {
                        System.out.println("You have already registered. Please login.");
                        break;
                    }
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter SA cell phone (+27...): ");
                    String cellPhone = scanner.nextLine();

                    String result = login.registerUser(username, password, cellPhone,
                                                       firstName, lastName);
                    System.out.println(result);
                    if (result.equals("User successfully registered.")) {
                        registered = true;
                    }
                    break;

                case 2:
                    if (!registered) {
                        System.out.println("No user registered. Please register first.");
                        break;
                    }
                    System.out.print("Enter username: ");
                    String loginUser = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPass = scanner.nextLine();
                    boolean success = login.loginUser(loginUser, loginPass);
                    System.out.println(login.returnLoginStatus(success));
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }
    }

