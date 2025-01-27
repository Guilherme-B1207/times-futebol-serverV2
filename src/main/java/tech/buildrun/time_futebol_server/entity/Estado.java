package tech.buildrun.time_futebol_server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
@Entity
@Table(name = "tf_estados")
public class Estado implements Serializable {

    private static final long serialVersionUID = 1L; //para evitar problemas futuros caso deseje excluir ou adicionar novos campos

    @SequenceGenerator(name = "seq_estado", sequenceName = "seq_estado_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_estado", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_estado")
    Integer id;

    @Column(name = "nome")
    String nome;

    @Column(name = "uf", length = 2)
    String uf;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Estado cargo = (Estado) o;
        return id.equals(cargo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
