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
public class Grupa implements ApstraktniDomenskiObjekat{

    private int idGrupa;
    private int maksBrojKlijenata;
    private Date datumPocetka;
    private Date datumZavrsetka;
    private Edukacija edukacija;
    private Zaposleni zaposleni;

    public Grupa() {
    }

    public Grupa(int maksBrojKlijenata, Date datumPocetka, Date datumZavrsetka, Edukacija edukacija, Zaposleni zaposleni) {
        this.maksBrojKlijenata = maksBrojKlijenata;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.edukacija = edukacija;
        this.zaposleni = zaposleni;
    }

    public Grupa(int idGrupa, int maksBrojKlijenata, Date datumPocetka, Date datumZavrsetka, Edukacija edukacija, Zaposleni zaposleni) {
        this.idGrupa = idGrupa;
        this.maksBrojKlijenata = maksBrojKlijenata;
        this.datumPocetka = datumPocetka;
        this.datumZavrsetka = datumZavrsetka;
        this.edukacija = edukacija;
        this.zaposleni = zaposleni;
    }

    public int getIdGrupa() {
        return idGrupa;
    }

    public int getMaksBrojKlijenata() {
        return maksBrojKlijenata;
    }

    public Date getDatumPocetka() {
        return datumPocetka;
    }

    public Date getDatumZavrsetka() {
        return datumZavrsetka;
    }

    public Edukacija getEdukacija() {
        return edukacija;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setIdGrupa(int idGrupa) {
        this.idGrupa = idGrupa;
    }

    public void setMaksBrojKlijenata(int maksBrojKlijenata) {
        this.maksBrojKlijenata = maksBrojKlijenata;
    }

    public void setDatumPocetka(Date datumPocetka) {
        this.datumPocetka = datumPocetka;
    }

    public void setDatumZavrsetka(Date datumZavrsetka) {
        this.datumZavrsetka = datumZavrsetka;
    }

    public void setEdukacija(Edukacija edukacija) {
        this.edukacija = edukacija;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    @Override
    public String toString() {
        return "Grupa{" + "idGrupa=" + idGrupa + ", maksBrojKlijenata=" + maksBrojKlijenata + ", datumPocetka=" + datumPocetka + ", datumZavrsetka=" + datumZavrsetka + ", edukacija=" + edukacija + ", zaposleni=" + zaposleni + '}';
    }
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "grupa";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while (rs.next()) {
            int idG = rs.getInt("grupa.idGrupe");
            
            int maxP = rs.getInt("grupa.maksBrojKlijenata");
            Date dPocetka = rs.getTimestamp("grupa.datumPocetka");
            Date dZavrsetka = rs.getTimestamp("grupa.datumZavrsetka");

           Edukacija e = new Edukacija();
            e.setIdEdukacija(rs.getInt("grupa.idEdukacija"));
            e.setNaziv(rs.getString("edukacija.naziv"));
            e.setOpis(rs.getString("edukacija.opis"));
            e.setBrojCasova(rs.getInt("edukacija.brojCasova"));
            e.setCena(rs.getDouble("edukacija.cena"));
            

            Zaposleni s = new Zaposleni();
            s.setIdZaposleni(rs.getInt("grupa.idZaposleni"));
            s.setIme(rs.getString("Zaposleni.ime"));
            s.setPrezime(rs.getString("Zaposleni.prezime"));

            Grupa g = new Grupa(idG,maxP, dPocetka, dZavrsetka, e, s);
            lista.add(g);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
                return " maksBrojKlijenata, datumPocetka, datumZavrsetka, idEdukacija, idZaposleni";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
                return " " + maksBrojKlijenata + ", '" 
                + new java.sql.Date(datumPocetka.getTime()) + "', '" 
                + new java.sql.Date(datumZavrsetka.getTime()) + "', " 
                + edukacija.getIdEdukacija() + ", " 
                + zaposleni.getIdZaposleni();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return " grupa.idGrupe= " + idGrupa;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
            
            int idG = rs.getInt("grupa.idGrupe");
            
            int maxP = rs.getInt("grupa.maksBrojKlijenata");
            Date dPocetka = rs.getTimestamp("grupa.datumPocetka");
            Date dZavrsetka = rs.getTimestamp("grupa.datumZavrsetka");

           Edukacija e = new Edukacija();
            e.setIdEdukacija(rs.getInt("grupa.idEdukacija"));
            e.setNaziv(rs.getString("edukacija.naziv"));
            e.setOpis(rs.getString("edukacija.opis"));
            e.setBrojCasova(rs.getInt("edukacija.brojCasova"));
            e.setCena(rs.getDouble("edukacija.cena"));
            

            Zaposleni s = new Zaposleni();
            s.setIdZaposleni(rs.getInt("grupa.idZaposleni"));
            s.setIme(rs.getString("Zaposleni.ime"));
            s.setPrezime(rs.getString("Zaposleni.prezime"));

            return new Grupa(idG,maxP, dPocetka, dZavrsetka, e, s);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
                return " maksBrojKlijenata=" + maksBrojKlijenata 
                + ", datumPocetka='" + new java.sql.Date(datumPocetka.getTime()) 
                + "', datumZavrsetka='" + new java.sql.Date(datumZavrsetka.getTime()) 
                + "', idEdukacija=" + edukacija.getIdEdukacija()
                + ", idZaposleni=" + zaposleni.getIdZaposleni();
    }
    
}
