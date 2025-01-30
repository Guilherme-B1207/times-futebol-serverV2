package tech.buildrun.time_futebol_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tech.buildrun.time_futebol_server.entity.Jogador;

public interface JogadorRepository extends JpaRepository<Jogador, Long>, JpaSpecificationExecutor<Jogador> {
    @Query("select j from Jogador j where j.id = :id")
    Jogador findByIdJogador(@Param("id") long id);

    @Query("SELECT j FROM Jogador j ORDER BY j.id DESC")
    Page<Jogador> listaPaginada(PageRequest pageable);

}
