package com.wells.desafio_literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;
    private String publishedDate;
    private String authors;
    private String categories;

    public Livro(DadosLivro livroDados) {
        this.title = livroDados.titulo();
        this.subtitle = livroDados.subtitulo();
        this.publishedDate = livroDados.dataLancamento();
        if (!livroDados.listaAutores().isEmpty()) {
            this.authors = livroDados.listaAutores().getFirst().toLowerCase();
        } else {

            this.authors = "no author";
        }
        ;
        if (!livroDados.listaCategorias().isEmpty()) {
            this.categories = livroDados.listaCategorias().getFirst().toLowerCase();
        } else {
            this.categories = "Sem categoria";
        }
    }

    // default constructor
    public Livro() {

    }

    public Livro(String title, String subtitle, List<String> authors, String releaseDate, List<String> categories) {
        this.title = title;
        this.subtitle = subtitle;
        this.publishedDate = releaseDate.toLowerCase();
        this.authors = authors.get(0).toLowerCase();
        this.categories = categories.get(0).toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", publishedDate='" + publishedDate + '\'' +
                ", authors=" + authors +
                ", categories=" + categories +
                '}';
    }
}
