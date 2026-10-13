/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModeli;

import domain.Edukacija;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tamaradrca
 */
public class ModelTabeleEdukacije extends AbstractTableModel{

    private List<Edukacija> listaEdukacija;
    private String[] kolone = {"Naziv" , "Opis" , "Cena" , "Broj casova" , "Instruktor"};

    public ModelTabeleEdukacije(List<Edukacija> listaEdukacija) {
        this.listaEdukacija = listaEdukacija;
    }
    
    
    @Override
    public int getRowCount() {
        return this.listaEdukacija.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Edukacija e = listaEdukacija.get(rowIndex);
        
        switch(columnIndex){
            case 0: return e.getNaziv();
            case 1: return e.getOpis();
            case 2: return e.getCena();
            case 3: return e.getBrojCasova();
            case 4: return e.getInstruktor();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }
    
    public Edukacija getEdukacijaAt(int selektovanaEdukacija){
        return listaEdukacija.get(selektovanaEdukacija);
    }

    public void setListaEdukacija(List<Edukacija> listaEdukacija) {
        this.listaEdukacija = listaEdukacija;
        fireTableDataChanged();
    }
    
    
    
    
    
}
