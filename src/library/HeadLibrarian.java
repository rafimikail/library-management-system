/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author kayva
 */
public class HeadLibrarian {
    
    private static Scanner y;

    public static void HeadLibrarianPage() throws IOException {
        System.out.println("Welcome to the main page of the Head Librarian");
        System.out.println("1. Generate report");
        System.out.println("2. Log out");
        System.out.print("Your choice : ");
        Scanner s = new Scanner(System.in);
        int home = s.nextInt();
        switch (home) {
            case 1:
                GenReport();
                System.out.println("");
                HeadLibrarianPage();
                break;
            case 2:
                Library.LoginPage();
                break;
            default:
                System.out.println("Error!! Please enter the correct choice");
                break;
        }

    }

    public static void Logger(String t) throws IOException {

        try (PrintWriter output = new PrintWriter(new FileWriter("logger.txt", true))) {
            output.println(t);
            output.close();
        } catch (Exception e) {
        }

    }

    public static void GenReport() throws IOException {
       
    Scanner y = new Scanner(new File("logger.txt"));
    while(y.hasNextLine()){
        System.out.println(y.nextLine());
    }
    }
}
