package tech.buildrun.time_futebol_server.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tech.buildrun.time_futebol_server.entity.Time;
import tech.buildrun.time_futebol_server.infra.error.ErrorResponse;
import tech.buildrun.time_futebol_server.record.TimeCreateRecord;
import tech.buildrun.time_futebol_server.repository.TimeRepository;
import tech.buildrun.time_futebol_server.repository.impl.TimeRepositoryImpl;

import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    final TimeRepository timeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepositoryImpl timeRepositoryImpl;

    public TimeService(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @Transactional
    public Optional<Time> salvar(TimeCreateRecord time) {
        Time TimeToSave = new Time();
        TimeToSave.setNome(time.nome());
        TimeToSave.setDataFundacao(time.dataFundacao());
        TimeToSave.setHistoria(time.historia());
        TimeToSave.setCor(time.cor());
        TimeToSave.setEstado(time.estado());
        if (time.jogadores() != null && !time.jogadores().isEmpty()) {
            List<Jogador> jogadoresSalvos = jogadorRepository.saveAll(time.jogadores());
            timeToSave.setJogadores(jogadoresSalvos);
        }

        return Optional.of(this.timeRepository.save(TimeToSave));
    }

    @Transactional
    public Optional<Time> excluir(Long id) {
        Optional<Time> funcaoOptional = timeRepository.findById(id);
        funcaoOptional.ifPresent(this.timeRepository::delete);
        return funcaoOptional;
    }

    @Transactional
    public Optional<Time> alterar(Time time) {
        Optional<Time> optional = timeRepository.findById(time.getId());
        optional.get().setNome(time.getNome());
        optional.get().setDataFundacao(time.getDataFundacao());
        optional.get().setHistoria(time.getHistoria());
        optional.get().setCor(time.getCor());
        optional.get().setEstado(time.getEstado());
        return Optional.of(this.timeRepository.save(optional.get()));
    }

    public Optional<Time> getById(Long id) {
       return timeRepository.findById(id);
    }

    public List<Time> findAll() {
        return timeRepository.findAll();
    }

    public Page<Time> pesquisa(
            Pageable pageable,
            String nome,
            String dataFundacao,
            String estado,
            String cor,
            String historia
    ) {
        return timeRepositoryImpl.findAllTimeByFiltro(
                pageable,
                nome,
                dataFundacao,
                estado,
                cor,
                historia
        );
        @Transactional
        public Time adicionarJogadorAoTime(Long id, Jogador jogadorNovo) {
            Time time = timeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));
            if (jogadorNovo.getNome() == null || jogadorNovo.getPosicao() == null) {
                throw new RuntimeException("Dados do jogador inválidos");
            }
            jogadorNovo.setTime(time);
            jogadorRepository.save(jogadorNovo);
            time.getJogadores().add(jogadorNovo);
            return timeRepository.save(time);
        }


        @Transactional
        public List<Jogador> listarJogadoresDoTime(Long id) {
            Time time = timeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));
            return time.getJogadores();
        }
        @Transactional
        public Time removerJogadorDoTime(Long id, Long id) {
            Time time = timeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));
            Jogador jogador = jogadorRepository.findById(jogadorId)
                    .orElseThrow(() -> new RuntimeException("Jogador não encontrado com ID: " + id));
            if (!time.getJogadores().contains(jogador)) {
                throw new RuntimeException("O jogador não pertence a este time.");
            }
            time.getJogadores().remove(jogador);
            jogador.setTime(null);
            jogadorRepository.save(jogador);
            return timeRepository.save(time);
        }
    }
//    public void adicionarJogador(Jogador jogador) {
//        jogador.setTime(this);
//        this.jogadores.add(jogador);
//    }
//
//    public void removerJogador(int index) {
//        this.jogadores.remove(index);
//    }
}
