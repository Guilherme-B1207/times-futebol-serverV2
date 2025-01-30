package tech.buildrun.time_futebol_server.record;

import tech.buildrun.time_futebol_server.dto.JogadorDTO;
import tech.buildrun.time_futebol_server.entity.Jogador;

//import tech.buildrun.time_futebol_server.dto.JogadorDTO;
import tech.buildrun.time_futebol_server.entity.Jogador;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record TimeCreateRecord(String nome, LocalDate dataFundacao, String historia, String cor, String estado, List<JogadorDTO> jogadores){
}
