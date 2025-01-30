package tech.buildrun.time_futebol_server.record;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record JogadorCreateRecord(String nome, String posicao, LocalDate dataNascimento, String nacionalidade, Double peso, Double altura) {
}
