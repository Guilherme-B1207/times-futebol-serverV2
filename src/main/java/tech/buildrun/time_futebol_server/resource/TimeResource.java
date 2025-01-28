package tech.buildrun.time_futebol_server.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.buildrun.time_futebol_server.entity.Time;
import tech.buildrun.time_futebol_server.infra.error.ErrorResponse;
import tech.buildrun.time_futebol_server.record.TimeCreateRecord;
import tech.buildrun.time_futebol_server.service.TimeService;

import java.util.Optional;

@RestController
@RequestMapping("/time")
public class TimeResource {

    @Autowired
    private TimeService timeService;

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

    @PostMapping
    public Optional<Time> salvar(@RequestBody TimeCreateRecord time){
        return timeService.salvar(time);
    }

    @DeleteMapping("/{id}")
    public Optional<Time> excluir(@PathVariable("id") Long id) {
        return timeService.excluir(id);
    }

    @PutMapping
    public Optional<Time> alterar(@RequestBody Time time) {
        return timeService.alterar(time);
    }

    @GetMapping("/pesquisa")
    public ResponseEntity<Page<Time>> pesquisa(
            @PageableDefault(size = 5) Pageable pageable,
            @RequestParam(value = "nome", required = false) String nome
    ){
        return ResponseEntity.ok().body(timeService.pesquisa(pageable, nome));
    }
}

