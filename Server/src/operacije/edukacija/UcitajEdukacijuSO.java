/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.edukacija;

import domain.Edukacija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class UcitajEdukacijuSO extends ApstraktnaGenerickaOperacija{

    Edukacija e;
    @Override
    protected void preduslovi(Object param) throws Exception {
          if (param == null || !(param instanceof Edukacija)) {
            throw new Exception("Sistem ne moze da ucita edukaciju");
          }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Edukacija u = (Edukacija) param;
        String uslov = " JOIN instruktor ON edukacija.idInstruktor = instruktor.idInstruktor "
         + "JOIN zaposleni ON edukacija.idZaposleni = zaposleni.idZaposleni WHERE edukacija.idEdukacija = " + u.getIdEdukacija();
        e = (Edukacija) broker.get(new Edukacija(), uslov);
        
    }

    public Edukacija getE() {
        return e;
    }
    
    
}
