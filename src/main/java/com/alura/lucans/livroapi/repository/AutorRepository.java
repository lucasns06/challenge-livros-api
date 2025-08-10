package com.alura.lucans.livroapi.repository;

import com.alura.lucans.livroapi.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {
}
