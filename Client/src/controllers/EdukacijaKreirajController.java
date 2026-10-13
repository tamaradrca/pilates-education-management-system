/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Edukacija;
import domain.Instruktor;
import domain.Zaposleni;
import forms.EdukacijaKreiraj;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;

/**
 *
 * @author tamaradrca
 */
public class EdukacijaKreirajController {
    
    private final EdukacijaKreiraj view;

    private Zaposleni zaposleni;
    private List<Instruktor> listaInstruktora = new ArrayList<>();
    
    public EdukacijaKreirajController(EdukacijaKreiraj view) {
        this.view = view;
        addActionListener();
        
    }
    
    public void otvoriFormu(){
        this.zaposleni = Komunikacija.getInstance().getUlogovani();
        view.getjLabelUlogovanZaposleni().setText(zaposleni.getIme() + " " + zaposleni.getPrezime());
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        popuniInstruktore();
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
        Koordinator.getInstance().otvoriKlijentFormu();
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
        view.kreirajEdukacijuAddActionListener(e -> kreirajEdukacija());
    }

    private void kreirajEdukacija() {
        
        String naziv = view.getjTextNazivEdukacije().getText().trim();
        String opis = view.getjTextAreaOpisEdukacije().getText();
        double cena=0;
        int brojCasova=0;
        try{
         cena = Integer.parseInt(view.getjTextCenaEdukacije().getText());
        brojCasova = Integer.parseInt(view.getjTextBrojCasova().getText());
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(view, "Unesite broj");
            return;
        }
        Zaposleni z = Komunikacija.getInstance().getUlogovani();
        Instruktor i = (Instruktor) view.getjComboBoxListaInstruktora().getSelectedItem();
        
        if (naziv.isEmpty() || opis.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Potrebno je uneti sve potrebne informacije!");
            return;

        }
        
        Edukacija e = new Edukacija(naziv,opis,cena,brojCasova,z,i);
        
        try {
            String rez = Komunikacija.getInstance().kreirajEdukaciju(e);
            JOptionPane.showMessageDialog(view, rez);
            view.getjTextNazivEdukacije().setText("");
            view.getjTextAreaOpisEdukacije().setText("");
            view.getjTextCenaEdukacije().setText("");
            view.getjTextBrojCasova().setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
        
    }

    private void popuniInstruktore() {
        try {
            this.listaInstruktora = Komunikacija.getInstance().vratiListuInstruktora();
            view.getjComboBoxListaInstruktora().removeAllItems();
            for(Instruktor i : listaInstruktora){
                view.getjComboBoxListaInstruktora().addItem(i);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

   
    
    
    
}
