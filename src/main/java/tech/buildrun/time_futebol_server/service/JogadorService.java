package tech.buildrun.time_futebol_server.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tech.buildrun.time_futebol_server.entity.Jogador;
import tech.buildrun.time_futebol_server.entity.Time;
import tech.buildrun.time_futebol_server.record.JogadorCreateRecord;
import tech.buildrun.time_futebol_server.repository.JogadorRepository;
import tech.buildrun.time_futebol_server.repository.TimeRepository;
import tech.buildrun.time_futebol_server.repository.impl.JogadorRepositoryImpl;
//import tech.buildrun.time_futebol_server.repository.impl.JogadorRepositoryImpl;

import java.util.List;
import java.util.Optional;

@Service
public class JogadorService {
    final JogadorRepository jogadorRepository;

    @Autowired
    private TimeRepository timeRepository;

    @Autowired
    private JogadorRepositoryImpl jogadorRepositoryImpl;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    @Transactional
    public Optional<Jogador> alterar(Jogador jogador) {
        Optional<Jogador> optional = jogadorRepository.findById(jogador.getId());
        optional.get().setNome(jogador.getNome());
        optional.get().setPosicao(jogador.getPosicao());
        optional.get().setNacionalidade(jogador.getNacionalidade());
        optional.get().setPeso(jogador.getPeso());
        optional.get().setAltura(jogador.getAltura());
        return Optional.of(this.jogadorRepository.save(optional.get()));
    }

    @Transactional
    public Optional<Jogador> salvar(JogadorCreateRecord jogador) {
        Jogador jogadorToSave = new Jogador();
        jogadorToSave.setNome(jogador.nome());
        jogadorToSave.setPosicao(jogador.posicao());
        jogadorToSave.setDataNascimento(jogador.dataNascimento());
        jogadorToSave.setNacionalidade(jogador.nacionalidade());
        jogadorToSave.setPeso(jogador.peso());
        jogadorToSave.setAltura(jogador.altura());
        return Optional.of(this.jogadorRepository.save(jogadorToSave));
    }

    @Transactional
    public Optional<Jogador> excluir(Long id) {
        Optional<Jogador> funcaoOptional = jogadorRepository.findById(id);
        funcaoOptional.ifPresent(this.jogadorRepository::delete);
        return funcaoOptional;
    }

    public Optional<Jogador> getById(Long id) {
        return jogadorRepository.findById(id);
    }

    public List<Jogador> findAll() {
        return jogadorRepository.findAll();
    }

//    @Transactional
//    public Optional<Jogador> salvar(Jogador jogador) {
//        return Optional.of(jogadorRepository.save(jogador));
//    }
//
//    @Transactional
//    public List<Jogador> listarJogadoresDoTime(Long id) {
//        Time time = timeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));
//        return time.getJogadores();
//    }

    public Page<Jogador> pesquisa(
            Pageable pageable,
            String nome,
            String posicao,
            String dataNascimento,
            String nacionalidade,
            Double peso,
            Double altura
    ) {
        return jogadorRepositoryImpl.findAllByFiltro(
                pageable,
                nome,
                posicao,
                dataNascimento,
                nacionalidade,
                peso,
                altura
        );
    }
}
