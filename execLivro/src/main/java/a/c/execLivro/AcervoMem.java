package a.c.execLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class AcervoMem implements IAcervo {
    private final LivrosRepository livrosRepository;

    @Autowired
    public AcervoMem(LivrosRepository livrosRepository){
        this.livrosRepository = livrosRepository;
    }

    @Override
    public List<Livro> listarLivros(){
        return livrosRepository.findAll();
    }

    @Override
    public List<String> LivrosAutorAno(String autor, int ano){
        return livrosRepository.findAll().stream()
            .filter(l -> l.getAno() == ano)
            .map(Livro::getTitulo)
            .toList();
    }

    @Override
    public List<String> listaAutores(){
        return livrosRepository.findAll().stream()
        .map(Livro::getAutor)
        .distinct()
        .toList();
    }

    @Override
    public void adicionar(Livro livro){
        livrosRepository.save(livro);
    }
}
