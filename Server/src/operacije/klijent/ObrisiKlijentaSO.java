/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijent;

import domain.Klijent;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class ObrisiKlijentaSO extends ApstraktnaGenerickaOperacija{

    Klijent m;
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(!(param instanceof Klijent)){
            throw new Exception("Neispravan objekat");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Klijent k = (Klijent) param;
        String uslov = " JOIN zaposleni ON klijent.idZaposleni = zaposleni.idZaposleni WHERE klijent.idKlijent = " +k.getIdKlijent();
        m = (Klijent) broker.get(new Klijent(), uslov);
        if(!(m.getIme().equalsIgnoreCase(k.getIme())) ||
                !(m.getPrezime().equalsIgnoreCase(k.getPrezime())) ||
                !(m.getBrojTelefona().equalsIgnoreCase(k.getBrojTelefona())) ||
                !(m.getImejl().equalsIgnoreCase(k.getImejl())) ||
                !(m.getDatumRodjenja().equals(k.getDatumRodjenja()))){
            throw new Exception("Sistem ne moze da obrise klijenta.");
            
        } else {
            broker.delete(k);
        }
        
        
        
    }
    
    
    
}
