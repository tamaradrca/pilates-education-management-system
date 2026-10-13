/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.klijent;

import domain.Edukacija;
import domain.Klijent;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class KreirajKlijentSO extends ApstraktnaGenerickaOperacija{

   
    @Override
    protected void preduslovi(Object param) throws Exception {
        if(!(param instanceof Klijent)){
            throw new Exception("Sistem ne moze da kreira klijenta. Neispravan objekat.");
        }
        Klijent k = (Klijent) param;
        LocalDate danas = LocalDate.now();
        LocalDate datum = k.getDatumRodjenja().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int godine = Period.between(datum,danas).getYears();
        if(godine<18){
            throw new Exception("Sistem ne moze da kreira klijenta");
        }
        if (!k.getBrojTelefona().matches("[0-9]+") || !k.getImejl().contains("@") || !k.getImejl().contains(".com")) {
            throw new Exception("Sistem ne moze da kreira klijenta");

        }

      
        String uslov = " WHERE imejl='" + k.getImejl() + "' AND brojTelefona='" + k.getBrojTelefona() + "'";
        Klijent postoji = (Klijent) broker.get(k, uslov);

        if (postoji != null) {
            throw new Exception("Sistem ne moze da kreira klijenta");
        }
        
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Klijent k = (Klijent) param;
        broker.add(k);
    }
    
}
