package a.c.execLivro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AutorController {
    private final IAcervo acervo;
    private final EstatisticaAutor estatisticaAutor;

    @Autowired
    public AutorController(IAcervo acervo, EstatisticaAutor estatisticaAutor) {
        this.acervo = acervo;
        this.estatisticaAutor = estatisticaAutor;
    }

    @GetMapping("autores")
    @CrossOrigin(origins = "*")
    public List<String> getListaAutores() {
        return acervo.listaAutores();
    }

    @GetMapping("livros/autor/{autor}/ano/{ano}")
    @CrossOrigin(origins = "*")
    public List<String> getLivrosByAutorAndAno(@PathVariable String autor, @PathVariable int ano) {
        estatisticaAutor.registrarLivro(autor);
        return acervo.LivrosAutorAno(autor, ano);
    }

    @GetMapping("autorMaisConsultado")
    @CrossOrigin(origins = "*")
    public Optional<String> getAutorMaisConsultado() {
        return estatisticaAutor.autorMaisConsultado();
    }

    @GetMapping("autorMenosConsultado")
    @CrossOrigin(origins = "*")
    public Optional<String> getAutorMenosConsultado() {
        return estatisticaAutor.autorMenosConsultado();
    }

    @GetMapping("todasAsContagens")
    @CrossOrigin(origins = "*")
    public Map<String, Integer> todasAsContagens() {
        return estatisticaAutor.todasAsContagens();
    }
}
