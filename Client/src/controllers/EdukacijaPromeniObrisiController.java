/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import domain.Edukacija;
import domain.Instruktor;
import domain.Zaposleni;
import forms.EdukacijaPromeniObrisi;
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
public class EdukacijaPromeniObrisiController {

    private final EdukacijaPromeniObrisi view;

    private List<Instruktor> listaInstruktora = new ArrayList<>();
    
    private Edukacija ta;

    public EdukacijaPromeniObrisiController(EdukacijaPromeniObrisi view) {
        this.view = view;
    }

    public void otvoriFormu(Edukacija ucitana) {
        
        ta = ucitana;
        view.setVisible(true);
        
        addActionLListener();
        

        view.getjTextNazivEdukacije().setText(ucitana.getNaziv());
        view.getjTextAreaOpisEdukacije().setText(ucitana.getOpis());
        view.getjTextCenaEdukacije().setText(Integer.toString((int) ucitana.getCena()));
        view.getjTextBrojCasova().setText(Integer.toString(ta.getBrojCasova()));
        popuniInstruktore();
        view.getjComboBoxListaInstruktora().setSelectedItem(ucitana.getInstruktor());
        view.getjLabelUlogovanZaposleni().setText(Komunikacija.getInstance().getUlogovani().getIme() + " " + Komunikacija.getInstance().getUlogovani().getPrezime());

        view.addMenuActionListener(
                e -> edukacije(),
                e -> klijenti(),
                e -> grupe(),
                e -> ins(),
                e -> odjava()
        );

    }

    private void edukacije() {
        Koordinator.getInstance().otvoriEdukacijaPretragaFormu();
        view.dispose();
    }

    private void klijenti() {
        Koordinator.getInstance().otvoriKlijentFormu();
        view.dispose();

    }

    private void grupe() {
        Koordinator.getInstance().otvoriGrupeFormu();
        view.dispose();
    }

    private void ins() {
        Koordinator.getInstance().otvoriInstruktoriFormu();
        view.dispose();
    }

    private void odjava() {
         try {
            String rez = Komunikacija.getInstance().odjaviZap();
            JOptionPane.showMessageDialog(view, rez);
        } catch (Exception ex) {
            Logger.getLogger(EdukacijaKreirajController.class.getName()).log(Level.SEVERE, null, ex);
        }
        view.dispose();
    }

    private void popuniInstruktore() {
        try {
            this.listaInstruktora = Komunikacija.getInstance().vratiListuInstruktora();
            view.getjComboBoxListaInstruktora().removeAllItems();
            for (Instruktor i : listaInstruktora) {
                view.getjComboBoxListaInstruktora().addItem(i);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void addActionLListener(){
        view.addIzmeniEdukacijuActionListener(e -> promeniEdukaciju(ta));
        view.addObrisiEdukacijuActionListener(e -> obrisiEdukaciju(ta));
    }

    private void promeniEdukaciju(Edukacija ta) {
        String naziv = view.getjTextNazivEdukacije().getText().trim();
        String opis = view.getjTextAreaOpisEdukacije().getText();
        String cenaS = view.getjTextCenaEdukacije().getText();
        double cena = 0;
        int brojCasova = 0;
        String br = view.getjTextBrojCasova().getText();
        Zaposleni z = Komunikacija.getInstance().getUlogovani();
        Instruktor i = (Instruktor) view.getjComboBoxListaInstruktora().getSelectedItem();
        try {
            cena = Integer.parseInt(cenaS);
            brojCasova = Integer.parseInt(br);
            Edukacija e = new Edukacija(ta.getIdEdukacija(), naziv, opis, cena, brojCasova, z, i);

            try {
                String rez = Komunikacija.getInstance().promeniEdukacija(e);
                JOptionPane.showMessageDialog(view, rez);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Morate uneti broj.");
            return;
        }

        
        
       
    }

    private void obrisiEdukaciju(Edukacija ta) {
        
        String naziv = view.getjTextNazivEdukacije().getText().trim();
        String opis = view.getjTextAreaOpisEdukacije().getText();
        String cenaS = view.getjTextCenaEdukacije().getText();
        double cena = 0;
        int brojCasova = 0;
        String br = view.getjTextBrojCasova().getText();
        Zaposleni z = Komunikacija.getInstance().getUlogovani();
        Instruktor i = (Instruktor) view.getjComboBoxListaInstruktora().getSelectedItem();

        try {
            cena = Integer.parseInt(cenaS);
            brojCasova = Integer.parseInt(br);
            Edukacija e = new Edukacija(ta.getIdEdukacija(), naziv, opis, cena, brojCasova, z, i);
            try {
                String rez = Komunikacija.getInstance().obrisiEdukaciju(e);
                JOptionPane.showMessageDialog(view, rez);
                view.dispose();
                Koordinator.getInstance().otvoriEdukacijaPretragaFormu();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, "" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);

            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Morate uneti broj.");
            return;
        }


        
    }

}
