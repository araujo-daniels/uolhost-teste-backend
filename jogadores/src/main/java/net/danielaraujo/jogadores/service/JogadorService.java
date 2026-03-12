package net.danielaraujo.jogadores.service;

import lombok.RequiredArgsConstructor;
import net.danielaraujo.jogadores.client.LigaJusticaClient;
import net.danielaraujo.jogadores.client.VingadoresClient;
import net.danielaraujo.jogadores.model.Jogador;
import net.danielaraujo.jogadores.repository.JogadorRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JogadorService {
    public static final String OS_VINGADORES = "Os Vingadores";
    public static final String LIGA_DA_JUSTICA = "Liga da Justiça";

    private final LigaJusticaClient ligaJusticaClient;
    private final VingadoresClient vingadoresClient;
    private final JogadorRepository repository;

    public Jogador cadastrarJogador(JogadorCreateRequest jogadorRequest) throws IOException {
        Jogador jogador = jogadorRequest.toJogador();
        String codinome = buscarCodinomeDisponivel(jogador.getGrupo());
        jogador.setCodinome(codinome);
        repository.save(jogador);
        return jogador;
    }

    public List<Jogador> buscarTodos() {
        return (List<Jogador>) repository.findAll();
    }

    public Optional<Jogador> buscarJogador(Integer id) {
        return repository.findById(id);
    }

    public Jogador atualizarJogador(Integer id, JogadorUpdateRequest jogadorUpdateRequest) {
        Jogador jogador = buscarJogador(id).orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        atualizarInformacoes(jogador, jogadorUpdateRequest);
        repository.save(jogador);
        return jogador;
    }

    public void excluirJogador(Integer id) {
        Jogador jogador = buscarJogador(id).orElseThrow(() -> new RuntimeException("Jogador não encontrado"));
        repository.delete(jogador);
    }

    private void atualizarInformacoes(Jogador jogador, JogadorUpdateRequest jogadorUpdateRequest) {
        jogador.setNome(jogadorUpdateRequest.getNome());
        jogador.setEmail(jogadorUpdateRequest.getEmail());
        jogador.setTelefone(jogadorUpdateRequest.getTelefone());
    }

    private String buscarCodinomeDisponivel(String grupo) throws IOException {
        List<String> codinomesGrupo = new ArrayList<>();
        List<String> codinomesUtilizados;

        if (OS_VINGADORES.equals(grupo)) {
            codinomesGrupo = vingadoresClient.getCodinomes();
        } else if (LIGA_DA_JUSTICA.equals(grupo)) {
            codinomesGrupo = ligaJusticaClient.getCodinomes();
        }

        codinomesUtilizados = repository.findCodinomeByGrupo(grupo);
        List<String> codinomesDisponiveis = new ArrayList<>(codinomesGrupo);
        codinomesDisponiveis.removeAll(codinomesUtilizados);

        if (codinomesDisponiveis.isEmpty()) {
            throw new RuntimeException("Não há codinomes disponíveis para o grupo " + grupo);
        }

        return codinomesDisponiveis.get(0);
    }
}
