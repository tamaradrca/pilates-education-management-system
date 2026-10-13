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
public class PretragaKlijentSO extends ApstraktnaGenerickaOperacija{

    List<Klijent> lista;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        String ime = (String) param;
        String uslov = " JOIN zaposleni ON klijent.idZaposleni = zaposleni.idZaposleni WHERE klijent.ime = '" + ime + "'";
        lista = broker.getAll(new Klijent(), uslov);
        if(lista.isEmpty()){
            throw new Exception("Sistem ne moze da nadje klijente po zadatoj vrednosti");
        }
    }

    public List<Klijent> getLista() {
        return lista;
    }
    
    
    
}
