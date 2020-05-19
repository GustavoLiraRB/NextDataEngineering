/*package dao;

import javax.persistence.StoredProcedureQuery;

import model.StoredProcedure;


public class StoredProcedureDao <A> extends GenericDao<StoredProcedure, Long> {
    public StoredProcedureDao() {
        super(StoredProcedure.class);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void atualizarRegistro () {


        entityManager.getTransaction().begin();
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("atualizacaoRegistro")


        query.execute();

        entityManager.getTransaction().commit();

    }*/

