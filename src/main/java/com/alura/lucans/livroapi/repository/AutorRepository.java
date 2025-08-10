package com.alura.lucans.livroapi.repository;

import com.alura.lucans.livroapi.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT DISTINCT a FROM Autor a LEFT JOIN FETCH a.livros")
    List<Autor> findAllComLivros();
    Optional<Autor> findByNome(String nome);
}
