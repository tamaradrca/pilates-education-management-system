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
public class UcitajKlijentaSO extends ApstraktnaGenerickaOperacija{

    Klijent k;
    @Override
    protected void preduslovi(Object param) throws Exception {
         if (param == null || !(param instanceof Klijent)) {
            throw new Exception("Sistem ne moze da ucita klijenta");
          }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Klijent ku=(Klijent) param;
        String uslov = " JOIN zaposleni ON klijent.idZaposleni = zaposleni.idZaposleni WHERE klijent.idKlijent =  " + ku.getIdKlijent();
        k = (Klijent) broker.get(ku, uslov);

    }

    public Klijent getK() {
        return k;
    }
    
}
