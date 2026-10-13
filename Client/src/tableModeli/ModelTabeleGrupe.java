/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModeli;

import domain.Grupa;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author tamaradrca
 */
public class ModelTabeleGrupe extends AbstractTableModel{

    private List<Grupa> listaGrupa;
    private String[] kolone = {"Grupa", "Edukacija" , "Datum pocetka" , "Datum zavrsetka" , "Maks broj klijenata" , "Zaposleni"};

    public ModelTabeleGrupe(List<Grupa> listaGrupa) {
        this.listaGrupa = listaGrupa;
    }
    
    @Override
    public int getRowCount() {
       return listaGrupa.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Grupa grupa = listaGrupa.get(rowIndex);
        
        switch(columnIndex){
            case 0: return grupa.getIdGrupa();
            case 1: return grupa.getEdukacija().getNaziv();
            case 2: return grupa.getDatumPocetka();
            case 3: return grupa.getDatumZavrsetka();
            case 4: return grupa.getMaksBrojKlijenata();
            case 5: return grupa.getZaposleni();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    public void setListaGrupa(List<Grupa> listaGrupa) {
        this.listaGrupa = listaGrupa;
        fireTableDataChanged();
    }
    public Grupa getGrupaAt(int selektovana){
        return listaGrupa.get(selektovana);
    }
    
    
    
    
}
