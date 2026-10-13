/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModeli;

import domain.PripadanjeGrupi;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tamaradrca
 */
public class ModelTabelePripadanjeGrupi extends AbstractTableModel{

    private List<PripadanjeGrupi> listaP;
    private String[] kolone = {"Klijent" , "Status uplate" , "Datum prijave"};

    public ModelTabelePripadanjeGrupi(List<PripadanjeGrupi> listaP) {
        this.listaP = listaP;
    }
    
    
    
    @Override
    public int getRowCount() {
        return listaP.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PripadanjeGrupi pg = listaP.get(rowIndex);
        
        switch(columnIndex){
            case 0: return pg.getKlijent().getIme() + " " + pg.getKlijent().getPrezime();
            case 1: return pg.getStatusUplate();
            case 2: return pg.getDatumPrijave();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setListaP(List<PripadanjeGrupi> listaP) {
        this.listaP = listaP;
        fireTableDataChanged();
    }
    
    
    
    
    
    
    
}
