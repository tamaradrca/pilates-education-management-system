/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.edukacija;

import domain.Edukacija;
import domain.Instruktor;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class VratiListuEdukacijaSO extends ApstraktnaGenerickaOperacija{
    
    List<Edukacija> listaEdukacija;

    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        String uslov = " JOIN instruktor ON edukacija.idInstruktor = instruktor.idInstruktor "
         + "JOIN zaposleni ON edukacija.idZaposleni = zaposleni.idZaposleni ";
        listaEdukacija = broker.getAll(new Edukacija(), uslov);

    }

    public List<Edukacija> getListaEdukacija() {
        return listaEdukacija;
    }
    
    
}
