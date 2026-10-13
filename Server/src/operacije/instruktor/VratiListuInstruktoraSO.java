/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.instruktor;

import domain.Instruktor;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class VratiListuInstruktoraSO extends ApstraktnaGenerickaOperacija {

    List<Instruktor> listaInstruktora;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        listaInstruktora = broker.getAll(new Instruktor(), "");

    }

    public List<Instruktor> getListaInstruktora() {
        return listaInstruktora;
    }
    
}
