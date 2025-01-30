package tech.buildrun.time_futebol_server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tech.buildrun.time_futebol_server.entity.Time;
import java.time.LocalDate;

public class TimeRepositoryImpl {

    @Autowired
    private EntityManager entityManager;

    public Page<Time> findAllTimeByFiltro(
            Pageable pageable,
            String nome,
            LocalDate dataFundacao,
            String estado,
            String cor,
            String historia
    ) {
        StringBuilder hql = new StringBuilder();
        hql.append(
                " SELECT t "
        );
        hql.append(" FROM Time t ");
        hql.append(getWheres(nome, dataFundacao, estado, cor, historia));
        hql.append(" ORDER BY t.id DESC ");

        TypedQuery<Time> query = entityManager.createQuery(
                hql.toString(),
                Time.class
        );
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());
        setParameters(nome, dataFundacao, estado, cor, historia, query);

        TypedQuery<Long> queryCount = getCountTypedQuery(nome, dataFundacao, estado, cor, historia);
        return new PageImpl(query.getResultList(), pageable, queryCount.getSingleResult());
    }

    private String getWheres(String nome,
                              LocalDate dataFundacao,
                              String estado,
                              String cor,
                              String historia) {
        StringBuilder where = new StringBuilder();
        where.append(" WHERE 1 = 1 ");

        if (nome != null) {
            where.append(" AND t.nome LIKE :nome ");
        }

//        if (!isEmpty(filter.getAcronym())) {
//            where.append(" AND d.acronym LIKE :acronym ");
//        }
//
//        if (!isEmpty(filter.getPhase())) {
//            where.append(" AND d.phase = :phase ");
//        }
        return where.toString();
    }

    private void setParameters(String nome,
                               LocalDate dataFundacao,
                               String estado,
                               String cor,
                               String historia, TypedQuery<?> query) {
        if (nome != null) {
            query.setParameter("nome", "%" + nome + "%");
        }
//        if (!isEmpty(filter.getAcronym())) {
//            query.setParameter("acronym", "%" +filter.getAcronym() + "%");
//        }
//        if (!isEmpty(filter.getPhase())) {
//            query.setParameter("phase", filter.getPhase());
//
//        }
    }

    private TypedQuery<Long> getCountTypedQuery(String nome,
                                                LocalDate dataFundacao,
                                                String estado,
                                                String cor,
                                                String historia) {
        StringBuilder hql = new StringBuilder();
        hql.append(" SELECT COUNT(t.id) ");
        hql.append(" FROM Time t ");
        hql.append(getWheres(nome, dataFundacao, estado, cor, historia));

        TypedQuery<Long> query = entityManager.createQuery(hql.toString(), Long.class);
        setParameters(nome, dataFundacao, estado, cor, historia, query);
        return query;
    }
}