/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Klijent;
import domain.Zaposleni;
import forms.KlijentPromeniObrisi;
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
public class KlijentPromeniObrisiController {
    private final KlijentPromeniObrisi view;

    private Zaposleni zaposleni;
    private Klijent ucitan;
    
    public KlijentPromeniObrisiController(KlijentPromeniObrisi view) {
        this.view = view;
        addActionListener();
    }

    public void otvoriFormu(Klijent k) {
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
        ucitan = k;
        
        view.getjTextIme().setText(ucitan.getIme());
        view.getjTextPrezime().setText(ucitan.getPrezime());
        view.getjTextBrojTelefona().setText(ucitan.getBrojTelefona());
        view.getjTextImejl().setText(ucitan.getImejl());
        view.getjCalendarComboBox1().setDate(ucitan.getDatumRodjenja());
        
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

    private void addActionListener() {
        view.izmeniKlijentaAddActionListener(e -> izmeniKlijenta());
        view.obrisiKlijentaAddActionListener(e -> obrisiKlijenta());
        
        
    }

    private void izmeniKlijenta() {
        String ime = view.getjTextIme().getText();
        String prezime = view.getjTextPrezime().getText();
        String brojTelefona = view.getjTextBrojTelefona().getText().trim();
        String imejl = view.getjTextImejl().getText();
        Date datumRodjenja = view.getjCalendarComboBox1().getDate();
        
        Klijent k = new Klijent(ime,prezime,imejl,brojTelefona,datumRodjenja, zaposleni);
        
        try {
            String rez = Komunikacija.getInstance().promeniKlijent(k);
            JOptionPane.showMessageDialog(view, rez);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
        
     
    }

    private void obrisiKlijenta() {
        
        String ime = view.getjTextIme().getText();
        String prezime = view.getjTextPrezime().getText();
        String brojTelefona = view.getjTextBrojTelefona().getText().trim();
        String imejl = view.getjTextImejl().getText();
        Date datumRodjenja = view.getjCalendarComboBox1().getDate();
        
        Klijent k = new Klijent(ucitan.getIdKlijent(),ime,prezime,imejl,brojTelefona,datumRodjenja, zaposleni);
        
        try {
            String rez = Komunikacija.getInstance().obrisiKlijenta(k);
            JOptionPane.showMessageDialog(view, rez);
            view.dispose();
            Koordinator.getInstance().otvoriKlijentPretragaFormu();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
        
    }
    
    
}
