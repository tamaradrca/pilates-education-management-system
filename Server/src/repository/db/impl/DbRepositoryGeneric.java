/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.ApstraktniDomenskiObjekat;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbRepository;
import repository.db.dbConnectionFactory;
/**
 *
 * @author tamaradrca
 */
public class DbRepositoryGeneric implements DbRepository<ApstraktniDomenskiObjekat>{

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        
        String query = "SELECT * FROM " + param.vratiNazivTabele();
        if(uslov!=null){
            query += uslov;
        }
        System.out.println(query);
        Statement st = dbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs  = st.executeQuery(query);
        lista = param.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
        
    }

    @Override
    public PreparedStatement add(ApstraktniDomenskiObjekat param) throws Exception {
        
        String query = "INSERT INTO " + param.vratiNazivTabele() + " (" + param.vratiKoloneZaUbacivanje() + " ) VALUES ( " + param.vratiVrednostiZaUbacivanje() + ")";
        
        System.out.println(query);
        
        PreparedStatement ps = dbConnectionFactory.getInstance().getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        
        ps.executeUpdate();
        
        return ps;
        
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        
        String query = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu() + " WHERE " + param.vratiPrimarniKljuc();
        
        System.out.println("query");
        
        Statement st = dbConnectionFactory.getInstance().getConnection().createStatement();
        
        st.executeUpdate(query);
        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String query = "DELETE FROM " + param.vratiNazivTabele() + " WHERE " + param.vratiPrimarniKljuc();
        Statement st = dbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(query);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();
        return lista;
    }

    @Override
    public ApstraktniDomenskiObjekat get(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        
        ApstraktniDomenskiObjekat novi = null;
        String query = "SELECT * FROM " + param.vratiNazivTabele();
        if(uslov!=null){
            query += uslov;
        }
        System.out.println(query);
        Statement st = dbConnectionFactory.getInstance().getConnection().createStatement();
        
        ResultSet rs = st.executeQuery(query);
        List<ApstraktniDomenskiObjekat> lista = param.vratiListu(rs);
       
        if(!lista.isEmpty()){
            novi = lista.get(0);
            System.out.println("Nadjen zaposlen.");
        }
        rs.close();
        st.close();
        
        return novi;
    }

    

  
    
}
