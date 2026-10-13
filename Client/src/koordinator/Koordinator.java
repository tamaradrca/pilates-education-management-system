/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package koordinator;

import controllers.EdukacijaKreirajController;
import controllers.EdukacijaPretragaController;
import controllers.EdukacijaPromeniObrisiController;
import controllers.GrupaKreirajController;
import controllers.GrupaPretragaController;
import controllers.GrupaPromeniController;
import controllers.InstruktorKreirajController;
import controllers.InstruktorPretragaController;
import controllers.InstruktorPromeniObrisiController;
import controllers.KlijentKreirajController;
import controllers.KlijentPretragaController;
import controllers.KlijentPromeniObrisiController;
import controllers.LoginController;
import domain.Edukacija;
import domain.Grupa;
import domain.Instruktor;
import domain.Klijent;
import forms.EdukacijaKreiraj;
import forms.EdukacijaPretraga;
import forms.EdukacijaPromeniObrisi;
import forms.GrupaKreiraj;
import forms.GrupaPretraga;
import forms.GrupaPromeni;
import forms.InstruktorKreiraj;
import forms.InstruktorPretraga;
import forms.InstruktorPromeniObrisi;
import forms.KlijentKreiraj;
import forms.KlijentPretraga;
import forms.KlijentPromeniObrisi;
import forms.LogIn;

/**
 *
 * @author tamaradrca
 */
public class Koordinator {
    
    private static Koordinator instance;

    public Koordinator() {
    }

    public static Koordinator getInstance() {
        if(instance==null){
            instance = new Koordinator();
        }
        return instance;
    }
    
       public void otvoriLoginFormu() {
        LoginController loginController = new LoginController(new LogIn());
        loginController.otvoriFormu();
        
    }
       
   public void otvoriEdukacijaKreirajFormu() {
       EdukacijaKreirajController eduController = new EdukacijaKreirajController(new EdukacijaKreiraj());
       eduController.otvoriFormu();
   }
   public void otvoriEdukacijaPretragaFormu(){
       EdukacijaPretragaController e = new EdukacijaPretragaController(new EdukacijaPretraga());
       e.otvoriFormu();
   }
   public void otvoriKlijentFormu(){
       KlijentKreirajController k = new KlijentKreirajController(new KlijentKreiraj());
       k.otvoriFormu();
       
   }
   
   public void otvoriKlijentPretragaFormu(){
       KlijentPretragaController u = new KlijentPretragaController(new KlijentPretraga());
       u.otvoriFormu();
   }
   
   public void otvoriKlijentPromeniObrisiFormu(Klijent k){
       KlijentPromeniObrisiController i = new KlijentPromeniObrisiController(new KlijentPromeniObrisi());
       i.otvoriFormu(k);
       
   }
   public void otvoriGrupeFormu(){
       GrupaKreirajController g = new GrupaKreirajController(new GrupaKreiraj());
       g.otvoriFormu();
   }
   
   public void otvoriGrupePretragaFormu(){
       GrupaPretragaController gr = new GrupaPretragaController(new GrupaPretraga());
       gr.otvoriFormu();
       
   }
   public void otvoriGrupaPromeniFormu(Grupa ucitana){
       GrupaPromeniController t = new GrupaPromeniController(new GrupaPromeni());
       t.otvoriFormu(ucitana);
       
   }
   public void otvoriInstruktoriFormu(){
       InstruktorKreirajController f = new InstruktorKreirajController(new InstruktorKreiraj());
       f.otvoriFormu();
       
   }
   
   public void otvoriInstruktorPretragaFormu(){
       InstruktorPretragaController r = new InstruktorPretragaController(new InstruktorPretraga());
       r.otvoriFormu();
       
   }
  

    public void otvoriEdukacijaPromeniObrisiFormu(Edukacija ucitana) {
        EdukacijaPromeniObrisiController i = new EdukacijaPromeniObrisiController(new EdukacijaPromeniObrisi());
        i.otvoriFormu(ucitana);
        
    }

    public void otvoriPromeniInstruktor(Instruktor u) {
        InstruktorPromeniObrisiController y = new InstruktorPromeniObrisiController(new InstruktorPromeniObrisi());
        y.otvoriFormu(u);
    }
    
}
