package a.c.execLivro;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AcervoFake implements IAcervo {

    private final Autor tolkien = new Autor(101, "J.R.R. Tolkien", LocalDate.of(1892, 1, 3));
    private final Autor orwell = new Autor(102, "George Orwell", LocalDate.of(1903, 6, 25));
    private final Autor saintExupery = new Autor(103, "Antoine de Saint-Exupéry", LocalDate.of(1900, 6, 29));

    @Override
    public List<Livro> listarLivros() {
        return List.of(
                new Livro(1, "O Senhor dos Anéis", tolkien, 1954),
                new Livro(2, "1984", orwell, 1949),
                new Livro(3, "O Pequeno Príncipe", saintExupery, 1943));
    }

    @Override
    public List<String> listaAutores() {
        return listarLivros().stream()
                .map(l -> l.getAutor().getNome())
                .distinct()
                .toList();
    }

    @Override
    public List<String> livrosAutorAno(String autor, int ano) {
        return listarLivros().stream()
                .filter(l -> l.getAutor().getNome().equalsIgnoreCase(autor) && l.getAno() == ano)
                .map(Livro::getTitulo)
                .toList();
    }

    @Override
    public void adicionar(Livro livro) {
        throw new UnsupportedOperationException("AcervoFake é somente leitura, não suporta adicionar livros.");
    }

    @Override
    public boolean remover(long id) {
        throw new UnsupportedOperationException("AcervoFake é somente leitura, não suporta remover livros.");
    }

    @Override
    public Optional<Livro> atualizar(long id, Livro dadosNovos) {
        throw new UnsupportedOperationException("AcervoFake é somente leitura, não suporta atualizar livros.");
    }
}
