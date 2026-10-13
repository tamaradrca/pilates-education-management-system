/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.grupa;

import domain.Grupa;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class VratiListuGrupaSO extends ApstraktnaGenerickaOperacija{

    List<Grupa> listaGrupa;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        String uslov = " JOIN edukacija ON grupa.idEdukacija = edukacija.idEdukacija JOIN zaposleni ON grupa.idZaposleni = zaposleni.idZaposleni ";
        listaGrupa = broker.getAll(new Grupa(), uslov);
        
    }

    public List<Grupa> getListaGrupa() {
        return listaGrupa;
    }
    
    
}
