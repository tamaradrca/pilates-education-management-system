/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Instruktor;
import domain.Zaposleni;
import forms.InstruktorKreiraj;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author tamaradrca
 */
public class InstruktorKreirajController {
    private final InstruktorKreiraj view;
    private Zaposleni zaposleni;

    public InstruktorKreirajController(InstruktorKreiraj view) {
        this.view = view;
        addActionListener();
    }
    
    public void otvoriFormu(){
        this.zaposleni = Komunikacija.getInstance().getUlogovani();
        view.getjLabelUlogovanZaposleni().setText(zaposleni.getIme() + " " + zaposleni.getPrezime());
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        
        view.addMenuActionListener(
                e -> edukacije(),
                e -> klijenti(),
                e -> grupe(),
                e -> ins(),
                e -> odjava()
        );

        
    }
        
    private void edukacije(){
        Koordinator.getInstance().otvoriEdukacijaPretragaFormu();
        view.dispose();
    }
    private void klijenti(){
        Koordinator.getInstance().otvoriKlijentPretragaFormu();
        view.dispose();
                
    }
    private void grupe(){
        Koordinator.getInstance().otvoriGrupeFormu();
        view.dispose();
    }
    private void ins(){
        Koordinator.getInstance().otvoriInstruktorPretragaFormu();
        view.dispose();
    }
    private void odjava(){
         try {
            String rez = Komunikacija.getInstance().odjaviZap();
            JOptionPane.showMessageDialog(view, rez);
        } catch (Exception ex) {
            Logger.getLogger(EdukacijaKreirajController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.dispose();
    }

    private void addActionListener() {
        view.kreirajInstruktoraAddActionListener(e -> kreirajInstruktora());
    }

    private void kreirajInstruktora() {
        String ime = view.getjTextIme().getText();
        String prezime = view.getjTextPrezime().getText();
        String imjel = view.getjTextImejl().getText();
        
        Instruktor i = new Instruktor(ime,prezime,imjel);
        
        try {
            String rez = Komunikacija.getInstance().kreirajIns(i);
            JOptionPane.showMessageDialog(view, rez);
        } catch (Exception ex) {
            Logger.getLogger(InstruktorKreirajController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
