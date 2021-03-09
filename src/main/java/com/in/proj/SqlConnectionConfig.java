package com.in.proj;

import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Scanner;

public class SqlConnectionConfig {

    public Connection connection() {

        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/playerregistration";
            String usename = "root";
            String password1 = "rini";

            Class.forName("com.mysql.cj.jdbc.Driver");
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
        int mobile_number = scanner1.nextInt();
        System.out.println("Enter  name:");
        Scanner scanner2 = new Scanner(System.in);
        String name = scanner2.next();
        System.out.println("Enter password");
        Scanner scanner3 = new Scanner(System.in);
        String password = scanner3.next();
        System.out.print("Enter the amount to add:");
        Scanner scanner5 = new Scanner(System.in);
        int amount = scanner5.nextInt();

        String sql = "INSERT INTO registration (mobile_number,name, password,amount) VALUES (?, ?, ?,?)";
        PreparedStatement insertStatement = connection().prepareStatement(sql);
        insertStatement.setInt(1, mobile_number);
        insertStatement.setString(2, name);
        insertStatement.setString(3, password);
        insertStatement.setInt(4, amount);
        int rowsInserted = insertStatement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("Inserted");
        }
    }


    public void loginData() throws SQLException {
        Gameoptions gameoptions=new Gameoptions();
        System.out.println("Enter mobile number:");
        Scanner scanner5 = new Scanner(System.in);
       int mobilenumber = scanner5.nextInt();

        System.out.println("Enter password:");
        Scanner scanner6 = new Scanner(System.in);
        String password = scanner6.next();
        String queryCheck = "SELECT * from registration WHERE mobile_number='" + mobilenumber + "'";
        Statement st = connection().createStatement();
        ResultSet rs = st.executeQuery(queryCheck); // execute the query, and get a java resultset


        if (rs.absolute(1)) {
            System.out.println("Logged succesfully....");

            int gl = rs.getInt("gamelevel");
            int am = rs.getInt("amount");
            String gd = rs.getString("gamemode");


            if (gd.equals("F")) {



                System.out.println("Current Login level " + gl);
                if (gl < 10 ) {

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

                    System.out.println("Balance Low , Please recharge " + finas);
                    System.out.println("Limit  " + limit);
                    if (am < limit) {
                        System.out.println("Balance Low , Please recharge ");
                    } else {
                        gameoptions.gameSelection(mobilenumber, gl,gd);
                    }
                } else {
                    System.out.println("Game Over...........");
                }
            }
            else
            {
                System.out.println("Current Login level " + gl);
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
                    System.out.println("Limit  " + limit);
                    if (am < limit) {
                        System.out.println("Balance Low , Please recharge ");
                    } else {
                        gameoptions.gameSelection(mobilenumber, gl ,gd);
                    }
                } else {
                    System.out.println("Game Over...........");
                }
            }
        }else {
            System.out.println("Not registered...........");
        }
//return mobilenumber;
    }
    public void addMoney() throws SQLException {
        System.out.println("Enter mobilenumber:");
        Scanner scanner8 = new Scanner(System.in);
        int mob=scanner8.nextInt();
        System.out.println("Enter the amount:");
        Scanner scanner7 = new Scanner(System.in);
        int newamount = scanner7.nextInt();

        String querytoADD = "UPDATE registration "
                + "SET amount = amount + '" + newamount + "'"
                + "WHERE mobile_number='" + mob + "'";



        Statement st1 = connection().createStatement();
        st1.executeUpdate(querytoADD); // execute the query, and get a java resultset
    }

    public DayOfWeek getDayNumberOld() {
        LocalDate date = LocalDate.now();
        DayOfWeek day = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return day;
        }
    }









