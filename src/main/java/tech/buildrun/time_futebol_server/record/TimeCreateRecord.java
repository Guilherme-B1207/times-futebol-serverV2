package tech.buildrun.time_futebol_server.record;

//import tech.buildrun.time_futebol_server.entity.Jogador;

import tech.buildrun.time_futebol_server.entity.Jogador;

import java.time.LocalDateTime;
import java.util.List;

public record TimeCreateRecord(String nome, LocalDate dataFundacao, String historia, String cor, String estado, List<Jogador> jogadores) {
}
