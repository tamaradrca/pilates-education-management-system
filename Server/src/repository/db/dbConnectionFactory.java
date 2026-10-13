/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import config.Config;


/**
 *
 * @author tamaradrca
 */
public class dbConnectionFactory {

    private static dbConnectionFactory instance;
    private Connection connection;

    public dbConnectionFactory() {

        try {
            if (connection == null || connection.isClosed()) {
                String url = Config.getInstance().getProperty("url");
                String user = Config.getInstance().getProperty("username");
                String pass = Config.getInstance().getProperty("password");
                connection = DriverManager.getConnection(url, user, pass);
                connection.setAutoCommit(false);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(dbConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static dbConnectionFactory getInstance() {
        if (instance == null) {
            instance = new dbConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
