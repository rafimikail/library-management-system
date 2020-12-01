/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author kayva
 */
public class Book {

    private static Scanner x;
    private static String book;
    private static Formatter z;
    private static String t;

    public static void AddCategories() throws IOException {
        System.out.printf("Enter the categories that you want to add : ");
        Scanner sc = new Scanner(System.in);
        String folderName = sc.nextLine();
        File file = new File("Book Categories\\" + folderName);
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.printf("\n%s is created\n\n", folderName);
                t = "A new category of book (" + folderName + ") has been created";
                HeadLibrarian.Logger(t);
            } else {
                System.out.printf("\nfailed to create\n\n");
            }
        } else {
            System.out.printf("\nThe category is already exists\n");
        }
        Librarian.LibrarianPage();
    }

    public static void ModifyCategories() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the category's old name to modify :");
        String oldName = sc.nextLine();
        File oldFile = new File("Book Categories\\" + oldName);
        System.out.println("Enter the category's new name : ");
        String newName = sc.nextLine();
        File newFile = new File("Book Categories\\" + newName);
        boolean ModifyBook = oldFile.renameTo(newFile);
        if (ModifyBook) {
            System.out.println("Category has been renamed");
            t = oldName+" directory is renamed to "+newName;
            HeadLibrarian.Logger(t);
        } else {
            System.out.println("Failed to renaming the file");
        }
        Librarian.LibrarianPage();
    }

    public static void DeleteCategories() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the categories you want to delete : ");
        String BookCat = sc.nextLine();
        File file = new File("Book Categories\\" + BookCat);
        if (file.exists()) {
            try {
                delete(file);
                System.out.println("");
                System.out.println(BookCat + " is deleted\n");
                t = BookCat + " just has been deleted from book categories";
                HeadLibrarian.Logger(t);
            } catch (Exception e) {
                System.out.println("Error!!");
            }
        } else {
            System.out.println(BookCat + " is not exists");
        }

        Librarian.LibrarianPage();
    }

    public static void delete(File file) throws IOException {
        if (file.isDirectory()) {
            //directory is empty, then delete it
            if (file.list().length == 0) {
                file.delete();
                System.out.println("Directory is deleted : " + file.getAbsolutePath());
            } else {
                //list all the directory contents
                String files[] = file.list();
                for (String temp : files) {
                    //construct the file structure
                    File fileDelete = new File(file, temp);
                    //recursive delete
                    delete(fileDelete);
                }
                //check the directory again, if empty then delete it
                if (file.list().length == 0) {
                    file.delete();
                    System.out.println("Directory is deleted : " + file.getAbsolutePath());
                }
            }
        } else {
            //if file, then delete it
            file.delete();
            System.out.println("\nFile is deleted : " + file.getAbsolutePath());
        }
    }

    public static void AddBook() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the categories : ");
        String BookCat = sc.nextLine();
        System.out.print("Enter the book name : ");
        String BookName = sc.nextLine();
        BufferedWriter writer = null;
        File file = new File("Book Categories\\" + BookCat + "\\" + BookName + ".txt");
        if (!file.exists()) {
            try {
                writer = new BufferedWriter(new FileWriter(file, true));
                writer.write("available");
                writer.newLine();
                writer.close();
                System.out.println("");
                System.out.println(BookName + " is added\n");
                t = "A new book (" + BookName + ") has been created";
                HeadLibrarian.Logger(t);
                System.out.println("");
                Librarian.LibrarianPage();
            } catch (Exception e) {
                System.out.println("Error category is not found");
            }
        } else {
            System.out.println(BookName + " already exists");
        }
        Librarian.LibrarianPage();

    }

    public static void ModifyBook() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the categories : ");
        String BookCat = sc.nextLine();
        ShowBooks(BookCat);
        System.out.println("Enter the book's old name to modify :");
        String oldName = sc.nextLine();
        File oldFile = new File("Book Categories\\" + BookCat + "\\" + oldName + ".txt");
        System.out.println("Enter the book's new name to modify");
        String newName = sc.nextLine();
        File newFile = new File("Book Categories\\" + BookCat + "\\" + newName + ".txt");
        boolean ModifyBook = oldFile.renameTo(newFile);
        if (ModifyBook) {
            System.out.println("File has been renamed");
            t = oldFile+" is renamed to "+newFile;
            HeadLibrarian.Logger(t);
        } else {
            System.out.println("Failed to renaming the file");
        }
        Librarian.LibrarianPage();
    }

    public static void DeleteBook(String BookCat) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the book's name to delete : ");
        String filepath = sc.nextLine();
        File file = new File("Book Categories\\" + BookCat + "\\" + filepath + ".txt");
        if (file.exists()) {
            try {

                file.delete();
                System.out.println("");
                System.out.println(filepath + " is deleted\n");
                t = "A book (" + filepath + ") has been deleted";
                HeadLibrarian.Logger(t);

            } catch (Exception e) {
                System.out.println("Error!!");
            }
        } else {
            System.out.println(filepath + " is not exists");
        }
        Librarian.LibrarianPage();
    }

    public static void SearchBook() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("-------------------- **Books Categories** --------------------");
        ShowBookCategories();
        System.out.println("Enter category of the book : ");
        String categ1 = sc.next();
        System.out.println("----------------------------- " + categ1 + " ------------------------------");
        ShowBooks(categ1);
        System.out.println("Enter title of the book you want to borrow : ");
        String buku = sc.nextLine();
    }

    public static void ShowBookCategories() {
        File categories = new File("Book Categories");
        String[] categ = categories.list();
        for (String names : categ) {
            System.out.println("-" + names);
        }
    }

    public static void ShowBooks(String categ) {
        try{
        File books = new File("Book Categories\\" + categ);
        String[] book = books.list();
        for (String boo : book) {
            System.out.println("-" + boo.substring(0, boo.length() - 4));
        }
        }catch(Exception e){
            System.out.println(e);
            
        }
    }

    public static void BorrowBook(String categ) {
        String status = "";
        Scanner jk = new Scanner(System.in);
        System.out.println("Enter title of the book you want to borrow : ");
        book = jk.nextLine();
        try {
            x = new Scanner(new File("Book Categories\\" + categ + "\\" + book + ".txt"));
            while (x.hasNextLine()) {
                status = x.nextLine();
            }
            if (status.equals("available")) {
                System.out.println("Successfully borrowed!!\n");
                statuschanger(categ);
                t = "A book (" + book + ") was borrowed";
                HeadLibrarian.Logger(t);
            } else {
                System.out.println("The book has been booked!\n");
            }
        } catch (Exception e) {
            System.out.println("error!");
        }
    }

    public static void BookKeeping(String categ, String bookName) throws IOException {
        try {
            z = new Formatter("Book Categories\\" + categ + "\\" + bookName + ".txt");
            z.format("%s", "available");
            System.out.println("Successfully returned\n");
            z.close();
        } catch (Exception e) {
            System.out.println("Error!");

        }
    }

    public static void statuschanger(String categ) {
        try {
            z = new Formatter("Book Categories\\" + categ + "\\" + book + ".txt");
            z.format("%s", "not available");
            System.out.println("Changed\n");
            z.close();
        } catch (Exception e) {
            System.out.println("Error!");

        }
    }

}
