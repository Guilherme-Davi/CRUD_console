package repository;

import model.Filme;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FilmeRepository{

    private final List<Filme> filmes = new ArrayList<>();
    private int nextId = 1; // id automatico como no crud do carro

    public Filme salvar(Filme filme) {
        filme.setId(nextId++);
        filmes.add(filme);
        return filme;
    }

    public List<Filme> listarTodos() {
        return new ArrayList<>(filmes);
    }

    public Optional<Filme> buscarPorId(int id) {
        return filmes.stream()
                .filter(f -> f.getId() == id)
                .findFirst();
    }

    public boolean atualizar(Filme filmeAtualizado) {
        Optional<Filme> filmeOptional = buscarPorId(filmeAtualizado.getId());

        if (filmeOptional.isPresent()) {
            Filme filmeExistente = filmeOptional.get();
            filmeExistente.setTitulo(filmeAtualizado.getTitulo());
            filmeExistente.setDescricao(filmeAtualizado.getDescricao());
            filmeExistente.setDataConclusao(filmeAtualizado.getDataConclusao());
            return true;
        }
        return false;
    }

    public boolean deletar(int id) {
        return filmes.removeIf(f -> f.getId() == id);
    }
}
