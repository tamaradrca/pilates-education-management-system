/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.db;

import repository.Repository;

/**
 *
 * @author tamaradrca
 */
public interface DbRepository<T> extends Repository<T> {
    
    default public void connect() throws Exception {
        dbConnectionFactory.getInstance().getConnection();
    }

    default public void disconnect() throws Exception {
        dbConnectionFactory.getInstance().getConnection().close();
    }

    default public void commit() throws Exception {
        dbConnectionFactory.getInstance().getConnection().commit();
    }

    default public void rollback() throws Exception {
        dbConnectionFactory.getInstance().getConnection().rollback();
    }
    
}
