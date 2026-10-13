/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Edukacija;
import domain.Grupa;
import domain.Instruktor;
import domain.Klijent;
import domain.PripadanjeGrupi;
import domain.Zaposleni;
import java.util.ArrayList;
import java.util.List;
import operacije.edukacija.KreirajEdukacijuSO;
import operacije.edukacija.ObrisiEdukacijuSO;
import operacije.edukacija.PretragaEdukacijaSO;
import operacije.edukacija.PromeniEdukacijuSO;
import operacije.edukacija.UcitajEdukacijuSO;
import operacije.edukacija.VratiListuEdukacijaSO;
import operacije.grupa.KreirajGrupuSO;
import operacije.grupa.PretragaGrupeSO;
import operacije.grupa.PromeniGrupuSO;
import operacije.grupa.UcitajGrupuSO;
import operacije.grupa.VratiListuGrupaSO;
import operacije.instruktor.KreirajInstruktoraSO;
import operacije.instruktor.ObrisiInstruktoraSO;
import operacije.instruktor.PretraziInstruktoraSO;
import operacije.instruktor.PromeniInstruktoraSO;
import operacije.instruktor.VratiListuInstruktoraSO;
import operacije.klijent.KreirajKlijentSO;
import operacije.klijent.ObrisiKlijentaSO;
import operacije.klijent.PretragaKlijentSO;
import operacije.klijent.PromeniKlijentSO;
import operacije.klijent.UcitajKlijentaSO;
import operacije.klijent.VratiListuKlijenataSO;
import operacije.pripadanjegrupi.VratiListuPripadanjaSO;
import operacije.zaposleni.LoginSO;

/**
 *
 * @author tamaradrca
 */
public class Controller {
    
    private static Controller instance;
    private List<Zaposleni> ulogovani = new ArrayList<>();

    public Controller() {
    }

    public static Controller getInstance() {
        if(instance==null){
            instance = new Controller();
        }
        return instance;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        LoginSO loginso = new LoginSO();
        loginso.izvrsi(z, null);
        
        for(Zaposleni u : ulogovani){
            if(u.getImejl().equalsIgnoreCase(z.getImejl())){
                throw new Exception("Korisnik je vec prijavljen.");
            }
        }
        
        Zaposleni ulogovan = loginso.getZaposleni();
        ulogovani.add(ulogovan);
        return ulogovan;
    }

    public List<Instruktor> vratiListuInstruktora() throws Exception {
        VratiListuInstruktoraSO vratiInstruktoreSo = new VratiListuInstruktoraSO();
        vratiInstruktoreSo.izvrsi(null, null);
        return vratiInstruktoreSo.getListaInstruktora();
    }

    public void kreirajEdukaciju(Edukacija e) throws Exception {
        KreirajEdukacijuSO kreirajSo = new KreirajEdukacijuSO();
        kreirajSo.izvrsi(e, null);
        
    }

    public List vratiListuEdukacija() throws Exception {
        VratiListuEdukacijaSO vratiEduSo = new VratiListuEdukacijaSO();
        vratiEduSo.izvrsi(null, null);
        return vratiEduSo.getListaEdukacija();
        
    }

    public List<Edukacija> pretragaEdukacija(String ime) throws Exception {
        PretragaEdukacijaSO pre = new PretragaEdukacijaSO();
        pre.izvrsi(ime, null);
        return pre.getListaEdukacija();
        
    }

    public Edukacija ucitajEdukaciju(Edukacija i) throws Exception {
        UcitajEdukacijuSO ucitaj = new UcitajEdukacijuSO();
        ucitaj.izvrsi(i, null);
        return ucitaj.getE();
    }

    public void promeniEdukaciju(Edukacija v) throws Exception {
        PromeniEdukacijuSO pro = new PromeniEdukacijuSO();
        pro.izvrsi(v, null);
    }

