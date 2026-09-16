package a.c.execLivro;

import java.util.List;
import java.util.Optional;

public interface IAcervo {
    List<Livro> listarLivros();
    List<String> listaAutores();
    List<String> livrosAutorAno(String autor, int ano);
    void adicionar(Livro livro);
    boolean remover(long id);
    Optional<Livro> atualizar(long id, Livro dadosNovos);
}
