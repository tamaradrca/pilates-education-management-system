/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.edukacija;

import domain.Edukacija;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class PretragaEdukacijaSO extends ApstraktnaGenerickaOperacija{
    
    private List<Edukacija> listaEdukacija;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        String ime = (String) param;
        String uslov = " JOIN instruktor ON edukacija.idInstruktor = instruktor.idInstruktor "
         + "JOIN zaposleni ON edukacija.idZaposleni = zaposleni.idZaposleni WHERE instruktor.ime= " + "'" + ime + "'";
        listaEdukacija = broker.getAll(new Edukacija(), uslov);
        if(listaEdukacija.isEmpty()){
            throw new Exception("Sistem ne moze da nadje edukacije po zadatom kriterijumu");
        }
    }

    public List<Edukacija> getListaEdukacija() {
        return listaEdukacija;
    }
    
    
    
}
