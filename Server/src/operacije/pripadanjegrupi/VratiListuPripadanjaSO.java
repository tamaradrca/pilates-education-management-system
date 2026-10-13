/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.pripadanjegrupi;

import domain.Grupa;
import domain.PripadanjeGrupi;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class VratiListuPripadanjaSO extends ApstraktnaGenerickaOperacija{

    List<PripadanjeGrupi> listaP;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Grupa g = (Grupa) param;
        String uslov = " JOIN klijent ON pripadanjeGrupi.idKlijent = klijent.idKlijent JOIN grupa ON pripadanjeGrupi.idGrupe = grupa.idGrupe JOIN edukacija ON grupa.idEdukacija = edukacija.idEdukacija WHERE pripadanjeGrupi.idGrupe=" + g.getIdGrupa();
        listaP = broker.getAll(new PripadanjeGrupi(), uslov);
        
    }

    public List<PripadanjeGrupi> getListaP() {
        return listaP;
    }
    
    
    
}
