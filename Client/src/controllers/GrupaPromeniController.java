/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Edukacija;
import domain.Grupa;
import domain.Klijent;
import domain.PripadanjeGrupi;
import domain.Zaposleni;
import forms.GrupaPromeni;
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
import tableModeli.ModelTabelePripadanjeGrupi;

/**
 *
 * @author tamaradrca
 */
public class GrupaPromeniController {
    
    private final GrupaPromeni view;
    
    private Grupa ucitana;
    private Zaposleni zaposleni;
    private List<Klijent> listaKlijenata;
    private List<PripadanjeGrupi> listaP;
    private ModelTabeleKlijenti model;
    private ModelTabelePripadanjeGrupi modelP;
    private ModelTabeleEdukacije modelE;
    private List<Edukacija> listaEdukacija;

    public GrupaPromeniController(GrupaPromeni view) {
        this.view = view;
        addActionListener();
    }
    
    public void otvoriFormu(Grupa u){
        this.zaposleni = Komunikacija.getInstance().getUlogovani();
        view.getjLabelUlogovanZaposleni().setText(zaposleni.getIme() + " " + zaposleni.getPrezime());
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        this.ucitana = u;
        
        view.getjTextMaksBrojKlijenata().setText(Integer.toString(ucitana.getMaksBrojKlijenata()));
        view.getjCalendarDatumPocetka().setDate(ucitana.getDatumPocetka());
        view.getjCalendarDatumZavrsetka().setDate(ucitana.getDatumZavrsetka());
        view.getjTextNazivEdukacije().setText(ucitana.getEdukacija().getNaziv());
        view.getjTextNazivEdukacije().setEditable(false);
        view.getjTableKlijenti().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        view.addMenuActionListener(
                e -> edukacije(),
                e -> klijenti(),
                e -> grupe(),
                e -> ins(),
                e -> odjava()
        );
        vratiKlijente();
        vratiPripadanjeGrupi();
        vratiEdukacije();
        
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
        view.dodajKlijentaAddActionListener(e -> promeniGrupu());
    }

    private void promeniGrupu() {
        int[] selektovaniRedovi = view.getjTableKlijenti().getSelectedRows();

        if (selektovaniRedovi.length == 0) {
            JOptionPane.showMessageDialog(view, "Izaberite bar jednog klijenta");
            return;
        }

        String maksBrojStr = view.getjTextMaksBrojKlijenata().getText();
        if (maksBrojStr.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Unesite maksimalan broj klijenata");
            return;
        }

        int maksBroj;
        try {
            maksBroj = Integer.parseInt(maksBrojStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Maksimalan broj klijenata mora biti broj");
            return;
        }

        List<Klijent> klijenti = new ArrayList<>();
        for (int red : selektovaniRedovi) {
            klijenti.add(model.getKlijentAt(red));
        }

        ucitana.setMaksBrojKlijenata(maksBroj);
        String status = view.getjTextStatusUplate().getText();
        Date datumPrijave = view.getjCalendarDatumPrijave().getDate();

        Object[] zahtev = new Object[]{ucitana, klijenti,status, datumPrijave};

        try {
            String rez = Komunikacija.getInstance().promeniGrupu(zahtev);
            JOptionPane.showMessageDialog(view, rez);
            vratiPripadanjeGrupi();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
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

    private void vratiPripadanjeGrupi() {
         try {
            this.listaP = Komunikacija.getInstance().vratiListuPripadanja(ucitana);
            modelP = new ModelTabelePripadanjeGrupi(listaP);
            view.getjTablePripadanjeGrupi().setModel(modelP);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void vratiEdukacije() {
        try {
            this.listaEdukacija = Komunikacija.getInstance().vratiListuEdukacija();
            modelE = new ModelTabeleEdukacije(listaEdukacija);
            view.getjTableEdukacije1().setModel(modelE);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
    
}
