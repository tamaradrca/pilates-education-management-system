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
public class Edukacija implements ApstraktniDomenskiObjekat{
    
    private static final long serialVersionUID = 1L;
    private int idEdukacija;
    private String naziv;
    private String opis;
    private double cena;
    private int brojCasova;
    private Zaposleni zaposleni;
    private Instruktor instruktor;

    public Edukacija() {
    }

    public Edukacija(String naziv, String opis, double cena, int brojCasova, Zaposleni zaposleni, Instruktor instruktor) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.brojCasova = brojCasova;
        this.zaposleni = zaposleni;
        this.instruktor = instruktor;
    }

    public Edukacija(int idEdukacija, String naziv, String opis, double cena, int brojCasova, Zaposleni zaposleni, Instruktor instruktor) {
        this.idEdukacija = idEdukacija;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.brojCasova = brojCasova;
        this.zaposleni = zaposleni;
        this.instruktor = instruktor;
    }

    public int getIdEdukacija() {
        return idEdukacija;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public double getCena() {
        return cena;
    }

    public int getBrojCasova() {
        return brojCasova;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public Instruktor getInstruktor() {
        return instruktor;
    }

    public void setIdEdukacija(int idEdukacija) {
        this.idEdukacija = idEdukacija;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public void setBrojCasova(int brojCasova) {
        this.brojCasova = brojCasova;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public void setInstruktor(Instruktor instruktor) {
        this.instruktor = instruktor;
    }

    @Override
    public String toString() {
        return "Edukacija{" + "idEdukacija=" + idEdukacija + ", naziv=" + naziv + ", opis=" + opis + ", cena=" + cena + ", brojCasova=" + brojCasova + ", zaposleni=" + zaposleni + ", instruktor=" + instruktor + '}';
    }
    

    @Override
    public String vratiNazivTabele() {
         return " edukacija ";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        while(rs.next()){
            int id = rs.getInt("edukacija.idEdukacija");
            String naziv = rs.getString("edukacija.naziv");
            String opis = rs.getString("edukacija.opis");
            double cena = rs.getDouble("edukacija.cena");
            int brC = rs.getInt("edukacija.brojCasova");
            
            Instruktor i = new Instruktor();
            i.setIdInstruktor(rs.getInt("edukacija.idInstruktor"));
            i.setIme(rs.getString("instruktor.ime"));
            i.setPrezime(rs.getString("instruktor.prezime"));
            i.setImejl(rs.getString("instruktor.imejl"));
            
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getInt("edukacija.idZaposleni"));
            z.setIme(rs.getString("zaposleni.ime"));
            z.setPrezime(rs.getString("zaposleni.prezime"));
            z.setImejl(rs.getString("zaposleni.imejl"));
            z.setLozinka(rs.getString("zaposleni.lozinka"));
            
            Edukacija edu = new Edukacija(id,naziv,opis,cena,brC,z,i);
            
            lista.add(edu);
            
        }
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
                return "naziv, opis, cena, brojCasova, idZaposleni, idInstruktor";

    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'" + naziv + "', '" + opis + "', " + cena + ", " + brojCasova + ", " + zaposleni.getIdZaposleni() + ", " + instruktor.getIdInstruktor();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return " edukacija.idEdukacija= "+ idEdukacija;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        if(rs.next()){
            int id = rs.getInt("edukacija.idEdukacija");
            String naziv = rs.getString("edukacija.naziv");
            String opis = rs.getString("edukacija.opis");
            double cena = rs.getDouble("edukacija.cena");
            int brC = rs.getInt("edukacija.brojCasova");
            
            Instruktor i = new Instruktor();
            i.setIdInstruktor(rs.getInt("instruktor.idInstruktor"));
            i.setIme(rs.getString("instruktor.ime"));
            i.setPrezime(rs.getString("instruktor.prezime"));
            i.setImejl(rs.getString("instruktor.imejl"));
            
            Zaposleni z = new Zaposleni();
            z.setIdZaposleni(rs.getInt("zaposleni.idZaposleni"));
            z.setIme(rs.getString("zaposleni.ime"));
            z.setPrezime(rs.getString("zaposleni.prezime"));
            z.setImejl(rs.getString("zaposleni.imejl"));
            z.setLozinka(rs.getString("zaposleni.lozinka"));
            
            Edukacija edu = new Edukacija(id,naziv,opis,cena,brC,z,i);
            return edu;
        }
        return null;
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='" + naziv + "', opis='" + opis + "', cena=" + cena + ", brojCasova=" + brojCasova + ", idZaposleni=" + zaposleni.getIdZaposleni() + " , idInstruktor=" + instruktor.getIdInstruktor();
    }
    
}
