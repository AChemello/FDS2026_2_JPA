package a.c.execLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;


@RestController
public class UsuarioController {
    private final AcervoMem acervoMem;
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository, LivroRepository livroRepository, AcervoMem acervoMem) {
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
        this.acervoMem = acervoMem;
    }

    @PostMapping("/usuarios")
    @CrossOrigin(origins = "*")
    public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody NovoUsuarioDTO novoUsuarioDTO) {
        Usuario usuario = new Usuario(novoUsuarioDTO.id(), novoUsuarioDTO.nome());
        usuarioRepository.save(usuario);
        return ResponseEntity
            .created(URI.create("/usuarios/" + usuario.getId()))
            .body(UsuarioDTO.from(usuario));
    }

    @GetMapping ("/usuarios/{usuarioId}")
    @CrossOrigin (origins = "*")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
            .map(UsuarioDTO::from)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuarios/{usuarioId}/livrosLidos")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<LivroResumoDTO>> listarLivrosLidos(@PathVariable Long usuarioId) {
        Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
        if(usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<LivroResumoDTO> lista = usuario.get().getLivrosLidos().stream()
            .map(LivroResumoDTO::from)
            .toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/usuarios/{usuarioId}/livrosLidos/{livroId}")
    @CrossOrigin (origins = "*")
    public ResponseEntity<Void> adicionarLivroLido(@PathVariable Long usuarioId, @PathVariable Long livroId) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);
        Optional<Livro> livroOpt = livroRepository.findById(livroId);
        if(usuarioOpt.isEmpty() || livroOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario usuario = usuarioOpt.get();
        Livro livro = livroOpt.get();

        if(!usuario.getLivrosLidos().contains(livro)) {
            usuario.getLivrosLidos().add(livro);
            usuarioRepository.save(usuario);
        }
        return ResponseEntity.noContent().build();
    }
}
