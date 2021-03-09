package com.in.proj;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Formpage {
    SqlConnectionConfig sqlConnectionConfig = new SqlConnectionConfig();

    public void registerPlayer() throws SQLException {


        sqlConnectionConfig.insertRegistrationData();
    }

    public void loginPlayer() throws SQLException {
        sqlConnectionConfig.loginData();
    }

    public void addMoneytoAcc() throws SQLException {
        sqlConnectionConfig.addMoney();
    }
}
