package tech.buildrun.time_futebol_server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.buildrun.time_futebol_server.entity.TimeJogador;

@Repository
public interface TimeJogadorRepository extends JpaRepository<TimeJogador, Long> {

}
