/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Klijent;
import forms.KlijentPretraga;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import tableModeli.ModelTabeleKlijenti;

/**
 *
 * @author tamaradrca
 */
public class KlijentPretragaController {
    
    private final KlijentPretraga view;
    
    private ModelTabeleKlijenti model;
    private List<Klijent> listaKlijenata;

    public KlijentPretragaController(KlijentPretraga view) {
        this.view = view;
        addActionListener();
    }
    
    public void otvoriFormu(){
        
        vratiKlijente();
        
  
        view.getjTableKlijenti().setBackground(new Color(204,255,255));
        view.getjTableKlijenti().setFont(new Font("Hiragino Mincho ProN", Font.BOLD,12));
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
        Koordinator.getInstance().otvoriEdukacijaKreirajFormu();
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
        view.pretragaAddActionListener(e -> pretragaKlijenta());
        view.ucitajAddActionListener(e -> ucitajKlijenta());
    }

   

    private void ucitajKlijenta() {
        
        if(view.getjTableKlijenti().getSelectedRow()==-1){
            JOptionPane.showMessageDialog(view, "Sistem ne moze da ucita klijenta");
            return;
        }
        Klijent u = model.getKlijentAt(view.getjTableKlijenti().getSelectedRow());
        
        try {
            Klijent ucitana = Komunikacija.getInstance().ucitajKlijenta(u);
            JOptionPane.showMessageDialog(view, "Sistem je ucitao klijenta");
            view.dispose();
            Koordinator.getInstance().otvoriKlijentPromeniObrisiFormu(ucitana);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
        
        
    }

    private void pretragaKlijenta() {
        String ime = view.getjTextPretragaImeKlijenta().getText();
        
        if(ime.isEmpty()){
          JOptionPane.showMessageDialog(view, "Unesite ime");
          model.setListaKlijenata(listaKlijenata);
          return;
          
        }
        try {
            List<Klijent> l = Komunikacija.getInstance().pretragaKlijent(ime);
            model.setListaKlijenata(l);
            JOptionPane.showMessageDialog(view, "Sistem je nasao klijente po zadatoj vrednosti");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void vratiKlijente() {
        try {
            this.listaKlijenata = Komunikacija.getInstance().vratiListuKlijenata();
            model = new ModelTabeleKlijenti(listaKlijenata);
            view.getjTableKlijenti().setModel(model);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
    
}
