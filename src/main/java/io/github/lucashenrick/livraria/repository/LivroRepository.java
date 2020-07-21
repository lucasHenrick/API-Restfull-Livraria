package io.github.lucashenrick.livraria.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.lucashenrick.livraria.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
