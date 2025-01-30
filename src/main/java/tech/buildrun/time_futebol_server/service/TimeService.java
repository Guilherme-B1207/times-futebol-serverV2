package tech.buildrun.time_futebol_server.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import tech.buildrun.time_futebol_server.dto.JogadorDTO;
import tech.buildrun.time_futebol_server.entity.Jogador;
import tech.buildrun.time_futebol_server.entity.Time;
import tech.buildrun.time_futebol_server.entity.TimeJogador;
import tech.buildrun.time_futebol_server.record.TimeCreateRecord;
import tech.buildrun.time_futebol_server.record.TimeJogadorCreateRecord;
import tech.buildrun.time_futebol_server.repository.JogadorRepository;
import tech.buildrun.time_futebol_server.repository.TimeJogadorRepository;
import tech.buildrun.time_futebol_server.record.TimeCreateRecord;
import tech.buildrun.time_futebol_server.record.TimeJogadorCreateRecord;
import tech.buildrun.time_futebol_server.repository.JogadorRepository;
import tech.buildrun.time_futebol_server.repository.TimeJogadorRepository;
import tech.buildrun.time_futebol_server.repository.TimeRepository;
import tech.buildrun.time_futebol_server.repository.impl.TimeRepositoryImpl;
import tech.buildrun.time_futebol_server.repository.impl.TimeRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TimeService {

    final TimeRepository timeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TimeJogadorRepository timeJogadorRepository;

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
        Time timeSaved =  this.timeRepository.save(TimeToSave);
        if (time.jogadores() != null && !time.jogadores().isEmpty()) {
            List<Jogador> jogadoresSalvos = jogadorRepository.saveAll(time.jogadores());
            jogadoresSalvos.forEach(jogador -> {
                TimeJogador timeJogador = new TimeJogador(
                        null,
                        timeSaved,
                        jogador,
                        timeJogador.salario(),
                        timeJogador.dtIniContrato(),
                        timeJogador.dtFimContrato(),
                        false
                );
                this.timeJogadorRepository.save(timeJogador);
            });
        }
        return Optional.of(timeSaved);
    }

    @Transactional
    public Optional<Time> salvarComJogador(TimeCreateRecord time) {
        Time TimeToSave = new Time();
        TimeToSave.setNome(time.nome());
        TimeToSave.setDataFundacao(time.dataFundacao());
        TimeToSave.setHistoria(time.historia());
        TimeToSave.setCor(time.cor());
        TimeToSave.setEstado(time.estado());
        Time timeSaved =  this.timeRepository.save(TimeToSave);
        if (time.jogadores() != null && !time.jogadores().isEmpty()) {
            List<Jogador> jogadoresSalvos = jogadorRepository.saveAll(time.jogadores());
            jogadoresSalvos.forEach(jogador -> {
                TimeJogador timeJogador = new TimeJogador(
                        null,
                        timeSaved,
                        jogador,
                        timeJogador.salario(),
                        timeJogador.dtIniContrato(),
                        timeJogador.dtFimContrato(),
                        false
                );
                this.timeJogadorRepository.save(timeJogador);
            });
        }
        return Optional.of(timeSaved);
    }

    @Transactional
    public TimeJogador adicionarJogadorAoTime(Long timeId, Long jogadorId, TimeJogadorCreateRecord timeJogadorCreateRecord) {
        Optional<Jogador> jogador = this.jogadorRepository.findById(jogadorId);
        Optional<Time> time = this.timeRepository.findById(timeId);
        if (jogador.isEmpty()) {
            throw new RuntimeException("Jogador não encontrado com ID: " + jogadorId);
        }
        if (time.isEmpty()) {
            throw new RuntimeException("Time não encontrado com ID: " + timeId);
        }
        TimeJogador timeJogador = new TimeJogador(
                null,
                time.get(),
                jogador.get(),
                timeJogadorCreateRecord.salario(),
                timeJogadorCreateRecord.dtIniContrato(),
                timeJogadorCreateRecord.dtFimContrato(),
                false
        );
        return this.timeJogadorRepository.save(timeJogador);
    }

    @Transactional
    public Time removerJogadorDoTime(Long timeJogadorId) {
        Optional<TimeJogador> timeJogador = timeJogadorRepository.findById(timeJogadorId);
                if(timeJogador.isEmpty()){
                    throw new RuntimeException("TimeJogador não encontrado com ID: " + timeJogadorId);
                }
                    TimeJogador jogadorRemovido = timeJogador.get();
                    jogadorRemovido.setContratoRompido(true);
                    this.timeJogadorRepository.save(jogadorRemovido);

                    return jogadorRemovido.getTime();
    }

//    @Transactional
//    public Optional<Time> alterar(Long id, TimeCreateRecord time) {}

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
            LocalDate dataFundacao,
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

    }
//    private Jogador mapperJogador(JogadorDTO jogadorDTO) {
//        Jogador newJogador = new Jogador(null, jogadorDTO.getNome(), jogadorDTO.getPosicao(), jogadorDTO.getDataNascimento(), jogadorDTO.getNacionalidade(),jogadorDTO.getPeso(), jogadorDTO.getAltura());
//    }
}
