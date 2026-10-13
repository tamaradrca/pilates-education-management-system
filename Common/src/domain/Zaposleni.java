/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tamaradrca
 */
public class Zaposleni implements ApstraktniDomenskiObjekat{

    private int idZaposleni;
    private String ime;
    private String prezime;
    private String imejl;
    private String lozinka;

    public Zaposleni() {
    }

    public Zaposleni(String ime, String prezime, String imejl, String lozinka) {
        this.ime = ime;
        this.prezime = prezime;
        this.imejl = imejl;
        this.lozinka = lozinka;
    }

    public Zaposleni(String imejl, String lozinka) {
        this.imejl = imejl;
        this.lozinka = lozinka;
    }
    
    

    public Zaposleni(int idZaposleni, String ime, String prezime, String imejl, String lozinka) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.imejl = imejl;
        this.lozinka = lozinka;
    }

    public int getIdZaposleni() {
        return idZaposleni;
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

    public String getLozinka() {
        return lozinka;
    }

    public void setIdZaposleni(int idZaposleni) {
        this.idZaposleni = idZaposleni;
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

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.ime, other.ime)) {
            return false;
        }
        if (!Objects.equals(this.imejl, other.imejl)) {
            return false;
        }
        return Objects.equals(this.lozinka, other.lozinka);
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("idZaposleni");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String imejl = rs.getString("imejl");
            String lozinka = rs.getString("lozinka");
            Zaposleni z = new Zaposleni(id,ime,prezime,imejl,lozinka);
            lista.add(z);
        }
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, imejl, lozinka";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + ime + "' , ' "+ prezime + "' , '" + imejl + "' , '" + lozinka + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "Zaposleni.idZaposleni= " + idZaposleni;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
            int id = rs.getInt("idZaposleni");
            String ime = rs.getString("ime");
            String prezime = rs.getString("prezime");
            String imejl = rs.getString("imejl");
            String lozinka = rs.getString("lozinka");
            Zaposleni z = new Zaposleni(id,ime,prezime,imejl,lozinka);
            return z;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime= '" + ime + "' , prezime= '" + prezime + "' , imejl='" + imejl + "' , lozinka='" + lozinka + "'";
    }
    
}
