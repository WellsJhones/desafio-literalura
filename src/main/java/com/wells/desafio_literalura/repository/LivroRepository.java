package com.wells.desafio_literalura.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wells.desafio_literalura.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE l.title LIKE CONCAT('%', ?1, '%')")
    List<Livro> findByTitleContaining(String title);

    @Query("SELECT l FROM Livro l WHERE l.categories LIKE CONCAT('%', ?1, '%')")
    List<Livro> findByCategoriesContaining(String category);

    @Query("SELECT l FROM Livro l WHERE l.title = ?1")
    Optional<Livro> findByTitle(String title);

    @Query("SELECT l FROM Livro l WHERE l.authors LIKE CONCAT('%', ?1, '%')")
    List<Livro> findByAuthorsContaining(String author);

}
