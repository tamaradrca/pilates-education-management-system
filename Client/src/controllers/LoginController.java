/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Zaposleni;
import forms.LogIn;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author tamaradrca
 */
public class LoginController {
    
    private final LogIn lf;

    public LoginController(LogIn lf) {
        this.lf = lf;
        addActionListener();
    }
    
    public void otvoriFormu(){
        lf.setLocationRelativeTo(null);
        lf.setVisible(true);
    }
    
    private void addActionListener() {
        lf.loginAddActionListener(e -> login());
    }
    
    private void login(){
        String imejl = lf.getjTextEMAIL().getText().trim();
        String lozinka = new String(lf.getjLOZINKA().getPassword());
        
        if (imejl.isEmpty() || lozinka.isEmpty()) {
            JOptionPane.showMessageDialog(lf, "Sva polja moraju biti popunjena!", "Upozorenje", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            Zaposleni z = Komunikacija.getInstance().login(imejl, lozinka);

            JOptionPane.showMessageDialog(lf, "Uspešno ste se prijavili na sistem.");
            
            lf.dispose();
            Koordinator.getInstance().otvoriEdukacijaKreirajFormu();

        } catch (Exception ex) {
            lf.getjTextEMAIL().setText("");
            
            JOptionPane.showMessageDialog(lf, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
