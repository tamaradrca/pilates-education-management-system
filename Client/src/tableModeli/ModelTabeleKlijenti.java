/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModeli;

import domain.Klijent;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tamaradrca
 */
public class ModelTabeleKlijenti extends AbstractTableModel {

    private List<Klijent> listaKlijenata;
    private String[] kolone = {"Ime" , "Prezime" , "Email" , "Datum rodjenja" , "Broj telefona" , "Zaposleni"};

    public ModelTabeleKlijenti(List<Klijent> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
    }

    @Override
    public int getRowCount() {
        return listaKlijenata.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Klijent k = listaKlijenata.get(rowIndex);
        
        switch(columnIndex){
            case 0: return k.getIme();
            case 1: return k.getPrezime();
            case 2: return k.getImejl();
            case 3: return k.getBrojTelefona();
            case 4: return k.getDatumRodjenja();
            case 5: return k.getZaposleni();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setListaKlijenata(List<Klijent> listaKlijenata) {
        this.listaKlijenata = listaKlijenata;
        fireTableDataChanged();
    }

    public List<Klijent> getListaKlijenata() {
        return listaKlijenata;
    }
    
   public Klijent getKlijentAt(int index){
       return listaKlijenata.get(index);
   }
    
    
}
