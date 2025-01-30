package tech.buildrun.time_futebol_server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.time_futebol_server.entity.Jogador;
import tech.buildrun.time_futebol_server.entity.Time;
import tech.buildrun.time_futebol_server.infra.error.ErrorResponse;
import tech.buildrun.time_futebol_server.record.JogadorCreateRecord;
import tech.buildrun.time_futebol_server.record.TimeCreateRecord;
import tech.buildrun.time_futebol_server.service.JogadorService;
import tech.buildrun.time_futebol_server.service.TimeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/jogador")
public class JogadorResource {

    @Autowired
    private TimeService timeService;

    @Autowired
    private JogadorService jogadorService;


//    @GetMapping("/{id}/jogadores")
//    public ResponseEntity<List<Jogador>> listarJogadoresDoTime(@PathVariable Long id) {
//        List<Jogador> jogadores = jogadorService.listarJogadoresDoTime(id);
//        return ResponseEntity.ok(jogadores);
//    }

    @PostMapping
    public ResponseEntity<Jogador> salvar(@RequestBody JogadorCreateRecord jogador) {
        Optional<Jogador> novoJogador = jogadorService.salvar(jogador);
        return novoJogador.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Optional<Jogador> jogador = jogadorService.getById(id);
        if (jogador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Time não encontrado. Verifique o ID informado."));
        } else {
            return ResponseEntity.ok(jogador.get());
        }
    }

    @GetMapping("find-all")
    public ResponseEntity<?> findAll() {
        List<Jogador> jogador = jogadorService.findAll();
        if (jogador.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Jogadores não encontrados no banco"));
        } else {
            return ResponseEntity.ok(jogador);
        }
    }

    @PutMapping
    public Optional<Jogador> alterar(@RequestBody Jogador jogador) {
        return jogadorService.alterar(jogador);
    }

    @DeleteMapping("/{id}")
    public Optional<Jogador> excluir(@PathVariable("id") Long id) {
        return jogadorService.excluir(id);
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<Page<Jogador>> pesquisa(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "dataFundacao", required = false) String posicao,
            @RequestParam(value = "estado", required = false) String dataNascimento,
            @RequestParam(value = "cor", required = false) String nacionalidade,
            @RequestParam(value = "historia", required = false) Double peso,
            @RequestParam(value = "altura", required = false) Double altura
    ){
        return ResponseEntity.ok().body(jogadorService.pesquisa( pageable, nome, posicao, dataNascimento, nacionalidade, peso, altura));
    }
}
