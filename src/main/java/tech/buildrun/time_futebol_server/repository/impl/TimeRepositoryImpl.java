package tech.buildrun.time_futebol_server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tech.buildrun.time_futebol_server.entity.Time;

public class TimeRepositoryImpl {

    @Autowired
    private EntityManager entityManager;
    public Page<Time> findAllTimeByFiltro(
            Pageable pageable,
            String nome,
            String dataFundacao,
            String estado,
            String cor,
            String historia
    ) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT t from Time t where 1 = 1 ");
        if(nome!= null) {
            sql.append(" AND UNACCENT(UPPER(t.nome)) like UNACCENT(UPPER(:nome)) ");
        }
        if(dataFundacao!= null){
            sql.append(" AND t.dataFundacao = :dataFundacao ");
        }
        if(estado!= null){
            sql.append(" AND UNACCENT(UPPER(t.estado)) like UNACCENT(UPPER(:estado)) ");
        }
        if(cor!= null){
            sql.append(" AND t.cor = :cor ");
        }
        if(historia!= null){
            sql.append(" AND UNACCENT(UPPER(t.historia)) like UNACCENT(UPPER(:historia)) ");
        }
        sql.append(" order by q.id desc ");
        TypedQuery<Time> query = entityManager.createQuery(sql.toString(), Time.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        if (nome != null) {
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }
        if (dataFundacao!= null) {
            query.setParameter("dataFundacao", dataFundacao);
        }
        if (estado!= null) {
            query.setParameter("estado", "%" + estado.toUpperCase() + "%");
        }
        if (cor!= null) {
            query.setParameter("cor", cor);
        }
        if (historia!= null) {
            query.setParameter("historia", "%" + historia.toUpperCase() + "%");
        }
        TypedQuery<Long> queryCount = getCountTypedQuery(
                nome,
                dataFundacao,
                estado,
                cor,
                historia
        );

        return new PageImpl(query.getResultList(), pageable, queryCount.getSingleResult());
    }

    private TypedQuery<Long> getCountTypedQuery(
            String nome,
            String dataFundacao,
            String estado,
            String cor,
            String historia
    ) {
        StringBuilder sqlCount = new StringBuilder();
        sqlCount.append(" select count(t.id) from Time t where 1 = 1 ");
        if(nome!= null) {
            sqlCount.append(" AND UNACCENT(UPPER(t.nome)) like UNACCENT(UPPER(:nome)) ");
        }
        if(dataFundacao!= null){
            sqlCount.append(" AND t.dataFundacao = :dataFundacao ");
        }
        if(estado!= null){
            sqlCount.append(" AND UNACCENT(UPPER(t.estado)) like UNACCENT(UPPER(:estado)) ");
        }
        if(cor!= null){
            sqlCount.append(" AND t.cor = :cor ");
        }
        if(historia!= null){
            sqlCount.append(" AND UNACCENT(UPPER(t.historia)) like UNACCENT(UPPER(:historia)) ");
        }
        TypedQuery<Long> queryCount = entityManager.createQuery(sqlCount.toString(), Long.class);

        if (nome != null) {
            queryCount.setParameter("nome", "%" + nome + "%");
        }
        if (dataFundacao!= null) {
            queryCount.setParameter("dataFundacao", dataFundacao);
        }
        if (estado!= null) {
            queryCount.setParameter("estado", "%" + estado + "%");
        }
        if (cor!= null) {
            queryCount.setParameter("cor", cor);
        }
        if (historia!= null) {
            queryCount.setParameter("historia", "%" + historia + "%");
        }
        return queryCount;
    }
}

