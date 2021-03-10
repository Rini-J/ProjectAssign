package com.in.proj;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.DayOfWeek;

public class NextGamePage {
    /*
    Enable user to continue the game after finishing one game
    @Param mobilenumber -mobile number of player
     */
    public void toContinue(long mobilenumber) throws SQLException {

        //System.out.println("Inside Contineu " +mobilenumber);
        Gameoptions gameoptions=new Gameoptions();
        SqlConnectionConfig connectionConfig=new SqlConnectionConfig();
        String balCheck = "SELECT * from playerdetails WHERE mobile_number='" + mobilenumber + "'";
        Statement newst = connectionConfig.connection().createStatement();
        ResultSet newrs = newst.executeQuery(balCheck); // execute the query, and get a java resultset


        if (newrs.absolute(1)) {

            int gl = newrs.getInt("gamelevel");
            int am = newrs.getInt("amount");
            String gd = newrs.getString("gamemode");
            //System.out.println("Current Login level " + gl);

            if (gd.equals("F")) {



                //System.out.println("Current Login level " + gl);
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

                    //System.out.println("Balance Low , Please recharge " + finas);
                    //System.out.println("Limit  " + limit);
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
                //System.out.println("Current Login level " + gl);
                if (gl > 0) {

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
                        gameoptions.gameSelection(mobilenumber, gl ,gd);
                    }
                } else {
                    System.out.println("Game Over...........");
                }
            }
        }
            else{
                System.out.println("Not registered...........");
            }
        }}

