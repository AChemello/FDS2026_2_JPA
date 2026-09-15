package a.c.execLivro;

import java.util.List;

public interface IAcervo{
    List<Livro> listarLivros();
    List<String> listaAutores();
    List<String> LivrosAutorAno(String autor, int ano);
    void adicionar(Livro livro);
}
