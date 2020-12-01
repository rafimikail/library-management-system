/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.util.Formatter;

/**
 *
 * @author kayva
 */
public class Librarian {

    private static Formatter z;
    private static String book;

    public static void LibrarianPage() throws IOException {
        
        Clock realClock = Clock.systemDefaultZone();
        Instant instant = realClock.instant();

        
        System.out.println("-------------Welcome to the main page of Librarian----------");
        System.out.println("1. Add a new member");
        System.out.println("2. Add/Modify/Delete books categories");
        System.out.println("3. Add/Modify/Delete books");
        System.out.println("4. Manage books status");
        System.out.println("5. Log out");
        System.out.print("Your choice : ");
        Scanner s = new Scanner(System.in);
        int home = s.nextInt();
        switch (home) {
            case 1:
                AddMember();
                break;
            case 2:
                System.out.println("\n1. Add Categories");
                System.out.println("2. Modify Categories");
                System.out.println("3. Delete Categories");
                System.out.println("4. Go back");
                System.out.print("Your choice : ");
                int choiceCat = s.nextInt();
                switch (choiceCat) {
                    case 1:
                        System.out.println("-------------------- **Books Categories** --------------------");
                        Book.ShowBookCategories();
                        Book.AddCategories();
                        break;
                    case 2:
                        System.out.println("-------------------- **Books Categories** --------------------");
                        Book.ShowBookCategories();
                        Book.ModifyCategories();
                        break;
                    case 3:
                        System.out.println("-------------------- **Books Categories** --------------------");
                        Book.ShowBookCategories();
                        Book.DeleteCategories();
                        break;
                    case 4:
                        LibrarianPage();
                        break;
                    default:
                        System.out.println("Error!! Please enter the correct choice");
                        break;
                }
                break;
            case 3:
                System.out.println("\n1. Add Books");
                System.out.println("2. Modify Books");
                System.out.println("3. Delete Books");
                System.out.println("4. Go back");
                System.out.print("Your choice : ");
                int choiceBook = s.nextInt();
                switch (choiceBook) {
                    case 1:
                        System.out.println("-------------------- **Books Categories** --------------------");
                        Book.ShowBookCategories();
                        Book.AddBook();
                        break;
                    case 2:
                        System.out.println("-------------------- **Books Categories** --------------------");
                        Book.ShowBookCategories();
                        Book.ModifyBook();
                        break;
                    case 3:
                        Scanner sc = new Scanner(System.in);
                        System.out.println("-------------------- **Books Categories** --------------------");
                        Book.ShowBookCategories();
                        System.out.print("Enter the categories : ");
                        String BookCat = sc.nextLine();
                        Book.ShowBooks(BookCat);
                        Book.DeleteBook(BookCat);
                        break;
                    case 4:
                        LibrarianPage();
                        break;
                    default:
                        System.out.println("Error!! Please enter the right choice");
                        break;
                }
                break;
            case 4:
                Scanner sc = new Scanner(System.in);
                Book.ShowBookCategories();
                System.out.print("Enter the categories : ");
                String categ = sc.nextLine();
                Book.ShowBooks(categ);
                System.out.println("Enter the title you want to returned : ");
                String bookName = sc.nextLine();
                Book.BookKeeping(categ,bookName);
                
                LibrarianPage();
                break;
            case 5:
                Library.LoginPage();
                String librarianlog = Library.User2()+" logged out as Librarian on "+instant;
                HeadLibrarian.Logger(librarianlog);
                break;
            default:
                System.out.println("Error!! Please input the correct choice");
                LibrarianPage();
                break;
        }

    }

    public static void AddMember() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Please enter username : ");
        String text = sc.nextLine();
        BufferedWriter writer = null;
        File file = new File("Login\\Members\\" + text + ".txt");
        if (!file.exists()) {
            System.out.print("Enter password : ");
            String pass = sc.nextLine();
            System.out.println("Enter the date of validity (dd/mm/yyyy): ");
            String date = sc.nextLine();
            try {
                writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(pass);
                writer.newLine();
                writer.write(date);
                writer.close();
                System.out.println("");
                System.out.println("Username of " + text + " is created.");
                System.out.println("");
                String a = "A book (" + text + ") is created";
                HeadLibrarian.Logger(a);
                Librarian.LibrarianPage();
            } catch (Exception e) {
                System.out.println(e + "Error");
            }
        } else {
            System.out.println("User " + text + " already exists");
        }
        Librarian.LibrarianPage();
    }

    public static void statuschanger(String categ,String bookName) {
        try {
            z = new Formatter("Books Categories\\" + categ + "\\" + bookName + ".txt");
            z.format("%s", "Available");
            z.close();
        } catch (Exception e) {
            System.out.println("Error!");

        }
    }

}
