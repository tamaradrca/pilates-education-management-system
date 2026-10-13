/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Edukacija;
import domain.Grupa;
import domain.Zaposleni;
import forms.GrupaPretraga;
import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import tableModeli.ModelTabeleEdukacije;
import tableModeli.ModelTabeleGrupe;

/**
 *
 * @author tamaradrca
 */
public class GrupaPretragaController {
    
    private final GrupaPretraga view;
    
    private Zaposleni zaposleni;
    private ModelTabeleGrupe model;
    private List<Grupa> listaGrupa;

    public GrupaPretragaController(GrupaPretraga view) {
        this.view = view;
        addActionListener();
    }
    
    public void otvoriFormu(){
        
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        
        view.getjTableGrupe().setBackground(new Color(204,255,255));
        view.getjTableGrupe().setFont(new Font("Hiragino Mincho ProN", Font.BOLD,12));
        
        view.addMenuActionListener(
                e -> edukacije(),
                e -> klijenti(),
                e -> grupe(),
                e -> ins(),
                e -> odjava()
        );
        
        vratiGrupe();
        
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
        view.addPretragaActionListener(e -> pretragaGrupe());
        view.addUcitajActionListener(e -> ucitajGrupu());
    }

    private void pretragaGrupe() {
        String naziv = view.getjTextPretragaNazivEdukacije().getText();
        
        if(naziv.isEmpty()){
            JOptionPane.showMessageDialog(view, "Unesite naziv edukacije");
            model.setListaGrupa(listaGrupa);
            return;
        }
        
        try {
            List<Grupa> pretrazene = Komunikacija.getInstance().pretragaGrupe(naziv);
            model.setListaGrupa(pretrazene);
            JOptionPane.showMessageDialog(view, "Sistem je nasao grupe za pracenje edukacija po zadatoj vrednosti");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
        
        
    }

    private void ucitajGrupu() {
        if(view.getjTableGrupe().getSelectedRow()==-1){
            JOptionPane.showMessageDialog(view, "" + "Sistem ne moze da ucita grupu", "Greška", JOptionPane.ERROR_MESSAGE);

            return;
        }
        Grupa e = model.getGrupaAt(view.getjTableGrupe().getSelectedRow());
        try {
            Grupa ucitana = Komunikacija.getInstance().ucitajGrupu(e);
            JOptionPane.showMessageDialog(view, "Sistem je ucitao grupu za pracenje edukacije.");
            Koordinator.getInstance().otvoriGrupaPromeniFormu(ucitana);
            view.dispose();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

        }
    }

    private void vratiGrupe() {
                try {
            this.listaGrupa = Komunikacija.getInstance().vratiListuGrupa();
            model = new ModelTabeleGrupe(listaGrupa);
            view.getjTableGrupe().setModel(model);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    
}
