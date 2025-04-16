package com.blog.main;

import com.blog.dao.*;
import com.blog.model.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static UserDAO userDAO = new UserDAO();
    private static BlogDAO blogDAO = new BlogDAO();
    private static CategoryDAO categoryDAO = new CategoryDAO();
    private static User currentUser = null;
    private static Scanner sc= new Scanner(System.in);

    public static void main(String[] args) {
        showWelcomeScreen();
    }

    private static void showWelcomeScreen() {
        while (true) {
            System.out.println("Welcome to Blogging App");
            System.out.println("0.Exit");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 0:
                    System.out.println("Exiting application");
                    System.exit(0);
                    break;
                case 1:
                    loginUser();
                    break;
                case 2:
                    registerUser();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void loginUser() {
        System.out.println("Login");
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            System.out.println("Login successful! Welcome, " + user.getFullName());
            showMainMenu();
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }

    private static void registerUser() {
        System.out.println("Registration");
        System.out.print("Full Name: ");
        String fullName = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();
        System.out.print("Phone No: ");
        String phoneNo = sc.nextLine();

        User user = new User(0, fullName, email, password, phoneNo);
        if (userDAO.addUser(user)) {
            System.out.println("Registration successful! You can now login.");
        } else {
            System.out.println("Registration failed. Email may already be in use.");
        }
    }

    private static void showMainMenu() {
        while (currentUser != null) {
            System.out.println("Blogging App");
            System.out.println("Welcome " + currentUser.getFullName() + " |");
            System.out.println("1.Add Category");
            System.out.println("2.Show Categories");
            System.out.println("3.All Blogs");
            System.out.println("4.My Blogs");
            System.out.println("5.Add Blog");
            System.out.println("6.Edit Blog");
            System.out.println("7. Search Blog");
            System.out.println("8.Delete Blog");
            System.out.println("9.Logout");
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

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

    private static void addCategory() {
        System.out.println("\n| Add Category |");
        System.out.println("|----------------|");
        System.out.print("Enter category name: ");
        String name = scanner.nextLine();

        Category category = new Category(0, name);
        if (categoryDAO.addCategory(category)) {
            System.out.println("Category added successfully!");
        } else {
            System.out.println("Failed to add category. It may already exist.");
        }
    }

    private static void showCategories() {
        System.out.println("\n| All Categories |");
        System.out.println("|-----------------|");
        List<Category> categories = categoryDAO.getAllCategories();
        
        if (categories.isEmpty()) {
            System.out.println("No categories found.");
        } else {
            for (Category category : categories) {
                System.out.println(category.getId() + ". " + category.getName());
            }
        }
    }

    private static void showAllBlogs() {
        System.out.println("\n| All Blogs |");
        System.out.println("|------------|");
        List<Blog> blogs = blogDAO.getAllBlogs();
        displayBlogList(blogs);
    }

    private static void showMyBlogs() {
        System.out.println("\n| My Blogs |");
        System.out.println("|-----------|");
        List<Blog> blogs = blogDAO.getBlogsByUser(currentUser.getId());
        displayBlogList(blogs);
    }

    private static void displayBlogList(List<Blog> blogs) {
        if (blogs.isEmpty()) {
            System.out.println("No blogs found.");
        } else {
            for (Blog blog : blogs) {
                System.out.println(blog.getId() + ". " + blog.getTitle());
            }
            System.out.print("\nEnter blog ID to view details (0 to go back): ");
            int blogId = getIntInput();
            
            if (blogId > 0) {
                viewBlogDetails(blogId);
            }
        }
    }

    private static void viewBlogDetails(int blogId) {
        // Implementation would fetch and display full blog details
        System.out.println("\n| Blog Details |");
        System.out.println("|--------------|");
        // In a real implementation, you would fetch the blog by ID
        System.out.println("Feature to be implemented");
    }

    private static void addBlog() {
        System.out.println("\n| Add Blog |");
        System.out.println("|-----------|");
        
        showCategories();
        System.out.print("Select category ID: ");
        int categoryId = getIntInput();
        
        System.out.print("Enter blog title: ");
        String title = sc.nextLine();
        System.out.print("Enter blog content: ");
        String content = sc.nextLine();

        Blog blog = new Blog(0, title, content, currentUser.getId(), categoryId);
        if (blogDAO.addBlog(blog)) {
            System.out.println("Blog added successfully!");
        } else {
            System.out.println("Failed to add blog.");
        }
    }

    private static void editBlog() {
        System.out.println("\n| Edit Blog |");
        System.out.println("|------------|");
        System.out.println("Feature to be implemented");
    }

    private static void searchBlogs() {
        System.out.println("\n| Search Blogs |");
        System.out.println("|---------------|");
        System.out.print("Enter search term: ");
        String term = sc.nextLine();
        System.out.println("Search functionality to be implemented");
    }

    private static void deleteBlog() {
        System.out.println("\n| Delete Blog |");
        System.out.println("|--------------|");
        System.out.println("Feature to be implemented");
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}