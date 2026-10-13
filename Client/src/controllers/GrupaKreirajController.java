/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Edukacija;
import domain.Grupa;
import domain.Klijent;
import domain.Zaposleni;
import forms.GrupaKreiraj;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import komunikacija.Komunikacija;
import koordinator.Koordinator;
import tableModeli.ModelTabeleEdukacije;
import tableModeli.ModelTabeleKlijenti;

/**
 *
 * @author tamaradrca
 */
public class GrupaKreirajController {
    
    private final GrupaKreiraj view;
    
    private Zaposleni zaposleni;
    private List<Edukacija> listaEdukacija;
    private ModelTabeleEdukacije model;
    private ModelTabeleKlijenti modelK;
    private List<Klijent> listaKlijenata;
    

    public GrupaKreirajController(GrupaKreiraj view) {
        this.view = view;
        addActionListener();
    }
    
    public void otvoriFormu(){
        this.zaposleni = Komunikacija.getInstance().getUlogovani();
        view.getjLabelUlogovanZaposleni().setText(zaposleni.getIme() + " " + zaposleni.getPrezime());
        view.setLocationRelativeTo(null);
        view.setVisible(true);
       view.getjTableKlijenti().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        
        view.addMenuActionListener(
                e -> edukacije(),
                e -> klijenti(),
                e -> grupe(),
                e -> ins(),
                e -> odjava()
        );
         vratiEdukacije();
         vratiKlijente();

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
        Koordinator.getInstance().otvoriGrupePretragaFormu();
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
        view.kreirajGrupuAddActionListener(e -> kreirajGrupu());
    }
    
    private void vratiEdukacije() {
        try {
            this.listaEdukacija = Komunikacija.getInstance().vratiListuEdukacija();
            model = new ModelTabeleEdukacije(listaEdukacija);
            view.getjTableEdukacije1().setModel(model);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    private void kreirajGrupu() {
        
        try{
            
            String maksS = view.getjTextMaksBrojKlijenata().getText();
            int maksBroj = Integer.parseInt(maksS);
            
            Date datP = view.getjCalendarDatumPocetka().getDate();
            Date datZ = view.getjCalendarDatumZavrsetka().getDate();
            int[] selektovaniRedovi = view.getjTableKlijenti().getSelectedRows();

        if (selektovaniRedovi.length == 0) {
            JOptionPane.showMessageDialog(view, "Izaberite bar jednog klijenta");
            return;
        }
List<Klijent> klijenti = new ArrayList<>();
        for (int red : selektovaniRedovi) {
            klijenti.add(modelK.getKlijentAt(red));
        }

            if(view.getjTableEdukacije1().getSelectedRow()==-1){
                JOptionPane.showMessageDialog(view, "Izaberite edukaciju");
                return;
            }
          
            Edukacija e = model.getEdukacijaAt(view.getjTableEdukacije1().getSelectedRow());
            
            Grupa grupa = new Grupa(maksBroj,datP,datZ,e,zaposleni);
            Object[] zahtev = new Object[]{grupa,klijenti};
            try {
                
               
                String rez = Komunikacija.getInstance().kreirajGrupu(zahtev);
                JOptionPane.showMessageDialog(view, rez);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

            }
            
            
        } catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(view, "Morate uneti broj.");
            return;
        }
        
    }
    private void vratiKlijente() {
         try {
            this.listaKlijenata = Komunikacija.getInstance().vratiListuKlijenata();
            modelK = new ModelTabeleKlijenti(listaKlijenata);
            view.getjTableKlijenti().setModel(modelK);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
}
