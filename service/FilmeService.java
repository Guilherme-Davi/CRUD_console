package service;

import model.Filme;
import repository.FilmeRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FilmeService{

    private final FilmeRepository filmeRepository;

    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    public Filme criarFilme(String titulo, String descricao, LocalDate dataConclusao) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("Título do filme não pode ser vazio.");
        }

        Filme filme = new Filme(titulo, descricao, dataConclusao);
        return filmeRepository.salvar(filme);
    }

    public List<Filme> listarFilmes() {
        return filmeRepository.listarTodos();
    }

    public Optional<Filme> buscarPorId(int id) {
        return filmeRepository.buscarPorId(id);
    }

    public boolean atualizarFilme(int id, String novoTitulo, String novaDescricao, LocalDate novaDataConclusao) {
        Optional<Filme> filmeOptional = filmeRepository.buscarPorId(id);

        if (filmeOptional.isEmpty()) {
            return false;
        }

        Filme filme = filmeOptional.get();
        filme.setTitulo(novoTitulo);
        filme.setDescricao(novaDescricao);
        filme.setDataConclusao(novaDataConclusao);

        return filmeRepository.atualizar(filme);
    }

    public boolean excluirFilme(int id) {
        return filmeRepository.deletar(id);
    }
}
