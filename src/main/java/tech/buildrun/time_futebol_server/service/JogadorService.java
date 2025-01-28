//package tech.buildrun.time_futebol_server.service;
//
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfiguration;
//import org.springframework.stereotype.Service;
//import tech.buildrun.time_futebol_server.dto.JogadorDTO;
//import tech.buildrun.time_futebol_server.entity.Jogador;
//import tech.buildrun.time_futebol_server.record.JogadorCreateRecord;
//import tech.buildrun.time_futebol_server.repository.JogadorRepository;
//import tech.buildrun.time_futebol_server.repository.impl.JogadorRepositoryImpl;
//
//import java.util.Optional;
//
//@Service
//public class JogadorService {
//    final JogadorRepository jogadorRepository;
//
//    @Autowired
//    private JogadorRepositoryImpl jogadorRepositoryImpl;
//
//    public JogadorService(JogadorRepository jogadorRepository) {
//        this.jogadorRepository = jogadorRepository;
//    }
//
//    @Transactional
//    public Optional<Jogador> alterar(Jogador jogador) {
//        Optional<Jogador> optional = jogadorRepository.findById(jogador.getId());
//        optional.get().setNome(jogador.getNome());
//        optional.get().setPosicao(jogador.getPosicao());
//        optional.get().setNacionalidade(jogador.getNacionalidade());
//        optional.get().setPeso(jogador.getPeso());
//        optional.get().setAltura(jogador.getAltura());
//        return Optional.ofNullable(this.jogadorRepository.save(optional.get()));
//    }
//
//    @Transactional
//    public Optional<Jogador> salvar(JogadorCreateRecord jogador) {
//        Jogador jogadorToSave = new Jogador();
//        jogadorToSave.setNome(jogador.nome());
//        jogadorToSave.setPosicao(jogador.posicao());
//        jogadorToSave.setNacionalidade(jogador.nacionalidade());
//        jogadorToSave.setPeso(jogador.peso());
//        jogadorToSave.setAltura(jogador.altura());
//        return Optional.of(this.jogadorRepository.save(jogadorToSave));
//    }
//
//    @Transactional
//    public Optional<Jogador> salvar(Jogador jogador) {
//       return Optional.ofNullable(jogadorRepository.save(jogador));
//    }
//
//}
