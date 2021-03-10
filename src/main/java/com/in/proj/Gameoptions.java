package com.in.proj;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Gameoptions {

    public void gameSelection(long mob ,int gl ,String gd) throws SQLException {
        SqlConnectionConfig connectionConfig=new SqlConnectionConfig();
        //System.out.println("Value inside game select " +mob);
        //creating object for Gameoptions class
        Gameoptions game=new Gameoptions();
        //variable for holding game level
        int nextg;
       // System.out.println("Value inside game Mode" +gd);
        if (gl >0 ) {
            if (gd.equals("F"))
            {
               // System.out.println("IF " +gd);
                 nextg = gl + 1;
            }
            else
            {
                nextg = gl - 1;
                System.out.println("else " +gd);
            }

            System.out.println("You have Played " +gl + "  Next Game Level is " +nextg);
            game.option2(mob,nextg);
        }
        else
        {
        System.out.println("Select the game Mode - Press \"F \" For Level 1 to 10 . Press \"B\" for  10 to 1 ");
        Scanner g=new Scanner(System.in);
        String gameoption=g.next();
            //query to update the game mode
            String querytoADD = "UPDATE playerdetails "
                    + "SET gamemode ='" + gameoption + "'"
                    + "WHERE mobile_number='" + mob + "'";

            Statement st1 = connectionConfig.connection().createStatement();
            // execute the query, and get a java resultset
            st1.executeUpdate(querytoADD);


        switch (gameoption){
            case "F":
                System.out.println("Play game starting from GAME 1");
                game.option2(mob,1);
                break;
            case "B":
                System.out.println("Play game starting from GAME 10");
                game.option2(mob,10);
                break;
            default:
                System.out.println("Invalid");
        }}

    }
    public void option2(long mob, int nextg) throws SQLException {
        GamePage gamePage=new GamePage();
        System.out.println("Enter S to START");
        Scanner inp =new Scanner(System.in);
        String select=inp.next();
        System.out.println("Starting......");

        System.out.println("Enter E to END");
        Scanner innewp =new Scanner(System.in);
        String seend=innewp.next();

            System.out.println("ENDING......");
            gamePage.endGame( mob,nextg);

    }
    }


