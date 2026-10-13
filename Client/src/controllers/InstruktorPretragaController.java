/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Instruktor;
import forms.InstruktorPretraga;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import tableModeli.ModelTabeleInstruktori;
import tableModeli.ModelTabeleKlijenti;

/**
 *
 * @author tamaradrca
 */
public class InstruktorPretragaController {
    
    private final InstruktorPretraga view;
    private ModelTabeleInstruktori model;
    private List<Instruktor> listaI;

    public InstruktorPretragaController(InstruktorPretraga view) {
        this.view = view;
        addActionListener();
    }

        public void otvoriFormu(){
        
        vratiInstruktore();
        
  
        view.getjTableInstruktori().setBackground(new Color(204,255,255));
        view.getjTableInstruktori().setFont(new Font("Hiragino Mincho ProN", Font.BOLD,12));
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
        view.pretragaAddActionListener(e -> pretraziIns());
        view.ucitajAddActionListener(e -> ucitajIns());
    }
    

    private void pretraziIns() {
        String ime = view.getjTextPretragaIme().getText();
        if(ime.isEmpty()){
            model.setListaI(listaI);
            return;
        }
        try {
            List<Instruktor> l = Komunikacija.getInstance().pretraziIns(ime);
            model.setListaI(l);
        } catch (Exception ex) {
            Logger.getLogger(InstruktorPretragaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void ucitajIns() {
        Instruktor u = model.getInstruktorAt(view.getjTableInstruktori().getSelectedRow());
        Koordinator.getInstance().otvoriPromeniInstruktor(u);
    }

    private void vratiInstruktore() {
              try {
            this.listaI = Komunikacija.getInstance().vratiListuInstruktora();
            model = new ModelTabeleInstruktori(listaI);
            view.getjTableInstruktori().setModel(model);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
}
