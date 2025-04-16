package com.blog.main;

import com.blog.dao.*;
import com.blog.model.*;
import java.util.Scanner;

public class Main {
    private static UserDAO userDAO = new UserDAO();
    private static BlogDAO blogDAO = new BlogDAO();
    private static CategoryDAO categoryDAO = new CategoryDAO();
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeScreen();
    }

    private static void showWelcomeScreen() {
        while (true) {
            System.out.println("\n| Welcome to Blogging App |");
            System.out.println("|------------------------|");
            System.out.println("| 0. Exit               |");
            System.out.println("| 1. Login              |");
            System.out.println("| 2. Register           |");
            System.out.println("|------------------------|");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 0:
                    System.out.println("Exiting application...");
                    System.exit(0);
                    break;
                case 1:
                    loginUser();
                    break;
                case 2:
                    registerUser();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loginUser() {
        System.out.println("\n| Login |");
        System.out.println("|----------------|");
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("\nLogin successful! Welcome, " + user.getFullName());
            showMainMenu();
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    private static void registerUser() {
        System.out.println("\n| Registration |");
        System.out.println("|----------------|");
        System.out.print("Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Phone No: ");
        String phoneNo = scanner.nextLine();

        User user = new User(0, fullName, email, password, phoneNo);
        if (userDAO.addUser(user)) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed. Email may already be in use.");
        }
    }

    private static void showMainMenu() {
        while (currentUser != null) {
            System.out.println("\n| Blogging App |");
            System.out.println("| Welcome " + currentUser.getFullName() + " |");
            System.out.println("|----------------|");
            System.out.println("| 1. Add Category   |");
            System.out.println("| 2. Show Categories|");
            System.out.println("| 3. All Blogs      |");
            System.out.println("| 4. My Blogs       |");
            System.out.println("| 5. Add Blog       |");
            System.out.println("| 6. Edit Blog      |");
            System.out.println("| 7. Search Blog    |");
            System.out.println("| 8. Delete Blog    |");
            System.out.println("| 9. Logout         |");
            System.out.println("|----------------|");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    showCategories();
                    break;
                case 3:
                    showAllBlogs();
                    break;
                case 4:
                    showMyBlogs();
                    break;
                case 5:
                    addBlog();
                    break;
                case 6:
                    editBlog();
                    break;
                case 7:
                    searchBlogs();
                    break;
                case 8:
                    deleteBlog();
                    break;
                case 9:
                    currentUser = null;
                    System.out.println("Logged out successfully.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Implement other methods (addCategory, showCategories, etc.) similarly
    // ...
}