    public void obrisiEdukaciju(Edukacija h) throws Exception {
        ObrisiEdukacijuSO ss = new ObrisiEdukacijuSO();
        ss.izvrsi(h, null);
    }

    public void kreirajKlijenta(Klijent k) throws Exception {
        KreirajKlijentSO kso = new KreirajKlijentSO();
        kso.izvrsi(k, null);
        
    }

    public List<Klijent> pretragaKlijent(String ime) throws Exception {
        PretragaKlijentSO pkso = new PretragaKlijentSO();
        pkso.izvrsi(ime, null);
        return pkso.getLista();
        
    }

    public List<Klijent> vratiListuKlijenata() throws Exception {
        VratiListuKlijenataSO vlkso = new VratiListuKlijenataSO();
        vlkso.izvrsi(null, null);
        return vlkso.getSviKlijenti();
    }

    public Klijent ucitajKlijenta(Klijent zaUcit) throws Exception {
        UcitajKlijentaSO ukso = new UcitajKlijentaSO();
        ukso.izvrsi(zaUcit, null);
        return ukso.getK();
    }

    public void promeniKlijenta(Klijent zaIzmenu) throws Exception {
        PromeniKlijentSO pkso = new PromeniKlijentSO();
        pkso.izvrsi(zaIzmenu, null);
        
    }

    public void obrisiKlijenta(Klijent zaBrisanje) throws Exception {
        ObrisiKlijentaSO okso = new ObrisiKlijentaSO();
        okso.izvrsi(zaBrisanje, null);
        
    }

    public void kreirajGrupu(Object[] niz) throws Exception {
        KreirajGrupuSO kgso = new KreirajGrupuSO();
        kgso.izvrsi(niz, null);
    }

    public List<Grupa> vratiListuGrupa() throws Exception {
        VratiListuGrupaSO vsgso = new VratiListuGrupaSO();
        vsgso.izvrsi(null, null);
        return vsgso.getListaGrupa();
        
    }

    public List<Grupa> pretragaGrupe(String nazivEd) throws Exception {
        PretragaGrupeSO pgso = new PretragaGrupeSO();
        pgso.izvrsi(nazivEd, null);
        return pgso.getPretGrupe();
    }

    public Grupa ucitajGrupu(Grupa ucitaj) throws Exception {
        UcitajGrupuSO ugso = new UcitajGrupuSO();
        ugso.izvrsi(ucitaj, null);
        return ugso.getUcitana();
    }

    public List<PripadanjeGrupi> vratiListuPripadanja(Grupa g) throws Exception {
        VratiListuPripadanjaSO vslp = new VratiListuPripadanjaSO();
        vslp.izvrsi(g, null);
        return vslp.getListaP();
    }

    public void promeniGrupu(Object[] niz) throws Exception {
        PromeniGrupuSO pgso = new PromeniGrupuSO();
        pgso.izvrsi(niz, null);
    }

    public void kreirajIns(Instruktor d) throws Exception {
        KreirajInstruktoraSO kiso = new KreirajInstruktoraSO();
        kiso.izvrsi(d, null);
    }

    public void odjavi(Zaposleni y) {
        Zaposleni log = null;
        for(Zaposleni z : ulogovani){
            if(z.getIdZaposleni()==y.getIdZaposleni()){
                log = y;
            }
        }
        ulogovani.remove(log);
    }

    public List<Instruktor> pretragaIns(String ime) throws Exception {
        PretraziInstruktoraSO piso = new PretraziInstruktoraSO();
        piso.izvrsi(ime, null);
        return piso.getLista();
    }

    public void promeniIns(Instruktor q) throws Exception {
        PromeniInstruktoraSO pisso = new PromeniInstruktoraSO();
        pisso.izvrsi(q, null);
        
    }

    public void obrisiIns(Instruktor w) throws Exception {
        ObrisiInstruktoraSO oiso = new ObrisiInstruktoraSO();
        oiso.izvrsi(w, null);
    }
    
    
    
}
