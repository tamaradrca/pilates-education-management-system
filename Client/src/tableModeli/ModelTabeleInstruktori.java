/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModeli;

import domain.Instruktor;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tamaradrca
 */
public class ModelTabeleInstruktori extends AbstractTableModel{

    private List<Instruktor> listaI;
    private String[] kolone = {"Ime" , "Prezime" , "Email"};

    public ModelTabeleInstruktori(List<Instruktor> listaI) {
        this.listaI = listaI;
    }
    
    @Override
    public int getRowCount() {
        return listaI.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Instruktor i = listaI.get(rowIndex);
        
        switch(columnIndex){
            case 0: return i.getIme();
            case 1: return i.getPrezime();
            case 2: return i.getImejl();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setListaI(List<Instruktor> listaI) {
        this.listaI = listaI;
        fireTableDataChanged();
    }
    
    public Instruktor getInstruktorAt(int index){
        return listaI.get(index);
    }
    
    
}
