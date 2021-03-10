package com.in.proj;

import java.sql.SQLException;
import java.sql.Statement;

public class GamePage {
    SqlConnectionConfig sq = new SqlConnectionConfig();

    public void startGame() {
        System.out.print("Game started........");

    }

    /*
     Updates the balance amount and game level of the player after each game
     @Param mobilenumber -Mobile number of player
     @Param nextg-nextgame level

    */
    public void endGame(long mob, int nextg) throws SQLException {
        NextGamePage nextgame = new NextGamePage();
        //System.out.println("inside endg game for number :" + mob);

        String querytoEND = "UPDATE playerdetails "
                + "SET amount = amount - '10'" + ", gamelevel ='" + nextg + "'"
                + "WHERE mobile_number='" + mob + "'";
        Statement game = sq.connection().createStatement();
        game.executeUpdate(querytoEND);
        nextgame.toContinue(mob);
    }
}
