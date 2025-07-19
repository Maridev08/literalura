package br.com.projeto.literalura.repository;

import br.com.projeto.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro,Long> {
    Optional<Livro> findByTitulo(String titulo);

    List<Livro>findByIdiomaContainingIgnoreCase(String idioma);

    List<Livro> findTop5ByOrderByDowloadsDesc();
}
