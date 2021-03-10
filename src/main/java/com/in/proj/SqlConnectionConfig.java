package com.in.proj;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Scanner;

public class SqlConnectionConfig {

    public Connection connection() {
        //create connection object
        Connection con = null;
        try {
            //database url
            String url = "jdbc:mysql://localhost:3306/playerregistration";
            //database credentials
            String usename = "root";
            String password1 = "rini";

            //registering jdbc driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //opening a connection
            con = DriverManager.getConnection(url, usename, password1);
            if (con != null) {
                System.out.println("Connected to database...........");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void insertRegistrationData() throws SQLException {
        System.out.println("Enter mobilenumber:");
        Scanner scanner1 = new Scanner(System.in);
        long mobile_number = scanner1.nextLong();
        System.out.println("Enter  name:");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.next();
        System.out.println("Enter PIN");
        Scanner scanner3 = new Scanner(System.in);
        String password = scanner3.next();
        System.out.print("Enter the amount to add:");
        Scanner scanner5 = new Scanner(System.in);
        long amount = scanner5.nextLong();

        //insert query
        String sql = "INSERT INTO playerdetails (mobile_number,name, password,amount,gamemode,gamelevel) VALUES (?, ?, ?,?,'N','0')";
        PreparedStatement insertStatement = connection().prepareStatement(sql);
        insertStatement.setLong(1, mobile_number);
        insertStatement.setString(2, name);
        insertStatement.setString(3, password);
        insertStatement.setLong(4, amount);
        int rowsInserted = insertStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Player successfully Registered ");
        }
    }


   /*
   Method to collect and check the login credentials
   This will check whether an user is previously registered or not using the mobile number entered.
    */
    public void loginData() throws SQLException {
        Gameoptions gameoptions = new Gameoptions();
        System.out.println("Enter mobile number:");
        Scanner scanner5 = new Scanner(System.in);
        long mobilenumber = scanner5.nextLong();

        System.out.println("Enter password:");
        Scanner scanner6 = new Scanner(System.in);
        String password = scanner6.next();
        //query to check whether the entered mobile number is there in the databse.
        String queryCheck = "SELECT * from playerdetails WHERE mobile_number='" + mobilenumber + "'"
                + "and password='" + password + "'";
        Statement st = connection().createStatement();
        //obtaining resultset after executing the query
        ResultSet rs = st.executeQuery(queryCheck);


        if (rs.absolute(1)) {
            System.out.println("Logged succesfully....");

            //variable to store the level of game
            int gl = rs.getInt("gamelevel");
            //variable to store the balance amount
            int am = rs.getInt("amount");
            //variable to store the game mode ,whether it is in forward direction or backward.
           String gd = rs.getString("gamemode");

            System.out.println("Current Account Balance is  " + am);

            if (gd.equals("F")) {


               // System.out.println("Current Login level " + gl);
                if (gl < 10) {

                    //creating object for SqlConnectionConfig class
                    SqlConnectionConfig ds = new SqlConnectionConfig();
                    //variable to store day of week
                    DayOfWeek finas = ds.getDayNumberOld();
                    int limit = 0;
                    //setting minimum balance as 20 for weekends and 10 for working days
                    switch (finas) {
                        case SATURDAY:
                            limit = 20;
                        case SUNDAY:
                            limit = 20;
                        default:
                            limit = 10;
                    }

                   // System.out.println("Balance Low , Please recharge " + finas);
                    //System.out.println("Limit  " + limit);
                    if (am < limit) {
                        System.out.println("Balance Low , Please recharge ");
                    } else {
                        gameoptions.gameSelection(mobilenumber, gl, gd);
                    }
                } else {
                    System.out.println("Game Over...........");
                }
            } else {
               // System.out.println("Current Login level " + gl);
                if (gl >= 0) {

                    SqlConnectionConfig ds = new SqlConnectionConfig();
                    DayOfWeek finas = ds.getDayNumberOld();
                    int limit = 0;
                    switch (finas) {
                        case SATURDAY:
                            limit = 20;
                        case SUNDAY:
                            limit = 20;
                        default:
                            limit = 10;
                    }

                    //System.out.println("Balance Low , Please recharge " + finas);
                    //System.out.println("Limit  " + limit);
                    if (am < limit) {
                        System.out.println("Balance Low , Please recharge ");
                    } else {
                        gameoptions.gameSelection(mobilenumber, gl, gd);
                    }
                } else {
                    System.out.println("Game Over...........");
                }
            }
        } else {
            System.out.println("Not registered...........");
        }

    }

   //method to recharge the user account
    public void addMoney() throws SQLException {
        System.out.println("Enter mobilenumber:");
        Scanner scanner8 = new Scanner(System.in);
        long mob = scanner8.nextLong();
        System.out.println("Enter the amount:");
        Scanner scanner7 = new Scanner(System.in);
        int newamount = scanner7.nextInt();

        String querytoADD = "UPDATE playerdetails "
                + "SET amount = amount + '" + newamount + "'"
                + "WHERE mobile_number='" + mob + "'";


        Statement st1 = connection().createStatement();
        st1.executeUpdate(querytoADD); // execute the query, and get a java resultset
    }

    public DayOfWeek getDayNumberOld() {
        //display the current date
        LocalDate date = LocalDate.now();
        //getting day of week from current date
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return day;
    }
}









