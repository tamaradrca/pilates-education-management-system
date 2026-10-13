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
public class PromeniKlijentSO extends ApstraktnaGenerickaOperacija{

    Klijent k;
    @Override
    protected void preduslovi(Object param) throws Exception {
        Klijent n = (Klijent) param;
      if (!n.getBrojTelefona().matches("[0-9]+") || !n.getImejl().contains("@") || !n.getImejl().contains(".com")) {

            throw new Exception("Sistem ne moze da izmeni klijenta");

    }
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        k = (Klijent) param;
        broker.edit(k);
    }
    
}
