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
public class ObrisiInstruktoraSO extends ApstraktnaGenerickaOperacija{
    private Instruktor instruktor;

    @Override
    protected void preduslovi(Object param) throws Exception {
        instruktor = (Instruktor) param;
        if (instruktor == null) {
            throw new Exception("Podaci nisu validni.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        broker.delete(instruktor);
    }
}
