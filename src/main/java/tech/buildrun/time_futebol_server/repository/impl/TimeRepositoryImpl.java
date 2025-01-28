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
    public Page<Time> findTimeByName(
            Pageable pageable,
            String nome
    ) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT t from Time t where 1 = 1 ");
        if(nome!= null) {
            sql.append(" AND UNACCENT(UPPER(t.nome)) like UNACCENT(UPPER(:nome)) ");
        }
        sql.append(" order by q.id desc ");
        TypedQuery<Time> query = entityManager.createQuery(sql.toString(), Time.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        if (nome != null) {
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }
        TypedQuery<Long> queryCount = getCountTypedQuery(
                nome
        );

        return new PageImpl(query.getResultList(), pageable, queryCount.getSingleResult());
    }

    private TypedQuery<Long> getCountTypedQuery(
            String nome
    ) {
        StringBuilder sqlCount = new StringBuilder();
        sqlCount.append(" select count(t.id) from Time t where 1 = 1 ");
        if (nome != null) {
            sqlCount.append(" AND UNACCENT(UPPER(t.nome)) like UNACCENT(UPPER(:nome)) ");
        }
        TypedQuery<Long> queryCount = entityManager.createQuery(sqlCount.toString(), Long.class);

        if (nome != null) {
            queryCount.setParameter("nome", "%" + nome + "%");
        }
        return queryCount;
    }
}

