/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tamaradrca
 */
public class Instruktor implements ApstraktniDomenskiObjekat{

    private int idInstruktor;
    private String ime;
    private String prezime;
    private String imejl;

    public Instruktor() {
    }

    public Instruktor(String ime, String prezime, String imejl) {
        this.ime = ime;
        this.prezime = prezime;
        this.imejl = imejl;
    }

    public Instruktor(int idInstruktor, String ime, String prezime, String imejl) {
        this.idInstruktor = idInstruktor;
        this.ime = ime;
        this.prezime = prezime;
        this.imejl = imejl;
    }

    public int getIdInstruktor() {
        return idInstruktor;
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

    public void setIdInstruktor(int idInstruktor) {
        this.idInstruktor = idInstruktor;
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
    
    
    @Override
    public String vratiNazivTabele() {
        return "instruktor";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("instruktor.idInstruktor");
            String ime = rs.getString("instruktor.ime");
            String prezime = rs.getString("instruktor.prezime");
            String imejl = rs.getString("instruktor.imejl");
            
            Instruktor i = new Instruktor(id,ime,prezime,imejl);
            lista.add(i);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime,prezime,imejl";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
              return "'" + ime + "', '" + prezime + "', '" + imejl + "'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "instruktor.idInstruktor=" + idInstruktor;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
            int id = rs.getInt("instruktor.idInstruktor");
             String ime = rs.getString("instruktor.ime");
            String prezime = rs.getString("instruktor.prezime");
            String imejl = rs.getString("instruktor.imejl");
            Instruktor i = new Instruktor(id,ime,prezime,imejl);
            return i;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
             return "ime='" + ime + "', prezime='" + prezime + "', imejl='" + imejl + "'";
    }
    
    
}
