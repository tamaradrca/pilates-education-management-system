/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijent;

import domain.Klijent;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class VratiListuKlijenataSO extends ApstraktnaGenerickaOperacija{

    List<Klijent> sviKlijenti;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        String uslov = " JOIN zaposleni ON klijent.idZaposleni = zaposleni.idZaposleni ";
        sviKlijenti = broker.getAll(new Klijent(), uslov);
    }

    public List<Klijent> getSviKlijenti() {
        return sviKlijenti;
    }
    
    
    
}
