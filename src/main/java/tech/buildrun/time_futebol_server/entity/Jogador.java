//package tech.buildrun.time_futebol_server.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "jogador")
//public class Jogador implements Serializable {
//
//    @Id
//    @SequenceGenerator(name = "seq_jogador", sequenceName = "seq_jogador_id", allocationSize = 1)
//    @GeneratedValue(generator = "seq_jogador", strategy = GenerationType.SEQUENCE)
//    @Column(name = "id_jogador")
//    private Long id;
//
//    @Column(name = "nome", nullable = false)
//    private String nome;
//
//    @ManyToOne
//    @JoinColumn(name = "posicao", referencedColumnName = "id", nullable = false)
//    private String posicao;
//
//    @JsonFormat(shape = JsonFormat.Shape.STRING)
//    @Column(name = "data_nascimento")
//    private LocalDateTime dataNascimento;
//
//    @Column(name = "nacionalidade", nullable = false)
//    private String nacionalidade;
//
//    @Column(name = "peso", nullable = false, columnDefinition = "numeric(6,3)")
//    private Double peso;
//
//    @Column(name = "peso", nullable = false, columnDefinition = "numeric(4,2)")
//    private Double altura;
//
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getNome() {
//        return nome;
//    }
//
//    public void setNome(String nome) {
//        this.nome = nome;
//    }
//
//    public String getPosicao() {
//        return posicao;
//    }
//
//    public void setPosicao(String posicao) {
//        this.posicao = posicao;
//    }
//
//    public LocalDateTime getDataNascimento() {
//        return dataNascimento;
//    }
//
//    public void setDataNascimento(LocalDateTime dataNascimento) {
//        this.dataNascimento = dataNascimento;
//    }
//
//    public String getNacionalidade() {
//        return nacionalidade;
//    }
//
//    public void setNacionalidade(String nacionalidade) {
//        this.nacionalidade = nacionalidade;
//    }
//
//    public Double getPeso() {
//        return peso;
//    }
//
//    public void setPeso(Double peso) {
//        this.peso = peso;
//    }
//
//    public Double getAltura() {
//        return altura;
//    }
//
//    public void setAltura(Double altura) {
//        this.altura = altura;
//    }
//}
