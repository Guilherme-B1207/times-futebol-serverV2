package tech.buildrun.time_futebol_server.dto;


import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class TimeDTO {
    String id;
    String nome;
    LocalDate dataFundacao;
    String historia;
    String cor;
    String estado;
    List<Jogador> jogador;

    public static TimeDTO toDto(Time entity) {
        TimeDTO dto = new TimeDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDataFundacao(entity.getDataFundacao());
        dto.setHistoria(entity.getHistoria());
        dto.setCor(entity.getCor());
        dto.setEstado(entity.getEstado());
        dto.setJogador(entity.getJogador());
        
        return dto;
    }
}


