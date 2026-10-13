/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tamaradrca
 */
public class PripadanjeGrupi implements ApstraktniDomenskiObjekat{

    private Grupa grupa;
    private Klijent klijent;
    private Date datumPrijave;
    private String statusUplate;

    public PripadanjeGrupi(Grupa grupa, Klijent klijent, Date datumPrijave, String statusUplate) {
        this.grupa = grupa;
        this.klijent = klijent;
        this.datumPrijave = datumPrijave;
        this.statusUplate = statusUplate;
    }

    public PripadanjeGrupi() {
    }

    
    
    
    public Grupa getGrupa() {
        return grupa;
    }

    public void setGrupa(Grupa grupa) {
        this.grupa = grupa;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Date getDatumPrijave() {
        return datumPrijave;
    }

    public void setDatumPrijave(Date datumPrijave) {
        this.datumPrijave = datumPrijave;
    }

    public String getStatusUplate() {
        return statusUplate;
    }

    public void setStatusUplate(String statusUplate) {
        this.statusUplate = statusUplate;
    }
    
    
    @Override
    public String vratiNazivTabele() {
        return "pripadanjeGrupi";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
                List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
        Date dPrijave = rs.getTimestamp("pripadanjeGrupi.datumPrijave");
        String sUplate = rs.getString("pripadanjeGrupi.statusUplate");

        Edukacija r = new Edukacija();
        r.setIdEdukacija(rs.getInt("edukacija.idEdukacija"));
        r.setNaziv(rs.getString("edukacija.naziv"));
        r.setBrojCasova(rs.getInt("edukacija.brojCasova"));
        r.setCena(rs.getDouble("edukacija.cena"));
        

        Grupa g = new Grupa();
        g.setIdGrupa(rs.getInt("grupa.idGrupe"));
        g.setMaksBrojKlijenata(rs.getInt("grupa.maksBrojKlijenata"));
        g.setDatumPocetka(rs.getTimestamp("Grupa.datumPocetka"));
        g.setDatumZavrsetka(rs.getTimestamp("Grupa.datumZavrsetka"));
        g.setEdukacija(r); 

        Klijent p = new Klijent();
        p.setIdKlijent(rs.getInt("klijent.idKlijent"));
        p.setIme(rs.getString("klijent.ime"));
        p.setPrezime(rs.getString("klijent.prezime"));
        p.setImejl(rs.getString("klijent.imejl"));
        p.setBrojTelefona(rs.getString("klijent.brojTelefona"));
        p.setDatumRodjenja(rs.getTimestamp("klijent.datumRodjenja"));
        
        PripadanjeGrupi pg = new PripadanjeGrupi(g, p, dPrijave, sUplate);
        lista.add(pg);
    }
    return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
                return "idGrupe, idKlijent, datumPrijave, statusUplate";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
                return grupa.getIdGrupa() + ", " 
                + klijent.getIdKlijent() + ", '" 
                + new java.sql.Date(datumPrijave.getTime()) + "', '" 
                + statusUplate + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
                return " pripadanjeGrupi.idGrupe=" + grupa.getIdGrupa() 
                + " AND pripadanjeGrupi.idKlijent=" + klijent.getIdKlijent();
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
                    Date dPrijave = rs.getTimestamp("pripadanjeGrupi.datumPrijave");
        String sUplate = rs.getString("pripadanjeGrupi.statusUplate");

        Edukacija r = new Edukacija();
        r.setIdEdukacija(rs.getInt("edukacija.idEdukacija"));
        r.setNaziv(rs.getString("edukacija.naziv"));
        r.setBrojCasova(rs.getInt("edukacija.brojCasova"));
        r.setCena(rs.getDouble("edukacija.cena"));
        

        Grupa g = new Grupa();
        g.setIdGrupa(rs.getInt("grupa.idGrupe"));
        g.setMaksBrojKlijenata(rs.getInt("grupa.maksBrojKlijenata"));
        g.setDatumPocetka(rs.getTimestamp("Grupa.datumPocetka"));
        g.setDatumZavrsetka(rs.getTimestamp("Grupa.datumZavrsetka"));
        g.setEdukacija(r); 

        Klijent p = new Klijent();
        p.setIdKlijent(rs.getInt("klijent.idKlijent"));
        p.setIme(rs.getString("klijent.ime"));
        p.setPrezime(rs.getString("klijent.prezime"));
        p.setImejl(rs.getString("klijent.imejl"));
        p.setBrojTelefona(rs.getString("klijent.brojTelefona"));
        p.setDatumRodjenja(rs.getTimestamp("klijent.datumRodjenja"));
        
        return new PripadanjeGrupi(g, p, dPrijave, sUplate);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
                return " datumPrijave='" + new java.sql.Date(datumPrijave.getTime()) 
                + "', statusUplate='" + statusUplate + "'";
    }
    
}
