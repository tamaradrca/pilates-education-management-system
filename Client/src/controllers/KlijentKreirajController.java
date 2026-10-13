/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Klijent;
import domain.Zaposleni;
import forms.KlijentKreiraj;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author tamaradrca
 */
public class KlijentKreirajController {
    
    private final KlijentKreiraj view;
    
    private Zaposleni zaposleni;

    public KlijentKreirajController(KlijentKreiraj view) {
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
        Koordinator.getInstance().otvoriInstruktoriFormu();
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
        
    private void addActionListener(){
        view.kreirajKlijentaAddActionListener(e -> kreirajKlijenta());
    }

    private void kreirajKlijenta() {
        
        String ime = view.getjTextIme().getText();
        String prezime = view.getjTextPrezime().getText();
        String brojTelefona = view.getjTextBrojTelefona().getText().trim();
        String imejl = view.getjTextImejl().getText();
        Date datumRodjenja = view.getjCalendarComboBox1().getDate();
        
        Klijent k = new Klijent(ime,prezime,imejl,brojTelefona,datumRodjenja, zaposleni);
     
        try {
            String rez = Komunikacija.getInstance().kreirajKlijent(k);
            JOptionPane.showMessageDialog(view, rez);
            view.getjTextIme().setText("");
            view.getjTextPrezime().setText("");
            view.getjTextBrojTelefona().setText("");
            view.getjTextImejl().setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }

    }
    
}
