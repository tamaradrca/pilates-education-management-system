/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.edukacija;

import domain.Edukacija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author tamaradrca
 */
public class PromeniEdukacijuSO extends ApstraktnaGenerickaOperacija{

    @Override
    protected void preduslovi(Object param) throws Exception {
           if(!(param instanceof Edukacija)){
            throw new Exception("Sistem ne moze da kreira edukaciju. Neispravan objekat.");
        }
        Edukacija e = (Edukacija) param;
        if(e.getNaziv()== null || e.getCena()<=0 || e.getBrojCasova()<=0){
            throw new Exception("Sistem ne moze da promeni edukaciju");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String key) throws Exception {
        Edukacija e = (Edukacija) param;
        broker.edit(e);
    }
    
    
    
}
