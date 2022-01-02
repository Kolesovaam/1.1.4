package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String dbURL = "jdbc:mysql://" + "localhost" + ":3306/" + "user" +
            "?verifyServerCertificate=false" + "&useSSL=false" +
            "&requireSSL=false" + "&useLegacyDatetimeCode=false" +
            "&amp" + "&serverTimezone=UTC";
    private static String dbUserName = "root";
    private static String password = "daTA256my!!";
    private static String dbDriver = "com.mysql.jdbc.Driver";
    private static String dbDialect = "org.hibernate.dialect.MySQLDialect";


    private static Connection connection = null;
    private static SessionFactory sessionFactory;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(dbURL, dbUserName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration()
                    .setProperty("connection.driver_class", dbDriver)
                    .setProperty("hibernate.connection.url",dbURL)
                    .setProperty("hibernate.connection.username", dbUserName)
                    .setProperty("hibernate.connection.password", password)
                    .setProperty("hibernate.dialect", dbDialect)
                    .setProperty("hibernate.show_sql", "true")
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Исключение!" + e);
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if(connection != null){
            try {
                getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(sessionFactory!= null){
            getSessionFactory().close();
        }
    }
}
