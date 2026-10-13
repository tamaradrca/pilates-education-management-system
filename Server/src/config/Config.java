/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author tamaradrca
 */
public class Config {
       private static Config instance;
    private Properties configuration;
    
    private final String putanjaDoFajla = "config/config.properties";

    private Config() throws IOException {
        try {
            configuration = new Properties();
            configuration.load(new FileInputStream(putanjaDoFajla));
        } catch (FileNotFoundException ex) {
            System.out.println("GREŠKA: Konfiguracioni fajl nije pronađen na putanji: " + putanjaDoFajla);
        } catch (IOException ex) {
            System.out.println("GREŠKA pri učitavanju konfiguracije.");
        }
    }

    public static Config getInstance() throws IOException {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    
    public String getProperty(String key) {
        return configuration.getProperty(key, "n/a");
    }
}
