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
public class Klijent implements ApstraktniDomenskiObjekat{
    
    private int idKlijent;
    private String ime;
    private String prezime;
    private String imejl;
    private String brojTelefona;
    private Date datumRodjenja;
    private Zaposleni zaposleni;

    public Klijent() {
    }

    public Klijent(String ime, String prezime, String imejl, String brojTelefona, Date datumRodjenja, Zaposleni zaposleni) {
        this.ime = ime;
        this.prezime = prezime;
        this.imejl = imejl;
        this.brojTelefona = brojTelefona;
        this.datumRodjenja = datumRodjenja;
        this.zaposleni = zaposleni;
    }

    public Klijent(int idKlijent, String ime, String prezime, String imejl, String brojTelefona, Date datumRodjenja, Zaposleni zaposleni) {
        this.idKlijent = idKlijent;
        this.ime = ime;
        this.prezime = prezime;
        this.imejl = imejl;
        this.brojTelefona = brojTelefona;
        this.datumRodjenja = datumRodjenja;
        this.zaposleni = zaposleni;
    }

    public int getIdKlijent() {
        return idKlijent;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getImejl() {
        return imejl;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setIdKlijent(int idKlijent) {
        this.idKlijent = idKlijent;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setImejl(String imejl) {
        this.imejl = imejl;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    

    @Override
    public String vratiNazivTabele() {
        return "klijent";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("klijent.idKlijent");
            String ime = rs.getString("klijent.ime");
            String prezime = rs.getString("klijent.prezime");
            String imejl = rs.getString("klijent.imejl");
            String brojTel = rs.getString("klijent.brojTelefona");
            Date dat = rs.getDate("klijent.datumRodjenja");
            
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getInt("klijent.idZaposleni"));
            z.setIme(rs.getString("zaposleni.ime"));
            z.setPrezime(rs.getString("zaposleni.prezime"));
            z.setImejl(rs.getString("zaposleni.imejl"));
            z.setLozinka(rs.getString("zaposleni.lozinka"));
            
            Klijent k = new Klijent(id,ime,prezime,imejl,brojTel,dat,z);
            lista.add(k);
            
        }
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
           return "ime, prezime, imejl, brojTelefona, datumRodjenja, idZaposleni";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
               return "'" + ime + "', '"
                + prezime + "', '"
                + imejl + "', '"
                + brojTelefona + "', '"
                + new java.sql.Date(datumRodjenja.getTime()) + "', "
                + zaposleni.getIdZaposleni();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "klijent.idKlijent= " + idKlijent;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if (rs.next()) {
            int id = rs.getInt("klijent.idKlijent");
            String ime = rs.getString("klijent.ime");
            String prezime = rs.getString("klijent.prezime");
            String imejl = rs.getString("klijent.imejl");
            String brojTel = rs.getString("klijent.brojTelefona");
            Date dat = rs.getDate("klijent.datumRodjenja");
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getInt("zaposleni.idZaposleni"));
            z.setIme(rs.getString("zaposleni.ime"));
            z.setPrezime(rs.getString("zaposleni.prezime"));
            
            return new Klijent(id,ime,prezime,imejl,brojTel,dat,z);
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
         return "ime='" + ime
                + "', prezime='" + prezime
                + "', imejl='" + imejl
                + "', brojTelefona='" + brojTelefona
                + "', datumRodjenja='" + new java.sql.Date(datumRodjenja.getTime()) 
                + "', idZaposleni=" + zaposleni.getIdZaposleni();
    }
    
}
