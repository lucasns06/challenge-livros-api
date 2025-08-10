package com.alura.lucans.livroapi.repository;

import com.alura.lucans.livroapi.modelo.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
