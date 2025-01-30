package tech.buildrun.time_futebol_server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.time_futebol_server.entity.Time;
import tech.buildrun.time_futebol_server.entity.TimeJogador;
import tech.buildrun.time_futebol_server.infra.error.ErrorResponse;
import tech.buildrun.time_futebol_server.record.TimeCreateRecord;
import tech.buildrun.time_futebol_server.record.TimeJogadorCreateRecord;
import tech.buildrun.time_futebol_server.service.TimeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/time")
public class TimeResource {

    @Autowired
    private TimeService timeService;

    //    @Autowired
//    private JogadorService jogadorService;
//
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Time> time = timeService.getById(id);
        if (time.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Time não encontrado. Verifique o ID informado."));
        } else {
            return ResponseEntity.ok(time.get());
        }
    }

    @GetMapping("find-all")
    public ResponseEntity<?> findAll() {
        List<Time> time = timeService.findAll();
        if (time.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Times não encontrados no banco"));
        } else {
            return ResponseEntity.ok(time);
        }
    }

    @PostMapping
    public ResponseEntity<Time> salvar(@RequestBody TimeCreateRecord time) {
        Optional<Time> novoTime = timeService.salvar(time);
        return novoTime.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public Optional<Time> excluir(@PathVariable("id") Long id) {
        return timeService.excluir(id);
    }

    @PutMapping("/{id}/jogadores/{jogadorId}")
    public ResponseEntity<?> adicionarJogadorAoTime(
            @PathVariable Long id,
            @PathVariable Long jogadorId,
            @RequestBody TimeJogadorCreateRecord timeJogadorCreateRecord
    ) {
        TimeJogador timeAtualizado = timeService.adicionarJogadorAoTime(id, jogadorId, timeJogadorCreateRecord);
        if (timeAtualizado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Time não encontrado. Verifique o ID informado."));
        } else {
            return ResponseEntity.ok(timeAtualizado);
        }
    }

    @DeleteMapping("/time-jogador/{timeJogadorId}")
    public ResponseEntity<Void> removerJogadorDoTime(
            @PathVariable Long timeJogadorId
    ) {
        timeService.removerJogadorDoTime(timeJogadorId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public Optional<Time> alterar(@RequestBody Time time) {
        return timeService.alterar(time);
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<Page<Time>> pesquisa(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "dataFundacao", required = false) LocalDate dataFundacao,
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam(value = "cor", required = false) String cor,
            @RequestParam(value = "historia", required = false) String historia
    ) {
        return ResponseEntity.ok().body(timeService.pesquisa(pageable, nome, dataFundacao, estado, cor, historia));
    }
}