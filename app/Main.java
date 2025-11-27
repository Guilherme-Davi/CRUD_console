package app;

import model.Filme;
import repository.FilmeRepository;
import service.FilmeService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        FilmeRepository filmeRepository = new FilmeRepository();
        FilmeService filmeService = new FilmeService(filmeRepository);

        int opcao;

        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarFilme(filmeService);
                case 2 -> listarFilmes(filmeService);
                case 3 -> atualizarFilme(filmeService);
                case 4 -> excluirFilme(filmeService);
                case 5 -> System.out.println("Saindo do sistema... Até mais!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }

            System.out.println();

        } while (opcao != 5);

        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("==== SISTEMA DE LISTA DE FILMES ====");
        System.out.println("1 - Cadastrar filme");
        System.out.println("2 - Listar filmes");
        System.out.println("3 - Atualizar filme");
        System.out.println("4 - Excluir filme");
        System.out.println("5 - Sair");
        System.out.println("====================================");
    }

    private static void cadastrarFilme(FilmeService filmeService) {
        System.out.println("=== Cadastro de Filme ===");
        System.out.print("Título do filme: ");
        String titulo = scanner.nextLine();

        System.out.print("Descrição (sinopse/gênero/observações): ");
        String descricao = scanner.nextLine();

        LocalDate dataConclusao = lerData("Data planejada para assistir (dd/MM/yyyy): ");

        try {
            Filme filme = filmeService.criarFilme(titulo, descricao, dataConclusao);
            System.out.println("Filme cadastrado com sucesso! ID: " + filme.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar filme: " + e.getMessage());
        }
    }

    private static void listarFilmes(FilmeService filmeService) {
        System.out.println("=== Lista de Filmes ===");
        List<Filme> filmes = filmeService.listarFilmes();

        if (filmes.isEmpty()) {
            System.out.println("Nenhum filme cadastrado.");
        } else {
            filmes.forEach(System.out::println);
        }
    }

    private static void atualizarFilme(FilmeService filmeService) {
        System.out.println("=== Atualização de Filme ===");

        int id = lerInteiro("Informe o ID do filme a ser atualizado: ");

        Optional<Filme> filmeOptional = filmeService.buscarPorId(id);
        if (filmeOptional.isEmpty()) {
            System.out.println("Filme não encontrado.");
            return;
        }

        Filme filme = filmeOptional.get();
        System.out.println("Filme atual:");
        System.out.println(filme);

        System.out.print("Novo título (deixe em branco para manter): ");
        String novoTitulo = scanner.nextLine();
        if (novoTitulo.isBlank()) {
            novoTitulo = filme.getTitulo();
        }

        System.out.print("Nova descrição (deixe em branco para manter): ");
        String novaDescricao = scanner.nextLine();
        if (novaDescricao.isBlank()) {
            novaDescricao = filme.getDescricao();
        }

        System.out.print("Nova data (dd/MM/yyyy) (deixe em branco para manter): ");
        String inputData = scanner.nextLine();
        LocalDate novaData;
        if (inputData.isBlank()) {
            novaData = filme.getDataConclusao();
        } else {
            novaData = parseData(inputData);
            if (novaData == null) {
                System.out.println("Data inválida. Atualização cancelada.");
                return;
            }
        }

        boolean sucesso = filmeService.atualizarFilme(id, novoTitulo, novaDescricao, novaData);
        if (sucesso) {
            System.out.println("Filme atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar filme.");
        }
    }

    private static void excluirFilme(FilmeService filmeService) {
        System.out.println("=== Exclusão de Filme ===");
        int id = lerInteiro("Informe o ID do filme a ser excluído: ");

        boolean sucesso = filmeService.excluirFilme(id);
        if (sucesso) {
            System.out.println("Filme excluído com sucesso!");
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }

    private static LocalDate lerData(String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String dataStr = scanner.nextLine();
            LocalDate data = parseData(dataStr);
            if (data != null) {
                return data;
            }
            System.out.println("Data inválida. Use o formato dd/MM/yyyy.");
        }
    }

    private static LocalDate parseData(String dataStr) {
        try {
            return LocalDate.parse(dataStr, FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }
}
