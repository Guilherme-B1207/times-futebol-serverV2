package tech.buildrun.time_futebol_server.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.*;
import tech.buildrun.time_futebol_server.entity.Jogador;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class JogadorDTO implements Serializable {


    private Long id;

    private String nome;

    private String posicao;

    private String nacionalidade;

    @JsonFormat(pattern = "yyyy-mm-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;

    private Double peso;

    private Double altura;

    private BigDecimal salario;

    private LocalDate dtIniContrato;

    private LocalDate dtfimContrato;
    public static JogadorDTO toDto(Jogador entity) {
        JogadorDTO dto = new JogadorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setPosicao(entity.getPosicao());
        dto.setNacionalidade(entity.getNacionalidade());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setPeso(entity.getPeso());
        dto.setAltura(entity.getAltura());
        return dto;
    }

    public static JogadorDTO toDtoWithTimeJogador(Jogador entity, BigDecimal salario, LocalDate dtIniContrato, LocalDate dtfimContrato) {
        JogadorDTO dto = new JogadorDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setPosicao(entity.getPosicao());
        dto.setNacionalidade(entity.getNacionalidade());
        dto.setDataNascimento(entity.getDataNascimento());
        dto.setPeso(entity.getPeso());
        dto.setAltura(entity.getAltura());
        dto.setSalario(salario);
        dto.setDtIniContrato(dtIniContrato);
        dto.setDtfimContrato(dtfimContrato);
        return dto;
    }
}
