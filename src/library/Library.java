/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.File;
import java.io.IOException;
import java.time.Clock;
import java.time.Instant;
import java.util.Scanner;

/**
 *
 * @author kayva
 */
public class Library {

    private static Scanner x;
    public static String filepath123;
    public static String user2;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        LoginPage();
    }

    public static void LoginPage() throws IOException {
        
        Clock realClock = Clock.systemDefaultZone();
        Instant instant = realClock.instant();
        
        
        Scanner s = new Scanner(System.in);
        String filepath;
        String user1,  user3;
        String pw1, pw2, pw3;
        System.out.println("-------------------- Welcome To Library Management System --------------------");
        System.out.println("Login as :\n1.Head Librarian\n2.Librarian\n3.Member\n4.Exit");
        System.out.print("Your choice : ");
        int as = s.nextInt();
        switch (as) {
            case 1:
                System.out.println("------------------------------------------------------------------------------");
                System.out.print("Username : ");
                user1 = s.next();
                System.out.print("Password : ");
                pw1 = s.next();
                filepath = user1 + ".txt";
                if (verifyLoginHeadLibrarian(user1, pw1, filepath)) {
                    HeadLibrarian.HeadLibrarianPage();
                    String headlibrarianlog = user1 + " just logged in as a Head Librarian";
                    HeadLibrarian.Logger(headlibrarianlog);
                } else {
                    System.out.println("your username or password is incorrect");
                    LoginPage();
                }
                break;

            case 2:
                System.out.println("------------------------------------------------------------------------------");
                System.out.print("Username : ");
                user2 = s.next();
                System.out.print("Password : ");
                pw2 = s.next();
                filepath = user2 + ".txt";
                if (verifyLoginLibrarian(user2, pw2, filepath)) {
                    Librarian.LibrarianPage();
                    String librarianlog = user2 + " just log in in as a Librarian on "+instant;
                    HeadLibrarian.Logger(librarianlog);
                } else {
                    System.out.println("Your username or password is incorrect");
                    LoginPage();
                }

                break;

            case 3:
                System.out.println("------------------------------------------------------------------------------");
                System.out.print("Username : ");
                user3 = s.next();
                System.out.print("Password : ");
                pw3 = s.next();
                filepath = user3 + ".txt";
                if (verifyLoginMember(user3, pw3, filepath)) {
                    Members.MemberPage(user3,pw3,filepath);
                    String memberlog = user3 + " just logged in as a Member";
                    HeadLibrarian.Logger(memberlog);
                } else {
                    System.out.println("Username or Password is incorrect!");
                    LoginPage();
                }
                break;
                
            case 4:
                exit();
                break;
            default :
                System.out.println("Please enter the correct choice ");
                LoginPage();
                break;
        }
        

    }

    public static boolean verifyLoginMember(String user, String pw, String filepath) {
        boolean found = false;
        String tempPassword = "";

        try {
            x = new Scanner(new File("Login\\Members\\" + filepath));
            while (x.hasNext()) {
                tempPassword = x.next();
                if (tempPassword.equals(pw)) {
                    found = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");

        }
        return found;
    }

    //not Finished
    public static boolean verifyLoginLibrarian(String user, String pw, String filepath) {
        boolean found = false;
        String tempPassword = "";

        try {
            x = new Scanner(new File("Login\\Librarian\\" + filepath));
            while (x.hasNext()) {
                tempPassword = x.next();
                if (tempPassword.equals(pw)) {
                    found = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }

        return found;
    }

    public static boolean verifyLoginHeadLibrarian(String user, String pw, String filepath) {
        boolean found = false;
        String tempPassword = "";
        try {
            x = new Scanner(new File("Login\\Head Librarian\\" + filepath));
            while (x.hasNext()) {
                tempPassword = x.next();
                if (tempPassword.equals(pw)) {
                    found = true;
                }
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        return found;
    }
    public static String User2(){
        String username = user2;
        return username;
    }
    
    public static void exit(){
        
    }
    
   

 

}
