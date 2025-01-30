package tech.buildrun.time_futebol_server.record;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TimeJogadorCreateRecord(BigDecimal salario,  LocalDate dtIniContrato, LocalDate dtFimContrato) {
}
