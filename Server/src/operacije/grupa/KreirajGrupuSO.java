/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.grupa;

import domain.Grupa;
import domain.Klijent;
import domain.PripadanjeGrupi;
import java.util.Date;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class KreirajGrupuSO extends ApstraktnaGenerickaOperacija{

    private Grupa grupa;
    List<Klijent> klijenti;
    
    @Override
    protected void preduslovi(Object param) throws Exception {
        Object[] niz =  (Object[]) param;
        grupa = (Grupa) niz[0];
        klijenti = (List<Klijent>) niz[1];
        
        if(grupa.getDatumZavrsetka().before(grupa.getDatumPocetka())){
            throw new Exception("Sistem ne moze da kreira edukaciju");
        }
          
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        
        broker.add(grupa);
        String uslov = " JOIN edukacija ON grupa.idEdukacija = edukacija.idEdukacija JOIN zaposleni ON grupa.idZaposleni = zaposleni.idZaposleni WHERE grupa.maksBrojKlijenata= " + grupa.getMaksBrojKlijenata();
        Grupa nova = (Grupa) broker.get(new Grupa(), uslov);
        
         for(Klijent k : klijenti){
            PripadanjeGrupi pg = new PripadanjeGrupi(nova,k,new Date(),"nije uplaceno");
            broker.add(pg);
        }
        
        
    }
    

    
}
