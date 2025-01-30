package tech.buildrun.time_futebol_server.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import tech.buildrun.time_futebol_server.entity.Jogador;

public class JogadorRepositoryImpl {
    @Autowired
    private EntityManager entityManager;

    public Page<Jogador> findAllByFiltro(
            Pageable pageable,
            String nome,
            String posicao,
            String dataNascimento,
            String nacionalidade,
            Double peso,
            Double altura
    ) {
        StringBuilder sql = new StringBuilder();
        sql.append("select j from Jogador j where 1 = 1");
        if (nome != null) {
            sql.append(" AND UNACCENT(UPPER(j.nome)) like UNACCENT(UPPER(:nome)) ");
        }
        if(posicao != null) {
                sql.append(" AND UNACCENT(UPPER(j.posicao)) like UNACCENT(UPPER(:posicao)) ");
        }
        if (dataNascimento!= null) {
            sql.append(" AND j.dataNascimento = :dataNascimento ");
        }
        if (nacionalidade!= null) {
            sql.append(" AND UNACCENT(UPPER(j.nacionalidade)) like UNACCENT(UPPER(:nacionalidade)) ");
        }
        if (peso!= null) {
            sql.append(" AND j.peso = :peso ");
        }
        if (altura!= null) {
            sql.append(" AND j.altura = :altura ");
        }
        sql.append("order by j.id desc");
        TypedQuery<Jogador> query = entityManager.createQuery(sql.toString(), Jogador.class);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        if (nome != null) {
            query.setParameter("nome", "%" + nome.toUpperCase() + "%");
        }
        TypedQuery<Long> queryCount = getCountTypedQuery(
                nome,
                posicao,
                dataNascimento,
                nacionalidade,
                peso,
                altura
        );

        return new PageImpl(query.getResultList(), pageable, queryCount.getSingleResult());
    }

    private TypedQuery<Long> getCountTypedQuery(
            String nome,
            String posicao,
            String dataNascimento,
            String nacionalidade,
            Double peso,
            Double altura
    ) {
        StringBuilder sqlCount = new StringBuilder();
        sqlCount.append(" select count(j.id) from Jogador j where 1 = 1 ");
        if (nome != null) {
            sqlCount.append(" AND UNACCENT(UPPER(j.nome)) like UNACCENT(UPPER(:nome)) ");
        }
        if(posicao != null) {
            sqlCount.append(" AND UNACCENT(UPPER(j.posicao)) like UNACCENT(UPPER(:posicao)) ");
        }
        if (dataNascimento!= null) {
            sqlCount.append(" AND j.dataNascimento = :dataNascimento ");
        }
        if (nacionalidade!= null) {
            sqlCount.append(" AND UNACCENT(UPPER(j.nacionalidade)) like UNACCENT(UPPER(:nacionalidade)) ");
        }
        if (peso!= null) {
            sqlCount.append(" AND j.peso = :peso ");
        }
        if (altura!= null) {
            sqlCount.append(" AND j.altura = :altura ");
        }

        TypedQuery<Long> queryCount = entityManager.createQuery(sqlCount.toString(), Long.class);

        if (nome != null) {
            queryCount.setParameter("nome", "%" + nome + "%");
        }
        if(posicao!= null) {
            queryCount.setParameter("posicao", "%" + posicao + "%");
        }
        if (dataNascimento!= null) {
            queryCount.setParameter("dataNascimento", dataNascimento);
        }
        if (nacionalidade!= null) {
            queryCount.setParameter("nacionalidade", "%" + nacionalidade + "%");
        }
        if (peso!= null) {
            queryCount.setParameter("peso", peso);
        }
        if (altura!= null) {
            queryCount.setParameter("altura", altura);
        }
        return queryCount;
    }
}
