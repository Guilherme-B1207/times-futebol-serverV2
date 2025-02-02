package tech.buildrun.time_futebol_server.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "time")
public class Time implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_time", sequenceName = "seq_time_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_time", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_time")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Column(name = "data_fundacao")
    private LocalDate dataFundacao;

    @Column(name = "historia", columnDefinition = "text") // passa para o postgres que é sem limite de caracteres
    private String historia;

    @OneToMany(mappedBy = "time", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TimeJogador> jogadores = new ArrayList<>();

    @Column(name = "cor")
    private String cor;

    @Column(name = "estado")
    private String estado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(LocalDate dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public List<TimeJogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<TimeJogador> jogadores) {
        this.jogadores = jogadores;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
