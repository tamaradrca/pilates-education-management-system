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
public class PretraziInstruktoraSO extends ApstraktnaGenerickaOperacija{

  private List<Instruktor> lista;

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || param.toString().trim().isEmpty()) {
            throw new Exception("Unesite ime za pretragu.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        String ime = (String) param;
        String uslov = " WHERE ime LIKE '%" + ime + "%'"
                     + " OR prezime LIKE '%" + ime + "%'"
                     + " OR imejl LIKE '%" + ime + "%'";
        lista = (List<Instruktor>) broker.getAll(new Instruktor(), uslov);
    }

    public List<Instruktor> getLista() {
        return lista;
    }
}
    

