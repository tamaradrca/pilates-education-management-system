/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.grupa;

import domain.Grupa;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class PretragaGrupeSO extends ApstraktnaGenerickaOperacija{

    List<Grupa> pretGrupe;
    @Override
    protected void preduslovi(Object param) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        String naziv = (String) param;
        String uslov = " JOIN edukacija ON grupa.idEdukacija = edukacija.idEdukacija JOIN zaposleni ON grupa.idZaposleni = zaposleni.idZaposleni WHERE edukacija.naziv LIKE '%" + naziv + "%'";
        pretGrupe = broker.getAll(new Grupa(), uslov);
        if(pretGrupe.isEmpty()){
            throw new Exception("Sistem ne moze da nadje grupe za pracenje edukacija po zadatoj vrednosti");
        }

    }

    public List<Grupa> getPretGrupe() {
        return pretGrupe;
    }
    
    
    
}
