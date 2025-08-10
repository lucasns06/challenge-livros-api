package com.alura.lucans.livroapi.repository;

import com.alura.lucans.livroapi.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT l FROM Livro l WHERE :idioma MEMBER OF l.idiomas")
    List<Livro> findByIdioma(String idioma);
}
