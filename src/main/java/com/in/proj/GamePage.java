package com.in.proj;

import java.sql.SQLException;
import java.sql.Statement;

public class GamePage {
    SqlConnectionConfig sq=new SqlConnectionConfig();

    public void startGame(){
        System.out.print("Game started........");

    }
    public void endGame(int mob,int nextg) throws SQLException {
NextGamePage nextgame = new NextGamePage();
        System.out.println("inside endg game for number :" +mob);



       // int  mn=sq.loginData();
       // System.out.print(mn);
        //long mn=987;
        String querytoEND = "UPDATE registration "
                + "SET amount = amount - '10'" + ", gamelevel ='" + nextg + "'"
                + "WHERE mobile_number='" + mob + "'" ;
        Statement game = sq.connection().createStatement();
        game.executeUpdate(querytoEND);
        nextgame.toContinue(mob);
    }
}
