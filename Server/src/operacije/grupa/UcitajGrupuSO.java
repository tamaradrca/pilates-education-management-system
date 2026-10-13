/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.grupa;

import domain.Grupa;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class UcitajGrupuSO extends ApstraktnaGenerickaOperacija{

    Grupa ucitana;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Grupa ucitaj = (Grupa) param;
        String uslov = " JOIN edukacija ON grupa.idEdukacija = edukacija.idEdukacija JOIN zaposleni ON grupa.idZaposleni = zaposleni.idZaposleni WHERE grupa.idGrupe= " + ucitaj.getIdGrupa();
        ucitana = (Grupa) broker.get(new Grupa(), uslov);

    }

    public Grupa getUcitana() {
        return ucitana;
    }
    
    
    
}
