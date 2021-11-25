package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String dbURL = "jdbc:mysql://" + "localhost" + ":3306/" + "user" +
            "?verifyServerCertificate=false" + "&useSSL=false" +
            "&requireSSL=false" + "&useLegacyDatetimeCode=false" +
            "&amp" + "&serverTimezone=UTC";;
    private static String dbUserName = "root";
    private static String password = "daTA256my!!";


    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbURL, dbUserName,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
