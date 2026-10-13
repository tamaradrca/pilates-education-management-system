/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.instruktor;

import domain.Instruktor;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class PromeniInstruktoraSO extends ApstraktnaGenerickaOperacija {
    private Instruktor instruktor;

    @Override
    protected void preduslovi(Object param) throws Exception {
        instruktor = (Instruktor) param;
        if (instruktor == null) {
            throw new Exception("Podaci nisu validni.");
        }
        if (instruktor.getIme() == null || instruktor.getIme().trim().isEmpty()) {
            throw new Exception("Ime instruktora ne sme biti prazno.");
        }
        if (instruktor.getPrezime() == null || instruktor.getPrezime().trim().isEmpty()) {
            throw new Exception("Prezime instruktora ne sme biti prazno.");
        }
        if (instruktor.getImejl() == null || instruktor.getImejl().trim().isEmpty()) {
            throw new Exception("Imejl instruktora ne sme biti prazan.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        broker.edit(instruktor);
    }
}
