/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Edukacija;
import domain.Instruktor;
import forms.EdukacijaPretraga;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import tableModeli.ModelTabeleEdukacije;

/**
 *
 * @author tamaradrca
 */
public class EdukacijaPretragaController {
    
    private final EdukacijaPretraga view;
    
    private ModelTabeleEdukacije model;
    private List<Edukacija> listaEdukacija;

    public EdukacijaPretragaController(EdukacijaPretraga view) {
        this.view = view;
        addActionListener();
    }
    
    public void otvoriFormu(){
        
        vratiEdukacije();
        
  
        view.getjTableEdukacije().setBackground(new Color(204,255,255));
        view.getjTableEdukacije().setFont(new Font("Hiragino Mincho ProN", Font.BOLD,12));
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
        view.addPretragaActionListener(e -> pretraziEdukacije());
        view.addUcitajActionListener(e -> ucitajEdukaciju());
    }
    

    private void pretraziEdukacije() {
        String imeIns = view.getjTextPretragaImeInstruktora().getText();
        
        
        if(imeIns.isEmpty()){
            JOptionPane.showMessageDialog(view, "Unesite ime instruktora");
            model.setListaEdukacija(listaEdukacija);
            return;
        }

        try {
            
            List<Edukacija> lis = Komunikacija.getInstance().pretragaPoImenu(imeIns);
            model.setListaEdukacija(lis);
            JOptionPane.showMessageDialog(view, "Sistem je nasao edukacije po zadatoj vrednosti");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
            
        
    }
    
      private void vratiEdukacije() {
        try {
            this.listaEdukacija = Komunikacija.getInstance().vratiListuEdukacija();
            model = new ModelTabeleEdukacije(listaEdukacija);
            view.getjTableEdukacije().setModel(model);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void ucitajEdukaciju() {
        if(view.getjTableEdukacije().getSelectedRow()==-1){
            JOptionPane.showMessageDialog(view, "Sistem ne moze da nadje edukaciju");
            return;
        }
        Edukacija e = model.getEdukacijaAt(view.getjTableEdukacije().getSelectedRow());
        try {
            Edukacija ucitana = Komunikacija.getInstance().ucitajEdukaciju(e);
            JOptionPane.showMessageDialog(view, "Sistem je ucitao edukaciju.");
            Koordinator.getInstance().otvoriEdukacijaPromeniObrisiFormu(ucitana);
            view.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
    }
    
    
}
