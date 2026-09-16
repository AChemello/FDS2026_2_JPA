package a.c.execLivro;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class AcervoMem implements IAcervo {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public AcervoMem(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    @PostConstruct
    public void init() {
        if (autorRepository.count() == 0) {
            Autor huguinho = autorRepository.save(new Autor(1, "Huguinho Pato", LocalDate.of(1980, 1, 1)));
            Autor maga = autorRepository.save(new Autor(2, "Maga Pato", LocalDate.of(1975, 5, 20)));

            livroRepository.save(new Livro(10, "Introdução ao Java", huguinho, 2022));
            livroRepository.save(new Livro(20, "Spring Boot na prática", maga, 2023));
            livroRepository.save(new Livro(25, "Usando JPA", huguinho, 2024));
        }
    }

    @Override
    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    @Override
    public List<String> listaAutores() {
        return autorRepository.findAll().stream()
                .map(Autor::getNome)
                .distinct()
                .toList();
    }

    @Override
    public List<String> livrosAutorAno(String autor, int ano) {
        return livroRepository.findByAutorNome(autor).stream()
                .filter(l -> l.getAno() == ano)
                .map(Livro::getTitulo)
                .toList();
    }

    @Override
    public void adicionar(Livro livro) {
        livroRepository.save(livro);
    }

    @Override
    public boolean remover(long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Optional<Livro> atualizar(long id, Livro dadosNovos) {
        return livroRepository.findById(id).map(livroExistente -> {
            livroExistente.setTitulo(dadosNovos.getTitulo());
            livroExistente.setAno(dadosNovos.getAno());
            if (dadosNovos.getAutor() != null) {
                autorRepository.findById(dadosNovos.getAutor().getId())
                        .ifPresent(livroExistente::setAutor);
            }
            return livroRepository.save(livroExistente);
        });
    }
}
