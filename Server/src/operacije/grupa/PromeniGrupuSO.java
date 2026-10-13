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
public class PromeniGrupuSO extends ApstraktnaGenerickaOperacija{
    
    private Grupa grupa;
    private List<Klijent> klijenti;
    private String status;
    private Date datum;

    @Override
    protected void preduslovi(Object param) throws Exception {
       
        Object[] niz = (Object[]) param;
        grupa = (Grupa) niz[0];
        klijenti = (List<Klijent>) niz[1];
        status = (String) niz[2];
        datum = (Date) niz[3];

        if (grupa == null || klijenti == null || klijenti.isEmpty()) {
            throw new Exception("Podaci nisu validni.");
        }
        
        if (grupa.getMaksBrojKlijenata() <= 0) {
            throw new Exception("Maksimalan broj klijenata mora biti veci od 0.");
        }
        
        String uslovBrojanja = " JOIN klijent ON pripadanjeGrupi.idKlijent = klijent.idKlijent"
                + " JOIN grupa ON pripadanjeGrupi.idGrupe = grupa.idGrupe"
                + " JOIN edukacija ON grupa.idEdukacija = edukacija.idEdukacija"
                + " WHERE pripadanjeGrupi.idGrupe=" + grupa.getIdGrupa();
        List<PripadanjeGrupi> trenutniClanovi = (List<PripadanjeGrupi>) broker.getAll(new PripadanjeGrupi(), uslovBrojanja);

        int trenutniBroj = trenutniClanovi.size();
        int noviClanovi = klijenti.size();

        if (trenutniBroj + noviClanovi > grupa.getMaksBrojKlijenata()) {
            throw new Exception("Sistem ne moze da izmeni grupu za pracenje");

        }
        

        for (Klijent k : klijenti) {
            String uslov = " JOIN klijent ON pripadanjeGrupi.idKlijent = klijent.idKlijent"
                         + " JOIN grupa ON pripadanjeGrupi.idGrupe = grupa.idGrupe"
                         + " JOIN edukacija ON grupa.idEdukacija = edukacija.idEdukacija"
                         + " WHERE pripadanjeGrupi.idGrupe=" + grupa.getIdGrupa()
                         + " AND pripadanjeGrupi.idKlijent=" + k.getIdKlijent();
            PripadanjeGrupi postojeci = (PripadanjeGrupi) broker.get(new PripadanjeGrupi(), uslov);
            if (postojeci != null) {
                throw new Exception("Sistem ne moze da izmeni grupu za pracenje");
            }
        }
       
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        
        broker.edit(grupa);
        
        for(Klijent k : klijenti){
            PripadanjeGrupi pg = new PripadanjeGrupi(grupa,k,datum,status);
            broker.add(pg);
        }
        
        
    }
    
}
