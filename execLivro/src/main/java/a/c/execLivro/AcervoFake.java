package a.c.execLivro;

import java.util.List;

public class AcervoFake implements IAcervo {

    @Override
    public List<Livro> listarLivros() {
        return List.of(
                new Livro(1, "O Senhor dos Anéis", "J.R.R. Tolkien", 1954),
                new Livro(2, "1984", "George Orwell", 1949),
                new Livro(3, "O Pequeno Príncipe", "Antoine de Saint-Exupéry", 1943));
    }

    @Override
    public List<String> listaAutores() {
        return listarLivros().stream()
                .map(Livro::getAutor)
                .distinct()
                .toList();
    }

    @Override
    public List<String> LivrosAutorAno(String autor, int ano) {
        return listarLivros().stream()
                .filter(l -> l.getAutor().equalsIgnoreCase(autor) && l.getAno() == ano)
                .map(Livro::getTitulo)
                .toList();
    }

    @Override
    public void adicionar(Livro livro) {
        throw new UnsupportedOperationException("AcervoFake é somente leitura, não suporta adicionar livros.");
    }
}
