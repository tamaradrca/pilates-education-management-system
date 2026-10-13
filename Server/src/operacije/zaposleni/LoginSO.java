/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zaposleni;

import domain.Zaposleni;
import operacije.ApstraktnaGenerickaOperacija;
import repository.Repository;

/**
 *
 * @author tamaradrca
 */
public class LoginSO extends ApstraktnaGenerickaOperacija{

    Zaposleni zaposleni;


    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof Zaposleni)) {
            throw new Exception("Neuspesno prijavljivanje na sistem.");

        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Zaposleni zap = (Zaposleni) param;
        
        String uslov = " WHERE imejl= '" + zap.getImejl() + "' AND lozinka= '" + zap.getLozinka() + "'";
        
        zaposleni = (Zaposleni) broker.get(zap, uslov);
        if(zaposleni == null) {
            throw new Exception("Neuspesno prijavljivanje na sistem");
        }
        
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }
    
    
    
}
