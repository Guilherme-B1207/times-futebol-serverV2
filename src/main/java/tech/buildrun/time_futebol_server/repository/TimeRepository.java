package tech.buildrun.time_futebol_server.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tech.buildrun.time_futebol_server.entity.Time;

import java.util.Optional;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    @Query("SELECT t FROM Time t ORDER BY t.id DESC")
    Page<Time> listaPaginada(PageRequest pageable);

    @Query("select t from Time t where t.id = :id")
    Optional<Time> findCompleteById(@Param("id") Long id);
}
