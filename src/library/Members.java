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
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author kayva
 */
public class Members {

    private static Scanner x;
    private static String secondline;
    private static Formatter z;

    public static void MemberPage(String user, String pw, String filepath) throws IOException {

        System.out.println("Welcome the main page of Members");
        System.out.println("1. Update Profile");
        System.out.println("2. Search for Books");
        System.out.println("3. Renew membership");
        System.out.println("4. Log out");
        System.out.print("Your choice : ");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("-------------------- **Update Profile** --------------------");
                System.out.println("Enter your new password : ");
                String newpw = sc.next();
                
                profileupdater(newpw,filepath);

                MemberPage(user, pw, filepath);
                break;
            case 2:
                System.out.println("-------------------- **Books Categories** --------------------");
                Book.ShowBookCategories();
                System.out.println("Enter category of the book : ");
                String categ = sc.next();
                System.out.println("----------------------------- " + categ + " ------------------------------");
                Book.ShowBooks(categ);
                Book.BorrowBook(categ);

                MemberPage(user, pw, filepath);
                break;
            case 3:
                System.out.print("Your account now is valid until : ");
                ShowValidity(filepath);
                
                System.out.printf("\nExtend validity until : \nDate : ");
                int date = sc.nextInt();
                System.out.print("Month : ");
                int month = sc.nextInt();
                System.out.print("Year : ");
                int year = sc.nextInt();
                RenewMembership(filepath, date, month, year, pw);

                MemberPage(user, pw, filepath);
                break;
            case 4:
                Library.LoginPage();
                break;
            default:
                System.out.println("Error!! Please enter the correct choice");
                MemberPage(user, pw, filepath);
                break;
        }

    }

//    public static boolean UpdateProfile(String user, String pw, String newpw, String oldpw, String filepath) {
//        boolean change = false;
//        if (pw.equals(oldpw)) {
//            try {
//                PrintWriter write = new PrintWriter("Login\\Members\\" + filepath);
//                checkvalidity(filepath);
//                write.println(newpw);
//                write.close();
//                System.out.println("Password has changed!");
//                change = true;
//            } catch (Exception e) {
//                System.out.println("Password has not changed!");
//            }
//            return change;
//        } else {
//            System.out.println("Password Incorrect");
//            return change;
//        }
//    }
     public static void profileupdater(String newpw, String filepath){
        
        
        try{
            z = new Formatter("Login\\Members\\"+filepath);
            z.format("%s\n%s", newpw,secondline);
            System.out.println("Success!");
            z.close();
        }catch(Exception e){
            System.out.println("Error!");
            
        }
            
        
        
    }

    

    public static void ShowValidity(String filepath) {
        File coba = new File("Login\\Members\\" + filepath);
        String firstline = "";
        secondline = "";
        try {
            Scanner a = new Scanner(coba);
            while (a.hasNextLine()) {
                firstline = a.nextLine();
                secondline = a.nextLine();
                System.out.println(secondline);
            }
        } catch (Exception e) {
            System.out.println("Error!");
        }

    }

    public static void RenewMembership(String filepath, int date,int month,int year, String pw) {
        try {
            z = new Formatter("Login\\Members\\" + filepath);
            z.format("%s\n%s", pw,date+"/"+month+"/"+year);
            System.out.println("Success!");
            z.close();
        } catch (Exception e) {
            System.out.println("Error!");
        }
    }

}
