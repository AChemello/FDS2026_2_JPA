package a.c.execLivro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByAutor(Autor autor);
    List<Livro> findByAutorNome(String nomeAutor);
    Livro findByTitulo(String titulo);
}
