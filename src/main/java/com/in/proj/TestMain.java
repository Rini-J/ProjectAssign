package com.in.proj;

import java.sql.SQLException;
import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) throws SQLException {
        SqlConnectionConfig sqlConnectionConfig=new SqlConnectionConfig();

        System.out.println("Enter the options...");
        System.out.println("Enter 1 for REGISTRATION");
        System.out.println("Enter 2 for LOGIN");
        System.out.print("Enter 3 for Adding Money to your account");
        Scanner scanner = new Scanner(System.in);
        int Option = scanner.nextInt();
        Formpage formpage = new Formpage();
        Gameoptions gameoptions = new Gameoptions();
        GamePage gamePage = new GamePage();
        switch (Option) {
            case 1:
                System.out.println("Register Player");
                formpage.registerPlayer();
                break;
            case 2:
                System.out.println("Login");
                formpage.loginPlayer();




                break;
            case 3:
                System.out.println("AddMoney");
                formpage.addMoneytoAcc();

                break;
            default:
                System.out.print("Invalid");

        }


    }
}
