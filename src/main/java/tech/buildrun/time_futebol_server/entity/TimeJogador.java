//package tech.buildrun.time_futebol_server.entity;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
//import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//
//import java.io.Serializable;
//import java.math.BigDecimal;
//import java.time.LocalDate;
//
//
//@Entity
//@Table(name = "time_jogador")
//@AllArgsConstructor
//public class TimeJogador implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "id_time")
//    private Time time;
//
//    @ManyToOne
//    @JoinColumn(name = "id_jogador")
//    private Jogador jogador;
//
//    @Column(name = "salario")
//    private BigDecimal salario;
//
//    @Column(name = "dt_ini_contrato")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    private LocalDate dtIniContrato;
//
//    @Column(name = "dt_fim_contrato")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    @JsonDeserialize(using = LocalDateDeserializer.class)
//    private LocalDate dtFimContrato;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public LocalDate getDtFimContrato() {
//        return dtFimContrato;
//    }
//
//    public void setDtFimContrato(LocalDate dtFimContrato) {
//        this.dtFimContrato = dtFimContrato;
//    }
//
//    public LocalDate getDtIniContrato() {
//        return dtIniContrato;
//    }
//
//    public void setDtIniContrato(LocalDate dtIniContrato) {
//        this.dtIniContrato = dtIniContrato;
//    }
//
//    public Jogador getJogador() {
//        return jogador;
//    }
//
//    public void setJogador(Jogador jogador) {
//        this.jogador = jogador;
//    }
//
//    public BigDecimal getSalario() {
//        return salario;
//    }
//
//    public void setgetSalario(BigDecimal salario) {
//        this.salario = salario;
//    }
//
//    public Time getTime() {
//        return time;
//    }
//
//    public void setTime(Time time) {
//        this.time = time;
//    }
//}
