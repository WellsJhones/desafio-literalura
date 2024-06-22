package com.wells.desafio_literalura.principal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.wells.desafio_literalura.model.Livro;
import com.wells.desafio_literalura.repository.LivroRepository;
import com.wells.desafio_literalura.service.ConsumoApi;

public class Principal {
    private LivroRepository repository;
    private ConsumoApi consumoApi = new ConsumoApi();

    private Scanner leitura = new Scanner(System.in);

    public Principal(LivroRepository repository) {
        this.repository = repository;
    }

    public void exibeMenu() throws JsonProcessingException {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro na web
                    2 - Listar livros buscados
                    3 - Buscar por titulo em livros salvos
                    4 - Buscar por categoria em livros salvos
                    5 - Buscar por autor na base de dados


                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    buscarLivrosWeb();
                    break;
                case 2:
                    listarLivros();
                    break;
                case 3:
                    buscarLivrosSalvos();
                    break;
                case 4:
                    buscarLivrosPelaCategoriaSalvos();
                    break;
                case 5:
                    buscarPorAutorNaBaseDeDados();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    opcao = 0;
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void buscarPorAutorNaBaseDeDados() {
        System.out.println("Qual o nome do autor?");
        String autor = leitura.nextLine();
        List<Livro> livros = repository.findByAuthorsContaining(autor);

        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("Livro não encontrado");
        }
    }

    private void buscarLivrosPelaCategoriaSalvos() {
        System.out.println("Qual a categoria?");
        String categoria = leitura.nextLine();
        List<Livro> livros = repository.findByCategoriesContaining(categoria);

        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("Livro não encontrado");
        }
    }

    private void buscarLivrosSalvos() {
        String title = leitura.nextLine();
        List<Livro> livros = repository.findByTitleContaining(title);

        if (!livros.isEmpty()) {
            livros.forEach(System.out::println);
        } else {
            System.out.println("Livro não encontrado");
        }
    }

    private void buscarLivrosWeb() throws JsonProcessingException {
        String dados = getDadosLivros();
        JSONObject responseObj = new JSONObject(dados);
        JSONArray bookArray;
        // check if the response has the items key
        if (!responseObj.has("items")) {
            System.out.println("Livro não encontrado");
            return;
        }

        try {
            bookArray = responseObj.getJSONArray("items");
            if (bookArray.length() == 0) {
                System.out.println("Livro não encontrado");
                return;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JSONObject book = bookArray.getJSONObject(0).getJSONObject("volumeInfo");
        String title = book.getString("title");
        System.out.println(title);

        String subtitle;
        if (book.has("subtitle")) {
            subtitle = book.getString("subtitle");
            System.out.println(subtitle);
        } else {
            subtitle = "Sem subtítulo";
        }

        List<String> authors;
        String releaseDate = book.getString("publishedDate").substring(0, 4);
        if (!book.has("authors")) {
            authors = new ArrayList<>();
            authors.add("no author");

        } else {
            JSONArray authorsArray = book.getJSONArray("authors");
            authors = new ArrayList<>();
            for (int i = 0; i < authorsArray.length(); i++) {
                authors.add(authorsArray.getString(i));
            }
        }
        System.out.println(authors);
        List<String> categories = new ArrayList<>();
        if (book.has("categories")) {
            JSONArray categoriesArray = book.getJSONArray("categories");
            for (int i = 0; i < categoriesArray.length(); i++) {
                categories.add(categoriesArray.getString(i));
            }
        } else {
            categories.add("Sem categoria");
        }
        System.out.println(categories);
        Livro livro = new Livro(title.toLowerCase(), subtitle.toLowerCase(), authors, releaseDate.toLowerCase(),
                categories);
        Optional<Livro> livroSalvo = repository.findByTitle(livro.getTitle().toLowerCase());
        if (!livroSalvo.isPresent()) {
            repository.save(livro);
        } else {
            System.out.println("Livro já salvo");
        }

    }

    private String getDadosLivros() throws JsonProcessingException {
        System.out.println("Qual o nome do livro?");
        String nome = leitura.nextLine();
        nome = nome.replace(" ", "+");
        return consumoApi.obterDados(nome);

    }

    private void listarLivros() {
        List<Livro> livros = repository.findAll();
        livros.forEach(System.out::println);
    }
}
