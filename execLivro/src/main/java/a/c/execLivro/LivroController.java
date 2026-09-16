package a.c.execLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class LivroController {
    private final IAcervo acervo;

    @Autowired
    public LivroController(IAcervo acervo) {
        this.acervo = acervo;
    }

    @GetMapping("")
    @CrossOrigin(origins = "*")
    public String mensagemDeBemVindo() {
        return "Bem vindo a biblioteca central!";
    }

    @GetMapping("livros")
    @CrossOrigin(origins = "*")
    public List<Livro> getListaLivros() {
        return acervo.listarLivros();
    }

    @GetMapping("livros/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> getLivroById(@PathVariable long id) {
        Optional<Livro> livro = acervo.listarLivros().stream()
                .filter(l -> l.getId() == id)
                .findFirst();
        return livro.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("livros")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
        acervo.adicionar(livro);
        return ResponseEntity
                .created(URI.create("/livros/" + livro.getId()))
                .body(livro);
    }

    @DeleteMapping("livros/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Void> removerLivro(@PathVariable long id) {
        return acervo.remover(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @PutMapping("livros/{id}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable long id, @RequestBody Livro livro) {
        return acervo.atualizar(id, livro)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
